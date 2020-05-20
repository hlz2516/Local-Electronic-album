package ����;

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
import tools.MD5;
import tools.RootPath;
import ���.Cover;
import ���.InputBar;

public class MainUI {
	public static BorderUI create() {
		BorderUI.setMenuHeightRate(0.04);
		BorderUI.setFuncHeightRate(0.001);
		BorderUI ui = new BorderUI();
		
		JPanel menu = new JPanel();
		menu.setPreferredSize(ui.getMenuPreferredSize());
		menu.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btn = new JButton("�˻�����");
		btn.setPreferredSize(new Dimension(100, menu.getPreferredSize().height - 10));
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!UIManager.DoesHaveUI("usermanagerui")) {
					BorderUI ui = UserManageUI.create();
					UIManager.addToMap("usermanagerui", ui);
				}
				UIManager.setCurUI("usermanagerui");
			}
		});
		
		menu.add(btn);
		ui.setMenuArea(menu);
		
		JPanel center = new JPanel();
		center.setPreferredSize(ui.getCenterPreferredSize());
		center.setLayout(null);
		
		int eachH = center.getPreferredSize().height / 20;
		
		JLabel me = new JLabel("��Ჾ����ϵͳ");
		me.setHorizontalAlignment(JLabel.CENTER);
		me.setFont(new Font("΢���ź�",Font.BOLD,32));
		me.setSize(500,eachH*3);
		//me.setBorder(BorderFactory.createLineBorder(Color.black));
		int mex = (center.getPreferredSize().width - me.getWidth()) / 2;
		me.setLocation(mex, eachH);
		center.add(me);
		
		JTextField accountf = new JTextField();
		accountf.setPreferredSize(new Dimension(200, eachH));
		InputBar account = new InputBar("�˺�", accountf);
		int x = (menu.getPreferredSize().width - account.getWidth()) / 2;
		account.setLocation(x, eachH*6);
		center.add(account);
		
		JPasswordField psf = new JPasswordField();
		psf.setPreferredSize(new Dimension(200, eachH));
		InputBar ps = new InputBar("����", psf);
		ps.setLocation(x, eachH*9);
		center.add(ps);
		
		JLabel message = new JLabel();
		message.setSize(new Dimension(250, eachH));
		message.setLocation((menu.getPreferredSize().width - message.getWidth()) / 2, eachH*15);
		message.setHorizontalAlignment(JLabel.CENTER);
		center.add(message);
		
		JButton login = new JButton("��¼");
		login.setSize(60,eachH);
		int lx = center.getPreferredSize().width / 2 - 60 - login.getWidth();
		login.setLocation(lx, eachH*12);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// �г���ǰuserĿ¼�����е��ļ�����������û�и�������˻�����ͬ��
				String rootDic = RootPath.getRootDictionary();
				FileOperator.createFolder(rootDic + "user");
				File file = new File(rootDic + "user");
				File[] users = file.listFiles();
				int i = 0;
				for(;i < users.length;i++) {
					if(users[i].getName().equals(accountf.getText())) {
						//�����ͬ����ȡ�����json�ļ�
						String Path = rootDic + "user\\" + accountf.getText() + "\\" +
						accountf.getText() +".json";
						UserBean user = (UserBean) FileOperator.readJSON(Path, UserBean.class);
						//���json�ļ�����������û��������ͬ�������cover����,���õ�ǰ�û�
						//��¼������������
						String ps = MD5.md5(new String(psf.getPassword()));
						if(user.getPassword().equals(ps)) {
							UserManager.setCurUser(user);
							if(!UIManager.DoesHaveUI("coverui")) {
								BorderUI temp = CoverUI.create();
								UIManager.addToMap("coverui", temp);
							}
							UIManager.setCurUI("coverui");
							
							if(UIManager.DoesHaveUI("mainui")) {
								UIManager.deleteUI("mainui");
							}
//							accountf.setText("");
//							psf.setText("");
//							message.setText("");
						}
						else {
							message.setText("��������������������룡");
							break;
						}
					}
				}
				if(i == users.length) {
					message.setText("�����ڵ�ǰ�û�������ע�ᣡ");
				}
			}
		});
		center.add(login);
		
		JButton register = new JButton("ע��");
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
