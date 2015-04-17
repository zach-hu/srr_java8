package com.tsa.puridiom.account.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AccountSetProjectCode extends Task	{
		public Object  executeTask (Object object) throws Exception

	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			List accountList = (List) incomingRequest.get("accountList");

			if (accountList != null) {
				if (accountList.size() > 0) {

					Iterator it = accountList.iterator() ;
					String s_project_code = Utility.ckNull(reqHeader.getUdf1Code());

					while (it.hasNext())
					{
						Account ac = (Account) it.next() ;
						ac.setFld4(s_project_code);
					}
				}
			}

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result ;

	}
}
