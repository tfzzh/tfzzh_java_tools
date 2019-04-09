/**
 * @author Weijie Xu
 * @dateTime Aug 30, 2014 4:09:09 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.LogicBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * @author Weijie Xu
 * @dateTime Aug 30, 2014 4:09:09 PM
 */
public class LogicAttributeTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 30, 2014 4:09:09 PM
	 */
	private LogicBeanTool bean;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 30, 2014 4:09:09 PM
	 * @param bean 类对象
	 */
	public LogicAttributeTemplate(final LogicBeanTool bean) {
		super(bean);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 30, 2014 4:09:09 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("data", this.bean);
		super.params.put("classBeanPrefix", ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX);
		super.params.put("classBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX);
		super.params.put("classElementSuffix", ConfigDataTemplateConstants.CLASSBEAN_CLASS_ELEMENT_SUFFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 30, 2014 4:09:09 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.bean = (LogicBeanTool) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 30, 2014 4:09:09 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX.replaceAll("[.]", "/") + "/tools";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 30, 2014 4:09:09 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.bean.getProName() + "LogicAttribute.java";
	}
}
