## mybatisMapperTool
###mybatisMapper自动生成工具

####1，启动：
TestMybatis：


```
public class TestMybatis {

    public static void main(String[] args) throws
    InterruptedException, SQLException, InvalidConfigurationException,
     XMLParserException, IOException {

        MybatisPlugin.start();

    }
}
```

####2，生成文件路径：见配置文件


####3，配置文件：


generatorConfig.xml



```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE generatorConfiguration PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd" >
<generatorConfiguration>
    <!-- classPathEntry用于
    	1：设置数据库驱动包位置-->
    <classPathEntry
            location="/Users/zhz/.m2/Repository/mysql/mysql-connector-java/5.1.20/mysql-connector-java-5.1.20.jar"/>
    <!-- targetRuntime：代码生成目标，默认是MyBatis3-->
    <context id="DB2Tables" targetRuntime="MyBatis3">

        <plugin type="com.hydra.tool.mybatis.MybatisPlugin"/>

        <commentGenerator>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>

        <!-- 2：数据库连接的信息 -->
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://112.126.90.253:3306/topnews?characterEncoding=utf8"
                        userId="dev" password="qufenqi123!@#">
        </jdbcConnection>
        <!-- 解决数据转换问题 -->
        <javaTypeResolver>
            <!--是否强制使用BigDecimal来表示所有的十进制和数值字段。-->
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>
        <!--3：生成模型的包名和位置-->
        <javaModelGenerator targetPackage="alipay.openplatform.edu.campus.topnews.dao.po" targetProject="src">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!--4：生成的映射文件包名和位置-->
        <sqlMapGenerator targetPackage="mybatis" targetProject="src">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!--5：生成DAO的包名和位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="alipay.openplatform.edu.campus.topnews.dao.mapper"
                             targetProject="src">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <!--6：要生成那些文件-->
        <table tableName="activity_pack_out" domainObjectName="ActivityPackOut" enableCountByExample="false"
               enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false">
            <property name="useActualColumnNames" value="true"></property>
        </table>

    </context>
</generatorConfiguration>
```