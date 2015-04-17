/**
 *
 */
package com.tsa.puridiom.timer;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.AsciiDecoder;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.EmailManager;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny Zapana
 */
public class CatalogJob extends ScheduledJob
{
	public void execute()
	{
		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(this.getOrganizationId());
			PuridiomProcess process = processLoader.loadProcess("catalog-item-retrieve-all.xml");
			Map incomingRequest = new HashMap();
			List catalogItems = new ArrayList();
			CatalogItem catalogItem;
			String originalDescription;
			String descriptionLimitedChar;
			StringBuffer reportContent = new StringBuffer();
			String reportName;
			int numDecodedCatalogItems = 0;
			char limitChar = 126;

			incomingRequest.put("organizationId", this.getOrganizationId());

			process.executeProcess(incomingRequest);

			catalogItems = (List) incomingRequest.get("catalogItemList");

			process = processLoader.loadProcess("catalog-item-update-by-entity.xml");

			for (Iterator iter = catalogItems.iterator(); iter.hasNext();)
			{
				catalogItem = (CatalogItem) iter.next();

				if (!HiltonUtility.isEmpty(catalogItem.getDescription()))
				{
					originalDescription = catalogItem.getDescription();
					descriptionLimitedChar = AsciiDecoder.getLimitedCharString(catalogItem.getDescription(), limitChar);

					if (!catalogItem.getDescription().equals(descriptionLimitedChar))
					{
						Log.debug(this, ">>>> Catalog Item " + catalogItem.getComp_id().getItemNumber() + " in Catalog :" + catalogItem.getComp_id().getCatalogId() + "	  was decoded from '"
								+ catalogItem.getDescription() + "' to '" + descriptionLimitedChar + "'");

						incomingRequest.put("organizationId", this.getOrganizationId());
						incomingRequest.put("catalogItem", catalogItem);
						incomingRequest.put("CatalogItem_catalogId", catalogItem.getComp_id().getCatalogId());
						incomingRequest.put("CatalogItem_itemNumber", catalogItem.getComp_id().getItemNumber());
						incomingRequest.put("CatalogItem_description", descriptionLimitedChar);

						process.executeProcess(incomingRequest);

						if (process.getStatus() == Status.SUCCEEDED)
						{
							reportContent.append("\r\n");
							reportContent.append("\r\n");
							reportContent.append("--------------------------------------------------\r\n");
							reportContent.append("Catalog: " + catalogItem.getComp_id().getCatalogId() + "\r\n");
							reportContent.append("Catalog Item: " + catalogItem.getComp_id().getItemNumber() + "\r\n");
							reportContent.append("\r\n");
							reportContent.append("Original description: " + originalDescription + "\r\n");
							reportContent.append("Decoded description: " + descriptionLimitedChar + "\r\n");

							numDecodedCatalogItems++;
						}

					}
				}
			}

			if (numDecodedCatalogItems > 0)
			{
				reportName = this.writeDecoderReport(reportContent);

				this.sendEmail(reportName, numDecodedCatalogItems);
			}

		} catch (Exception e)
		{
			Log.error(this, "CatalogJob could not be executed successfully: " + e.getMessage());
		}

		Log.debug(this, "CatalogJob done");
	}

	private String writeDecoderReport(StringBuffer reportContent)
	{
		String fileName = "DecoderReport_" + UniqueKeyGenerator.getInstance().getUniqueKey().toString() + ".txt";
		String filePath = DictionaryManager.getInstance("host", this.getOrganizationId()).getProperty("reportsOut", "") + fileName;
		FileOutputStream fo;
		PrintStream ps;

		try
		{
			fo = new FileOutputStream(filePath);
			ps = new PrintStream(fo);
			ps.println(reportContent.toString());
			ps.close();
			fo.close();

			Log.debug(this, "DecoderReport was created successfully");
		} catch (Exception e)
		{
			e.printStackTrace();
			Log.error(this, "DecoderReport could not be written: " + e.getMessage());
		}

		return filePath;
	}

	private void sendEmail(String reportName, int numCatalogItems) throws Exception
	{
		StringBuffer messageText = new StringBuffer();
		String subject = "Decoder Report from " + this.getOrganizationId();
		String sendFrom = DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.from");
		String sendTo = DictionaryManager.getInstance("emails", this.getOrganizationId()).getProperty("mail.notification", "support@puridiom.com");

		messageText.append(" Organization: " + this.getOrganizationId() + "\r\n");
		messageText.append(" Date: " + new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss aaa").format(Calendar.getInstance().getTime()) + "\r\n");
		messageText.append("\r\n");
		messageText.append(numCatalogItems + " Catalog items were decoded (See attached document) \r\n");
		messageText.append("\r\n");

		try
		{

			EmailManager.getInstance().sendEmail(sendFrom, sendTo, null, subject, messageText.toString(), reportName, this.getOrganizationId());

		} catch (EmailsException e)
		{
			EmailManager.getInstance().sendErrorEmail("DecoderReport could not be sent ", this.getOrganizationId());

			e.printStackTrace();

			Log.error(this, "DecoderReport could not be sent : " + e.getMessage());

			throw e;
		}
	}
}
