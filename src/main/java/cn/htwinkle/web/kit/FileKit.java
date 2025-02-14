package cn.htwinkle.web.kit;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具类
 *
 * @author : twinkle
 * @date : 2020/3/15 16:37
 */
public class FileKit {
    /**
     * FileKit的输出日志对象
     */
    private static final Logger LOGGER = Logger.getLogger(FileKit.class.getName());

    /**
     * 复制文件的目录
     */
    public static final String BACKUP_FILE_PATH = "backUpFilePath";

    /**
     * 获取文件的大小
     *
     * @param size size
     * @return String
     */
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return size + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return size + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return (size / 100) + "." + (size % 100) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return (size / 100) + "." + size % 100 + "GB";
        }
    }

    /**
     * 复制文件
     *
     * @param file file
     * @return boolean
     */
    public static File toBackupFile(File file, String childPath) {
        if (!EnvKit.INSTANCE.isProEnviron()) {
            return null;
        }
        File baseBackUpDir = getBackupDir();
        if (baseBackUpDir == null) {
            return null;
        }
        File childDir = new File(baseBackUpDir, childPath);
        if (!childDir.exists()) {
            FileUtils.mkdir(childDir.getAbsolutePath());
        }
        try {
            File copyedFile = new File(childDir, file.getName());
            LOGGER.info("正在拷贝文件 : " + copyedFile.getAbsolutePath());
            FileUtils.copyFile(file, copyedFile);
            return copyedFile;
        } catch (IOException e) {
            LOGGER.error("error : " + ExceptionUtil.stacktraceToString(e));
        }
        return null;
    }

    /**
     * 获取备份的文件目录
     *
     * @return File
     */
    public static File getBackupDir() {
        String backUpPath = PropKit.get(BACKUP_FILE_PATH);
        if (StrKit.isBlank(backUpPath)) {
            return null;
        }
        File baseBackUpDir = new File(backUpPath);
        if (!baseBackUpDir.exists()) {
            FileUtils.mkdir(baseBackUpDir.getAbsolutePath());
        }
        return baseBackUpDir;
    }

}
