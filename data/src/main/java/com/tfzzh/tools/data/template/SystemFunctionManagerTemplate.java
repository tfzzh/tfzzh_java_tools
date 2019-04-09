/**
 * @author XuWeijie
 * @dateTime May 3, 2010 1:00:40 PM
 */
package com.tfzzh.tools.data.template;

import java.util.List;

import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 系统数据BEAN模版
 * 
 * @author XuWeijie
 * @dateTime May 3, 2010 1:00:40 PM
 */
public class SystemFunctionManagerTemplate extends BaseTemplate {

	/**
	 * 功能数据实例列表
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月14日_上午10:25:12
	 */
	private List<DataBeanTool> data;

	/**
	 * 功能名
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月14日_上午10:39:19
	 */
	private String functionName;

	/**
	 * @author 许纬杰
	 * @datetime 2016年4月14日_上午10:26:02
	 * @param ldb 功能数据实例列表
	 */
	public SystemFunctionManagerTemplate(final List<DataBeanTool> ldb) {
		super(ldb);
	}

	/**
	 * 设置全局用参数
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月14日_上午10:25:43
	 * @param obj 参数对象
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setOverAllParam(final Object obj) {
		this.data = (List<DataBeanTool>) obj;
		final String fn = this.data.get(0).getFunctionName();
		this.functionName = fn.substring(0, 1).toUpperCase() + fn.substring(1);
	}

	/**
	 * @author XuWeijie
	 * @dateTime Aug 10, 2010 1:44:18 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setParams()
	 */
	@Override
	public void setParams() {
		super.setParams();
		// 放入数据表列表
		super.params.put("datas", this.data);
		super.params.put("fn", this.functionName);
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
		return srcPath + ConfigDataTemplateConstants.BASE_EXCEL_CONFIG_PATH + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/data/control";
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:03:29 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.functionName + "DataManager.java";
	}
}
