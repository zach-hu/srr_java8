/**
 *
 */
package com.tsa.puridiom.cxml.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author JOHNNY
 *
 */
public class ItemOut implements Serializable {

	private String quantity;

	private String lineNumber;

	private String requisitionID;

	private String agreementItemNumber;

	private Date requestedDeliveryDate;

	private Boolean isAdHoc;

	private ItemID itemID;

	private Path path;

	private ItemDetail itemDetail;

	// TODO (SupplierID | SupplierList)?
	private AddressShipBillTo shipTo;

	private Shipping shipping;

	private Tax tax;

	// TODO SpendDetail
	// private List<Distribution> distribution;
	private List distribution = new ArrayList();

	// private List<Contact> contact;
	private List contact = new ArrayList();

	private Comments comments;

	/**
	 * @return the agreementItemNumber
	 */
	public String getAgreementItemNumber() {
		return agreementItemNumber;
	}

	/**
	 * @param agreementItemNumber
	 *            the agreementItemNumber to set
	 */
	public void setAgreementItemNumber(String agreementItemNumber) {
		this.agreementItemNumber = agreementItemNumber;
	}

	/**
	 * @return the comments
	 */
	public Comments getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(Comments comments) {
		this.comments = comments;
	}

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
	 * @return the distribution
	 */
	// public List<Distribution> getDistribution() {
	public List getDistribution() {
		return distribution;
	}

	/**
	 *
	 * @param distribution
	 * @return
	 */
	public List addDistribution(Distribution distribution) {
		this.distribution.add(distribution);

		return this.distribution;
	}

	/**
	 * @param distribution
	 *            the distribution to set
	 */
	// public void setDistribution(List<Distribution> distribution) {
	public void setDistribution(List distribution) {
		this.distribution = distribution;
	}

	/**
	 * @return the isAdHoc
	 */
	public Boolean getIsAdHoc() {
		return isAdHoc;
	}

	/**
	 * @param isAdHoc
	 *            the isAdHoc to set
	 */
	public void setIsAdHoc(Boolean isAdHoc) {
		this.isAdHoc = isAdHoc;
	}

	/**
	 * @return the itemDetail
	 */
	public ItemDetail getItemDetail() {
		return itemDetail;
	}

	/**
	 * @param itemDetail
	 *            the itemDetail to set
	 */
	public void setItemDetail(ItemDetail itemDetail) {
		this.itemDetail = itemDetail;
	}

	/**
	 * @return the itemID
	 */
	public ItemID getItemID() {
		return itemID;
	}

	/**
	 * @param itemID
	 *            the itemID to set
	 */
	public void setItemID(ItemID itemID) {
		this.itemID = itemID;
	}

	/**
	 * @return the lineNumber
	 */
	public String getLineNumber() {
		return lineNumber;
	}

	/**
	 * @param lineNumber
	 *            the lineNumber to set
	 */
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(Path path) {
		this.path = path;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the requestedDeliveryDate
	 */
	public Date getRequestedDeliveryDate() {
		return requestedDeliveryDate;
	}

	/**
	 * @param requestedDeliveryDate
	 *            the requestedDeliveryDate to set
	 */
	public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
		this.requestedDeliveryDate = requestedDeliveryDate;
	}

	/**
	 * @return the requisitionID
	 */
	public String getRequisitionID() {
		return requisitionID;
	}

	/**
	 * @param requisitionID
	 *            the requisitionID to set
	 */
	public void setRequisitionID(String requisitionID) {
		this.requisitionID = requisitionID;
	}

	/**
	 * @return the shipping
	 */
	public Shipping getShipping() {
		return shipping;
	}

	/**
	 * @param shipping
	 *            the shipping to set
	 */
	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	/**
	 * @return the shipTo
	 */
	public AddressShipBillTo getShipTo() {
		return shipTo;
	}

	/**
	 * @param shipTo
	 *            the shipTo to set
	 */
	public void setShipTo(AddressShipBillTo shipTo) {
		this.shipTo = shipTo;
	}

