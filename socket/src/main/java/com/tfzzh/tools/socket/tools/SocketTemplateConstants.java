/**
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午5:15:31
 */
package com.tfzzh.tools.socket.tools;

import com.tfzzh.tools.Constants;

/**
 * 接口的模板用常量类
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午5:15:31
 */
public interface SocketTemplateConstants {

	/**
	 * 待读模版文件编码<br />
	 * 默认UTF-8<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-28 下午8:43:21
	 */
	String FILE_CODE = Constants.SYSTEM_CODE;

	/**
	 * 模版的路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-28 下午8:44:42
	 */
	String TEMPLATE_PATH = Messages.getString("SocketTemplateConstants.TEMPLATE_PATH");

	/**
	 * 项目模板基础路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:32:42
	 */
	String PROJECT_TEMPLATE_PATH = Constants.OS_WIN ? SocketTemplateConstants.class.getResource("/").getPath().substring(1) : SocketTemplateConstants.class.getResource("/").getPath() + Messages.getString("SocketTemplateConstants.PROJECT_TEMPLATE_PATH");

	/**
	 * 作者
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-28 下午8:46:02
	 */
	String SYSTEM_AUTHOR = Messages.getString("SocketTemplateConstants.SYSTEM_AUTHOR");

	/**
	 * 项目名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-28 下午8:46:06
	 */
	String PROJECT_NAME = Messages.getString("SocketTemplateConstants.PROJECT_NAME");

	/**
	 * 请求消息DTD
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午5:23:31
	 */
	String LINKED_DTD_PATH = Messages.getString("SocketTemplateConstants.LINKED_DTD_PATH");

	/**
	 * 代理消息DTD
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 上午10:37:03
	 */
	String LINKED_PROXY_DTD_PATH = Messages.getString("SocketTemplateConstants.LINKED_PROXY_DTD_PATH");

	/**
	 * 请求的数据结构DTD
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午5:23:32
	 */
	String PARAMETER_DTD_PATH = Messages.getString("SocketTemplateConstants.PARAMETER_DTD_PATH");

	/**
	 * 请求的代理数据结构DTD
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午9:16:29
	 */
	String PARAMETER_PROXY_DTD_PATH = Messages.getString("SocketTemplateConstants.PARAMETER_PROXY_DTD_PATH");

	/**
	 * 请求消息的xml文件的名称前缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午5:25:32
	 */
	String LINKED_XML_PERFIX = Messages.getString("SocketTemplateConstants.LINKED_XML_PERFIX");

	/**
	 * 代理消息的xml文件的名称前缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 上午10:37:01
	 */
	String LINKED_PROXY_XML_PERFIX = Messages.getString("SocketTemplateConstants.LINKED_PROXY_XML_PERFIX");

	/**
	 * 请求的数据结构的xml文件的名称前缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午5:25:33
	 */
	String PARAMETER_XML_PERFIX = Messages.getString("SocketTemplateConstants.PARAMETER_XML_PERFIX");

	/**
	 * 请求的代理数据结构的xml文件的名称前缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午9:19:29
	 */
	String PARAMETER_PROXY_XML_PERFIX = Messages.getString("SocketTemplateConstants.PARAMETER_PROXY_XML_PERFIX");

	/**
	 * 项目包前缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 上午10:35:04
	 */
	String PROJECT_PACKAGE_PREFIX = Messages.getString("SocketTemplateConstants.PROJECT_PACKAGE_PREFIX");

	/**
	 * 项目路径前缀
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 4:26:31 PM
	 */
	String PROJECT_PATH_PREFIX = SocketTemplateConstants.PROJECT_PACKAGE_PREFIX.replaceAll("[.]", "/");

	/**
	 * 通用服务器相关源代码生成后所在源路径
	 *
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 上午10:14:47
	 */
	String SOURCE_BUILD_BASE_SRC_PATH = Messages.getString("SocketTemplateConstants.SOURCE_BUILD_BASE_SRC_PATH");

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午9:04:29
	 */
	String SOURCE_BUILD_BASE_SCOPES_NAME = Messages.getString("SocketTemplateConstants.SOURCE_BUILD_BASE_SCOPES_NAME");

	/**
	 * 代理服务器相关源代码生成后所在源路径
	 *
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 上午9:56:22
	 */
	String SOURCE_BUILD_GATE_SRC_PATH = Messages.getString("SocketTemplateConstants.SOURCE_BUILD_GATE_SRC_PATH");

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午9:04:31
	 */
	String SOURCE_BUILD_GATE_SCOPES_NAME = Messages.getString("SocketTemplateConstants.SOURCE_BUILD_GATE_SCOPES_NAME");

	/**
	 * 节点服务器相关源代码生成后所在源路径
	 *
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 上午10:06:03
	 */
	String SOURCE_BUILD_NODE_SRC_PATH = Messages.getString("SocketTemplateConstants.SOURCE_BUILD_NODE_SRC_PATH");

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午9:04:32
	 */
	String SOURCE_BUILD_NODE_SCOPES_NAME = Messages.getString("SocketTemplateConstants.SOURCE_BUILD_NODE_SCOPES_NAME");

	/**
	 * 消息信息的后缀
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午1:28:32
	 */
	String CLASSBEAN_MESSAGE_SUFFIX = Messages.getString("SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX");
}
