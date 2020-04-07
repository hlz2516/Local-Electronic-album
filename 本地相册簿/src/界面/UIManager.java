package ����;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

public class UIManager {
	private static JFrame frame;
	private static String curName;
	private static Map<String,BorderUI> map = new HashMap<String,BorderUI>();
	
	public static void addToMap(String name,BorderUI UI) {
		map.put(name,UI);
	}
	
	public static void setCurUI(String name) {
		Set<String> s = map.keySet();
		Iterator<String> iter = s.iterator();
		while(iter.hasNext()) {
			if(iter.next().equals(name)) {   //����map������ҵ���value == name��
				if(curName == null || curName.equals("")) { //����ǵ�һ�����õ�ǰ����
					curName = name;
					BorderUI cur = (BorderUI)map.get(name);
					cur.setTitile(cur.getTitle());
					cur.push();
					//System.out.println("��ǰ������" + cur.getTitle());
					return;
				}
				//������ǣ���ô���Ƚ���ǰ�������ڲ���������ϵ
				BorderUI pre = (BorderUI)map.get(curName);
				pre.relieveCenter();
				pre.relieveFunc();
				//�ٸ��ĵ�ǰ�Ľ�����
				curName = name;
				//System.out.println(curName);
				//���ͨ���������ҵ�Ҫ���õĽ���Ҳ����panel������panel����frame�����ÿɼ�
				BorderUI cur = (BorderUI)map.get(name);
				cur.setTitile(cur.getTitle());
				cur.push();
				//System.out.println("��ǰ������" + cur.getTitle());
				break;
			}
		}
	}
	
	//Ѱ�ҵ�ǰMap���Ƿ���ָ��UI����UI���ڣ�������ڷ���true�����򷵻�false
	public static boolean DoesHaveUI(String UIName) {
		Set<String> s = map.keySet();
		Iterator<String> iter = s.iterator();
		while(iter.hasNext()) {
			if(iter.next().equals(UIName))
				return true;
		}
		return false;
	}
	
	public static String getCurUIName() {
		return curName;
	}
	
	public static BorderUI getCurUI() {
		return map.get(curName);
	}
	
	public static void setFrame(JFrame f) {
		frame = f;
	}
	
	public static JFrame getFrame() {
		return frame;
	}
	
	public static void deleteUI(String UIName) {
		Set<String> s = map.keySet();
		if(s.contains(UIName)) {
			System.out.println("���ҵ�UI");
			map.remove(UIName);
		}
	}
}
