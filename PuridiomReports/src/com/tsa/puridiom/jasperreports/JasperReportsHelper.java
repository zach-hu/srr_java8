/*
 * Created on Nov 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.jasperreports;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;

import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.reports.datasource.ReportDataSource;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;



/**
 * @author renzo
 *
 */
public class JasperReportsHelper
{

	public static void mergeReports(List reportsToMerge, String finalFile)
	{
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, reportsToMerge);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, finalFile);
		exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);

		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}

		//System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}
	public static void mergeReportsXls(List reportsToMerge, String finalFile, String organizationId)
	{
		String fileName = DictionaryManager.getInstance("host", organizationId).getProperty("reportsPath", "") + organizationId + File.separator + "worksheet-xls.jasper";
		File destFile = new File(fileName);

		JRXlsExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, reportsToMerge);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, finalFile);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, new Boolean(false));
        exporter.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, new Boolean(false));

		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}

		//System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}
	public static String getReportsPath(String organizationId)
    {
		return JasperReportsHelper.getReportsPath(organizationId, "N");
    }
    public static String getReportsPath(String organizationId, String reportType)
    {
        String path = DictionaryManager.getInstance("host", organizationId).getProperty("reportsPath", "");
        Log.debug("JasperReportsHelper", "loadSubReport starts with path: " + path + " oid: " + organizationId);
        Log.debug("JasperReportsHelper", "getReportsPath: " + reportType);
        if(reportType.equalsIgnoreCase("Y"))
        {
        	path = path +File.separator + "reports" + File.separator;
        }
        Log.debug("JasperReportsHelper", "path: " + path);
        return path;
    }

    public static void compile(String fileName, String organizationId)
    {
        Log.debug("JasperReportsHelper","compiling: " + fileName);
        long start = System.currentTimeMillis();
        String path = JasperReportsHelper.getReportsPath(organizationId);
        try
        {
            JasperCompileManager.compileReportToFile(path + fileName);
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
        System.err.println("Compile time : " + (System.currentTimeMillis() - start));
    }

    /**
     * @param fileName
     * @param parameters
     * @param organizationId
     * @param ds datasource
     * @return
     */
    public static JasperPrint fill(String fileName, Map parameters, String organizationId, JRDataSource ds) throws JRException
    {
        //Log.debug("JasperReportsHelper","filling: " + fileName);
        Log.debug("JasperReportsHelper", "JasperPrint starts with file: " + fileName);
//		JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", true);
        long start = System.currentTimeMillis();

        JasperPrint jasperPrintMaster = null;
        /*
         *debugging printing parameters*****
         Set parameterKeys = parameters.keySet();
        for (Iterator iter = parameterKeys.iterator(); iter.hasNext();)
        {
            String element = (String) iter.next();
            Log.debug("JasperReportsHelper","Parameter: " + element + "\tvalue: "+ parameters.get(element));
        }
        */
        try
        {
            //JasperFillManager.fillReportToFile(path + fileName, parameters, ds);
        	String pdfType = (String)parameters.get("pdfType");
            JasperReport jasperReport = JasperReportsHelper.loadSubReport(fileName, organizationId, pdfType);

            Log.debug("JasperReportsHelper", "JasperPrint load with parameters ");
            jasperPrintMaster = JasperFillManager.fillReport(jasperReport, parameters, ds);
        }
        catch (JRException e)
        {
            Log.error("JasperReportsHelper", "JasperPrint exception: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
        //System.err.println("Filling time : " + (System.currentTimeMillis() - start));
        Log.debug("JasperReportsHelper", "JasperPrint fillingtime: " + (System.currentTimeMillis() - start));
        return jasperPrintMaster;
    }

    public static String getSigUrl(String userId, String organizationId)
    {
        URL sigURL = null;

            String imgName = "";
            if(Utility.isEmpty(userId))
            {
                imgName = "blank.gif";
            }
            else
            {
                try
				{
					imgName = UserManager.getInstance().getUser(organizationId, userId).getSignature();
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					imgName = "blank.gif";
				}
                if(imgName.indexOf(".sig") > 0){		imgName = imgName.replaceAll(".sig", ".gif");		}
                if(Utility.isEmpty(imgName)){		imgName = "blank.gif";		}
            }

            return JasperReportsHelper.getImgUrl(organizationId, imgName, "sig");
    }
    public static String getAttachmentUrl(String organizationId, String attachName)
    {
    	String url = DictionaryManager.getInstance("host", organizationId).getProperty("reportsUrl")+ "/requests/mail_approve.jsp?filename=" + attachName + "&oid=" + organizationId;
    	return url;
    }
    public static String getPropertiesUrl(String organizationId, String url)
    {

    	if(url.equalsIgnoreCase("img"))
    	{
    		return DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
    	}
    	else if(url.equalsIgnoreCase("sig"))
    	{
    		return DictionaryManager.getInstance("host", organizationId).getProperty("sigUrl");
    	}
    	else
    	{
    		return "";
    	}

    }
    public static String getImgUrl(String organizationId, String imgName)
    {
    	return JasperReportsHelper.getImgUrl(organizationId, imgName, "img");
    }
    public static String getImgUrl(String organizationId, String imgName, String type)
    {
    	Log.debug("JasperReportsHelper","img: " + imgName + ", oid: " + organizationId + "type: " + type);
        URL imgURL = null;
        boolean baseUrl = false;

        String imgPath = JasperReportsHelper.getPropertiesUrl(organizationId, type);
    	if (! PropertiesManager.getInstance(organizationId).getProperty("MISC", "UseOrgIdPath", "N").equalsIgnoreCase("Y")) {
    		return imgPath +  imgName ;
    	}

        if(!Utility.isEmpty(imgPath))
        {
            try
            {
                imgURL = new URL(imgPath + organizationId.toLowerCase() + "/" + imgName);
                Log.debug("JasperReportsHelper","looking for: " + imgURL.toString());
                if(!JasperReportsHelper.existsURL(imgURL.toString()))
                {
                	imgURL = new URL(imgPath + imgName);
                	Log.debug("JasperReportsHelper","looking for: " + imgURL.toString());
                    if(!JasperReportsHelper.existsURL(imgURL.toString()))
                    {
                    	baseUrl = true;
                    }
                }
            }
            catch (Exception e)
            {
                try
                {
                    imgURL = new URL(imgPath + imgName);
                }
                catch (MalformedURLException e1)
                {
                    baseUrl = true;
                }
            }
    	}
        else
        {
            baseUrl = true;
        }

        if(baseUrl)
        {
            try
            {
                imgURL = new URL(imgPath + imgName);
                Log.debug("JasperReportsHelper","lookign for: " + imgURL.toString());
                if(!JasperReportsHelper.existsURL(imgURL.toString()))
                {
                	imgURL = new URL(imgPath + "blank.gif");
                	Log.debug("JasperReportsHelper","lookign for: " + imgURL.toString());
                }
            }
            catch (Exception e)
            {
                Log.error("JasperReportsHelper", "getImgUrl error loading Image file: " + imgName + " oid: " + organizationId );
                e.printStackTrace();
            }
        }
        Log.debug("JasperReportsHelper","returns: " + imgURL.toString());
        return imgURL.toString();
    }

	/* Added to allow for multiple logos, not currently used  */
    public static String getLogo(String organizationId, String udfParam) {
    	return getLogo(organizationId, "PO10", udfParam) ;
    }

    public static String getLogo(String organizationId, String type, String udfParam)
    {
    	String logoname = PropertiesManager.getInstance(organizationId).getProperty("MISC", "LOGOFILENAME", "logo.gif");
    	if(PropertiesManager.getInstance(organizationId).getProperty("MISC", "MULTIPLELOGO", "N").equalsIgnoreCase("Y"))
    	{
    		//it means that SHIPTOCODE was setted as the name of the logo
    		if(type.equalsIgnoreCase("SHIPTOCODE")){
    			logoname= udfParam+".jpg";
    		}
    		else //else it takes description of udf
    		{
    			String udfLogo = PropertiesManager.getInstance(organizationId).getProperty("MISC","UDFLOGO","");
    			if(!Utility.isEmpty(udfLogo) && type.equalsIgnoreCase("PO10"))
    			{
    				logoname = ReportUtils.getUdfMultipleLogoPoHeader(organizationId, type, udfLogo, udfParam);
    			}
    			else if(!Utility.isEmpty(udfParam))
	    		{
	    			logoname = ReportUtils.getStdTableDescription(organizationId, type, udfParam);
	    		}
    		}
    	}
    	if(HiltonUtility.isEmpty(logoname))
    	{
    		logoname = "logo.gif";
    	}
    	else
    	{
    		if(logoname.lastIndexOf(".") < 0)
    		{
    			logoname = logoname + ".gif";
    		}
    	}

    	if (! PropertiesManager.getInstance(organizationId).getProperty("MISC", "UseOrgIdPath", "N").equalsIgnoreCase("Y")) {
    		return JasperReportsHelper.getImgUrl(organizationId, logoname.toLowerCase());
    	} else {
	    	if(existsImg(organizationId, logoname))
	    	{
	    		return JasperReportsHelper.getImgUrl(organizationId, logoname.toLowerCase());
	        }
	        else
	        {
	        	return JasperReportsHelper.getImgUrl(organizationId, "logo.gif");
	        }
    	}
    }

    public static String getAddress(String organizationId, String type, String udfParam)
    {
    	String addressCompany = "";
    	if(PropertiesManager.getInstance(organizationId).getProperty("MISC", "MULTIPLELOGO", "N").equalsIgnoreCase("Y"))
    	{
    		//it means that SHIPTOCODE was setted as the name of the logo
    		String udfLogo = PropertiesManager.getInstance(organizationId).getProperty("MISC","UDFLOGO","");
    		if(!Utility.isEmpty(udfLogo))
    		{
    			addressCompany = ReportUtils.getUdfMultipleLogoPoHeader(organizationId, type, udfLogo, udfParam);
    		}
    	}
    	if(HiltonUtility.isEmpty(addressCompany))
    	{
    		addressCompany = "";
    	}

    	return addressCompany;
    }

    public static boolean existsURL(String URLName)
    {
    	  try
    	  {
    	    HttpURLConnection.setFollowRedirects(false);
    	    // note : you may also need
    	    //        HttpURLConnection.setInstanceFollowRedirects(false)
    	    HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
    	    con.setRequestMethod("HEAD");

    	    return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
    	  }
    	  catch (Exception e)
    	  {
    	       e.printStackTrace();
    	       return false;
    	  }
    }

    public static boolean existsImg(String organizationId, String imgName)
    {
    	URL imgURL = null;

        String imgPath = DictionaryManager.getInstance("host", organizationId).getProperty("reportsImgUrl");
        if(!Utility.isEmpty(imgPath))
        {
            try
            {
                imgURL = new URL(imgPath + organizationId.toLowerCase() +"/" + imgName.toLowerCase());
               	return (JasperReportsHelper.existsURL(imgURL.toString()));
    	  	}
    	  	catch (Exception e)
    	  	{
    	       	e.printStackTrace();
    	       	return false;
    	  	}
    	}
    	else
    	{
    		return false;
		}
    }

    public static JasperPrint fillConnection(String fileName, Map parameters, String organizationId, Connection con)
    {
        Log.debug("JasperReportsHelper","filling: " + fileName);
        long start = System.currentTimeMillis();
        //String path = JasperReportsHelper.getReportsPath(organizationId);
        JasperPrint jasperPrintMaster = null;
        try
        {
            JasperReport jasper = JasperReportsHelper.loadSubReport(fileName, organizationId);
            jasperPrintMaster = JasperFillManager.fillReport(jasper, parameters, con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.err.println("Filling time : " + (System.currentTimeMillis() - start));
        return jasperPrintMaster;
    }
    public static JasperReport loadSubReport(String fileName, String organizationId)
    {
    	return JasperReportsHelper.loadSubReport(fileName, organizationId, "");
    }
    public static JasperReport loadSubReport(String fileName, String organizationId, String formType)
    {
    	return JasperReportsHelper.loadSubReport(fileName, organizationId, formType, "N");
    }
    public static JasperReport loadSubReport(String fileName, String organizationId, String formType, String reportType)
    {
    	Log.debug("JasperReportsHelper","file: " + fileName + ",oid " + organizationId);
    	if(Utility.isEmpty(organizationId)){	organizationId = "puridiom";	}
        String path = JasperReportsHelper.getReportsPath(organizationId, reportType);//y report :  N  forma
        boolean isTypeFile = false;
        String origFileName = fileName;
        String optionalFileName = "";

        if(!HiltonUtility.isEmpty(formType))
        {
        	fileName = formType.toLowerCase() + "_" + fileName;
        	isTypeFile = true;
        }

        JasperReport subReport = null;
        try
        {
           File reportFile = Utility.getOidFile(path + fileName, organizationId);
            if(!reportFile.exists() && isTypeFile)
            {
            	subReport = JasperReportsHelper.loadSubReport(origFileName, organizationId, "");
            }
            else
            {
            	if (reportFile.exists())
            	{
            		subReport = (JasperReport)JRLoader.loadObject(reportFile.getAbsolutePath());
            	}
            	else
            	{
            		if(fileName.lastIndexOf("-csv")!= -1)
                    {
                    	optionalFileName = fileName.substring(0, fileName.lastIndexOf("-csv"))+".jasper";
                    }
            		else if(fileName.lastIndexOf("-html")!= -1)
                    {
                    	optionalFileName = fileName.substring(0, fileName.lastIndexOf("-html"))+".jasper";
                    }
            		else if(fileName.lastIndexOf("-xls")!= -1)
                    {
                    	optionalFileName = fileName.substring(0, fileName.lastIndexOf("-xls"))+".jasper";
                    }


            		subReport = JasperReportsHelper.loadSubReport(optionalFileName, organizationId, formType, reportType);
            	}



            	subReport = (JasperReport)JRLoader.loadObject(reportFile.getAbsolutePath());
            }
        }
        catch (Exception e)
        {
            Log.error("JasperReportsHelper", "loadSubReport error loading file: " + fileName + " oid: " + organizationId + ", path: " + path);
            Log.error("JasperReportsHelper", e.getMessage());
            e.printStackTrace();
            if(isTypeFile)
            {
            	subReport = JasperReportsHelper.loadSubReport(fileName, organizationId, "");
            }
        }

        Log.debug("JasperReportsHelper", "loadSubReport done with file: " +subReport.getName());
        return subReport;
    }

    public static void exportFileToPdf(String fileName, String organizationId)
    {
        Log.debug("JasperReportsHelper","exporting: " + fileName);
        long start = System.currentTimeMillis();
        String path = JasperReportsHelper.getReportsPath(organizationId);

        try
        {
            JasperExportManager.exportReportToPdfFile(path + fileName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.err.println("export time : " + (System.currentTimeMillis() - start));
    }

    public static void exportToPdf(String fileName, String organizationId, Map parameters, JRDataSource ds, String pdfName)
    {
        Log.debug("JasperReportsHelper","exporting: " + fileName + " to " + pdfName);
        long start = System.currentTimeMillis();

        try
        {
            JasperPrint jasperPrintMaster = JasperReportsHelper.fill(fileName, parameters, organizationId, ds);
            //JasperPrintManager.printReportToPdfFile(jasperPrintMaster, pdfName);
            JasperExportManager.exportReportToPdfFile(jasperPrintMaster, pdfName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.err.println("export time : " + (System.currentTimeMillis() - start));
    }

    public static void exportToPdf(String fileName, String organizationId, Map parameters, Connection con, String pdfName)
    {
        Log.debug("JasperReportsHelper","exporting: " + fileName + " to " + pdfName);
        long start = System.currentTimeMillis();

        try
        {
            //JasperPrint jasperPrintMaster = JasperReportsHelper.fill(fileName, parameters, organizationId, con);
            JasperFillManager.fillReportToFile(fileName, parameters, con);
            JasperExportManager.exportReportToPdfFile(pdfName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.err.println("export time : " + (System.currentTimeMillis() - start));
    }
    public static JasperPrint fillReport(Map parameters)
    {
        JasperPrint jasperPrint = null;
        try
        {
            String organizationId = (String)parameters.get("organizationId");
            String format = (String)parameters.get("format");
            Log.debug("JasperReportsHelper", "Loading file: " + (String)parameters.get("filePath"));
            String reportName = (String)parameters.get("reportName");

            if(format.equalsIgnoreCase("html"))
            {
                //jasperReport = JasperManager.loadReport(reportName + "-html.jasper");
            	reportName = reportName + "-html.jasper";
            }
            else if(format.equalsIgnoreCase("csv"))
            {
            	reportName = reportName + "-csv.jasper";
            }
            else if(format.equalsIgnoreCase("xls"))
            {
            	reportName = reportName + "-xls.jasper";
            }
            else
            {
                //jasperReport = JasperManager.loadReport(reportName + ".jasper");
                reportName = reportName + ".jasper";
            }
            String isReport = (String)parameters.get("isReport");
            if(HiltonUtility.isEmpty(isReport)){		isReport = "N";		}
            JasperReport jasperReport = JasperReportsHelper.loadSubReport(reportName, organizationId, "", isReport);

            if(((Boolean)parameters.get("isDatasource")).booleanValue())
            {
                List datasource = (List)parameters.get("datasource");
                //GenerateReportObject reportObject = new GenerateReportObject();
                Map incomingRequest = new HashMap();
                incomingRequest.put("organizationId", organizationId);
                incomingRequest.put("browseName", parameters.get("reportName"));
                JRDataSource ds = null;

                if(datasource.size() > 0)
                {
                	ds = new ReportDataSource((BrowseObject)parameters.get("reportObject"), datasource);
                }
                else
                {
                	ds = new JREmptyDataSource(1);
                }

                String tmpPath = DictionaryManager.getInstance("host", organizationId).getProperty("reportsPath", "");
                JRFileVirtualizer virtualizer = new JRFileVirtualizer(JasperReportsHelper.getVirtualizerSize(datasource.size()), tmpPath);

                /* To use Virtualizer uncomment the following lines */
//                JRSwapFile jrSwapFile = new JRSwapFile(tmpPath, 1024, 1024);
//                int numberPages = JasperReportsHelper.getVirtualizerSize(datasource.size());
//                JRSwapFileVirtualizer virtualizer = new JRSwapFileVirtualizer(numberPages,jrSwapFile,true);
                //JRGzipVirtualizer virtualizer = new JRGzipVirtualizer(JasperReportsHelper.getVirtualizerSize(datasource.size()));
//
//        		Map newParameters = (Map) parameters.get("reportParameters");
//        		newParameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
//
//        		jasperPrint = JasperFillManager.fillReport(jasperReport, newParameters, ds);

        		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

        		long start = System.currentTimeMillis();

        		jasperPrint = JasperFillManager.fillReport(jasperReport, (Map)parameters.get("reportParameters"), ds);

        		virtualizer.setReadOnly(true);
                long end = System.currentTimeMillis();

                Log.error("JasperReportsHelper", "It took " + ((end - start)/1000) +" seconds to execute the report" + (String)parameters.get("reportName"));
            }
            else
            {
                String queryfilters = (String)parameters.get("filters");
                Map reportParameters = (Map)parameters.get("reportParameters");
                reportParameters.put("extraQuery", queryfilters);
                jasperPrint = JasperFillManager.fillReport(jasperReport, reportParameters, (Connection)parameters.get("sqlConnection"));
            }

        }
        catch (Exception e)
        {
            Log.error("JasperHelper", "- error ocurred filling Report!");
            Log.error("JasperHelper", e.getMessage());
            e.printStackTrace();
        }
        return jasperPrint;
    }
    public static int getVirtualizerSize(int dsSize)
    {
//    	int ret = 20;
//    	if(dsSize > 600)
//    	{
//    		ret = dsSize / 600;
		int ret = 1;

		if (dsSize > 60)
		{
			ret = dsSize / 60;
			if (ret > 20)
			{
				ret = ret / 2;
			}
		}
		return ret;
    }
    public static String nameReport(Map parameters)
    {
    	String nameIt;
    	String webReport = (String)parameters.get("webreport");
        if(webReport == null) {     webReport = "Y";    }
        if(webReport.equalsIgnoreCase("Y"))
        {
            String format = (String)parameters.get("format");
            if(format == null) {        format = "pdf";     }

            UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            nameIt = ukg.getUniqueKey().toString();
            nameIt = nameIt + "." + format;
            Log.debug("JasperReportsHelper", "Naming file: " + nameIt);
//            return nameIt;
        }
        else if(webReport.equalsIgnoreCase("R"))
        {
        	String format = (String)parameters.get("format");
            if(format == null) {        format = "pdf";     }
        	String reportName = (String)parameters.get("reportName");
        	UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
            nameIt = ukg.getUniqueKey().toString();
            nameIt = reportName + "-" + nameIt + "." + format;
            Log.debug("JasperReportsHelper", "Naming file: " + nameIt);
//            return nameIt;
        }
        else
        {
        	nameIt = (String)parameters.get("namePdf");
        }

        nameIt = nameIt.replaceAll("\\\\", "").replaceAll("/", "");

        JasperReportsHelper jr = new JasperReportsHelper();
        Log.debug(jr, "FILENAME REPORT " + nameIt);

        return nameIt;
    }

    public static Object selectRunner(String format, JasperPrint jasperPrint, String fileName, Map parameters) throws JRException
    {
        String filePath =  DictionaryManager.getInstance("host", (String)parameters.get("organizationId")).getProperty("reportsOut", "") + fileName;
        //String filePath = JasperReportsHelper.getReportsPath((String)parameters.get("organizationId")) + fileName;
        Log.debug("JasperReportsHelper", "looking for: " + filePath);
        if(format.equalsIgnoreCase("xls"))
        {
            JasperReportsHelper.runXls(jasperPrint, filePath);
        }
        else if(format.equalsIgnoreCase("html"))
        {
            JasperReportsHelper.runHtml(jasperPrint, filePath);
        }
        else if(format.equalsIgnoreCase("emailhtml"))
        {
        	JasperReportsHelper.emailPoHtml(jasperPrint, filePath);
        }
        else if(format.equalsIgnoreCase("csv"))
        {
        	JasperReportsHelper.runCsv(jasperPrint, filePath);
        }
        else if(format.equalsIgnoreCase("txt"))
        {
        	JasperReportsHelper.runTxt(jasperPrint, filePath);
        }
        else
        {
            JasperReportsHelper.runPdf(jasperPrint, filePath);
        }
        String webReport = (String)parameters.get("webreport");
        if(webReport == null) {     webReport = "Y";    }
        if(webReport.equalsIgnoreCase("Y"))
        {
            return JasperReportsHelper.nameWebReport(parameters, fileName);
        }
        if(webReport.equalsIgnoreCase("R"))
        {
            return filePath;
        }
        else
        {
            return JasperReportsHelper.fileNameReport(parameters, fileName);
        }
    }

    /**
     * @param format pdf,xml,html
     * @param jasperPrintFiles
     * @param webName reportName - without path
     * @param parameters
     * @return
     * @throws JRException
     */
    public static Object selectRunner(String format, List jasperPrintFiles, String webName, Map parameters) throws JRException
    {
        String fileName =  DictionaryManager.getInstance("host", (String)parameters.get("organizationId")).getProperty("reportsOut", "") + webName;
        Log.debug("JasperReportsHelper", "looking for: " + fileName);
        if(format.equalsIgnoreCase("pdf"))
        {
            JasperReportsHelper.runPdfList(jasperPrintFiles, fileName);
            String webReport = (String)parameters.get("webreport");
            if(webReport == null) {     webReport = "Y";    }
            if(webReport.equalsIgnoreCase("Y"))
            {
                return JasperReportsHelper.nameWebReport(parameters, webName);
            }
            else
            {
                return JasperReportsHelper.fileNameReport(parameters, webName);
            }
        }
        return null;
    }

    public static void runPdfList(List jasperPrintList, String fileName)
    {
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);

        try
        {
            exporter.exportReport();
        }
        catch (JRException e)
        {
            e.printStackTrace();
        }
    }

    public static void runPdf(JasperPrint jasperPrint, String fileName) throws JRException
    {

        File file = new File(fileName);
        if(file.exists())
        {
            file.delete();
        }

        //JasperManager.printReportToPdfFile(jasperPrint, fileName);***************************************add time debugging****************
        JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);

    }

    public static void runXls(JasperPrint jasperPrint, String fileName) throws JRException
    {
        File destFile = new File(fileName);


        JRXlsExporter exporter = new JRXlsExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, new Boolean(true));
        exporter.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, new Boolean(false));

        exporter.exportReport();
    }

    public static void runCsv(JasperPrint jasperPrint, String fileName) throws JRException
    {
        File destFile = new File(fileName);

        JRCsvExporter exporter = new JRCsvExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
        exporter.exportReport();

    }

    public static void runTxt(JasperPrint jasperPrint, String fileName) throws JRException
    {
        File destFile = new File(fileName);

        JRTextExporter exporter = new JRTextExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
        //exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(150));
        //exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(150));
        exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(10));
        exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(10));
        exporter.setParameter(JRTextExporterParameter.FONT_MAP, "Courier New");
        exporter.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT, "");

        exporter.exportReport();
    }

    public static void runHtml(JasperPrint jasperPrint, String fileName) throws JRException
    {
        File destFile = new File(fileName);

        JRHtmlExporter exporter = new JRHtmlExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
        exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
        exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.FALSE);

        exporter.exportReport();
    }

    public static void emailPoHtml(JasperPrint jasperPrint, String fileName) throws JRException
    {
        File destFile = new File(fileName);

        JRHtmlExporter exporter = new JRHtmlExporter();
        //PuridiomHtmlExporter exporter = new PuridiomHtmlExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
        Log.debug("JasperReportsHelper.emailPoHtml", "looking for: " + fileName);
        exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
        exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);

        exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "UTF-8");
        String htmlheader = "<html>" +
"<head>" +
"<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\">" +
   " <style type=\"text/css\">" +
"<!--" +
".hdr14	{ font-size: 14px; font-family: verdana, arial, sans-serif; font-Weight: bold; color: #9999CC }" +
"a:link	{ text-decoration: none	}" +
"input.submit {	background-color: #ce0000; font-family: verdana, arial, sans-serif; font-size: 11px; font-weight: bold; color: white; width: 90px; }" +
"input.submit2 {	background-color: transparent; font-family: verdana, arial, sans-serif; font-size: 11px; font-weight: bold; color: blue; width: 90px; border: none; cursor: pointer;    cursor: hand;}" +
".small	{ font-size: 10px; font-family: verdana }" +
".smallbold { font-size: 10px; font-family: verdana; font-Weight: bold }" +
"-->" +
"    </style>" +
"</head>" +
"" +
"<body text=\"black\" bgcolor=\"white\" link=\"mediumblue\" alink=\"darkgreen\" VLINK=\"mediumblue\" leftmargin=0 rightmargin=0 topmargin=0 marginheight=0 marginwidth=0>" +
"<table width=\"564\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"white\">";
        exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, htmlheader);
        //exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "</td></tr></table> hi");
        try
        {
        	exporter.exportReport();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
    }

    public static String nameWebReport(Map parameters, String name)
    {
        StringBuffer hostReports = new StringBuffer();
        String nameIt = DictionaryManager.getInstance("host", (String)parameters.get("organizationId")).getProperty("reportsDisplay", "") + name;
        Map applications = PropertiesManager.getInstance((String)parameters.get("organizationId")).getSection("APPLICATION");
        String url = (String)applications.get("URL");
        String [] parts = url.split("/");
        hostReports.append(parts[0]); hostReports.append("//"); hostReports.append(parts[2]);
        parts = nameIt.split("/");
        for(int a = 3; a<parts.length;a++ ){
        	hostReports.append("/"); hostReports.append(parts[a]);
        }
        return hostReports.toString();
    }

    public static String fileNameReport(Map parameters, String name)
    {
        String nameIt = DictionaryManager.getInstance("host", (String)parameters.get("organizationId")).getProperty("reportsOut", "") + name;
        return nameIt;
    }

    public static Object poEmailApp(Map parameters)
    {
        String fileName = "notFound.html";
        Object reportName = null;

        try
        {
            Log.debug("JasperReportsHelper", "printPdf starts");

            String organizationId = (String)parameters.get("organizationId");

        	JRDataSource ds = (JRDataSource)parameters.get("datasource");

            String formType = (String)parameters.get("formType");
            Log.debug("JasperReportsHelper", "printPdf file: " + formType);
            JasperPrint jasperPrint = JasperReportsHelper.fill(formType, parameters, organizationId, ds);
            fileName = JasperReportsHelper.nameReport(parameters);
            reportName= JasperReportsHelper.selectRunner("emailhtml", jasperPrint, fileName, parameters);

            Log.debug("JasperReportsHelper", "printPdf is done");
        }
        catch (JRException e)
        {
            reportName = JasperReportsHelper.nameWebReport(parameters,"notFound.html");
            Log.error(e, e.getMessage());
            e.printStackTrace();
        }

        //reportName = JasperReportsHelper.nameWebReport(parameters, fileName);

        return reportName;


    }
    public static JasperPrint getJasperPrint(Map parameters) throws JRException
    {
    	String organizationId = (String)parameters.get("organizationId");
    	JRDataSource ds = (JRDataSource)parameters.get("datasource");
        String formType = (String)parameters.get("formType");
        Log.debug("JasperReportsHelper", "printPdf file: " + formType);
        JasperPrint jasperPrint = JasperReportsHelper.fill(formType, parameters, organizationId, ds);
        String nameMe = (String)parameters.get("namePdf");
        jasperPrint.setName(nameMe);

        return jasperPrint;
    }

    public static Object printPdf(Map parameters) throws JRException
    {
        String fileName = "notFound.pdf";
        Object reportName = null;

        try
        {
            Log.debug("JasperReportsHelper", "printPdf starts");
            String TCs = (String)parameters.get("TCs");
            String organizationId = (String)parameters.get("organizationId");
            if(Utility.isEmpty(TCs))
            {
                JasperPrint jasperPrint = JasperReportsHelper.getJasperPrint(parameters);
                fileName = JasperReportsHelper.nameReport(parameters);
                reportName= JasperReportsHelper.selectRunner("pdf", jasperPrint, fileName, parameters);
            }
            else
            {
                List jasperPrintList = (List)parameters.get("jasperPrintList");
                List reportList = new ArrayList();
                for(int i = 0; i < jasperPrintList.size(); i++)
                {
                    Object reportParms[] = (Object[])jasperPrintList.get(i);
                    JasperPrint jasperPrint = JasperReportsHelper.fill((String)reportParms[0], parameters, organizationId, (JRDataSource)reportParms[1]);
                    reportList.add(jasperPrint);
                    //JasperPrint jasperPrint = JasperReportsHelper.fill(formType, parameters, organizationId, ds);
                }
                fileName = JasperReportsHelper.nameReport(parameters);
                reportName= JasperReportsHelper.selectRunner("pdf", reportList, fileName, parameters);

            }

            Log.debug("JasperReportsHelper", "printPdf is done");
        }
        catch (JRException e)
        {
            //reportName = JasperReportsHelper.nameWebReport(parameters,"notFound.html");
            Log.error(e, e.getMessage());
            e.printStackTrace();
            throw e;
        }

        //reportName = JasperReportsHelper.nameWebReport(parameters, fileName);

        return reportName;

    }

    public static Object printTxt(Map parameters)
    {
        String fileName = "notFound.txt";
        Object reportName = null;

        try
        {
            Log.debug("JasperReportsHelper", "printTxt starts");
            String TCs = (String)parameters.get("TCs");
            String organizationId = (String)parameters.get("organizationId");
            if(Utility.isEmpty(TCs))
            {
                JasperPrint jasperPrint = JasperReportsHelper.getJasperPrint(parameters);
                fileName = (String)parameters.get("nameTxt");
                reportName= JasperReportsHelper.selectRunner("txt", jasperPrint, fileName, parameters);
            }
            else
            {
                List jasperPrintList = (List)parameters.get("jasperPrintList");
                List reportList = new ArrayList();
                for(int i = 0; i < jasperPrintList.size(); i++)
                {
                    Object reportParms[] = (Object[])jasperPrintList.get(i);
                    JasperPrint jasperPrint = JasperReportsHelper.fill((String)reportParms[0], parameters, organizationId, (JRDataSource)reportParms[1]);
                    reportList.add(jasperPrint);
                    //JasperPrint jasperPrint = JasperReportsHelper.fill(formType, parameters, organizationId, ds);
                }
                fileName = JasperReportsHelper.nameReport(parameters);
                reportName= JasperReportsHelper.selectRunner("txt", reportList, fileName, parameters);

            }

            Log.debug("JasperReportsHelper", "printPdf is done");
        }
        catch (JRException e)
        {
            reportName = JasperReportsHelper.nameWebReport(parameters,"notFound.html");
            Log.error(e, e.getMessage());
            e.printStackTrace();
        }

        //reportName = JasperReportsHelper.nameWebReport(parameters, fileName);

        return reportName;

    }

	public static Object printInvoiceLineExport(Map parameters)
	{
		String fileName = "notFound.txt";
		Object reportName = null;

		try
		{
			Log.debug("JasperReportsHelper", "printInvoiceLineExport starts");
			String TCs = (String)parameters.get("TCs");
			String organizationId = (String)parameters.get("organizationId");
			if(Utility.isEmpty(TCs))
			{
				JasperPrint jasperPrint = JasperReportsHelper.getJasperPrint(parameters);
				fileName = (String)parameters.get("nameExport");
				reportName= JasperReportsHelper.selectRunner("xls", jasperPrint, fileName, parameters);
			}
			else
			{
				List jasperPrintList = (List)parameters.get("jasperPrintList");
				List reportList = new ArrayList();
				for(int i = 0; i < jasperPrintList.size(); i++)
				{
					Object reportParms[] = (Object[])jasperPrintList.get(i);
					JasperPrint jasperPrint = JasperReportsHelper.fill((String)reportParms[0], parameters, organizationId, (JRDataSource)reportParms[1]);
					reportList.add(jasperPrint);
				}
				fileName = JasperReportsHelper.nameReport(parameters);
				reportName= JasperReportsHelper.selectRunner("txt", reportList, fileName, parameters);
			}
			Log.debug("JasperReportsHelper", "printInvoiceLineExport is done");
		}
		catch (JRException e)
		{
			reportName = JasperReportsHelper.nameWebReport(parameters,"notFound.html");
			Log.error(e, e.getMessage());
			e.printStackTrace();
		}
		return reportName;
	}

    public static Object runReport(Map parameters)
    {
        JasperPrint jasperPrint = JasperReportsHelper.fillReport(parameters);

        String fileName = "notFound.html";
        Object reportName = null;
        if(jasperPrint != null)
        {
            fileName = JasperReportsHelper.nameReport(parameters);
        }

        try
        {
            reportName= JasperReportsHelper.selectRunner((String)parameters.get("format"), jasperPrint, fileName, parameters);
        }
        catch (JRException e)
        {
            reportName = JasperReportsHelper.nameWebReport(parameters,"notFound.html");
            e.printStackTrace();
        }

        //reportName = JasperReportsHelper.nameWebReport(parameters, fileName);
        jasperPrint = null;
        reportName = JasperReportsHelper.nameWebReport(parameters, fileName);
        return reportName;
    }

    public static Object printReportPdf(Map parameters)
    {
        String fileName = "notFound.pdf";
         Object reportName = null;

        try
        {
            Log.debug("JasperReportsHelper", "printReportPdf starts");

            String organizationId = (String)parameters.get("organizationId");

       		JRDataSource ds = (JRDataSource)parameters.get("datasource");
       		fileName = (String)parameters.get("fileName");

            JasperPrint jasperPrint = null;
       	    JasperReport jasperReport = JasperReportsHelper.loadSubReport(fileName, organizationId, "", "Y");
       	    jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);

       		String nameMe = (String)parameters.get("namePdf");
       		jasperPrint.setName(nameMe);

       		fileName = JasperReportsHelper.nameReport(parameters);
       		reportName= JasperReportsHelper.selectRunner((String)parameters.get("format"), jasperPrint, fileName, parameters);
       		jasperPrint = null;
            reportName = JasperReportsHelper.nameWebReport(parameters, fileName);

            Log.debug("JasperReportsHelper", "printPdf is done");
        }
        catch (JRException e)
        {
            reportName = JasperReportsHelper.nameWebReport(parameters,"notFound.html");
            Log.error(e, e.getMessage());
            e.printStackTrace();
        }

        return reportName;

    }

    public static void main(String[] args)
    {
        System.err.println("start");
        //JasperReportsHelper.compile("serviceBlanket1.jrxml", "");
        //String path = JasperReportsHelper.getReportsPath("TEST");
        //JasperReportsHelper.compile("shiptosb.jrxml", "");
        //JasperReportsHelper.compile("poitems.jrxml", "");
        //JasperReportsHelper.fill("shiptosb.jasper", parementers, "", ds);
        //Log.debug("JasperReportsHelper",JasperReportsHelper.getImgUrl("PURIDIOM", "newblue.gif"));
        Log.debug("JasperReportsHelper",JasperReportsHelper.getSigUrl("002453", "BSC04P"));
        System.err.println("done");
    }
}
