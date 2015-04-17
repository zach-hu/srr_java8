package com.tsa.puridiom.invoice.tasks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.Country;
import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceCheck;
import com.tsa.puridiom.entity.InvoiceCheckLine;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.InvoiceVendor;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.invoiceheader.tasks.InvoiceHeaderUpdate;
import com.tsa.puridiom.invoiceline.tasks.InvoiceLineUpdate;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

public class PrintCheckInvoices extends Task
{
	Map incomingRequest = null;
	PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	PuridiomProcess process= null;
	String organizationId = "";

	Map byVendorMap = new LinkedHashMap();
	List checkList = new ArrayList();

	public Object executeTask(Object object) throws Exception {
        Object ret = null;

        try
        {
            incomingRequest = (Map) object;
            Object icIvcHeaderObj = incomingRequest.get("InvoiceHeader_icIvcHeader");
            organizationId = (String)incomingRequest.get("organizationId");

            String discountsAnyway = (String)incomingRequest.get("discountsAnyway");
            String discountsAnywayFlag = "";
            for (int x=discountsAnyway.length()-1;x>=0;x--)
            {
            	discountsAnywayFlag = discountsAnywayFlag + discountsAnyway.charAt(x);
            }

            byVendorMap = createMapInvoicesByVendor(icIvcHeaderObj);

            checkList = createChecksFromMap(byVendorMap, discountsAnywayFlag);

            incomingRequest.put("checkList", checkList);

            showCheckList(checkList);

            printChecks(checkList);

            setInvoicesPaid(byVendorMap);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Invoice print process failed" + e.getMessage(), e);
        }

        ret = checkList;

        return ret;
    }


	private Map createMapInvoicesByVendor(Object icIvcHeaderObj) throws Exception
	{
		Map result = new LinkedHashMap();

		if (icIvcHeaderObj instanceof String[])
        {
            String	icIvcHeaderArray[] = (String[]) icIvcHeaderObj;
            for (int i = 0; i < icIvcHeaderArray.length; i++)
            {
            	addInvoiceToMap(result, icIvcHeaderArray[i]);
            }
        }
        else
        {
        	String icIvcHeader = (String) icIvcHeaderObj;
        	addInvoiceToMap(result, icIvcHeader);
        }
		return result;
	}

	private void addInvoiceToMap(Map byVendorMap, String icIvcHeader) throws Exception
	{
		if (!HiltonUtility.isEmpty(icIvcHeader))
		{
			process = processLoader.loadProcess("invoice-retrieve.xml");
	        incomingRequest.put("InvoiceHeader_icIvcHeader", icIvcHeader);
	        process.executeProcess(incomingRequest);

	        InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader");
	        if (ivh != null)
	        {
	        	String vendorId = HiltonUtility.ckNull(ivh.getVendorId());
	        	if ( !HiltonUtility.isEmpty(vendorId))
	        	{
		        	if (!byVendorMap.containsKey(vendorId))
		        	{
		        		byVendorMap.put(vendorId, new ArrayList());
		        	}

		        	List ivhList = (List)byVendorMap.get(vendorId);
		        	ivhList.add(ivh);
		        	byVendorMap.put(vendorId, ivhList);
	        	}
	        }
		}
	}

	private void setInvoicesPaid(Map byVendorMap) throws Exception
	{
		Iterator it = byVendorMap.entrySet().iterator();

		InvoiceHeaderUpdate task = new InvoiceHeaderUpdate();
		InvoiceLineUpdate task2 = new InvoiceLineUpdate();

        while (it.hasNext())
        {
        	Map.Entry e = (Map.Entry)it.next();
        	List ivhList = (List) e.getValue();

        	for (Iterator it2 = ivhList.iterator(); it2.hasNext(); )
		    {
        		InvoiceHeader ivh = (InvoiceHeader) it2.next();
        		ivh.setStatus("6030");
        		incomingRequest.put("invoiceHeader", ivh);
        		task.executeTask(incomingRequest);

        		for (Iterator it3 = ivh.getInvoiceLineList().iterator(); it3.hasNext(); )
    		    {
            		InvoiceLine ivl = (InvoiceLine) it3.next();
            		ivl.setStatus("6030");
            		incomingRequest.put("invoiceLine", ivl);
            		task2.executeTask(incomingRequest);
    		    }
		    }
        }
	}

