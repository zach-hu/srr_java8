<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.AccountRollup" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus"%>
<%@ page import="com.tsa.puridiom.common.utility.HiltonUtility"%>
<%@ page import="com.tsagate.properties.DictionaryManager"%>
<%@ page import="com.tsagate.foundation.utility.Dates"%>
<%@ page import="com.tsa.puridiom.entity.InvoiceVendor"%>
<%@ page import="com.tsa.puridiom.entity.InvoiceHeader"%>
<%@ page import="com.tsa.puridiom.entity.InvoiceAddress"%>
<%@ page import="com.tsa.puridiom.entity.PoLine"%>
<%@ page import="com.tsa.puridiom.entity.Address" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.NumberFormat" %>

<script language='Javascript1.2' src="<%=contextPath%>/menu/ivOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>
<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_view_item_account = propertiesManager.getProperty("VOUCHER", "VIEWITEMACCOUNT", "N");
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	s_ivc_status = invoiceHeader.getStatus();
	String	s_curr_code = invoiceHeader.getCurrencyCode();
	String	s_fiscal_year = invoiceHeader.getFiscalYear() ;
 	String	s_current_process = "REVIEW";
	String	s_current_page = "/invoice/iv_review.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";
	int	acci = 0;
	String s_order_status = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_status"));
	String annio = Integer.toString(d_today.getYear());
	boolean codaEnabled = propertiesManager.getProperty("CODA","Enabled", "N").equalsIgnoreCase("Y");
	boolean bCreatedFromPo = false;
	if ((invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0)) > 0))
	{
		bCreatedFromPo = true;
	}
	if (poHeader == null)
	{
		poHeader = new PoHeader();
	}
%>
<script language='Javascript1.2' type="text/javascript">
<!--

	invoiceNumber = "<%=invoiceHeader.getInvoiceNumber()%>";
	invoiceStatus = "<%=invoiceHeader.getStatus()%>";
	ivcInProgress = "<%=DocumentStatus.IVC_INPROGRESS%>";
	ivcRecalled = "<%=DocumentStatus.IVC_RECALLED%>";
	ivcApproving = "<%=DocumentStatus.IVC_APPROVING%>";
	ivcApproved = "<%=DocumentStatus.IVC_APPROVED%>";
	ivcRejected = "<%=DocumentStatus.IVC_REJECTED%>";
	ivcPaid = "<%=DocumentStatus.IVC_PAID%>";
	ivcAccess = <%=role.getAccessRights("VOUCHERS")%>;
	invoiceExtractActive = <%=propertiesManager.getProperty("SUN", "InvoiceExtract", "N").equalsIgnoreCase("Y")%>;
	recallAccess = <%=uid.equalsIgnoreCase(invoiceHeader.getEnteredBy())%>;

	Array91= createInvoiceOptionsMenu(Array91[0]);

//-->
</SCRIPT>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_fiscalYear" value="<%=invoiceHeader.getFiscalYear()%>"/>
<tsa:hidden name="InvoiceHeader_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
<tsa:hidden name="InvoiceHeader_icPoHeader" value="<%=invoiceHeader.getIcPoHeader()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="Account_accountType" value="IVH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_email" value="<%=invoiceHeader.getOrderByEmail()%>"/>
<tsa:hidden name="formtype" value="IVC"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="invoiceaction" value=""/>
<tsa:hidden name="allowBrowse" value="false"/>
<tsa:hidden name="originalAccount_icLine" value=""/>
<tsa:hidden name="lineNumber" value=""/>
<tsa:hidden name="showItemAccount" value="F"/>
<tsa:hidden name="invoiceLine_icIvcLine" value="0"/>
<tsa:hidden name="today_date" value="<%=annio%>"/>
<tsa:hidden name="Current_year" value="I"/>
<tsa:hidden name="InvoiceHeader_invoiceDate" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), oid, userDateFormat)%>"/>

<table width=<%=formEntryWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Invoice Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=125px><%=invoiceHeader.getInvoiceNumber()%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus())%></td>
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

