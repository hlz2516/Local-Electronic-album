package 界面;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

import beans.PageBean;
import tools.FileOperator;
import 组件.Page;
import 组件.PageManager;
import 组件.PageManager.ImageCreat;

public class PageUI {
	public static BorderUI create() {
		BorderUI ui = new BorderUI();
		ui.setTitile("相册簿页面");
		
		PageManager pm = new PageManager();
		//在这里进行页面数据的加载
		//同样以之前的目录进行测试
		Page p1 = null;
		ArrayList<PageBean> pbs = FileOperator.readJSONArray(".//pages.json", PageBean.class);
		if(pbs != null) {
			ArrayList<Page> pages = new ArrayList<Page>(pbs.size());
			for(int i =0;i < pbs.size();i++) {
				Page tmp = new Page();
				tmp.setPreferredSize(ui.getCenterPreferredSize());
				tmp.setBean(pbs.get(i));
				pm.addPage(tmp);
			}
			p1 = pm.getPages().get(0);
		}else {
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
		int btnNum = 14;
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
		
		JButton createPage = new JButton("创建一页");
		createPage.setPreferredSize(btnSize);
		PageManager.PageCreate pc = pm.new PageCreate();
		createPage.addActionListener(pc);
		
		JButton deletePage = new JButton("删除当前页");
		deletePage.setPreferredSize(btnSize);
		PageManager.PageDelete pd = pm.new PageDelete();
		deletePage.addActionListener(pd);
		
		JButton upLoad = new JButton("保存至本地");
		upLoad.setPreferredSize(btnSize);
		PageManager.Save save = pm.new Save();
		upLoad.addActionListener(save);
		
		JButton quit = new JButton("退出");
		quit.setPreferredSize(btnSize);
		
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
		funcArea.add(createPage);
		funcArea.add(deletePage);
		funcArea.add(upLoad);
		funcArea.add(quit);
		funcArea.add(check);
		ui.setFuncArea(funcArea);
		
		return ui;
	}
}
