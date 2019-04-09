/**
 * @author ${author}
 */
package ${projectPath}.action.bean.tools;

import com.tfzzh.core.view.socket.tools.BaseMessageBeanAttribute;

<#assign con="|">
<#list beans as b>
	<#assign idd="|"+b.proName+"|">
	<#if con?index_of(idd) == -1>
		<#assign con=con+b.proName+"|">
import ${projectPath}.action.bean.${b.proName}${msgSuffix};
	</#if>
</#list>

/**
 * 消息Bean用常量
 * 
 * @author ${author}
 */
public interface MessageBeanConstants {
<#list beans as b>

	/**
	 * ${b.desc}
	 * 
	 * @author ${author}
	 */
	BaseMessageBeanAttribute<${b.proName}${msgSuffix}> MB_${b.id?c} = new BaseMessageBeanAttribute<${b.proName}${msgSuffix}>(${b.id?c}, ${b.proName}${msgSuffix}.class);
</#list>
}
