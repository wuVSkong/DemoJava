package com.http;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


public class RetrivePage {

//	private static HttpClient client1 = new HttpClient();
	public static void main(String[] args) {
		
		String path = "C:/Users/AdminWK/Desktop/jsontest.txt";
		System.out.println(path);
		
		try {
			String temp = readTxt(path);
			System.out.println(temp);
			JSONObject json = JSONObject.fromObject(temp);
//			JSONObject obj = JSONObject.fromObject(temp.toString());
//			JSONArray aray = JSONArray.fromObject(json);
			
			List<Demo> list = getJavaCollection(new Demo() ,json.getJSONArray("TDMER23").toString());
			for (Demo map : list) {
				System.out.println(map);
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static <T> List<T> getJavaCollection(T clazz, String jsons) {
		List<T> objs = null;
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsons);
		if (jsonArray != null) {
			objs = new ArrayList<T>();
			List list = (List) JSONSerializer.toJava(jsonArray);
			for (Object o : list) {
				JSONObject jsonObject = JSONObject.fromObject(o);
				T obj = (T) JSONObject.toBean(jsonObject, clazz.getClass());
				objs.add(obj);
			}
		}
		return objs;
	}
	
		//将txt转换为String
	public static String readTxt(String path) throws IOException{
		StringBuffer content = new StringBuffer(""); //文档内容
		try {
			FileReader reader = new FileReader(path);
			BufferedReader br = new BufferedReader(reader);
			String s1 = null;
			while((s1 = br.readLine()) != null)
                             {
				content.append(s1+"\r");
			}
			br.close();
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return content.toString().trim();
	}
	
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}
	
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
	
	/**
	 * 图片上处理汉字
	 */
	public void imgPringFont(){
		try {

			File file = new File("F:/yang.jpg");

			BufferedImage image = ImageIO.read(file);

			Graphics2D g2 = image.createGraphics();

			g2.setColor(Color.red);
			
			g2.setFont(new Font("华文行楷", Font.BOLD, 20));

			g2.drawString("吴创!", image.getWidth() - 220, image.getHeight() - 220);

			g2.dispose();

			ImageIO.write(image, "png", file);
			
			System.out.println("成功");
		} catch (IOException e) {

			e.printStackTrace();

		}
	}
	
	/**
	 * 字符串去重排序
	 */
	public void removeSort(){
		String[] workCodes = {"1001","1002","1001"};
		List<String> result = new ArrayList<>();  
		boolean flag;  
		for(int i=0;i<workCodes.length;i++){
		    flag = false;  
		    for(int j=0;j<result.size();j++){  
		        if(workCodes[i].equals(result.get(j))){  
		            flag = true;  
		            break;  
		        }  
		    }  
		    if(!flag){  
		        result.add(workCodes[i]);  
		    }
		}
		String work = String.join(",", result);
		System.out.println(work);
	}
	
	
	
}

