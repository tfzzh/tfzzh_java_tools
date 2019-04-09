/**
 * @author ${author}
 */
package ${projectPath}.${scopeName}.action.tools;

import com.tfzzh.core.view.socket.RequestSession;
import com.tfzzh.core.view.socket.action.CorrespondenceMapping;
import com.tfzzh.core.view.socket.action.RequestAction;
import com.tfzzh.core.view.socket.bean.BaseMessageBean;
import com.tfzzh.core.view.socket.bean.InputMessageBean;
<#assign con="|">
<#list beans as b>
	<#list b.linkedList as l>
		<#if l.msg??>
			<#if l.clientMsg??>
				<#assign msg=l.clientMsg>
			<#else>
				<#assign msg=l.msg>
			</#if>
			<#assign idd="|"+msg.proName+"|">
			<#if con?index_of(idd) == -1>
				<#assign con=con+msg.proName+"|">
import ${projectPath}${msg.scopePackageName}.action.bean.${msg.proName}${msgSuffix};
			</#if>
		</#if>
	</#list>
</#list>
<#list beans as b>
import ${projectPath}${b.scopePackageName}.action.${b.proName}Action;
</#list>

/**
 * 对应关系映射
 * 
 * @author ${author}
 */
public class ${proName}CorrespondenceMapping implements CorrespondenceMapping {

	/**
	 * 执行目标方法
	 * 
	 * @author ${author}
	 * @param bean 进入的消息
	 * @param session 对应的会话信息
	 */
	public void exec(final InputMessageBean bean, final RequestSession session) {
		final RequestAction ra = bean.getRequestInfo().getRequestAction();
<#list beans as b>
	<#if b_index == 0>
		if (ra instanceof ${b.proName}Action) {
	<#else>
		} else if (ra instanceof ${b.proName}Action) {
	</#if>
			this.exec((${b.proName}Action) ra, bean.getCode(), session, bean.getMsg());
	<#if !b_has_next>
		}
	</#if>
</#list>
	}
<#list beans as b>

	/**
	 * @author ${author}
	 * @param action 行为对象
	 * @param code 请求码
	 * @param session 对应的会话信息
	 * @param msg 请求的参数集合
	 */
	private void exec(final ${b.proName}Action action, final int code, final RequestSession session, final BaseMessageBean msg) {
		switch (code) {
	<#assign con="|">
	<#list b.linkedList as l>
		<#list l.id as li>
			<#assign idd="|"+li?c+"|">
			<#if con?index_of(idd) == -1>
				<#assign con=con+li?c+"|">
		case ${li?c}: // ${l.desc}
			action.${l.methodName}(session<#if l.msg??>, (<#if l.clientMsg??>${l.clientMsg.proName}${msgSuffix}<#else>${l.msg.proName}${msgSuffix}</#if>) msg</#if>);
			return;
			</#if>
		</#list>
	</#list>
		}
	}
</#list>
}
