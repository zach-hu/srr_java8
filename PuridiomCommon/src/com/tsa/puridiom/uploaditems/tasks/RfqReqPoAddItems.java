package com.tsa.puridiom.uploaditems.tasks;
import java.math.BigDecimal;
import java.util.HashMap;
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

import com.tsa.puridiom.property.PropertiesManager;

import java.util.ArrayList;

public class RfqReqPoAddItems extends Task
{


	List listItems=new ArrayList();
    String formType=null;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		listItems= (List)incomingRequest.get("readRfqReqPoItems");
		formType = (String)incomingRequest.get("formType");

		ArrayList listItemsNew = (ArrayList)incomingRequest.get("readRfqReqPoItems");

		String save="SAVE";
		String icHeaderName=(String)incomingRequest.get("icHeaderName");
		String icHeaderValue=(String)incomingRequest.get("icHeaderValue");
		String line=(String)incomingRequest.get("line");
		String itemNumber=(String)incomingRequest.get("itemNumber");
		String description=(String)incomingRequest.get("description");
		String quantity=(String)incomingRequest.get("quantity");
		String umCode=(String)incomingRequest.get("umCode");
		String unitPrice=(String)incomingRequest.get("unitPrice");
		String umFactor=(String)incomingRequest.get("umFactor");

		String processFactorString=(String)incomingRequest.get("proccesFactor");
		String callLineProcess=(String)incomingRequest.get("callLineProcess");
		String callRetrieveProcess=(String)incomingRequest.get("callRetrieveProcess");

		String itemNumberDefault=(String)incomingRequest.get("itemNumberDefault");
		String descriptionDefault=(String)incomingRequest.get("descriptionDefault");
		String quantityDefault=(String)incomingRequest.get("quantityDefault");
		String umCodeDefault=(String)incomingRequest.get("umCodeDefault");
		String unitPriceDefault=(String)incomingRequest.get("unitPriceDefault");

		String filenameXls=(String)incomingRequest.get("filenameXls");

		String FileNameXls=(String)incomingRequest.get("FileNameXLS");

		String uomFactor=null;

		PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId"));
		int itemNumberColum=Integer.parseInt(propertiesManager.getProperty("UPLOADITEMS","ITEMNUMBER",""))-1;
        int descriptionColum=Integer.parseInt(propertiesManager.getProperty("UPLOADITEMS","DESCRIPTION",""))-1;
        int QtyColum=Integer.parseInt(propertiesManager.getProperty("UPLOADITEMS","QTY",""))-1;
        int uomColum=Integer.parseInt(propertiesManager.getProperty("UPLOADITEMS","UOM",""))-1;
        int unitPriceColum=Integer.parseInt(propertiesManager.getProperty("UPLOADITEMS","UNITPRICE",""))-1;

		try
		{
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		processLoader.setApplicationName(this.getApplicationName());
		PuridiomProcess processFactor = processLoader.loadProcess(processFactorString);
		PuridiomProcess processLine = processLoader.loadProcess(callLineProcess);
        PuridiomProcess processRetrieve = processLoader.loadProcess(callRetrieveProcess);

		 for(int i=0;i<listItems.size();i++)
		 {
		 RfqLine rfqLine = new RfqLine();
		 RequisitionLine requisitionLine = new RequisitionLine();
		 PoLine poLine = new PoLine();

		 List itemList=new ArrayList();
		 itemList=(ArrayList)listItems.get(i);

		  incomingRequest.put("rfqLine",rfqLine);
		  incomingRequest.put("requisitionLine",requisitionLine);
		  incomingRequest.put("poLine",poLine);


		  incomingRequest.put(filenameXls,FileNameXls);

		  if (!itemList.get(itemNumberColum).equals("0"))
		  {incomingRequest.put(itemNumber,(String)itemList.get(itemNumberColum));}
		  else{incomingRequest.put(itemNumber,itemNumberDefault);}
		  if (!itemList.get(descriptionColum).equals("0"))
		  {incomingRequest.put(description,(String)itemList.get(descriptionColum));}
		  else{incomingRequest.put(description,descriptionDefault);}
		  if (!itemList.get(QtyColum).equals("0"))
		  {incomingRequest.put(quantity, (String)itemList.get(QtyColum));}
		  else{incomingRequest.put(quantity, quantityDefault);}
		  if (!itemList.get(uomColum).equals("0"))
		  {incomingRequest.put(umCode,(String)itemList.get(uomColum)) ;}
		  else{incomingRequest.put(umCode,umCodeDefault);}
		  if (!itemList.get(unitPriceColum).equals("0"))
		  {incomingRequest.put(unitPrice,(String)itemList.get(unitPriceColum)) ;}
		  else{incomingRequest.put(unitPrice,unitPriceDefault);}

		  incomingRequest.put("proccesFactor",(String)itemList.get(uomColum));
		  if (!itemList.get(uomColum).equals("0"))
		  {
			  processFactor.executeProcess(incomingRequest);
			  uomFactor=(String)incomingRequest.get("uomFactorRetrieve");
		  }
		  else{uomFactor="1";}

		  incomingRequest.put(umFactor,uomFactor);
		  incomingRequest.put("createAction", save);
		  incomingRequest.put("readRfqReqPoItems", listItems);

		  processLine.executeProcess(incomingRequest);

		  this.status = Status.SUCCEEDED;
		  //itemList.clear();
		  //RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
		 }

		 processRetrieve.executeProcess(incomingRequest);

    	  this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
		}


		return result;
	}

}