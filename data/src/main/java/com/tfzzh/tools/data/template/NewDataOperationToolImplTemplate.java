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
public class NewDataOperationToolImplTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午4:40:56
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("datas", ToolBeanExcelPool.getInstance().getAllData());
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午12:44:36
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return this.getOutFilePath(ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH);
	}

	/**
	 * 实际的输出路径
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年11月13日 下午4:51:50
	 * @param srcPath 输出源码包路径
	 * @return 待输出的文件所在路径
	 */
	protected String getOutFilePath(final String srcPath) {
		return srcPath + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/control/tools/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午12:44:36
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return "NewDataOperationToolImpl.java";
	}
}
