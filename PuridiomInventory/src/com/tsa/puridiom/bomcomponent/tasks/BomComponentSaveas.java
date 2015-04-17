package com.tsa.puridiom.bomcomponent.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class BomComponentSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain bomComponent */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			BomComponent	originalBomComponent = (BomComponent) incomingRequest.get("bomComponent");
			BomComponent	bomComponent = new BomComponent();

			bomComponent.setIcComponent(originalBomComponent.getIcComponent());
			bomComponent.setParentItem(originalBomComponent.getParentItem());
			bomComponent.setComponentItem(originalBomComponent.getComponentItem());
			bomComponent.setComponentType(originalBomComponent.getComponentType());
			bomComponent.setBomLine(originalBomComponent.getBomLine());
			bomComponent.setDescription(originalBomComponent.getDescription());
			bomComponent.setUnitOfMeasure(originalBomComponent.getUnitOfMeasure());
			bomComponent.setUsageQty(originalBomComponent.getUsageQty());
			bomComponent.setEstCost(originalBomComponent.getEstCost());
			bomComponent.setScrapPerc(originalBomComponent.getScrapPerc());
			bomComponent.setFromDate(originalBomComponent.getFromDate());
			bomComponent.setThruDate(originalBomComponent.getThruDate());
			bomComponent.setMethodId(originalBomComponent.getMethodId());
			bomComponent.setInvtype(originalBomComponent.getInvtype());
			bomComponent.setNotes(originalBomComponent.getNotes());
			bomComponent.setStageId(originalBomComponent.getStageId());
			bomComponent.setDescType(originalBomComponent.getDescType());
			bomComponent.setFixOrvar(originalBomComponent.getFixOrvar());
			bomComponent.setTaxAc(originalBomComponent.getTaxAc());
			bomComponent.setInOut(originalBomComponent.getInOut());
			bomComponent.setUnitPrice(originalBomComponent.getUnitPrice());
			bomComponent.setUnitCost(originalBomComponent.getUnitCost());
			bomComponent.setPrimaryRes(originalBomComponent.getPrimaryRes());
			bomComponent.setOperName(originalBomComponent.getOperName());
			bomComponent.setFeatureSl(originalBomComponent.getFeatureSl());
			bomComponent.setCostRatio(originalBomComponent.getCostRatio());
			bomComponent.setDateEntered(originalBomComponent.getDateEntered());
			bomComponent.setOwner(originalBomComponent.getOwner());

			incomingRequest.put("bomComponent", bomComponent);

			BomComponentAdd addTask = new BomComponentAdd();
			bomComponent = (BomComponent) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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