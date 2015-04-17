package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class PoHeaderUpdateForLastRevision extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbsession = (DBSession)incomingRequest.get("dbsession");	
			String oid = (String)incomingRequest.get("organizationId");
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			
			if(poHeader != null && poHeader.getStatus().compareTo(DocumentStatus.PO_AWARDED) >= 0){
				
				PoHeader previousPoHeader = null;
				BigDecimal releaseNumber = poHeader.getReleaseNumber();
				BigDecimal revisionNumber = poHeader.getRevisionNumber();
				BigDecimal previousRevisionNumber = revisionNumber.subtract(new BigDecimal(1));
				
				String query = "from PoHeader as PoHeader where PoHeader.poNumber = ? AND PoHeader.releaseNumber = ? AND PoHeader.revisionNumber = ?";
				List resultList = dbsession.query(query, new Object[] {poHeader.getPoNumber(),  releaseNumber, previousRevisionNumber} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL});
				if(resultList != null && resultList.size() > 0){
					previousPoHeader = (PoHeader) resultList.get(0);
				}
			
				if(previousPoHeader != null){
					
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader(oid);
					PuridiomProcess process = processLoader.loadProcess("receiptheader-update-po-lastrevision.xml");
					Map updateIncomingRequest = new HashMap();
					
					updateIncomingRequest.put("organizationId", oid);
					updateIncomingRequest.put("userId", incomingRequest.get("userId"));
					updateIncomingRequest.put("dbsession", dbsession);
					updateIncomingRequest.put("poHeader", poHeader);
					updateIncomingRequest.put("ReceiptHeader_icPoHeader", previousPoHeader.getIcPoHeader().toString());	// IcHeaderKey
					
					process.executeProcess(updateIncomingRequest);
				}
			} else {
				Log.error(this, "PoHeader empty! in PoHeaderUpdateForLastRevision");
			}
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			e.printStackTrace();
		}
		return result;
	}

}