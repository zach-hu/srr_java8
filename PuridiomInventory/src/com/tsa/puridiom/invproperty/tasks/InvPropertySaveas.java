package com.tsa.puridiom.invproperty.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvPropertySaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invProperty */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvProperty	originalInvProperty = (InvProperty) incomingRequest.get("invProperty");
			InvProperty	invProperty = new InvProperty();

			invProperty.setIcProperty(originalInvProperty.getIcProperty());
			invProperty.setPropertyType(originalInvProperty.getPropertyType());
			invProperty.setTagNumber(originalInvProperty.getTagNumber());
			invProperty.setIcRc(originalInvProperty.getIcRc());
			invProperty.setItemNumber(originalInvProperty.getItemNumber());
			invProperty.setSerialNumber(originalInvProperty.getSerialNumber());
			invProperty.setPoNumber(originalInvProperty.getPoNumber());
			invProperty.setContract(originalInvProperty.getContract());
			invProperty.setReceiptNumber(originalInvProperty.getReceiptNumber());
			invProperty.setShipNumber(originalInvProperty.getShipNumber());
			invProperty.setRemarks(originalInvProperty.getRemarks());
			invProperty.setDateIn(originalInvProperty.getDateIn());
			invProperty.setDateOut(originalInvProperty.getDateOut());
			invProperty.setStatus(originalInvProperty.getStatus());
			invProperty.setOwner(originalInvProperty.getOwner());
			invProperty.setCblOutNumber(originalInvProperty.getCblOutNumber()) ;
			invProperty.setArmyNumber(originalInvProperty.getArmyNumber()) ;
			invProperty.setIcRecLine(originalInvProperty.getIcRecLine()) ;
			invProperty.setIcPoLine(originalInvProperty.getIcPoLine()) ;
			invProperty.setAssetNumber(originalInvProperty.getAssetNumber()) ;
			invProperty.setSource(originalInvProperty.getSource()) ;

			incomingRequest.put("invProperty", invProperty);

			InvPropertyAdd addTask = new InvPropertyAdd();
			invProperty = (InvProperty) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = invProperty;
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