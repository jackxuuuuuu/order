# Cafeteria Order System (食堂点餐系统)

## 项目简介

这是一个简单的食堂点餐管理系统，实现了基本的点餐功能。系统不包含配送模块，适用于食堂现场点餐场景。

## 主要功能

### 1. 用户管理
- 用户注册和登录
- 用户信息管理
- 用户角色管理（顾客、管理员）

### 2. 菜品管理
- 菜品分类管理
- 菜品信息管理（名称、价格、描述）
- 菜品库存管理
- 菜品上下架

### 3. 订单管理
- 购物车功能
- 订单创建
- 订单状态管理（待支付、已支付、制作中、已完成、已取消）
- 订单历史查询
- 订单取消

### 4. 支付管理
- 支付方式选择
- 支付状态管理

## 技术栈

- Java 1.8
- Maven
- 标准Java项目结构

## 项目结构

```
order-system/
├── src/main/java/com/cafeteria/
│   ├── entity/          # 实体类
│   ├── dao/             # 数据访问层
│   ├── service/         # 业务逻辑层
│   ├── controller/      # 控制层
│   ├── util/            # 工具类
│   └── Main.java        # 主程序入口
```

## 安装和运行

### 1. 克隆项目
```bash
git clone https://github.com/jackxuuuuuu/gomoku.git
cd gomoku
```

### 2. 编译项目
```bash
mvn clean compile
```

### 3. 打包项目
```bash
mvn clean package
```

### 4. 运行项目
```bash
java -jar target/order-system-1.0-SNAPSHOT.jar
```

## 核心实体说明

- **User**: 用户实体，包含用户基本信息和角色
- **Dish**: 菜品实体，包含菜品信息和库存
- **Order**: 订单实体，包含订单主要信息
- **OrderDetail**: 订单详情实体，包含订单中的菜品信息
- **ShoppingCart**: 购物车实体，临时存储用户选择的菜品

## 贡献

欢迎贡献代码！请提交Issue或Pull Request来改进项目。

## 许可证

MIT License
