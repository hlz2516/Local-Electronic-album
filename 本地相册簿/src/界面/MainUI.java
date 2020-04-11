package 界面;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import beans.UserBean;
import beans.UserManager;
import tools.FileOperator;
import tools.RootPath;
import 组件.Cover;
import 组件.InputBar;

public class MainUI {
	public static BorderUI create() {
		BorderUI.setFuncHeightRate(0.001);
		BorderUI ui = new BorderUI();
		
		JPanel menu = new JPanel();
		menu.setPreferredSize(ui.getMenuPreferredSize());
		menu.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btn = new JButton("账户管理");
		btn.setPreferredSize(new Dimension(100, menu.getPreferredSize().height - 10));
		
		menu.add(btn);
		ui.setMenuArea(menu);
		
		JPanel center = new JPanel();
		center.setPreferredSize(ui.getCenterPreferredSize());
		center.setLayout(null);
		
		int eachH = center.getPreferredSize().height / 20;
		
		JLabel me = new JLabel("相册簿管理系统");
		me.setHorizontalAlignment(JLabel.CENTER);
		me.setFont(new Font("微软雅黑",Font.BOLD,32));
		me.setSize(500,eachH*3);
		//me.setBorder(BorderFactory.createLineBorder(Color.black));
		int mex = (center.getPreferredSize().width - me.getWidth()) / 2;
		me.setLocation(mex, eachH);
		center.add(me);
		
		JTextField accountf = new JTextField();
		accountf.setPreferredSize(new Dimension(200, eachH));
		InputBar account = new InputBar("账号", accountf);
		int x = (menu.getPreferredSize().width - account.getWidth()) / 2;
		account.setLocation(x, eachH*6);
		center.add(account);
		
		JPasswordField psf = new JPasswordField();
		psf.setPreferredSize(new Dimension(200, eachH));
		InputBar ps = new InputBar("密码", psf);
		ps.setLocation(x, eachH*9);
		center.add(ps);
		
		JButton login = new JButton("登录");
		login.setSize(60,eachH);
		int lx = center.getPreferredSize().width / 2 - 60 - login.getWidth();
		login.setLocation(lx, eachH*12);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 列出当前user目录下所有的文件夹名，看有没有跟输入的账户名相同的
				String rootDic = RootPath.getRootDictionary();
				FileOperator.createFolder(rootDic + "user");
				File file = new File(rootDic + "user");
				File[] users = file.listFiles();
				for(int i =0;i < users.length;i++) {
					if(users[i].getName().equals(accountf.getText())) {
						//如果相同，读取里面的json文件
						String Path = rootDic + "user\\" + accountf.getText() + "\\" +
						accountf.getText() +".json";
						UserBean user = (UserBean) FileOperator.readJSON(Path, UserBean.class);
						//如果json文件里的密码与用户输入的相同，则进入cover界面,设置当前用户
						//登录界面的输入清空
						String ps = new String(psf.getPassword());
						if(user.getPassword().equals(ps)) {
							UserManager.setCurUser(user);
							if(!UIManager.DoesHaveUI("coverui")) {
								BorderUI temp = CoverUI.create();
								UIManager.addToMap("coverui", temp);
							}
							UIManager.setCurUI("coverui");
							
							accountf.setText("");
							psf.setText("");
						}
					}
				}
				
			}
		});
		center.add(login);
		
		JButton register = new JButton("注册");
		register.setSize(60,eachH);
		int rx = center.getPreferredSize().width / 2 + 60;
		register.setLocation(rx, eachH*12);
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!UIManager.DoesHaveUI("registerui")) {
					BorderUI temp = RegisterUI.create();
					UIManager.addToMap("registerui", temp);
				}
				UIManager.setCurUI("registerui");
			}
		});
		center.add(register);
		
		ui.setCenterArea(center);
		
		ui.setFuncDefault();
		return ui;
	}
}
