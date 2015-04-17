package com.tsa.puridiom.invbinlocation.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvBinLocationBatchUpdatePhysActual extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		this.setStatus(Status.SUCCEEDED) ;
		try
		{
			PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
			String 	s_duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
			
			if(incomingRequest.get("InvBinLocation_icRc_Array") instanceof String)
			{
				String InvBinLocation_icRc_Array = (String) incomingRequest.get("InvBinLocation_icRc_Array");
				String InvBinLocation_physActual_Array = (String) incomingRequest.get("InvBinLocation_physActual_Array");
				String InvBinLocation_duomPhysActual_Array = (String) incomingRequest.get("InvBinLocation_duomPhysActual_Array");

				if (incomingRequest.containsKey("InvBinLocation_itemNumber")) {
					// don't wipe out item number
					incomingRequest.remove("InvBinLocation_itemNumber") ;
				}
				//String	changed = (String) incomingRequest.get("changed");

				//if (changed.equalsIgnoreCase("Y")) {
					incomingRequest.put("InvBinLocation_icRc",InvBinLocation_icRc_Array);
					incomingRequest.put("InvBinLocation_physActual",InvBinLocation_physActual_Array);
					incomingRequest.put("InvBinLocation_qtyOnHand",InvBinLocation_physActual_Array);
					if (s_duomRequired.equalsIgnoreCase("Y")) {
						incomingRequest.put("InvBinLocation_duomPhysActual",InvBinLocation_duomPhysActual_Array);
						incomingRequest.put("InvBinLocation_duomQtyOnHand", InvBinLocation_duomPhysActual_Array) ;
					}
					
					if (incomingRequest.containsKey("InvBinLocation_itemNumber")) {
							// don't wipe out item number
							incomingRequest.remove("InvBinLocation_itemNumber") ;
					}
					PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
					PuridiomProcess process = processLoader.loadProcess("invbinlocation-update.xml");
					process.executeProcess(incomingRequest);

					this.setStatus(process.getStatus());
				//}
			}
			else
			{
				String[] InvBinLocation_icRc_Array = (String[]) incomingRequest.get("InvBinLocation_icRc_Array");
				String[] InvBinLocation_physActual_Array = (String[]) incomingRequest.get("InvBinLocation_physActual_Array");
				String[] InvBinLocation_duomPhysActual_Array = (String[]) incomingRequest.get("InvBinLocation_duomPhysActual_Array");
				String[] changed_Array = (String[]) incomingRequest.get("changed");

				if (incomingRequest.containsKey("InvBinLocation_itemNumber")) {
					// don't wipe out item number
					incomingRequest.remove("InvBinLocation_itemNumber") ;
				}
				for (int i=0; i < InvBinLocation_icRc_Array.length; i++)
				{
					String	changed = changed_Array[i];
					if ( (!HiltonUtility.isEmpty(changed) && changed.equalsIgnoreCase("Y"))) {
						incomingRequest.put("InvBinLocation_icRc",InvBinLocation_icRc_Array[i]);
						incomingRequest.put("InvBinLocation_physActual",InvBinLocation_physActual_Array[i]);
						incomingRequest.put("InvBinLocation_qtyOnHand",InvBinLocation_physActual_Array[i]);
						if (s_duomRequired.equalsIgnoreCase("Y")) {
							incomingRequest.put("InvBinLocation_duomPhysActual", InvBinLocation_duomPhysActual_Array[i]) ;
							incomingRequest.put("InvBinLocation_duomQtyOnHand",InvBinLocation_duomPhysActual_Array[i]);
						}
						if (incomingRequest.containsKey("InvBinLocation_itemNumber")) {
							// don't wipe out item number
							incomingRequest.remove("InvBinLocation_itemNumber") ;
						}
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
						PuridiomProcess process = processLoader.loadProcess("invbinlocation-update.xml");
						process.executeProcess(incomingRequest);

						this.setStatus(process.getStatus());
					}
				}

			}

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}