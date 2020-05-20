package ×é¼þ;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;

public class InputBar extends JPanel{
	private JLabel tipLabel;
	private JTextComponent textCom;
	private static int tipWidth = 80;
	
	public InputBar(String tip,JTextComponent textCom) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
//		System.out.println(textCom.getPreferredSize());
		Dimension textComSize = textCom.getPreferredSize();
		Dimension tipLabSize = new Dimension(tipWidth, textComSize.height);
		
		tipLabel = new JLabel();
		tipLabel.setPreferredSize(tipLabSize);
		tipLabel.setText(tip);
		tipLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(tipLabel);
		
		this.textCom = textCom;
		this.add(this.textCom);
		
		Dimension size = new Dimension(tipWidth + textComSize.width + 10, 
				textComSize.height);
		//System.out.println(size);
		this.setSize(size);
	}
	
	public static int getTipWidth() {
		return tipWidth;
	}
}

