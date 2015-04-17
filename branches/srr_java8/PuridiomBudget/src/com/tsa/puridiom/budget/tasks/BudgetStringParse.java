package com.tsa.puridiom.budget.tasks;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class BudgetStringParse extends Task	{

    ArrayList			errorList = new ArrayList() ;
    DBSession dbs = null ;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

//		 Table of Action Types
//		  1	-	Forward REQ
//	 	  2	-	Reject REQ
//	 	  3	-	Delete or Cancel REQ
//		  4	-	Award PO
//		  5	-	Cancel PO
//		  6	-	Expense PO
//		  7	-	Manual Expense

        String organizationId = (String)incomingRequest.get("organizationId");
        String	userId = (String) incomingRequest.get("userId");

        // Parameters
        String accs = (String) incomingRequest.get("accountString") ;
        String sep = (String) incomingRequest.get("accountSeparator") ;
		String		udfArray[] = (String[]) incomingRequest.get("budgetUdfArray") ;
		String		labelArray[] = (String[]) incomingRequest.get("budgetLabelArray") ;
		String		commodity = (String) incomingRequest.get("commodity") ;
		if ( commodity == null) commodity = "" ;

		int	budgetColumns = Integer.parseInt((String) incomingRequest.get("budgetColumns")) ;


        String s_sep = " " + sep + " " ;
    	StringTokenizer   valueEnum = new StringTokenizer( accs, s_sep );

    	String sVal[] = new String[16];
    	int	p = 0;
    	for( ; valueEnum.hasMoreElements(); )
    		{
    			sVal[p] = valueEnum.nextToken(s_sep) ;
    			p++ ;
    		}

    	int numBudgetFields = 15;
		String[] budgetKey = new String[numBudgetFields];
		for (int ib = 0; ib < budgetColumns; ib++)
		{
			String ls_field = udfArray[ib] ;
			
			if (Utility.isEmpty(ls_field)) { ls_field = ""; }
			//for the puridiom version we are skipping this check.. fields are already named fld.
			//might need to be changed in the future.
			if(ls_field.indexOf("Fld") == 0 || ls_field.indexOf("Comm") ==0 )
			{
				String		ls_data = "" ;
				if (ls_field.startsWith("Comm")) {
					ls_data = commodity ;
				} else {
					int ptr = Integer.parseInt(ls_field.substring(3))  ;
					ls_data = sVal[ptr - 1];
				}
				if(Utility.isEmpty(ls_data))  ls_data = " " ;
				incomingRequest.put("budget_label" + Integer.toString(ib + 1),labelArray[ib]) ;
				incomingRequest.put("BudgetCenter_budget" + Integer.toString(ib + 1),ls_data) ;
				budgetKey[ib] = ls_data.trim();
			}
		}
		incomingRequest.put("budgetKey", budgetKey) ;

		this.setStatus(Status.SUCCEEDED);

	 return accs ;

	}
	
}
