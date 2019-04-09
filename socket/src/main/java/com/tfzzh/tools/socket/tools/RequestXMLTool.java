/**
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午8:57:38
 */
package com.tfzzh.tools.socket.tools;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.exception.ConfigurationOrInstallException;
import com.tfzzh.tools.socket.bean.MessageToolBean;
import com.tfzzh.tools.socket.bean.OperationToolBean;
import com.tfzzh.tools.socket.bean.ProxyRequestToolBean;
import com.tfzzh.tools.socket.bean.RequestToolBean;
import com.tfzzh.tools.socket.bean.iface.LinkedTool;
import com.tfzzh.tools.socket.bean.iface.OperationTool;
import com.tfzzh.tools.socket.bean.iface.RequestTool;

/**
 * 请求行为工具池
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午8:57:38
 */
public class RequestXMLTool {

	/**
	 * 请求对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:00:51
	 */
	private final Map<ScopeType, Map<ScopeType, Map<String, RequestTool>>> requests = new HashMap<ScopeType, Map<ScopeType, Map<String, RequestTool>>>();

	/**
	 * 代理请求对象列表<br />
	 * 该类消息：<br />
	 * 在来源域为各自独立Operation<br />
	 * 在代理域为统一的Action与Operation<br />
	 * 在目标域为各自独立Action<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午2:00:36
	 */
	private final Map<Integer, ProxyRequestToolBean> proxys = new HashMap<Integer, ProxyRequestToolBean>();

	/**
	 * 操作对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午6:52:51
	 */
	private final Map<ScopeType, Map<ScopeType, Map<String, OperationTool>>> operations = new HashMap<ScopeType, Map<ScopeType, Map<String, OperationTool>>>();

	/**
	 * 消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:59:21
	 */
	private final Map<ScopeType, Map<Integer, MessageToolBean>> msgMap = new HashMap<ScopeType, Map<Integer, MessageToolBean>>();

	/**
	 * 普通消息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月6日 上午10:26:58
	 */
	private final Map<ScopeType, Map<Integer, MessageToolBean>> normalMsgMap = new HashMap<ScopeType, Map<Integer, MessageToolBean>>();

	/**
	 * 动态消息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月6日 上午10:27:33
	 */
	private final Map<ScopeType, Map<Integer, MessageToolBean>> dynamicMsgMap = new HashMap<ScopeType, Map<Integer, MessageToolBean>>();

	/**
	 * 代理消息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月12日 下午4:27:20
	 */
	private final Map<ScopeType, Map<Integer, MessageToolBean>> proxyMsgMap = new HashMap<ScopeType, Map<Integer, MessageToolBean>>();

	/**
	 * 对象唯一实例
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:21:58
	 */
	private final static RequestXMLTool tool = new RequestXMLTool();

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:35:16
	 */
	private RequestXMLTool() {
	}

	/**
	 * 得到该对象实例
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:21:44
	 * @return 对象唯一实例
	 */
	public static RequestXMLTool getInstance() {
		return RequestXMLTool.tool;
	}

	/**
	 * 放入一个普通的请求消息工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:35:15
	 * @param scopes 作用域
	 * @param tool 请求消息工具
	 */
	public void putNormalRequestMessage(final ScopeType scopes, final MessageToolBean tool) {
		{
			Map<Integer, MessageToolBean> map = this.msgMap.get(scopes);
			if (null == map) {
				map = new HashMap<Integer, MessageToolBean>();
				this.msgMap.put(scopes, map);
			}
			map.put(tool.getId(), tool);
		}
		{
			Map<Integer, MessageToolBean> map = this.normalMsgMap.get(scopes);
			if (null == map) {
				map = new HashMap<Integer, MessageToolBean>();
				this.normalMsgMap.put(scopes, map);
			}
			map.put(tool.getId(), tool);
		}
	}

	/**
	 * 放入一个动态的请求消息工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月6日 上午10:30:33
	 * @param scopes 作用域
	 * @param tool 请求消息工具
	 */
	public void putDynamicRequestMessage(final ScopeType scopes, final MessageToolBean tool) {
		// 设置为动态对象
		tool.imDynamic();
		{
			Map<Integer, MessageToolBean> map = this.msgMap.get(scopes);
			if (null == map) {
				map = new HashMap<Integer, MessageToolBean>();
				this.msgMap.put(scopes, map);
			}
			map.put(tool.getId(), tool);
		}
		{
			Map<Integer, MessageToolBean> map = this.dynamicMsgMap.get(scopes);
			if (null == map) {
				map = new HashMap<Integer, MessageToolBean>();
				this.dynamicMsgMap.put(scopes, map);
			}
			map.put(tool.getId(), tool);
		}
	}

