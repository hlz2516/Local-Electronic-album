package teststh;

import java.awt.Dialog;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyDialog extends JDialog{
	String name;
	String birth;
	public MyDialog(JFrame parent) {
		super(parent);
		Rectangle pr = parent.getBounds();
		Rectangle r = new Rectangle(pr.x +100, pr.y+100, pr.width *2/3, pr.height*2/3);
		setBounds(r);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setLayout(null);
		
		JTextField tf = new JTextField();
		tf.setSize(200,30);
		tf.setLocation(300, 20);
		this.add(tf);
		
		JTextField tf1 = new JTextField();
		tf1.setSize(200,30);
		tf1.setLocation(300, 80);
		this.add(tf1);
		
		JButton sendbtn = new JButton("·¢ËÍ");
		sendbtn.setSize(200,30);
		sendbtn.setLocation(300,140);
		sendbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				User user = User.getInstance();
				user.setName(tf.getText());
				user.setBirth(tf1.getText());
				MyDialog d = MyDialog.this;
				d.dispose();
			}
		});
		this.add(sendbtn);
		
		this.setVisible(true);
	}
}
