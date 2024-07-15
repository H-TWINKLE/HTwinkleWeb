package cn.htwinkle.web._front._config;

import cn.htwinkle.web.base.BaseController;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;


/**
 * 配置列表类型
 *
 * @author : twinkle
 * @date : 2020/3/9 21:46
 */
public class ConfigController extends BaseController {

    @Inject
    private ConfigService configService;

    @Override
    public void index() {
        renderJson(Ret.ok().set("list", configService.getList()));
    }
}
