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
	String s_current_process 		= "XREFCOMBO";
	String s_current_page 			= "/admin/xrefcombo.jsp";
	String s_current_method 		= "DoNothing";
	String s_current_process_method = "";
	String desc_XrefCombo_xrefType	= "";

	if (xrefCombo != null) {
		action = "old";
	} else {
		xrefCombo = new XrefCombo();
	}

	if (XrefCombo_xrefType != null) {
		if (XrefCombo_xrefType.equalsIgnoreCase("CARP")) { desc_XrefCombo_xrefType = xrefCombo.getCode1(); }
		if (XrefCombo_xrefType.equalsIgnoreCase("BINS")) { desc_XrefCombo_xrefType = "Bin Location"; }
	}
%>

<tsa:hidden name="XrefCombo_icXref" value="<%=XrefCombo_icXref%>"/>
<tsa:hidden name="XrefCombo_xrefType" value="<%=XrefCombo_xrefType%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/puridiom.js"></SCRIPT>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=150px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=desc_XrefCombo_xrefType%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "id", "ID")%> #:</b></td>
			<td width=250px><%=XrefCombo_icXref%></td>
		</tr>
		</table>
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
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="right">
	<%	if (action.equalsIgnoreCase("old")) { %>
		<a href="javascript: if (verifyAction('Delete this record?')) { deleteXrefCombo('<%=XrefCombo_xrefType%>'); void(0); }">
			<img src="<%=contextPath%>/images/delete.gif" alt="Delete" border="0">&nbsp;Delete Record
		</a>
	<%	} %>
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
      	<%@ include file="/admin/xrefcombo_data.jsp" %>
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
					<div id="pxbutton"><a href="javascript: addXrefCombo(); void(0);">Add</a></div>
				  <% } else if (action.equalsIgnoreCase("old")) { %>
					<div id="pxbutton"><a href="javascript: updateXrefCombo(); void(0);">Update</a></div>
				  <% } %>
				</td>
				<td align="center">
					<div id="pxbutton"><a href="javascript: returnXrefCombo('<%=XrefCombo_xrefType%>'); void(0);">Return</a></div>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;
	setNavCookie('/admin/xrefcombo.jsp', 'DoNothing', '<%=XrefCombo_xrefType%>');

	function addXrefCombo() {
		frm.XrefCombo_code1.value = trim(frm.XrefCombo_code1);
		if (frm.XrefCombo_code1 && frm.XrefCombo_code1.value == "") {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code1-"+XrefCombo_xrefType, "Code1")%> is required.");
			frm.XrefCombo_code1.focus();
		} else {
			doSubmit('/admin/xrefcombo.jsp','XrefComboAdd;XrefComboRetrieveById');
		}
	}

	function updateXrefCombo() {
		frm.XrefCombo_code1.value = trim(frm.XrefCombo_code1);
		if (frm.XrefCombo_code1 && frm.XrefCombo_code1.value == "") {
			alert("<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "xrefcombo-code1-"+XrefCombo_xrefType, "Code1")%> is required.");
			frm.XrefCombo_code1.focus();
		} else {
			doSubmit('/admin/xrefcombo.jsp','XrefComboUpdate;XrefComboRetrieveById');
		}
		//doSubmit('/admin/xrefcombo.jsp','XrefComboUpdate;XrefComboRetrieveById');
	}

	function returnXrefCombo(type) {
		setOriginalFilter("XrefCombo_xrefType", "=", type);
		if (type == 'CARP') {
			frm.browseName.value = 'xrefcombo-carp';
		}
		if (type == 'BINS') {
			frm.browseName.value = 'xrefcombo-bins';
		}
		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function deleteXrefCombo(type) {
		setOriginalFilter("XrefCombo_xrefType", "=", type);
		if (type == 'CARP') {
			frm.browseName.value = 'xrefcombo-carp';
		}
		if (type == 'BINS') {
			frm.browseName.value = 'xrefcombo-bins';
		}
		doSubmit('/browse/browse_sys_tables.jsp', 'XrefComboDelete;BrowseRetrieve');
	}

	function browseAccountFld(frmField, udf)
	{
		var browseName = "systable";

		if (udf == 'AC01') {
			var browseName = "<%=propertiesManager.getProperty("BROWSE", "Account_fld1", "systable")%>";
		} else if (udf == 'AC05') {
			var browseName = "<%=propertiesManager.getProperty("BROWSE", "Account_fld5", "systable")%>";
		}

		if (browseName == "systable") {
			browseStd(frmField, udf);
		} else {
			browseLookup(frmField, browseName);
		}
	}

//-->
</script>