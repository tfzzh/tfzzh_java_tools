/**
 * @author Weijie Xu
 * @dateTime 2014-3-29 下午4:13:38
 */
package com.tfzzh.tools.socket.bean;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tfzzh.tools.socket.bean.iface.LinkedTool;
import com.tfzzh.tools.socket.bean.iface.RequestTool;
import com.tfzzh.tools.socket.tools.ScopeType;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * 请求工具
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-29 下午4:13:38
 */
public class RequestToolBean implements RequestTool {

	/**
	 * 名字
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:14:06
	 */
	private final String name;

	/**
	 * 类名（功能名）
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月23日_下午5:24:48
	 */
	private final String beanName;

	/**
	 * 项目用名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:14:04
	 */
	private final String proName;

	/**
	 * 作用域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午5:05:43
	 */
	private final ScopeType scope;

	/**
	 * 来源域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月1日 上午9:54:44
	 */
	private final ScopeType from;

	/**
	 * 链接列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:14:50
	 */
	private final List<LinkedTool> linkedList;

	/**
	 * 用到的消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:15:17
	 */
	private final Map<Integer, MessageToolBean> msgMap;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:18:40
	 * @param scope 作用域
	 * @param from 来源域
	 * @param beanName 类名（功能名）
	 */
	public RequestToolBean(final ScopeType scope, final ScopeType from, final String beanName) {
		final String tmpBeanName = beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
		this.name = "from" + from.getProName() + tmpBeanName;
		this.proName = "From" + from.getProName() + tmpBeanName;
		this.beanName = beanName;
		this.scope = scope;
		this.from = from;
		this.linkedList = new LinkedList<LinkedTool>();
		this.msgMap = new HashMap<Integer, MessageToolBean>();
	}

	/**
	 * 放入一个链接信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:29:51
	 * @param linked 链接信息
	 */
	@Override
	public void putLinked(final LinkedTool linked) {
		this.linkedList.add(linked);
		final MessageToolBean msg = linked.getMsg();
		if ((null != msg) && !this.msgMap.containsKey(msg.getId())) {
			// 如果不存在则放入
			this.msgMap.put(msg.getId(), msg);
		}
	}

	/**
	 * 得到名字
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:30:09
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 得到类名（功能名）
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月23日_下午5:26:09
	 * @return the beanName
	 */
	@Override
	public String getBeanName() {
		return this.beanName;
	}

	/**
	 * 得到说明
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午8:43:58
	 * @return 说明
	 */
	@Override
	public String getDesc() {
		return String.format("来自“%1$s”的请求行为", this.from.getDesc());
	}

	/**
	 * 得到项目用名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:30:09
	 * @return the proName
	 */
	@Override
	public String getProName() {
		return this.proName;
	}

	/**
	 * 得到作用域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午6:58:05
	 * @return 作用域类型
	 */
	@Override
	public ScopeType getScope() {
		return this.scope;
	}

	/**
	 * 得到来源域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月1日 上午9:55:22
	 * @return 来源域类型
	 */
	@Override
	public ScopeType getFrom() {
		return this.from;
	}

	/**
	 * 得到所在作用域包名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午9:08:08
	 * @return 所在作用域包名
	 */
	@Override
	public String getScopePackageName() {
		final String name = this.scope.getScopeName();
		if ((null == name) || (name.length() == 0)) {
			return "";
		} else {
			return "." + name;
		}
	}

	/**
	 * 得到所在作用域的路径名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 上午9:47:38
	 * @return 所在作用域的路径名
	 */
	@Override
	public String getScopePathName() {
		final String name = this.scope.getScopeName();
		if ((null == name) || (name.length() == 0)) {
			return "";
		} else {
			return "/" + name.replaceAll("[.]", "/");
		}
	}

	/**
	 * 得到链接列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:30:09
	 * @return the linkedList
	 */
	@Override
	public List<LinkedTool> getLinkedList() {
		return this.linkedList;
	}

	/**
	 * 得到用到的消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:30:09
	 * @return 消息信息列表
	 */
	@Override
	public Collection<MessageToolBean> getMessages() {
		// 其实这里可以考虑直接用msg.name做键，使用TreeMap进行直接排序
		final Collection<MessageToolBean> coll = this.msgMap.values();
		final List<MessageToolBean> list = new LinkedList<MessageToolBean>(coll);
		Collections.sort(list, new Comparator<MessageToolBean>() {

			@Override
			public int compare(final MessageToolBean b1, final MessageToolBean b2) {
				final String n1 = b1.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX;
				final String n2 = b2.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX;
				return n1.compareTo(n2);
			}
		});
		return list;
	}
}
