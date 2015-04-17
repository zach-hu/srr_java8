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
public class Phone implements Serializable {
	private String name;

	private TelephoneNumber telephoneNumber;

	public Phone() {
	}

	public Phone(TelephoneNumber telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Phone(String name, TelephoneNumber telephoneNumber) {
		this.name = name;
		this.telephoneNumber = telephoneNumber;
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
	 * <!ELEMENT Phone (TelephoneNumber)> <!ATTLIST Phone name %string; #IMPLIED >
	 *
	 * @param phoneObject
	 * @return
	 */
	public static Element buildPhoneElement(Phone phoneObject) {
		Element phoneElement = new Element("Phone");

		phoneObject.setAttributes(phoneElement, phoneObject);

		phoneObject.setContent(phoneElement, phoneObject);

		return phoneElement;
	}

	private void setAttributes(Element phoneElement, Phone phoneObject) {
		/*
		 * name %string; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (phoneObject.getName() != null) {
			attributes.add(new Attribute("name", phoneObject.getName()));
		}

		phoneElement.setAttributes(attributes);
	}

	private void setContent(Element phoneElement, Phone phoneObject) {
		/*
		 * (TelephoneNumber)
		 */
		phoneElement.addContent(TelephoneNumber
				.buildTelephoneNumberElement(phoneObject.getTelephoneNumber()));
	}
}
