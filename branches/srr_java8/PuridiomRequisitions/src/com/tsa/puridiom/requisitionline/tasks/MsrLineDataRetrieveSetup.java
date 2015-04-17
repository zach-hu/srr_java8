/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
/**
 * @author Administrator
 */
public class MsrLineDataRetrieveSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		try{
			
		
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String msrIcReqLineHistoryString = (String) incomingRequest.get("msrIcReqLineHistory") ;
		BigDecimal msrIcReqLineHistory = new BigDecimal(msrIcReqLineHistoryString);
		String msrIcReqHeaderString = (String) incomingRequest.get("msrIcReqHeader") ;
		BigDecimal msrIcReqHeader = new BigDecimal(msrIcReqHeaderString);
		//REQLINE SOURCED
		String queryString = "from RequisitionLine as line where line.icLineHistory = ? and line.reqType <> 'M'";
		List resultList = dbs.query(queryString, new Object[] {msrIcReqLineHistory} , new Type[] { Hibernate.BIG_DECIMAL}) ;
				
		if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}
		RequisitionLine reqLineSourced = (RequisitionLine)result; 
		//MSRLINE
		String msrIcReqLineString = (String) incomingRequest.get("msrIcReqLine") ;
		BigDecimal msrIcReqLine= new BigDecimal(msrIcReqLineString);
		String queryMsrLineString = "from RequisitionLine as line where line.icReqLine = ?";
		List resultMsrLineList = dbs.query(queryMsrLineString, new Object[] {msrIcReqLine, } , new Type[] { Hibernate.BIG_DECIMAL}) ;
		Object resultMsrLine = null;	
		if (resultMsrLineList != null && resultMsrLineList.size() > 0)
		{
			resultMsrLine = resultMsrLineList.get(0);
		}
		RequisitionLine msrLine = (RequisitionLine)resultMsrLine;
		
		if(result!=null){
			this.setStatus(dbs.getStatus()) ;
			//GET REQ HEADER SOURCED
			incomingRequest.put("RequisitionHeader_icReqHeader", reqLineSourced.getIcReqHeader().toString());
			incomingRequest.put("PoLine_icReqLine", reqLineSourced.getIcReqLine());
			incomingRequest.put("PoLine_icLineHistory", reqLineSourced.getIcLineHistory());
			incomingRequest.put("ReceiptLine_icPoLine", reqLineSourced.getIcPoLine().toString());
			incomingRequest.put("ReceiptLine_icReqLine", reqLineSourced.getIcReqLine().toString());
			incomingRequest.put("reqLineSourced", reqLineSourced);
			incomingRequest.put("reqLineTypeSourced", HiltonUtility.ckNull(reqLineSourced.getReqType()));
			incomingRequest.put("existReqSourced", "Y");
			incomingRequest.put("msrLine", msrLine);
			incomingRequest.put("InvItem_itemNumber", msrLine.getRequisitionNumber()+".000"+msrLine.getLineNumber().intValue());
			incomingRequest.put("InvBinLocation_itemNumber", msrLine.getRequisitionNumber()+".000"+msrLine.getLineNumber().intValue());
		
			//GET REQ LINE SOURCED
		}else{
			incomingRequest.put("existReqSourced", "N");
			incomingRequest.put("msrLine", msrLine);
			this.setStatus(Status.SUCCEEDED) ;
		}
		}
		catch(Exception e){
			this.setStatus(Status.FAILED) ;
			throw new TsaException(this.getName(), e);
		}
		
		return null ;
	}
}
