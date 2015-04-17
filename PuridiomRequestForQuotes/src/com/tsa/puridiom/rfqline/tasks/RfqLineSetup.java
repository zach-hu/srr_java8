package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.UnitOfMeasure;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.unitofmeasure.tasks.UnitOfMeasureRetrieveById;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

import java.util.Map;

public class RfqLineSetup extends Task {

	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String	organizationId = (String) incomingRequest.get("organizationId") ;
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId) ;

			String taxable = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","TAXABLE","Y");
			String commodityCode = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","COMMODITYCODE","");
			String uom = propertiesManager.getProperty("NON-STANDARD ITEM DEFAULTS","UMCODE","EA");
			String umFactor = "1";
			
			if (!Utility.isEmpty(uom)) {
				try {
					UnitOfMeasureRetrieveById uomRetrieve = new UnitOfMeasureRetrieveById();
					Map params = this.getDefaultUpdateParameters(incomingRequest);
					UnitOfMeasure unitOfMeasure = (UnitOfMeasure) uomRetrieve.executeTask(params);
					if (unitOfMeasure != null) {
						umFactor = String.valueOf(unitOfMeasure.getFactor());
					}
				} catch (Exception e1) {
					umFactor = "1";
				}
			}

			incomingRequest.put("RfqLine_icRfqLine", ukg.getUniqueKey().toString());
			incomingRequest.put("RfqLine_icRfqHeader", "0");
			//incomingRequest.put("RfqLine_rfqLine", "0");
			incomingRequest.put("RfqLine_rfqNumber", "");
			incomingRequest.put("RfqLine_source", "");
			incomingRequest.put("RfqLine_itemNumber", "");
			incomingRequest.put("RfqLine_umCode", uom);
			incomingRequest.put("RfqLine_quantity", "1");
			incomingRequest.put("RfqLine_taxable", taxable);
			incomingRequest.put("RfqLine_status", DocumentStatus.RFQ_INPROGRESS);
			incomingRequest.put("RfqLine_commodity", commodityCode);
			incomingRequest.put("RfqLine_icSource", "0");
			incomingRequest.put("RfqLine_commentFlag", "");
			incomingRequest.put("RfqLine_asset", "N");
			incomingRequest.put("RfqLine_splits", "");
			incomingRequest.put("RfqLine_catalogId", "");
			incomingRequest.put("RfqLine_umFactor", umFactor);
			incomingRequest.put("RfqLine_icReqLine", "0");
			incomingRequest.put("RfqLine_shiptoFlag", "");
			incomingRequest.put("RfqLine_allocated", "0");
			incomingRequest.put("RfqLine_mfgName", "");
			incomingRequest.put("RfqLine_modelNumber", "");
			incomingRequest.put("RfqLine_udf1Code", "");
			incomingRequest.put("RfqLine_udf2Code", "");
			incomingRequest.put("RfqLine_udf3Code", "");
			incomingRequest.put("RfqLine_udf4Code", "");
			incomingRequest.put("RfqLine_udf5Code", "");
            incomingRequest.put("RfqLine_udf6Code", "");
            incomingRequest.put("RfqLine_udf7Code", "");
            incomingRequest.put("RfqLine_udf8Code", "");
            incomingRequest.put("RfqLine_udf9Code", "");
            incomingRequest.put("RfqLine_udf10Code", "");
            incomingRequest.put("RfqLine_memoLine", "");

			incomingRequest.put("RfqLine_lineRevNo", "");
			incomingRequest.put("RfqLine_icLineHistory", ukg.getUniqueKey().toString());
			incomingRequest.put("RfqLine_itemLocation", "");
			incomingRequest.put("RfqLine_description", "");
			incomingRequest.put("RfqLine_taxCode", "");
			incomingRequest.put("RfqLine_routing", "");
			incomingRequest.put("RfqLine_receiptRequired", "");
			incomingRequest.put("RfqLine_icAccount", "0");
			incomingRequest.put("RfqLine_vendorAwarded", "");
			incomingRequest.put("RfqLine_icXls", "0");

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e) {
			this.status = Status.FAILED;
			throw e;
		}

		return null ;
	}
}
