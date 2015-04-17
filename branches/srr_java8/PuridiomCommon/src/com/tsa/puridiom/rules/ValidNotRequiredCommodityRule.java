package com.tsa.puridiom.rules;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class ValidNotRequiredCommodityRule extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
        {
	    	DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	    	String formType = (String)incomingRequest.get("formType");

	    	if (formType.equals("REQ"))
  	    	{
	    		List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
	    		for (Iterator it = requisitionLineList.iterator(); it.hasNext(); )
		        {
		        	RequisitionLine requisitionLine = (RequisitionLine) it.next();
		        	String commodity = requisitionLine.getCommodityCode();

					String queryString = "";
					List resultList = null;

					if ( !HiltonUtility.isEmpty(commodity))
					{
						queryString = "Select count(*) from Commodity as commodity " +
						"WHERE commodity.commodity = ? AND commodity.status = '02' ";
						resultList = dbs.query(queryString, new Object[] {commodity} , new Type[] { Hibernate.STRING}) ;
						if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
						{
							incomingRequest.put("commodity-valid-not-required", "failed");
						}
					}
		        }
  	    	}
	    	else if (formType.equals("PO"))
  	    	{
	    		List poLineList = (List) incomingRequest.get("poLineList");
	    		for (Iterator it = poLineList.iterator(); it.hasNext(); )
		        {
		        	PoLine poLine = (PoLine) it.next();
		        	String commodity = poLine.getCommodity();

					String queryString = "";
					List resultList = null;

					if ( !HiltonUtility.isEmpty(commodity))
					{
						queryString = "Select count(*) from Commodity as commodity " +
						"WHERE commodity.commodity = ? AND commodity.status = '02' ";
						resultList = dbs.query(queryString, new Object[] {commodity} , new Type[] { Hibernate.STRING}) ;
						if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
						{
							incomingRequest.put("commodity-valid-not-required", "failed");
						}
					}
		        }
  	    	}
	    	else if (formType.equals("RFQ"))
  	    	{
	    		List rfqLineList = (List) incomingRequest.get("rfqLineList");
	    		for (Iterator it = rfqLineList.iterator(); it.hasNext(); )
		        {
		        	RfqLine rfqLine = (RfqLine) it.next();
		        	String commodity = rfqLine.getCommodity();

					String queryString = "";
					List resultList = null;

					if ( !HiltonUtility.isEmpty(commodity))
					{
						queryString = "Select count(*) from Commodity as commodity " +
						"WHERE commodity.commodity = ? AND commodity.status = '02' ";
						resultList = dbs.query(queryString, new Object[] {commodity} , new Type[] { Hibernate.STRING}) ;
						if ( (resultList == null || resultList.size() < 1) || ((Integer)resultList.get(0)).intValue() < 1 )
						{
							incomingRequest.put("commodity-valid-not-required", "failed");
						}
					}
		        }
  	    	}
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
            throw new TsaException("An Error occurred at ValidNotRequiredCommodityRule", e);
		}
		return result;
    }
}