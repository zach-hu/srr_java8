<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	int i;

	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_shipToCode = rfqHeader.getShipToCode();
	Address address = rfqHeader.getShipToAddress();
	if(address==null){
		address = new Address();
	}
	String s_addressLine1 = address.getAddressLine1();
	String s_addressLine2 = address.getAddressLine2();
	String s_addressLine3 = address.getAddressLine3();
	String s_addressLine4 = address.getAddressLine4();
	String s_city = address.getCity();
	String s_state = address.getState();
	String s_postalCode = address.getPostalCode();
	String s_country = address.getCountry();
	String s_attention = rfqHeader.getShipToContact();
	String s_fiscalyear = rfqHeader.getFiscalYear();

	String	s_current_process = "HEADER_SHIPPING";
	String	s_current_page = "/rfq/rfq_shipping.jsp";
	String	s_current_method = "RfqHeaderUpdateById";
	String	s_current_process_method = "";

	boolean bAllowEdit = true;
  	String	invalidFields = (String) request.getAttribute("invalidFields");

	while(!HiltonUtility.isEmpty(invalidFields) && invalidFields.startsWith(";")) {
		if (invalidFields.length() > 1) {
			invalidFields = invalidFields.substring(1);
		} else {
			invalidFields = "";
		}
	}

	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${rfqHeader.rfqType}"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=rfqHeader.getFiscalYear()%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_icRfqHeader%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="country" value="<%=rfqHeader.getUdf1Code()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="shipping_information" defaultString="Shipping Information" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<%@ include file="/rfq/rfq_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top>
		<table border=0 cellpadding=0 cellspacing=0 width=90%>
		<TR>
			<TD width=250px align=center valign=top>
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" width=100%>
				<tsa:tr field="rfq-shipToCode">
					<TD ALIGN="RIGHT" NOWRAP width=50%><a href="javascript: browseLookup('RfqHeader_shipToCode', 'ship_to'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-shipToCode" defaultString="Ship To" /> for this solicitation or enter the <tsa:label labelName="rfq-shipToCode" defaultString="Ship To" /> in the box on the right."><tsa:label labelName="rfq-shipToCode" defaultString="Ship To" checkRequired="true" /></A>:&nbsp;</TD>
					<TD width=50%><tsa:input type="text" name="RfqHeader_shipToCode" tabIndex="1" size="15" maxLength="15" value="<%=s_shipToCode%>" onchange="upperCase(this); getNewInfo('shipTo', this);"/></TD>
				</tsa:tr>

				<tsa:tr field="rfq-addressLine1">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-addressLine1" defaultString="Address 1" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="Address_addressLine1" size="20" maxLength="30" value="<%=s_addressLine1%>" onfocus="this.blur();" disabled="true"/></TD>
				</tsa:tr>

				<tsa:tr field="rfq-shp-addressLine2">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-shp-addressLine2" defaultString="Address 2" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="Address_addressLine2" size="20" maxLength="30" value="<%=s_addressLine2%>" onfocus="this.blur();" disabled="true"/></TD>
				</tsa:tr>

				<tsa:tr field="rfq-shp-addressLine3">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-shp-addressLine3" defaultString="Address 3" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="Address_addressLine3" size="20" maxLength="30" value="<%=s_addressLine3%>" onfocus="this.blur();" disabled="true"/></TD>
				</tsa:tr>

				<tsa:tr field="rfq-shp-addressLine4">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-shp-addressLine4" defaultString="Address 4" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="Address_addressLine4" size="20" maxLength="30" value="<%=s_addressLine4%>" onfocus="this.blur();" disabled="true"/></TD>
				</tsa:tr>

				<tsa:tr field="rfq-shp-city">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-shp-city" defaultString="City" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="Address_city" size="20" maxLength="30" value="<%=s_city%>" onfocus="this.blur();" disabled="true"/></TD>
				</tsa:tr>

				<tsa:tr field="rfq-shp-state">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-shp-state" defaultString="State" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="Address_state" size="20" maxLength="30" value="<%=s_state%>" onfocus="this.blur();" disabled="true"/></TD>
				</tsa:tr>

				<tsa:tr field="rfq-shp-zip">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-shp-zip" defaultString="Zip" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="Address_postalCode" size="20" maxLength="30" value="<%=s_postalCode%>" onfocus="this.blur();" disabled="true"/></TD>
				</tsa:tr>

				<tsa:tr field="rfq-shp-country">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-shp-country" defaultString="Country" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="Address_country" size="20" maxLength="25" value="<%=s_country%>" onfocus="this.blur();" disabled="true"/></TD>
				</tsa:tr>

				<TR><TD COLSPAN="2"><BR></TD></TR>
				</TABLE>

			</TD>
			<TD WIDTH=5px>&nbsp;</TD>
			<TD width=250px align=center valign=top>
				<br><br><br>
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING=0 width=100%>
				<tsa:tr field="rfq-attention">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-attention" defaultString="Attention" checkRequired="true" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="RfqHeader_shipToContact" tabIndex="3" size="15" maxLength="40" value="<%=s_attention%>"/></TD>
				</tsa:tr>
				<tsa:tr field="rfq-requiredDate">
					<TD ALIGN="RIGHT"><tsa:label labelName="rfq-requiredDate" defaultString="Required By" checkRequired="true" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="RfqHeader_requiredDate" tabIndex="5" size="15" maxLength="10" value="<%=HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), oid, userDateFormat)%>"/>
						<A HREF="javascript: show_calendar('RfqHeader_requiredDate', '<%=userDateFormat%>');"><IMG src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in box on the left." VALIGN="BOTTOM" BORDER=0></A>
					</TD>
				</tsa:tr>
				<tsa:tr field="rfq-shipVia">
					<TD ALIGN="RIGHT" NOWRAP><a href="javascript: browseLookup('RfqHeader_shippingCode', 'shipvia'); void(0);" title="Click here to choose the method in which you would like to recieve this solicitation or enter the method in the box on the right."><tsa:label labelName="rfq-shipVia" defaultString="Ship Via" checkRequired="true" /></A>:&nbsp;</TD>
					<TD><tsa:input type="text" name="RfqHeader_shippingCode" tabIndex="7" size="15" maxLength="15" value="<%=rfqHeader.getShippingCode()%>" onchange="upperCase(this);"/></TD>
				</tsa:tr>
				<tsa:tr field="rfq-priority">
					<TD ALIGN="RIGHT" NOWRAP><a href="javascript: browseLookup('RfqHeader_priorityCode', 'priority'); void(0);" title="Click here to choose the <tsa:label labelName="rfq-priority" defaultString="Priority" /> for this solicitation or enter the <tsa:label labelName="rfq-priority" defaultString="Priority" /> in the box on the right."><tsa:label labelName="rfq-priority" defaultString="Priority" checkRequired="true" /></A>:&nbsp;</TD>
					<TD><tsa:input type="text" name="RfqHeader_priorityCode" tabIndex="9" size="15" maxLength="15" value="<%=rfqHeader.getPriorityCode()%>" onchange="upperCase(this);"/></TD>
				</tsa:tr>
				<tsa:tr field="rfq-routing">
					<TD ALIGN="RIGHT" NOWRAP><tsa:label labelName="rfq-routing" defaultString="Routing" checkRequired="true" />:&nbsp;</TD>
					<TD><tsa:input type="text" name="RfqHeader_routing" tabIndex="11" size="15" maxLength="25" value="<%=rfqHeader.getRouting()%>"/></TD>
				</tsa:tr>

				<TR><TD COLSPAN="2"><BR></TD></TR>
				</TABLE>
			</td>
		</tr>
		</table>
	</td>
	<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
			<td rowspan=2 valign=top><%@ include file="/rfq/rfq_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqHeaderUpdateById;RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Save" defaultString="Save" /></a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%	} else {%>
	<td width=100% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
<%	}%>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var rfqnumber = "<%= headerEncoder.encodeForJavaScript(s_rfqNumber) %>";
	var fiscalyear = "<%= headerEncoder.encodeForJavaScript(s_fiscalyear) %>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
		<%	if (!bAllowEdit) { %>
		checkInputSettings();
	<%	} else if(bAllowEdit) { %>
		setInvalidFields("<%=headerEncoder.encodeForJavaScript(invalidFields)%>");
	<%	}%>
<%	if (!editMode) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("RfqHeaderUpdateById") >= 0) {
			var alertMessage = "";

			if (frm.RfqHeader_requiredDate && !chkdate(frm.RfqHeader_requiredDate)) {
				alertMessage += "\n<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requiredDate", "Required By")%> is not a valid date.";
			}

			if (alertMessage.length > 0) {
				alert("Please fix the following problems:\n" + alertMessage);
				return false;
			}
		}
		return true;
	}

// End Hide script -->
</SCRIPT>