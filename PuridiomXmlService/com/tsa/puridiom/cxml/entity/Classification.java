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
public class Classification implements Serializable {
	private String domain;

	private String content;

	public Classification() {
	}

	public Classification(String domain, String content) {
		this.domain = domain;
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
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain
	 *            the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * <!ELEMENT Classification (#PCDATA)> <!-- string --> <!ATTLIST
	 * Classification domain %string; #REQUIRED >
	 */
	public static Element buildClassificationElement(
			Classification classificationObject) {
		Element classificationElement = new Element("Classification");

		classificationObject.setAttributes(classificationElement,
				classificationObject);

		classificationObject.setContent(classificationElement,
				classificationObject);

		return classificationElement;
	}

	private void setAttributes(Element classificationElement,
			Classification classificationObject) {
		/*
		 * domain %string; #REQUIRED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes
				.add(new Attribute("domain", classificationObject.getDomain()));

		classificationElement.setAttributes(attributes);
	}

	private void setContent(Element classificationElement,
			Classification classificationObject) {
		/*
		 * (#PCDATA)
		 */
		classificationElement.setText(classificationObject.getContent());
	}

}
