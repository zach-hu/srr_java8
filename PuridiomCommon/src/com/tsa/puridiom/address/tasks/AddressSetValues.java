package com.tsa.puridiom.address.tasks;

import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.AddressPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class AddressSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AddressPK comp_id = new AddressPK();
			Address address = (Address) incomingRequest.get("address");
			if (address == null)
			{
				address = new Address();
			}

			if (incomingRequest.containsKey("Address_addressType"))
			{
				String addressType = (String ) incomingRequest.get("Address_addressType");
				comp_id.setAddressType(addressType);
			}
			if (incomingRequest.containsKey("Address_addressCode"))
			{
				String addressCode = (String ) incomingRequest.get("Address_addressCode");
				comp_id.setAddressCode(addressCode);
			}
			if (incomingRequest.containsKey("Address_addressLine1"))
			{
				String addressLine1 = (String ) incomingRequest.get("Address_addressLine1");
				address.setAddressLine1(addressLine1);
			}
			if (incomingRequest.containsKey("Address_addressLine2"))
			{
				String addressLine2 = (String ) incomingRequest.get("Address_addressLine2");
				address.setAddressLine2(addressLine2);
			}
			if (incomingRequest.containsKey("Address_addressLine3"))
			{
				String addressLine3 = (String ) incomingRequest.get("Address_addressLine3");
				address.setAddressLine3(addressLine3);
			}
			if (incomingRequest.containsKey("Address_addressLine4"))
			{
				String addressLine4 = (String ) incomingRequest.get("Address_addressLine4");
				address.setAddressLine4(addressLine4);
			}
			if (incomingRequest.containsKey("Address_city"))
			{
				String city = (String ) incomingRequest.get("Address_city");
				address.setCity(city);
			}
			if (incomingRequest.containsKey("Address_state"))
			{
				String state = (String ) incomingRequest.get("Address_state");
				address.setState(state);
			}
			if (incomingRequest.containsKey("Address_postalCode"))
			{
				String postalCode = (String ) incomingRequest.get("Address_postalCode");
				address.setPostalCode(postalCode);
			}
			if (incomingRequest.containsKey("Address_country"))
			{
				String country = (String ) incomingRequest.get("Address_country");
				address.setCountry(country);
			}
			if (incomingRequest.containsKey("Address_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("Address_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				address.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("Address_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("Address_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				address.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("Address_status"))
			{
				String status = (String ) incomingRequest.get("Address_status");
				address.setStatus(status);
			}
			if (incomingRequest.containsKey("Address_owner"))
			{
				String owner = (String ) incomingRequest.get("Address_owner");
				address.setOwner(owner);
			}
			if (incomingRequest.containsKey("Address_inventory"))
			{
				String inventory = (String ) incomingRequest.get("Address_inventory");
				address.setInventory(inventory);
			}
			if (incomingRequest.containsKey("Address_shipTo"))
			{
				String shipTo = (String ) incomingRequest.get("Address_shipTo");
				address.setShipTo(shipTo);
			}
			if (incomingRequest.containsKey("Address_billTo"))
			{
				String billTo = (String ) incomingRequest.get("Address_billTo");
				address.setBillTo(billTo);
			}
			if (incomingRequest.containsKey("Address_invoiceTo"))
			{
				String invoiceTo = (String ) incomingRequest.get("Address_invoiceTo");
				address.setInvoiceTo(invoiceTo);
			}
			if (incomingRequest.containsKey("Address_issuedBy"))
			{
				String issuedBy = (String ) incomingRequest.get("Address_issuedBy");
				address.setIssuedBy(issuedBy);
			}
			if (incomingRequest.containsKey("Address_offerTo"))
			{
				String offerTo = (String ) incomingRequest.get("Address_offerTo");
				address.setOfferTo(offerTo);
			}
			if (incomingRequest.containsKey("Address_administeredBy"))
			{
				String administeredBy = (String ) incomingRequest.get("Address_administeredBy");
				address.setAdministeredBy(administeredBy);
			}
			if (incomingRequest.containsKey("Address_paymentBy"))
			{
				String paymentBy = (String ) incomingRequest.get("Address_paymentBy");
				address.setPaymentBy(paymentBy);
			}
			if (incomingRequest.containsKey("Address_purchaseFor"))
			{
				String purchaseFor = (String ) incomingRequest.get("Address_purchaseFor");
				address.setPurchaseFor(purchaseFor);
			}
			if (incomingRequest.containsKey("Address_addrFld10"))
			{
				String addrFld10 = (String ) incomingRequest.get("Address_addrFld10");
				address.setAddrFld10(addrFld10);
			}
			if (incomingRequest.containsKey("Address_addrFld11"))
			{
				String addrFld11 = (String ) incomingRequest.get("Address_addrFld11");
				address.setAddrFld11(addrFld11);
			}
			if (incomingRequest.containsKey("Address_addrFld12"))
			{
				String addrFld12 = (String ) incomingRequest.get("Address_addrFld12");
				address.setAddrFld12(addrFld12);
			}
			if (incomingRequest.containsKey("Address_addrFld13"))
			{
				String addrFld13 = (String ) incomingRequest.get("Address_addrFld13");
				address.setAddrFld13(addrFld13);
			}
			if (incomingRequest.containsKey("Address_addrFld14"))
			{
				String addrFld14 = (String ) incomingRequest.get("Address_addrFld14");
				address.setAddrFld14(addrFld14);
			}
			if (incomingRequest.containsKey("Address_addrFld15"))
			{
				String addrFld15 = (String ) incomingRequest.get("Address_addrFld15");
				address.setAddrFld15(addrFld15);
			}
			if (incomingRequest.containsKey("Address_addrFld16"))
			{
				String addrFld16 = (String ) incomingRequest.get("Address_addrFld16");
				address.setAddrFld16(addrFld16);
			}
			if (incomingRequest.containsKey("Address_addrFld17"))
			{
				String addrFld17 = (String ) incomingRequest.get("Address_addrFld17");
				address.setAddrFld17(addrFld17);
			}
			if (incomingRequest.containsKey("Address_addrFld18"))
			{
				String addrFld18 = (String ) incomingRequest.get("Address_addrFld18");
				address.setAddrFld18(addrFld18);
			}
			if (incomingRequest.containsKey("Address_addrFld19"))
			{
				String addrFld19 = (String ) incomingRequest.get("Address_addrFld19");
				address.setAddrFld19(addrFld19);
			}
			if (incomingRequest.containsKey("Address_addrFld20"))
			{
				String addrFld20 = (String ) incomingRequest.get("Address_addrFld20");
				address.setAddrFld20(addrFld20);
			}
			if (incomingRequest.containsKey("Address_taxCode"))
			{
				String taxCode = (String ) incomingRequest.get("Address_taxCode");
				address.setTaxCode(taxCode);
			}
			address.setComp_id(comp_id);

			result = address;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}