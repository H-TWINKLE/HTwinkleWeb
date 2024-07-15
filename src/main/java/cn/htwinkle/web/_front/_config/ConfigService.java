package cn.htwinkle.web._front._config;

import cn.htwinkle.web.base.BaseService;
import cn.htwinkle.web.model.Config;

import java.util.List;

/**
 * 图片的服务层
 *
 * @author : twinkle
 * @date : 2020/3/15 11:38
 */
public class ConfigService extends BaseService {

    public List<Config> getList() {
        return Config.dao.findAll();
    }
}
