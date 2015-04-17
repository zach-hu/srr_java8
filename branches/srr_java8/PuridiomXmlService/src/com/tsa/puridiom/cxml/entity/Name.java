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
public class Name implements Serializable {

	private String lang;

	private String content;

	public Name() {
	}

	public Name(String lang, String content) {
		this.lang = lang;
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
	 * <!ELEMENT Name (#PCDATA)> <!-- string --> <!ATTLIST Name xml:lang
	 * %xmlLangCode; #REQUIRED >
	 *
	 * @param nameObject
	 * @return
	 */
	public static Element buildNameElement(Name nameObject) {
		Element nameElement = new Element("Name");

		nameObject.setAttributes(nameElement, nameObject);

		nameObject.setContent(nameElement, nameObject);

		return nameElement;
	}

	private void setAttributes(Element nameElement, Name nameObject) {
		/*
		 * xml:lang %xmlLangCode; #REQUIRED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (nameObject.getLang() != null) {
			attributes.add(new Attribute("lang", nameObject.getLang(),
					Namespace.getNamespace("xml",
							"http://www.w3.org/1999/xhtml")));
		}

		nameElement.setAttributes(attributes);
	}

	private void setContent(Element nameElement, Name nameObject) {
		/*
		 * (#PCDATA)
		 */
		nameElement.setText(nameObject.getContent());
	}
}
