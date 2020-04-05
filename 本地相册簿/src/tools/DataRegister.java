package tools;

public class DataRegister {
	private static String theme = "";
	private static String imagePath = "";
	private static String briefIntro = "";
	private static Boolean changed = false;
	
	public static String getTheme() {
		return theme;
	}
	public static void setTheme(String theme) {
		DataRegister.theme = theme;
	}
	public static String getImagePath() {
		return imagePath;
	}
	public static void setImagePath(String imagePath) {
		DataRegister.imagePath = imagePath;
	}
	public static String getBriefIntro() {
		return briefIntro;
	}
	public static void setBriefIntro(String briefIntro) {
		DataRegister.briefIntro = briefIntro;
	}
	public static Boolean getChanged() {
		return changed;
	}
	public static void setChanged(Boolean changed) {
		DataRegister.changed = changed;
	}
}
