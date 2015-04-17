package com.tsa.puridiom.address.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.AddressPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressChangeName extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Object ret = null;

		try
		{
			Map incomingRequest = (Map)object;
			String newLocation = (String)incomingRequest.get("newItemLocation");
			String oldLocation = (String)incomingRequest.get("oldItemLocation");

			List addressList = (List) incomingRequest.get("retrieveAddressList");
			List newAddressList = new ArrayList();
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");

			for (int i = 0; i < addressList.size(); i++)
			{
				Address address = (Address) addressList.get(i);
				if(address.getComp_id().getAddressCode().equals(oldLocation))
				{
					AddressPK pkNew = new AddressPK();
					pkNew.setAddressType(address.getComp_id().getAddressType());
					pkNew.setAddressCode(newLocation);

					Address newAddress = new Address();
					newAddress.setAddressLine1(address.getAddressLine1());
			        newAddress.setAddressLine2(address.getAddressLine2());
			        newAddress.setAddressLine3(address.getAddressLine3());
			        newAddress.setAddressLine4(address.getAddressLine4());
			        newAddress.setCity(address.getCity());
			        newAddress.setState(address.getState());
			        newAddress.setPostalCode(address.getPostalCode());
			        newAddress.setCountry(address.getCountry());
			        newAddress.setDateEntered(address.getDateEntered());
			        newAddress.setDateExpires(address.getDateExpires());
			        newAddress.setStatus(address.getStatus());
			        newAddress.setOwner(address.getOwner());
			        newAddress.setInventory(address.getInventory());
			        newAddress.setShipTo(address.getShipTo());
			        newAddress.setBillTo(address.getBillTo());
			        newAddress.setInvoiceTo(address.getInvoiceTo());
			        newAddress.setIssuedBy(address.getIssuedBy());
			        newAddress.setOfferTo(address.getOfferTo());
			        newAddress.setAdministeredBy(address.getAdministeredBy());
			        newAddress.setPaymentBy(address.getPaymentBy());
			        newAddress.setPurchaseFor(address.getPurchaseFor());
			        newAddress.setAddrFld10(address.getAddrFld10());
			        newAddress.setAddrFld11(address.getAddrFld11());
			        newAddress.setAddrFld12(address.getAddrFld12());
			        newAddress.setAddrFld13(address.getAddrFld13());
			        newAddress.setAddrFld14(address.getAddrFld14());
			        newAddress.setAddrFld15(address.getAddrFld15());
			        newAddress.setAddrFld16(address.getAddrFld16());
			        newAddress.setAddrFld17(address.getAddrFld17());
			        newAddress.setAddrFld18(address.getAddrFld18());
			        newAddress.setAddrFld19(address.getAddrFld19());
			        newAddress.setAddrFld20(address.getAddrFld20());
			        newAddress.setTaxCode(address.getTaxCode());

					newAddress.setComp_id(pkNew);
					newAddressList.add(newAddress);

					dbs.delete(address);
					this.setStatus(dbs.getStatus());
					if(this.getStatus() != Status.SUCCEEDED)
					{
						throw new TsaException("An error ocurred inserting new address!");
					}
				}
			}
			incomingRequest.put("newAddressList", newAddressList);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}