<table border="0" cellpadding="2" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
	<td width=100% valign=middle height=36px>
		<table border="0" cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<td width="80%" valign="middle">&nbsp;</td>
			<td width="20%" valign="middle" align="center" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)">More Options</a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td width=<%=formEntryWidth%> align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
		<tr>
			<td width=300px align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=250px>
				<tr>
					<td align=center>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;General Information</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr <%=HtmlWriter.isVisible(oid, "invoiceDate")%>>
							<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceDate", "Invoice Date")%>:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "paymentDueDate")%>>
							<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "paymentDueDate", "Payment Due")%>:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getPaymentDueDate(), oid, userDateFormat)%></td>
						</tr>
						<%
						if (oid.equalsIgnoreCase("dtn07p"))
						{
							String s_udf_1_code = invoiceHeader.getUdf1Code();
							if (!HiltonUtility.isEmpty(s_udf_1_code))
							{
								Date d_udf_1_code = Dates.getDate(s_udf_1_code);
								s_udf_1_code = HiltonUtility.getFormattedDate(d_udf_1_code, oid, userDateFormat);
							}
						%>
						<tr>
							<td align="right" nowrap><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV01", "UDF1")%>:</b></td>
							<td nowrap valign=top><%=s_udf_1_code%></td>
				        </tr>
						<% } %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-orderNumber")%>>
						<% if (bCreatedFromPo) {%>
							<td nowrap align=right valign=top><b><a href="javascript: orderPreview('<%=invoiceHeader.getIcPoHeader()%>'); void(0);" title="View Order"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderNumber", "Order Number", true)%>:&nbsp;</a></b></td>
						<% } else { %>
							<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-orderNumber", "Order Number")%>:</b></td>
						<% } %>
							<td nowrap valign=top>
								<a href="javascript: orderPreview('<%=invoiceHeader.getIcPoHeader()%>'); void(0);" title="View Order"><%=invoiceHeader.getPoNumber()%></a>
								<% if (invoiceHeader.getPoRelease().compareTo(new BigDecimal(0)) > 0) {%>
								&nbsp;<b>Release:</b>&nbsp;<%=invoiceHeader.getPoRelease()%><%}%>
								<% if (poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0) {%>
								&nbsp;<b>Rev:</b>&nbsp;<%=poHeader.getRevisionNumber() %><%}%>
							</td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-invoiceTotal")%>>
							<td nowrap align=right valign=top width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-invoiceTotal", "Invoice Total")%>:</b></td>
							<td valign=top><%=HiltonUtility.getFormattedCurrency(invoiceHeader.getInvoiceTotal(), s_curr_code, oid, true)%></td>
						</tr>
						<% if (oid.equalsIgnoreCase("hoy08p")) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-departmentCode")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-departmentCode", "Lab")%>:</b></td>
							<td nowrap valign=top><%=invoiceHeader.getDepartmentCode()%></td>
						</tr>
						<% } %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-orderedByName")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-orderedByName", "Ordered By")%>:</b></td>
							<td nowrap valign=top><%=invoiceHeader.getOrderByName()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-orderedByEmail")%>>
							<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-orderedByEmail", "Email")%>:</b></td>
							<td valign=top><%=invoiceHeader.getOrderByEmail()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-orderedByPhone")%>>
							<td nowrap align=right valign=top><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-orderedByPhone", "Phone")%>:</b></td>
							<td valign=top><%=invoiceHeader.getOrderByPhone()%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
<%
	Address shipTo = (Address) invoiceHeader.getShipToAddress();
	if (shipTo == null)
	{
		shipTo = new Address();
	}
%>
			<td width=300px align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;Shipping Information</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shipToCode")%>><td class=browseRow height=12px nowrap><b><%=invoiceHeader.getShipToCode()%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine1")%>><td class=browseRow height=12px nowrap><%=shipTo.getAddressLine1()%></td></tr>
						</table>

						<table id=supplierRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(shipTo.getAddressLine2()))
		{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine2")%>><td class=browseRow height=12px nowrap><%=shipTo.getAddressLine2()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(shipTo.getAddressLine3()))
		{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine3")%>><td class=browseRow height=12px nowrap><%=shipTo.getAddressLine3()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(shipTo.getAddressLine4()))
		{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine4")%>><td class=browseRow height=12px nowrap><%=shipTo.getAddressLine4()%></td></tr>
<%	}%>
						<tr><td class=browseRow height=12px nowrap><%=shipTo.getCity()%>&nbsp;<%=shipTo.getState()%>&nbsp;<%=shipTo.getPostalCode()%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-country")%>><td class=browseRow height=12px nowrap><%=shipTo.getCountry()%></td></tr>
						</table>

<%	if (!HiltonUtility.isEmpty(invoiceHeader.getShipToContact())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-attention", "Attn")%>:&nbsp;<%=invoiceHeader.getShipToContact()%></td></tr>
						</table>
<%	} %>
					</td>
				</tr>
				</table>
			</td>
			<td rowspan=4 align=right valign=top><%@ include file="/invoice/iv_sidebar.jsp" %></td>
		</tr>

		<tr><td colspan="2">
		<table style="margin-left: 17px" border="0">
			<tr<%=HtmlWriter.isVisible(oid, "ivc-generalDescription")%>>
			  	<td valign=top align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-generalDescription", "Description", false)%>:</b></td>
			    <td> <textarea readonly class="browseRow" style="border: 0" style="overflow:hidden" cols="80" rows="5" >${esapi:encodeForHTML(invoiceHeader.invoiceDesc)}</textarea></td>
			</tr>
			<%if(oid.equalsIgnoreCase("hoy08p") && s_ivc_status.compareTo(DocumentStatus.IVC_PENDING_PAYMENT) < 0) { %>
			  	<tr>
			  		<td valign=top align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateExported", "Date Exported")%>:</b></td>
			    	<td nowrap valign=top><%=invoiceHeader.getLastExtract()%></td>
			  	</tr>
				<tr>&nbsp</tr>
			<% } %>
			<%if((oid.equalsIgnoreCase("msg07p") || oid.equalsIgnoreCase("hoy08p")) && s_ivc_status.compareTo(DocumentStatus.IVC_PENDING_PAYMENT) >= 0 ) { %>
			  	<tr <%=HtmlWriter.isVisible(oid, "exportDate")%>>
					<td valign=top align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "exportDate", "Date Exported")%>:</b></td>
					<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getDateExported(), oid, userDateFormat)%></td>
			  	</tr>
			  	<tr<%=HtmlWriter.isVisible(oid, "batchGroup")%>>
			  		<td valign=top align="right"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "batchGroup", "Batch Group ID")%>:</b></td>
			    	 	<td nowrap valign=top><%=invoiceHeader.getLastExtract()%></td>
			  	</tr>
				<tr>&nbsp</tr>
			<% } %>
		</table>
		</td></tr>


