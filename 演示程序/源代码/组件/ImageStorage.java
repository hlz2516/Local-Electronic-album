package ���;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//ʵ�ֶ���ᲾͼƬ�Ĵ洢������ͼ�β���ʾ
public class ImageStorage {
	private static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	//����һ��Ŀ¼������Ŀ¼�µ�����ͼƬ�ļ�����images�
	//���û�������Ჾʱ������ָ����Ჾ��ͼƬ����
	public static void loadImages(String path){
		File[] files = new File(path).listFiles();
		for(int i = 0;i < files.length;i++) {
			//System.out.println(files[i].getName());
			String fileName = files[i].getName();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			String realName = fileName.substring(0,fileName.lastIndexOf("."));
			System.out.println("ImageStorage���id:" + realName);
			//System.out.println(fileType);
			if(fileType.equalsIgnoreCase(".jpg") || fileType.equalsIgnoreCase(".png") || 
					fileType.equalsIgnoreCase(".bmp")) {
				try {
					BufferedImage image = ImageIO.read(files[i]);
					ImageIcon icon = new ImageIcon(image);
					icon.setDescription(realName); //ͼƬ��������id
					images.add(icon);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//�û��˳���Ჾʱ�������images�������
	public static void removeAll() {
		images.clear();
	}
	
	public static ArrayList<ImageIcon> getImages() {
		return images;
	}
}
