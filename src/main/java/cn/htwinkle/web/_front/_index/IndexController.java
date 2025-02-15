package cn.htwinkle.web._front._index;

import cn.htwinkle.web._front.picture.PictureService;
import cn.htwinkle.web.base.BaseController;
import cn.htwinkle.web.constants.Constants;
import cn.htwinkle.web.interceptor.GolbalInterceptor;
import cn.htwinkle.web.model.Config;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PropKit;
import org.leon.swagger.annotation.Api;
import org.leon.swagger.annotation.ApiOperation;
import org.leon.swagger.model.constant.HttpMethod;


/**
 * 主页类型
 *
 * @author : twinkle
 * @date : 2020/3/9 21:46
 */
@Api(tag = IndexController.TAG, description = "首页")
public class IndexController extends BaseController {

    protected static final String TAG = "INDEX";

    @Inject
    private PictureService pictureService;

    @Override
    public void index() {
        setTitle(PropKit.get(Constants.RECORD_TITLE));
        set("picArr", pictureService.getPictureListIndexBy());
        set("indexPage", true);
        set("globalCount", GolbalInterceptor.GLOBAL_COUNTER.get());
        render("index.html");
    }

    @ApiOperation(url = "/app", tag = IndexController.TAG, httpMethod = HttpMethod.GET, description = "获取当前最新app")
    public void app() {
        Config configs = Config.dao.findFirst("SELECT * FROM config WHERE config_name=? LIMIT 1", "GITHUB_PROXY_URL");
        String proxy = configs != null && StrUtil.isNotEmpty(configs.getConfigValue()) ? configs.getConfigValue() : "";
        String result = HttpUtil.get("https://api.github.com/repos/H-TWINKLE/htwinkle.cn.app/releases/latest");
        String downloadUrl = "";
        if (StrUtil.isNotBlank(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray assets = jsonObject.getJSONArray("assets");
            for (int i = 0; i < assets.size(); i++) {
                String name = assets.getJSONObject(i).getString("name");
                if (StrUtil.isNotEmpty(name) && name.contains("signed")) {
                    downloadUrl = assets.getJSONObject(i).getString("browser_download_url");
                }
            }
        }
        if (StrUtil.isNotEmpty(downloadUrl)) {
            redirect(proxy + downloadUrl);
        } else {
            renderJson(Kv.create().set("msg", "获取下载地址失败"));
        }
    }
}
