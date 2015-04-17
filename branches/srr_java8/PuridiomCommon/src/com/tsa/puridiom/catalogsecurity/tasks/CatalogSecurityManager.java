package com.tsa.puridiom.catalogsecurity.tasks;

import java.util.HashMap;
import java.util.Map;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.CatalogSecurity;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class CatalogSecurityManager
{
	private static CatalogSecurityManager INSTANCE = new CatalogSecurityManager();

	private CatalogSecurityManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.catalogsecurity.tasks.CatalogSecurityManager
	 */
	public static CatalogSecurityManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new CatalogSecurityManager();
		}
		return INSTANCE;
	}
    /**
     * @return com.tsa.puridiom.entity.CatalogSecuirty
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
	public Object getCatalogSecurity(String organizationId, String catalogId, String itemNumber) throws java.lang.Exception
	{
		Object result = null;
		try
		{
			if (Utility.isEmpty(catalogId) || Utility.isEmpty(itemNumber) || Utility.isEmpty(organizationId))
			{
				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("CatalogSecurity_catalogId", catalogId);
				incomingRequest.put("CatalogSecuirty_itemNumber", itemNumber);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("catalogsecurity-retrieve-by-id.xml");

				process.executeProcess(incomingRequest);

				result = incomingRequest.get("catalogSecurity");
				if (result == null)
				{
					result = new CatalogSecurity();
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw e;
		}

	}

	 public boolean exitsUserId(String organizationId, String userId) throws java.lang.Exception {

    	boolean result  = false;
    	Object resultList = null;
		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("CatalogSecurity_accessId", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("catalogsecurity-retrieve-by-user-id.xml");

				process.executeProcess(incomingRequest);

				resultList = incomingRequest.get("catalogSecurity");
			}
			if (resultList != null)
			{
				CatalogSecurity catalogSecurity = (CatalogSecurity)resultList;

				if(!HiltonUtility.isEmpty(catalogSecurity.getAccessId()) ){
					result = true;
				}
			}
		}

		catch (Exception e)
		{	Log.debug(this, "Error retrieve catalogSecurityByUserId: \r\n" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	 }

	 public boolean isEnabledPunchoutCatalog(String organizationId, UserProfile user, String catalogId) throws java.lang.Exception {

	    	boolean result  = false;

			try
			{	String isEnabledPunchoutCatalog = "F";
				if ( user!= null && !Utility.isEmpty(organizationId))
				{
					Map incomingRequest = new HashMap();
					incomingRequest.put("organizationId", organizationId);

					incomingRequest.put("catalogId", catalogId);
					incomingRequest.put("userId", (String) user.getUserId());
					incomingRequest.put("userDepartment", (String) user.getDepartment());
					incomingRequest.put("userNameUdf1", (String) user.getNameUdf1());
					incomingRequest.put("userNameUdf2", (String) user.getNameUdf2());
					incomingRequest.put("userNameUdf3", (String) user.getNameUdf3());

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("lookup-punchout-by-catalogsecurity.xml");

					process.executeProcess(incomingRequest);

					isEnabledPunchoutCatalog = (String)incomingRequest.get("isEnabledPunchoutCatalog");
				}
				if (isEnabledPunchoutCatalog.equalsIgnoreCase("Y"))
				{
					result = true;
				}
			}

			catch (Exception e)
			{	Log.debug(this, "Error CatalogSecurityManager method isEnabledPunchoutCatalog: \r\n" + e.getMessage());
				e.printStackTrace();
				throw e;
			}
			return result;
		 }

	 public String isEnabledOthersCatalog(String organizationId, UserProfile user, String catalogId) throws java.lang.Exception {

	    	String result  = "";

			try
			{	String isEnabledOthersCatalog = "F";
				if ( user!= null && !Utility.isEmpty(organizationId))
				{
					Map incomingRequest = new HashMap();
					incomingRequest.put("organizationId", organizationId);

					incomingRequest.put("catalogId", catalogId);
					incomingRequest.put("userId", (String) user.getUserId());
					incomingRequest.put("userDepartment", (String) user.getDepartment());
					incomingRequest.put("userNameUdf1", (String) user.getNameUdf1());
					incomingRequest.put("userNameUdf2", (String) user.getNameUdf2());
					incomingRequest.put("userNameUdf3", (String) user.getNameUdf3());

					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
					PuridiomProcess process = processLoader.loadProcess("lookup-others-by-catalogsecurity.xml");

					process.executeProcess(incomingRequest);

//					isEnabledOthersCatalog = (String)incomingRequest.get("isEnabledOthersCatalog");
					result = (String)incomingRequest.get("isEnabledOthersCatalog");

				}
//				if (isEnabledOthersCatalog.equalsIgnoreCase("Y"))
//				{
//					result = true;
//				}else if (isEnabledOthersCatalog.equalsIgnoreCase("F"))
//				{
//					result = false;
//				}
			}

			catch (Exception e)
			{	Log.debug(this, "Error CatalogSecurityManager method isEnabledPunchoutCatalog: \r\n" + e.getMessage());
				e.printStackTrace();
				throw e;
			}
			return result;
		 }

}