/*
 * Created on Oct 6, 2003
 */
package com.tsagate.foundation.utility;

import com.tsagate.foundation.service.error.ErrorHandlingService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.input.DOMBuilder;

/**
 * @author renzo
 */
public class Utility
{
	/**
	 * Copies the contents from a file to another.
	 * Method assumes that files exist(all checks should be done before calling method).
	 * @param originalFile - (File to copy from)
	 * @param newFile - (File to copy to)
	 */
	public static void copyFile(File originalFile, File newFile)
	{
		FileInputStream from = null;
		FileOutputStream to = null;
		try
		{
			from = new FileInputStream(originalFile);
			to = new FileOutputStream(newFile);
			byte buffer[] = new byte[from.available()];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) > -1)
			{
				to.write(buffer, 0, bytesRead);
			}
			if (!newFile.exists())
			{
				throw new IOException("The file could not be created.");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error copying files, originalFile[" + originalFile.getAbsolutePath() + "], newFile[" + newFile.getAbsolutePath() + "]");
		}
		finally
		{
			if (from != null)
			{
				try
				{
					from.close();
				}
				catch (IOException e)
				{
					System.out.println("Error copying files(closing from stream), originalFile[" + originalFile.getAbsolutePath() + "], newFile[" + newFile.getAbsolutePath() + "]");
				}
			}
			if (to != null)
			{
				try
				{
					to.close();
				}
				catch (IOException e)
				{
					System.out.println("Error copying files(closing to stream), originalFile[" + originalFile.getAbsolutePath() + "], newFile[" + newFile.getAbsolutePath() + "]");
				}
			}
		}
	}
	public static String getDateFormat(Object date, String pattern)
    {
		return Utility.getDateFormat(date, pattern, null);
    }
    /**
     * getDateFormat
     * <p>will find out if date is a string or a date and format it to the correct string.
     * need to find about locales.</p>
     * <p>As a remainder when updating a date to the database always use the <b>java.sql.Date</b> class
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateFormat(Object date, String pattern, String locale)
    {
    	SimpleDateFormat formatter = null;
    	if(Utility.isEmpty(locale))
    	{
    		formatter = new SimpleDateFormat (pattern);
    	}
    	else
    	{
    		if(Utility.isEmpty(locale))
    		{
    			locale = "es";
    		}
    		formatter = new SimpleDateFormat (pattern, new Locale(locale));
    	}
        String objReturn = "";
        if(Utility.isEmpty(pattern) )
        {
            pattern = "yyyy-MM-dd";
        }

        try
        {
            if(date == null || (date instanceof String && Utility.isEmpty((String) date)))
            {
                date = new Date(Calendar.getInstance().getTimeInMillis());
            } else if (date instanceof String) {
                date = Dates.getDate((String) date);
            }

            objReturn = formatter.format(date);

        }
        catch (Exception e)
        {
            ErrorHandlingService ehs = ErrorHandlingService.getInstance();
            Object args[] = {date, pattern};
            ehs.handleException("Utility", "getDateFormat", args, e);
        }

        return objReturn;
    }
    /**
    *  isEmpty Method
    *  Tests for an empty string
    *  @param  test String
    *  @return boolean
    */
   public static boolean isObjectEmpty(Object object)
   {
       boolean lbEmpty = false;
       if(object != null)
       {
           String objString = "";
           if (object instanceof String)
           {
               objString = (String) object;
           }
           else
           {
               objString = object.toString();
           }
           lbEmpty = Utility.isEmpty(objString);
       }
       else
       {
           lbEmpty = true;
       }

       return lbEmpty;
   }

    /**
    *  isEmpty Method
    *  Tests for an empty string
    *  @param  test String
    *  @return boolean
    */
   public static boolean isEmpty(String test)
   {
       boolean lbEmpty = false;

       if (test == null || test.trim().length() <= 0)
       {
           lbEmpty = true;
       }

       return lbEmpty;
   }

   /**
    *  ckNull Method
    *  @param  test String value to test - set to an empty string if null
    *  @return String
    */
   public static String ckNull(String test)
   {
       if (test == null)
       {
           test =  "";
       }
       else
       {
           if(test.length() <= 0)
           {
           test = "";
           }
       }
       return test;
   }

   public static String formatString(String sformat, String temp) throws Exception
   {
       String  formatted = "";
       int     ilen = 0;
       int     itempLen = 0;
       char[]  chTemp = temp.toCharArray();
       char[]  chFormat = sformat.toCharArray();
       char[]  chReturn = chFormat;

       ilen = chFormat.length;
       itempLen = chTemp.length;

       int icnt = 0;

       try
       {
           for (int i = itempLen - 1; i >= 0; i--)
           {
                   chReturn[(ilen - 1) - icnt] = chTemp[i];
                   icnt++;
           }

        formatted = new String(chReturn);
       }
       catch (IndexOutOfBoundsException iobe)
       {
               iobe.printStackTrace();
       }
       catch (Exception e)
       {
            throw e;
       }

       return formatted;
   }

   public static BigDecimal getBigDecimalFormatted(Object object, int decimalPlaces)
   {
        BigDecimal bd = new BigDecimal("0.00");
           try
        {
               if (object instanceof String) {
                   String	objectString = (String) object;
                   if (!Utility.isEmpty(objectString)) {
                       bd = new BigDecimal(objectString);
                   }
               }
               else {
                   bd = (BigDecimal) object;
               }
            if (bd == null)
            {
                bd = new BigDecimal("0.00000");
            }
            bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bd;
   }
   /**
    * toString Method returns the string representation of the object.
    * if it is a BigDecimal will return 0.
    * @param o
    */
   public static String tsaToString(Object o)
   {
           String ret = "";
           if(o != null)
           {
               ret = o.toString();
           }
           else
           {
               if (o instanceof BigDecimal)
               {
                   ret = "0";
               }
               else
               {
                   ret = "";
               }
           }
           Log.debug("Utility.tsaToString", ret);
           return ret;
   }

   /**
    *  searchAndReplace Method
    *  @param  asString String search and replace on
    *  @param  asSearch String value to search for
    *  @param  asReplace String value to replace with
    *  @param  abIgnore boolean - if true, ignore case
    *  @return String
    */
   public static String searchAndReplace(String originalString, String searchString, String replaceString, boolean ignoreCase)
   {
       String tempString = "";
       String tempSearch = "";

       if(ignoreCase)
       {
        tempString = originalString.toLowerCase();
        tempSearch = searchString.toLowerCase();
       }
       else
       {
        tempString = originalString;
        tempSearch = searchString;
       }

       StringBuffer    lsbString = new StringBuffer(originalString);
       StringBuffer    lsbStringtemp = new StringBuffer(tempString);
       int             liStart = 0;
       int             liEnd = 0;

       liStart  = lsbStringtemp.toString().indexOf(tempSearch);
       liEnd    = searchString.length() + liStart;

       while (liStart >= 0)
       {
           lsbString.replace(liStart, liEnd, replaceString);
           lsbStringtemp.replace(liStart, liEnd, replaceString);
           liStart  = lsbStringtemp.toString().indexOf(searchString);
           liEnd    = searchString.length() + liStart;
       }
       return lsbString.toString();
   }

   //returns the name of the month when given the numeric value
   public static String getMonthName(java.math.BigDecimal bd_month)
   {
           String month = "";
           final String monthNames[] = {"January", "February", "March",
                   "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
           if (bd_month != null)
           {
               bd_month = bd_month.subtract(new BigDecimal(1));
               month = monthNames[bd_month.intValue()];
           }

           return month;
       }

    /**
    *  isAlphaCharacter Method
    *  Tests for an alphabetic character
    *  @param  test char
    *  @return boolean
    */
      public static boolean isAlphaChar(char test)
      {
           String	alphaCharacters = "AabBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
           boolean lbAlpha = false;

           lbAlpha = (alphaCharacters.indexOf(test) >= 0);

           return lbAlpha;
      }

    /**
    *  numericFilter Method
    *  Filters to return only numeric characters
    *  @param  filterString String
    *  @return String
    */
    public static String numericFilter(String filterString)
    {
       String	numericCharacters = "0123456789";
       String	returnString = "";
       String	test = "";

        for ( int i = 0; i < Utility.ckNull(filterString).length(); i++) {
            test = filterString.substring(i,i+1);
            if (numericCharacters.indexOf(test) >= 0) { returnString += test; }
        }
        return returnString;
    }
    public static void main(String[] args)
    {
        File file = new File("C:\\JavaProjects\\PuridiomExpress\\workspace\\ExpressProperties\\src\\com\\tsagate\\properties\\Dictionary.java");
        System.out.println("File: " + file.getName());
        System.out.println("Parent: " + file.getParentFile().toString());
        System.out.println("Path: " + file.getPath());
    }

    /**
     * @param fileName
     * @param organizationId
     * @return File
     */
    public static File getOidFile(String fileName, String organizationId)
    {
    	Log.debug(Utility.class, "getOidFile: fileName: " + fileName + ", organizationId" + organizationId);
        File file = new File(fileName);
        File ret = null;
        File dir = file.getParentFile();
        try
        {
	        if(dir.exists())
	        {
	        	Log.debug(Utility.class, "directory exists");
	            //look for file in organization's directory
	            File tempDir = null;
	            if (organizationId != null)
	            {
	                tempDir = new File(dir.getPath() + File.separator + organizationId.toString().toLowerCase() + File.separator + file.getName());
	            }
	            if(tempDir == null || !tempDir.exists())
	            {
	                //look for file in puridiom directory
	                tempDir = new File(dir.getPath() + File.separator + file.getName());
	                if(!tempDir.exists())
	                {
	                    //look for file in normal directory
	                    tempDir = new File(dir.getPath() + File.separator + file.getName());
	                    if(!tempDir.exists())
	                    {
	                        tempDir = new File(file.getName());
	                        Log.error("Utility", fileName + " could not be found!");
	                    }
	                }
	            }
	            ret = tempDir;
	        }
	        else
	        {
	        	Log.debug(Utility.class, "directory does not exists");
	        	 dir = dir.getParentFile();
	        	 Log.debug(Utility.class, "parent is: " + dir.getPath());
	        	 if(dir.exists())
	             {
	        		 File tempDir = new File(dir.getPath() + File.separator + file.getName());
	        		 if(tempDir.exists())
	        		 {
	        			 ret = tempDir;
	        		 }
	        		 else
	        		 {
	        			 Log.error(Utility.class, tempDir.getPath() + " was not found");
	        		 }
	             }
	        	 else
        		 {
        			 Log.error(Utility.class, "Parent " + dir.getPath() + " was not found");
        		 }
	        }
        }
        catch (Exception e) {
			Log.error(Utility.class, fileName + " file was not found.");
		}

        Log.debug(Utility.class, fileName + "found in [" + ret.getAbsolutePath() + "]");

        return ret;
    }

    public static String replaceAll(String replaceString, String originalString, String replacementString)
    {
        String returnStr = "";

        if(replacementString == null)
        {
            replacementString = "";
        }
        if(originalString.indexOf(replaceString) < 0)
        {
            return originalString;
        }
        try
        {
            // Compile regular expression
            Pattern pattern = Pattern.compile(replaceString);

            // Replace all occurrences of pattern in input
            Matcher matcher = pattern.matcher(originalString);
            int index = replacementString.indexOf("$");
    		StringBuffer sb = new StringBuffer(replacementString);

    		while (index > -1)
    		{
    			sb.replace(index, index + 1, "\\$");
    			index = sb.indexOf("$", index + 3);
    			replacementString = sb.toString();
    		}
            returnStr = matcher.replaceAll(replacementString);

        }
        catch (Exception e)
        {
            returnStr = originalString;
            System.out.println("replaceString: " + replaceString);
            System.out.println("originalString: " + originalString);
            System.out.println("replacementString: " + replacementString);
            e.printStackTrace();
        }
        finally
        {
            //clean up
        }
        return returnStr;
    }

    public static Document loadXml(String fileName, String organizationId)
    {
    	Document document = null;
        try
        {
            //File f = new File(ruleName);
            File f = Utility.getOidFile(fileName, organizationId);
            if (f.exists())
            {
                DOMBuilder docBuilder = new DOMBuilder();
                document = docBuilder.build(f);
            }
            else
            {
                throw new Exception("File not found for: " + fileName);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            //system.out.println(exception.toString());
        }
        return document;
    }
    public static String nonAlphanumericFilter(String filterString)
    {
       String	nonAlphanumericCharacters = "{}[],.<>:'?/|`~!@#$%^&*()_-+\\" + '"';
       String	returnString = "";
       String	test = "";

        for ( int i = 0; i < Utility.ckNull(filterString).length(); i++) {
            test = filterString.substring(i,i+1);
            if (nonAlphanumericCharacters.indexOf(test) >= 0) { returnString += test; }
        }
        return returnString;
    }
}
