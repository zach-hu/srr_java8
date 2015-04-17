package com.tsa.puridiom.xrefcombo.tasks;

import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.RequisitionHeader;
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

public class AutoAccountingCapitalHeaderRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String failed = "N";

		try
        {
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");

	        List accountList  = (List) incomingRequest.get("accounts");
	    	RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");

	    	for (Iterator it = accountList.iterator(); it.hasNext(); )
		    {
        		Account account = (Account) it.next();

				String fld1 = account.getFld1(); //	Entity			AC01		CPTL(code2)
				String fld2 = account.getFld2(); //	Department		AC02		CPTL(code3)
				String fld3 = account.getFld3(); //	Account			AC03		CPTL(code4)
				String fld4 = account.getFld4(); //	Event(CAR)		AC04		CARP(code3)
				String fld5 = account.getFld5(); //	Project			AC05		CARP(code2)
				String fld6 = account.getFld6(); //	Program			AC06		CARP(code4)

				String queryString = "";
				List resultList = null;

				/* same check as browses */
//				queryString = "Select count(*) from XrefCombo as XrefCombo, StdTable as StdTable where XrefCombo.xrefType = 'CPTL' AND StdTable.id.tableType = 'AC01' " +
//				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code2 = ? AND XrefCombo.code2 = StdTable.id.tableKey ";
//				resultList = dbs.query(queryString, new Object[] {fld1} , new Type[] { Hibernate.STRING}) ;
//				if (resultList == null || resultList.size() < 1 || ((Integer)resultList.get(0)).intValue() < 1 )
//				{
//					failed = "Y";
//					return failed;
//				}
//				queryString = "Select count(*) from XrefCombo as XrefCombo, StdTable as StdTable where XrefCombo.xrefType = 'CPTL' AND StdTable.id.tableType = 'AC02' " +
//				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code3 = ? AND XrefCombo.code3 = StdTable.id.tableKey ";
//				resultList = dbs.query(queryString, new Object[] {fld2} , new Type[] { Hibernate.STRING}) ;
//				if (resultList == null || resultList.size() < 1 || ((Integer)resultList.get(0)).intValue() < 1 )
//				{
//					failed = "Y";
//					return failed;
//				}

//				queryString = "Select count(*) from XrefCombo as XrefCombo, StdTable as StdTable where XrefCombo.xrefType = 'CPTL' AND StdTable.id.tableType = 'AC03' " +
//				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code4 = ? AND XrefCombo.code4 = StdTable.id.tableKey ";
//				resultList = dbs.query(queryString, new Object[] {fld3} , new Type[] { Hibernate.STRING}) ;
//				if (resultList == null || resultList.size() < 1 || ((Integer)resultList.get(0)).intValue() < 1 )
//				{
//					failed = "Y";
//					return failed;
//				}

				queryString = "Select count(*) from XrefCombo as XrefCombo, StdTable as StdTable where XrefCombo.xrefType = 'CARP' AND StdTable.id.tableType = 'AC04' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code3 = ? AND XrefCombo.code3 = StdTable.id.tableKey ";
				resultList = dbs.query(queryString, new Object[] {fld4} , new Type[] { Hibernate.STRING}) ;
				if (resultList == null || resultList.size() < 1 || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					failed = "Y";
					return failed;
				}

				queryString = "Select count(*) from XrefCombo as XrefCombo, StdTable as StdTable where XrefCombo.xrefType = 'CARP' AND StdTable.id.tableType = 'AC05' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code2 = ? AND XrefCombo.code2 = StdTable.id.tableKey ";
				resultList = dbs.query(queryString, new Object[] {fld5} , new Type[] { Hibernate.STRING}) ;
				if (resultList == null || resultList.size() < 1 || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					failed = "Y";
					return failed;
				}

				queryString = "Select count(*) from XrefCombo as XrefCombo, StdTable as StdTable where XrefCombo.xrefType = 'CARP' AND StdTable.id.tableType = 'AC06' " +
				"AND XrefCombo.status = '02' AND StdTable.status = '02' AND XrefCombo.code4 = ? AND XrefCombo.code4 = StdTable.id.tableKey ";
				resultList = dbs.query(queryString, new Object[] {fld6} , new Type[] { Hibernate.STRING}) ;
				if (resultList == null || resultList.size() < 1 || ((Integer)resultList.get(0)).intValue() < 1 )
				{
					failed = "Y";
					return failed;
				}

				/* check neccesary combinations */

				// check CPTL table 	Entity(AC01/code2) Dept(AC02/code3) Account(AC03/code4)
				queryString = "Select count(*) from XrefCombo as XrefCombo where XrefCombo.xrefType = 'CPTL' AND XrefCombo.status = '02' " +
						"AND XrefCombo.code2 = ? AND XrefCombo.code3 = ? AND XrefCombo.code4 = ? ";
				resultList = dbs.query(queryString, new Object[] {fld1, fld2, fld3} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
				if (resultList == null || resultList.size() < 1 || ((Integer)resultList.get(0)).intValue() < 1 )
    			{
					incomingRequest.put("CPTL-capital-header", "failed");
    			}

				// check CARP table 	Division(departmentCodeHeader - code1)	CAR(AC04/code3) Project(AC05-code2) Program(AC06-code4)
				queryString = "Select count(*) from XrefCombo as XrefCombo where XrefCombo.xrefType = 'CARP' AND XrefCombo.status = '02' " +
						"AND XrefCombo.code1 = ? " +
						"AND XrefCombo.code3 = ? AND XrefCombo.code2 = ? AND XrefCombo.code4 = ? ";
				resultList = dbs.query(queryString, new Object[] {requisitionHeader.getDepartmentCode(), fld4, fld5, fld6} , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
				if (resultList == null || resultList.size() < 1 || ((Integer)resultList.get(0)).intValue() < 1 )
    			{
    				failed = "Y";
    				return failed;
    			}
		    }
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at AutoAccountingCapitalRules", e);
		}
		return failed;
    }
}