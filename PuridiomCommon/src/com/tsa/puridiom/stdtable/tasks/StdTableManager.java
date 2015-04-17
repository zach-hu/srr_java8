package com.tsa.puridiom.stdtable.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.entity.CurrCode;
import com.tsa.puridiom.entity.Country;
import com.tsa.puridiom.entity.StdTable;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * Creation date: November 2007
 * @author: Nestor Morocco
 */
public class StdTableManager {
	private static HashMap stdTablesManagers = new HashMap();
	private List stdTables;
	private String organizationId;

	public static StdTableManager getInstance(String oid) {
		if (StdTableManager.stdTablesManagers.get(oid) == null) {
		    StdTableManager.stdTablesManagers.put(oid, new StdTableManager(oid));
		}
		return (StdTableManager) StdTableManager.stdTablesManagers.get(oid);
	}

	private StdTableManager(String oid)
	{
	    this.organizationId = oid;
	}

	public List getStdTables(String tableType) {
		List list = new ArrayList();
		try {

		    Map incomingRequest = new HashMap() ;
			incomingRequest.put("organizationId", this.organizationId);
			incomingRequest.put("StdTable_tableType", tableType);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader() ;
			PuridiomProcess process = processLoader.loadProcess("stdtable-retrieve-by.xml");

			process.executeProcess(incomingRequest);
			list = (List)incomingRequest.get("stdTableList");

			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public String getStdTableDescription(String organizationId, String tableType, String tableKey) throws java.lang.Exception
	{
		String description = "";

		try
		{
			StdTable stdTable = null;
			if (!HiltonUtility.isEmpty(organizationId))
			{
				HashMap processParameters = new HashMap();
				processParameters.put("organizationId", organizationId);
				processParameters.put("StdTable_tableType", tableType);
				processParameters.put("StdTable_tableKey",tableKey );

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess("stdtable-retrieve-by-id.xml");

				process.executeProcess(processParameters);

				stdTable = (StdTable) processParameters.get("stdTable");
				if (stdTable != null)
				{
					description = stdTable.getDescription();
				}
			}
			return HiltonUtility.ckNull(description);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}

}
