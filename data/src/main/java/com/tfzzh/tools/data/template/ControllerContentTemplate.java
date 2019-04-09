/**
 * @author XuWeijie
 * @dateTime May 3, 2010 1:00:40 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * 系统数据BEAN模版
 * 
 * @author XuWeijie
 * @dateTime May 3, 2010 1:00:40 PM
 */
public class ControllerContentTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:10:00 PM
	 */
	public ControllerContentTemplate() {
		super(null);
	}

	/**
	 * @author XuWeijie
	 * @dateTime Aug 10, 2010 1:44:18 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	public void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		// 放入数据表列表
		super.params.put("datas", ToolBeanExcelPool.getInstance().getAllData());
		// TODO 此处之后需要考虑内容定义方式
		super.params.put("systemIdentify", "data");
		super.params.put("sysManager", "sysDataManager");
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:03:29 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH + ConfigDataTemplateConstants.BASE_EXCEL_CONFIG_PATH;
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:03:29 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return "ControllerNewAuto.xml";
	}
}
