/**
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午7:54:27
 */
package com.tfzzh.tools.socket.bean;

import com.tfzzh.tools.socket.bean.iface.LinkedTool;
import com.tfzzh.tools.socket.tools.ScopeType;

/**
 * 链接工具对象
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午7:54:27
 */
public class LinkedToolBean implements LinkedTool {

	/**
	 * ID
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:44
	 */
	private final int[] id;

	// /**
	// * 连接所在类名，可以为null
	// *
	// * @author XuWeijie
	// * @datetime 2015年10月14日_下午8:31:13
	// */
	// private final String name;
	//
	// /**
	// * 连接所在类名，可以为null
	// *
	// * @author XuWeijie
	// * @datetime 2015年10月14日_下午8:31:54
	// */
	// private final String proName;
	/**
	 * 是否范围的
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年10月30日 下午7:40:15
	 */
	private final boolean isRange;

	/**
	 * 操作方法名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:43
	 */
	private final String actionMethodName;

	/**
	 * 项目用类名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月21日 下午7:26:35
	 */
	private final String proMethodName;

	/**
	 * 项目用方法名，针对在Operation中方法命名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月22日 下午12:51:13
	 */
	private final String operationMethodName;

	/**
	 * 验证用内容
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月26日_下午3:59:56
	 */
	private final String validate;

	/**
	 * 说明，针对代码生成
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:43
	 */
	private final String desc;

	/**
	 * 对应请求数据工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:40
	 */
	private final MessageToolBean msg;

	/**
	 * 对应客户端所需的数据工具，仅针对不同时候
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月7日 下午3:24:26
	 */
	private final MessageToolBean clientMsg;

	/**
	 * 是否守护方法
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月29日 下午7:09:41
	 */
	private final boolean isKeep;

	/**
	 * 是否代理方法
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午3:18:44
	 */
	private final boolean isProxy;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:41
	 * @param id ID
	 * @param scope 当前所在域
	 * @param from 请求来源域
	 * @param methodName 请求对应的方法名
	 * @param validate 验证用数据
	 * @param desc 说明，针对代码生成
	 * @param msg 请求数据工具
	 * @param clientMsg 请求数据工具
	 * @param isKeep 是否守护方法
	 * @param isProxy 是否代理方法
	 */
	public LinkedToolBean(final int[] id, final ScopeType scope, final ScopeType from, final String methodName, final String validate, final String desc, final MessageToolBean msg, final MessageToolBean clientMsg, final boolean isKeep, final boolean isProxy) {
		this.id = id;
		this.isRange = false;
		this.actionMethodName = methodName;
		final String tmpProName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
		this.proMethodName = "From" + from.getProName() + "To" + scope.getProName() + tmpProName;
		if (isProxy) {
			this.operationMethodName = "to" + scope.getProName() + "With" + from.getProName() + tmpProName;
		} else {
			this.operationMethodName = "to" + tmpProName;
		}
		this.validate = validate;
		this.desc = desc;
		this.msg = msg;
		this.clientMsg = clientMsg;
		this.isKeep = isKeep;
		this.isProxy = isProxy;
	}

	// public LinkedToolBean(final int[] id, final String name, final ScopeType scope, final ScopeType from, final String methodName, final String desc, final MessageToolBean msg, final MessageToolBean clientMsg, final boolean isKeep, final boolean isProxy) {
	// this.id = id;
	// this.isRange = false;
	// this.actionMethodName = methodName;
	// final String tmpProName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
	// if (null == name || name.length() == 0) {
	// this.proMethodName = "From" + from.getProName() + "To" + scope.getProName() + tmpProName;
	// this.operationMethodName = "to" + scope.getProName() + "With" + from.getProName() + tmpProName;
	// } else {
	// this.proMethodName = "From" + from.getProName() + "To" + scope.getProName() + tmpProName;
	// this.operationMethodName = "to" + scope.getProName() + "With" + from.getProName() + tmpProName;
	// }
	// this.desc = desc;
	// this.msg = msg;
	// this.clientMsg = clientMsg;
	// this.isKeep = isKeep;
	// this.isProxy = isProxy;
	// }
	/**
	 * @author Weijie Xu
	 * @dateTime 2014年10月30日 下午7:41:18
	 * @param minId 范围ID小值
	 * @param maxId 范围ID大值
	 * @param scope 当前所在域
	 * @param from 请求来源域
	 * @param methodName 请求对应的方法名
	 * @param validate 验证用数据
	 * @param desc 说明，针对代码生成
	 * @param msg 请求数据工具
	 * @param clientMsg 请求数据工具
	 * @param isKeep 是否守护方法
	 * @param isProxy 是否代理方法
	 */
	public LinkedToolBean(final int minId, final int maxId, final ScopeType scope, final ScopeType from, final String methodName, final String validate, final String desc, final MessageToolBean msg, final MessageToolBean clientMsg, final boolean isKeep,
			final boolean isProxy) {
		this.id = new int[] { minId, maxId };
		// this.name = null;
		this.isRange = true;
		this.actionMethodName = methodName;
		final String tmpProName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
		this.proMethodName = "From" + from.getProName() + "To" + scope.getProName() + tmpProName;
		if (isProxy) {
			this.operationMethodName = "to" + scope.getProName() + "With" + from.getProName() + tmpProName;
		} else {
			this.operationMethodName = "to" + tmpProName;
		}
		this.validate = validate;
		this.desc = desc;
		this.msg = msg;
		this.clientMsg = clientMsg;
		this.isKeep = isKeep;
		this.isProxy = isProxy;
	}

