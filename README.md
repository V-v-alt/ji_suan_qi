<<<<<<< HEAD
# 网页版计算器 (Web Calculator)

基于 Spring Boot 构建的网页版计算器应用，支持用户管理、VIP会员系统和计算日志记录。

## 🎯 功能特性

### 计算器功能
- ✅ 支持两位数的加减乘除运算
- ✅ 使用 BigDecimal 处理浮点数精度问题
- ✅ 整数结果自动去除小数点
- ✅ 传统计算器布局，紫色系配色

### 用户系统
- ✅ 用户注册与登录
- ✅ BCrypt 密码加密存储
- ✅ 会话管理

### VIP会员系统
- ✅ 四种VIP类型：日卡、月卡、季卡、年卡
- ✅ 独立的VIP升级页面
- ✅ 手机号验证

### 权限控制
| 运算 | 使用条件 |
|------|----------|
| 加法 | 无需登录 |
| 减法 | 无需登录 |
| 乘法 | 需要登录 |
| 除法 | 需要登录且升级为VIP |

### 数据持久化
- ✅ 计算日志自动记录到数据库
- ✅ 用户信息持久化存储

## 🛠️ 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 17 | 编程语言 |
| Spring Boot | 3.2.0 | Web应用框架 |
| Spring Data JPA | 3.2.0 | 数据访问层 |
| Spring Security | 6.2.0 | 安全框架 |
| Thymeleaf | 3.1.2 | 模板引擎 |
| MySQL | 8.0+ | 数据库 |
| BCrypt | - | 密码加密 |

## 📁 项目结构

```
├── src/main/java/com/example/calculator/
│   ├── CalculatorApplication.java   # 启动类
│   ├── controller/                  # 控制器层
│   │   ├── CalculatorController.java # 计算器控制器
│   │   ├── UserController.java      # 用户控制器
│   │   └── VipController.java       # VIP控制器
│   ├── service/                     # 服务层
│   │   ├── CalculatorService.java   # 计算器服务
│   │   └── UserService.java         # 用户服务
│   ├── repository/                  # 数据访问层
│   │   ├── OperationLogRepository.java
│   │   └── UserRepository.java
│   ├── entity/                      # 实体类
│   │   ├── OperationLog.java        # 计算日志实体
│   │   ├── User.java                # 用户实体
│   │   └── VipType.java             # VIP类型枚举
│   └── config/                      # 配置类
│       └── SecurityConfig.java      # 安全配置
├── src/main/resources/
│   ├── application.properties       # 应用配置
│   └── templates/                   # Thymeleaf模板
│       ├── calculator.html          # 计算器页面
│       ├── login.html               # 登录页面
│       ├── register.html            # 注册页面
│       └── vip-upgrade.html         # VIP升级页面
└── pom.xml                          # Maven依赖
```

## 🚀 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 数据库配置

1. 创建数据库：
```sql
CREATE DATABASE calc_aa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'calc_aa'@'localhost' IDENTIFIED BY 'calc_aa';
GRANT ALL PRIVILEGES ON calc_aa.* TO 'calc_aa'@'localhost';
FLUSH PRIVILEGES;
```

2. 配置文件 `src/main/resources/application.properties`：
```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/calc_aa?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=calc_aa
spring.datasource.password=calc_aa
spring.jpa.hibernate.ddl-auto=update
```

### 启动方式

**开发模式运行：**
```bash
mvn spring-boot:run
```

**打包构建：**
```bash
mvn clean package -DskipTests
```

**运行打包后的 Jar：**
```bash
java -jar target/calculator-1.0.0.jar
```

### 访问地址

启动后访问：http://localhost:8080/

## 🔌 接口说明

### 计算器接口

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/` | 计算器首页 |
| POST | `/calculate` | 执行计算 |

**POST /calculate 参数：**
| 参数 | 类型 | 说明 |
|------|------|------|
| num1 | double | 第一个操作数 |
| num2 | double | 第二个操作数 |
| operator | string | 运算符 (+, -, *, /) |

### 用户接口

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/login` | 登录页面 |
| POST | `/login` | 用户登录 |
| GET | `/register` | 注册页面 |
| POST | `/register` | 用户注册 |
| GET | `/logout` | 退出登录 |

**POST /login 参数：**
| 参数 | 类型 | 说明 |
|------|------|------|
| username | string | 用户名 |
| password | string | 密码 |

**POST /register 参数：**
| 参数 | 类型 | 说明 |
|------|------|------|
| username | string | 用户名 |
| password | string | 密码 |
| confirmPassword | string | 确认密码 |

