/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class Identity implements Serializable {

	private Object content;

	private Date lastChangedTimestamp;

	public Identity() {
	}

	public Identity(String content) {
		this.content = content;
	}

	public Identity(String content, Date lastChangedTimestamp) {
		this.content = content;
		this.lastChangedTimestamp = lastChangedTimestamp;
	}

	/**
	 * @return the content
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Object content) {
		this.content = content;
	}

	/**
	 * @return the lastChangedTimestamp
	 */
	public Date getLastChangedTimestamp() {
		return lastChangedTimestamp;
	}

	/**
	 * @param lastChangedTimestamp
	 *            the lastChangedTimestamp to set
	 */
	public void setLastChangedTimestamp(Date lastChangedTimestamp) {
		this.lastChangedTimestamp = lastChangedTimestamp;
	}

	/**
	 * <!ELEMENT Identity ANY> <!ATTLIST Identity lastChangedTimestamp
	 * %datetime.tz; #IMPLIED >
	 *
	 * @param identityObject
	 * @return
	 */
	public static Element buildIdentityElement(Identity identityObject) {
		Element identityElement = new Element("Identity");

		identityObject.setAttributes(identityElement, identityObject);

		identityObject.setContent(identityElement, identityObject);

		return identityElement;
	}

	private void setAttributes(Element identityElement, Identity identityObject) {
		/*
		 * lastChangedTimestamp %datetime.tz; #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		if (identityObject.getLastChangedTimestamp() != null) {
			attributes.add(new Attribute("lastChangedTimestamp", String
					.valueOf(identityObject.getLastChangedTimestamp())));
		}

		identityElement.setAttributes(attributes);
	}

	private void setContent(Element identityElement, Identity identityObject) {
		/*
		 * ANY
		 */
//		if (identityObject.getContent() instanceof AnyObject) {
//			identityElement.setText((AnyObject) identityObject.getContent());
//		} else {
			identityElement.setText(identityObject.getContent().toString());
//		}
	}
}
