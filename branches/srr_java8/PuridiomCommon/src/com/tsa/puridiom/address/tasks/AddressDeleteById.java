package com.tsa.puridiom.address.tasks;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.*;

public class AddressDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Address address = (Address)incomingRequest.get("address");
		if(address == null)
		{
			address = new Address();
		}
		AddressSetValuesPK setValues = new AddressSetValuesPK();
		setValues.setValues(incomingRequest, address);

		PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
		PuridiomProcess process = processLoader.loadProcess("address-retrieve-by-id.xml");
		process.executeProcess(incomingRequest);
		address = (Address) incomingRequest.get("address");

		if (address != null){
			dbs.delete(address);
		}
		this.setStatus(dbs.getStatus()) ;
		return address ;
	}

}