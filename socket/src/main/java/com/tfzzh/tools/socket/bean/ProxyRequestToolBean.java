/**
 * @author Weijie Xu
 * @dateTime 2014年7月2日 下午7:41:01
 */
package com.tfzzh.tools.socket.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tfzzh.tools.socket.bean.iface.LinkedTool;
import com.tfzzh.tools.socket.bean.iface.OperationTool;
import com.tfzzh.tools.socket.bean.iface.RequestTool;
import com.tfzzh.tools.socket.tools.ScopeType;

/**
 * 代理请求工具<br />
 * 在来源域为各自独立Operation<br />
 * 在代理域为统一的Action与Operation<br />
 * 在目标域为各自独立Action<br />
 * 
 * @author Weijie Xu
 * @dateTime 2014年7月2日 下午7:41:01
 */
public class ProxyRequestToolBean {

	/**
	 * 来源域Operation名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月2日 下午7:41:01
	 */
	private final String fromOperationName;

	/**
	 * 代理域接收Action名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午3:11:15
	 */
	private final String proxyActionName;

	/**
	 * 代理域Operation名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午3:11:35
	 */
	private final String proxyOperationName;

	/**
	 * 目标域接收Action名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午3:11:36
	 */
	private final String targetActionName;

	/**
	 * 来源域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月2日 下午7:41:01
	 */
	private final ScopeType from;

	/**
	 * 代理域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月2日 下午7:41:01
	 */
	private final ScopeType proxy;

	/**
	 * 目标域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月2日 下午7:41:01
	 */
	private final ScopeType target;

	/**
	 * 类名（功能名）
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月23日_下午7:42:05
	 */
	private final String beanName;

	/**
	 * 代理的连接
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午9:44:00
	 */
	private LinkedTool proxyLink;

	/**
	 * 链接列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月2日 下午7:41:01
	 */
	private final List<LinkedTool> linkedList;

	/**
	 * 用到的消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月2日 下午7:41:01
	 */
	private final Map<Integer, MessageToolBean> msgMap;

	/**
	 * 是否存在没有参数的连接
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午10:05:05
	 */
	private boolean nullMsg = false;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年7月2日 下午7:41:01
	 * @param from 来源域
	 * @param proxy 代理域
	 * @param target 目标域
	 * @param beanName 类名（功能名）
	 */
	public ProxyRequestToolBean(final ScopeType from, final ScopeType proxy, final ScopeType target, final String beanName) {
		this.fromOperationName = target.getProName() + "With" + proxy.getProName() + "Proxy";
		this.proxyActionName = from.getProName() + "To" + target.getProName() + "Proxy";
		this.proxyOperationName = from.getProName() + "To" + target.getProName() + "Proxy";
		this.targetActionName = from.getProName() + "With" + proxy.getProName() + "Proxy";
		this.from = from;
		this.proxy = proxy;
		this.target = target;
		this.beanName = beanName;
		this.linkedList = new LinkedList<LinkedTool>();
		this.msgMap = new HashMap<Integer, MessageToolBean>();
	}

	/**
	 * 放入一个链接信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月2日 下午7:41:01
	 * @param linked 链接信息
	 */
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
	 * 放入代理方法连接
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午9:44:54
	 * @param linked 代理连接
	 */
	public void putProxyLinked(final LinkedTool linked) {
		this.proxyLink = linked;
	}

	/**
	 * 得到代理方法连接
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午3:03:12
	 * @return 代理方法连接
	 */
	public LinkedTool getProxyLinked() {
		return this.proxyLink;
	}

	/**
	 * 得到代理域的接收请求工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:09:32
	 * @return 代理域的接收请求工具
	 */
	public RequestTool getProxyRequestTool() {
		return new ProxyWithFromRequestToolBean();
	}

	/**
	 * 得到来源域的发送操作工作
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午10:06:24
	 * @return 来源域的发送操作工作
	 */
	public OperationTool getFromOperationTool() {
		return new FromToProxyOperationToolBean();
	}

	/**
	 * 得到目标域的接收请求工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:11:08
	 * @return 目标域的接收请求工具
	 */
	public RequestTool getTargetRequestTool() {
		return new TargetWithProxyRequestToolBean();
	}

	/**
	 * 得到代理域的发送操作工作
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午10:06:24
	 * @return 代理域的发送操作工作
	 */
	public OperationTool getProxyOperationTool() {
		return new ProxyToTargetOperationToolBean();
	}

	/**
	 * 在代理域接收请求的行为
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午8:33:58
	 */
	public class ProxyWithFromRequestToolBean implements RequestTool {

		/**
		 * 得到项目用名，首字符大写
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午8:33:42
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getProName()
		 */
		@Override
		public String getProName() {
			return ProxyRequestToolBean.this.proxyActionName;
		}

		/**
		 * 得到说明
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午8:44:49
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getDesc()
		 */
		@Override
		public String getDesc() {
			return String.format("从“%1$s”向“%2$s”请求的代理", ProxyRequestToolBean.this.from.getDesc(), ProxyRequestToolBean.this.target.getDesc());
		}

