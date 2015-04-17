package com.tsa.puridiom.vendorregcommrel.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendorRegCommRelAddList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		    List vendorRegCommRelList = new ArrayList();
		    Object obj = incomingRequest.get("VendorRegCommRel_commodityCode");
		    Object vendorIdObj = incomingRequest.get("VendorRegCommRel_vendorId");
		    String	vendorId = "";
		    
		    if (vendorIdObj instanceof String[]) {
		        vendorId = ((String[]) vendorIdObj)[0];
		    } else if (vendorIdObj instanceof String) {
		        vendorId = (String) vendorIdObj;
		    }
		    
		    if (obj instanceof String[]) {
		        String	commodities[] = (String[]) obj;
		        for (int i=0; i < commodities.length; i++) {
		            if (Utility.isEmpty(commodities[i])) {
		                this.setStatus(Status.SUCCEEDED) ;
		                continue;
		            }
		            if (Utility.isEmpty(vendorId)) {
		                throw new Exception("Vendor Id must be specified to add VendorRegCommRel");
		            }
		            VendorRegCommRel vendorRegCommRel = new VendorRegCommRel();
		            VendorRegCommRelPK pk = new VendorRegCommRelPK();
		            pk.setCommodityCode(commodities[i]);
		            pk.setVendorId(vendorId);

		            vendorRegCommRel.setComp_id(pk);
		            
		            dbs.add(vendorRegCommRel);
		            
		            vendorRegCommRelList.add(vendorRegCommRel);
		            
		            this.setStatus(dbs.getStatus()) ;
		            if (this.getStatus() != Status.SUCCEEDED) {
		                break;
		            }
		        }
		    } else {
		        String	commodity = (String) obj;
	            if (!Utility.isEmpty(commodity)) {
		            if (Utility.isEmpty(vendorId)) {
		                throw new Exception("Vendor Id must be specified to add VendorRegCommRel");
		            }
		            VendorRegCommRel vendorRegCommRel = new VendorRegCommRel();
		            VendorRegCommRelPK pk = new VendorRegCommRelPK();
		            pk.setCommodityCode(commodity);
		            pk.setVendorId(vendorId);
	
		            vendorRegCommRel.setComp_id(pk);
			            
		            dbs.add(vendorRegCommRel);
		            
		            vendorRegCommRelList.add(vendorRegCommRel);
		            
		            this.setStatus(dbs.getStatus()) ;
	            } else {
	                this.setStatus(Status.SUCCEEDED) ;
	            }
		    }
		    
			result = vendorRegCommRelList;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}