<%	if (oid.equalsIgnoreCase("ctb08p")) { %>
		<tr>
			<td width=300px align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;IMIS Information</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-obmoNumber")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoNumber", "OBMO Number")%>:</b></td>
							<td nowrap valign=top><%=invoiceHeader.getObmoNumber()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-obmoDate")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoDate", "OBMO Date")%>:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getObmoDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-obmoTotal")%>>
							<td nowrap align=right valign=top width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoTotal", "OBMO Total")%>:</b></td>
							<td valign=top><%=HiltonUtility.getFormattedCurrency(invoiceHeader.getObmoTotal(), s_curr_code, oid, true)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-obmoCurrency")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoCurrency", "Currency")%>:</b></td>
							<td nowrap valign=top><%=s_curr_code%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
			<td width=300px align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=250px class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=0 width=250px class=browseRow>
						<tr>
							<td class=browseRow height=18px nowrap>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-inspectionDate")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-inspectionDate", "Inspection Date")%>:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getInspectionDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-processedDate")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-processedDate", "Processed Date")%>:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getProcessedDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-imisApprovedDate")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-imisApprovedDate", "IMIS Approved Date")%>:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getImisApprovedDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-imisPaymentDate")%>>
							<td nowrap align=right valign=top width=40%><b> <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-imisPaymentDate", "IMIS Payment Date")%>:</b></td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getImisPaymentDate(), oid, userDateFormat)%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td colspan=2>&nbsp;</td></tr>
<%	}
		if (processMap.containsKey("PAYMENT_INFO")) {
			InvoiceVendor vendor= (InvoiceVendor) request.getAttribute("invoiceVendor");
			InvoiceAddress vendAddr = (InvoiceAddress) request.getAttribute("invoiceAddress");
			if (vendor == null) {
				vendor = new InvoiceVendor();
			}
			if (vendAddr == null) {
				vendAddr = new InvoiceAddress();
			}
%>
		<tr>
			<td width=300px align=center valign=top>
				<table id=supplierTable border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;Supplier Information</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=supplierRows>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-supplier")%>><td class=browseRow height=12px nowrap><b><%=invoiceHeader.getVendorId()%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine1")%>><td class=browseRow height=12px nowrap><%=vendAddr.getAddressLine1()%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
	<%	if (!HiltonUtility.isEmpty(vendAddr.getAddressLine2())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine2")%>><td class=browseRow height=12px nowrap><%=vendAddr.getAddressLine2()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(vendAddr.getAddressLine3())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine3")%>><td class=browseRow height=12px nowrap><%=vendAddr.getAddressLine3()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(vendAddr.getAddressLine4())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine4")%>><td class=browseRow height=12px nowrap><%=vendAddr.getAddressLine4()%></td></tr>
	<%	}%>
						<tr><td class=browseRow height=12px nowrap><%=vendAddr.getCityStatePostal()%></td></tr>
	<%if (!HiltonUtility.isEmpty(vendAddr.getCountry())) {%>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-country")%>><td class=browseRow height=12px nowrap><%=vendAddr.getCountry()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(invoiceHeader.getContactName())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-attention", "Attn")%>: <%=invoiceHeader.getContactName()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(invoiceHeader.getApReference())) { %>
			<tr <%=HtmlWriter.isVisible(oid, "ivc-apreference")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-apreference", "AP Reference")%>: <%=invoiceHeader.getApReference()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(invoiceHeader.getVendorAccount())) { %>
			<tr <%=HtmlWriter.isVisible(oid, "ivc-vendoraccount")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-vendoraccount", "Vendor Account")%>: <%=invoiceHeader.getVendorAccount()%></td></tr>
	<%	} %>
						</table>

					</td>
				</tr>
				</table>
			</td>
