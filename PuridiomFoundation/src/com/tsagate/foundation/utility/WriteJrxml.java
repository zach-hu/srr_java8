/*
 * Created on Feb 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsagate.foundation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.jdom.CDATA;
import org.jdom.Element;

//import com.tsa.puridiom.updateservice.tasks.TSAFormatWord;
import com.tsagate.properties.DictionaryManager;


/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WriteJrxml
{
	public String getPath(String type)
	{
		return DictionaryManager.getInstance("host","PURIDIOM").getProperty("reportsPath", "\\xml\\report\\jasper") + "reports" + File.separator;
	}

	public String getTemplate()
	{
		return "template.jrxml";
	}

	public void copyFile(String fileName, String newFileName)
	{
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try
		{
			fis = new FileInputStream(fileName);
	        fos = new FileOutputStream(newFileName);
	        FileChannel canalFuente = fis.getChannel();
	        FileChannel canalDestino = fos.getChannel();
	        canalFuente.transferTo(0, canalFuente.size(), canalDestino);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			if(fis != null)
			{
				try
				{
					fis.close();
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
			if(fos != null)
			{
				try
				{
					fos.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

//***************************************************************************************************************************************

	public void writeFilterTemplate(Map map)
    {
       try
       {
    	   String path = this.getPath("filter");
           String newFileName = path + (String)map.get("newName");
           this.copyFile(path + this.getTemplate(), newFileName);
           String selectPart = (String)map.get("SELECT");
           this.processXml(selectPart, newFileName);

       }
       catch (Exception e)
       {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }

	public String getReportName(File file)
	{
		String fileName = file.getName();
		String reportName = "";
		int dotIndex = fileName.lastIndexOf(".");
		if(dotIndex > -1)
		{
			reportName = fileName.substring(0, dotIndex);
			TSAFormatWord formatWord= new TSAFormatWord();
			reportName = formatWord.formatToClassName(reportName);
		}
		return reportName;
	}

	public void processXml(String selectPart, String newFileName)
	{
		XmlFile xmlFile = new XmlFile(newFileName);
        Element root = xmlFile.getRoot();
        xmlFile.getRoot().getAttribute("name").setValue(this.getReportName(new File(newFileName)));

        this.setfields(root.getChildren("field"), selectPart);
        this.setTitleText(root.getChild("title").getChild("band").getChildren("staticText"), selectPart);
        this.setDetail(root.getChild("detail").getChild("band").getChildren("textField"), selectPart);

        xmlFile.getRoot().removeAttribute("isIgnorePagination");
        xmlFile.output();
	}

	public void setfields(List fields, String selectPart)
	{
        StringTokenizer st = new StringTokenizer(selectPart, ",");
        int fieldsSize = fields.size();
        int i = 0;
        while (st.hasMoreElements())
        {
     	   String next = (String)st.nextElement();

     	  String []part = null;
     	  String name = next.replace('.', ';');
     	  part = name.split(";");
     	  String tableName = part[0].trim();
     	  String atributeName = part[1].trim();

     	  TSAFormatWord formatWord= new TSAFormatWord();
		  tableName = formatWord.formatToClassName(tableName);
		  atributeName = formatWord.formatToVariableName(atributeName);

		  String colName = tableName+"_" + atributeName;

		  if(i < fieldsSize)
		  {
	           Element field = (Element)fields.get(i);

	           field.setAttribute("name", colName);
	           if(colName.toLowerCase().indexOf("date") > 0)
               {
	        	   field.setAttribute("class","java.util.Date");
               }
               else if(next.toLowerCase().indexOf("total") > 0 || next.toLowerCase().indexOf("amount") > 0)
               {
            	   field.setAttribute("class","java.math.BigDecimal");
               }
               else
               {
            	   field.setAttribute("class","java.lang.String");
               }

	           fields.set(i, field);
	           i++;
		  }
        }

        if(i < fieldsSize)
        {
        	while(fieldsSize > i)
        	{
        		fields.remove(fieldsSize -1);
            	fieldsSize--;
        	}
        }
	}

	public void setTitleText(List staticTextFields, String selectPart)
	{

        StringTokenizer st = new StringTokenizer(selectPart, ",");
        int fieldsSize = staticTextFields.size();
        int i = 0;
        while (st.hasMoreElements())
        {
     	   String next = (String)st.nextElement();

     	  String []part = null;
     	  String name = next.replace('.', ';');
     	  part = name.split(";");
     	  String atributeName = part[1].trim();

     	  TSAFormatWord formatWord= new TSAFormatWord();
		  atributeName = formatWord.formatToText(atributeName);

		  if(i < 14)
		  {
	           Element staticText = (Element)staticTextFields.get(i);
	           Element text = staticText.getChild("text");
	           CDATA cdata = new CDATA(atributeName);
	           List cdataL = new ArrayList();
	           cdataL.add(cdata);
	           text.setContent(cdataL);

	           staticTextFields.set(i, staticText);
	           i++;
		  }
        }

        if(i < fieldsSize)
        {
        	while(fieldsSize > i)
        	{
        		staticTextFields.remove(fieldsSize -1);
            	fieldsSize--;
        	}
        }

	}

	public void setDetail(List staticTextFields, String selectPart)
	{
        StringTokenizer st = new StringTokenizer(selectPart, ",");
        int fieldsSize = staticTextFields.size();
        int i = 0;
        while (st.hasMoreElements())
        {
     	   String next = (String)st.nextElement();

     	  String []part = null;
     	  String name = next.replace('.', ';');
     	  part = name.split(";");
     	 String tableName = part[0].trim();
    	  String atributeName = part[1].trim();

    	  TSAFormatWord formatWord= new TSAFormatWord();
		  tableName = formatWord.formatToClassName(tableName);
		  atributeName = formatWord.formatToVariableName(atributeName);

		  String colName = "$F{" + tableName + "_" + atributeName + "}";
		  if(i < 14)
		  {
	           Element staticText = (Element)staticTextFields.get(i);
	           Element text = staticText.getChild("textFieldExpression");
	           CDATA cdata = new CDATA(colName);
	           List cdataL = new ArrayList();
	           cdataL.add(cdata);
	           text.setContent(cdataL);

	           staticTextFields.set(i, staticText);
	           i++;
		  }
        }

        if(i < fieldsSize)
        {
        	while(fieldsSize > i)
        	{
        		staticTextFields.remove(fieldsSize -1);
            	fieldsSize--;
        	}
        }
	}

   public Map mapJRXMLGenerator(String selectString, String newName)
   {
	   // "supplier-summary-report.xml"
	   Map map = new HashMap();
       map.put("SELECT", selectString);
       map.put("newName", newName);

	   return map;
   }

   public static void main(String[] args) throws ClassNotFoundException, SQLException
   {
       System.out.println("start");
       StringBuffer sb = new StringBuffer("");
       String newNameTemplate = "r_rqh_acc.jrxml";

       Map map = new HashMap();
       sb.append("account.fld_1 ,	account.fld_2 ,	account.fld_3 ,	account.fld_4 ,	account.fld_5 ,	account.fld_6 ,	account.fld_7 ,");
       sb.append("account.fld_8 ,	account.fld_9 ,	account.fld_10 ,	account.fld_11 ,	account.fld_12 ,	account.fld_13 ,	account.fld_14 ,	account.fld_15 ,");
       sb.append("requisition_header.department_code ,	vendor.vendor_class ,	account.alloc_amount ,	requisition_header.requisition_number ,");
       sb.append("requisition_header.requisition_date ,	requisition_header.status ,	requisition_header.total ,	vendor.vendor_id ,");
       sb.append("requisition_header.requisition_type ,	requisition_header.assigned_buyer ");

       WriteJrxml xml = new WriteJrxml();
       map = xml.mapJRXMLGenerator(sb.toString(), newNameTemplate);
       xml.writeFilterTemplate(map);

       System.out.println("end");
   }
}

