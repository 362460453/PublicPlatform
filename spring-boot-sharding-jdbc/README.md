# boot-sharding

#### 项目介绍
boot-sharding是springBoot项目，简单结合当当网的sharding-jdbc实现分区分表，读写分离，是一个学习示例。<br>
欢迎大家提意见，您的宝贵意见，是我们进步的动力。<br>

	 
#### **启动说明:**
- 1.下载源码,导入idea或者eclipse工具。<br>
- 2.分别创建mysql数据库sharding_master和sharding_salve两个实例,依次运行(resources/sql)目录里面的sql文件。<br>
- 3.Junit测试OrderControllerTest里面的createOrder和getOrderListByUserId方法，可以看到控制台打印出执行结果。<br>
- 4.Junit测试OrderServiceTest里面的createOrder和getOrderListByUserId方法，可以看到控制台打印出执行结果数据。<br>
- 5.运行Application启动项目，浏览器输入http://localhost:8080/order/5/5。
- 6.观察sharding-master和sharding-salve两个实例中的表数据。<br>

	
#### **友情链接：**
- GitHub：https://github.com/apple987 <br>
- 码云地址： https://gitee.com/bootstrap2table<br>
- mycat示例： https://gitee.com/bootstrap2table/boot_master/tree/feature/mycat<br>
- dubbo示例： https://gitee.com/reger/spring-boot-starter-dubbo<br>

#### **问题反馈：**
- 意见反馈：https://gitee.com/bootstrap2table/boot-sharding/issues
- 联系作者: m15171479289@163.com<br>
		
