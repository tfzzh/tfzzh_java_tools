/**
 * @author Weijie Xu
 * @dateTime 2014年6月11日 下午2:55:58
 */
package com.tfzzh.tools.socket.tools;

import com.tfzzh.exception.InitializeException;

/**
 * 空间工具
 * 
 * @author Weijie Xu
 * @dateTime 2014年6月11日 下午2:55:58
 */
public class ScopeTool {

	/**
	 * 唯一实例
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:06:02
	 */
	private static ScopeTool tool = new ScopeTool();

	/**
	 * 空间类型工具
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:11:42
	 */
	private ScopeTypeTool typeTools;

	/**
	 * 是否已经初始化
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:06:52
	 */
	private boolean isInit = false;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:06:04
	 */
	private ScopeTool() {
	}

	/**
	 * 得到对象实例
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:12:38
	 * @return 对象唯一实例
	 */
	public static ScopeTool getInstance() {
		return ScopeTool.tool;
	}

	/**
	 * 一些初始化操作
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:13:20
	 * @param typeTools 空间类型工具
	 */
	public void init(final ScopeTypeTool typeTools) {
		this.typeTools = typeTools;
		this.isInit = true;
	}

	/**
	 * 得到指定空间类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:17:42
	 * @param type 类型值
	 * @return 目标空间类型
	 */
	public ScopeType getScopeType(final String type) {
		this.validateInit();
		return this.typeTools.getType(type);
	}

	/**
	 * 得到所有空间类型的列表（数组模式）
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:19:32
	 * @return 所有空间类型的列表
	 */
	public ScopeType[] getAllScopeType() {
		this.validateInit();
		return this.typeTools.getAllType();
	}

	/**
	 * 得到默认的空间类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月18日 下午3:48:17
	 * @return 默认的空间类型
	 */
	public ScopeType getDefaultScopeType() {
		this.validateInit();
		return this.typeTools.getDefaultType();
	}

	/**
	 * 验证初始化，如果还未初始化，则抛出异常
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月11日 下午3:16:54
	 */
	private void validateInit() {
		if (!this.isInit) {
			throw new InitializeException("Need Call init Before...");
		}
	}
}
