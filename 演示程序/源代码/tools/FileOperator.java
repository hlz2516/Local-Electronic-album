package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;

public class FileOperator {
	public static void createFolder(String name) {
		File file = new File(name);
		if(!file.exists()) {
			file.mkdir();
		}
	}
	
	public static void writeJSON(String JSONpath,Object obj) {
		BufferedWriter bw = null;
		JSONWriter writer = null;
		try {
			bw = new BufferedWriter(new FileWriter(JSONpath));
			writer = new JSONWriter(bw);
			writer.writeObject(obj);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Object readJSON(String JSONpath,Class classtype) {
		Object obj = null;
		BufferedReader br = null;
		JSONReader reader = null;
		try {
			br = new BufferedReader(new FileReader(JSONpath));
			reader = new JSONReader(br);
			obj = reader.readObject(classtype);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			reader.close();
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	public static void writeJSONArray(String JSONpath,JSONArray arr) {
		BufferedWriter bw = null;
		JSONWriter writer = null;
		try {
			bw = new BufferedWriter(new FileWriter(JSONpath));
			writer = new JSONWriter(bw);
			writer.writeObject(arr);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static <T> ArrayList<T> readJSONArray(String JSONpath,Class c){
		File file = new File(JSONpath);
		if(!file.exists()) return null;
		ArrayList<T> res = new ArrayList<T>();
		BufferedReader br = null;
		JSONReader reader = null;
		try {
			br = new BufferedReader(new FileReader(JSONpath));
			reader = new JSONReader(br);
			reader.startArray();
			while(reader.hasNext()) {
				T temp = (T)reader.readObject(c);
				res.add(temp);
			}
			reader.endArray();
		} catch (Exception e) {
		}finally {
			reader.close();
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public static void copyFile(String source,String target) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source);
			fos = new FileOutputStream(target);
			byte[] bytes = new byte[1024];
			int size = 0;
			while((size = fis.read(bytes)) != -1) {
				fos.write(bytes, 0, size);
			}
			fos.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				if(fis != null)
					fis.close();
				if(fos != null)
					fos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public static void deleteFiles(String path) {
		File file = new File(path);
		if(!file.exists()) return;
		ergodicDeletion(file);
	}
	
	public static void ergodicDeletion(File file) {
		
		if(file.isFile()) {
			file.delete();
			return;
		}
		
		//file有两种可能，可能是文件，也可能是目录
		File[] files = file.listFiles();
		for(File subF:files) {
			ergodicDeletion(subF);
		}
		//一波遍历完后可以删除该目录了
		file.delete();
	}
}
