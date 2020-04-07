package teststh;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ����.BorderUI;
import ����.CoverUI;
import ����.UIManager;
import ���.ImageStorage;

//�����еĶ�������Ϊ��ʵ�����һЩ��������λ���ٿ��Թ�
public class Test {  
	public static void main(String[] args) {
        try {  
            File fi = new File(".//images//2222.jpg"); //��ͼ�ļ�  
            File fo = new File(".//2222.jpg"); //��Ҫת������Сͼ�ļ�  
            int nw = 500;  
            /* 
            AffineTransform ���ʾ 2D ����任����ִ�д� 2D ���굽���� 2D 
		            ���������ӳ�䣬�������ߵġ�ֱ���ԡ��͡�ƽ���ԡ�������ʹ��һϵ 
		            ��ƽ�ơ����š���ת����ת�ͼ������������任�� 
            */  
            //AffineTransform transform = new AffineTransform();  
            BufferedImage bis = ImageIO.read(fi); //��ȡͼƬ  
            int w = bis.getWidth();  
            int h = bis.getHeight();  
             //double scale = (double)w/h;  
            int nh = (nw*h)/w ;  
            double sx = (double)nw/w;  
            double sy = (double)nh/h;  
            //transform.setToScale(sx,sy); //setToScale(double sx, double sy) ���˱任����Ϊ���ű任��  
            System.out.println("w:" + w + " h:" + h);  
            System.out.println("nw:" + nw + " nh: " + nh);
            /* 
             * AffineTransformOp��ʹ�÷���ת����ִ�д�Դͼ��� Raster �� 2D ���굽Ŀ��ͼ��� 
             *  Raster �� 2D ���������ӳ�䡣��ʹ�õĲ�ֵ�����ɹ��췽��ͨ�� 
             *  һ�� RenderingHints �����ͨ�������ж����������ֵ����֮һ��ָ���� 
			            ����ڹ��췽����ָ���� RenderingHints ������ʹ�ò�ֵ��ʾ�ͳ��� 
			            ��������ʾΪ�˲������ò�ֵ���͡�Ҫ�������ɫת��ʱ������ʹ����ɫ 
			            ������ʾ�Ͷ�����ʾ�� ע�⣬���Ҫ��������Լ����Դͼ����Ŀ��ͼ�� 
			            ���벻ͬ�� ���� Raster ����Դͼ���е� band ���������Ŀ��ͼ���� 
			            �� band ���� 
            */  
           // AffineTransformOp ato = new AffineTransformOp(transform,null);  
            BufferedImage bid = new BufferedImage(600,400,BufferedImage.TYPE_3BYTE_BGR);  
            bid.getGraphics().drawImage(bis, 0, 0, 600, 400, null);
            /* 
             * TYPE_3BYTE_BGR ��ʾһ������ 8 λ RGB ��ɫ������ͼ�� 
             * ��Ӧ�� Windows ���� BGR ��ɫģ�ͣ������� 3 �ֽڴ� 
             * ���� Blue��Green �� Red ������ɫ�� 
            */  
            //ato.filter(bis,bid);  
            ImageIO.write(bid,"jpeg",fo);  
        } catch(Exception e) {  
            e.printStackTrace();  
        }  
	}
}
