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
public class AccountingSegment implements Serializable {

	private String id;

	private Name name;

	private Description description;

	/**
	 * @return the description
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * <!ELEMENT AccountingSegment ( Name, Description )> <!ATTLIST
	 * AccountingSegment id %string; #REQUIRED >
	 */
	public static Element buildAccountingSegmentElement(
			AccountingSegment accountingSegmentObject) {
		Element accountingSegmentElement = new Element("AccountingSegment");

		accountingSegmentObject.setAttributes(accountingSegmentElement,
				accountingSegmentObject);

		accountingSegmentObject.setContent(accountingSegmentElement,
				accountingSegmentObject);

		return accountingSegmentElement;
	}

	private void setAttributes(Element accountingSegmentElement,
			AccountingSegment accountingSegmentObject) {
		/*
		 * id %string; #REQUIRED
		 */
//		List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("id", accountingSegmentObject.getId()));

		accountingSegmentElement.setAttributes(attributes);
	}

	private void setContent(Element accountingSegmentElement,
			AccountingSegment accountingSegmentObject) {
		/*
		 * ( Name, Description )
		 */
		accountingSegmentElement.addContent(Name
				.buildNameElement(accountingSegmentObject.getName()));

		accountingSegmentElement.addContent(Description
				.buildDescriptionElement(accountingSegmentObject
						.getDescription()));
	}

}
