<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%
	RfqNote rfqNote = (RfqNote) request.getAttribute("rfqNote");
	String	s_edit = (String) request.getAttribute("edit");
	String	s_ln = (String) request.getAttribute("ln");
	String	hic = (String) request.getAttribute("RfqNote_icHeader");
	String	lic = (String) request.getAttribute("RfqNote_icLine");
	String	returnPage = HiltonUtility.ckNull((String) request.getAttribute("returnPage"));
	String	returnHandler = HiltonUtility.ckNull((String) request.getAttribute("returnHandler"));
	String	s_title = "Bid Response Notes";

	s_edit = HiltonUtility.ckNull(s_edit);
	s_ln = HiltonUtility.ckNull(s_ln);
	if (!HiltonUtility.isEmpty(s_ln)) {
		s_title = s_title + " for line " + s_ln;
	}
	if (HiltonUtility.isEmpty(lic)) {
		lic = "0";
	}
	
	if (rfqNote == null) {
		rfqNote = new RfqNote();
		RfqNotePK pk = new RfqNotePK();
		pk.setIcHeader(new BigDecimal(hic));
		pk.setIcLine(new BigDecimal(lic));
		pk.setVendorId(user.getVendorId());
		rfqNote.setComp_id(pk);
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=hic%>"/>

<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
			</td>
		</tr>
		</table>
	</td>
	<td width=30px valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" width="30" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table cellpadding="0" cellspacing="0" border=0>
		<tr>
			<td width=1000px height=1px class=lightShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<div id="itemNumberInfo" style="display:none; visibility:hidden; align:center;">
<table cellpadding=2 cellspacing=0 border=0 align=center width=100%>
<tr>
	<td width=100px align=right class=label>Item #:</td>
	<td id="itemNumberDisplay"></td>
</tr>
</table>
</div>
<div id="itemDescriptionInfo" style="display:none; visibility:hidden; align:center;">
<table cellpadding=2 cellspacing=0 border=0 align=center width=100%>
<tr>
	<td width=100px align=right class=label>Description:</td>
	<td id="itemDescriptionDisplay"></td>
</tr>
</table>
</div>

<br>

<table cellpadding=0 cellspacing=0 border=0 align=center width=100%>
<tr>
	<td align=center valign=center>
		<table cellpadding=0 cellspacing=0 border=0 align=center width=400px>
		<tr>
			<td align=center>
				<textarea name="RfqNote_notesText" wrap="virtual" rows=8 cols=55 onFocus="if (checkBlur()) { this.blur(); return false; }">${esapi:encodeForHTML(rfqNote.notesText)}</textarea>
				<tsa:hidden name="RfqNote_icHeader" value="<%=rfqNote.getComp_id().getIcHeader()%>"/>
				<tsa:hidden name="RfqNote_icLine" value="<%=rfqNote.getComp_id().getIcLine()%>"/>
				<tsa:hidden name="RfqNote_vendorId" value="<%=rfqNote.getComp_id().getVendorId()%>"/>
			</td>
		</tr>
		</table>
		<br>
		<br>
		<table cellpadding=0 cellspacing=0 border=0 align=center width=400px>
		<tr>
		<%	if (s_edit.equals("Y")) {%>
			<td align=center width=50%><A HREF="javascript: saveNotes(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_save.gif" class=button border=0></a></td>
			<%	if (!HiltonUtility.isEmpty(returnPage)) {%>
			<td align=center width=50%><a href="javascript: returnMe(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_return.gif" class=button border=0></a></td>
			<%	} else {%>
			<td align=center width=50%><a href="javascript: closeThis(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_close.gif" class=button border=0></a></td>
			<%	}
				} else {
					if (!HiltonUtility.isEmpty(returnPage)) {%>
			<td align=center width=50%><a href="javascript: returnMe(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_return.gif" class=button border=0></a></td>
			<%	} else {%>								
			<td align=center><a href="javascript: closeThis(); void(0);"><img src="<%=contextPath%>/supplierportal/images/button_close.gif" class=button border=0></a></td>
		<%		}
				}%>
		</tr>
		</table>
	</td>
</tr>
</table>

</form>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	self.focus();

	frm = document.purchaseform;
	
	var itemNumber = "";
	var itemDescription = "";
	var returnPage = "<%=returnPage%>";
	var returnHandler = "<%=returnHandler%>";

<%	if (!HiltonUtility.isEmpty(s_ln)) {%>
	if ( opener.frm.rfqItemNumber[(<%=s_ln%> - 1)] ) {
		itemNumber = opener.frm.rfqItemNumber[(<%=s_ln%> - 1)].value;
	} else if (opener.frm.rfqItemNumber) {
		itemNumber = opener.frm.rfqItemNumber.value;
	}
	if ( opener.frm.rfqItemNumber[(<%=s_ln%> - 1)] ) {
		itemDescription = opener.frm.rfqItemDescription[(<%=s_ln%> - 1)].value;
	} else if (opener.frm.rfqItemNumber) {
		itemDescription = opener.frm.rfqItemDescription.value;
	}

	if (!isEmpty(itemNumber)) {
		document.getElementById("itemNumberInfo").style.visibility = "visible";
		document.getElementById("itemNumberInfo").style.display = "block";
		document.getElementById("itemNumberDisplay").innerText = itemNumber;
	}
	if (!isEmpty(itemDescription)) {
		document.getElementById("itemDescriptionInfo").style.visibility = "visible";
		document.getElementById("itemDescriptionInfo").style.display = "block";
		document.getElementById("itemDescriptionDisplay").innerText = itemDescription;
	}
<%	}%>
<%	if (s_edit.equalsIgnoreCase("Y")) {%>
	frm.RfqNote_notesText.focus();

	function checkBlur() {
		return false;
	}
<%	} else {%>
	function checkBlur() {
		return true;
	}
<%	}%>

	function closeThis() {
		parent.close();
	}

	function saveNotes() {
		if (!isEmpty(returnPage) && !isEmpty(returnHandler)) {
			doSubmit(returnPage, "RfqNoteUpdateById;" + returnHandler);
		} else {
			if (opener.frm.as_info_changed) {
				opener.frm.as_info_changed.value = "Y";
			}
			doSubmit("/system/close_window.jsp", "RfqNoteUpdateById");
		}
	}
	
	function returnMe() {
		if (!isEmpty(returnPage) && !isEmpty(returnHandler)) {
			doSubmit(returnPage, returnHandler);
		} else {
			closeThis();
		}
	}

// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>