<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.foundation.utility.Dates"%>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_showauto = "Y";
	String	s_autonumber	 = "N";
	String	s_number_label	 = "";
	String	s_current_number = "";
	String	s_action	 = (String) request.getAttribute("action");
	String	s_formtype = (String) request.getAttribute("formtype");
	String	s_formnumber = (String) request.getAttribute("formnumber");
	String	s_saveas_number = "";
	String	s_fiscalyear = (String) request.getAttribute("fiscalyear");
	String	s_currentpage = (String) request.getAttribute("currentpage");
	String	s_currentprocessmethod = (String) request.getAttribute("currentprocessmethod");
	String  s_is_po_blanket_order = "N"; 

	String begin = propertiesManager.getProperty("Misc", "fybegin", "1");
	s_fiscalyear = Dates.getFiscalYear(Integer.parseInt(begin), userTimeZone);

	if (s_formtype.equalsIgnoreCase("REQ"))
	{
		s_number_label	 = "Requisition";
		s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTOREQ", "N");
		s_showauto = propertiesManager.getProperty("AUTONUMBER", "SHOWAUTOREQ", "Y");
	}
	else if (s_formtype.equalsIgnoreCase("RFQ"))
	{
		s_number_label	 = "Solicitation";
		s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTORFQ", "N");
		s_showauto = propertiesManager.getProperty("AUTONUMBER", "SHOWAUTORFQ", "Y");
	}
	else if (s_formtype.equalsIgnoreCase("DSB"))
	{
		s_number_label	 = "Disbursement";
		s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTODISBURS", "N");
		s_showauto = propertiesManager.getProperty("AUTONUMBER", "SHOWAUTODISB", "Y");
	}
	else if (s_formtype.equalsIgnoreCase("PO") || s_formtype.equalsIgnoreCase("CT"))
	{
		s_number_label = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "save-po-number-label", "Purchase Order");
		s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTOPO", "N");
		s_showauto = propertiesManager.getProperty("AUTONUMBER", "SHOWAUTOPO", "Y");

		String poType = HiltonUtility.ckNull((String) request.getAttribute("potype"));
		if (poType.equals("RO") || poType.equals("DR") || poType.equals("SR"))
		{
			s_saveas_number = s_formnumber;
		}
		if (poType.equals("BO") || poType.equals("DO") || poType.equals("SB"))
		{
			s_is_po_blanket_order = "Y";
		}
	}
	else if (s_formtype.equalsIgnoreCase("INVITEM"))
	{
		s_number_label	 = "Item";
		s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTOINVITEM", "N");
		s_showauto = "N";
	}
	else if (s_formtype.equalsIgnoreCase("SALE"))
	{
		s_number_label	 = "Transaction";
		s_autonumber = propertiesManager.getProperty("AUTONUMBER", "AUTOSALE", "N");
	}

	if (s_fiscalyear == null)		{ s_fiscalyear=""; 		}
	if (s_autonumber == null)	{ s_autonumber = "N";	}

	if ( !HiltonUtility.isEmpty(s_formnumber) && !HiltonUtility.isNA(s_formnumber))	{		s_current_number = s_formnumber;	}
%>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" VALIGN="BOTTOM" HEIGHT="135" CLASS="basic">
	<TR>
		<TD>
			<TABLE BORDER="0" CELLPADDING="2" WIDTH="100%" CLASS="basic">
