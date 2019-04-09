/**
 * @author Weijie Xu
 * @dateTime 2014年6月11日 下午3:08:51
 */
package com.tfzzh.tools.socket.tools;

/**
 * 空间类型工具
 * 
 * @author Weijie Xu
 * @dateTime 2014年6月11日 下午3:08:51
 */
public abstract class ScopeTypeTool {

	/**
	 * 根据类型值得到指定空间类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:09:51
	 * @param type 类型值
	 * @return 目标空间类型
	 */
	public abstract ScopeType getType(String type);

	/**
	 * 得到所有空间类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:10:43
	 * @return 所有空间类型
	 */
	public abstract ScopeType[] getAllType();

	/**
	 * 得到默认的空间类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月18日 下午3:46:37
	 * @return 默认的空间类型
	 */
	public abstract ScopeType getDefaultType();
}
