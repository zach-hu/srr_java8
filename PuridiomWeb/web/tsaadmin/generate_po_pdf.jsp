<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<table border=0 cellpadding=3 cellspacing=0 width=680px>
<tr>
  <td align="right"><b>Organization Id:</b></td>
  <td><input type=text name="as_organizationId" value="" onchange="upperCase(this);"></td>
</tr>
<tr>
	<td align=right><b>IC_PO_HEADER for Order:</b></td>
	<td><input type=text name="PoHeader_icPoHeader" value=""></td>
</tr>
<tr><td colspan=2><br></td></tr>
<tr>
	<td colspan=2 align=center><a href="javascript: orderPreview(); void(0);"><img src="<%=contextPath%>/images/button_ok.gif" border=0 class=button></a></td>
</tr>
</table>

<br><br><br><br>

<%@ include file="/system/footer.jsp" %>

<script language='Javascript1.2' type="text/javascript">
<!--
	var frm = document.purchaseform;

	function orderPreview() {
		frm.organizationId.value = frm.as_organizationId.value;
		popupParameters = "PoHeader_icPoHeader=" + frm.PoHeader_icPoHeader.value;
		doSubmitToPopup('tsaadmin/po_pdf_preview.jsp', 'PoRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}
	
	function openWindow (url, w, h) {
		if (browser == "Netscape") {
			if (w == undefined) { w = 'WIDTH=500'; }
			if (h == undefined) { h = 'HEIGHT=300'; }
			if (theFocus == undefined) { theFocus = 'lookup'; }
		}
		else {
			if (w == null) { w = 'WIDTH=500'; }
			if (h == null) { h = 'HEIGHT=300'; }
			if (theFocus == null) { theFocus = 'lookup'; }
		}
		var winspecs = w +","+ h +",resizable=1,scrollbars=1,location=0,top=0,left=0";
	
		lookup_window = window.open("<%=contextPath%>/system/popup_html.jsp", "lookup_window", winspecs);
	
		if (theFocus == 'main') {
			self.focus();
		}
		else {
			lookup_window.focus();
		}
	
		if (lookup_window.opener == null) lookup_window.opener = window;
		self.name = "main";
	}
	
//-->
</script>