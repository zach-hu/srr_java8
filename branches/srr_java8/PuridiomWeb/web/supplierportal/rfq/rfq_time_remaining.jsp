<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.TimeZone" %>
<%
	String	s_due = HiltonUtility.getFullDateTimeString(rfqHeader.getDueDate(), rfqHeader.getBidDueTime(), rfqHeader.getTimeZone(), user.getOrganizationId());
	String	s_due_date = HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), user.getOrganizationId(), "MM-dd-yyyy");
	String	s_today = new String();
	String	s_validto = HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), oid,"yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	DateFormat	dateFormatter = new SimpleDateFormat("MM-dd-yyyy  HH:mm:ss");
	SimpleDateFormat sdf_format = new SimpleDateFormat (propertiesManager.getProperty("MISC", "DateFormat", "yyyy-MM-dd"));
	TimeZone	zone = TimeZone.getTimeZone(rfqHeader.getTimeZone());
	int	i_year = 0;
	int	i_month = 0;
	int	i_day = 0;

	dateFormatter.setTimeZone(zone);

	s_today = dateFormatter.format(new Date());

	if (s_validto.length() > 0) {
		String s_default_days = propertiesManager.getProperty("BIDBOARD OPTIONS", "DEFAULTBIDVALIDTO", "30");
		if (HiltonUtility.isEmpty(s_default_days)) {
			s_default_days = "30";
		}

		i_year	= Integer.valueOf(s_validto.substring(0,4)).intValue();
		i_month = Integer.valueOf(s_validto.substring(5,7)).intValue();
		i_day	= Integer.valueOf(s_validto.substring(8,10)).intValue();

		cal.set(i_year, i_month, i_day);
		cal.add(cal.DAY_OF_MONTH, + Integer.valueOf(s_default_days).intValue());
		s_validto = sdf_format.format(cal.getTime());
	}
%>
<input type=text name="time" size=20 value=" " onFocus="this.blur();" style="border:0; color:red; text-align:right;" class=formType>
<tsa:hidden name="due_date" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), user.getOrganizationId())%>"/>
<tsa:hidden name="due_time" value="<%=rfqHeader.getBidDueTime()%>"/>
<tsa:hidden name="time2" value="<%=s_due_date%>"/>
<tsa:hidden name="today" value="<%=s_today%>"/>
<tsa:hidden name="required_date" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), user.getOrganizationId())%>"/>
<tsa:hidden name="default_validto" value="<%=s_validto%>"/>
<tsa:hidden name="RfqHeader_bidDueTime" value="<%=rfqHeader.getBidDueTime()%>"/>