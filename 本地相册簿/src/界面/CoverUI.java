package 界面;

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
import tools.DataRegister;
import tools.FileOperator;
import 组件.Cover;
import 组件.CoverEditor;
import 组件.GridFrame;
import 组件.ImageStorage;
import 组件.MyThread;
import 组件.PageManager;

import com.alibaba.fastjson.JSONArray;

public class CoverUI {
	public static BorderUI create() {
		BorderUI ui = new BorderUI();
		
		ui.setTitile("相册簿封面集");
		
		Dimension d = ui.getCenterPreferredSize();
		GridFrame grid = new GridFrame(3,8,d);
		grid.setBackground(Color.red);
		ui.setCenterArea(grid);
		
		//加载相册簿封面集
		String path = ".//user//test"; //测试用户为test
		Cover.setStorePath(path + "//covers");
		ArrayList<CoverBean> covers = FileOperator.readJSONArray(path + "//covers.json", CoverBean.class);
		for(int i = 0;i < covers.size();i++) {
			Cover tmp = new Cover();
			tmp.setBean(covers.get(i));
			grid.addCover(tmp);
		}
		
		JPanel func = new JPanel();
		func.setPreferredSize(ui.getFuncPreferredSize());
		func.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		
		JButton createbtn = new JButton("create");
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
					System.out.println("主线程已唤醒");
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
		
		JButton createCSbtn = new JButton("create covers");
		createCSbtn.setPreferredSize(new Dimension(120, 30));
		createCSbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Cover> covers = new ArrayList<Cover>();
				for(int i = 0;i < 3;i++) {
					covers.add(new Cover());
				}
				grid.addCovers(covers);
			}
		});
		func.add(createCSbtn);
		
		JButton deleteBtn = new JButton("delete");
		deleteBtn.setPreferredSize(new Dimension(100,30));
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 删除最后一个元素
				int index = grid.getCoverSize() - 1;
				grid.deleteCover(index);
				//System.out.println(grid.getsize());
			}
		});
		func.add(deleteBtn);
		
		JButton copybtn = new JButton("copy");
		copybtn.setPreferredSize(new Dimension(100,30));
		copybtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 复制最后一个位置的cover给倒数第二个，注意：复制操作只复制图形，设计这样的操作是为了方便交换
				int index = grid.getCoverSize() - 1;
				grid.copy(index, index-1);
			}
		});
		func.add(copybtn);
		
		JButton swapbtn = new JButton("swap");
		swapbtn.setPreferredSize(new Dimension(100, 30));
		swapbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 只交换最后一个和倒数第二个
				int index = grid.getCoverSize() - 1;
				grid.swap(index-1, index);
			}
		});
		func.add(swapbtn);
		
		JButton savebtn = new JButton("save");
		savebtn.setPreferredSize(new Dimension(100, 30));
		savebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Cover> covers = grid.getCovers();
				//设置cover的存储路径
//				String path = ".//user//test";
				FileOperator.createFolder(path);
				Cover.setStorePath(path);
				FileOperator.createFolder(path + "//covers");
				//遍历covers，找出其id，分别在用户名目录下创建文件夹和往covers里写入jpg，
				//然后往jsonarray里加入其bean
				JSONArray jArray = new JSONArray();
				for(int i =0;i < covers.size();i++) {
					Cover curCover = covers.get(i);
					CoverBean curbean = curCover.getBean();
					String id = curbean.getCoverId();
					FileOperator.createFolder(path + "//" + id);
					String source = null;
					if((source = curCover.getImagePath()) != null) {
						if(source.equals(path + "//covers//" + id + ".jpg")) {
							jArray.add(curbean);
							continue;
						}
						FileOperator.copyFile(source, path + "//covers//" + id + ".jpg");
					}
					jArray.add(curbean);
				}
				//在用户名目录下创建covers.json，然后写入jsonarray
				FileOperator.writeJSON(path + "//covers.json", jArray);
			}
		});
		func.add(savebtn);
		
		JButton enter = new JButton();
		enter.setPreferredSize(new Dimension(200,ui.getFuncPreferredSize().height));
		enter.setText("进入相册");
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/***************************/
				savebtn.doClick();
				Cover curCover = Cover.getCurCover();
				String curId = curCover.getBean().getCoverId();
				
				PageManager.setStorePath(path + "//" + curId);
				
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
