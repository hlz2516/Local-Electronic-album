package ����;

import java.awt.*;
import javax.swing.*;

public class Entrance {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Dimension rect = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(rect.width*2/3,rect.height*2/3);
		frame.setLocationByPlatform(true);
		frame.setTitle("��Ჾ����ϵͳ");
		//frame.setLayout(null);
		
		UIManager.setFrame(frame);
		BorderUI mainui = MainUI.create();
		UIManager.addToMap("mainui", mainui);
		UIManager.setCurUI("mainui");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
