package com.twinkle.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.baidu.aip.ocr.AipOcr;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.twinkle.common.model._MappingKit;

import static com.twinkle.utils.Constant.*;

public enum CommUtils {

	INSTANCE;

	private HashMap<String, String> options;

	private AipOcr orcClient = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

	public static final String UUID = "020a2d70-cbbe-43fd-be3d-db7040d4a4e2";

	private ThreadPoolExecutor initExeCutorPool = new ThreadPoolExecutor(2, 5, 5, TimeUnit.SECONDS,
			new ArrayBlockingQueue<>(20));

	private HashMap<String, String> validatorHashMapParam() {

		if (options == null) {
			options = new HashMap<>();
		}

		if (options.size() == 0) {
			options.put("language_type", "ENG");
		}

		return options;

	}

	public Boolean regex(String text, String regex) {
		return Pattern.compile(regex).matcher(text).find();
	}

	public String mathChinese(String text) {
		return Pattern.compile("[^\u4E00-\u9FA5]").matcher(text).replaceAll(""); // [\u4E00-\u9FA5]是unicode2的中文区间
	}

	public String isServicePath() {
		return regex(System.getProperty(Constant.JavaProperty), Constant.JavaPath) ? Constant.demoPath
				: Constant.tomcatPath;
	}

	public String ocrPath() {
		return regex(System.getProperty(Constant.JavaProperty), Constant.JavaPath) ? Constant.orcPath
				: Constant.orcTomcatPath;
	}

	public String jwglUrl(String flag, String xh, String xm) {

		if (!"".equals(xm)) {
			try {
				xm = URLEncoder.encode(xh, "gb2312");
			} catch (Exception e) {
				return null;
			}
		}

		switch (flag) {
		case "Host":
			return " jwgl.cdnu.edu.cn";
		case "Index":
			return "http://jwgl.cdnu.edu.cn/xs_main.aspx?xh=" + xh;
		case "Info":
			return "http://jwgl.cdnu.edu.cn/xsgrxx.aspx?xh=" + xh + "&xm=" + xm + "&gnmkdm=N121501";
		case "TimeTable":
			return "http://jwgl.cdnu.edu.cn/xskbcx.aspx?xh=" + xh + "&xm=" + xm + "&gnmkdm=N121603";
		case "Score":
			return "http://jwgl.cdnu.edu.cn/xscj_gc.aspx?xh=" + xh + "&xm=" + xm + "&gnmkdm=N121605";
		default:
			return "";
		}
	}

	public String eolUrl(String subject) {
		return "http://eol.cdnu.edu.cn/eol" + subject + "&_=" + System.currentTimeMillis();
	}

	public void startRecordPlugin() {

		DruidPlugin druidPlugin = new DruidPlugin(
				"jdbc:mysql://127.0.0.1:3306/twinkle?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull", "root",
				"123");
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);

		_MappingKit.mapping(arp);
		druidPlugin.start();
		arp.start();

	}

	public void addToExeCutorPool(Runnable r) {

		initExeCutorPool.execute(r);
	}

	public String getRemoteAddr(Controller c) {

		if (c == null)
			return null;

		String ip = c.getRequest().getHeader("x-forwarded-for");

		if (validatorIp(ip)) {
			return ip;
		}
		ip = c.getRequest().getHeader("Proxy-Client-IP");
		if (validatorIp(ip)) {
			return ip;
		}
		ip = c.getRequest().getHeader("WL-Proxy-Client-IP");
		if (validatorIp(ip)) {
			return ip;
		}
		ip = c.getRequest().getHeader("HTTP_CLIENT_IP");
		if (validatorIp(ip)) {
			return ip;
		}
		ip = c.getRequest().getHeader("X-Real-IP");
		if (validatorIp(ip)) {
			return ip;
		}
		ip = c.getRequest().getRemoteAddr();
		if (validatorIp(ip)) {
			if (ip.equals(Constant.LOCALIP)) {
				return "127.0.0.1";
			}
			return ip;
		}

		return "";

	}

	private boolean validatorIp(String ip) {

		return ip != null && ip.length() > 0 && !ip.equals("unknown");

	}

	public String getOrcText(byte[] bytes) {

		return orcClient.basicGeneral(bytes, validatorHashMapParam()).toString();
	}

	public byte[] imgConvert(InputStream in) throws IOException {

		if (in == null)
			return null;

		BufferedImage bImage = ImageIO.read(in);

		BufferedImage newBImage = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		newBImage.createGraphics().drawImage(bImage, 0, 0, Color.WHITE, null);

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		ImageIO.write(newBImage, "jpg", os);

		// ImageIO.write(newBImage, "jpg", new File("E:\\TWINKLE\\Desktop\\1.jpg"));

		return os.toByteArray();

	}

}
