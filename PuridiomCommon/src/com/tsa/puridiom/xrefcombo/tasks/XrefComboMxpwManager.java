package com.tsa.puridiom.xrefcombo.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.XrefCombo;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class XrefComboMxpwManager
{
	private static XrefComboMxpwManager INSTANCE = new XrefComboMxpwManager();

	private XrefComboMxpwManager()
	{
		super();
	}
	/**
	 * @return com.tsa.puridiom.catalog.tasks.XrefComboMxpwManager
	 */
	public static XrefComboMxpwManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new XrefComboMxpwManager();
		}
		return INSTANCE;
	}
    /**
     * @return com.tsa.puridiom.entity.XrefCombo
     * @param userId java.lang.String
     * @exception java.lang.Exception The exception description.
     */
	public XrefCombo getXrefComboMxpw(String organizationId, String warehouse, String itemNumber) throws java.lang.Exception
	{
		XrefCombo result = null;
		List resultList = null;
		try
		{
			if (!Utility.isEmpty(warehouse) && !Utility.isEmpty(itemNumber) )
			{

				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("XrefCombo_code1", warehouse);
				incomingRequest.put("XrefCombo_code2", itemNumber);
				incomingRequest.put("XrefCombo_xrefType", "MXPW");


				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("xrefcombo-retrieve-by.xml");

				process.executeProcess(incomingRequest);

				resultList = (List)incomingRequest.get("xrefComboList");
				if (resultList != null && resultList.size() > 0 )
				{
					XrefCombo xrefCombo = (XrefCombo)resultList.get(0);
					result = xrefCombo;
				}

			}
			return result;
		}
		catch (Exception e)
		{
			Log.error(this, "Error on method getXrefComboMxpw " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	public String getCode4(String organizationId, String warehouse, String itemNumber) throws java.lang.Exception
	{
		String result = "";
		List resultList = null;
		try
		{
			if (!Utility.isEmpty(warehouse) && !Utility.isEmpty(itemNumber) )
			{

				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("XrefCombo_code1", warehouse);
				incomingRequest.put("XrefCombo_code2", itemNumber);
				incomingRequest.put("XrefCombo_xrefType", "MXPW");


				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("xrefcombo-retrieve-by.xml");

				process.executeProcess(incomingRequest);

				resultList = (List)incomingRequest.get("xrefComboList");
				if (resultList != null && resultList.size() > 0 )
				{
					XrefCombo XrefCombo = (XrefCombo)resultList.get(0);
					result = XrefCombo.getCode4();
				}

			}
			return result;
		}
		catch (Exception e)
		{
			Log.error(this, "Error on method getCode4 "+ e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
}