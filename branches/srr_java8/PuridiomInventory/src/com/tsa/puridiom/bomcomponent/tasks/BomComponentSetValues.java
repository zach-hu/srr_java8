package com.tsa.puridiom.bomcomponent.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomComponentSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BomComponent bomComponent = (BomComponent) incomingRequest.get("bomComponent");
			if (bomComponent == null)
			{
				bomComponent = new BomComponent();
			}

			if (incomingRequest.containsKey("BomComponent_icComponent"))
			{
				String icComponentString = (String) incomingRequest.get("BomComponent_icComponent");
				if (Utility.isEmpty(icComponentString))
				{
					icComponentString = "0";
				}
				BigDecimal icComponent = new BigDecimal ( icComponentString );
				bomComponent.setIcComponent(icComponent);
			}
			if (incomingRequest.containsKey("BomComponent_parentItem"))
			{
				String parentItem = (String) incomingRequest.get("BomComponent_parentItem");
				bomComponent.setParentItem(parentItem);
			}
			if (incomingRequest.containsKey("BomComponent_componentItem"))
			{
				String componentItem = (String) incomingRequest.get("BomComponent_componentItem");
				bomComponent.setComponentItem(componentItem);
			}
			if (incomingRequest.containsKey("BomComponent_componentType"))
			{
				String componentType = (String) incomingRequest.get("BomComponent_componentType");
				bomComponent.setComponentType(componentType);
			}
			if (incomingRequest.containsKey("BomComponent_bomLine"))
			{
				String bomLineString = (String) incomingRequest.get("BomComponent_bomLine");
				if (Utility.isEmpty(bomLineString))
				{
					bomLineString = "0";
				}
				BigDecimal bomLine = new BigDecimal ( bomLineString );
				bomComponent.setBomLine(bomLine);
			}
			if (incomingRequest.containsKey("BomComponent_description"))
			{
				String description = (String) incomingRequest.get("BomComponent_description");
				bomComponent.setDescription(description);
			}
			if (incomingRequest.containsKey("BomComponent_unitOfMeasure"))
			{
				String unitOfMeasure = (String) incomingRequest.get("BomComponent_unitOfMeasure");
				bomComponent.setUnitOfMeasure(unitOfMeasure);
			}
			if (incomingRequest.containsKey("BomComponent_usageQty"))
			{
				String usageQtyString = (String) incomingRequest.get("BomComponent_usageQty");
				if (Utility.isEmpty(usageQtyString))
				{
					usageQtyString = "0";
				}
				BigDecimal usageQty = new BigDecimal ( usageQtyString );
				bomComponent.setUsageQty(usageQty);
			}
			if (incomingRequest.containsKey("BomComponent_estCost"))
			{
				String estCostString = (String) incomingRequest.get("BomComponent_estCost");
				if (Utility.isEmpty(estCostString))
				{
					estCostString = "0";
				}
				BigDecimal estCost = new BigDecimal ( estCostString );
				bomComponent.setEstCost(estCost);
			}
			if (incomingRequest.containsKey("BomComponent_scrapPerc"))
			{
				String scrapPercString = (String) incomingRequest.get("BomComponent_scrapPerc");
				if (Utility.isEmpty(scrapPercString))
				{
					scrapPercString = "0";
				}
				BigDecimal scrapPerc = new BigDecimal ( scrapPercString );
				bomComponent.setScrapPerc(scrapPerc);
			}
			if (incomingRequest.containsKey("BomComponent_fromDate"))
			{
				String fromDateString = (String) incomingRequest.get("BomComponent_fromDate");
				Date fromDate = Dates.getDate(fromDateString);
				bomComponent.setFromDate(fromDate);
			}
			if (incomingRequest.containsKey("BomComponent_thruDate"))
			{
				String thruDateString = (String) incomingRequest.get("BomComponent_thruDate");
				Date thruDate = Dates.getDate(thruDateString);
				bomComponent.setThruDate(thruDate);
			}
			if (incomingRequest.containsKey("BomComponent_methodId"))
			{
				String methodId = (String) incomingRequest.get("BomComponent_methodId");
				bomComponent.setMethodId(methodId);
			}
			if (incomingRequest.containsKey("BomComponent_invtype"))
			{
				String invtype = (String) incomingRequest.get("BomComponent_invtype");
				bomComponent.setInvtype(invtype);
			}
			if (incomingRequest.containsKey("BomComponent_notes"))
			{
				String notes = (String) incomingRequest.get("BomComponent_notes");
				bomComponent.setNotes(notes);
			}
			if (incomingRequest.containsKey("BomComponent_stageId"))
			{
				String stageId = (String) incomingRequest.get("BomComponent_stageId");
				bomComponent.setStageId(stageId);
			}
			if (incomingRequest.containsKey("BomComponent_descType"))
			{
				String descType = (String) incomingRequest.get("BomComponent_descType");
				bomComponent.setDescType(descType);
			}
			if (incomingRequest.containsKey("BomComponent_fixOrvar"))
			{
				String fixOrvar = (String) incomingRequest.get("BomComponent_fixOrvar");
				bomComponent.setFixOrvar(fixOrvar);
			}
			if (incomingRequest.containsKey("BomComponent_taxAc"))
			{
				String taxAc = (String) incomingRequest.get("BomComponent_taxAc");
				bomComponent.setTaxAc(taxAc);
			}
			if (incomingRequest.containsKey("BomComponent_inOut"))
			{
				String inOut = (String) incomingRequest.get("BomComponent_inOut");
				bomComponent.setInOut(inOut);
			}
			if (incomingRequest.containsKey("BomComponent_unitPrice"))
			{
				String unitPriceString = (String) incomingRequest.get("BomComponent_unitPrice");
				if (Utility.isEmpty(unitPriceString))
				{
					unitPriceString = "0";
				}
				BigDecimal unitPrice = new BigDecimal ( unitPriceString );
				bomComponent.setUnitPrice(unitPrice);
			}
			if (incomingRequest.containsKey("BomComponent_unitCost"))
			{
				String unitCostString = (String) incomingRequest.get("BomComponent_unitCost");
				if (Utility.isEmpty(unitCostString))
				{
					unitCostString = "0";
				}
				BigDecimal unitCost = new BigDecimal ( unitCostString );
				bomComponent.setUnitCost(unitCost);
			}
			if (incomingRequest.containsKey("BomComponent_primaryRes"))
			{
				String primaryRes = (String) incomingRequest.get("BomComponent_primaryRes");
				bomComponent.setPrimaryRes(primaryRes);
			}
			if (incomingRequest.containsKey("BomComponent_operName"))
			{
				String operName = (String) incomingRequest.get("BomComponent_operName");
				bomComponent.setOperName(operName);
			}
			if (incomingRequest.containsKey("BomComponent_featureSl"))
			{
				String featureSl = (String) incomingRequest.get("BomComponent_featureSl");
				bomComponent.setFeatureSl(featureSl);
			}
			if (incomingRequest.containsKey("BomComponent_costRatio"))
			{
				String costRatioString = (String) incomingRequest.get("BomComponent_costRatio");
				if (Utility.isEmpty(costRatioString))
				{
					costRatioString = "0";
				}
				BigDecimal costRatio = new BigDecimal ( costRatioString );
				bomComponent.setCostRatio(costRatio);
			}
			if (incomingRequest.containsKey("BomComponent_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("BomComponent_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				bomComponent.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("BomComponent_owner"))
			{
				String owner = (String) incomingRequest.get("BomComponent_owner");
				bomComponent.setOwner(owner);
			}

			result = bomComponent;
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