/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Administrator
 */
public class GetMsrLineNumberFromReqHeader extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = "false";
		String msrLineNumber="";
		try{
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader") ;
		List reqLineList = (List)(requisitionHeader.getRequisitionLineList());
		
		if(reqLineList!=null){
				RequisitionLine reqLine = (RequisitionLine)reqLineList.get(0);
				BigDecimal reqLineIcLineHistory = HiltonUtility.ckNull(reqLine.getIcLineHistory());
				BigDecimal reqLineIcReqHeader = HiltonUtility.ckNull(reqLine.getIcReqHeader());
				if(reqLineIcLineHistory.compareTo(new BigDecimal(0))>0 && !HiltonUtility.isEmpty(reqLine.getMsrNumber()))
				{
					String queryString = "from RequisitionLine as line where line.icLineHistory = ? and line.icReqHeader <> ?";
					List resultList = dbs.query(queryString, new Object[] {reqLineIcLineHistory, reqLineIcReqHeader } , new Type[] { Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;
					Object resultMsr = null;		
					if (resultList != null && resultList.size() > 0)
					{
						resultMsr = resultList.get(0);
					}
					if(resultMsr!=null)
					{
						RequisitionLine msrLine = (RequisitionLine) resultMsr;
						msrLineNumber = msrLine.getRequisitionNumber() + "-" + HiltonUtility.ckNull(msrLine.getLineNumber()).intValue();
						incomingRequest.put("msrLineNumber", msrLineNumber);
						result="true";
					}
				}
			this.setStatus(dbs.getStatus()) ;
		}
		else{
			this.setStatus(Status.SUCCEEDED) ;
		}
		}
		catch(Exception e){
			this.setStatus(Status.FAILED) ;
			throw new TsaException(this.getName(), e);
		}
		
		return result ;
	}
}
