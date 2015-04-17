<%@page import="com.tsa.puridiom.paymentterm.tasks.PaymentTermManager"%>
<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/JsSimpleDateFormat.js"></SCRIPT>
<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_price_decimals = propertiesManager.getProperty("MISC", "PriceDecimals", "2");
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	
	String	s_ivc_ic_header = (String) request.getAttribute("InvoiceHeader_icIvcHeader");
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	s_ivc_status = invoiceHeader.getStatus();
	String  s_ivc_terms_code = HiltonUtility.ckNull(invoiceHeader.getTermsCode());
	PaymentTerm paymentTerm = (PaymentTerm)PaymentTermManager.getInstance().getPaymentTerm(oid, s_ivc_terms_code);
	
	boolean bCreatedFromPo = false;
	if ((invoiceHeader.getIcPoHeader().compareTo(new BigDecimal(0)) > 0))
	{
		bCreatedFromPo = true;
	}
	String	s_current_process = "GENERAL_INFO";
	String	s_current_page = "/invoice/iv_general_info.jsp";
	String	s_current_method = "InvoiceValidateNumber";
	String	s_current_process_method = "";

	String	duplicateNumberErrorMsg = (String) request.getAttribute("duplicateNumberErrorMsg");
	String	s_order_status = HiltonUtility.ckNull((String)request.getAttribute("PoHeader_status"));

	String	s_SelectBilltoShipto = propertiesManager.getProperty("MISC", "SHOWSELECTBILLTOSHIPTO", "Y");
	String	defaultInvoiceNumber = HiltonUtility.ckNull(propertiesManager.getProperty("VOUCHER OPTIONS", "DEFAULTINVOICENUMBER", "")); //Enter Invoice
	String	notAllowShiptEdit = HiltonUtility.ckNull(propertiesManager.getProperty("VOUCHER OPTIONS","NOTALLOWSHIPTOEDIT","N"));
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
<tsa:hidden name="InvoiceHeader_fiscalYear" value="<%=invoiceHeader.getFiscalYear()%>"/>
<tsa:hidden name="HistoryLog_icHeaderHistory" value="<%=invoiceHeader.getIcHeaderHistory()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="DocComment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/invoice/iv_general_info.jsp"/>
<tsa:hidden name="duplicateNumberFailurePage" value="/invoice/iv_general_info.jsp"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></div>
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
			<td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus(), oid)%></td>
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
<%@ include file="/invoice/iv_info.jsp" %>
<br>

