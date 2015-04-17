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
public class Correspondent implements Serializable {
	private String preferredLanguage;

	// private List<Contact> contact;
	private List contact = new ArrayList();

	// private List<Extrinsic> extrinsic;
	private List extrinsic = new ArrayList();

	/**
	 * @return the contact
	 */
	// public List<Contact> getContact() {
	public List getContact() {
		return contact;
	}

	/**
	 *
	 * @param contact
	 * @return
	 */
	public List addContact(Contact contact) {
		this.contact.add(contact);

		return this.contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	// public void setContact(List<Contact> contact) {
	public void setContact(List contact) {
		this.contact = contact;
	}

	/**
	 * @return the extrinsic
	 */
	// public List<Extrinsic> getExtrinsic() {
	public List getExtrinsic() {
		return extrinsic;
	}

	public List addExtrinsic(Extrinsic extrinsic) {
		this.extrinsic.add(extrinsic);

		return this.extrinsic;
	}

	/**
	 * @param extrinsic
	 *            the extrinsic to set
	 */
	// public void setExtrinsic(List<Extrinsic> extrinsic) {
	public void setExtrinsic(List extrinsic) {
		this.extrinsic = extrinsic;
	}

	/**
	 * @return the preferredLanguage
	 */
	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	/**
	 * @param preferredLanguage
	 *            the preferredLanguage to set
	 */
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	/**
	 * <!ELEMENT Correspondent (Contact+, Extrinsic*)> <!ATTLIST Correspondent
	 * preferredLanguage %xmlLangCode; #IMPLIED >
	 *
	 * @param correspondentObject
	 * @return
	 */
	public static Element buildCorrespondentElement(
			Correspondent correspondentObject) {
		Element correspondentElement = new Element("Correspondent");

		correspondentObject.setAttributes(correspondentElement,
				correspondentObject);

		correspondentObject.setContent(correspondentElement,
				correspondentObject);

		return correspondentElement;
	}

	private void setAttributes(Element correspondentElement,
			Correspondent correspondentObject) {
		/*
		 * preferredLanguage %xmlLangCode; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (correspondentObject.getPreferredLanguage() != null) {
			attributes.add(new Attribute("preferredLanguage",
					correspondentObject.getPreferredLanguage()));
		}

		correspondentElement.setAttributes(attributes);
	}

	private void setContent(Element correspondentElement,
			Correspondent correspondentObject) {
		/*
		 * (Contact+, Extrinsic*)
		 */
		Iterator iterator = correspondentObject.getContact().iterator();

		do {
			correspondentElement.addContent(Contact
					.buildContactElement((Contact) iterator.next()));
		} while (iterator.hasNext());

		for (iterator = correspondentObject.getExtrinsic().iterator(); iterator
				.hasNext();) {
			correspondentElement.addContent(Extrinsic
					.buildExtrinsicElement((Extrinsic) iterator.next()));
		}
	}
}
