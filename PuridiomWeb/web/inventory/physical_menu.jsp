<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<%
String	s_resetMessage = "" ;
String	s_resetStatus = (String) request.getParameter("resetStatus") ;
if (s_resetStatus == null) {
} else if ( s_resetStatus.equalsIgnoreCase("Success")) {
		s_resetMessage = "Physical count initialization completed successfully!" ;
} else if (s_resetStatus.equalsIgnoreCase("Failed")){
	s_resetMessage = "Physical count intialization failed!" ;
}
%>

<tsa:hidden name="InvBinLocation_itemLocation" value="${InvBinLocation_itemLocation}"/>
<tsa:hidden name="resetStatus" value=""/>

<tsa:hidden name="process" value=""/>
<tsa:hidden name="itemAction" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>${esapi:encodeForHTML(InvBinLocation_itemLocation)} Physical Count Menu</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%		if (s_resetMessage.length() > 0) { %>
<table border=0 cellspacing=0 cellpadding=0 width=680px>
		<tr>
			<td align=center class=red><%=s_resetMessage%></td>
		</tr>
</table>
<br>
<% 		} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellspacing=0 cellpadding=0 width=680px align=center>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: physicalInit(); void(0);" class=formType>Initialize Physical Count Records</a></td>
			<td>Initialize inventory physical count process.</td>
		</tr>
		<tr>
			<td align=right height=32px width=10%>
				<img src="<%=contextPath%>/images/bullet.gif" border=0>&nbsp;</td>
			<td nowrap width=35% class=formType>
				<a href="javascript: doSubmit('/inventory/physical_count.jsp', 'InvItemRetrieveBinsByLocation'); void(0);" class=formType>Enter Physical Count Records</a></td>
			<td>Enter inventory physical counts for a location.</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	setNavCookie("/inventory/physical_menu.jsp", "DoNothing", "Physical Count Menu");

	frm = document.purchaseform;


	function physicalInit()
	{
	  if (confirm("Are you sure you want to initialize the physical count process?")) {
	  	doSubmit("/inventory/physical_count_reset.jsp","DoNothing") ;
	  }
    }


// End Hide script -->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>