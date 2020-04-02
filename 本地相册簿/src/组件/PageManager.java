package ���;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import beans.PageBean;
import beans.StretchImageBean;
import beans.StretchTextBean;
import tools.FileOperator;
import ����.BorderUI;
import ����.UIManager;
//����ʵ��ҳ���ڵı༭��ҳ��Ĺ���ҳ��Ĵ洢�����
public class PageManager {
	private static Page curPage;
	private ArrayList<Page> pages;
	private JLabel label;
	//���ó�Ա�ڲ�����Բ�����ǰҳ������������ڷ������Ƕ�ҳ���ڵĸ��ֲ���
	//��װ������Թ��ⲿ�ؼ����ã�����ʵ�ֶ�̬�ضԵ�ǰҳ���ڲ����и��ֲ���
	public class ImageCreat implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			curPage.createImageLabel();
		}
	}
	public class ImageDelete implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			curPage.deleteImageLabel();
		}
	}
	public class TextCreat implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			curPage.createTextArea();
		}
	}
	public class TextDelete implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			curPage.deleteTextArea();
		}
	}
	public class checkBean implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ArrayList<StretchImageLabel> imgs = curPage.getImageLabels();
			ArrayList<StretchTextArea> texts = curPage.getTextAreas();
			for(StretchImageLabel img:imgs) {
				StretchImageBean imgbean = img.getBean();
				System.out.println("image id:" + imgbean.getId() + " location:" +
						imgbean.getX() + "," + imgbean.getY() + " size:" + 
						imgbean.getWidth() + "," + imgbean.getHeight());
			}
			
			for(StretchTextArea text:texts) {
				StretchTextBean txtbean = text.getBean();
				System.out.println("location:" + txtbean.getX() + "," + txtbean.getY() +
						" size:" + txtbean.getWidth() + txtbean.getHeight() + " text:" + txtbean.getText());
			}
			System.out.println("--------------------");
		}
	}
	//�����ڲ���ʵ�ֶ�ҳ��Ĺ���
	public class PageCreate implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Page page = new Page();
			BorderUI ui = UIManager.getCurUI();
			page.setPreferredSize(ui.getCenterPreferredSize());
			
			int curIndex = pages.indexOf(curPage);
			pages.add(curIndex+1, page);
		}
	}
	public class PageDelete implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(pages.contains(curPage)) {
				int curIndex = pages.indexOf(curPage);
				int size = pages.size();
				pages.remove(curPage);
				if(curIndex == size -1) {
					showPage(--curIndex);
				}else {
					showPage(curIndex);
				}
			}
		}
	}
	public class TurnToFirst implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!pages.isEmpty())
				showPage(0);
			label.setText("1");
		}
	}
	public class TurnToPrevious implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int curIndex = pages.indexOf(curPage);
			showPage(--curIndex);
		}
	}
	public class TurnToNext implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int curIndex = pages.indexOf(curPage);
			showPage(++curIndex);
		}
	}
	public class TurnToLast implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!pages.isEmpty())
				showPage(pages.size()-1);
		}
	}
	public class Save implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//���ԣ�Ĭ�ϴ洢ͼƬ���ļ�·��".//testImages"	,json·��Ϊ".//pages.json"
			//����save��ť�󣬱���pages���ÿ��pagebean,����ÿ��pagebean���images��texts
			//����images��
			//���Ƿ�ı䣨ischanged�������û�ı䣬ȥͼƬ��������Ѱ�Ҷ�Ӧid��ͼƬ��
			//����ҵ��˾Ͳ���д��ͼƬȻ��ɾ��ͼƬ������������ţ�û�ҵ���д��
			//����ı䣬ֱ��д��ͼƬ
			//����texts������ͬ�ϣ�����Ҫ�����ÿ��textArea��ʵ��������gettext����
			ArrayList<ImageIcon> savedImages = ImageStorage.getImages();
			
			for(int pi = 0;pi < pages.size();pi++) {
				//ͼƬ����ز���
				ArrayList<StretchImageLabel> imgLabels = pages.get(pi).getImageLabels();
				for(int ml = 0;ml < imgLabels.size();ml++) {
					StretchImageLabel tmpLabel = imgLabels.get(ml);
					tmpLabel.setStorePath(".//testImages");
					
					StretchImageBean imgbean = tmpLabel.getBean();
					if(!imgbean.isChanged()) {
						Boolean isFound = false;
						for(ImageIcon icon : savedImages) {
							if(icon.getDescription().equals(imgbean.getId())) {
								savedImages.remove(icon);
								isFound = true;
								System.out.println("���ҵ�֮ǰ�洢����û��ĵ���Ƭ");
								break;
							}
						}
						if(!isFound) {  //д��
							tmpLabel.saveImage();
						}
					}else tmpLabel.saveImage();
				}
				
				//���ֵ���ز���
				ArrayList<StretchTextArea> txtAreas = pages.get(pi).getTextAreas();
				for(int ti=0;ti < txtAreas.size();ti++) {
					StretchTextArea tmpta = txtAreas.get(ti);
					tmpta.saveText();
				}
			}
			
			//��������������Խ�ÿ��pagebean������뵽JSONArray����д��json
			JSONArray pagesjson = new JSONArray();
			for(int i =0;i < pages.size();i++) {
				pagesjson.add(pages.get(i).getbean());
			}
			FileOperator.writeJSONArray(".//pages.json", pagesjson);
			//д����Խ��:OK!!!
		}
	}
	
	public PageManager() {
		pages = new ArrayList<Page>();
	}
	
	public static void setCurPage(Page page) {
		curPage = page;
	}
	
	public static Page getCurPage() {
		return curPage;
	}
	
	public void addPage(Page page) {
		pages.add(page);
	}
	
	public void deletePage(Page page) {
		if(pages.contains(page)) {
			pages.remove(page);
		}
	}
	
	public void showPage(int index) {
		if(index >= pages.size() || index < 0) return;
		Page page = pages.get(index);
		BorderUI ui = UIManager.getCurUI();
		ui.relieveCenter();
		ui.setCenterArea(page);
		ui.push();
		page.repaint();
		setCurPage(page);
		
		label.setText((index + 1) + "");
	}
	//���������е����⡣���빦��������ʾ�ڼ�ҳ��Label
	public void setPageLabel(JLabel label) {
		this.label = label;
	}
	
	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}
	
	public ArrayList<Page> getPages(){
		return pages;
	}
}
