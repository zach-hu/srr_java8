package com.tsa.puridiom.bomreference.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomReferenceSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BomReference bomReference = (BomReference) incomingRequest.get("bomReference");
			if (bomReference == null)
			{
				bomReference = new BomReference();
			}

			if (incomingRequest.containsKey("BomReference_icReference"))
			{
				String icReferenceString = (String) incomingRequest.get("BomReference_icReference");
				if (Utility.isEmpty(icReferenceString))
				{
					icReferenceString = "0";
				}
				BigDecimal icReference = new BigDecimal ( icReferenceString );
				bomReference.setIcReference(icReference);
			}
			if (incomingRequest.containsKey("BomReference_parentItem"))
			{
				String parentItem = (String) incomingRequest.get("BomReference_parentItem");
				bomReference.setParentItem(parentItem);
			}
			if (incomingRequest.containsKey("BomReference_componentItem"))
			{
				String componentItem = (String) incomingRequest.get("BomReference_componentItem");
				bomReference.setComponentItem(componentItem);
			}
			if (incomingRequest.containsKey("BomReference_icComponent"))
			{
				String icComponentString = (String) incomingRequest.get("BomReference_icComponent");
				if (Utility.isEmpty(icComponentString))
				{
					icComponentString = "0";
				}
				BigDecimal icComponent = new BigDecimal ( icComponentString );
				bomReference.setIcComponent(icComponent);
			}
			if (incomingRequest.containsKey("BomReference_referenceId"))
			{
				String referenceId = (String) incomingRequest.get("BomReference_referenceId");
				bomReference.setReferenceId(referenceId);
			}
			if (incomingRequest.containsKey("BomReference_refNo"))
			{
				String refNoString = (String) incomingRequest.get("BomReference_refNo");
				if (Utility.isEmpty(refNoString))
				{
					refNoString = "0";
				}
				BigDecimal refNo = new BigDecimal ( refNoString );
				bomReference.setRefNo(refNo);
			}
			if (incomingRequest.containsKey("BomReference_qty"))
			{
				String qtyString = (String) incomingRequest.get("BomReference_qty");
				if (Utility.isEmpty(qtyString))
				{
					qtyString = "0";
				}
				BigDecimal qty = new BigDecimal ( qtyString );
				bomReference.setQty(qty);
			}
			if (incomingRequest.containsKey("BomReference_methodId"))
			{
				String methodId = (String) incomingRequest.get("BomReference_methodId");
				bomReference.setMethodId(methodId);
			}
			if (incomingRequest.containsKey("BomReference_stageId"))
			{
				String stageId = (String) incomingRequest.get("BomReference_stageId");
				bomReference.setStageId(stageId);
			}
			if (incomingRequest.containsKey("BomReference_icRouting"))
			{
				String icRoutingString = (String) incomingRequest.get("BomReference_icRouting");
				if (Utility.isEmpty(icRoutingString))
				{
					icRoutingString = "0";
				}
				BigDecimal icRouting = new BigDecimal ( icRoutingString );
				bomReference.setIcRouting(icRouting);
			}
			if (incomingRequest.containsKey("BomReference_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("BomReference_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				bomReference.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("BomReference_owner"))
			{
				String owner = (String) incomingRequest.get("BomReference_owner");
				bomReference.setOwner(owner);
			}

			result = bomReference;
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