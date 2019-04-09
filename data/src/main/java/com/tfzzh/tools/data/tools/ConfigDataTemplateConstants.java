/**
 * @author XuWeijie
 * @dateTime Apr 30, 2010 10:52:47 AM
 */
package com.tfzzh.tools.data.tools;

import com.tfzzh.core.annotation.PropertiesMethod;
import com.tfzzh.core.annotation.PropertiesValue;
import com.tfzzh.tools.BaseConstants;
import com.tfzzh.tools.Constants;

/**
 * @author XuWeijie
 * @dateTime Apr 30, 2010 10:52:47 AM
 */
public class ConfigDataTemplateConstants extends BaseConstants {

	/**
	 * 待读模版文件编码<br />
	 * 默认UTF-8<br />
	 * 
	 * @author XuWeijie
	 * @dateTime May 1, 2010 7:37:19 PM
	 */
	public static String FILE_CODE = Constants.SYSTEM_CODE;

	/**
	 * 所用数据库编码<br />
	 * 默认UTF-8<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:16:25 PM
	 */
	public static String DATA_CODE = Constants.SYSTEM_CODE.replaceAll("[-_]", "");

	/**
	 * 模版的路径
	 * 
	 * @author Xu Weijie
	 * @dateTime 2012-4-26 下午1:57:11
	 */
	@PropertiesValue
	public static String TEMPLATE_PATH;

	/**
	 * 系统制作人：听风紫竹
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:08:21 PM
	 */
	@PropertiesValue
	public static String SYSTEM_AUTHOR;

	/**
	 * 项目包名称
	 * 
	 * @author tfzzh
	 * @dateTime 2010-8-1 下午06:30:02
	 */
	@PropertiesValue
	public static String PROJECT_NAME;

	/**
	 * 项目模版基本路径，为项目包所在目录，对应文件中内容的临时值
	 * 
	 * @author tfzzh
	 * @dateTime 2016年11月21日 下午3:27:32
	 */
	@PropertiesValue("PROJECT_TEMPLATE_PATH")
	public static String PROJECT_TEMPLATE_PATH_TMP;

	/**
	 * 项目模版基本路径，为项目包所在目录
	 * 
	 * @author XuWeijie
	 * @dateTime Sep 29, 2010 9:42:06 PM
	 */
	@PropertiesMethod
	public static String PROJECT_TEMPLATE_PATH;

	/**
	 * 组合项目模版基本路径，为项目包所在目录
	 * 
	 * @author tfzzh
	 * @dateTime 2016年11月21日 下午3:26:24
	 * @return 项目模版基本路径，为项目包所在目录
	 */
	@SuppressWarnings("unused")
	private String asseProjectTemplatePath() {
		return Constants.OS_WIN ? ConfigDataTemplateConstants.class.getResource("/").getPath().substring(1) : ConfigDataTemplateConstants.class.getResource("/").getPath() + ConfigDataTemplateConstants.PROJECT_TEMPLATE_PATH_TMP;
	}

	/**
	 * 项目包前缀
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 4:26:29 PM
	 */
	@PropertiesValue
	public static String PROJECT_PACKAGE_PREFIX;

	/**
	 * 项目路径前缀
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 4:26:31 PM
	 */
	@PropertiesMethod
	public static String PROJECT_PATH_PREFIX;

	/**
	 * 组合项目路径前缀
	 * 
	 * @author tfzzh
	 * @dateTime 2016年11月21日 下午3:49:24
	 * @return 项目路径前缀
	 */
	@SuppressWarnings("unused")
	private String asseProjectPathPrefix() {
		return ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX.replaceAll("[.]", "/");
	}

	/**
	 * 逻辑文件路径叠加前缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午2:16:03
	 */
	@PropertiesValue
	public static String CLASSBEAN_PACKAGE_PREFIX;

	/**
	 * 逻辑文件名称后缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午2:16:04
	 */
	@PropertiesValue
	public static String CLASSBEAN_CLASS_SUFFIX;

	/**
	 * 逻辑的元素文件名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 5, 2014 1:28:24 PM
	 */
	@PropertiesValue
	public static String CLASSBEAN_CLASS_ELEMENT_SUFFIX;

	/**
	 * 实体文件名称后缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-28 下午8:15:55
	 */
	@PropertiesValue
	public static String CLASSBEAN_ENTITY_SUFFIX;

	/**
	 * 关系实体文件名称后缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月4日 下午8:45:31
	 */
	@PropertiesValue
	public static String CLASSBEAN_ENTITY_CORRELATION_SUFFIX;

	/**
	 * 实体文件名特殊字
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 下午8:35:33
	 */
	@PropertiesValue
	public static String CLASSBEAN_ENTITY_SPECIAL;

	/**
	 * 关系实体文件名特殊字
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月5日 下午8:35:34
	 */
	@PropertiesValue
	public static String CLASSBEAN_ENTITY_CORRELATION_SPECIAL;

	/**
	 * XML目标配置类文件默认存放路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 8:59:31 PM
	 */
	@PropertiesValue
	public static String BASE_XML_CONFIG_PATH;

	/**
	 * EXCEL目标配置类文件默认存放路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 11:09:34 AM
	 */
	@PropertiesValue
	public static String BASE_EXCEL_CONFIG_PATH;

	/**
	 * 解析用dtd文件所在地址，相对配置文件基础路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午3:23:03
	 */
	@PropertiesValue
	public static String DTD_PATH;

	/**
	 * 有效的xml文件的名称前缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午3:23:04
	 */
	@PropertiesValue
	public static String XML_PERFIX;

	/**
	 * XML源代码生成后所在源路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午4:29:24
	 */
	@PropertiesValue
	public static String SOURCE_BUILD_XML_SRC_PATH;

	/**
	 * Excel源代码生成后所在源路径
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 11:02:17 AM
	 */
	@PropertiesValue
	public static String SOURCE_BUILD_EXCEL_SRC_PATH;

	/**
	 * @author tfzzh
	 * @dateTime 2016年11月21日 下午1:36:17
	 * @param bundleName 所相关资源名
	 */
	public ConfigDataTemplateConstants(final String bundleName) {
		super(bundleName);
	}
}
