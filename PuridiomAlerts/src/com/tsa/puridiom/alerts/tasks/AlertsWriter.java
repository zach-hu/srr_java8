package com.tsa.puridiom.alerts.tasks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class AlertsWriter extends Task
{
	//public static String xml_filename;
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map alertsMap = new HashMap ();
		List chkNameList = new ArrayList();
		List offsetNameList = new ArrayList();
		List sendToList_Bchk = new ArrayList();
		List sendToList_Rchk = new ArrayList();
		List sendToList_Ochk = new ArrayList();
		List sendToList_Mchk = new ArrayList();
		try
		{
			Map incomingRequest = (Map)object;
			String organizationId = (String)incomingRequest.get("organizationId");
			Document doc = null;
			String alertPathBase = DictionaryManager.getInstance("host", organizationId).getProperty("alerts-path", "");
			File alerPathFile = Utility.getOidFile(alertPathBase, organizationId);
			String alertPath = alerPathFile.getPath();
			Map newValueMap = (HashMap) incomingRequest.get("newValue");
			chkNameList = (List) newValueMap.get("chkNameList");
			offsetNameList = (List) newValueMap.get("offsetNameList");
			sendToList_Bchk = (List) newValueMap.get("sendToList_Bchk");
			sendToList_Rchk = (List) newValueMap.get("sendToList_Rchk");
			sendToList_Ochk = (List) newValueMap.get("sendToList_Ochk");
			String [] sendToList_Manual = (String []) incomingRequest.get("manualSendTo");



			SAXBuilder builder = new SAXBuilder();
			doc = builder.build(new File(alertPath));
		    List rulesList = (List) doc.getRootElement().getChildren();
		    String typeFormat = null;

                for(int rulesIndex = 0, i=0; rulesIndex < rulesList.size(); rulesIndex++, i++ )
                {

                    	Element root =  (Element)rulesList.get(rulesIndex);
                    	Element type = root.getChild("type");
                    	String typeName = type.getText();
                    	if( typeName.equalsIgnoreCase("VEND"))
                    	{
                    		typeFormat= "vendor";
                    	}
                    	else if (typeName.equalsIgnoreCase("PO" ))
                    	{
                    		typeFormat = "poHeader";
                    	}
                    	else if(typeName.equalsIgnoreCase("RSQ") )
                    	{
                    		typeFormat = "requisitionHeader";
                    	}
                    	Element whereAlert = root.getChild("where");
                    	Element argAlert = whereAlert.getChild("arg");
                    	if(offsetNameList.get(rulesIndex) != null)
                    	{
                       		Element offset = argAlert.getChild("offset");
                    		offset.setText( (String) offsetNameList.get(rulesIndex));

                    	}

	        	        if(chkNameList.get(rulesIndex) == null )
	        	        {
	        	        	root.getAttribute("enabled").setValue("no");

	        	        }
	        	        else
	        	        {
	        	        	root.getAttribute("enabled").setValue("yes");
	        	        }

	        	        Element sendTo = root.getChild("sendto");
	        	        List listuser = (List) sendTo.getChildren("user");
	        	        Element userOwner = (Element) listuser.get(0);
	        	        String nom  = userOwner.getAttributeValue("type");

	        	        if((sendToList_Ochk.get(rulesIndex) != null) && (userOwner.getAttributeValue("type").equalsIgnoreCase(typeFormat+"_owner")))
	        	        {

	        	        	userOwner.setText("Y");
	        	        }
	        	        else //if((sendToList_Ochk.get(rulesIndex) == null) && (userOwner.getAttributes().equals(typeFormat+"_owner")))
	        	        {
	        	        	userOwner.setText("N");
	        	        }
	        	        Element userRequisitioner = (Element) listuser.get(1);
	        	        String nomR  = userRequisitioner.getAttributeValue("type");
		       	        if((sendToList_Rchk.get(rulesIndex) != null) && (userRequisitioner.getAttributeValue("type").equalsIgnoreCase( typeFormat+"_requisitionerCode")))
		       	        {
		        	       	userRequisitioner.setText("Y");
		        	    }
		                else //if((sendToList_Rchk.get(rulesIndex) == null) && (userRequisitioner.getAttributes().equals( typeFormat+"_requisitionerCode")))
		       	        {
		       	        	userRequisitioner.setText("N");
		       	        }

		       	        Element userBuyer = (Element) listuser.get(2);
		       	        String nomB  = userBuyer.getAttributeValue("type");
        	            if((sendToList_Bchk.get(rulesIndex) != null) && (userBuyer.getAttributeValue("type").equalsIgnoreCase( typeFormat+"_buyerCode")))
	        	        {
        	            	userBuyer.setText("Y");
	        	        }
	        	        else // if((sendToList_Bchk.get(rulesIndex) == null) && (userBuyer.getAttributes().equals( typeFormat+"_buyerCode")))
	        	        {
        	            	userBuyer.setText("N");
	        	        }

        	            String manual =  sendToList_Manual[rulesIndex];
        	            if(!manual.equals("") && manual != null){
        	            	System.out.println("manual: " + manual);
        	            	int pos = manual.indexOf(";");

        	            	if(pos > 0){
        	            		String [] manualSendTo = manual.split(";");
        	            		int ss=0;
        	            		for (int x = 0 ; x < manualSendTo.length ; x++ ){
          	            			String correo = manualSendTo[x];
        	            			Element userMail = new Element("user");
        	            			userMail.addAttribute("type", "manual");
        	            			userMail.setText(correo);
        	            			sendTo.addContent(userMail);

        	            		}
        	            	}
        	            	else {
        	            		String correo = manual;
        	            		Element userMail = new Element("user");
    	            			userMail.addAttribute("type", "manual");
    	            			userMail.setText(correo);
    	            			sendTo.addContent(userMail);
        	            	}



        	            }


	        	     //   }
	            }

	        outputXmlFile(doc,alertPath);
         		ret = alertsMap ;
				this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}



		return ret;
	}

	public void outputXmlFile(Document xmlFile, String xmlPathFilename) throws IOException
    {
	try
	        {
			FileOutputStream fos = new FileOutputStream(xmlPathFilename);

	        XMLOutputter outputter = new XMLOutputter();
	        outputter.setExpandEmptyElements(true);
	        outputter.output(xmlFile, fos);

	        System.out.println(xmlPathFilename + " ::SUCCEFULL");

	        }
	        catch(IOException ie)
	        {
	            Log.error(this, ie.toString());
	        }

	    }
}