	/**
	 * 放入一个代理的请求消息工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月12日 下午4:31:48
	 * @param scopes 作用域
	 * @param tool 请求消息工具
	 */
	public void putProxyRequestMessage(final ScopeType scopes, final MessageToolBean tool) {
		{
			Map<Integer, MessageToolBean> map = this.msgMap.get(scopes);
			if (null == map) {
				map = new HashMap<Integer, MessageToolBean>();
				this.msgMap.put(scopes, map);
			}
			map.put(tool.getId(), tool);
		}
		{
			Map<Integer, MessageToolBean> map = this.proxyMsgMap.get(scopes);
			if (null == map) {
				map = new HashMap<Integer, MessageToolBean>();
				this.proxyMsgMap.put(scopes, map);
			}
			map.put(tool.getId(), tool);
		}
	}

	/**
	 * 得到指定请求消息工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:35:14
	 * @param scope 作用域
	 * @param id 目标ID
	 * @return 请求消息工具
	 */
	public MessageToolBean getRequestMessage(final ScopeType scope, final int id) {
		Map<Integer, MessageToolBean> map;
		MessageToolBean tool = null;
		for (final ScopeType st : scope.getRelatedScopes()) {
			if (null != (map = this.msgMap.get(st))) {
				if (null != (tool = map.get(id))) {
					// 已经取得
					break;
				}
			}
		}
		// if (null == map) {
		// throw new ConfigurationOrInstallException("Reuqest Message scopes > " + scopes + " >> id : " + id + " not exists!!");
		// }
		if (null == tool) {
			throw new ConfigurationOrInstallException("Reuqest Message Id > " + id + " not exists in!!" + Arrays.toString(scope.getRelatedScopes()));
		}
		return tool;
	}

