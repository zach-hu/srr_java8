package com.tsa.puridiom.coda.tasks;


// Fake entity class to fool browse

public class ElementFinder {
	String code ;
	String name ;
	String shortName ;

	public ElementFinder(String code, String name, String shortName) {
		this.name = name ;
		this.code = code ;
		this.shortName = shortName ;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}


}
