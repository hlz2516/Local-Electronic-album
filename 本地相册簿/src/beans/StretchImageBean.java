package beans;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.alibaba.fastjson.annotation.JSONField;

import ���.ImageStorage;
import ���.StretchImageLabel;

public class StretchImageBean extends StretchComBean{
	private String id; //ͼƬ��Ψһ��ʶ��Ҳ��images���ͼƬ�ļ�������
	@JSONField(serialize=false)
	private Boolean isChanged = false; //ֻ��Ϊ���ڱ��ش洢ʱ�ж�ͼƬ�Ƿ�ı�
	public String getId() {
		return id;
	}
	//�������idΪ��ʱ��˵��û��ͼƬ��id��Ϊ��ʱ�������һ��id
	//���Ҫ��StretchImageLabel�൥�����ԣ���⿪27��ע��26��
	//���Ҫ����������в��ԣ���⿪26��ע��27��
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
