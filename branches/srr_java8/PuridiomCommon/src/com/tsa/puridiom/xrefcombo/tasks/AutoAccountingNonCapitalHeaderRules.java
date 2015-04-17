package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AutoAccountingNonCapitalHeaderRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String failed = "N";

		try
        {
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        List accountList  = (List) incomingRequest.get("accounts");

	    	for (Iterator it = accountList.iterator(); it.hasNext(); )
		    {
        		Account account = (Account) it.next();

				String fld1 = account.getFld1(); //	Entity			AC01		ENDP(code2)/CMOD(code3)/DIVS(code2)
				String fld2 = account.getFld2(); //	Department		AC02		ENDP(code3)
				String fld3 = account.getFld3(); //	Account			AC03		CMOD(code4)
				String fld4 = account.getFld4(); //	Event			AC04(*)		DIVS(code5)
				String fld5 = account.getFld5(); //	Performance		AC05(*)		DIVS(code6)
				String fld6 = account.getFld6(); //	Program			AC06(*)		DIVS(code7)

				String queryString = "";
				List resultList = null;

				/* same check as browses easy */
				queryString = "Select count(*) from StdTable as StdTable, XrefCombo as XrefCombo where XrefCombo.xrefType = 'ENDP' AND StdTable.id.tableType = 'AC01' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code2 = ? AND XrefCombo.code2 = StdTable.id.tableKey ";
				resultList = dbs.query(queryString, new Object[] {fld1} , new Type[] { Hibernate.STRING}) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					incomingRequest.put("AC01-nocapital-header", "failed");
				}
				queryString = "Select count(*) from StdTable as StdTable, XrefCombo as XrefCombo where XrefCombo.xrefType = 'ENDP' AND StdTable.id.tableType = 'AC02' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code3 = ? AND XrefCombo.code3 = StdTable.id.tableKey ";
				resultList = dbs.query(queryString, new Object[] {fld2} , new Type[] { Hibernate.STRING}) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					incomingRequest.put("AC02-nocapital-header", "failed");
				}

				/*queryString = "Select count(*) from StdTable as StdTable, XrefCombo as XrefCombo where XrefCombo.xrefType = 'CMOD' AND StdTable.id.tableType = 'AC03' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code4 = ? AND XrefCombo.code4 = StdTable.id.tableKey ";
				resultList = dbs.query(queryString, new Object[] {fld3} , new Type[] { Hibernate.STRING}) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					incomingRequest.put("AC03-nocapital-header", "failed");
				}*/

				queryString = "Select count(*) from StdTable as StdTable, XrefCombo as XrefCombo where XrefCombo.xrefType = 'DIVS' AND StdTable.id.tableType = 'AC04' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND StdTable.id.tableKey = ? AND " +
				"( XrefCombo.code5 = StdTable.id.tableKey " +
				" OR (XrefCombo.code5 like '*') " +
				" OR (XrefCombo.code5 like '_*' AND substr(StdTable.id.tableKey, 1, 1) = substr(XrefCombo.code5, 1, 1) ) " +
				" OR (XrefCombo.code5 like '__*' AND substr(StdTable.id.tableKey, 1, 2) = substr(XrefCombo.code5, 1, 2) ) " +
				" OR (XrefCombo.code5 like '___*' AND substr(StdTable.id.tableKey, 1, 3) = substr(XrefCombo.code5, 1, 3) ) " +
				" OR (XrefCombo.code5 like '____*' AND substr(StdTable.id.tableKey, 1, 4) = substr(XrefCombo.code5, 1, 4) ) " +
				" OR (XrefCombo.code5 like '_____*' AND substr(StdTable.id.tableKey, 1, 5) = substr(XrefCombo.code5, 1, 5) ) ) ";
				resultList = dbs.query(queryString, new Object[] {fld4} , new Type[] { Hibernate.STRING}) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					incomingRequest.put("AC04-nocapital-header", "failed");
				}

				queryString = "Select count(*) from StdTable as StdTable, XrefCombo as XrefCombo where XrefCombo.xrefType = 'DIVS' AND StdTable.id.tableType = 'AC05' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND StdTable.id.tableKey = ? AND " +
				"( XrefCombo.code6 = StdTable.id.tableKey " +
				" OR (XrefCombo.code6 like '*') " +
				" OR (XrefCombo.code6 like '_*' AND substr(StdTable.id.tableKey, 1, 1) = substr(XrefCombo.code6, 1, 1) ) " +
				" OR (XrefCombo.code6 like '__*' AND substr(StdTable.id.tableKey, 1, 2) = substr(XrefCombo.code6, 1, 2) ) " +
				" OR (XrefCombo.code6 like '___*' AND substr(StdTable.id.tableKey, 1, 3) = substr(XrefCombo.code6, 1, 3) ) " +
				" OR (XrefCombo.code6 like '____*' AND substr(StdTable.id.tableKey, 1, 4) = substr(XrefCombo.code6, 1, 4) ) " +
				" OR (XrefCombo.code6 like '_____*' AND substr(StdTable.id.tableKey, 1, 5) = substr(XrefCombo.code6, 1, 5) ) " +
				" OR (XrefCombo.code6 like '______*' AND substr(StdTable.id.tableKey, 1, 6) = substr(XrefCombo.code6, 1, 6) ) ) ";
				resultList = dbs.query(queryString, new Object[] {fld5} , new Type[] { Hibernate.STRING}) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					incomingRequest.put("AC05-nocapital-header", "failed");
				}

				queryString = "Select count(*) from StdTable as StdTable, XrefCombo as XrefCombo where XrefCombo.xrefType = 'DIVS' AND StdTable.id.tableType = 'AC06' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND StdTable.id.tableKey = ? AND " +
				"( XrefCombo.code7 = StdTable.id.tableKey " +
				" OR (XrefCombo.code7 like '*') " +
				" OR (XrefCombo.code7 like '_*' AND substr(StdTable.id.tableKey, 1, 1) = substr(XrefCombo.code7, 1, 1) ) " +
				" OR (XrefCombo.code7 like '__*' AND substr(StdTable.id.tableKey, 1, 2) = substr(XrefCombo.code7, 1, 2) ) " +
				" OR (XrefCombo.code7 like '___*' AND substr(StdTable.id.tableKey, 1, 3) = substr(XrefCombo.code7, 1, 3) ) ) ";
				resultList = dbs.query(queryString, new Object[] {fld6} , new Type[] { Hibernate.STRING}) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					incomingRequest.put("AC06-nocapital-header", "failed");
				}


				/* check neccesary combinations */

				// check ENDP table 	Entity(AC01/code2) Dept(AC02/code3)
				queryString = "Select count(*) from XrefCombo as XrefCombo where XrefCombo.xrefType = 'ENDP' AND XrefCombo.status = '02' " +
						"AND XrefCombo.code2 = ? AND XrefCombo.code3 = ? ";
				resultList = dbs.query(queryString, new Object[] {fld1, fld2} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
    			{
					incomingRequest.put("ENDP-nocapital-header", "failed");
    			}

				// check CMOD table 	Entity(AC01/code2) Account(AC03-code4) Commodity(comm line item -code3)
				/*queryString = "Select count(*) from XrefCombo as XrefCombo where XrefCombo.xrefType = 'CMOD' AND XrefCombo.status = '02' " +
						"AND XrefCombo.code2 = ? AND XrefCombo.code4 = ? AND XrefCombo.code3 = ? ";
				resultList = dbs.query(queryString, new Object[] {fld1, fld3, requisitionLine.getCommodityCode()} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
    			{
    				incomingRequest.put("CMOD-nocapital", "failed");
    			}*/

				// check DIVS table 	Entity(AC01/code2)	Dept(AC02/code3) Account(AC03/code4)	Event(AC04/code5) Performance(AC05-code6) Program(AC06-code7)
				queryString = "Select count(*) from XrefCombo as XrefCombo where XrefCombo.xrefType = 'DIVS' AND XrefCombo.status = '02' " +
						"AND XrefCombo.code2 = ? " +
						"AND ( XrefCombo.code3 = ? " +
						" OR (XrefCombo.code3 = '*' ) " +
						" OR (XrefCombo.code3 like '_*' AND substr(?, 1, 1) = substr(XrefCombo.code3, 1, 1) ) " +
						" OR (XrefCombo.code3 like '__*' AND substr(?, 1, 2) = substr(XrefCombo.code3, 1, 2) ) " +
						" OR (XrefCombo.code3 like '___*' AND substr(?, 1, 3) = substr(XrefCombo.code3, 1, 3) ) " +
						" OR (XrefCombo.code3 like '____*' AND substr(?, 1, 4) = substr(XrefCombo.code3, 1, 4) ) " +
						" OR (XrefCombo.code3 like '_____*' AND substr(?, 1, 5) = substr(XrefCombo.code3, 1, 5) ) )" +
	        			"AND ( XrefCombo.code4 = ? OR XrefCombo.code4 = '*' ) " +
						"AND ( XrefCombo.code5 = ? " +
						" OR (XrefCombo.code5 like '*')" +
						" OR (XrefCombo.code5 like '_*' AND substr(?, 1, 1) = substr(XrefCombo.code5, 1, 1) ) " +
						" OR (XrefCombo.code5 like '__*' AND substr(?, 1, 2) = substr(XrefCombo.code5, 1, 2) ) " +
						" OR (XrefCombo.code5 like '___*' AND substr(?, 1, 3) = substr(XrefCombo.code5, 1, 3) ) " +
						" OR (XrefCombo.code5 like '____*' AND substr(?, 1, 4) = substr(XrefCombo.code5, 1, 4) ) " +
						" OR (XrefCombo.code5 like '_____*' AND substr(?, 1, 5) = substr(XrefCombo.code5, 1, 5) ) ) " +
						"AND ( XrefCombo.code6 = ? " +
						" OR (XrefCombo.code6 like '*')" +
						" OR (XrefCombo.code6 like '_*' AND substr(?, 1, 1) = substr(XrefCombo.code6, 1, 1) ) " +
						" OR (XrefCombo.code6 like '__*' AND substr(?, 1, 2) = substr(XrefCombo.code6, 1, 2) ) " +
						" OR (XrefCombo.code6 like '___*' AND substr(?, 1, 3) = substr(XrefCombo.code6, 1, 3) ) " +
						" OR (XrefCombo.code6 like '____*' AND substr(?, 1, 4) = substr(XrefCombo.code6, 1, 4) ) " +
						" OR (XrefCombo.code6 like '_____*' AND substr(?, 1, 5) = substr(XrefCombo.code6, 1, 5) ) " +
						" OR (XrefCombo.code6 like '______*' AND substr(?, 1, 6) = substr(XrefCombo.code6, 1, 6) ) ) " +
						"AND ( XrefCombo.code7 = ? " +
						" OR (XrefCombo.code7 like '*')" +
						" OR (XrefCombo.code7 like '_*' AND substr(?, 1, 1) = substr(XrefCombo.code7, 1, 1) ) " +
						" OR (XrefCombo.code7 like '__*' AND substr(?, 1, 2) = substr(XrefCombo.code7, 1, 2) ) " +
						" OR (XrefCombo.code7 like '___*' AND substr(?, 1, 3) = substr(XrefCombo.code7, 1, 3) ) " +
						" OR (XrefCombo.code7 like '____*' AND substr(?, 1, 4) = substr(XrefCombo.code7, 1, 4) ) ) " ;

				resultList = dbs.query(queryString, new Object[] {fld1, fld2, fld3, fld3, fld3, fld3, fld3, fld3, fld4, fld4, fld4, fld4, fld4, fld4,
						fld5, fld5, fld5, fld5, fld5, fld5, fld5, fld6, fld6, fld6, fld6, fld6} ,
						new Type[] { Hibernate.STRING, Hibernate.STRING,
						Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING,
						Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING,
						Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING,
						Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING }) ;
				if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
    			{
    				incomingRequest.put("DIVS-nocapital-header", "failed");
    			}
	        }

        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at AutoAccountingNonCapitalHeaderRules", e);
		}
		return failed;
    }
}