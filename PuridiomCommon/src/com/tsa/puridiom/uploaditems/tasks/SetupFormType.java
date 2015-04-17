package com.tsa.puridiom.uploaditems.tasks;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;

import java.util.ArrayList;

public class SetupFormType extends Task
{


	List listItems=new ArrayList();
    String formType=null;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		listItems= (List)incomingRequest.get("readRfqReqPoItems");
		formType = (String)incomingRequest.get("formType");
		String icHeaderValue = (String)incomingRequest.get("icHeaderValue");

		String icHeaderName = null;
		String line=null;
		String itemNumber=null;
		String description=null;
		String quantity=null;
		String umCode=null;
		String callLineProcess=null;
		String callRetrieveProcess=null;
		String unitPrice=null;
		String umFactor=null;
		String filenameXls=null;

		String itemNumberDefault="";
		String descriptionDefault="";
		String quantityDefault="1";
		String umCodeDefault="EA";
		String unitPriceDefault="0";
        String proccesFactorString = "uom-factor-retrieve.xml";

		String FileNameXLS=null;

		try
		{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		processLoader.setApplicationName(this.getApplicationName());


		FileNameXLS = ((String)incomingRequest.get("FilenameXls"));

		if (formType.equals("RFQ"))
        {

		 icHeaderName = "RfqHeader_icRfqHeader";
		 line="rfqLine";
    	 itemNumber="RfqLine_itemNumber";
    	 description="RfqLine_description";
    	 quantity="RfqLine_quantity";
    	 umCode="RfqLine_umCode";
    	 unitPrice="RfqLine_unitPrice";
    	 umFactor="RfqLine_umFactor";
    	 filenameXls="RfqLine_icXls";
    	 callLineProcess="rfqline-create-from-lookup.xml";
    	 callRetrieveProcess="rfqline-retrieve-by-header.xml";

        }

        if (formType.equals("REQ"))
        {

         icHeaderName = "RequisitionHeader_icReqHeader";
         line="requisitionLine";
    	 itemNumber="RequisitionLine_itemNumber";
    	 description="RequisitionLine_description";
    	 quantity="RequisitionLine_quantity";
    	 umCode="RequisitionLine_umCode";
    	 unitPrice="RequisitionLine_unitPrice";
    	 umFactor="RequisitionLine_umFactor";

    	 filenameXls="RequisitionLine_icXls";
    	 callLineProcess="requisitionline-create-from-lookup.xml";
    	 callRetrieveProcess="requisitionline-retrieve-by-header.xml";
    	 
    	 incomingRequest.put("nonStandardItem", "Y");

        }

        if (formType.equals("PO"))
        {
         icHeaderName = "PoHeader_icPoHeader";
         line="poLine";
    	 itemNumber="PoLine_itemNumber";
    	 description="PoLine_description";
    	 quantity="PoLine_quantity";
    	 umCode="PoLine_umCode";
    	 unitPrice="PoLine_unitPrice";
    	 umFactor="PoLine_umFactor";
    	 filenameXls="PoLine_icXls";
    	 callLineProcess="poline-create-from-lookup.xml";
    	 callRetrieveProcess="poline-retrieve-by-header.xml";

        }

        incomingRequest.put("proccesFactor",proccesFactorString);
        incomingRequest.put("FileNameXLS",FileNameXLS);
        incomingRequest.put("umFactor",umFactor);

        //incomingRequest.put("icHeaderName",icHeaderName);
        //incomingRequest.put("icHeaderValue",icHeaderValue);
        incomingRequest.put(icHeaderName,icHeaderValue);
        incomingRequest.put("line",line);
		incomingRequest.put("itemNumber",itemNumber);
		incomingRequest.put("description",description);
		incomingRequest.put("quantity",quantity);
		incomingRequest.put("umCode",umCode);
		incomingRequest.put("unitPrice",unitPrice);
		incomingRequest.put("filenameXls",filenameXls);

		incomingRequest.put("callLineProcess",callLineProcess) ;
		incomingRequest.put("callRetrieveProcess",callRetrieveProcess) ;

		incomingRequest.put("itemNumberDefault", itemNumberDefault);
		incomingRequest.put("descriptionDefault", descriptionDefault);
		incomingRequest.put("quantityDefault", quantityDefault);
		incomingRequest.put("umCodeDefault", umCodeDefault);
		incomingRequest.put("unitPriceDefault", unitPriceDefault);

		this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
		 this.status = Status.FAILED;
		}


		return result;
	}

}