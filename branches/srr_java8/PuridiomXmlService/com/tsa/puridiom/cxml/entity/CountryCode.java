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
public class CountryCode implements Serializable {
	private String isoCountryCode;

	private Integer content;

	public CountryCode() {
	}

	public CountryCode(String isoCountryCode, Integer content) {
		this.isoCountryCode = isoCountryCode;
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public Integer getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Integer content) {
		this.content = content;
	}

	/**
	 * @return the isoCountryCode
	 */
	public String getIsoCountryCode() {
		return isoCountryCode;
	}

	/**
	 * @param isoCountryCode
	 *            the isoCountryCode to set
	 */
	public void setIsoCountryCode(String isoCountryCode) {
		this.isoCountryCode = isoCountryCode;
	}

	/**
	 * <!ELEMENT CountryCode (#PCDATA)> <!-- uint --> <!ATTLIST CountryCode
	 * isoCountryCode %isoCountryCode; #REQUIRED >
	 *
	 * @param countryCodeObject
	 * @return
	 */
	public static Element buildCountryCodeElement(CountryCode countryCodeObject) {
		Element countryCodeElement = new Element("CountryCode");

		countryCodeObject.setAttributes(countryCodeElement, countryCodeObject);

		countryCodeObject.setContent(countryCodeElement, countryCodeObject);

		return countryCodeElement;
	}

	private void setAttributes(Element countryCodeElement,
			CountryCode countryCodeObject) {
		/*
		 * isoCountryCode %isoCountryCode; #REQUIRED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("isoCountryCode", countryCodeObject
				.getIsoCountryCode()));

		countryCodeElement.setAttributes(attributes);
	}

	private void setContent(Element countryCodeElement,
			CountryCode countryCodeObject) {
		/*
		 * (#PCDATA)
		 */
		countryCodeElement.setText(String.valueOf(countryCodeObject
				.getContent()));

	}

}
