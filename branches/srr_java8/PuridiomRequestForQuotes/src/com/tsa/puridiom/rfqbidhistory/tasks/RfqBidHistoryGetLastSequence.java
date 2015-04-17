package com.tsa.puridiom.rfqbidhistory.tasks;

import java.util.Map;
import java.util.List;
import java.math.BigDecimal;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqBidHistoryGetLastSequence extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 * @author EDSAC
	  */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
 		{
			DBSession dbs =(DBSession)incomingRequest.get("dbsession");

			BigDecimal icRfqHeader = new BigDecimal ((String)incomingRequest.get("RfqBidHistory_icRfqHeader"));
			Object vendorIdObject = (Object)incomingRequest.get("RfqBidHistory_vendorId");
			String vendorId = new String();
			if (vendorIdObject instanceof String[]) {
				String vendorIdStringArray[] = (String[]) vendorIdObject;
				vendorId = vendorIdStringArray[0];
			}
			else {
				vendorId = (String) vendorIdObject;
			}

			StringBuffer queryString = new StringBuffer("Select MAX(RfqBidHistory.id.bidSequence) from RfqBidHistory RfqBidHistory Where RfqBidHistory.id.icRfqHeader = '" + icRfqHeader + "' and RfqBidHistory.id.vendorId = '" + vendorId + "'");
			String query = queryString.toString();
			List lastSequenceList = dbs.query(query);

			if(lastSequenceList != null && lastSequenceList.size()>0)
            {
				ret = (BigDecimal) lastSequenceList.get(0);
				if (ret == null) {
					ret = new BigDecimal("0");
				}
            }
            else
            {
            	ret = new BigDecimal("0");
            }
            this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.getMessage();
			throw new TsaException(this.getName(), e);

		}
		return ret;
	}
}