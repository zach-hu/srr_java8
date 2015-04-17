<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>

<%
	XrefCombo	xrefCombo			= (XrefCombo) request.getAttribute("xrefCombo");
	String		XrefCombo_icXref	= (String)request.getAttribute("XrefCombo_icXref");
	String		XrefCombo_xrefType	= (String)request.getAttribute("XrefCombo_xrefType");

	PropertiesManager	propertiesManager 	= PropertiesManager.getInstance(oid);

	String action 				= "new";

	String s_quantity_decimals		= propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String s_current_process 		= "XREFCOMBOREVIEWBY";
	String s_current_page 			= "/admin/auto_accounting/autoaccounting_review.jsp";
	String s_current_method 		= "DoNothing";
	String s_current_process_method = "";
	String desc_XrefCombo_xrefType	= "";

	if(xrefCombo != null) {
		action = "old";
	}
	else {
		xrefCombo = new XrefCombo();
	}

	if (XrefCombo_xrefType != null) {
		if (XrefCombo_xrefType.equalsIgnoreCase("CPTL")) { desc_XrefCombo_xrefType = "Capital Clearing Account"; }
		if (XrefCombo_xrefType.equalsIgnoreCase("CARP")) { desc_XrefCombo_xrefType = "CAR / Project Combinations"; }
		if (XrefCombo_xrefType.equalsIgnoreCase("ENDP")) { desc_XrefCombo_xrefType = "Entity / Department Combinations"; }
		if (XrefCombo_xrefType.equalsIgnoreCase("DIVS")) { desc_XrefCombo_xrefType = "Division / Specific Combinations"; }
		if (XrefCombo_xrefType.equalsIgnoreCase("CMOD")) { desc_XrefCombo_xrefType = "Commodity Account Combinations"; }
	}

%>

<tsa:hidden name="XrefCombo_icXref" value="<%=XrefCombo_icXref%>"/>
<tsa:hidden name="XrefCombo_xrefType" value="<%=XrefCombo_xrefType%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr><td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td></tr>
		<tr><td nowrap class=hdr12 valign=middle><div style="margin-left:10px; margin-right:10px" class=hdr12><%=desc_XrefCombo_xrefType%></div></td></tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="100%" /></td>

	<td valign=bottom align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "id", "ID")%> #:</b></td>
			<td width=250px><%=XrefCombo_icXref%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0>
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

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td width="680px" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="680px">
    <tr>
      <td width="680px" align="center" valign="top">
      	<%@ include file="/admin/auto_accounting/autoaccounting_data_review.jsp" %>
      </td>

	</tr>
	</table>
  </td>
</tr>
<tr><td height=50px>&nbsp;</td></tr>
<tr>
	<td align="center">
		<table width=680px cellpadding=0 cellspacing=0 border=0>
			<tr>
				<td align="center">
				  <% if (action.equalsIgnoreCase("new")) { %>
					<a href="javascript: addXrefCombo(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Add"></a>
				  <% } else if (action.equalsIgnoreCase("old")) { %>
					<a href="javascript: updateXrefCombo(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Update"></a>
				  <% } %>
				</td>
				<td align="center">
					<a href="javascript: returnXrefComboBrowse('<%=XrefCombo_xrefType%>'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a>
				</td>
			</tr>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	setNavCookie('/admin/auto_accounting/autoaccounting_review.jsp', 'DoNothing', 'Auto Accounting Data');

	function addXrefCombo() {
		doSubmit('/admin/auto_accounting/autoaccounting_review.jsp','XrefComboAdd;XrefComboRetrieveById');
	}

	function updateXrefCombo() {
		doSubmit('/admin/auto_accounting/autoaccounting_review.jsp','XrefComboUpdate;XrefComboRetrieveById');
	}

	function returnXrefComboBrowse(type) {
		if (type == 'CPTL') {
			frm.browseName.value = 'autoacc_cptclracc';
		}
		else if (type == 'CARP') {
			frm.browseName.value = 'autoacc_carprjcom';
		}
		else if (type == 'ENDP') {
			frm.browseName.value = 'autoacc_entdepcom';
		}
		else if (type == 'DIVS') {
			frm.browseName.value = 'autoacc_divspccom';
		}
		else if (type == 'CMOD') {
			frm.browseName.value = 'autoacc_cmmacccom';
		}
		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	 function valuesCheck(field,type) {
	 	var alertMessage = true;
	 	if (field.value.indexOf("*")>=0) {
		 	if (type=="CPTL" && field.name=="XrefCombo_code1")	alertMessage = false;
		 	if (type=="DIVS"
		 		&& (field.name=="XrefCombo_code3" || field.name=="XrefCombo_code4"
		 		|| field.name=="XrefCombo_code5" || field.name=="XrefCombo_code6"
		 		|| field.name=="XrefCombo_code7"))				alertMessage = false;
		}
		else
			alertMessage = false;
	 	if (alertMessage) {
	 		alert("Field can't contain an asterisk (*)!");
	 		field.value = removeAsterisks(field.value);
	 	}
	 }

	 function removeAsterisks(fieldValue) {
	 	while (fieldValue.indexOf("*")>=0) {
	 		fieldValue = fieldValue.substring(0, fieldValue.indexOf("*"))+fieldValue.substring(fieldValue.indexOf("*")+1);
	 	}
		return fieldValue;
	 }
//-->
</script>