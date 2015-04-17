/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.*;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;
/**
 * @author Administrator
 */
public class BudgetUdfSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

        String oid = (String)incomingRequest.get("organizationId");

		String udfArray[] = new String[15];
		String labelArray[] = new String[15];

		String	sUdf	= null ;
		String	sLabel = null ;
		int budCount = 0 ;
		for(int li = 0; li < 15; li++)
		{
			sUdf = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Budget Udf" + String.valueOf(li + 1),"BUDGET_" + String.valueOf(li + 1) );
			sLabel = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Label Udf" + String.valueOf(li + 1),"BUDGET_" + String.valueOf(li + 1) );
			if (sUdf.startsWith("Fld") || sUdf.startsWith("Commodity")) {
				budCount++ ;
			} else {
				sUdf = "" ;
				sLabel = "" ;
			}
			udfArray[li] = sUdf;
			labelArray[li] = sLabel;
		}

		incomingRequest.put("budgetUdfArray",udfArray) ;
		incomingRequest.put("budgetLabelArray", labelArray) ;
		incomingRequest.put("budgetColumns", Integer.toString(budCount)) ;

		String	sAuthority = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Authority", "Department");
		if(Utility.isEmpty(sAuthority))	sAuthority = "Department";
		incomingRequest.put("budgetAuthority",sAuthority) ;

		String 	sAuthorityUdf = PropertiesManager.getInstance(oid).getProperty("BUDGET", "Authority_Udf", "00").trim();
		// pad left with 0
		sAuthorityUdf = Integer.valueOf(sAuthorityUdf).toString();
		incomingRequest.put("budgetAuthorityUdf",sAuthorityUdf) ;
		incomingRequest.put("budgetAuthorityUdfField","fld_" + sAuthorityUdf) ;

		String sAccSep = PropertiesManager.getInstance(oid).getProperty("MISC", "ACCOUNTSEPARATOR", "-");
		incomingRequest.put("accountSeparator",sAccSep);


		return null ;
	}
}
