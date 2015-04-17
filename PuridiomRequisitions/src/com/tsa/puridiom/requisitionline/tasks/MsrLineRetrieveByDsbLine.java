package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MsrLineRetrieveByDsbLine extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
			String itemNumber = HiltonUtility.ckNull(disbLine.getItemNumber());
			String msrNumber = "";
			String msrLineNumber = "";
			int pointPlace = itemNumber.indexOf(".");
			if(pointPlace > 0 ){
				msrNumber = itemNumber.substring(0, pointPlace);
				try {
					msrLineNumber = String.valueOf(Integer.parseInt(itemNumber.substring(pointPlace+1, itemNumber.length())));
				} catch (Exception e) {
					// TODO: handle exception
				} 
				
			}
			if(!HiltonUtility.isEmpty(msrNumber)){
				String queryString = "from RequisitionLine as RequisitionLine where RequisitionLine.requisitionNumber = ? and RequisitionLine.lineNumber = ?";
				List resultList = dbs.query(queryString, new Object[] {msrNumber, msrLineNumber} , new Type[] { Hibernate.STRING,Hibernate.STRING}) ;	
				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
				this.setStatus(dbs.getStatus()) ;

			}else{
				this.setStatus(Status.SUCCEEDED) ;
			}
					}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}