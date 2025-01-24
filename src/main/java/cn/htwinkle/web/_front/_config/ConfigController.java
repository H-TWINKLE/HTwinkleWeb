package cn.htwinkle.web._front._config;

import cn.htwinkle.web.base.BaseController;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Ret;
import org.leon.swagger.annotation.Api;
import org.leon.swagger.annotation.ApiOperation;
import org.leon.swagger.model.constant.HttpMethod;


/**
 * 配置列表类型
 *
 * @author : twinkle
 * @date : 2020/3/9 21:46
 */
@Api(tag = ConfigController.TAG, description = "配置中心")
public class ConfigController extends BaseController {

    protected static final String TAG = "CONFIG";

    @Inject
    private ConfigService configService;

    @Override
    @ApiOperation(url = "/config/", tag = ConfigController.TAG, httpMethod = HttpMethod.GET, description = "获取配置信息")
    public void index() {
        renderJson(Ret.ok().set("list", configService.getList()));
    }
}
