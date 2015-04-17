<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.utility.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	String	s_dollar_decimals = PropertiesManager.getInstance(oid).getProperty("MISC", "DollarDecimals", "2");
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");
	String s_returnPage = (String) request.getAttribute("returnPage");

	if (HiltonUtility.isEmpty(s_returnPage))	{
		if (s_view.equals("CLASSIC")) {
			s_returnPage = "/rfq/rfq_summary.jsp";
		} else {
			s_returnPage = "/rfq/rfq_review.jsp";
		}
	}

	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>
<tsa:hidden name="stdTable_tableType" value=""/>
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
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Supplier View</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
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
<br>

<%
	RfqVendor rfqVendor = (RfqVendor) request.getAttribute("rfqVendor");
	RfqVendorPK rfqVendorPK = rfqVendor.getComp_id();
	String s_vendorId = rfqVendorPK.getVendorId();
	if (HiltonUtility.isEmpty(s_vendorId)) {s_vendorId = "N/A";}
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<table border=0 width=100% cellpadding=0 cellspacing=0>
		<tr>
			<td>
				<table border=0 width=100% cellpadding=0 cellspacing=0>
				<tr>
					<td colspan=2>
						<table border=0>
						<tr>
							<td width=15px>&nbsp;</td>
							<td nowrap valign=middle class="formtype"><tsa:hidden name="RfqVendor_vendorId" value="<%=s_vendorId%>"/><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%> - <%=s_vendorId%>:</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width=50% valign=top>
						<table border=0 width=100% cellpadding=0 cellspacing=0>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-bidResponse")%> align=right><a href="javascript: browseStd('RfqVendor_bidResponseCode', 'RESP'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidResponse", "Bid Response")%> for this supplier or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidResponse", "Bid Response")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidResponse", "Bid Response", true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-bidResponse")%>><input type=text name="RfqVendor_bidResponseCode"  size=15 maxlength=15 tabindex=2 value="<%=rfqVendor.getBidResponseCode()%>" onchange="upperCase(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-fob")%> align=right><A HREF="javascript: browseStd('RfqVendor_fob', 'FOB'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-fob", "FOB")%> for this supplier or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-fob", "FOB")%> in the box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-fob", "FOB", true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-fob")%>><input type=text name="RfqVendor_fob" size=15 maxlength=15 tabindex=4 value="<%=rfqVendor.getFob()%>" onchange="upperCase(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-paymentTerms")%> align=right><A HREF="javascript: browseLookup('RfqVendor_paymentTerms', 'payment-terms'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-paymentTerms", "Terms")%> for this supplier or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-paymentTerms", "Terms")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-paymentTerms", "Terms", true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-paymentTerms")%>><input type=text name="RfqVendor_paymentTerms" size=15 maxlength=15 tabindex=6 value="<%=rfqVendor.getPaymentTerms()%>" onchange="upperCase(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-contact")%> align=right><A HREF="javascript: browseContactAddress('RfqVendor_contactId',frm.RfqVendor_vendorId.value);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-contact", "Contact")%> for this supplier or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-contact", "Contact")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-contact", "Contact", true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-contact")%>><input type=text name="RfqVendor_contactId" size=15 maxlength=15 tabindex=8 value="<%=rfqVendor.getContactId()%>" onchange="upperCase(this);"><tsa:hidden name="RfqVendor_contactName" value="<%=rfqVendor.getContactName()%>"/></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-address")%> align=right><A HREF="javascript: browseContactAddress('RfqVendor_contactId',frm.RfqVendor_vendorId.value);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-address", "Address")%> for the supplier contact or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-address", "Address")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-address", "Address")%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-address")%>><input type=text name="RfqVendor_addressCode" size=15 maxlength=15 tabindex=10 value="<%=rfqVendor.getAddressCode()%>" onchange="upperCase(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-edi")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-edi", "EDI")%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-edi")%>>
								<INPUT TYPE="CHECKBOX" name="c_checkbox" size=15 tabindex=12 <% if (rfqVendor.getEdiRfq().indexOf("Y")>= 0){ %>CHECKED<%}%> value="" onclick="setEdi();">
								<tsa:hidden name="RfqVendor_ediRfq" value="<%=rfqVendor.getEdiRfq()%>"/>
							</td>
						</tr>
						</table>
					</td>
					<td width=50% valign=top>
						<table border=0 width=100% cellpadding=0 cellspacing=0>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-currency")%> align=right><a href="javascript: browseLookup('RfqVendor_vendCurrency', 'curr_code'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-currency", "Currency")%> for this supplier or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-currency", "Currency")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-currency", "Currency", true)%></a>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-currency")%>><input type=text name="RfqVendor_vendCurrency" size=15 maxlength=15 tabindex=14 value="<%=rfqVendor.getVendCurrency()%>" onchange="upperCase(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-shippingCharges")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shippingCharges", "Shipping")%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-shippingCharges")%>><input type=text name="RfqVendor_shippingCharges" size=15 tabindex=16 value="<%=HiltonUtility.getFormattedDollar(rfqVendor.getShippingCharges(), oid)%>" style="text-align:right" onchange="formatPrice(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-otherCharges")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-otherCharges", "Other")%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-otherCharges")%>><input type=text name="RfqVendor_otherCharges" size=15 tabindex=17 value="<%=HiltonUtility.getFormattedDollar(rfqVendor.getOtherCharges(), oid)%>" style="text-align:right" onchange="formatPrice(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-taxAmount")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-taxAmount", "Tax")%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-taxAmount")%>><input type=text name="RfqVendor_taxAmount" size=15  tabindex=18 value="<%=HiltonUtility.getFormattedDollar(rfqVendor.getTaxAmount(), oid)%>" style="text-align:right" onchange="formatPrice(this);"></td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-responseDate", "Response Date", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-responseDate")%>>
								<input type=text name="RfqVendor_dateResponseRecv" size=15 tabindex=20 value="<%=HiltonUtility.getFormattedDate(rfqVendor.getDateResponseRecv(), oid, userDateFormat)%>" onchange="checkDate(this);">
								<a href="javascript: show_calendar('RfqVendor_dateResponseRecv', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to find a Date or enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-promiseDate", "Promise Date", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%>>
								<input type=text name="RfqVendor_datePromised" size=15 maxlength=15 tabindex=22 value="<%=HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid, userDateFormat)%>" onchange="checkDate(this);">
								<a href="javascript: show_calendar('RfqVendor_datePromised', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to find a Date or enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%> align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidValidTo", "Bid Valid To", true)%>:&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-bidValidTo")%>>
								<input type=text name="RfqVendor_bidValidTo" size=15 maxlength=15 tabindex=24 value="<%=HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(), oid, userDateFormat)%>" onchange="checkDate(this);">
								<a href="javascript: show_calendar('RfqVendor_bidValidTo', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to find a Date or enter a Date in the box on the left." valign=bottom border=0></a>
							</td>
						</tr>
		<!--
						<tr>
							<td align=right>Last Change Date:</td>
							<td>
								<input type=text name="as_last_chg_date" SIZE="20" value="" ONFOCUS="this.blur();">
							</td>
						</tr>
		-->
					</td>
				</tr>
				<tr><td colspan=2><br></td></tr>
				<tr><td colspan=2 align=center><!--a href="javascript:viewBids(); void(0);">View Bids</a--></td></tr>
				</table>
			</td>
		</tr>
		</tr>
		</table>

		<br>

		<table border=0 width=100% cellpadding=0 cellspacing=0>
		<tr <%=HtmlWriter.isVisible(oid, "rfq-sup-notes")%>>
			<td align=center>
				<table border=1 cellspacing=0 cellpadding=0 width=365px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Supplier Notes</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table id="notes" border=0 cellpadding=2 cellspacing=0 width=365px>