	/**
	 * 得到相关ID列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午11:18:36
	 * @return 相关ID列表
	 * @see com.tfzzh.tools.socket.bean.iface.LinkedTool#getId()
	 */
	@Override
	public int[] getId() {
		return this.id;
	}

	// /**
	// * 得到连接所在类名，可以为null
	// *
	// * @author XuWeijie
	// * @datetime 2015年10月14日_下午8:25:49
	// * @return the name
	// */
	// public String getName() {
	// return this.name;
	// }
	/**
	 * 是否范围
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年10月30日 下午7:43:05
	 * @return true，是范围的；<br />
	 *         false，不是范围的；<br />
	 * @see com.tfzzh.tools.socket.bean.iface.LinkedTool#isRange()
	 */
	@Override
	public boolean isRange() {
		return this.isRange;
	}

	/**
	 * 得到组合的ID字串
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:34
	 * @return 组合的ID字串
	 * @see com.tfzzh.tools.socket.bean.iface.LinkedTool#getIds()
	 */
	@Override
	public String getIds() {
		if (this.id.length == 1) {
			return String.valueOf(this.id[0]);
		} else {
			final StringBuilder sb = new StringBuilder();
			boolean isFirst = true;
			for (final int i : this.id) {
				if (isFirst) {
					isFirst = false;
				} else {
					sb.append(", ");
				}
				sb.append(i);
			}
			return sb.toString();
		}
	}

	/**
	 * 得到操作方法名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:34
	 * @return the methodName
	 */
	@Override
	public String getActionMethodName() {
		return this.actionMethodName;
	}

	/**
	 * 得到项目用方法名，针对在Operation中方法命名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月22日 下午2:40:44
	 * @return the proOperationMethodName
	 */
	@Override
	public String getOperationMethodName() {
		return this.operationMethodName;
	}

	/**
	 * 得到项目其他用方法名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月21日 下午7:27:52
	 * @return the proMethodName
	 */
	@Override
	public String getProMethodName() {
		return this.proMethodName;
	}

	/**
	 * 得到验证用内容
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月26日_下午4:01:21
	 * @return the validate
	 */
	@Override
	public String getValidate() {
		return this.validate;
	}

	/**
	 * 得到说明，针对代码生成
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:34
	 * @return the desc
	 */
	@Override
	public String getDesc() {
		return this.desc;
	}

	/**
	 * 得到对应请求数据工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-22 上午9:41:34
	 * @return the msg
	 */
	@Override
	public MessageToolBean getMsg() {
		return this.msg;
	}

	/**
	 * 得到对应客户端所需的数据工具，仅针对不同时候
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月7日 下午3:26:25
	 * @return 客户端所需的数据工具
	 */
	@Override
	public MessageToolBean getClientMsg() {
		return this.clientMsg;
	}

	/**
	 * 是否守护方法
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月29日 下午7:09:57
	 * @return the isKeep
	 */
	@Override
	public boolean isKeep() {
		return this.isKeep;
	}

	/**
	 * 是否代理方法
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午3:19:57
	 * @see com.tfzzh.tools.socket.bean.iface.LinkedTool#isProxy()
	 */
	@Override
	public boolean isProxy() {
		return this.isProxy;
	}
}
