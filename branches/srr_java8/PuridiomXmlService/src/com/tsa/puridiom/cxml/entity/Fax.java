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
public class Fax implements Serializable {
	private String name;

	private TelephoneNumber telephoneNumber;

	private URL url;

	private Email email;

	public Fax() {
	}

	public Fax(TelephoneNumber telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Fax(URL url) {
		this.url = url;
	}

	public Fax(Email email) {
		this.email = email;
	}

	public Fax(String name, TelephoneNumber telephoneNumber, URL url,
			Email email) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		this.url = url;
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(Email email) {
		this.email = email;
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
	 * @return the telephoneNumber
	 */
	public TelephoneNumber getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber
	 *            the telephoneNumber to set
	 */
	public void setTelephoneNumber(TelephoneNumber telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}

	/**
	 * <!ELEMENT Fax (TelephoneNumber | URL | Email)> <!ATTLIST Fax name
	 * %string; #IMPLIED >
	 *
	 * @param faxElement
	 * @param faxObject
	 */
	public static Element buildFaxElement(Fax faxObject) {
		Element faxElement = new Element("Fax");

		faxObject.setAttributes(faxElement, faxObject);

		faxObject.setContent(faxElement, faxObject);

		return faxElement;
	}

	private void setAttributes(Element faxElement, Fax faxObject) {
		/*
		 * name %string; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (faxObject.getName() != null) {
			attributes.add(new Attribute("name", faxObject.getName()));
		}

		faxElement.setAttributes(attributes);
	}

	private void setContent(Element faxElement, Fax faxObject) {
		Element faxChildElement;
		/*
		 * (TelephoneNumber | URL | Email)
		 */
		if (faxObject.getTelephoneNumber() != null) {
			faxChildElement = TelephoneNumber
					.buildTelephoneNumberElement(faxObject.getTelephoneNumber());

		} else if (faxObject.getUrl() != null) {
			faxChildElement = URL.buildURlElement(faxObject.getUrl());
		} else {
			faxChildElement = Email.buildEmailElement(faxObject.getEmail());
		}

		faxElement.addContent(faxChildElement);
	}
}
