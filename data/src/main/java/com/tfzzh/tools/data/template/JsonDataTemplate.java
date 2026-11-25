/**
 * @author XuWeijie
 * @dateTime May 9, 2010 6:55:19 PM
 */
package com.tfzzh.tools.data.template;

import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

/**
 * 系统数据DAO实现类模版
 * 
 * @author XuWeijie
 * @dateTime May 9, 2010 6:55:19 PM
 */
public class JsonDataTemplate extends BaseTableTemplate {

	/**
	 * @author XuWeijie
	 * @dateTime May 9, 2010 6:55:48 PM
	 * @param bean 所需表消息
	 */
	public JsonDataTemplate(final DataBeanTool bean) {
		super(bean);
	}

	/**
	 * 设置参数
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 14, 2014 6:58:28 PM
	 * @see com.tfzzh.tools.data.template.BaseTableTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("dataCode", ConfigDataTemplateConstants.DATA_CODE);
		super.params.put("entityBeanSuffix", ConfigDataTemplateConstants.CLASSBEAN_ENTITY_SUFFIX);
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 9, 2010 6:55:20 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return super.data.getName() + "_data.json";
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 9, 2010 6:55:20 PM
	 * @see com.tfzzh.tools.data.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		String basePath = ConfigDataTemplateConstants.SOURCE_BUILD_DATA_SRC_PATH;
		if ((basePath == null) || basePath.isEmpty()) {
			// 默认：由Excel源码路径的"/java/"替换为"/resources/"，与运行期classpath下"json_data/"读取位置对齐
			basePath = ConfigDataTemplateConstants.SOURCE_BUILD_EXCEL_SRC_PATH.replace("/java/", "/resources/");
		}
		return basePath + "json_data";
	}
}
