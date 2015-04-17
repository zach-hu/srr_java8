/*
 * Created on Jan 13, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.tsa.puridiom.po.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PoLoadAcctDflts extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			
			PropertiesManager pm =PropertiesManager.getInstance((String)incomingRequest.get("companyId"));
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			String	userId = (String) incomingRequest.get("userId") ;
			UserManager userManager = UserManager.getInstance() ;
			UserProfile user = userManager.getUser(organizationId, userId) ;
			
			incomingRequest.put("Account_headerIc", incomingRequest.get("PoHeader_icPoHeader"));
			incomingRequest.put("Account_lineIc", "0");
			incomingRequest.put("Account_sequence", "1");
			incomingRequest.put("Account_accountType", "POH");
						 
			String fieldUdf1 = pm.getProperty("ACCT NAME UDFS", "UDF1", "");
			this.acctVerify(fieldUdf1, user.getNameUdf1(), incomingRequest);
			String fieldUdf2 = pm.getProperty("ACCT NAME UDFS", "UDF2", "");
			this.acctVerify(fieldUdf2, user.getNameUdf2(), incomingRequest);
			String fieldUdf3 = pm.getProperty("ACCT NAME UDFS", "UDF2", "");
			this.acctVerify(fieldUdf3, user.getNameUdf3(), incomingRequest);
			String fieldUdf4 = pm.getProperty("ACCT NAME UDFS", "UDF4", "");
			this.acctVerify(fieldUdf4, user.getNameUdf4(), incomingRequest);
			String fieldUdf5 = pm.getProperty("ACCT NAME UDFS", "UDF5", "");
			this.acctVerify(fieldUdf5, user.getNameUdf5(), incomingRequest);
			String dept = pm.getProperty("ACCT NAME UDFS", "DEPT", "");
			this.acctVerify(dept, user.getDepartment(), incomingRequest);
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
	
	private void acctVerify(String udfFld, String value, Map incomingRequest)
	{
	    
		List  validFlds = new ArrayList();
		validFlds.add("fld1");
		validFlds.add("fld2");
		validFlds.add("fld3");
		validFlds.add("fld4");
		validFlds.add("fld5");
	    validFlds.add("fld6");
        validFlds.add("fld7");
        validFlds.add("fld8");
        validFlds.add("fld9");
        validFlds.add("fld10");
        validFlds.add("fld11");
        validFlds.add("fld12");
        validFlds.add("fld13");
        validFlds.add("fld14");
        validFlds.add("fld15");
		
        if(!Utility.isEmpty(udfFld))
        {
            if(!udfFld.equalsIgnoreCase("[NONE]"))
            {
                if(validFlds.contains(udfFld))
                {
                    incomingRequest.put("Account_" + udfFld, value);
                }
            }
        }
		
	}
}
