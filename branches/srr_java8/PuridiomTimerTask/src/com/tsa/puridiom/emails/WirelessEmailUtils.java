/*
 * Created on May 11, 2005
 *
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WirelessEmailUtils
{
	public static String RESPONSE_FILE_NAME = "response_from_";
	public static String FILE_NAME_EXTENSION = ".eml";
    /**
     * @param cxml
     * @return Object[3]
     *                [0] Boolean true if a valid Cxml document!
     *                [1] Message
     *                          exception message
     *                          "good file"
     *                          "file does not exist"
     *                [2] Document
     */
    public static Object[] validateCxml(File cxml)
    {
        System.out.println("validating " + cxml.getName());
        Document jdomDoc = null;
        Object oRet[] = new Object[3];
        if (cxml.exists())
        {
            SAXBuilder builder = new SAXBuilder(true);
            try
            {
                jdomDoc = builder.build(cxml);
                oRet[0] = new Boolean(true);
                oRet[1] = "good file";

            }
            catch (JDOMException e)
            {
                oRet[0] = new Boolean(false);
                oRet[1] = e.getMessage();
            }
            oRet[2] = jdomDoc;
        }
        else
        {
            oRet[0] = new Boolean(false);
            oRet[1] = "File does not Exist!";
            System.out.println("file: " + cxml.getName() + " does not exists.");
        }
        oRet[0] = new Boolean(true);
        System.out.println("done validating file: " );

        return oRet;
    }

    public static void main(String[] args)
    {
        File cxml = new File("E:\\hiltonprojects\\axaemailreader\\cxml\\AXA_QT05120502.XML");
        WirelessEmailUtils.validateCxml(cxml);
    }

    public static void processEmail(File email, String organizationId)
    {
    	if(email != null)
    	{
    		boolean go = true;
    		String process = "";
    		String from = "";
    		String line = "";
    		BufferedReader in = null;
			try
			{
				in = new BufferedReader(new FileReader(email));
			}
			catch (FileNotFoundException e)
			{
				go = false;
				Log.error(WirelessEmailUtils.class, email  + " not found!");
				e.printStackTrace();
			}
			if(go)
			{
				try
				{
					while ((line = in.readLine()) != null)
					{
						String temp = line.toString().toLowerCase();
						if(temp.indexOf("approve") >= 0)
						{
							process = "requisition-approve-wireless.xml";
						}
						else if(temp.indexOf("reject") >= 0)
						{
							process = "requisition_reject.xml";
						}
						else if(temp.indexOf("<from>") >= 0)
						{
							int index = temp.indexOf("<from>");
							int endIndex = temp.indexOf("</from>");
							from = temp.substring(index + 6, endIndex);
						}
					}
				}
				catch (IOException e)
				{
					go = false;
					Log.error(WirelessEmailUtils.class, email  + " An error ocurred reading saved email!");
					e.printStackTrace();
				}
			}
			if(in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if(go)
			{
				WirelessEmailUtils.executeProcess(process, email.getName(), from, organizationId);
			}

    	}
    }

    public static String getUserId(String mailId, String organizationId)
    {
    	String user = "";
    	try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess("userprofile-retrieve-by-mailid.xml");
            Map incomingRequest = new HashMap();
            incomingRequest.put("UserProfile_mailId", mailId);
            incomingRequest.put("organizationId", organizationId);

            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
            	/*UserProfile userProfile = (UserProfile)incomingRequest.get("userProfile");
            	if(userProfile != null)
            	{
            		user = userProfile.getUserId();
            	}*/
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return user;

    }
    public static void executeProcess(String processName, String filename, String from, String organizationId)
    {
        try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess(processName);
            Map incomingRequest = new HashMap();
            incomingRequest.put("filename", filename);
            incomingRequest.put("organizationId", organizationId);
            incomingRequest.put("userId", WirelessEmailUtils.getUserId(from, organizationId));

            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
            	//TODO what to do when the req is approved.
            	//email back the user?
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }


	}

	public static String fileLine(File file)
    {
        StringBuffer xml = new StringBuffer();
        try
        {
            //Logger.getInstance().log(WirelessEmailUtils.class, Logger.DEBUG, "fileLine", file.getName());
              BufferedReader reader = new BufferedReader(new java.io.FileReader(file));

              String oneLine = reader.readLine();
              while (oneLine != null)
              {
                xml.append(oneLine);
                oneLine = reader.readLine();
              }
              reader.close();
              reader = null;
              //Logger.getInstance().log(WirelessEmailUtils.class, Logger.DEBUG, "postFile", "closed buffers");
        }
        catch (Exception e) {
            // TODO: handle exception
        }
      return xml.toString();

    }
    public static String getFrom(File cxml)
    {
        String fromName = "response_from_" + cxml.getName();

        File from = new File(cxml.getParent() + File.separator + fromName);
        if(from.exists())
        {
            return WirelessEmailUtils.fileLine(from);
        }
        else
        {
            return null;
        }
    }

    public static void saveFrom(String from, String cxmlName, String path, String organizationId)
    {
        File file = new File(path + WirelessEmailUtils.RESPONSE_FILE_NAME + cxmlName);
        Log.debug(WirelessEmailUtils.class, "saving from for: " + file.getName() + "\t from: " + from);
        boolean created = false;
        try
        {
            created = file.createNewFile();
        }
        catch (IOException e1)
        {
        	Log.error(WirelessEmailUtils.class, "error saving from address!" + e1.getMessage());
            e1.printStackTrace();
        }

        if(created)
        {
            try
            {
            	Log.debug(WirelessEmailUtils.class,  "saving from content ");
                FileWriter fw = new FileWriter(file);
                fw.write(from);
                fw.close();
            }
            catch (FileNotFoundException e)
            {
            	Log.error(WirelessEmailUtils.class,  "Response from file not found!" + e.getMessage());
                e.printStackTrace();
            }
            catch (IOException e)
            {
            	Log.error(WirelessEmailUtils.class,  "Error writting response xml" + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    public static String getNameFromSubject(String subject)
    {
    	String name = "Requisition";
    	if(!HiltonUtility.isEmpty(subject))
    	{
    		String temp = subject.toLowerCase();
    		int index = temp.indexOf("requisition ");
    		if(index > 0)
    		{
    			int spaceIndex = temp.indexOf(" ", index + 12);
    			name = subject.substring(index, spaceIndex);
    		}
    	}
    	name = name + WirelessEmailUtils.FILE_NAME_EXTENSION;;
    	name = name.replaceAll(" ", "_");
    	return name;
    }

    /**
     * @param fileName
     * @param inputStream
     * @param from
     * @param organizationId
     */
    public static void saveFile(String fileName, String inputStream, String from, String organizationId)
    {
        Log.debug("EmailUtils", "saveFile \tfound: " + fileName);
        try
        {
            String path = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.filepath", "c:\\HiltonProjects\\cxml\\");
            path = path + "incoming" + File.separator;
            File savedFile = new File(path + WirelessEmailUtils.getNameFromSubject(fileName));
            if(savedFile.exists())
            {
                boolean deleteOld = savedFile.delete();
                Log.debug(WirelessEmailUtils.class, "\tFile already exist. Old file was: "+ String.valueOf(deleteOld));
            }
            if(savedFile.createNewFile())
            {
                FileWriter out = new FileWriter(savedFile);
                out.write(inputStream.toString());
//                out.write("<from>" + from + "</from>");
                out.close();
                Log.debug(WirelessEmailUtils.class, "saving sender");
//                if(from != null)
//                {
//                    WirelessEmailUtils.saveFrom(from, savedFile.getName(), path, organizationId);
//                }
                Log.debug(WirelessEmailUtils.class,  "Saving File done!");
            }
            else
            {
            	Log.error(WirelessEmailUtils.class,  "File[" + savedFile.getCanonicalPath() +  "] was not saved!");
            }
        }
        catch (Exception e)
        {
        	Log.debug(WirelessEmailUtils.class, " error saving file!");
            e.printStackTrace();
        }
    }

    /**
     * @param fileName
     * 					file to save
     * @param inputStream
     * @param from
     * @param organizationId
     */
    public static void saveFile(String fileName, InputStream inputStream, String from, String organizationId)
    {
        Log.debug("EmailUtils", "saveFile \tfound: " + fileName);
        try
        {
            String path = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.filepath", "c:\\HiltonProjects\\cxml\\");
            path = path + "incoming\\";
            File savedFile = new File(path + fileName);
            if(savedFile.exists())
            {
                boolean deleteOld = savedFile.delete();
                Log.debug(WirelessEmailUtils.class, "\tFile already exist. Old file was: "+ String.valueOf(deleteOld));
            }
            if(savedFile.createNewFile())
            {

                FileOutputStream to = new FileOutputStream(savedFile);
                byte[]  buffer = new byte[inputStream.available()];
                int     bytesRead;

                while ( (bytesRead = inputStream.read(buffer)) > -1 )   //Read bytes until EOF
                {
                    to.write(buffer, 0, bytesRead);
                }
                to.flush();
                to.close();

                Log.debug(WirelessEmailUtils.class, "saving sender");

                if(from != null)
                {
                    WirelessEmailUtils.saveFrom(from, savedFile.getName(), path, organizationId);
                }

                Log.debug(WirelessEmailUtils.class,  "Saving File done!");
            }
            else
            {
            	Log.error(WirelessEmailUtils.class,  "File[" + savedFile.getCanonicalPath() +  "] was not saved!");
            }
        }
        catch (Exception e)
        {
        	Log.debug(WirelessEmailUtils.class, " error saving file!");
            e.printStackTrace();
        }
    }
}
