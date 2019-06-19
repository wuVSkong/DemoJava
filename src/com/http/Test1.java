package com.http;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.HttpStatus;

public class Test1 {

	public static void main(String[] args) throws ParseException {
//		long day = (strToDate("20180922").getTime() - getNewDate().getTime())/(24*60*60*1000);
//		System.out.println(day);//1
		//2018-08-24 15:49:51
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data = sdf.parse("2018-08-24 15:49:51");
		System.out.println(data.getTime()+"----"+getNewDate().getTime()+"----"+(getNewDate().getTime()-data.getTime())/(24*60*60*1000));
	}
	/***
	 * 获取当前时间并格式化
	 * @return
	 * @throws ParseException
	 */
	public static Date getNewDate() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String data1 = sdf.format(new Date());
		Date data = sdf.parse(data1);
		return data;
	}
	/***
	 * 传入String格式日期格式化并返回日期格式
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date data = sdf.parse(date);
		return data;
	}
	
	public void upload(String localFile){
        File file = new File(localFile);
        PostMethod filePost = new PostMethod("http://192.168.1.146:8080/wzplat/upload");
        HttpClient client = new HttpClient();
        
        try {
            // 通过以下方法可以模拟页面参数提交
            filePost.setParameter("content", "1111");
            filePost.setParameter("file", "222222");

            Part[] parts = {new FilePart(file.getName(), file) };
            filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
            
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                System.out.println("上传成功");
            } else {
                System.out.println("上传失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            filePost.releaseConnection();
        }
    }
	
}
