package com.tsa.puridiom.departmentbuyer.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.tsa.puridiom.entity.DepartmentBuyer;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

public class DepartmentBuyerManager
{
	private static DepartmentBuyerManager INSTANCE = new DepartmentBuyerManager();

	private DepartmentBuyerManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.catalog.tasks.CatalogItemManager
	 */
	public static DepartmentBuyerManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new DepartmentBuyerManager();
		}
		return INSTANCE;
	}
    /**
     * @return com.tsa.puridiom.entity.CatalogItem
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
	public Object getDepartmentBuyer(String organizationId, String departmentCode) throws java.lang.Exception
	{
		Object result = null;
		List resultList = null;
		try
		{
			if (Utility.isEmpty(departmentCode) || Utility.isEmpty(organizationId))
			{
				result = new DepartmentBuyer();
				return result;
			}
			else
			{
				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("DepartmentBuyer_departmentCode", departmentCode);


				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("departmentbuyer-retrieve-by-dept.xml");

				process.executeProcess(incomingRequest);

				resultList = (List)incomingRequest.get("departmentBuyer");
				if (resultList == null)
				{
					result = new DepartmentBuyer();
				}
				else{
					result = resultList.get(0);
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public String getBuyerId(String organizationId, String departmentCode) throws java.lang.Exception
	{
		String result = "";
		List resultList = null;
		try
		{
			if (!Utility.isEmpty(departmentCode) || Utility.isEmpty(organizationId))
			{

				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("DepartmentBuyer_departmentCode", departmentCode);


				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("departmentbuyer-retrieve-by-dept.xml");

				process.executeProcess(incomingRequest);

				resultList = (List)incomingRequest.get("departmentBuyer");
				if (resultList != null && resultList.size() > 0 )
				{
					DepartmentBuyer objDepartmentBuyer = (DepartmentBuyer)resultList.get(0);
					result = objDepartmentBuyer.getComp_id().getUserId();
				}

			}
			return result;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}