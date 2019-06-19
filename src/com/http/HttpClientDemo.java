package com.http;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpClientDemo {

	public static void main(String[] args) throws Exception, IOException {  
        //1.创建客户端   
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        //2.创建get请求  
        HttpGet httpGet = new HttpGet("https://www.csdn.net");  
        //3.执行get请求,得到响应  
        CloseableHttpResponse response = httpClient.execute(httpGet);  
        //4.判断是否响应成功=状态码为200  
        if(response.getStatusLine().getStatusCode()==200){  
            //5.获得响应结果  
            HttpEntity entity = response.getEntity();  
            String html = EntityUtils.toString(entity,Charset.forName("utf-8"));  
            //System.out.println(html);  
            //6.关闭响应  
            //使用jsoup解析得到的数据   
            //6.1得到document对象  
            Document document = Jsoup.parse(html);  
            //6.2解析数据  
            String title = document.title();  
            System.out.println(title);  
            //6.3提取所有链接  
            Elements links = document.select("a[href]");    
               for (Element link : links)   
               {  
                    System.out.println("link : " + link.attr("href"));    
                    System.out.println("text : " + link.text());    
               }  
            response.close();  
        }  
   }
}