<%	if (s_showauto.equals("Y")) {%>
				<TR>
					<TD ALIGN="RIGHT" NOWRAP CLASS="basic"><B>Auto Number:</B></TD>
					<TD><INPUT TYPE="CHECKBOX" NAME="ck_auto_number" ONCLICK="resetNumber();" value="Y" <% if (s_autonumber.equals("Y")) {%>CHECKED<%}%>></TD>
				</TR>
<%	}%>
				<TR>	<TD COLSPAN="2">
						<tsa:hidden name="action" value="<%=s_action%>"/>
						<tsa:hidden name="as_action" value="<%=s_action%>"/>
						<tsa:hidden name="current_number" value="<%=s_current_number%>"/>
					</TD>
				</TR>
				<TR>
					<TD ALIGN="RIGHT" NOWRAP CLASS="basic"><B><%=s_number_label%> Number:</B></TD>
					<TD><INPUT TYPE="TEXT" NAME="saveas_number" TABINDEX="0" <% if (s_formtype.equalsIgnoreCase("INVITEM")) {%>SIZE="25" MAXLENGTH="30"<%} else {%>SIZE="16" MAXLENGTH="20"<%}%> value="<%=s_saveas_number%>" ONFOCUS="ckAutoNumber();" ONCHANGE="upperCase(this);"></TD>
				</TR>
<%	if (!s_formtype.equalsIgnoreCase("INVITEM")) {%>
				<TR>
					<TD ALIGN="RIGHT" NOWRAP CLASS="basic"><B><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%>:</B></TD>
					<TD>
						<select title="Select the Fiscal Year " name="saveas_fiscal_year" tabindex=2>
						<%=HiltonUtility.getFiscalYearOptions(oid, userTimeZone, s_formtype, s_fiscalyear)%>
						</select>
					</TD>
				</TR>
<%	}%>

<%	if (s_is_po_blanket_order.equalsIgnoreCase("Y")) {%>
				<TR>
					<TD ALIGN="center" NOWRAP CLASS="basic" colspan="2">
						<B>Apply the selected <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%> to Blanket and Schedule dates?:</B>
						<br/>
					<!-- /TD>
					<TD -->
						<input type="radio" name="saveas_po_banket_fiscalyear" value="Y">Yes
						&nbsp;&nbsp;
						<input type="radio" name="saveas_po_banket_fiscalyear" value="N" checked>No
					</TD>
				</TR>
<%	}%>

				<TR><TD COLSPAN="2"><BR></TD></TR>
			</TABLE>
		</TD>
	</TR>
	<TR>
		<TD>
			<TABLE BORDER="0" WIDTH="100%" CLASS="basic">
			<TR>
				<TD ALIGN="CENTER"><div id="pxbutton"><A HREF="javascript: setInfo(); void(0);" BORDER="0">Ok</A></div></TD>
				<TD ALIGN="CENTER"><div id="pxbutton"><A HREF="javascript: window.top.hidePopWin(); void(0)">Cancel</A></div></TD>
			</TR>
			</TABLE>
		</TD>
	</TR>
