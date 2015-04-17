<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.text.*" %>
<%
	PropertiesManager pm = PropertiesManager.getInstance(user.getOrganizationId());
	boolean	rfqsActive = pm.isModuleActive("REQUEST FOR QUOTES");
	boolean	extRfqsActive = pm.isModuleActive("EXTENDED RFQS");
	boolean	posActive = pm.isModuleActive("PURCHASE ORDERS");
	boolean	vouchersActive = pm.isModuleActive("VOUCHERS");
	boolean	salesActive = pm.isModuleActive("SALES");
%>
<SCRIPT language='Javascript1.2' type="text/javascript">
<!--
	var rfqsActive = <%=rfqsActive%>;
	var extRfqsActive = <%=extRfqsActive%>;
	var posActive = <%=posActive%>;
	var vouchersActive = <%=vouchersActive%>;
	var salesActive = <%=salesActive%>;
//-->
</SCRIPT>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td><a href="http://www.puridiom.com" target="_top"><img src="<%=contextPath%>/supplierportal/images/gcsclock.gif"	align="bottom" alt="Purchasing Center Home" border=0></a></td>
	<td valign=bottom>
		<table border=0 cellpadding=0 cellspacing=0 width=430px>
			<tr class=mrow>
				<th height=16px>&nbsp;</th>
	<%	if (user.isAuthenticated()) {%>
				<th align=center width=40px height=16px nowrap><a href="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);">Menu</a></th>
				<th align=center valign=middle width=1px height=16px class=separator>|</th>
				<!--th align=center width=40px height=16px nowrap><a href="javascript: helpMe(); void(0);">Help</a>&nbsp;</th-->
				<!--th align=center valign=middle width=1px height=16px class=separator>|</th-->
				<th align=center width=60px height=16px nowrap><a href="javascript: logOff(); void(0);">Log&nbsp;Off</a>&nbsp;</th>
	<%	}%>
			</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr><td class=mnav><img src="<%=contextPath%>/supplierportal/images/none.gif" border=0 height=1px width=100% class=mnav></td></tr>
</table>

<%	if (user.isAuthenticated()) {%>
<table border=0 cellspacing=0 cellpadding=0 width=680px height=22px>
	<tr class=mnav>
	<%	if (salesActive) {%>
		<td nowrap align=center width=120px><a href="javascript: searchSales(); void(0);" class=mnav>Surplus Items</a></td>
	<%	}
		if (rfqsActive || extRfqsActive) {%>
		<td nowrap align=center width=120px><a href="javascript: doSubmit('/browse/rfq_browse_filter_options.jsp', 'DoNothing'); void(0);" class=mnav>Solicitations</a></td>
	<%	}
		if (user.isQualified()) {
			if (posActive) {%>
		<td nowrap align=center width=70px><a href="javascript: doSubmit('/browse/po_browse_filter_options.jsp', 'DoNothing'); void(0);" class=mnav>Orders</a></td>
		<%	}
			if (vouchersActive) {%>
		<td nowrap align=center width=90px><a href="javascript: searchInvoices(); void(0);" class=mnav>Invoices</a></td>
<%			}%>
		<td nowrap align=center width=140px><a href="javascript: doSubmit('/user/prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve'); void(0);" class=mnav>Profile Information</a></td>
<%	} else if (!user.isGuest()) {%>
		<td nowrap align=center width=150px><a href="javascript: doSubmit('/user/prequalification.jsp', 'VendorRegisterRetrieveByEmail;VendorOptionsRetrieve'); void(0);" class=mnav>Pre-Qualification Process</a></td>
<%	}
		if (!user.isGuest()) {%>
		<td nowrap align=center width=140px><a href="javascript: doSubmit('/documents/std_documents_menu.jsp', 'DoNothing'); void(0);" class=mnav>Download Documents</a></td>
	<%	}%>
	</tr>
</table>

<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr valign=top>
	<%	if (!HiltonUtility.isEmpty(user.getDisplayName())) {%>
	<td nowrap align=left><font color=black size=1><b>&nbsp;Hello <%=user.getDisplayName()%>!<br></b></font></td>
	<%	}%>
	<td nowrap align=right><font SIZE=-2><i><%=HiltonUtility.getFormattedDate(new Date(), oid, "EEE, MMM d, yyyy hh:mm:ss z")%></i></font></td>
</tr>
</table>

<hr size=0 color=black width=680px align=left>
<%	} else {%>
<br>
<%	}%>

<!--
Guest - <%=user.isGuest()%><br>
Qualified - <%=user.isQualified()%><br>
Authenticated - <%=user.isAuthenticated()%>
-->