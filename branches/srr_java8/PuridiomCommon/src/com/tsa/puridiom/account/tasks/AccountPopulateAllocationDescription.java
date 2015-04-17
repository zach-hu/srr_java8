/*
 * Created on July 17, 2008
 */
package com.tsa.puridiom.account.tasks;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Kelli
 */
public class AccountPopulateAllocationDescription extends Task {
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
            Account account = (Account) incomingRequest.get("account");
            int ielements = Integer.valueOf(accountDescElements).intValue();

            StringBuffer allocationDescription = new StringBuffer("");
            AccountRetrieveFldDescription retrieve = new AccountRetrieveFldDescription();
            Map requestParameters = new HashMap();

            requestParameters.put("organizationId", organizationId);
            requestParameters.put("userId", incomingRequest.get("userId"));
            requestParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
            requestParameters.put("dbsession", incomingRequest.get("dbsession"));

            for (int ie = 1; ie <= ielements; ie++) {
                String code = this.getAccountFldValue(account, String.valueOf(ie));
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

            account.setAllocationDescription(allocationDescription.toString());

            result = account;

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Accounts could not be retrieved! ", e);
		}

		return result;
	}

    private String getAccountFldValue(Account account, String fldNumber) {
        String fldValue = "";

        try {
            String methodName = "getFld" + fldNumber;

            Method method = account.getClass().getMethod(methodName, null);

            fldValue = (String) method.invoke(account, new Class[]{});
        }
        catch (Exception e) {
        }

        return fldValue;
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