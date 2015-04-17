package com.tsa.puridiom.timer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.emails.EmailUtilities;
import com.tsa.puridiom.emails.SendReplyEmail;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;

public class ReadEmailDirectoryJob extends ScheduledJob
{
	private String jobType;

	public void onStart()
	{
		this.setJobType("officemax");
	}

	public File[] getFiles(String organizationId)
    {
        Log.debug(this, "getting files starts");

        File incomingDir = new File(EmailUtilities.getEmailDirectory(organizationId, this.getJobType()));
        File dirCmd[] = incomingDir.listFiles();

        return dirCmd;
    }

	public int processEmail(File emailFile)
	{
		int success = Status.READY;
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", this.getOrganizationId());
			incomingRequest.put("officeMaxEmail", emailFile);
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("officemax-email-redirect.xml");
			process.executeProcess(incomingRequest);
			success = process.getStatus();
		}
		catch (Exception exception)
		{
			success = Status.FAILED;
		}

		return success;
	}

	public void execute()
	{
		//get list of files
		File emails[] = this.getFiles(this.getOrganizationId());
		//do something with them
		for (int i = 0; i < emails.length; i++)
		{
			int succes = this.processEmail(emails[i]);
			//move them to processed or failed directory
			this.moveTo(emails[i], succes);
		}
	}

	public static void saveObject( String folder, String file_name, Object object )
	    {
	         try
	         {
	             FileOutputStream file_output_stream = new FileOutputStream(folder + file_name );
	             ObjectOutputStream output = new ObjectOutputStream(file_output_stream );
	             output.writeObject( object );
	             file_output_stream.close();
	             Runtime.getRuntime().exec("cacls " + folder + file_name + "/E /G Everyone:R");
	         }
	         catch ( Exception e )
	         {
	             System.out.println("Exception in saveThis");
	             System.out.println( e.getMessage() );
	             e.printStackTrace();
	         }
	     }

	
	public void moveTo(File fileToMove, int _success)
    {
		File parentDir = fileToMove.getParentFile().getParentFile();
    	File outputFile = null;
    	String name = fileToMove.getName();

    	String outputDir = parentDir.getAbsolutePath() + File.separator;
    	if(_success == Status.SUCCEEDED)
    	{//move to processed
    		outputFile = new File(outputDir + "success" + File.separator + name);
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
    			errorReplyEmail.sendEmail("There was an error moving file: " + fileToMove.getName(), this.getOrganizationId());
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

	public void deleteFile(File fileToDelete)
    {
        try
        {
            Log.debug(this, "ProcessIncomingFolde deleting: " + fileToDelete);

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

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType)
	{
		this.jobType = jobType;
	}

}
