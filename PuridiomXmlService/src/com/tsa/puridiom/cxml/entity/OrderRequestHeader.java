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
 */
public class OrderRequestHeader implements Serializable
{
	private String orderID;

	private String orderDate;

	private String orderType;

	private String type;

	private String orderVersion;

	private Boolean isInternalVersion;

	private String agreementID;

	private String agreementPayloadID;

	private String requisitionID;

	private Boolean shipComplete;

	private Money total;

	private AddressShipBillTo shipTo;

	private AddressShipBillTo billTo;

	private Shipping shipping;

	private Tax tax;

	private PCard paymentPCard;

	// TODO Finish end
	// private List<PaymentTerm> paymentTerm;
	private List paymentTerm = new ArrayList();

	// end TODO
	// private List<Contact> contact;
	private List contact = new ArrayList();

	private Comments comments;

	// TODO Finish end
	private Followup followup;

	private DocumentReference documentReference;

	private SupplierOrderInfo supplierOrderInfo;

	// private List<Extrinsic> extrinsic;
	private List extrinsic = new ArrayList();

	/**
	 * @return the agreementID
	 */
	public String getAgreementID()
	{
		return agreementID;
	}

	/**
	 * @param agreementID
	 *            the agreementID to set
	 */
	public void setAgreementID(String agreementID)
	{
		this.agreementID = agreementID;
	}

	/**
	 * @return the agreementPayloadID
	 */
	public String getAgreementPayloadID()
	{
		return agreementPayloadID;
	}

	/**
	 * @param agreementPayloadID
	 *            the agreementPayloadID to set
	 */
	public void setAgreementPayloadID(String agreementPayloadID)
	{
		this.agreementPayloadID = agreementPayloadID;
	}

	/**
	 * @return the billTo
	 */
	public AddressShipBillTo getBillTo()
	{
		return billTo;
	}

	/**
	 * @param billTo
	 *            the billTo to set
	 */
	public void setBillTo(AddressShipBillTo billTo)
	{
		this.billTo = billTo;
	}

