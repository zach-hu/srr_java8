package com.tsa.puridiom.autogen.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.Map;

public class AutoGenRetrieveByGenYear extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String organizationId = (String)incomingRequest.get("organizationId");
            String userTimeZone = (String)incomingRequest.get("userTimeZone");
			String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "fybegin", "1");

			String genYearAst = "";
			String genYearDsb = "";
			String genYearPo  = "";
			String genYearReq = "";
			String genYearRfq = "";

			if(incomingRequest.containsKey("AutoGenYearAst"))
				genYearAst = (String) incomingRequest.get("AutoGenYearAst");
			else
				genYearAst = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

			if(incomingRequest.containsKey("AutoGenYearDsb"))
				genYearDsb = (String) incomingRequest.get("AutoGenYearDsb");
			else
				genYearDsb = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

			if(incomingRequest.containsKey("AutoGenYearPo"))
				genYearPo = (String) incomingRequest.get("AutoGenYearPo");
			else
				genYearPo = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

			if(incomingRequest.containsKey("AutoGenYearReq"))
				genYearReq = (String) incomingRequest.get("AutoGenYearReq");
			else
				genYearReq = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

			if(incomingRequest.containsKey("AutoGenYearRfq"))
				genYearRfq = (String) incomingRequest.get("AutoGenYearRfq");
			else
				genYearRfq = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

			String queryString = "from AutoGen as autoGen where " +
								 "(autoGen.id.documentType = 'AST' and autoGen.id.genYear = ? ) or " +
								 "(autoGen.id.documentType = 'DSB' and autoGen.id.genYear = ? ) or " +
								 "(autoGen.id.documentType = 'PO'  and autoGen.id.genYear = ? ) or " +
								 "(autoGen.id.documentType = 'REQ' and autoGen.id.genYear = ? ) or " +
								 "(autoGen.id.documentType = 'REQM' and autoGen.id.genYear = ? ) or " +
								 "(autoGen.id.documentType = 'RFQ' and autoGen.id.genYear = ? )";
			result = dbs.query(queryString, new Object[] {genYearAst, genYearDsb, genYearPo, genYearReq, genYearReq, genYearRfq}, new Type[] {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING});

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}