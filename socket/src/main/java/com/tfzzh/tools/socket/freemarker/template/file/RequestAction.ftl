/**
 * @author ${author}
 */
package ${projectPath}${bean.scopePackageName}.action;

import com.tfzzh.core.view.socket.RequestSession;
import com.tfzzh.core.view.socket.action.RequestAction;
import com.tfzzh.core.view.socket.annotation.ActionOperation;
import com.tfzzh.core.view.socket.annotation.ActionSpace;
import com.tfzzh.core.view.socket.annotation.ProxyActionOperation;
<#if bean.messages?size != 0>
	<#list bean.messages as msg>
import ${projectPath}${msg.scopePackageName}.action.bean.${msg.proName}${msgSuffix};
	</#list>
</#if>

/**
 * ${bean.desc}
 * 
 * @author ${author}
 */
@ActionSpace("${bean.name}Action")
public class ${bean.proName}Action implements RequestAction {
<#assign con="|">
<#list bean.linkedList as link>
	<#assign idd="|"+link.ids+"|">
	<#if con?index_of(idd) == -1>
		<#assign con=con+link.ids+"|">

	/**
	 * 来自“${bean.from.desc}”的${link.desc}
	 * 
	 * @author ${author}
	 * @param session 请求的会话信息
	<#if link.msg??>
	 * @param msg 请求的消息内容
	</#if>
	 */
	@<#if link.proxy>ProxyActionOperation<#else>ActionOperation</#if>(id = <#if !link.proxy>${link.ids}<#else>{ ${link.ids} }</#if><#if link.msg??>, msgCode = <#if link.clientMsg??>${link.clientMsg.id?c}<#else>${link.msg.id?c}</#if></#if><#if link.keep>, isKeep = true</#if>)
	public void ${link.methodName}(final RequestSession session<#if link.msg??>, final <#if link.clientMsg??>${link.clientMsg.proName}${msgSuffix}<#else>${link.msg.proName}${msgSuffix}</#if> msg</#if>) {
		// TODO 请替换相关逻辑
	}
	</#if>
</#list>
}