<%	}
		if (processMap.containsKey("PAYMENT_INFO")) {
			Address billTo = (Address) invoiceHeader.getBillToAddress();
			if (billTo == null) {
				billTo = new Address();
			}
%>
			<td width=300px align=center valign=top>
				<table id=paymentTable border=0 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=250px class=browseHdr>
						<tr>
							<td class=browseHdr height=18px nowrap>&nbsp;Billing Information</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow id=billingRows>
						<table border=0 cellspacing=0 cellpadding=2 width=210px class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-billToCode")%>><td class=browseRow height=12px nowrap><b><%=invoiceHeader.getBillToCode()%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine1")%>><td class=browseRow height=12px nowrap><%=billTo.getAddressLine1()%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
	<%	if (!HiltonUtility.isEmpty(billTo.getAddressLine2())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine2")%>><td class=browseRow height=12px nowrap><%=billTo.getAddressLine2()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(billTo.getAddressLine3())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine3")%>><td class=browseRow height=12px nowrap><%=billTo.getAddressLine3()%></td></tr>
	<%	}
			if (!HiltonUtility.isEmpty(billTo.getAddressLine4())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine4")%>><td class=browseRow height=12px nowrap><%=billTo.getAddressLine4()%></td></tr>
	<%	} %>
						<tr><td class=browseRow height=12px nowrap><%=billTo.getCityStatePostal()%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-country")%>><td class=browseRow height=12px nowrap><%=billTo.getCountry()%></td></tr>
						</table>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
	<%	if (!HiltonUtility.isEmpty(invoiceHeader.getBillToContact())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-bil-attention", "Attn")%>:&nbsp;<%=invoiceHeader.getBillToContact()%></td></tr>
	<%	} %>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
<%	} %>
		</table>
	</td>
</tr>
</table>

<br>

<%//	if (processMap.containsKey("ITEMS")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td width=3% class=browseHdr nowrap>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> width=13% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> width=8% class=browseHdr align=right nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-quantity", "Quantity")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> width=8% class=browseHdr align=center nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-uom", "UOM")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> width=13% class=browseHdr align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-unitCost", "Unit Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> width=13% class=browseHdr align=right nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-extendedCost", "Ext Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-lineStatus")%> width=15% class=browseHdr align=center nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-lineStatus", "Line Status")%></td>
							<td width=3% class=browseHdr>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
