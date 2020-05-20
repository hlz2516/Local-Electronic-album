package ����;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import beans.UserBean;
import tools.FileOperator;
import tools.MD5;
import tools.RootPath;
import ���.InputBar;

public class UserManageUI {
	public static BorderUI create() {
		BorderUI ui = new BorderUI();
		
		ui.setMenuDefault();
		
		JPanel center = new JPanel();
		center.setPreferredSize(ui.getCenterPreferredSize());
		center.setLayout(null);
		
		int eachH = center.getPreferredSize().height / 20;
		
		JLabel text = new JLabel("�޸�����");
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setFont(new Font("΢���ź�",Font.BOLD,32));
		text.setSize(500,eachH*2);
		//me.setBorder(BorderFactory.createLineBorder(Color.black));
		int mex = (center.getPreferredSize().width - text.getWidth()) / 2;
		text.setLocation(mex, eachH);
		center.add(text);
		
		JTextField accountf = new JTextField();
		accountf.setPreferredSize(new Dimension(200, eachH));
		InputBar account = new InputBar("�˺�", accountf);
		int x = (center.getPreferredSize().width - account.getWidth()) / 2;
		account.setLocation(x, eachH*5);
		center.add(account);
		
		JPasswordField psf = new JPasswordField();
		psf.setPreferredSize(new Dimension(200, eachH));
		InputBar ps = new InputBar("������", psf);
		ps.setLocation(x, eachH*7);
		center.add(ps);
		
		JLabel message = new JLabel();
		message.setSize(250,eachH);
		message.setLocation((center.getPreferredSize().width - message.getWidth())/2, eachH*12);
		message.setHorizontalAlignment(JLabel.CENTER);
		center.add(message);
		
		JButton modify = new JButton("�޸�");
		modify.setSize(new Dimension(60,eachH));
		int lx = center.getPreferredSize().width / 2 - 60 - modify.getWidth();
		modify.setLocation(lx, eachH*10);
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����userĿ¼�鿴�Ƿ������һ�û�
				String path = RootPath.getRootDictionary() + "user";
				String curName = null;
				File[] userFiles = new File(path).listFiles();
				int i = 0;
				for(;i<userFiles.length;i++) {
					if(accountf.getText().equals(userFiles[i].getName())) {
						message.setText("");
						curName = accountf.getText();
						break;
					}
				}
				if(i == userFiles.length) {
					message.setText("�����ڸ��û������������룡");
					return;
				}
				//������ڶ�ȡjson���ݵ�userbean
				StringBuilder newPath = new StringBuilder(path);
				newPath.append("\\").append(curName).append("\\").append(curName).append(".json");
				UserBean user = (UserBean) FileOperator.readJSON(newPath.toString(), UserBean.class);
				
				//�ж����������������������Ҫ�����޸�userbean������
				String password = new String(psf.getPassword());
				user.setPassword(MD5.md5(password));
				//��userbeanд��
				FileOperator.writeJSON(newPath.toString(), user);
				message.setText("�޸ĳɹ�");
			}
		});
		center.add(modify);
		
		JButton back = new JButton("����");
		back.setSize(new Dimension(60, eachH));
		int rx = center.getPreferredSize().width / 2 + 60;
		back.setLocation(rx, eachH*10);
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!UIManager.DoesHaveUI("mainui")) {
					BorderUI ui = MainUI.create();
					UIManager.addToMap("mainui", ui);
				}
				UIManager.setCurUI("mainui");
				message.setText("");
			}
		});
		center.add(back);
		
		ui.setCenterArea(center);
		
		ui.setFuncDefault();
		
		return ui;
	}
}
