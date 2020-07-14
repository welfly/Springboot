package com.tony.tt.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tony.tt.service.IPersionServices;
import com.tony.tt.vo.User;

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
//		System.out.println(u);
		Map<String, Object> m = new HashMap<String, Object>();
		iPersionServices.addOne(u);
		
		return m;
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
	
	public String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            HttpGet httpGet = new HttpGet(url);
            // 创建参数列表
            if (null != param) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
                 httpPost.setEntity(entity);
            }
            // 执行http请求
//            response = httpClient.execute(httpPost);
            response = httpClient.execute(httpGet);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            	if(null != response) {
            		response.close();
            	}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 
        return resultString;
    }
	/**
	 * 操作html字符串
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("pages/{i}")
	public void WStoHtml(HttpServletRequest request,HttpServletResponse response, @PathVariable String i) throws IOException{
//		String urls = request.getParameter("url");
//		String url = "https://www.cnblogs.com/minjieagile/p/13291539.html";
		String url = "http://localhost:9092/pages/"+i;
		
		String body = doPost(url,new HashMap<String, String>());//body为获取的html代码
	    //System.out.println(body);
	    //System.out.println("11111");
	    Document doc = Jsoup.parse(body);
//	    Elements es =  doc.select("table");
//	    for (Element element : es) {
//            element.html("123");//将table的内容替换为123
//        }
//	    for (Element element : es) {
//            System.out.println(element.html());
//        }
//	    System.out.println(doc.outerHtml());
	    response.setContentType("text/html;charset=utf-8"); 
	    PrintWriter out=response.getWriter();
	    out.println(doc.outerHtml());
	}
}
