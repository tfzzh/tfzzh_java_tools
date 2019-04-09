/**
 * @author Weijie Xu
 * @dateTime Sep 2, 2014 12:53:56 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * @author Weijie Xu
 * @dateTime Sep 2, 2014 12:53:56 PM
 */
public class DataSetTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 12:53:56 PM
	 */
	public DataSetTemplate() {
		super(null);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 12:53:56 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("classBeanPrefix", ConfigDataTemplateConstants.CLASSBEAN_PACKAGE_PREFIX);
		super.params.put("datas", ToolBeanExcelPool.getInstance().getAllData());
		super.params.put("dataCorrelations", ToolBeanExcelPool.getInstance().getAllDataCorrelation());
		// super.params.put("datas", ToolBeanExcelPool.getInstance().getScopeNormalData());
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 12:53:56 PM
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
		return srcPath + ConfigDataTemplateConstants.BASE_EXCEL_CONFIG_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/control/tools";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Sep 2, 2014 12:53:56 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return "DataSet.java";
	}
}
