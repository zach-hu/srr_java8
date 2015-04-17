package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AccountGljbCrossFromReqRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String failed = "N";
		String statusLine = "succeeded";
		String statusHeader = "succeeded";

		try
        {
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	    	List requisitionLineList = (List) incomingRequest.get("requisitionLineList");


	        for (Iterator it = requisitionLineList.iterator(); it.hasNext(); )
	        {
	        	RequisitionLine requisitionLine = (RequisitionLine) it.next();
	        	List accountList = requisitionLine.getAccountList();

	        	for (Iterator it2 = accountList.iterator(); it2.hasNext(); )
			    {
	        		Account account = (Account) it2.next();

					String fld1 = account.getFld1(); //	Entity			AC01
					//String fld2 = account.getFld2(); //	GL Account		AC02
					String fld3 = account.getFld3(); //	Job Number		AC03

					if (!HiltonUtility.isEmpty(fld3))
					{
						String queryString = "";
						List resultList = null;

						queryString = "from StdTable as StdTable where StdTable.id.tableType = 'AC03' and StdTable.id.tableKey = ? " +
						"AND StdTable.status = '02' ";

						resultList = dbs.query(queryString, new Object[] {fld3} , new Type[] { Hibernate.STRING}) ;
	        			if (resultList == null || resultList.size() < 1)
	        			{
	        				incomingRequest.put("accountLineJobNumber", "failed");
	        			}

						// check GLJB table 	Entity(AC01/code1) Job Number(AC03/code2)
						queryString = "from XrefCombo as XrefCombo where XrefCombo.xrefType = 'GLJB' AND XrefCombo.status = '02' " +
								"AND XrefCombo.code1 = ? AND XrefCombo.code2 = ? ";

						resultList = dbs.query(queryString, new Object[] {fld1, fld3} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
	        			if (resultList == null || resultList.size() < 1)
	        			{
	        				incomingRequest.put("accountLineGLJBCross", "failed");
	        			}
					}
				}
	        }
	        

	        RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
	    	List accountHeaderList = (List) reqHeader.getAccountList();

	        for (Iterator it3 = accountHeaderList.iterator(); it3.hasNext(); )
			{
	        	Account account = (Account) it3.next();

				String fld1 = account.getFld1(); //	Entity			AC01
				//String fld2 = account.getFld2(); //	GL Account		AC02
				String fld3 = account.getFld3(); //	Job Number		AC03

				String queryString = "";
				List resultList = null;

				// check GLJB table 	Entity(AC01/code1) Job Number(AC03/code2)
				queryString = "from XrefCombo as XrefCombo where XrefCombo.xrefType = 'GLJB' AND XrefCombo.status = '02' " +
						"AND XrefCombo.code1 = ? AND XrefCombo.code2 = ? ";

				resultList = dbs.query(queryString, new Object[] {fld1, fld3} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
       			if (resultList == null || resultList.size() < 1)
       			{
					statusHeader = "failed";
					break;
       			}
			}

	        incomingRequest.put("accountHeaderGLJBCross", statusHeader);

	        this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "n Error occurred at GLJBCrossRules" + e);
            throw new TsaException("An Error occurred at GLJBCrossRules", e);
		}
		return failed;
    }
}