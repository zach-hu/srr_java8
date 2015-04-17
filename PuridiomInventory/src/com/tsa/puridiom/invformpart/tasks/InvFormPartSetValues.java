package com.tsa.puridiom.invformpart.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvFormPart;
import com.tsa.puridiom.entity.InvFormPartPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class InvFormPartSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvFormPartPK comp_id = new InvFormPartPK();
			InvFormPart invFormPart = (InvFormPart) incomingRequest.get("invFormPart");
			if (invFormPart == null)
			{
				invFormPart = new InvFormPart();
			}

			if (incomingRequest.containsKey("InvFormPart_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvFormPart_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvFormPart_formPart"))
			{
				String formPartString = (String) incomingRequest.get("InvFormPart_formPart");
				if (Utility.isEmpty(formPartString))
				{
					formPartString = "0";
				}
				BigDecimal formPart = new BigDecimal ( formPartString );
				comp_id.setFormPart(formPart);
			}
			if (incomingRequest.containsKey("InvFormPart_measureBy"))
			{
				String measureBy = (String ) incomingRequest.get("InvFormPart_measureBy");
				invFormPart.setMeasureBy(measureBy);
			}
			if (incomingRequest.containsKey("InvFormPart_overallWidth"))
			{
				String overallWidthString = (String) incomingRequest.get("InvFormPart_overallWidth");
				if (Utility.isEmpty(overallWidthString))
				{
					overallWidthString = "0";
				}
				BigDecimal overallWidth = new BigDecimal ( overallWidthString );
				invFormPart.setOverallWidth(overallWidth);
			}
			if (incomingRequest.containsKey("InvFormPart_overallLen"))
			{
				String overallLenString = (String) incomingRequest.get("InvFormPart_overallLen");
				if (Utility.isEmpty(overallLenString))
				{
					overallLenString = "0";
				}
				BigDecimal overallLen = new BigDecimal ( overallLenString );
				invFormPart.setOverallLen(overallLen);
			}
			if (incomingRequest.containsKey("InvFormPart_detachWidth"))
			{
				String detachWidthString = (String) incomingRequest.get("InvFormPart_detachWidth");
				if (Utility.isEmpty(detachWidthString))
				{
					detachWidthString = "0";
				}
				BigDecimal detachWidth = new BigDecimal ( detachWidthString );
				invFormPart.setDetachWidth(detachWidth);
			}
			if (incomingRequest.containsKey("InvFormPart_detachLen"))
			{
				String detachLenString = (String) incomingRequest.get("InvFormPart_detachLen");
				if (Utility.isEmpty(detachLenString))
				{
					detachLenString = "0";
				}
				BigDecimal detachLen = new BigDecimal ( detachLenString );
				invFormPart.setDetachLen(detachLen);
			}
			if (incomingRequest.containsKey("InvFormPart_sizeUdf"))
			{
				String sizeUdf = (String ) incomingRequest.get("InvFormPart_sizeUdf");
				invFormPart.setSizeUdf(sizeUdf);
			}
			if (incomingRequest.containsKey("InvFormPart_printSides"))
			{
				String printSidesString = (String) incomingRequest.get("InvFormPart_printSides");
				if (Utility.isEmpty(printSidesString))
				{
					printSidesString = "0";
				}
				BigDecimal printSides = new BigDecimal ( printSidesString );
				invFormPart.setPrintSides(printSides);
			}
			if (incomingRequest.containsKey("InvFormPart_printCopies"))
			{
				String printCopiesString = (String) incomingRequest.get("InvFormPart_printCopies");
				if (Utility.isEmpty(printCopiesString))
				{
					printCopiesString = "0";
				}
				BigDecimal printCopies = new BigDecimal ( printCopiesString );
				invFormPart.setPrintCopies(printCopies);
			}
			if (incomingRequest.containsKey("InvFormPart_marginWords"))
			{
				String marginWords = (String ) incomingRequest.get("InvFormPart_marginWords");
				invFormPart.setMarginWords(marginWords);
			}
			if (incomingRequest.containsKey("InvFormPart_addchgdel"))
			{
				String addchgdel = (String ) incomingRequest.get("InvFormPart_addchgdel");
				invFormPart.setAddchgdel(addchgdel);
			}
			if (incomingRequest.containsKey("InvFormPart_blockout"))
			{
				String blockout = (String ) incomingRequest.get("InvFormPart_blockout");
				invFormPart.setBlockout(blockout);
			}
			if (incomingRequest.containsKey("InvFormPart_copiesUdf"))
			{
				String copiesUdf = (String ) incomingRequest.get("InvFormPart_copiesUdf");
				invFormPart.setCopiesUdf(copiesUdf);
			}
			if (incomingRequest.containsKey("InvFormPart_paperColor"))
			{
				String paperColor = (String ) incomingRequest.get("InvFormPart_paperColor");
				invFormPart.setPaperColor(paperColor);
			}
			if (incomingRequest.containsKey("InvFormPart_paperWeight"))
			{
				String paperWeightString = (String) incomingRequest.get("InvFormPart_paperWeight");
				if (Utility.isEmpty(paperWeightString))
				{
					paperWeightString = "0";
				}
				BigDecimal paperWeight = new BigDecimal ( paperWeightString );
				invFormPart.setPaperWeight(paperWeight);
			}
			if (incomingRequest.containsKey("InvFormPart_paperGrade"))
			{
				String paperGrade = (String ) incomingRequest.get("InvFormPart_paperGrade");
				invFormPart.setPaperGrade(paperGrade);
			}
			if (incomingRequest.containsKey("InvFormPart_inkFront"))
			{
				String inkFront = (String ) incomingRequest.get("InvFormPart_inkFront");
				invFormPart.setInkFront(inkFront);
			}
			if (incomingRequest.containsKey("InvFormPart_inkBack"))
			{
				String inkBack = (String ) incomingRequest.get("InvFormPart_inkBack");
				invFormPart.setInkBack(inkBack);
			}
			if (incomingRequest.containsKey("InvFormPart_paperUdf"))
			{
				String paperUdf = (String ) incomingRequest.get("InvFormPart_paperUdf");
				invFormPart.setPaperUdf(paperUdf);
			}
			if (incomingRequest.containsKey("InvFormPart_inkUdf"))
			{
				String inkUdf = (String ) incomingRequest.get("InvFormPart_inkUdf");
				invFormPart.setInkUdf(inkUdf);
			}
			if (incomingRequest.containsKey("InvFormPart_carbonWeight"))
			{
				String carbonWeightString = (String) incomingRequest.get("InvFormPart_carbonWeight");
				if (Utility.isEmpty(carbonWeightString))
				{
					carbonWeightString = "0";
				}
				BigDecimal carbonWeight = new BigDecimal ( carbonWeightString );
				invFormPart.setCarbonWeight(carbonWeight);
			}
			if (incomingRequest.containsKey("InvFormPart_carbonGrade"))
			{
				String carbonGrade = (String ) incomingRequest.get("InvFormPart_carbonGrade");
				invFormPart.setCarbonGrade(carbonGrade);
			}
			if (incomingRequest.containsKey("InvFormPart_carbonColor"))
			{
				String carbonColor = (String ) incomingRequest.get("InvFormPart_carbonColor");
				invFormPart.setCarbonColor(carbonColor);
			}
			if (incomingRequest.containsKey("InvFormPart_carbonSize"))
			{
				String carbonSizeString = (String) incomingRequest.get("InvFormPart_carbonSize");
				if (Utility.isEmpty(carbonSizeString))
				{
					carbonSizeString = "0";
				}
				BigDecimal carbonSize = new BigDecimal ( carbonSizeString );
				invFormPart.setCarbonSize(carbonSize);
			}
			if (incomingRequest.containsKey("InvFormPart_carbonSpot"))
			{
				String carbonSpot = (String ) incomingRequest.get("InvFormPart_carbonSpot");
				invFormPart.setCarbonSpot(carbonSpot);
			}
			if (incomingRequest.containsKey("InvFormPart_carbonStrip"))
			{
				String carbonStrip = (String ) incomingRequest.get("InvFormPart_carbonStrip");
				invFormPart.setCarbonStrip(carbonStrip);
			}
			if (incomingRequest.containsKey("InvFormPart_carbonUdf"))
			{
				String carbonUdf = (String ) incomingRequest.get("InvFormPart_carbonUdf");
				invFormPart.setCarbonUdf(carbonUdf);
			}
			if (incomingRequest.containsKey("InvFormPart_punchHoles"))
			{
				String punchHolesString = (String) incomingRequest.get("InvFormPart_punchHoles");
				if (Utility.isEmpty(punchHolesString))
				{
					punchHolesString = "0";
				}
				BigDecimal punchHoles = new BigDecimal ( punchHolesString );
				invFormPart.setPunchHoles(punchHoles);
			}
			if (incomingRequest.containsKey("InvFormPart_punchSize"))
			{
				String punchSizeString = (String) incomingRequest.get("InvFormPart_punchSize");
				if (Utility.isEmpty(punchSizeString))
				{
					punchSizeString = "0";
				}
				BigDecimal punchSize = new BigDecimal ( punchSizeString );
				invFormPart.setPunchSize(punchSize);
			}
			if (incomingRequest.containsKey("InvFormPart_punchCenter"))
			{
				String punchCenterString = (String) incomingRequest.get("InvFormPart_punchCenter");
				if (Utility.isEmpty(punchCenterString))
				{
					punchCenterString = "0";
				}
				BigDecimal punchCenter = new BigDecimal ( punchCenterString );
				invFormPart.setPunchCenter(punchCenter);
			}
			if (incomingRequest.containsKey("InvFormPart_punchPos"))
			{
				String punchPos = (String ) incomingRequest.get("InvFormPart_punchPos");
				invFormPart.setPunchPos(punchPos);
			}
			if (incomingRequest.containsKey("InvFormPart_mperfLeft"))
			{
				String mperfLeftString = (String) incomingRequest.get("InvFormPart_mperfLeft");
				if (Utility.isEmpty(mperfLeftString))
				{
					mperfLeftString = "0";
				}
				BigDecimal mperfLeft = new BigDecimal ( mperfLeftString );
				invFormPart.setMperfLeft(mperfLeft);
			}
			if (incomingRequest.containsKey("InvFormPart_mperfRight"))
			{
				String mperfRightString = (String) incomingRequest.get("InvFormPart_mperfRight");
				if (Utility.isEmpty(mperfRightString))
				{
					mperfRightString = "0";
				}
				BigDecimal mperfRight = new BigDecimal ( mperfRightString );
				invFormPart.setMperfRight(mperfRight);
			}
			if (incomingRequest.containsKey("InvFormPart_operfLeft"))
			{
				String operfLeftString = (String) incomingRequest.get("InvFormPart_operfLeft");
				if (Utility.isEmpty(operfLeftString))
				{
					operfLeftString = "0";
				}
				BigDecimal operfLeft = new BigDecimal ( operfLeftString );
				invFormPart.setOperfLeft(operfLeft);
			}
			if (incomingRequest.containsKey("InvFormPart_operfRight"))
			{
				String operfRightString = (String) incomingRequest.get("InvFormPart_operfRight");
				if (Utility.isEmpty(operfRightString))
				{
					operfRightString = "0";
				}
				BigDecimal operfRight = new BigDecimal ( operfRightString );
				invFormPart.setOperfRight(operfRight);
			}
			if (incomingRequest.containsKey("InvFormPart_perfType"))
			{
				String perfType = (String ) incomingRequest.get("InvFormPart_perfType");
				invFormPart.setPerfType(perfType);
			}
			if (incomingRequest.containsKey("InvFormPart_turnType"))
			{
				String turnType = (String ) incomingRequest.get("InvFormPart_turnType");
				invFormPart.setTurnType(turnType);
			}
			
			invFormPart.setComp_id(comp_id);

			result = invFormPart;
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