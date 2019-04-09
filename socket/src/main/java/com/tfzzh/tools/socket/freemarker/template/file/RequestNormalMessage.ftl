/**
 * @author ${author}
 */
package ${projectPath}${bean.scopePackageName}.action.bean;

import java.io.IOException;
<#if bean.hasList>
<#if bean.hasListImpl>
import java.util.LinkedList;
</#if>
import java.util.List;
</#if>
import java.util.Map;

<#if bean.needValidate>
	<#if bean.hasNumberValidate>
import com.tfzzh.core.validate.NumberValidateTypeEnum;
	</#if>
import com.tfzzh.core.validate.RequestValidateException;
	<#if bean.hasStringValidate>
import com.tfzzh.core.validate.StringValidate;
	</#if>
</#if>
import com.tfzzh.core.view.socket.annotation.ActionMessage;
<#if !bean.hasParent>
import com.tfzzh.core.view.socket.bean.BaseMessageBean;
</#if>
import com.tfzzh.core.view.socket.tools.TfzzhDataInputStream;
import com.tfzzh.core.view.socket.tools.TfzzhDataOutputStream;
<#if !bean.inSameScope>
import ${projectPath}${bean.parent.scopePackageName}.action.bean.${bean.parent.proName}${msgSuffix};
</#if>

<#assign parentParams=bean.parentParams>
<#assign ind=0>
/**
 * ${bean.desc}消息<br />
<#if bean.hasParent>
	<#list parentParams as param>
		<#assign ind=ind+1>
 * ${ind}:${param.name}(${param.typeName}):${param.desc}<br />
	</#list>
</#if>
<#list bean.params as param>
		<#assign ind=ind+1>
 * ${ind}:${param.name}(${param.typeName}):${param.desc}<br />
</#list>
 * 以上(${ind}个)为所需参数，以及为以上表现顺序<br />
 * 
 * @author ${author}
 */
@ActionMessage(${bean.id?c})
public class ${bean.proName}${msgSuffix} extends <#if bean.hasParent>${bean.parent.proName}${msgSuffix}<#else>BaseMessageBean</#if> {

	/**
	 * @author ${author}
	 */
	private static final long serialVersionUID = 1L;
<#list bean.params as param>

	/**
	 * ${param.desc}
	 * 
	 * @author ${author}
	 */
	private ${param.typeName} ${param.name};
</#list>

	/**
	 * @author ${author}
	 */
	public ${bean.proName}${msgSuffix}() {
<#if bean.hasParent>
	<#if bean.parentProxy>
		super.hasChild();
	</#if>
</#if>
	}

	/**
	 * @author ${author}
<#if bean.hasParent>
	<#list parentParams as param>
	 * @param ${param.name} ${param.desc}
	</#list>
</#if>
<#list bean.params as param>
	 * @param ${param.name} ${param.desc}
</#list>
	 */
	public ${bean.proName}${msgSuffix}(<#if bean.hasParent><#list parentParams as param>final ${param.typeName} ${param.name}<#if param_has_next>, <#elseif (bean.params?size > 0)>, </#if></#list></#if><#list bean.params as param>final ${param.typeName} ${param.name}<#if param_has_next>, </#if></#list>) {
<#if bean.hasParent>
		// 首先完成父类数值工作
		super(<#list parentParams as param>${param.name}<#if param_has_next>, </#if></#list>);
</#if>
<#list bean.params as param>
		this.${param.name} = ${param.name};
</#list>
<#if bean.hasParent>
	<#if bean.parentProxy>
		super.hasChild();
	</#if>
</#if>
	}

	/**
	 * @author ${author}
	 * @param buf 预从中读取消息的字节流对象
	 * @param problemMsg 问题消息列表
	 * @throws IOException 抛
	 */
	public ${bean.proName}${msgSuffix}(final TfzzhDataInputStream buf, final Map<String, String> problemMsg) throws IOException {
<#if bean.hasParent>
	<#if bean.parentProxy>
		super.hasChild();
	</#if>
</#if>
		this.read(buf, problemMsg);
	}
<#list bean.params as param>

	/**
	 * 得到${param.desc}
	 * 
	 * @author ${author}
	 * @return the ${param.name}
	 */
	public ${param.typeName} get${param.proName}() {
		return this.${param.name};
	}

	/**
	 * 设置${param.desc}
	 * 
	 * @author ${author}
	 * @param ${param.name} ${param.desc}
	 */
	public void set${param.proName}(final ${param.typeName} ${param.name}) {
		this.${param.name} = ${param.name};
	}
</#list>

	/**
	 * @author ${author}
	 * @param buf 预从中读取消息的字节流对象
	 * @throws IOException 抛
	 * @see com.tfzzh.core.view.socket.bean.BaseMessageBean#write(com.tfzzh.core.view.socket.tools.TfzzhDataOutputStream)
	 */
	@Override
	public void write(final TfzzhDataOutputStream buf) throws IOException {
<#if bean.hasParent>
		super.write(buf);
</#if>
<#list bean.params as param>
	<#if param.objType??>
		<#switch param.typeObjectName>
			<#case "List">
		if (null == this.${param.name}) {
			buf.writeInt(0);
		} else {
			buf.writeInt(this.${param.name}.size());
			for (final ${param.obj.proName}${msgSuffix} bean : this.${param.name}) {
				bean.write(buf);
			}
		}
			<#break>
			<#case "Bean">
		this.${param.name}.write(buf);
			<#break>
		</#switch>
	<#else>
		buf.write${param.typeObjectName}(this.${param.name});
	</#if>
</#list>
	}

	/**
	 * @author ${author}
	 * @param buf 预从中读取消息的字节流对象
	 * @param problemMsg 问题消息列表
	 * @throws IOException 抛
	 * @see com.tfzzh.core.view.socket.bean.BaseMessageBean#read(com.tfzzh.core.view.socket.tools.TfzzhDataInputStream,
	 *      java.util.Map)
	 */
	@Override
	public void read(final TfzzhDataInputStream buf, final Map<String, String> problemMsg) throws IOException {
<#if bean.hasParent>
		super.read(buf, problemMsg);
</#if>
<#list bean.params as param>
	<#if param.objType??>
		<#switch param.typeObjectName>
			<#case "List">
		int size = buf.readInt();
		this.${param.name} = new LinkedList<${param.obj.proName}${msgSuffix}>();
		for (; size > 0; size--) {
			this.${param.name}.add(new ${param.obj.proName}${msgSuffix}(buf, problemMsg));
		}
			<#break>
			<#case "Bean">
		this.${param.name} = new ${param.obj.proName}${msgSuffix}(buf, problemMsg);
			<#break>
		</#switch>
	<#else>
		this.${param.name} = buf.read${param.typeObjectName}();
	</#if>
</#list>
<#if bean.needValidate>
		// 值都处理OK后开始进行验证
		this.validate(problemMsg);
</#if>
	}
<#if bean.needValidate>

	/**
	 * 进行数据验证
	 * 
	 * @author ${author}
	 * @param problemMsg 问题消息列表
	 */
	private void validate(final Map<String, String> problemMsg) {
<#list bean.params as param>
	<#if param.needValidate>
		// 验证${param.desc}
		try {
		<#list param.validates as vali>
			${vali.key.methodPath}(this.${param.name}<#list vali.value as va>, ${va}</#list>);
		</#list>
		} catch (final RequestValidateException e) {
			problemMsg.put("${param.name}", e.getMessage());
		}
	</#if>
</#list>
	}
</#if>
}
