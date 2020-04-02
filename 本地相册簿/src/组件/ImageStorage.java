package 组件;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//实现对相册簿图片的存储，辅助图形层显示
public class ImageStorage {
	private static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	//传入一个目录，将该目录下的所有图片文件载入images里。
	//在用户进入相册簿时，载入指定相册簿的图片内容
	public static void loadImages(String path){
		File[] files = new File(path).listFiles();
		for(int i = 0;i < files.length;i++) {
			//System.out.println(files[i].getName());
			String fileName = files[i].getName();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String realName = fileName.substring(0,fileName.lastIndexOf("."));
			System.out.println("ImageStorage里的id:" + realName);
			//System.out.println(fileType);
			if(fileType.equalsIgnoreCase(".jpg") || fileType.equalsIgnoreCase(".png") || 
					fileType.equalsIgnoreCase(".bmp")) {
				try {
					BufferedImage image = ImageIO.read(files[i]);
					ImageIcon icon = new ImageIcon(image);
					icon.setDescription(realName); //图片描述就是id
					images.add(icon);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//用户退出相册簿时，将清空images里的内容
	public static void removeAll() {
		images.clear();
	}
	
	public static ArrayList<ImageIcon> getImages() {
		return images;
	}
}
