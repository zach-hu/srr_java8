/*
 * PunchOutRequest.java
 *
 * Original Created on January 30, 2002
 * Revised for Hilton on June 8, 2004
 */

package com.tsa.puridiom.punchoutcatalog;

/**
 * @author  Jeff / Kelli
 * @version
 */

import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.DictionaryManager;

import java.io.*;
import java.util.*;

public class PunchOutRequestManager {

	private static HashMap requestParameters = new HashMap();
	private static PunchOutRequestManager manager = null;
	private static String xmlPath = null;
	private static String organizationId = "puridiom";

	public static PunchOutRequestManager getInstance() {
		if (manager == null) {
			manager = new PunchOutRequestManager();
		}
		return manager;
	}

	private PunchOutRequestManager() {

		String	path = DictionaryManager.getInstance("host", this.getOrganizationId()).getProperty("browse-xml-path", "");
		this.setXmlPath(path);
	}

    public String processSSLPunchOutRequest(Object object, String targetUrl,String targetParm, Map parameters) throws Exception  {
   		String	startPage = null;

   		try {
	   		String	requestId = String.valueOf(UniqueKeyGenerator.getInstance().getUniqueKey());
	   		Map incomingRequest = (Map) object;

	   		requestParameters.put(requestId, incomingRequest);

	   		PunchOutRequest pr = new PunchOutRequest();
	   		pr.setOrganizationId((String)incomingRequest.get("organizationId"));
	   		startPage = pr.processSSLPunchOutRequest(targetUrl, targetParm, requestId, parameters);
   		}
   		catch(Exception e) {
   			e.printStackTrace();
   			throw e;
   		}

   		return startPage;
    }

    public List processPunchoutOrderMessage(String encodedXml, String organizationId, String catalogId) {
    	PunchOutRequest pr = new PunchOutRequest();

        pr.setOrganizationId(organizationId);
        pr.setCatalogId(catalogId);
    	return pr.processPunchoutOrderMessage(encodedXml);
    }
    
    public List processPunchoutOrderMessage(String encodedXml, String organizationId) {
    	PunchOutRequest pr = new PunchOutRequest();

        pr.setOrganizationId(organizationId);
    	return pr.processPunchoutOrderMessage(encodedXml);
    }

    public String readTextFile(String fileName) {
		FileReader fr;
		StringBuffer buff = new StringBuffer() ;

		try {
			fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
		    buff.setLength(0) ;
			String lineText = null;

			while((lineText = br.readLine()) != null) {
				  buff.append(lineText) ;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return buff.toString() ;
    }

    private String getXmlPath() {
    	return xmlPath;
    }
    private void setXmlPath(String path) {
    	xmlPath = path;
    }

    public static Map getRequestParameters(String requestId) {
    	return (Map) PunchOutRequestManager.requestParameters.get(requestId);
    }

    public static void setRequestParameters(String requestId, Map parameters) {
    	PunchOutRequestManager.requestParameters.put(requestId, parameters);
    }

    public static void removeRequestParameters(String requestId) {
    	PunchOutRequestManager.requestParameters.remove(requestId);
    }
    /**
     * @return Returns the organizationId.
     */
    public String getOrganizationId()
    {
        return organizationId;
    }
    /**
     * @param organizationId The organizationId to set.
     */
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }
}


