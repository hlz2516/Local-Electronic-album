package beans;

import java.util.ArrayList;

public class PageBean {
	private ArrayList<StretchImageBean> images;
	private ArrayList<StretchTextBean> texts;
	
	public PageBean() {
		images = new ArrayList<StretchImageBean>();
		texts = new ArrayList<StretchTextBean>();
	}

	public ArrayList<StretchImageBean> getImages() {
		return images;
	}

	public void setImages(ArrayList<StretchImageBean> images) {
		this.images = images;
	}

	public ArrayList<StretchTextBean> getTexts() {
		return texts;
	}

	public void setTexts(ArrayList<StretchTextBean> texts) {
		this.texts = texts;
	}
	
	public void addImage(StretchImageBean image) {
		images.add(image);
	}
	
	public void deleteImage(StretchImageBean image) {
		if(images.contains(image))
			images.remove(image);
	}
	
	public void deleteText(StretchTextBean text) {
		if(texts.contains(text))
			texts.remove(text);
	}
	
	public void addText(StretchTextBean text) {
		texts.add(text);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i =0;i< images.size();i++) {
			sb.append(images.get(i).toString() + '\n');
		}
		sb.append('\n');
		for(int i =0;i< texts.size();i++) {
			sb.append(texts.get(i).toString() + '\n');
		}
		sb.append("-------------");
		return sb.toString();
	}
}
