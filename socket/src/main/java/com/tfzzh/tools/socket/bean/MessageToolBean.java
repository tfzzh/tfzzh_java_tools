/**
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午7:58:57
 */
package com.tfzzh.tools.socket.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.tfzzh.exception.ConfigurationException;
import com.tfzzh.tools.socket.tools.FieldObjectEnum;
import com.tfzzh.tools.socket.tools.FieldTypeEnum;
import com.tfzzh.tools.socket.tools.MessageTypeEnum;
import com.tfzzh.tools.socket.tools.RequestXMLTool;
import com.tfzzh.tools.socket.tools.ScopeType;
import com.tfzzh.tools.socket.tools.SocketTemplateConstants;
import com.tfzzh.tools.socket.tools.ValidateTypeEnum;

/**
 * 请求消息工具对象
 * 
 * @author Weijie Xu
 * @dateTime 2014-3-21 下午7:58:57
 */
public class MessageToolBean {

	/**
	 * 自身ID
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:01:24
	 */
	private final int id;

	/**
	 * 父类ID
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午2:35:43
	 */
	private final int parentId;

	/**
	 * 父类对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月11日 下午1:35:18
	 */
	private MessageToolBean parent = null;

	/**
	 * 与之对应的客户端用消息对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月7日 下午3:35:12
	 */
	private final MessageToolBean correspondenceClient;

	/**
	 * 是否与父类在同一项目
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月11日 下午1:37:19
	 */
	private boolean sameScope = true;

	/**
	 * 名字
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午1:32:46
	 */
	private final String name;

	/**
	 * 项目用名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午1:32:48
	 */
	private final String proName;

	/**
	 * 说明
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:01:25
	 */
	private final String desc;

	/**
	 * 作用域类型
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午5:05:43
	 */
	private final ScopeType scope;

	/**
	 * 消息类型
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月27日_下午8:23:32
	 */
	private final MessageTypeEnum msgType;

	/**
	 * 字段集合
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:01:25
	 */
	private final Map<String, MessageParamToolBean> params = new LinkedHashMap<String, MessageParamToolBean>();

	/**
	 * 是否存在List
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午3:21:32
	 */
	private boolean hasList = false;

	/**
	 * 需要验证的情况
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午6:58:44
	 */
	private boolean needValidate = false;

	/**
	 * 是否需要数值型验证
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午8:10:50
	 */
	private boolean hasNumberValidate = false;

	/**
	 * 是否需要字符串型验证
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午8:10:48
	 */
	private boolean hasStringValidate = false;

	/**
	 * 是否为代理
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午4:13:09
	 */
	private final boolean isProxy;

	/**
	 * 是否对动态对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月1日 下午5:07:22
	 */
	private boolean isDynamic = false;

	/**
	 * 是否为去向客户端的代理，isProxy为真时有效
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月5日 下午6:43:32
	 */
	private final boolean toClient;

	/**
	 * 对象映射关系列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年10月22日 下午7:57:34
	 */
	private final static Map<String, String> OBJ_MAPPING = new HashMap<String, String>();
	/**
	 * 代码段，初始化一些数据用
	 */
	static {
		MessageToolBean.OBJ_MAPPING.put("String", "UTF");
		MessageToolBean.OBJ_MAPPING.put("Integer", "Int");
		MessageToolBean.OBJ_MAPPING.put("int", "Int");
		MessageToolBean.OBJ_MAPPING.put("Double", "Double");
		MessageToolBean.OBJ_MAPPING.put("double", "Double");
		MessageToolBean.OBJ_MAPPING.put("Float", "Float");
		MessageToolBean.OBJ_MAPPING.put("float", "Float");
		MessageToolBean.OBJ_MAPPING.put("Short", "Short");
		MessageToolBean.OBJ_MAPPING.put("short", "Short");
		MessageToolBean.OBJ_MAPPING.put("Long", "Long");
		MessageToolBean.OBJ_MAPPING.put("long", "Long");
		MessageToolBean.OBJ_MAPPING.put("Byte", "Byte");
		MessageToolBean.OBJ_MAPPING.put("byte", "Byte");
		MessageToolBean.OBJ_MAPPING.put("Boolean", "Boolean");
		MessageToolBean.OBJ_MAPPING.put("boolean", "Boolean");
		MessageToolBean.OBJ_MAPPING.put("Character", "Char");
		MessageToolBean.OBJ_MAPPING.put("char", "Char");
	}

