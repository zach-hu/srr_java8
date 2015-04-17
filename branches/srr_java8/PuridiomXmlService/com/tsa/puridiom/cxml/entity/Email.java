/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class Email implements Serializable {
	private String name;

	private String preferredLang;

	private String content;

	public Email() {
	}

	public Email(String content) {
		this.content = content;
	}

	public Email(String name, String preferredLang, String content) {
		this.name = name;
		this.preferredLang = preferredLang;
		this.content = content;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the preferredLang
	 */
	public String getPreferredLang() {
		return preferredLang;
	}

	/**
	 * @param preferredLang
	 *            the preferredLang to set
	 */
	public void setPreferredLang(String preferredLang) {
		this.preferredLang = preferredLang;
	}

	/**
	 * <!ELEMENT Email (#PCDATA)> <!-- string --> <!ATTLIST Email name %string;
	 * #IMPLIED preferredLang %xmlLangCode; #IMPLIED >
	 *
	 * @param emailObject
	 * @return
	 */
	public static Element buildEmailElement(Email emailObject) {
		Element emailElement = new Element("Email");

		emailObject.setAttributes(emailElement, emailObject);

		emailObject.setContent(emailElement, emailObject);

		return emailElement;
	}

	private void setAttributes(Element emailElement, Email emailObject) {
		/*
		 * name %string; #IMPLIED preferredLang %xmlLangCode; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (emailObject.getName() != null) {
			attributes.add(new Attribute("name", emailObject.getName()));
		}

		if (emailObject.getPreferredLang() != null) {
			attributes.add(new Attribute("preferredLang", emailObject
					.getPreferredLang()));
		}

		emailElement.setAttributes(attributes);
	}

	private void setContent(Element emailElement, Email emailObject) {
		/*
		 * (#PCDATA)
		 */
		emailElement.setText(emailObject.getContent());

	}

}
