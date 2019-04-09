/**
 * @author XuWeijie
 * @dateTime May 1, 2010 7:33:58 PM
 */
package com.tfzzh.tools.data.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.tfzzh.log.CoreLog;
import com.tfzzh.tools.Constants;
import com.tfzzh.tools.DateFormat;
import com.tfzzh.tools.FileTools;
import com.tfzzh.tools.data.tools.ConfigDataTemplateConstants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author XuWeijie
 * @dateTime May 1, 2010 7:33:58 PM
 */
public abstract class BaseTemplate {

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 12:52:35 PM
	 */
	protected static Configuration cfg = BaseTemplate.getConfiguration();

	/**
	 * 模版中所需要的参数集合
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 1:09:19 PM
	 */
	protected Map<String, Object> params = new HashMap<>();

	/**
	 * 新生成的文件
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年9月19日 上午10:51:08
	 */
	private File newFile = null;

	/**
	 * 原来的文件
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年9月19日 上午10:51:09
	 */
	private File oldFile = null;

	/**
	 * 变更文件总量
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月26日 下午7:17:09
	 */
	private static int changeCount = 0;

	/**
	 * 创建文件总量
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月26日 下午7:17:10
	 */
	private static int createCount = 0;

	/**
	 * 得到模版消息
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 12:54:22 PM
	 * @return 模版基础消息
	 */
	private static Configuration getConfiguration() { // 一次性模板读取
		@SuppressWarnings("deprecation")
		final Configuration cfg = new Configuration();
		// 读取模版
		cfg.setClassForTemplateLoading(BaseTemplate.class, ConfigDataTemplateConstants.TEMPLATE_PATH);
		cfg.setDefaultEncoding(ConfigDataTemplateConstants.FILE_CODE);
		cfg.setOutputEncoding(Constants.SYSTEM_CODE);
		cfg.setLocale(Locale.CHINA);
		return cfg;
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:20:08 PM
	 */
	protected BaseTemplate() {
		this(null);
	}

	/**
	 * @author Weijie Xu
	 * @dateTime Aug 13, 2014 9:09:01 PM
	 * @param obj 参数对象
	 */
	protected BaseTemplate(final Object obj) {
		this(ConfigDataTemplateConstants.PROJECT_TEMPLATE_PATH, obj);
	}

	/**
	 * @author XuWeijie
	 * @dateTime May 3, 2010 3:47:32 PM
	 * @param projectPath 项目路径
	 * @param obj 参数对象
	 */
	protected BaseTemplate(final String projectPath, final Object obj) {
		this.setOverAllParam(obj);
		// 设置参数
		this.setParams();
		// 进行写文件操作
		this.writeFile(projectPath);
	}

	/**
	 * 设置全局用参数
	 * 
	 * @author XuWeijie
	 * @dateTime Aug 20, 2010 2:36:49 PM
	 * @param obj 参数对象
	 */
	protected void setOverAllParam(final Object obj) {
	}

	/**
	 * 设置参数
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 12:54:56 PM
	 */
	protected void setParams() {
		this.params.put("author", ConfigDataTemplateConstants.SYSTEM_AUTHOR);
		this.params.put("dateTime", DateFormat.getShortDateShow(new Date()));
		this.params.put("projectName", ConfigDataTemplateConstants.PROJECT_NAME);
	}

	/**
	 * 写文件操作
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 1:01:31 PM
	 * @param projectPath 项目路径
	 */
	private void writeFile(final String projectPath) {
		Template t;
		try {
			t = BaseTemplate.cfg.getTemplate(this.getTemplateName());
			final Writer out = this.getWriterFile(projectPath);
			// 从模版中生成文件
			t.process(this.params, out);
			out.close();
			if (null != this.oldFile) {
				// 需要进行比较的情况
				if (this.fileCompare()) {
					// 两个文件相同，不再创建，且结束
					// this.newFile.delete();
					// System.out.println("old File:" + this.oldFile.getPath());
					// out.close();
					if (this.newFile.exists()) {
						this.newFile.delete();
					}
					return;
				} else {
					// 两文件不同，用新的替换旧的
					// final String name = this.oldFile.getName();
					// 首先删除旧的，然后给新的改名字
					if (this.oldFile.exists()) {
						this.oldFile.delete();
					}
					// // 写入新文件
					// out.flush();
					// out.close();
					// 然后改名字
					this.newFile.renameTo(this.oldFile);
					// System.out.println("new File:" + this.newFile.getPath());
					// this.newFile.createNewFile();
					// this.newFile.delete();
					// System.out.println("\thas Change " + this.newFile.getName() + "...");
					BaseTemplate.changeCount++;
					if (CoreLog.getInstance().debugEnabled(this.getClass())) {
						CoreLog.getInstance().debug(this.getClass(), ">>Change File[", Integer.toString(++BaseTemplate.changeCount), "]: ", this.oldFile.getName(), " >> ", this.oldFile.getCanonicalPath(), "...");
					}
					return;
				}
			} else {
				// 因为不存在原始文件
				BaseTemplate.createCount++;
				// 写入新文件
				if (CoreLog.getInstance().debugEnabled(this.getClass())) {
					CoreLog.getInstance().debug(this.getClass(), "++Create File[", Integer.toString(++BaseTemplate.createCount), "]: ", this.newFile.getName(), " >> ", this.newFile.getCanonicalPath());
				}
			}
		} catch (final TemplateException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 进行文件比较
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年9月19日 上午10:59:50
	 * @return true，两文件相同；<br />
	 *         false，两文件不同；<br />
	 */
	private boolean fileCompare() {
		return FileTools.compareFile(this.newFile, this.oldFile);
	}

	/**
	 * 得到所需要写入内容的文件信息
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 2:23:52 PM
	 * @param projectPath 项目路径
	 * @return 文件消息
	 */
	private Writer getWriterFile(final String projectPath) {
		// 得到文件所在文件夹
		String filePath = projectPath + this.getOutFilePath();
		filePath = FileTools.purifyFilePath(filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			// 创建文件路径
			file.mkdirs();
		}
		// 得到文件名称
		final String fileName = this.getOutFileName();
		// 得到文件消息
		file = new File(filePath + File.separator + fileName);
		this.params.put("tmpFilePostfix", "");
		if (this.isNeedTemplate()) {
			// 如果已经存在目标文件则生成名称带有tmp的相关文件
			if (file.exists()) {
				// 文件已经存在
				file = new File(filePath + File.separator + this.getTmpFilePostfix(fileName));
				this.params.put("tmpFilePostfix", this.getTmpJavaNamePostfix());
			}
		} else {
			if (file.exists()) {
				this.oldFile = file;
				// 创建一新文件
				file = new File(filePath + File.separator + fileName + ".new");
				// file = new File(filePath + File.separator + fileName);
				this.newFile = file;
			} else {
				this.newFile = file;
			}
		}
		try {
			if (CoreLog.getInstance().debugEnabled(this.getClass())) {
				CoreLog.getInstance().debug(this.getClass(), "Create File: ", filePath, "/", fileName);
			}
			// 在这里进行文件创建
			return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Constants.SYSTEM_CODE));
			// return new BufferedWriter(osw);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到模版名称
	 * 
	 * @author XuWeijie
	 * @dateTime May 9, 2010 4:44:42 PM
	 * @return 模版名称
	 */
	protected String getTemplateName() {
		final String name = this.getClass().getSimpleName();
		return name.substring(0, name.length() - 8) + ".ftl";
	}

	/**
	 * 得到待输出的文件所在路径
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 1:10:25 PM
	 * @return 待输出的文件所在路径
	 */
	protected abstract String getOutFilePath();

	/**
	 * 得到待输出的文件名称，不含路径
	 * 
	 * @author XuWeijie
	 * @dateTime May 3, 2010 2:22:22 PM
	 * @return 待输出的文件名称，不含路径
	 */
	protected abstract String getOutFileName();

	/**
	 * 是否需要模板
	 * 
	 * @author XuWeijie
	 * @dateTime Aug 28, 2010 1:35:40 PM
	 * @return true，如果已经存在目标文件则生成名称带有tmp的相关文件；<br />
	 *         false，如果已经存在目标文件直接覆盖；<br />
	 */
	protected boolean isNeedTemplate() {
		return false;
	}

	/**
	 * 得到临时文件名后缀<br />
	 * 在"isNeedTemplate"为true时有效<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午10:38:58
	 * @param fileName 文件名称，多半是带有后缀的
	 * @return 临时文件名后缀
	 */
	protected String getTmpFilePostfix(final String fileName) {
		return fileName + ".tmp";
	}

	/**
	 * 得到临时状态下，Java名称的后缀名<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月30日 下午10:45:48
	 * @return Java名称的后缀名
	 */
	protected String getTmpJavaNamePostfix() {
		return "";
	}

	/**
	 * 得到变更文件总量
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月26日 下午7:18:56
	 * @return the changeCount
	 */
	public static int getChangeCount() {
		return BaseTemplate.changeCount;
	}

	/**
	 * 得到创建文件总量
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月26日 下午7:18:56
	 * @return the createCount
	 */
	public static int getCreateCount() {
		return BaseTemplate.createCount;
	}
}
