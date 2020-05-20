package 组件;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import beans.StretchComBean;
import beans.StretchTextBean;

public class StretchTextArea extends JTextArea{
	private StretchTextBean bean;
	private Dimension size = new Dimension(200,50);
	private Point loc = new Point(0,0);

	private static boolean isPressed;
	private static boolean stretchMode;
	private static StretchTextArea focusedTextArea;

	public StretchTextArea() {
		bean = new StretchTextBean();
		bean.setX(0);bean.setY(0);
		bean.setWidth(size.width);
		bean.setHeight(size.height);
		
		this.setSize(size);
		this.setLocation(loc);
		this.setLineWrap(true);
		this.setBorder(BorderFactory.createDashedBorder(null));
		
		//鼠标移到右下角时改变光标
		this.addMouseMotionListener(new MouseMotionListener() {
					
					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						JTextArea inst = (JTextArea)e.getSource();
						if((e.getX() >= size.width-2) && 
								(e.getY() >= size.height-2)) {
							inst.setCursor(Cursor.getPredefinedCursor(6));
							stretchMode = true;
						}
						else {
							inst.setCursor(Cursor.getPredefinedCursor(0));
							stretchMode = false;
						} 
		
					}
					
					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
		//实现拖动文字框
		this.addMouseListener(new MouseAdapter() {
			private int originX;
			private int originY;
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isPressed) {
					JTextArea inst = (JTextArea)e.getSource();
					int diffX = e.getXOnScreen() - originX;
					int diffY = e.getYOnScreen() - originY;
					if(stretchMode) {
						size.width += diffX;
						size.height += diffY;
						inst.setSize(size);
					}
					else {
						loc = new Point(inst.getX() + diffX, inst.getY() + diffY);
						inst.setLocation(loc);
					}
					StretchTextArea temp = StretchTextArea.this;
					bean.setX(temp.getX());bean.setY(temp.getY());
					bean.setWidth(temp.getWidth());
					bean.setHeight(temp.getHeight());
					isPressed = false;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
					isPressed = true;
					originX = e.getXOnScreen();
					originY = e.getYOnScreen();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				StretchTextArea ta = StretchTextArea.this;
				switch (e.getKeyCode()) {
				case KeyEvent.VK_F1: //显示虚线框
					ta.setBorder(BorderFactory.createDashedBorder(null));
					break;
				case KeyEvent.VK_F2: //隐藏虚线框
					ta.setBorder(null);
					break;
				case KeyEvent.VK_F3: //显示光标
					ta.getCaret().setVisible(true);
					break;
				case KeyEvent.VK_F4: //隐藏光标
					ta.getCaret().setVisible(false);
					break;
				default:
					break;
				}
			}
		});
		
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				focusedTextArea = StretchTextArea.this;
			}
		});
	}
	
	public void setBean(StretchTextBean bean) {
		this.bean = bean;
		this.size = new Dimension(bean.getWidth(),bean.getHeight());
		this.loc = new Point(bean.getX(),bean.getY());
		this.setSize(size);
		this.setLocation(loc);
		this.setText(bean.getText());
		this.setBorder(null);
	}
	
	public StretchTextBean getBean() {
		return bean;
	}
	
	public void saveText() {
		//System.out.println(super.getText());
		bean.setText(super.getText());
	}
	
	public String getText() {
		return bean.getText();
	}
	
	public void setLocation(Point p) {
		super.setLocation(p);
		this.loc = p;
	}
	
	public static StretchTextArea getFocusedTextArea() {
		return focusedTextArea;
	}
	
	//测试之影分身之术？
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		Dimension rect = Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setSize(rect.width*2/3,rect.height*2/3);
//		frame.setLocationByPlatform(true);
//		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
//		frame.setLayout(null);
//		//创建可扩展文字框，输入一段文字保存
//		StretchTextArea ta = new StretchTextArea();
//		frame.add(ta);
//		ta.setBackground(frame.getBackground());
//		
//		JButton save = new JButton();
//		save.setSize(100,30);
//		save.setLocation(200, 200);
//		save.setText("save");
//		save.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				ta.saveText();
//				//创建一个可扩展文字框，根据前者的bean来生成图形
//				StretchTextArea ta1 = new StretchTextArea();
//				ta1.setBean(ta.getBean());
//				frame.add(ta1);
//				ta1.setBackground(frame.getBackground());
//			}
//		});
//		
//		frame.add(save);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
}
