/**
 * @author Weijie Xu
 * @dateTime 2014年7月3日 下午10:15:52
 */
package com.tfzzh.tools.socket.bean.iface;

import java.util.Collection;
import java.util.List;

import com.tfzzh.tools.socket.bean.MessageToolBean;
import com.tfzzh.tools.socket.tools.ScopeType;

/**
 * 发送操作工具
 * 
 * @author Weijie Xu
 * @dateTime 2014年7月3日 下午10:15:52
 */
public interface OperationTool {

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
	 * @dateTime 2014年7月3日 下午10:16:33
	 * @return 项目用名
	 */
	String getProName();

	/**
	 * 得到说明
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:20:00
	 * @return 说明
	 */
	String getDesc();

	/**
	 * 得到类文件所在域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午10:31:58
	 * @return 类文件所在域
	 */
	ScopeType getScope();

	/**
	 * 得到要发送到的目标域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午1:37:29
	 * @return 要发送到的目标域
	 */
	ScopeType getTarget();

	/**
	 * 得到作用域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:16:59
	 * @return 作用域类型
	 */
	String getScopePackageName();

	/**
	 * 得到所在作用域的包路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午10:29:48
	 * @return 所在作用域的包路径
	 */
	String getScopePathName();

	/**
	 * 放入一个链接信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午1:52:45
	 * @param linked 链接信息
	 */
	void putLinked(LinkedTool linked);

	/**
	 * 得到链接列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:22:25
	 * @return 链接列表
	 */
	List<LinkedTool> getLinkedList();

	/**
	 * 得到用到的消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:19:35
	 * @return 消息信息列表
	 */
	Collection<MessageToolBean> getMessages();

	/**
	 * 是否存在没有参数的连接
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:18:48
	 * @return ture，是存在；<br />
	 *         false，不存在；<br />
	 */
	boolean isNullMsg();
}
