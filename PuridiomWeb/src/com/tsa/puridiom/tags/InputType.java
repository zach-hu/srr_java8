/**
 * @author Jigar Mistry
 */
package com.tsa.puridiom.tags;

public enum InputType {
	text,mintext,midtext,maxtext,radio,checkbox,textarea,yesnoradio, dropdown;

	/**
	 * Return size of specified type of text
	 *
	 * @param type - type of textbox
	 * @return - size of textbox
	 */
	public String getSize(InputType type){
		switch(type){
			case mintext : return "15";
			case midtext : return "20";
			case maxtext : return "50";
			default : return "0";
		}
	}

	/**
	 * Return input type of specified type
	 *
	 * @param type - input type
	 * @return - input type
	 */
	public String getType(InputType type){
		switch(type){
			case text : return "text";
			case mintext : return "text";
			case midtext : return "text";
			case maxtext : return "text";
			case radio : return "radio";
			case checkbox : return "checkbox";
			case textarea : return "textarea";
			case yesnoradio : return "yesnoradio";
			case dropdown : return "dropdown";
			default : return "";
		}
	}
}
