package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.DictionaryManager;

public class PoApprovalsExceptions extends Task
{
	static BigDecimal NOTHING = new BigDecimal(0.00);
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object ret = Boolean.FALSE;
		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			String language = (String)incomingRequest.get("language");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			Boolean isPOToAward = Boolean.FALSE;
			ret =Boolean.TRUE;
			if(PropertiesManager.getInstance(organizationId).getProperty("PO APPROVALS", "CREATEDFROMNOTHING", "N").equalsIgnoreCase("Y"))
			{
				boolean isRevision = poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0;
			/*	if(poType.equals(OrderType.BLANKET_ORDER) || poType.equals(OrderType.PURCHASE_ORDER))
				{ */
				List poLineList = (List)incomingRequest.get("poLineList");
				for(int i = 0; i < poLineList.size(); i++)
				{
					PoLine poLine = (PoLine)poLineList.get(i);
					boolean checkException = true;
					if(isRevision)
					{
						String sLineRevNo = poLine.getLineRevNo();
						BigDecimal lineRevNo = new BigDecimal(0);
						if(!HiltonUtility.isEmpty(sLineRevNo)){		lineRevNo = new BigDecimal(sLineRevNo); }
						checkException = poHeader.getRevisionNumber().compareTo(lineRevNo) == 0;
					}
					if(poLine.getIcReqLine().compareTo(PoApprovalsExceptions.NOTHING) == 0 && poLine.getIcRfqLine().compareTo(PoApprovalsExceptions.NOTHING) == 0 && checkException)
					{

						ret = Boolean.FALSE;
						incomingRequest.put("errorMsg", DictionaryManager.getLabelsInstance(organizationId, language).getLabel(organizationId, "poFromNothingException", "Orders containing Non Standard Items must be sent for approval."));
						i = poLineList.size();
					}
				}
					//	}
			}

			if (organizationId.equalsIgnoreCase("bsc04p") && poHeader.getPoType().equals("CT"))
			{
				isPOToAward = Boolean.TRUE;
			}

			incomingRequest.put("isPOToAward", isPOToAward);
		}
		catch (Exception e)
		{
			ret = Boolean.TRUE;
		}
		this.setStatus(Status.SUCCEEDED);
		return ret;
	}

}
