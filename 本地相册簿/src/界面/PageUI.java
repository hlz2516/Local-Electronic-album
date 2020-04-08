package ����;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

import beans.PageBean;
import tools.FileOperator;
import ���.Cover;
import ���.ImageStorage;
import ���.Page;
import ���.PageManager;
import ���.PageManager.ImageCreat;

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
		JButton createPage = new JButton("����һҳ");
		createPage.setPreferredSize(new Dimension((int)(mw*leftRate) /3 , sh));
		PageManager.PageCreate pc = pm.new PageCreate();
		createPage.addActionListener(pc);
		leftPart.add(createPage);
		
		
		JButton deletePage = new JButton("ɾ����ǰҳ");
		deletePage.setPreferredSize(new Dimension((int)(mw*leftRate) /3 , sh));
		PageManager.PageDelete pd = pm.new PageDelete();
		deletePage.addActionListener(pd);
		leftPart.add(deletePage);
		
		JPanel centerPart = new JPanel();
		centerPart.setPreferredSize(new Dimension((int)(mw*centerRate), mh));
		centerPart.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu.add(centerPart);
		
		JLabel title = new JLabel("��" + Cover.getCurCover().getThemeText() + "��");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setPreferredSize(new Dimension(200,sh));
		centerPart.add(title);
		
		JPanel rightPart = new JPanel();
		rightPart.setPreferredSize(new Dimension((int)(mw*rightRate), mh));
		rightPart.setLayout(new FlowLayout(FlowLayout.RIGHT));
		menu.add(rightPart);
		
		JButton quit = new JButton("�˳�");
		quit.setPreferredSize(new Dimension(60,sh));
		PageManager.Quit q = pm.new Quit();
		quit.addActionListener(q);
		rightPart.add(quit);
		
		ui.setMenuArea(menu);
		
		//���������ҳ�����ݵļ���
		//ͬ����֮ǰ��Ŀ¼���в���
		Page p1 = null;
		System.out.println(PageManager.getStorePath());
		ArrayList<PageBean> pbs = FileOperator.readJSONArray(PageManager.getStorePath() + "\\pages.json", PageBean.class);
		if(pbs != null) { //��N�ν���Ჾ���Լ���
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
		}else { //˵�����ǵ�һ�ν������Ჾ
			//�����Ŀ¼���½�һ��images�ļ���
			FileOperator.createFolder(PageManager.getStorePath() + "\\images");
			System.out.println("��һ�ν���");
			p1 = new Page();
			p1.setPreferredSize(ui.getCenterPreferredSize());
			pm.addPage(p1);
		}

		pm.setCurPage(p1);
		ui.setCenterArea(p1);
		
		JPanel funcArea = new JPanel();
		funcArea.setPreferredSize(ui.getFuncPreferredSize());
		funcArea.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		//�ؼ��У��½�ͼƬ��ɾ��ͼƬ���½��ı���ɾ���ı��򣬷�����һҳ��������һҳ��
		//��ʾ��ǰҳ��������һҳ���������һҳ��ɾ����ǰҳ�����������أ�����
		int btnNum = 11;
		int width = funcArea.getPreferredSize().width / btnNum - 5;
		int height = funcArea.getPreferredSize().height;
		Dimension btnSize = new Dimension(width,height);
		
		JButton createImgBtn= new JButton("����ͼƬ��");
		createImgBtn.setPreferredSize(btnSize);
		PageManager.ImageCreat ic = pm.new ImageCreat();
		createImgBtn.addActionListener(ic);

		JButton deleteImgBtn = new JButton("ɾ��ͼƬ��");
		deleteImgBtn.setPreferredSize(btnSize);
		PageManager.ImageDelete id = pm.new ImageDelete();
		deleteImgBtn.addActionListener(id);

		JButton createTextBtn = new JButton("�������ֿ�");
		createTextBtn.setPreferredSize(btnSize);
		PageManager.TextCreat tc = pm.new TextCreat();
		createTextBtn.addActionListener(tc);

		JButton deleteTextBtn = new JButton("ɾ�����ֿ�");
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
		
		JButton upLoad = new JButton("����������");
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
