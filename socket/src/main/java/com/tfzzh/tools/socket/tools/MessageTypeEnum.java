/**
 * @author 许纬杰
 * @datetime 2016年4月27日_下午8:20:21
 */
package com.tfzzh.tools.socket.tools;

/**
 * 消息类型
 * 
 * @author 许纬杰
 * @datetime 2016年4月27日_下午8:20:21
 */
public enum MessageTypeEnum {
	/**
	 * 是基于Socket/WebSocket的
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月27日_下午8:20:29
	 */
	Socket,
	/**
	 * 是基于HTTP的
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月27日_下午8:20:30
	 */
	Http;

	/**
	 * 得到目标类型对象
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月27日_下午8:32:09
	 * @param type 目标类型值
	 * @return 类型对象
	 */
	public static MessageTypeEnum getType(String type) {
		type = type.toLowerCase();
		for (final MessageTypeEnum e : MessageTypeEnum.values()) {
			if (e.name().toLowerCase().equals(type)) {
				return e;
			}
		}
		// 实际上，应该不可能出现的情况
		return null;
	}
}
