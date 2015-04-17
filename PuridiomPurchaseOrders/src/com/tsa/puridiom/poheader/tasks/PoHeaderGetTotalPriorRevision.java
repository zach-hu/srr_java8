package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class PoHeaderGetTotalPriorRevision extends Task
{
    public Object  executeTask (Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;
        List resultList = null;
        try
        {
            //DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        	String  organizationId = (String) incomingRequest.get("organizationId") ;
            DBSession dbs = new DBSession(organizationId);

            PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
            String poNumber = poHeader.getPoNumber();
            BigDecimal releaseNumber = poHeader.getReleaseNumber();
            BigDecimal revisionNumberCurrent = poHeader.getRevisionNumber();
            BigDecimal revisionNumberPrior = new BigDecimal(0);
            BigDecimal totalPriorRevision = new BigDecimal(0);
            BigDecimal totalCurrentRevision = (BigDecimal)poHeader.getTotal();
            BigDecimal revisionValue = new BigDecimal(0);

            if( revisionNumberCurrent.intValue() > 0){
            	revisionNumberPrior = revisionNumberCurrent.subtract(new BigDecimal(1));
            	BigDecimal counter = new BigDecimal(2);
            	boolean getPrevious = true;

            	while (getPrevious && revisionNumberPrior.compareTo(new BigDecimal(0)) >= 0)
            	{
            		String queryString = "from PoHeader as poHeader where poHeader.poNumber = ? AND poHeader.revisionNumber = ? AND poHeader.releaseNumber = ? order by poHeader.revisionNumber" ;
            		resultList = dbs.query(queryString, new Object[] {poNumber, revisionNumberPrior, releaseNumber} , new Type[] { Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL}) ;
            		revisionNumberPrior =  revisionNumberCurrent.subtract(counter);
            		counter = counter.add(new BigDecimal(1));

            		if(resultList != null && resultList.size() > 0){
                		PoHeader ph = (PoHeader) resultList.get(0);
                		totalPriorRevision = ph.getTotal();
                		getPrevious = false;
                	} else {
                		getPrevious = true;
                	}

            	}

            	revisionValue = revisionValue.add(totalCurrentRevision);
            	revisionValue = revisionValue.subtract(totalPriorRevision);
            	poHeader.setRevisionValue(revisionValue);
            }

            incomingRequest.put("PoHeader_icPoHeader", poHeader.getIcPoHeader().toString());

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            Log.error(this, " Error on the setup revision value " + e.toString());
            e.toString();
            throw e;
        }
        return result;
    }
}