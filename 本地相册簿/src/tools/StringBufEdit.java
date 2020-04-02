package tools;

public class StringBufEdit {
	public static void deleteStringFromTail(StringBuilder string,String subString) {
		int index = string.lastIndexOf(subString);
		string.delete(index, string.length());
	}
}
