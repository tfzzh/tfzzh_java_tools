/**
 * @author Weijie Xu
 * @dateTime Aug 12, 2014 7:26:16 PM
 */
package com.tfzzh.tools.data.tools;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.exception.NotAvailableOperationModeException;

/**
 * 数据库字段类型
 * 
 * @author Weijie Xu
 * @dateTime Aug 12, 2014 7:26:16 PM
 */
public enum DatabaseFieldTypeEnum {

	/**
	 * 定长字串
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:29 PM
	 */
	dfChar("char") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjString;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder(this.val.length() + length.length() + 2).append(this.val).append('(').append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len); // * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}
	},
	/**
	 * 不定常字串
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:29 PM
	 */
	dfVarchar("varchar") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjString;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder(this.val.length() + length.length() + 2).append(this.val).append('(').append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len); // * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}
	},
	/**
	 * String类型，针对一些NoSQL数据库
	 * 
	 * @author tfzzh
	 * @dateTime 2016年12月30日 上午11:14:14
	 */
	dfString("string") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjString;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder(this.val.length() + length.length() + 2).append(this.val).append('(').append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len); // * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}
	},
	/**
	 * 普通int
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:30 PM
	 */
	dfInt("int") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjInteger;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 4;
		}

		@Override
		public String getRsTypeName() {
			return "Int";
		}

		/**
		 * 得到默认值
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月18日_下午3:05:39
		 * @param defValue 输入的默认值
		 * @return 处理后的默认值
		 */
		@Override
		public String getDefValue(final String defValue) {
			if (null == defValue) {
				return "";
			}
			final int ind = defValue.indexOf(".");
			if (ind != -1) {
				return defValue.substring(0, ind);
			}
			return defValue;
		}
	},
	/**
	 * Integer类型，针对一些NoSQL数据库
	 * 
	 * @author tfzzh
	 * @dateTime 2016年12月30日 上午11:15:43
	 */
	dfInteger("integer") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjInteger;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 4;
		}

		@Override
		public String getRsTypeName() {
			return "Int";
		}

		/**
		 * 得到默认值
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月18日_下午3:05:39
		 * @param defValue 输入的默认值
		 * @return 处理后的默认值
		 */
		@Override
		public String getDefValue(final String defValue) {
			if (null == defValue) {
				return "";
			}
			final int ind = defValue.indexOf(".");
			if (ind != -1) {
				return defValue.substring(0, ind);
			}
			return defValue;
		}
	},
	/**
	 * 小int
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:30 PM
	 */
	dfTinyint("tinyint") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjShort;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 1;
		}

		@Override
		public String getRsTypeName() {
			return "Short";
		}

		/**
		 * 得到默认值
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月18日_下午3:05:39
		 * @param defValue 输入的默认值
		 * @return 处理后的默认值
		 */
		@Override
		public String getDefValue(final String defValue) {
			if (null == defValue) {
				return "";
			}
			final int ind = defValue.indexOf(".");
			if (ind != -1) {
				return defValue.substring(0, ind);
			}
			return defValue;
		}
	},
	/**
	 * 小int
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:30 PM
	 */
	dfSmallint("smallint") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjShort;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 4;
		}

		@Override
		public String getRsTypeName() {
			return "Short";
		}

		/**
		 * 得到默认值
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月18日_下午3:05:39
		 * @param defValue 输入的默认值
		 * @return 处理后的默认值
		 */
		@Override
		public String getDefValue(final String defValue) {
			if (null == defValue) {
				return "";
			}
			final int ind = defValue.indexOf(".");
			if (ind != -1) {
				return defValue.substring(0, ind);
			}
			return defValue;
		}
	},
	/**
	 * 大int
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:31 PM
	 */
	dfBigint("bigint") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjLong;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 8;
		}

		@Override
		public String getRsTypeName() {
			return "Long";
		}

		/**
		 * 得到默认值
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月18日_下午3:05:39
		 * @param defValue 输入的默认值
		 * @return 处理后的默认值
		 */
		@Override
		public String getDefValue(final String defValue) {
			if (null == defValue) {
				return "";
			}
			final int ind = defValue.indexOf(".");
			if (ind != -1) {
				return defValue.substring(0, ind);
			}
			return defValue;
		}
	},
	/**
	 * @author tfzzh
	 * @dateTime 2016年12月30日 上午11:18:45
	 */
	dfLong("long") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjLong;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 8;
		}

		@Override
		public String getRsTypeName() {
			return "Long";
		}

		/**
		 * 得到默认值
		 * 
		 * @author XuWeijie
		 * @datetime 2015年9月18日_下午3:05:39
		 * @param defValue 输入的默认值
		 * @return 处理后的默认值
		 */
		@Override
		public String getDefValue(final String defValue) {
			if (null == defValue) {
				return "";
			}
			final int ind = defValue.indexOf(".");
			if (ind != -1) {
				return defValue.substring(0, ind);
			}
			return defValue;
		}
	},
	/**
	 * 长浮点型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:31 PM
	 */
	dfDouble("double") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjDouble;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 8;
		}

		@Override
		public String getRsTypeName() {
			return "Double";
		}
	},
	/**
	 * 短浮点型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:32 PM
	 */
	dfFloat("float") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjFloat;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 4;
		}

		@Override
		public String getRsTypeName() {
			return "Float";
		}
	},
	/**
	 * 布尔型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年9月18日 上午10:44:17
	 */
	dfBoolean("boolean") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjBoolean;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 0;
		}

		@Override
		public String getRsTypeName() {
			return "Boolean";
		}
	},
	/**
	 * 文本型，对应较大的文本内容 len：65535
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:32 PM
	 */
	dfText("text") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjString;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 0;
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}
	},
	/**
	 * len：16777215
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 4, 2014 2:55:31 PM
	 */
	dfMediumText("mediumtext") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjString;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 0;
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}
	},
	/**
	 * len：4294967295
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 4, 2014 2:55:18 PM
	 */
	dfLongText("longtext") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjString;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 0;
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}
	},
	/**
	 * 日期时间，暂定与long对应
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:33 PM
	 */
	dfDatetime("datetime") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjDate;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 8;
		}

		@Override
		public String getRsTypeName() {
			return "Timestamp";
		}
	},
	/**
	 * 日期时间，暂定与long对应
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:33 PM
	 */
	dfDate("date") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjDateDay;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 8;
		}

		@Override
		public String getRsTypeName() {
			return "Date";
		}
	},
	/**
	 * 日期时间，暂定与long对应
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:33 PM
	 */
	dfTime("time") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjDateTime;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 8;
		}

		@Override
		public String getRsTypeName() {
			return "Time";
		}
	},
	/**
	 * 二进制为主的数据记录方式
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:38:33 PM
	 */
	dfBit("bit") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjByte;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder(this.val.length() + length.length() + 2).append(this.val).append('(').append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len);
		}

		@Override
		public String getRsTypeName() {
			return "Byte";
		}
	},
	/**
	 * len：65535
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 4, 2014 2:55:55 PM
	 */
	dfBlob("blob") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.IfInputStream;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 0;
		}

		@Override
		public String getRsTypeName() {
			return "Blob";
		}
	},
	/**
	 * len：255
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 4, 2014 3:01:53 PM
	 */
	dfTinyBlob("tinyblob") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.IfInputStream;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 0;
		}

		@Override
		public String getRsTypeName() {
			return "Blob";
		}
	},
	/**
	 * len：16777215
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 4, 2014 3:01:53 PM
	 */
	dfMediumBlob("mediumblob") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.IfInputStream;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 0;
		}

		@Override
		public String getRsTypeName() {
			return "Blob";
		}
	},
	/**
	 * len：4294967295
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 4, 2014 3:02:13 PM
	 */
	dfLongBlob("longblob") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.IfInputStream;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return this.val;
		}

		@Override
		public int getLengthNum(final String len) {
			return 0;
		}

		@Override
		public String getRsTypeName() {
			return "Blob";
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014年12月23日 下午3:51:44
	 */
	dfVarcharToInteger("varcharToInteger") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjInteger;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder("varchar".length() + length.length() + 2).append("varchar(").append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len); // * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}

		@Override
		public boolean isNeedParse() {
			return true;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014年12月23日 下午3:51:44
	 */
	dfVarcharToLong("varcharToLong") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjLong;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder("varchar".length() + length.length() + 2).append("varchar(").append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len) * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}

		@Override
		public boolean isNeedParse() {
			return true;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014年12月23日 下午3:51:43
	 */
	dfVarcharToShort("varcharToShort") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjShort;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder("varchar".length() + length.length() + 2).append("varchar(").append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len); // * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}

		@Override
		public boolean isNeedParse() {
			return true;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014年12月23日 下午3:51:43
	 */
	dfVarcharToDouble("varcharToDouble") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjDouble;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder("varchar".length() + length.length() + 2).append("varchar(").append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len) * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}

		@Override
		public boolean isNeedParse() {
			return true;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014年12月23日 下午3:51:42
	 */
	dfVarcharToFloat("varcharToFloat") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjFloat;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder("varchar".length() + length.length() + 2).append("varchar(").append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len); // * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}

		@Override
		public boolean isNeedParse() {
			return true;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014年12月23日 下午3:51:41
	 */
	dfVarcharToBoolean("varcharToBoolean") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjBoolean;
		}

		@Override
		public String getDataFieldContent(final String length) {
			return new StringBuilder("varchar".length() + length.length() + 2).append("varchar(").append(length).append(')').toString();
		}

		@Override
		public int getLengthNum(final String len) {
			return Integer.parseInt(len); // * DataConfigConstants.CHAR_ENCODEING.getCharacterBase();
		}

		@Override
		public String getRsTypeName() {
			return "String";
		}

		@Override
		public boolean isNeedParse() {
			return true;
		}
	},
	/**
	 * @author tfzzh
	 * @dateTime 2016年12月30日 下午12:27:16
	 */
	dfObject("object") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjObject;
		}

		@Override
		public String getDataFieldContent(final String length) {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public int getLengthNum(final String len) {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getRsTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public boolean isNeedParse() {
			return true;
		}
	},
	/**
	 * @author tfzzh
	 * @dateTime 2016年12月30日 下午12:27:31
	 */
	dfArrays("arrays") {

		@Override
		public FieldTypeEnum getClassFieldType() {
			return FieldTypeEnum.OjArrays;
		}

		@Override
		public String getDataFieldContent(final String length) {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public int getLengthNum(final String len) {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getRsTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public boolean isNeedParse() {
			return true;
		}
	};

	/**
	 * 对应的值
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:40:20 PM
	 */
	protected final String val;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:40:21 PM
	 * @param val 对应的值
	 */
	DatabaseFieldTypeEnum(final String val) {
		this.val = val;
	}

	/**
	 * 得到数据字段类型名
	 * 
	 * @author XuWeijie
	 * @datetime 2015年9月17日_下午8:22:36
	 * @return the val
	 */
	public String getTypeName() {
		return this.val;
	}

	/**
	 * 得到对应类属性类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:47:47 PM
	 * @return 对应的类字段类型
	 */
	public abstract FieldTypeEnum getClassFieldType();

	/**
	 * 得到数据库用字段类型内容
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:48:41 PM
	 * @return 对应的数据库用字段类型内容
	 * @param length 对应的长度内容
	 */
	public abstract String getDataFieldContent(String length);

	/**
	 * 得到所相关定义的字段长度
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 2:37:46 PM
	 * @param len 录入的内容
	 * @return 对应的长度值
	 */
	public abstract int getLengthNum(String len);

	/**
	 * 得到默认值
	 * 
	 * @author XuWeijie
	 * @datetime 2015年9月18日_下午3:05:39
	 * @param defValue 输入的默认值
	 * @return 处理后的默认值
	 */
	public String getDefValue(final String defValue) {
		return defValue;
	}

	/**
	 * 得到从ResultSet中获取属性值的方法的名
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 27, 2014 5:54:48 PM
	 * @return 获取属性值的方法的名
	 */
	public abstract String getRsTypeName();

	/**
	 * 是否需要进行转换工作
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月23日 下午7:18:20
	 * @return true，需要；<br />
	 *         false，不需要；<br />
	 */
	public boolean isNeedParse() {
		return false;
	}

	/**
	 * 得到目标类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 12, 2014 7:43:02 PM
	 * @param val 目标值
	 * @return 对应的数据属性对象
	 */
	public static DatabaseFieldTypeEnum getType(final String val) {
		for (final DatabaseFieldTypeEnum e : DatabaseFieldTypeEnum.values()) {
			if (e.val.equalsIgnoreCase(val)) {
				return e;
			}
		}
		throw new ConfigurationException("Error Database Field type[" + val + "]");
	}
}
