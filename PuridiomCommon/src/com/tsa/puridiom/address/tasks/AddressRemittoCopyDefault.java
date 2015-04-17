package com.tsa.puridiom.address.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.AddressPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class AddressRemittoCopyDefault extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			Address address = (Address)incomingRequest.get("address");
			String addressType = (String ) incomingRequest.get("Address_addressType");
			String addressCode = (String ) incomingRequest.get("Address_addressCode");
			String vendorName = (String ) incomingRequest.get("Vendor_vendorName");
			if ( vendorName==null )
			{
				vendorName="";
			}

			boolean fieldsEmpty = false;
			if ((HiltonUtility.isEmpty(address.getAddressLine1()) || address.getAddressLine1().equals(vendorName)) && HiltonUtility.isEmpty(address.getAddressLine2() + address.getAddressLine3() + address.getAddressLine4() + address.getCity() + address.getState() + address.getPostalCode()))
			{
				fieldsEmpty = true;
			}

			if (addressCode.equals("DEFAULT"))
			{
				addressCode = "REMITTO";
			}

			String queryString = "from Address as Address where Address.id.addressType = ? and Address.id.addressCode = ? ";
			List resultList = dbs.query(queryString, new Object[] {addressType, addressCode, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;

			if ( (resultList == null || !(resultList.size() > 0)) && !fieldsEmpty)
			{
				incomingRequest.put("Address_addressCode","REMITTO");
				incomingRequest.put("Address_addrFld18","");
				incomingRequest.put("Address_addrFld17","Y");
				incomingRequest.put("Address_addrFld16","");
				incomingRequest.put("AdressRemittoFromAddressDefault","Y");
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("address-add.xml");
				process.executeProcess(incomingRequest);
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}