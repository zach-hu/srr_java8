package com.tsa.puridiom.invoice.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceVendor;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.entity.VendorInsuranceDefault;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvoiceValidateI312 extends Task {
	private static BigDecimal TEN_THOUSAND = new BigDecimal(10000);
	
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			InvoiceVendor invoiceVendor = (InvoiceVendor) incomingRequest.get("invoiceVendor");
			
			//retrieve po header
			Map request = new HashMap();
			request.put("dbsession", dbs);
			request.put("organizationId", incomingRequest.get("organizationId"));
			request.put("PoHeader_icPoHeader", invoiceHeader.getIcPoHeader().toString());
			
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("poheader-retrieve-by-id.xml");
			
			process.executeProcess(request);
			
			PoHeader poHeader = (PoHeader) request.get("poHeader");
			
			incomingRequest.put("isNotValidI312", "false");
			
			if (poHeader != null) {
				boolean totalGreater10000 = TEN_THOUSAND.compareTo(poHeader.getTotal()) <= 0;
				boolean poForAService = poHeader.getSubType().equals("03") || poHeader.getSubType().equals("05");
				if (totalGreater10000 && poForAService) {			
						request = new HashMap();
						request.put("dbsession", dbs);
						request.put("Address_addressType", invoiceVendor.getVendorId());
						
						process = processLoader.loadProcess("address-retrieve-by-supplier.xml");
						
						process.executeProcess(request);
						
						List addresses = (List) request.get("address");
						
						//find default address
						Address address = null;
						for (Object addrObj: addresses) {
							Address addr = (Address) addrObj;
							
							if ("DEFAULT".equals(addr.getComp_id().getAddressCode())) {
								address = addr;
								break;
							}
						}
						
						if (address == null || !"SC".equalsIgnoreCase(address.getState())) {
							//retrieve vendor insurance default
							request = new HashMap();
							request.put("dbsession", dbs);
							request.put("Vendor_vendorId", invoiceVendor.getVendorId());
							
							process = processLoader.loadProcess("vendorinsurancedefault-retrieve-by-id.xml");
							
							process.executeProcess(request);
							
							VendorInsuranceDefault vendorInsuranceDefault = (VendorInsuranceDefault) request.get("vendorInsuranceDefault");
							
							if (vendorInsuranceDefault == null || (!"A".equals(vendorInsuranceDefault.getCertStatus1()) && !"W".equals(vendorInsuranceDefault.getCertStatus1())))
							{
								incomingRequest.put("isNotValidI312", "true");
							}
						}
				}
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			incomingRequest.put("isValidI312", "false");
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
