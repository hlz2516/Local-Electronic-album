package beans;

public class StretchTextBean extends StretchComBean{
	String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return "text:" + text + " " + super.toString();
	}
}