	/**
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:01:21
	 * @param id 请求消息ID
	 * @param parentId 父类对象ID
	 * @param name 名字
	 * @param desc 说明
	 * @param scope 作用域
	 * @param msgType 消息类型
	 * @param isProxy 是否为代理
	 * @param toClient 是否为去向客户端的代理，isProxy为真时有效
	 * @param correspondenceClient 与之对应的客户端用消息对象
	 */
	public MessageToolBean(final int id, final int parentId, final String name, final String desc, final ScopeType scope, final MessageTypeEnum msgType, final boolean isProxy, final boolean toClient, final MessageToolBean correspondenceClient) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.proName = name.substring(0, 1).toUpperCase() + name.substring(1);
		this.desc = desc;
		this.scope = scope;
		this.msgType = msgType;
		this.isProxy = isProxy;
		this.toClient = toClient;
		this.correspondenceClient = correspondenceClient;
	}

	/**
	 * 在批量创建完成之后的初始化操作
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午1:59:45
	 */
	public void init() {
		for (final MessageParamToolBean param : this.params.values()) {
			param.init();
		}
		if (this.isHasParent()) {
			this.parent = RequestXMLTool.getInstance().getRequestMessage(MessageToolBean.this.scope, this.parentId);
			this.sameScope = this.parent.scope == this.scope;
		}
	}

	/**
	 * 得到自身ID
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:52:40
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 是否存在父级对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午2:38:14
	 * @return true，存在；<br />
	 *         false，不存在；<br />
	 */
	public boolean isHasParent() {
		return this.parentId != 0;
	}

	/**
	 * 得到父类信息
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月11日 下午1:34:49
	 * @return 父类对象
	 */
	public MessageToolBean getParent() {
		return this.parent;
	}

	/**
	 * 得到父类相关的属性列表
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月1日 下午5:12:49
	 * @return 父类相关的属性列表
	 */
	public List<MessageParamToolBean> getParentParams() {
		final List<MessageParamToolBean> params = new LinkedList<MessageParamToolBean>();
		MessageToolBean b = this.getParent();
		while (null != b) {
			params.addAll(0, b.params.values());
			b = b.getParent();
		}
		return params;
	}

	/**
	 * 父类是否存在代理
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午4:21:40
	 * @return true，存在代理；<br />
	 *         false，不存在代理；<br />
	 */
	public boolean isParentProxy() {
		return null == this.parent ? false : this.parent.isProxy();
	}

	/**
	 * 是否与父类在同一空间（包路径）下
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月11日 下午1:39:00
	 * @return true，同一空间（包路径）；<br />
	 *         false，不同一空间；<br />
	 */
	public boolean isInSameScope() {
		return this.sameScope;
	}

	/**
	 * 得到名字
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午1:32:42
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 得到项目用名，首字符大写
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-29 下午1:32:42
	 * @return the proName
	 */
	public String getProName() {
		return this.proName;
	}

	/**
	 * 得到说明
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月3日 下午3:42:08
	 * @return the desc
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * 得到所在作用域的包名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月8日 下午9:08:08
	 * @return 所在作用域的包名
	 */
	public String getScopePackageName() {
		final String name = this.scope.getScopeName();
		if ((null == name) || (name.length() == 0)) {
			return "";
		} else {
			return "." + this.scope.getScopeName();
		}
	}

	/**
	 * 得到所在作用域的路径名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 上午9:47:38
	 * @return 所在作用域的路径名
	 */
	public String getScopePathName() {
		final String name = this.scope.getScopeName();
		if ((null == name) || (name.length() == 0)) {
			return "";
		} else {
			return "/" + this.scope.getScopeName();
		}
	}

	/**
	 * 得到消息类型
	 * 
	 * @author 许纬杰
	 * @datetime 2016年4月27日_下午8:26:19
	 * @return the msgType
	 */
	public MessageTypeEnum getMsgType() {
		return this.msgType;
	}

	/**
	 * 放入一个属性
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:53:52
	 * @param name 名称
	 * @param type 类型
	 * @param obj 如果是对象的对象类型
	 * @param validate 验证方式
	 * @param valData 验证的数据
	 * @param desc 说明
	 */
	public void putNormalParam(final String name, final String type, final String obj, final String validate, final String valData, final String desc) {
		this.params.put(name, new MessageParamToolBean(name, type, obj, validate, valData, desc));
	}

	/**
	 * 放入一个属性，动态状态时
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年6月6日 上午10:43:14
	 * @param name 名称
	 * @param type 类型
	 * @param obj 如果是对象的对象类型
	 * @param validate 验证方式
	 * @param valData 验证的数据
	 * @param dynamicId 动态的参数ID
	 * @param desc 说明
	 */
	public void putDynamicParam(final String name, final String type, final String obj, final String validate, final String valData, final String dynamicId, final String desc) {
		this.params.put(name, new MessageParamToolBean(name, type, obj, validate, valData, dynamicId, desc));
	}

	/**
	 * 得到字段集合
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:52:53
	 * @return the params
	 */
	public Collection<MessageParamToolBean> getParams() {
		return this.params.values();
	}

	/**
	 * 得到与之对应的客户端用消息对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月7日 下午3:37:03
	 * @return the correspondenceClient
	 */
	public MessageToolBean getCorrespondenceClient() {
		return this.correspondenceClient;
	}

	/**
	 * 得到对象后缀名
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月4日 下午8:41:15
	 * @return 对象后缀名
	 */
	public String getSuffix() {
		return SocketTemplateConstants.CLASSBEAN_MESSAGE_SUFFIX;
	}

	/**
	 * 是否存在List
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月4日 下午3:22:12
	 * @return the List
	 */
	public boolean isHasList() {
		return this.hasList ? true : (null == this.parent ? false : this.parent.isHasList());
	}

	/**
	 * 是否存在对List的实现
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月11日 下午2:09:52
	 * @return ths ListImpl
	 */
	public boolean isHasListImpl() {
		return this.hasList;
	}

	/**
	 * 是否需要验证的情况<br />
	 * 无需向上验证，已经存在于目标代码中<br />
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午6:59:07
	 * @return the needValidate
	 */
	public boolean isNeedValidate() {
		return this.needValidate;
	}

	/**
	 * 是否需要数值型验证
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午8:11:37
	 * @return the hasNumberValidate
	 */
	public boolean isHasNumberValidate() {
		return this.hasNumberValidate;
	}

	/**
	 * 是否需要字符串型验证
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年4月9日 下午8:11:37
	 * @return the hasStringValidate
	 */
	public boolean isHasStringValidate() {
		return this.hasStringValidate;
	}

	/**
	 * 是否为代理
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月4日 下午4:14:49
	 * @return the isProxy
	 */
	public boolean isProxy() {
		return this.isProxy || this.isParentProxy();
	}

	/**
	 * 得到是否动态对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月1日 下午5:07:55
	 * @return the isDynamic
	 */
	public boolean isDynamic() {
		return this.isDynamic;
	}

	/**
	 * 我是动态对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年12月1日 下午5:07:55
	 */
	public void imDynamic() {
		this.isDynamic = true;
	}

	/**
	 * 得到是否为去向客户端的代理，isProxy为真时有效
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014年7月5日 下午6:45:03
	 * @return the toClient
	 */
	public boolean isToClient() {
		return this.toClient;
	}

	/**
	 * 字段工具对象
	 * 
	 * @author Weijie Xu
	 * @dateTime 2014-3-21 下午8:01:18
	 */
	public class MessageParamToolBean {

		/**
		 * 字段名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:11:18
		 */
		private final String name;

		/**
		 * 项目用名，字段名首字符大写
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月4日 上午10:31:35
		 */
		private final String proName;

		/**
		 * 说明
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:11:20
		 */
		private final String desc;

		/**
		 * 字段类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:11:22
		 */
		private final FieldTypeEnum type;

		/**
		 * 对象的内容
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:26:46
		 */
		private String objContent;

		/**
		 * 相关的对象类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:11:25
		 */
		private FieldObjectEnum objType;

		/**
		 * List对象，记录列表长度用字段类型，一般为有限的几种，默认为Integer
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年10月22日 下午8:22:29
		 */
		private FieldTypeEnum listCountType;

		/**
		 * 对象对应的请求消息对象
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:26:55
		 */
		private MessageToolBean obj;

		/**
		 * 动态用ID
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年6月6日 上午10:38:28
		 */
		private final Integer dynamicId;

		/**
		 * 验证类型及相关数据结合
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:11:26
		 */
		private final Map<ValidateTypeEnum, String[]> validates;

		/**
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:36:20
		 * @param name 名称
		 * @param type 类型
		 * @param obj 如果是对象的对象类型
		 * @param validate 验证方式
		 * @param valData 验证的数据
		 * @param desc 说明
		 */
		private MessageParamToolBean(final String name, final String type, final String obj, final String validate, final String valData, final String desc) {
			this.name = name;
			this.proName = name.substring(0, 1).toUpperCase() + name.substring(1);
			this.desc = desc;
			this.type = FieldTypeEnum.getType(type);
			this.objContent = obj;
			this.validates = this.analyticalValidate(validate, valData);
			if (this.validates.size() != 0) {
				MessageToolBean.this.needValidate = true;
			}
			this.dynamicId = null;
		}

		/**
		 * @author Weijie Xu
		 * @dateTime 2014年6月6日 上午10:41:27
		 * @param name 名称
		 * @param type 类型
		 * @param obj 如果是对象的对象类型
		 * @param validate 验证方式
		 * @param valData 验证的数据
		 * @param dynamicId 动态属性用ID
		 * @param desc 说明
		 */
		private MessageParamToolBean(final String name, final String type, final String obj, final String validate, final String valData, final String dynamicId, final String desc) {
			this.name = name;
			this.proName = name.substring(0, 1).toUpperCase() + name.substring(1);
			this.desc = desc;
			this.type = FieldTypeEnum.getType(type);
			this.objContent = obj;
			this.validates = this.analyticalValidate(validate, valData);
			if (this.validates.size() != 0) {
				MessageToolBean.this.needValidate = true;
			}
			this.dynamicId = Integer.parseInt(dynamicId);
		}

		/**
		 * 解析验证消息
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:36:07
		 * @param validate 验证的方式
		 * @param valData 验证的数据
		 * @return 实际用到的列表
		 */
		private Map<ValidateTypeEnum, String[]> analyticalValidate(final String validate, final String valData) {
			final Map<ValidateTypeEnum, String[]> map = new LinkedHashMap<ValidateTypeEnum, String[]>();
			if ((null == validate) || (validate.length() == 0) || (null == valData) || (valData.length() == 0)) {
				return map;
			}
			final String[] vals = validate.split("[|]");
			final String[] valDatas = valData.split("[|]");
			String[] cons;
			String[] datas;
			int ind;
			ValidateTypeEnum type;
			for (int i = 0; i < vals.length; i++) {
				if (vals[i].length() > 0) {
					cons = valDatas[i].split("[,]");
					try {
						type = ValidateTypeEnum.getType(Integer.parseInt(vals[0]));
						if (type.isNumberValidate()) {
							MessageToolBean.this.hasNumberValidate = true;
						}
						if (type.isStringValidate()) {
							MessageToolBean.this.hasStringValidate = true;
						}
					} catch (final RuntimeException e) {
						throw new ConfigurationException("Validate Type: " + vals[0] + " is not Exists. In " + MessageToolBean.this.name + ">" + this.name);
					}
					datas = new String[cons.length];
					for (ind = datas.length - 1; ind >= 0; ind--) {
						datas[ind] = type.getParamContent(this.type, cons[ind]);
					}
					map.put(type, datas);
				}
			}
			return map;
		}

		/**
		 * 初始化工作
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月4日 下午2:14:59
		 */
		private void init() {
			if (null != this.objContent) {
				final String[] oc = this.objContent.split("[|]");
				this.objType = FieldObjectEnum.getType(oc[0]);
				if ((oc.length == 2) || (oc.length == 3)) {
					if (this.objType.isNeedSizeType()) {
						if (this.objType.isList() && !MessageToolBean.this.hasList) {
							MessageToolBean.this.hasList = true;
						}
						if (oc.length == 3) {
							this.listCountType = FieldTypeEnum.getType(oc[2]);
							if (null == this.listCountType) {
								this.listCountType = FieldTypeEnum.Int;
							}
						} else {
							this.listCountType = FieldTypeEnum.Int;
						}
					}
					try {
						this.obj = RequestXMLTool.getInstance().getRequestMessage(MessageToolBean.this.scope, Integer.parseInt(oc[1]));
					} catch (final Exception e) {
						this.obj = new MessageToolBean(0, 0, oc[1], MessageToolBean.OBJ_MAPPING.get(oc[1]), null, MessageTypeEnum.Socket, false, false, null);
					}
					this.objContent = null;
				}
			}
		}

		/**
		 * 得到字段名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:51:44
		 * @return the name
		 */
		public String getName() {
			return this.name;
		}

		/**
		 * 得到项目用名，字段名首字符大写
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月4日 上午10:32:51
		 * @return the proName
		 */
		public String getProName() {
			return this.proName;
		}

		/**
		 * 得到说明
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:51:44
		 * @return the desc
		 */
		public String getDesc() {
			return this.desc;
		}

		/**
		 * 得到字段类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:51:44
		 * @return the type
		 */
		public FieldTypeEnum getType() {
			return this.type;
		}

		/**
		 * 得到类型名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-29 下午4:52:52
		 * @return 类型名
		 */
		public String getTypeName() {
			if (null != this.objType) {
				return this.objType.getTypeName(this.obj);
			} else {
				return this.type.getTypeName();
			}
		}

		/**
		 * 得到显示用信息
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月4日 下午3:44:19
		 * @return 显示用信息
		 */
		public String getTypeObjectName() {
			if (null != this.objType) {
				return this.objType.getObjectTypeName();
			} else {
				return this.type.getObjectTypeName();
			}
		}

		/**
		 * 得到完整的类型对象名
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年6月18日 下午4:27:51
		 * @return 完整的类型对象名
		 */
		public String getCompleteTypeName() {
			if (null != this.objType) {
				return this.objType.getCompleteTypeName(this.obj);
			} else {
				return this.type.getCompleteTypeName();
			}
		}

		/**
		 * 得到对象的内容
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:51:44
		 * @return the objContent
		 */
		public String getObjContent() {
			return this.objContent;
		}

		/**
		 * 得到相关的对象类型
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:51:44
		 * @return the objType
		 */
		public FieldObjectEnum getObjType() {
			return this.objType;
		}

		/**
		 * 得到List对象，记录列表长度用字段类型，一般为有限的几种，默认为Integer
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年10月22日 下午8:28:32
		 * @return the listCountType
		 */
		public FieldTypeEnum getListCountType() {
			return this.listCountType;
		}

		/**
		 * 得到对象对应的请求消息对象
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:51:44
		 * @return the obj
		 */
		public MessageToolBean getObj() {
			if (null != this.obj) {
				return this.obj;
			} else {
				return null;
			}
		}

		/**
		 * 得到动态用ID
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年6月6日 上午10:42:18
		 * @return the dynamicId
		 */
		public Integer getDynamicId() {
			return this.dynamicId;
		}

		/**
		 * 是否需要验证
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014年4月9日 下午7:03:46
		 * @return true，需要；<br />
		 *         false，不需要；<br />
		 */
		public boolean isNeedValidate() {
			return this.validates.size() != 0;
		}

		/**
		 * 得到验证类型及相关数据结合
		 * 
		 * @author Weijie Xu
		 * @dateTime 2014-3-21 下午8:51:44
		 * @return the validates
		 */
		public Set<Entry<ValidateTypeEnum, String[]>> getValidates() {
			return this.validates.entrySet();
		}
	}
}
