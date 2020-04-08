package tools;

import java.io.File;
import java.net.URISyntaxException;

import ΩÁ√Ê.Entrance;

public class RootPath {
	public static String getRootDictionary(){
		String path = null;
		try {
			path = new File(Entrance.class.getProtectionDomain().getCodeSource().getLocation()
				    .toURI()).getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
		int lastIndex = path.lastIndexOf(File.separator) + 1;
		path = path.substring(firstIndex, lastIndex);
		return path;
	}
}
