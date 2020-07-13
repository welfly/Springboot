package com.tony.tt.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tony.tt.dao.PersionMapper;
import com.tony.tt.service.IPersionServices;
import com.tony.tt.vo.User;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class IPersionServicesImpl implements IPersionServices {

	@Autowired
	private PersionMapper persionMapper;
	
	@Autowired
	private Configuration configuration;
	
	@Transactional
	@Override
	public Map<String, Object> findByIdi(int id) {
		User u = null;
		Writer out = null;
		Map<String, Object> restMap = new HashMap<String, Object>();
		try {
			u = persionMapper.findById(id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if( null == u) {
			restMap.put("msg", "当前用户不存在");
			restMap.put("code", 400);
			return restMap;
		}
		try {
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("id", u.getId());
			dataMap.put("name", u.getName());
			dataMap.put("gender", u.getGender());
			dataMap.put("age", u.getAge());
			dataMap.put("addr", u.getAddr());
			dataMap.put("phoneNo", u.getPhoneNo());
			
//			用下面的方法找不到模板
//			StaticHtmlTemple html = new StaticHtmlTemple();
//			html.setHtml(dataMap);
	        
	            // step2 获取模版路径
//	        	模板路基已经在application.properties中配置
//	            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
	            // step3 创建数据模型, key 需要和模板文件中${}的key保持一致。

	            // step4 加载模版文件
	            Template template = configuration.getTemplate("content.ftl");
	            // step5 生成数据
//	            System.out.println(this.getClass().getClassLoader().getResource(".").getPath());
//	            System.out.println(this.getClass().getResource("").getPath());
	            File file = new File("");
	            String filePath = file.getAbsolutePath();
//	            System.out.println(filePath);
	            File f1 = new File(filePath+"/htmls");
	            if (!f1.exists()) {
	            	f1.mkdir();
	            }
//	            System.out.println(f1.getAbsolutePath() );
	            File docFile = new File(f1.getAbsolutePath() +"/"+ dataMap.get("id") +".html");
	            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile),"UTF-8"));
	            // step6 输出文件
	            template.process(dataMap, out);
	            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^静态html文件创建成功 !@@@@@@@@@@@@@@@@@@");
	            restMap.put("userInfo", u);
	            restMap.put("code", 200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            try {
                if (null != out) {
                    out.flush();
                }
                out.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
		return restMap;
	}

	@Transactional
	@Override
	public Map<String, Object> addOne(User u) {
		int rest = 0;
		Map<String, Object> restM = new HashMap<String, Object>();
		try {
			rest = persionMapper.addOne(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rest != 0) {
			restM.put("msg", 200);
		} else {
			restM.put("msg", 404);
		}
		return restM;
	}

	@Transactional
	@Override
	public Map<String, Object> delet(int id) {
		int rest = 0;
		Map<String, Object> restM = new HashMap<String, Object>();
		try {
			rest = persionMapper.d1(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rest != 0) {
			restM.put("msg", 200);
		} else {
			restM.put("msg", 404);
		}
//		System.out.println(1/0);
		return restM;
	}

	@Transactional
	@Override
	public Map<String, Object> update1(User u) {
		int rest = 0;
		Map<String, Object> restM = new HashMap<String, Object>();
		try {
			rest = persionMapper.updateByName(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rest != 0) {
			restM.put("msg", 200);
		} else {
			restM.put("msg", 404);
		}
		return restM;
	}

}
