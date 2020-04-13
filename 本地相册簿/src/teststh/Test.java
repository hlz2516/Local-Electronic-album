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

import 界面.BorderUI;
import 界面.CoverUI;
import 界面.UIManager;
import 组件.ImageStorage;

//本包中的东西均是为了实验测试一些东西，各位看官可略过
public class Test {  
	public static void main(String[] args) {
		JButton[] coms = new JButton[2];
		
		coms[0] = new JButton("喜欢");
		coms[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("我是真的喜欢");
			}
		});
		
		coms[1] = new JButton("不喜欢");
		coms[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("是真的不喜欢");
			}
		});
		
		int res = JOptionPane.showOptionDialog(null, "你喜欢我么", "请回答", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, coms, coms[0]);
		System.out.println(res);
	}
}
