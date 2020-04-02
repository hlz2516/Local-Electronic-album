package 组件;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import beans.StretchImageBean;
import tools.FileOperator;
import tools.ImageChooser;

public class StretchImageLabel extends JLabel{
	private StretchImageBean bean;
	private String imagePath;
	private String storePath;
	private Dimension size = new Dimension(200,200);
	private Point loc = new Point(0,0);
	private static int originX;
	private static int originY;
	private static boolean isPressed = false;
	private static boolean stretchMode = false;
	private static StretchImageLabel focusedLabel;
	
	public StretchImageLabel() {
		bean = new StretchImageBean();
		bean.setWidth(size.width);
		bean.setHeight(size.height);
		bean.setX(0);bean.setY(0);
		
		this.setSize(size);
		this.setLocation(loc);
		this.setBorder(BorderFactory.createDashedBorder(null));

		//鼠标移到右下角会变成西北角的双向箭头
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				JLabel inst = (JLabel)e.getSource();
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
		//实现按住鼠标移动图片框
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isPressed) {
					JLabel inst = (JLabel)e.getSource();
					int diffX = e.getXOnScreen() - originX;
					int diffY = e.getYOnScreen() - originY;
					if(stretchMode) {
						size.width += diffX;
						size.height += diffY;
						inst.setSize(size);
						if(imagePath != null) {
							ImageIcon img = new ImageIcon(imagePath);
							img.setImage(img.getImage().getScaledInstance(size.width, 
									size.height,Image.SCALE_DEFAULT));
							inst.setIcon(img);
						}
					}
					else {
						loc = new Point(inst.getX() + diffX, inst.getY() + diffY);
						inst.setLocation(loc);
					}
					//图片经过移动后更新bean
					StretchImageLabel temp = StretchImageLabel.this;
					bean.setX(temp.getLocation().x);
					bean.setY(temp.getLocation().y);
					bean.setWidth(temp.getWidth());
					bean.setHeight(temp.getHeight());
					isPressed = false;
//					System.out.println("x:" + bean.getX() + " y:" + bean.getY() + 
//		" w:" + bean.getWidth() + " h:" + bean.getHeight() + " id:" + bean.getId());
					
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				//if((e.getX() == size.width-1) && (e.getY() == size.height-1))
					//System.out.println("这是右下角");
					isPressed = true;
					originX = e.getXOnScreen();
					originY = e.getYOnScreen();
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				StretchImageLabel inst = StretchImageLabel.this;
				//单击设置当前获取焦点的imgLabel
				focusedLabel = inst;
				//System.out.println(focusedLabel.getName());

				BufferedImage bufferImg = null;
				// 双击输入图片
				if(arg0.getClickCount() == 2)
				{
					imagePath = ImageChooser.chooseImage();
					if(imagePath == null) return;
					bean.setRandomId();  //自动获取一个新ID
					//System.out.println(" id:" + bean.getId());
					try {
						File file = new File(imagePath);
						bufferImg = ImageIO.read(new FileInputStream(file));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//System.out.println(img.getWidth() + "," + img.getHeight());
					size = new Dimension(bufferImg.getWidth(),bufferImg.getHeight());
					inst.setSize(size);
					Image temp = inst.createImage(bufferImg.getSource());
					inst.setIcon(new ImageIcon(temp));
				}
				else if(arg0.getButton() == MouseEvent.BUTTON3) {
					bean.setId(null);
					imagePath = null;
					inst.setIcon(null);
					inst.setSize(size);
				}
			}
		});
	}
	
	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	
	//根据bean的值自动生成界面
	public void setBean(StretchImageBean bean) {
		this.bean = bean;
		this.bean.setChanged(false);
		this.size = new Dimension(bean.getWidth(), bean.getHeight());
		this.loc = new Point(bean.getX(), bean.getY());
		this.setSize(size);
		this.setLocation(loc);
		String id = bean.getId();
		System.out.println("bean id:" + id);
		ArrayList<ImageIcon> images = ImageStorage.getImages();
		//寻找ID相同的图片设置图片
		for(ImageIcon icon : images) {
			//System.out.println(icon.getDescription());
			if(icon.getDescription().equals(id)) {
				icon.setImage(icon.getImage().getScaledInstance(size.width, 
						size.height,Image.SCALE_DEFAULT));
				this.setIcon(icon);
				//设置为上次加载的路径，路径组成为相册簿的图片目录+图片id。这里只用images目录举例
				this.imagePath = this.getStorePath() + "//" + icon.getDescription() + ".jpg";
				//System.out.println(this.imagePath);
				break;
			}
		}
	}
	
	public StretchImageBean getBean() {
		return bean;
	}
	
	public void saveImage() {
		if(imagePath == null) return;
		//所以无论什么格式的图片存储后都会变成jpg
		FileOperator.copyFile(imagePath, storePath + "//" + bean.getId() + ".jpg");
	}
	
	public static StretchImageLabel getFocusedImageLabel() {
		return focusedLabel;
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		Dimension rect = Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setSize(rect.width*2/3,rect.height*2/3);
//		frame.setLocationByPlatform(true);
//
//		frame.setLayout(null);
//		ArrayList<ImageIcon> list = ImageStorage.loadImages(".//images");
//		
//		//用户操作存入图片
////		StretchImageLabel label1 = new StretchImageLabel();
////		label1.setStorePath(".//images");
////		frame.add(label1);
////		
////		JButton savebtn = new JButton();
////		savebtn.setLocation(500,500);
////		savebtn.setSize(60,30);
////		savebtn.setText("save");
////		savebtn.addActionListener(new ActionListener() {
////			
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				// TODO Auto-generated method stub
////				label1.saveImage();
////				
////			}
////		});
////		frame.add(savebtn);
//		//根据bean来生成图片
//		StretchImageBean bean = new StretchImageBean();
//		bean.setX(100);
//		bean.setY(100);
//		bean.setWidth(800);
//		bean.setHeight(600);
//		//这里的设置有点问题，以后是直接载入json里的bean的所以不用修改id。这里为了测试所以构造一个bean
//		//在对其他类测试时请把StretchImageBean的setId方法修正回来！
//		bean.setId("1111"); 
//		StretchImageLabel label = new StretchImageLabel();
//		label.setStorePath(".//images");
//		label.setBean(bean);
//		frame.add(label);
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//	}
}
