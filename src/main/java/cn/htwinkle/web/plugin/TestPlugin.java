package cn.htwinkle.web.plugin;

import cn.htwinkle.web.kit.EnvKit;
import cn.htwinkle.web.model._MappingKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

public class TestPlugin implements IPlugin {

    private Prop p;
    private ActiveRecordPlugin activeRecordPlugin;
    private DruidPlugin druidPlugin;

    /**
     * 作为plugin使用
     */
    public TestPlugin() {
        _init();
        init();
    }

    /**
     * 作为generate使用
     *
     * @param startFlag 是否直接启动druid缓存池
     */
    public TestPlugin(boolean startFlag) {
        if (startFlag) {
            _init();
            druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), getPassword());
            druidPlugin.start();
        } else {
            init();
        }
    }

    private String getPassword() {
        return EnvKit.INSTANCE.getEnvironmentValue(p.get("password"));
    }

    private void _init() {
        p = PropKit.use("config-dev.properties");
    }

    private void init() {
        druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), getPassword());
        activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.setShowSql(PropKit.getBoolean("devMode", false));
        _MappingKit.mapping(activeRecordPlugin);
    }


    public DataSource getDateSource() {
        return druidPlugin.getDataSource();
    }


    @Override
    public boolean start() {
        if (activeRecordPlugin == null || druidPlugin == null)
            return true;
        return druidPlugin.start() && activeRecordPlugin.start();
    }

    @Override
    public boolean stop() {
        return activeRecordPlugin.stop() && druidPlugin.stop();
    }


}
