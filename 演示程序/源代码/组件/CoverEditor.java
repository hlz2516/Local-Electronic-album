package 组件;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tools.DataRegister;
import tools.ImageChooser;
import 界面.BorderUI;
import 界面.CoverUI;
import 界面.UIManager;

public class CoverEditor extends JDialog{
	public CoverEditor(JFrame parent) {
		super(parent);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setLayout(null);
		Dimension size = new Dimension(500,600);
		this.setSize(size);
		this.setTitle("编辑您的相册簿封面");
		
		int rows = 20;
		int eachH =  size.height / rows;
		
		JTextField themef = new JTextField();
		themef.setPreferredSize(new Dimension(150,eachH));
		InputBar theme = new InputBar("主题", themef);
		int x = (size.width - theme.getSize().width) / 2 - InputBar.getTipWidth() / 2;  
		theme.setLocation(x, eachH*2);
		this.add(theme);
		
		JTextField imagef = new JTextField();
		imagef.setPreferredSize(new Dimension(150,eachH));
		InputBar imageIn = new InputBar("图片路径", imagef);
		imageIn.setLocation(x, eachH*5);
		this.add(imageIn);
		
		JButton choose = new JButton("选择图片");
		choose.setSize(100,eachH);
		choose.setLocation(imageIn.getX() + imageIn.getWidth() + 5, eachH*5);
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				imagef.setText(ImageChooser.chooseImage());
			}
		});
		this.add(choose);
		
		JTextArea briefa = new JTextArea();
		briefa.setLineWrap(true);
		briefa.setPreferredSize(new Dimension(150, eachH*3));
		briefa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		InputBar brief = new InputBar("相簿简介", briefa);
		brief.setLocation(x, eachH*8);
		this.add(brief);
		
		JButton btn = new JButton("完成");
		btn.setSize(60,eachH);
		btn.setLocation((size.width - 60) / 2, eachH*13);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(themef.getText() == null || themef.getText().equals("")) {
					themef.setText("请编辑主题！");
					return;
				}
				if(imagef.getText() == null || imagef.getText().equals("")) {
					imagef.setText("请选择图片！");
					return;
				}
				DataRegister.setTheme(themef.getText());
				DataRegister.setImagePath(imagef.getText());
				DataRegister.setBriefIntro(briefa.getText());
				DataRegister.setChanged(true);
				CoverEditor me = CoverEditor.this;
				me.dispose();
			}
		});
		this.add(btn);
		
		Dimension frameSize = parent.getSize();
		Point p = parent.getLocation();
		//System.out.println(frameSize);
		int locX = (frameSize.width - size.width) / 2;
		int locY = (frameSize.height - size.height) / 2;
		this.setLocation(locX + p.x, locY + p.y);
		this.setVisible(true);
	}
	
	public CoverEditor(JFrame parent,String Theme,String ImagePath,String Brief) {
		super(parent);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setLayout(null);
		Dimension size = new Dimension(500,600);
		this.setSize(size);
		this.setTitle("编辑您的相册簿封面");
		
		int rows = 20;
		int eachH =  size.height / rows;
		
		JTextField themef = new JTextField();
		themef.setPreferredSize(new Dimension(150,eachH));
		themef.setText(Theme);
		InputBar theme = new InputBar("主题", themef);
		int x = (size.width - theme.getSize().width) / 2 - InputBar.getTipWidth() / 2;  
		theme.setLocation(x, eachH*2);
		this.add(theme);
		
		JTextField imagef = new JTextField();
		imagef.setPreferredSize(new Dimension(150,eachH));
		imagef.setText(ImagePath);
		InputBar imageIn = new InputBar("图片路径", imagef);
		imageIn.setLocation(x, eachH*5);
		this.add(imageIn);
		
		JButton choose = new JButton("选择图片");
		choose.setSize(100,eachH);
		choose.setLocation(imageIn.getX() + imageIn.getWidth() + 5, eachH*5);
		choose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				imagef.setText(ImageChooser.chooseImage());
			}
		});
		this.add(choose);
		
		JTextArea briefa = new JTextArea();
		briefa.setLineWrap(true);
		briefa.setPreferredSize(new Dimension(150, eachH*3));
		briefa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		briefa.setText(Brief);
		InputBar brief = new InputBar("相簿简介", briefa);
		brief.setLocation(x, eachH*8);
		this.add(brief);
		
		JButton btn = new JButton("完成");
		btn.setSize(60,eachH);
		btn.setLocation((size.width - 60) / 2, eachH*13);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(themef.getText() == null || themef.getText().equals("")) {
					themef.setText("请编辑主题！");
					return;
				}
				if(imagef.getText() == null || imagef.getText().equals("")) {
					imagef.setText("请选择图片！");
					return;
				}
				DataRegister.setTheme(themef.getText());
				DataRegister.setImagePath(imagef.getText());
				DataRegister.setBriefIntro(briefa.getText());
				DataRegister.setChanged(true);
				CoverEditor me = CoverEditor.this;
				me.dispose();
			}
		});
		this.add(btn);
		
		Dimension frameSize = parent.getSize();
		Point p = parent.getLocation();
		//System.out.println(frameSize);
		int locX = (frameSize.width - size.width) / 2;
		int locY = (frameSize.height - size.height) / 2;
		this.setLocation(locX + p.x, locY + p.y);
		this.setVisible(true);
	}
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		Dimension rect = Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setSize(rect.width*2/3,rect.height*2/3);
//		frame.setLocationByPlatform(true);
//		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
//		frame.setLayout(null);
//		
//		CoverEditor ce = new CoverEditor(frame);
//		ce.setVisible(true);
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
}