<%
		List lineitems = (List) request.getAttribute("invoiceLineList");
		List lineudf = (List) request.getAttribute("poLineOrderList");

		if (lineitems != null && lineitems.size() > 0)
		{
			for (int i = 0; i < lineitems.size(); i++)
			{
				InvoiceLine invoiceLine = (InvoiceLine) lineitems.get(i);
				String poLine = "";

				if (lineudf != null && lineudf.size() > 0 && i < lineudf.size())
				{
					poLine = (String) lineudf.get(i);
				}

				BigDecimal bdQuantity = invoiceLine.getQuantity();
				BigDecimal bdUnitPrice = invoiceLine.getUnitPrice();
				BigDecimal bdUmFactor = invoiceLine.getUmFactor();
				if (bdUmFactor.compareTo(new BigDecimal(0)) == 0)
				{
					bdUmFactor = new BigDecimal(1);
				}
				BigDecimal bdExtCost = bdQuantity.multiply(bdUnitPrice).multiply(bdUmFactor);
				if (s_curr_code.equals("JPY"))
				{
					bdExtCost = bdExtCost.setScale(0, BigDecimal.ROUND_HALF_UP);
				}
				String s_asset = invoiceLine.getAsset();
				String s_taxable = invoiceLine.getTaxable();

				List accountList = (List) invoiceLine.getAccountList();
%>
						<tr id=itemRows>
							<td width=3% class=browseRow nowrap align=right><%=i+1%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> width=13% class=browseRow nowrap>
								<%=invoiceLine.getItemNumber()%>
								<tsa:hidden name="InvoiceLine_icPoHeader" value="<%=invoiceLine.getIcPoHeader()%>"/>
								<tsa:hidden name="InvoiceLine_icPoLine" value="<%=invoiceLine.getIcPoLine()%>"/>
								<tsa:hidden name="InvoiceLine_icIvcLine" value="<%=invoiceLine.getIcIvcLine()%>"/>
								<tsa:hidden name="InvoiceLine_itemNumber" value="<%=invoiceLine.getItemNumber()%>"/>
								<tsa:hidden name="InvoiceLine_taxable_<%=i%>" value="<%=s_taxable%>"/>
								<tsa:hidden name="InvoiceLine_asset_<%=i%>" value="<%=s_asset%>"/>
								<tsa:hidden name="InvoiceLine_quantity" value="<%=HiltonUtility.getFormattedQuantity(invoiceLine.getQuantity(), oid)%>"/>
								<tsa:hidden name="InvoiceLine_unitPrice" value="<%=HiltonUtility.getFormattedPrice(invoiceLine.getUnitPrice(), oid)%>"/>
								<tsa:hidden name="InvoiceLine_lineTotal" value="<%=bdExtCost%>"/>
								<tsa:hidden id="icReqLine_<%//=i%>" name="icReqLine_<%//=i%>" value="<%//=reqLine.getIcReqLine()%>"/>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQuantity(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> width=8% class=browseRow align=center nowrap><%=invoiceLine.getUmCode()%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> width=13% class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(invoiceLine.getUnitPrice(), s_curr_code, oid, false)%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> width=13% class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(bdExtCost, s_curr_code, oid, false)%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-lineStatus")%> width=15% class=browseRow align=center nowrap><%=DocumentStatus.toString(invoiceLine.getStatus(), oid)%></td>
							<td width=3% class=browseRow>&nbsp;</td>
						</tr>
						<tr id=itemRows>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=6 class=browseRow><%=invoiceLine.getDescription()%></td>
						</tr>
<%			if (accountList != null)
				{
					BigDecimal bd_total_perc = new BigDecimal(0.00);
					BigDecimal bd_total_amt = new BigDecimal(0.00);

					for (int ix = 0; ix < accountList.size(); ix++)
					{
									Account account = (Account) accountList.get(ix);

									BigDecimal bd_alloc_perc = account.getAllocPercent();
									BigDecimal bd_alloc_amt = account.getAllocAmount();

									if (bd_alloc_perc == null)	{	bd_alloc_perc = new BigDecimal(0);	}
									if (bd_alloc_amt == null)	{	bd_alloc_amt = new BigDecimal(0);	}

									bd_total_perc = bd_total_perc.add(bd_alloc_perc);
									bd_total_amt = bd_total_amt.add(bd_alloc_amt);

									String	s_account = "";
									String	s_accArray[] = new String[15];

									s_accArray[0] = account.getFld1();
									s_accArray[1] = account.getFld2();
									s_accArray[2] = account.getFld3();
									s_accArray[3] = account.getFld4();
									s_accArray[4] = account.getFld5();
									s_accArray[5] = account.getFld6();
									s_accArray[6] = account.getFld7();
									s_accArray[7] = account.getFld8();
									s_accArray[8] = account.getFld9();
									s_accArray[9] = account.getFld10();
									s_accArray[10] = account.getFld11();
									s_accArray[11] = account.getFld12();
									s_accArray[12] = account.getFld13();
									s_accArray[13] = account.getFld14();
									s_accArray[14] = account.getFld15();

									for (int j = 0; j <15; j++)
									{
										if (s_accArray[j] != null && s_accArray[j].trim().length() > 0)
										{
											if (s_account.length() > 0)
											{
												s_account = s_account + s_account_separator + s_accArray[j];
											}
											else
											{
													s_account = s_accArray[j];
											}
										}
									}
%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>
								<table border=0>
								<tr>
									<%	if (s_view_item_account.equalsIgnoreCase("Y")) { %>
										<td><b>Account:</b>&nbsp;<a href="javascript: viewItemAccount('<%=account.getComp_id().getIcLine()%>', '<%=account.getComp_id().getSequence()%>','<%=ix%>','<%=lineitems.size()%>'); void(0);" title="Click here to View/Modify Item Account."><%=s_account%></a>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getCurrency(bd_alloc_amt, s_curr_code, oid)%></td>
										<%if(!HiltonUtility.isEmpty(poLine)){%>
											<tr><td colspan=6 class=browseRow><b><%=poLine%></b></td></tr>
									<%    }
										} else { %>
										<td><b>Account:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getCurrency(bd_alloc_amt, s_curr_code, oid)%></td>
										<%if(!HiltonUtility.isEmpty(poLine)){%>
											<tr><td colspan=6 class=browseRow><b><%=poLine%></b></td></tr>
									<%    }
										} %>
									<%	if (oid.equalsIgnoreCase("ctb08p")) { %>
<!-- 									<td width="25px">&nbsp;</td>
									<td width="25px"><b>LQ:</b><%=account.getImisLiquidated()%></td>
									<td width="100px"><b>PENC:</b><%=account.getImisId()%>&nbsp;&nbsp;<%=account.getImisLine()%></td>
									<td width="150px"><b>Obligation #:</b><%=account.getImisObmo()%>&nbsp;&nbsp;<%=account.getImisSequence()%>&nbsp;&nbsp;<%=account.getImisOblig()%></td>
-->
									<%	} %>
								</tr>
								</table>
							</td>
						</tr>
<%				}
						} %>

						<tr><td colspan=7><hr></td></tr>
<%		}
		} %>
						</table>
					</td>
				</tr>
				</table>

<%
	//			if (processMap.containsKey("HEADER_TOTALS")) {
	BigDecimal  bdSubtotal = invoiceHeader.getInvoiceSubtotal();
	BigDecimal  bdDiscount = invoiceHeader.getInvoiceDiscount();
	BigDecimal  bdAdjustment = invoiceHeader.getInvoiceAdjustment();
	BigDecimal  bdTax = invoiceHeader.getInvoiceTax();
	BigDecimal  bdUseTax = invoiceHeader.getUseTax();
	BigDecimal  bdShipping = invoiceHeader.getInvoiceShipping();
	BigDecimal  bdOther = invoiceHeader.getInvoiceOther();
	BigDecimal  bdRejecting = invoiceHeader.getInvoiceRejecting();

	BigDecimal bdTotal = bdSubtotal.subtract(bdDiscount);
	//bdTotal = bdTotal.add(bdTax).add(bdUseTax).add(bdShipping).add(bdOther);
	bdTotal = bdTotal.add(bdTax).add(bdShipping).add(bdOther);
	//bdTotal = bdTotal.subtract(bdRejecting);
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-subtotal")%>>
					<td colspan="3" width=82% class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-subtotal", "Subtotal")%>:</td>
					<td width=13% class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceSubtotal(), s_curr_code, oid, false)%></td>
					<td width=5% class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-Adjustment")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;Adjustment:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceAdjustment(), s_curr_code, oid)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-taxAmount")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-taxAmount", "Tax")%>:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceTax(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-usetax")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-usetax", "Use Tax")%>:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(invoiceHeader.getUseTax(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shippingCharges")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shippingCharges", "Shipping")%>:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceShipping(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-otherCharges")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-otherCharges", "Other")%>:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceOther(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-rejectedReason")%>>
					<td width="50%" class=browseRow nowrap align=right>
						<%	if (!HiltonUtility.isEmpty(invoiceHeader.getReasonCode())) {%>
						&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-rejectedReason", "Reason")%>:
						<%	} %>
					</td>
					<td class=browseRow nowrap align=left><%=invoiceHeader.getReasonCode()%></td>
					<td class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-rejectedAmount", "Rejecting")%>:</td>
					<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceRejecting(), s_curr_code, oid, false)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-invoiceTotal")%>>
			<td class=browseRow nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-invoiceTotal", "Invoice Total")%>:</td>
			<td class=browseRow nowrap align=left><%=HiltonUtility.getCurrency(invoiceHeader.getInvoiceTotal(), s_curr_code, oid)%></td>
			<td class=processOn nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-total", "Total")%>:</b></td>
			<td class=browseRow nowrap align=right><b><%=HiltonUtility.getCurrency(bdTotal, s_curr_code, oid)%></b></td>
			<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				</table>
