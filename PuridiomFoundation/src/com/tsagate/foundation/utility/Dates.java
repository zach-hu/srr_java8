package com.tsagate.foundation.utility;

import java.sql.Time;
import java.text.*;
import java.util.*;

/**
 * @author JEFF
 */
public class Dates {
	private int month, day, year;
	private int j, y, d, m;
	private String s_date;
	public java.sql.Date dates = null;

	/** Creates new date **/
	public Dates() {
	}

	public static java.sql.Date getDate(String sDate, String sDateFormat)
	{
		java.sql.Date sqlDate = null;
		try
		{
            Calendar calendar = Calendar.getInstance();
			if (!Utility.isEmpty(sDate))
			{

                String[] dateFormateArray;
                String[] dateArray;
                int yearInd = 0;
                int monthInd = 0;
                int dayInd = 0;
                String strMonth = "";
                String strYear = "";
                String strDay = "";

                String[] strSplitter = {"-", " ", "/", "."};
                for (int i = 0; i < strSplitter.length; i++)
                {
                	if (sDateFormat.indexOf(strSplitter[i]) != -1)
                	{
                		dateFormateArray = sDateFormat.split(strSplitter[i]);
                		for (int ix = 0; ix < dateFormateArray.length ; ix++)
                		{
                			if (dateFormateArray[ix].equalsIgnoreCase("MM")) {
                				monthInd = ix;
                			} else if (dateFormateArray[ix].equalsIgnoreCase("dd")) {
                				dayInd = ix;
                			} else if (dateFormateArray[ix].equalsIgnoreCase("yyyy")) {
                				yearInd = ix;
                			} else if (dateFormateArray[ix].equalsIgnoreCase("yy")) {
                				yearInd = ix;
                			}
                		}
                		break;
                	}
                }

                for (int ia = 0; ia < strSplitter.length; ia++)
                {
                	if (sDate.indexOf(strSplitter[ia]) != -1) {
                		dateArray = sDate.split(strSplitter[ia]);
                		strMonth = dateArray[monthInd];
                		strDay = dateArray[dayInd];
                		strYear = dateArray[yearInd];
                	}
                }

                if (strDay.length() == 1) strDay ="0" + strDay;
                if (strMonth.length() == 1) strMonth = "0" + strMonth;
                if (strYear.length() == 2) strYear = "20" + strYear;

                int year = Integer.valueOf(strYear);
                int month = Integer.valueOf(strMonth);
                int day = Integer.valueOf(strDay);

                calendar.set(year, (month-1), day);
			}
			sqlDate = new java.sql.Date(calendar.getTimeInMillis());
            System.out.println("date = " + sqlDate.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sqlDate;
	}

    public static java.sql.Date getDate(String sDate)
    {
        java.sql.Date sqlDate = null;
        try
        {
            Date    date = new Date();
            if (!Utility.isEmpty(sDate))
            {
                sDate = sDate.replaceAll("-", "/").trim();
                int p = sDate.indexOf(" ") ;
                if (p >= 0) {
                	sDate = sDate.substring(0, p) ;
                }
                date = new Date(sDate);
            }
            sqlDate = new java.sql.Date(date.getTime());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sqlDate;
    }


	public static java.sql.Date getDateTime(String sDate) {
		java.sql.Date sqlDate = null;
		try
		{
			Date date = Calendar.getInstance().getTime();
			if (!Utility.isEmpty(sDate))
			{
				sDate = sDate.replaceAll("-", "/");
				int p = sDate.indexOf(" ") ;
				if (p >= 0) {
					sDate = sDate.substring(0, p) ;
				}
				date = new Date(sDate);
			}
			sqlDate = new java.sql.Date(date.getTime());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sqlDate;
	}

	public static java.util.Date getDateTimeHours(String sDate) {
		java.util.Date utilDate = null;
		try
		{
			 Date date = Calendar.getInstance().getTime();
			 //Calendar.AM_PM;
			 //Date date = new Date();
			 int year=date.getYear();
		     int month=date.getMonth();
		     int day=date.getDate();
		     int hour=date.getHours();
		     int minute=date.getMinutes();
		     int second=date.getSeconds();
		     java.sql.Timestamp timestamp =new java.sql.Timestamp(year,month,day,hour,minute,second,0);
		     if (!Utility.isEmpty(sDate))
		     {
				sDate = sDate.replaceAll("-", "/");
				date = new Date(sDate);
			}
			utilDate = timestamp;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			return utilDate;
		}
	}

	/** Creates new date */
	public Dates(int m, int d, int y) {
		month = m;
		day = d;
		year = y;
	}

	// Set the julian to the Julian day number corresponding to the given parameters.
	// From Algorithm 199 of the Collected Algorithms of the ACM
	// Valid for any date after Oct. 15, 1582
	private long setJulianDay(int m, int d, int y) {
		// add a one to the month to convert from gregorain calander system to ours
		m = m + 1;

		if (m > 2) {
			m = m - 3;
		}
		else {
			m = m + 9;
			y = y - 1;
		}

		int c = y / 100;
		int ya = y - 100 * c;

		long p1 = (146097 * c) / 4;
		long p2 = (1461 * ya) / 4;
		long p3 = (153 * m + 2) / 5;
		long julian = p1 + p2 + p3 + d + 1721119;

		return julian;
	}

	//Returns the number of days between two Date Objects
	public int getDateDifference(Date d_date1, Date d_date2) {
		int iDay = d_date1.getDate() + 1;
		int iMonth = d_date1.getMonth() + 1;
		int iYear = d_date1.getYear() + 1900;

		Dates d1 = new Dates(iMonth, iDay, iYear);

		iDay = d_date2.getDate() + 1;
		iMonth = d_date2.getMonth() + 1;
		iYear = d_date2.getYear() + 1900;

		Dates d2 = new Dates(iMonth, iDay, iYear);

		return daysBetween(d1, d2);
	}

	//Returns the number of days between two Dates
	public int daysBetween(Dates d_date1, Dates d_date2) {

		//Calendar Instances
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		//Clear the Calendar Instances of their values
		date1.clear();
		date2.clear();

		//Set the dates from the user input
		date1.set(d_date1.year, d_date1.month - 1, d_date1.day);
		date2.set(d_date2.year, d_date2.month - 1, d_date2.day);

		//Get the Julian Dates
		long julian1 =
			setJulianDay(
				date1.get(Calendar.MONTH),
				date1.get(Calendar.DAY_OF_MONTH),
				date1.get(Calendar.YEAR));
		long julian2 =
			setJulianDay(
				date2.get(Calendar.MONTH),
				date2.get(Calendar.DAY_OF_MONTH),
				date2.get(Calendar.YEAR));

		int j_return;

		//Which is bigger
		if (julian1 == julian2) {
			j_return = 0;
		}
		else if (julian1 > julian2 || julian2 > julian1) {
			//j_return = julian1 - julian2;
			j_return = (int) (julian2 - julian1);
		}
		else {
			j_return = -1;
		}

		return j_return;
	}

	//returns a new date given a date and a number of days to add or subtract from
	public static Date add(Date d_date, int num_days) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(d_date);

		cal.add(Calendar.DATE, num_days);

		return (Date) cal.getTime();
	}

	//returns a new date string given a date and a number of days to add or subtract from
	public String add(Dates d_date, int num_days)
	{
		// New calander date
		Calendar date = new GregorianCalendar();

		//Need this line to format to string
		DateFormat df = DateFormat.getDateInstance();

		//Clear the calander object
		date.clear();

		//Set the dates from the user input
		date.set(d_date.year, d_date.month - 1, d_date.day);

		//add days
		date.add(Calendar.DATE, num_days);

		//Get the date
		Date d = (Date) date.getTime();

		//Format to a string and return
		s_date = df.format(d);

		return s_date;

	}

	//returns a new date string given a date string and a number of days to add or subtract from
	public Date add(String s_date, int num_days, boolean bdate) {
		final String METHOD_NAME = "addDate ";

		y = 0;
		d = 0;
		m = 0;

		StringBuffer sb = new StringBuffer(s_date);
		String token = findToken(sb);

		s_date = sb.toString();

		StringTokenizer parser = new StringTokenizer(s_date, token);

		try {
			if (s_date.indexOf(token) < 4) {
				while (parser.hasMoreTokens()) {
					m = Integer.parseInt(parser.nextToken());
					d = Integer.parseInt(parser.nextToken());
					y = Integer.parseInt(parser.nextToken());
				}
			}
			else {
				while (parser.hasMoreTokens()) {
					y = Integer.parseInt(parser.nextToken());
					m = Integer.parseInt(parser.nextToken());
					d = Integer.parseInt(parser.nextToken());
				}
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		// New calander date
		Calendar date = new GregorianCalendar();

		//Clear the calander object
		date.clear();

		//Set the dates from the user input
		date.set(y, m - 1, d);

		//add days
		date.add(Calendar.DATE, num_days);

		//Get the date
		Date d = (Date) date.getTime();

		return d;
	}

	public Date add(String s_date, int num_days, boolean bdate, String format) {
		final String METHOD_NAME = "addDate ";

		y = 0;
		d = 0;
		m = 0;

		StringBuffer sb = new StringBuffer(s_date);
		String token = findToken(sb);

		s_date = sb.toString();

		StringTokenizer parser = new StringTokenizer(s_date, token);

		try {
            if ( format.indexOf("d") < format.indexOf("M") && format.indexOf("M") < format.indexOf("y")){
				while (parser.hasMoreTokens()) {
					d = Integer.parseInt(parser.nextToken());
					m = Integer.parseInt(parser.nextToken());
					y = Integer.parseInt(parser.nextToken());
				}
			}
            else if ( format.indexOf("y") < format.indexOf("d") && format.indexOf("d") < format.indexOf("M")){
                while (parser.hasMoreTokens()) {
                    y = Integer.parseInt(parser.nextToken());
                    d = Integer.parseInt(parser.nextToken());
                    m = Integer.parseInt(parser.nextToken());
                }
            }
            else if (s_date.indexOf(token) < 4) {
				while (parser.hasMoreTokens()) {
					m = Integer.parseInt(parser.nextToken());
					d = Integer.parseInt(parser.nextToken());
					y = Integer.parseInt(parser.nextToken());
				}
			}
			else {
				while (parser.hasMoreTokens()) {
					y = Integer.parseInt(parser.nextToken());
					m = Integer.parseInt(parser.nextToken());
					d = Integer.parseInt(parser.nextToken());
				}
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		// New calander date
		Calendar date = new GregorianCalendar();

		//Clear the calander object
		date.clear();

		//Set the dates from the user input
		date.set(y, m - 1, d);

		//add days
		date.add(Calendar.DATE, num_days);

		//Get the date
		Date d = (Date) date.getTime();

		return d;
	}



	//returns a new date string given a date string and a number of days to add or subtract from
	public static String add(String s_date, int num_days) {
		return add(s_date, num_days, "MM-dd-yyyy");
	}

	//returns a new date string given a date string and a number of days to add or subtract from
	public static String add(String s_date, int num_days, String format) {
		Dates dates = new Dates();

		Date d = null;
		if (format.equals("MM-dd-yyyy"))
		{
			d = dates.add(s_date, num_days, true);
		}
		else
		{
			d = dates.add(s_date, num_days, true, format);
		}

		//Format to a string and return
		//SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat df = new SimpleDateFormat(format);

		s_date = df.format(d);

		return s_date;

	}

	//Returns the number of days between two Dates
	public int daysBetween(String s_date1, String s_date2) {
		int j_return = 0;
		int y1 = 0;
		int y2 = 0;
		int d1 = 0;
		int d2 = 0;
		int m1 = 0;
		int m2 = 0;

		StringBuffer sb1 = new StringBuffer(s_date1);
		StringBuffer sb2 = new StringBuffer(s_date2);

		String token1 = findToken(sb1);
		String token2 = findToken(sb2);

		s_date1 = sb1.toString();
		s_date2 = sb2.toString();

		StringTokenizer parser1 = new StringTokenizer(s_date1, token1);
		StringTokenizer parser2 = new StringTokenizer(s_date2, token2);

		try {
			while (parser1.hasMoreTokens()) {
				y1 = Integer.parseInt(parser1.nextToken());
				m1 = Integer.parseInt(parser1.nextToken());
				d1 = Integer.parseInt(parser1.nextToken());
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		try {
			while (parser2.hasMoreTokens()) {
				y2 = Integer.parseInt(parser2.nextToken());
				m2 = Integer.parseInt(parser2.nextToken());
				d2 = Integer.parseInt(parser2.nextToken());
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		//Calendar Instances
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		//Clear the Calendar Instances of their values
		date1.clear();
		date2.clear();

		//Set the dates from the user input
		date1.set(y1, m1 - 1, d1);
		date2.set(y2, m2 - 1, d2);

		//Get the Julian Dates
		long julian1 =
			setJulianDay(
				date1.get(Calendar.MONTH),
				date1.get(Calendar.DAY_OF_MONTH),
				date1.get(Calendar.YEAR));
		long julian2 =
			setJulianDay(
				date2.get(Calendar.MONTH),
				date2.get(Calendar.DAY_OF_MONTH),
				date2.get(Calendar.YEAR));

		//Which is bigger
		if (julian1 == julian2) {
			j_return = 0;
		}
		else if (julian1 > julian2 || julian2 > julian1) {
			j_return = (int) (julian2 - julian1);
		}
		else {
			j_return = -1;
		}

		return j_return;
	}

    //returns todays date as a string in default format if asFormat empty
    /**
     * @param format
     * @return
     */
    public static String today(String format) {
        return today(format, "");
    }


    //returns todays date as a string in default format if format empty, default server time zone if timeZoneId is empty
	/**
	 * @param format
	 * @return
	 */
	public static String today(String format, String timeZoneId) {
		SimpleDateFormat formatter;
		String dateString;
		String dateFormat = "";
		String origFormat = "";

        if (Utility.isEmpty(timeZoneId)) {
            //timeZoneId = Calendar.getInstance().getTimeZone().getID();
            timeZoneId = TimeZone.getDefault().getID();
        }

        if (!Utility.isEmpty(format) && format.length() == 10 || format.length() == 8) {
            format = format.replaceAll("Y", "y");
            format = format.replaceAll("D", "d");
        }

		if (Utility.isEmpty(format)) {
			dateFormat = "MM/dd/yyyy";

			origFormat = dateFormat;
			dateFormat = dateFormat.replace('-', '/');
		}
		else {
			dateFormat = format;
		}

		formatter = new SimpleDateFormat(dateFormat);
        formatter.setTimeZone(TimeZone.getTimeZone(timeZoneId));
		dateString = formatter.format(new Date());

		if (origFormat.indexOf("-") > 0) {
			dateString = dateString.replace('/', '-');
		}

		return dateString;
	}

    /**
     * @param format
     * @return
     */
    public static java.util.Date getCurrentDate(String timeZoneId) {
        SimpleDateFormat formatter;
        java.util.Date today;

        if (Utility.isEmpty(timeZoneId)) {
            timeZoneId = TimeZone.getDefault().getID();
        }

        formatter = new SimpleDateFormat();
        formatter.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        String dateString = formatter.format(new Date());

        try {
            today = new Date(dateString);
        } catch (Exception e) {
            today = new Date();
        }

        return today;
    }

	public static String getFiscalYear(int begin, String timeZoneId)
	{
		Date today = Dates.getCurrentDate(timeZoneId);
		//Calendar cal = GregorianCalendar.getInstance();

		int month = today.getMonth() + 1;
		//int month = cal.get(Calendar.MONTH) + 1;
		int year = today.getYear() + 1900;
		//int year = cal.get(Calendar.YEAR) + 1900;

		String fYear = null;

		if ((month >= begin) || (begin == 1))
		{
			fYear = Integer.toString(year);
		}
		else
		{
			fYear = Integer.toString(year - 1);
		}

		return fYear;
	}

	public static Date getFiscalYearStartDate(int begin, String timeZoneId)
	{
		Date today = new Date();
		int month = today.getMonth() + 1;
		String	fiscalYear = String.valueOf(month) + "-01-" + Dates.getFiscalYear(begin, timeZoneId);

		return Dates.getDate(fiscalYear);
	}

	//Returns the number of seconds between two Time Strings
	//  If dates are included in the string, they are ignored
	public int getSecondsBetween(String s_date1, String s_date2) {
		Time time1 = this.getTime(s_date1);
		Time time2 = this.getTime(s_date2);

		return this.getSecondsBetween(time1, time2);
	}

	//Returns the number of seconds between two Times
	public int getSecondsBetween(Time t1, Time t2) {
		int seconds1 = 0;
		int seconds2 = 0;
		int minutes = 0;
		int hours = 0;

		hours = t1.getHours();
		minutes = t1.getMinutes();
		seconds1 = t1.getSeconds();

		seconds1 = (hours * 3600) + (minutes * 60) + seconds1;

		hours = t2.getHours();
		minutes = t2.getMinutes();
		seconds2 = t2.getSeconds();

		seconds2 = (hours * 3600) + (minutes * 60) + seconds2;

		return (seconds2 - seconds1);
	}
	public static String convertFrom24to12(String time)
	{
		String time12 = "";
		try
		{
			SimpleDateFormat sdf24 = new SimpleDateFormat( "hh:mm");
			SimpleDateFormat sdf12 = new SimpleDateFormat( "hh:mm aa");
			time12 = sdf12.format(sdf24.parse("13:09"));

		}
		catch (ParseException e)
		{
			time12 = "";
			e.printStackTrace();
		}
		return time12;
	}
	public static void main(String[] args) {

		try
		{
			SimpleDateFormat sdf24 = new SimpleDateFormat( "hh:mm");
			SimpleDateFormat sdf12 = new SimpleDateFormat( "hh:mm aa");
			System.out.println(sdf12.format(sdf24.parse("13:09")));

			(new SimpleDateFormat("hh:mm")).format(((new SimpleDateFormat("hh:mm")).parse("13:09")));
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Date getFirstDayOfWeek(Date date)
	{
		Calendar cal =Calendar.getInstance();
		if(!Utility.isObjectEmpty(date))
		{
			cal.setTime(date);
		}
		//cal.setFirstDayOfWeek(Calendar);

		return Dates.add(cal.getTime(), ((cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek())) * -1);
	}

	public static Date getFirstDayOfMonth(Date date)
	{
		Calendar cal =Calendar.getInstance();
		if(!Utility.isObjectEmpty(date))
		{
			cal.setTime(date);
		}
		//cal.setFirstDayOfWeek(Calendar);
		cal.set(Calendar.DATE, 1);

		return cal.getTime();
	}

	public static Date getFirstDayOfYear(Date date)
	{
		Calendar cal =Calendar.getInstance();
		if(!Utility.isObjectEmpty(date))
		{
			cal.setTime(date);
		}
		//cal.setFirstDayOfWeek(Calendar);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);

		return cal.getTime();
	}
	public static String getTimeString(String sdate) {
		String stime = "";
		int ibegin = 0;
		int iend = 0;

		sdate = sdate.trim();

		//strip out the date string
		if (sdate.indexOf(':') > 2) {
			ibegin = sdate.indexOf(':') - 2;
			stime = (sdate.substring(ibegin, sdate.length())).trim();
		}

		//strip out the milliseconds
		if (stime.lastIndexOf(':') > 5) {
			iend = stime.lastIndexOf(':');
			stime = (stime.substring(0, iend)).trim();
		}

		//just to be sure
		if (stime.length() > 8) {
			iend = 8;
			stime = (stime.substring(0, iend)).trim();
		}

		return stime;
	}

    public static String getNow(String time)
    {
        return getNow(time, null);
    }

	public static String getNow(String time, String timeZoneId)
	{
	    String ret = null;
	    if(!Utility.isEmpty(time))
	    {
	        ret = Dates.getTimeString(time);
	    }
	    else
	    {
	        Date date = Dates.getCurrentDate(timeZoneId);

            ret = Dates.getTimeString(date.toString());
	    }
	    return ret;
	}

	public Time getTime(String sdate) {
		return Time.valueOf(getTimeString(sdate));
	}

	//Converts a String Date in the MM-dd-yyyy format
	//to the yyyy/mm/dd Format.
	public String reformatDate(String sdate) {
		/*
		String result = "";
		int ilength = sdate.length();
		String year = sdate.substring(ilength - 4, ilength);
		int islash = sdate.indexOf("/");
		String month = sdate.substring(0,islash);
		int islash2 = sdate.indexOf("/",islash+1);
		String day = sdate.substring(islash+1,islash2);
		result = year + "/" + month + "/" + day;
		return result;
		*/

		String result = "";

		DateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");

		result = dateFormatter.format(new Date(sdate));

		return result;
	}

	/**
	 *  Method ofOffsetDays
	 *      Determine the number of days to add to a passed in number
	 *          of offset days, based on weekends/closed days
	 *  @param int offsetDays - Offset Days
	 *  @param boolean bschedule - true if "Calendar Management", "Schedule", = Y in the ini file
	 *  @return int
	 *      - number of offset days
	 */
	public int getOffsetDays(int offsetDays, boolean bschedule) {
		Date ddate = new Date();

		for (int i = 1; i <= offsetDays; i++) {
			ddate = add(ddate, 1);

			if (isWeekend(ddate)) {
				offsetDays++;
			}
			else if (bschedule) {
				//                if (isClosedDate(ddate))
				//                {
				//                    offsetDays++;
				//                }
			}
		}

		return offsetDays;
	}

	/**
	 *  Method isWeekend
	 *      Determines if the Date passed in is a Saturday or Sunday
	 *  @param Date - Date to be tested
	 *  @return boolean
	 *       true - Date is a weekend
	 *      false - Date is NOT a weekend
	 */
	private boolean isWeekend(Date ddate) {
		final String METHOD_NAME = "isWeekend ";

		if (ddate == null) {
			return false;
		}

		// Check for a Sunday or Saturday
		if (ddate.getDay() == 0 || ddate.getDay() == 6) {
			// Yes, it is a WeekEnd
			return true;
		}

		// No, it must not be a weekend
		return false;
	}

	/**
	 *  Method findToken
	 *      Determines the token used in the date and replaces the token if found invalid
	 *  @param StringBuffer - holds date value to be checked for the token
	 *  @return String - token
	 */
	private String findToken(StringBuffer sbdate) {
		String token = "";
		String sdate = sbdate.toString();

		if (sdate.indexOf("/") > 0) {
			token = "/";
		}
		else if (sdate.indexOf("-") > 0) {
			token = "-";
		}
		else if (sdate.indexOf("\\") > 0) {
			token = "/";
			sdate.replace('\\', '/');
		} else if (sdate.indexOf(".") > 0) {
            token = ".";
        }
		else {
		}

		sbdate.setLength(0);
		sbdate.append(sdate);

		return token;
	}

	public static java.sql.Date getSqlDate(String sDate) {
    	if(sDate == null) {
        	return null;
    	} else {
			sDate = sDate.replaceAll("-", "/");
    		java.util.Date date;

    		if(sDate.length() < 1)
    		{
    			date = new Date();
    		}
    		else
    		{
    			date = new java.util.Date(sDate) ;
    		}


        	return new java.sql.Date(date.getTime());
    	}
	}

	public static java.sql.Date getSqlDate(String sDate, String format)
	{
		java.sql.Date sqlDate = null;

		if(sDate == null) {
	       	return null;
	   	} else {
             if (!Utility.isEmpty(format) && format.length() == 10 || format.length() == 8) {
                 format = format.replaceAll("Y", "y");
                 format = format.replaceAll("D", "d");
             }

			sDate = sDate.replaceAll("-", "/");
            sDate = sDate.replaceAll("\\.", "/");
			format = format.replaceAll("-", "/");
            format = format.replaceAll("\\.", "/");

			if (sDate.length() < format.length())
				format = format.replaceAll("yyyy", "yy");

			java.util.Date date;

	   		if(sDate.length() < 1)
	    	{
	    		date = new Date();
	    	}
	    	else
	    	{
	    		String var = "";
	    		String nDate = "";
	    		if (sDate.indexOf(" ") > 0) {
	    			// if timestamp remove time
	    			sDate = sDate.substring(0, sDate.indexOf(" ")) ;
	    		}
	    		StringTokenizer st = new StringTokenizer(sDate, "/");
		    		while (st.hasMoreTokens()){
	    			var = st.nextToken();
	    			DecimalFormat df = new DecimalFormat("00");
	    			var = df.format(Long.parseLong(var));
	    			nDate = nDate + var;
	    			if (st.hasMoreTokens()){
	    				nDate = nDate + "/";
	    			}
	    		}
	    		DateFormat dateFormatter = new SimpleDateFormat(format);
				dateFormatter.setLenient(false);
				try	{
					date = dateFormatter.parse(nDate);
					sqlDate = new java.sql.Date(date.getTime());
				}catch(Exception e){
					// Add msg here
					e.printStackTrace() ;
				}
			}
	   		return sqlDate;
	    }
	}

	public static java.sql.Date getSqlDate(java.util.Date date) {
		if(date == null) {
			return null;
		} else {
			return new java.sql.Date(date.getTime());
		}
	}

	public static int getDaysAfter(String beginDate, String endDate, int stopDays) throws Exception {
		int daysAfter = 0;

		try  {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			try {
				if (Utility.isEmpty(beginDate)) {
					beginDate = formatter.format(new Date());
				} else {
					beginDate = beginDate.replace('/', '-');
					beginDate = formatter.format(formatter.parse(beginDate));
				}

				if (Utility.isEmpty(endDate)) {
					endDate = formatter.format(new Date());
				} else {
					endDate = endDate.replace('/', '-');
					endDate = formatter.format(formatter.parse(endDate));
				}
			}
			catch(ParseException pe)
			{
				pe.printStackTrace();
				throw pe;
			}

			int	yearBegin = Integer.valueOf(beginDate.substring(0,4)).intValue();
			int	monthBegin = Integer.valueOf(beginDate.substring(5,7)).intValue();
			int	dayBegin = Integer.valueOf(beginDate.substring(8,10)).intValue();
			int	yearEnd = Integer.valueOf(endDate.substring(0,4)).intValue();
			int	monthEnd = Integer.valueOf(endDate.substring(5,7)).intValue();
			int	dayEnd = Integer.valueOf(endDate.substring(8,10)).intValue();

			Calendar begin = Calendar.getInstance();
			Calendar end = Calendar.getInstance();

			begin.set(yearBegin, monthBegin, dayBegin);
			end.set(yearEnd, monthEnd, dayEnd);

			while (begin.before(end) && ((stopDays == 0) || (daysAfter < stopDays)))
			{
				begin.add(Calendar.DAY_OF_MONTH, 1);
				daysAfter++;
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		return daysAfter;
	}

	//returns a new date given a long date time and the number of minutes to add or subtract from
	public static Date addMinutes(Date d_date, int minutes) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(d_date);

		cal.add(Calendar.MINUTE, minutes);

		Date newDate = (Date) cal.getTime();

		return newDate;
	}

	public static Date addMonths(Date d_date, int months) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(d_date);

		cal.add(Calendar.MONTH, months);

		Date newDate = (Date) cal.getTime();

		return newDate;
	}

	//returns a new date given a long date time and the number of seconds to add or subtract from
	public static Date addSeconds(Date d_date, int seconds) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(d_date);

		cal.add(Calendar.SECOND, seconds);

		Date newDate = (Date) cal.getTime();

		return newDate;
	}

    public static String getUTCTimeZone(String timeZoneId) {
        String offset = "";
        try {
            TimeZone tz = TimeZone.getDefault();
            if (!Utility.isEmpty(timeZoneId)) {
                tz = TimeZone.getTimeZone(timeZoneId);
            }

            int rawOffset = tz.getRawOffset();
            int hour = rawOffset / (60*60*1000);
            int min = Math.abs(rawOffset / (60*1000)) % 60;
            String smin = String.valueOf(min);
            String shour = String.valueOf(hour);
            if (hour == 0) {
                shour = " " + shour;
            } else if (hour > 0) {
                shour = "+" + shour;
            }
            if (smin.length() < 2) {
                smin = smin + "0";
            }
            offset = "UTC " + shour + ":" + smin;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return offset;
    }

    public static String getTimeZoneAbbreviation(String timeZoneId) {
    	if (Utility.isEmpty(timeZoneId)) {
    		timeZoneId = TimeZone.getDefault().getID();
    	}
        TimeZone tz = TimeZone.getTimeZone(timeZoneId);
        String abbreviation = tz.getDisplayName(false, TimeZone.SHORT);

        return abbreviation;
    }

    public static String getTimeZoneAbbreviation(String timeZoneId, String sdate) {
        Date date = Dates.getDate(sdate);
        return Dates.getTimeZoneAbbreviation(timeZoneId, date);
    }

    public static String getTimeZoneAbbreviation(String timeZoneId, Date date) {
    	if (Utility.isEmpty(timeZoneId)) {
    		timeZoneId = TimeZone.getDefault().getID();
    	}
    	TimeZone tz = TimeZone.getTimeZone(timeZoneId);
        String abbreviation = tz.getDisplayName(tz.inDaylightTime(date), TimeZone.SHORT);

        return abbreviation;
    }
}