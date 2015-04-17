package com.tsa.puridiom.requisitionline.rules;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.vendor.tasks.VendorRetrieveById;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RequisitionLineAddToCatalogSupplierRule extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
        {
	    	List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
            Map suppliersValidated = new HashMap();
            VendorRetrieveById vendorRetrieve = new VendorRetrieveById();
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

	        for (Iterator it = requisitionLineList.iterator(); it.hasNext(); )
	        {
	        	RequisitionLine requisitionLine = (RequisitionLine) it.next();

	        	if ( requisitionLine.getUdf1Code().equals("Y"))	// HOYA udf1Code is for Add To Catalog flag
	        	{
	        		if (HiltonUtility.isEmpty(requisitionLine.getVendorId()))
	        		{
	        			incomingRequest.put("RequisitionLineAddToCatalogSupplierRule", "failed");
	        		}
	        		else
	        		{
	        		    // Determine if supplier is valid
	        		    String vendorId = requisitionLine.getVendorId();
                        boolean valid = false;
                        if (suppliersValidated.containsKey(vendorId))
                        {
                            valid = ((Boolean) suppliersValidated.get(vendorId)).booleanValue();
                        }
                        else
                        {
                            incomingRequest.put("Vendor_vendorId", vendorId);
                            Vendor vendor = (Vendor) vendorRetrieve.executeTask(incomingRequest);
                            if (vendor != null && (vendor.getStatus().equals("02") || (vendor.getStatus().equals("01") && vendor.getDateExpires().after(Dates.getSqlDate(Dates.today("", userTimeZone)))))) {
                                valid = true;
                            } else {
                                valid = false;
                            }
                            suppliersValidated.put(vendorId, new Boolean(valid));

                            if (!valid) {
                                incomingRequest.put("RequisitionLineAddToCatalogSupplierRule", "failed");
                                break;
                            }
                        }
                    }
	        	}
	        }
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at RequisitionLineAddToCatalogSupplierRule", e);
		}
		return result;
    }
}