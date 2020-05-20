package 组件;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import beans.UserManager;
import tools.FileOperator;
import tools.RootPath;
import 界面.BorderUI;
import 界面.UIManager;

public class GridFrame extends JPanel{
	private int startP;
	private int capacity;
	private ArrayList<Cover> covers;
	private JPanel[] cells; //网格布局里所有的pane的引用
	private static Dimension gridSize;
	private static GridLayout curLayout;

	public GridFrame(int rows,int cols,Dimension frameSize) {
		covers = new ArrayList<Cover>();
//		this.frameSize = frameSize;
		capacity = rows * cols;
		int gap = 4;
		this.setLayout(new GridLayout(rows, cols, gap, gap));//row,col,hgap,vgap
		int gw = (frameSize.width - gap) / cols - gap;
		int gh = (frameSize.height - gap) / rows - gap;
		gridSize = new Dimension(gw, gh);
		cells = new JPanel[capacity];
		//System.out.println("framesize:" + frameSize);
		this.setPreferredSize(frameSize);
//		int width1 = (frameSize.width + gap) / cols - gap;
//		int height1 = (frameSize.height + gap) / rows - gap;
		for(int m = 0;m<cols*rows;m++) {
			cells[m] = new JPanel();
			//cells[m].setBackground(new Color(6*m,6*m,6*m));
			this.add(cells[m]);
			this.validate();
		}
		curLayout = (GridLayout) this.getLayout();
	}
	
	public void addCover(Cover cover) {
		replace(cover,startP);
		startP++;
		//System.out.println(startP);
		covers.add(cover);
	}
	
	public void addCovers(ArrayList<Cover> cs) {
		covers.addAll(cs);
		for(int i = 0;i < covers.size();i++) {
			replace(covers.get(i),i);
		}
		startP = covers.size();
	}
	
	public ArrayList<Cover> getCovers(){
		return covers;
	}
	
	public void deleteCover(int index) {
		if(index < 0 || index >= covers.size()) return;
		
		if(index == startP - 1) { //说明要删除的是最后一个元素
			setBlank(index);
		}
		else {
			for(int i =index;i < startP-1;i++) {
				swap(i,i+1);
			}
			setBlank(startP - 1); 
		}
		covers.remove(startP - 1);
		startP--;
	}
	
	public void deleteCover(Cover cover) {
		int index = -1;
		if(covers.contains(cover)) {
			index = covers.indexOf(cover);
		}
		if(index == -1) return;
		this.deleteCover(index);
		
	}
	
	//替换只考虑图形不考虑逻辑
	public void replace(JPanel target, int index) {
		if(index < 0 || index >= cells.length) return;
		this.remove(cells[index]);
		this.validate();
		this.add(target,index);
		cells[index] = target;
		this.validate();
	}
	