	private List createChecksFromMap(Map byVendorMap, String discountsAnywayFlag) throws Exception
	{
		List result = new ArrayList();

		Iterator it = byVendorMap.entrySet().iterator();
        while (it.hasNext())
        {
        	List invoiceCheckLineList = new ArrayList();

        	Map.Entry e = (Map.Entry)it.next();
        	//System.out.println(e.getKey() + " " + e.getValue());

        	InvoiceCheck invoiceCheck = new InvoiceCheck(); // sets checkNumber and checkDate
        	invoiceCheck.setCity("");
        	invoiceCheck.setVendorName( (String) e.getKey());
        	invoiceCheck.setInvoiceCheckNumber(this.getNextNumber());

        	List ivhList = (List) e.getValue();
        	int discountIndex = 0;

        	for (Iterator it2 = ivhList.iterator(); it2.hasNext(); )
		    {
        		InvoiceHeader ivh = (InvoiceHeader) it2.next();
        		InvoiceCheckLine invoiceCheckLine = new InvoiceCheckLine(ivh);

        		if( !HiltonUtility.isEmpty(ivh.getShipToCode()) )
				{
	        		HashMap processParametersShipTo = new HashMap();
	        		process = processLoader.loadProcess("address-retrieve-shipto.xml");
	        		processParametersShipTo.put("organizationId", organizationId);
	        		processParametersShipTo.put("Address_addressCode", ivh.getShipToCode().toString());
	                process.executeProcess(processParametersShipTo);
	                Address shipTo = (Address) processParametersShipTo.get("address");
	                if (shipTo != null)
	                {
	                	invoiceCheckLine.setLocation(shipTo.getCity() + " " + shipTo.getState());
	                }
	                else
	                {
	                	invoiceCheckLine.setLocation(ivh.getShipToCode());
	                }
				}

        		invoiceCheckLine.calculateDiscount( discountsAnywayFlag.charAt(discountIndex++) );

        		invoiceCheckLineList.add(invoiceCheckLine);

        		if( !HiltonUtility.isEmpty(ivh.getVendorId()) )
				{
        			HashMap processParameters = new HashMap();

        			process = processLoader.loadProcess("invoiceheader-vendor-retrieve-by-id.xml");

        	        processParameters.put("organizationId", organizationId);
                    processParameters.put("InvoiceHeader_icIvcHeader", ivh.getIcIvcHeader().toString());
                    process.executeProcess(processParameters);

                    InvoiceVendor vendor = (InvoiceVendor) processParameters.get("invoiceVendor");
                    InvoiceAddress address = (InvoiceAddress) processParameters.get("invoiceAddress");

                    if (vendor != null)
                    {
                    	invoiceCheck.setVendorName(vendor.getVendorName());
                    }
                    if (address != null)
                    {
	                    invoiceCheck.setCity(address.getCity());
	                    invoiceCheck.setAddressLine2(address.getAddressLine2());
	                    invoiceCheck.setState(address.getState());
	                    invoiceCheck.setPostalCode(address.getPostalCode());
	                    invoiceCheck.setCountry( address.getCountry());

	                    process = processLoader.loadProcess("country-retrieve-by-id.xml");
	                    processParameters.put("Country_countryCode", address.getCountry());
	                    process.executeProcess(processParameters);
	                    Country country = (Country) processParameters.get("country");
	                    if (country != null)
	                    {
	                    	invoiceCheck.setCountry( country.getCountryName() );
	                    }
                    }
				}

		    }
        	invoiceCheck.setInvoiceCheckLineList(invoiceCheckLineList);
        	invoiceCheck.calculateTotal();

        	result.add(invoiceCheck);
        }

        return result;

	}

	public String getNextNumber() throws Exception
	{
		incomingRequest.put("AutoGen_documentType", "INVC");
		incomingRequest.put("AutoGen_genYear", "2008");
		process = processLoader.loadProcess("autogen-next-doc-number.xml");
		process.executeProcess(incomingRequest);
		if (process.getStatus() < Status.SUCCEEDED)
        {
             throw new Exception("Auto Number process failed.");
        }
		return  (String) incomingRequest.get("nextDocNumber");
	}

	public void printChecks(List checkList) throws Exception
	{
		//process = processLoader.loadProcess("check-batch-pdf.xml");
        //incomingRequest.put("checkList", checkList);
        //process.executeProcess(incomingRequest);
		writeCheckFile(checkList);
		writeRemittanceFile(checkList);
	}

