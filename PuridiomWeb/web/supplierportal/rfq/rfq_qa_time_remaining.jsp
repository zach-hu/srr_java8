<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.TimeZone" %>
<%
	String	s_today = new String();
	String	s_validto = HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), oid,"yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	DateFormat	dateFormatter = new SimpleDateFormat("MM-dd-yyyy  HH:mm:ss");
	SimpleDateFormat sdf_format = new SimpleDateFormat (propertiesManager.getProperty("MISC", "DateFormat", "yyyy-MM-dd"));
	TimeZone	zone = TimeZone.getTimeZone(rfqHeader.getTimeZone());

	dateFormatter.setTimeZone(zone);

	s_today = dateFormatter.format(new Date());
%>
<input type=text name="time" size=20 value=" " onFocus="this.blur();" style="border:0; color:red; text-align:right;" class=formType>
<tsa:hidden name="due_date" value="<%=dueDate%>"/>
<tsa:hidden name="due_time" value="<%=dueTime%>"/>
<tsa:hidden name="time2" value="<%=dueDate%>"/>
<tsa:hidden name="today" value="<%=s_today%>"/>
<tsa:hidden name="required_date" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), user.getOrganizationId())%>"/>
<tsa:hidden name="default_validto" value="<%=s_validto%>"/>