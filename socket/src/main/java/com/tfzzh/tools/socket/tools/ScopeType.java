/**
 * @author Weijie Xu
 * @dateTime 2014年6月11日 下午2:32:43
 */
package com.tfzzh.tools.socket.tools;

/**
 * 作用域范围
 * 
 * @author Weijie Xu
 * @dateTime 2014年6月11日 下午2:32:43
 */
public interface ScopeType {

	/**
	 * 得到相关域
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午4:56:48
	 * @return 相关的域列表，返回有序
	 */
	ScopeType[] getRelatedScopes();

	/**
	 * 得到项目用名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午7:22:36
	 * @return 项目用名
	 */
	String getProName();

	/**
	 * 得到相关的项目路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午6:54:46
	 * @return 相关项目路径
	 */
	String getProjectPath();

	/**
	 * 得到相关需要有修改内容的项目路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午5:28:22
	 * @return 需要有修改内容的项目路径
	 */
	String getProjectChangePath();

	/**
	 * 得到作用域名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午9:03:00
	 * @return 作用域名称
	 */
	String getScopeName();

	/**
	 * 得到说明
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月1日 上午9:44:41
	 * @return 说明
	 */
	String getDesc();
}
