package teststh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;

import beans.*;

public class JsonTest {
//	public static void main(String[] args) {
//		//构建bean
//		PageBean page = new PageBean();
//		StretchImageBean img = new StretchImageBean();
//		img.setX(10);img.setY(10);
//		img.setWidth(20);img.setHeight(20);
//		img.setRandomId();
//		
//		StretchTextBean txt = new StretchTextBean();
//		txt.setX(30);txt.setY(30);
//		txt.setWidth(40);txt.setHeight(40);
//		txt.setText("这是文本框");
//		
//		page.addImage(img);
//		page.addText(txt);
//		//写入json
//		String path = ".//test.json";
//		BufferedWriter bw = null;
//		JSONWriter writer = null;
//		try {
//			bw = new BufferedWriter(new FileWriter(path));
//			writer = new JSONWriter(bw);
//			writer.writeObject(page);
//			writer.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				writer.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				bw.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		String path = ".//test.json";
//		PageBean obj = null;
//		BufferedReader br = null;
//		JSONReader reader = null;
//		try {
//			br = new BufferedReader(new FileReader(path));
//			reader = new JSONReader(br);
//			obj = reader.readObject(PageBean.class);
//			System.out.println(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			reader.close();
//			try {
//				br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
}
