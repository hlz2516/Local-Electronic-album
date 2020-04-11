package ����;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import beans.CoverBean;
import beans.UserManager;
import tools.DataRegister;
import tools.FileOperator;
import tools.RootPath;
import ���.Cover;
import ���.CoverEditor;
import ���.GridFrame;
import ���.ImageStorage;
import ���.MyThread;
import ���.PageManager;

import com.alibaba.fastjson.JSONArray;

public class CoverUI {
	public static BorderUI create() {
		BorderUI.setMenuHeightRate(0.001);
		BorderUI.setFuncHeightRate(0.08);
		BorderUI ui = new BorderUI();
		ui.setMenuDefault();
		
		Dimension d = ui.getCenterPreferredSize();
		GridFrame grid = new GridFrame(3,8,d);
		grid.setBackground(Color.red);
		ui.setCenterArea(grid);
		
		//������Ჾ���漯
		String rootDic = RootPath.getRootDictionary();
		String path = rootDic + "user\\" + UserManager.getCurUser().getName(); //�����û�Ϊtest
		Cover.setStorePath(path + "\\covers");
		ArrayList<CoverBean> covers = FileOperator.readJSONArray(path + "\\covers.json", CoverBean.class);
		if(covers != null) {
			for(int i = 0;i < covers.size();i++) {
				Cover tmp = new Cover();
				tmp.setBean(covers.get(i));
				grid.addCover(tmp);
			}
			
		}

		JPanel func = new JPanel();
		func.setPreferredSize(ui.getFuncPreferredSize());
		func.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		
		JButton createbtn = new JButton("������Ჾ");
		createbtn.setPreferredSize(new Dimension(100,30));
		createbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//grid.addCover(new Cover());
				CoverEditor editor = new CoverEditor(UIManager.getFrame());
				MyThread.setCurThread(Thread.currentThread());
				MyThread t = new MyThread(editor);
				t.start();
				
				try {
					Thread.sleep(9999999999999999L);
				} catch (Exception e2) {
					System.out.println("���߳��ѻ���");
				}
				if(DataRegister.getTheme() == null && 
						DataRegister.getImagePath() == null && 
						DataRegister.getBriefIntro() == null) {
					
				}else {
					Cover tmp = new Cover();
					grid.addCover(tmp);
					tmp.setThemeText(DataRegister.getTheme());
					tmp.setImagePath(DataRegister.getImagePath());
					tmp.showImage();
					tmp.setBriefText(DataRegister.getBriefIntro());
					grid.validate();
				}
				DataRegister.setChanged(false);
			}
		});
		func.add(createbtn);
		
		JButton deleteBtn = new JButton("ɾ����Ჾ");
		deleteBtn.setPreferredSize(new Dimension(100,30));
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ɾ��ѡ�е���Ჾ
				Cover curCover = Cover.getCurCover();
				grid.deleteCover(curCover);
				//System.out.println(grid.getsize());
			}
		});
		func.add(deleteBtn);
		
//		JButton copybtn = new JButton("copy");
//		copybtn.setPreferredSize(new Dimension(100,30));
//		copybtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// ���Ƶ�ǰλ�õ�cover������ǰ���һ����ע�⣺���Ʋ���ֻ����ͼ�Σ���������Ĳ�����Ϊ�˷��㽻��
//				ArrayList<Cover> covers = grid.getCovers();
//				int curIndex = covers.indexOf(Cover.getCurCover());
//				grid.copy(curIndex, curIndex-1);
//			}
//		});
//		func.add(copybtn);
		
		JButton swapfbtn = new JButton("��ǰ�ƶ�");
		swapfbtn.setPreferredSize(new Dimension(100, 30));
		swapfbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ֻ����ѡ�еĺ�������ǰ���һ����Ჾ
				ArrayList<Cover> covers = grid.getCovers();
				int curIndex = covers.indexOf(Cover.getCurCover());
				if(curIndex == -1 || curIndex == 0) return;
				grid.swap(curIndex-1, curIndex);
				Cover curCover = covers.get(curIndex-1);
				Cover.setCurCover(curCover);
			}
		});
		func.add(swapfbtn);
		
		JButton swapbbtn = new JButton("����ƶ�");
		swapbbtn.setPreferredSize(new Dimension(100, 30));
		swapbbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ֻ����ѡ�еĺ������ں����һ����Ჾ
				ArrayList<Cover> covers = grid.getCovers();
				int curIndex = covers.indexOf(Cover.getCurCover());
				if(curIndex == -1 || curIndex == grid.getCoverSize() -1) return;
				grid.swap(curIndex, curIndex + 1);
				Cover curCover = covers.get(curIndex+1);
				Cover.setCurCover(curCover);
			}
		});
		func.add(swapbbtn);
		
		JButton savebtn = new JButton("save");
		savebtn.setPreferredSize(new Dimension(100, 30));
		savebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Cover> covers = grid.getCovers();
				//����cover�Ĵ洢·��
//				String path = ".//user//test";
				FileOperator.createFolder(path);
				Cover.setStorePath(path);
				FileOperator.createFolder(path + "\\covers");
				//����covers���ҳ���id���ֱ����û���Ŀ¼�´����ļ��к���covers��д��jpg��
				//Ȼ����jsonarray�������bean
				JSONArray jArray = new JSONArray();
				for(int i =0;i < covers.size();i++) {
					Cover curCover = covers.get(i);
					CoverBean curbean = curCover.getBean();
					String id = curbean.getCoverId();
					FileOperator.createFolder(path + "\\" + id);
					String source = null;
					if((source = curCover.getImagePath()) != null) {
						if(source.equals(path + "\\covers\\" + id + ".jpg")) {
							jArray.add(curbean);
							continue;
						}
						FileOperator.copyFile(source, path + "\\covers\\" + id + ".jpg");
					}
					jArray.add(curbean);
				}
				//���û���Ŀ¼�´���covers.json��Ȼ��д��jsonarray
				FileOperator.writeJSON(path + "\\covers.json", jArray);
			}
		});
		func.add(savebtn);
		
		JButton enter = new JButton();
		enter.setPreferredSize(new Dimension(200,ui.getFuncPreferredSize().height));
		enter.setText("�������");
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/***************************/
				savebtn.doClick();
				Cover curCover = Cover.getCurCover();
				if(curCover == null) {
					System.out.println("����ѡ����Ჾ������");
					return;
				}
				String curId = curCover.getBean().getCoverId();
				
				PageManager.setStorePath(path + "\\" + curId);
				
				/**************************/
				if(!UIManager.DoesHaveUI("pageui")) {
					BorderUI temp = PageUI.create();
					UIManager.addToMap("pageui", temp);
				}
				UIManager.setCurUI("pageui");
			}
		});
		func.add(enter);
		
		ui.setFuncArea(func);
		
		return ui;
	}
}
