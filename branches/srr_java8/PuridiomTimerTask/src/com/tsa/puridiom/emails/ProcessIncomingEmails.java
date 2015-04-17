/*
 * Created on Jun 3, 2005
 */
package com.tsa.puridiom.emails;

import java.io.File;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProcessIncomingEmails
{
    private int emailsProcesed = 0;
    private String organizationId = "";

    public File[] getFiles()
    {
        Log.debug(this, "getting files starts");

        File incomingDir = new File(this.getIncomingFolder());
        File dirCmd[] = incomingDir.listFiles();

        return dirCmd;
    }

    public boolean processFileRules(String filename)
    {
    	boolean ret = false;
    	if(!HiltonUtility.isEmpty(filename))
    	{
    		if(filename.indexOf(WirelessEmailUtils.RESPONSE_FILE_NAME) < 0)
    		{
    			if(filename.indexOf(WirelessEmailUtils.FILE_NAME_EXTENSION) > 0)
    			{
    				ret = true;
    			}
    		}

    	}

    	return ret;
    }

    public void processAll(File dirCmd[])
    {
        for(int i = 0; i < dirCmd.length; i++)
        {
            Log.debug(this, "ProcessIncomingFolder found: " + dirCmd[i].getName());
            if(this.processFileRules(dirCmd[i].getName()))
            {
                Log.debug(this, "ProcessIncomingFolder processing: " + dirCmd[i]);
                WirelessEmailUtils.processEmail(dirCmd[i], this.getOrganizationId());

                this.deleteFile(dirCmd[i]);
                dirCmd[i] = null;
                this.emailsProcesed++;
                Log.debug(this,"ProcessIncomingFolder done with: " + dirCmd[i]);
            }
        }
        Log.debug(this, "ProcessIncomingFolder done");
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

    public ProcessIncomingEmails()
    {
        Log.debug(this, "ProcessIncomingFolder started");
    }

    public void processFiles()
    {
        String processThem = DictionaryManager.getInstance("emails", organizationId).getProperty("processThem", "N");
        if(processThem.equalsIgnoreCase("Y"))
        {
            this.process(true);
        }
        else
        {
            this.process(false);
        }
    }

    public void process(boolean process)
    {
        Log.debug(this, "ProcessIncomingFolder starts");

        if(process)
        {
            File dirCmd[] = this.getFiles();
            this.processAll(dirCmd);
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
}