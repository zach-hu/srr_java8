package com.tsa.puridiom.poline.tasks;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
public class PoLineRetrieveReceipts extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest= (Map) object;
		Object result= null;
		try
		{
			DBSession dbs= (DBSession) incomingRequest.get("dbsession");
			String icPoLineString = (String) incomingRequest.get("PoLine_icLineKey");
			if (Utility.isEmpty(icPoLineString))
			{
				throw new Exception("PoLine_icLineKey cannot be empty.  Receipts could not be retrieved.");
			}
			BigDecimal icPoLine= new BigDecimal(icPoLineString);
			String queryString= "from PoLine as poLine where (poLine.status = '3035' OR poLine.status = '3040') AND poLine.icLineKey = ? ";
			List resultList = dbs.query(queryString, new Object[] { icPoLine }, new Type[] { Hibernate.BIG_DECIMAL });
			result= resultList;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}