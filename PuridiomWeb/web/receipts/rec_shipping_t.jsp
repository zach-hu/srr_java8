<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsagate.properties.*" %>
<%@ page import="java.math.*" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	ReceiptHeader receiptHeader = (ReceiptHeader) request.getAttribute("receiptHeader");
	BigDecimal bd_ic_rec_header = receiptHeader.getIcRecHeader();
	String	s_rec_number = receiptHeader.getReceiptNumber();
	String	s_rec_type = receiptHeader.getReceiptType();
	String	s_rec_status = receiptHeader.getReceiptStatus();
	String	s_rec_fiscal_year = receiptHeader.getFiscalYear();
	if (HiltonUtility.isEmpty(s_rec_status)) {
		s_rec_status = DocumentStatus.RCV_INPROGRESS;
	}

	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	if (poHeader == null) {
		poHeader = new PoHeader();
	}
	String	s_po_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_poNumber"));
	String	s_revision_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_revisionNumber"));
	String	s_release_number = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_releaseNumber"));
	if (HiltonUtility.isEmpty(s_revision_number)) {
		s_revision_number = "0";
	}
	if (HiltonUtility.isEmpty(s_release_number)) {
		s_release_number = "0";
	}
	BigDecimal	bd_revision_number = new BigDecimal(s_revision_number);
	BigDecimal	bd_release_number = new BigDecimal(s_release_number);

	String	s_address_line_1 = "";
	String	s_address_line_2 = "";
	String	s_address_line_3 = "";
	String	s_address_line_4 = "";
	String	s_city = "";
	String	s_state = "";
	String	s_postal_code = "";
	String	s_country = "";
	Address shipTo = (Address)request.getAttribute("shipToAddress");
	if (shipTo != null) {
		s_address_line_1 = shipTo.getAddressLine1();
		s_address_line_2 = shipTo.getAddressLine2();
		s_address_line_3 = shipTo.getAddressLine3();
		s_address_line_4 = shipTo.getAddressLine4();
		s_city = shipTo.getCity();
		s_state = shipTo.getState();
		s_postal_code = shipTo.getPostalCode();
		s_country = shipTo.getCountry();
	}

	String	receiptMethod = HiltonUtility.ckNull((String)request.getAttribute("receiptMethod"));
	String	s_current_process = "HEADER_SHIPPING";
	String	s_current_page = "/receipts/rec_shipping.jsp";
	String	s_current_method = "ReceiptHeaderUpdate";
	String	s_current_process_method = "";

	BigDecimal	bd_zero = new BigDecimal(0);

	boolean allowEdit = false;
	if ((receiptMethod.equals("FinalizeReceipt") && s_rec_status.compareTo(DocumentStatus.RCV_PENDINGFINALIZATION) == 0) ||
		(receiptMethod.equals("ReceiveFromNothing") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0) ||
		(receiptMethod.equals("ReceiveByOrder") && s_rec_status.compareTo(DocumentStatus.RCV_INPROGRESS) == 0))
		allowEdit = true;
%>

