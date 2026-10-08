# 数据表结构 JSON 规范

> 版本：v1.0
> 用途：根据本规范，可为任意项目生成对应的表结构 JSON 文件

---

## 1. 整体结构

```json
{
  "tables": [
    {
      "id": 1,
      "name": "TableName",
      "databaseName": "db_name",
      "tableName": "table_name",
      "description": "表说明",
      "srcPath": "module",
      "functionName": "feature",
      "createAuthor": "author",
      "createDate": "yyyy-MM-dd",
      "lastChangeAuthor": "author",
      "lastChangeDate": "yyyy-MM-dd",
      "fields": [ ... ],
      "data": { ... }
    }
  ]
}
```

| 字段 | 必填 | 类型 | 说明 |
|------|------|------|------|
| `tables` | 是 | Array | 表定义数组，每个元素是一个表对象 |

---

## 2. 表定义结构

| 字段 | 必填 | 类型 | 说明 |
|------|------|------|------|
| `id` | 是 | int | 表唯一标识，全局不重复 |
| `name` | 是 | String | 表对象名（Java 实体类名），如 `UserInfo`、`BaseEntity` |
| `databaseName` | 是 | String | 数据库名，如 `test_db` |
| `tableName` | 是 | String | 数据库表名（下划线命名），如 `user_info` |
| `description` | 是 | String | 表说明 |
| `srcPath` | 是 | String | 源码模块名/包名，用于文件路径生成，如 `user`、`config` |
| `functionName` | 是 | String | 功能包名，用于包路径生成，如 `user`、`tieba` |
| `createAuthor` | 是 | String | 创建人，固定填 `tfzzh` |
| `createDate` | 是 | String | 创建日期，格式 `yyyy-MM-dd`，如 `2026-06-29` |
| `lastChangeAuthor` | 是 | String | 最后修改人，固定填 `tfzzh` |
| `lastChangeDate` | 是 | String | 最后修改日期，格式 `yyyy-MM-dd` |
| `fields` | 是 | Array | 字段定义数组 |
| `data` | 否 | Object | 表初始化数据，见第 4 节 |

---

## 3. 字段定义结构

每个字段是一个 JSON 对象，结构如下：

```json
{
  "id": 1001,
  "name": "Id",
  "dataFieldName": "id",
  "fieldType": "bigint",
  "length": "20",
  "description": "自增ID",
  "isKey": 1,
  "isUuid": false,
  "isIncrementKey": true,
  "isUnique": 0,
  "isUnsigned": true,
  "canNull": false,
  "defaultValue": "",
  "createAuthor": "tfzzh",
  "createDate": "2026-06-29",
  "lastChangeAuthor": "tfzzh",
  "lastChangeDate": "2026-06-29"
}
```

| 字段 | 必填 | 类型 | 说明 |
|------|------|------|------|
| `id` | 是 | int | 字段唯一标识，全局不重复 |
| `name` | 是 | String | 字段名（Java 属性名），如 `userName` |
| `dataFieldName` | 是 | String | 数据库字段名（下划线命名），如 `user_name` |
| `fieldType` | 是 | String | 数据库字段类型，见第 5 节类型映射 |
| `length` | 条件 | String | 字段长度。`text`/`blob`/`datetime` 等无长度类型必须为空或省略 |
| `description` | 是 | String | 字段说明 |
| `isKey` | 否 | int/null | 主键顺序。`null` 表示非主键；正数为升序；负数为降序。如 `1` |
| `isUuid` | 否 | boolean | 是否 UUID，默认 `false` |
| `isIncrementKey` | 否 | boolean | 是否自增，默认 `false` |
| `isUnique` | 否 | int | 是否唯一。`0` 表示不唯一；非 `0` 表示唯一。如 `1` |
| `isUnsigned` | 否 | boolean | 是否无符号，默认 `false` |
| `canNull` | 否 | boolean | 是否可空，默认 `false` |
| `defaultValue` | 否 | String | 默认值 |
| `createAuthor` | 是 | String | 创建人，固定 `tfzzh` |
| `createDate` | 是 | String | 创建日期，格式 `yyyy-MM-dd` |
| `lastChangeAuthor` | 是 | String | 最后修改人，固定 `tfzzh` |
| `lastChangeDate` | 是 | String | 最后修改日期，格式 `yyyy-MM-dd` |

