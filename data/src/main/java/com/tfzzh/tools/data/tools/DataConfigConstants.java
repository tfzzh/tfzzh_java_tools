/**
 * @author Weijie Xu
 * @dateTime Aug 13, 2014 3:00:14 PM
 */
package com.tfzzh.tools.data.tools;

import com.tfzzh.tools.Constants;

/**
 * 数据配置常量池
 * 
 * @author Weijie Xu
 * @dateTime Aug 13, 2014 3:00:14 PM
 */
public interface DataConfigConstants {

	/**
	 * 数据库编码
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:23:30 PM
	 */
	String DATABASE_CODE = Constants.SYSTEM_CODE;

	/**
	 * 索引最大总长度限制
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:00:51 PM
	 */
	int INDEX_MAX_LENGTH = 1000;

	/**
	 * 字符编码类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 3:30:34 PM
	 */
	CharacterEncodingTypeEnum CHAR_ENCODEING = CharacterEncodingTypeEnum.getType(DataConfigConstants.DATABASE_CODE);
}
