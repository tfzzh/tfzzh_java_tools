/**
 * JSON数据解析工具<br />
 * 解析JSON格式的表结构定义，构建DataBeanTool等对象并填充到ToolBeanExcelPool<br />
 * 
 * @author auto
 * @dateTime 2026-06-28
 */
package com.tfzzh.tools.data.tools.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.bean.DataBeanTool.DataFieldTool;
import com.tfzzh.tools.data.tools.ToolBeanExcelPool;

/**
 * JSON数据解析工具
 */
public class JsonDataParser {

	/**
	 * @param path 目标JSON文件地址
	 */
	public JsonDataParser(final String path) {
		this.read(path);
	}

	/**
	 * 读取JSON文件
	 * 
	 * @param path 目标JSON文件地址
	 */
	private void read(final String path) {
		final StringBuilder sb = new StringBuilder();
		try (final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			return;
		}
		final JSONObject root = JSON.parseObject(sb.toString());
		final JSONArray tables = root.getJSONArray("tables");
		if (tables == null) {
			return;
		}
		for (int i = 0; i < tables.size(); i++) {
			final JSONObject table = tables.getJSONObject(i);
			this.parseTable(table);
		}
	}

	/**
	 * 完成所有数据读取后，执行整理和初始化
	 */
	public static void finalizePool() {
		// 进行数据类的整理
		ToolBeanExcelPool.getInstance().tidyData();
		// 一些其他的初始化操作
		ToolBeanExcelPool.getInstance().initLogicTools();
	}

	/**
	 * 解析单个表定义
	 * 
	 * @param table 表JSON对象
	 */
	private void parseTable(final JSONObject table) {
		final long id = table.getLongValue("id", 0L);
		final String name = table.getString("name");
		final String databaseName = table.getString("databaseName");
		final String tableName = table.getString("tableName");
		final String description = table.getString("description");
		final String srcPath = table.getString("srcPath");
		final String functionName = table.getString("functionName");
		final String createAuthor = table.getString("createAuthor");
		final String createDate = table.getString("createDate");
		final String lastChangeAuthor = table.getString("lastChangeAuthor");
		final String lastChangeDate = table.getString("lastChangeDate");
		final DataBeanTool data = new DataBeanTool(id, name, databaseName, tableName, description, srcPath, functionName, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		final JSONArray fields = table.getJSONArray("fields");
		if (fields != null) {
			for (int i = 0; i < fields.size(); i++) {
				final JSONObject field = fields.getJSONObject(i);
				this.parseField(data, field);
			}
		}
		// 解析初始化数据
		final JSONObject dataObj = table.getJSONObject("data");
		if (dataObj != null) {
			this.parseTableData(data, dataObj);
		}
		// 处理父类关系
		data.handleParent();
		// 放入数据池
		ToolBeanExcelPool.getInstance().putDataBeanTool(data);
	}

	/**
	 * 解析表初始化数据
	 *
	 * @param data 所属DataBeanTool
	 * @param dataObj 数据JSON对象
	 */
	private void parseTableData(final DataBeanTool data, final JSONObject dataObj) {
		final JSONArray columns = dataObj.getJSONArray("columns");
		if ((columns == null) || (columns.size() == 0)) {
			return;
		}
		final List<String> fieldOrder = new ArrayList<>(columns.size());
		for (int i = 0; i < columns.size(); i++) {
			fieldOrder.add(columns.getString(i));
		}
		data.setFieldOrder(fieldOrder);
		final JSONArray rows = dataObj.getJSONArray("rows");
		if (rows == null) {
			return;
		}
		for (int i = 0; i < rows.size(); i++) {
			final JSONArray row = rows.getJSONArray(i);
			if (row == null) {
				continue;
			}
			final List<String> dataLine = new ArrayList<>(row.size());
			for (int j = 0; j < row.size(); j++) {
				dataLine.add(row.getString(j));
			}
			data.addDataInfo(dataLine);
		}
	}

	/**
	 * 解析单个字段定义
	 * 
	 * @param data 所属DataBeanTool
	 * @param field 字段JSON对象
	 */
	private void parseField(final DataBeanTool data, final JSONObject field) {
		final long id = field.getLongValue("id", 0L);
		final String name = field.getString("name");
		final String dataFieldName = field.getString("dataFieldName");
		final String fieldType = field.getString("fieldType");
		final String length = field.getString("length");
		final String description = field.getString("description");
		// isKey: JSON中为整数，映射到Integer（null表示非主键；正数为升序，负数为降序）
		final Integer isKey;
		if (field.containsKey("isKey") && (field.get("isKey") != null)) {
			final int keyVal = field.getIntValue("isKey", 0);
			isKey = keyVal != 0 ? Integer.valueOf(keyVal) : null;
		} else {
			isKey = null;
		}
		final boolean isUuid = field.getBooleanValue("isUuid", false);
		final boolean isIncrementKey = field.getBooleanValue("isIncrementKey", false);
		final int isUnique = field.getIntValue("isUnique", 0);
		final boolean isUnsigned = field.getBooleanValue("isUnsigned", false);
		final boolean canNull = field.getBooleanValue("canNull", false);
		final String defaultValue = field.getString("defaultValue");
		final String index = field.getString("index");
		final String createAuthor = field.getString("createAuthor");
		final String createDate = field.getString("createDate");
		final String lastChangeAuthor = field.getString("lastChangeAuthor");
		final String lastChangeDate = field.getString("lastChangeDate");
		final DataFieldTool tool = data.addField(id, name, dataFieldName, fieldType, length, description, isKey, isUuid, isIncrementKey, isUnique, isUnsigned, canNull, defaultValue, index, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
		ToolBeanExcelPool.getInstance().putDataFieldTool(tool);
	}
}