	/**
	 * @return the tax
	 */
	public Tax getTax() {
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(Tax tax) {
		this.tax = tax;
	}

	/**
	 * <!ELEMENT ItemOut (ItemID, Path?, ItemDetail?, (SupplierID |
	 * SupplierList)?, ShipTo?, Shipping?, Tax?, SpendDetail?, Distribution*,
	 * Contact*, Comments?)> <!ATTLIST ItemOut quantity %r8; #REQUIRED
	 * lineNumber %uint; #IMPLIED requisitionID %string; #IMPLIED
	 * agreementItemNumber %string; #IMPLIED requestedDeliveryDate %date;
	 * #IMPLIED isAdHoc (yes) #IMPLIED >
	 *
	 * @param itemOutObject
	 * @return
	 */
	public static Element buildItemOutElement(ItemOut itemOutObject) {
		Element itemOutElement = new Element("ItemOut");

		itemOutObject.setAttributes(itemOutElement, itemOutObject);

		itemOutObject.setContent(itemOutElement, itemOutObject);

		return itemOutElement;
	}

	private void setAttributes(Element itemOutElement, ItemOut itemOutObject) {
		/*
		 * quantity %r8; #REQUIRED lineNumber %uint; #IMPLIED requisitionID
		 * %string; #IMPLIED agreementItemNumber %string; #IMPLIED
		 * requestedDeliveryDate %date; #IMPLIED isAdHoc (yes) #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("quantity", itemOutObject.getQuantity()));

		if (itemOutObject.getLineNumber() != null) {
			attributes.add(new Attribute("lineNumber", itemOutObject
					.getLineNumber()));
		}

		if (itemOutObject.getRequisitionID() != null) {
			attributes.add(new Attribute("requisitionID", itemOutObject
					.getRequisitionID()));
		}

		if (itemOutObject.getAgreementItemNumber() != null) {
			attributes.add(new Attribute("agreementItemNumber", itemOutObject
					.getAgreementItemNumber()));
		}

		if (itemOutObject.getRequestedDeliveryDate() != null) {
			attributes.add(new Attribute("requestedDeliveryDate", String
					.valueOf(itemOutObject.getRequestedDeliveryDate())));
		}

		if (itemOutObject.getIsAdHoc() != null) {
			attributes.add(new Attribute("isAdHoc", String
					.valueOf(itemOutObject.getIsAdHoc())));
		}

		itemOutElement.setAttributes(attributes);
	}

	private void setContent(Element itemOutElement, ItemOut itemOutObject) {
		/*
		 * (ItemID, Path?, ItemDetail?, (SupplierID | SupplierList)?, ShipTo?,
		 * Shipping?, Tax?, SpendDetail?, Distribution*, Contact*, Comments?)
		 */
		itemOutElement.addContent(ItemID.buildItemIDElement(itemOutObject
				.getItemID()));

		if (itemOutObject.getPath() != null) {
			itemOutElement.addContent(Path.buildPathElement(itemOutObject
					.getPath()));
		}

		if (itemOutObject.getItemDetail() != null) {
			itemOutElement.addContent(ItemDetail
					.buildItemDetailElement(itemOutObject.getItemDetail()));
		}

		// TODO (SupplierID | SupplierList)?

		if (itemOutObject.getShipTo() != null) {
			Element shipToElement = new Element(Params.SHIP_TO_ELEMENT);

			shipToElement.addContent(AddressShipBillTo
					.buildAddressElement(itemOutObject.getShipTo()));

			itemOutElement.addContent(shipToElement);
		}

		if (itemOutObject.getShipping() != null) {
			itemOutElement.addContent(Shipping
					.buildShippingElement(itemOutObject.getShipping()));
		}

		if (itemOutObject.getTax() != null) {
			itemOutElement.addContent(Tax.buildTaxElement(itemOutObject
					.getTax()));
		}

		// TODO SpendDetail?

		Iterator iter;

		for (iter = itemOutObject.getDistribution().iterator(); iter.hasNext();) {
			itemOutElement.addContent(Distribution
					.buildDistributionElement((Distribution) iter.next()));
		}

		for (iter = itemOutObject.getContact().iterator(); iter.hasNext();) {
			itemOutElement.addContent(Contact
					.buildContactElement((Contact) iter.next()));
		}

		if (itemOutObject.getComments() != null) {
			itemOutElement.addContent(Comments
					.buildCommentsElement(itemOutObject.getComments()));
		}

	}

}