### VIP接口

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/vip` | VIP升级页面 |
| POST | `/vip/upgrade` | 升级为VIP |

**POST /vip/upgrade 参数：**
| 参数 | 类型 | 说明 |
|------|------|------|
| vipType | int | VIP类型 (1-4) |
| phone | string | 手机号 |

**VIP类型说明：**
| 类型码 | 名称 | 价格 | 有效期 |
|--------|------|------|--------|
| 1 | 日卡 | ¥10 | 1天 |
| 2 | 月卡 | ¥25 | 30天 |
| 3 | 季卡 | ¥68 | 90天 |
| 4 | 年卡 | ¥198 | 365天 |

## 🗄️ 数据库表结构

### user 表（用户表）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | INT | 主键，自增 |
| username | VARCHAR(50) | 用户名，唯一 |
| password | VARCHAR(255) | 加密后的密码 |
| is_vip | BOOLEAN | 是否为VIP |
| vip_type | ENUM | VIP类型 |
| vip_expire_time | DATETIME | VIP到期时间 |
| phone | VARCHAR(20) | 手机号 |

### operation_log 表（操作日志表）

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | INT | 主键，自增 |
| num1 | DOUBLE | 第一个操作数 |
| num2 | DOUBLE | 第二个操作数 |
| operator | VARCHAR(10) | 运算符 |
| result | DOUBLE | 计算结果 |
| created_at | DATETIME | 创建时间 |

## 📄 许可证

MIT License

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！
=======
# 计算器前后端项目
这是一个基于 SpringBoot + HTML 的计算器项目，实现了用户注册、登录和基础运算功能。

功能特性

✅ 基础计算：支持两位数及以内的加减乘除运算

✅ 用户系统：用户注册、登录、退出功能

✅ VIP会员：注册VIP可解锁高级功能

✅ 权限控制：
加法、减法：无需登录即可使用
乘法：需要登录
除法：需要登录且为VIP用户

✅ 历史记录：保存用户计算历史

✅ 健康检查：后端服务状态监测
技术栈
分类	技术	版本
前端	HTML5	-
前端	CSS3	-
前端	JavaScript	ES6+
后端	springboot	3.8+
后端	Flask	2.0.1
后端	Flask-CORS	3.0.10
数据库	MySQL	5.7+
数据库驱动	PyMySQL	1.0.2
项目结构
calcu/
├── index.html          # 前端页面
├── script.js           # 前端逻辑
├── style.css           # 前端样式
├── app.py              # 后端主程序
├── app_simple.py       # 后端简化版本（无数据库依赖）
├── test_db.py          # 数据库连接测试
├── test_flask.py       # Flask测试
├── requirements.txt    # 依赖列表
├── start.bat           # Windows启动脚本
└── .gitignore          # Git忽略配置
快速开始
环境要求
Python 3.8+
MySQL 5.7+
数据库配置：
数据库名：calc
用户名：calc
密码：123456
端口：13306
安装依赖
pip install -r requirements.txt
启动服务
方式一：使用启动脚本

start.bat
方式二：直接运行

python app.py
服务将在 http://127.0.0.1:5001 启动。

访问应用
打开浏览器访问 index.html 文件即可使用计算器。

API 接口说明
1. 健康检查
接口地址：GET /api/health

响应示例：

{
    "status": "ok"
}
2. 用户注册
接口地址：POST /api/register

请求参数：

参数名	类型	必填	说明
username	string	是	用户名
password	string	是	密码
请求示例：

{
    "username": "user123",
    "password": "password123"
}
响应示例：

{
    "message": "注册成功"
}
3. 用户登录
接口地址：POST /api/login

请求参数：

参数名	类型	必填	说明
username	string	是	用户名
password	string	是	密码
响应示例：

{
    "message": "登录成功",
    "user": {
        "id": 1,
        "username": "user123",
        "is_vip": false
    }
}
4. VIP注册
接口地址：POST /api/register_vip

请求参数：

参数名	类型	必填	说明
user_id	int	是	用户ID
gender	string	是	性别（男/女）
phone	string	是	手机号码
birthday	string	是	生日（YYYY-MM-DD）
agree	boolean	是	是否同意注册VIP
请求示例：

{
    "user_id": 1,
    "gender": "男",
    "phone": "13800138000",
    "birthday": "1990-01-01",
    "agree": true
}
响应示例：

{
    "message": "VIP注册成功",
    "is_vip": true
}
5. 检查VIP状态
接口地址：POST /api/check_vip

请求参数：

参数名	类型	必填	说明
user_id	int	是	用户ID
>>>>>>> 1a0d64b85d1aa28871ddfc11bf1ad06fabbec5dc
