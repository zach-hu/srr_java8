package com.tsa.puridiom.menu;

import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * Creation date: September 2004
 * @author: Kelli Knisely
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
	 * @param userId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getRecentRequisitions(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);
				processParameters.put("RecentRequisition_requisitionerCode", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("recentrequisition-retrieve-header-by.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentRequisitionList");

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
		return list;
	}

	public static List getRecentMsr(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);
				processParameters.put("RecentRequisition_requisitionerCode", userId);
				processParameters.put("msrOnly", "Y") ;

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("recentrequisition-retrieve-header-by.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentRequisitionList");

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
		return list;
	}


	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param userId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getRecentOrders(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);
				processParameters.put("RecentOrder_buyerCode", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("recentorder-retrieve-header-by.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentOrderList");

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
		return list;
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param userId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getRecentSolicitations(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);
				processParameters.put("RecentRfq_buyerCode", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("recentrfq-retrieve-header-by.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentRfqList");

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
		return list;
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param userId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getRecentReqItems(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);
				processParameters.put("RecentReqItem_requisitionerCode", userId);
				processParameters.put("RecentReqItem_itemSource", "CAT");

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("recentreqitem-retrieve-by.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentReqItemList");

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
		return list;
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param userId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getRecentReqInvItems(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);
				processParameters.put("RecentReqItem_requisitionerCode", userId);
				processParameters.put("RecentReqItem_itemSource", "INV");

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("recentreqitem-retrieve-by.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentReqItemList");

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
		return list;
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param userId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getRecentOrderItems(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);
				processParameters.put("RecentOrderItem_buyerCode", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("recentorderitem-retrieve-by.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentOrderItemList");

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
		return list;
	}
	
	
	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param userId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getRecentReceipts(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);
				processParameters.put("RecentReceipt_receivedBy", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("recentreceipt-retrieve-header-by.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("recentReceiptList");

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
		return list;
	}
	
	
	public static Map getGraphData(String organizationId, String userId)
	{
	    Map ret = null;
	    try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();

                UserProfile user = UserManager.getInstance().getUser(organizationId, userId);

				processParameters.put("organizationId", organizationId);
				processParameters.put("user", user);
				processParameters.put("userId", userId);
				processParameters.put("userTimeZone", user.getTimeZone());

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
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

	public static List getSystemReports(String organizationId, String userId) throws java.lang.Exception
	{
	    List reports = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("report-retrieve-system.xml");

				process.executeProcess(processParameters);

				reports = (List) processParameters.get("reportsList");

				if (reports == null)
				{
				    reports = new ArrayList();
				}
			}
		}
		catch (Exception e)
		{
		    Log.error("MenuManager", "getSystemReports-" + e.toString());
			throw e;
		}
		return reports;
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getExternalCatalogs(String organizationId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("catalog-retrieve-external.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("catalogList");

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
		return list;
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getTopInternalCatalogs(String organizationId, int maxNumber) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("maxRows", String.valueOf(maxNumber));

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("catalog-retrieve-internal.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("catalogList");

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
		return list;
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getNews(String organizationId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				//processParameters.put("userId", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("news-retrieve-all.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("newsList");

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
		return list;
	}

	/**
	 * @return java.util.List
	 * @param organizationId java.lang.String
	 * @param userId java.lang.String
	 * @exception java.lang.Exception
	 */
	public static List getTemplateRequisitions(String organizationId, String userId) throws java.lang.Exception
	{
		List list = new ArrayList();

		try
		{
			if (!Utility.isEmpty(userId) && !Utility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("userId", userId);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("requisition-retrieve-templates.xml");

				process.executeProcess(processParameters);

				list = (List) processParameters.get("templateRequisitionList");

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
		return list;
	}
	/**
	 * Returns a String that represents the value of this object.
	 * @return a string representation of the receiver
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[ClassName=com.tsa.puridiom.menu.MenuManager]");
		return sb.toString();
	}
}
