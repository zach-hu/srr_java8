package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.InvReturn;
import com.tsa.puridiom.entity.InvReturnPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class InvReturnSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvReturn invReturn = (InvReturn) incomingRequest.get("invReturn");
			if (invReturn == null)
			{
				invReturn = new InvReturn();
			}

			if (incomingRequest.containsKey("InvReturn_icInvReturn"))
			{
				String icInvReturnString = (String) incomingRequest.get("InvReturn_icInvReturn");
				if (Utility.isEmpty(icInvReturnString))
				{
					icInvReturnString = "0";
				}
				BigDecimal icInvReturn = new BigDecimal ( icInvReturnString );
				invReturn.setIcInvReturn(icInvReturn);
			}
			if (incomingRequest.containsKey("InvReturn_requisitionNumber"))
			{
				String requisitionNumber = (String ) incomingRequest.get("InvReturn_requisitionNumber");
				invReturn.setRequisitionNumber(requisitionNumber);
			}
			if (incomingRequest.containsKey("InvReturn_disbNumber"))
			{
				String disbNumber = (String ) incomingRequest.get("InvReturn_disbNumber");
				invReturn.setDisbNumber(disbNumber);
			}
			if (incomingRequest.containsKey("InvReturn_lineNo"))
			{
				String lineNoString = (String) incomingRequest.get("InvReturn_lineNo");
				if (Utility.isEmpty(lineNoString))
				{
					lineNoString = "0";
				}
				BigDecimal lineNo = new BigDecimal ( lineNoString );
				invReturn.setLineNo(lineNo);
			}
			if (incomingRequest.containsKey("InvReturn_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvReturn_itemNumber");
				invReturn.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvReturn_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("InvReturn_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					icReqHeaderString = "0";
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
				invReturn.setIcReqHeader(icReqHeader);
			}
			if (incomingRequest.containsKey("InvReturn_icReqLine"))
			{
				String icReqLineString = (String) incomingRequest.get("InvReturn_icReqLine");
				if (Utility.isEmpty(icReqLineString))
				{
					icReqLineString = "0";
				}
				BigDecimal icReqLine = new BigDecimal ( icReqLineString );
				invReturn.setIcReqLine(icReqLine);
			}
			if (incomingRequest.containsKey("InvReturn_icDsbHeader"))
			{
				String icDsbHeaderString = (String) incomingRequest.get("InvReturn_icDsbHeader");
				if (Utility.isEmpty(icDsbHeaderString))
				{
					icDsbHeaderString = "0";
				}
				BigDecimal icDsbHeader = new BigDecimal ( icDsbHeaderString );
				invReturn.setIcDsbHeader(icDsbHeader);
			}
			if (incomingRequest.containsKey("InvReturn_icDsbLine"))
			{
				String icDsbLineString = (String) incomingRequest.get("InvReturn_icDsbLine");
				if (Utility.isEmpty(icDsbLineString))
				{
					icDsbLineString = "0";
				}
				BigDecimal icDsbLine = new BigDecimal ( icDsbLineString );
				invReturn.setIcDsbLine(icDsbLine);
			}
			if (incomingRequest.containsKey("InvReturn_icBin"))
			{
				String icBinString = (String) incomingRequest.get("InvReturn_icBin");
				if (Utility.isEmpty(icBinString))
				{
					icBinString = "0";
				}
				BigDecimal icBin = new BigDecimal ( icBinString );
				invReturn.setIcBin(icBin);
			}
			if (incomingRequest.containsKey("InvReturn_recBy"))
			{
				String recBy = (String ) incomingRequest.get("InvReturn_recBy");
				invReturn.setRecBy(recBy);
			}
			if (incomingRequest.containsKey("InvReturn_recDate"))
			{
				String recDateString = (String) incomingRequest.get("InvReturn_recDate");
				Date recDate = Dates.getDate(recDateString);
				invReturn.setRecDate(recDate);
			}
			if (incomingRequest.containsKey("InvReturn_recAmount"))
			{
				String recAmountString = (String) incomingRequest.get("InvReturn_recAmount");
				if (Utility.isEmpty(recAmountString))
				{
					recAmountString = "0";
				}
				BigDecimal recAmount = new BigDecimal ( recAmountString );
				invReturn.setRecAmount(recAmount);
			}
			if (incomingRequest.containsKey("InvReturn_owner"))
			{
				String owner = (String ) incomingRequest.get("InvReturn_owner");
				invReturn.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvReturn_duomQty"))
			{
				String duomQtyString = (String) incomingRequest.get("InvReturn_duomQty");
				if (Utility.isEmpty(duomQtyString))
				{
					duomQtyString = "0";
				}
				BigDecimal duomQty = new BigDecimal ( duomQtyString );
				invReturn.setDuomQty(duomQty);
			}

			result = invReturn;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}