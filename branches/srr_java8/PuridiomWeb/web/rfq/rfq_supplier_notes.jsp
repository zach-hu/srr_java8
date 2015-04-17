<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	RfqNote rfqNote = (RfqNote) request.getAttribute("rfqNote");
	String	s_ln = (String) request.getAttribute("ln");
	String	hic = (String) request.getAttribute("RfqNote_icHeader");
	String	lic = (String) request.getAttribute("RfqNote_icLine");
	String	vendorId = (String) request.getAttribute("RfqNote_vendorId");
	String	s_title = "Solicitation Notes";
	String	s_edit = "";
	
	if (propertiesManager.getProperty("MODULES", "SUPPLIER PORTAL", "N").equalsIgnoreCase("Y")) {
		s_edit = "N"; 		//bidboard is active, do not allow edit
	} else {
		s_edit = "Y";		//bidboard is not active, allow user edit
	}

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
		pk.setVendorId(vendorId);
		rfqNote.setComp_id(pk);
	}
%>

<br>

<table cellpadding=0 cellspacing=0 border=0 align=center width=100%>
<tr>
	<td align=center valign=center>
		<table cellpadding=0 cellspacing=0 border=0 align=center width=200px>
		<tr>
			<td><b><%=VendorManager.getInstance().getVendorName(oid,vendorId)%>'s Notes for Line Item 1:</b></td>
		</tr>
		<tr>
			<td align=center>
				<textarea name="RfqNote_notesText" wrap="virtual" rows=8 cols=55 onFocus="checkBlur(this); void(0); "><%=HiltonUtility.ckNull(rfqNote.getNotesText())%></textarea>
				<tsa:hidden name="RfqNote_icHeader" value="<%=rfqNote.getComp_id().getIcHeader()%>"/>
				<tsa:hidden name="RfqNote_icLine" value="<%=rfqNote.getComp_id().getIcLine()%>"/>
				<tsa:hidden name="RfqNote_vendorId" value="<%=rfqNote.getComp_id().getVendorId()%>"/>
			</td>
		</tr>
		</table>
		<br>
		<br>
		<table cellpadding=0 cellspacing=0 border=0 align=center width=200px>
		<tr>
		<%	if (s_edit.equalsIgnoreCase("Y")) {%>
		  <td align=center><a href="javascript: saveNotes(); void(0);"><img src="<%=contextPath%>/images/button_save.gif" class="button" border=0 alt="Save"></a></td>
		  <td align=center><a href="javascript: closeThis(); void(0);"><img src="<%=contextPath%>/images/button_close.gif" class="button" border=0 alt="Close"></a></td>
		<%  } else {	%>	
		  <td align=center><a href="javascript: closeThis(); void(0);"><img src="<%=contextPath%>/images/button_close.gif" class="button" border=0 alt="Close"></a></td>	
		<%  }			%>
		</tr>
		</table>
	</td>
</tr>
</table>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	self.focus();

	frm = document.purchaseform;

<%	if (s_edit.equalsIgnoreCase("Y")) {%>
		function checkBlur(item) {
			return false;
		}
<%	} else {%>		
	  	function checkBlur(item) {
			item.blur();
			return true;
	  	}
<%	}%>

	function closeThis() {
		parent.close();
	}

	function saveNotes() {
		doSubmit("/system/close_window.jsp", "RfqNoteUpdateById");
	}

// end hiding contents -->
</SCRIPT>
</BODY>
</HTML>

<%@ include file="/system/prevent_caching.jsp" %>