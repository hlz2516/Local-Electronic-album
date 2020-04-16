package 界面;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import 组件.OneTitleBar;

public class BorderUI implements Cloneable{
	private JComponent menuArea;
	private JComponent centerArea;
	private JComponent funcArea;
	private static double menuHeightRate = 0.04;
	private static double funcHeightRate = 0.08;
	private JComponent Area[] = new JComponent[3];
	
	
	public BorderUI() {
		JFrame frame = UIManager.getFrame();
		Dimension frameSize = frame.getSize();

		//创建菜单栏
		menuArea = new JPanel();
		int menuH = (int)(frameSize.height*menuHeightRate);
		menuArea.setPreferredSize(new Dimension(frameSize.width, menuH));
		Area[0] = menuArea;
		
		//设置中心区域
		centerArea = new JPanel();
		int centerH = (int)(frameSize.height * (1 - menuHeightRate - funcHeightRate));
		centerArea.setPreferredSize(new Dimension(frameSize.width,centerH));
		Area[1] = centerArea;
		
		//设置功能区域
		funcArea = new JPanel();
		int funcH = (int)(frameSize.height * funcHeightRate);
		funcArea.setPreferredSize(new Dimension(frameSize.width,funcH));
		Area[2] = funcArea;
	}
	
	public void setMenuArea(JComponent pane) {
		if(pane.getPreferredSize().equals(getMenuPreferredSize())) {
			menuArea = pane;
		}
	}
	
	public void setCenterArea(JComponent pane) {
		if(pane.getPreferredSize().equals(getCenterPreferredSize())) {
			centerArea = pane;
		}
	}
	
	public void setFuncArea(JComponent pane) {
		if(pane.getPreferredSize().equals(getFuncPreferredSize())) {
			funcArea = pane;
		}
	}
	
	public JComponent getmenuArea() {
		return menuArea;
	}
	
	public JComponent getCenterArea() {
		return centerArea;
	}
	
	public JComponent getFuncArea() {
		return funcArea;
	}
	
	public Dimension getMenuPreferredSize() {
		return menuArea.getPreferredSize();
	}
	
	public Dimension getCenterPreferredSize() {
		return centerArea.getPreferredSize();
	}
	
	public Dimension getFuncPreferredSize() {
		return funcArea.getPreferredSize();
	}
	
	public static void setMenuHeightRate(double rate) {
		if(rate > 0 && rate < 1)
			menuHeightRate = rate;
	}
	
	public static void setFuncHeightRate(double rate) {
		if(rate > 0 && rate < 1)
			funcHeightRate = rate;
	}
	
	public void push() {
		JFrame frame = UIManager.getFrame();
		if(menuArea != null)
			frame.add(menuArea,BorderLayout.NORTH);
		frame.validate();
		if(centerArea != null)
			frame.add(centerArea,BorderLayout.CENTER);
		frame.validate();
		if(funcArea != null)
			frame.add(funcArea,BorderLayout.SOUTH);
		frame.validate();
	}
	
	public void relieve() {
		JFrame frame = UIManager.getFrame();
		if(menuArea != null)
			frame.remove(menuArea);
		frame.validate();
		if(centerArea != null)
			frame.remove(centerArea);
		frame.validate();
		if(funcArea != null)
			frame.remove(funcArea);
		frame.validate();
	}
	
	public void relieveMenu() {
		JFrame frame = UIManager.getFrame();
		if(menuArea != null)
			frame.remove(menuArea);
		frame.validate();
	}
	
	public void relieveCenter() {
		JFrame frame = UIManager.getFrame();
		if(centerArea != null)
			frame.remove(centerArea);
		frame.validate();
	}
	
	public void relieveFunc() {
		JFrame frame = UIManager.getFrame();
		if(funcArea != null)
			frame.remove(funcArea);
		frame.validate();
	}
	
	//9.复制一个BorderUI并且对其进行改造生成一个新的BorderUI，如果传的参数为null则表示不更改对应的区域
//	public static BorderUI modifyCreate(BorderUI ui,JComponent menuArea,
//			JComponent centerArea,JComponent funcArea) {
//		BorderUI temp = (BorderUI)ui.clone();
//		if(ui == null)
//			return ui;
//		if(menuArea != null) 
//			temp.setMenuArea(menuArea);
//		if(centerArea != null)
//			temp.setCenterArea(centerArea);
//		if(funcArea != null)
//			temp.setFuncArea(funcArea);
//		
//		return temp;
//	}
	
	public void setMenuDefault() {
		if(menuArea != null)
			this.relieveMenu();
		menuArea = Area[0];
	}
	
	public void setCenterDefault() {
		if(centerArea != null)
			this.relieveCenter();
		centerArea = Area[1];
	}
	
	public void setFuncDefault() {
		if(funcArea != null)
			this.relieveFunc();
		funcArea = Area[2];
	}
	protected Object clone() {
		BorderUI ui = new BorderUI();
		ui.setMenuArea(menuArea);
		ui.setCenterArea(centerArea);
		ui.setFuncArea(funcArea);
		return ui;
	}
}
