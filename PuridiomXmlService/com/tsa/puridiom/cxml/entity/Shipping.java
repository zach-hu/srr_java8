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
public class Shipping implements Serializable {
	private String trackingDomain;

	private String trackingId;

	private String tracking;

	private Money money;

	private Description description;

	public Shipping() {
	}

	public Shipping(String trackingDomain, String trackingId, String tracking,
			Money money, Description description) {
		this.trackingDomain = trackingDomain;
		this.trackingId = trackingId;
		this.tracking = tracking;
		this.money = money;
		this.description = description;
	}

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
	 * @return the money
	 */
	public Money getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(Money money) {
		this.money = money;
	}

	/**
	 * @return the tracking
	 */
	public String getTracking() {
		return tracking;
	}

	/**
	 * @param tracking
	 *            the tracking to set
	 */
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}

	/**
	 * @return the trackingDomain
	 */
	public String getTrackingDomain() {
		return trackingDomain;
	}

	/**
	 * @param trackingDomain
	 *            the trackingDomain to set
	 */
	public void setTrackingDomain(String trackingDomain) {
		this.trackingDomain = trackingDomain;
	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}

	/**
	 * @param trackingId
	 *            the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	/**
	 * <!ELEMENT Shipping (Money, Description)> <!ATTLIST Shipping
	 * trackingDomain %string; #IMPLIED trackingId %string; #IMPLIED tracking
	 * %string; #IMPLIED >
	 *
	 * @param shippingElement
	 * @param shippingObject
	 */
	public static Element buildShippingElement(Shipping shippingObject) {
		Element shippingElement = new Element("Shipping");

		shippingObject.setAttributes(shippingElement, shippingObject);

		shippingObject.setContent(shippingElement, shippingObject);

		return shippingElement;
	}

	private void setAttributes(Element shippingElement, Shipping shippingObject) {
		/*
		 * trackingDomain %string; #IMPLIED trackingId %string; #IMPLIED
		 * tracking %string; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (shippingObject.getTrackingDomain() != null) {
			attributes.add(new Attribute("trackingDomain", shippingObject
					.getTrackingDomain()));
		}

		if (shippingObject.getTrackingId() != null) {
			attributes.add(new Attribute("trackingId", shippingObject
					.getTrackingId()));
		}

		if (shippingObject.getTracking() != null) {
			attributes.add(new Attribute("tracking", shippingObject
					.getTracking()));
		}

		shippingElement.setAttributes(attributes);
	}

	private void setContent(Element shippingElement, Shipping shippingObject) {
		/*
		 * (Money, Description)
		 */
		shippingElement.addContent(Money.buildMoneyElement(shippingObject
				.getMoney()));

		shippingElement.addContent(Description
				.buildDescriptionElement(shippingObject.getDescription()));
	}

}
