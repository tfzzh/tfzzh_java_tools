/**
 * @author Weijie Xu
 * @dateTime 2014年6月30日 下午8:00:51
 */
package com.tfzzh.tools.socket.freemarker.template;

import com.tfzzh.tools.socket.bean.iface.OperationTool;
import com.tfzzh.tools.socket.tools.ScopeTool;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;

/**
 * @author Weijie Xu
 * @dateTime 2014年6月30日 下午8:00:51
 */
public class OperationTemplate extends BaseTemplate {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 */
	private OperationTool bean;

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @param projectPath 项目路径
	 * @param obj 需求的对象
	 */
	public OperationTemplate(final String projectPath, final OperationTool obj) {
		super(SocketTemplateConstants.PROJECT_TEMPLATE_PATH + "/../" + projectPath, obj);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setParams()
	 */
	@Override
	protected void setParams() {
		super.setParams();
		super.params.put("projectPath", SocketTemplateConstants.PROJECT_PACKAGE_PREFIX);
		super.params.put("bean", this.bean);
		// TODO 当前这个方式并不合理，之后调整——xuweijie
		super.params.put("localScope", ScopeTool.getInstance().getScopeType("l"));
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#setOverAllParam(java.lang.Object)
	 */
	@Override
	protected void setOverAllParam(final Object obj) {
		this.bean = (OperationTool) obj;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFilePath()
	 */
	@Override
	protected String getOutFilePath() {
		return SocketTemplateConstants.PROJECT_PATH_PREFIX + this.bean.getScopePathName() + "/operation/";
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @see com.tfzzh.tools.socket.freemarker.template.BaseTemplate#getOutFileName()
	 */
	@Override
	protected String getOutFileName() {
		return "To" + this.bean.getProName() + "Operation.java";
	}

	/**
	 * 是否需要模板
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午8:00:51
	 * @return true，如果已经存在目标文件则生成名称带有tmp的相关文件；<br />
	 *         false，如果已经存在目标文件直接覆盖；<br />
	 */
	@Override
	protected boolean isNeedTemplate() {
		return true;
	}
}
