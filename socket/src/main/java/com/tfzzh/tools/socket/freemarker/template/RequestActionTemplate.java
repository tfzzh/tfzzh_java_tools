/**
 * @author Weijie Xu
 * @dateTime 2014-3-27 下午8:46:40
 */
package com.tfzzh.tools.socket.freemarker.template;

import com.tfzzh.tools.socket.bean.iface.RequestTool;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * @author Weijie Xu
 * @dateTime 2014-3-27 下午8:46:40
 */
public class RequestActionTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 上午9:37:35
	 */
	private RequestTool bean;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:46:47
	 * @param projectPath 项目路径
	 * @param obj 需求的对象
	 */
	public RequestActionTemplate(final String projectPath, final RequestTool obj) {
		super(SocketTemplateConstants.PROJECT_TEMPLATE_PATH + "/../" + projectPath, obj);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午4:58:06
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", SocketTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("bean", this.bean);
		super.params.put("msgSuffix", SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午4:58:08
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.bean = (RequestTool) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:46:40
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return SocketTemplateConstants.PROJECT_PATH_PREFIX + this.bean.getScopePathName() + "/action/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-27 下午8:46:40
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return this.bean.getProName() + "Action.java";
	}

	/**
	 * 是否需要模板
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午3:20:37
	 * @return true，如果已经存在目标文件则生成名称带有tmp的相关文件；<br />
	 *         false，如果已经存在目标文件直接覆盖；<br />
	 */
	@Override
	protected boolean isNeedTemplate() {
		return true;
	}
}