<%//			} */%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%//	} %>

<%	String isInvoiceBalanceZero = "N";
	if (bdTotal.compareTo(invoiceHeader.getInvoiceTotal()) == 0)
		isInvoiceBalanceZero = "Y";
%>
<tsa:hidden name="isInvoiceBalanceZero" value="<%=isInvoiceBalanceZero%>"/>

<br>

<%//	if (processMap.containsKey("HEADER_ACCOUNTS")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=75% height=18px class=browseHdr>&nbsp;<b>Account</b></td>
							<td width=25% height=18px class=browseHdr align=right>&nbsp;<b>Amount Alloc.</b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
			List accList = (List) request.getAttribute("accountRollupList");
			BigDecimal bd_total_amt = new BigDecimal(0.00);
			if (accList != null)
			{
				for (int i = 0; i < accList.size(); i++)
				{
					AccountRollup acctRollup = (AccountRollup) accList.get(i);
					BigDecimal bd_alloc_amt = acctRollup.getAllocAmount();
					String	s_account = ReportUtils.getAcctString(acctRollup.getFld1(), acctRollup.getFld2(), acctRollup.getFld3(), acctRollup.getFld4(), acctRollup.getFld5(), acctRollup.getFld6(), acctRollup.getFld7(), acctRollup.getFld8(), acctRollup.getFld9(), acctRollup.getFld10(), acctRollup.getFld11(), acctRollup.getFld12(), acctRollup.getFld13(), acctRollup.getFld14(), acctRollup.getFld15(), oid);
					bd_total_amt = bd_total_amt.add(bd_alloc_amt);
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=75% class=browseRow><%=s_account%></td>
					<td width=25% class=browseRow align=right><%=HiltonUtility.getCurrency(bd_alloc_amt, s_curr_code, oid, false)%></td>
				</tr>
				</table>
<%			}
				if (accList.size() > 0) {	%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=2 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td width=75% class=browseRow>&nbsp;</td>
					<td width=25% class=browseRow align=right><%=HiltonUtility.getCurrency(bd_total_amt, s_curr_code, oid)%></td>
				</tr>
				</table>
<%			}
			}
			if (accList == null || accList.size() == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no accounts associated with this invoice.</td></tr>
				</table>
<%		}	%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<tsa:hidden name="fullInvoiceTotal" value="<%=bdTotal%>"/>
<tsa:hidden name="amntInvoiceTotal" value="<%=bd_total_amt%>"/>

