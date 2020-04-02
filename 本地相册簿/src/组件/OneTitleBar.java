package 组件;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OneTitleBar extends JPanel{
	private static OneTitleBar titleBar;
	private static JLabel textArea;
	private OneTitleBar() {
		super();
	}
	
	public static OneTitleBar getInstance() {
		if(titleBar == null) {
			titleBar = new OneTitleBar();
			textArea = new JLabel();
			textArea.setHorizontalAlignment(JLabel.CENTER);
			textArea.setVerticalAlignment(JLabel.NORTH);
			titleBar.add(textArea);
		}
		return titleBar;
	}
	
	public static void setText(String text) {
		textArea.setText(text);
	}
	
	//调用suitSize之前默认你已经设置了OneTitleBar的首选大小
	public static void suitSize() {
		if(titleBar != null) {
			titleBar.setBackground(Color.yellow);
			if(titleBar.getPreferredSize().getWidth() != 0) {
				textArea.setPreferredSize(titleBar.getPreferredSize());
			}
			else System.out.println("标题栏大小为0");
		}
		else System.out.println("titleBar为空");
	}
	
	public boolean hasText() {
		if(textArea != null)
			return textArea.getText() != null;
		return false;
	}
}