<%	if (!HiltonUtility.isEmpty(duplicateNumberErrorMsg)) { %>
<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center class="error">
		<%=duplicateNumberErrorMsg%>
	</td>
</tr>
</table>

<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width="100%">
		<tr>
			<td valign="top">
				<table border=0 cellpadding=0 cellspacing=0 width="50%" align=center>
				<tsa:tr>
					<tsa:td field="ivc-invoiceNumber" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="ivc-invoiceNumber" defaultString="Invoice Number" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="ivc-invoiceNumber" >
					<% String invoiceNumber = "";
					if (HiltonUtility.isNA(invoiceHeader.getInvoiceNumber())){
					invoiceNumber = defaultInvoiceNumber;
					}
					else {invoiceNumber = invoiceHeader.getInvoiceNumber();
					}%>
					<tsa:input type="mintext" title="Enter Invoice Number" name="InvoiceHeader_invoiceNumber" tabIndex="1" maxLength="20" value="<%=invoiceNumber%>" onclick = "if(this.value=='Enter Invoice') this.value='';" onchange="if(this.value=='') setDefaultInvoiceNumber(this); removeBadChars(this); upperCase(this);checkInvoiceNumberLength(this);" onkeyup="checkInvoiceNumberLength(this);" ></tsa:input>
					<tsa:hidden name="originalInvoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
						<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_INPROGRESS) == 0) { %>
						<img src="<%=contextPath%>/images/question_blue.gif" title="<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-numhelp", "Please enter a valid Invoice number.")%>" valign=bottom border=0>
						<% }  %>
					</tsa:td>
				</tsa:tr>
				<tsa:tr>
					<tsa:td field="ivc-invoiceTotal" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="ivc-invoiceTotal" defaultString="Invoice Total" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="ivc-invoiceTotal" >
					<tsa:input type="mintext" title="Enter Invoice Total" name="InvoiceHeader_invoiceTotal" tabIndex="5" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getInvoiceTotal(), oid)%>" style="text-align:right" onchange="formatMe(this);"></tsa:input>
					</tsa:td>
				</tsa:tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-currency")%>>
					<td align="right" nowrap><a href="javascript: browseLookup('InvoiceHeader_currencyCode', 'curr_code'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-currency", "Currency")%> for this invoice or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-currency", "Currency")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "inv-currency", "Currency", true)%></a>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_currencyCode" tabindex="10" size=15 maxlength=15 value="<%=invoiceHeader.getCurrencyCode()%>" onchange="upperCase(this); currencyChangeWarning(this.value);"></td>
				</tr>
				<tsa:tr>
					<tsa:td field="ivc-invoiceDate" width="" align="right" noWrap="nowrap" >
					<tsa:label labelName="ivc-invoiceDate" defaultString="Invoice Date" noinstance="yes"/>:&nbsp;
					</tsa:td>
					<tsa:td field="ivc-invoiceDate" >
					<tsa:input type="mintext" title="Enter Invoice Date" name="InvoiceHeader_invoiceDate" tabIndex="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), oid, userDateFormat)%>" onchange="updateInvoiceDueTime();">
					<a href="javascript: setEdit('invoiceDate'); show_calendar('InvoiceHeader_invoiceDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</tsa:input>
					</tsa:td>
				</tsa:tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceReceivedDate")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceReceivedDate", "Date Received", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceReceivedDate")%> >
						<input type=text title="Enter Invoice Received Date" name="InvoiceHeader_invoiceReceivedDate" tabindex="20" size="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceReceivedDate(), oid, userDateFormat)%>">
						<a href="javascript: setEdit('invoiceReceivedDate'); show_calendar('InvoiceHeader_invoiceReceivedDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceDueDate")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceDueDate", "Invoice Due Date", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceDueDate")%> >
						<input type=text title="Enter Payment Due Date" name="InvoiceHeader_paymentDueDate" tabindex="25" size="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getPaymentDueDate(), oid, userDateFormat)%>">
						<a href="javascript: setEdit('invoiceDueDate'); show_calendar('InvoiceHeader_paymentDueDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceDateEntered")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceDateEntered", "Date Entered", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceDateEntered")%> >
						<input type=text title="Date Entered" name="InvoiceHeader_dateEntered" size="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getDateEntered(), oid, userDateFormat)%>" disabled>
						<a href="javascript: setEdit('invoiceDateEntered'); show_calendar('InvoiceHeader_dateEntered', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-enteredby")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-enteredby", "Entered By")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_enteredBy" tabindex="30" size="20" value="<%=invoiceHeader.getEnteredBy()%>" READONLY DISABLED></td>
				</tr>
				<tr>
					<% if (bCreatedFromPo) {%>
						<td align=right <%=HtmlWriter.isVisible(oid, "orderNumber")%> nowrap><a href="javascript: orderPreview('<%=invoiceHeader.getIcPoHeader()%>'); void(0);" title="View Order"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderNumber", "Order Number", true)%>:&nbsp;</a></td>
					<% } else { %>
						<td align=right <%=HtmlWriter.isVisible(oid, "orderNumber")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderNumber", "Order Number", true)%>:&nbsp;</td>
					<% } %>
					<td nowrap <%=HtmlWriter.isVisible(oid, "orderNumber")%> >
						<input type=text title="Order Number" name="InvoiceHeader_poNumber" tabindex="35" size="15" value="<%=invoiceHeader.getPoNumber()%>" <% if (bCreatedFromPo) {%> READONLY DISABLED<% } %>>
						<% if (bCreatedFromPo && invoiceHeader.getPoRelease().compareTo(new BigDecimal(0)) > 0) { %>Release:&nbsp;<input type="text" name="InvoiceHeader_poRelease" tabindex="40" size="4" value="${esapi:encodeForHTMLAttribute(invoiceHeader.poRelease)}" READONLY DISABLED><% } %>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-inspectionDate")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-inspectionDate", "Inspection Date", true)%>:&nbsp;</td>
					<td>
						<input type="text" name="InvoiceHeader_inspectionDate" tabindex="45" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getInspectionDate(), oid, userDateFormat)%>">
						<a href="javascript: setEdit('inspectionDate'); show_calendar('InvoiceHeader_inspectionDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-processedDate")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-processedDate", "Processed Date", true)%>:&nbsp;</td>
					<td>
						<input type="text" name="InvoiceHeader_processedDate" tabindex="50" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getProcessedDate(), oid, userDateFormat)%>" <% if (bCreatedFromPo) {%> READONLY DISABLED<% } %>>
						<a href="javascript: setEdit('processedDate'); show_calendar('InvoiceHeader_processedDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-imisApprovedDate")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-imisApprovedDate", "IMIS Approved Date", true)%>:&nbsp;</td>
					<td>
						<input type="text" name="InvoiceHeader_imisApprovedDate" tabindex="55" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getImisApprovedDate(), oid, userDateFormat)%>" <% if (bCreatedFromPo) {%> READONLY DISABLED<% } %>>
						<a href="javascript: setEdit('imisApprovedDate'); show_calendar('InvoiceHeader_imisApprovedDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-imisPaymentDate")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-imisPaymentDate", "IMIS Payment Date", true)%>:&nbsp;</td>
					<td>
						<input type="text" name="InvoiceHeader_imisPaymentDate" tabindex="60" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getImisPaymentDate(), oid, userDateFormat)%>" <% if (bCreatedFromPo) {%> READONLY DISABLED<% } %>>
						<a href="javascript: setEdit('imisPaymentDate'); show_calendar('InvoiceHeader_imisPaymentDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
		        </tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "orderTotal")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderTotal", "Order Total", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "orderTotal")%> ><input type=text title="Enter Order Total" name="InvoiceHeader_poTotal" tabindex="65" size=15 value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getPoTotal(), oid)%>" style="text-align:right" onchange="formatMe(this);" <% if (bCreatedFromPo) { %> READONLY DISABLED<% } %> ></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "orderDate")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "orderDate", "Order Date", true)%>:&nbsp;</td>
					<td nowrap <%=HtmlWriter.isVisible(oid, "orderDate")%> >
						<input type=text title="Enter Order Date" name="InvoiceHeader_poDate" tabindex="70" size=15 value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getPoDate(), oid, userDateFormat)%>" <% if (bCreatedFromPo) { %> READONLY DISABLED <% } %>>
						<a href="javascript: setEdit('orderDate'); show_calendar('InvoiceHeader_poDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<% if (oid.equalsIgnoreCase("msg07p")) { %>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-order-status")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-order-status", "Order Status")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_orderStatus" tabindex="75" size="25" maxlength="60" value="<%=DocumentStatus.toString(s_order_status, oid)%>" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-orderedByName")%>>
					<td nowrap align="right"><a href="javascript: setEdit('orderByName'); browseLookup('InvoiceHeader_orderByName', 'user'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-orderedByName", "Ordered By")%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_orderByName" tabindex="80" size="20" maxlength="40" value="${esapi:encodeForHTMLAttribute(invoiceHeader.orderByName)}" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-owner-email")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-order-by", "Email")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_orderByEmail" tabindex="85" size="25" maxlength="60" value="${esapi:encodeForHTMLAttribute(invoiceHeader.orderByEmail)}" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-order-by")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-order-by", "Phone")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_orderByPhone" tabindex="90" size="20" maxlength="40" value="${esapi:encodeForHTMLAttribute(invoiceHeader.orderByPhone)}" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-department")%>>
					<td nowrap align="right"><a href="javascript: setEdit('department'); browseLookup('InvoiceHeader_departmentCode', 'department'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-department", "Department")%> for this invoice or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-department", "Department")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-department", "Department", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_departmentCode" tabindex="95" size="20" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.departmentCode)}" onChange="javascript:this.value=this.value.toUpperCase();"  <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-IV01")%>>
