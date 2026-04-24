package com.curso.jasper.util;

public enum Formato {
	PDF("application/pdf"),
	HTML("text/html");
	
	private String mimeType;
	
	public String getMimeType() {
		return mimeType;
	}
	
	private Formato(String mimeType) {
		this.mimeType = mimeType;
	}
}
