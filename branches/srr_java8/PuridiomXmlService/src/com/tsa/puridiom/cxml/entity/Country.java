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
 * @author JOHNNY
 */
public class Country implements Serializable
{

	private String isoCountryCode;

	private String content;

	public Country()
	{
	}

	public Country(String isoCountryCode, String content)
	{
		this.setIsoCountryCode(isoCountryCode);
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * @return the isoCountryCode
	 */
	public String getIsoCountryCode()
	{
		return isoCountryCode;
	}

	/**
	 * @param isoCountryCode
	 *            the isoCountryCode to set
	 */
	public void setIsoCountryCode(String isoCountryCode)
	{
		this.isoCountryCode = isoCountryCode;

		if ((this.isoCountryCode != null) && (this.isoCountryCode.length() > 2))
		{
			this.isoCountryCode = this.isoCountryCode.substring(0, 2).toUpperCase();
		}
	}

	/**
	 * <!ELEMENT Country (#PCDATA)> <!-- string --> <!ATTLIST Country
	 * isoCountryCode %isoCountryCode; #REQUIRED >
	 *
	 * @param countryObject
	 * @return
	 */
	public static Element buildCountryElement(Country countryObject)
	{
		Element countryElement = new Element("Country");

		countryObject.setAttributes(countryElement, countryObject);

		countryObject.setContent(countryElement, countryObject);

		return countryElement;
	}

	private void setAttributes(Element countryElement, Country countryObject)
	{
		/*
		 * isoCountryCode %isoCountryCode; #REQUIRED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("isoCountryCode", countryObject.getIsoCountryCode()));

		countryElement.setAttributes(attributes);
	}

	private void setContent(Element countryElement, Country countryObject)
	{
		/*
		 * (#PCDATA)
		 */
		countryElement.setText(countryObject.getContent());

	}
}
