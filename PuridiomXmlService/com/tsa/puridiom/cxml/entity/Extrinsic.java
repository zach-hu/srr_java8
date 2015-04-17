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
public class Extrinsic implements Serializable {
	private String name;

	private Object content;

	/**
	 * @return the content
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Object content) {
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
	 * <!ELEMENT Extrinsic ANY> <!ATTLIST Extrinsic name %string; #REQUIRED >
	 *
	 * @param extrinsicObject
	 * @return
	 */
	public static Element buildExtrinsicElement(Extrinsic extrinsicObject) {
		Element extrinsicElement = new Element("Extrinsic");

		extrinsicObject.setAttributes(extrinsicElement, extrinsicObject);

		extrinsicObject.setContent(extrinsicElement, extrinsicObject);

		return extrinsicElement;
	}

	private void setAttributes(Element extrinsicElement,
			Extrinsic extrinsicObject) {
		/*
		 * name %string; #REQUIRED
		 */
//		List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("name", extrinsicObject.getName()));

		extrinsicElement.setAttributes(attributes);
	}

	private void setContent(Element moneyElement, Extrinsic extrinsicObject) {
		/*
		 * ANY
		 */
		// TODO
	}
}