<%	List accountList = invoiceHeader.getAccountList();
		String accountTitle = "Account";
		String className = "browseHdr";
		if (accountList != null && accountList.size() > 0)
		{ %>
			<%//@ include file="/invoice/display_account.jsp"%>
<%		} %>

<%	accountList = (List) request.getAttribute("taxAccountList");
		accountTitle = "Tax Account";
		if (accountList != null && accountList.size() > 0)
		{ %>
			<%//@ include file="/invoice/display_account.jsp"%>
<%		} %>

<%	accountList = (List) request.getAttribute("usetaxAccountList");
		accountTitle = "Use Tax Account";
		if (accountList != null && accountList.size() > 0)
		{ %>
			<%//@ include file="/invoice/display_account.jsp"%>
<%		} %>

<%	accountList = (List) request.getAttribute("shippingAccountList");
		accountTitle = "Shipping Account";
		if (accountList != null && accountList.size() > 0)
		{ %>
			<%//@ include file="/invoice/display_account.jsp"%>
<%		} %>


<%	accountList = (List) request.getAttribute("otherAccountList");
		accountTitle = "Other Account";
		if (accountList != null && accountList.size() > 0)
		{ %>
			<%//@ include file="/invoice/display_account.jsp"%>
<%		} %>

<%//	} %>

<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=75% height=18px class=browseHdr>&nbsp;<b>Comment</b></td>
							<td width=8% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
							<td width=7% height=18px class=browseHdr align=center>&nbsp;<b>Bold</b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Placement</b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
	List cmtList = (List) invoiceHeader.getDocCommentList();
	int ci = 0;
	if (cmtList != null)
	{
		for (int i = 0; i < cmtList.size(); i++)
		{
			DocComment docComment = (DocComment) cmtList.get(i);
			if (docComment == null) {
				continue;
			}
			DocText docText = docComment.getDocText();
			if (docText == null) {
				docText = new DocText();
			}
			String s_cmt_title = docComment.getCommentTitle();
			String s_cmt_print = docComment.getCommentPrint();
			String s_cmt_bold = docComment.getCommentBold();
			String s_cmt_place = docComment.getCommentPlace();
			String s_cmt_text = docText.getStdText();

			if (s_cmt_place.equals("A"))
			{
				s_cmt_place = "After";
			}
			else if (s_cmt_place.equals("B"))
			{
				s_cmt_place = "Before";
			}
			ci++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=75% class=browseRow><%=s_cmt_title%></td>
					<td width=8% class=browseRow align=center valign=top><%=s_cmt_print%></td>
					<td width=7% class=browseRow align=center valign=top><%=s_cmt_bold%></td>
					<td width=10% class=browseRow align=center valign=top><%=s_cmt_place%></td>
				</tr>
				</table>

				<table class=browseRow>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%><%=s_cmt_text%></td>
				</tr>
				</table>
<%		}
		}
		if (ci == 0) {	%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no comments associated with this invoice.</td></tr>
				</table>
<%	}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}	%>

<%   if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdr>
						<tr>
							<td width=80% height=18px class=browseHdr>&nbsp;<b>Attachment</b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;<b>Print</b></td>
							<td width=10% height=18px class=browseHdr align=center>&nbsp;</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
