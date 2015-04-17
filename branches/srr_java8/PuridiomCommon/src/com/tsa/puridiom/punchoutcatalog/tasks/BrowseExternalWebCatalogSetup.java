package com.tsa.puridiom.punchoutcatalog.tasks;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.Catalog;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.punchoutcatalog.PunchOutRequestManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class BrowseExternalWebCatalogSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		String organizationId = HiltonUtility.ckNull( (String) incomingRequest.get("organizationId"));

		try
		{
			Catalog catalog = (Catalog) incomingRequest.get("catalog");
			Address shipToAddress = (Address) incomingRequest.get("shipToAddress");
			String sessionId = (String) incomingRequest.get("sessionId");
			String catalogFullAccess = HiltonUtility.ckNull((String) incomingRequest.get("CatalogExternalFullAccess"));
			Map parameters = new HashMap();

			if (catalog == null)
			{
				throw new Exception("Catalog was not found.  Punchout request cannot be processed.");
			}

			String targetParm = catalog.getRequestXml();
			String targetUrl = catalog.getPunchoutUrl();
			if(catalogFullAccess.equalsIgnoreCase("Y"))
			{
				String parseNameXml[] = targetParm.split("\\.");
				String name = parseNameXml[0];
				String ext = parseNameXml[1];
				String targetParmAux = name + "-full." + ext;
				String filename = this.getXmlPath(organizationId) + targetParmAux;
				//File f = new File(filename);
				File f = Utility.getOidFile(filename, organizationId);
				if(f.exists())
				{
					targetParm = targetParmAux;
				}
			}

			if (!HiltonUtility.isEmpty(sessionId))
			{
				parameters.put("BuyerCookie", sessionId);
			}

			if (shipToAddress != null)
			{
				parameters.put("ZipCode", this.getZipCodeFromAddress(shipToAddress));
			}

			if ("Y".equals(PropertiesManager.getInstance(organizationId).getProperty("PUNCHOUT", "USEMAILUSERASUSERID", "N"))) {
				String mailId = (String) incomingRequest.get("mailId");
				parameters.put("userIdPunch", mailId);
			}
			else
			{
				parameters.put("userIdPunch", incomingRequest.get("userId"));
			}

			parameters.put("mailIdPunch", incomingRequest.get("mailId"));

			String successPage = PunchOutRequestManager.getInstance().processSSLPunchOutRequest(incomingRequest, targetUrl, targetParm, parameters);
        	if (targetParm.equals("yahoo-shopping.xml")) {
				String keywords = (String) incomingRequest.get("as_keywords");
	        	if (keywords != null) {
	        		successPage = successPage + "?query=" + keywords+  "&category=";
	        	} else {
	        		successPage = successPage.substring(0, successPage.lastIndexOf("/"));
	        	}
        	}
			result = successPage;

			this.status = Status.SUCCEEDED;
		} catch (Exception e)
		{
			incomingRequest.put("errorMsg", e.getMessage());
			incomingRequest.put("exception", e);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}

	private String getXmlPath(String organizationId)
    {
    	String	xmlPath = DictionaryManager.getInstance("host", organizationId).getProperty("browse-xml-path", "");
    	return xmlPath;
    }

	private String getZipCodeFromAddress(Address shipToAddress)
	{
		String zipCode = shipToAddress.getPostalCode();

		if (zipCode.length() > 5)
		{
			zipCode = zipCode.substring(0, 4);
		}

		return zipCode;
	}
}