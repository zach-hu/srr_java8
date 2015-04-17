/**
 *
 * Created on Feb 9, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.inventory.tasks.BinSetup.java
 *
 */
package com.tsa.puridiom.inventory.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class BinSetupPreview extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List binLocations = (List)incomingRequest.get("invBinLocations");
			BigDecimal qtyToDisurse = (BigDecimal)incomingRequest.get("qtyToDisburse");
			BigDecimal totalQtyOnHand = new BigDecimal(0);
			BigDecimal totalQtyAlloc = new BigDecimal(0);
			List binQties = new ArrayList();
			List binsUsed = new ArrayList();
			InvBinLocation tempBin = null ;

			if (binLocations.size() == 0) {
				InvBinLocation bin = new InvBinLocation() ;
				binLocations.add(bin) ;
			}

			int cnt = 0;
			for(int i = 0; i < binLocations.size(); i++) {
				InvBinLocation binLocation = (InvBinLocation)binLocations.get(i);
				BigDecimal qtyOnhand = binLocation.getQtyOnHand();
				BigDecimal qtyAlloc = binLocation.getQtyAlloc();

				if(qtyOnhand == null){	qtyOnhand = new BigDecimal(0);	}
				if (qtyAlloc == null){	qtyAlloc = new BigDecimal(0);		}

				BigDecimal qtyAvailable = qtyOnhand.subtract(qtyAlloc);
				BigDecimal binQty = new BigDecimal(0);
				if(qtyAvailable.compareTo(qtyToDisurse) > -1)
				{
					binQty = qtyToDisurse;
				}
				else
				{
					binQty = qtyAvailable;
				}
				if (binQty.compareTo(new BigDecimal(0))> 0) {
					cnt = 1;
				}
			}

			for(int i = 0; i < binLocations.size(); i++)
			{
				InvBinLocation binLocation = (InvBinLocation)binLocations.get(i);
				BigDecimal qtyOnhand = binLocation.getQtyOnHand();
				BigDecimal qtyAlloc = binLocation.getQtyAlloc();

				if(qtyOnhand == null){	qtyOnhand = new BigDecimal(0);	}
				if (qtyAlloc == null){	qtyAlloc = new BigDecimal(0);		}

				BigDecimal qtyAvailable = qtyOnhand.subtract(qtyAlloc);
				if(qtyAvailable.compareTo(new BigDecimal(0)) < 0)
				{
					qtyAvailable = new BigDecimal(0);
				}

				BigDecimal binQty = new BigDecimal(0);
				if(qtyAvailable.compareTo(qtyToDisurse) > -1)
				{
					binQty = qtyToDisurse;
				}
				else
				{
					binQty = qtyAvailable;
				}
				if (binQty.compareTo(new BigDecimal(0))> 0 || cnt == 0) {
					cnt++ ;
					binQties.add(binQty);
					qtyToDisurse = qtyToDisurse.subtract(binQty);

					binLocations.set(i, binLocation);
					binsUsed.add(binLocation);
					if(qtyToDisurse.compareTo(new BigDecimal(0)) < 1 )
					{
						break;
					}
				}
			}
			incomingRequest.put("invBinLocations", binsUsed);
			incomingRequest.put("invBinQties", binQties);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
