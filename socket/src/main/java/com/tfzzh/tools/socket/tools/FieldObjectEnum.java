/**
 * @author Weijie Xu
 * @dateTime 2014-3-8 上午10:38:52
 */
package com.tfzzh.tools.socket.tools;

import com.tfzzh.tools.socket.bean.MessageToolBean;

/**
 * 字段对象
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-8 上午10:38:52
 */
public enum FieldObjectEnum {
	/**
	 * 对象内容的列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:49
	 */
	ListOj {

		@Override
		public String getObjectTypeName() {
			return "List";
		}

		@Override
		public String getTypeName(final MessageToolBean bean) {
			return "List<" + bean.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX + ">";
		}

		@Override
		public String getCompleteTypeName(final MessageToolBean bean) {
			return "List<" + bean.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX + ">";
		}

		/**
		 * 是否为列表（List）类型 @author Weijie Xu
		 * 
		 * @dateTime 2014年11月28日 下午12:30:26
		 * @return 这里一定是
		 * @see com.tfzzh.tools.socket.tools.FieldObjectEnum#isList()
		 */
		@Override
		public boolean isList() {
			return true;
		}
	},
	/**
	 * 基本对象内容的列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年10月13日 下午8:26:45
	 */
	ListBs {

		@Override
		public String getObjectTypeName() {
			return "List";
		}

		@Override
		public String getTypeName(final MessageToolBean bean) {
			return "List<" + bean.getName() + ">";
		}

		@Override
		public String getCompleteTypeName(final MessageToolBean bean) {
			return "List<" + bean.getName() + ">";
		}

		/**
		 * 是否为列表（List）类型 @author Weijie Xu
		 * 
		 * @dateTime 2014年11月28日 下午12:30:26
		 * @return 这里一定是
		 * @see com.tfzzh.tools.socket.tools.FieldObjectEnum#isList()
		 */
		@Override
		public boolean isList() {
			return true;
		}

		/**
		 * 是否使用的MessageBean作为元素
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年10月22日 下午7:19:08
		 * @return false，非使用MessageBean作为元素；
		 * @see com.tfzzh.tools.socket.tools.FieldObjectEnum#isUseMessageBean()
		 */
		@Override
		public boolean isUseMessageBean() {
			return false;
		}
	},
	/**
	 * 对象类型数组模型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月27日 上午10:38:57
	 */
	ArrayOj {

		@Override
		public String getObjectTypeName() {
			return "Array";
		}

		@Override
		public String getTypeName(final MessageToolBean bean) {
			return bean.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX + "[]";
		}

		@Override
		public String getCompleteTypeName(final MessageToolBean bean) {
			return bean.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX + "[]";
		}
	},
	/**
	 * 基础类型数组模型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月27日 上午10:38:57
	 */
	ArrayBs {

		@Override
		public String getObjectTypeName() {
			return "Array";
		}

		@Override
		public String getTypeName(final MessageToolBean bean) {
			return bean.getName() + "[]";
		}

		@Override
		public String getCompleteTypeName(final MessageToolBean bean) {
			return bean.getName() + "[]";
		}

		/**
		 * 是否使用的MessageBean作为元素
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月27日 上午10:40:45
		 * @return false，非使用MessageBean作为元素；
		 * @see com.tfzzh.tools.socket.tools.FieldObjectEnum#isUseMessageBean()
		 */
		@Override
		public boolean isUseMessageBean() {
			return false;
		}
	},
	/**
	 * 对象类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:50
	 */
	Bean {

		@Override
		public String getObjectTypeName() {
			return "Bean";
		}

		@Override
		public String getTypeName(final MessageToolBean bean) {
			return bean.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX;
		}

		@Override
		public String getCompleteTypeName(final MessageToolBean bean) {
			return bean.getProName() + SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX;
		}

		/**
		 * 是否需要长度类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月27日 下午2:39:52
		 * @return 需要
		 * @see com.tfzzh.tools.socket.tools.FieldObjectEnum#isNeedSizeType()
		 */
		@Override
		public boolean isNeedSizeType() {
			return false;
		}
	};

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午1:43:03
	 */
	private final String val;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午1:43:01
	 */
	FieldObjectEnum() {
		this.val = this.name().toLowerCase();
	}

	/**
	 * 得到类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午3:43:15
	 * @return 类型名称
	 */
	public abstract String getObjectTypeName();

	/**
	 * 得到完整的类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月18日 下午4:25:08
	 * @param bean 消息信息工具
	 * @return 完整的类型名称
	 */
	public abstract String getCompleteTypeName(MessageToolBean bean);

	/**
	 * 得到类型显示用信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午2:36:24
	 * @param bean 消息信息工具
	 * @return 得到显示用名
	 */
	public abstract String getTypeName(MessageToolBean bean);

	/**
	 * 是否为列表（List）类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月28日 下午12:29:31
	 * @return 这里一定不是
	 */
	public boolean isList() {
		return false;
	}

	/**
	 * 是否需要长度类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月27日 下午2:39:22
	 * @return 默认为需要
	 */
	public boolean isNeedSizeType() {
		return true;
	}

	/**
	 * 是否使用的MessageBean作为元素
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年10月22日 下午7:17:46
	 * @return true，是的；<br />
	 *         false，非使用MessageBean作为元素；<br />
	 */
	public boolean isUseMessageBean() {
		return true;
	}

	/**
	 * 得到对应的目标类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午1:43:09
	 * @param type 目标名
	 * @return 对应的类型；<br />
	 *         null，不存在目标类型；<br />
	 */
	public static FieldObjectEnum getType(final String type) {
		for (final FieldObjectEnum ft : FieldObjectEnum.values()) {
			if (ft.val.equalsIgnoreCase(type)) {
				return ft;
			}
		}
		return null;
	}
}
