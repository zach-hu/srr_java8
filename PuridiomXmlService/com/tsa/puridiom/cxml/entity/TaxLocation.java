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
public class TaxLocation implements Serializable {
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
	 * <!ELEMENT TaxLocation (#PCDATA)> <!-- string --> <!ATTLIST TaxLocation
	 * xml:lang %xmlLangCode; #REQUIRED >
	 *
	 * @param taxLocationElement
	 * @param taxLocationObject
	 */
	public static Element buildTaxLocationElement(TaxLocation taxLocationObject) {
		Element taxLocationElement = new Element("TaxLocation");

		taxLocationObject.setAttributes(taxLocationElement, taxLocationObject);

		taxLocationObject.setContent(taxLocationElement, taxLocationObject);

		return taxLocationElement;
	}

	private void setAttributes(Element taxLocationElement,
			TaxLocation taxLocationObject) {
		/*
		 * xml:lang %xmlLangCode; #REQUIRED
		 */
//		List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("lang", taxLocationObject.getLang(),
				Namespace.getNamespace("xml", "http://www.w3.org/1999/xhtml")));

		taxLocationElement.setAttributes(attributes);
	}

	private void setContent(Element taxLocationElement,
			TaxLocation taxLocationObject) {
		/*
		 * (#PCDATA)
		 */
		taxLocationElement.setText(taxLocationObject.getContent());

	}
}
