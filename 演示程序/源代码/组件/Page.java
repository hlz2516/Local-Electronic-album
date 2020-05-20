package 组件;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import beans.PageBean;
import beans.StretchImageBean;
import beans.StretchTextBean;
import tools.RootPath;
import 界面.UIManager;

public class Page extends JPanel{
	private PageBean bean;
	private ArrayList<StretchImageLabel> imageLabels;
	private ArrayList<StretchTextArea> textAreas;

	public Page() {
		bean = new PageBean();
		imageLabels = new ArrayList<StretchImageLabel>();
		textAreas = new ArrayList<StretchTextArea>();
		
		setLayout(null);
	}
	
	public void createImageLabel() {
		StretchImageLabel temp = new StretchImageLabel();
		this.add(temp);
		this.repaint();
		imageLabels.add(temp);
		
		bean.addImage(temp.getBean());
	}
	
	public void deleteImageLabel() {
		StretchImageLabel tmp = StretchImageLabel.getFocusedImageLabel();
		if(imageLabels.contains(tmp)) {
			imageLabels.remove(tmp);
			this.remove(tmp);
			this.repaint();
			
			bean.deleteImage(tmp.getBean());
		}
	}
	
	public void createTextArea() {
		StretchTextArea temp = new StretchTextArea();
		this.add(temp);
		temp.setBackground(Color.white);
		this.repaint();
		textAreas.add(temp);

		bean.addText(temp.getBean());
	}
	
	public void deleteTextArea() {
		StretchTextArea tmp = StretchTextArea.getFocusedTextArea();
		if(textAreas.contains(tmp)) {
			textAreas.remove(tmp);
			this.remove(tmp);
			this.repaint();

			bean.deleteText(tmp.getBean());
		}

	}
	
	public ArrayList<StretchImageLabel> getImageLabels(){
		return imageLabels;
	}
	
	public ArrayList<StretchTextArea> getTextAreas(){
		return textAreas;
	} 
	
	public void setBean(PageBean bean) {
		this.bean = bean;
		ArrayList<StretchImageBean> images = bean.getImages();
		for(int i = 0;i < images.size();i++) {
			StretchImageBean tmpbean = images.get(i);
			StretchImageLabel tmpLabel = new StretchImageLabel();
			tmpLabel.setStorePath(PageManager.getStorePath() + "\\images");
			tmpLabel.setBean(tmpbean);
			this.add(tmpLabel);
			imageLabels.add(tmpLabel);
		}
		ArrayList<StretchTextBean> texts = bean.getTexts();
		for(int i =0;i < texts.size();i++) {
			StretchTextBean tmpbean = texts.get(i);
			StretchTextArea tmpta = new StretchTextArea();
			tmpta.setBean(tmpbean);
			this.add(tmpta);
			tmpta.setBackground(Color.white);
			textAreas.add(tmpta);
		}
	}
	
	public PageBean getbean() {
		return bean;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		String path = RootPath.getRootDictionary();
		try {
			BufferedImage bg = ImageIO.read(new File(path + "lib\\PhotoFrame1.jpg"));
			Dimension size = super.getSize();
			Image bg1 = bg.getScaledInstance(size.width, size.height,1);//1代表默认缩放算法
			g.drawImage(bg1, 0, 0, size.width, size.height, null);
			UIManager.getFrame().repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
