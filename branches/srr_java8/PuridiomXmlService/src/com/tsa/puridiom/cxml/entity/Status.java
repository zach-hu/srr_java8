/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;

/**
 * @author Johnny Zapana
 *
 */
public class Status implements Serializable {
	private Integer code;

	private String text;

	private String lang;

	private String content;

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * <!ELEMENT Status (#PCDATA)> <!ATTLIST Status code %uint; #REQUIRED text
	 * %string; #REQUIRED xml:lang %xmlLangCode; #IMPLIED >
	 *
	 * @param statusObject
	 * @return
	 */
	public static Element buildStatusElement(Status statusObject) {
		Element statusElement = new Element("Status");

		statusObject.setAttributes(statusElement, statusObject);

		statusObject.setContent(statusElement, statusObject);

		return statusElement;
	}

	private void setAttributes(Element statusElement, Status statusObject) {
		/*
		 * code %uint; #REQUIRED text %string; #REQUIRED xml:lang %xmlLangCode;
		 * #IMPLIED
		 */
//		List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("code", String.valueOf(statusObject
				.getCode())));

		attributes.add(new Attribute("text", statusObject.getText()));

		if (statusObject.getLang() != null) {
			attributes.add(new Attribute("lang", statusObject.getLang(),
					Namespace.getNamespace("xml",
							"http://www.w3.org/1999/xhtml")));
		}

		statusElement.setAttributes(attributes);
	}

	private void setContent(Element statusElement, Status statusObject) {
		/*
		 * (#PCDATA)
		 */
		statusElement.setText(statusObject.getContent());

	}

}