</TABLE>
</FORM>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	thisfrm = document.purchaseform;
	self.focus();

	var currentprocessmethod = "<%=headerEncoder.encodeForJavaScript(s_currentprocessmethod)%>";
	currentprocessmethod = replaceChar(currentprocessmethod, '/', ';');

	function setInfo()
	{
<%	if (!s_formtype.equalsIgnoreCase("INVITEM")) {%>
		var todayDate = new Date();
		var fy = thisfrm.saveas_fiscal_year[thisfrm.saveas_fiscal_year.selectedIndex].value;
		if (fy < todayDate.getYear()) {
			fy = todayDate.getYear();
		}
		var saveasFY = fy;
<%	}%>
		var saveasNo = thisfrm.saveas_number.value;
		var curNo = thisfrm.current_number.value;
		
<%	if (s_showauto.equals("N"))
		{
			if (s_autonumber.equals("N"))
			{ %>
				var autoNo = false;
<%		}
			else
			{ %>
				var autoNo = true;
<%		}
		}
		else
		{ %>
			var autoNo = thisfrm.ck_auto_number.checked;
<%	} %>

		if (!autoNo && isEmpty(saveasNo))
		{
			alert("<%=s_number_label%> number cannot be blank!");
			return false;
		}

<%	if (s_formtype.equalsIgnoreCase("REQ"))
		{ %>
		if(window.parent.frm.RequisitionHeader_requisitionNumber != null )
	    {
			window.parent.frm.RequisitionHeader_requisitionNumber.value = saveasNo;
	    }
	    if(window.parent.frm.RequisitionHeader_fiscalYear != null)
	    {
			window.parent.frm.RequisitionHeader_fiscalYear.value = saveasFY;
	    }
			var newInputField = "<input type='hidden' name='formtype' value='REQ'>";
			newInputField = newInputField + "<input type='hidden' name='RequisitionHeader_fiscalYear' value='" + saveasFY + "'>";
			setHiddenFields(newInputField);
<%	}
		else if(s_formtype.equalsIgnoreCase("RFQ"))
		{ %>
			window.parent.frm.RfqHeader_rfqNumber.value = saveasNo;
			window.parent.frm.RfqHeader_fiscalYear.value = saveasFY;
<%	}
		else if(s_formtype.equalsIgnoreCase("DSB"))
		{ %>
			window.parent.frm.DisbHeader_disbNumber.value = saveasNo;
			window.parent.frm.DisbHeader_fiscalYear.value = saveasFY;
<%	}
		else if(s_formtype.equalsIgnoreCase("PO") || s_formtype.equalsIgnoreCase("CT"))
		{ %>
			window.parent.frm.PoHeader_poNumber.value = saveasNo;
			window.parent.frm.PoHeader_fiscalYear.value = saveasFY;
			if (window.parent.frm.fromSaveAs) {
				window.parent.frm.fromSaveAs.value = "Y";
			}

			<% if (s_is_po_blanket_order.equalsIgnoreCase("Y")) {%>
				window.parent.frm.PoHeader_applyFiscalYear.value = getCheckedRadioButtonValue('saveas_po_banket_fiscalyear');
			<% } %>
<%	}
		else if(s_formtype.equalsIgnoreCase("INVITEM"))
		{ %>
			window.parent.frm.newItemNumber.value = saveasNo;
			window.parent.frm.itemAction.value = "UPDATE";
<%	}
		else if(s_formtype.equalsIgnoreCase("SALE"))
		{ %>
			window.parent.frm.SaleHeader_saleNumber.value = saveasNo;
			window.parent.frm.SaleHeader_fiscalYear.value = saveasFY;
<%	}%>


		setTimeout('window.top.hidePopWin();', 10);

		//set cursor to hourglass while the system is processing
		window.parent.document.body.style.cursor = "wait";
<%	if (s_action.equalsIgnoreCase("save"))
		{
			if (s_formtype.equalsIgnoreCase("REQ"))
			{ %>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/requests/req_summary.jsp', 'RequisitionGetFormNumber;RequisitionRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('<%=headerEncoder.encodeForJavaScript(s_currentpage)%>', 'RequisitionGetFormNumber;' + currentprocessmethod);
				}

<%		}
			else if (s_formtype.equalsIgnoreCase("RFQ"))
			{ %>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/rfq/rfq_summary.jsp', 'RfqGetFormNumber;RfqRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('<%=headerEncoder.encodeForJavaScript(s_currentpage)%>', 'RfqGetFormNumber;' + currentprocessmethod);
				}
<%		}
			else if (s_formtype.equalsIgnoreCase("DSB"))
			{ %>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/inventory/dsb_summary.jsp', 'OtcSave;DisbSetProperty;DisbursementRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('<%=headerEncoder.encodeForJavaScript(s_currentpage)%>', 'OtcSave;DisbSetProperty;' + currentprocessmethod);
				}
<%		}
			else if (s_formtype.equalsIgnoreCase("PO"))
			{ %>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/orders/po_summary.jsp', 'PoSave;PoRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('<%=headerEncoder.encodeForJavaScript(s_currentpage)%>', 'PoSave;' + currentprocessmethod);
				}
