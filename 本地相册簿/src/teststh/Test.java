package teststh;

import java.awt.Dimension;
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
		JButton[] coms = new JButton[2];
		
		coms[0] = new JButton("ϲ��");
		coms[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("�������ϲ��");
			}
		});
		
		coms[1] = new JButton("��ϲ��");
		coms[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("����Ĳ�ϲ��");
			}
		});
		
		int res = JOptionPane.showOptionDialog(null, "��ϲ����ô", "��ش�", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, coms, coms[0]);
		System.out.println(res);
	}
}
