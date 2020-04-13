package ����;

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
import ���.DateChooser;
import ���.InputBar;

public class RegisterUI {
	public static BorderUI create() {
		BorderUI ui = new BorderUI();
		ui.setFuncHeightRate(0.00);
		
		ui.setMenuDefault();
		
		JPanel center = new JPanel();
		center.setPreferredSize(ui.getCenterPreferredSize());
		center.setLayout(null);
		
		int eachH = center.getPreferredSize().height / 20;
		
		//�û���
		int width = ui.getCenterPreferredSize().width;
		JTextField userNamef = new JTextField();
		userNamef.setPreferredSize(new Dimension(200,eachH));
		//userNamef.addKeyListener(new CharLimit(userNamef, 12));
		InputBar userName = new InputBar("�û���", userNamef);
		userName.setLocation((width - userName.getWidth())/2, eachH);
		center.add(userName);
		
		//����
		JPasswordField passwordf = new JPasswordField();
		passwordf.setPreferredSize(new Dimension(200,eachH));
		InputBar password = new InputBar("����",passwordf);
		password.setLocation((width - userName.getWidth())/2, eachH*3);
		center.add(password);
		
		//��������
		JLabel birth = new JLabel();
		birth.setSize(new Dimension(InputBar.getTipWidth(), eachH));
		birth.setText("��������");
		
		DateChooser dateChooser = new DateChooser(center);
		dateChooser.setSize(passwordf.getPreferredSize());
		int x = (width - userName.getWidth())/2;
		dateChooser.setLocation(x + birth.getWidth(), eachH*5);
		birth.setLocation(x+10,eachH*5);
		center.add(birth);
		center.add(dateChooser);
		
		//�Ա�ѡ��
		JRadioButton man = new JRadioButton("��");
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
		JRadioButton woman = new JRadioButton("Ů");
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
		
		//�����
		JTextField mailf = new JTextField();
		mailf.setPreferredSize(new Dimension(200, eachH));
		InputBar mail = new InputBar("�����", mailf);
		mail.setLocation((width - mail.getWidth())/2, eachH*9);
		center.add(mail);
		
		//�绰�����
		JTextField phonef = new JTextField();
		phonef.setPreferredSize(new Dimension(200, eachH));
		InputBar phone = new InputBar("�绰����", phonef);
		phone.setLocation((width - phone.getWidth())/2, eachH*11);
		center.add(phone);
		
		//һ����ʾע�������label
		JLabel msg = new JLabel();
		msg.setSize(80,50);
		msg.setLocation((width - msg.getWidth())/2, eachH*13);
		msg.setVerticalAlignment(JLabel.CENTER);
		center.add(msg);
		
		//ע��
		JButton btn = new JButton("ע��");
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
				msg.setText("ע��ɹ�!");
			}
		});
		center.add(btn);
		
		//����
		JButton back = new JButton("����");
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