<%		}
			else if (s_formtype.equalsIgnoreCase("INVITEM"))
			{ %>
				window.parent.doSubmit('/inventory/inv_item.jsp', 'InvItemAdd');
<%			}
			else if (s_formtype.equalsIgnoreCase("SALE"))
			{ %>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/sales/sale_summary.jsp', 'SaleGetFormNumber;SaleRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('<%=headerEncoder.encodeForJavaScript(s_currentpage)%>', 'SaleGetFormNumber;' + currentprocessmethod);
				}
<%		}
		}
		else if (s_action.equalsIgnoreCase("saveas"))
		{
			if (s_formtype.equalsIgnoreCase("REQ"))
			{ %>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/requests/req_summary.jsp', 'RequisitionSaveas;RequisitionRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('/requests/req_review.jsp', 'RequisitionSaveas;RequisitionRetrieve');
				}
<%		}
			else if (s_formtype.equalsIgnoreCase("RFQ"))
			{ %>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/rfq/rfq_summary.jsp', 'RfqSaveas;RfqRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('/rfq/rfq_review.jsp', 'RfqSaveas;RfqRetrieve');
				}
<%		}
			else if (s_formtype.equalsIgnoreCase("PO"))
			{ %>
				if (window.parent.frm.PoHeader_poType.value == "RO" || window.parent.frm.PoHeader_poType.value == "DR" || window.parent.frm.PoHeader_poType.value == "SR")
				{
					var saveAsMethod = 'PoSaveasRelease';
				}
				else
				{
					var saveAsMethod = 'PoSaveas';
				}
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/orders/po_summary.jsp', saveAsMethod+';PoRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('/orders/po_review.jsp', saveAsMethod+';PoRetrieve');
				}
<%		}
			else if (s_formtype.equalsIgnoreCase("CT"))
			{%>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/orders/po_summary.jsp', 'PoCreateFromContract;PoRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('/orders/po_review.jsp', 'PoCreateFromContract;PoRetrieve');
				}
<%		}
			else if (s_formtype.equalsIgnoreCase("INVITEM"))
			{ %>
				window.parent.doSubmit('/inventory/inv_item.jsp', 'InvItemSaveas');
<%		}
			else if (s_formtype.equalsIgnoreCase("SALE"))
			{ %>
				if (window.parent.frm.viewType.value == "CLASSIC")
				{
					window.parent.doSubmit('/sales/sale_summary.jsp', 'SaleSaveas;SaleRetrieve');
				}
				else if (window.parent.frm.viewType.value == "WIZARD")
				{
					window.parent.doSubmit('/sales/sale_review.jsp', 'SaleSaveas;SaleRetrieve');
				}
<%		}
		} %>

		<%if (s_formtype.equalsIgnoreCase("INVITEM")){%>
			window.close();
		<%} else {%>
			window.top.hidePopWin();
			return false;
		<%}%>
		
	}

	function resetNumber()
	{
		if ( thisfrm.ck_auto_number.checked )
		{
			thisfrm.saveas_number.value = "";
			thisfrm.saveas_number.blur();
		}
	}

	function ckAutoNumber()
	{
<%	if (s_showauto.equals("N"))
		{
	    	if (s_autonumber.equals("Y"))
	    	{ %>
				thisfrm.saveas_number.blur();
<%	    }
		}
		else
		{ %>
			if ( thisfrm.ck_auto_number.checked )
			{
				thisfrm.saveas_number.blur();
			}
<%	} %>
	}

	function replaceChar(x, r, n) {
		//	x = string of characters
		//	r = character to be replaced
		//	n = character that replaces r

		var w = "";
		var tst = "";

		for ( var i = 0; i < x.length; i++) {
			tst = x.substring(i,i+1);
			if (tst.indexOf(r) >= 0) {
				w = w + n;
			}
			else {
				w = w + tst;
			}
		}
		return w;
	}

// end hiding contents -->
</SCRIPT>

</body>
</html>