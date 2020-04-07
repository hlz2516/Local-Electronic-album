package 界面;

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
			if(iter.next().equals(name)) {   //遍历map，如果找到了value == name的
				if(curName == null || curName.equals("")) { //如果是第一次设置当前界面
					curName = name;
					BorderUI cur = (BorderUI)map.get(name);
					cur.setTitile(cur.getTitle());
					cur.push();
					//System.out.println("当前界面名" + cur.getTitle());
					return;
				}
				//如果不是，那么，先将当前界面与内部区域解除关系
				BorderUI pre = (BorderUI)map.get(curName);
				pre.relieveCenter();
				pre.relieveFunc();
				//再更改当前的界面名
				curName = name;
				//System.out.println(curName);
				//最后通过界面名找到要设置的界面也就是panel，将该panel加入frame并设置可见
				BorderUI cur = (BorderUI)map.get(name);
				cur.setTitile(cur.getTitle());
				cur.push();
				//System.out.println("当前界面名" + cur.getTitle());
				break;
			}
		}
	}
	
	//寻找当前Map下是否有指定UI名的UI存在，如果存在返回true，否则返回false
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
			System.out.println("已找到UI");
			map.remove(UIName);
		}
	}
}
