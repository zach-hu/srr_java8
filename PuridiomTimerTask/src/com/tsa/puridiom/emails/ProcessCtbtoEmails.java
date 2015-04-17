package com.tsa.puridiom.emails;

import java.io.File;

import com.tsagate.foundation.utility.Log;

public class ProcessCtbtoEmails extends ProcessIncomingEmails 
{
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
                Log.debug(this,"ProcessIncomingFolder done with: " + dirCmd[i]);
            }
        }
        Log.debug(this, "ProcessIncomingFolder done");
    }

}
