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
 *
 */
public class AddressShipBillTo implements Serializable {
	private String isoCountryCode;

	private String addressID;

	private Name name;

	private PostalAddress postalAddress;

	private Email email;

	private Phone phone;

	private Fax fax;

	private URL url;

	public AddressShipBillTo() {
	}

	public AddressShipBillTo(String isoCountryCode, String addressID,
			Name name, PostalAddress postalAddress, Email email, Phone phone,
			Fax fax, URL url) {
		this.isoCountryCode = isoCountryCode;
		this.addressID = addressID;
		this.name = name;
		this.postalAddress = postalAddress;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.url = url;
	}

	/**
	 * @return the addressID
	 */
	public String getAddressID() {
		return addressID;
	}

	/**
	 * @param addressID
	 *            the addressID to set
	 */
	public void setAddressID(String addressID) {
		this.addressID = addressID;
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
	 * @return the fax
	 */
	public Fax getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(Fax fax) {
		this.fax = fax;
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
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public Phone getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	/**
	 * @return the postalAddress
	 */
	public PostalAddress getPostalAddress() {
		return postalAddress;
	}

	/**
	 * @param postalAddress
	 *            the postalAddress to set
	 */
	public void setPostalAddress(PostalAddress postalAddress) {
		this.postalAddress = postalAddress;
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
	 * <!ELEMENT Address (Name, PostalAddress?, Email?, Phone?, Fax?, URL?)>
	 * <!ATTLIST Address isoCountryCode %isoCountryCode; #IMPLIED addressID
	 * %string; #IMPLIED >
	 *
	 * @param addressElement
	 * @param addressObject
	 */
	public static Element buildAddressElement(AddressShipBillTo addressObject) {
		Element addressElement = new Element("Address");

		addressObject.setAttributes(addressElement, addressObject);

		addressObject.setContent(addressElement, addressObject);

		return addressElement;
	}

	private void setAttributes(Element addressElement,
			AddressShipBillTo addressObject) {
		/*
		 * isoCountryCode %isoCountryCode; #IMPLIED addressID %string; #IMPLIED
		 */

		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (addressObject.getIsoCountryCode() != null) {
			attributes.add(new Attribute("isoCountryCode", addressObject
					.getIsoCountryCode()));
		}

		if (addressObject.getAddressID() != null) {
			attributes.add(new Attribute("addressID", addressObject
					.getAddressID()));
		}

		addressElement.setAttributes(attributes);
	}

	private void setContent(Element addressElement,
			AddressShipBillTo addressObject) {
		/* (Name, PostalAddress?, Email?, Phone?, Fax?, URL?) */

		addressElement.addContent(Name
				.buildNameElement(addressObject.getName()));

		if (addressObject.getPostalAddress() != null) {
			addressElement
					.addContent(PostalAddress
							.buildPostalAddressElement(addressObject
									.getPostalAddress()));
		}

		if (addressObject.getEmail() != null) {
			addressElement.addContent(Email.buildEmailElement(addressObject
					.getEmail()));
		}

		if (addressObject.getPhone() != null) {
			addressElement.addContent(Phone.buildPhoneElement(addressObject
					.getPhone()));
		}

		if (addressObject.getFax() != null) {
			addressElement.addContent(Fax.buildFaxElement(addressObject
					.getFax()));
		}

		if (addressObject.getUrl() != null) {
			addressElement.addContent(URL.buildURlElement(addressObject
					.getUrl()));
		}
	}
}
