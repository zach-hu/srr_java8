package com.tsa.puridiom.supplierportal.menu;

import com.tsa.puridiom.supplierportal.PuridiomSupplierPortalProcessLoader;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

/**
 * Creation date: October 26, 2006
 * @author: Kathleen
 */
public class MenuManager
{
	private MenuManager()
	{
		super();
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param vendorId java.lang.String
	 * @param bIsQualified java.lang.Boolean
	 * @exception java.lang.Exception
	 */
	public static List getRecentlyBidSolicitations(String organizationId, String vendorId, boolean bIsQualified) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(vendorId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("vendorId", vendorId);
				processParameters.put("bIsQualified", Boolean.valueOf(bIsQualified));

				PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("rfqheader-retrieve-recently-bid.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentlyBidSolicitationsList");

				if (list == null)
				{
				    list = new ArrayList();
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			return list;
		}
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param vendorId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getRecentlyClosedSolicitations(String organizationId, String vendorId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(vendorId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("vendorId", vendorId);

				PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("rfqheader-retrieve-recently-closed.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentlyClosedSolicitationsList");

				if (list == null)
				{
				    list = new ArrayList();
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			return list;
		}
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param vendorId java.lang.String
	 * @param bIsQualified java.lang.Boolean
	 * @exception java.lang.Exception
	 */
	public static List getSolicitationsByCommodities(String organizationId, String vendorId, boolean bIsQualified) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(vendorId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("vendorId", vendorId);
				processParameters.put("bIsQualified", Boolean.valueOf(bIsQualified));

				PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("rfqheader-retrieve-matching-commodities.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("matchingCommoditiesList");

				if (list == null)
				{
				    list = new ArrayList();
				}
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			return list;
		}
	}

	public static Map getGraphData(String organizationId, String vendorId, String contactCode)
	{
	    Map ret = null;
	    try
		{
			if (!Utility.isEmpty(vendorId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				//processParameters.put("user", UserManager.getInstance().getUser(organizationId, userId));
				processParameters.put("vendorId", vendorId);
				processParameters.put("contactCode", contactCode);


				PuridiomProcessLoader processLoader = new PuridiomSupplierPortalProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("graph-data.xml");

				process.executeProcess(processParameters);

				ret = processParameters;

			}
		}
		catch (Exception e)
		{
			Log.error("MenuManager", e.toString());
		}

		return ret;
	}

	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.bidboard.menu.MenuManager]");
		return sb.toString();
	}
}
