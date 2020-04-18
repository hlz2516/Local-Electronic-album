package beans;

import com.alibaba.fastjson.annotation.JSONField;

public class StretchComBean {
	@JSONField(ordinal = 0)
	private int x;
	@JSONField(ordinal = 1)
	private int y;
	@JSONField(ordinal = 2)
	private int width;
	@JSONField(ordinal = 3)
	private int height;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String toString() {
		return "location:" + "[" + x + "," + y + "] " + "size:" + "[" +
	width + "," + height + "]";
	}
}
