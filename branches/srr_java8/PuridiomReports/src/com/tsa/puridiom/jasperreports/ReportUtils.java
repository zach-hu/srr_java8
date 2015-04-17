/*
 * Created on Feb 1, 2005
 */
package com.tsa.puridiom.jasperreports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import net.sf.jasperreports.engine.JRDataSource;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.documents.OrderType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.encryptor.Encryptor;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.EntityDataSource;
import com.tsa.puridiom.reports.datasource.HibernateQueryResultDataSource;
import com.tsa.puridiom.reports.datasource.ReportDataSource;
import com.tsa.puridiom.stdtable.tasks.StdTableRetrieveById;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportUtils
{
	/**
	 * <p>method used to add breakpoints to strings of text bigger than 40 chars
	 * It adds breaks at the next space after the 40.</p>
	 * @param value
	 * @return
	 */
	public static String getText(String value)
	{
			StringBuffer sbValue = new StringBuffer(value.toString());
			StringBuffer temp = new StringBuffer(value.toString());
			for(int i = 40; i < sbValue.length(); i = i + 40)
			{
				int breakLineIndex = i;
				if(temp.charAt(i) != ' ')
				{
					breakLineIndex = temp.indexOf(" ", i);
					if(breakLineIndex > i + 40 || breakLineIndex >= temp.length() || breakLineIndex == -1)
					{
						breakLineIndex = i;
					}
				}
				temp.insert(breakLineIndex, "\r\n");
			}

		return temp.toString();
	}
	public static String getProperty(String oid, String section, String property, String sdefault)
	{
		String prop = "";

		PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "TSA");
		return prop;
	}
	public static boolean isAkdocBidder(String oid, String vendorId)
	{
		boolean ret = false;
		try
		{
			ret = (((Vendor)VendorManager.getInstance().getVendor(oid, vendorId)).getVendorClass().indexOf("B") > -1);
		}
		catch (Exception e)
		{
			Log.error("ReportUtils", e.getMessage() + "- When finding if vendor has a bidders preference");
		}
		return ret;
	}
	public static String getCompanyName(String oid)
	{
		String companyName = "Puridiom";
		try
		{
			companyName = PropertiesManager.getInstance(oid).getProperty("COMPANY", "Name", "Puridiom");
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return companyName;
	}
	public static String getAlternateText(List entities, int index){
		Object oTemp = entities.get(index - 1);
        String stdText = null;
        if (oTemp instanceof RequisitionLine)
        {
            RequisitionLine reqLine = (RequisitionLine) oTemp;
            if(reqLine.getAltText()!= null && reqLine.getAltText().getDocText()!= null)
            	stdText = reqLine.getAltText().getDocText().getStdText();
        }
        else if (oTemp instanceof PoLine){
        	PoLine poLine = (PoLine) oTemp;
            if(poLine.getAltText()!= null && poLine.getAltText().getDocText()!= null)
            	stdText = poLine.getAltText().getDocText().getStdText();
        }

        else if (oTemp instanceof InvoiceLine){
        }
        return stdText;
	}

	public static String getPrintEffectiveDates(Object PoHeader_effectiveDate, Object PoHeader_expirationDate, Object PoHeader_poType, String organizationId)
	{
		StringBuffer sbText = new StringBuffer("");
		try
		{
			if(PropertiesManager.getInstance(organizationId).getProperty("Print Orders", "PrintEffectiveDates", "Y").equalsIgnoreCase("Y"))
			{
				if(Utility.isObjectEmpty(PoHeader_poType)){	PoHeader_poType = "PO";	}
				String type = (String)PoHeader_poType;
				if(type.equalsIgnoreCase(OrderType.SERVICE_BLANKET) || type.equalsIgnoreCase(OrderType.BLANKET_ORDER)
						|| type.equalsIgnoreCase(OrderType.DELIVERY_ORDER) || type.equalsIgnoreCase(OrderType.CONTRACT))
				{
					String text = PropertiesManager.getInstance(organizationId).getProperty("Print Orders", "PrintEffectiveText", "THIS ORDER IS EFFECTIVE FROM");

					if(PoHeader_poType.equals(OrderType.CONTRACT))
					{
						text = text.replaceAll("ORDER", "CONTRACT");
					}
					sbText.append(text);
					sbText.append(" ");
					if(PoHeader_effectiveDate != null)
					{
						sbText.append(HiltonUtility.getFormattedDate(PoHeader_effectiveDate, organizationId));
					}
					sbText.append(" TO ");
					if(PoHeader_expirationDate != null)
					{
						sbText.append(HiltonUtility.getFormattedDate(PoHeader_expirationDate, organizationId));
					}
				}
			}

		}
		catch (Exception e)
		{
			Log.warn(e, "getPrintEffectiveDates " + e.toString());
		}
		return sbText.toString();
	}

	public static String getPrintReplaceOrder(Object PoHeader_udf7Code, String organizationId)
	{
		StringBuffer sbText = new StringBuffer("");
		try
		{
			if(PropertiesManager.getInstance(organizationId).getProperty("Print Orders", "PrintReplaceOrder", "N").equalsIgnoreCase("Y"))
			{
				if(!Utility.isObjectEmpty(PoHeader_udf7Code))
				{
					sbText.append(PropertiesManager.getInstance(organizationId).getProperty("Print Orders", "PrintReplaceText", "THIS REPLACES ORDER NO. E"));
					sbText.append(" ");
					sbText.append(PoHeader_udf7Code);
				}

			}

		}
		catch (Exception e)
		{
			Log.warn(e, "getPrintReplaceOrder " + e.toString());
		}
		return sbText.toString();
	}

	public static String getPoConfirming(Object PoHeader_confirming, Object PoHeader_poType, Object PoHeader_confirmNameCode, Object PoHeader_confirmDate, String organizationId)
	{
		StringBuffer sbText = new StringBuffer("");
		StringBuffer logText = new StringBuffer("");

		logText.append("getPoConfirming[ ");
		logText.append("PoHeader_confirming: " + PoHeader_confirming.toString());
		logText.append(", PoHeader_poType: " + PoHeader_poType.toString());
		logText.append(", PoHeader_confirmNameCode: " + PoHeader_confirmNameCode.toString());
		logText.append(",PoHeader_confirmDate: " + PoHeader_confirmDate.toString());
		logText.append(", oid: " + organizationId);

		Log.debug("ReportUtils", logText.toString());
		sbText.append("CONFIRMING");

		try
		{
			if (!Utility.isObjectEmpty(PoHeader_confirming))
			{
				String confirming = (String) PoHeader_confirming;
				if (confirming.equalsIgnoreCase("Y"))
				{
					String text = (String) PoHeader_poType + " COPIES";
					String code = PropertiesManager.getInstance(organizationId).getProperty(text, "CONFIRM COMMENT", "CONFIRMING");
					try
					{
						Log.debug("ReportUtils", "getPoConfirming looking for comment:  " + code);
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("stdcomment-retrieve-by-id.xml");
						Map incomingRequest = new HashMap();
						incomingRequest.put("organizationId", organizationId);
						incomingRequest.put("StdComment_commentId", code);
						process.executeProcess(incomingRequest);

						if (process.getStatus() == Status.SUCCEEDED)
						{
							DocText docText = (DocText) incomingRequest.get("docText");

							if (docText != null)
							{
								sbText.append(docText.getStdText());
							}

							if (!Utility.isObjectEmpty(PoHeader_confirmNameCode))
							{
								if (sbText.length() > 0)
								{
									sbText.append(" ");
								}
								sbText.append(PoHeader_confirmNameCode);
							}

							if (PoHeader_confirmDate != null)
							{
								if (sbText.length() > 0)
								{
									sbText.append(" ");
								}
								sbText.append(HiltonUtility.getFormattedDate(PoHeader_confirmDate, organizationId));
							}
						}
					} catch (Exception exception)
					{
						Log.warn(exception, "getPrintReplaceOrder " + exception.toString());
					}
				}
			}
		} catch (Exception e)
		{
			Log.warn(e, "getPrintReplaceOrder " + e.toString());
		}
		return sbText.toString();
	}
	public static String getPoConfirmingDE(Object PoHeader_confirming, Object PoHeader_poType, Object PoHeader_confirmNameCode, Object PoHeader_confirmDate, String organizationId)
	{
		StringBuffer sbText = new StringBuffer("");
		StringBuffer logText = new StringBuffer("");

		logText.append("getPoConfirming[ ");
		logText.append("PoHeader_confirming: " + PoHeader_confirming.toString());
		logText.append(", PoHeader_poType: " + PoHeader_poType.toString());
		logText.append(", PoHeader_confirmNameCode: " + PoHeader_confirmNameCode.toString());
		logText.append(",PoHeader_confirmDate: " + PoHeader_confirmDate.toString());
		logText.append(", oid: " + organizationId);

		Log.debug("ReportUtils", logText.toString());
		sbText.append("Please confirm this order immediately / Bitte senden Sie uns umgehend Ihre Auftragsbestätigung");

		try
		{
			if (!Utility.isObjectEmpty(PoHeader_confirming))
			{
				String confirming = (String) PoHeader_confirming;
				if (confirming.equalsIgnoreCase("Y"))
				{
					String text = (String) PoHeader_poType + " COPIES";
					String code = PropertiesManager.getInstance(organizationId).getProperty(text, "CONFIRM COMMENT", "Please confirm this order immediately / Bitte senden Sie Uns ungehend Ihre Auftragsbestätigung");
					try
					{
						Log.debug("ReportUtils", "getPoConfirming looking for comment:  " + code);
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("stdcomment-retrieve-by-id.xml");
						Map incomingRequest = new HashMap();
						incomingRequest.put("organizationId", organizationId);
						incomingRequest.put("StdComment_commentId", code);
						process.executeProcess(incomingRequest);

						if (process.getStatus() == Status.SUCCEEDED)
						{
							DocText docText = (DocText) incomingRequest.get("docText");

							if (docText != null)
							{
								sbText.append(docText.getStdText());
							}

							if (!Utility.isObjectEmpty(PoHeader_confirmNameCode))
							{
								if (sbText.length() > 0)
								{
									sbText.append(" ");
								}
								sbText.append(PoHeader_confirmNameCode);
							}

							if (PoHeader_confirmDate != null)
							{
								if (sbText.length() > 0)
								{
									sbText.append(" ");
								}
								sbText.append(HiltonUtility.getFormattedDate(PoHeader_confirmDate, organizationId));
							}
						}
					} catch (Exception exception)
					{
						Log.warn(exception, "getPrintReplaceOrder " + exception.toString());
					}
				}
			}
		} catch (Exception e)
		{
			Log.warn(e, "getPrintReplaceOrder " + e.toString());
		}
		return sbText.toString();
	}
	public static String getPoConfirmingNL(Object PoHeader_confirming, Object PoHeader_poType, Object PoHeader_confirmNameCode, Object PoHeader_confirmDate, String organizationId)
	{
		StringBuffer sbText = new StringBuffer("");
		StringBuffer logText = new StringBuffer("");

		logText.append("getPoConfirming[ ");
		logText.append("PoHeader_confirming: " + PoHeader_confirming.toString());
		logText.append(", PoHeader_poType: " + PoHeader_poType.toString());
		logText.append(", PoHeader_confirmNameCode: " + PoHeader_confirmNameCode.toString());
		logText.append(",PoHeader_confirmDate: " + PoHeader_confirmDate.toString());
		logText.append(", oid: " + organizationId);

		Log.debug("ReportUtils", logText.toString());
		sbText.append("Please confirm this order immediately");

		try
		{
			if (!Utility.isObjectEmpty(PoHeader_confirming))
			{
				String confirming = (String) PoHeader_confirming;
				if (confirming.equalsIgnoreCase("Y"))
				{
					String text = (String) PoHeader_poType + " COPIES";
					String code = PropertiesManager.getInstance(organizationId).getProperty(text, "CONFIRM COMMENT", "Please confirm this order immediately");
					try
					{
						Log.debug("ReportUtils", "getPoConfirming looking for comment:  " + code);
						PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
						PuridiomProcess process = processLoader.loadProcess("stdcomment-retrieve-by-id.xml");
						Map incomingRequest = new HashMap();
						incomingRequest.put("organizationId", organizationId);
						incomingRequest.put("StdComment_commentId", code);
						process.executeProcess(incomingRequest);

						if (process.getStatus() == Status.SUCCEEDED)
						{
							DocText docText = (DocText) incomingRequest.get("docText");

							if (docText != null)
							{
								sbText.append(docText.getStdText());
							}

							if (!Utility.isObjectEmpty(PoHeader_confirmNameCode))
							{
								if (sbText.length() > 0)
								{
									sbText.append(" ");
								}
								sbText.append(PoHeader_confirmNameCode);
							}

							if (PoHeader_confirmDate != null)
							{
								if (sbText.length() > 0)
								{
									sbText.append(" ");
								}
								sbText.append(HiltonUtility.getFormattedDate(PoHeader_confirmDate, organizationId));
							}
						}
					} catch (Exception exception)
					{
						Log.warn(exception, "getPrintReplaceOrder " + exception.toString());
					}
				}
			}
		} catch (Exception e)
		{
			Log.warn(e, "getPrintReplaceOrder " + e.toString());
		}
		return sbText.toString();
	}
	public static String getPrintRevisionWarning(Object PoHeader_revisionNumber, String organizationId)
	{
		StringBuffer sbText = new StringBuffer("");
		try
		{
			if(PropertiesManager.getInstance(organizationId).getProperty("Print Orders", "PrintRevisionWarning", "N").equalsIgnoreCase("Y"))
			{
				if(!Utility.isObjectEmpty(PoHeader_revisionNumber))
				{
					BigDecimal rev = (BigDecimal)PoHeader_revisionNumber;
					if(rev.compareTo(new BigDecimal(0)) > 0)
					{
						String temp = PropertiesManager.getInstance(organizationId).getProperty("Print Orders", "PrintRevWarnText", "*** THIS IS REVISION -REV- ***");
						if(temp.indexOf("-REV-") >= 0)
						{
							temp = temp.replaceAll("-REV-", ((BigDecimal)PoHeader_revisionNumber).toString());
						}
						sbText.append(temp);
					}
				}

			}

		}
		catch (Exception e)
		{
			Log.warn(e, "getPrintReplaceOrder " + e.toString());
		}
		return sbText.toString();
	}


	public static String getVendContactDetails(String type, String vendorId, String contactCode, String organizationId)
	{
		return HiltonUtility.getVendContactDetails(type, vendorId, contactCode, organizationId);
	}

	public static String getStdTableDescription(String organizationId, String tableType, String tableKey)
	{
		return ReportUtils.getStdTableDescription(organizationId, tableType, tableKey, "description");
	}
	
	public static String getStdTableField(String organizationId, String tableType, String tableKey, String field)
	{
		String fieldReturn = "";
		if(Utility.isEmpty(tableKey))
		{
			return "";
		}
		try
		{
    		Map incomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("stdtable-retrieve-by-id.xml");
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("StdTable_tableType", tableType);
			incomingRequest.put("StdTable_tableKey", tableKey);
			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				StdTable stdTable = (StdTable)incomingRequest.get("stdTable");
				if(field.equalsIgnoreCase("description"))
				{
					fieldReturn = stdTable.getDescription();
				}
				else if(field.equalsIgnoreCase("udf1Code"))
				{
					fieldReturn = stdTable.getUdf1Code();
				}
				else if(field.equalsIgnoreCase("udf2Code"))
				{
					fieldReturn = stdTable.getUdf2Code();
				}
				else if(field.equalsIgnoreCase("udf2Code"))
				{
					fieldReturn = stdTable.getUdf2Code();
				}
			}
			else
			{
				fieldReturn = "";
			}
		}
		catch (Exception exception)
		{
			fieldReturn = "";
			Log.warn(exception, exception.toString());
		}

		Log.debug("ReportUtils", "getStdTableField[ " + "tableType: " + tableType + ", tableKey: " + tableKey +
				", oid: " + organizationId + "returns: " + fieldReturn  + "]");

		return fieldReturn;
	}

	public static String getStdTableDescription(String organizationId, String tableType, String tableKey, String field)
	{
		String description = "";
		if(Utility.isEmpty(tableKey))
		{
			return "";
		}
		try
		{
    		Map incomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("stdtable-retrieve-by-id.xml");
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("StdTable_tableType", tableType);
			incomingRequest.put("StdTable_tableKey", tableKey);
			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				StdTable stdTable = (StdTable)incomingRequest.get("stdTable");
				if(field.equalsIgnoreCase("description"))
				{
					description = stdTable.getDescription();
				}
				else if(field.equalsIgnoreCase("fax"))
				{
					description = stdTable.getDescription();
				}
			}
			else
			{
				description = "";
			}
		}
		catch (Exception exception)
		{
			description = "";
			Log.warn(exception, exception.toString());
		}

		if(Utility.isEmpty(description))
		{
			description = tableKey;
		}

		Log.debug("ReportUtils", "getStdTableDescription[ " + "tableType: " + tableType + ", tableKey: " + tableKey +
				", oid: " + organizationId + "returns: " + description  + "]");

		return description;
	}

	public static String getUdfMultipleLogoPoHeader(String organizationId, String type, String udfLogo, String icHeader)
	{
		String logoname = "";

		try
		{
			Map incomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("poheader-retrieve-by-id.xml");
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("PoHeader_icPoHeader", icHeader);
			process.executeProcess(incomingRequest);

			Map newIncomingRequest = new HashMap();
			PuridiomProcess processAddress = processLoader.loadProcess("address-retrieve-by-id.xml");
			newIncomingRequest.put("Address_addressType", "ADDR");

			if (process.getStatus() == Status.SUCCEEDED)
			{
				PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
				if(poHeader == null)
				{
					poHeader = new PoHeader();
				}
				if(udfLogo.equalsIgnoreCase("UDF1") && !HiltonUtility.isEmpty(poHeader.getUdf1Code()))
				{
					logoname = poHeader.getUdf1Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF2") && !HiltonUtility.isEmpty(poHeader.getUdf2Code()))
				{
					logoname = poHeader.getUdf2Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF3") && !HiltonUtility.isEmpty(poHeader.getUdf3Code()))
				{
					logoname = poHeader.getUdf3Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF4") && !HiltonUtility.isEmpty(poHeader.getUdf4Code()))
				{
					logoname = poHeader.getUdf4Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF5") && !HiltonUtility.isEmpty(poHeader.getUdf5Code()))
				{
					logoname = poHeader.getUdf5Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF6") && !HiltonUtility.isEmpty(poHeader.getUdf6Code()))
				{
					logoname = poHeader.getUdf6Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF7") && !HiltonUtility.isEmpty(poHeader.getUdf7Code()))
				{
					logoname = poHeader.getUdf7Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF8") && !HiltonUtility.isEmpty(poHeader.getUdf8Code()))
				{
					logoname = poHeader.getUdf8Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF9") && !HiltonUtility.isEmpty(poHeader.getUdf9Code()))
				{
					logoname = poHeader.getUdf9Code();
				}
				if(udfLogo.equalsIgnoreCase("UDF10") && !HiltonUtility.isEmpty(poHeader.getUdf10Code()))
				{
					logoname = poHeader.getUdf10Code();
				}
				newIncomingRequest.put("Address_addressCode", logoname);
				processAddress.executeProcess(newIncomingRequest);
				Address address = (Address)newIncomingRequest.get("address");
				if(address == null)
				{
					address = new Address();
				}
				if(!HiltonUtility.isEmpty(logoname))
				{
					logoname = "logo_" + logoname.toLowerCase();
				}
				if(type.equalsIgnoreCase("ADDR10") && !HiltonUtility.isEmpty(address.getAddressLine1()))
				{
					logoname = address.getAddressLine1();
				}
				if(type.equalsIgnoreCase("ADDRLINE2") && !HiltonUtility.isEmpty(address.getAddressLine2()))
				{
					logoname = address.getAddressLine2();
				}
				if(type.equalsIgnoreCase("ADDRCITY") && !HiltonUtility.isEmpty(address.getCity()))
				{
					logoname = address.getCity() + ", " + HiltonUtility.ckNull(address.getState()) + " " + HiltonUtility.ckNull(address.getPostalCode());
				}
			}
			else
			{
				logoname = "";
			}
		}
		catch (Exception exception)
		{
			logoname = "";
			Log.warn(exception, exception.toString());
		}

		return logoname;
	}

	public static String getPaymentTerms(String organizationId, String termCode)
	{
		String description = "";
		if(Utility.isEmpty(termCode))
		{
			return "";
		}
		try
		{
    		Map incomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("paymentterm-retrieve-by-id.xml");
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("PaymentTerm_termsCode", termCode);

			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				PaymentTerm paymentTerm = (PaymentTerm)incomingRequest.get("paymentTerm");
				description = paymentTerm.getTermDescription();
			}
			else
			{
				description = "";
			}
		}
		catch (Exception exception)
		{
			description = "";
			Log.warn(exception, exception.toString());
		}

		Log.debug("ReportUtils", "getStdTableDescription[ " + "termCode: " + termCode + 	", oid: " + organizationId + "returns: " + description  + "]");

		return description;
	}

	public static void main(String[] args)
	{
		//System.err.println("description: " + ReportUtils.getStdTableDescription("TEST", "FOB", "PREPAID"));
		//ReportUtils.getSubReportDataSource("r_vchs", "InvoiceHeader_invoiceNumber", "1234", ">=", "akdoc");
		//ReportUtils.getSubReportDataSource("r-popd2", "InvoiceHeader_poNumber,InvoiceHeader_releaseNumber", $F{PoHeader_poNumber} + "," + $F{PoHeader_releaseNumber}, "=,=", ",", ",", ",", organizationId)


	}

    public static String getEvaluationLink(String uid, String oid, String mid, String action, String number, String filename, String icHeader, String type, String to)
    {
        return ReportUtils.getApprovalLink(uid, oid, mid, action, number, filename, icHeader, type, to);
    }

	public static String getApprovalLink(String uid, String oid, String mid, String action, String number, String filename, String icHeader, String type)
	{
		return ReportUtils.getApprovalLink(uid, oid, mid, action, number, filename, icHeader, type, "");
	}

	public static String getApprovalLink(String uid, String oid, String mid, String action, String number, String filename, String icHeader, String type, String to)
	{
		Encryptor enc = new Encryptor();
		StringBuffer ret = new StringBuffer();
		String servlet = PropertiesManager.getInstance(oid).getProperty("APPROVALS", "REDIRECTSERVLET", "/ApproveRedirect");
		ret.append(servlet);
		ret.append("/ext=" + enc.of_set(uid));
		ret.append("&zat=" + enc.of_set(oid));
		ret.append("&ail=" + enc.of_set(mid));
		ret.append("&xnot=" + enc.of_set(number));
		ret.append("&ack=" + action);
		ret.append("&std=" + enc.of_set(icHeader));
		ret.append("&xpe=" + enc.of_set(type));
		if(Utility.isEmpty(to))
		{
			to = "/approval/mail_approve.jsp";
		}
		ret.append("&to=" + to);
		ret.append("&em=1");
		if(!Utility.isEmpty(filename))
		{
			ret.append("&filename=" + filename);
		}

		return ret.toString();
	}

	public static JRDataSource getTotalsDS(BigDecimal subtotal, BigDecimal discount, BigDecimal tax, BigDecimal useTaxAmount, BigDecimal shipping, BigDecimal shippingTaxAmount, BigDecimal other, BigDecimal otherTaxAmount, BigDecimal total)
	{
		Totals totals = new Totals();
		totals.setSubtotal(subtotal);
		totals.setDiscount(discount);
		totals.setTaxAmount(tax);
		totals.setUseTaxAmount(useTaxAmount);
		totals.setShippingCharges(shipping);
		totals.setShippingTaxAmount(shippingTaxAmount);
		totals.setOtherCharges(other);
		totals.setOtherTaxAmount(otherTaxAmount);
		totals.setTotal(total);

		JRDataSource ds = new EntityDataSource(totals);
		return ds;
	}

	public static HibernateQueryResultDataSource getShipToDS(List entities, int index)
    {
		return ReportUtils.getShipToDS(entities, index, null, null);
    }
	
	public static HibernateQueryResultDataSource getReqSourcedToDS(List entities, int index)
    {
		Object oTemp = entities.get(index - 1);
        List reqSourcedMapList = null;
        List addyList = new ArrayList();

        if (oTemp instanceof RequisitionLine)
        {
            RequisitionLine reqLine = (RequisitionLine) oTemp;
            Map reqSourcedMap  = reqLine.getReqSourcedMapList();
            addyList.add(ReportUtils.createReqSourcedReport(reqSourcedMap));
            
        }


        HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(addyList);
        return ds;
    }

	public static HibernateQueryResultDataSource getRcvLineShipToDS(List shipToList, String headerShipToCode, Object requiredDate)
	{
		List addyList = new ArrayList();
		if(shipToList == null)
        {
        	shipToList = new ArrayList();
        }
		if(shipToList.size() == 1 && !Utility.isEmpty(headerShipToCode))
        {
        	ShipTo shipTo = (ShipTo)shipToList.get(0);
        	if(shipTo.getShipToCode().equalsIgnoreCase(headerShipToCode))
        	{
        		Address addy = shipTo.getShipToAddress();
        		addyList.add(ReportUtils.createShipToLineReport(addy, shipTo, requiredDate));
        	}
        }
		else
        {
            for(int i = 0; i < shipToList.size(); i++)
            {
            	ShipTo shipTo = (ShipTo)shipToList.get(i);
            	Address addy = shipTo.getShipToAddress();
            	addyList.add(ReportUtils.createShipToLineReport(addy, shipTo, requiredDate));
            }
        }
		HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(addyList);
        return ds;
	}

	public static HibernateQueryResultDataSource getShipToDS(List entities, int index, String headerShipToCode, Object requiredDate)
    {
        Object oTemp = entities.get(index - 1);
        List shipToList = null;
        List addyList = new ArrayList();

        if (oTemp instanceof RequisitionLine)
        {
            RequisitionLine reqLine = (RequisitionLine) oTemp;
            shipToList = reqLine.getShipToList();
            for(int i = 0; i < shipToList.size(); i++)
            {
            	ShipTo shipTo = (ShipTo)shipToList.get(i);
            	Address addy = shipTo.getShipToAddress();
            	addyList.add(ReportUtils.createShipToLineReport(addy, shipTo, requiredDate));
            }
        }
        else if (oTemp instanceof PoLine)
        {
            PoLine poLine = (PoLine) oTemp;
            shipToList = poLine.getShipToList();
            if(shipToList == null)
            {
            	shipToList = new ArrayList();
            }
            if(shipToList.size() == 1 && !Utility.isEmpty(headerShipToCode))
            {
            	ShipTo shipTo = (ShipTo)shipToList.get(0);
            	if(shipTo.getShipToCode().equalsIgnoreCase(headerShipToCode))
            	{
            		Address addy = shipTo.getShipToAddress();
            		addyList.add(ReportUtils.createShipToLineReport(addy, shipTo, requiredDate));
            	}
            }
            else
            {
	            for(int i = 0; i < shipToList.size(); i++)
	            {
	            	ShipTo shipTo = (ShipTo)shipToList.get(i);
	            	Address addy = shipTo.getShipToAddress();
	            	addyList.add(ReportUtils.createShipToLineReport(addy, shipTo, requiredDate));
	            }
            }
        }

        HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(addyList);
        return ds;
    }
	
	public static Object createReqSourcedReport(Map reqSourcedMap)
	{
		RequisitionHeader requisitionHeader = (RequisitionHeader)reqSourcedMap.get("requisitionHeader");
		PoHeader poHeader = (PoHeader)reqSourcedMap.get("poHeader");
		ReqSourcedReport reqSourcedAddy = new ReqSourcedReport(requisitionHeader);
		reqSourcedAddy.setPoHeader(poHeader);
		reqSourcedAddy.setReceiptHeaderList((List)reqSourcedMap.get("receiptHeaderList"));
		reqSourcedAddy.setInvoiceHeaderList((List)reqSourcedMap.get("invoiceHeaderList"));
		reqSourcedAddy.setInvBinLocationList((List)reqSourcedMap.get("invBinLocationList"));
    	return reqSourcedAddy;
	}

	public static Object createShipToLineReport(Address addy, ShipTo shipTo, Object headerRequiredDate)
	{
		ShipToLineReport shipToAddy = new ShipToLineReport(addy);
    	shipToAddy.setQuantity(shipTo.getQuantity());
    	shipToAddy.setShipToCode(shipTo.getShipToCode());
    	shipToAddy.setAttention(shipTo.getAttention());
    	if(!Utility.isObjectEmpty(headerRequiredDate))
    	{
    		if(!headerRequiredDate.equals(shipTo.getShipDate()))
    		{
    			shipToAddy.setRequiredDate(shipTo.getShipDate());
    		}
    	}
    	else
    	{
    		shipToAddy.setRequiredDate(shipTo.getShipDate());
    	}

    	return shipToAddy;
	}
	/**
	 * @param subReport
	 * @param oColname
	 * @param oFilter_txt
	 * @param oOperator
	 * @param lOperator
	 * @param oOriginalFilter
	 * @parma oSort
	 * @param organizationId
	 * @return
	 */
	public static JRDataSource getSubReportDataSource(String subReport, Object oColname, Object oFilter_txt, Object oOperator, Object lOperator, Object oOriginalFilter, Object oSort, String organizationId)
	{
		ReportDataSource rDS = null;
		try
		{
    		Map incomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("subreport-get-data.xml");
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("browseName", subReport);
			String sColName =  (String)oColname;
			if (sColName.indexOf(",") > 0)
			{
				oColname = ((String) oColname).split(",");
				String aColname[] = (String[])oColname;
				oFilter_txt = ((String) oFilter_txt).split(",", aColname.length);
				oOperator = ((String) oOperator).split(",", aColname.length);
				lOperator = ((String) lOperator).split(",", aColname.length);
				oOriginalFilter = ((String) oOriginalFilter).split(",", aColname.length);

				oSort = ((String) oSort).split(",", aColname.length);
			}
			incomingRequest.put("colname", oColname);
			incomingRequest.put("filter_txt", oFilter_txt);
			incomingRequest.put("operator", oOperator);
			incomingRequest.put("logicalOperator", lOperator);
			incomingRequest.put("originalFilter", oOriginalFilter);
			incomingRequest.put("sort", oSort);

			incomingRequest.put("execute", "N");

			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				List datasource = (List)incomingRequest.get("datasource");
				BrowseObject browseObject = (BrowseObject)incomingRequest.get("browseObject");
				rDS = new ReportDataSource(browseObject, datasource);
			}
			else
			{

			}
		}
		catch (Exception exception)
		{
			Log.warn(exception, exception.toString());
		}
		return rDS;
	}
	public static HibernateQueryResultDataSource getLineShipToDS(Object oShipToList)
    {
		List addyList = new ArrayList();
		if(oShipToList != null)
		{
			List shipToList = (List)oShipToList;
			for(int i = 0; i < shipToList.size(); i++)
            {
            	ShipTo shipTo = (ShipTo)shipToList.get(i);
            	Address addy = shipTo.getShipToAddress();
            	ShipToLineReport shipToAddy = new ShipToLineReport(addy);
            	shipToAddy.setQuantity(shipTo.getQuantity());
            	shipToAddy.setShipToCode(shipTo.getShipToCode());
            	shipToAddy.setAttention(shipTo.getAttention());
            	addyList.add(shipToAddy);
            }
		}

        HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(addyList);
        return ds;
    }

	public static EntityDataSource getLineDS(Object lineNumber, Object quantity, Object umcode, Object itemNumber, Object unitPrice, Object lineTotal, Object description, Object umFactor, Object umCode)
	{
		LineReport line = new LineReport();
		line.setDescription(description);
		line.setItemNumber(itemNumber);
		line.setLineNumber(lineNumber);
		line.setLineTotal(lineTotal);
		line.setQuantity(quantity);
		line.setUmCode(umCode);
		line.setUnitPrice(unitPrice);
		line.setUmFactor(umFactor);
		EntityDataSource ds = new EntityDataSource(line);
		return ds;

	}
    public static HibernateQueryResultDataSource getAccountsDS(List entities, int index)
    {
        Object oTemp = entities.get(index - 1);
        List accountsList = null;
        if (oTemp instanceof RequisitionLine)
        {
            RequisitionLine reqLine = (RequisitionLine) oTemp;
            accountsList = reqLine.getAccountList();
        }
        else if (oTemp instanceof PoLine)
        {
            PoLine poLine = (PoLine) oTemp;
            accountsList = poLine.getAccountList();
        }

        else if (oTemp instanceof InvoiceLine)
        {
            InvoiceLine invoiceLine = (InvoiceLine) oTemp;
            accountsList = invoiceLine.getAccountList();
        }

        HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(accountsList);
        return ds;
    }

    public static HibernateQueryResultDataSource getInspectionsDS(List entities, int index)
    {
    	Object oTemp = entities.get(index - 1);
    	List inspectionsList = null;
    	if (oTemp instanceof RequisitionLine)
    	{
    		RequisitionLine reqLine = (RequisitionLine) oTemp;
    		inspectionsList = reqLine.getInspectionList();
    	}
    	if (oTemp instanceof PoLine)
    	{
    		PoLine poLine = (PoLine) oTemp;
    		inspectionsList = poLine.getInspectionList();
    	}

    	HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(inspectionsList);
    	return ds;
    }

    public static boolean isInspStatusPrintable(String status, DBSession dbs)
    {
    	boolean printable = false;
    	try {
	    	StdTableRetrieveById stdTableRetrive = new StdTableRetrieveById();

	    	Map parameter = new HashMap();
	    	parameter.put("dbsession", dbs);
	    	parameter.put("StdTable_tableType", "RECSTAT");
	    	parameter.put("StdTable_tableKey", status);

			StdTable stdTable = (StdTable)stdTableRetrive.executeTask(parameter);

			if (stdTable != null && !HiltonUtility.isEmpty(stdTable.getUdf2Code()) && stdTable.getUdf2Code().equalsIgnoreCase("Y")) {
				printable = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
    	return printable;
    }

    public static HibernateQueryResultDataSource getDS(Object listObj)
    {
    	List objectList = null;
    	if (listObj instanceof List)
    	{
			objectList = (List) listObj;
		} else if(listObj != null){
			objectList = new ArrayList();
			objectList.add(listObj);
	    } else {
	    	objectList = new ArrayList();
	    }

    	HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(objectList);
    	return ds;
    }

    public static String getUserName(String userId, String oid)
    {
    	return ReportUtils.getUserDetails("name", userId, oid);
    }
    public static UserProfile getUserProfile(String userId, String oid)
    {
    	UserProfile userProfile = null;
    	if(!Utility.isEmpty(userId))
        {
            try
            {
                userProfile = UserManager.getInstance().getUser(oid, userId);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    	return userProfile;
    }

    public static String getUserDetails(String detail, String userId, String oid)
    {
        String ret = "";
        Log.debug("ReportUtils", "getUserDetail[ " + "userid: " + userId + ", oid: " + oid + "]");
        UserProfile userProfile = ReportUtils.getUserProfile(userId, oid);
        if(userProfile != null)
        {
        	if(detail.equalsIgnoreCase("email"))
        	{
        		ret = userProfile.getMailId();
        	}
        	else if(detail.equalsIgnoreCase("title"))
        	{
        		ret = userProfile.getTitle();
        	}
        	else if(detail.equalsIgnoreCase("name"))
        	{
        		ret = userProfile.getDisplayName();
        	}
        	else if(detail.equalsIgnoreCase("phone"))
        	{
        		ret = userProfile.getPhoneNumber();
        	}
        	else if(detail.equalsIgnoreCase("fax"))
        	{
        		ret = userProfile.getFaxNumber();
        	}
        }
        Log.debug("ReportUtils.getUserDetail", "returns: " + ret);
        return ret;
    }

    public static String getUserEmail(String userId, String oid)
    {
        return ReportUtils.getUserDetails("email", userId, oid);
    }

    public static String getUserPhone(String userId, String oid)
    {
    	return ReportUtils.getUserDetails("phone", userId, oid);
    }

    /**
     * @deprecated use getUserDetails
     * @param userId
     * @param data
     * @param oid
     * @return
     */
    public static String getUserData(String userId, String data, String oid)
    {
    	return ReportUtils.getUserDetails(data, userId, oid);
    }

    public static HibernateQueryResultDataSource getCommentsDS(Object listObj, String placement)
    {
    	List docCommentList = null;
    	if (listObj instanceof List)
    	{
			docCommentList = (List) listObj;
		}
    	List commentsList = new ArrayList();
    	for (int i = 0; i < docCommentList.size(); i++)
        {
            DocComment docComment = (DocComment) docCommentList.get(i);
            if (placement.equalsIgnoreCase("before") && docComment.getCommentPlace().equalsIgnoreCase("B") && docComment.getCommentPrint().equalsIgnoreCase("Y"))
            {
                commentsList.add(docComment);
            }
            else if (placement.equalsIgnoreCase("after") && docComment.getCommentPlace().equalsIgnoreCase("A") && docComment.getCommentPrint().equalsIgnoreCase("Y"))
            {
                commentsList.add(docComment);
            }
        }

    	HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(commentsList);
        return ds;
    }

    public static HibernateQueryResultDataSource getCommentsDS(List entities, int index, String place)
    {
        Object oTemp = entities.get(index - 1);
        List docCommentList = null;
        if (oTemp instanceof RequisitionLine)
        {
            RequisitionLine reqLine = (RequisitionLine) oTemp;
            docCommentList = reqLine.getDocCommentList();
        }
        else if (oTemp instanceof PoLine)
        {
            PoLine poLine = (PoLine) oTemp;
            docCommentList = poLine.getDocCommentList();
        }
        List commentsList = new ArrayList();

        for (int i = 0; i < docCommentList.size(); i++)
        {
            DocComment docComment = (DocComment) docCommentList.get(i);
            if (place.equalsIgnoreCase("before") && docComment.getCommentPlace().equalsIgnoreCase("B")&& docComment.getCommentPrint().equalsIgnoreCase("Y"))
            {
                commentsList.add(docComment);
            }
            else if (place.equalsIgnoreCase("after") && docComment.getCommentPlace().equalsIgnoreCase("A")&& docComment.getCommentPrint().equalsIgnoreCase("Y"))
            {
                commentsList.add(docComment);
            }
        }

        HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(commentsList);
        return ds;
    }
    public static String getAcctString(String fld1, String fld2, String fld3, String fld4, String fld5, String fld6,
                                                         String fld7, String fld8, String fld9, String fld10, String fld11, String fld12,
                                                         String fld13, String fld14, String fld15, String organizationId)
    {
        String sep = PropertiesManager.getInstance(organizationId).getProperty("MISC", "AccountSeparator", "-");
        String sAcctElements = PropertiesManager.getInstance(organizationId).getProperty("FORM", "AccountElements", "15");
        int acctElements = Integer.parseInt(sAcctElements);
        StringBuffer temp = new StringBuffer();
        temp.setLength(0);
        int elements = 0;
        if(elements < acctElements)
        {
        	temp = ReportUtils.addFld(temp, fld1, sep);
        	elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld2, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld3, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld4, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld5, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld6, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld7, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld8, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld9, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld10, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld11, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld12, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld13, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld14, sep);
	        elements++;
        }
        if(elements < acctElements)
        {
	        temp = ReportUtils.addFld(temp, fld15, sep);
	        elements++;
        }

        return temp.toString();
    }

    public static StringBuffer addFld(StringBuffer temp, String fld, String sep)
    {
        if(!Utility.isEmpty(fld))
        {
            if(temp.length() > 0)
            {
                temp.append(sep);
            }

            temp.append(fld);
        }
        return temp;
    }

    public static String getDateApproved(String approved, Date dateApproved)
    {
        if(approved == null)
        {
            approved = "";
        }
        String str = "";
        try
        {
            if(approved.equals("A"))
            {
                str = "Current Approver";
            }
            else if(approved.equals("Y"))
            {
                if(dateApproved != null)
                {
                    str = dateApproved.toString();
                }
            }
            else
            {
                str = " ";
            }
        }
        catch (Exception e)
        {
            Log.error(ReportUtils.class, " -getApprovedDate");
            e.printStackTrace();
        }
        return str;

    }
    /**
     * Catches division by 0. if so divides by 1.
     * @param a
     * @param b
     * @return
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b)
    {
        BigDecimal ret = null;
        try
        {
            ret = a.divide(b, BigDecimal.ROUND_UP);
        }
        catch (Exception e)
        {
            ret = a;
        }
        return ret;
    }

    public static String concatenate(String a, String b)
    {
        return a + b;

    }

    public static String getLineShipTo(BigDecimal icHeader, BigDecimal icLine, DBSession dbs)
    {
        String shipTo = "";
        String queryString = "Select s.id.shipToCode from ShipTo as s where s.id.icHeader = ? AND s.id.icLine = ?" ;

        List result ;
        try
        {
            result = dbs.query(queryString,	new Object[] {icHeader, icLine}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL});

            if(result == null)
            {
                shipTo = "";
            }
            else
            {
                if(result.size() > 1)
                {
                    shipTo = "MULTIPLE";
                }
                else if(result.size() == 0)
                {
                    shipTo = "";
                }
                else
                {
                    shipTo = (String)result.get(0);
                }
            }
        }
        catch (Exception e)
        {
            shipTo = "Exception!";
            e.printStackTrace();
        }

        return shipTo;
    }

    public static String getVendorPhone(String vendorId, String organizationId)
    {
    	String phone = "";
    	try
		{
    		Map incomingRequest = new HashMap();
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("vendor-retrieve-by-id.xml");
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("Vendor_vendorId", vendorId);
			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				//Vendor vendor = (Vendor)incomingRequest.get("vendor");

			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		catch (Exception exception)
		{
			phone = "";
		}
		return phone;
    }

    public static String getDepartmentData(String deptCode, String toGet, String organizationId)
    {
    	String ret = "";
    	try
		{
    		PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("department-retrieve-by-id-only.xml");

    		Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("Department_departmentCode", deptCode);
			process.executeProcess(incomingRequest);

			if (process.getStatus() == Status.SUCCEEDED)
			{
				Department department = (Department)incomingRequest.get("department");
				if(toGet.equalsIgnoreCase("name"))
				{
					ret = department.getDepartmentName();
				}

			}
			else
			{
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		catch (Exception exception)
		{
			ret = "";
		}
		return ret;
    }
    
    public static String getInspectionMfrChain(List inspectionMsrList)
    {
    	String resultChain = "";
    	if(inspectionMsrList != null && inspectionMsrList.size() > 0){
    		for(int imr = 0 ; imr < inspectionMsrList.size() ; imr ++){
    			InspectionMfr inspectionMfr = (InspectionMfr)inspectionMsrList.get(imr);
    			String mfrHeatLot = HiltonUtility.ckNull(inspectionMfr.getMfrHeatLot());
    			if(mfrHeatLot.length()>30){
    				mfrHeatLot = mfrHeatLot.substring(0, 30);
    			}
    			if(imr == 0 ){
    				resultChain = mfrHeatLot;
    			}else{
    				resultChain = resultChain + ", "+ mfrHeatLot;
    			}
    		}
    	}
    	return resultChain;
    }
}