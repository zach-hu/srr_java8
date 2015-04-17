package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvReturn;
import com.tsa.puridiom.entity.InvReturnPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

public class InvReturnSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invReturn */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvReturn	originalInvReturn = (InvReturn) incomingRequest.get("invReturn");
			InvReturn	invReturn = new InvReturn();

			invReturn.setIcInvReturn(new BigDecimal(ukg.getUniqueKey().toString()));
			invReturn.setRequisitionNumber(originalInvReturn.getRequisitionNumber());
			invReturn.setLineNo(originalInvReturn.getLineNo());
			invReturn.setItemNumber(originalInvReturn.getItemNumber());
			invReturn.setIcReqHeader(originalInvReturn.getIcReqHeader());
			invReturn.setIcReqLine(originalInvReturn.getIcReqLine());
			invReturn.setIcBin(originalInvReturn.getIcBin());
			invReturn.setRecBy(originalInvReturn.getRecBy());
			invReturn.setRecDate(originalInvReturn.getRecDate());
			invReturn.setRecAmount(originalInvReturn.getRecAmount());
			invReturn.setOwner(originalInvReturn.getOwner());
			invReturn.setDuomQty(originalInvReturn.getDuomQty());

			incomingRequest.put("invReturn", invReturn);

			InvReturnAdd addTask = new InvReturnAdd();
			invReturn = (InvReturn) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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