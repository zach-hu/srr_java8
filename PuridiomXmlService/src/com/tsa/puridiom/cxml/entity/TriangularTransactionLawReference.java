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
public class TriangularTransactionLawReference implements Serializable {

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
	 * <!ELEMENT TriangularTransactionLawReference (#PCDATA)> <!ATTLIST
	 * TriangularTransactionLawReference xml:lang %xmlLangCode; #REQUIRED >
	 *
	 * @param triangularTransactionLawReferenceElement
	 * @param triangularTransactionLawReferenceObject
	 */
	public static Element buildTriangularTransactionLawReferenceElement(
			TriangularTransactionLawReference triangularTransactionLawReferenceObject) {
		Element triangularTransactionLawReferenceElement = new Element(
				"TriangularTransactionLawReference");

		triangularTransactionLawReferenceObject.setAttributes(
				triangularTransactionLawReferenceElement,
				triangularTransactionLawReferenceObject);

		triangularTransactionLawReferenceObject.setContent(
				triangularTransactionLawReferenceElement,
				triangularTransactionLawReferenceObject);

		return triangularTransactionLawReferenceElement;
	}

	private void setAttributes(
			Element triangularTransactionLawReferenceElement,
			TriangularTransactionLawReference triangularTransactionLawReferenceObject) {
		/*
		 * xml:lang %xmlLangCode; #REQUIRED
		 */
//		List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("lang",
				triangularTransactionLawReferenceObject.getLang(), Namespace
						.getNamespace("xml", "http://www.w3.org/1999/xhtml")));

		triangularTransactionLawReferenceElement.setAttributes(attributes);
	}

	private void setContent(
			Element triangularTransactionLawReferenceElement,
			TriangularTransactionLawReference triangularTransactionLawReferenceObject) {
		/*
		 * (#PCDATA)
		 */
		triangularTransactionLawReferenceElement
				.setText(triangularTransactionLawReferenceObject.getContent());

	}
}
