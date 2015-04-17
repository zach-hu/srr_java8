package com.tsa.puridiom.docattachment.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class DocAttachmentRetrieveByLine extends Task
{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String retrieveForSave = (String)incomingRequest.get("retrieveForSave");
			String icHeaderString = (String)incomingRequest.get("DocAttachment_icHeader");
			String icLineString = (String)incomingRequest.get("DocAttachment_icLine");
			if (HiltonUtility.isEmpty(icLineString) || "null".equals(icLineString)) {
				icLineString = "0";
			}
			
			if(HiltonUtility.isEmpty(retrieveForSave) || retrieveForSave.equalsIgnoreCase("N")){
				if (!HiltonUtility.isEmpty((String)incomingRequest.get("DocAttachment_icHeader")) && !HiltonUtility.isEmpty((String)incomingRequest.get("InvItem_itemNumber"))) {
					icHeaderString = (String) incomingRequest.get("DocAttachment_icHeader");
					//icLineString = new String("0");
				} else if (!HiltonUtility.isEmpty((String)incomingRequest.get("RfqLine_icRfqHeader")) && !HiltonUtility.isEmpty((String)incomingRequest.get("RfqLine_icRfqLine"))) {
					icHeaderString = (String) incomingRequest.get("RfqLine_icRfqHeader");
					icLineString = (String) incomingRequest.get("RfqLine_icRfqLine");
				} else if (!HiltonUtility.isEmpty((String)incomingRequest.get("PoLine_icPoHeader")) && !HiltonUtility.isEmpty((String)incomingRequest.get("PoLine_icPoLine"))) {
					icHeaderString = (String) incomingRequest.get("PoLine_icPoHeader");
					icLineString = (String) incomingRequest.get("PoLine_icPoLine");
				} else if (!HiltonUtility.isEmpty((String)incomingRequest.get("RequisitionLine_icReqHeader")) && !HiltonUtility.isEmpty((String)incomingRequest.get("RequisitionLine_icReqLine"))) {
					icHeaderString = (String) incomingRequest.get("RequisitionLine_icReqHeader");
					icLineString = (String) incomingRequest.get("RequisitionLine_icReqLine");
				}
			}
			if (Utility.isEmpty(icHeaderString)) {
				throw new Exception ("DocAttachment icHeader OR icLine cannot be empty.");
			}

			String queryString = "from DocAttachment as d where d.id.icHeader = ?  AND d.id.icLine = ? order by d.id.docIc ASC" ;
			BigDecimal icHeader = new BigDecimal(icHeaderString);
			BigDecimal icLine = new BigDecimal(icLineString);

			ret = dbs.query(queryString,	new Object[] {icHeader, icLine}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			Log.error(this, e);
			this.setStatus(Status.FAILED);
			throw new TsaException("Attachments could not be Retrieved.", e);
		}
		return ret ;
	}
}