	/**
	 * @return the comments
	 */
	public Comments getComments()
	{
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(Comments comments)
	{
		this.comments = comments;
	}

	/**
	 * @return the contact
	 */
	// public List<Contact> getContact() {
	public List getContact()
	{
		return contact;
	}

	/**
	 * @param contact
	 * @return
	 */
	public List addContact(Contact contact)
	{
		this.contact.add(contact);

		return this.contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	// public void setContact(List<Contact> contact) {
	public void setContact(List contact)
	{
		this.contact = contact;
	}

	/**
	 * @return the documentReference
	 */
	public DocumentReference getDocumentReference()
	{
		return documentReference;
	}

	/**
	 * @param documentReference
	 *            the documentReference to set
	 */
	public void setDocumentReference(DocumentReference documentReference)
	{
		this.documentReference = documentReference;
	}

	/**
	 * @return the extrinsic
	 */
	// public List<Extrinsic> getExtrinsic() {
	public List getExtrinsic()
	{
		return extrinsic;
	}

	/**
	 * @param extrinsic
	 * @return
	 */
	public List addExtrinsic(Extrinsic extrinsic)
	{
		this.extrinsic.add(extrinsic);

		return this.extrinsic;
	}

	/**
	 * @param extrinsic
	 *            the extrinsic to set
	 */
	// public void setExtrinsic(List<Extrinsic> extrinsic) {
	public void setExtrinsic(List extrinsic)
	{
		this.extrinsic = extrinsic;
	}

	/**
	 * @return the followup
	 */
	public Followup getFollowup()
	{
		return followup;
	}

	/**
	 * @param followup
	 *            the followup to set
	 */
	public void setFollowup(Followup followup)
	{
		this.followup = followup;
	}

	/**
	 * @return the isInternalVersion
	 */
	public Boolean getIsInternalVersion()
	{
		return isInternalVersion;
	}

	/**
	 * @param isInternalVersion
	 *            the isInternalVersion to set
	 */
	public void setIsInternalVersion(Boolean isInternalVersion)
	{
		this.isInternalVersion = isInternalVersion;
	}

	/**
	 * @return the orderDate
	 */
	public String getOrderDate()
	{
		return orderDate;
	}

	/**
	 * @param orderDate
	 *            the orderDate to set
	 */
	public void setOrderDate(String orderDate)
	{
		this.orderDate = orderDate;
	}

	/**
	 * @return the orderID
	 */
	public String getOrderID()
	{
		return orderID;
	}

	/**
	 * @param orderID
	 *            the orderID to set
	 */
	public void setOrderID(String orderID)
	{
		this.orderID = orderID;
	}

	/**
	 * @return the orderType
	 */
	public String getOrderType()
	{
		return orderType;
	}

	/**
	 * @param orderType
	 *            the orderType to set
	 */
	public void setOrderType(String orderType)
	{
		this.orderType = orderType;
	}

	/**
	 * @return the orderVersion
	 */
	public String getOrderVersion()
	{
		return orderVersion;
	}

	/**
	 * @param orderVersion
	 *            the orderVersion to set
	 */
	public void setOrderVersion(String orderVersion)
	{
		this.orderVersion = orderVersion;
	}

	/**
	 * @return the paymentPCard
	 */
	public PCard getPaymentPCard()
	{
		return paymentPCard;
	}

	/**
	 * @param paymentPCard
	 *            the paymentPCard to set
	 */
	public void setPaymentPCard(PCard paymentPCard)
	{
		this.paymentPCard = paymentPCard;
	}

	/**
	 * @return the paymentTerm
	 */
	// public List<PaymentTerm> getPaymentTerm() {
	public List getPaymentTerm()
	{
		return paymentTerm;
	}

	/**
	 * @param paymentTerm
	 * @return
	 */
	public List addPaymentTerm(PaymentTerm paymentTerm)
	{
		this.paymentTerm.add(paymentTerm);

		return this.paymentTerm;
	}

	/**
	 * @param paymentTerm
	 *            the paymentTerm to set
	 */
	// public void setPaymentTerm(List<PaymentTerm> paymentTerm) {
	public void setPaymentTerm(List paymentTerm)
	{
		this.paymentTerm = paymentTerm;
	}

	/**
	 * @return the requisitionID
	 */
	public String getRequisitionID()
	{
		return requisitionID;
	}

	/**
	 * @param requisitionID
	 *            the requisitionID to set
	 */
	public void setRequisitionID(String requisitionID)
	{
		this.requisitionID = requisitionID;
	}

	/**
	 * @return the shipComplete
	 */
	public Boolean getShipComplete()
	{
		return shipComplete;
	}

	/**
	 * @param shipComplete
	 *            the shipComplete to set
	 */
	public void setShipComplete(Boolean shipComplete)
	{
		this.shipComplete = shipComplete;
	}

	/**
	 * @return the shipping
	 */
	public Shipping getShipping()
	{
		return shipping;
	}

	/**
	 * @param shipping
	 *            the shipping to set
	 */
	public void setShipping(Shipping shipping)
	{
		this.shipping = shipping;
	}

	/**
	 * @return the shipTo
	 */
	public AddressShipBillTo getShipTo()
	{
		return shipTo;
	}

	/**
	 * @param shipTo
	 *            the shipTo to set
	 */
	public void setShipTo(AddressShipBillTo shipTo)
	{
		this.shipTo = shipTo;
	}

	/**
	 * @return the supplierOrderInfo
	 */
	public SupplierOrderInfo getSupplierOrderInfo()
	{
		return supplierOrderInfo;
	}

	/**
	 * @param supplierOrderInfo
	 *            the supplierOrderInfo to set
	 */
	public void setSupplierOrderInfo(SupplierOrderInfo supplierOrderInfo)
	{
		this.supplierOrderInfo = supplierOrderInfo;
	}

	/**
	 * @return the tax
	 */
	public Tax getTax()
	{
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(Tax tax)
	{
		this.tax = tax;
	}

	/**
	 * @return the total
	 */
	public Money getTotal()
	{
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(Money total)
	{
		this.total = total;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	// end TODO

	/**
	 * <!ELEMENT OrderRequestHeader (Total, ShipTo?, BillTo, Shipping?, Tax?,
	 * Payment?, PaymentTerm*, Contact*, Comments?, Followup?,
	 * DocumentReference?, SupplierOrderInfo?, Extrinsic*)> <!ATTLIST
	 * OrderRequestHeader orderID %string; #REQUIRED orderDate %datetime.tz;
	 * #REQUIRED orderType (release| regular) "regular" type (new | update |
	 * delete) "new" orderVersion %number; #IMPLIED isInternalVersion (yes)
	 * #IMPLIED agreementID %string; #IMPLIED agreementPayloadID %string;
	 * #IMPLIED requisitionID %string; #IMPLIED shipComplete (yes) #IMPLIED >
	 */
	public static Element buildOrderRequestHeaderElement(OrderRequestHeader orderRequestHeaderObject)
	{
		Element orderRequestHeaderElement = new Element("OrderRequestHeader");

		orderRequestHeaderObject.setAttributes(orderRequestHeaderElement, orderRequestHeaderObject);

		orderRequestHeaderObject.setContent(orderRequestHeaderElement, orderRequestHeaderObject);

		return orderRequestHeaderElement;
	}

	private void setAttributes(Element orderRequestHeaderElement, OrderRequestHeader orderRequestHeaderObject)
	{
		/*
		 * orderID %string; #REQUIRED orderDate %datetime.tz; #REQUIRED
		 * orderType (release| regular) "regular" type (new | update | delete)
		 * "new" orderVersion %number; #IMPLIED isInternalVersion (yes) #IMPLIED
		 * agreementID %string; #IMPLIED agreementPayloadID %string; #IMPLIED
		 * requisitionID %string; #IMPLIED shipComplete (yes) #IMPLIED
		 */
		// List<Attribute> attributes = new ArrayList<Attribute>();
		List attributes = new ArrayList();

		attributes.add(new Attribute("orderID", String.valueOf(orderRequestHeaderObject.getOrderID())));

		attributes.add(new Attribute("orderDate", String.valueOf(orderRequestHeaderObject.getOrderDate())));

		if (orderRequestHeaderObject.getOrderType() != null)
		{
			attributes.add(new Attribute("orderType", orderRequestHeaderObject.getOrderType()));
		}

		if (orderRequestHeaderObject.getType() != null)
		{
			attributes.add(new Attribute("type", orderRequestHeaderObject.getType()));
		}

		if (orderRequestHeaderObject.getOrderVersion() != null)
		{
			attributes.add(new Attribute("orderVersion", orderRequestHeaderObject.getOrderVersion()));
		}

		if (orderRequestHeaderObject.getIsInternalVersion() != null)
		{
			attributes.add(new Attribute("isInternalVersion", (orderRequestHeaderObject.getIsInternalVersion().booleanValue()) ? "yes" : ""));
		}

		if (orderRequestHeaderObject.getAgreementID() != null)
		{
			attributes.add(new Attribute("agreementID", orderRequestHeaderObject.getAgreementID()));
		}

		if (orderRequestHeaderObject.getAgreementPayloadID() != null)
		{
			attributes.add(new Attribute("agreementPayloadID", orderRequestHeaderObject.getAgreementPayloadID()));
		}

		if (orderRequestHeaderObject.getRequisitionID() != null)
		{
			attributes.add(new Attribute("requisitionID", orderRequestHeaderObject.getRequisitionID()));
		}

		if (orderRequestHeaderObject.getShipComplete() != null)
		{
			attributes.add(new Attribute("shipComplete", (orderRequestHeaderObject.getShipComplete().booleanValue()) ? "yes" : ""));
		}

		orderRequestHeaderElement.setAttributes(attributes);
	}

	private void setContent(Element orderRequestHeaderElement, OrderRequestHeader orderRequestHeaderObject)
	{
		/*
		 * (Total, ShipTo?, BillTo, Shipping?, Tax?, Payment?, PaymentTerm*,
		 * Contact*, Comments?, Followup?, DocumentReference?,
		 * SupplierOrderInfo?, Extrinsic*)
		 */
		Element totalElement = new Element(Params.TOTAL_ELEMENT);

		totalElement.addContent(Money.buildMoneyElement(orderRequestHeaderObject.getTotal()));

		orderRequestHeaderElement.addContent(totalElement);

		if (orderRequestHeaderObject.getShipTo() != null)
		{
			Element shipToElement = new Element(Params.SHIP_TO_ELEMENT);

			shipToElement.addContent(AddressShipBillTo.buildAddressElement(orderRequestHeaderObject.getShipTo()));

			orderRequestHeaderElement.addContent(shipToElement);
		}

		Element billToElement = new Element(Params.BILL_TO_ELEMENT);

		billToElement.addContent(AddressShipBillTo.buildAddressElement(orderRequestHeaderObject.getBillTo()));

		orderRequestHeaderElement.addContent(billToElement);

		if (orderRequestHeaderObject.getShipping() != null)
		{
			orderRequestHeaderElement.addContent(Shipping.buildShippingElement(orderRequestHeaderObject.getShipping()));
		}

		if (orderRequestHeaderObject.getTax() != null)
		{
			orderRequestHeaderElement.addContent(Tax.buildTaxElement(orderRequestHeaderObject.getTax()));
		}

		if (orderRequestHeaderObject.getPaymentPCard() != null)
		{
			Element paymentElement = new Element(Params.PAYMENT_ELEMENT);

			paymentElement.addContent(PCard.buildPCardElement(orderRequestHeaderObject.getPaymentPCard()));

			orderRequestHeaderElement.addContent(paymentElement);
		}

		// TODO PaymentTerm*

		for (Iterator iter = orderRequestHeaderObject.getContact().iterator(); iter.hasNext();)
		{
			orderRequestHeaderElement.addContent(Contact.buildContactElement((Contact) iter.next()));
		}

		if (orderRequestHeaderObject.getComments() != null)
		{
			orderRequestHeaderElement.addContent(Comments.buildCommentsElement(orderRequestHeaderObject.getComments()));
		}

		// TODO Followup?, DocumentReference?, SupplierOrderInfo?

		for (Iterator iter = orderRequestHeaderObject.getExtrinsic().iterator(); iter.hasNext();)
		{
			orderRequestHeaderElement.addContent(Extrinsic.buildExtrinsicElement((Extrinsic) iter.next()));
		}

	}
}
