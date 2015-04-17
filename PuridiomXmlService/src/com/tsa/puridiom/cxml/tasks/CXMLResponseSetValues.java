/**
 *
 */
package com.tsa.puridiom.cxml.tasks;

import java.io.File;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.cxml.entity.CXML;
import com.tsa.puridiom.cxml.entity.Status;
import com.tsa.puridiom.cxml.entity.CXML.Response;
import com.tsa.puridiom.cxml.util.HttpPoster;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.xml.PuridiomEntityResolver;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.Dictionary;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny Zapana
 */
public class CXMLResponseSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Document requestDocument = (Document) incomingRequest.get("requestDocument");
		String organizationId = (String) incomingRequest.get("organizationId");
		CXML cXMLResponse = new CXML();
		Response response;
		String historyReason;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			String vendorId = poHeader.getVendorId().toUpperCase();
			String cxmlOmitEncoding = propertiesManager.getProperty("CXML", "OMITENCODING-" + vendorId, "N");
			if (cxmlOmitEncoding.equals("N")) {
				cxmlOmitEncoding = propertiesManager.getProperty("CXML", "OMITENCODING", "N");
			}
			incomingRequest.put("cxmlOmitEncoding", cxmlOmitEncoding);
			
			String customer = "punchout" + File.separator + (String) incomingRequest.get("organizationId");

			String bundle = "punchout_" + poHeader.getVendorId().toLowerCase();

			Dictionary dictionary = DictionaryManager.getInstance(bundle, customer);

			String cXMLRequestString = CXML.toString(requestDocument, cxmlOmitEncoding);

			String cXMLStringResponse = "";

			SAXBuilder sb = new SAXBuilder();
			sb.setEntityResolver(PuridiomEntityResolver.getInstance());

			Document responseDocument;

			Element responseElement;

			boolean isTestMode = DictionaryManager.getInstance("emails", organizationId).getProperty("cxml.testServer", "Y").equalsIgnoreCase("Y") ? true : false;

			Date currentDate = Calendar.getInstance().getTime();

			Log.debug(this, "Request :" + cXMLRequestString);

			if (!isTestMode)
			{
				Log.debug(this, "URL Response :" + customer + " - " + dictionary.getProperty("cxml.url"));
				cXMLStringResponse = HttpPoster.getInstance().postXML(dictionary.getProperty("cxml.url"), cXMLRequestString);
			} else
			{
				Log.debug(this, "Shipping HTTP(s) post.  In test mode.");
				cXMLStringResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE cXML SYSTEM \"http://xml.cxml.org/schemas/cXML/1.1.007/cXML.dtd\"><cXML version=\"1.1\" payloadID=\"\" timestamp=\""
						+ currentDate + "\" xml:lang=\"en\">  <Response>    <Status code=\"200\" text=\"IN TEST MODE (non-HTTP post)\" xml:lang=\"en\"/>  </Response></cXML>";
			}

			Log.debug(this, "Response :" + cXMLStringResponse);

			responseDocument = sb.build(new StringReader(cXMLStringResponse));

			response = cXMLResponse.new Response();

			responseElement = responseDocument.getRootElement().getChild("Response");

			if (!HiltonUtility.isEmpty(responseElement.getAttributeValue("Id")))
			{
				response.setId(Integer.valueOf(responseElement.getAttributeValue("Id")));
			}

			response.setStatus(this.buildStatusObject(responseElement));

			cXMLResponse.setResponse(response);

			if (cXMLResponse.getResponse().getStatus().getCode().toString().equals(CXML.OK_STATUS_CODE))
			{
				historyReason = "cXML order " + poHeader.getDisplayPoNumber() + " electronically submitted to supplier.";

				poHeader.setDateEdiXmit(currentDate);
				poHeader.setDatePrinted(currentDate);
			} else
			{
				historyReason = "Order submission failed.  " + cXMLResponse.getResponse().getStatus().getText();
			}

			incomingRequest.put("order_historyreason", historyReason);
			incomingRequest.put("responseDocument", responseDocument);

			Log.debug(this, "CXMLResponse object was created successfully");

			this.setStatus(com.tsagate.foundation.processengine.Status.SUCCEEDED);

		} catch (Exception e)
		{
			e.printStackTrace();

			Log.error(this, "Error creating CXMLResponse object \r\n" + e.getMessage());

			this.setStatus(com.tsagate.foundation.processengine.Status.FAILED);
			throw e;
		}

		return cXMLResponse;
	}

	private Status buildStatusObject(Element responseElement)
	{
		Element statusElement = responseElement.getChild("Status");
		Status status = new Status();

		status.setLang(statusElement.getAttributeValue("lang", Namespace.getNamespace("xml", "http://www.w3.org/1999/xhtml")));
		status.setCode(Integer.valueOf(statusElement.getAttributeValue("code")));
		status.setText(statusElement.getAttributeValue("text"));

		if (!HiltonUtility.isEmpty(statusElement.getText()))
		{
			status.setContent(statusElement.getText());
		}

		return status;
	}
}