<%
		String s_udf_1_code = invoiceHeader.getUdf1Code();
		if (oid.equalsIgnoreCase("dtn07p"))
		{
			if (!HiltonUtility.isEmpty(s_udf_1_code))
			{
				Date d_udf_1_code = Dates.getDate(s_udf_1_code);
				s_udf_1_code = HiltonUtility.getFormattedDate(d_udf_1_code, oid, userDateFormat);
			}
		}
%>
					<td align="right" nowrap><a href="javascript: browseStd('InvoiceHeader_udf1Code', 'IV01'); void(0);"><tsa:label labelName="ivc-IV01" defaultString="UDF 1"/></a>:&nbsp;</td>

					<td>
						<input type="text" name="InvoiceHeader_udf1Code" tabindex="100" size="15" maxlength="15" value="<%=s_udf_1_code%>" onchange="upperCase(this);">
<%	if (oid.equalsIgnoreCase("dtn07p"))	{
%>
						<a href="javascript: show_calendar('InvoiceHeader_udf1Code', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
<%	} %>
					</td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV03")%>>
<%	if (oid.equalsIgnoreCase("TTR09P"))	{%>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV03", "UDF3", true)%>:&nbsp;</td>
<%	} else {%>
					<td align="right" nowrap><a href="javascript: setEdit('IV03'); browseStd('InvoiceHeader_udf3Code', 'IV03'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV03", "UDF3")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV03", "UDF3", true)%>:</a>&nbsp;</td>
