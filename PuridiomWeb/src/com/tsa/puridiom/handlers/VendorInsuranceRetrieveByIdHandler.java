package com.tsa.puridiom.handlers;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorInsuranceRetrieveByIdHandler implements IHandler
{
	public Map  handleRequest (Map incomingRequest) throws Exception
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("vendorinsurance-retrieve-by-id.xml");
			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				String vendorDefault = (String) incomingRequest.get("VendorInsuranceDefault");
				if ("true".equals(vendorDefault))
				{
					if ("orders/po_compliance.jsp".equals((String) incomingRequest.get("viewPage"))) {
						incomingRequest.put("viewPage", "orders/po_compliance_default.jsp");
					}
					else if ("orders/po_insurance.jsp".equals((String) incomingRequest.get("viewPage"))) {
					incomingRequest.put("viewPage", "orders/po_insurance_default.jsp");
					}
					else {
						incomingRequest.put("viewPage", incomingRequest.get("successPage"));
					}
				}
				else {
					incomingRequest.put("viewPage", incomingRequest.get("successPage"));
				}
			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		catch (Exception exception)
		{
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			throw exception;
		}
		finally
		{
			if (incomingRequest.get("viewPage") == null)
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}