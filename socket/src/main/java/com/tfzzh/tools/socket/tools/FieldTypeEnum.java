/**
 * @author Weijie Xu
 * @dateTime 2014-3-6 下午5:18:00
 */
package com.tfzzh.tools.socket.tools;

/**
 * 字段类型
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-6 下午5:18:00
 */
public enum FieldTypeEnum {
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:34
	 */
	String {

		@Override
		public Object defaultValue() {
			return null;
		}

		@Override
		public java.lang.String getTypeName() {
			return "String";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "String";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "String";
		}

		@Override
		public boolean isCanNull() {
			return true;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:36
	 */
	Int {

		@Override
		public Object defaultValue() {
			return 0;
		}

		@Override
		public java.lang.String getTypeName() {
			return "int";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Int";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Integer";
		}

		@Override
		public boolean isCanNull() {
			return false;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:36
	 */
	Integer {

		@Override
		public Object defaultValue() {
			return 0;
		}

		@Override
		public java.lang.String getTypeName() {
			return "Integer";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Int";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Integer";
		}

		@Override
		public boolean isCanNull() {
			return true;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:38
	 */
	Long {

		@Override
		public Object defaultValue() {
			return 0;
		}

		@Override
		public java.lang.String getTypeName() {
			return "long";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Long";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Long";
		}

		@Override
		public boolean isCanNull() {
			return false;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:39
	 */
	Short {

		@Override
		public Object defaultValue() {
			return (short) 0;
		}

		@Override
		public java.lang.String getTypeName() {
			return "short";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Short";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Short";
		}

		@Override
		public boolean isCanNull() {
			return false;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:41
	 */
	Float {

		@Override
		public Object defaultValue() {
			return 0f;
		}

		@Override
		public java.lang.String getTypeName() {
			return "float";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Float";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Float";
		}

		@Override
		public boolean isCanNull() {
			return false;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:42
	 */
	Double {

		@Override
		public Object defaultValue() {
			return 0d;
		}

		@Override
		public java.lang.String getTypeName() {
			return "double";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Double";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Double";
		}

		@Override
		public boolean isCanNull() {
			return false;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:43
	 */
	Boolean {

		@Override
		public Object defaultValue() {
			return false;
		}

		@Override
		public java.lang.String getTypeName() {
			return "boolean";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Boolean";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Boolean";
		}

		@Override
		public boolean isCanNull() {
			return false;
		}
	},
	/**
	 * Character
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:27:43
	 */
	Char {

		@Override
		public Object defaultValue() {
			return (char) 0;
		}

		@Override
		public java.lang.String getTypeName() {
			return "char";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Char";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Character";
		}

		@Override
		public boolean isCanNull() {
			return false;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午12:36:01
	 */
	Byte {

		@Override
		public Object defaultValue() {
			return (byte) 0;
		}

		@Override
		public java.lang.String getTypeName() {
			return "byte";
		}

		@Override
		public java.lang.String getObjectTypeName() {
			return "Byte";
		}

		@Override
		public java.lang.String getCompleteTypeName() {
			return "Byte";
		}

		@Override
		public boolean isCanNull() {
			return false;
		}
	};

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午6:33:59
	 */
	private final String val;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午6:33:58
	 */
	FieldTypeEnum() {
		this.val = this.name().toLowerCase();
	}

	/**
	 * 默认的值
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 上午11:08:15
	 * @return 默认的值
	 */
	public abstract Object defaultValue();

	/**
	 * 得到类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午4:49:10
	 * @return 得到显示用名
	 */
	public abstract String getTypeName();

	/**
	 * 得到对象模式的类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午1:35:33
	 * @return 对象模式类型名称
	 */
	public abstract String getObjectTypeName();

	/**
	 * 得到完整的类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月18日 下午4:26:29
	 * @return 完整的类型名称
	 */
	public abstract String getCompleteTypeName();

	/**
	 * 是否可以为空值
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月16日 下午7:23:44
	 * @return true，可以空；<br />
	 *         false，不能为空；<br />
	 */
	public abstract boolean isCanNull();

	/**
	 * 得到对应的目标类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午6:35:14
	 * @param type 目标名
	 * @return 对应的类型；<br />
	 *         null，不存在目标类型；<br />
	 */
	public static FieldTypeEnum getType(final String type) {
		for (final FieldTypeEnum ft : FieldTypeEnum.values()) {
			if (ft.val.equalsIgnoreCase(type)) {
				return ft;
			}
		}
		return null;
	}
}
