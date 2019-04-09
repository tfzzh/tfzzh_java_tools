/**
 * @author ${author}
 */
package ${projectPath}.${scopeName}.tools;

import com.tfzzh.core.view.socket.tools.OperationArrtibute;
<#list beans as b>
import ${projectPath}${b.scopePackageName}.operation.To${b.proName}Operation;
</#list>
import com.yqgame.socketserver.tools.OperationConstants;

/**
 * 逻辑节点的控制类常量
 * 
 * @author ${author}
 */
public interface ${proName}OperationConstants extends OperationConstants {
<#list beans as b>

	/**
	 * 与消息分发的操作属性
	 * 
	 * @author ${author}
	 */
	OperationArrtibute<To${b.proName}Operation> TO_${b.proName?upper_case} = new OperationArrtibute<To${b.proName}Operation>("to${b.proName}");
</#list>
}
