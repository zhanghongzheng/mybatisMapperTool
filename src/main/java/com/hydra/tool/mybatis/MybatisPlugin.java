package com.hydra.tool.mybatis;

import com.google.common.collect.Lists;
import com.hydra.tool.string.StrUtil;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GongZheng on 15/6/16 下午11:29.
 * Describe:
 */
public class MybatisPlugin extends PluginAdapter {
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {
        // add field, getter, setter for limit clause
        addPage(topLevelClass, introspectedTable, "page");
        return super.modelExampleClassGenerated(topLevelClass,
                introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document,
                                           IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();

        XmlElement listByMap = new XmlElement("select");
        listByMap.addAttribute(new Attribute("id",
                "listByMap"));
        listByMap.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        listByMap.addAttribute(new Attribute("parameterType", "java.util.Map"));

        // 添加select
        listByMap.addElement(new TextElement("select"));

        // 添加include
        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "Base_Column_List"));
        listByMap.addElement(include);

        // 添加from xxx
        listByMap.addElement(new TextElement("from " + introspectedTable.getTableConfiguration().getTableName()));

        // 添加where
        XmlElement where = new XmlElement("where");
        for (IntrospectedColumn ic : introspectedTable.getAllColumns()) {
            XmlElement ifElement = new XmlElement("if");
            ifElement.addAttribute(new Attribute("test", ic.getJavaProperty() + "!= null"));
            ifElement.addElement(new TextElement("and " + ic.getActualColumnName() + " = #{" + ic.getJavaProperty() + "}"));
            where.addElement(ifElement);
        }
        listByMap.addElement(where);

        // 排序
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "pageOrder != null"));
        ifElement.addElement(new TextElement("order by #{pageOrder}"));
        listByMap.addElement(ifElement);

        // 分页
        listByMap.addElement(new TextElement("limit #{pageStart}, #{pageRows}"));

        parentElement.addElement(listByMap);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
            XmlElement element, IntrospectedTable introspectedTable) {

        XmlElement pageStart = new XmlElement("include"); //$NON-NLS-1$
        pageStart.addAttribute(new Attribute("refid", "OracleDialectPrefix"));
        element.getElements().add(0, pageStart);

        XmlElement isNotNullElement = new XmlElement("include"); //$NON-NLS-1$
        isNotNullElement.addAttribute(new Attribute("refid",
                "OracleDialectSuffix"));
        element.getElements().add(isNotNullElement);

        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
                introspectedTable);
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        Method method = new Method();
        method.setName("listByMap");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setFinal(false);
        method.setStatic(false);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Map<String, Object>"), "params"));
        FullyQualifiedJavaType listFull = FullyQualifiedJavaType.getNewListInstance();
        listFull.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        method.setReturnType(listFull);
        interfaze.addMethod(method);
        interfaze.addImportedType((new FullyQualifiedJavaType("java.util.List")));
        interfaze.addImportedType((new FullyQualifiedJavaType("java.util.Map")));

        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }


    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        List<String> stringList = Lists.newArrayList();
        stringList.add("StringBuilder sb = new StringBuilder();");
        stringList.add("sb.append(\"" + topLevelClass.getType() + "{\");");

        List<IntrospectedColumn> list = introspectedTable.getAllColumns();
        for (int i = 0; i < list.size(); i++) {
            Field field = new Field();
            field.setStatic(true);
            field.setFinal(true);
            field.setName(StrUtil.toUnderlineCase(list.get(i).getJavaProperty()).toUpperCase());
            field.setInitializationString("\"" + list.get(i).getJavaProperty() + "\"; // " + list.get(i).getRemarks());
            field.setType(new FullyQualifiedJavaType("String"));
            field.setVisibility(JavaVisibility.PUBLIC);
            topLevelClass.addField(field);

            if (i != list.size() - 1) {
                stringList.add("sb.append(\"" + list.get(i).getJavaProperty() + "=\").append(" + list.get(i).getJavaProperty() + ").append(\",\");");
            } else {
                stringList.add("sb.append(\"" + list.get(i).getJavaProperty() + "=\").append(" + list.get(i).getJavaProperty() + ");");
            }
        }
        stringList.add("sb.append(\"}\");");
        stringList.add("return sb.toString();");

        Method method = new Method();
        method.setReturnType(new FullyQualifiedJavaType("String"));
        method.setName("toString");
        method.addAnnotation("@Override");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addBodyLines(stringList);

        topLevelClass.addMethod(method);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * @param topLevelClass
     * @param introspectedTable
     * @param name
     */
    private void addPage(TopLevelClass topLevelClass,
                         IntrospectedTable introspectedTable, String name) {
        // 设置当前类的信息 类名 包名
        topLevelClass.addImportedType(new FullyQualifiedJavaType(
                "com.tgwoo.core.dao.pojo.Page"));

        CommentGenerator commentGenerator = context.getCommentGenerator();
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(new FullyQualifiedJavaType("com.tgwoo.core.dao.pojo.Page"));
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(new FullyQualifiedJavaType(
                "com.tgwoo.core.dao.pojo.Page"), name));
        method.addBodyLine("this." + name + "=" + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
        method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType(
                "com.tgwoo.core.dao.pojo.Page"));
        method.setName("get" + camel);
        method.addBodyLine("return " + name + ";");
        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }

    public static void start() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(MybatisPlugin.class.getClassLoader().getResource("generatorConfig.xml").getPath());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
