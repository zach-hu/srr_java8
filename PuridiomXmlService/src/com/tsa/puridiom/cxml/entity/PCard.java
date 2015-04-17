/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author Johnny Zapana
 */
public class PCard implements Serializable
{
	private final static SimpleDateFormat EXP_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private BigDecimal number;

	private String expiration;

	private String name;

	private PostalAddress postalAddress;

	public PCard()
	{
	}

	public PCard(BigDecimal number, String expiration, String name, PostalAddress postalAddress)
	{
		this.number = number;
		this.expiration = expiration;
		this.name = name;
		this.postalAddress = postalAddress;
	}

	/**
	 * @return the expiration
	 */
	public String getExpiration()
	{
		return expiration;
	}

	/**
	 * @param expiration
	 *            the expiration to set
	 */
	public void setExpiration(String expiration)
	{
		this.expiration = expiration;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public BigDecimal getNumber()
	{
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(BigDecimal number)
	{
		this.number = number;
	}

	/**
	 * @return the postalAddress
	 */
	public PostalAddress getPostalAddress()
	{
		return postalAddress;
	}

	/**
	 * @param postalAddress
	 *            the postalAddress to set
	 */
	public void setPostalAddress(PostalAddress postalAddress)
	{
		this.postalAddress = postalAddress;
	}

	/**
	 * <!ELEMENT PCard (PostalAddress?)> <!ATTLIST PCard number %number;
	 * #REQUIRED expiration %date; #REQUIRED name %string; #IMPLIED >
	 *
	 * @param commentsObject
	 * @return
	 */
	public static Element buildPCardElement(PCard pCardObject)
	{
		Element pCardElement = new Element("PCard");

		pCardObject.setAttributes(pCardElement, pCardObject);

		pCardObject.setContent(pCardElement, pCardObject);

		return pCardElement;
	}

	private void setAttributes(Element pCardElement, PCard pCardObject)
	{
		/*
		 * number %number; #REQUIRED expiration %date; #REQUIRED name %string;
		 * #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("number", String.valueOf(pCardObject.getNumber())));

		attributes.add(new Attribute("expiration", EXP_FORMAT.format(pCardObject.getExpiration())));

		if (pCardObject.getName() != null)
		{
			attributes.add(new Attribute("name", pCardObject.getName()));
		}

		pCardElement.setAttributes(attributes);
	}

	private void setContent(Element commentsElement, PCard pCardObject)
	{
		/*
		 * (PostalAddress?)
		 */

		if (pCardObject.getPostalAddress() != null)
		{
			commentsElement.addContent(PostalAddress.buildPostalAddressElement(pCardObject.getPostalAddress()));
		}
	}
}
