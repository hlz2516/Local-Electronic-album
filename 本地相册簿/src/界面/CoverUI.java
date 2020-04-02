package 界面;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CoverUI {
	public static BorderUI create() {
		BorderUI ui = new BorderUI();
		
		ui.setTitile("相册簿封面集");
		
		ui.setCenterDefault();
		
		JPanel func = new JPanel();
		func.setPreferredSize(ui.getFuncPreferredSize());
		func.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
		
		JButton enter = new JButton();
		enter.setPreferredSize(new Dimension(200,ui.getFuncPreferredSize().height));
		enter.setText("进入相册");
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!UIManager.DoesHaveUI("pageui")) {
					BorderUI temp = PageUI.create();
					UIManager.addToMap("pageui", temp);
				}
				UIManager.setCurUI("pageui");
			}
		});
		func.add(enter);
		ui.setFuncArea(func);
		
		return ui;
	}
}
