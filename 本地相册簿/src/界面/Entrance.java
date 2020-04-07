package 界面;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import beans.StretchImageBean;
import beans.StretchTextBean;
import beans.UserBean;
import tools.FileOperator;
import 组件.ImageStorage;
import 组件.MyThread;
import 组件.Page;
import 组件.StretchImageLabel;
import 组件.StretchTextArea;

public class Entrance {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Dimension rect = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(rect.width*2/3,rect.height*2/3);
		frame.setLocationByPlatform(true);
		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		//frame.setLayout(null);
//		ImageStorage.loadImages(".//testImages");
		
		UIManager.setFrame(frame);
		BorderUI coverui = CoverUI.create();
		UIManager.addToMap("coverui", coverui);
		UIManager.setCurUI("coverui");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
