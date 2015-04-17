package com.tsa.puridiom.address.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class AddressDeleteDefault extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Address address = (Address)incomingRequest.get("address");
		if(address == null)
		{
			address = new Address();
			String addressType = (String ) incomingRequest.get("Address_addressType");
			String addressCode = "DEFAULT";
			AddressPK comp_id = new AddressPK();
			comp_id.setAddressCode(addressCode);
			comp_id.setAddressType(addressType);
			address.setComp_id(comp_id);
		}
		
		dbs.delete(address);
		this.setStatus(dbs.getStatus()) ;
		return address ;
	}

}