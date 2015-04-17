package com.tsa.puridiom.xrefcombo.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;
public class XrefComboRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from XrefCombo as xrefcombo where 1=1 ");

		if (incomingRequest.containsKey("XrefCombo_icXref"))
		{
			String icXref = (String) incomingRequest.get("XrefCombo_icXref");
			queryString.append(" AND xrefcombo.icXref = '" + icXref + "'");


		}
		if (incomingRequest.containsKey("XrefCombo_xrefType"))
		{
			String xrefType = (String) incomingRequest.get("XrefCombo_xrefType");
			queryString.append(" AND xrefcombo.xrefType = '" + xrefType + "'");

		}
		if (incomingRequest.containsKey("XrefCombo_code1"))
		{
			String code1 = (String) incomingRequest.get("XrefCombo_code1");
			queryString.append(" AND xrefcombo.code1 = '" + code1 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code2"))
		{
			String code2 = (String) incomingRequest.get("XrefCombo_code2");
			queryString.append(" AND xrefcombo.code2 = '" + code2 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code3"))
		{
			String code3 = (String) incomingRequest.get("XrefCombo_code3");
			queryString.append(" AND xrefcombo.code3 = '" + code3 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code4"))
		{
			String code4 = (String) incomingRequest.get("XrefCombo_code4");
			queryString.append(" AND xrefcombo.code4 = '" + code4 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code5"))
		{
			String code5 = (String) incomingRequest.get("XrefCombo_code5");
			queryString.append(" AND xrefcombo.code5 = '" + code5 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code6"))
		{
			String code6 = (String) incomingRequest.get("XrefCombo_code6");
			queryString.append(" AND xrefcombo.code6 = '" + code6 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code7"))
		{
			String code7 = (String) incomingRequest.get("XrefCombo_code7");
			queryString.append(" AND xrefcombo.code7 = '" + code7 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code8"))
		{
			String code8 = (String) incomingRequest.get("XrefCombo_code8");
			queryString.append(" AND xrefcombo.code8 = '" + code8 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code9"))
		{
			String code9 = (String) incomingRequest.get("XrefCombo_code9");
			queryString.append(" AND xrefcombo.code9 = '" + code9 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code10"))
		{
			String code10 = (String) incomingRequest.get("XrefCombo_code10");
			queryString.append(" AND xrefcombo.code10 = '" + code10 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code11"))
		{
			String code11 = (String) incomingRequest.get("XrefCombo_code11");
			queryString.append(" AND xrefcombo.code11 = '" + code11 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code12"))
		{
			String code12 = (String) incomingRequest.get("XrefCombo_code12");
			queryString.append(" AND xrefcombo.code12 = '" + code12 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code13"))
		{
			String code13 = (String) incomingRequest.get("XrefCombo_code13");
			queryString.append(" AND xrefcombo.code13 = '" + code13 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code14"))
		{
			String code14 = (String) incomingRequest.get("XrefCombo_code14");
			queryString.append(" AND xrefcombo.code14 = '" + code14 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_code15"))
		{
			String code15 = (String) incomingRequest.get("XrefCombo_code15");
			queryString.append(" AND xrefcombo.code15 = '" + code15 + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_xrefInd"))
		{
			String xrefInd = (String) incomingRequest.get("XrefCombo_xrefInd");
			queryString.append(" AND xrefcombo.xrefInd = '" + xrefInd + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_xrefAmt"))
		{
			String xrefAmt = (String) incomingRequest.get("XrefCombo_xrefAmt");
			queryString.append(" AND xrefcombo.xrefAmt = '" + xrefAmt + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_dateEntered"))
		{
			String dateEntered = (String) incomingRequest.get("XrefCombo_dateEntered");
			queryString.append(" AND xrefcombo.dateEntered = '" + dateEntered + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_dateExpires"))
		{
			String dateExpires = (String) incomingRequest.get("XrefCombo_dateExpires");
			queryString.append(" AND xrefcombo.dateExpires = '" + dateExpires + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_status"))
		{
			String status = (String) incomingRequest.get("XrefCombo_status");
			queryString.append(" AND xrefcombo.status = '" + status + "'");
		}
		if (incomingRequest.containsKey("XrefCombo_owner"))
		{
			String owner = (String) incomingRequest.get("XrefCombo_owner");
			queryString.append(" AND xrefcombo.owner = '" + owner + "'");
		}

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}