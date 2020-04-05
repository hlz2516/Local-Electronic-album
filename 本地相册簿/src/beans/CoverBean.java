package beans;

import java.util.UUID;

public class CoverBean{
	String theme;
	String coverId;
	String briefIntro;
	
	public CoverBean() {
		coverId = UUID.randomUUID().toString();
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getCoverId() {
		return coverId;
	}
	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}
	public String getBriefIntro() {
		return briefIntro;
	}
	public void setBriefIntro(String briefIntro) {
		this.briefIntro = briefIntro;
	}
}
