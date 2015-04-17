/*
 * Created on July 17, 2008
 */
package com.tsa.puridiom.account.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Kelli
 */
public class AccountPopulateAllocationDescriptionByAllocList extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
     *   Retrieves the description of each account segment for all account records in accountList
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String organizationId = (String) incomingRequest.get("organizationId");
            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
            String accountDescElements = propertiesManager.getProperty("FORM","ACCOUNTDESCELEMENTS","0");
            String accountSeparator = propertiesManager.getProperty("MISC","AccountSeparator","-");
            List accountList = (List) incomingRequest.get("accountAllocAmountList");
            int ielements = Integer.valueOf(accountDescElements).intValue();

            if (ielements > 0) {
                AccountRetrieveFldDescription retrieve = new AccountRetrieveFldDescription();
                Map requestParameters = new HashMap();

                requestParameters.put("organizationId", organizationId);
                requestParameters.put("userId", incomingRequest.get("userId"));
                requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
                requestParameters.put("dbsession", incomingRequest.get("dbsession"));

                for (int i = 0; i < accountList.size(); i++) {
                    Object obj[] = (Object[]) accountList.get(i);
                    Object newObj[] = new Object[17];
                    StringBuffer allocationDescription = new StringBuffer("");

                    for (int ie = 1; ie < obj.length; ie ++)
                    {
                        String code = (String) obj[ie];

                        newObj[ie] = code;

                        if (ie <= ielements) {
                            String fld = "";

                            if (ie < 10) {
                                fld = "0" + String.valueOf(ie);
                            } else {
                                fld = String.valueOf(ie);
                            }

                            requestParameters.put("accountCode", code);
                            requestParameters.put("accountFld", fld);

                            String description = (String) retrieve.executeTask(requestParameters);

                            allocationDescription = this.addFldDescription(allocationDescription, code, description, accountSeparator);
                        }
                    }

                    newObj[0] = obj[0];
                    newObj[16] = allocationDescription.toString();

                    accountList.set(i, newObj);
                }
            }

            result = accountList;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Account Allocation Descriptions could not be retrieved! [" + e.getMessage() + "]", e);
		}

		return result ;
	}

    private StringBuffer addFldDescription(StringBuffer temp, String fld, String fldDescription, String accountSeparator) {
        if(HiltonUtility.isEmpty(fldDescription)) {
            fldDescription = fld;
        }

        if(!HiltonUtility.isEmpty(fldDescription)) {
            if (!HiltonUtility.isEmpty(temp.toString())) {
                temp.append(accountSeparator);
            }
            temp.append(fldDescription);
        }
        return temp;
    }
}