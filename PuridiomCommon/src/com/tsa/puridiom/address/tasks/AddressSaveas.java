package com.tsa.puridiom.address.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class AddressSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain address */
			AddressPK comp_id = new AddressPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			Address	originalAddress = (Address) incomingRequest.get("address");
			Address	address = new Address();

			comp_id.setAddressType(originalAddress.getComp_id().getAddressType());
			comp_id.setAddressCode(originalAddress.getComp_id().getAddressCode());
			address.setAddressLine1(originalAddress.getAddressLine1());
			address.setAddressLine2(originalAddress.getAddressLine2());
			address.setAddressLine3(originalAddress.getAddressLine3());
			address.setAddressLine4(originalAddress.getAddressLine4());
			address.setCity(originalAddress.getCity());
			address.setState(originalAddress.getState());
			address.setPostalCode(originalAddress.getPostalCode());
			address.setCountry(originalAddress.getCountry());
			address.setDateEntered(originalAddress.getDateEntered());
			address.setDateExpires(originalAddress.getDateExpires());
			address.setStatus(originalAddress.getStatus());
			address.setOwner(originalAddress.getOwner());
			address.setInventory(originalAddress.getInventory());
			address.setShipTo(originalAddress.getShipTo());
			address.setBillTo(originalAddress.getBillTo());
			address.setInvoiceTo(originalAddress.getInvoiceTo());
			address.setIssuedBy(originalAddress.getIssuedBy());
			address.setOfferTo(originalAddress.getOfferTo());
			address.setAdministeredBy(originalAddress.getAdministeredBy());
			address.setPaymentBy(originalAddress.getPaymentBy());
			address.setPurchaseFor(originalAddress.getPurchaseFor());
			address.setAddrFld10(originalAddress.getAddrFld10());
			address.setAddrFld11(originalAddress.getAddrFld11());
			address.setAddrFld12(originalAddress.getAddrFld12());
			address.setAddrFld13(originalAddress.getAddrFld13());
			address.setAddrFld14(originalAddress.getAddrFld14());
			address.setAddrFld15(originalAddress.getAddrFld15());
			address.setAddrFld16(originalAddress.getAddrFld16());
			address.setAddrFld17(originalAddress.getAddrFld17());
			address.setAddrFld18(originalAddress.getAddrFld18());
			address.setAddrFld19(originalAddress.getAddrFld19());
			address.setAddrFld20(originalAddress.getAddrFld20());
			address.setTaxCode(originalAddress.getTaxCode());
			address.setComp_id(comp_id);

			incomingRequest.put("address", address);

			AddressAdd addTask = new AddressAdd();
			address = (Address) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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