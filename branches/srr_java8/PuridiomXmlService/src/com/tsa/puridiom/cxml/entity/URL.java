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
public class URL implements Serializable {
	private String name;

	private String content;

	public URL() {
	}

	public URL(String name, String content) {
		this.name = name;
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
	 * <!ELEMENT URL (#PCDATA)> <!-- URL --> <!ATTLIST URL name %string;
	 * #IMPLIED >
	 *
	 * @param urlObject
	 * @return
	 */
	public static Element buildURlElement(URL urlObject) {
		Element urlElement = new Element("URL");

		urlObject.setAttributes(urlElement, urlObject);

		urlObject.setContent(urlElement, urlObject);

		return urlElement;
	}

	private void setAttributes(Element urlElement, URL urlObject) {
		/*
		 * name %string; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (urlObject.getName() != null) {
			attributes.add(new Attribute("name", urlObject.getName()));
		}

		urlElement.setAttributes(attributes);
	}

	private void setContent(Element urlElement, URL urlObject) {
		/*
		 * (#PCDATA)
		 */
		urlElement.setText(urlObject.getContent());
	}
}
