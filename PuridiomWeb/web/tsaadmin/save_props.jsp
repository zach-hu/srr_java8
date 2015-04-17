<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.util.*" %>
<body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0" width="680px">
<form name="purchaseform" target="_parent" action="<%=contextPath%>/tsaadmin/edit_properties.jsp" method="POST">
<%ReadProperties readProperties = new ReadProperties();
String organizationId = (String)request.getParameter("oid");
String fileName = (String)request.getParameter("propsfilename");
String propsAction = (String)request.getParameter("propsAction");
if(propsAction == null){	propsAction = "read";	}
if(propsAction.equalsIgnoreCase("save"))
{
	readProperties.saveFromRequest(request);
}
else
{
	readProperties.readProperties(organizationId, fileName);
	Properties props = readProperties.getPropertiesFile();
	Enumeration enu = props.propertyNames();
}
%>
<tsa:hidden name="oid" value="<%=organizationId%>"/>
<tsa:hidden name="propsFileName" value="<%=fileName%>"/>
<tsa:hidden name="propsAction" value="<%=propsAction%>"/>

<table border="0" cellpadding="3" cellspacing="0" width="680px">
<tr>
	<td align=center>
		<table border=0 cellpadding=3 cellspacing=0>
			<%while (enu.hasMoreElements())
			{%>
				<tr>
				    <%String key = (String) enu.nextElement();
				    String value = props.getProperty(key);%>
				    <td><%=key%></td>
				    <td><input type="text" name="<%=key%>" value="<%=value%>"></td>
			    </tr>
			<%}%>
		</table>
	</td>
</tr>
</table>
<br><br>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
	<tr>
		<td align="center"><a href="javascript: doSubmit('/tsaadmin/save_props.jsp'); void(0);"><img src="<%=contextPath%>/images/button_save.gif" border=0 class=button alt="Save Properties List"></a></td>
		<td align="center"><a href="javascript: doSubmit('/tsaadmin/displayprops.jsp'); void(0);"><img src="<%=contextPath%>/images/button_return.gif" border=0 class=button alt="Return to Properties List"></a></td>
	</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;

	document.title = "TSA Admin Menu";

	 function refreshProperties() {
		frm.organizationId.value = frm.as_property_oid.value;
		doSubmit('tsaadmin/reload_properties.jsp', 'DoNothing');
	}

	function doSubmit(page)
	{
		frm.action = page;

	}

//-->
</script>