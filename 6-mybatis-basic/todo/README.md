# mybatis 基本示例

## MySQL 数据库创建脚本

```
CREATE TABLE `todo` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`note` varchar(200) NOT NULL,
`done` tinyint(4) NOT NULL,
`create_at` datetime NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```