/**
 * @author Weijie Xu
 * @dateTime 2014年7月3日 下午8:27:23
 */
package com.tfzzh.tools.socket.bean.iface;

import java.util.Collection;
import java.util.List;

import com.tfzzh.tools.socket.bean.MessageToolBean;
import com.tfzzh.tools.socket.tools.ScopeType;

/**
 * 接收请求工具
 * 
 * @author Weijie Xu
 * @dateTime 2014年7月3日 下午8:27:23
 */
public interface RequestTool {

	/**
	 * 得到类名（功能名）
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月23日_下午5:26:09
	 * @return the beanName
	 */
	String getBeanName();

	/**
	 * 得到项目用名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午8:29:17
	 * @return 项目用名
	 */
	String getProName();

	/**
	 * 得到说明
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午8:44:26
	 * @return 说明
	 */
	String getDesc();

	/**
	 * 得到类文件所在域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午10:43:02
	 * @return 类文件所在域
	 */
	ScopeType getScope();

	/**
	 * 得到请求消息所来自的域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午1:36:41
	 * @return 请求消息所来自的域
	 */
	ScopeType getFrom();

	/**
	 * 得到所在作用域包名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午8:28:30
	 * @return 所在作用域包名
	 */
	String getScopePackageName();

	/**
	 * 得到所在作用域的包路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午10:10:36
	 * @return 所在作用域的包路径
	 */
	String getScopePathName();

	/**
	 * 放入一个链接信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午1:45:23
	 * @param linked 链接信息
	 */
	void putLinked(LinkedTool linked);

	/**
	 * 得到链接列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午8:27:50
	 * @return 链接列表
	 */
	List<LinkedTool> getLinkedList();

	/**
	 * 得到用到的消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午8:39:47
	 * @return 消息信息列表
	 */
	Collection<MessageToolBean> getMessages();
}
