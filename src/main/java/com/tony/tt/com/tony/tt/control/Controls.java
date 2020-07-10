package com.tony.tt.control;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tony.tt.service.IPersionServices;
import com.tony.tt.vo.User;

import freemarker.template.Configuration;

@RestController
public class Controls {
	
	@Autowired
	private IPersionServices iPersionServices;
	
	
	@RequestMapping(value = "/hellow", method =RequestMethod.GET )
//	@GetMapping(value = "/hellow")
	public String getHello() {
		return "hellow jlakjslk吉拉数据砥砺奋进";
	}
	
	@RequestMapping(value = "/", method =RequestMethod.GET )
//	@GetMapping(value = "/hellow")
	public String getHello2() {
		return "hellow jlakjslk吉拉数据砥砺奋进啊啊啊啊啊啊啊";
	}
	
	@GetMapping("/getUser/{id}")
	public Map<String, Object> getUser1(@PathVariable int id) {
		return iPersionServices.findByIdi(id);
		
	}
	
//	使用postman 直接报错403， 使用get方法
	@GetMapping("/add1")
	public Map<String, Object> addUser(User u) {
		System.out.println(u);
		return iPersionServices.addOne(u);
	}
	
	@GetMapping("/delete/{id}")
	public Map<String, Object> delete(@PathVariable int id) {
		System.out.println("asdfasdrrf");
		return iPersionServices.delet(id);
		
	}
	
	@GetMapping("/update1")
	public Map<String, Object> changeUser(User u) {
		System.out.println(u);
		return iPersionServices.update1(u);
	}
	
	
	/**
	 * 添加内容文件同时生成静态页面
	 *
	 * @param weixinContent 内容实体对象
	 * @param request
	 * @param
	 * @throws IOException 
	 */
	@GetMapping(value = "/service")
	public void ContentAdd()  {
		
	}
}
