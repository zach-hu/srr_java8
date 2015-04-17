/*
 * Created on Feb 24, 2005
 */
package com.tsa.puridiom.requisitionheader.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
/**
 * @author Administrator
 */
public class RequisitionHeaderSupplierSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
		if ( reqHeader!= null && HiltonUtility.isEmpty(reqHeader.getVendorId()))
		{
			if (incomingRequest.containsKey("catalog"))
			{
				Catalog catalog = (Catalog) incomingRequest.get("catalog");
				if (catalog != null) {
					if(!(reqHeader.getRequisitionType().equalsIgnoreCase("M")))
					{
						incomingRequest.put("RequisitionHeader_vendorId", catalog.getVendorId());
					}

				    String vendorId =  catalog.getVendorId();

				    if (!HiltonUtility.isEmpty(vendorId))
	                {
	                    String queryString = "from Vendor as vendor where vendor.vendorId = ? ";
	                    DBSession dbs = (DBSession)incomingRequest.get("dbsession");
	                    List resultList = dbs.query(queryString, new Object[] {vendorId } , new Type[] { Hibernate.STRING}) ;
	                    if (resultList != null && resultList.size() > 0)
	                    {
	                    	Vendor vendor = (Vendor) resultList.get(0);
	                    	incomingRequest.put("RequisitionHeader_shippingCode",vendor.getShipVia());
	                    	incomingRequest.put("RequisitionHeader_fobCode", vendor.getFobId());
	                    	incomingRequest.put("RequisitionHeader_currencyCode",vendor.getCurrencyCode());
	                    }

	                }
				}
			}
		}
		this.setStatus(Status.SUCCEEDED);

		return null;
	}
}
