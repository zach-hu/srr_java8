/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.vendoraffiliate.tasks;

import java.util.Iterator;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.VendorAffiliate;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class VendorAffiliateDataRetrieve extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        this.setStatus(Status.SUCCEEDED) ;
        
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("vendoraffiliate-data-retrieve.xml");
		        
		List vendorAffiliateList = (List) incomingRequest.get("vendorAffiliateList");
        for (Iterator it = vendorAffiliateList.iterator(); it.hasNext(); ) {
				incomingRequest.put("vendorAffiliate", (VendorAffiliate) it.next());
				process.executeProcess(incomingRequest);
				this.setStatus(process.getStatus()) ;
				if (process.getStatus() == Status.FAILED) {
					break ;
				}
				
        }
					
		return vendorAffiliateList  ;
	}
	
}
