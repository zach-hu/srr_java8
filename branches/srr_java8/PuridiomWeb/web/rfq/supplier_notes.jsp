<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%
	RfqNote rfqNote = (RfqNote) request.getAttribute("rfqNote");
	String	currentVendorId = (String) request.getAttribute("RfqNote_vendorId");
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
		pk.setVendorId(currentVendorId);
		rfqNote.setComp_id(pk);
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=hic%>"/>

<table width=100% cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=s_title%></div>
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
	<td width=100px align=right class=label valign=top>Description:</td>
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
				<textarea name="RfqNote_notesText" wrap="virtual" rows=8 cols=55>${esapi:encodeForHTML(rfqNote.notesText)}</textarea>
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
			<td align=center width=50%><A HREF="javascript: saveNotes(); void(0);"><img src="<%=contextPath%>/images/button_save.gif" class=button border=0></a></td>
			<%	if (!HiltonUtility.isEmpty(returnPage)) {%>
			<td align=center width=50%><a href="javascript: returnMe(); void(0);"><img src="<%=contextPath%>/images/button_return.gif" class=button border=0></a></td>
			<%	} else {%>
			<td align=center width=50%><a href="javascript: window.top.hidePopWin(); void(0);"><img src="<%=contextPath%>/images/button_close.gif" class=button border=0></a></td>
			<%	}
				} else {
					if (!HiltonUtility.isEmpty(returnPage)) {%>
			<td align=center width=50%><a href="javascript: returnMe(); void(0);"><img src="<%=contextPath%>/images/button_return.gif" class=button border=0></a></td>
			<%	} else {%>								
			<td align=center><a href="javascript: window.top.hidePopWin(); void(0);"><img src="<%=contextPath%>/images/button_close.gif" class=button border=0></a></td>
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
	if ( window.parent.frm.rfqItemNumber[(<%=s_ln%> - 1)] ) {
		itemNumber = window.parent.frm.rfqItemNumber[(<%=s_ln%> - 1)].value;
	} else if (window.parent.frm.rfqItemNumber) {
		itemNumber = window.parent.frm.rfqItemNumber.value;
	}
	if ( window.parent.frm.rfqItemNumber[(<%=s_ln%> - 1)] ) {
		itemDescription = window.parent.frm.rfqItemDescription[(<%=s_ln%> - 1)].value;
	} else if (window.parent.frm.rfqItemNumber) {
		itemDescription = window.parent.frm.rfqItemDescription.value;
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
<%	} else {%>
	frm.RfqNote_notesText.disabled = true;
<%	}%>

	function saveNotes() {
		if (!isEmpty(returnPage) && !isEmpty(returnHandler)) {
			doSubmit(returnPage, "RfqNoteUpdateById;" + returnHandler);
		} else {
			if (window.parent.frm.as_info_changed) {
				window.parent.frm.as_info_changed.value = "Y";
			}
			doSubmit("/system/close_window.jsp", "RfqNoteUpdateById");
		}
	}
	
	function returnMe() {
		if (!isEmpty(returnPage) && !isEmpty(returnHandler)) {
			doSubmit(returnPage, returnHandler);
		} else {
			window.top.hidePopWin();
		}
	}

// end hiding contents -->
</SCRIPT>

</body>
</html>