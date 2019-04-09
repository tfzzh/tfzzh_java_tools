/**
 * @author Weijie Xu
 * @dateTime Aug 16, 2014 3:54:13 PM
 */
package com.tfzzh.tools.data.bean.tools;

import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;
import com.tfzzh.tools.data.bean.LogicBeanTool.LogicFieldTool;
import com.tfzzh.tools.data.tools.FieldConditionTypeEnum;

/**
 * 逻辑对象用的条件Bean
 * 
 * @author Weijie Xu
 * @dateTime Aug 16, 2014 3:54:13 PM
 */
public class LogicConditionBean {

	/**
	 * 字段条件类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 4:02:48 PM
	 */
	private final FieldConditionTypeEnum conditionType;

	/**
	 * 实际需要用来做判断的字段<br />
	 * 认为是当前对象中字段<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 4:02:47 PM
	 */
	private final LogicFieldTool logicField;

	/**
	 * 实际字段对应的目标值<br />
	 * 一种目标值，直接用在代码生成中的内容结果数据中<br />
	 * 自身附带各种必要符号，如：引号，字节标识符d，f等<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 4:02:48 PM
	 */
	private final String tarValue;

	/**
	 * 实际字段对应的目标数据字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 10:07:50 AM
	 */
	private final DataFieldTool tarDataField;

	/**
	 * 实际字段对应的目标字段<br />
	 * 一般认为是非当前对象中字段<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 9:39:03 AM
	 */
	private final LogicFieldTool tarLogicField;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 4:05:56 PM
	 * @param logicField 实际需要用来做判断的字段
	 * @param tarValue 目标值最终显示内容
	 */
	public LogicConditionBean(final LogicFieldTool logicField, final String tarValue) {
		this.conditionType = FieldConditionTypeEnum.Value;
		this.logicField = logicField;
		this.tarValue = tarValue;
		this.tarDataField = null;
		this.tarLogicField = null;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 10:09:48 AM
	 * @param logicField 实际需要用来做判断的字段
	 * @param tarDataField 实际字段对应的目标数据字段
	 */
	public LogicConditionBean(final LogicFieldTool logicField, final DataFieldTool tarDataField) {
		this.conditionType = FieldConditionTypeEnum.Attribute;
		this.logicField = logicField;
		this.tarValue = null;
		this.tarDataField = tarDataField;
		this.tarLogicField = null;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 4:07:23 PM
	 * @param logicField 实际需要用来做判断的字段
	 * @param tarLogicField 对应的逻辑属性
	 */
	public LogicConditionBean(final LogicFieldTool logicField, final LogicFieldTool tarLogicField) {
		this.conditionType = FieldConditionTypeEnum.Attribute;
		this.logicField = logicField;
		this.tarValue = null;
		this.tarDataField = null;
		this.tarLogicField = tarLogicField;
	}

	/**
	 * 得到字段条件类型
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 16, 2014 4:09:56 PM
	 * @return the conditionType
	 */
	public FieldConditionTypeEnum getConditionType() {
		return this.conditionType;
	}

	/**
	 * 得到实际需要用来做判断的字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 9:45:44 AM
	 * @return the logicField
	 */
	public LogicFieldTool getLogicField() {
		return this.logicField;
	}

	/**
	 * 得到实际字段对应的目标值
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 9:45:44 AM
	 * @return the tarValue
	 */
	public String getTarValue() {
		return this.tarValue;
	}

	/**
	 * 得到实际字段对应的目标数据字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 10:09:01 AM
	 * @return the tarDataField
	 */
	public DataFieldTool getTarDataField() {
		return this.tarDataField;
	}

	/**
	 * 得到实际字段对应的目标字段
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 18, 2014 9:45:44 AM
	 * @return the tarLogicField
	 */
	public LogicFieldTool getTarLogicField() {
		return this.tarLogicField;
	}
}
