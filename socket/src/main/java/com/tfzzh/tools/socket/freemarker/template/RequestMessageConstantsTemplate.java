/**
 * @author Weijie Xu
 * @dateTime 2014-3-27 下午8:46:40
 */
package com.tfzzh.tools.socket.freemarker.template;

import com.tfzzh.tools.socket.tools.RequestXMLTool;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * 对应关系映射模板
 * 
 * @author Weijie Xu
 * @dateTime 2014年6月23日 下午4:17:04
 */
public class RequestMessageConstantsTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月23日 下午4:17:04
	 * @param projectPath 项目路径
	 */
	public RequestMessageConstantsTemplate(final String projectPath) {
		super(SocketTemplateConstants.PROJECT_TEMPLATE_PATH + "/../" + projectPath, null);
	}

	/**
	 * @author XuWeijie
	 * @datetime 2015年12月30日_下午2:48:57
	 * @param projectPath 项目路径
	 * @param obj 参数对象
	 */
	protected RequestMessageConstantsTemplate(final String projectPath, final Object obj) {
		super(SocketTemplateConstants.PROJECT_TEMPLATE_PATH + "/../" + projectPath, obj);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月23日 下午4:17:04
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", SocketTemplateConstants.PROJECT_PACKAGE_PREFIX);
		// super.params.put("beans", RequestXMLTool.getInstance().getAllLinkedList("Linked"));
		super.params.put("beans", RequestXMLTool.getInstance().getAllRequestList());
		super.params.put("msgSuffix", SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月23日 下午4:17:04
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return SocketTemplateConstants.PROJECT_PATH_PREFIX + "/action/bean/tools/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月23日 下午4:17:04
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return "RequestMessageConstants.java";
	}
}
