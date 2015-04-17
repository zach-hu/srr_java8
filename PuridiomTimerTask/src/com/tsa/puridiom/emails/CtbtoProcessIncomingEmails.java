/*
 * Created on Jun 3, 2005
 */
package com.tsa.puridiom.emails;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.FileCopyToTask;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CtbtoProcessIncomingEmails
{
    private int emailsProcesed = 0;
    private String organizationId = "";
    public static int NO_FORM = -10;
    public static int UNDEFINED = -90;

    public Object[] getFiles()
    {
        Log.debug(this, "getting files starts");
        String incomingDirName = this.getIncomingFolder();
        File incomingDir = new File(incomingDirName);
        File dirCmd[] = incomingDir.listFiles();
        String tmpDirName = incomingDirName + File.separator + "tmp" + File.separator;
        List fileNames = new ArrayList();
        for (int i = 0; i < dirCmd.length; i++)
        {
        	boolean moved = true;
        	String msg = "";
        	try
        	{
        		if(!dirCmd[i].isDirectory())
        		{
	        		File tmpName = new File(tmpDirName + dirCmd[i].getName());
	        		try
	        		{
		        		if(dirCmd[i].renameTo(tmpName))
		        		{
		        				fileNames.add(tmpName.getName());
		        		}
		        		else
		        		{
		        			moved = false;
		        			msg = "Os Related error moving file: " + dirCmd[i].getName();
		        		}
	        		}
	        		catch (SecurityException e)
        			{
        				moved = false;
        				msg = "Security Exception, " + e.getMessage();
        				Log.error(this, "Security Exceptiom moving file: " + dirCmd[i].getName() + e.getMessage());
					}
        		}
			}
        	catch (Exception e)
			{
				moved = false;
				msg = "General Exception, moving file: "  + dirCmd[i].getName() + e.getMessage();
			}
        	if(!moved)
        	{
        		SendReplyEmail replyEmail = new SendReplyEmail();
				replyEmail.sendEmail(msg, organizationId);
        	}

		}
        Log.debug(this, "getting files done: " + fileNames);
        return fileNames.toArray();
    }

    public boolean processFileRules(String filename)
    {
    	boolean ret = false;
    	if(!HiltonUtility.isEmpty(filename))
    	{
			String mailExtension = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.extension", ".eml");
			if(filename.indexOf(mailExtension) > 0)
			{
				ret = true;
			}
    	}

    	return ret;
    }

    /**
     * attachEmails relates the document to the specified form.
     * @param name
     * @return Object[]
     * first element is process.status
     * 2 element is base file name
     * 3 element is form number(including type)
     * 4 element is formtype
     * 5 element is title
     */
    public Object[] attachEmails(String name)
    {
    	Log.debug(this, "attach-email: " + name);
    	Object ret[] = {"0", "", "", "", ""};
    	try
        {
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess process = processLoader.loadProcess("attach-emails.xml");
            Map incomingRequest = new HashMap();
            incomingRequest.put("organizationId", organizationId);
            incomingRequest.put("subject", name);

            process.executeProcess(incomingRequest);
            if (process.getStatus() == Status.SUCCEEDED)
            {
            	String sIcHeader = (String)incomingRequest.get("icHeader");
            	Log.debug(this, "attach-emails done sucessfully for ic: " + sIcHeader);
            	String baseFileName = (String)incomingRequest.get("baseFileName");
            	Log.debug(this, "base file name: " + baseFileName);
            	ret[1] = baseFileName;
            	ret[3] = incomingRequest.get("startPattern");
            	ret[4] = incomingRequest.get("email_docTitle");
            }
            else
            {
            	ret[0] = String.valueOf(Status.FAILED);
            }
            ret[0] = String.valueOf(process.getStatus());
            ret[2] = this.getFormNumber(incomingRequest);
        }
        catch (Exception exception)
        {
        	Log.error(this, "attach-emails failed");
            exception.printStackTrace();
            ret[0] = String.valueOf(Status.FAILED);
        }
        Log.debug(this, "attach-email: " + name + " done...");
        return ret;
    }
    private String getFormNumber(Map incomingRequest)
    {
    	String formNumber = (String)incomingRequest.get("formnumber");
        String formtype = (String)incomingRequest.get("formtype");
        String release = (String)incomingRequest.get("release");
        String tmp = "";
        if(!Utility.isEmpty(formtype))
        {
        	tmp = tmp + formtype;
        }
        if(!Utility.isEmpty(formNumber))
        {
        	tmp = tmp + " " + formNumber;
        }
        if(!Utility.isEmpty(release))
        {
        	tmp = tmp + "/" + release;
        }
        Log.debug(this, "form number is: " + tmp);
		return tmp;
	}

	public void moveTo(String fileToMoveName, int _success, String baseFileName)
    {
		File fileToMove = new File(fileToMoveName);
    	File parentDir = fileToMove.getParentFile().getParentFile();
    	File outputFile = null;
    	String name = fileToMove.getName();

    	String outputDir = parentDir.getAbsolutePath() + File.separator;
    	if(_success == Status.SUCCEEDED)
    	{//move to processed
    		outputFile = new File(outputDir + "attached" + File.separator + name);
    	}
    	else
    	{//move to failed
    		outputFile = new File(outputDir + "failed" + File.separator + name);
    	}
    	try
    	{
    		boolean moved = fileToMove.renameTo(outputFile);
    		if(!moved)
    		{
    			Log.error(this, "file could not be moved");
    			SendReplyEmail errorReplyEmail = new SendReplyEmail();
    			errorReplyEmail.sendEmail("There was an error moving file: " + fileToMoveName, organizationId);
    			this.deleteFile(fileToMove);
    		}
    		else
    		{
	    		Log.debug(this, "file moved to: " + outputFile);
    		}
    	}
    	catch (Exception e) {
    		Log.debug(this, "file could not be moved to: " + outputFile);
    		name = "failed_" + name;
    		Log.debug(this, "renaming file to: " + name);
    		fileToMove.renameTo(new File(parentDir.getAbsolutePath() + name));
		}
    }

	/**
     * @param fromFile
     * @param organizationId
     * @param toFile
     */
    public void copyToBaseDir(String fromFile, String organizationId, String toFile)
    {
    	this.copyToBaseDir(fromFile, organizationId, toFile, "Y");
    }

    /**
     * @param fromFile
     * @param organizationId
     * @param toFile
     */
    public void copyToBaseDir(String fromFile, String organizationId, String toFile, String getPath)
    {
    	Log.debug(this, "copyToBaseDir: fromFile" +fromFile + ", toFile: " + toFile);
    	FileCopyToTask copyTo = new FileCopyToTask();
    	Map incomingRequest = new HashMap();

    	incomingRequest.put("organizationId", organizationId);
    	incomingRequest.put("newFile", toFile);
		incomingRequest.put("originalFile", new File(fromFile));
    	incomingRequest.put("getPath", getPath);

    	try
    	{
    		Log.debug(this, "moving to base");
			copyTo.executeTask(incomingRequest);
		}
    	catch (Exception e)
		{
			Log.error(this, "There was an error copying file to base directory. " + e.getMessage());
			SendReplyEmail errorReplyEmail = new SendReplyEmail();
			errorReplyEmail.sendEmail("There was an error copying file to base directory. " + e.getMessage(), organizationId);
			e.printStackTrace();
		}
    	Log.debug(this, "done moving");
    }
    public File getFileFromTmp(String name)
    {
    	String incomingDirName = this.getIncomingFolder();
        String tmpDirName = incomingDirName + File.separator + "tmp" + File.separator;
        return new File(tmpDirName + name);
    }
    public void copyToFinalDir(String from, String to, int success)
    {
    	GetOutputFile output = new GetOutputFile();
    	output.setOrganizationId(this.organizationId);

    	this.copyToBaseDir(from, organizationId, output.outputFile(from, to, success).getAbsolutePath(), "N");
    }

    public void processAll(Object dirCmd[])
    {
    	Log.debug(this, "There is " + dirCmd.length + " files");
        for(int i = 0; i < dirCmd.length; i++)
        {
            Log.debug(this, "ProcessIncomingFolder PROCESSING: " + dirCmd[i] + ", ORDER: " + i);
            String filePath = this.getFileFromTmp(dirCmd[i].toString()).getAbsolutePath();
            if(this.processFileRules(dirCmd[i].toString()))
            {
                Log.debug(this, "ProcessIncomingFolder processing: " + dirCmd[i]);
                Object returned[] = this.attachEmails(dirCmd[i].toString());
                int success = Integer.parseInt((String)returned[0]);
                String reply = DictionaryManager.getInstance("emails", organizationId).getProperty("process.reply", "Y");
                if(reply.equalsIgnoreCase("Y"))
                {
                	this.replyToEmail(filePath, success, (String)returned[2], false, (String)returned[3], (String)returned[4]);
                }

                String moveto = DictionaryManager.getInstance("emails", organizationId).getProperty("process.moveto", "Y");
                if(moveto.equalsIgnoreCase("Y"))
                {//copy to the attached/failed directory for further processing.
                	this.copyToFinalDir(filePath, (String)returned[1], success);
                }
                dirCmd[i] = null;
                this.emailsProcesed++;
                Log.debug(this,"ProcessIncomingFolder done with: " + dirCmd[i]);
            }
            else
            {
            	Log.debug(this, "ProcessIncomingFolder did not process: " + dirCmd[i]);
            	String moveto = DictionaryManager.getInstance("emails", organizationId).getProperty("process.moveto", "Y");
                if(moveto.equalsIgnoreCase("Y"))
                {
                	this.moveTo(filePath, Status.FAILED, "");
                }
            }
            String delete = DictionaryManager.getInstance("emails", organizationId).getProperty("process.delete", "Y");
            if(delete.equalsIgnoreCase("Y"))
            {
            	this.deleteFile(new File(filePath));
            }
        }
        Log.debug(this, "ProcessIncomingFolder done");
    }

    private void replyToEmail(String fileName, int success, String number, String formType, String title)
    {
		this.replyToEmail(fileName, success, number, true, formType, title);
	}

    private void replyToEmail(String fileName, int success, String number, boolean getFile, String formType, String title)
    {
		SendReplyEmail reply = new SendReplyEmail();
		if(getFile)
		{
			fileName = this.getFileFromTmp(fileName).getAbsolutePath();
		}
		reply.buildEmailFromFile(fileName, this.getOrganizationId(), success, number, formType, title);

	}

	public void deleteFile(File fileToDelete)
    {
        try
        {
            Log.debug(this, "ProcessIncomingFolde deleting: " + fileToDelete);
            if(!fileToDelete.exists())
            {
            	return;
            }

            boolean deleted = fileToDelete.delete();

            if(deleted)
            {
                Log.debug(this, "ProcessIncomingFolder File deleted.");
            }
            else
            {
                Log.debug(this, "ProcessIncomingFolder File ["+ fileToDelete.getName() + "] couldn't be deleted.");
            }
        }
        catch (Exception e) {
            Log.error(this, "ProcessIncomingFolder error deleting file" + e.getMessage());
        }
    }

    public void processSelected(File slectedFiles[])
    {
        for(int i = 0; i < slectedFiles.length; i++)
        {
            //EmailUtils.processCxml(slectedFiles[i], this.props, "Y", "");
        }
    }

    public String getIncomingFolder()
    {
    	String incomingFolder = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.filepath", "c:\\HiltonProjects\\emails\\") + "incoming";
        Log.debug(this, "ProcessIncomingFolder directory is: " + incomingFolder);
        return incomingFolder;
    }

    public String getFailedFolder()
    {
        String faildedFolder = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.filepath", "c:\\HiltonProjects\\emails\\") + "failed";
        Log.debug(this, "ProcessIncomingFolder directory is: " + faildedFolder);
        return faildedFolder;
    }

    public CtbtoProcessIncomingEmails()
    {
        Log.debug(this, "ProcessIncomingFolder started");
    }

    public void processFiles()
    {
        this.process(true);
    }

    public void process(boolean process)
    {
        Log.debug(this, "ProcessIncomingFolder starts");

        if(process)
        {
            this.processAll(this.getFiles());
        }
        Log.debug(this, "ProcessIncomingFolder processed [" + String.valueOf(this.emailsProcesed) + "] files." );
    }

    public int getEmailsProcesed()
    {
        return emailsProcesed;
    }

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public static void main(String[] args)
	{
		CtbtoProcessIncomingEmails processEmails = new CtbtoProcessIncomingEmails();
		processEmails.process(true);

	}
}