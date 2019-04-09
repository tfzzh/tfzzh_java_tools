/**
 * @author ${author}
 */
package ${projectPath}.action.bean.tools;

import com.tfzzh.core.view.socket.tools.RequestMessageAttribute;
<#assign con="|">
<#list beans as b>
	<#list b.linkedList as l>
		<#if !l.proxy && l.msg??>
			<#assign idd="|"+l.msg.proName+"|">
			<#if con?index_of(idd) == -1>
				<#assign con=con+l.msg.proName+"|">
import ${projectPath}.action.bean.${l.msg.proName}${msgSuffix};
				<#if l.clientMsg??>
import ${projectPath}.action.bean.${l.clientMsg.proName}${msgSuffix};
				</#if>
			</#if>
		</#if>
	</#list>
</#list>

/**
 * 消息Bean用常量
 * 
 * @author ${author}
 */
public interface RequestMessageConstants {
<#assign con="|">
<#list beans as b>
	<#list b.linkedList as l>
		<#if !l.proxy>
			<#assign idd="|"+l.ids+"|">
			<#if con?index_of(idd) == -1>
				<#assign con=con+l.ids+"|">

				<#if l.msg??>
	/**
	 * ${l.desc}
	 * 
	 * @author ${author}
	 */
	RequestMessageAttribute<${l.msg.proName}${msgSuffix}> RM_${l.id[0]?c} = new RequestMessageAttribute<${l.msg.proName}${msgSuffix}>(${l.id[0]?c}, ${l.msg.id?c}, ${l.msg.proName}${msgSuffix}.class);
					<#if l.clientMsg??>

	/**
	 * ${l.desc}，针对在客户端的
	 * 
	 * @author ${author}
	 */
	RequestMessageAttribute<${l.clientMsg.proName}${msgSuffix}> RM_CLIENT_${l.id[0]?c} = new RequestMessageAttribute<${l.clientMsg.proName}${msgSuffix}>(${l.id[0]?c}, ${l.msg.id?c}, ${l.clientMsg.proName}${msgSuffix}.class);
					</#if>
				<#else>
	/**
	 * ${l.desc}
	 * 
	 * @author ${author}
	 */
	RequestMessageAttribute<BaseMessageBean> RM_${l.id[0]?c} = new RequestMessageAttribute<BaseMessageBean>(${l.id[0]?c});
				</#if>
			</#if>
		</#if>
	</#list>
</#list>
}
