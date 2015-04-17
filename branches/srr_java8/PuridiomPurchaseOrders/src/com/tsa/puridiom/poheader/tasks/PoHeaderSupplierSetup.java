/*
 * Created on Feb 24, 2005
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
/**
 * @author Administrator
 */
public class PoHeaderSupplierSetup extends Task {
    /* (non-Javadoc)
     * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception {

        Map incomingRequest = (Map) object;

        PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
        if (HiltonUtility.isEmpty(poHeader.getVendorId()))
        {
            if (incomingRequest.containsKey("catalog"))
            {
                Catalog catalog = (Catalog) incomingRequest.get("catalog");
                if (catalog != null)
                {
	                String vendorId =  catalog.getVendorId();
	                incomingRequest.put("PoHeader_vendorId", vendorId);

	                if (!HiltonUtility.isEmpty(vendorId))
	                {
	                    String queryString = "from Vendor as vendor where vendor.vendorId = ? ";
	                    DBSession dbs = (DBSession)incomingRequest.get("dbsession");
	                    List resultList = dbs.query(queryString, new Object[] {vendorId } , new Type[] { Hibernate.STRING}) ;

	                    if (resultList != null && resultList.size() > 0)
	                    {
	                    	Vendor vendor = (Vendor) resultList.get(0);

	                    	incomingRequest.put("PoHeader_vendorName", vendor.getVendorName());
	                    	incomingRequest.put("PoHeader_shipViaCode",vendor.getShipVia());
	                    	incomingRequest.put("PoHeader_fobCode", vendor.getFobId());
	                    	incomingRequest.put("PoHeader_vendorClass", vendor.getVendorClass());
	                    	incomingRequest.put("PoHeader_termsCode",vendor.getVendTerms());
	                    	incomingRequest.put("PoHeader_ediOrder",vendor.getPrintFaxCode());
	                    	incomingRequest.put("PoHeader_inspectionReqd",vendor.getInspectionReqd());
	                    	incomingRequest.put("PoHeader_currencyCode",vendor.getCurrencyCode());
	                    }

	                }
            	}
            }
        }
        this.setStatus(Status.SUCCEEDED);

        return null;
    }
}
