package cn.htwinkle.devotion._front.diao;

import cn.htwinkle.devotion.base.BaseService;
import cn.htwinkle.devotion.constants.Constants;
import cn.htwinkle.devotion.kit.FileKit;
import cn.htwinkle.devotion.model.User;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 地奥制药服务
 *
 * @author : twinkle
 * @date : 2020/3/15 14:11
 */
public class DiAoService extends BaseService {

    public static final String DI_AO_PATH = PathKit.getWebRootPath() + File.separator + PropKit.get(Constants.DIAO_PATH);

    public User toLogin(String admin, String pass) {
        User user = User.dao.findFirst("SELECT * FROM user " +
                "WHERE userAdmin=? LIMIT 1", admin);

        if (user == null)
            return null;

        String salt = user.getUserSalt();
        String realPass = getRealPass(salt, pass);
        if (!realPass.equals(user.getUserPass())) {
            return null;
        }

        user.removeSecretParams();
        return user;
    }

    private String getRealPass(String salt, String pass) {
        return HashKit.sha256(salt + pass);
    }

    /**
     * 所有的文件
     *
     * @return List<File>
     */
    public List<Kv> getAllFileFromDiAoPath() {

        File diAoPathFiles = new File(DI_AO_PATH);

        if (!diAoPathFiles.isDirectory()) {
            return null;
        }
        File[] files = diAoPathFiles.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        List<Kv> list = new ArrayList<>();

        for (File file : files) {
            list.add(Kv.create()
                    .set("name", file.getName())
                    .set("path", PropKit.get(Constants.DIAO_PATH) + file.getName())
                    .set("date", new Date(file.lastModified()))
                    .set("size", FileKit.getPrintSize(file.length())));
        }

        return list;
    }


    /**
     * 移动文件到指定的位置
     *
     * @param file file
     * @return boolean
     */
    public boolean fileRenameToDiAoPath(File file) {
        return file.renameTo(new File(DI_AO_PATH, file.getName()));

    }


}