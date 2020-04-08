package ×é¼þ;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import beans.PageBean;
import beans.StretchImageBean;
import beans.StretchTextBean;

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
			tmpta.setBackground(this.getBackground());
			textAreas.add(tmpta);
		}
	}
	
	public PageBean getbean() {
		return bean;
	}
}
