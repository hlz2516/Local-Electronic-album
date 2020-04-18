package 界面;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import 组件.InputBar;

public class UserManageUI {
	public static BorderUI create() {
		BorderUI ui = new BorderUI();
		
		ui.setMenuDefault();
		
		JPanel center = new JPanel();
		center.setPreferredSize(ui.getCenterPreferredSize());
		center.setLayout(null);
		
		int eachH = center.getPreferredSize().height / 20;
		
		JLabel text = new JLabel("修改密码");
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setFont(new Font("微软雅黑",Font.BOLD,32));
		text.setSize(500,eachH*2);
		//me.setBorder(BorderFactory.createLineBorder(Color.black));
		int mex = (center.getPreferredSize().width - text.getWidth()) / 2;
		text.setLocation(mex, eachH);
		center.add(text);
		
		JTextField accountf = new JTextField();
		accountf.setPreferredSize(new Dimension(200, eachH));
		InputBar account = new InputBar("账号", accountf);
		int x = (center.getPreferredSize().width - account.getWidth()) / 2;
		account.setLocation(x, eachH*5);
		center.add(account);
		
		JPasswordField psf = new JPasswordField();
		psf.setPreferredSize(new Dimension(200, eachH));
		InputBar ps = new InputBar("密码", psf);
		ps.setLocation(x, eachH*7);
		center.add(ps);
		
		JButton modify = new JButton("修改");
		modify.setSize(new Dimension(60,eachH));
		int lx = center.getPreferredSize().width / 2 - 60 - modify.getWidth();
		modify.setLocation(lx, eachH*10);
		modify.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 进入user目录查看是否存在这一用户
				
				//如果存在读取json数据到userbean
				
				//判断密码框输入情况，如果符合要求则修改userbean的密码
				
				//将userbean写回
			}
		});
		center.add(modify);
		
		JButton back = new JButton("返回");
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
			}
		});
		center.add(back);
		
		ui.setCenterArea(center);
		
		ui.setFuncDefault();
		
		return ui;
	}
}
