package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
public class AddressRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Address as address where 1=1 ");
		List args = new ArrayList();
		List<Type> types = new ArrayList();
		if(incomingRequest.containsKey("Address_addressType"))
		{			
			String addressType = (String) incomingRequest.get("Address_addressType");
			args.add(addressType);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.id.addressType = ?");
		}
		if(incomingRequest.containsKey("Address_addressCode"))
		{			
			String addressCode = (String) incomingRequest.get("Address_addressCode");
			args.add(addressCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.id.addressCode = ?");
		}
		if(incomingRequest.containsKey("Address_addressLine1"))
		{			
			String addressLine1 = (String) incomingRequest.get("Address_addressLine1");
			args.add(addressLine1);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addressLine1 = ?");
		}
		if(incomingRequest.containsKey("Address_addressLine2"))
		{			
			String addressLine2 = (String) incomingRequest.get("Address_addressLine2");
			args.add(addressLine2);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addressLine2 = ?");
		}
		if(incomingRequest.containsKey("Address_addressLine3"))
		{			
			String addressLine3 = (String) incomingRequest.get("Address_addressLine3");
			args.add(addressLine3);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addressLine3 = ?");
		}
		if(incomingRequest.containsKey("Address_addressLine4"))
		{			
			String addressLine4 = (String) incomingRequest.get("Address_addressLine4");
			args.add(addressLine4);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addressLine4 = ?");
		}
		if(incomingRequest.containsKey("Address_city"))
		{			
			String city = (String) incomingRequest.get("Address_city");
			args.add(city);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.city = ?");
		}
		if(incomingRequest.containsKey("Address_state"))
		{			
			String state = (String) incomingRequest.get("Address_state");
			args.add(state);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.state = ?");
		}
		if(incomingRequest.containsKey("Address_postalCode"))
		{			
			String postalCode = (String) incomingRequest.get("Address_postalCode");
			args.add(postalCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.postalCode = ?");
		}
		if(incomingRequest.containsKey("Address_country"))
		{			
			String country = (String) incomingRequest.get("Address_country");
			args.add(country);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.country = ?");
		}
		if(incomingRequest.containsKey("Address_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("Address_dateEntered");
			args.add(dateEntered);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.dateEntered = ?");
		}
		if(incomingRequest.containsKey("Address_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("Address_dateExpires");
			args.add(dateExpires);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.dateExpires = ?");
		}
		if(incomingRequest.containsKey("Address_status"))
		{			
			String status = (String) incomingRequest.get("Address_status");
			args.add(status);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.status = ?");
		}
		if(incomingRequest.containsKey("Address_owner"))
		{			
			String owner = (String) incomingRequest.get("Address_owner");
			args.add(owner);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.owner = ?");
		}
		if(incomingRequest.containsKey("Address_inventory"))
		{			
			String inventory = (String) incomingRequest.get("Address_inventory");
			args.add(inventory);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.inventory = ?");
		}
		if(incomingRequest.containsKey("Address_shipTo"))
		{			
			String shipTo = (String) incomingRequest.get("Address_shipTo");
			args.add(shipTo);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.shipTo = ?");
		}
		if(incomingRequest.containsKey("Address_billTo"))
		{			
			String billTo = (String) incomingRequest.get("Address_billTo");
			args.add(billTo);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.billTo = ?");
		}
		if(incomingRequest.containsKey("Address_invoiceTo"))
		{			
			String invoiceTo = (String) incomingRequest.get("Address_invoiceTo");
			args.add(invoiceTo);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.invoiceTo = ?");
		}
		if(incomingRequest.containsKey("Address_issuedBy"))
		{			
			String issuedBy = (String) incomingRequest.get("Address_issuedBy");
			args.add(issuedBy);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.issuedBy = ?");
		}
		if(incomingRequest.containsKey("Address_offerTo"))
		{			
			String offerTo = (String) incomingRequest.get("Address_offerTo");
			args.add(offerTo);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.offerTo = ?");
		}
		if(incomingRequest.containsKey("Address_administeredBy"))
		{			
			String administeredBy = (String) incomingRequest.get("Address_administeredBy");
			args.add(administeredBy);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.administeredBy = ?");
		}
		if(incomingRequest.containsKey("Address_paymentBy"))
		{			
			String paymentBy = (String) incomingRequest.get("Address_paymentBy");
			args.add(paymentBy);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.paymentBy = ?");
		}
		if(incomingRequest.containsKey("Address_puchaseFor"))
		{			
			String purchaseFor = (String) incomingRequest.get("Address_puchaseFor");
			args.add(purchaseFor);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.puchaseFor = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld10"))
		{			
			String addrFld10 = (String) incomingRequest.get("Address_addrFld10");
			args.add(addrFld10);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld10 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld11"))
		{			
			String addrFld11 = (String) incomingRequest.get("Address_addrFld11");
			args.add(addrFld11);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld11 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld12"))
		{			
			String addrFld12 = (String) incomingRequest.get("Address_addrFld12");
			args.add(addrFld12);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld12 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld13"))
		{			
			String addrFld13 = (String) incomingRequest.get("Address_addrFld13");
			args.add(addrFld13);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld13 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld14"))
		{			
			String addrFld14 = (String) incomingRequest.get("Address_addrFld14");
			args.add(addrFld14);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld14 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld15"))
		{			
			String addrFld15 = (String) incomingRequest.get("Address_addrFld15");
			args.add(addrFld15);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld15 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld16"))
		{			
			String addrFld16 = (String) incomingRequest.get("Address_addrFld16");
			args.add(addrFld16);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld16 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld17"))
		{			
			String addrFld17 = (String) incomingRequest.get("Address_addrFld17");
			args.add(addrFld17);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld17 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld18"))
		{			
			String addrFld18 = (String) incomingRequest.get("Address_addrFld18");
			args.add(addrFld18);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld18 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld19"))
		{			
			String addrFld19 = (String) incomingRequest.get("Address_addrFld19");
			args.add(addrFld19);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld19 = ?");
		}
		if(incomingRequest.containsKey("Address_addrFld20"))
		{			
			String addrFld20 = (String) incomingRequest.get("Address_addrFld20");
			args.add(addrFld20);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.addrFld20 = ?");
		}
		if(incomingRequest.containsKey("Address_taxCode"))
		{			
			String taxCode = (String) incomingRequest.get("Address_taxCode");
			args.add(taxCode);
			types.add(Hibernate.STRING);
			queryString.append(" AND address.taxCode = ?");
		}
		List result = dbs.query(queryString.toString(), args.toArray(), types.toArray(new Type[types.size()]));
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}