### 3.1 字段 ID 规则

字段 ID 必须全局唯一，建议按表 ID 分配区间：

| 表 ID | 字段 ID 范围 | 示例 |
|-------|-------------|------|
| 1 | 1001 ~ 1999 | `Id` = 1001, `UserCode` = 1, ... |
| 2 | 2001 ~ 2999 | `Id` = 2001, `OpenId` = 13, ... |
| 3 | 3001 ~ 3999 | `Id` = 3001, `AdminUuid` = 20, ... |

**每个表的第一个字段必须是 `Id`（自增 ID），字段 ID = 表 ID × 1000 + 1**

### 3.2 主键规则

1. 非日志表：自增 `Id` 字段不是主键，主键是业务字段（如 `UserCode`、`PostCode`）
2. 日志表：如果没有其他主键字段，则 `Id` 是主键（`isKey: 1`）
3. 如果日志表已有其他主键（如 `PostCode` + `ReplyCode` 复合主键），则 `Id` 不加 `isKey`

---

## 4. 初始化数据（可选）

```json
{
  "data": {
    "columns": ["userName", "password", "email", "status"],
    "rows": [
      ["admin", "admin123", "admin@test.com", "1"],
      ["test", "test123", "test@test.com", "1"]
    ]
  }
}
```

| 字段 | 说明 |
|------|------|
| `columns` | 字段名列表（对应 `fields` 中的 `name`），不是 `dataFieldName` |
| `rows` | 二维字符串数组，每行与 `columns` 一一对应 |

**注意**：`data` 是可选的。如果不需要预置数据，可以省略该字段。

---

## 5. 类型映射规范

### 5.1 数据库类型 → Java 类型映射

| 数据库类型 | Java 类型 | 长度规则 | 典型场景 | 说明 |
|-----------|-----------|----------|---------|------|
| `char` | `String` | 定长 | 固定编码、状态码、日期格式 | 必须定长，不定长用 `varchar` |
| `varchar` | `String` | 最大长度 | 普通文本、描述、名称 | 不定长字符串 |
| `text` | `String` | **无** | 中等长度文本 | 最大 64KB |
| `mediumtext` | `String` | **无** | 较长文本 | 最大 16MB |
| `longtext` | `String` | **无** | 超长文本（如 JSON 列表） | 最大 4GB |
| `int` | `Integer` | `11` | 普通整数 | 带符号 |
| `tinyint` | `Short` | 默认不写 | 小范围整数（状态码 0/1） | 范围 -128 ~ 127 |
| `bigint` | `Long` | `20` | 大整数、时间戳 | 64位整数 |
| `float` | `Float` | `10,7` | 小数 | 单精度浮点数 |
| `double` | `Double` | `16,14` | 高精度小数 | 双精度浮点数 |
| `bit` | `Integer` | 1~64 | 位字段 | 按位数存储 |
| `blob` | `InputStream` | **无** | 二进制数据（小） | 最大 64KB |
| `mediumblob` | `InputStream` | **无** | 中等二进制数据 | 最大 16MB |
| `longblob` | `InputStream` | **无** | 大二进制数据 | 最大 4GB |

### 5.2 已废弃类型（禁止使用）

| 数据库类型 | 原因 | 替代方案 |
|-----------|------|---------|
| `datetime` | 已废弃 | 用 `bigint`（Long 时间戳）或 `char`（格式化字符串） |
| `date` | 已废弃 | 同上 |
| `time` | 已废弃 | 同上 |

### 5.3 `char` 使用规则（定长字符串）

`char` 必须满足**定长**条件。以下场景使用 `char`：

| 场景 | 长度 | 例子 | 说明 |
|------|------|------|------|
| 状态码/类型码 | 1~4 | `Gender`(1), `UserType`(1) | 枚举值，固定长度 |
| 固定编码 | 4~16 | `Dcode`(4), `UserCode`(12) | 系统生成或分配的唯一编码 |
| 日期格式（String） | 7, 8, 10 | `LastLoginDay`(10), `CreateMonthDate`(7) | 年月(7)、年月日(8/10) |

