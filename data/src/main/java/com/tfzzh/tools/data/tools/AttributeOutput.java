/**
 * @author Weijie Xu
 * @dateTime 2014年5月16日 下午3:47:32
 */
package com.tfzzh.tools.data.tools;

/**
 * 输出属性
 * 
 * @author Weijie Xu
 * @dateTime 2014年5月16日 下午3:47:32
 */
public interface AttributeOutput {

	/**
	 * 得到属性类型名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月14日 下午7:20:27
	 * @return 属性类型名称
	 */
	String getAttributeEnumName();

	/**
	 * 得到属性取值对象名称
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月16日 下午3:01:44
	 * @return 属性取值对象
	 */
	String getAttributeValueName();

	/**
	 * 得到属性类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年5月19日 下午6:20:17
	 * @return 属性类型
	 */
	FieldTypeEnum getAttrbuteType();
}
