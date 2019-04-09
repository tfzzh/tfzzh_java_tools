/**
 * @author Weijie Xu
 * @dateTime 2014年6月30日 下午8:00:51
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
import com.tfzzh.tools.socket.bean.iface.OperationTool;
import com.tfzzh.tools.socket.tools.ScopeType;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * 操作工具
 * 
 * @author Weijie Xu
 * @dateTime 2014年6月30日 下午8:00:51
 */
public class OperationToolBean implements OperationTool {

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
	 * @dateTime 2014年6月30日 下午8:00:51
	 */
	private final String proName;

	/**
	 * 作用域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 */
	private final ScopeType scope;

	/**
	 * 目标域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月1日 上午9:58:04
	 */
	private final ScopeType target;

	/**
	 * 链接列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 */
	private final List<LinkedTool> linkedList;

	/**
	 * 用到的消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 */
	private final Map<Integer, MessageToolBean> msgMap;

	/**
	 * 是否存在没有参数的连接
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午10:56:06
	 */
	private boolean nullMsg = false;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @param scope 作用域
	 * @param from 来源域
	 * @param beanName 类名（功能名）
	 */
	public OperationToolBean(final ScopeType from, final ScopeType scope, final String beanName) {
		final String tmpBeanName = beanName.substring(0, 1).toUpperCase() + beanName.substring(1);
		this.proName = scope.getProName() + tmpBeanName;
		this.beanName = beanName;
		this.scope = from;
		this.target = scope;
		this.linkedList = new LinkedList<LinkedTool>();
		this.msgMap = new HashMap<Integer, MessageToolBean>();
	}

	/**
	 * 放入一个链接信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @param linked 链接信息
	 */
	@Override
	public void putLinked(final LinkedTool linked) {
		this.linkedList.add(linked);
		final MessageToolBean msg = linked.getMsg();
		if ((null != msg) && !this.msgMap.containsKey(msg.getId())) {
			// 如果不存在则放入
			this.msgMap.put(msg.getId(), msg);
		} else if ((null == msg) && !this.nullMsg) {
			this.nullMsg = true;
		}
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
	 * 得到项目用名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @return the proName
	 */
	@Override
	public String getProName() {
		return this.proName;
	}

	/**
	 * 得到说明
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:21:28
	 * @return 说明
	 * @see com.tfzzh.tools.socket.bean.iface.OperationTool#getDesc()
	 */
	@Override
	public String getDesc() {
		return String.format("去到“%1$s”操作行为", this.target.getDesc());
	}

	/**
	 * 得到作用域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @return the scope
	 */
	@Override
	public ScopeType getScope() {
		return this.scope;
	}

	/**
	 * 得到目标域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月1日 上午9:58:50
	 * @return 目标域类型
	 */
	@Override
	public ScopeType getTarget() {
		return this.target;
	}

	/**
	 * 得到所在作用域包名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
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
	 * @dateTime 2014年6月30日 下午8:00:51
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
	 * @dateTime 2014年6月30日 下午8:00:51
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
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @return 消息信息列表
	 */
	@Override
	public Collection<MessageToolBean> getMessages() {
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

	/**
	 * 是否存在没有参数的连接
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午10:56:54
	 * @return the nullMsg
	 */
	@Override
	public boolean isNullMsg() {
		return this.nullMsg;
	}
}
