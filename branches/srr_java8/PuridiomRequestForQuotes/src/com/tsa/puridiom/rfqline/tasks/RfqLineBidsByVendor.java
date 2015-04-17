/**
 *
 * Created on Feb 2, 2004
 * @author renzo
 * project: HiltonRequestForQuotes
 * com.tsa.puridiom.rfqline.tasks.RfqLineBids.java
 *
 */
package com.tsa.puridiom.rfqline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RfqLineBidsByVendor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			String sicRfqHeader = (String)incomingRequest.get("RfqBid_icRfqHeader");
			if(sicRfqHeader == null){
				throw new TsaException("RfqBid_icRfqHeader was not found!");
			}
			BigDecimal icRfqHeader = new BigDecimal(sicRfqHeader);
			String vendorId = (String)incomingRequest.get("vendorId");

			if (Utility.isEmpty(vendorId))
			{
				vendorId = (String)incomingRequest.get("RfqVendor_vendorId");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			//String query = "from RfqLine rfqLine, DocText docText, RfqVendor vendor, RfqBid rfqBid where " +
			String query = "from RfqLine rfqLine, RfqVendor vendor, RfqBid rfqBid where " +
					//"( rfqLine.icText = docText.icText (+)) and" +
					//"( rfqLine.icText *= docText.icText ) and" +
					"( rfqBid.id.vendorId = vendor.id.vendorId ) and" +
					"( rfqBid.id.icRfqLine = rfqLine.id ) and" +
					"( vendor.id.icRfqHeader = ? ) and" +
					"( ( rfqBid.id.icRfqHeader = ? ) and" +
					"( rfqLine.vendorAwarded = ? ) and" +
					"( rfqBid.id.vendorId = ? ) )";

			ret = dbs.query(query, new Object[] {icRfqHeader, icRfqHeader, vendorId, vendorId} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING, Hibernate.STRING}) ;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(e.toString());
		}

		return ret;
	}

}
