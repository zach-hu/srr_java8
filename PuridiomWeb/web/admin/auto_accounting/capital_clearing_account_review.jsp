<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/auto_accounting.js"></SCRIPT>

<%
	PropertiesManager	propertiesManager	= PropertiesManager.getInstance(oid);
	CapitalClearingAccount	capitalClearingAccount = (CapitalClearingAccount) request.getAttribute("capitalClearingAccount");

	String	CapitalClearingAccount_values[]	= new String[3];
	for (int x=0; x<3; x++) {
		CapitalClearingAccount_values[x] = "";
	}

	String action = "new";

	if (capitalClearingAccount!=null) {
		action = "old";

		CapitalClearingAccount_values[0]	= capitalClearingAccount.getEntity();
		CapitalClearingAccount_values[1]	= capitalClearingAccount.getDepartment();
		CapitalClearingAccount_values[2]	= capitalClearingAccount.getAccount();
	} else {
		capitalClearingAccount = new CapitalClearingAccount();
	}

%>

<tsa:hidden name="allowBrowse" value="true"/>

<tsa:hidden name="CapitalClearingAccount_icHeader" value="<%=capitalClearingAccount.getIcHeader()%>"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<!--In this part we have a function to attach a document-->
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr>
			<td nowrap class=hdr12 valign=middle align="left"><div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "capitalclearingaccount", "Capital Clearing Account")%></div></td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>
	<td valign=bottom align=right>
		<table cellpadding="0" cellspacing="0" border=0>
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br/>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="600px">
    <tr>
      <td align="center" valign="top">
      	<%@ include file="/admin/auto_accounting/capital_clearing_account_data.jsp" %>
      </td>
	</tr>
	</table>
  </td>
</tr>
</table>

<br/>

<table border=0 cellspacing=0 cellpadding=2 width="680px">
<tr>
		<td align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=100%>
		<tr>
			<td align="center">
				<% if(action.equalsIgnoreCase("new")) {%>
				<a href="javascript: addCapitalClearingAccount(); void(0);">
				<img class=button src="<%=contextPath%>/images/button_save.gif" border=0>
				</a>
				<% } else if (action.equalsIgnoreCase("old")) {%>
				<a href="javascript: updateCapitalClearingAccount(); void(0);">
				<img class=button src="<%=contextPath%>/images/button_save.gif" border=0>
				</a>
				<% } %>
			</td>
			<td align="center">
				<!-- <a href="javascript: doSubmit('admin/budget/budget_menu.jsp','DoNothing'); void(0);"> -->
				<a href="javascript: browse('capital_clearing_account'); void(0);">
				<img class=button src="<%=contextPath%>/images/button_return.gif" border=0>
				</a>
			</td>
		</tr>
		</table>
		</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script value=JavaScript>
<!-- Hide script

var frm = document.purchaseform;

function addCapitalClearingAccount() {
	doSubmit('/admin/auto_accounting/capital_clearing_account_review.jsp', 'CapitalClearingAccountAdd;CapitalClearingAccountRetrieveById');
}

function updateCapitalClearingAccount() {
	doSubmit('/admin/auto_accounting/capital_clearing_account_review.jsp', 'CapitalClearingAccountUpdate;CapitalClearingAccountRetrieveById');
}


// End Hide script -->
</script>