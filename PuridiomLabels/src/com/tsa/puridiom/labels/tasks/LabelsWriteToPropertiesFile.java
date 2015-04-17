/*
 * Created on May 29, 2009
 */
package com.tsa.puridiom.labels.tasks;

import java.io.File;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.Labels;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class LabelsWriteToPropertiesFile extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try {
            String organizationId = (String) incomingRequest.get("organizationId");
            List labelsList = (List) incomingRequest.get("labelsList");
            //System.out.println("labelsList size: " + labelsList.size());
            File file = (File) incomingRequest.get("labelsPropertiesFile");
            PrintWriter writer = null;
            String labelCode = "";
            String labelValue = "";
            String visibleValue = "visible";
            String linkValue = "true";
            String readOnlyValue = "false";
            String requiredValue = "false";
            String browseName = "";
            String browseType = "";
            String fieldType = "";
            Map baseLabels = new HashMap();

    		/**
    		 * Insert code to handle label properties from database by language
    		 */
            //String language = "";
            if (file == null) {
            	LabelsCreatePropertiesFile createFile = new LabelsCreatePropertiesFile();
            	file = (File) createFile.executeTask(incomingRequest);
            }

        	writer = new PrintWriter(new FileWriter(file));

        	for (int i = 0; i < labelsList.size(); i++) {
            	Labels label = (Labels) labelsList.get(i);

            	labelCode = label.getLabelCode().toLowerCase();
            	labelValue = label.getLabelValue();
            	browseName = label.getBrowseName();
            	browseType = label.getUdf1Code();
            	fieldType = label.getFieldType();
            	
            	if (label.getVisible().equals("N")) {
            		visibleValue = "hidden";
            	} else {
            		visibleValue = "visible";
            	}
            	if (label.getReadOnly().equals("Y")) {
            		readOnlyValue = "true";
            	} else {
            		readOnlyValue = "false";
            	}
            	if (label.getAllowLink().equals("N")) {
            		linkValue = "false";
            	} else {
            		linkValue = "true";
            	}
            	if (label.getRequired().equals("Y")) {
            		requiredValue = "true";
            	} else {
            		requiredValue = "false";
            	}
            	if (!baseLabels.containsKey(labelCode) && Utility.isEmpty(label.getModule())) {
            		System.out.println("label " + labelCode + " written as base label");
	   	    		writer.println(labelCode + "=" + labelValue);
	   	    		writer.println(labelCode + "-visible=" + visibleValue);
	   		    	writer.println(labelCode + "-readonly=" + readOnlyValue);
	   		    	writer.println(labelCode + "-link=" + linkValue);
	   		    	writer.println(labelCode + "-required=" + requiredValue);
	   		    	writer.println(labelCode + "-alt=" + label.getHoverHelp());
	   		    	if (!Utility.isEmpty(browseName)) {
	   	   		    	writer.println(labelCode + "-browsename=" + browseName);
	   	   		    	if (!Utility.isEmpty(browseType)) {
	   	   		    		writer.println(labelCode + "-browsetype=" + browseType);
	   	   		    	}
	   		    	}
	   		    	if (!Utility.isEmpty(label.getFieldLength())) {
	   		    		writer.println(labelCode + "-fieldlength=" + label.getFieldLength());
	   		    	}
	   		    	if (!Utility.isEmpty(fieldType)) {
	   		    		writer.println(labelCode + "-fieldtype=" + fieldType);
	   		    	}
	   		    	if (!Utility.isEmpty(label.getAbbreviation())) {
		   		    	writer.println("brw-" + labelCode + "=" + label.getAbbreviation());
		   		    	writer.println("hdg-" + labelCode + "=" + label.getAbbreviation());
	   		    	}
	   		    	writer.println("val" + labelCode + "=" + label.getValidationMessage());

	   		    	baseLabels.put(labelCode, labelValue);
            	} else if (!Utility.isEmpty(label.getModule()) && !Utility.isEmpty(label.getModuleType())) {
            		System.out.println("label " + labelCode + " written as module type specific label");
	   	   	    	writer.println(label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "=" + labelValue);
	   	   		    writer.println(label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "-visible=" + visibleValue);
	   	   		    writer.println(label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "-readonly=" + readOnlyValue);
	   	   		    writer.println(label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "-link=" + linkValue);
	   	   		    writer.println(label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "-required=" + requiredValue);
	   	   		    writer.println(label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "-alt=" + label.getHoverHelp());
	   	   		    if (!Utility.isEmpty(browseName)) {
	   	   	   		   	writer.println(label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "-browsename=" + label.getBrowseName());
	   	   	   		   	if (!Utility.isEmpty(browseType)) {
	   	   	   		   		writer.println(label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "-browsetype=" + browseType);
	   	   	   		   	}
	   	   		    }
		   	   		if (!Utility.isEmpty(fieldType)) {
	   		    		writer.println(labelCode + "-fieldtype=" + fieldType);
	   		    	}
	   	   		    if (!Utility.isEmpty(label.getFieldLength())) {
	   	   		    	writer.println(labelCode + "-fieldlength=" + label.getFieldLength());
	   	   		    }
	   	   		    writer.println(label.getModuleType() + "-" + label.getModule() + "-" + "hdg-" + labelCode + "=" + label.getAbbreviation());
	   	   		    writer.println("brw-" + label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "=" + label.getAbbreviation());
	   	   		    writer.println("val" + label.getModuleType() + "-" + label.getModule() + "-" + labelCode + "=" + label.getValidationMessage());
	   		    } else if (!Utility.isEmpty(label.getModule())) {
	   		    	System.out.println("label " + labelCode + " written as module specific label");
   	   	    		writer.println(label.getModule() + "-" + labelCode + "=" + labelValue);
   	   		    	writer.println(label.getModule() + "-" + labelCode + "-visible=" + visibleValue);
   	   		    	writer.println(label.getModule() + "-" + labelCode + "-readonly=" + readOnlyValue);
   	   		    	writer.println(label.getModule() + "-" + labelCode + "-link=" + linkValue);
   	   		    	writer.println(label.getModule() + "-" + labelCode + "-required=" + requiredValue);
   	   		    	writer.println(label.getModule() + "-" + labelCode + "-alt=" + label.getHoverHelp());
   	   		    	if (!Utility.isEmpty(browseName)) {
   	   	   		    	writer.println(label.getModule() + "-" + labelCode + "-browsename=" + label.getBrowseName());
	   	   		    	if (!Utility.isEmpty(browseType)) {
	   	   		    		writer.println(label.getModule() + "-" + labelCode + "-browsetype=" + browseType);
	   	   		    	}
   	   		    	}
   	   		    	if (!Utility.isEmpty(fieldType)) {
	 		    		writer.println(labelCode + "-fieldtype=" + fieldType);
	 		    	}
   	   		    	if (!Utility.isEmpty(label.getFieldLength())) {
   	   		    		writer.println(labelCode + "-fieldlength=" + label.getFieldLength());
   	   		    	}
   	   		    	writer.println(label.getModule() + "-" + "hdg-" + labelCode + "=" + label.getAbbreviation());
   	   		    	writer.println("brw-" + label.getModule() + "-" + labelCode + "=" + label.getAbbreviation());
   	   		    	writer.println("val" + label.getModule() + "-" + labelCode + "=" + label.getValidationMessage());
   		    	} else {
   		    		System.out.println("label " + labelCode + " NOT WRITTEN");
   		    	}
            }

            writer.close();

   			Log.debug(this, "labels.properties has been created from the database for " + organizationId);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return null;
	}
}
