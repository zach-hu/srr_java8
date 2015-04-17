/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.InspectionHeader ;
import com.tsa.puridiom.inspection.tasks.InspectionRetrieveListByLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator
 */
public class RequisitionLineListRetrieveInspections extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		Object result ;
	    DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	    List reqLineList = (List) incomingRequest.get("requisitionLineList") ;

	    for (int i = 0; i < reqLineList.size(); i++) {
	    	RequisitionLine reqLine = (RequisitionLine) reqLineList.get(i) ;
	    	incomingRequest.put("InspectionLine_icMsrLine", reqLine.getIcLineHistory()) ;

	    	InspectionRetrieveListByLine ibl = new InspectionRetrieveListByLine() ;
	    	List inspList = (List) ibl.executeTask(incomingRequest) ;
	    	reqLine.setInspectionList(inspList) ;
	    	reqLineList.set(i,reqLine) ;
	    }

	    result = reqLineList ;
	    incomingRequest.put("requisitionLineList", reqLineList) ;

		this.setStatus(dbs.getStatus()) ;

		return  result;
	}

}
