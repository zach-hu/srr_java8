/*
 * PunchOutRequest.java
 *
 * Original Created on January 30, 2002
 * Revised for Hilton on June 8, 2004
 */

package com.tsa.puridiom.punchoutcatalog;

/**
 * @author  Jeff / Kelli
 * @version
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.tsa.puridiom.common.documents.ItemLookup;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.xml.PuridiomEntityResolver;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class PunchOutRequest
{
    private String organizationId = "tsagate";
    private String catalogId = "";

    public String processSSLPunchOutRequest(String targetUrl,String targetParm, String requestId, Map parameters) throws Exception{
        URL url = null;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        Document jdoc = null;
        String in = null ;
        String	punchOutRequest = "";

		Document document = null;
		try {
			String filename = this.getXmlPath(this.organizationId) + targetParm;
			//File f = new File(filename);
			File f = Utility.getOidFile(filename, this.organizationId);
			SAXBuilder docBuilder = new SAXBuilder();
			docBuilder.setEntityResolver(PuridiomEntityResolver.getInstance());
			document = docBuilder.build(f);

			Element rootElement = document.getRootElement();

			rootElement.setAttribute("payloadID", String.valueOf(UniqueKeyGenerator.getInstance().getUniqueKey()));
			rootElement.setAttribute("timestamp", Dates.today("yyyy-MM-dd hh:mm:ss", ""));

			Element urlElement = rootElement.getChild("Request").getChild("PunchOutSetupRequest").getChild("BrowserFormPost").getChild("URL");
			//String	returnUrl = urlElement.getText();

			String applicationUrl = PropertiesManager.getInstance(this.organizationId).getProperty("APPLICATION", "URL", "https://my.puridiom.com");
			String	returnUrl = "";

			if (applicationUrl.endsWith("/puridiom/")) {
				returnUrl = applicationUrl + "catalogs/punchout_return.jsp";
			} else if (applicationUrl.endsWith("/puridiom")) {
				returnUrl = applicationUrl + "/catalogs/punchout_return.jsp";
			} else {
				returnUrl = applicationUrl + "/puridiom/catalogs/punchout_return.jsp";
			}

			returnUrl = returnUrl +"?puridiomRequestId=" +  requestId;

			urlElement.setText(returnUrl);

			Element punchOutSetupRequestElement = rootElement.getChild("Request").getChild("PunchOutSetupRequest");

			if (parameters.containsKey("BuyerCookie"))
			{
				Element buyerCookieElement = punchOutSetupRequestElement.getChild("BuyerCookie");
				String sessionId = (String) parameters.get("BuyerCookie");
				buyerCookieElement.setText(sessionId);
			}

			if (parameters.containsKey("userIdPunch"))
			{

				List buyerExtrinstElement = punchOutSetupRequestElement.getChildren("Extrinsic");
				String userIdPunch = HiltonUtility.ckNull((String) parameters.get("userIdPunch"));
				String mailIdPunch = HiltonUtility.ckNull((String) parameters.get("mailIdPunch"));
				ArrayList  ids = new ArrayList();
				ids.add(userIdPunch);
				ids.add(mailIdPunch);
				for (int i = 0; i < buyerExtrinstElement.size(); i++)
				{
					Element el = (Element)buyerExtrinstElement.get(i);
					el.setText((String) ids.get(i));
				}

			}


			if (parameters.containsKey("ZipCode"))
			{
				String zipCode = HiltonUtility.ckNull((String) parameters.get("ZipCode"));
				Element extrinsicElement = new Element("Extrinsic");

				extrinsicElement.setAttribute(new Attribute("name", "ZipCode"));
				extrinsicElement.setText(zipCode);

				punchOutSetupRequestElement.addContent(extrinsicElement);
			}

			XMLOutputter xmlOutputter = new XMLOutputter();
			punchOutRequest = xmlOutputter.outputString(document);
		}
		catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

        try {
            url = new URL(targetUrl );
        }
        catch(Exception e) {
            throw e;
        }

        try {
        	Log.debug(this, "posting xml to punchout: " + punchOutRequest);
        	
            if (url.openConnection() instanceof HttpsURLConnection)
			{
				connection = (HttpsURLConnection) url.openConnection();
			} else
			{
				connection = (HttpURLConnection) url.openConnection();
			}

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST") ;
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
			connection.setRequestProperty("Content-Type","text/xml");

			OutputStream os = connection.getOutputStream();

			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write(punchOutRequest);
			osw.flush();
			osw.close();

            inputStream = connection.getInputStream();
        	in = streamToString(inputStream) ;
        	inputStream.close() ;
        }
        catch(IOException e) {
            throw e;
        }

        SAXBuilder parser = new SAXBuilder();
        String status = "500";
        String	startPage = null ;

        if (targetParm.equals("yahoo-shopping.xml")) {
       		return targetUrl;
        }

        try {
        	StringReader sr = new StringReader(in) ;
            jdoc = parser.build(sr);

	        Element root = jdoc.getRootElement();
	        if (root != null) {
	        	status = root.getChild("Response").getChild("Status").getAttribute("code").getValue();
	        	if (status.equals("500")) {
	        		String err = root.getChild("Response").getChild("Status").getText() ;
	        		System.out.println("Punchout Response Error: " + err);
//	        		startPage = "" ;
	        	} else {
	        		startPage = root.getChild("Response").getChild("PunchOutSetupResponse").getChild("StartPage").getChild("URL").getTextTrim() ;
	        	}
	        }  else {
	        	//system.out.println("Invalid document!") ;
	        }
        }
        catch(JDOMException e) {
        	e.printStackTrace();
            throw e;

        }
        return startPage;
    }

    public String streamToString(InputStream inputStream) {
        DataInputStream iData = new DataInputStream(inputStream);
        String iLine = null;
        StringBuffer buf = new StringBuffer("");

        do {
            try {
                iLine = iData.readLine();
            }
            catch(IOException e) {
                //system.out.println("readLine Exception: " + e.toString());
            }
            if (iLine == null) {
                break;
            }
            if (iLine.trim().length() > 0) {
                buf.append(iLine);
            }
        } while(true);

        return buf.toString();
    }

    public List processPunchoutOrderMessage(String encodedXml) {
       	List itemLookupList = new ArrayList();
       	boolean supplierFromItemId = false;

       	String commodityOverRide = null;
       	if (!Utility.isEmpty(catalogId)) {
       		String property = "COMMODITYOVERRIDE-" + catalogId.toUpperCase();
       		commodityOverRide = Utility.ckNull(PropertiesManager.getInstance(organizationId).getProperty("PUNCHOUT", property, "")).trim();
       	}

       	if (Utility.isEmpty(commodityOverRide)) {
       		commodityOverRide = Utility.ckNull(PropertiesManager.getInstance(organizationId).getProperty("PUNCHOUT", "commodityOverRide", "")).trim();
       	}

        try {
            // There doesn't seem to be a need to decode the xml string.  Certain characters are causing exceptions when decoding.
            //String xmlStr = URLDecoder.decode(encodedXml) ;
            String	xmlStr = encodedXml;
        	SAXBuilder parser = new SAXBuilder();
        	Document jdoc = null;
        	List itemList = null ;

        	StringReader sr = new StringReader(xmlStr) ;
            jdoc = parser.build(sr);

	        Element root = jdoc.getRootElement();
	        if (root != null) {
				Element msg = root.getChild("Message") ;
				Element poom = msg.getChild("PunchOutOrderMessage") ;
	        	itemList = poom.getChildren();

				int cnt = 0 ;
	        	Iterator it = itemList.iterator() ;
	        	while (it.hasNext()) {
	        		Element e = (Element) it.next() ;
	        		if (e.getName().equals("ItemIn")) {
				        BigDecimal bdQuantity = new BigDecimal(1);
				        try {
				        	bdQuantity = new BigDecimal(e.getAttribute("quantity").getValue());
				        }
				        catch (Exception nfe) {
				        	bdQuantity = new BigDecimal(1) ;
				        }

						BigDecimal bdUnitPrice = new BigDecimal(0);
						try {
							bdUnitPrice = new BigDecimal(e.getChild("ItemDetail").getChild("UnitPrice").getChild("Money").getTextTrim());
						}
						catch (Exception nfe) {
							bdUnitPrice = new BigDecimal(0) ;
						}

						ItemLookup item = new ItemLookup() ;

						item.setSource("XML") ;
						item.setItemNumber(e.getChild("ItemID").getChild("SupplierPartID").getTextTrim()) ;
						item.setCatalogId("") ;


                        if (commodityOverRide.equals("EMPTY")) {
                            item.setCommodity("");
                        }
                        else if (commodityOverRide.equals("") && e.getChild("ItemDetail").getChild("Classification") != null) {
							item.setCommodity(e.getChild("ItemDetail").getChild("Classification").getTextTrim());
						} else if (commodityOverRide.equals("") && e.getChild("ItemDetail").getChild("Classification") == null) {
							item.setCommodity(e.getChild("ItemDetail").getTextTrim());
						} else {
							// Override the commodity code
							item.setCommodity(commodityOverRide);
						}

						item.setDescription(e.getChild("ItemDetail").getChild("Description").getTextTrim());
						item.setUnitOfOrder(e.getChild("ItemDetail").getChild("UnitOfMeasure").getTextTrim());
						item.setOrderCost(bdUnitPrice) ;
						item.setQuantity(bdQuantity);
						item.setChargeable(item.getChargeable()) ;
						item.setIcText(new BigDecimal(0)) ;
						item.setIcHeader(new BigDecimal(0)) ;
						item.setKit("N") ;
						item.setOwner("") ;
						item.setReceiptRequired("") ;
						item.setRestricted("") ;
						item.setTaxable("Y") ;
						item.setUdf01("") ;
						item.setUdf02("") ;
						item.setUdf03("") ;
						item.setUdf04("") ;

						Element sid = e.getChild("SupplierID");
						if (sid != null && !HiltonUtility.isEmpty(sid.getTextTrim())) {
							item.setVendorId(sid.getTextTrim());
							supplierFromItemId = true;
						}
						else
						{
							supplierFromItemId = false;
						}
						Element et = e.getChild("ItemID").getChild("SupplierPartAuxiliaryID") ;
						if (et == null) {
							item.setUdf05("") ;
						} else {
							item.setUdf05(et.getTextTrim()) ;
						}
						et = e.getChild("ItemDetail").getChild("ManufacturerName") ;
						if (et == null) {
							item.setMfgName("") ;
						} else {
							item.setMfgName(et.getTextTrim());
						}
						et = e.getChild("ItemDetail").getChild("ManufacturerPartID") ;
						if (et == null) {
							item.setModel("") ;
						} else {
							item.setModel(et.getTextTrim());
						}
						//<Extrinsic name="SUPPLIER_CNTRCT_VER">1</Extrinsic>
						//<Extrinsic name="SUPPLIER_CNTRCT_ID">C002526J</Extrinsic>
						//SUPPLIER_CNTRCT_ID: C002526J  SUPPLIER_CNTRCT_VER: 1
						String memoLine = "";
						List extrinsicFields = e.getChild("ItemDetail").getChildren("Extrinsic");
						for (Iterator it2 = extrinsicFields.iterator(); it2.hasNext();)
			            {
			               	et = (Element) it2.next();
			               	if (et!= null)
		               		{
				               	if(supplierFromItemId)
				               	{
			               			memoLine += et.getAttribute("name").getValue() + ": " + et.getTextTrim() + "  ";
				               	}
				               	else
				               	{
				               		if (et.getAttribute("name").getValue().equals("SupplierID"))
				               		{
				               			item.setVendorId(et.getTextTrim());
				               		}
				               		else if (et.getAttribute("name").getValue().equals("SupplierName"))
				                    {
				                          item.setVendorName(et.getTextTrim());
				                    }
				               		else if (et.getAttribute("name").getValue().equals("CatalogID"))
				               		{
				               			//item.setCatalogId(et.getTextTrim());
				               		}
				               	}
				               	
				               	if (et.getAttribute("name").getValue().equals("SUPPLIER_CNTRCT_ID"))
			               		{
			               			item.setBlanketOrder(et.getTextTrim());
			               		}
			               	}
			            }
						if(supplierFromItemId)
		               	{
							item.setMemoLine(memoLine);
		               	}

						item.setAsset("");
						itemLookupList.add(item) ;
	        		}
	        	}
	        }  else {
	            Log.error(this, "Invalid punchoutOrderMessage document!") ;
	        }
        }
        catch(Exception e) {
            Log.error(this, "Exception: " + e.toString());
        }

		return itemLookupList ;
    }

    public String readTextFile(String fileName) {
		FileReader fr;
		StringBuffer buff = new StringBuffer() ;

		try {
			fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
		    buff.setLength(0) ;
			String lineText = null;

			while((lineText = br.readLine()) != null) {
				  buff.append(lineText) ;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return buff.toString() ;
    }

    private String getXmlPath(String organizationId)
    {
    	String	xmlPath = DictionaryManager.getInstance("host", organizationId).getProperty("browse-xml-path", "");
    	return xmlPath;
    }
    /**
     * @return Returns the organizationId.
     */
    public String getOrganizationId()
    {
        return organizationId;
    }
    /**
     * @param organizationId The organizationId to set.
     */
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogId() {
		return catalogId;
	}
}