/**
 * @author Weijie Xu
 * @dateTime 2014-3-7 下午12:44:36
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * @author Weijie Xu
 * @dateTime 2014-3-7 下午12:44:36
 */
public class LogicStructurePoolTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午12:45:43
	 */
	public LogicStructurePoolTemplate() {
		super(null);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午4:40:56
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("classBeanPrefix", ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX);
		super.params.put("classBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX);
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
		// super.params.put("beans", ToolBeanExcelPool.getInstance().getAllLogic(ConfigDataTemplateConstants.CLASSBEAN_CLASS_SUFFIX));
		super.params.put("beanEnts", ToolBeanExcelPool.getInstance().getAllLogic(ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX));
		super.params.put("logics", ToolBeanExcelPool.getInstance().getAllLogic());
		super.params.put("datas", ToolBeanExcelPool.getInstance().getAllData());
		super.params.put("dataCorrelations", ToolBeanExcelPool.getInstance().getAllDataCorrelation());
		super.params.put("dataLogics", ToolBeanExcelPool.getInstance().getDataLogic());
		super.params.put("transitionLogics", ToolBeanExcelPool.getInstance().getTransitionLogic());
		super.params.put("attributes", ToolBeanExcelPool.getInstance().getAllAttrTypeSturcture());
		super.params.put("linkKeys", ToolBeanExcelPool.getInstance().getObjectLinkKeys());
		// 此为针对 集合列 对象的相关控制
		// super.params.put("implTypes", ToolBeanExcelPool.getInstance().getAllImplType());
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午12:44:36
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/control/tools/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午12:44:36
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return "LogicStructurePool.java";
	}
}
