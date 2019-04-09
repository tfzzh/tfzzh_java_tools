/**
 * @author Weijie Xu
 * @dateTime 2014-3-7 下午1:14:18
 */
package com.tfzzh.tools.data;

import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.bean.DataCorrelationBeanTool;
import com.tfzzh.tools.data.bean.LogicBeanTool;
import com.tfzzh.tools.data.bean.tools.LinkKeyBean;
import com.tfzzh.tools.data.template.ControllerContentTemplate;
import com.tfzzh.tools.data.template.CorrelationIndexTemplate;
import com.tfzzh.tools.data.template.CreateTableSQLTemplate;
import com.tfzzh.tools.data.template.DataAttributeTemplate;
import com.tfzzh.tools.data.template.DataCorrelationBeanTemplate;
import com.tfzzh.tools.data.template.DataSetTemplate;
import com.tfzzh.tools.data.template.DataStructurePoolTemplate;
import com.tfzzh.tools.data.template.LogicAnalysisBeanTemplate;
import com.tfzzh.tools.data.template.LogicAnalysisElementBeanTemplate;
import com.tfzzh.tools.data.template.LogicAttributeTemplate;
import com.tfzzh.tools.data.template.LogicBeanTemplate;
import com.tfzzh.tools.data.template.LogicSetTemplate;
import com.tfzzh.tools.data.template.LogicStructurePoolTemplate;
import com.tfzzh.tools.data.template.NewDataOperationToolImplTemplate;
import com.tfzzh.tools.data.template.SystemBeanTemplate;
import com.tfzzh.tools.data.template.SystemDAOImplTemplate;
import com.tfzzh.tools.data.template.SystemDAOTemplate;
import com.tfzzh.tools.data.template.SystemManagerImplTemplate;
import com.tfzzh.tools.data.template.SystemManagerTemplate;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * @author Weijie Xu
 * @dateTime 2014-3-7 下午1:14:18
 */
public class MakeFileForExcel {

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午1:14:18
	 */
	public MakeFileForExcel() {
		this.make();
	}

	/**
	 * 进行模版生成
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-7 下午1:14:18
	 */
	private void make() {
		// 集合主键
		for (final LinkKeyBean lk : ToolBeanExcelPool.getInstance().getObjectLinkKeys()) {
			new CorrelationIndexTemplate(lk);
		}
		// 数据对象
		for (final DataBeanTool t : ToolBeanExcelPool.getInstance().getAllData()) {
			new CreateTableSQLTemplate(t);
			new SystemDAOImplTemplate(t);
			new SystemDAOTemplate(t);
			new SystemBeanTemplate(t);
			new DataAttributeTemplate(t);
		}
		// 数据关系对象
		for (final DataCorrelationBeanTool t : ToolBeanExcelPool.getInstance().getAllDataCorrelation()) {
			new DataCorrelationBeanTemplate(t);
		}
		// 逻辑对象
		for (final LogicBeanTool t : ToolBeanExcelPool.getInstance().getAllLogic()) {
			// new LogicObjectTemplate(t);
			new LogicAttributeTemplate(t);
		}
		for (final LogicBeanTool t : ToolBeanExcelPool.getInstance().getDataLogic()) {
			new LogicBeanTemplate(t);
		}
		for (final LogicBeanTool t : ToolBeanExcelPool.getInstance().getTransitionLogic()) {
			new LogicBeanTemplate(t);
		}
		for (final LogicBeanTool t : ToolBeanExcelPool.getInstance().getAnalysisLogic()) {
			new LogicAnalysisBeanTemplate(t);
			if (!t.getAnalysisTemplate().isRelatedStream()) {
				// 针对非数据流产生的对象
				new LogicAnalysisElementBeanTemplate(t);
			}
		}
		// 其他综合
		new ControllerContentTemplate();
		new SystemManagerImplTemplate();
		new SystemManagerTemplate();
		new DataSetTemplate();
		new NewDataOperationToolImplTemplate();
		new DataStructurePoolTemplate();
		new LogicSetTemplate();
		new LogicStructurePoolTemplate();
	}
}