	public void writeCheckFile(List checkList) throws Exception
	{
		try{

			SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
            Date date = new Date();
            String dateString = format.format(date);
			String fileName = "vchextract/GPI_AP_Format_Payments__Evergr_" + dateString + ".txt";
			String filePath =  DictionaryManager.getInstance("host", organizationId).getProperty("reportsOut", "") + fileName;
		    PrintWriter writer = new PrintWriter( new BufferedWriter(new FileWriter(filePath)));

	    	for (Iterator it = checkList.iterator(); it.hasNext(); )
		    {
	    		InvoiceCheck ich = (InvoiceCheck) it.next();
	    		writer.println();
	    		writer.println();
	    		writer.println();
	    		writer.println();
	    		String wdp = "      WDP Chase Bank of                    ";
	    		String checkDate = HiltonUtility.completeCharacters(HiltonUtility.getFormattedDate(ich.getCheckDate(), organizationId,"dd-MMM-yy").toUpperCase(), " ", 10,  1);
	    		String checkNumber = HiltonUtility.completeCharacters(ich.getInvoiceCheckNumber(), "0", 6,  0);
	    		String netAmountTotal = HiltonUtility.completeCharacters(HiltonUtility.getFormattedCurrency(ich.getNetAmountTotal(), "USD", organizationId, false), "*", 14,  0);
	    		writer.println( wdp + checkDate + "     "  + checkNumber + "  " + netAmountTotal );
	    		writer.println();
	    		writer.println("      " + HiltonUtility.completeCharacters(HiltonUtility.convertToDollarText(ich.getNetAmountTotal()), "*", 66,  1));
	    		writer.println();
	    		writer.println();
	    		writer.println("         " + ich.getVendorName() );
	    		writer.println("         " + ich.getAddressLine2());
	    		writer.println("         " + ich.getCity() + ", "+ ich.getState() + "  " + ich.getPostalCode());
	    		writer.println("         " + ich.getCountry());
	    		writer.println();
	    		writer.println();
	    		writer.println();
	    		writer.println();
	    		writer.println();
	    		writer.println();
	    		writer.println();
	    		writer.println();
		    }
		    writer.close();
		}
		catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}


	public String blanks(int size)
	{
		String result="";
		for(int i=0; i< size; i++)
		{
			result = result + " ";
		}
		return result;
	}

	public void writeRemittanceFile(List checkList) throws Exception
	{
		try{

			SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
            Date date = new Date();
            String dateString = format.format(date);
            String fileName = "vchextract/GPI_AP_Remittance_Advice_" + dateString + ".txt";
			String filePath =  DictionaryManager.getInstance("host", organizationId).getProperty("reportsOut", "") + fileName;

		    PrintWriter writer = new PrintWriter( new BufferedWriter(new FileWriter(filePath)));

		    int counterRemittance = 0;
		    int MAX_LINES = 40;

	    	for (Iterator it = checkList.iterator(); it.hasNext(); )
		    {
	    		InvoiceCheck ich = (InvoiceCheck) it.next();

	    		int invoiceLineCounter = 0;
	    		int TOTAL_LINES = (ich.getInvoiceCheckLineList()).size();

	    		int totalPages = TOTAL_LINES / MAX_LINES + 1 ;
	    		if (TOTAL_LINES%MAX_LINES==0)
	    		{
	    			totalPages--;
	    		}
	    		int pageNumber = 1;


    			String invoiceAmountTotal = HiltonUtility.completeCharacters((HiltonUtility.getFormattedDollar(ich.getInvoiceAmountTotal(), organizationId)).toString(), " ", 15, 0);
    			String discountAmountTotal = HiltonUtility.completeCharacters((HiltonUtility.getFormattedDollar(ich.getDiscountAmountTotal(), organizationId)).toString(), " ", 15, 0);
    			String netAmountTotal = HiltonUtility.completeCharacters((HiltonUtility.getFormattedDollar(ich.getNetAmountTotal(), organizationId)).toString(), " ", 15, 0);

	    		for (Iterator it2 = ich.getInvoiceCheckLineList().iterator(); it2.hasNext();)
	    	    {
	    			invoiceLineCounter++;
	    			pageNumber = invoiceLineCounter/MAX_LINES + 1;

		    		// header
	    			if ( invoiceLineCounter%MAX_LINES==1 )
	    			{
			    		String carquest = blanks(55) + "CARQUEST Auto Parts";
			    		if (counterRemittance != 0)
			    		{
			    			carquest = "\f" + carquest;
			    		}
			    		writer.println(carquest);
			    		writer.println(blanks(56) + "REMITTANCE ADVICE");
			    		writer.println(blanks(57) + "Date: " + (HiltonUtility.getFormattedDate(ich.getCheckDate(), organizationId,"dd-MMM-yy")).toUpperCase() +
			    				blanks(34) + "Report Date:  " + HiltonUtility.getFormattedDate(ich.getCheckDate(), organizationId,"MM/dd/yy"));
			    		writer.println();
			    		writer.println(blanks(116) + "Page " + pageNumber + " of " + totalPages);
			    		writer.println("Supplier  " + ich.getVendorName());
			    		writer.println("Site      " + ich.getCity());
			    		writer.println();
			    		writer.println("    Check No:" + blanks(11) + HiltonUtility.completeCharacters(ich.getInvoiceCheckNumber(), "0", 6,  0));
			    		writer.println("    Amount:  " + HiltonUtility.completeCharacters("$"+HiltonUtility.getFormattedDollar(ich.getNetAmountTotal(),organizationId).toString(), " ", 17,  0));
			    		writer.println();
			    		writer.println("     Location              Inv/RGN         Invoice   Acct/Credit                          Invoice       Discount            Net");
			    		writer.println("     Name                  Number          Date      Comments                              Amount         Amount         Amount");
			    		writer.println("     ------------------------------------- --------- ----------------------------  -------------- -------------- --------------");
	    			}
		    		//end header

	    			InvoiceCheckLine ichLine = (InvoiceCheckLine) it2.next();
	    			String location = HiltonUtility.completeCharacters(ichLine.getLocation(), " ", 22, 1);
	    			String invoiceNumber = HiltonUtility.completeCharacters(ichLine.getInvoiceNumber(), " ", 16, 1);
	    			String invoiceDate = HiltonUtility.completeCharacters(HiltonUtility.getFormattedDate(ichLine.getInvoiceDate(), organizationId,"MM/dd/yy"), " ", 10, 1);
	    			String description = HiltonUtility.completeCharacters(ichLine.getDescription(), " ", 100, 1);
	    			String invoiceAmount = HiltonUtility.completeCharacters((HiltonUtility.getFormattedDollar(ichLine.getInvoiceAmount(), organizationId)).toString(), " ", 15, 0);
	    			String discountAmount = HiltonUtility.completeCharacters((HiltonUtility.getFormattedDollar(ichLine.getDiscountAmount(), organizationId)).toString(), " ", 15, 0);
	    			String netAmount = HiltonUtility.completeCharacters((HiltonUtility.getFormattedDollar(ichLine.getNetAmount(), organizationId)).toString(), " ", 15, 0);

	    			writer.println(blanks(5) + location + invoiceNumber + invoiceDate + description +
	    					invoiceAmount + discountAmount + netAmount);


	    			if ( invoiceLineCounter==TOTAL_LINES )
	    			{
	    				writer.println(blanks(83) + "-------------- -------------- --------------");
	    				writer.println(blanks(55) + "Sub Total" + blanks(18) + invoiceAmountTotal + discountAmountTotal + netAmountTotal);
	    				writer.println(blanks(83) + "-------------- -------------- --------------");
	    				writer.println(blanks(53) + "Check Total"+ blanks(18) + invoiceAmountTotal + discountAmountTotal + netAmountTotal);
	    			}

	    			counterRemittance++;
	    	    }
		    }
		    writer.close();
		}
		catch(Exception e)
	    {
	    	e.printStackTrace();
	    }

	}