	public void swap(int c1,int c2) {
		if(c1 < 0 || c2 < 0) return;
		if(c1 >= covers.size() || c2 >= covers.size()) return;
		Component com1 = null;
		Component com2 = null;
		Cover newCover = null;
		Cover tmp = null;
		try {
			com1 = this.getComponent(c1);
			com2 = this.getComponent(c2);
		} catch (Exception e) {
			return;
		}
		if((com1 instanceof Cover) && (com2 instanceof Cover)) {
			//用一个引用保存C1位置上的Cover
			tmp = (Cover)com1;
			//复制C2到C1的位置上，注意：C1位置为一个新的Cover而非原Cover，所以在逻辑上covers里c1索引上的Cover也要变
			newCover = copy(c2, c1);
			//用那个引用替换C2位置的Cover
			replace(tmp,c2);
		}
		covers.set(c1, newCover);
		covers.set(c2, tmp);
		
//		List<Cover> covers = this.getCovers();
//		for(Cover cover:covers) {
//			System.out.println(cover.getBean());
//		System.out.println("------------------------------");
//      }
	}
	//这里的copy只涉及图形操作而不涉及底层的数据操作(covers和bean),但会返回一个目标位置上的cover
	public Cover copy(int source,int target) {
		if(source < 0 || target < 0) return null;
		if(source >= covers.size() || target >= covers.size()) return null;
		Component com1 = null;
		Component com2 = null;
		try {
			com1 = this.getComponent(source);
			com2 = this.getComponent(target);
		} catch (Exception e) {
			return null;
		}
		Cover tmp = null;
		if((com1 instanceof Cover) && (com2 instanceof Cover)) {
			//创建一个新的Cover与source位置上的cover相同
			//注意：这里创建应当调用clone,现在方便起见随便搞下
			Cover c1 = (Cover)com1;
			//Cover tmp = null;
			try {
				tmp = c1.clone();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Cover tmp = new Cover();
			//用新的cover替换target位置
			replace(tmp,target);
			tmp.showImage();
		}
		return tmp;
	}
	
	public void setBlank(int index) {
		try {
			this.getComponent(index);
		} catch (Exception e) {
			return;
		}
		this.remove(index);
		this.validate();
		JPanel blank = new JPanel();
		//blank.setPreferredSize(frameSize);
		this.add(blank,index);
		cells[index] = blank;
		this.validate();
	}
	
	public int getCoverSize() {
		return startP;
	}
	
	public static Dimension getGridSize() {
		return gridSize;
	}
	
	public static GridLayout getCurLayout() {
		return curLayout;
	}

	public static void setCurLayout(GridLayout curFrame) {
		GridFrame.curLayout = curFrame;
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		Dimension rect = Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setSize(rect.width*2/3,rect.height*2/3);
//		frame.setLocationByPlatform(true);
//		//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
//		//frame.setLayout(null);
//		UIManager.setFrame(frame);
//		BorderUI ui = new BorderUI();
//		ui.setTitile("you fuck");
//		
//		Dimension d = ui.getCenterPreferredSize();
//		GridFrame grid = new GridFrame(3,8,d);
//		grid.setBackground(Color.red);
//		ui.setCenterArea(grid);
//		
//		JPanel func = new JPanel();
//		func.setPreferredSize(ui.getFuncPreferredSize());
//		func.setLayout(new FlowLayout());
//		
//		JButton createbtn = new JButton("create");
//		createbtn.setPreferredSize(new Dimension(100,30));
//		createbtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				grid.addCover(new Cover());
//			}
//		});
//		func.add(createbtn);
//		
//		JButton createCSbtn = new JButton("create covers");
//		createCSbtn.setPreferredSize(new Dimension(120, 30));
//		createCSbtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				ArrayList<Cover> covers = new ArrayList<Cover>();
//				for(int i = 0;i < 3;i++) {
//					covers.add(new Cover());
//				}
//				grid.addCovers(covers);
//			}
//		});
//		func.add(createCSbtn);
//		
//		JButton deleteBtn = new JButton("delete");
//		deleteBtn.setPreferredSize(new Dimension(100,30));
//		deleteBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 删除最后一个元素
//				int index = grid.getCoverSize() - 1;
//				grid.deleteCover(index);
//				//System.out.println(grid.getsize());
//			}
//		});
//		func.add(deleteBtn);
//		
//		JButton copybtn = new JButton("copy");
//		copybtn.setPreferredSize(new Dimension(100,30));
//		copybtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 复制最后一个位置的cover给倒数第二个，注意：复制操作只复制图形，设计这样的操作是为了方便交换
//				int index = grid.getCoverSize() - 1;
//				grid.copy(index, index-1);
//			}
//		});
//		func.add(copybtn);
//		
//		JButton swapbtn = new JButton("swap");
//		swapbtn.setPreferredSize(new Dimension(100, 30));
//		swapbtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// 只交换最后一个和倒数第二个
//				int index = grid.getCoverSize() - 1;
//				grid.swap(index-1, index);
//			}
//		});
//		func.add(swapbtn);
//		
//		ui.setFuncArea(func);
//		UIManager.addToMap("test", ui);
//		UIManager.setCurUI("test");
//		
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
//
//	}
}
