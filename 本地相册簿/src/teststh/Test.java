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

import ����.BorderUI;
import ����.CoverUI;
import ����.UIManager;
import ���.ImageStorage;

//�����еĶ�������Ϊ��ʵ�����һЩ��������λ���ٿ��Թ�
public class Test {  
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800,600);
		
		JLabel show = new JLabel();
		show.setHorizontalAlignment(JLabel.CENTER);
		frame.add(show);
		
		JButton btn = new JButton("�����Ի���");
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
					System.out.println("���߳��ѻ���");
					show.setText("��ã�" + Name.getName());
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
		
		JLabel tip = new JLabel("����������:");
		tip.setPreferredSize(new Dimension(100,30));
		this.add(tip);
		
		JTextField input = new JTextField();
		input.setPreferredSize(new Dimension(200,30));
		this.add(input);
		
		JButton send = new JButton("����");
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