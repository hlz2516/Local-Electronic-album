package ×é¼þ;

import tools.DataRegister;

public class MyThread extends Thread{
	private CoverEditor editor;
	private static Thread curThread;

	public static Thread getCurThread() {
		return curThread;
	}

	public static void setCurThread(Thread curThread) {
		MyThread.curThread = curThread;
	}
	
	public MyThread(CoverEditor ce) {
		editor = ce;
	}
	

	public void run() {
		while(!DataRegister.getChanged()) {
			if(editor.isDisplayable()) {
				DataRegister.setTheme(null);
				DataRegister.setImagePath(null);
				DataRegister.setBriefIntro(null);
				break;
			}
			try {
				Thread.sleep(500);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		curThread.interrupt();
		editor = null;
		curThread = null;
	}
}
