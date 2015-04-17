package com.tsa.puridiom.emails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;


public class UploadItemsProcess extends Task{


	public UploadItemsProcess()
	{

	}

    public void uploadItemsFun(String organizationId)
    {

   //********************************************************************************************
    	try {
    		Map incomingRequest = new HashMap();

    	    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
      	    PuridiomProcess processUploadItems = processLoader.loadProcess("upload-items-process.xml");

      	    String action="UI";
      	    String status="00";

      	    incomingRequest.put("organizationId", organizationId);
      	    incomingRequest.put("SendQueue_action", action);
      	    incomingRequest.put("SendQueue_status", status);

      	    processUploadItems.executeProcess(incomingRequest);

      	    this.setStatus(Status.SUCCEEDED);
      	    Log.debug(this, "UploadItemsFun COMPLETED...");
      	  }

    	  catch (Exception e)
    	  {
    		e.printStackTrace();
    		this.setStatus(Status.FAILED);
    		Log.debug(this, "UploadItemsFun ERROR...");
    	  }
     //*********************************************************************************************

    }


}
