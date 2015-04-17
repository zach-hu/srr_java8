/**
 *
 */
package com.tsa.puridiom.cxml.tasks;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.jdom.Document;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.cxml.entity.Accounting;
import com.tsa.puridiom.cxml.entity.AddressShipBillTo;
import com.tsa.puridiom.cxml.entity.CXML;
import com.tsa.puridiom.cxml.entity.Classification;
import com.tsa.puridiom.cxml.entity.Comments;
import com.tsa.puridiom.cxml.entity.Contact;
import com.tsa.puridiom.cxml.entity.Country;
import com.tsa.puridiom.cxml.entity.CountryCode;
import com.tsa.puridiom.cxml.entity.Credential;
import com.tsa.puridiom.cxml.entity.Description;
import com.tsa.puridiom.cxml.entity.Distribution;
import com.tsa.puridiom.cxml.entity.Email;
import com.tsa.puridiom.cxml.entity.Extrinsic;
import com.tsa.puridiom.cxml.entity.FromTo;
import com.tsa.puridiom.cxml.entity.Identity;
import com.tsa.puridiom.cxml.entity.ItemDetail;
import com.tsa.puridiom.cxml.entity.ItemID;
import com.tsa.puridiom.cxml.entity.ItemOut;
import com.tsa.puridiom.cxml.entity.Money;
import com.tsa.puridiom.cxml.entity.Name;
import com.tsa.puridiom.cxml.entity.OrderRequest;
import com.tsa.puridiom.cxml.entity.OrderRequestHeader;
import com.tsa.puridiom.cxml.entity.PCard;
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
import com.tsa.puridiom.cxml.exceptions.PunchCatalogException;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.Punchout;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.entity.ShipTo;
import com.tsa.puridiom.entity.TaxCode;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.taxcode.TaxCodeManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.Dictionary;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny Zapana
 */
