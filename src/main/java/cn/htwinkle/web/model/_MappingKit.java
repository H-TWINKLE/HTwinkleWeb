package cn.htwinkle.web.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("article", "articleId", Article.class);
		arp.addMapping("picture", "pictureId", Picture.class);
		arp.addMapping("project", "projectId", Project.class);
		arp.addMapping("user", "userId", User.class);
		arp.addMapping("visit", "visitId", Visit.class);
	}
}
