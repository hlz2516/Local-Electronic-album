package beans;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.alibaba.fastjson.annotation.JSONField;

import 组件.ImageStorage;
import 组件.StretchImageLabel;

public class StretchImageBean extends StretchComBean{
	private String id; //图片的唯一标识，也是images里该图片文件的命名
	@JSONField(serialize=false)
	private Boolean isChanged = false; //只是为了在本地存储时判断图片是否改变
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setRandomId() {
		this.id = UUID.randomUUID().toString();
		isChanged = true;
	}
	
	public Boolean isChanged() {
		return isChanged;
	}
	
	public void setChanged(Boolean bool) {
		isChanged = bool;
	}
	
	public String toString() {
		return "id:" + id + " " + super.toString() + " isChanged:" + isChanged;
	}
}
