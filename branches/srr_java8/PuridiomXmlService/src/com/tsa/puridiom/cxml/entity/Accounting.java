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
public class Accounting implements Serializable {
	private String name;

	// private List<Segment> segment;
	private List segment = new ArrayList();

	// private List<AccountingSegment> accountingSegment;
	private List accountingSegment = new ArrayList();

	public Accounting() {
	}

	public Accounting(String name, List segment, List accountingSegment) {
		this.name = name;
		this.segment = segment;
		this.accountingSegment = accountingSegment;
	}

	/**
	 * @return the accountingSegment
	 */
	// public List<AccountingSegment> getAccountingSegment() {
	public List getAccountingSegment() {
		return accountingSegment;
	}

	/**
	 *
	 * @param accountingSegment
	 * @return
	 */
	public List addAccountingSegment(AccountingSegment accountingSegment) {
		this.accountingSegment.add(accountingSegment);

		return this.accountingSegment;
	}

	/**
	 * @param accountingSegment
	 *            the accountingSegment to set
	 */
	// public void setAccountingSegment(List<AccountingSegment>
	// accountingSegment) {
	public void setAccountingSegment(List accountingSegment) {
		this.accountingSegment = accountingSegment;
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
	 * @return the segment
	 */
	// public List<Segment> getSegment() {
	public List getSegment() {
		return segment;
	}

	/**
	 *
	 * @param segment
	 * @return
	 */
	public List addSegment(Segment segment) {
		this.segment.add(segment);

		return this.segment;
	}

	/**
	 * @param segment
	 *            the segment to set
	 */
	// public void setSegment(List<Segment> segment) {
	public void setSegment(List segment) {
		this.segment = segment;
	}

	/**
	 * <!ENTITY % cxml.accounting "( Segment+ | AccountingSegment+ )"> <!ELEMENT
	 * Accounting (%cxml.accounting;)> <!ATTLIST Accounting name %string;
	 * #REQUIRED >
	 */
	public static Element buildAccountingElement(Accounting accountingObject) {
		Element accountingElement = new Element("Accounting");

		accountingObject.setAttributes(accountingElement, accountingObject);

		accountingObject.setContent(accountingElement, accountingObject);

		return accountingElement;
	}

	private void setAttributes(Element accountingElement,
			Accounting accountingObject) {
		/*
		 * name %string; #REQUIRED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("name", accountingObject.getName()));

		accountingElement.setAttributes(attributes);
	}

	private void setContent(Element accountingElement,
			Accounting accountingObject) {
		/*
		 * (%cxml.accounting;)
		 */
		Iterator iterator;

		if (accountingObject.getSegment().size() > 0) {
			iterator = accountingObject.getSegment().iterator();
			do {
				accountingElement.addContent(Segment
						.buildSegmentElement((Segment) iterator.next()));
			} while (iterator.hasNext());
		} else {
			iterator = accountingObject.getAccountingSegment().iterator();
			do {
				accountingElement
						.addContent(AccountingSegment
								.buildAccountingSegmentElement((AccountingSegment) iterator
										.next()));
			} while (iterator.hasNext());
		}
	}

}
