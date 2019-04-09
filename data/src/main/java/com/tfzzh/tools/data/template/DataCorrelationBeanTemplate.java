/**
 * @author Weijie Xu
 * @dateTime Aug 25, 2014 12:45:15 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataCorrelationBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 系统数据BEAN模版
 * 
 * @author Weijie Xu
 * @dateTime Aug 25, 2014 12:45:15 PM
 */
public class DataCorrelationBeanTemplate extends BaseTemplate {

	/**
	 * 数据关联关系对象
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:47:18 PM
	 */
	private DataCorrelationBeanTool data;

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:45:15 PM
	 * @param bean 所需表消息
	 */
	public DataCorrelationBeanTemplate(final DataCorrelationBeanTool bean) {
		super(bean);
	}

	/**
	 * 设置参数
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:45:15 PM
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", ConfigDataTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("data", this.data);
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:45:15 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.data = (DataCorrelationBeanTool) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:45:15 PM
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
		return srcPath + "../" + this.data.getSrcPath() + "/" + ConfigDataTemplateConstants.PROJECT_PATH_PREFIX + "/" + this.data.getFunctionName() + "/model/data";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 12:45:15 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.data.getProName() + "CorrelationBean.java";
	}
}
