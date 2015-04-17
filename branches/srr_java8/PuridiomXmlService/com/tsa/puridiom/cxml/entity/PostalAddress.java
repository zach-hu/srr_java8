/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class PostalAddress implements Serializable {
	private String name;

	// private List<String> deliverTo;
	private List deliverTo = new ArrayList();

	// private List<String> street;
	private List street = new ArrayList();

	private String city;

	private String state;

	private String postalCode;

	private Country country;

	public PostalAddress() {
	}

	public PostalAddress(String name, List deliverTo, List street, String city,
			String state, String postalCode, Country country) {
		this.name = name;
		this.deliverTo = deliverTo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the deliverTo
	 */
	// public List<String> getDeliverTo() {
	public List getDeliverTo() {
		return deliverTo;
	}

	/**
	 *
	 * @param deliverTo
	 * @return
	 */
	public List addDeliverTo(String deliverTo) {
		this.deliverTo.add(deliverTo);

		return this.deliverTo;
	}

	/**
	 * @param deliverTo
	 *            the deliverTo to set
	 */
	// public void setDeliverTo(List<String> deliverTo) {
	public void setDeliverTo(List deliverTo) {
		this.deliverTo = deliverTo;
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
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the street
	 */
	// public List<String> getStreet() {
	public List getStreet() {
		return street;
	}

	/**
	 *
	 * @param street
	 * @return
	 */
	public List addStreet(String street) {
		this.street.add(street);

		return this.street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	// public void setStreet(List<String> street) {
	public void setStreet(List street) {
		this.street = street;
	}

	/**
	 * <!ELEMENT PostalAddress (DeliverTo*, Street+, City, State?, PostalCode?,
	 * Country)> <!ATTLIST PostalAddress name %string; #IMPLIED >
	 *
	 * @param postalAddressObject
	 * @return
	 */
	public static Element buildPostalAddressElement(
			PostalAddress postalAddressObject) {
		Element postalAddressElement = new Element("PostalAddress");

		postalAddressObject.setAttributes(postalAddressElement,
				postalAddressObject);

		postalAddressObject.setContent(postalAddressElement,
				postalAddressObject);

		return postalAddressElement;
	}

	private void setAttributes(Element postalAddressElement,
			PostalAddress postalAddressObject) {
		/*
		 * name %string; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (postalAddressObject.getName() != null) {
			attributes
					.add(new Attribute("name", postalAddressObject.getName()));
		}

		postalAddressElement.setAttributes(attributes);
	}

	private void setContent(Element postalAddressElement,
			PostalAddress postalAddressObject) {
		/*
		 * (DeliverTo*, Street+, City, State?, PostalCode?, Country)
		 */
		Iterator iterator;

		for (iterator = postalAddressObject.getDeliverTo().iterator(); iterator
				.hasNext();) {
			postalAddressElement.addContent(new Element(
					Params.DELIVER_TO_ELEMENT)
					.setText((String) iterator.next()));
		}

		iterator = postalAddressObject.getStreet().iterator();

		do {

			postalAddressElement.addContent(new Element(Params.STREET_ELEMENT)
					.setText((String) iterator.next()));

		} while (iterator.hasNext());

		postalAddressElement.addContent(new Element(Params.CITY_ELEMENT)
				.setText(postalAddressObject.getCity()));

		if (postalAddressObject.getState() != null) {
			postalAddressElement.addContent(new Element(Params.STATE_ELEMENT)
					.setText(postalAddressObject.getState()));
		}

		if (postalAddressObject.getPostalCode() != null) {
			postalAddressElement.addContent(new Element(
					Params.POSTAL_CODE_ELEMENT).setText(postalAddressObject
					.getPostalCode()));
		}

		postalAddressElement.addContent(Country
				.buildCountryElement(postalAddressObject.getCountry()));

	}

}
