package ����;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

import beans.PageBean;
import tools.FileOperator;
import ���.Page;
import ���.PageManager;
import ���.PageManager.ImageCreat;

public class PageUI {
	public static BorderUI create() {
		BorderUI ui = new BorderUI();
		ui.setTitile("��Ჾҳ��");
		
		PageManager pm = new PageManager();
		//���������ҳ�����ݵļ���
		//ͬ����֮ǰ��Ŀ¼���в���
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
		//�ؼ��У��½�ͼƬ��ɾ��ͼƬ���½��ı���ɾ���ı��򣬷�����һҳ��������һҳ��
		//��ʾ��ǰҳ��������һҳ���������һҳ��ɾ����ǰҳ�����������أ�����
		int btnNum = 14;
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
		
		JButton createPage = new JButton("����һҳ");
		createPage.setPreferredSize(btnSize);
		PageManager.PageCreate pc = pm.new PageCreate();
		createPage.addActionListener(pc);
		
		JButton deletePage = new JButton("ɾ����ǰҳ");
		deletePage.setPreferredSize(btnSize);
		PageManager.PageDelete pd = pm.new PageDelete();
		deletePage.addActionListener(pd);
		
		JButton upLoad = new JButton("����������");
		upLoad.setPreferredSize(btnSize);
		PageManager.Save save = pm.new Save();
		upLoad.addActionListener(save);
		
		JButton quit = new JButton("�˳�");
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
