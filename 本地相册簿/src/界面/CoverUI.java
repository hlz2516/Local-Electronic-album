package 界面;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.CoverBean;
import beans.UserManager;
import tools.DataRegister;
import tools.FileOperator;
import tools.RootPath;
import 组件.Cover;
import 组件.CoverEditor;
import 组件.GridFrame;
import 组件.ImageStorage;
import 组件.MyThread;
import 组件.PageManager;

import com.alibaba.fastjson.JSONArray;

public class CoverUI {
	public static BorderUI create() {
		BorderUI.setMenuHeightRate(0.001);
		BorderUI.setFuncHeightRate(0.08);
		BorderUI ui = new BorderUI();
		ui.setMenuDefault();
		
		Dimension d = ui.getCenterPreferredSize();
		GridFrame grid = new GridFrame(3,8,d);
		//grid.setBackground(Color.red);
		ui.setCenterArea(grid);
		
		//加载相册簿封面集
		String rootDic = RootPath.getRootDictionary();
		String path = rootDic + "user\\" + UserManager.getCurUser().getName(); //测试用户为test
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
		func.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		
		Dimension btnSize = new Dimension(120, func.getPreferredSize().height - 10);
		
		JButton createbtn = new JButton("创建相册簿");
		createbtn.setPreferredSize(btnSize);
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
		
		JButton savebtn = new JButton("save");;
		
		JButton deleteBtn = new JButton("删除相册簿");
		deleteBtn.setPreferredSize(btnSize);
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//弹出对话框提示用户确认是否删除
			  int res=JOptionPane.showConfirmDialog(null, "删除该相册簿后将无法还原，请问您确定吗？",
					  "删除确认", JOptionPane.YES_NO_OPTION);
              if(res==JOptionPane.YES_OPTION){ 
    			Cover curCover = Cover.getCurCover();
    			//System.out.println(curCover.getBean());
  				// 删除选中的相册簿
  				grid.deleteCover(curCover);
  		
  				savebtn.doClick();
  				
			    //逻辑上，会把该相册簿文件夹下所有文件删除，以及相册簿封面管理更新(删除covers列表里对应的cover)
				String userName = UserManager.getCurUser().getName();
				String id = curCover.getBean().getCoverId();
				String path = RootPath.getRootDictionary() + "user\\" + userName + 
						"\\" + id;
				FileOperator.deleteFiles(path);
  				
              }else{
                  return;
              } 
			}
		});
		func.add(deleteBtn);
		
//		JButton copybtn = new JButton("copy");
//		copybtn.setPreferredSize(new Dimension(100,30));
//		copybtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 复制当前位置的cover给相邻前面的一个，注意：复制操作只复制图形，设计这样的操作是为了方便交换
//				ArrayList<Cover> covers = grid.getCovers();
//				int curIndex = covers.indexOf(Cover.getCurCover());
//				grid.copy(curIndex, curIndex-1);
//			}
//		});
//		func.add(copybtn);
		
		JButton swapfbtn = new JButton("向前移动");
		swapfbtn.setPreferredSize(btnSize);
		swapfbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 只交换选中的和其相邻前面的一个相册簿
				ArrayList<Cover> covers = grid.getCovers();
				int curIndex = covers.indexOf(Cover.getCurCover());
				if(curIndex == -1 || curIndex == 0) return;
				grid.swap(curIndex-1, curIndex);
				Cover curCover = covers.get(curIndex-1);
				Cover.setCurCover(curCover);
			}
		});
		func.add(swapfbtn);
		
		JButton swapbbtn = new JButton("向后移动");
		swapbbtn.setPreferredSize(btnSize);
		swapbbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 只交换选中的和其相邻后面的一个相册簿
				ArrayList<Cover> covers = grid.getCovers();
				int curIndex = covers.indexOf(Cover.getCurCover());
				if(curIndex == -1 || curIndex == grid.getCoverSize() -1) return;
				grid.swap(curIndex, curIndex + 1);
				Cover curCover = covers.get(curIndex+1);
				Cover.setCurCover(curCover);
			}
		});
		func.add(swapbbtn);
		
		//save按钮的声明在前面，因为前面有用到
		savebtn.setPreferredSize(btnSize);
		savebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Cover> covers = grid.getCovers();
				//设置cover的存储路径
//				String path = ".//user//test";
				FileOperator.createFolder(path);
				Cover.setStorePath(path);
				FileOperator.createFolder(path + "\\covers");
				//遍历covers，找出其id，分别在用户名目录下创建文件夹和往covers里写入jpg，
				//然后往jsonarray里加入其bean
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
				//在用户名目录下创建covers.json，然后写入jsonarray
				FileOperator.writeJSON(path + "\\covers.json", jArray);
			}
		});
		func.add(savebtn);
		
		JButton enter = new JButton();
		enter.setPreferredSize(btnSize);
		enter.setText("进入相册");
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/***************************/
				savebtn.doClick();
				Cover curCover = Cover.getCurCover();
				if(curCover == null) {
					System.out.println("请先选中相册簿簿封面");
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
		
		JButton cancel = new JButton("注销用户");
		cancel.setPreferredSize(btnSize);
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!UIManager.DoesHaveUI("mainui")) {
					BorderUI ui = MainUI.create();
					UIManager.addToMap("mainui", ui);
				}
				UIManager.setCurUI("mainui");
				if(UIManager.DoesHaveUI("coverui")) {
					UIManager.deleteUI("coverui");
				}
			}
		});
		func.add(cancel);
		
		ui.setFuncArea(func);
		
		return ui;
	}
}
