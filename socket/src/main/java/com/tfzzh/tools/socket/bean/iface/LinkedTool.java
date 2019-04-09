/**
 * @author Weijie Xu
 * @dateTime 2014年7月3日 下午9:30:49
 */
package com.tfzzh.tools.socket.bean.iface;

import com.tfzzh.tools.socket.bean.MessageToolBean;

/**
 * @author Weijie Xu
 * @dateTime 2014年7月3日 下午9:30:49
 */
public interface LinkedTool {

	/**
	 * 得到相关ID列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午9:32:24
	 * @return 相关ID列表
	 */
	int[] getId();

	/**
	 * 是否范围
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年10月30日 下午7:42:20
	 * @return true，是范围的；<br />
	 *         false，不是范围的；<br />
	 */
	boolean isRange();

	/**
	 * 得到组合的ID字串
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 上午11:17:54
	 * @return 组合的ID字串
	 */
	String getIds();

	/**
	 * 得到接收请求用方法名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午9:32:25
	 * @return 接收请求用方法名
	 */
	String getActionMethodName();

	/**
	 * 得到发送请求用方法名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月22日 下午2:40:44
	 * @return 发送请求用方法名
	 */
	String getOperationMethodName();

	/**
	 * 得到项目其他用方法名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月21日 下午8:21:37
	 * @return 项目用操作方法名
	 */
	String getProMethodName();

	/**
	 * 得到验证用内容
	 * 
	 * @author XuWeijie
	 * @datetime 2015年12月26日_下午4:01:21
	 * @return the validate
	 */
	String getValidate();

	/**
	 * 得到说明，针对代码生成
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午9:32:27
	 * @return 说明
	 */
	String getDesc();

	/**
	 * 得到对应请求数据工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午9:32:28
	 * @return 请求数据工具
	 */
	MessageToolBean getMsg();

	/**
	 * 得到对应客户端所需的数据工具，仅针对不同时候
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月7日 下午3:26:36
	 * @return 客户端所需的数据工具
	 */
	MessageToolBean getClientMsg();

	/**
	 * 是否守护方法<br />
	 * 关系在是否在正常日志中输出<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月3日 下午9:32:26
	 * @return true，是守护方法； false，不是守护方法；
	 */
	boolean isKeep();

	/**
	 * 是否代理方法
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午3:15:12
	 * @return true，是代理方法； false，不是代理方法；
	 */
	boolean isProxy();
}
