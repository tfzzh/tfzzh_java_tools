/**
 * @author Weijie Xu
 * @dateTime 2014年4月9日 下午3:16:01
 */
package com.tfzzh.tools.socket.tools;

/**
 * 验证的类型
 * 
 * @author Weijie Xu
 * @dateTime 2014年4月9日 下午3:16:01
 */
public enum ValidateTypeEnum {
	/**
	 * 1，是否存在
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:52
	 */
	IsExist(1) {

		@Override
		public String getMethodPath() {
			return "StringValidate.validateIsExist";
		}

		/**
		 * 不需要有对应的显示值
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:45:57
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return null;
		}

		@Override
		public boolean isNumberValidate() {
			return false;
		}

		@Override
		public boolean isStringValidate() {
			return true;
		}
	},
	/**
	 * 2，针对字符串，是否目标长度范围内（包含）
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:52
	 */
	LengthRange(2) {

		@Override
		public String getMethodPath() {
			return "StringValidate.validateLengthRange";
		}

		/**
		 * 长度，已知为int，直接输出就可以
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:46:12
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return val;
		}

		@Override
		public boolean isNumberValidate() {
			return false;
		}

		@Override
		public boolean isStringValidate() {
			return true;
		}
	},
	/**
	 * 11，针对数值，是否在目标范围内（包含（<=，>=））
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:53
	 */
	Contain(11) {

		@Override
		public String getMethodPath() {
			return "NumberValidateTypeEnum.Contain.validate";
		}

		/**
		 * 数值比较，有5种情况，需要逐一判定
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:46:58
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return this.getNumberParamContent(type, val);
		}

		@Override
		public boolean isNumberValidate() {
			return true;
		}

		@Override
		public boolean isStringValidate() {
			return false;
		}
	},
	/**
	 * 12，针对数值，是否在目标范围内（左包含（<=，>））
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:54
	 */
	LeftContain(12) {

		@Override
		public String getMethodPath() {
			return "NumberValidateTypeEnum.LeftContain.validate";
		}

		/**
		 * 数值比较，有5种情况，需要逐一判定
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:48:15
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return this.getNumberParamContent(type, val);
		}

		@Override
		public boolean isNumberValidate() {
			return true;
		}

		@Override
		public boolean isStringValidate() {
			return false;
		}
	},
	/**
	 * 13，针对数值，是否在目标范围内（右包含（<，>=））
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:54
	 */
	RightContain(13) {

		@Override
		public String getMethodPath() {
			return "NumberValidateTypeEnum.RightContain.validate";
		}

		/**
		 * 数值比较，有5种情况，需要逐一判定
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:48:15
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return this.getNumberParamContent(type, val);
		}

		@Override
		public boolean isNumberValidate() {
			return true;
		}

		@Override
		public boolean isStringValidate() {
			return false;
		}
	},
	/**
	 * 14，针对数值，是否在目标范围内（不包含（<，>））
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:55
	 */
	NotContain(14) {

		@Override
		public String getMethodPath() {
			return "NumberValidateTypeEnum.NotContain.validate";
		}

		/**
		 * 数值比较，有5种情况，需要逐一判定
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:48:15
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return this.getNumberParamContent(type, val);
		}

		@Override
		public boolean isNumberValidate() {
			return true;
		}

		@Override
		public boolean isStringValidate() {
			return false;
		}
	},
	/**
	 * 15，针对数值，是否在目标范围内（大于（>））
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:55
	 */
	MoreThan(15) {

		@Override
		public String getMethodPath() {
			return "NumberValidateTypeEnum.MoreThan.validate";
		}

		/**
		 * 数值比较，有5种情况，需要逐一判定
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:48:15
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return this.getNumberParamContent(type, val);
		}

		@Override
		public boolean isNumberValidate() {
			return true;
		}

		@Override
		public boolean isStringValidate() {
			return false;
		}
	},
	/**
	 * 16，针对数值，是否在目标范围内（大于等于（>=））
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:55
	 */
	MoreEqual(16) {

		@Override
		public String getMethodPath() {
			return "NumberValidateTypeEnum.MoreEqual.validate";
		}

		/**
		 * 数值比较，有5种情况，需要逐一判定
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:48:15
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return this.getNumberParamContent(type, val);
		}

		@Override
		public boolean isNumberValidate() {
			return true;
		}

		@Override
		public boolean isStringValidate() {
			return false;
		}
	},
	/**
	 * 17，针对数值，是否在目标范围内（小于（<））
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:56
	 */
	LessThan(17) {

		@Override
		public String getMethodPath() {
			return "NumberValidateTypeEnum.LessThan.validate";
		}

		/**
		 * 数值比较，有5种情况，需要逐一判定
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:48:15
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return this.getNumberParamContent(type, val);
		}

		@Override
		public boolean isNumberValidate() {
			return true;
		}

		@Override
		public boolean isStringValidate() {
			return false;
		}
	},
	/**
	 * 18，针对数值，是否在目标范围内（小于等于（<=））
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:20:56
	 */
	LessEqual(18) {

		@Override
		public String getMethodPath() {
			return "NumberValidateTypeEnum.LessEqual.validate";
		}

		/**
		 * 数值比较，有5种情况，需要逐一判定
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:48:15
		 * @see com.tfzzh.tools.socket.tools.ValidateTypeEnum#getParamContent(com.tfzzh.tools.socket.tools.FieldTypeEnum, java.lang.String)
		 */
		@Override
		public String getParamContent(final FieldTypeEnum type, final String val) {
			return this.getNumberParamContent(type, val);
		}

		@Override
		public boolean isNumberValidate() {
			return true;
		}

		@Override
		public boolean isStringValidate() {
			return false;
		}
	};

	/**
	 * 对应值
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:19:36
	 */
	private final int val;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:19:35
	 * @param val 目标值
	 */
	ValidateTypeEnum(final int val) {
		this.val = val;
	}

	/**
	 * 得到方法路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午7:59:01
	 * @return 方法路径
	 */
	public abstract String getMethodPath();

	/**
	 * 得到显示用内容
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午7:44:59
	 * @param type 字段类型
	 * @param val 目标值
	 * @return 显示用值
	 */
	public abstract String getParamContent(FieldTypeEnum type, String val);

	/**
	 * 是否数值型验证
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午8:12:51
	 * @return true，是数值验证；<br />
	 *         false，不是数值型验证；<br />
	 */
	public abstract boolean isNumberValidate();

	/**
	 * 是否字符串型验证
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午8:12:52
	 * @return true，是字符串型验证；<br />
	 *         false，不是字符串型验证；<br />
	 */
	public abstract boolean isStringValidate();

	/**
	 * 得到数值字段显示用内容
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月10日 下午5:30:11
	 * @param type 字段类型
	 * @param val 目标值
	 * @return 显示用值
	 */
	protected String getNumberParamContent(final FieldTypeEnum type, final String val) {
		switch (type) {
		case Int:
			return val;
		case Long:
			return val + "l";
		case Short:
			return "(short) " + val;
		case Float:
			return val + "f";
		case Double:
			return val + "d";
		default: // 正常来说不可能出现的情况，作为一般字符串处理
			return "\"" + val + "\"";
		}
	}

	/**
	 * 根据类型值得到目标类型对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午3:25:04
	 * @param type 类型值
	 * @return 目标类型对象
	 */
	public static ValidateTypeEnum getType(final int type) {
		for (final ValidateTypeEnum t : ValidateTypeEnum.values()) {
			if (t.val == type) {
				return t;
			}
		}
		return null;
	}
}
