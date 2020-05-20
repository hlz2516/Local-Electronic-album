package tools;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageChooser {
	public static String chooseImage() {
		String path = null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & GIF Images", "jpg","png","gif");
		chooser.setFileFilter(filter);
		try {
			int res = chooser.showOpenDialog(null);
			if(res == JFileChooser.APPROVE_OPTION) {
				path = chooser.getSelectedFile().getPath();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}
}
