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
public class MsrLinesDataSetReqSourcedMap extends Task
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
		RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader") ;
		List msrLineList = (List)(requisitionHeader.getRequisitionLineList());
		
		if(msrLineList!=null){
			for(int mll = 0 ; mll < msrLineList.size(); mll++){
				RequisitionLine msrLine = (RequisitionLine)msrLineList.get(mll);
				String msrIcReqLineHistory = String.valueOf(HiltonUtility.ckNull(msrLine.getIcLineHistory()));
				String msrIcReqHeader = String.valueOf(HiltonUtility.ckNull(msrLine.getIcReqHeader()));
				String msrIcReqLine = String.valueOf(HiltonUtility.ckNull(msrLine.getIcReqLine()));
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("dbsession",dbs);
				newIncomingRequest.put("organizationId",(String)incomingRequest.get("organizationId"));
				newIncomingRequest.put("msrIcReqLineHistory",msrIcReqLineHistory);
				newIncomingRequest.put("msrIcReqHeader",msrIcReqHeader);
				newIncomingRequest.put("msrIcReqLine",msrIcReqLine);
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("msrline-data-retrieve.xml");
				process.executeProcess(newIncomingRequest);
				Map reqSourcedMap = new HashMap();
				reqSourcedMap.put("requisitionHeader",newIncomingRequest.get("reqHeaderSourced"));
				reqSourcedMap.put("poHeader",newIncomingRequest.get("poHeader"));
				reqSourcedMap.put("receiptHeaderList",(List)newIncomingRequest.get("receiptHeaderList"));
				reqSourcedMap.put("invoiceHeaderList",(List)newIncomingRequest.get("invoiceHeaderList"));
				reqSourcedMap.put("invBinLocationList",(List)newIncomingRequest.get("invBinLocationList"));
				msrLine.setReqSourcedMapList(reqSourcedMap);
			}
			this.setStatus(dbs.getStatus()) ;
			
		}else{
			this.setStatus(Status.SUCCEEDED) ;
		}
		requisitionHeader.setRequisitionLineList(msrLineList);
		incomingRequest.put("requisitionHeader", requisitionHeader);
		}
		catch(Exception e){
			this.setStatus(Status.FAILED) ;
			throw new TsaException(this.getName(), e);
		}
		
		return result ;
	}
}
