/**
 * @author Weijie Xu
 * @dateTime Aug 13, 2014 3:03:23 PM
 */
package com.tfzzh.tools.data.tools;

import com.tfzzh.exception.ConfigurationException;

/**
 * 字符编码类型
 * 
 * @author Weijie Xu
 * @dateTime Aug 13, 2014 3:03:23 PM
 */
public enum CharacterEncodingTypeEnum {

	/**
	 * 正常字符
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:04:32 PM
	 */
	Latin1 {

		@Override
		protected boolean isBelongTo(String typeName) {
			typeName = "|" + typeName.toLowerCase() + "|";
			return "|latin1|".indexOf(typeName) >= 0;
		}

		@Override
		public int getCharacterBase() {
			return 1;
		}
	},
	/**
	 * 常用完整字符集
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:04:32 PM
	 */
	Uft8 {

		@Override
		protected boolean isBelongTo(String typeName) {
			typeName = "|" + typeName.toLowerCase() + "|";
			return "|utf-8|utf8|".indexOf(typeName) >= 0;
		}

		@Override
		public int getCharacterBase() {
			return 3;
		}
	},
	/**
	 * 针对一般中文字符集
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:04:33 PM
	 */
	Gbk {

		@Override
		protected boolean isBelongTo(String typeName) {
			typeName = "|" + typeName.toLowerCase() + "|";
			return "|gbk|gb2312|".indexOf(typeName) >= 0;
		}

		@Override
		public int getCharacterBase() {
			return 2;
		}
	};

	/**
	 * 是否属于当前类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:11:35 PM
	 * @param typeName 类型名
	 * @return true，属于；<br />
	 *         false，不属于；<br />
	 */
	protected abstract boolean isBelongTo(String typeName);

	/**
	 * 得到相关的字符基数
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:18:51 PM
	 * @return 字符基数
	 */
	public abstract int getCharacterBase();

	/**
	 * 得到字符编码类型对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:08:58 PM
	 * @param typeName 类型名
	 * @return 字符编码类型对象
	 */
	public static CharacterEncodingTypeEnum getType(final String typeName) {
		for (final CharacterEncodingTypeEnum e : CharacterEncodingTypeEnum.values()) {
			if (e.isBelongTo(typeName)) {
				return e;
			}
		}
		throw new ConfigurationException("Error Character Encoding:" + typeName);
	}
}
