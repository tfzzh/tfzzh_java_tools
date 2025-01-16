/**
 * @author Weijie Xu
 * @dateTime 2014-3-6 下午5:18:00
 */
package com.tfzzh.tools.data.tools;

import java.io.InputStream;
import java.util.Date;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.exception.NotAvailableOperationModeException;
import com.tfzzh.tools.StringTools;

/**
 * 字段类型
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-6 下午5:18:00
 */
public enum FieldTypeEnum implements AttributeOutput {

	/**
	 * 字符串类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:34
	 */
	OjString("String") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return String.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return String.class;
		}

		@Override
		public String getObjectTypeName() {
			return String.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			if (null == obj) {
				return "null";
			} else {
				return "\"" + obj + "\"";
			}
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return val;
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "StringAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 2;
		}

		@Override
		public String toSocketTypeName() {
			return "String";
		}

		@Override
		public String getStringToValueCon() {
			return "OjString";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "\"" + val.replaceAll("\"", "\\\\\"") + "\"";
		}
	},
	/**
	 * 字符缓冲器类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月16日 下午2:16:10
	 */
	OjStringBuffer("StringBuffer") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return StringBuffer.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return StringBuffer.class;
		}

		@Override
		public String getObjectTypeName() {
			return StringBuffer.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			if (null == obj) {
				return "null";
			} else {
				return "new StringBuffer(\"" + obj + "\")";
			}
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return val;
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "StringBufferAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String toSocketTypeName() {
			return "String";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "new StringBuilder().append(\"" + val.replaceAll("\"", "\\\\\"") + "\")";
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:36
	 */
	BsInt("int") {

		@Override
		public String getDefaultValue() {
			return "0";
		}

		@Override
		public String getTypeName() {
			return int.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Integer.class;
		}

		@Override
		public String getObjectTypeName() {
			return Integer.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return (s.length() == 0 ? "0" : s);
		}

		@Override
		public Object stringToValue(final String val) {
			return Integer.parseInt(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "IntegerAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String getIndexKeyHashCodeContent(final String name) {
			return "this." + name;
		}

		/**
		 * 是否基本类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月2日 下午3:17:38
		 * @return 是的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBasicData()
		 */
		@Override
		public boolean isBasicData() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 1;
		}

		@Override
		public String toSocketTypeName() {
			return "Int";
		}

		@Override
		public String getStringToValueCon() {
			return "BsInt";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "(int) " + (val.length() == 0 ? "0" : val);
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:36
	 */
	OjInteger("Integer") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Integer.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Integer.class;
		}

		@Override
		public String getObjectTypeName() {
			return Integer.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return "Integer.parseInt(\"" + (s.length() == 0 ? "0" : s) + "\")";
		}

		@Override
		public Object stringToValue(final String val) {
			return Integer.parseInt(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "IntegerObjAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 1;
		}

		@Override
		public String toSocketTypeName() {
			return "Int";
		}

		@Override
		public String getStringToValueCon() {
			return "BsInt";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "Double.valueOf(\"" + (val.length() == 0 ? "0" : val) + "\").intValue()";
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:38
	 */
	BsLong("long") {

		@Override
		public String getDefaultValue() {
			return "0l";
		}

		@Override
		public String getTypeName() {
			return long.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Long.class;
		}

		@Override
		public String getObjectTypeName() {
			return Long.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return (s.length() == 0 ? "0" : s) + "l";
		}

		@Override
		public Object stringToValue(final String val) {
			return Long.parseLong(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "LongAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String getIndexKeyHashCodeContent(final String name) {
			return "(int) this." + name;
		}

		/**
		 * 是否基本类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月2日 下午3:17:38
		 * @return 是的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBasicData()
		 */
		@Override
		public boolean isBasicData() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 4;
		}

		@Override
		public String toSocketTypeName() {
			return "Long";
		}

		@Override
		public String getStringToValueCon() {
			return "BsLong";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "(long) " + (val.length() == 0 ? "0" : val);
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:38
	 */
	OjLong("Long") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Long.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Long.class;
		}

		@Override
		public String getObjectTypeName() {
			return Long.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return "Long.parseLong(\"" + (s.length() == 0 ? "0" : s) + "\")";
		}

		@Override
		public Object stringToValue(final String val) {
			return Long.parseLong(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "LongObjAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 4;
		}

		@Override
		public String toSocketTypeName() {
			return "Long";
		}

		@Override
		public String getStringToValueCon() {
			return "BsLong";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "Double.valueOf(\"" + (val.length() == 0 ? "0" : val) + "\").longValue()";
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:39
	 */
	BsShort("short") {

		@Override
		public String getDefaultValue() {
			return "(short) 0";
		}

		@Override
		public String getTypeName() {
			return short.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Short.class;
		}

		@Override
		public String getObjectTypeName() {
			return Short.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return "(short)" + (s.length() == 0 ? "0" : s);
		}

		@Override
		public Object stringToValue(final String val) {
			return Short.parseShort(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "ShortAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String getIndexKeyHashCodeContent(final String name) {
			return "this." + name;
		}

		/**
		 * 是否基本类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月2日 下午3:17:38
		 * @return 是的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBasicData()
		 */
		@Override
		public boolean isBasicData() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			return "Short";
		}

		@Override
		public String getStringToValueCon() {
			return "BsShort";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "(short) " + (val.length() == 0 ? "0" : val);
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:39
	 */
	OjShort("Short") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Short.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Short.class;
		}

		@Override
		public String getObjectTypeName() {
			return Short.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return "Short.parseShort(\"" + (s.length() == 0 ? "0" : s) + "\")";
		}

		@Override
		public Object stringToValue(final String val) {
			return Short.parseShort(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "ShortObjAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String toSocketTypeName() {
			return "Short";
		}

		@Override
		public String getStringToValueCon() {
			return "BsShort";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "Double.valueOf(\"" + (val.length() == 0 ? "0" : val) + "\").shortValue()";
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:41
	 */
	BsFloat("float") {

		@Override
		public String getDefaultValue() {
			return "0f";
		}

		@Override
		public String getTypeName() {
			return float.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Float.class;
		}

		@Override
		public String getObjectTypeName() {
			return Float.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return (s.length() == 0 ? "0" : s) + "f";
		}

		@Override
		public Object stringToValue(final String val) {
			return Float.parseFloat(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "FloatAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String getIndexKeyHashCodeContent(final String name) {
			return "(int) this." + name;
		}

		/**
		 * 是否基本类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月2日 下午3:17:38
		 * @return 是的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBasicData()
		 */
		@Override
		public boolean isBasicData() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			return "Float";
		}

		@Override
		public String getStringToValueCon() {
			return "BsFloat";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "(float) " + (val.length() == 0 ? "0" : val);
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:41
	 */
	OjFloat("Float") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Float.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Float.class;
		}

		@Override
		public String getObjectTypeName() {
			return Float.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return "Float.parseFloat(\"" + (s.length() == 0 ? "0" : s) + "\")";
		}

		@Override
		public Object stringToValue(final String val) {
			return Float.parseFloat(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "FloatObjAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String toSocketTypeName() {
			return "Float";
		}

		@Override
		public String getStringToValueCon() {
			return "BsFloat";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "Double.valueOf(\"" + (val.length() == 0 ? "0" : val) + "\").floatValue()";
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:42
	 */
	BsDouble("double") {

		@Override
		public String getDefaultValue() {
			return "0d";
		}

		@Override
		public String getTypeName() {
			return double.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Double.class;
		}

		@Override
		public String getObjectTypeName() {
			return Double.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return (s.length() == 0 ? "0" : s) + "d";
		}

		@Override
		public Object stringToValue(final String val) {
			return Double.parseDouble(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "DoubleAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String getIndexKeyHashCodeContent(final String name) {
			return "(int) this." + name;
		}

		/**
		 * 是否基本类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月2日 下午3:17:38
		 * @return 是的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBasicData()
		 */
		@Override
		public boolean isBasicData() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 3;
		}

		@Override
		public String toSocketTypeName() {
			return "Double";
		}

		@Override
		public String getStringToValueCon() {
			return "BsDouble";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return (val.length() == 0 ? "0" : val) + "d";
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:42
	 */
	OjDouble("Double") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Double.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Double.class;
		}

		@Override
		public String getObjectTypeName() {
			return Double.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			final String s = String.valueOf(obj);
			return "Double.parseDouble(\"" + ((s.length() == 0) ? "0" : s) + "\")";
		}

		@Override
		public Object stringToValue(final String val) {
			return Double.parseDouble(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "DoubleObjAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 3;
		}

		@Override
		public String toSocketTypeName() {
			return "Double";
		}

		@Override
		public String getStringToValueCon() {
			return "BsDouble";
		}

		/**
		 * 需要增量字段
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月1日 上午10:59:30
		 * @return 这是需要的
		 */
		@Override
		public boolean isNeedAddField() {
			return true;
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "Double.valueOf(\"" + (val.length() == 0 ? "0" : val) + "\")";
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:43
	 */
	BsBoolean("boolean") {

		@Override
		public String getDefaultValue() {
			return "false";
		}

		@Override
		public String getTypeName() {
			return boolean.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Boolean.class;
		}

		@Override
		public String getObjectTypeName() {
			return Boolean.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			return String.valueOf(obj);
		}

		@Override
		public Object stringToValue(final String val) {
			return Boolean.parseBoolean(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "BooleanAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String getIndexKeyHashCodeContent(final String name) {
			return "(this." + name + " ? 0 : 1)";
		}

		/**
		 * 是否基本类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月2日 下午3:17:38
		 * @return 是的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBasicData()
		 */
		@Override
		public boolean isBasicData() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 5;
		}

		@Override
		public String toSocketTypeName() {
			return "Boolean";
		}

		@Override
		public String getStringToValueCon() {
			return "BsBoolean";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return val;
		}
	},
	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:29:43
	 */
	OjBoolean("Boolean") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Boolean.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Boolean.class;
		}

		@Override
		public String getObjectTypeName() {
			return Boolean.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			return "Boolean.parseBoolean(\"" + String.valueOf(obj) + "\")";
		}

		@Override
		public Object stringToValue(final String val) {
			return Boolean.parseBoolean(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "BooleanObjAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 5;
		}

		@Override
		public String toSocketTypeName() {
			return "Boolean";
		}

		@Override
		public String getStringToValueCon() {
			return "BsBoolean";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "Boolean.parseBoolean(\"" + val + "\")";
		}
	},
	/**
	 * Character
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:27:43
	 */
	BsChar("char") {

		@Override
		public String getDefaultValue() {
			return "(char) 0";
		}

		@Override
		public String getTypeName() {
			return char.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Character.class;
		}

		@Override
		public String getObjectTypeName() {
			return Character.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			return "'" + String.valueOf(obj) + "'";
		}

		@Override
		public Object stringToValue(final String val) {
			return (char) Integer.parseInt(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "CharAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String getIndexKeyHashCodeContent(final String name) {
			return "(int) this." + name;
		}

		/**
		 * 是否基本类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月2日 下午3:17:38
		 * @return 是的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBasicData()
		 */
		@Override
		public boolean isBasicData() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			return "Char";
		}

		@Override
		public String getStringToValueCon() {
			return "BsChar";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "'" + val + "'";
		}
	},
	/**
	 * Character
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-6 下午5:27:43
	 */
	OjCharacter("Character") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Character.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Character.class;
		}

		@Override
		public String getObjectTypeName() {
			return Character.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			return "'" + String.valueOf(obj) + "'";
		}

		@Override
		public Object stringToValue(final String val) {
			return (char) Integer.parseInt(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "CharObjAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String toSocketTypeName() {
			return "Char";
		}

		@Override
		public String getStringToValueCon() {
			return "BsChar";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "'" + val + "'";
		}
	},
	/**
	 * Byte
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月15日 下午7:09:39
	 */
	BsByte("byte") {

		@Override
		public String getDefaultValue() {
			return "(byte) 0";
		}

		@Override
		public String getTypeName() {
			return byte.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Byte.class;
		}

		@Override
		public String getObjectTypeName() {
			return Byte.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			return "'" + String.valueOf(obj) + "'";
		}

		@Override
		public Object stringToValue(final String val) {
			return (byte) Integer.parseInt(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "ByteAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String getIndexKeyHashCodeContent(final String name) {
			return "(int) this." + name;
		}

		/**
		 * 是否基本类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月2日 下午3:17:38
		 * @return 是的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBasicData()
		 */
		@Override
		public boolean isBasicData() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			return "Byte";
		}

		@Override
		public String getStringToValueCon() {
			return "BsByte";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "(byte) " + val;
		}
	},
	/**
	 * Byte
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月15日 下午7:09:39
	 */
	OjByte("Byte") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Byte.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Byte.class;
		}

		@Override
		public String getObjectTypeName() {
			return Byte.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			return "Byte.parseByte(\"" + String.valueOf(obj) + "\")";
		}

		@Override
		public Object stringToValue(final String val) {
			return (byte) Integer.parseInt(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "ByteObjAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String toSocketTypeName() {
			return "Byte";
		}

		@Override
		public String getStringToValueCon() {
			return "BsByte";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "Byte.parseByte(\"" + val + "\")";
		}
	},
	/**
	 * Byte的数组
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 10:25:24 AM
	 */
	ArByte("byte[]") {

		@Override
		public String getDefaultValue() {
			return null;
		}

		@Override
		public String getTypeName() {
			return "byte[]";
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Byte[].class;
		}

		@Override
		public String getObjectTypeName() {
			return "byte[]";
		}

		@Override
		public String showFormat(final Object obj) {
			return "new byte[] {" + obj + "}";
		}

		@Override
		public Object stringToValue(final String val) {
			final String[] ds = StringTools.split(val);
			final byte[] bs = new byte[ds.length];
			for (int i = ds.length; i >= 0; i--) {
				bs[i] = (byte) Integer.parseInt(ds[i]);
			}
			return bs;
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "ByteArrAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[ArByte]..");
		}

		@Override
		public String getStringToValueCon() {
			return "OjByteArray";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "new byte[]{ " + val + "}";
		}
	},
	/**
	 * 时间类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:43:24 PM
	 */
	OjDate("Date") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return Date.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return Date.class;
		}

		@Override
		public String getObjectTypeName() {
			return Date.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			if (null == obj) {
				return "null";
			} else {
				return "new Date(\"" + obj + "\")";
			}
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@SuppressWarnings("deprecation")
		@Override
		public Object stringToValue(final String val) {
			// TODO 暂且用这个方式，之后考虑使用更适合的方法
			return new Date(val);
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "DateAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public String toSocketTypeName() {
			return "Long";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return val + "l";
		}
	},
	/**
	 * 时间类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:43:24 PM
	 */
	OjDateDay("DateDay") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return String.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return String.class;
		}

		@Override
		public String getObjectTypeName() {
			return String.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			if (null == obj) {
				return "null";
			} else {
				return (String) obj;
			}
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return val;
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "StringAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return FieldTypeEnum.OjString;
		}

		@Override
		public String toSocketTypeName() {
			return "String";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "\"" + val + "\"";
		}
	},
	/**
	 * 时间类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 7:43:24 PM
	 */
	OjDateTime("DateTime") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return String.class.getSimpleName();
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return String.class;
		}

		@Override
		public String getObjectTypeName() {
			return String.class.getSimpleName();
		}

		@Override
		public String showFormat(final Object obj) {
			if (null == obj) {
				return "null";
			} else {
				return (String) obj;
			}
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return val;
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "StringAttribute";
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return FieldTypeEnum.OjString;
		}

		@Override
		public String toSocketTypeName() {
			return "String";
		}

		@Override
		public String stringToTypeShow(final String val) {
			return "\"" + val + "\"";
		}
	},
	/**
	 * 对象Bean，结构对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月14日 下午6:53:27
	 */
	PrBean("Bean") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "Bean";
		}

		@Override
		public Class<?> getObjectTypeClass() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			try {
				return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
			} catch (final Exception e) {
				return null;
			}
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "StructureBeanAttribute";
		}

		@Override
		public String showFormat(final Object obj) {
			return null;
		}

		@Override
		public Object stringToValue(final String val) {
			return null;
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 4:19:23 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBean()
		 */
		@Override
		public boolean isBean() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[Bean]..");
		}
	},
	/**
	 * 接口类型针对输入InputString的实现<br />
	 * 此接口目标仅用于“数据对象”中对应表Blob的字段上，在“逻辑对象”中会使用对象的对象直接获得目标数据<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 9, 2014 11:24:02 AM
	 */
	IfInputStream("input") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "byte[]";
		}

		@Override
		public Class<?> getObjectTypeClass() {
			return InputStream.class;
		}

		@Override
		public String getObjectTypeName() {
			return "InputStream";
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			return "InputStream";
		}

		@Override
		public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
			return "InputStream";
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "InputAttribute";
		}

		@Override
		public String showFormat(final Object obj) {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public boolean isList() {
			return false;
		}

		// @Override
		// public boolean isBean() {
		// return true;
		// }
		/**
		 * @author Weijie Xu
		 * @dateTime Sep 10, 2014 2:37:41 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#getSqlType()
		 */
		@Override
		public int getSqlType() {
			return 6;
		}

		@Override
		public String toSocketTypeName() {
			return "arraybs|byte";
		}

		/**
		 * 去到Socket的Param时，是否为对象类模型，当前仅针对“input”模式
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年11月21日 下午4:28:32
		 * @return true，此处一定是
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#toSocketTypeIsObj()
		 */
		@Override
		public boolean toSocketTypeIsObj() {
			return true;
		}
	},
	/**
	 * 针对nosql数据库的属性对象数据
	 */
	OjObject("object") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "Object";
		}

		@Override
		public String getBaseName(final String mainName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public Class<?> getObjectTypeClass() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "Object";
		}

		@Override
		public String showFormat(final Object obj) {
			return null;
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return null;
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 4:19:23 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBean()
		 */
		@Override
		public boolean isBean() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[object]..");
		}
	},
	/**
	 * 针对nosql数据库的属性列表数据
	 */
	OjArrays("arrays") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "List";
		}

		@Override
		public String getBaseName(final String mainName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public Class<?> getObjectTypeClass() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			return "List<" + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">";
		}

		@Override
		public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
			return ObjectImplTypeTool.getListImpl(type) + "<" + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">";
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "Array";
		}

		@Override
		public String showFormat(final Object obj) {
			return null;
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return null;
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public boolean isList() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[List]..");
		}
	},
	/**
	 * 接口类型各种Map的实现
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月14日 下午6:51:36
	 */
	IfMap("Map") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "Map";
		}

		@Override
		public String getBaseName(final String mainName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public Class<?> getObjectTypeClass() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			return "Map<" + keyName + ", " + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">";
		}

		@Override
		public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
			return ObjectImplTypeTool.getMapImpl(type) + "<" + keyName + ", " + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">";
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "MapAttribute";
		}

		@Override
		public String showFormat(final Object obj) {
			return null;
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return null;
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 1:30:09 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isList()
		 */
		@Override
		public boolean isList() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2015年1月19日 下午6:54:31
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isMap()
		 */
		@Override
		public boolean isMap() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 4:19:23 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBean()
		 */
		@Override
		public boolean isBean() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[Map]..");
		}
	},
	/**
	 * 接口类型各种Map的实现
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月14日 下午6:51:36
	 */
	AllMap("AllMap") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "Map<Object, Object>";
		}

		@Override
		public String getBaseName(final String mainName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public Class<?> getObjectTypeClass() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			return "Map<Object, Object>";
		}

		@Override
		public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
			return ObjectImplTypeTool.getMapImpl(type) + "<Object, Object>";
		}

		@Override
		public String getAttributeEnumName() {
			return AllMap.getName();
		}

		@Override
		public String getAttributeValueName() {
			return "IdleMapAttribute";
		}

		@Override
		public String showFormat(final Object obj) {
			return null;
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return null;
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public boolean isBasicData() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 1:30:09 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isList()
		 */
		@Override
		public boolean isList() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2015年1月19日 下午6:54:31
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isMap()
		 */
		@Override
		public boolean isMap() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 4:19:23 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBean()
		 */
		@Override
		public boolean isBean() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[Map]..");
		}
	},
	/**
	 * 接口类型各种Map的实现
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月14日 下午6:51:36
	 */
	IfMapList("MapList") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "MapList";
		}

		@Override
		public String getBaseName(final String mainName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public Class<?> getObjectTypeClass() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			return "Map<" + keyName + ", List<" + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">>";
		}

		@Override
		public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
			return ObjectImplTypeTool.getMapImpl(type) + "<" + keyName + ", List<" + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">>";
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "MapListAttribute";
		}

		@Override
		public String showFormat(final Object obj) {
			return null;
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return null;
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 1:30:09 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isList()
		 */
		@Override
		public boolean isList() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2015年1月19日 下午6:54:31
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isMap()
		 */
		@Override
		public boolean isMap() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 4:19:23 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBean()
		 */
		@Override
		public boolean isBean() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[MapList]..");
		}
	},
	/**
	 * 接口类型各种List的实现
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月14日 下午6:51:36
	 */
	IfList("List") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "List";
		}

		@Override
		public String getBaseName(final String mainName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public Class<?> getObjectTypeClass() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			return "List<" + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">";
		}

		@Override
		public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
			return ObjectImplTypeTool.getListImpl(type) + "<" + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">";
		}

		@Override
		public String getAttributeEnumName() {
			return this.name();
		}

		@Override
		public String getAttributeValueName() {
			return "ListAttribute";
		}

		@Override
		public String showFormat(final Object obj) {
			return null;
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return null;
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 18, 2014 1:30:09 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isList()
		 */
		@Override
		public boolean isList() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 4:19:23 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBean()
		 */
		@Override
		public boolean isBean() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[List]..");
		}
	},
	/**
	 * 接口类型各种List的实现
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月14日 下午6:51:36
	 */
	AllList("AllList") {

		@Override
		public String getDefaultValue() {
			return "null";
		}

		@Override
		public String getTypeName() {
			return "List<Object>";
		}

		@Override
		public String getBaseName(final String mainName) {
			return mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX;
		}

		@Override
		public Class<?> getObjectTypeClass() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName() {
			throw new NotAvailableOperationModeException("Can't be Calls to this Method...");
		}

		@Override
		public String getObjectTypeName(final String mainName, final String keyName) {
			if (null == mainName) {
				return "List<Object>";
			} else {
				return "List<" + mainName + ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX + ">";
			}
		}

		@Override
		public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
			return ObjectImplTypeTool.getListImpl(type) + "<>";
		}

		@Override
		public String getAttributeEnumName() {
			return AllList.name();
		}

		@Override
		public String getAttributeValueName() {
			return "Object";
		}

		@Override
		public String showFormat(final Object obj) {
			return null;
		}

		/**
		 * 比较时，是否需要使用equals方法进行
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年12月3日 下午6:48:22
		 * @return 这里是需要的
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isNeedEquals()
		 */
		@Override
		public boolean isNeedEquals() {
			return true;
		}

		@Override
		public Object stringToValue(final String val) {
			return null;
		}

		@Override
		public FieldTypeEnum getAttrbuteType() {
			return this;
		}

		@Override
		public boolean isBasicData() {
			return true;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime Aug 28, 2014 4:19:23 PM
		 * @see com.tfzzh.tools.data.tools.FieldTypeEnum#isBean()
		 */
		@Override
		public boolean isBean() {
			return true;
		}

		@Override
		public String toSocketTypeName() {
			throw new ConfigurationException("Cannt Be this... type[List]..");
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
	 * @param name 名字
	 */
	FieldTypeEnum(final String name) {
		this.val = name;
	}

	/**
	 * 得到名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 8:06:22 PM
	 * @return 名称
	 */
	public String getName() {
		return this.name();
	}

	/**
	 * 得到相关参数值
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 19, 2014 8:05:17 PM
	 * @return 参数值
	 */
	public String getValue() {
		return this.val;
	}

	/**
	 * 是否基础类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月2日 下午3:17:10
	 * @return 默认为否
	 */
	public boolean isBasicData() {
		return false;
	}

	/**
	 * 默认的值
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 上午11:08:15
	 * @return 默认的值
	 */
	public abstract String getDefaultValue();

	/**
	 * 得到类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午4:49:10
	 * @return 得到显示用名
	 */
	public abstract String getTypeName();

	/**
	 * 得到基础类型名称
	 * 
	 * @author tfzzh
	 * @dateTime 2016年12月31日 下午8:11:15
	 * @param mainName 主名称
	 * @return 基础类型名称
	 */
	public String getBaseName(final String mainName) {
		return this.getTypeName();
	}

	/**
	 * 得到对象模式的类型对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月14日 下午4:32:28
	 * @return 对象模式类型对象
	 */
	public abstract Class<?> getObjectTypeClass();

	/**
	 * 得到对象模式的类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-8 下午1:35:33
	 * @return 对象模式类型名称
	 */
	public abstract String getObjectTypeName();

	/**
	 * 得到对象模式的类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 8:51:07 PM
	 * @param mainName 主名称
	 * @param keyName 键名称，当前针对Map类型
	 * @return 得到显示用名
	 */
	public String getObjectTypeName(final String mainName, final String keyName) {
		return this.getObjectTypeName();
	}

	/**
	 * 得到对象模型的类型的实现的名称
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 28, 2014 6:12:01 PM
	 * @param type 实现类型ID
	 * @param mainName 主名称
	 * @param keyName 键名称，当前针对Map类型
	 * @return 对象模型的类型的实现的名称
	 */
	public String getObjectTypeImplName(final int type, final String mainName, final String keyName) {
		return this.getObjectTypeName();
	}

	/**
	 * 值的显示格式，用于模板生成
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-11 上午10:10:10
	 * @param obj 目标值
	 * @return 用于输出的格式
	 */
	public abstract String showFormat(Object obj);

	/**
	 * 比较时，是否需要使用equals方法进行
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月3日 下午6:29:59
	 * @return 这里默认是不需要
	 */
	public boolean isNeedEquals() {
		return false;
	}

	/**
	 * String表现值
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-11 下午7:17:29
	 * @param val String值
	 * @return 目标类型的值
	 */
	public abstract Object stringToValue(String val);

	/**
	 * 从String转换为可在代码中用的类型内容
	 * 
	 * @author XuWeijie
	 * @datetime 2015年11月12日_下午2:06:25
	 * @param val String值
	 * @return 转换后的内容
	 */
	public String stringToTypeShow(final String val) {
		throw new ConfigurationException("It's not possible!...");
	}

	/**
	 * 得到将String转为值的模版用内容
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月26日 下午8:39:08
	 * @return 将String转为值的模版用内容
	 */
	public String getStringToValueCon() {
		throw new ConfigurationException("It's not possible!...");
	}

	/**
	 * 去到Socket的Param时所用类型名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月21日 下午4:00:48
	 * @return 对应Socket的Param所用类型名
	 */
	public abstract String toSocketTypeName();

	/**
	 * 去到Socket的Param时，是否为对象类模型，当前仅针对“input”模式
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月21日 下午4:26:33
	 * @return true，此处一定不是
	 */
	public boolean toSocketTypeIsObj() {
		return false;
	}

	/**
	 * 需要增量字段，默认为不需要
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月1日 上午10:59:30
	 * @return 这里都是不需要的
	 */
	public boolean isNeedAddField() {
		return false;
	}

	/**
	 * 得到索引键的hashCode，限定环境，针对索引类——CorrelationIndex
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 27, 2014 3:04:32 PM
	 * @param name 属性的名称
	 * @return 索引键hashCode用显示代码
	 */
	public String getIndexKeyHashCodeContent(final String name) {
		return "this." + name + ".hashCode()";
	}

	/**
	 * 是否列表类型字段<br />
	 * 默认为非列表类型字段<br />
	 * 如需要变更，请重写方法<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 1:28:49 PM
	 * @return true，列表类型字段； false，非列表类型字段；
	 */
	public boolean isList() {
		return false;
	}

	/**
	 * 是否为MAP类型字段<br />
	 * 从属于列表之下<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2015年1月19日 下午6:53:32
	 * @return true，Map类型字段； false，非Map类型字段；
	 */
	public boolean isMap() {
		return false;
	}

	/**
	 * 是否Bean类的对象，包括Bean，Map，List
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 28, 2014 4:17:28 PM
	 * @return true，是Bean类对象；<br />
	 *         false，不是Bean类对象；<br />
	 */
	public boolean isBean() {
		return false;
	}

	/**
	 * 得到数据库用类型，模板中用<br />
	 * 仅针对当前公司项目，其实用处不大<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Sep 10, 2014 2:34:09 PM
	 * @return 数据库用类型
	 */
	public int getSqlType() {
		throw new ConfigurationException("Not Exists Type:" + this.name() + " target Value...");
	}

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
			if (ft.val.equals(type)) {
				return ft;
			}
		}
		throw new ConfigurationException("Error type: " + type);
	}
}