**禁止**将以下场景用 `char`：
- 手机号码（`Phone`）→ 不定长，应使用 `varchar(15)`
- 用户输入的自由文本 → 应使用 `varchar` 或 `text`

### 5.4 日期/时间字段规则

时间字段统一使用以下两种方案：

| 方案 | 数据库类型 | Java 类型 | 场景 | 说明 |
|------|-----------|-----------|------|------|
| **时间戳** | `bigint` | `Long` | 存储时间戳（毫秒/秒） | 推荐，无时区问题，便于计算 |
| **格式化字符串** | `char` | `String` | 固定格式的日期字符串 | 需要展示格式时 |

**格式化字符串长度对应表：**

| 格式 | 长度 | 数据库类型 | Java 类型 | 示例 |
|------|------|-----------|-----------|------|
| `yyyy-MM` | 7 | `char` | `String` | `2026-06` |
| `yyyy-MM-dd` | 10 | `char` | `String` | `2026-06-29` |
| `yyyy-MM-dd HH` | 13 | `char` | `String` | `2026-06-29 01` |
| `yyyy-MM-dd HH:mm` | 16 | `char` | `String` | `2026-06-29 01:17` |
| `yyyy-MM-dd HH:mm:ss` | 19 | `char` | `String` | `2026-06-29 01:17:30` |

---

## 6. 字段命名规则

### 6.1 命名后缀暗示类型

| 后缀 | 暗示类型 | 说明 |
|------|---------|------|
| `Code` | `char`（定长编码） | 系统编码、UUID、Key 等 |
| `Id` / `ID` | `int` 或 `bigint` | 主键 ID |
| `Status` / `Type` | `char(1)` | 状态/类型 |
| `Time` / `Date` | `bigint` 或 `char` | 时间戳或日期字符串 |
| `Num` / `Count` / `Times` | `int` | 计数 |
| `Str` | `varchar` 或 `char` | 字符串，需结合上下文判断 |
| `List`（+ `text`） | `longtext` | JSON 列表存储 |

### 6.2 字段名包含关键词

| 关键词 | 建议类型 | 说明 |
|--------|---------|------|
| `Phone` | `varchar(15)` | 手机号，不定长 |
| `Password` | `char(32)` | 密码的MD5 |
| `Email` | `varchar(128)` | 邮箱 |
| `Ip` / `IP` | `varchar(40)` | IP 地址（IPv6 更长） |
| `Url` / `URL` | `varchar(512)` | URL 地址 |
| `Avatar` / `Icon` | `varchar(256)` | 头像/图标地址 |
| `Content` / `Text` | `text` 或 `longtext` | 内容文本 |
| `Desc` / `Description` | `varchar(256)` | 描述 |
| `Version` | `varchar(16)` | 版本号 |
| `Longitude` / `Latitude` | `float(10,7)` | 经纬度 |

---

## 7. 完整示例

### 7.1 非日志表（User 表）

