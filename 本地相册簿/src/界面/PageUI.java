package 界面;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

import beans.PageBean;
import tools.FileOperator;
import 组件.Cover;
import 组件.ImageStorage;
import 组件.Page;
import 组件.PageManager;
import 组件.PageManager.ImageCreat;

public class PageUI {
	public static BorderUI create() {
		PageManager pm = new PageManager();
		String path = PageManager.getStorePath();
		BorderUI.setMenuHeightRate(0.05);
		BorderUI ui = new BorderUI();
		
		JPanel menu = new JPanel();
		menu.setPreferredSize(ui.getMenuPreferredSize());
		menu.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		int mw = menu.getPreferredSize().width;
		int mh = menu.getPreferredSize().height;
		double leftRate = 0.35,centerRate = 0.35,rightRate = 0.25;
		
		JPanel leftPart = new JPanel();
		leftPart.setPreferredSize(new Dimension((int)(mw*leftRate), mh));
		leftPart.setLayout(new FlowLayout(FlowLayout.LEFT));
		menu.add(leftPart);
		
		int sh = mh - 5;
		JButton createPage = new JButton("创建一页");
		createPage.setPreferredSize(new Dimension((int)(mw*leftRate) /3 , sh));
		PageManager.PageCreate pc = pm.new PageCreate();
		createPage.addActionListener(pc);
		leftPart.add(createPage);
		
		
		JButton deletePage = new JButton("删除当前页");
		deletePage.setPreferredSize(new Dimension((int)(mw*leftRate) /3 , sh));
		PageManager.PageDelete pd = pm.new PageDelete();
		deletePage.addActionListener(pd);
		leftPart.add(deletePage);
		
		JPanel centerPart = new JPanel();
		centerPart.setPreferredSize(new Dimension((int)(mw*centerRate), mh));
		centerPart.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu.add(centerPart);
		
		JLabel title = new JLabel("《" + Cover.getCurCover().getThemeText() + "》");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setPreferredSize(new Dimension(200,sh));
		centerPart.add(title);
		
		JPanel rightPart = new JPanel();
		rightPart.setPreferredSize(new Dimension((int)(mw*rightRate), mh));
		rightPart.setLayout(new FlowLayout(FlowLayout.RIGHT));
		menu.add(rightPart);
		
		JButton quit = new JButton("退出");
		quit.setPreferredSize(new Dimension(60,sh));
		PageManager.Quit q = pm.new Quit();
		quit.addActionListener(q);
		rightPart.add(quit);
		
		ui.setMenuArea(menu);
		
		//在这里进行页面数据的加载
		//同样以之前的目录进行测试
		Page p1 = null;
		System.out.println(PageManager.getStorePath());
		ArrayList<PageBean> pbs = FileOperator.readJSONArray(PageManager.getStorePath() + "\\pages.json", PageBean.class);
		if(pbs != null) { //第N次进相册簿所以加载
			ImageStorage.loadImages(PageManager.getStorePath() + "\\images");
			
			ArrayList<Page> pages = new ArrayList<Page>(pbs.size());
			for(int i =0;i < pbs.size();i++) {
				Page tmp = new Page();
				tmp.setPreferredSize(ui.getCenterPreferredSize());
				tmp.setBean(pbs.get(i));
				System.out.println(pbs.get(i));
				pm.addPage(tmp);
			}
			p1 = pm.getPages().get(0);
		}else { //说明这是第一次进这个相册簿
			//在这个目录下新建一个images文件夹
			FileOperator.createFolder(PageManager.getStorePath() + "\\images");
			System.out.println("第一次进入");
			p1 = new Page();
			p1.setPreferredSize(ui.getCenterPreferredSize());
			pm.addPage(p1);
		}

		pm.setCurPage(p1);
		ui.setCenterArea(p1);
		
		JPanel funcArea = new JPanel();
		funcArea.setPreferredSize(ui.getFuncPreferredSize());
		funcArea.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		//控件有：新建图片框，删除图片框，新建文本框，删除文本框，翻至第一页，翻至上一页，
		//显示当前页，翻至下一页，翻至最后一页，删除当前页，保存至本地，返回
		int btnNum = 11;
		int width = funcArea.getPreferredSize().width / btnNum - 5;
		int height = funcArea.getPreferredSize().height;
		Dimension btnSize = new Dimension(width,height);
		
		JButton createImgBtn= new JButton("创建图片框");
		createImgBtn.setPreferredSize(btnSize);
		PageManager.ImageCreat ic = pm.new ImageCreat();
		createImgBtn.addActionListener(ic);

		JButton deleteImgBtn = new JButton("删除图片框");
		deleteImgBtn.setPreferredSize(btnSize);
		PageManager.ImageDelete id = pm.new ImageDelete();
		deleteImgBtn.addActionListener(id);

		JButton createTextBtn = new JButton("创建文字框");
		createTextBtn.setPreferredSize(btnSize);
		PageManager.TextCreat tc = pm.new TextCreat();
		createTextBtn.addActionListener(tc);

		JButton deleteTextBtn = new JButton("删除文字框");
		deleteTextBtn.setPreferredSize(btnSize);
		PageManager.TextDelete td = pm.new TextDelete();
		deleteTextBtn.addActionListener(td);

		JButton first = new JButton("<<");
		first.setPreferredSize(btnSize);
		PageManager.TurnToFirst ttf = pm.new TurnToFirst();
		first.addActionListener(ttf);
		
		JButton previous = new JButton("<");
		previous.setPreferredSize(btnSize);
		PageManager.TurnToPrevious ttp = pm.new TurnToPrevious();
		previous.addActionListener(ttp);
		
		JLabel curPage = new JLabel("1");
		curPage.setPreferredSize(btnSize);
		curPage.setHorizontalAlignment(JLabel.CENTER);
		pm.setPageLabel(curPage);
		
		JButton next = new JButton(">");
		next.setPreferredSize(btnSize);
		PageManager.TurnToNext ttn = pm.new TurnToNext();
		next.addActionListener(ttn);
		
		JButton last = new JButton(">>");
		last.setPreferredSize(btnSize);
		PageManager.TurnToLast ttl = pm.new TurnToLast();
		last.addActionListener(ttl);
		
		JButton upLoad = new JButton("保存至本地");
		upLoad.setPreferredSize(btnSize);
		PageManager.Save save = pm.new Save();
		upLoad.addActionListener(save);
		
		JButton check = new JButton("check");
		check.setPreferredSize(btnSize);
		PageManager.checkBean cb = pm.new checkBean();
		check.addActionListener(cb);
		
		funcArea.add(createImgBtn);
		funcArea.add(deleteImgBtn);
		funcArea.add(createTextBtn);
		funcArea.add(deleteTextBtn);
		funcArea.add(first);
		funcArea.add(previous);
		funcArea.add(curPage);
		funcArea.add(next);
		funcArea.add(last);
		//funcArea.add(createPage);
		//funcArea.add(deletePage);
		funcArea.add(upLoad);
		//funcArea.add(quit);
		funcArea.add(check);
		ui.setFuncArea(funcArea);
		
		return ui;
	}
}
