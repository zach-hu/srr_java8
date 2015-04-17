<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.properties.*" %>

<%
String organizationId = (String)request.getParameter("oid");
String propsFileName = (String)request.getParameter("propsFileName");
String propsAction = (String)request.getParameter("propsAction");
if(propsAction == null){	propsAction = "read";	}
ReadProperties readProperties = new ReadProperties();

if(propsAction.equalsIgnoreCase("save"))
{
	readProperties.saveFromRequest(request);
}
else
{
	readProperties.readProperties(organizationId, propsFileName);
}
Properties props = readProperties.getPropertiesFile();
Enumeration enu = props.propertyNames();
%>
<tsa:hidden name="oid" value="<%=organizationId%>"/>
<tsa:hidden name="propsFileName" value="<%=propsFileName%>"/>
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
				    <%if(key.indexOf("visible") > 0)
				    {%>
				    		<td><tsa:hidden name="<%=key%>" value="<%=value%>"/>
				    				<input type="checkbox" name="<%=key%>_box" <%if(value.equalsIgnoreCase("visible")) {%> checked="checked" <%}%>>
				    		</td>
					<%}
				    else
				    {%>
				    	<td><input type="text" name="<%=key%>" value="<%=value%>"></td>
				    <%} %>
			    </tr>
			<%}%>
		</table>
	</td>
</tr>
</table>
<br><br>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
	<tr>
		<td align="center"><a href="javascript: saveProps(); void(0);"><img class="button" src="<%=contextPath%>/images/button_save.gif" border=0 class=button alt="Save Properties List"></a></td>
		<td align="center"><a href="<%=contextPath%>/"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 class=button alt="Return to Properties List"></a></td>
	</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	function saveProps()
	{
		frm.propsAction.value = "save";
		doSubmit('/tsaadmin/edit_properties.jsp', 'DoNothing');
	}

//-->
</script>