/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator
 */
public class AccountRetrieveByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("Account_icHeader");
		String accountPref = "";

		BigDecimal bdh = new BigDecimal(icHeader) ;

//      	String queryString = "from Account as a where a.id.icHeader = ? order by Account.accountType, order by Account.comp_id.sequence";
      	String queryString = "from Account as a where a.id.icHeader = ? order by a.accountType, a.comp_id.sequence";

		List result = dbs.query(queryString,
					bdh,
					Hibernate.BIG_DECIMAL) ;

		if(result != null && !result.isEmpty())
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			String formType = (String)incomingRequest.get("formtype");

			String startAt = PropertiesManager.getInstance(organizationId).getProperty("AUTONUMBER", "STARTAT", "3");
            String length = PropertiesManager.getInstance(organizationId).getProperty("AUTONUMBER", "LENGTH", "1");

            if (formType == null) formType = "" ;
            Account account = (Account)result.get(0);
			account.getComp_id().getSequence();
			accountPref = account.getFld1();

			if(!HiltonUtility.isEmpty(accountPref)){
				int start = Integer.parseInt(startAt) - 1;
				int lengthInt = Integer.parseInt(length);
				int endAt = start+lengthInt;
				int accountLength = accountPref.length();

				if (endAt <= accountLength)
				{
					//if(!HiltonUtility.isEmpty(accountPref)){

					accountPref = accountPref.substring(start,endAt);
					if (formType.equalsIgnoreCase("REQ")) {
			            String	prefix = PropertiesManager.getInstance(organizationId).getProperty("AUTONUMBER", "REQACCPREFIX", "");
						accountPref = prefix + accountPref ;
					}
					//}
				}
				else
				{
					accountPref = "ERROR: FLD1 must be at least "+ endAt +" characters";
				}
			}
		}
		incomingRequest.put("accountPref", accountPref);
		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}
