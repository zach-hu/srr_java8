/**
 *
 */
package com.tsa.puridiom.cxml.tasks;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.cxml.entity.Accounting;
import com.tsa.puridiom.cxml.entity.AddressShipBillTo;
import com.tsa.puridiom.cxml.entity.CXML;
import com.tsa.puridiom.cxml.entity.Classification;
import com.tsa.puridiom.cxml.entity.Contact;
import com.tsa.puridiom.cxml.entity.Country;
import com.tsa.puridiom.cxml.entity.CountryCode;
import com.tsa.puridiom.cxml.entity.Credential;
import com.tsa.puridiom.cxml.entity.Description;
import com.tsa.puridiom.cxml.entity.Distribution;
import com.tsa.puridiom.cxml.entity.Email;
import com.tsa.puridiom.cxml.entity.FromTo;
import com.tsa.puridiom.cxml.entity.Identity;
import com.tsa.puridiom.cxml.entity.ItemDetail;
import com.tsa.puridiom.cxml.entity.ItemID;
import com.tsa.puridiom.cxml.entity.ItemOut;
import com.tsa.puridiom.cxml.entity.Money;
import com.tsa.puridiom.cxml.entity.Name;
import com.tsa.puridiom.cxml.entity.OrderRequest;
import com.tsa.puridiom.cxml.entity.OrderRequestHeader;
import com.tsa.puridiom.cxml.entity.Params;
import com.tsa.puridiom.cxml.entity.Phone;
import com.tsa.puridiom.cxml.entity.PostalAddress;
import com.tsa.puridiom.cxml.entity.Segment;
import com.tsa.puridiom.cxml.entity.Sender;
import com.tsa.puridiom.cxml.entity.Shipping;
import com.tsa.puridiom.cxml.entity.Tax;
import com.tsa.puridiom.cxml.entity.TelephoneNumber;
import com.tsa.puridiom.cxml.entity.CXML.Header;
import com.tsa.puridiom.cxml.entity.CXML.Request;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.Punchout;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.entity.ShipTo;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.Dictionary;
import com.tsagate.properties.DictionaryManager;
import com.tsa.puridiom.punchout.tasks.*;

/**
 * @author Johnny Zapana
 *
 */
public class CXMLOrderSetValues extends Task {
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		CXML cxmlDocument = new CXML();
		Header header;
		Request request;
		OrderRequest orderRequest;

		try {
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			SendQueue sendQueue = (SendQueue) incomingRequest
					.get("SendQueue_object");

			Object obj = request.getAttribute("punchout");
			Punchout punchout = (Punchout) obj;

			Object[] objs = { poHeader.getPoNumber(),
					poHeader.getReleaseNumber().toString(),
					sendQueue.getDateadded(), sendQueue.getTimeadded() };

			cxmlDocument.setPayloadID(MessageFormat.format(
					Params.CXML_REQUEST_PAYLOAD_ID_FMT_PATTERN, objs));

			cxmlDocument.setComment(MessageFormat.format(
					Params.CXML_PO_INFO_COMMENT_FMT_PATTERN, objs));

			cxmlDocument.setTimestamp(Calendar.getInstance().getTime());

			header = cxmlDocument.new Header();

			header.setFrom(this.buildFromToObject(Params.FROM_ELEMENT_NAME,
					punchout));

			header.setTo(this.buildFromToObject(Params.TO_ELEMENT_NAME,
					punchout));

			header.setSender(this.buildSenderObject(punchout));

			request = cxmlDocument.new Request();

			orderRequest = new OrderRequest();

			orderRequest.setOrderRequestHeader(this
					.buildOrderRequestHeaderObject(poHeader,
							(String) incomingRequest.get("organizationId")));

			orderRequest.setItemsOut(this.buildItemOutCollection(poHeader,
					punchout));

			request.setOrderRequest(orderRequest);

			cxmlDocument.setHeader(header);
			cxmlDocument.setRequest(request);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return cxmlDocument;
	}

	private FromTo buildFromToObject(String elementName, Punchout punchout) {
		FromTo fromTo = new FromTo();
		elementName = elementName.toLowerCase();

		if (elementName.equals("from_element_name")){
			fromTo.addCredential(new Credential(punchout.getFromDomain(),
					new Identity(punchout.getFromIdentity()), null));
		}
		else {
			fromTo.addCredential(new Credential(punchout.getToDomain(),
				new Identity(punchout.getToIdentity()), null));
		}

		return fromTo;
	}

	private Sender buildSenderObject(Punchout punchout) {
		Sender sender = new Sender();

		sender.addCredential(new Credential(punchout.getSenderDomain(),
						new Identity(punchout.getSenderIdentity()),
						punchout.getSharedSecret()));

		sender.setUserAgent(punchout.getSenderAgent);

		return sender;
	}

