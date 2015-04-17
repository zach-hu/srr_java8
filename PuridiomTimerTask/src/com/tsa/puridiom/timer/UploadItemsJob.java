package com.tsa.puridiom.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReportQueue;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.emails.CtbtoProcessIncomingEmails;
import com.tsa.puridiom.emails.EmailAuthenticator;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.EmailsMisc;
import com.tsa.puridiom.emails.Pop3Server;
import com.tsa.puridiom.emails.RetrieveEmails;
import com.tsa.puridiom.emails.SaveEmails;
import com.tsa.puridiom.emails.SendReplyEmail;
import com.tsa.puridiom.emails.UploadItemsProcess;
import com.tsa.puridiom.emails.WirelessEmailUtils;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.sendqueue.tasks.SendQueueUpdate;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import com.tsagate.foundation.utility.Log;
//import com.tsa.puridiom.punchoutcatalog.util.Logger;

public class UploadItemsJob extends ScheduledJob
{

	public void execute()
	{
		Log.debug(this, "UploadItemsJob Start execute...");
		UploadItemsProcess up = new UploadItemsProcess();
		up.uploadItemsFun(this.getOrganizationId());
		Log.debug(this, "UploadItemsJob End execute...");
	}

}