	public void showCheckList(List checkList)
	{
		for (Iterator it = checkList.iterator(); it.hasNext();)
	    {
    		InvoiceCheck invoiceCheck = (InvoiceCheck) it.next();
    		System.out.println();
    		System.out.println("invoiceCheck " + invoiceCheck);
    		System.out.println("VendorName " + invoiceCheck.getVendorName());
    		System.out.println("City " + invoiceCheck.getCity());
    		System.out.println("CheckNumber " + invoiceCheck.getInvoiceCheckNumber());
    		System.out.println("CheckDate " + invoiceCheck.getCheckDate());
    		System.out.println("DiscountAmountTotal " + invoiceCheck.getDiscountAmountTotal());
    		System.out.println("InvoiceAmountTotal " + invoiceCheck.getInvoiceAmountTotal());
    		System.out.println("NetAmountTotal " + invoiceCheck.getNetAmountTotal());

    		System.out.println("City " + invoiceCheck.getCity());
    		System.out.println( "AddressLine2" + ( invoiceCheck.getAddressLine2()));

    		for (Iterator it2 = invoiceCheck.getInvoiceCheckLineList().iterator(); it2.hasNext();)
    	    {
    			InvoiceCheckLine invoiceCheckLine = (InvoiceCheckLine) it2.next();
        		System.out.println("  invoiceCheckLine " + invoiceCheckLine);
        		System.out.println("  Description " + invoiceCheckLine.getDescription());
        		System.out.println("  InvoiceNumber " + invoiceCheckLine.getInvoiceNumber());
        		System.out.println("  Location " + invoiceCheckLine.getLocation());
        		System.out.println("  TermsCode " + invoiceCheckLine.getTermsCode());
        		System.out.println("  DiscountAmount " + invoiceCheckLine.getDiscountAmount());
        		System.out.println("  InvoiceAmount " + invoiceCheckLine.getInvoiceAmount());
        		System.out.println("  NetAmont " + invoiceCheckLine.getNetAmount());
        		System.out.println("  PaymentDueDate " + invoiceCheckLine.getPaymentDueDate());
        		System.out.println("  TermDiscDays " + invoiceCheckLine.getTermsDiscDays());
        		System.out.println("  TermDiscperc " + invoiceCheckLine.getTermsDiscperc());
    	    }
	    }
	}

}