	// <!ATTLIST OrderRequestHeader
	// orderID %string; #REQUIRED
	// orderDate %datetime.tz; #REQUIRED
	// orderType (release| regular) "regular"
	// type (new | update | delete) "new"
	// orderVersion %number; #IMPLIED
	// isInternalVersion (yes) #IMPLIED
	// agreementID %string; #IMPLIED
	// agreementPayloadID %string; #IMPLIED
	// requisitionID %string; #IMPLIED
	// shipComplete (yes) #IMPLIED
	// >
	// (Total, ShipTo?, BillTo, Shipping?, Tax?,
	// Payment?, PaymentTerm*, Contact*, Comments?, Followup?,
	// DocumentReference?, SupplierOrderInfo?, Extrinsic*)>
	private OrderRequestHeader buildOrderRequestHeaderObject(PoHeader poHeader,
			String oid) throws Exception {

		OrderRequestHeader orderRequestHeader = new OrderRequestHeader();
		Shipping shipping;
		Description description;
		Contact contact;
		UserProfile buyer;
		UserProfile requisitioner;

		orderRequestHeader.setOrderID(poHeader.getPoNumber() + "/"
				+ String.valueOf(poHeader.getReleaseNumber()));

		orderRequestHeader.setOrderDate(poHeader.getPoDate());

		// TODO
		// orderType (release| regular) "regular"

		orderRequestHeader.setType("new");

		// TODO
		// orderVersion %number; #IMPLIED
		// isInternalVersion (yes) #IMPLIED
		// agreementID %string; #IMPLIED
		// agreementPayloadID %string; #IMPLIED
		// requisitionID %string; #IMPLIED
		// shipComplete (yes) #IMPLIED

		orderRequestHeader.setTotal(new Money(poHeader.getCurrencyCode(),
				Double.valueOf(poHeader.getTotal().toString())));

		if (poHeader.getShippingCharges() != null) {
			shipping = new Shipping();
			description = new Description();

			description.setLang("en");
			description.putContent(Params.TEXT_NAME, poHeader.getShipViaCode());

			shipping.setTrackingDomain(poHeader.getShipViaCode());
			shipping.setMoney(new Money(poHeader.getCurrencyCode(), Double
					.valueOf(poHeader.getShippingCharges().toString())));
			shipping.setDescription(description);

			orderRequestHeader.setShipping(shipping);
		}

		if (poHeader.getTaxAmount() != null) {
			description = new Description();

			description.setLang("en");

			if (poHeader.getTaxCode() != null) {

				description.putContent(Params.TEXT_NAME, poHeader.getTaxCode());
			}

			orderRequestHeader.setTax(new Tax(new Money(poHeader
					.getCurrencyCode(), Double.valueOf(poHeader.getTaxAmount()
					.toString())), description));
		}

		contact = new Contact();

		buyer = UserManager.getInstance().getUser(oid, poHeader.getBuyerCode());

		contact.setRole("purchasingAgent");
		contact.addEmail(new Email(buyer.getMailId()));
		contact.setName(new Name("en", buyer.getFirstName() + " "
				+ buyer.getLastName()));

		contact.addPhone(new Phone(this.buildTelephoneNumber(buyer
				.getPhoneNumber())));

		orderRequestHeader.addContact(contact);

		contact = new Contact();

		requisitioner = UserManager.getInstance().getUser(oid,
				poHeader.getRequisitionerCode());

		contact.addEmail(new Email(requisitioner.getMailId()));
		contact.setRole("endUser");
		contact.setName(new Name("en", requisitioner.getFirstName() + " "
				+ requisitioner.getLastName()));

		contact.addPhone(new Phone(this.buildTelephoneNumber(requisitioner
				.getPhoneNumber())));

		orderRequestHeader.addContact(contact);

		if (poHeader.getShipToAddress() != null) {
			orderRequestHeader.setShipTo(this.buildAddressShipBillToObject(
					poHeader.getShipToAddress(), requisitioner,
					Params.SHIP_TO_ELEMENT, null));
		}

		orderRequestHeader.setBillTo(this.buildAddressShipBillToObject(poHeader
				.getBillToAddress(), null, Params.BILL_TO_ELEMENT, null));

		return orderRequestHeader;
	}

	private List buildItemOutCollection(PoHeader poHeader, Punchout punchout)
			throws Exception {
		List itemsOut = new ArrayList();

		PoLine poLine;
		String postLineShipTo = punchout.getShipTo();
		String catalog = punchout.getAuxiliary();
		int numberLine = 0;

		for (Iterator iter = poHeader.getPoLineList().iterator(); iter
				.hasNext();) {
			poLine = (PoLine) iter.next();

			if (postLineShipTo.equalsIgnoreCase("y"))
					&& (poLine.getShipToList().size() > 0)) {
				for (Iterator iterator = poLine.getShipToList().iterator(); iterator
						.hasNext();) {
					itemsOut.add(this.buildItemOut(poHeader, poLine,
							(ShipTo) iterator.next(),
							new Integer(++numberLine), catalog, postLineShipTo,
							punchout));
				}
			} else {
				itemsOut.add(this.buildItemOut(poHeader, poLine, null,
						new Integer(++numberLine), catalog, postLineShipTo,
						punchout));
			}
		}

