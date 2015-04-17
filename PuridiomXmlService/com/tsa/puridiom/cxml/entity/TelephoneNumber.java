/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;

import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class TelephoneNumber implements Serializable {
	private CountryCode countryCode;

	private Integer areaOrCityCode;

	private String number;

	private Integer extension;

	public TelephoneNumber() {
	}

	public TelephoneNumber(CountryCode countryCode, Integer areaOrCityCode,
			String number) {
		this.countryCode = countryCode;
		this.areaOrCityCode = areaOrCityCode;
		this.number = number;
	}

	public TelephoneNumber(CountryCode countryCode, Integer areaOrCityCode,
			String number, Integer extension) {
		this.countryCode = countryCode;
		this.areaOrCityCode = areaOrCityCode;
		this.number = number;
		this.extension = extension;
	}

	/**
	 * @return the areaOrCityCode
	 */
	public Integer getAreaOrCityCode() {
		return areaOrCityCode;
	}

	/**
	 * @param areaOrCityCode
	 *            the areaOrCityCode to set
	 */
	public void setAreaOrCityCode(Integer areaOrCityCode) {
		this.areaOrCityCode = areaOrCityCode;
	}

	/**
	 * @return the countryCode
	 */
	public CountryCode getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the extension
	 */
	public Integer getExtension() {
		return extension;
	}

	/**
	 * @param extension
	 *            the extension to set
	 */
	public void setExtension(Integer extension) {
		this.extension = extension;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * <!ELEMENT TelephoneNumber (CountryCode, AreaOrCityCode, Number,
	 * Extension?)>
	 *
	 * @param telephoneNumberObject
	 * @return
	 */
	public static Element buildTelephoneNumberElement(
			TelephoneNumber telephoneNumberObject) {
		Element telephoneNumberElement = new Element("TelephoneNumber");

		telephoneNumberObject.setContent(telephoneNumberElement,
				telephoneNumberObject);

		return telephoneNumberElement;
	}

	private void setContent(Element telephoneNumberElement,
			TelephoneNumber telephoneNumberObject) {
		/*
		 * (CountryCode, AreaOrCityCode, Number, Extension?)
		 */

		telephoneNumberElement
				.addContent(CountryCode
						.buildCountryCodeElement(telephoneNumberObject
								.getCountryCode()));

		telephoneNumberElement.addContent(new Element(
				Params.AREA_OR_CITY_CODE_ELEMENT).setText(String
				.valueOf(telephoneNumberObject.getAreaOrCityCode())));

		telephoneNumberElement.addContent(new Element(Params.NUMBER_ELEMENT)
				.setText(telephoneNumberObject.getNumber()));

		if (telephoneNumberObject.getExtension() != null) {
			telephoneNumberElement.addContent(new Element(
					Params.EXTENSION_ELEMENT).setText(String
					.valueOf(telephoneNumberObject.getExtension())));
		}
	}
}
