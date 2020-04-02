package 组件;

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
import 界面.BorderUI;
import 界面.UIManager;
//该类实现页面内的编辑，页面的管理，页面的存储与加载
public class PageManager {
	private static Page curPage;
	private ArrayList<Page> pages;
	private JLabel label;
	//利用成员内部类可以操作当前页面对象，以下类内方法均是对页面内的各种操作
	//封装成类可以供外部控件调用，就能实现动态地对当前页面内部进行各种操作
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
	//以下内部类实现对页面的管理
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
			//测试：默认存储图片的文件路径".//testImages"	,json路径为".//pages.json"
			//按下save按钮后，遍历pages里的每个pagebean,引出每个pagebean里的images和texts
			//遍历images，
			//看是否改变（ischanged），如果没改变，去图片加载器里寻找对应id的图片，
			//如果找到了就不用写回图片然后删除图片加载器里的那张，没找到就写回
			//如果改变，直接写回图片
			//对于texts，遍历同上，不过要将获得每个textArea的实例并调用gettext方法
			ArrayList<ImageIcon> savedImages = ImageStorage.getImages();
			
			for(int pi = 0;pi < pages.size();pi++) {
				//图片的相关操作
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
								System.out.println("已找到之前存储过且没变的的照片");
								break;
							}
						}
						if(!isFound) {  //写回
							tmpLabel.saveImage();
						}
					}else tmpLabel.saveImage();
				}
				
				//文字的相关操作
				ArrayList<StretchTextArea> txtAreas = pages.get(pi).getTextAreas();
				for(int ti=0;ti < txtAreas.size();ti++) {
					StretchTextArea tmpta = txtAreas.get(ti);
					tmpta.saveText();
				}
			}
			
			//在上述操作后可以将每个pagebean对象加入到JSONArray里，最后写入json
			JSONArray pagesjson = new JSONArray();
			for(int i =0;i < pages.size();i++) {
				pagesjson.add(pages.get(i).getbean());
			}
			FileOperator.writeJSONArray(".//pages.json", pagesjson);
			//写入测试结果:OK!!!
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
	//这里的设计有点问题。传入功能区的显示第几页的Label
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
