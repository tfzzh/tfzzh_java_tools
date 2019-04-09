/**
 * @author Weijie Xu
 * @dateTime Aug 23, 2014 5:08:42 PM
 */
package com.tfzzh.tools.data.bean.tools;

import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;
import com.tfzzh.tools.data.tools.FieldConditionTypeEnum;

/**
 * 数据对象用的条件Bean
 * 
 * @author Weijie Xu
 * @dateTime Aug 23, 2014 5:08:42 PM
 */
public class DataConditionBean {

	/**
	 * 条件类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:13:17 PM
	 */
	private final FieldConditionTypeEnum conditionType;

	/**
	 * 作用字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:13:18 PM
	 */
	private DataFieldTool field;

	/**
	 * 目标字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:13:18 PM
	 */
	private final DataFieldTool tarField;

	/**
	 * 目标值
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:13:19 PM
	 */
	private final String[] tarValue;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:36:20 PM
	 * @param field 作用字段
	 * @param tarField 目标字段
	 */
	public DataConditionBean(final DataFieldTool field, final DataFieldTool tarField) {
		this.conditionType = FieldConditionTypeEnum.Attribute;
		this.field = field;
		this.tarField = tarField;
		this.tarValue = null;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:36:20 PM
	 * @param field 作用字段
	 * @param tarValue 目标值
	 */
	public DataConditionBean(final DataFieldTool field, final String[] tarValue) {
		this.conditionType = FieldConditionTypeEnum.Value;
		this.field = field;
		this.tarField = null;
		this.tarValue = tarValue;
	}

	/**
	 * 得到条件类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:37:46 PM
	 * @return the conditionType
	 */
	public FieldConditionTypeEnum getConditionType() {
		return this.conditionType;
	}

	/**
	 * 得到数据属性工具
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:37:46 PM
	 * @return the field
	 */
	public DataFieldTool getField() {
		return this.field;
	}

	/**
	 * 得到目标字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:37:46 PM
	 * @return the tarField
	 */
	public DataFieldTool getTarField() {
		return this.tarField;
	}

	/**
	 * 得到目标值
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 5:37:46 PM
	 * @return the tarValue
	 */
	public String[] getTarValue() {
		return this.tarValue;
	}

	/**
	 * 换位，因为一开始的位置的未知
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 23, 2014 7:34:41 PM
	 */
	public void swop() {
		final DataFieldTool tmp = this.field;
		this.field = this.tarField;
		this.field = tmp;
	}
}
