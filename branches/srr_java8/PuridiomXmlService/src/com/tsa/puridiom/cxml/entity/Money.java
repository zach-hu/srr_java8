/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author Johnny Zapana
 */
public class Money implements Serializable
{

	private final static String DEFAULT_CURRENCY_CODE = "USD";

	private String currency;

	private BigDecimal alternateAmount;

	private String alternateCurrency;

	private BigDecimal content;

	public Money()
	{
	}

	public Money(String currency, BigDecimal content)
	{
		this.setCurrency(currency);

		this.content = content;

	}

	public Money(String currency, BigDecimal alternateAmount, String alternateCurrency, BigDecimal content)
	{
		this.setCurrency(currency);

		this.alternateAmount = alternateAmount;

		this.alternateCurrency = alternateCurrency;

		this.content = content;
	}

	/**
	 * @return the alternateAmount
	 */
	public BigDecimal getAlternateAmount()
	{
		return alternateAmount;
	}

	/**
	 * @param alternateAmount
	 *            the alternateAmount to set
	 */
	public void setAlternateAmount(BigDecimal alternateAmount)
	{
		this.alternateAmount = alternateAmount;
	}

	/**
	 * @return the alternateCurrency
	 */
	public String getAlternateCurrency()
	{
		return alternateCurrency;
	}

	/**
	 * @param alternateCurrency
	 *            the alternateCurrency to set
	 */
	public void setAlternateCurrency(String alternateCurrency)
	{
		this.alternateCurrency = alternateCurrency;
	}

	/**
	 * @return the content
	 */
	public BigDecimal getContent()
	{
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(BigDecimal content)
	{
		this.content = content;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency()
	{
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency)
	{
		this.currency = currency;

		if ((this.currency == null) || (this.currency.length() == 0))
		{
			this.currency = DEFAULT_CURRENCY_CODE;
		}
	}

	/**
	 * <!ELEMENT Money (#PCDATA)> <!-- number --> <!ATTLIST Money currency
	 * %isoCurrencyCode; #REQUIRED alternateAmount %number; #IMPLIED
	 * alternateCurrency %isoCurrencyCode; #IMPLIED >
	 *
	 * @param moneyElement
	 * @param moneyObject
	 */
	public static Element buildMoneyElement(Money moneyObject)
	{
		Element moneyElement = new Element("Money");

		moneyObject.setAttributes(moneyElement, moneyObject);

		moneyObject.setContent(moneyElement, moneyObject);

		return moneyElement;
	}

	private void setAttributes(Element moneyElement, Money moneyObject)
	{
		/*
		 * currency %isoCurrencyCode; #REQUIRED alternateAmount %number;
		 * #IMPLIED alternateCurrency %isoCurrencyCode; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("currency", moneyObject.getCurrency()));

		if (moneyObject.getAlternateAmount() != null)
		{
			attributes.add(new Attribute("alternateAmount", String.valueOf(moneyObject.getAlternateAmount())));
		}

		if (moneyObject.getAlternateCurrency() != null)
		{
			attributes.add(new Attribute("alternateCurrency", moneyObject.getAlternateCurrency()));
		}

		moneyElement.setAttributes(attributes);
	}

	private void setContent(Element moneyElement, Money moneyObject)
	{
		/*
		 * (#PCDATA)
		 */
		moneyElement.setText(String.valueOf(moneyObject.getContent()));

	}
}