	/**
	 * 得到普通消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:31:09
	 * @param scopes 作用域
	 * @param suffix 排序用名称的后缀
	 * @return 消息信息列表
	 */
	public Collection<MessageToolBean> getNormalMessageList(final ScopeType scopes, final String suffix) {
		final Map<Integer, MessageToolBean> map = this.normalMsgMap.get(scopes);
		if (null == map) {
			return null;
		}
		final Collection<MessageToolBean> coll = map.values();
		final List<MessageToolBean> list = new LinkedList<MessageToolBean>(coll);
		Collections.sort(list, new Comparator<MessageToolBean>() {

			@Override
			public int compare(final MessageToolBean b1, final MessageToolBean b2) {
				final String n1 = b1.getProName() + suffix;
				final String n2 = b2.getProName() + suffix;
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 得到动态消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月6日 上午11:01:57
	 * @param scopes 作用域
	 * @param suffix 排序用名称的后缀
	 * @return 消息信息列表
	 */
	public Collection<MessageToolBean> getDynamicMessageList(final ScopeType scopes, final String suffix) {
		final Map<Integer, MessageToolBean> map = this.dynamicMsgMap.get(scopes);
		if (null == map) {
			return null;
		}
		final Collection<MessageToolBean> coll = map.values();
		final List<MessageToolBean> list = new LinkedList<MessageToolBean>(coll);
		Collections.sort(list, new Comparator<MessageToolBean>() {

			@Override
			public int compare(final MessageToolBean b1, final MessageToolBean b2) {
				final String n1 = b1.getProName() + suffix;
				final String n2 = b2.getProName() + suffix;
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 得到代理消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月12日 下午4:32:37
	 * @param scopes 作用域
	 * @param suffix 排序用名称的后缀
	 * @return 消息信息列表
	 */
	public Collection<MessageToolBean> getProxyMessageList(final ScopeType scopes, final String suffix) {
		final Map<Integer, MessageToolBean> map = this.proxyMsgMap.get(scopes);
		if (null == map) {
			return null;
		}
		final Collection<MessageToolBean> coll = map.values();
		final List<MessageToolBean> list = new LinkedList<MessageToolBean>(coll);
		Collections.sort(list, new Comparator<MessageToolBean>() {

			@Override
			public int compare(final MessageToolBean b1, final MessageToolBean b2) {
				final String n1 = b1.getProName() + suffix;
				final String n2 = b2.getProName() + suffix;
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 得到所有消息信息列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月18日 下午3:28:35
	 * @param suffix 排序用名称的后缀
	 * @return 消息信息列表
	 */
	public Collection<MessageToolBean> getAllMessageList(final String suffix) {
		final List<MessageToolBean> list = new LinkedList<MessageToolBean>();
		for (final Map<Integer, MessageToolBean> map : this.msgMap.values()) {
			list.addAll(map.values());
		}
		Collections.sort(list, new Comparator<MessageToolBean>() {

			@Override
			public int compare(final MessageToolBean b1, final MessageToolBean b2) {
				final String n1 = b1.getProName() + suffix;
				final String n2 = b2.getProName() + suffix;
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 得到所有消息信息列表ID排序
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月1日 下午5:25:22
	 * @return 消息信息列表ID排序
	 */
	public Collection<MessageToolBean> getAllMessageListSortId() {
		final List<MessageToolBean> list = new LinkedList<MessageToolBean>();
		for (final Map<Integer, MessageToolBean> map : this.msgMap.values()) {
			list.addAll(map.values());
		}
		Collections.sort(list, new Comparator<MessageToolBean>() {

			@Override
			public int compare(final MessageToolBean b1, final MessageToolBean b2) {
				return b1.getId() > b2.getId() ? 1 : -1;
			}
		});
		return list;
	}

	/**
	 * 得到目标ID的消息<br />
	 * 逐个空间遍历查找<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午3:42:39
	 * @param id 目标ID
	 * @return 目标消息工具
	 */
	public MessageToolBean getMessage(final int id) {
		MessageToolBean mbt;
		for (final Map<Integer, MessageToolBean> m : this.msgMap.values()) {
			if (null != (mbt = m.get(id))) {
				return mbt;
			}
		}
		throw new ConfigurationException("Error Request Param Msg id:" + id);
	}

	/**
	 * 放入一个链接工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:45:57
	 * @param scope 作用域
	 * @param from 请求来源域
	 * @param beanName 对象名（功能名）
	 * @param tool 链接工具
	 */
	public void putLinked(final ScopeType scope, final ScopeType from, final String beanName, final LinkedTool tool) {
		{
			Map<ScopeType, Map<String, RequestTool>> sco = this.requests.get(scope);
			if (null == sco) {
				sco = new HashMap<ScopeType, Map<String, RequestTool>>();
				this.requests.put(scope, sco);
			}
			Map<String, RequestTool> requestM = sco.get(from);
			if (null == requestM) {
				requestM = new HashMap<String, RequestTool>();
				sco.put(from, requestM);
			}
			RequestTool request = requestM.get(beanName);
			if (null == request) {
				request = new RequestToolBean(scope, from, beanName);
				requestM.put(beanName, request);
			}
			request.putLinked(tool);
		}
		{
			Map<ScopeType, Map<String, OperationTool>> sco = this.operations.get(from);
			if (null == sco) {
				sco = new HashMap<ScopeType, Map<String, OperationTool>>();
				this.operations.put(from, sco);
			}
			Map<String, OperationTool> operationM = sco.get(scope);
			if (null == operationM) {
				operationM = new HashMap<String, OperationTool>();
				sco.put(scope, operationM);
			}
			OperationTool operation = operationM.get(beanName);
			if (null == operation) {
				operation = new OperationToolBean(from, scope, beanName);
				operationM.put(beanName, operation);
			}
			operation.putLinked(tool);
		}
	}

	/**
	 * 放入一个接收请求工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午1:40:16
	 * @param rt 接收请求工具
	 */
	private void putRequestTool(final RequestTool rt) {
		Map<ScopeType, Map<String, RequestTool>> sco = this.requests.get(rt.getScope());
		if (null == sco) {
			sco = new HashMap<ScopeType, Map<String, RequestTool>>();
			this.requests.put(rt.getScope(), sco);
			final Map<String, RequestTool> requestM = new HashMap<String, RequestTool>();
			sco.put(rt.getFrom(), requestM);
			requestM.put(rt.getBeanName(), rt);
		} else {
			Map<String, RequestTool> requestM = sco.get(rt.getFrom());
			if (null == requestM) {
				requestM = new HashMap<String, RequestTool>();
				sco.put(rt.getFrom(), requestM);
				requestM.put(rt.getBeanName(), rt);
			} else {
				final RequestTool request = requestM.get(rt.getBeanName());
				if (null == request) {
					// 不存在直接放入
					requestM.put(rt.getBeanName(), rt);
				} else {
					// 存在将连接一个个放入
					for (final LinkedTool l : rt.getLinkedList()) {
						request.putLinked(l);
					}
				}
			}
		}
	}

	/**
	 * 放入一个发送操作工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午1:40:18
	 * @param ot 发送操作工具
	 */
	private void putOperationTool(final OperationTool ot) {
		Map<ScopeType, Map<String, OperationTool>> sco = this.operations.get(ot.getScope());
		if (null == sco) {
			sco = new HashMap<ScopeType, Map<String, OperationTool>>();
			this.operations.put(ot.getScope(), sco);
			final Map<String, OperationTool> operationM = new HashMap<String, OperationTool>();
			sco.put(ot.getTarget(), operationM);
			operationM.put(ot.getBeanName(), ot);
		} else {
			Map<String, OperationTool> operationM = sco.get(ot.getTarget());
			if (null == operationM) {
				operationM = new HashMap<String, OperationTool>();
				sco.put(ot.getTarget(), operationM);
				operationM.put(ot.getBeanName(), ot);
			} else {
				final OperationTool operation = operationM.get(ot.getBeanName());
				if (null == operation) {
					// 不存在直接放入
					operationM.put(ot.getBeanName(), ot);
				} else {
					// 存在将连接一个个放入
					for (final LinkedTool l : ot.getLinkedList()) {
						operation.putLinked(l);
					}
				}
			}
		}
	}

	/**
	 * 放入代理类
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午2:33:32
	 * @param code 代理编码
	 * @param from 来源域
	 * @param proxy 代理域
	 * @param target 目标域
	 * @param beanName 类名（功能名）
	 * @return 生成的代理请求类
	 */
	public ProxyRequestToolBean putProxy(final int code, final ScopeType from, final ScopeType proxy, final ScopeType target, final String beanName) {
		final ProxyRequestToolBean prtb = new ProxyRequestToolBean(from, proxy, target, beanName);
		this.proxys.put(code, prtb);
		return prtb;
	}

	/**
	 * 整理代理连接，将其放入到请求列表中
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午1:20:37
	 */
	public void tidyProxy() {
		for (final ProxyRequestToolBean b : this.proxys.values()) {
			// 得到四种状态内容
			this.putRequestTool(b.getProxyRequestTool());
			this.putRequestTool(b.getTargetRequestTool());
			this.putOperationTool(b.getFromOperationTool());
			this.putOperationTool(b.getProxyOperationTool());
		}
	}

	/**
	 * 得到请求对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午4:31:39
	 * @param scopes 作用域
	 * @return 请求对象列表
	 */
	public Collection<RequestTool> getRequestList(final ScopeType scopes) {
		final Map<ScopeType, Map<String, RequestTool>> map = this.requests.get(scopes);
		if (map == null) {
			return null;
		}
		final List<RequestTool> list = new LinkedList<RequestTool>();
		for (final Map<String, RequestTool> rs : map.values()) {
			list.addAll(rs.values());
		}
		Collections.sort(list, new Comparator<RequestTool>() {

			@Override
			public int compare(final RequestTool b1, final RequestTool b2) {
				final String n1 = b1.getProName();
				final String n2 = b2.getProName();
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 得到操作对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午7:47:28
	 * @param scopes 作用域
	 * @return 操作对象列表
	 */
	public Collection<OperationTool> getOperationList(final ScopeType scopes) {
		final Map<ScopeType, Map<String, OperationTool>> map = this.operations.get(scopes);
		if (map == null) {
			return null;
		}
		final List<OperationTool> list = new LinkedList<OperationTool>();
		for (final Map<String, OperationTool> os : map.values()) {
			list.addAll(os.values());
		}
		Collections.sort(list, new Comparator<OperationTool>() {

			@Override
			public int compare(final OperationTool b1, final OperationTool b2) {
				final String n1 = b1.getProName();
				final String n2 = b2.getProName();
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 得到所有的请求对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月14日 下午9:09:56
	 * @return 请求对象列表
	 */
	public List<RequestTool> getAllRequestList() {
		final List<RequestTool> list = new LinkedList<RequestTool>();
		for (final Map<ScopeType, Map<String, RequestTool>> m : this.requests.values()) {
			for (final Map<String, RequestTool> os : m.values()) {
				list.addAll(os.values());
			}
		}
		Collections.sort(list, new Comparator<RequestTool>() {

			@Override
			public int compare(final RequestTool b1, final RequestTool b2) {
				final String n1 = b1.getProName();
				final String n2 = b2.getProName();
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 得到所有连接对象列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月21日 下午8:14:15
	 * @param suffix 排序用后缀名
	 * @return 连接对象列表
	 */
	public List<LinkedTool> getAllLinkedList(final String suffix) {
		final List<LinkedTool> list = new LinkedList<LinkedTool>();
		for (final Map<ScopeType, Map<String, RequestTool>> m : this.requests.values()) {
			for (final Map<String, RequestTool> rs : m.values()) {
				for (final RequestTool r : rs.values()) {
					list.addAll(r.getLinkedList());
				}
			}
		}
		Collections.sort(list, new Comparator<LinkedTool>() {

			@Override
			public int compare(final LinkedTool b1, final LinkedTool b2) {
				final String n1 = b1.getActionMethodName() + suffix;
				final String n2 = b2.getActionMethodName() + suffix;
				return n1.compareTo(n2);
			}
		});
		return list;
	}

	/**
	 * 初始化工具相关
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午2:55:30
	 */
	public void initTools() {
		for (final Map<Integer, MessageToolBean> mt : this.msgMap.values()) {
			for (final MessageToolBean t : mt.values()) {
				t.init();
			}
		}
	}
}
