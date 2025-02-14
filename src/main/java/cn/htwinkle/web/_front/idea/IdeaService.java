package cn.htwinkle.web._front.idea;

import cn.htwinkle.web.base.BaseService;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * TODO 描述用途
 *
 * @author : twinkle
 * @date : 2020/9/27 20:26
 */
public class IdeaService extends BaseService {

    /**
     * IdeaService的输出日志对象
     */
    private static final Logger LOGGER = Logger.getLogger(IdeaService.class.getName());
    public static final String JIHUOMA_ZIP = "https://idea.medeming.com/a/jihuoma1.zip";
    public static final String LOOKDIV_COM_INDEX = "http://lookdiv.com/index/index/indexcode.html";

    /**
     * 获取所以的激活码
     *
     * @return Ret
     */
    public Ret getListCode(String filter, String key) {
        List<String> list = new ArrayList<>();
        Ret ret = getRetCode(key);
        if (ret.isOk()) {
            list.add(ret.getStr("code"));
        }
        ret = getZipFile(filter);
        if (ret.isOk()) {
            list.addAll(ret.getAs("list"));
        }
        if (list.size() > 0) {
            return Ret.ok().set("list", list);
        }
        return Ret.fail();
    }

    /**
     * 获取code
     *
     * @return Ret
     */
    public Ret getRetCode(String key) {
        try {
            Document doc = Jsoup.connect(LOOKDIV_COM_INDEX)
                    .data("key", key).post();
            Elements text = doc.getElementsByTag("textarea");
            if (text.size() > 0) {
                return Ret.create().setOk().set("code", text.get(0).text());
            } else {
                return Ret.create().setFail();
            }
        } catch (IOException e) {
            return Ret.create().setFail();
        }
    }

    /**
     * 获取压缩文件
     *
     * @return Ret
     */
    public Ret getZipFile(String filter) {
        Ret ret = Ret.create();
        try {
            Connection.Response connection =
                    Jsoup.connect(JIHUOMA_ZIP)
                            .ignoreContentType(true)
                            .execute();
            InputStream in = connection.bodyStream();
            try (ZipInputStream zipInputStream = new ZipInputStream(in, StandardCharsets.ISO_8859_1)) {
                List<String> list = new ArrayList<>();
                ZipEntry zipFile;
                //循环读取zip中的cvs/txt文件，zip文件名不能包含中文
                while ((zipFile = zipInputStream.getNextEntry()) != null) {
                    //获得cvs名字
                    String fileName = zipFile.getName();
                    //检测文件是否存在
                    if (StrKit.notBlank(filter) && !fileName.contains(filter) && fileName.contains(".")) {
                        LogKit.info("开始解析文件：" + fileName);
                        //读取
                        BufferedReader br =
                                new BufferedReader(
                                        new InputStreamReader(zipInputStream, Charset.forName("GBK")));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        //内容不为空，输出
                        while ((line = br.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        list.add(stringBuilder.toString());
                    }
                    //一定记得关闭流
                    zipInputStream.closeEntry();
                }
                ret.set("list", list).setOk();
                return ret;
            }
        } catch (IOException e) {
            LOGGER.error("解析激活码文件失败 : " + e.getLocalizedMessage());
            return ret.setFail();
        }
    }

}