<%
	RfqNote rfqNote = (RfqNote) request.getAttribute("rfqNote");
	if (rfqNote != null)
	{
%>
						<tr>
							<td><%=rfqNote.getNotesText()%></td>
						</tr>
<%
	} else { %>
						<tr><td>This supplier has not entered any additional notes.</td></tr>
<%
	}%>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td><br><br></td></tr>
		<tr <%=HtmlWriter.isVisible(oid, "rfq-sup-attachments")%>>
			<td align=center>
				<table border=1 cellspacing=0 cellpadding=0 width=365px class=browseHdr>
				<tr>
					<td class=browseHdr height=18px nowrap>&nbsp;Supplier Attachments</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table id="attachments" border=0 cellpadding=2 cellspacing=0 width=365px>
<%
	List list = (List) request.getAttribute("vendorDocumentList");

	if (list != null && list.size() > 0)
	{
		for (int i = 0; i < list.size(); i++)
		{
			VendorDocument vendorDocument = (VendorDocument) list.get(i);
			VendorDocumentPK vendorDocumentPK = vendorDocument.getComp_id();
			String	filename = vendorDocument.getDocFilename();
			String	ext = filename.substring(filename.lastIndexOf(".") + 1);
%>
						<tr>
							<td valign=middle width=20px align=center>
								<tsa:hidden name="docFilename" value = "<%=filename%>"/>
<%		if (ext.equalsIgnoreCase("DOC")) { %>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) { %>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) { %>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) { %>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) { %>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) { %>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else { %>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		} %>
							</td>
							<td><a href="javascript: openDocument(<%=i%>); void(0);"><%=vendorDocument.getDocTitle()%></a></td>
						</tr>
<%	}
	} else { %>
						<tr><td>There are currently no documents associated with this supplier.</td></tr>
<%
	}%>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%	if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_returnPage%>', 'RfqVendorUpdateById;RfqRetrieve'); void(0);">Save</a></div></td>
<%	} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=s_returnPage%>', 'RfqRetrieve'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function thisLoad()
	{
		f_StartIt();
<%	if (!editMode) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	var browser = browserCheck();


	function setEdi()
	{
		if (frm.c_checkbox.checked==true)
		{
			frm.RfqVendor_ediRfq.value = "Y";
		}
		else
		{
			frm.RfqVendor_ediRfq.value = "N";
		}
	}

	function formatPrice (x)
	{
		var dollar_dec = <%=Integer.valueOf(s_dollar_decimals).intValue()%>;
		var p = nformat(eval(nfilter(x)),dollar_dec);

		x.value = p;
	}

	function openDocument(row) {
		var myTable = document.getElementById("attachments");
		var totalRows = myTable.rows.length;
		var filename = "";

		if (totalRows > 1) {
			filename = frm.docFilename[row].value;
		} else {
			filename = frm.docFilename.value;
		}

		popupUrl = '/system/popupDocAttachment.jsp';
		popupHandler = "VendorDocumentDownloadFile";
		popupUserId = frm.userId.value;
		popupOrganizationId = frm.organizationId.value;
		popupParameters = "filename=" + filename;

		if (theFocus == null) { theFocus = 'document_window'; }

		var winspecs = "WIDTH=680,HEIGHT=680,resizable=1,scrollbars=1,menubar=0,location=0,top=0,left=0";
		document_window = window.open("<%=contextPath%>/system/popup.html", "document_window", winspecs);

		if (theFocus == 'main') {
			self.focus();
		}
		else {
			document_window.focus();
		}

		if (document_window.opener == null) document_window.opener = window;
		self.name = "main";
	}

// End Hide script -->
</SCRIPT>