public class CXMLRequestSetValues extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		CXML cXMLRequest = new CXML();
		Header header;
		Request request;
		OrderRequest orderRequest;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");

			SendQueue sendQueue = (SendQueue) incomingRequest.get("sendQueue");

			String organizationId = (String) incomingRequest.get("organizationId");

			String catalogFullAccess = HiltonUtility.ckNull((String) incomingRequest.get("CatalogExternalFullAccess"));

			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);

			String customer = "punchout" + File.separator + (String) incomingRequest.get("organizationId");

			String bundle = "punchout_" + poHeader.getVendorId().toLowerCase();

			if(catalogFullAccess.equalsIgnoreCase("Y"))
			{
				String bundleAux = bundle + "_full";
				Dictionary dictionaryFull = DictionaryManager.getInstance(bundleAux, customer);
				if (dictionaryFull != null)
				{
					bundle = bundleAux;
				}
			}

			Object obj = incomingRequest.get("punchout");
			Punchout punchout = (Punchout) obj;

			String cxmlHeaderEmpty = propertiesManager.getProperty("CXML","CXMLHEADEREMPTY","N");

			String payloadId = "";

			String comment = "";

			Date timestamp = null;

			Object[] objs = { poHeader.getPoNumber(), poHeader.getReleaseNumber().toString(), sendQueue.getDateadded(), sendQueue.getTimeadded() };

			if (punchout == null)
			{
				throw new PunchCatalogException("Not an acceptable method for this supplier(" + poHeader.getVendorName() + ")");
			}

			if (punchout.getGeneralInfo().equalsIgnoreCase("y"))
			{
				payloadId = MessageFormat.format(Params.CXML_REQUEST_PAYLOAD_ID_FMT_PATTERN, objs);
				comment = MessageFormat.format(Params.CXML_PO_INFO_COMMENT_FMT_PATTERN, objs);
				timestamp = Calendar.getInstance().getTime();
			}
			if(bundle.equalsIgnoreCase("punchout_mscindustria") && cxmlHeaderEmpty.equalsIgnoreCase("Y"))
			{
				cXMLRequest.setVersion("");

				cXMLRequest.setPayloadID("");

				cXMLRequest.setTimestamp(null);
			}
			else
			{
				cXMLRequest.setPayloadID(payloadId);

				cXMLRequest.setTimestamp(timestamp);
			}

			cXMLRequest.setComment(comment);

			header = cXMLRequest.new Header();

			header.setFrom(this.buildFromToObject(Params.FROM_ELEMENT_NAME, punchout));

			header.setTo(this.buildFromToObject(Params.TO_ELEMENT_NAME, punchout));

			// Configuration of SENDER
			String vendorId = poHeader.getVendorId().toUpperCase();
			String senderConfig = propertiesManager.getProperty("CXML", "SENDER-CONFIG-CUSTOM-FIELD-" + vendorId, "");
			if (senderConfig != null && senderConfig.length() > 0) {
				Class cls = poHeader.getClass() ;
            	Method mth = cls.getMethod("get" + senderConfig, null);
            	String customField = (String) mth.invoke(poHeader, null);
				String senderIdentity =  propertiesManager.getProperty("CXML", "SENDER-IDENTITY-" + vendorId + "-" + customField.toUpperCase(), "");
				String senderSharedSecret =  propertiesManager.getProperty("CXML", "SENDER-SHAREDSECRET-" + vendorId + "-" + customField.toUpperCase(), "");
				header.setSender(this.buildSenderObject(punchout, senderIdentity, senderSharedSecret));
			} else {
				header.setSender(this.buildSenderObject(punchout));
			}

			request = cXMLRequest.new Request();

			orderRequest = new OrderRequest();

			orderRequest.setOrderRequestHeader(this.buildOrderRequestHeaderObject(poHeader, punchout, organizationId));

			orderRequest.setItemsOut(this.buildItemOutCollection(poHeader, punchout, organizationId));

			request.setOrderRequest(orderRequest);

			cXMLRequest.setHeader(header);
			cXMLRequest.setRequest(request);

			Document requestDocument = CXML.buildCXMLDocument(cXMLRequest);

			incomingRequest.put("requestDocument", requestDocument);

			Log.debug(this, "CXMLRequest with PayloadID '" + cXMLRequest.getPayloadID() + "' was created successfully");

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			e.printStackTrace();

			Log.error(this, "Error creating CXMLRequest \r\n" + e.getMessage());

			this.setStatus(Status.FAILED);
			throw e;
		}

		return cXMLRequest;
	}

	private FromTo buildFromToObject(String elementName, Punchout punchout)
	{
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
						punchout.getSenderSecret()));

		sender.setUserAgent(punchout.getSenderAgent());

		return sender;
	}

	private Sender buildSenderObject(Punchout punchout, String senderIdentity, String senderSharedSecret)
	{
		Sender sender = new Sender();

		sender.addCredential(new Credential(punchout.getSenderDomain(),
						new Identity(senderIdentity),
						senderSharedSecret));

		sender.setUserAgent(punchout.getSenderAgent());

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
	private OrderRequestHeader buildOrderRequestHeaderObject(PoHeader poHeader, Punchout punchout, String oid) throws Exception
	{
		OrderRequestHeader orderRequestHeader = new OrderRequestHeader();
		Shipping shipping;
		Description description;
		Contact contact;
		UserProfile buyer;
		UserProfile requisitioner;
		TaxCode taxCode;
		PCard pCard;
		String releaseNumber = "";

		if (poHeader.getReleaseNumber() != null)
		{
			releaseNumber = poHeader.getReleaseNumber().toString();
		}

		orderRequestHeader.setOrderID(poHeader.getPoNumber() + "/" + releaseNumber);

		orderRequestHeader.setOrderDate(getFormatTimeStamp(poHeader.getPoDate().toString()));

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

		if(oid.equalsIgnoreCase("msg07p") && poHeader.getCurrencyCode().equalsIgnoreCase("US"))
		{
			orderRequestHeader.setTotal(new Money("USD", poHeader.getTotal()));
		}
		else
		{
			orderRequestHeader.setTotal(new Money(poHeader.getCurrencyCode(), poHeader.getTotal()));
		}

		if (poHeader.getShippingCharges() != null)
		{
			shipping = new Shipping();
			description = new Description();

			description.setLang("en");
			description.putContent(Params.TEXT_NAME, poHeader.getShipViaCode());

			shipping.setTrackingDomain(poHeader.getShipViaCode());
			shipping.setMoney(new Money(poHeader.getCurrencyCode(), poHeader.getShippingCharges()));
			shipping.setDescription(description);

			orderRequestHeader.setShipping(shipping);
		}

		if (poHeader.getTaxAmount() != null)
		{
			description = new Description();

			description.setLang("en");

			if (!HiltonUtility.isEmpty(poHeader.getTaxCode()))
			{
				taxCode = TaxCodeManager.getInstance().getTaxCode(oid, poHeader.getTaxCode());
				description.putContent(Params.TEXT_NAME, taxCode.getDescription());
			}

			orderRequestHeader.setTax(new Tax(new Money(poHeader.getCurrencyCode(), poHeader.getTaxAmount()), description));
		}

		if ((!HiltonUtility.isEmpty(poHeader.getPcardOrder())) && poHeader.getPcardOrder().equalsIgnoreCase("Y") && (!HiltonUtility.isEmpty(poHeader.getPcardNumber())))
		{
			pCard = new PCard();

			pCard.setNumber(new BigDecimal(poHeader.getPcardNumber()));
			pCard.setExpiration((getFormatTimeStamp(poHeader.getPcardExpires())));
			pCard.setName(poHeader.getPcardName());

			orderRequestHeader.setPaymentPCard(pCard);
		}

		contact = new Contact();

		buyer = UserManager.getInstance().getUser(oid, poHeader.getBuyerCode());

		if (buyer == null)
		{
			throw new PunchCatalogException("No buyer on PO!");
		}

		contact.setRole("purchasingAgent");
		contact.addEmail(new Email(buyer.getMailId()));
		contact.setName(new Name("en", buyer.getFirstName() + " " + buyer.getLastName()));

		contact.addPhone(new Phone(this.buildTelephoneNumber(buyer.getPhoneNumber())));

		orderRequestHeader.addContact(contact);

		contact = new Contact();

		requisitioner = UserManager.getInstance().getUser(oid, poHeader.getRequisitionerCode());

		if (requisitioner == null)
		{
			throw new PunchCatalogException("No requisitioner on PO!");
		}

		// contact.addEmail(new Email(requisitioner.getMailId()));
		if(HiltonUtility.isEmpty(punchout.getDefaultEmail()) && requisitioner != null)
		{
			if(!HiltonUtility.isEmpty(HiltonUtility.ckNull(requisitioner.getMailId())))
			{
				contact.addEmail(new Email(requisitioner.getMailId()));
			}

		}
		else
		{
			contact.addEmail(new Email(punchout.getDefaultEmail()));
		}
		contact.setRole("endUser");
		contact.setName(new Name("en", requisitioner.getFirstName() + " " + requisitioner.getLastName()));

		contact.addPhone(new Phone(this.buildTelephoneNumber(requisitioner.getPhoneNumber())));

		orderRequestHeader.addContact(contact);

		if (poHeader.getShipToAddress() != null)
		{
			AddressShipBillTo addressShipTo = new AddressShipBillTo();

			if(poHeader.getVendorId().equalsIgnoreCase("DELL"))
			{
				addressShipTo = this.buildAddressShipBillToObject(poHeader.getShipToAddress(), requisitioner.getMailId(), requisitioner.getPhoneNumber(), poHeader
						.getShipToContact(), poHeader.getContactPhone(), requisitioner.getLocale());
			}
			else
			{
				addressShipTo = this.buildAddressShipBillToObject(poHeader.getShipToAddress(), requisitioner.getMailId(), requisitioner.getPhoneNumber(), poHeader
						.getShipToContact());
			}

			if (oid.equalsIgnoreCase("hoy08p") && poHeader.getVendorId().equalsIgnoreCase("OFFICEMAX"))
			{
				addressShipTo.setName(new Name("en", poHeader.getShipToAddress().getAddrFld15()));
			}

			orderRequestHeader.setShipTo(addressShipTo);
		} else
		{
			throw new PunchCatalogException("PO does not have a ship to address!");
		}

		if (poHeader.getBillToAddress() != null)
		{
			orderRequestHeader.setBillTo(this.buildAddressShipBillToObject(poHeader.getBillToAddress(), null, null, poHeader.getBillToContact()));
		} else
		{
			throw new PunchCatalogException("PO does not have a bill to address!");
		}

		if (oid.equalsIgnoreCase("wpc08p") && poHeader.getVendorId().equals("127019"))
		{
			Comments comments = new Comments();

			comments.addContent("IMPORTANT: SHIP VIA {" + poHeader.getShipViaCode() + "}");

			orderRequestHeader.setComments(comments);
		}

		return orderRequestHeader;
	}

	private List buildItemOutCollection(PoHeader poHeader, Punchout punchout, String organizationId) throws Exception
	{
		List itemsOut = new ArrayList();

		PoLine poLine;
		String postLineShipTo = punchout.getShipTo();
		String catalog = punchout.getAuxiliary();
		int numberLine = 0;

		for (Iterator iter = poHeader.getPoLineList().iterator(); iter.hasNext();)
		{
			poLine = (PoLine) iter.next();

			if (postLineShipTo.equalsIgnoreCase("true") && (poLine.getShipToList().size() > 0))
			{
				for (Iterator iterator = poLine.getShipToList().iterator(); iterator.hasNext();)
				{
					itemsOut.add(this.buildItemOut(poHeader, poLine, (ShipTo) iterator.next(), new Integer(++numberLine), catalog, postLineShipTo, punchout, organizationId));
				}
			} else
			{
				itemsOut.add(this.buildItemOut(poHeader, poLine, null, new Integer(++numberLine), catalog, postLineShipTo, punchout, organizationId));
			}
		}

		return itemsOut;
	}

	private ItemOut buildItemOut(PoHeader poHeader, PoLine poLine, ShipTo shipTo, Integer lineNumber, String catalog, String postLineShipTo, Punchout punchout, String organizationId)
			throws Exception
	{
		ItemOut itemOut = new ItemOut();
		ItemDetail itemDetail;
		Description description;
		Shipping shipping;
		UserProfile requisitioner;
		TaxCode taxCode;
		Contact contact;

		if (shipTo != null)
		{
			itemOut.setQuantity(shipTo.getQuantity().toString());
		} else
		{
			itemOut.setQuantity(poLine.getQuantity().toString());
		}

		itemOut.setLineNumber(lineNumber.toString());

		if (poLine.getCatalogId().equalsIgnoreCase(catalog))
		{
			itemOut.setItemID(new ItemID(poLine.getItemNumber(), poLine.getUdf5Code()));
		} else
		{
			itemOut.setItemID(new ItemID(poLine.getItemNumber()));
		}

		itemDetail = new ItemDetail();

		itemDetail.setUnitPrice(new Money(poHeader.getCurrencyCode(), poLine.getUnitPrice()));

		description = new Description();

		description.setLang("en");
		description.putContent(Params.TEXT_NAME, poLine.getDescription());

		itemDetail.addDescription(description);

		itemDetail.setUnitOfMeasure(poLine.getUmCode());

		itemDetail.addClassification(new Classification("UNSPSC", poLine.getCommodity()));

		itemDetail.addExtrinsic(new Extrinsic("taxable", poLine.getTaxable()));

		itemOut.setItemDetail(itemDetail);

		if (PropertiesManager.getInstance(organizationId).getProperty("PO DEFAULTS", "ASSIGNSHIPTOBYCXMLLINE", "Y").equalsIgnoreCase("Y"))
		{
			if ((shipTo != null) && (shipTo.getShipToAddress() != null))
			{
				String deliverTo = shipTo.getShipToAddress().getAddressLine1();

				if (!HiltonUtility.isEmpty(shipTo.getAttention()))
				{
					deliverTo = shipTo.getAttention();
				}

				itemOut.setShipTo(this.buildAddressShipBillToObject(shipTo.getShipToAddress(), null, null, deliverTo));
			} else if (postLineShipTo.equalsIgnoreCase("true"))
			{

				itemOut.setShipTo(this.buildAddressShipBillToObject(poHeader.getShipToAddress(), null, null, null));
			}
		}

		if (shipTo == null)
		{
			if ((poLine.getShippingCharges() != null) && (poLine.getShippingCharges().intValue() != 0))
			{
				shipping = new Shipping();

				shipping.setMoney(new Money(poHeader.getCurrencyCode(), poLine.getShippingCharges()));

				description = new Description();

				description.setLang("en");
				description.putContent(Params.TEXT_NAME, "Shipping Charges");

				shipping.setDescription(description);

				itemOut.setShipping(shipping);
			}

			if ((poLine.getTaxAmount() != null) && (poLine.getTaxAmount().intValue() != 0))
			{
				description = new Description();
				description.setLang("en");

				if (!HiltonUtility.isEmpty(poLine.getTaxCode()))
				{
					taxCode = TaxCodeManager.getInstance().getTaxCode(organizationId, poLine.getTaxCode());
					description.putContent(Params.TEXT_NAME, taxCode.getDescription());
				} else
				{
					description.putContent(Params.TEXT_NAME, "Tax Charges");
				}

				itemOut.setTax(new Tax(new Money(poHeader.getCurrencyCode(), poLine.getTaxAmount()), description));
			}
		}

		Account account = new Account();
		// boolean hasAccounts = true;

		if (poLine.getAccountList() != null && poLine.getAccountList().size() > 0)
		{
			account = (Account) poLine.getAccountList().get(0);
			// hasAccounts = true;
		} else if (poHeader.getAccountList() != null && poHeader.getAccountList().size() > 0)
		{
			account = (Account) poHeader.getAccountList().get(0);
			// hasAccounts = true;
		} else
		{
			throw new PunchCatalogException("POH/POL missing account info: " + poHeader.getIcPoHeader());
		}

		// if (hasAccounts)
		// {
		Distribution distribution = new Distribution();

		distribution.setAccounting(new Accounting("DistributionCharge", this.buildDistributionSegment(account, punchout), null));

		distribution.setCharge(new Money(poHeader.getCurrencyCode(), null, null, poLine.getUnitPrice().multiply(poLine.getQuantity())));

		itemOut.addDistribution(distribution);
		// }

		if (PropertiesManager.getInstance(organizationId).getProperty("PO DEFAULTS", "ASSIGNENDUSERBYCXMLLINE", "Y").equalsIgnoreCase("Y"))
		{
			contact = new Contact();

			requisitioner = UserManager.getInstance().getUser(organizationId, poLine.getRequisitionerCode());

			contact.setRole("endUser");
			if(HiltonUtility.isEmpty(punchout.getDefaultEmail()) && requisitioner != null)
			{
				if(!HiltonUtility.isEmpty(HiltonUtility.ckNull(requisitioner.getMailId())))
				{
					contact.addEmail(new Email(requisitioner.getMailId()));
				}
			}
			else
			{
				contact.addEmail(new Email(punchout.getDefaultEmail()));
			}
			contact.setName(new Name("en", requisitioner.getFirstName() + " " + requisitioner.getLastName()));

			contact.addPhone(new Phone(this.buildTelephoneNumber(requisitioner.getPhoneNumber())));

			itemOut.addContact(contact);
		}

		return itemOut;
	}

	// <!ELEMENT Address (Name, PostalAddress?, Email?, Phone?, Fax?, URL?)>
	// <!ATTLIST Address
	// isoCountryCode %isoCountryCode; #IMPLIED
	// addressID %string; #IMPLIED
	// >
	private AddressShipBillTo buildAddressShipBillToObject(Address shipBillTo, String mailId, String phoneNumber, String deliverTo)
	{
		AddressShipBillTo addressShipBillTo = new AddressShipBillTo();
		PostalAddress postalAddress = new PostalAddress();
		String country = shipBillTo.getCountry();
		addressShipBillTo.setAddressID(shipBillTo.getComp_id().getAddressCode());
		addressShipBillTo.setName(new Name("en", shipBillTo.getAddressLine1()));

		if (shipBillTo.getAddressLine1() != null)
		{
			if (!HiltonUtility.isEmpty(deliverTo))
			{
				postalAddress.addDeliverTo(deliverTo);
			} else
			{
				postalAddress.addDeliverTo(shipBillTo.getAddressLine1());
			}
		}

		postalAddress.addStreet(shipBillTo.getAddressLine2());
		postalAddress.addStreet(shipBillTo.getAddressLine3());
		postalAddress.addStreet(shipBillTo.getAddressLine4());

		postalAddress.setCity(shipBillTo.getCity());

		postalAddress.setState(shipBillTo.getState());

		postalAddress.setPostalCode(shipBillTo.getPostalCode());

		if (HiltonUtility.isEmpty(country) && (shipBillTo.getState().length() == 2))
		{
			country = "US";
		}

		postalAddress.setCountry(new Country(country, country));
		addressShipBillTo.setIsoCountryCode(country);

		// if (postalAddress.getCountry() != null && postalAddress.getCity() !=
		// null)
		// {
		addressShipBillTo.setPostalAddress(postalAddress);
		// }

		if (!HiltonUtility.isEmpty(mailId))
		{
			addressShipBillTo.setEmail(new Email(mailId));
		}

		if (!HiltonUtility.isEmpty(phoneNumber))
		{
			addressShipBillTo.setPhone(new Phone("work", this.buildTelephoneNumber(phoneNumber)));
		}

		return addressShipBillTo;
	}

	private AddressShipBillTo buildAddressShipBillToObject(Address shipBillTo, String mailId, String phoneNumber, String deliverTo, String contactNumber, String locale)
	{
		AddressShipBillTo addressShipBillTo = new AddressShipBillTo();
		PostalAddress postalAddress = new PostalAddress();
		String country = shipBillTo.getCountry();
		addressShipBillTo.setAddressID(shipBillTo.getComp_id().getAddressCode());
		addressShipBillTo.setName(new Name("en", shipBillTo.getAddressLine1()));

		if (shipBillTo.getAddressLine1() != null)
		{
			if (!HiltonUtility.isEmpty(deliverTo))
			{
				postalAddress.addDeliverTo(deliverTo);
			} else
			{
				postalAddress.addDeliverTo(shipBillTo.getAddressLine1());
			}
		}

		postalAddress.addStreet(shipBillTo.getAddressLine2());
		postalAddress.addStreet(shipBillTo.getAddressLine3());
		postalAddress.addStreet(shipBillTo.getAddressLine4());

		postalAddress.setCity(shipBillTo.getCity());

		postalAddress.setState(shipBillTo.getState());

		postalAddress.setPostalCode(shipBillTo.getPostalCode());

		if (HiltonUtility.isEmpty(country) && (shipBillTo.getState().length() == 2))
		{
			country = "US";
		}

		postalAddress.setCountry(new Country(country, country));
		addressShipBillTo.setIsoCountryCode(country);

		// if (postalAddress.getCountry() != null && postalAddress.getCity() !=
		// null)
		// {
		addressShipBillTo.setPostalAddress(postalAddress);
		// }

		if (!HiltonUtility.isEmpty(mailId))
		{
			addressShipBillTo.setEmail(new Email(mailId));
		}
		if(HiltonUtility.isEmpty(phoneNumber))
		{
			phoneNumber = HiltonUtility.ckNull(contactNumber);
		}

		if (!HiltonUtility.isEmpty(phoneNumber))
		{
			addressShipBillTo.setPhone(new Phone("work", this.buildTelephoneNumberUserProfile(phoneNumber, locale)));
		}

		return addressShipBillTo;
	}

	private List buildDistributionSegment(Account account, Punchout punchout) throws Exception
	{
		List segment = new ArrayList();
		Segment segmentElement;
		Method accountMethod;
		String type;
		String value;

		for (int i = 0; i < Params.NUMBER_OF_ACCOUNTING_FIELDS; i++)
		{
			type = punchout.getGeneralFld(i + 1);

			if (!HiltonUtility.isEmpty(type))
			{
				value = "";
				segmentElement = new Segment();

				segmentElement.setType(type);
				try
				{
					accountMethod = account.getClass().getMethod("getFld" + String.valueOf(i + 1), new Class[] {});

					value = (String) accountMethod.invoke(account, new Object[0]);
				} catch (Exception e)
				{
					// TODO: handle exception
					continue;
				}

				segmentElement.setId(value);

				segmentElement.setDescription(value);

				segment.add(segmentElement);
			}
		}

		return segment;
	}

    public String getFormatTimeStamp(String d_date)
	{
    	StringBuffer timeStamp = new StringBuffer();
		String timeZoneId = TimeZone.getDefault().getID();
		Calendar aGMTCalendar = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
		timeStamp.append(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Dates.getDate((String) d_date)));
		timeStamp.append("-" + new SimpleDateFormat("HH:mm").format(aGMTCalendar.getTime()));

		return timeStamp.toString();
	}

	private TelephoneNumber buildTelephoneNumber(String phoneNumber)
	{
		TelephoneNumber telephoneNumber = new TelephoneNumber();
		String reducedPhoneNumber;

		if (phoneNumber.toLowerCase().indexOf("ext") > 0)
		{
			phoneNumber = phoneNumber.substring(0, phoneNumber.toLowerCase().indexOf("ext")).trim();
		}

		reducedPhoneNumber = this.removeExtraneousChars(phoneNumber);

		switch (reducedPhoneNumber.length())
		{
			case 7:
				telephoneNumber.setCountryCode(new CountryCode("US", "1"));
				telephoneNumber.setAreaOrCityCode(new Integer(0));
				telephoneNumber.setNumber(reducedPhoneNumber);
				break;
			case 10:
				telephoneNumber.setCountryCode(new CountryCode("US", "1"));
				telephoneNumber.setAreaOrCityCode(new Integer(reducedPhoneNumber.substring(0, 3)));
				telephoneNumber.setNumber(reducedPhoneNumber.substring(3, 6) + "-" + reducedPhoneNumber.substring(6));
				break;
			case 11:
				telephoneNumber.setCountryCode(new CountryCode("US", reducedPhoneNumber.substring(0, 0)));
				telephoneNumber.setAreaOrCityCode(new Integer(reducedPhoneNumber.substring(1, 4)));
				telephoneNumber.setNumber(reducedPhoneNumber.substring(4, 7) + "-" + reducedPhoneNumber.substring(7));
				break;
			case 13:
				telephoneNumber.setCountryCode(new CountryCode("US", reducedPhoneNumber.substring(0, 0)));
				telephoneNumber.setAreaOrCityCode(new Integer(reducedPhoneNumber.substring(0, 3)));
				telephoneNumber.setNumber(reducedPhoneNumber.substring(3, 6) + "-" + reducedPhoneNumber.substring(6, 10) + "-" + reducedPhoneNumber.substring(10));
				break;
			default:
				CountryCode countryCode = new CountryCode("", "");
				Integer areaOrCityCode = null;

				if (!HiltonUtility.isEmpty(reducedPhoneNumber))
				{
					countryCode = new CountryCode("", "0");
					areaOrCityCode = new Integer(0);
				}

				telephoneNumber.setCountryCode(countryCode);
				telephoneNumber.setAreaOrCityCode(areaOrCityCode);
				telephoneNumber.setNumber(reducedPhoneNumber);
				break;
		}

		return telephoneNumber;
	}

	private TelephoneNumber buildTelephoneNumberUserProfile(String phoneNumber, String locale)
	{
		TelephoneNumber telephoneNumber = new TelephoneNumber();
		String reducedPhoneNumber;

		if (phoneNumber.toLowerCase().indexOf("ext") > 0)
		{
			phoneNumber = phoneNumber.substring(0, phoneNumber.toLowerCase().indexOf("ext")).trim();
		}

		reducedPhoneNumber = this.removeExtraneousChars(phoneNumber);
		if(HiltonUtility.isEmpty(locale))
		{
			locale = "US";
		}

		switch (reducedPhoneNumber.length())
		{
			case 7:
				telephoneNumber.setCountryCode(new CountryCode(locale, "1"));
				telephoneNumber.setAreaOrCityCode(new Integer(0));
				telephoneNumber.setNumber(reducedPhoneNumber);
				break;
			case 10:
				telephoneNumber.setCountryCode(new CountryCode(locale, "1"));
				telephoneNumber.setAreaOrCityCode(new Integer(reducedPhoneNumber.substring(0, 3)));
				telephoneNumber.setNumber(reducedPhoneNumber.substring(3, 6) + "-" + reducedPhoneNumber.substring(6));
				break;
			case 11:
				telephoneNumber.setCountryCode(new CountryCode(locale, reducedPhoneNumber.substring(0, 2)));
				telephoneNumber.setAreaOrCityCode(new Integer(reducedPhoneNumber.substring(2, 5)));
				telephoneNumber.setNumber(phoneNumber);
				break;
			case 13:
				telephoneNumber.setCountryCode(new CountryCode(locale, reducedPhoneNumber.substring(0, 0)));
				telephoneNumber.setAreaOrCityCode(new Integer(reducedPhoneNumber.substring(0, 3)));
				telephoneNumber.setNumber(reducedPhoneNumber.substring(3, 6) + "-" + reducedPhoneNumber.substring(6, 10) + "-" + reducedPhoneNumber.substring(10));
				break;
			default:
				CountryCode countryCode = new CountryCode("", "");
				Integer areaOrCityCode = null;

				if (!HiltonUtility.isEmpty(reducedPhoneNumber))
				{
					countryCode = new CountryCode("", "0");
					areaOrCityCode = new Integer(0);
				}

				telephoneNumber.setCountryCode(countryCode);
				telephoneNumber.setAreaOrCityCode(areaOrCityCode);
				telephoneNumber.setNumber(reducedPhoneNumber);
				break;
		}

		return telephoneNumber;
	}

	private String removeExtraneousChars(String rawNumber)
	{
		StringBuffer stringBuffer = new StringBuffer();
		byte[] bytes = rawNumber.getBytes();

		for (int i = 0; i < bytes.length; i++)
		{
			switch (bytes[i])
			{
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					stringBuffer.append((char) bytes[i]);
					break;
				default:
					break;
			}
		}

		return stringBuffer.toString();
	}
}
