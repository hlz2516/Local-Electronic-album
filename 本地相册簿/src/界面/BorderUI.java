package 界面;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import 组件.OneTitleBar;

public class BorderUI implements Cloneable{
	public static OneTitleBar titleBar; 
	
	private JComponent centerArea;
	private JComponent funcArea;
	private static double titleHeightRate = 0.04;
	private static double funcHeightRate = 0.08;
	private String title;
	private Dimension frameSize;
	private static JFrame frame;
	private static JComponent Area[] = new JComponent[2];
	
	
	public BorderUI() {
		frame = UIManager.getFrame();
		frameSize = frame.getSize();
		//创建标题栏
		titleBar = OneTitleBar.getInstance();
		int titleH = (int)(frameSize.height * titleHeightRate);
		titleBar.setPreferredSize(new Dimension(frameSize.width,titleH));
		titleBar.suitSize();
		titleBar.setText("");
		
		//设置中心区域
		centerArea = new JPanel();
		int centerH = (int)(frameSize.height * (1 - titleHeightRate - funcHeightRate));
		centerArea.setPreferredSize(new Dimension(frameSize.width,centerH));
		Area[0] = centerArea;
		
		//设置功能区域
		funcArea = new JPanel();
		int funcH = (int)(frameSize.height * funcHeightRate);
		funcArea.setPreferredSize(new Dimension(frameSize.width,funcH));
		Area[1] = funcArea;
	}
	
	public void setCenterArea(JComponent pane) {
		if(pane.getPreferredSize().equals(getCenterPreferredSize())) {
			centerArea = pane;
			//System.out.println("centerArea repalced");
		}
	}
	
	public void setFuncArea(JComponent pane) {
		if(pane.getPreferredSize().equals(getFuncPreferredSize())) {
			funcArea = pane;
		}
	}
	
	public JComponent getCenterArea() {
		return this.centerArea;
	}
	
	public JComponent getFuncArea() {
		return this.funcArea;
	}
	
	public Dimension getCenterPreferredSize() {
		return centerArea.getPreferredSize();
	}
	
	public Dimension getFuncPreferredSize() {
		return funcArea.getPreferredSize();
	}
	
	public static void setTitleHeightRate(double rate) {
		if(rate > 0 && rate < 1)
			titleHeightRate = rate;
	}
	
	public static void setFuncHeightRate(double rate) {
		if(rate > 0 && rate < 1)
			funcHeightRate = rate;
	}
	
	public void setTitile(String title) {
		this.title = title;
		titleBar.setText(title);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void push() {
		if(titleBar != null)
			frame.add(titleBar,BorderLayout.NORTH);
		frame.validate();
		if(centerArea != null)
			frame.add(centerArea,BorderLayout.CENTER);
		frame.validate();
		if(funcArea != null)
			frame.add(funcArea,BorderLayout.SOUTH);
		frame.validate();
	}
	
	public void relieve() {
		if(titleBar != null)
			frame.remove(titleBar);
		frame.validate();
		if(centerArea != null)
			frame.remove(centerArea);
		frame.validate();
		if(funcArea != null)
			frame.remove(funcArea);
		frame.validate();
	}
	
	public void relieveCenter() {
		if(centerArea != null)
			frame.remove(centerArea);
		frame.validate();
	}
	
	public void relieveFunc() {
		if(funcArea != null)
			frame.remove(funcArea);
		frame.validate();
	}
	
	//9.复制一个BorderUI并且对其进行改造生成一个新的BorderUI，如果传的参数为null则表示不更改对应的区域
	public static BorderUI modifyCreate(BorderUI ui,String title,
			JComponent centerArea,JComponent funcArea) {
		BorderUI temp = (BorderUI)ui.clone();
		if(ui == null)
			return ui;
		if(title != null) 
			temp.setTitile(title);
		if(centerArea != null)
			temp.setCenterArea(centerArea);
		if(funcArea != null)
			temp.setFuncArea(funcArea);
		
		return temp;
	}
	
	public void setCenterDefault() {
		if(centerArea != null)
			this.relieveCenter();
		centerArea = Area[0];
	}
	
	public void setFuncDefault() {
		if(funcArea != null)
			this.relieveFunc();
		funcArea = Area[1];
	}
	protected Object clone() {
		BorderUI ui = new BorderUI();
		ui.setCenterArea(centerArea);
		ui.setFuncArea(funcArea);
		return ui;
	}
}
