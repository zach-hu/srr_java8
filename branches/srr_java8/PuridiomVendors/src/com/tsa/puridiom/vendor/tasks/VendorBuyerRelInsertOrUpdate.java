package com.tsa.puridiom.vendor.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.entity.VendorBuyerRel;
import com.tsa.puridiom.entity.VendorBuyerRelPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

/**
 * Class responsible for inserting or updating the associated 
 * VendorBuyerReal to Vendor
 * 
 * @author Alexander
 *
 */
public class VendorBuyerRelInsertOrUpdate extends Task
{
	@SuppressWarnings("unchecked")
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{	
			DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
			Vendor vendor = (Vendor) incomingRequest.get("vendor");
			
			if (vendor == null)
			{
				throw new Exception ("Vendor was not found.");
			}
			
			String queryString = "from VendorBuyerRel vbr where vbr.id.vendorId = ?";
			List resultList = dbs.query(queryString, new Object[] {vendor.getVendorId()} , new Type[] { Hibernate.STRING}) ;
			
			VendorBuyerRel vendorBuyerRel = null;
			if (resultList != null && resultList.size() > 0)
			{
				vendorBuyerRel = (VendorBuyerRel) resultList.get(0);
			}
			
			String buyer = vendor.getBuyer();
			if (vendorBuyerRel != null) 
			{
				if (buyer != null && buyer.length() > 0) 
				{
					dbs.delete(vendorBuyerRel);

					VendorBuyerRelPK vendorBuyerRelPK = new VendorBuyerRelPK();
					vendorBuyerRelPK.setVendorId(vendor.getVendorId());
					vendorBuyerRelPK.setUserId(buyer);
					
					vendorBuyerRel = new VendorBuyerRel(vendorBuyerRelPK);
					dbs.add(vendorBuyerRel);					
				} 
				else 
				{
					dbs.delete(vendorBuyerRel);
				}
			}
			else
			{
				if (buyer != null && buyer.length() > 0) 
				{
					VendorBuyerRelPK vendorBuyerRelPK = new VendorBuyerRelPK();
					vendorBuyerRelPK.setVendorId(vendor.getVendorId());
					vendorBuyerRelPK.setUserId(buyer);
					
					vendorBuyerRel = new VendorBuyerRel(vendorBuyerRelPK);
					dbs.add(vendorBuyerRel);
				} 
			}
		
			result = vendor;
			this.setStatus(dbs.getStatus()) ;
			//this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