<tsa:hidden name="ReceiptHeader_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="ReceiptHeader_receiptNumber" value="<%=s_rec_number%>"/>
<tsa:hidden name="ReceiptHeader_receiptType" value="<%=s_rec_type%>"/>
<tsa:hidden name="ReceiptHeader_receiptStatus" value="<%=s_rec_status%>"/>
<tsa:hidden name="ReceiptHeader_fiscalYear" value="<%=s_rec_fiscal_year%>"/>
<tsa:hidden name="ReceiptLine_icRecHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_revisionNumber" value="<%=bd_revision_number%>"/>
<tsa:hidden name="PoHeader_releaseNumber" value="<%=bd_release_number%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%=receiptHeader.getIcPoHeader()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="RCH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=bd_ic_rec_header%>"/>
<tsa:hidden name="receiptMethod" value="<%=receiptMethod%>"/>
<tsa:hidden name="formType" value="REC"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td>
		<%	if (!HiltonUtility.isEmpty(s_po_number)) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Order #:</b>&nbsp;<%=s_po_number%>
		<%	} %>
			</td>
			<td align=right><b>Receipt #:</b></td>
		<%	if (HiltonUtility.isEmpty(s_rec_number)) { %>
			<td width=100px>N/A</td>
		<%	} else { %>
			<td width=100px><%=s_rec_number%></td>
		<%	} %>
		</tr>
		<tr>
			<td>
		<%	if (bd_revision_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Revision #:</b>&nbsp;<%=bd_revision_number%>
		<%	} %>
		<%	if (bd_release_number.compareTo(bd_zero) > 0) { %>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Release #:</b>&nbsp;<%=bd_release_number%>
		<%	} %>
			</td>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=120px><%=DocumentStatus.toString(s_rec_status, oid)%></td>
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
<%@ include file="/receipts/rec_info.jsp" %>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<tr>
			<td width=250px align=center valign=top>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shipToCode")%>>
					<td align=right nowrap width=50%><a href="javascript: browseLookup('ReceiptHeader_shipToCode', 'ship_to'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shipToCode", "Ship To")%> for this order or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shipToCode", "Ship To")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shipToCode", "Ship To", true)%></a>:&nbsp;</td>
					<td width=50%><input type=text name="ReceiptHeader_shipToCode" tabindex=1 size=15 maxlength=15 value="<%=receiptHeader.getShipToCode()%>" onchange="upperCase(this); getNewInfo('shipTo', this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine1")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-addressLine1", "Address 1")%>:&nbsp;</td>
					<td width=50%><input type=text name="Address_addressLine1" size=25 maxlength=40 value="<%=s_address_line_1%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine2")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-addressLine2", "Address 2")%>:&nbsp;</td>
					<td width=50%><input type=text name="Address_addressLine2" size=25 maxlength=40 value="<%=s_address_line_2%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine3")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-addressLine3", "Address 3")%>:&nbsp;</td>
					<td width=50%><input type=text name="Address_addressLine3" size=25 maxlength=40 value="<%=s_address_line_3%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-addressLine4")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-addressLine4", "Address 4")%>:&nbsp;</td>
					<td width=50%><input type=text name="Address_addressLine4" size=25 maxlength=40 value="<%=s_address_line_4%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-city")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-city", "City")%>:&nbsp;</td>
					<td width=50%><input type=text name="Address_city" size=20 maxlength=30 value="<%=s_city%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-state")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-state", "State")%>:&nbsp;</td>
					<td width=50%><input type=text name="Address_state" size=20 maxlength=15 value="<%=s_state%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-zip")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-zip", "Zip")%>:&nbsp;</td>
					<td width=50%><input type=text name="Address_postalCode" size=20 maxlength=15 value="<%=s_postal_code%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-country")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-country", "Country")%>:&nbsp;</td>
					<td width=50%><input type=text name="Address_country" size=20 maxlength=25 value="<%=s_country%>" onfocus="this.blur()" disabled></td>
				</tr>
				</table>
			</td>
			<td width=5px>&nbsp;</td>
			<td width=250px align=center valign=top>
				<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr><td colspan=2><br><br><br><br></td></tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shp-attention")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shp-attention", "Attention", true)%>:&nbsp;</td>
					<td><input type=text name="PoHeader_shipToContact" tabindex=3 size=15 maxlength=40 value="<%=poHeader.getShipToContact()%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-shipVia")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-shipVia", "Ship Via", true)%>:&nbsp;</td>
					<td><input type=text name="PoHeader_shipViaCode" tabindex=5 size=15 maxlength=15 value="<%=poHeader.getShipViaCode()%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-priority")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-priority", "Priority", true)%>:&nbsp;</td>
					<td><input type=text name="PoHeader_priorityCode" tabindex=7 size=15 maxlength=15 value="<%=poHeader.getPriorityCode()%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "rec-routing")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-routing", "Routing", true)%>:&nbsp;</td>
					<td><input type="text" name="PoHeader_routing" tabindex=9 size="15" maxlength="25" value="<%=poHeader.getRouting()%>" onfocus="this.blur()" disabled></td>
				</tr>
				<tr><td colspan=2><br></td></tr>
				</table>
			</td>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
			<td rowspan=3 align=right valign=top><%@ include file="/receipts/rec_sidebar.jsp" %></td>
<%	} %>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/receipts/rec_summary.jsp', 'ReceiptHeaderUpdate;ReceiptRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/receipts/rec_summary.jsp', 'ReceiptRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var recNumber = "<%=s_rec_number%>";
	var fiscalyear = "<%=s_rec_fiscal_year%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
	<%	if (!allowEdit) { %>
		checkInputSettings();
	<%	} %>
	}

// End Hide script -->
</SCRIPT>
