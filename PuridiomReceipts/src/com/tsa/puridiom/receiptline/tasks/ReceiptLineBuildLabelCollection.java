/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvProperty;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility ;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptLineBuildLabelCollection extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		List   receiptLineList = (List) incomingRequest.get("receiptLineList") ;
		List	dataList = new ArrayList() ;
		String	assetNumber = (String) incomingRequest.get("InvProperty_assetNumber") ;

		for (int ix = 0; ix < receiptLineList.size(); ix++) {
			ReceiptLine rcl = (ReceiptLine) receiptLineList.get(ix) ;
			PoLine pol = rcl.getPoLine() ;
			PoHeader poh = rcl.getPoHeader() ;
			List ivpList = rcl.getInvPropertyList() ;

			if (ivpList == null) ivpList = new ArrayList() ;
//			if (ivpList.size() == 0) ivpList.add(new InvProperty()) ;

			for (int iy = 0; iy < ivpList.size(); iy++) {
				InvProperty ivp = (InvProperty) ivpList.get(iy) ;

				Map record = new Hashtable() ;
				record.put("PoLine_description", pol.getDescription()) ;
				record.put("PoLine_itemNumber", pol.getItemNumber()) ;
				record.put("PoHeader_vendorName", poh.getVendorName()) ;
				record.put("PoHeader_poNumber", poh.getPoNumber()) ;
				record.put("PoLine_altItemNumber", pol.getAltItemNumber()) ;
				record.put("PoHeader_departmentCode", poh.getDepartmentCode()) ;
				record.put("PoLine_udf5Code", pol.getUdf5Code()) ;
				record.put("InvProperty_tagNumber", ivp.getTagNumber()) ;
				record.put("InvProperty_serialNumber", ivp.getSerialNumber()) ;
				record.put("InvProperty_assetNumber", ivp.getAssetNumber()) ;

				if (ivp.getAssetNumber() != null) {
					if (assetNumber == null) {
						dataList.add(record) ;
					} else {
						if (assetNumber.equals(ivp.getAssetNumber())) {
							dataList.add(record) ;
						}
					}
				}
			}
		}
		this.setStatus(Status.SUCCEEDED) ;

		return dataList ;
	}
}
