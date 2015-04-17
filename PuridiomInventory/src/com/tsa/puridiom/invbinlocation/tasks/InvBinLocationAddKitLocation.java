package com.tsa.puridiom.invbinlocation.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

@SuppressWarnings(value = { "unchecked" })
public class InvBinLocationAddKitLocation extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object result = null;
		try
		{
			int j = 0;
			boolean lot_enable = false;
			Map incomingRequest = (Map)object;
			DBSession dbSesion = (DBSession)incomingRequest.get("dbsession") ;
            String organizationId = (String)incomingRequest.get("organizationId");
            List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
            
            InvBinLocation invBinLocation = new InvBinLocation();
            
            String itemLocation = (String)incomingRequest.get("InvBinLocation_itemLocation");
            String itemDate = (String)incomingRequest.get("InvBinLocation_itemDate");
            String aisle = (String)incomingRequest.get("InvBinLocation_aisle");
            String locrow = (String)incomingRequest.get("InvBinLocation_locrow");
            String tier = (String)incomingRequest.get("InvBinLocation_tier");
            String bin = (String)incomingRequest.get("InvBinLocation_bin");
            String lot = (String)incomingRequest.get("InvBinLocation_lot");
            String udf2Code = (String)incomingRequest.get("InvBinLocation_udf2Code");

            List invBinLocList = (List) incomingRequest.get("invBinLocationList");
            
            if(!HiltonUtility.isEmpty(aisle) && !HiltonUtility.isEmpty(locrow) && !HiltonUtility.isEmpty(tier) && !HiltonUtility.isEmpty(bin)){
            	j = invBinLocList.size() - 1;
            	lot_enable = true;
            }
            
            for(; j<invBinLocList.size(); j++){
           		invBinLocation = (InvBinLocation)invBinLocList.get(j);
           		if(invBinLocation.getUdf3Code().equals(((RequisitionLine)requisitionLineList.get(0)).getRequisitionNumber())){
           			if(HiltonUtility.isEmpty(itemLocation)){
           				itemLocation = invBinLocation.getItemLocation();
           			}
           			if(HiltonUtility.isEmpty(itemDate)){
           				itemDate = invBinLocation.getItemDate().toString();
           			}
           			if(HiltonUtility.isEmpty(aisle)){
           				aisle = invBinLocation.getAisle();
           			}
           			if(HiltonUtility.isEmpty(locrow)){
           				locrow = invBinLocation.getLocrow();
           			}
           			if(HiltonUtility.isEmpty(tier)){
           				tier = invBinLocation.getTier();
           			}
           			if(HiltonUtility.isEmpty(bin)){
           				bin = invBinLocation.getBin();
           			}
           			if(HiltonUtility.isEmpty(lot) && !lot_enable){
           				lot = invBinLocation.getLot();
           			}
           			if(HiltonUtility.isEmpty(udf2Code)){
           				udf2Code = invBinLocation.getUdf2Code();
           			}

           			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
           			PuridiomProcess process = processLoader.loadProcess("invitem-retrieve-by-id-msrline.xml");

           			for (int i = 0; i < requisitionLineList.size(); i++) {
           				RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
           				Map newIncomingRequest = new HashMap();
           				newIncomingRequest.put("dbsession", dbSesion);
           				newIncomingRequest.put("organizationId", organizationId);
           				newIncomingRequest.put("InvItem_itemNumber", requisitionLine.getItemNumber());
           				newIncomingRequest.put("InvLocation_itemNumber", requisitionLine.getItemNumber());
           				newIncomingRequest.put("InvLocation_itemLocation", requisitionLine.getItemLocation());
           				newIncomingRequest.put("InvBinLocation_itemNumber", requisitionLine.getItemNumber());
           				newIncomingRequest.put("InvBinLocation_itemLocation", requisitionLine.getItemLocation());
           				newIncomingRequest.put("InvBinLocation_icRecLine", requisitionLine.getIcReqLine().toString());
           				newIncomingRequest.put("RequisitionHeader_icReqHeader", requisitionLine.getIcReqHeader().toString());
           				newIncomingRequest.put("RequisitionLine_icReqLine", requisitionLine.getIcReqLine().toString());

           				process.executeProcess(newIncomingRequest);

           				Map invBinLocIncomingRequest = new HashMap();
           				invBinLocIncomingRequest.put("dbsession", dbSesion);
           				invBinLocIncomingRequest.put("organizationId", organizationId);
           				//				invBinLocIncomingRequest.put("InvBinLocation_icRc", "");
           				invBinLocIncomingRequest.put("InvBinLocation_itemNumber", requisitionLine.getItemNumber());
           				invBinLocIncomingRequest.put("InvBinLocation_itemLocation", itemLocation);
           				//				invBinLocIncomingRequest.put("InvBinLocation_cost", incomingRequest.get("InvBinLocation_cost"));
           				//				invBinLocIncomingRequest.put("InvBinLocation_itemDate", itemDate);
           				invBinLocIncomingRequest.put("InvBinLocation_aisle", aisle);
           				invBinLocIncomingRequest.put("InvBinLocation_locrow", locrow);
           				invBinLocIncomingRequest.put("InvBinLocation_tier", tier);
           				invBinLocIncomingRequest.put("InvBinLocation_bin", bin);
           				if(lot_enable){
           					invBinLocIncomingRequest.put("InvBinLocation_lot", lot);
           				}
           				//				invBinLocIncomingRequest.put("InvBinLocation_udf2Code", udf2Code);
           				invBinLocIncomingRequest.put("InvBinLocation_udf3Code", requisitionLine.getRequisitionNumber());
           				//				invBinLocIncomingRequest.put("InvBinLocation_qtyOnHand", incomingRequest.get("InvBinLocation_qtyOnHand"));
           				//				invBinLocIncomingRequest.put("InvBinLocation_qtyAlloc", incomingRequest.get("InvBinLocation_qtyAlloc"));

           				InvBinLocationRetrieveBy invBinLocationRetrieveTask = new InvBinLocationRetrieveBy();

           				List invBinLocationList = (List)invBinLocationRetrieveTask.executeTask(invBinLocIncomingRequest);

           				if(invBinLocationList.size() == 0){
           					PuridiomProcess invBinLocationAddProcess = processLoader.loadProcess("invbinlocation-add.xml");
           					invBinLocIncomingRequest.put("InvBinLocation_icRc", "");
           					invBinLocIncomingRequest.put("InvBinLocation_itemDate", itemDate);
           					invBinLocIncomingRequest.put("InvBinLocation_udf2Code", udf2Code);

           					invBinLocationAddProcess.executeProcess(invBinLocIncomingRequest);
           				}
           			}
           		}
            }
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
 		return result;
	}

}
