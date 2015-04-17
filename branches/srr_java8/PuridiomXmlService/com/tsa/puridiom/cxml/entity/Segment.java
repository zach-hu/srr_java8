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
public class Segment implements Serializable {
	private String type;

	private String id;

	private String description;

	public Segment() {
	}

	public Segment(String type, String id, String description) {
		this.type = type;
		this.id = id;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * <!ELEMENT Segment EMPTY> <!ATTLIST Segment type %string; #REQUIRED id
	 * %string; #REQUIRED description %string; #REQUIRED >
	 */
	public static Element buildSegmentElement(Segment segmentObject) {
		Element segmentElement = new Element("Segment");

		segmentObject.setAttributes(segmentElement, segmentObject);

		return segmentElement;
	}

	private void setAttributes(Element segmentElement, Segment segmentObject) {
		/*
		 * type %string; #REQUIRED id %string; #REQUIRED description %string;
		 * #REQUIRED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("type", segmentObject.getType()));

		attributes.add(new Attribute("id", segmentObject.getId()));

		attributes.add(new Attribute("description", segmentObject
				.getDescription()));

		segmentElement.setAttributes(attributes);
	}

}
