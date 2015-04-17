package com.tsa.puridiom.budgetcenter.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class BudgetCenterRetrieveWithWildCards extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

		
		List wildcardList = (List)incomingRequest.get("wildcardList");
	    String[] budgetValues = (String[])incomingRequest.get("budgetKey");
	    List budgetList = new ArrayList();
		
	    try
	    {
		
	    boolean budgetFound = false;
	    boolean valueFound = false;
	    BudgetCenter budgetCenter = null;
	    for(int i = 0; i < wildcardList.size(); i++ )
		{
			BudgetCenter wildcardBudget = (BudgetCenter)wildcardList.get(i);
			for(int valueIndex = 0; valueIndex < budgetValues.length; valueIndex++)
			{
				String value = budgetValues[valueIndex];
				if(!HiltonUtility.isEmpty(value))
				{
					String wildValue = this.getValueFromBudget(wildcardBudget, valueIndex);
					if(wildValue.equalsIgnoreCase("*") || wildValue.equalsIgnoreCase(value))
					{
						valueFound = true;
					}
					else
					{
						valueFound = false;
						valueIndex = budgetValues.length;
					}
				}
			}
			//we found a budget that matches all of the values from the key provided
			if(valueFound)
			{
				budgetFound = true;
				i = wildcardList.size();
				budgetCenter = wildcardBudget;
			}
		}//end wildcardList for

	    if (budgetCenter!=null)
	    {
	    	budgetList.add(budgetCenter);
	    }

		this.setStatus(Status.SUCCEEDED);
	    
	    }
	    catch (Exception e) {
	    	this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at DispositionCodeRecLineRules" + e);
			e.printStackTrace();
            throw e; 
		}
		return budgetList;
	}
	
	private String getValueFromBudget(BudgetCenter budgetCenter, int fldIndex)
	{
		String value = "";
		switch (fldIndex) {
		case 0:
			value = budgetCenter.getBudget1();
			break;
		case 1:
			value = budgetCenter.getBudget2();
			break;
		case 2:
			value = budgetCenter.getBudget3();
			break;
		case 3:
			value = budgetCenter.getBudget4();
			break;
		case 4:
			value = budgetCenter.getBudget5();
			break;
		case 5:
			value = budgetCenter.getBudget6();
			break;
		case 6:
			value = budgetCenter.getBudget7();
			break;
		case 7:
			value = budgetCenter.getBudget8();
			break;
		case 8:
			value = budgetCenter.getBudget9();
			break;
		case 9:
			value = budgetCenter.getBudget10();
			break;
		case 10:
			value = budgetCenter.getBudget11();
			break;
		case 11:
			value = budgetCenter.getBudget12();
			break;
		case 12:
			value = budgetCenter.getBudget13();
			break;
		case 13:
			value = budgetCenter.getBudget14();
			break;
		case 14:
			value = budgetCenter.getBudget15();
			break;

		default:
			break;
		}
		return value;
	}

	
}