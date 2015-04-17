package com.tsa.puridiom.invbinlocation.tasks;

import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvBinLocationSetup extends Task
{
	public void executeTask(Map incomingRequest) throws Exception
	{
		incomingRequest.put("InvBinLocation.icRc", "0");
		incomingRequest.put("InvBinLocation.itemNumber", "");
		incomingRequest.put("InvBinLocation.itemLocation", "");
		incomingRequest.put("InvBinLocation.vendorId", "");
		incomingRequest.put("InvBinLocation.manufactNo", "");
		incomingRequest.put("InvBinLocation.lot", "");
		incomingRequest.put("InvBinLocation.cost", "0");
		incomingRequest.put("InvBinLocation.itemDate", "2003-11-12");
		incomingRequest.put("InvBinLocation.aisle", "");
		incomingRequest.put("InvBinLocation.locrow", "");
		incomingRequest.put("InvBinLocation.tier", "");
		incomingRequest.put("InvBinLocation.bin", "");
		incomingRequest.put("InvBinLocation.udf1Code", "");
		incomingRequest.put("InvBinLocation.udf2Code", "");
		incomingRequest.put("InvBinLocation.udf3Code", "");
		incomingRequest.put("InvBinLocation.udf4Code", "");
		incomingRequest.put("InvBinLocation.udf5Code", "");
		incomingRequest.put("InvBinLocation.owner", "") ;
		incomingRequest.put("InvBinLocation.qtyOnHand", "0");
		incomingRequest.put("InvBinLocation.qtyAlloc", "0");
		incomingRequest.put("InvBinLocation.hdrIc", "");
		incomingRequest.put("InvBinLocation.icRecLine", "0");
		incomingRequest.put("InvBinLocation.physActual", "0");
		incomingRequest.put("InvBinLocation.physOriginal", "0");
		incomingRequest.put("InvBinLocation.originalAlloc", "0");
		incomingRequest.put("InvBinLocation_status", "");
		incomingRequest.put("InvBinLocation_tempIc", "0");
		incomingRequest.put("InvBinLocation_source", "INV") ;
	}
}
