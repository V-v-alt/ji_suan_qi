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
