package 界面;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import beans.UserBean;
import tools.FileOperator;
import tools.RootPath;
import 组件.DateChooser;
import 组件.InputBar;

public class RegisterUI {
	public static BorderUI create() {
		BorderUI ui = new BorderUI();
		ui.setFuncHeightRate(0.00);
		
		ui.setMenuDefault();
		
		JPanel center = new JPanel();
		center.setPreferredSize(ui.getCenterPreferredSize());
		center.setLayout(null);
		
		int eachH = center.getPreferredSize().height / 20;
		
		//用户名
		int width = ui.getCenterPreferredSize().width;
		JTextField userNamef = new JTextField();
		userNamef.setPreferredSize(new Dimension(200,eachH));
		//userNamef.addKeyListener(new CharLimit(userNamef, 12));
		InputBar userName = new InputBar("用户名", userNamef);
		userName.setLocation((width - userName.getWidth())/2, eachH);
		center.add(userName);
		
		//密码
		JPasswordField passwordf = new JPasswordField();
		passwordf.setPreferredSize(new Dimension(200,eachH));
		InputBar password = new InputBar("密码",passwordf);
		password.setLocation((width - userName.getWidth())/2, eachH*3);
		center.add(password);
		
		//出生年月
		JLabel birth = new JLabel();
		birth.setSize(new Dimension(InputBar.getTipWidth(), eachH));
		birth.setText("出生日期");
		
		DateChooser dateChooser = new DateChooser(center);
		dateChooser.setSize(passwordf.getPreferredSize());
		int x = (width - userName.getWidth())/2;
		dateChooser.setLocation(x + birth.getWidth(), eachH*5);
		birth.setLocation(x+10,eachH*5);
		center.add(birth);
		center.add(dateChooser);
		
		//性别选择
		JRadioButton man = new JRadioButton("男");
		man.setSize(50,30);
		man.setLocation(password.getLocation().x,eachH*7);
		man.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(man.isSelected()) {
					//System.out.println("man is selected");
				}
			}
		});
		JRadioButton woman = new JRadioButton("女");
		woman.setSize(50,30);
		woman.setLocation(password.getLocation().x + 100, eachH*7);
		woman.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(woman.isSelected()) {
					//System.out.println("woman is selected");
				}
			}
		});
		ButtonGroup group = new ButtonGroup();
		group.add(man);
		group.add(woman);
		center.add(man);
		center.add(woman);
		
		//邮箱绑定
		JTextField mailf = new JTextField();
		mailf.setPreferredSize(new Dimension(200, eachH));
		InputBar mail = new InputBar("邮箱绑定", mailf);
		mail.setLocation((width - mail.getWidth())/2, eachH*9);
		center.add(mail);
		
		//电话号码绑定
		JTextField phonef = new JTextField();
		phonef.setPreferredSize(new Dimension(200, eachH));
		InputBar phone = new InputBar("电话号码", phonef);
		phone.setLocation((width - phone.getWidth())/2, eachH*11);
		center.add(phone);
		
		//一个显示注册情况的label
		JLabel msg = new JLabel();
		msg.setSize(80,50);
		msg.setLocation((width - msg.getWidth())/2, eachH*13);
		msg.setVerticalAlignment(JLabel.CENTER);
		center.add(msg);
		
		//注册
		JButton btn = new JButton("注册");
		btn.setSize(100,30);
		btn.setLocation(center.getPreferredSize().width / 2 - 60 - btn.getWidth(), eachH*15);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(userNamef.getText() == null || userNamef.getText().equals(""))
					return;
				String password = new String(passwordf.getPassword());
				if(password == null || password.equals("")) return;
				if(dateChooser.getSelectedDate() == null) return;
				if(!group.isSelected(man.getModel()) && !group.isSelected(woman.getModel())) 
					return;
				if(mailf.getText() == null || mailf.getText().equals(""))
					return;
				if(phonef.getText() == null || phonef.getText().equals(""))
					return;
				UserBean user = new UserBean();
				user.setName(userNamef.getText());
				user.setPassword(password);
				user.setBirth(dateChooser.getSelectedDate());
				user.setEmail(mailf.getText());
				user.setPhone(phonef.getText());
				
				String rootDic = RootPath.getRootDictionary();
				FileOperator.createFolder(rootDic + "user");
				FileOperator.createFolder(rootDic + "user\\" + user.getName());
				String userMessage = rootDic + "user\\" + user.getName() + "\\" + 
						user.getName() + ".json";
				FileOperator.writeJSON(userMessage, user);
				msg.setText("注册成功!");
			}
		});
		center.add(btn);
		
		//返回
		JButton back = new JButton("返回");
		back.setSize(btn.getSize());
		back.setLocation(center.getPreferredSize().width / 2 + 60, eachH*15);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!UIManager.DoesHaveUI("mainui")) {
					BorderUI tmp = MainUI.create();
					UIManager.addToMap("mainui", tmp);
				}
				UIManager.setCurUI("mainui");
			}
		});
		center.add(back);
		
		ui.setCenterArea(center);
		
		ui.setFuncDefault();
		return ui;
	}
}
