package teststh;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import 界面.BorderUI;
import 界面.CoverUI;
import 界面.UIManager;
import 组件.ImageStorage;

//本包中的东西均是为了实验测试一些东西，各位看官可略过
public class Test {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Dimension rect = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(rect.width*2/3,rect.height*2/3);
		frame.setLocationByPlatform(true);
		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setLayout(null);
		
		JTextField name = new JTextField();
		name.setSize(100,30);
		name.setLocation(400, 200);
		frame.add(name);
		
		JTextField birth = new JTextField();
		birth.setSize(100,30);
		birth.setLocation(600, 200);
		frame.add(birth);
		
		JButton createbtn = new JButton("create");
		createbtn.setSize(100,30);
		createbtn.setLocation(200, 200);
		createbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyDialog d = new MyDialog(frame);
				User user = User.getInstance();
				Thread maint = Thread.currentThread();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while((user.getName()==null) && (user.getBirth()==null)) {
							if(d.isDisplayable()) break;
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						maint.interrupt();
						//System.out.println(d);
					}
				}).start();
				try {
					maint.sleep(9999999999999L);
				} catch (InterruptedException e1) {
					//e1.printStackTrace();
					//System.out.println("主线程已唤醒");
				}
				name.setText(user.getName());
				birth.setText(user.getBirth());
				user.setNullName();
				user.setNullBirth();
			}
		});
		frame.add(createbtn);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

//class Mythread extends Thread{
//	public void run() {
//		
//		
//	}
//}
