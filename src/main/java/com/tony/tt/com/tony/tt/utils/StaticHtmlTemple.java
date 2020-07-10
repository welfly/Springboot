package com.tony.tt.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.tony.tt.configrations.ApplictionContextUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class StaticHtmlTemple {

//	获取application配置文件中的属性值
	private String getPropertis() {
		Environment env = ApplictionContextUtils.get(Environment.class);
		return env.getProperty("template.path");
	}
	
	//获得freeMarker对象
	// step1 创建freeMarker配置实例
	@Autowired
	@SuppressWarnings("deprecation")
	private Configuration configuration = new Configuration();
	
	public void setHtml(Map<String, Object> dataMap) {

        Writer out = null;
        try {
            // step2 获取模版路径
//        	模板路基已经在application.properties中配置
//            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型, key 需要和模板文件中${}的key保持一致。
//            dataMap.put("classPath", "com.freemark.hello");
//            dataMap.put("className", "AutoCodeDemo");
//            dataMap.put("helloWorld", "通过d是大法官是大法官！哈客户收到客户发卡机红烧豆腐");
            // step4 加载模版文件
        	System.out.println(getPropertis());
            Template template = configuration.getTemplate(getPropertis() );
            // step5 生成数据
            File docFile = new File("D:/"+ dataMap.get("id") +".html");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^AutoCodeDemo.java 文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
                out.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
	}
}