		return itemsOut;
	}

	private ItemOut buildItemOut(PoHeader poHeader, PoLine poLine,
			ShipTo shipTo, Integer lineNumber, String catalog,
			String postLineShipTo, Punchout punchout) throws Exception {
		ItemOut itemOut = new ItemOut();
		ItemDetail itemDetail;
		Description description;
		Shipping shipping;

		if (shipTo != null) {
			itemOut.setQuantity(shipTo.getQuantity().toString());
		} else {
			itemOut.setQuantity(poLine.getQuantity().toString());
		}

		itemOut.setLineNumber(lineNumber.toString());

		if (poLine.getCatalogId().equalsIgnoreCase(catalog)) {
			itemOut.setItemID(new ItemID(poLine.getItemNumber(), poLine
					.getUdf5Code()));
		} else {
			itemOut.setItemID(new ItemID(poLine.getItemNumber()));
		}

		itemDetail = new ItemDetail();

		itemDetail.setUnitPrice(new Money(poHeader.getCurrencyCode(), Double
				.valueOf(poLine.getUnitPrice().toString())));

		description = new Description();

		description.setLang("en");
		description.putContent(Params.TEXT_NAME, poLine.getDescription());

		itemDetail.addDescription(description);

		itemDetail.setUnitOfMeasure(poLine.getUmCode());

		itemDetail.addClassification(new Classification("UNSPSC", poLine
				.getCommodity()));

		itemOut.setItemDetail(itemDetail);

		if ((shipTo != null) && (shipTo.getShipToAddress() != null)) {
			String deliverTo = shipTo.getShipToAddress().getAddressLine1();

			if (!HiltonUtility.isEmpty(shipTo.getAttention())) {
				deliverTo += "/" + shipTo.getAttention();
			}

			itemOut.setShipTo(this.buildAddressShipBillToObject(shipTo
					.getShipToAddress(), null, Params.SHIP_TO_ELEMENT,
					deliverTo));
		} else if (postLineShipTo.equalsIgnoreCase("true")) {

			itemOut.setShipTo(this.buildAddressShipBillToObject(poHeader
					.getShipToAddress(), null, Params.SHIP_TO_ELEMENT, null));
		}

		if (shipTo == null) {
			if ((poLine.getShippingCharges() != null)
					&& (poLine.getShippingCharges().intValue() != 0)) {
				shipping = new Shipping();

				shipping.setMoney(new Money(poHeader.getCurrencyCode(), Double
						.valueOf(poLine.getShippingCharges().toString())));

				description = new Description();

				description.setLang("en");
				description.putContent(Params.TEXT_NAME, "Shipping Charges");

				shipping.setDescription(description);

				itemOut.setShipping(shipping);
			}

			if ((poLine.getTaxAmount() != null)
					&& (poLine.getTaxAmount().intValue() != 0)) {
				description = new Description();
				description.setLang("en");
				if (poLine.getTaxCode() != null) {
					// taxDescription = new Description("en", poLine
					// .getTaxCodeInfo().getDescription());
					description.putContent(Params.TEXT_NAME, poLine
							.getTaxCode());
				} else {
					description.putContent(Params.TEXT_NAME, "Tax Charges");
				}

				itemOut.setTax(new Tax(new Money(poHeader.getCurrencyCode(),
						Double.valueOf(poLine.getTaxAmount().toString())),
						description));
			}
		}

		Account account = new Account();
		boolean hasAccounts = false;

		if (poLine.getAccountList() != null
				&& poLine.getAccountList().size() > 0) {
			account = (Account) poLine.getAccountList().get(0);
			hasAccounts = true;
		} else if (poHeader.getAccountList() != null
				&& poHeader.getAccountList().size() > 0) {
			account = (Account) poHeader.getAccountList().get(0);
			hasAccounts = true;
		}
		// else {
		// // throw new Exception("POH/POL missing account info: " +
		// // poHeader.getIcPoHeader());
		// }

		if (hasAccounts) {
			Distribution distribution = new Distribution();

			distribution.setAccounting(new Accounting("DistributionCharge",
					this.buildDistributionSegment(account, punchout), null));

			distribution.setCharge(new Money(poHeader.getCurrencyCode(), null,
					null, Double.valueOf(poLine.getUnitPrice().multiply(
							poLine.getQuantity()).toString())));

			itemOut.addDistribution(distribution);
		}

		return itemOut;
	}

	// <!ELEMENT Address (Name, PostalAddress?, Email?, Phone?, Fax?, URL?)>
	// <!ATTLIST Address
	// isoCountryCode %isoCountryCode; #IMPLIED
	// addressID %string; #IMPLIED
	// >
	private AddressShipBillTo buildAddressShipBillToObject(Address shipBillTo,
			UserProfile requisitioner, String addressKey, String deliverTo) {
		AddressShipBillTo addressShipBillTo = new AddressShipBillTo();
		PostalAddress postalAddress = new PostalAddress();

		addressShipBillTo.setName(new Name("en", shipBillTo.getComp_id()
				.getAddressCode()));

		if (shipBillTo.getAddressLine1() != null) {
			if (deliverTo != null) {
				postalAddress.addDeliverTo(deliverTo);
			} else {
				postalAddress.addDeliverTo(shipBillTo.getAddressLine1());
			}
		}

		postalAddress.addStreet(shipBillTo.getAddressLine2());
		postalAddress.addStreet(shipBillTo.getAddressLine3());
		postalAddress.addStreet(shipBillTo.getAddressLine4());

		postalAddress.setCity(shipBillTo.getCity());

		postalAddress.setState(shipBillTo.getState());

		postalAddress.setPostalCode(shipBillTo.getPostalCode());

		postalAddress.setCountry(new Country(shipBillTo.getCountry(),
				shipBillTo.getCountry()));

		if (postalAddress.getCountry() != null
				&& postalAddress.getCity() != null) {
			addressShipBillTo.setPostalAddress(postalAddress);
		}

		if (requisitioner != null) {
			addressShipBillTo.setEmail(new Email(requisitioner.getMailId()));

			addressShipBillTo.setPhone(new Phone("work", this
					.buildTelephoneNumber(requisitioner.getPhoneNumber())));
		}

		return addressShipBillTo;
	}

	private List buildDistributionSegment(Account account, Punchout punchout)
			throws Exception {
		List segment = new ArrayList();
		Segment segmentElement;
		Method methodAccount;
		String type;
		String value;

		for (int i = 0; i < 15; i++) {
			type = punchout.getGeneralFld(i + 1);
			if (!HiltonUtility.isEmpty(type)) {
				value = "";
				segmentElement = new Segment();

				segmentElement.setType(type);
				try {
					methodAccount = account.getClass().getMethod(
							"getFld" + String.valueOf(i + 1), new Class[] {});

					value = (String) methodAccount.invoke(account,
							new Object[0]);
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}

				if (value == null) {
					value = "";
				}

				segmentElement.setId(value);

				segmentElement.setDescription(value);

				segment.add(segmentElement);
			}
		}

		return segment;
	}

	private TelephoneNumber buildTelephoneNumber(String phoneNumber) {
		TelephoneNumber telephoneNumber = new TelephoneNumber();

		if (phoneNumber.toLowerCase().indexOf("ext") > 0) {
			phoneNumber = phoneNumber.substring(0,
					phoneNumber.toLowerCase().indexOf("ext")).trim();
		}

		switch (phoneNumber.length()) {
		case 7:
			telephoneNumber
					.setCountryCode(new CountryCode("US", new Integer(1)));
			telephoneNumber.setAreaOrCityCode(new Integer(0));
			telephoneNumber.setNumber(phoneNumber);
			break;
		case 10:
			telephoneNumber
					.setCountryCode(new CountryCode("US", new Integer(1)));
			telephoneNumber.setAreaOrCityCode(new Integer(phoneNumber
					.substring(0, 3)));
			telephoneNumber.setNumber(phoneNumber.substring(3, 6) + "-"
					+ phoneNumber.substring(6));
			break;
		case 11:
			telephoneNumber.setCountryCode(new CountryCode("US", new Integer(
					phoneNumber.substring(0, 0))));
			telephoneNumber.setAreaOrCityCode(new Integer(phoneNumber
					.substring(1, 4)));
			telephoneNumber.setNumber(phoneNumber.substring(4, 7) + "-"
					+ phoneNumber.substring(7));
			break;
		case 13:
			telephoneNumber.setCountryCode(new CountryCode("US", new Integer(
					phoneNumber.substring(0, 0))));
			telephoneNumber.setAreaOrCityCode(new Integer(phoneNumber
					.substring(0, 3)));
			telephoneNumber.setNumber(phoneNumber.substring(6, 10) + "-"
					+ phoneNumber.substring(10));
			break;
		default:
			telephoneNumber
					.setCountryCode(new CountryCode("US", new Integer(1)));
			telephoneNumber.setAreaOrCityCode(new Integer(0));
			telephoneNumber.setNumber(phoneNumber);
			break;
		}

		return telephoneNumber;
	}
}
