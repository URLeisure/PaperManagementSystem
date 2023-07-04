package com.shi.main;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/07 14:36
 **/

public class CodeGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/paper_management?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
        String username = "root";
        String password = "root";
        String author = "shianyi";
        String outputDir = "E:\\Code\\PaperManagementSystem\\src\\main\\java";
        //String outputDir = "E:\\mybatisgen\\com\\shi\\main";
        String basePackage = "com.shi";
        String moduleName = "main";
        String mapperLocation = "E:\\Code\\PaperManagementSystem\\src\\main\\resources\\" + moduleName;
        //String tableName = "paper,student,teacher";
        String tableName = "admin,paper,student,teacher";
        String tablePrefix = "x_";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(basePackage) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName); // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