<%	} %>
					<td><input type="text" name="InvoiceHeader_udf3Code" tabindex="105" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf3Code)}" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV05")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV05'); browseStd('InvoiceHeader_udf5Code', 'IV05'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV05", "UDF5")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV05", "UDF5", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf5Code" tabindex="110" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf5Code)}" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV07")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV07'); browseStd('InvoiceHeader_udf7Code', 'IV07'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV07", "UDF7")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV07", "UDF7", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf7Code" tabindex="115" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf7Code)}" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV09")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV09'); browseStd('InvoiceHeader_udf9Code', 'IV09'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV09", "UDF9")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV09", "UDF9", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf9Code" tabindex="120" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf9Code)}" onchange="upperCase(this);"></td>
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
			<td valign="top">
				<table border=0 cellpadding=0 cellspacing=0 width="50%" align=center>
				<tr>
					<%if(bCreatedFromPo && notAllowShiptEdit.equalsIgnoreCase("Y")) {%>
						<td align=right <%=HtmlWriter.isVisible(oid, "ivc-shipToCode")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shipToCode", "Ship To Code", true)%>:&nbsp;</td>
					<%} else {%>
						<td align=right <%=HtmlWriter.isVisible(oid, "ivc-shipToCode")%> nowrap><a href="javascript: browseLookup('InvoiceHeader_shipToCode', 'ship_to'); void(0);" title="Click here to choose the Ship To Code for this Invoice or enter the Ship To Code in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shipToCode", "Ship To Code", true)%></a>:&nbsp;</td>
					<% } %>
					<td <%=HtmlWriter.isVisible(oid, "ivc-shipToCode")%> ><input type=text title="Enter Ship To Code" name="InvoiceHeader_shipToCode" tabindex="125" size="25" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.shipToCode)}" onchange="upperCase(this); getNewInfo('shipto', this);" <% if (bCreatedFromPo && !oid.equalsIgnoreCase("TTR09P")) {%> READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine1")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-addressLine1", "Address 1")%>:&nbsp;</td>
					<td><input type="text" name="Address_addressLine1" tabindex="130" size="25" maxlength="40" value="<%=shipTo.getAddressLine1()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine2")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-addressLine2", "Address 2")%>:&nbsp;</td>
					<td><input type="text" name="Address_addressLine2" tabindex="135" size="25" maxlength="40" value="<%=shipTo.getAddressLine2()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine3")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-addressLine3", "Address 3")%>:&nbsp;</td>
					<td><input type="text" name="Address_addressLine3" tabindex="140" size="25" maxlength="40" value="<%=shipTo.getAddressLine3()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine4")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-addressLine4", "Address 4")%>:&nbsp;</td>
					<td><input type="text" name="Address_addressLine4" tabindex="145" size="25" maxlength="40" value="<%=shipTo.getAddressLine4()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-city")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-city", "City")%>:&nbsp;</td>
					<td><input type="text" name="Address_city" tabindex="150" size="25" maxlength="30" value="<%=shipTo.getCity()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-state")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-state", "State")%>:&nbsp;</td>
					<td><input type="text" name="Address_state" tabindex="155" size="25" maxlength="15" value="<%=shipTo.getState()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-zip")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-zip", "Zip/Postal Code")%>:&nbsp;</td>
					<td><input type="text" name="Address_postalCode" tabindex="160" size="25" maxlength="15" value="<%=shipTo.getPostalCode()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-country")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shp-country", "Country")%>:&nbsp;</td>
					<td><input type="text" name="Address_country" tabindex="165" size="25" maxlength="25" value="<%=shipTo.getCountry()%>" READONLY DISABLED></td>
				</tr>
				<tr>
					<%if(oid.equalsIgnoreCase("hoy08p") && (bCreatedFromPo)){%>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-billToCode")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-billToCode", "Bill To Code", true)%>:&nbsp;</td><%} else{%>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-billToCode")%> nowrap><a href="javascript: browseLookup('InvoiceHeader_billToCode', 'bill_to'); void(0);" title="Click here to choose the Bill To Code for this invoice or enter the Bill To Code in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-billToCode", "Bill To Code", true)%></a>:&nbsp;</td><%} %>
					<td <%=HtmlWriter.isVisible(oid, "ivc-billToCode")%> ><input type=text  title="Enter Bill To Code" name="InvoiceHeader_billToCode" tabindex="170" size="25" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.billToCode)}" onchange="upperCase(this);" <% if (bCreatedFromPo && !oid.equalsIgnoreCase("TTR09P")) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-billToAttn")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-billToAttn", "Attention", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-billToAttn")%> ><input type=text  title="Enter Bill To Contact" name="InvoiceHeader_billToContact" tabindex="175" size="25" maxlength="40" value="${esapi:encodeForHTMLAttribute(invoiceHeader.billToContact)}" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-obmoNumber")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoNumber", "OBMO Number", true)%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_obmoNumber" tabindex="180" size="15" maxlength="15" value="<%=invoiceHeader.getObmoNumber()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-obmoDate")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoDate", "OBMO Date", true)%>:&nbsp;</td>
					<td>
						<input type="text" name="InvoiceHeader_obmoDate" tabindex="185" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getObmoDate(), oid, userDateFormat)%>" READONLY DISABLED>
						<a href="javascript: setEdit('obmo'); show_calendar('InvoiceHeader_obmoDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-obmoTotal")%>>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoTotal", "OBMO Total", true)%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_obmoTotal" tabindex="190" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDollar(invoiceHeader.getObmoTotal(), oid)%>" READONLY DISABLED></td>
		        </tr>
		        <% if (oid.equalsIgnoreCase("msg07p")) { %>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV02")%>>
					<td align="right" nowrap>
					<% if (DictionaryManager.isLink(oid, "po-PO04")) { %>
						<a href="javascript: browseStd('InvoiceHeader_udf2Code', 'PO04'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV02", "UDF2", true)%>:</a>&nbsp;
					<% } else { %>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV02", "UDF2", true)%>:&nbsp;
					<% } %>
					</td>
					<td><input type="text" name="InvoiceHeader_udf2Code" tabindex="195" size=15 maxlength=15 value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf2Code)}" onchange="upperCase(this);"></td>
				</tr>
		        <% } else { %>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV02")%>>
<%	if (oid.equalsIgnoreCase("TTR09P"))	{%>
					<td align="right" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV02", "UDF2", true)%>:&nbsp;</td>
<%	} else {%>
					<td align="right" nowrap><a href="javascript: setEdit('IV02'); browseStd('InvoiceHeader_udf2Code', 'IV02'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV02", "UDF2")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV02", "UDF2", true)%>:</a>&nbsp;</td>
<%	} %>
					<td><input type="text" name="InvoiceHeader_udf2Code" tabindex="200" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf2Code)}" onchange="upperCase(this);"></td>
		        </tr>
		        <% } %>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV04")%>>
					<td align="right" nowrap>
						<% if (oid.equalsIgnoreCase("msg07p")) { %>
						<a href="javascript: browseStd('InvoiceHeader_udf4Code', 'PO04'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV04", "UDF4")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV04", "UDF4", true)%>:</a>&nbsp;
						<% } else { %>
<%	if (oid.equalsIgnoreCase("TTR09P"))	{%>
						<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV04", "UDF4", true)%>:&nbsp;
<%	} else {%>
						<a href="javascript: setEdit('IV04'); browseStd('InvoiceHeader_udf4Code', 'IV04'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV04", "UDF4")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV04", "UDF4", true)%>:</a>&nbsp;
<%	} %>
   					    <% } %>
					</td>
					<td><input type="text" name="InvoiceHeader_udf4Code" tabindex="205" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf4Code)}" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV06")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV06'); browseStd('InvoiceHeader_udf6Code', 'IV06'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV06", "UDF6")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV06", "UDF6", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf6Code" tabindex="210" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf6Code)}" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV08")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV08'); browseStd('InvoiceHeader_udf8Code', 'IV08'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV08", "UDF8")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV08", "UDF8", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf8Code" tabindex="215" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf8Code)}" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV10")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV10'); browseStd('InvoiceHeader_udf10Code', 'IV10'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV10", "UDF10")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV10", "UDF10", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf10Code" tabindex="220" size="15" maxlength="15" value="${esapi:encodeForHTMLAttribute(invoiceHeader.udf10Code)}" onchange="upperCase(this);"></td>
		        </tr>
				</table>
			</td>
		</tr>
		<tr><td colspan="2"><br></td></tr>
		<tr>
			<td valign="top" colspan="2">
				<table border=0 cellpadding=0 cellspacing=0 align=center width="100%">
				<tr <%=HtmlWriter.isVisible(oid, "ivc-generalDescription")%>>
					<td nowrap align=right valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-generalDescription", "General Description", true)%>:&nbsp;</td>
					<td><textarea wrap="virtual" name="InvoiceHeader_invoiceDesc" tabindex="96" rows="4" cols="70" onchange="upperCase(this)">${esapi:encodeForHTML(invoiceHeader.invoiceDesc)}</textarea></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 align="right" valign="top"><%@ include file="/invoice/iv_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	var navMenu = getNavCookie("navArray");
	if (navMenu.indexOf("Invoice Voucher") < 0)
	{
		if (navMenu.indexOf("Order") < 0) {
			setNavCookie("/invoice/iv_general_info.jsp", "InvoiceHeaderShipToRetrieveById", "Invoice Voucher");
		} else {
			setNavCookie("/invoice/iv_general_info.jsp", "InvoiceHeaderShipToRetrieveById", "Invoice Voucher", true);
		}
	}

	function thisLoad()
	{
		f_StartIt();
	<%if(invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_RECALLED) > 0){%>
		<%if(invoiceHeader.getStatus().equals(DocumentStatus.IVC_REJECTED) && user.getVchApp().equalsIgnoreCase("N")){%>
			checkInputSettings();
			allowEdit = false;
			<%}%>
	<%}
	
	if (oid.equalsIgnoreCase("akdoc") && invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_PAID) < 0){%>
			frm.InvoiceHeader_udf1Code.readOnly = false;
			frm.InvoiceHeader_udf1Code.contentEditable = true;
			frm.InvoiceHeader_udf1Code.disabled = false;
	<%}%>
			frm.InvoiceHeader_dateEntered.readOnly = false;
			frm.InvoiceHeader_dateEntered.contentEditable = true;
			frm.InvoiceHeader_dateEntered.disabled = false;
	<%	
			if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_APPROVING) >= 0 && !invoiceHeader.getStatus().equals(DocumentStatus.IVC_REJECTED)){
				%>
				checkInputSettings();
				allowEdit = false;
				<%
			}%>
	}

	function checkInvoiceNumberLength(field){
		if(field.value.length>15)
		{
			alert("The Invoice Number cannot exceed 15 characters");
			field.value= field.value.substring(0,15);
		}
		
	}
	
	function updateInvoiceDueTime(){
		<%if(bCreatedFromPo){%>
		if( frm.InvoiceHeader_invoiceDate){
	    	var sdf = new JsSimpleDateFormat("<%=userDateFormat%>");
	    	var dateObj = sdf.parse(frm.InvoiceHeader_invoiceDate.value);
	    	var payment_term_days = <%=paymentTerm.getTermDays()%>;
	    	dateObj.setTime(dateObj.getTime() + ((payment_term_days)* 24 * 60 * 60 * 1000));
	    	var formattedString = sdf.format(dateObj);
	    	if(frm.InvoiceHeader_paymentDueDate)
	    	{
	    		frm.InvoiceHeader_paymentDueDate.value= formattedString;
	    	}
		}
		<%}%>
	}
	
	function formatMe(x)
	{
		var prc_dec = <%=s_price_decimals%>;
		if (x.name == "InvoiceHeader_invoiceTotal")
		{
			var total = eval(nfilter(frm.InvoiceHeader_invoiceTotal));
			if (frm.InvoiceHeader_invoiceTotal.value != '')
			{
				frm.InvoiceHeader_invoiceTotal.value = nformat(total, prc_dec);
			}
		}
		else if (x.name == "InvoiceHeader_poTotal")
		{
			var total = eval(nfilter(frm.InvoiceHeader_poTotal));
			if (frm.InvoiceHeader_poTotal.value != '')
			{
				frm.InvoiceHeader_poTotal.value = nformat(total, prc_dec);
			}
		}
	}

	function validateForm()
	{
		if (frm.handler.value.indexOf("Invoice") == 0)
		{
			if (frm.InvoiceHeader_invoiceNumber.value == "Enter Invoice" || isNA(frm.InvoiceHeader_invoiceNumber.value) || frm.InvoiceHeader_invoiceNumber.value.length <= 0)
			{
				alert("Please enter an invoice number.");
				frm.InvoiceHeader_invoiceNumber.focus();
				return false;
			}
			else if (((frm.InvoiceHeader_udf1Code.value != "C") && frm.InvoiceHeader_invoiceTotal.value == 0) ||  frm.InvoiceHeader_invoiceTotal.value.length <= 0)
			{
				alert("Please enter the invoice total.");
				frm.InvoiceHeader_invoiceTotal.focus();
				return false;
			}

			if (frm.InvoiceHeader_invoiceDate)
			{
				if (isEmpty(frm.InvoiceHeader_invoiceDate.value)) {
					alert("Please enter an invoice date.");
					frm.InvoiceHeader_invoiceDate.focus();
					return false;
				}
				if (!chkdate(frm.InvoiceHeader_invoiceDate)) {
					alert("Please enter a valid invoice date.");
					frm.InvoiceHeader_invoiceDate.focus();
					return false;
				}
			}

			if ( frm.organizationId.value.toUpperCase() == 'DTN07P' && !isEmpty(frm.InvoiceHeader_udf1Code.value) )
			{
				if (frm.InvoiceHeader_udf1Code && !chkdate(frm.InvoiceHeader_udf1Code)) {
					alert("Please enter a valid date for Accounting Date.");
					frm.InvoiceHeader_udf1Code.focus();
					return false;
				}
			}

			if ( frm.organizationId.value.toUpperCase() == 'TTR09P' && isEmpty(frm.InvoiceHeader_invoiceDesc.value) )
			{
					alert("You must enter a General Description.");
					frm.InvoiceHeader_invoiceDesc.focus();
					return false;
			}
		}

		return true;
	}

	function validateNumber()
	{
		if (frm.InvoiceHeader_invoiceNumber.value != frm.originalInvoiceNumber.value)
		{
			doSubmit('/invoice/iv_general_info.jsp', 'InvoiceValidateNumber');
		}
	}

	function setEdit(x)
	{
		<% if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_RECALLED) > 0) { %>
			allowEdit = false;
			<% if (oid.equalsIgnoreCase("akdoc") && invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_PAID) < 0) { %>
				if (x == 'IV01'){
					frm.allowBrowse.value = true;
				}
				else{
					frm.allowBrowse.value = false;
				}
			<%}
		} else { %>
			if (x == 'orderDate' && <%=bCreatedFromPo%>) {
				allowEdit = false;
			} else {
				allowEdit = true;
			}
			if (x == 'imis' || x == 'obmo')	{
				allowEdit = false;
			}
			//if ( (x == 'orderByName' || x == 'department' || x == 'shipToCode' || x == 'billToCode') && <%--=bCreatedFromPo--%>) {
			<% if (s_SelectBilltoShipto.equalsIgnoreCase("N")){ %>
				if ( (x == 'orderByName' || x == 'shipToCode' || x == 'billToCode') && <%=bCreatedFromPo%>) {
					frm.allowBrowse.value = false;
				} else {
					frm.allowBrowse.value = true;
				}
			<%}%>
		<%}%>
	}
	function orderPreview(icPoHeader)
	{
		popupParameters = "PoHeader_icPoHeader=" + icPoHeader;
		doSubmitToPopup('/orders/po_preview.jsp', 'PoRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function setPaymentDueDate()
	{

<%Date 	d_oneMoreMonth		= new Date(d_today.getYear(), d_today.getMonth()+1, d_today.getDate());%>

	}
	
	function setDefaultInvoiceNumber(field){
		var defaultInvoiceNumber = '<%=defaultInvoiceNumber%>';
		field.value = defaultInvoiceNumber;
	}

	function browseSetup(browseName, browseType, fieldName) {
		var flds = frm.elements(fieldName);

		if (browseType.length > 0){
			browseStd(fieldName, browseType, false);
		} else if (flds != undefined && flds.length > 1) {
			browseSchedule(fieldName, browseName);
		} else {
			browseLookup(fieldName, browseName);
		}
	}

// End Hide script -->
</SCRIPT>