/**
 * @author Xu Weijie
 * @datetime 2018年1月22日_上午11:05:08
 */
package com.tfzzh.tools.data.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfzzh.tools.StringTools;
import com.tfzzh.tools.XmlToBean;
import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;
import com.tfzzh.tools.data.bean.XmlFieldInfoBean;
import com.tfzzh.tools.data.bean.XmlIndexFieldInfoBean;
import com.tfzzh.tools.data.bean.XmlIndexInfoBean;
import com.tfzzh.tools.data.bean.XmlKeyInfoBean;
import com.tfzzh.tools.data.bean.XmlTableInfoBean;

/**
 * XML解析工具<br />
 * 属于全内容<br />
 * 需要是单一对象，通过initData()方法，将读入的xml具体写入到数据池<br />
 * 
 * @author Xu Weijie
 * @datetime 2018年1月22日_上午11:05:08
 */
public class ToolBeanPoolXmlTool {

	/**
	 * 数据列表
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:20:11
	 */
	private final List<XmlTableInfoBean> xl = new ArrayList<>();

	/**
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:20:22
	 * @param path 路径列表
	 */
	public ToolBeanPoolXmlTool(final String path) {
		this.addPaths(path);
	}

	/**
	 * 增加路径列表
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:22:58
	 * @param path 路径列表
	 */
	public void addPaths(final String path) {
		final List<String> paths = StringTools.splitToArray(path, ",");
		for (final String p : paths) {
			this.read(p);
		}
	}

	/**
	 * @author Xu Weijie
	 * @datetime 2018年1月22日_上午11:22:05
	 * @param path 目标路径
	 */
	private void read(final String path) {
		final List<XmlTableInfoBean> xl = XmlToBean.readXmlToBean(path, XmlTableInfoBean.class);
		this.xl.addAll(xl);
	}

	/**
	 * 初始化数据
	 * 
	 * @author Xu Weijie
	 * @datetime 2018年1月23日_上午11:15:41
	 */
	public void initData() {
		XmlKeyInfoBean key;
		DataBeanTool db;
		for (final XmlTableInfoBean t : this.xl) {
			// 放入数据对象
			db = new DataBeanTool(t.getId(), t.getProName(), t.getConnName(), t.getName(), t.getDesc(), t.getSrc(), t.getFunName(), t.getCreateUser(), t.getCreateTime(), t.getChangeUser(), t.getChagneTime());
			ToolBeanExcelPool.getInstance().putDataBeanTool(db);
			DataFieldTool df;
			for (final XmlFieldInfoBean f : t.getFields()) {
				df = db.addField(f.getId(), f.getProName(), f.getName(), f.getType(), f.getLength(), f.getDesc(), f.isUuid(), f.isIncrement(), f.isNonnegative(), f.isNonnull(), f.getDefValue(), f.getCreateUser(), f.getCreateTime(), f.getChangeUser(), f.getChagneTime());
				ToolBeanExcelPool.getInstance().putDataFieldTool(df);
			}
			// 处理主键
			key = t.getKey();
			if (null != key) {
				db.putKeyIndex(key.getKeyField());
			}
			// 处理索引
			if (null != t.getIndexs()) {
				Map<String, String> fm;
				int i = 1;
				for (final XmlIndexInfoBean ind : t.getIndexs()) {
					fm = new HashMap<>();
					for (final XmlIndexFieldInfoBean f : ind.getIndexField()) {
						fm.put(f.getVal(), f.getOrder());
					}
					db.putIndexData(ind.getType(), i++, fm);
				}
			}
		}
		// 进行数据类的整理
		ToolBeanExcelPool.getInstance().tidyData();
	}
}
