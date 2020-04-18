package teststh;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
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
		frame.setSize(800,600);
		
		JLabel show = new JLabel();
		show.setHorizontalAlignment(JLabel.CENTER);
		frame.add(show);
		
		JButton btn = new JButton("弹出对话框");
		btn.setPreferredSize(new Dimension(100,30));
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QDialog d = new QDialog();
				Thread t1 = Thread.currentThread();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while(Name.getName() == null) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						t1.interrupt();
					}
				}).start();
				try {
					t1.sleep(999999999999999L);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					System.out.println("主线程已唤醒");
					show.setText("你好，" + Name.getName());
					Name.setName(null);
				}
			}
		});
		frame.add(btn,BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class QDialog extends JDialog{
	public QDialog() {
		this.setSize(500,100);
		setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		setLayout(new FlowLayout());
		
		JLabel tip = new JLabel("请输入名字:");
		tip.setPreferredSize(new Dimension(100,30));
		this.add(tip);
		
		JTextField input = new JTextField();
		input.setPreferredSize(new Dimension(200,30));
		this.add(input);
		
		JButton send = new JButton("发送");
		send.setPreferredSize(new Dimension(60,30));
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Name.setName(input.getText());
				QDialog me = QDialog.this;
				me.dispose();
			}
		});
		this.add(send);
		
		this.setVisible(true);
	}
} 

class Name{
	private static String name;
	public static void setName(String name) {
		Name.name = name;
	}
	public static String getName() {
		return name;
	}
}