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
public class Contact implements Serializable {
	private String role;

	private String addressID;

	private Name name;

	// private List<PostalAddress> postalAddress;
	private List postalAddress = new ArrayList();

	// private List<Email> email;
	private List email = new ArrayList();

	// private List<Phone> phone;
	private List phone = new ArrayList();

	// private List<Fax> fax;
	private List fax = new ArrayList();

	// private List<URL> url;
	private List url = new ArrayList();

	public Contact() {
	}

	public Contact(String role, String addressID, Name name,
			List postalAddress, List email, List phone, List fax, List url) {
		this.role = role;
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
	// public List<Email> getEmail() {
	public List getEmail() {
		return email;
	}

	/**
	 *
	 * @param email
	 * @return
	 */
	public List addEmail(Email email) {
		this.email.add(email);

		return this.email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	// public void setEmail(List<Email> email) {
	public void setEmail(List email) {
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	// public List<Fax> getFax() {
	public List getFax() {
		return fax;
	}

	/**
	 *
	 * @param fax
	 * @return
	 */
	public List addFax(Fax fax) {
		this.fax.add(fax);

		return this.fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	// public void setFax(List<Fax> fax) {
	public void setFax(List fax) {
		this.fax = fax;
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
	// public List<Phone> getPhone() {
	public List getPhone() {
		return phone;
	}

	/**
	 *
	 * @param phone
	 * @return
	 */
	public List addPhone(Phone phone) {
		this.phone.add(phone);

		return this.phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	// public void setPhone(List<Phone> phone) {
	public void setPhone(List phone) {
		this.phone = phone;
	}

	/**
	 * @return the postalAddress
	 */
	// public List<PostalAddress> getPostalAddress() {
	public List getPostalAddress() {
		return postalAddress;
	}

	/**
	 *
	 * @param postalAddress
	 * @return
	 */
	public List addPostalAddress(PostalAddress postalAddress) {
		this.postalAddress.add(postalAddress);

		return this.postalAddress;
	}

	/**
	 * @param postalAddress
	 *            the postalAddress to set
	 */
	// public void setPostalAddress(List<PostalAddress> postalAddress) {
	public void setPostalAddress(List postalAddress) {
		this.postalAddress = postalAddress;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the url
	 */
	// public List<URL> getUrl() {
	public List getUrl() {
		return url;
	}

	/**
	 *
	 * @param url
	 * @return
	 */
	public List addUrl(URL url) {
		this.url.add(phone);

		return this.url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	// public void setUrl(List<URL> url) {
	public void setUrl(List url) {
		this.url = url;
	}

	/**
	 * <!ELEMENT Contact (Name, PostalAddress*, Email*, Phone*, Fax*, URL*)>
	 * <!ATTLIST Contact role NMTOKEN #IMPLIED addressID %string; #IMPLIED >
	 *
	 * @param contactObject
	 * @return
	 */
	public static Element buildContactElement(Contact contactObject) {
		Element contactElement = new Element("Contact");

		contactObject.setAttributes(contactElement, contactObject);

		contactObject.setContent(contactElement, contactObject);

		return contactElement;
	}

	private void setAttributes(Element contactElement, Contact contactObject) {
		/*
		 * role NMTOKEN #IMPLIED addressID %string; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (contactObject.getRole() != null) {
			attributes.add(new Attribute("role", contactObject.getRole()));
		}

		if (contactObject.getAddressID() != null) {
			attributes.add(new Attribute("addressID", contactObject
					.getAddressID()));
		}

		contactElement.setAttributes(attributes);
	}

	private void setContent(Element contactElement, Contact contactObject) {
		/*
		 * (Name, PostalAddress*, Email*, Phone*, Fax*, URL*)
		 */
		Iterator iterator;

		contactElement.addContent(Name
				.buildNameElement(contactObject.getName()));

		for (iterator = contactObject.getPostalAddress().iterator(); iterator
				.hasNext();) {
			contactElement
					.addContent(PostalAddress
							.buildPostalAddressElement((PostalAddress) iterator
									.next()));
		}

		for (iterator = contactObject.getEmail().iterator(); iterator.hasNext();) {
			contactElement.addContent(Email.buildEmailElement((Email) iterator
					.next()));
		}

		for (iterator = contactObject.getPhone().iterator(); iterator.hasNext();) {
			contactElement.addContent(Phone.buildPhoneElement((Phone) iterator
					.next()));
		}

		for (iterator = contactObject.getFax().iterator(); iterator.hasNext();) {
			contactElement.addContent(Fax
					.buildFaxElement((Fax) iterator.next()));
		}

		for (iterator = contactObject.getUrl().iterator(); iterator.hasNext();) {
			contactElement.addContent(URL
					.buildURlElement((URL) iterator.next()));
		}

	}

}
