/**
 *
 * Created on Feb 10, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.disbursement.tasks.DisbursementCreateSetup.java
 *
 */
package com.tsa.puridiom.disbursement.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.DisbHeader;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;
//import com.tsagate.foundation.utility.Dates;
//import com.tsagate.foundation.utility.TsaException;
//import com.tsagate.foundation.utility.UniqueKeyGenerator;


public class DisbursementCreateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DisbHeader disbHeader = null;
		try
		{
			//setup req ics
			RequisitionHeader reqheader = (RequisitionHeader)incomingRequest.get("RequisitionHeader");
			if(reqheader == null){	throw new TsaException("Requisition entity not found!");	}
			disbHeader = new DisbHeader();
			disbHeader.setRequisitionNumber(reqheader.getRequisitionNumber());
			disbHeader.setIcReqHeader(reqheader.getIcReqHeader());
			disbHeader.setFiscalYear(reqheader.getFiscalYear());

			incomingRequest.put("Account_icHeader", reqheader.getIcReqHeader().toString());
			incomingRequest.put("Account_icLine", "0");

			incomingRequest.put("DocComment_icHeader", reqheader.getIcReqHeader().toString());
			incomingRequest.put("DocComment_icLine", "0");

			//		Create new ic codes
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			disbHeader.setIcDsbHeader(new BigDecimal(ukg.getUniqueKey().toString()));

			incomingRequest.put("DisbHeader_icDsbHeader", disbHeader.getIcDsbHeader().toString());
			incomingRequest.put("DisbLine_icDsbHeader", disbHeader.getIcDsbHeader().toString());

			incomingRequest.put("newDocComment_icHeader", disbHeader.getIcDsbHeader().toString()) ;
			incomingRequest.put("newDocComment_icLine", "0") ;

			incomingRequest.put("DisbHeader_fiscalYear", disbHeader.getFiscalYear());
			incomingRequest.put("RequisitionHeader_requisitionType", reqheader.getRequisitionType());

			String userid =(String) incomingRequest.get("userId");
            String userTimeZone =(String) incomingRequest.get("userTimeZone");
			disbHeader.setOwner(userid);
			disbHeader.setLastChgBy(userid);
			disbHeader.setDateEntered(Dates.getDate(Dates.today("", userTimeZone)));
			disbHeader.setDisbDate(Dates.getDate(Dates.today("", userTimeZone)));
			disbHeader.setDisbType(reqheader.getRequisitionType());
			disbHeader.setStatus(DocumentStatus.INV_INPROGRESS);
			disbHeader.setRequisitionerCode(Utility.ckNull(reqheader.getRequisitionerCode()));
			disbHeader.setShipToCode(Utility.ckNull(reqheader.getShipToCode()));
			disbHeader.setItemLocation(Utility.ckNull(reqheader.getItemLocation()));
			disbHeader.setSubtotal(reqheader.getTotal());
			disbHeader.setIcHeaderHistory(reqheader.getIcHeaderHistory());
            disbHeader.setTimeZone(userTimeZone);
            disbHeader.setInternalComments(reqheader.getInternalComments());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return disbHeader;
	}
}
