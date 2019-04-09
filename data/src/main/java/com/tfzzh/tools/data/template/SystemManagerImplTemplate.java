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
public class SystemManagerImplTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 30, 2014 4:50:32 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	public void setParams() {
		super.setParams();
		// 放入数据表列表
		super.params.put("datas", ToolBeanExcelPool.getInstance().getAllData());
		// TODO 此处之后需要考虑内容定义方式
		super.params.put("systemIdentify", "data");
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
	}

	/**
	 * TODO data 为暂时的定名
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:03:29 PM
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
		return srcPath + ConfigDataTemplateConstants.BASE_EXCEL_CONFIG_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/data/control/impl";
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:03:29 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return "SystemDataManagerImpl.java";
	}
}
