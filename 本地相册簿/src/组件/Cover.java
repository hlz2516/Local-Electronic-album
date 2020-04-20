package 组件;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

import beans.CoverBean;
import tools.DataRegister;
import 界面.UIManager;

public class Cover extends JPanel implements Cloneable{
	private CoverBean bean;
	private String imagePath;
	private static String storePath;
	private static Cover curCover;
	private static double themeHRate = 0.10;
	private static double briefIntroHRate = 0.20;
	//内部组件
	private JLabel theme;
	private JLabel imgLabel;
	private JTextArea briefIntro;
	
	public Cover() {
		bean = new CoverBean();
		
		this.setLayout(new BorderLayout(10,3));
		int width = GridFrame.getGridSize().width;
		int height = GridFrame.getGridSize().height;
		
		theme = new JLabel();
		theme.setPreferredSize(new Dimension(width, (int)(height * themeHRate)));
		//theme.setBorder(BorderFactory.createLineBorder(Color.black));
		theme.setHorizontalAlignment(JLabel.CENTER);
		theme.setVerticalAlignment(JLabel.BOTTOM);
		this.add(theme,BorderLayout.NORTH);
		
		imgLabel = new JLabel();
		imgLabel.setPreferredSize(new Dimension(width,
				(int)(height * (1 - themeHRate - briefIntroHRate))));
		imgLabel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));
		imgLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 1) {
					Cover tmp = Cover.this;
					Cover.setCurCover(tmp);
					
				}
				if(arg0.getClickCount() == 2) {
					Cover thisCover = Cover.this;
					MyThread.setCurThread(Thread.currentThread());
					CoverEditor editor = new CoverEditor(UIManager.getFrame(),
							theme.getText(), imagePath, briefIntro.getText());
					MyThread t = new MyThread(editor);
					t.start();
					
					try {
						Thread.sleep(9999999999999999L);
					} catch (Exception e2) {
						System.out.println("主线程已唤醒");
					}
					if(DataRegister.getTheme() == null && 
							DataRegister.getImagePath() == null && 
							DataRegister.getBriefIntro() == null) {
						
					}else {
						thisCover.setThemeText(DataRegister.getTheme());
						thisCover.setImagePath(DataRegister.getImagePath());
						thisCover.showImage();
						thisCover.setBriefText(DataRegister.getBriefIntro());
					}
					DataRegister.setChanged(false);
				}
			}
		});
		
		imgLabel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				JLabel inst = (JLabel)e.getSource();
				inst.setCursor(Cursor.getPredefinedCursor(12));
			}
		});
		this.add(imgLabel,BorderLayout.CENTER);
		
		JLabel fillW = new JLabel();
		fillW.setPreferredSize(new Dimension(width/20, 
				(int)(height * (1 - themeHRate - briefIntroHRate))));
		this.add(fillW,BorderLayout.WEST);
		
		JLabel fillE = new JLabel();
		fillE.setPreferredSize(new Dimension(width/20, 
				(int)(height * (1 - themeHRate - briefIntroHRate))));
		this.add(fillE,BorderLayout.EAST);
		
		briefIntro = new JTextArea();
		briefIntro.setPreferredSize(new Dimension(width, (int)(height*briefIntroHRate)));
		//briefIntro.setBorder(BorderFactory.createLineBorder(Color.black));
		briefIntro.setLineWrap(true);
		briefIntro.setEditable(false);
		this.add(briefIntro,BorderLayout.SOUTH);
		briefIntro.setBackground(this.getBackground());
		
		this.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
	}
	public CoverBean getBean() {
		return bean;
	}
	public void setBean(CoverBean bean) {
		this.bean = bean;
		this.setThemeText(bean.getTheme());
		this.setImagePath(storePath + "\\" + bean.getCoverId() + ".jpg");
		this.showImage();
		this.setBriefText(bean.getBriefIntro());
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public static String getStorePath() {
		return storePath;
	}
	public static void setStorePath(String storePath) {
		Cover.storePath = storePath;
	}
	public static Cover getCurCover() {
		return curCover;
	}
	public static void setCurCover(Cover curCover) {
		Cover.curCover = curCover;
	}
	public void setThemeText(String text) {
		if(text == null || text.equals("")) text = "";
		theme.setText(text);
		bean.setTheme(text);
	}
	public String getThemeText() {
		return theme.getText();
	}
	public void setBriefText(String text) {
		if(text == null || text.equals("")) text = "";
		briefIntro.setText(text);
		bean.setBriefIntro(text);
	}
	public String getBriefText() {
		return briefIntro.getText();
	}
//	public void setBriefIntroColor(Color color) {
//		briefIntro.setBackground(color);
//	}
	public void showImage() {
		if(imagePath == null) return;
		 ImageIcon img = new ImageIcon(imagePath);
		 Dimension imgSize = imgLabel.getPreferredSize();
		 img.setImage(img.getImage().getScaledInstance(imgSize.width, imgSize.height,Image.SCALE_DEFAULT));
		 imgLabel.setIcon(img);
	}
	protected Cover clone() throws CloneNotSupportedException{
			Cover c = new Cover();
			c.setBean(this.getBean());
			c.setImagePath(this.imagePath);
			c.setThemeText(this.getThemeText());
			c.setBriefText(this.getBriefText());
			return c;
	}
	
	public void paintComponent(Graphics g) {
		int h1 = (int)(GridFrame.getGridSize().height * themeHRate);
//		int h2 = (int)(GridFrame.getGridSize().height * (1 - briefIntroHRate));
		int w = GridFrame.getGridSize().width;
		g.setColor(Color.lightGray);
		g.drawLine(0, h1, w, h1);
//		g.drawLine(0, h2, w, h2);
	}
	
	public String toString() {
		return bean.toString();
	}
}