		/**
		 * 得到类文件所在域
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午11:00:26
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScope()
		 */
		@Override
		public ScopeType getScope() {
			return ProxyRequestToolBean.this.proxy;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 下午1:38:08
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getFrom()
		 */
		@Override
		public ScopeType getFrom() {
			return ProxyRequestToolBean.this.from;
		}

		/**
		 * 得到所在作用域包名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午8:33:42
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScopePackageName()
		 */
		@Override
		public String getScopePackageName() {
			final String name = this.getScope().getScopeName();
			if ((null == name) || (name.length() == 0)) {
				return "";
			} else {
				return "." + name;
			}
		}

		/**
		 * 得到所在作用域的包路径
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午10:42:31
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScopePathName()
		 */
		@Override
		public String getScopePathName() {
			final String name = this.getScope().getScopeName();
			if ((null == name) || (name.length() == 0)) {
				return "";
			} else {
				return "/" + name.replaceAll("[.]", "/");
			}
		}

		/**
		 * 作为代理域，只有一个主连接
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午8:33:42
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getLinkedList()
		 */
		@Override
		public List<LinkedTool> getLinkedList() {
			final List<LinkedTool> l = new LinkedList<LinkedTool>();
			l.add(ProxyRequestToolBean.this.proxyLink);
			return l;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午8:40:34
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getMessages()
		 */
		@Override
		public Collection<MessageToolBean> getMessages() {
			final List<MessageToolBean> l = new LinkedList<MessageToolBean>();
			l.add(ProxyRequestToolBean.this.proxyLink.getMsg());
			return l;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 下午1:53:36
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#putLinked(com.tfzzh.tools.socket.bean.iface.LinkedTool)
		 */
		@Override
		public void putLinked(final LinkedTool linked) {
			ProxyRequestToolBean.this.putLinked(linked);
		}

		/**
		 * @author XuWeijie
		 * @datetime 2015年12月23日_下午7:28:06
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getBeanName()
		 */
		@Override
		public String getBeanName() {
			return ProxyRequestToolBean.this.beanName;
		}
	}

	/**
	 * 在目标域接收请求的行为
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午10:10:40
	 */
	public class TargetWithProxyRequestToolBean implements RequestTool {

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午10:10:27
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getProName()
		 */
		@Override
		public String getProName() {
			return ProxyRequestToolBean.this.targetActionName;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午10:10:27
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getDesc()
		 */
		@Override
		public String getDesc() {
			return String.format("由“%1$s”代理来自“%2$s”的请求", ProxyRequestToolBean.this.proxy.getDesc(), ProxyRequestToolBean.this.from.getDesc());
		}

		/**
		 * 得到类文件所在域
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午11:00:26
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScope()
		 */
		@Override
		public ScopeType getScope() {
			return ProxyRequestToolBean.this.target;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 下午1:38:08
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getFrom()
		 */
		@Override
		public ScopeType getFrom() {
			return ProxyRequestToolBean.this.proxy;
		}

		/**
		 * 得到所在作用域包名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午8:33:42
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScopePackageName()
		 */
		@Override
		public String getScopePackageName() {
			final String name = this.getScope().getScopeName();
			if ((null == name) || (name.length() == 0)) {
				return "";
			} else {
				return "." + name;
			}
		}

		/**
		 * 得到所在作用域的包路径
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午10:42:31
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScopePathName()
		 */
		@Override
		public String getScopePathName() {
			final String name = this.getScope().getScopeName();
			if ((null == name) || (name.length() == 0)) {
				return "";
			} else {
				return "/" + name.replaceAll("[.]", "/");
			}
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午10:10:27
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getLinkedList()
		 */
		@Override
		public List<LinkedTool> getLinkedList() {
			return ProxyRequestToolBean.this.linkedList;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午10:10:27
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getMessages()
		 */
		@Override
		public Collection<MessageToolBean> getMessages() {
			return ProxyRequestToolBean.this.msgMap.values();
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 下午1:53:36
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#putLinked(com.tfzzh.tools.socket.bean.iface.LinkedTool)
		 */
		@Override
		public void putLinked(final LinkedTool linked) {
			ProxyRequestToolBean.this.putLinked(linked);
		}

		/**
		 * @author XuWeijie
		 * @datetime 2015年12月23日_下午7:28:06
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getBeanName()
		 */
		@Override
		public String getBeanName() {
			// XXX 需要完善相关逻辑
			return null;
		}
	}

	/**
	 * 在来源域的发送操作行为
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午9:38:47
	 */
	public class FromToProxyOperationToolBean implements OperationTool {

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午10:27:28
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getProName()
		 */
		@Override
		public String getProName() {
			return ProxyRequestToolBean.this.fromOperationName;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午10:27:28
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getDesc()
		 */
		@Override
		public String getDesc() {
			return String.format("从“%1$s”向“%2$s”的请求通过“%3$s”的代理操作", ProxyRequestToolBean.this.from.getDesc(), ProxyRequestToolBean.this.target.getDesc(), ProxyRequestToolBean.this.proxy.getDesc());
		}

		/**
		 * 得到类文件所在域
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午11:00:26
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScope()
		 */
		@Override
		public ScopeType getScope() {
			return ProxyRequestToolBean.this.from;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 下午1:38:08
		 * @see com.tfzzh.tools.socket.bean.iface.OperationTool#getTarget()
		 */
		@Override
		public ScopeType getTarget() {
			return ProxyRequestToolBean.this.proxy;
		}

		/**
		 * 得到所在作用域包名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午8:33:42
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScopePackageName()
		 */
		@Override
		public String getScopePackageName() {
			final String name = this.getScope().getScopeName();
			if ((null == name) || (name.length() == 0)) {
				return "";
			} else {
				return "." + name;
			}
		}

		/**
		 * 得到所在作用域的包路径
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午10:42:31
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScopePathName()
		 */
		@Override
		public String getScopePathName() {
			final String name = this.getScope().getScopeName();
			if ((null == name) || (name.length() == 0)) {
				return "";
			} else {
				return "/" + name.replaceAll("[.]", "/");
			}
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午10:27:28
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getLinkedList()
		 */
		@Override
		public List<LinkedTool> getLinkedList() {
			return ProxyRequestToolBean.this.linkedList;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午10:27:28
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getMessages()
		 */
		@Override
		public Collection<MessageToolBean> getMessages() {
			return ProxyRequestToolBean.this.msgMap.values();
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午9:59:33
		 * @see com.tfzzh.tools.socket.bean.iface.OperationTool#isNullMsg()
		 */
		@Override
		public boolean isNullMsg() {
			return ProxyRequestToolBean.this.nullMsg;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 下午1:53:36
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#putLinked(com.tfzzh.tools.socket.bean.iface.LinkedTool)
		 */
		@Override
		public void putLinked(final LinkedTool linked) {
			ProxyRequestToolBean.this.putLinked(linked);
		}

		/**
		 * @author XuWeijie
		 * @datetime 2015年12月23日_下午7:28:06
		 * @see com.tfzzh.tools.socket.bean.iface.OperationTool#getBeanName()
		 */
		@Override
		public String getBeanName() {
			return ProxyRequestToolBean.this.beanName;
		}
	}

	/**
	 * 在代理域的发送操作行为
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午9:53:01
	 */
	public class ProxyToTargetOperationToolBean implements OperationTool {

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午9:53:31
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getProName()
		 */
		@Override
		public String getProName() {
			return ProxyRequestToolBean.this.proxyOperationName;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午9:53:31
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getDesc()
		 */
		@Override
		public String getDesc() {
			return String.format("从代理“%1$s”向“%2$s”转发的“%3$s”的操作", ProxyRequestToolBean.this.proxy.getDesc(), ProxyRequestToolBean.this.target.getDesc(), ProxyRequestToolBean.this.from.getDesc());
		}

		/**
		 * 得到类文件所在域
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午11:00:26
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScope()
		 */
		@Override
		public ScopeType getScope() {
			return ProxyRequestToolBean.this.proxy;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 下午1:38:08
		 * @see com.tfzzh.tools.socket.bean.iface.OperationTool#getTarget()
		 */
		@Override
		public ScopeType getTarget() {
			return ProxyRequestToolBean.this.target;
		}

		/**
		 * 得到所在作用域包名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月3日 下午8:33:42
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScopePackageName()
		 */
		@Override
		public String getScopePackageName() {
			final String name = this.getScope().getScopeName();
			if ((null == name) || (name.length() == 0)) {
				return "";
			} else {
				return "." + name;
			}
		}

		/**
		 * 得到所在作用域的包路径
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午10:42:31
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getScopePathName()
		 */
		@Override
		public String getScopePathName() {
			final String name = this.getScope().getScopeName();
			if ((null == name) || (name.length() == 0)) {
				return "";
			} else {
				return "/" + name.replaceAll("[.]", "/");
			}
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午9:53:31
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getLinkedList()
		 */
		@Override
		public List<LinkedTool> getLinkedList() {
			final List<LinkedTool> l = new LinkedList<LinkedTool>();
			l.add(ProxyRequestToolBean.this.proxyLink);
			return l;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午9:53:31
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#getMessages()
		 */
		@Override
		public Collection<MessageToolBean> getMessages() {
			final List<MessageToolBean> l = new LinkedList<MessageToolBean>();
			l.add(ProxyRequestToolBean.this.proxyLink.getMsg());
			return l;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 上午9:59:33
		 * @see com.tfzzh.tools.socket.bean.iface.OperationTool#isNullMsg()
		 */
		@Override
		public boolean isNullMsg() {
			return false;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年7月4日 下午1:53:36
		 * @see com.tfzzh.tools.socket.bean.iface.RequestTool#putLinked(com.tfzzh.tools.socket.bean.iface.LinkedTool)
		 */
		@Override
		public void putLinked(final LinkedTool linked) {
			ProxyRequestToolBean.this.putLinked(linked);
		}

		/**
		 * @author XuWeijie
		 * @datetime 2015年12月23日_下午7:28:06
		 * @see com.tfzzh.tools.socket.bean.iface.OperationTool#getBeanName()
		 */
		@Override
		public String getBeanName() {
			return "";
		}
	}
}
