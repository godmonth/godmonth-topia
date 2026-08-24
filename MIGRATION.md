# 从 topia-commons 迁移到 godmonth-topia

`godmonth-topia` 是 `topia-commons` 的公开继任版：模块一一对应，类名与结构基本不变。迁移核心是换 Maven 坐标、换 Java 包名。已发布版本：`1.0.0`（[Maven Central](https://central.sonatype.com/namespace/com.godmonth.topia)）。

## 对照

| 项 | topia-commons | godmonth-topia |
|---|---|---|
| groupId | `io.bitexpress.topia.commons` | `com.godmonth.topia` |
| 父 POM | `commons-platform-root` | `topia-parent` |
| BOM | `commons-bom` | `topia-bom` |
| artifact 前缀 | `commons-*` | `topia-*` |
| Java 包 | `io.bitexpress.topia.commons.*` | `com.godmonth.topia.*`（去掉 `commons` 段） |
| JDK | 25 | **17+** |
| 仓库 | 私有 Nexus snapshot | Maven Central |

## 1. 换 Maven 坐标

建议用 BOM 统一版本：

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.godmonth.topia</groupId>
            <artifactId>topia-bom</artifactId>
            <version>1.0.0</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

模块对照：

| 旧 artifactId | 新 artifactId |
|---|---|
| `commons-concept` | `topia-concept` |
| `commons-pagination` | `topia-pagination` |
| `commons-rpc` | `topia-rpc` |
| `commons-basic` | `topia-basic` |
| `commons-data` | `topia-data` |
| `commons-jsr303-validation` | `topia-jsr303-validation` |
| `commons-idempotence` | `topia-idempotence` |
| `commons-bom` | `topia-bom` |

单模块示例：

```xml
<!-- 旧 -->
<dependency>
    <groupId>io.bitexpress.topia.commons</groupId>
    <artifactId>commons-rpc</artifactId>
    <version>8.0-SNAPSHOT</version>
</dependency>

<!-- 新 -->
<dependency>
    <groupId>com.godmonth.topia</groupId>
    <artifactId>topia-rpc</artifactId>
    <version>1.0.0</version>
</dependency>
```

去掉对 `io.bitexpress.topia.commons:commons-*` 的依赖，以及仅因 commons 引入的 `io.bitexpress:third-party-bom`（若不再使用）。

## 2. 换 Java 包名

全局替换（import、全限定类名、Spring 扫描、XML `class=`、Dubbo/SPI、反射字符串）：

```
io.bitexpress.topia.commons.  →  com.godmonth.topia.
```

新包名是 `com.godmonth.topia.basic`，**不是** `com.godmonth.topia.commons.basic`。

| 旧包前缀 | 新包前缀 |
|---|---|
| `io.bitexpress.topia.commons.concept` | `com.godmonth.topia.concept` |
| `io.bitexpress.topia.commons.pagination` | `com.godmonth.topia.pagination` |
| `io.bitexpress.topia.commons.rpc` | `com.godmonth.topia.rpc` |
| `io.bitexpress.topia.commons.basic` | `com.godmonth.topia.basic` |
| `io.bitexpress.topia.commons.data` | `com.godmonth.topia.data` |
| `io.bitexpress.topia.commons.jsr303` | `com.godmonth.topia.jsr303` |
| `io.bitexpress.topia.commons.idempotence` | `com.godmonth.topia.idempotence` |

示例：

| 旧 FQCN | 新 FQCN |
|---|---|
| `io.bitexpress.topia.commons.rpc.error.ErrorCode` | `com.godmonth.topia.rpc.error.ErrorCode` |
| `io.bitexpress.topia.commons.data.model.IdObject` | `com.godmonth.topia.data.model.IdObject` |
| `io.bitexpress.topia.commons.pagination.Pagination` | `com.godmonth.topia.pagination.Pagination` |
| `io.bitexpress.topia.commons.basic.rpc.utils2.BaseResponseUtils` | `com.godmonth.topia.basic.rpc.utils2.BaseResponseUtils` |

7 个实现模块共 131 个 main 类，按上述规则一一对应，类名不变。

## 3. 破坏性变更

### `IdempotenceTemplate` 不再集成状态机

旧版 `commons-idempotence` 依赖 `com.godmonth.status:executor-core`。`execute()` 在保存后会按 `afterSaveOperation` 调用 `OrderExecutor`（默认 `ADVANCE`）。

新版 `topia-idempotence` **已去掉**该依赖和下列 API：

- `setOrderExecutor(OrderExecutor)`
- `setAfterSaveOperation(NextOperation)`

`execute()` 在事务回调后直接返回订单，不再推进状态。

若业务仍依赖自动 ADVANCE / ASYNC_ADVANCE，在调用方自行接 `godmonth-status2` 的 `OrderExecutor`：

```java
T order = idempotenceTemplate.execute(request, param -> /* 落库 */);
return orderExecutor.execute(order, null, null).getModel();
```

### 传递依赖版本

新库在父 POM 中显式管理版本（不再走 `third-party-bom`）。与宿主应用对齐时注意：

| 依赖 | godmonth-topia 1.0.0 |
|---|---|
| JDK | 17 |
| Spring Framework | 7.0.8 |
| Spring Data | 2025.0.1 |
| Hibernate ORM | 7.1.0.Final |
| Hibernate Validator | 8.0.2.Final |
| Jackson | 2.19.0 |
| Jakarta Validation | 3.0.2 |
| Dubbo | 3.3.4 |
| joda-money | 1.0.5（旧库常为 2.x） |

两库都已使用 `jakarta.*`（无 `javax.validation` / `javax.servlet`）。宿主若仍停留在 javax EE，需先完成 Jakarta 迁移。

## 4. 建议步骤

1. 引入 `topia-bom:1.0.0`，把 `commons-*` 换成对应 `topia-*`。
2. 全局替换包名前缀 `io.bitexpress.topia.commons.` → `com.godmonth.topia.`。
3. 构建与 CI 使用 JDK 17+。
4. 检查 `IdempotenceTemplate` 的 `orderExecutor` / `afterSaveOperation` 配置，按上一节补状态推进。
5. 核对 Spring 7 / Hibernate 7 / Jackson 2.19 / joda-money 与宿主是否兼容。
6. 全量编译与测试，重点：RPC DTO 序列化、JPA 实体、`MethodValidationPostProcessor3`。

## 5. 常用依赖片段

```xml
<dependency>
    <groupId>com.godmonth.topia</groupId>
    <artifactId>topia-basic</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>com.godmonth.topia</groupId>
    <artifactId>topia-rpc</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>com.godmonth.topia</groupId>
    <artifactId>topia-data</artifactId>
    <version>1.0.0</version>
</dependency>
```
