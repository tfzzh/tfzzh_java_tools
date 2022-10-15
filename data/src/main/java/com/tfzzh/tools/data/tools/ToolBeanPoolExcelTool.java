/**
 * @author Weijie Xu
 * @dateTime Aug 12, 2014 3:31:42 PM
 */
package com.tfzzh.tools.data.tools;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.DateFormat;
import com.tfzzh.tools.FileTools;
import com.tfzzh.tools.data.bean.DataBeanTool;
import com.tfzzh.tools.data.bean.DataCorrelationBeanTool;
import com.tfzzh.tools.data.bean.LogicBeanTool;

/**
 * Excel解析工具<br />
 * 属于全内容<br />
 * 
 * @author Weijie Xu
 * @dateTime Aug 12, 2014 3:31:42 PM
 */
public class ToolBeanPoolExcelTool {

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 4:36:21 PM
	 * @param path 目标文件地址
	 */
	public ToolBeanPoolExcelTool(final String path) {
		this.read(path);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 4:36:23 PM
	 * @param path 目标文件地址
	 */
	private void read(String path) {
		path = FileTools.purifyFilePath(path);
		// 工作空间
		Workbook wb;
		try {
			wb = new XSSFWorkbook(new FileInputStream(path));
		} catch (final Exception e) {
			try {
				wb = new HSSFWorkbook(new FileInputStream(path));
			} catch (final Exception e1) {
				e.printStackTrace();
				e1.printStackTrace();
				return;
			}
		}
		{
			// 以下顺序不可乱
			// 获取数据库表相关数据
			this.readDataTableData(wb);
			// 获取数据库表字段相关数据
			this.readDataFieldData(wb);
			// 进行数据类的整理
			ToolBeanExcelPool.getInstance().tidyData();
			// 获取数据关联条件的整合对象相关数据
			this.readDataCorrelationData(wb);
			// 获取逻辑相关数据
			this.readDataLogicData(wb);
			// 获取逻辑相关属性数据
			this.readDataLogicAttributeData(wb);
			// 一些其他的初始化操作
			ToolBeanExcelPool.getInstance().initLogicTools();
		}
	}

	/**
	 * 获取数据库表相关数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 5:02:19 PM
	 * @param wb 工作空间信息
	 */
	private void readDataTableData(final Workbook wb) {
		// 所需工作表
		final Sheet sheet = wb.getSheet("数据对象关系总表");
		if (null == sheet) {
			throw new ConfigurationException("Not Exists target Sheet[数据对象关系总表]...");
		}
		// 行消息
		Row row;
		// 字段消息
		Cell cell;
		// 类/表Id
		long id;
		// 数据名，用于与表单名称对应
		String dataName;
		// 相关数据库的名字（通用名/前缀，非实际名字）
		String databaseName;
		// 数据库中对应表明，用于数据库中的使用
		String tableName;
		// 中文说明
		String desc;
		// 源码包路径
		String srcPath;
		// 功能名
		String functionName;
		// 创建人
		String createAuthor;
		// 创建时间
		String createDate;
		// 最后修改人
		String lastChangeAuthor;
		// 最后修改时间
		String lastChangeDate;
		// 行位置索引，默认是0
		int rowInd = 1;
		// 列位置索引
		int colInd;
		// 开始读取过程
		while ((null != (row = sheet.getRow(rowInd++))) && (row.getFirstCellNum() != -1)) {
			// System.out.println("数据对象关系总表 > " + rowInd);
			// 每行从新开始
			colInd = 0;
			// 数据对象ID
			cell = row.getCell(colInd); // 0
			if (null == cell) {
				// System.out.println("cell null > " + row.getFirstCellNum());
				break;
			}
			id = (long) cell.getNumericCellValue();
			// 数据对象名
			cell = row.getCell(colInd += 3); // 3
			dataName = cell.getStringCellValue();
			// 数据库通用名
			cell = row.getCell(++colInd); // 4
			databaseName = cell.getStringCellValue();
			// 数据表名
			cell = row.getCell(++colInd); // 5
			tableName = cell.getStringCellValue();
			// 中文说明
			cell = row.getCell(++colInd); // 6
			desc = cell.getStringCellValue();
			// 所属源码包
			cell = row.getCell(++colInd); // 7
			srcPath = cell.getStringCellValue();
			// 所属功能名
			cell = row.getCell(++colInd); // 8
			functionName = cell.getStringCellValue();
			// 创建时间
			cell = row.getCell(++colInd); // 9
			try {
				createDate = DateFormat.getShortDateShow(cell.getDateCellValue());
			} catch (final Exception e) {
				createDate = DateFormat.getShortDateShow(new Date());
			}
			// 创建人
			cell = row.getCell(++colInd); // 10
			createAuthor = cell.getStringCellValue();
			// 最后修改时间
			cell = row.getCell(++colInd); // 11
			if (null == cell) {
				lastChangeDate = null;
			} else {
				lastChangeDate = DateFormat.getShortDateShow(cell.getDateCellValue());
			}
			// 最后修改人
			cell = row.getCell(++colInd); // 12
			if (null == cell) {
				lastChangeAuthor = null;
			} else {
				lastChangeAuthor = cell.getStringCellValue();
			}
			ToolBeanExcelPool.getInstance().putDataBeanTool(new DataBeanTool(id, dataName, databaseName, tableName, desc, srcPath, functionName, createAuthor, createDate, lastChangeAuthor, lastChangeDate));
		}
	}

	/**
	 * TODO 之后需要考虑多表单的处理方式，可以考虑在增加一个总配置页<br />
	 * 获取数据库表字段相关数据<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 5:02:57 PM
	 * @param wb 工作空间信息
	 */
	private void readDataFieldData(final Workbook wb) {
		// 所需工作表
		final Sheet sheet = wb.getSheet("数据对象关系—表详细");
		if (null == sheet) {
			throw new ConfigurationException("Not Exists target Sheet[数据对象关系—表详细]...");
		}
		// 行消息
		Row row;
		// 字段消息
		Cell cell;
		// 属性ID
		long id;
		// 相关表ID
		long dataId;
		// 属性名
		String name;
		// 中文说明
		String desc;
		// 表字段名
		String fieldName;
		// 表字段类型
		String type;
		// 字段长度
		String fieldLength;
		// 主键
		Integer isKey;
		// 唯一
		Integer isUnique;
		// 自增
		boolean isIncrementKey;
		// UUID
		boolean isUuid;
		// 非负
		boolean isUnsigned;
		// 可Null
		boolean canNull;
		// 默认值
		String def;
		// 索引
		String index;
		// 创建人
		String createAuthor;
		// 创建时间
		String createDate;
		// 最后修改人
		String lastChangeAuthor;
		// 最后修改时间
		String lastChangeDate;
		// 行位置索引，默认是0
		int rowInd = 1;
		// 列位置索引
		int colInd;
		// 数据对象
		DataBeanTool data = null;
		// 开始读取过程
		while (null != (row = sheet.getRow(rowInd++))) {
			// System.out.println("数据对象关系—表详细 > " + rowInd);
			// 每行从新开始
			if (null == data) {
				colInd = 0;
				// 对象ID
				cell = row.getCell(colInd); // 0
				if (null == cell) {
					// 不再存在必要的数据
					break;
				}
				dataId = (long) cell.getNumericCellValue();
				// 处理相关表数据
				this.parseTableData(wb, data);
				data = ToolBeanExcelPool.getInstance().getDataTool(dataId);
				if (null == data) {
					throw new ConfigurationException("Not Exists DataId[" + dataId + "] in 数据对象关系—表详细(row:" + (rowInd + 1) + ")...");
				}
				// 属性ID
				cell = row.getCell(colInd += 3); // 3
				id = (long) cell.getNumericCellValue();
				// } else if ((null != (cell = row.getCell(0))) && (cell.getCellType() != Cell.CELL_TYPE_BLANK)) {
			} else if ((null != (cell = row.getCell(0))) && (cell.getCellType() != CellType.BLANK)) {
				// } else if ((null != (cell = row.getCell(0))) && (!"".equals(cell.getStringCellValue()))) {
				// 无空行而开启了新数据
				colInd = 0;
				dataId = (long) cell.getNumericCellValue();
				// 处理相关表数据
				this.parseTableData(wb, data);
				data = ToolBeanExcelPool.getInstance().getDataTool(dataId);
				if (null == data) {
					throw new ConfigurationException("Not Exists DataId[" + dataId + "] in 数据对象关系—表详细(row:" + (rowInd + 1) + ")...");
				}
				// 属性ID
				cell = row.getCell(colInd += 3); // 3
				id = (long) cell.getNumericCellValue();
			} else {
				colInd = 3;
				// 属性ID
				cell = row.getCell(colInd); // 3
				// if ((null == cell) || (cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
				if ((null == cell) || (cell.getCellType() == CellType.BLANK)) {
					// if ((null == cell) || ("".equals(cell.getStringCellValue()))) {
					// 分割行，需要换行，并进入到下一个对象
					// 处理相关表数据
					this.parseTableData(wb, data);
					data = null;
					continue;
				}
				try {
					id = (long) cell.getNumericCellValue();
				} catch (final Exception e) {
					// 非数值，跳过
					// 处理相关表数据
					this.parseTableData(wb, data);
					data = null;
					continue;
				}
				if (id == 0) {
					continue;
				}
			}
			// 属性名
			cell = row.getCell(++colInd); // 4
			name = cell.getStringCellValue();
			// 中文说明
			cell = row.getCell(++colInd); // 5
			desc = cell.getStringCellValue();
			// 表字段名
			cell = row.getCell(colInd += 2); // 7
			fieldName = cell.getStringCellValue();
			// 表字段类型
			cell = row.getCell(++colInd); // 8
			type = cell.getStringCellValue();
			// 字段长度
			cell = row.getCell(++colInd); // 9
			if (null != cell) {
				// switch (cell.getCellType()) {
				// case Cell.CELL_TYPE_NUMERIC:
				// fieldLength = String.valueOf((int) cell.getNumericCellValue());
				// break;
				// case Cell.CELL_TYPE_STRING:
				// fieldLength = cell.getStringCellValue();
				// break;
				// default:
				// fieldLength = "";
				// break;
				// }
				switch (cell.getCellType()) {
				case NUMERIC:
					fieldLength = String.valueOf((int) cell.getNumericCellValue());
					break;
				case STRING:
					fieldLength = cell.getStringCellValue();
					break;
				default:
					fieldLength = "";
					break;
				}
			} else {
				fieldLength = "";
			}
			// 主键
			cell = row.getCell(++colInd); // 10
			if (null != cell) {
				// switch (cell.getCellType()) {
				// case Cell.CELL_TYPE_NUMERIC:
				// isKey = (int) cell.getNumericCellValue();
				// break;
				// case Cell.CELL_TYPE_STRING:
				// isKey = Integer.valueOf(cell.getStringCellValue());
				// break;
				// default:
				// isKey = null;
				// break;
				// }
				switch (cell.getCellType()) {
				case NUMERIC:
					isKey = (int) cell.getNumericCellValue();
					break;
				case STRING:
					isKey = Integer.valueOf(cell.getStringCellValue());
					break;
				default:
					isKey = null;
					break;
				}
			} else {
				isKey = null;
			}
			// 唯一
			cell = row.getCell(++colInd); // 11
			if (null != cell) {
				// isUnique = !(cell.getCellType() == Cell.CELL_TYPE_BLANK);
				isUnique = Integer.valueOf((int) cell.getNumericCellValue());
			} else {
				isUnique = 0;
			}
			// 自增
			cell = row.getCell(++colInd); // 12
			if (null != cell) {
				// isIncrementKey = !(cell.getCellType() == Cell.CELL_TYPE_BLANK);
				isIncrementKey = cell.getCellType() != CellType.BLANK;
			} else {
				isIncrementKey = false;
			}
			// UUID
			cell = row.getCell(++colInd); // 13
			if (null != cell) {
				// isUuid = !(cell.getCellType() == Cell.CELL_TYPE_BLANK);
				isUuid = cell.getCellType() != CellType.BLANK;
			} else {
				isUuid = false;
			}
			// 非负
			cell = row.getCell(++colInd); // 14
			if (null != cell) {
				// isUnsigned = !(cell.getCellType() == Cell.CELL_TYPE_BLANK);
				isUnsigned = cell.getCellType() != CellType.BLANK;
			} else {
				isUnsigned = false;
			}
			// 可Null
			cell = row.getCell(++colInd); // 15
			if (null != cell) {
				// canNull = !(cell.getCellType() == Cell.CELL_TYPE_BLANK);
				canNull = cell.getCellType() != CellType.BLANK;
			} else {
				canNull = false;
			}
			// 默认值
			cell = row.getCell(++colInd); // 16
			if (null != cell) {
				// final int cellType;
				// // 默认值
				// if ((cellType = cell.getCellType()) == Cell.CELL_TYPE_BLANK) {
				// def = "";
				// } else if (cellType == Cell.CELL_TYPE_NUMERIC) {
				// def = String.valueOf(cell.getNumericCellValue());
				// } else if (cellType == Cell.CELL_TYPE_STRING) {
				// def = cell.getStringCellValue();
				// } else {
				// def = "";
				// }
				switch (cell.getCellType()) {
				case NUMERIC:
					def = String.valueOf(cell.getNumericCellValue());
					break;
				case STRING:
					def = cell.getStringCellValue();
					break;
				case BLANK:
				default:
					def = "";
					break;
				}
			} else {
				def = "";
			}
			// 索引
			cell = row.getCell(++colInd); // 17
			if (null != cell) {
				index = cell.getStringCellValue();
			} else {
				index = "";
			}
			// 创建时间
			cell = row.getCell(++colInd); // 18
			createDate = DateFormat.getShortDateShow(cell.getDateCellValue());
			// 创建人
			cell = row.getCell(++colInd); // 19
			createAuthor = cell.getStringCellValue();
			// 最后修改时间
			cell = row.getCell(++colInd); // 20
			if ((null == cell) || (null == cell.getDateCellValue())) {
				lastChangeDate = null;
			} else {
				lastChangeDate = DateFormat.getShortDateShow(cell.getDateCellValue());
			}
			// 最后修改人
			cell = row.getCell(++colInd); // 21
			if (null == cell) {
				lastChangeAuthor = null;
			} else {
				lastChangeAuthor = cell.getStringCellValue();
			}
			ToolBeanExcelPool.getInstance().putDataFieldTool(data.addField(id, name, fieldName, type, fieldLength, desc, isKey, isUuid, isIncrementKey, isUnique, isUnsigned, canNull, def, index, createAuthor, createDate, lastChangeAuthor, lastChangeDate));
		}
		this.parseTableData(wb, data);
	}

	/**
	 * 解析所相关数据表
	 * 
	 * @author XuWeijie
	 * @dateTime Aug 5, 2010 6:47:36 PM
	 * @param wb 工作空间信息
	 * @param data 数据对象工具
	 */
	private void parseTableData(final Workbook wb, final DataBeanTool data) {
		if (null == data) {
			return;
		}
		// add xwj 2017-09-20
		data.handleParent();
		int i = 0;
		Sheet ds = wb.getSheet(data.getProName() + "_Data");
		while (null != ds) {
			// 用于记录列字段关系
			final Map<Integer, String> colField = new TreeMap<>();
			// 行消息
			Row row;
			// 字段消息
			Cell cell;
			int index;
			int rl = 0;
			{ // 进行数据列关系
				index = -1;
				String content;
				int leftParenthesisIndex;
				do {
					row = ds.getRow(rl++);
					if (null == row) {
						break;
					}
					index = -1;
					while ((cell = row.getCell(++index)) != null) {
						// switch (cell.getCellType()) {
						// case Cell.CELL_TYPE_STRING:
						// // 是字符
						// content = cell.getStringCellValue();
						// break;
						// case Cell.CELL_TYPE_FORMULA: {
						// switch (cell.getCachedFormulaResultType()) {
						// case Cell.CELL_TYPE_STRING:
						// // 是文字类型
						// content = cell.getStringCellValue();
						// break;
						// default:
						// continue;
						// }
						// break;
						// }
						// case Cell.CELL_TYPE_BLANK:
						// return;
						// default:
						// // 其他类型进行下列判定
						// continue;
						// }
						switch (cell.getCellType()) {
						case STRING:
							// 是字符
							content = cell.getStringCellValue();
							break;
						case FORMULA: {
							switch (cell.getCachedFormulaResultType()) {
							case STRING:
								// 是文字类型
								content = cell.getStringCellValue();
								break;
							default:
								continue;
							}
							break;
						}
						case BLANK:
							return;
						default:
							// 其他类型进行下列判定
							continue;
						}
						// 进行是否存在左括号的判定，括号内为字段中文说明
						if ((leftParenthesisIndex = content.indexOf("(")) != -1) {
							content = content.substring(0, leftParenthesisIndex);
						} else if ((leftParenthesisIndex = content.indexOf("（")) != -1) {
							content = content.substring(0, leftParenthesisIndex);
						}
						// 进行字段名称验证
						if (data.existsField(content)) {
							// 名称存在，放入到列字段对应消息中
							colField.put(index, content);
						}
					}
				} while (colField.size() == 0);
				{ // 进行字段顺序记录
					final List<String> fieldOrder = new ArrayList<>(colField.size());
					for (final String str : colField.values()) {
						fieldOrder.add(str);
					}
					data.setFieldOrder(fieldOrder);
				}
			}
			index = rl;
			List<String> dataLine;
			// 遍历数据行以得到数据消息
			while ((row = ds.getRow(index++)) != null) {
				// 开始进行行分析
				dataLine = new ArrayList<>();
				for (final int ind : colField.keySet()) {
					cell = row.getCell(ind);
					if (cell == null) {
						dataLine.add("");
						continue;
					} else {
						// switch (cell.getCellType()) {
						// case Cell.CELL_TYPE_BLANK:
						// dataLine.add("");
						// continue;
						// case Cell.CELL_TYPE_BOOLEAN:
						// dataLine.add(String.valueOf(cell.getBooleanCellValue()));
						// continue;
						// case Cell.CELL_TYPE_NUMERIC:
						// dataLine.add(String.valueOf(cell.getNumericCellValue()));
						// continue;
						// case Cell.CELL_TYPE_STRING:
						// dataLine.add(cell.getStringCellValue());
						// continue;
						// case Cell.CELL_TYPE_FORMULA: {
						// switch (cell.getCachedFormulaResultType()) {
						// case Cell.CELL_TYPE_NUMERIC:
						// dataLine.add(String.valueOf(cell.getNumericCellValue()));
						// continue;
						// case Cell.CELL_TYPE_STRING:
						// dataLine.add(cell.getStringCellValue());
						// continue;
						// default:
						// continue;
						// }
						// }
						// default:
						// continue;
						// }
						switch (cell.getCellType()) {
						case BLANK:
							dataLine.add("");
							continue;
						case BOOLEAN:
							dataLine.add(String.valueOf(cell.getBooleanCellValue()));
							continue;
						case NUMERIC:
							dataLine.add(String.valueOf(cell.getNumericCellValue()));
							continue;
						case STRING:
							dataLine.add(cell.getStringCellValue());
							continue;
						case FORMULA: {
							switch (cell.getCachedFormulaResultType()) {
							case NUMERIC:
								dataLine.add(String.valueOf(cell.getNumericCellValue()));
								continue;
							case STRING:
								dataLine.add(cell.getStringCellValue());
								continue;
							default:
								continue;
							}
						}
						default:
							continue;
						}
						// dataLine.add(cell.getStringCellValue());
					}
				}
				data.addDataInfo(dataLine);
			}
			ds = wb.getSheet(data.getProName() + "_Data_" + ++i);
		}
	}

	/**
	 * 获取数据关联条件的整合对象相关数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 25, 2014 11:20:07 AM
	 * @param wb 工作空间信息
	 */
	private void readDataCorrelationData(final Workbook wb) {
		// 所需工作表
		final Sheet sheet = wb.getSheet("多数据对象关联关系总表");
		if (null == sheet) {
			throw new ConfigurationException("Not Exists target Sheet[内存对象关系总表]...");
		}
		// 行消息
		Row row;
		// 字段消息
		Cell cell;
		// 类Id
		long id;
		// 数据名，用于与表单名称对应
		String name;
		// 中文说明
		String desc;
		// 主数据ID
		long mainDataId;
		// 条件内容
		String condition;
		// // 是否保证在初始化时，相关各列表数据，只会被使用一次
		// boolean useOnly;
		// 获取关联数据对象Id列表用SQL语句
		String sql;
		// 源码包路径
		String srcPath;
		// 功能名
		String functionName;
		// 创建人
		String createAuthor;
		// 创建时间
		String createDate;
		// 最后修改人
		String lastChangeAuthor;
		// 最后修改时间
		String lastChangeDate;
		// 行位置索引，默认是0
		int rowInd = 1;
		// 列位置索引
		int colInd;
		// 开始读取过程
		while (null != (row = sheet.getRow(rowInd++))) {
			// 每行从新开始
			colInd = 0;
			// 数据对象ID
			cell = row.getCell(colInd); // 0
			if (null == cell) {
				break;
			}
			id = (long) cell.getNumericCellValue();
			// 数据对象名
			cell = row.getCell(colInd += 2); // 2
			name = cell.getStringCellValue();
			// 中文说明
			cell = row.getCell(++colInd); // 3
			desc = cell.getStringCellValue();
			// 主对象ID
			cell = row.getCell(colInd += 2); // 5
			mainDataId = (long) cell.getNumericCellValue();
			// 关联条件
			cell = row.getCell(++colInd); // 6
			condition = cell.getStringCellValue();
			// // 是否保证在初始化时，相关各列表数据，只会被使用一次
			// cell = row.getCell(++colInd); // 7
			// if (null == cell) {
			// useOnly = false;
			// } else {
			// useOnly = true;
			// }
			// 获取关联数据对象Id列表用SQL语句
			cell = row.getCell(++colInd); // 7
			sql = cell.getStringCellValue();
			// 所属源码包
			cell = row.getCell(++colInd); // 8
			srcPath = cell.getStringCellValue();
			// 所属功能名
			cell = row.getCell(++colInd); // 9
			functionName = cell.getStringCellValue();
			// 创建时间
			cell = row.getCell(++colInd); // 10
			createDate = DateFormat.getShortDateShow(cell.getDateCellValue());
			// 创建人
			cell = row.getCell(++colInd); // 11
			createAuthor = cell.getStringCellValue();
			// 最后修改时间
			cell = row.getCell(++colInd); // 12
			if (null == cell) {
				lastChangeDate = null;
			} else {
				lastChangeDate = DateFormat.getShortDateShow(cell.getDateCellValue());
			}
			// 最后修改人
			cell = row.getCell(++colInd); // 13
			if (null == cell) {
				lastChangeAuthor = null;
			} else {
				lastChangeAuthor = cell.getStringCellValue();
			}
			ToolBeanExcelPool.getInstance().putDataCorrelationBeanTool(new DataCorrelationBeanTool(id, name, desc, mainDataId, condition, sql, srcPath, functionName, createAuthor, createDate, lastChangeAuthor, lastChangeDate));
		}
	}

	/**
	 * 获取逻辑相关数据
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 3:32:27 PM
	 * @param wb 工作空间信息
	 */
	private void readDataLogicData(final Workbook wb) {
		// 所需工作表
		final Sheet sheet = wb.getSheet("内存对象关系总表");
		if (null == sheet) {
			return;
			// throw new ConfigurationException("Not Exists target Sheet[内存对象关系总表]...");
		}
		// 行消息
		Row row;
		// 字段消息
		Cell cell;
		// 类/表Id
		long id;
		// 数据名，用于与表单名称对应
		String dataName;
		// 中文说明
		String desc;
		// 逻辑对象类型
		String type;
		// 相关联的数据对象ID
		long dataId;
		// 源码包路径
		String srcPath;
		// 功能名
		String functionName;
		// 创建人
		String createAuthor;
		// 创建时间
		String createDate;
		// 最后修改人
		String lastChangeAuthor;
		// 最后修改时间
		String lastChangeDate;
		// 行位置索引，默认是0
		int rowInd = 1;
		// 列位置索引
		int colInd;
		// 开始读取过程
		while ((null != (row = sheet.getRow(rowInd++))) && (row.getFirstCellNum() != -1)) {
			// System.out.println("数据对象关系总表 > " + rowInd);
			// 每行从新开始
			colInd = 0;
			// 数据对象ID
			cell = row.getCell(colInd); // 0
			if (null == cell) {
				// System.out.println("cell null > " + row.getFirstCellNum());
				break;
			}
			id = (long) cell.getNumericCellValue();
			// 数据对象名
			cell = row.getCell(colInd += 3); // 3
			dataName = cell.getStringCellValue();
			// 中文说明
			cell = row.getCell(++colInd); // 4
			desc = cell.getStringCellValue();
			// 逻辑对象类型
			cell = row.getCell(++colInd); // 5
			type = cell.getStringCellValue();
			// 相关联的数据对象ID
			cell = row.getCell(++colInd); // 6
			if (null == cell) {
				dataId = 0l;
			} else {
				dataId = (long) cell.getNumericCellValue();
			}
			// 所属源码包
			cell = row.getCell(++colInd); // 7
			srcPath = cell.getStringCellValue();
			// 所属功能名
			cell = row.getCell(++colInd); // 8
			functionName = cell.getStringCellValue();
			// 创建时间
			cell = row.getCell(++colInd); // 9
			createDate = DateFormat.getShortDateShow(cell.getDateCellValue());
			// 创建人
			cell = row.getCell(++colInd); // 10
			createAuthor = cell.getStringCellValue();
			// 最后修改时间
			cell = row.getCell(++colInd); // 11
			if (null == cell) {
				lastChangeDate = null;
			} else {
				lastChangeDate = DateFormat.getShortDateShow(cell.getDateCellValue());
			}
			// 最后修改人
			cell = row.getCell(++colInd); // 12
			if (null == cell) {
				lastChangeAuthor = null;
			} else {
				lastChangeAuthor = cell.getStringCellValue();
			}
			ToolBeanExcelPool.getInstance().putLogicBeanTool(new LogicBeanTool(id, dataName, desc, type, dataId, srcPath, functionName, createAuthor, createDate, lastChangeAuthor, lastChangeDate));
		}
	}

	/**
	 * 获取逻辑相关属性数据<br />
	 * 这里相关到两个类型的表单，1，基础属性；2，结构属性；<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime Aug 15, 2014 3:34:15 PM
	 * @param wb 工作空间信息
	 */
	private void readDataLogicAttributeData(final Workbook wb) {
		// 行消息
		Row row;
		// 字段消息
		Cell cell;
		// 对象ID
		long logicId;
		// 属性ID
		long id;
		// 属性名
		String name;
		// 数据属性Id
		long dataFieldId;
		// 中文说明
		String desc;
		// 类型
		String type;
		// 创建人
		String createAuthor;
		// 创建时间
		String createDate;
		// 最后修改人
		String lastChangeAuthor;
		// 最后修改时间
		String lastChangeDate;
		// 行位置索引，默认是0
		int rowInd = 1;
		// 列位置索引
		int colInd;
		// 数据对象
		LogicBeanTool logic = null;
		{ // 操作1，解析属性
			final Sheet sheet = wb.getSheet("字段解析对象总表");
			if (null == sheet) {
				return;
				// throw new ConfigurationException("Not Exists target Sheet[字段解析对象总表]...");
			}
			// 是否主数据列表数据关联键
			boolean isDataLinkKey;
			// 模板类型
			String templateType;
			// 拆分符
			String splitSymbols;
			// 解析模板实现类型
			int analysisTemplateImplType;
			rowInd = 1;
			// 开始读取过程
			while (null != (row = sheet.getRow(rowInd++))) {
				// 每行从新开始
				if (null == logic) {
					colInd = 0;
					// 对象ID
					cell = row.getCell(colInd); // 0
					if (null == cell) {
						// 不再存在必要的数据
						break;
					}
					logicId = (long) cell.getNumericCellValue();
					logic = ToolBeanExcelPool.getInstance().getLogicTool(logicId);
					if (null == logic) {
						throw new ConfigurationException("Not Exists LogicId[" + logicId + "] in 字段解析对象总表(row:" + (rowInd + 1) + ")...");
					}
					// 因为新对象，所以处理解析对象所需特殊属性
					// 模板类型
					cell = row.getCell(colInd += 3); // 3
					templateType = cell.getStringCellValue();
					// 解析模板实现类型
					cell = row.getCell(++colInd); // 4
					if (null != cell) {
						analysisTemplateImplType = (int) cell.getNumericCellValue();
					} else {
						analysisTemplateImplType = 0;
					}
					// 解析模版用拆分符
					cell = row.getCell(++colInd); // 5
					if (null != cell) {
						splitSymbols = cell.getStringCellValue();
					} else {
						// 针对一些不需要进行解析的模版类型
						splitSymbols = "";
					}
					// 属性ID
					cell = row.getCell(++colInd); // 6
					id = (int) cell.getNumericCellValue();
					// 放入解析用信息
					logic.putAnalysisInfo(templateType, splitSymbols, analysisTemplateImplType);
				} else if (null != (cell = row.getCell(0))) {
					colInd = 0;
					logicId = (int) cell.getNumericCellValue();
					logic = ToolBeanExcelPool.getInstance().getLogicTool(logicId);
					if (null == logic) {
						throw new ConfigurationException("Not Exists LogicId[" + logicId + "] in 字段解析对象总表(row:" + (rowInd + 1) + ")...");
					}
					// 因为新对象，所以处理解析对象所需特殊属性
					// 模板类型
					cell = row.getCell(colInd += 3); // 3
					templateType = cell.getStringCellValue();
					// 解析模板实现类型
					cell = row.getCell(++colInd); // 4
					if (null != cell) {
						analysisTemplateImplType = (int) cell.getNumericCellValue();
					} else {
						analysisTemplateImplType = 0;
					}
					// 解析模版用拆分符
					cell = row.getCell(++colInd); // 5
					splitSymbols = cell.getStringCellValue();
					// 属性ID
					cell = row.getCell(++colInd); // 6
					id = (int) cell.getNumericCellValue();
					// 放入解析用信息
					logic.putAnalysisInfo(templateType, splitSymbols, analysisTemplateImplType);
				} else {
					colInd = 6;
					// 属性ID
					cell = row.getCell(colInd); // 6
					if (null == cell) {
						// 分割行，需要换行，并进入到下一个对象
						logic = null;
						continue;
					}
					id = (int) cell.getNumericCellValue();
				}
				// 属性名
				cell = row.getCell(++colInd); // 7
				name = cell.getStringCellValue();
				// 中文说明
				cell = row.getCell(++colInd); // 8
				desc = cell.getStringCellValue();
				// 类型
				cell = row.getCell(++colInd); // 9
				type = cell.getStringCellValue();
				// 是否主数据列表数据关联键
				cell = row.getCell(++colInd); // 10
				if (null == cell) {
					isDataLinkKey = false;
				} else {
					isDataLinkKey = true;
				}
				// 创建时间
				cell = row.getCell(++colInd); // 11
				try {
					createDate = DateFormat.getShortDateShow(cell.getDateCellValue());
				} catch (final Exception e) {
					createDate = null;
					e.printStackTrace();
				}
				// 创建人
				cell = row.getCell(++colInd); // 12
				createAuthor = cell.getStringCellValue();
				// 最后修改时间
				cell = row.getCell(++colInd); // 13
				if (null == cell) {
					lastChangeDate = null;
				} else {
					lastChangeDate = DateFormat.getShortDateShow(cell.getDateCellValue());
				}
				// 最后修改人
				cell = row.getCell(++colInd); // 14
				if (null == cell) {
					lastChangeAuthor = null;
				} else {
					lastChangeAuthor = cell.getStringCellValue();
				}
				logic.addField(id, name, type, desc, isDataLinkKey, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
			}
		}
		{ // 操作2，基础属性
			// 对应的逻辑对象ID，针对解析类对象的处理
			long logicBeanId = 0;
			// 解析类逻辑对象
			LogicBeanTool analysisLogic;
			// 是否主数据列表数据关联键
			boolean isDataLinkKey;
			// 如果是数据关联属性是否可变
			boolean canChange;
			// 是否空闲字段
			boolean isIdle;
			// 所需工作表
			final Sheet sheet = wb.getSheet("内存对象关系—表详细");
			if (null == sheet) {
				throw new ConfigurationException("Not Exists target Sheet[内存对象关系—表详细]...");
			}
			rowInd = 1;
			// 开始读取过程
			while (null != (row = sheet.getRow(rowInd++))) {
				// System.out.println("内存对象关系—表详细 > " + rowInd);
				// 每行从新开始
				if (null == logic) {
					colInd = 0;
					// 对象ID
					cell = row.getCell(colInd); // 0
					if (null == cell) {
						// 不再存在必要的数据
						break;
					}
					logicId = (long) cell.getNumericCellValue();
					logic = ToolBeanExcelPool.getInstance().getLogicTool(logicId);
					if (null == logic) {
						throw new ConfigurationException("Not Exists LogicId[" + logicId + "] in 内存对象关系—表详细(row:" + (rowInd + 1) + ")...");
					}
					// 属性ID
					cell = row.getCell(colInd += 4); // 4
					id = (int) cell.getNumericCellValue();
				} else if (null != (cell = row.getCell(0))) {
					colInd = 0;
					logicId = (long) cell.getNumericCellValue();
					logic = ToolBeanExcelPool.getInstance().getLogicTool(logicId);
					if (null == logic) {
						throw new ConfigurationException("Not Exists LogicId[" + logicId + "] in 内存对象关系—表详细(row:" + (rowInd + 1) + ")...");
					}
					// 属性ID
					cell = row.getCell(colInd += 4); // 4
					id = (long) cell.getNumericCellValue();
				} else {
					colInd = 4;
					// 属性ID
					cell = row.getCell(colInd); // 4
					if (null == cell) {
						// 分割行，需要换行，并进入到下一个对象
						logic = null;
						continue;
					}
					id = (long) cell.getNumericCellValue();
				}
				// 属性名
				cell = row.getCell(++colInd); // 5
				name = cell.getStringCellValue();
				// 数据属性Id
				cell = row.getCell(colInd += 3); // 8
				if (null != cell) {
					dataFieldId = (int) cell.getNumericCellValue();
					// 直接跳
					colInd += 3;
					isIdle = false;
				} else {
					dataFieldId = 0;
					cell = row.getCell(colInd += 3); // 11
					if (null != cell) {
						logicBeanId = (int) cell.getNumericCellValue();
						isIdle = false;
					} else {
						isIdle = true;
					}
				}
				// 中文说明
				cell = row.getCell(colInd += 2); // 13
				desc = cell.getStringCellValue();
				// 类型
				cell = row.getCell(++colInd); // 14
				type = cell.getStringCellValue();
				// 是否主数据列表数据关联键
				cell = row.getCell(++colInd); // 15
				if (null == cell) {
					isDataLinkKey = false;
				} else {
					isDataLinkKey = true;
				}
				// 如果是数据关联属性是否可变
				cell = row.getCell(++colInd); // 16
				if (null == cell) {
					canChange = false;
				} else {
					canChange = true;
				}
				// 创建时间
				cell = row.getCell(++colInd); // 17
				try {
					createDate = DateFormat.getShortDateShow(cell.getDateCellValue());
				} catch (final Exception e) {
					createDate = null;
					e.printStackTrace();
				}
				// 创建人
				cell = row.getCell(++colInd); // 18
				createAuthor = cell.getStringCellValue();
				// 最后修改时间
				cell = row.getCell(++colInd); // 19
				if (null == cell) {
					lastChangeDate = null;
				} else {
					lastChangeDate = DateFormat.getShortDateShow(cell.getDateCellValue());
				}
				// 最后修改人
				cell = row.getCell(++colInd); // 20
				if (null == cell) {
					lastChangeAuthor = null;
				} else {
					lastChangeAuthor = cell.getStringCellValue();
				}
				if (dataFieldId != 0) {
					// 普通属性
					ToolBeanExcelPool.getInstance().putLogicFieldTool(logic.addField(id, name, dataFieldId, type, desc, isDataLinkKey, canChange, createAuthor, createDate, lastChangeAuthor, lastChangeDate));
				} else if (!isIdle) {
					analysisLogic = ToolBeanExcelPool.getInstance().getLogicTool(logicBeanId);
					if (null == analysisLogic) {
						throw new ConfigurationException("Not Exists Analysis LogicId[" + logicBeanId + "] in 内存对象关系—表详细(row:" + (rowInd + 1) + ")...");
					}
					// 解析对象属性
					ToolBeanExcelPool.getInstance().putLogicFieldTool(logic.addField(id, name, type, desc, analysisLogic, createAuthor, createDate, lastChangeAuthor, lastChangeDate));
				} else {
					// 仅放入到逻辑对象中
					logic.addIdleField(id, name, type, desc, createAuthor, createDate, lastChangeAuthor, lastChangeDate);
				}
			}
		}
		{ // 操作3，结构属性
			// 所需工作表
			final Sheet sheet = wb.getSheet("内存数据树状总表");
			if (null == sheet) {
				throw new ConfigurationException("Not Exists target Sheet[内存数据树状总表]...");
			}
			// 条件内容
			String condition;
			long parentId;
			LogicBeanTool parent;
			// 所属上级的属性
			long parentFieldId = 0;
			// 对象列表类型，针对，Map，List，MapList
			int objListType;
			// 键索引，针对Map或MapList的属性类型
			long keyFieldId;
			// 加载时机，实际是针对目标数据对象主列表的
			String loadingOpportunity;
			rowInd = 0;
			// 开始读取过程
			while (null != (row = sheet.getRow(rowInd += 2))) {
				colInd = 2;
				// 对象ID
				cell = row.getCell(colInd); // 2
				if (null == cell) {
					// System.out.println("cell null > " + row.getFirstCellNum());
					break;
				}
				logicId = (long) cell.getNumericCellValue();
				logic = ToolBeanExcelPool.getInstance().getLogicTool(logicId);
				if (null == logic) {
					throw new ConfigurationException("Not Exists LogicId[" + logicId + "] in 内存数据树状总表(row:" + (rowInd + 1) + ")...");
				}
				// 相关上层对象ID
				cell = row.getCell(colInd += 5); // 7
				parentId = (long) cell.getNumericCellValue();
				parent = ToolBeanExcelPool.getInstance().getLogicTool(parentId);
				if (null == parent) {
					// 此情况不关联其他的操作，直接下一条
					// 暂且任务此情况对应，根节点对象，设置为系统初始化时自动启动
					logic.setAutomaticLoading();
					continue;
				}
				// 属性/变量名
				cell = row.getCell(colInd -= 3); // 4
				name = cell.getStringCellValue();
				// 属性/变量说明
				cell = row.getCell(++colInd); // 5
				desc = cell.getStringCellValue();
				// 对象关联条件
				cell = row.getCell(++colInd); // 6
				if (null != cell) {
					condition = cell.getStringCellValue();
				} else {
					condition = "";
				}
				// 所在上层的属性ID
				cell = row.getCell(colInd += 2); // 8
				parentFieldId = (long) cell.getNumericCellValue();
				// 所在上层对象属性类型
				cell = row.getCell(++colInd); // 9
				type = cell.getStringCellValue();
				// 对象列表类型，针对，Map，List，MapList
				cell = row.getCell(++colInd); // 10
				if (null == cell) {
					objListType = 0;
				} else {
					objListType = (int) cell.getNumericCellValue();
				}
				// 键索引
				cell = row.getCell(++colInd); // 11
				if (null == cell) {
					keyFieldId = 0;
				} else {
					keyFieldId = (long) cell.getNumericCellValue();
				}
				// 加载时机
				cell = row.getCell(colInd += 2); // 13
				loadingOpportunity = cell.getStringCellValue();
				// 创建时间
				cell = row.getCell(++colInd); // 14
				createDate = DateFormat.getShortDateShow(cell.getDateCellValue());
				// 创建人
				cell = row.getCell(++colInd); // 15
				createAuthor = cell.getStringCellValue();
				// 最后修改时间
				cell = row.getCell(++colInd); // 16
				if (null == cell) {
					lastChangeDate = null;
				} else {
					lastChangeDate = DateFormat.getShortDateShow(cell.getDateCellValue());
				}
				// 最后修改人
				cell = row.getCell(++colInd); // 17
				if (null == cell) {
					lastChangeAuthor = null;
				} else {
					lastChangeAuthor = cell.getStringCellValue();
				}
				ToolBeanExcelPool.getInstance().putLogicFieldTool(parent.addField(parentFieldId, name, condition, type, keyFieldId, desc, logic, objListType, loadingOpportunity, createAuthor, createDate, lastChangeAuthor, lastChangeDate));
				// 设置对象加载时机
				logic.setLoadOppo(loadingOpportunity);
			}
		}
	}
}
