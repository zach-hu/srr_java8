package com.tsa.puridiom.invlocation.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.entity.InvLocationPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class InvLocationSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvLocationPK comp_id = new InvLocationPK();
			InvLocation invLocation = (InvLocation) incomingRequest.get("invLocation");
			List<InvLocation> invLocList = (List)incomingRequest.get("invLocList");
			if(invLocList == null) invLocList = new ArrayList();
			String message = "";
			boolean test = false;


			if (invLocation == null)
			{
				invLocation = new InvLocation();
			}

			if (incomingRequest.containsKey("InvLocation_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvLocation_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvLocation_itemLocation"))
			{

				String itemLocation = (String ) incomingRequest.get("InvLocation_itemLocation");
				for (int i = 0; i < invLocList.size(); i++)
				{
					if (itemLocation.equals(invLocList.get(i).getComp_id().getItemLocation()))
					{
						test = true;
						break;
					}
				}

				if (!test){
					comp_id.setItemLocation(itemLocation);
				}


			}
			if (incomingRequest.containsKey("InvLocation_qtyOnHand"))
			{
				String qtyOnHandString = (String) incomingRequest.get("InvLocation_qtyOnHand");
				if (Utility.isEmpty(qtyOnHandString))
				{
					qtyOnHandString = "0";
				}
				BigDecimal qtyOnHand = new BigDecimal ( qtyOnHandString );
				invLocation.setQtyOnHand(qtyOnHand);
			}
			if (incomingRequest.containsKey("InvLocation_qtyOnOrder"))
			{
				String qtyOnOrderString = (String) incomingRequest.get("InvLocation_qtyOnOrder");
				if (Utility.isEmpty(qtyOnOrderString))
				{
					qtyOnOrderString = "0";
				}
				BigDecimal qtyOnOrder = new BigDecimal ( qtyOnOrderString );
				invLocation.setQtyOnOrder(qtyOnOrder);
			}
			if (incomingRequest.containsKey("InvLocation_minOnHand"))
			{
				String minOnHandString = (String) incomingRequest.get("InvLocation_minOnHand");
				if (Utility.isEmpty(minOnHandString))
				{
					minOnHandString = "0";
				}
				BigDecimal minOnHand = new BigDecimal ( minOnHandString );
				invLocation.setMinOnHand(minOnHand);
			}
			if (incomingRequest.containsKey("InvLocation_maxOnHand"))
			{
				String maxOnHandString = (String) incomingRequest.get("InvLocation_maxOnHand");
				if (Utility.isEmpty(maxOnHandString))
				{
					maxOnHandString = "0";
				}
				BigDecimal maxOnHand = new BigDecimal ( maxOnHandString );
				invLocation.setMaxOnHand(maxOnHand);
			}
			if (incomingRequest.containsKey("InvLocation_qtyEoq"))
			{
				String qtyEoqString = (String) incomingRequest.get("InvLocation_qtyEoq");
				if (Utility.isEmpty(qtyEoqString))
				{
					qtyEoqString = "0";
				}
				BigDecimal qtyEoq = new BigDecimal ( qtyEoqString );
				invLocation.setQtyEoq(qtyEoq);
			}
			if (incomingRequest.containsKey("InvLocation_qtyEsq"))
			{
				String qtyEsqString = (String) incomingRequest.get("InvLocation_qtyEsq");
				if (Utility.isEmpty(qtyEsqString))
				{
					qtyEsqString = "0";
				}
				BigDecimal qtyEsq = new BigDecimal ( qtyEsqString );
				invLocation.setQtyEsq(qtyEsq);
			}
			if (incomingRequest.containsKey("InvLocation_averageCost"))
			{
				String averageCostString = (String) incomingRequest.get("InvLocation_averageCost");
				if (Utility.isEmpty(averageCostString))
				{
					averageCostString = "0";
				}
				BigDecimal averageCost = new BigDecimal ( averageCostString );
				invLocation.setAverageCost(averageCost);
			}
			if (incomingRequest.containsKey("InvLocation_udf1Code"))
			{
				String udf1Code = (String ) incomingRequest.get("InvLocation_udf1Code");
				invLocation.setUdf1Code(udf1Code);
			}
			if (incomingRequest.containsKey("InvLocation_udf2Code"))
			{
				String udf2Code = (String ) incomingRequest.get("InvLocation_udf2Code");
				invLocation.setUdf2Code(udf2Code);
			}
			if (incomingRequest.containsKey("InvLocation_udf3Code"))
			{
				String udf3Code = (String ) incomingRequest.get("InvLocation_udf3Code");
				invLocation.setUdf3Code(udf3Code);
			}
			if (incomingRequest.containsKey("InvLocation_udf4Code"))
			{
				String udf4Code = (String ) incomingRequest.get("InvLocation_udf4Code");
				invLocation.setUdf4Code(udf4Code);
			}
			if (incomingRequest.containsKey("InvLocation_udf5Code"))
			{
				String udf5Code = (String ) incomingRequest.get("InvLocation_udf5Code");
				invLocation.setUdf5Code(udf5Code);
			}
			if (incomingRequest.containsKey("InvLocation_qtyAlloc"))
			{
				String qtyAllocString = (String) incomingRequest.get("InvLocation_qtyAlloc");
				if (Utility.isEmpty(qtyAllocString))
				{
					qtyAllocString = "0";
				}
				BigDecimal qtyAlloc = new BigDecimal ( qtyAllocString );
				invLocation.setQtyAlloc(qtyAlloc);
			}
			if (incomingRequest.containsKey("InvLocation_icInvAccount"))
			{
				String icInvAccountString = (String) incomingRequest.get("InvLocation_icInvAccount");
				if (Utility.isEmpty(icInvAccountString))
				{
					icInvAccountString = "0";
				}
				BigDecimal icInvAccount = new BigDecimal ( icInvAccountString );
				invLocation.setIcInvAccount(icInvAccount);
			}
			if (incomingRequest.containsKey("InvLocation_icInvHeader"))
			{
				String icInvHeaderString = (String) incomingRequest.get("InvLocation_icInvHeader");
				if (Utility.isEmpty(icInvHeaderString))
				{
					icInvHeaderString = "0";
				}
				BigDecimal icInvHeader = new BigDecimal ( icInvHeaderString );
				invLocation.setIcInvHeader(icInvHeader);
			}
			if (incomingRequest.containsKey("InvLocation_qtyRequested"))
			{
				String qtyRequestedString = (String) incomingRequest.get("InvLocation_qtyRequested");
				if (Utility.isEmpty(qtyRequestedString))
				{
					qtyRequestedString = "0";
				}
				BigDecimal qtyRequested = new BigDecimal ( qtyRequestedString );
				invLocation.setQtyRequested(qtyRequested);
			}
			if (incomingRequest.containsKey("InvLocation_autoReplenish"))
			{
				String autoReplenish = (String ) incomingRequest.get("InvLocation_autoReplenish");
				invLocation.setAutoReplenish(autoReplenish);
			}
			if (incomingRequest.containsKey("InvLocation_physActual"))
			{
				String physActualString = (String) incomingRequest.get("InvLocation_physActual");
				if (Utility.isEmpty(physActualString))
				{
					physActualString = "0";
				}
				BigDecimal physActual = new BigDecimal ( physActualString );
				invLocation.setPhysActual(physActual);
			}
			if (incomingRequest.containsKey("InvLocation_physOriginal"))
			{
				String physOriginalString = (String) incomingRequest.get("InvLocation_physOriginal");
				if (Utility.isEmpty(physOriginalString))
				{
					physOriginalString = "0";
				}
				BigDecimal physOriginal = new BigDecimal ( physOriginalString );
				invLocation.setPhysOriginal(physOriginal);
			}
			if (incomingRequest.containsKey("InvLocation_primeLocation"))
			{
				String primeLocation = (String ) incomingRequest.get("InvLocation_primeLocation");
				invLocation.setPrimeLocation(primeLocation);
			}
			if (incomingRequest.containsKey("InvLocation_physAlloc"))
			{
				String physAllocString = (String) incomingRequest.get("InvLocation_physAlloc");
				if (Utility.isEmpty(physAllocString))
				{
					physAllocString = "0";
				}
				BigDecimal physAlloc = new BigDecimal ( physAllocString );
				invLocation.setPhysAlloc(physAlloc);
			}
			if (incomingRequest.containsKey("InvLocation_originalAlloc"))
			{
				String originalAllocString = (String) incomingRequest.get("InvLocation_originalAlloc");
				if (Utility.isEmpty(originalAllocString))
				{
					originalAllocString = "0";
				}
				BigDecimal originalAlloc = new BigDecimal ( originalAllocString );
				invLocation.setOriginalAlloc(originalAlloc);
			}
			if (incomingRequest.containsKey("InvLocation_qtyPendOrder"))
			{
				String qtyPendOrderString = (String) incomingRequest.get("InvLocation_qtyPendOrder");
				if (Utility.isEmpty(qtyPendOrderString))
				{
					qtyPendOrderString = "0";
				}
				BigDecimal qtyPendOrder = new BigDecimal ( qtyPendOrderString );
				invLocation.setQtyPendOrder(qtyPendOrder);
			}
			if (incomingRequest.containsKey("InvLocation_duomQtyOnHand"))
			{
				String qtyOnHandString = (String) incomingRequest.get("InvLocation_duomQtyOnHand");
				if (Utility.isEmpty(qtyOnHandString))
				{
					qtyOnHandString = "0";
				}
				BigDecimal qtyOnHand = new BigDecimal ( qtyOnHandString );
				invLocation.setDuomQtyOnHand(qtyOnHand);
			}
			if (incomingRequest.containsKey("InvLocation_duomQtyAlloc"))
			{
				String qtyAllocString = (String) incomingRequest.get("InvLocation_duomQtyAlloc");
				if (Utility.isEmpty(qtyAllocString))
				{
					qtyAllocString = "0";
				}
				BigDecimal qtyAlloc = new BigDecimal ( qtyAllocString );
				invLocation.setDuomQtyAlloc(qtyAlloc);
			}
			if (incomingRequest.containsKey("InvLocation_duomPhysActual"))
			{
				String physActualString = (String) incomingRequest.get("InvLocation_duomPhysActual");
				if (Utility.isEmpty(physActualString))
				{
					physActualString = "0";
				}
				BigDecimal physActual = new BigDecimal ( physActualString );
				invLocation.setDuomPhysActual(physActual);
			}
			if (incomingRequest.containsKey("InvLocation_duomPhysOriginal"))
			{
				String physOriginalString = (String) incomingRequest.get("InvLocation_duomPhysOriginal");
				if (Utility.isEmpty(physOriginalString))
				{
					physOriginalString = "0";
				}
				BigDecimal physOriginal = new BigDecimal ( physOriginalString );
				invLocation.setDuomPhysOriginal(physOriginal);
			}

			invLocation.setComp_id(comp_id);

			result = invLocation;
			if (!test){
				this.status = Status.SUCCEEDED;
			}else{
				if (incomingRequest.containsKey("errorMsg")) {
					message = (String) incomingRequest.get("errorMsg") + "  ";
				}
				message = message + " This item already has this location set up!";
				incomingRequest.put("errorMsg", message);
				result = message;
				this.status = Status.FAILED;
			}
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}