```json
{
  "tables": [
    {
      "id": 1,
      "name": "User",
      "databaseName": "gongde",
      "tableName": "user",
      "description": "用户表",
      "srcPath": "user",
      "functionName": "user",
      "createAuthor": "tfzzh",
      "createDate": "2026-06-29",
      "lastChangeAuthor": "tfzzh",
      "lastChangeDate": "2026-06-29",
      "fields": [
        {
          "id": 1001,
          "name": "Id",
          "dataFieldName": "id",
          "fieldType": "bigint",
          "length": "20",
          "description": "自增ID",
          "isIncrementKey": true,
          "isUnsigned": true,
          "canNull": false,
          "createAuthor": "tfzzh",
          "createDate": "2026-06-29",
          "lastChangeAuthor": "tfzzh",
          "lastChangeDate": "2026-06-29"
        },
        {
          "id": 1,
          "name": "UserCode",
          "dataFieldName": "user_code",
          "fieldType": "char",
          "length": "12",
          "description": "用户编码",
          "isKey": 1,
          "canNull": false,
          "createAuthor": "tfzzh",
          "createDate": "2026-06-29",
          "lastChangeAuthor": "tfzzh",
          "lastChangeDate": "2026-06-29"
        },
        {
          "id": 2,
          "name": "Phone",
          "dataFieldName": "phone",
          "fieldType": "varchar",
          "length": "15",
          "description": "手机号",
          "canNull": true,
          "createAuthor": "tfzzh",
          "createDate": "2026-06-29",
          "lastChangeAuthor": "tfzzh",
          "lastChangeDate": "2026-06-29"
        },
        {
          "id": 3,
          "name": "CreateTime",
          "dataFieldName": "create_time",
          "fieldType": "bigint",
          "length": "20",
          "description": "创建时间",
          "createAuthor": "tfzzh",
          "createDate": "2026-06-29",
          "lastChangeAuthor": "tfzzh",
          "lastChangeDate": "2026-06-29"
        }
      ],
      "data": {
        "columns": ["UserCode", "Phone", "CreateTime"],
        "rows": [
          ["U00000000001", "13800000000", "1709123456789"]
        ]
      }
    }
  ]
}
```

### 7.2 日志表（SystemDayLog）

```json
{
  "tables": [
    {
      "id": 7,
      "name": "SystemDayLog",
      "databaseName": "gongde",
      "tableName": "system_day_log",
      "description": "系统日志",
      "srcPath": "log",
      "functionName": "log",
      "createAuthor": "tfzzh",
      "createDate": "2026-06-29",
      "lastChangeAuthor": "tfzzh",
      "lastChangeDate": "2026-06-29",
      "fields": [
        {
          "id": 7001,
          "name": "Id",
          "dataFieldName": "id",
          "fieldType": "bigint",
          "length": "20",
          "description": "自增ID",
          "isKey": 1,
          "isIncrementKey": true,
          "isUnsigned": true,
          "canNull": false,
          "createAuthor": "tfzzh",
          "createDate": "2026-06-29",
          "lastChangeAuthor": "tfzzh",
          "lastChangeDate": "2026-06-29"
        },
        {
          "id": 44,
          "name": "LogDay",
          "dataFieldName": "log_day",
          "fieldType": "char",
          "length": "10",
          "description": "日志日期",
          "createAuthor": "tfzzh",
          "createDate": "2026-06-29",
          "lastChangeAuthor": "tfzzh",
          "lastChangeDate": "2026-06-29"
        },
        {
          "id": 45,
          "name": "LogType",
          "dataFieldName": "log_type",
          "fieldType": "char",
          "length": "1",
          "description": "日志类型",
          "createAuthor": "tfzzh",
          "createDate": "2026-06-29",
          "lastChangeAuthor": "tfzzh",
          "lastChangeDate": "2026-06-29"
        },
        {
          "id": 46,
          "name": "LogContent",
          "dataFieldName": "log_content",
          "fieldType": "longtext",
          "description": "日志内容",
          "createAuthor": "tfzzh",
          "createDate": "2026-06-29",
          "lastChangeAuthor": "tfzzh",
          "lastChangeDate": "2026-06-29"
        }
      ]
    }
  ]
}
```

---

## 8. 文件组织规范

生成 JSON 后，建议按以下方式组织文件：

```
json/
├── split/                    # 独立表 JSON 目录
│   ├── INDEX.md              # 表目录
│   ├── user_User.json        # 命名规则：functionName_tableName.json
│   ├── log_SystemDayLog.json
│   └── ...
└── tables.json               # 聚合文件（所有表的合并）
```

---

## 9. 使用流程

1. 根据项目需求，定义所有表（参考第 7 节示例）
2. 确保每个表有 `Id` 字段（第一个字段，bigint，自增）
3. 确保字段 ID 全局唯一
4. 按类型规范选择正确的数据库类型（第 5 节）
5. 生成独立 JSON 文件或聚合 JSON 文件
6. 使用 `AnhaoMakeJsonMain` 读取 JSON 生成代码

---

> 如有问题，请根据项目实际场景调整。本规范是通用模板，可根据需要裁剪。
