/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Element;

/**
 * @author Johnny Zapana
 *
 */
public class ItemDetail implements Serializable {

	private Money unitPrice;

	// private List<Description> description;
	private List description = new ArrayList();

	private String unitOfMeasure;

	// private List<Classification> classification;
	private List classification = new ArrayList();

	private String manufacturerPartID;

	private ManufacturerName manufacturerName;

	private URL url;

	private Integer leadTime;

	// private List<Extrinsic> extrinsic;
	private List extrinsic = new ArrayList();

	/**
	 * @return the classification
	 */
	// public List<Classification> getClassification() {
	public List getClassification() {
		return classification;
	}

	/**
	 *
	 * @param classification
	 * @return
	 */
	public List addClassification(Classification classification) {
		this.classification.add(classification);

		return this.classification;
	}

	/**
	 * @param classification
	 *            the classification to set
	 */
	// public void setClassification(List<Classification> classification) {
	public void setClassification(List classification) {
		this.classification = classification;
	}

	/**
	 * @return the description
	 */
	// public List<Description> getDescription() {
	public List getDescription() {
		return description;
	}

	/**
	 *
	 * @param description
	 * @return
	 */
	public List addDescription(Description description) {
		this.description.add(description);

		return this.description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	// public void setDescription(List<Description> description) {
	public void setDescription(List description) {
		this.description = description;
	}

	/**
	 * @return the extrinsic
	 */
	// public List<Extrinsic> getExtrinsic() {
	public List getExtrinsic() {
		return extrinsic;
	}

	/**
	 *
	 * @param extrinsic
	 * @return
	 */
	public List addExtrinsic(Extrinsic extrinsic) {
		this.extrinsic.add(extrinsic);

		return this.extrinsic;
	}

	/**
	 * @param extrinsic
	 *            the extrinsic to set
	 */
	// public void setExtrinsic(List extrinsic) {
	public void setExtrinsic(List extrinsic) {
		this.extrinsic = extrinsic;
	}

	/**
	 * @return the leadTime
	 */
	public Integer getLeadTime() {
		return leadTime;
	}

	/**
	 * @param leadTime
	 *            the leadTime to set
	 */
	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}

	/**
	 * @return the manufacturerName
	 */
	public ManufacturerName getManufacturerName() {
		return manufacturerName;
	}

	/**
	 * @param manufacturerName
	 *            the manufacturerName to set
	 */
	public void setManufacturerName(ManufacturerName manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	/**
	 * @return the manufacturerPartID
	 */
	public String getManufacturerPartID() {
		return manufacturerPartID;
	}

	/**
	 * @param manufacturerPartID
	 *            the manufacturerPartID to set
	 */
	public void setManufacturerPartID(String manufacturerPartID) {
		this.manufacturerPartID = manufacturerPartID;
	}

	/**
	 * @return the unitOfMeasure
	 */
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	/**
	 * @param unitOfMeasure
	 *            the unitOfMeasure to set
	 */
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	/**
	 * @return the unitPrice
	 */
	public Money getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(Money unitPrice) {
		this.unitPrice = unitPrice;
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
	 * <!ELEMENT ItemDetail (UnitPrice, Description+, UnitOfMeasure,
	 * Classification+, ManufacturerPartID?, ManufacturerName?, URL?, LeadTime?,
	 * Extrinsic*)>
	 */
	public static Element buildItemDetailElement(ItemDetail itemDetailObject) {
		Element itemDetailElement = new Element("ItemDetail");

		itemDetailObject.setContent(itemDetailElement, itemDetailObject);

		return itemDetailElement;
	}

	private void setContent(Element itemDetailElement,
			ItemDetail itemDetailObject) {
		/*
		 * (UnitPrice, Description+, UnitOfMeasure, Classification+,
		 * ManufacturerPartID?, ManufacturerName?, URL?, LeadTime?, Extrinsic*)
		 */
		Element unitPriceElement = new Element(Params.UNIT_PRICE_ELEMENT);

		unitPriceElement.addContent(Money.buildMoneyElement(itemDetailObject
				.getUnitPrice()));

		itemDetailElement.addContent(unitPriceElement);

		Iterator iterator = itemDetailObject.getDescription().iterator();
		do {
			itemDetailElement.addContent(Description
					.buildDescriptionElement((Description) iterator.next()));
		} while (iterator.hasNext());

		itemDetailElement
				.addContent(new Element(Params.UNIT_OF_MEASURE_ELEMENT)
						.setText(itemDetailObject.getUnitOfMeasure()));

		iterator = itemDetailObject.getClassification().iterator();
		do {
			itemDetailElement.addContent(Classification
					.buildClassificationElement((Classification) iterator
							.next()));
		} while (iterator.hasNext());

		if (itemDetailObject.getManufacturerPartID() != null) {
			itemDetailElement.addContent(new Element(
					Params.MANUFACTURER_PART_ID_ELEMENT)
					.setText(itemDetailObject.getManufacturerPartID()));
		}

		if (itemDetailObject.getManufacturerName() != null) {
			itemDetailElement.addContent(ManufacturerName
					.buildManufacturerNameElement(itemDetailObject
							.getManufacturerName()));
		}

		if (itemDetailObject.getUrl() != null) {
			itemDetailElement.addContent(URL.buildURlElement(itemDetailObject
					.getUrl()));
		}

		if (itemDetailObject.getLeadTime() != null) {
			itemDetailElement.addContent(new Element(Params.LEAD_TIME_ELEMENT)
					.setText(String.valueOf(itemDetailObject.getLeadTime())));
		}

		for (iterator = itemDetailObject.getExtrinsic().iterator(); iterator
				.hasNext();) {
			itemDetailElement.addContent(Extrinsic
					.buildExtrinsicElement((Extrinsic) iterator.next()));
		}
	}
}
