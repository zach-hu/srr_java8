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
public class ManufacturerName implements Serializable {
	private String lang;

	private String content;

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
	 * <!ELEMENT ManufacturerName (#PCDATA)> <!-- string --> <!ATTLIST
	 * ManufacturerName xml:lang %xmlLangCode; #IMPLIED >
	 */
	public static Element buildManufacturerNameElement(
			ManufacturerName manufacturerNameObject) {
		Element manufacturerNameElement = new Element("ManufacturerName");

		manufacturerNameObject.setAttributes(manufacturerNameElement,
				manufacturerNameObject);

		manufacturerNameObject.setContent(manufacturerNameElement,
				manufacturerNameObject);

		return manufacturerNameElement;
	}

	private void setAttributes(Element manufacturerNameElement,
			ManufacturerName manufacturerNameObject) {
		/*
		 * xml:lang %xmlLangCode; #IMPLIED
		 */
//		List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (manufacturerNameObject.getLang() != null) {
			attributes.add(new Attribute("lang", manufacturerNameObject
					.getLang(), Namespace.getNamespace("xml",
					"http://www.w3.org/1999/xhtml")));
		}

		manufacturerNameElement.setAttributes(attributes);
	}

	private void setContent(Element manufacturerNameElement,
			ManufacturerName manufacturerNameObject) {
		/*
		 * (#PCDATA)
		 */
		manufacturerNameElement.setText(manufacturerNameObject.getContent());

	}
}