<%
		List attachmentList = (List) invoiceHeader.getDocAttachmentList();
			int ai = 0;
			if (attachmentList != null) {
				for(int i = 0; i < attachmentList.size(); i++) {
					DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
					if (docAttachment == null) {
						continue;
					}
					String	filename = docAttachment.getDocFilename();
					String	ext = filename.substring(filename.lastIndexOf(".") + 1);
					ai++;
%>

				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=80% class=browseRow>
						<table border=0 cellpadding=0 cellspacing=0 width=100% class=browseRow>
						<tr>
							<td width=25px align=center valign=middle>
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
							</td>
							<td>
								<a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
								<tsa:hidden name="docFilename" value="<%=filename%>"/>
							</td>
						</tr>
						</table>
					</td>
					<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPrint()%></td>
					<td width=10% class=browseRow align=center valign=top></td>
				</tr>
				</table>

	<% 		}
			}
			if (ai == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no attachments associated with this invoice.</td></tr>
				</table>
<%		}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}%>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	//hideArea("navTable");
	//displayArea("menubarSpacer");
	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("Invoice Voucher") < 0)
	{
		setNavCookie(currentpage, currentprocessmethod, "Invoice Voucher");
	}

	function openDocument(row)
	{
			var filename = "";
		if (document.all("docFilename").length > 1)
		{
			filename = frm.docFilename[row].value;
		}
		else
		{
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

	function getRoutingList(action)
	{
		frm.invoiceaction.value = action;
		var icHeader = "<%=invoiceHeader.getIcIvcHeader()%>";
		popupParameters = "InvoiceHeader_icIvcHeader=" + icHeader;
		popupParameters = popupParameters + ";ApprovalLog_icHeader=" + icHeader;
		doSubmitToPopup('/invoice/iv_display_routing_list.jsp', 'InvoiceDisplayRoutingList', 'width=675px', 'height=450px');
	}

	function viewHistory()
	{
		popupParameters = "HistoryLog_icHeaderHistory=<%=invoiceHeader.getIcHeaderHistory()%>;formtype=IVC;InvoiceLine_icIvcHeader=<%=invoiceHeader.getIcIvcHeader()%>;invoiceNumber=<%=s_ivc_number%>";
		doSubmitToPopup('/invoice/iv_history.jsp', 'HistoryLogRetrieveByHistoryHeader', 'width=675px', 'height=450px');
	}

	function invoiceCancel()
	{
		if (verifyAction('Cancel this Invoice?'))
		{
			doSubmit('/invoice/iv_review.jsp', 'InvoiceCancel;InvoiceRetrieve');
		}
	}

	function viewPayments()
	{
		<% if (! codaEnabled) { %>
			popupParameters = "InvoiceHeader_icIvcHeader=<%=invoiceHeader.getIcIvcHeader()%>";
			doSubmitToPopup('/invoice/payments.jsp', 'PaymentAccountRetrieveByInvoice', 'WIDTH=700px', 'HEIGHT=300px');
		<%} else {	%>
			popupParameters = "InvoiceHeader_icIvcHeader=<%=invoiceHeader.getIcIvcHeader()%>;invoiceNumber=<%=invoiceHeader.getInvoiceNumber()%>;poNumber=<%=invoiceHeader.getPoNumber()%>;vendorId=V*";
			doSubmitToPopup('/invoice/payments.jsp', 'CodaPaymentRetrieve', 'WIDTH=700px', 'HEIGHT=300px');
		<% } %>
	}

	function orderPreview(icPoHeader)
    {
		popupParameters = "PoHeader_icPoHeader=" + icPoHeader;
		doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function viewItemAccount(ic, row, accountRow, totalRow)
	{
			frm.originalAccount_icLine.value = ic;
			frm.lineNumber.value = row;
			frm.formType.value = "IVL";
			frm.showItemAccount.value = "Y";

			if(totalRow > 1){
			   frm.invoiceLine_icIvcLine.value = frm.InvoiceLine_icIvcLine[accountRow].value;
			}else{
			   frm.invoiceLine_icIvcLine.value = frm.InvoiceLine_icIvcLine.value;
			}
			doSubmit("/invoice/iv_accounts_ln.jsp", "InvoiceItemAccountRetrieve");
	}
	function orderPreview(icPoHeader)
	{
		popupParameters = "PoHeader_icPoHeader=" + icPoHeader;
		doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function resendInvoiceToApprover() {
		if (verifyAction("Resend this Invoice to the current approver?")) {
			var icHeader = "<%=invoiceHeader.getIcIvcHeader()%>";
			popupParameters = "SendQueue_docic=" + icHeader;

			doSubmitToPopup('/invoice/iv_approver_email_resent.jsp', 'InvoiceResendApproverEmail', 'width=680px', 'height=200px');
		}
		else {
			return;
		}
	}

// End Hide script -->
</SCRIPT>