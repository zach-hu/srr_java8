<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/supplierportal/scripts/date_check.js"></SCRIPT>
<%
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_date_format = propertiesManager.getProperty("MISC", "DateFormat", "MM-dd-yyyy");
	String	s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	s_ivc_status = invoiceHeader.getStatus();
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
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_vendorId" value="<%=invoiceHeader.getVendorId()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="formType" value="IVH"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="currentPage" value="/invoice/iv_general_info.jsp"/>
<tsa:hidden name="duplicateNumberFailurePage" value="/invoice/iv_general_info.jsp"/>
<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>General Information</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<table border=0 cellspacing=0 cellpadding=1 width=100%>
		<tr>
			<td align=right><b>Invoice #:</b></td>
			<td width=125px><%=invoiceHeader.getInvoiceNumber()%></td>
		</tr>
		<tr>
			<td align=right><b>Status:</b></td>
			<td width=125px><%=DocumentStatus.toString(invoiceHeader.getStatus(), oid)%></td>
		</tr>
		</table>
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

<%	if (!HiltonUtility.isEmpty(duplicateNumberErrorMsg)) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center class="error">
		<%=duplicateNumberErrorMsg%>
	</td>
</tr>
</table>

<br>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width="100%">
		<tr>
			<td valign="top">
				<table border=0 cellpadding=0 cellspacing=0 width="50%" align=center>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceNumber")%> nowrap><%=DictionaryManager.getLabel(oid, "invoiceNumber", "Invoice Number", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceNumber")%> >
						<input type="text" title="Enter Invoice Number" name="InvoiceHeader_invoiceNumber" tabindex="1" size="15" maxlength="20" value="<%=invoiceHeader.getInvoiceNumber()%>" onchange="upperCase(this);">
						<tsa:hidden name="originalInvoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceTotal")%> nowrap><%=DictionaryManager.getLabel(oid, "invoiceTotal", "Invoice Total", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceTotal")%> ><input type=text title="Enter Invoice Total" name="InvoiceHeader_invoiceTotal" tabindex="2" size=15 value="<%=invoiceHeader.getInvoiceTotal().setScale(2, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" onchange="formatMe(this);"></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceDate")%> nowrap><%=DictionaryManager.getLabel(oid, "invoiceDate", "Invoice Date", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceDate")%> >
						<input type=text title="Enter Invoice Date" name="InvoiceHeader_invoiceDate" tabindex="3" size="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), oid)%>">
						<a href="javascript: setEdit('invoiceDate'); show_calendar('InvoiceHeader_invoiceDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/supplierportal/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceDueDate")%> nowrap><%=DictionaryManager.getLabel(oid, "invoiceDueDate", "Invoice Due Date", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceDueDate")%> >
						<input type=text title="Enter Payment Due Date" name="InvoiceHeader_paymentDueDate" tabindex="5" size="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getPaymentDueDate(), oid)%>">
						<a href="javascript: setEdit('invoiceDueDate'); show_calendar('InvoiceHeader_paymentDueDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/supplierportal/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "invoiceDateEntered")%> nowrap>
						<%=DictionaryManager.getLabel(oid, "invoiceDateEntered", "Date Entered", true)%>:&nbsp;
					</td>
					<td <%=HtmlWriter.isVisible(oid, "invoiceDateEntered")%> >
						<input type=text title="Date Entered" name="InvoiceHeader_dateEntered" size="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getDateEntered(), oid)%>" disabled>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-entered-by")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-entered-by", "Entered By")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_enteredBy" tabindex="17" size="20" value="<%=invoiceHeader.getEnteredBy()%>" READONLY DISABLED></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "orderNumber")%> nowrap><%=DictionaryManager.getLabel(oid, "orderNumber", "Order Number", true)%>:&nbsp;</td>
					<td nowrap <%=HtmlWriter.isVisible(oid, "orderNumber")%> >
						<input type=text title="Order Number" name="InvoiceHeader_poNumber" tabindex="7" size="15" value="<%=invoiceHeader.getPoNumber()%>" <% if (bCreatedFromPo) {%> READONLY DISABLED<% } %>>
						<% if (bCreatedFromPo && invoiceHeader.getPoRelease().compareTo(new BigDecimal(0)) > 0) { %>Release:&nbsp;<input type="text" name="InvoiceHeader_poRelease" tabindex="9" size="4" value="<%=invoiceHeader.getPoRelease()%>" READONLY DISABLED><% } %>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "orderDate")%> nowrap><%=DictionaryManager.getLabel(oid, "orderDate", "Order Date", true)%>:&nbsp;</td>
					<td nowrap <%=HtmlWriter.isVisible(oid, "orderDate")%> >
						<input type=text title="Enter Order Date" name="InvoiceHeader_poDate" tabindex="11" size=15 value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getPoDate(), oid)%>" <% if (bCreatedFromPo) { %> READONLY DISABLED <% } %>>
						<a href="javascript: setEdit('orderDate'); show_calendar('InvoiceHeader_poDate', '<%=s_date_format%>');"><img src="<%=contextPath%>/supplierportal/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "orderTotal")%> nowrap><%=DictionaryManager.getLabel(oid, "orderTotal", "Order Total", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "orderTotal")%> ><input type=text title="Enter Order Total" name="InvoiceHeader_poTotal" tabindex="13" size=15 value="<%=invoiceHeader.getPoTotal().setScale(2, BigDecimal.ROUND_HALF_UP)%>" style="text-align:right" onchange="formatMe(this);" <% if (bCreatedFromPo) { %> READONLY DISABLED<% } %> ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-order-by")%>>
					<td nowrap align="right"><a href="javascript: setEdit('orderByName'); browseLookup('InvoiceHeader_orderByName', 'user'); void(0);"><%=DictionaryManager.getLabel(oid, "ivc-order-by", "Ordered By")%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_orderByName" tabindex="17" size="20" maxlength="40" value="<%=invoiceHeader.getOrderByName()%>" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-owner-email")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-order-by", "Email")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_orderByEmail" tabindex="19" size="30" maxlength="60" value="<%=invoiceHeader.getOrderByEmail()%>" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-order-by")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-order-by", "Phone")%>:&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_orderByPhone" tabindex="21" size="20" maxlength="40" value="<%=invoiceHeader.getOrderByPhone()%>" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-IV01")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV01'); browseStd('InvoiceHeader_udf1Code', 'IV01'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV01", "UDF1")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV01", "UDF1", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf1Code" tabindex="23" size="15" maxlength="15" value="<%=invoiceHeader.getUdf1Code()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV03")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV03'); browseStd('InvoiceHeader_udf3Code', 'IV03'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV03", "UDF3")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV03", "UDF3", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf3Code" tabindex="23" size="15" maxlength="15" value="<%=invoiceHeader.getUdf3Code()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV05")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV05'); browseStd('InvoiceHeader_udf5Code', 'IV05'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV05", "UDF5")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV05", "UDF5", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf5Code" tabindex="23" size="15" maxlength="15" value="<%=invoiceHeader.getUdf5Code()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV07")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV07'); browseStd('InvoiceHeader_udf7Code', 'IV07'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV07", "UDF7")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV07", "UDF7", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf7Code" tabindex="23" size="15" maxlength="15" value="<%=invoiceHeader.getUdf7Code()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV09")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV09'); browseStd('InvoiceHeader_udf9Code', 'IV09'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV09", "UDF9")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV09", "UDF9", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf9Code" tabindex="23" size="15" maxlength="15" value="<%=invoiceHeader.getUdf9Code()%>" onchange="upperCase(this);"></td>
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
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-shipToCode")%> nowrap><a href="javascript: setEdit('shipToCode'); browseLookup('InvoiceHeader_shipToCode', 'ship_to'); void(0);" title="Click here to choose the Ship To Code for this Invoice or enter the Ship To Code in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-shipToCode", "Ship To Code", false)%></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-shipToCode")%> ><input type=text title="Enter Ship To Code" name="InvoiceHeader_shipToCode" tabindex="23" size="25" maxlength="15" value="<%=invoiceHeader.getShipToCode()%>" onchange="upperCase(this); getNewInfo('shipto', this);" <% if (bCreatedFromPo) {%> READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine1")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-sup-addressLine1", "Address 1")%>:&nbsp;</td>
					<td><input type="text" name="Address_addressLine1" tabindex="25" size="25" maxlength="40" value="<%=shipTo.getAddressLine1()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine2")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-sup-addressLine2", "Address 2")%>:&nbsp;</td>
					<td><input type="text" name="Address_addressLine2" tabindex="27" size="25" maxlength="40" value="<%=shipTo.getAddressLine2()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine3")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-sup-addressLine3", "Address 3")%>:&nbsp;</td>
					<td><input type="text" name="Address_addressLine3" tabindex="29" size="25" maxlength="40" value="<%=shipTo.getAddressLine3()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine4")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-sup-addressLine4", "Address 4")%>:&nbsp;</td>
					<td><input type="text" name="Address_addressLine4" tabindex="31" size="25" maxlength="40" value="<%=shipTo.getAddressLine4()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-city")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-sup-city", "City")%>:&nbsp;</td>
					<td><input type="text" name="Address_city" tabindex="33" size="25" maxlength="30" value="<%=shipTo.getCity()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-state")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-sup-state", "State")%>:&nbsp;</td>
					<td><input type="text" name="Address_state" tabindex="35" size="25" maxlength="15" value="<%=shipTo.getState()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-zip")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-sup-zip", "Zip/Postal Code")%>:&nbsp;</td>
					<td><input type="text" name="Address_postalCode" tabindex="37" size="25" maxlength="15" value="<%=shipTo.getPostalCode()%>" READONLY DISABLED></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-country")%>>
					<td nowrap align="right"><%=DictionaryManager.getLabel(oid, "ivc-sup-country", "Country")%>:&nbsp;</td>
					<td><input type="text" name="Address_country" tabindex="39" size="25" maxlength="25" value="<%=shipTo.getCountry()%>" READONLY DISABLED></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-billToCode")%> nowrap><a href="javascript: setEdit('billToCode'); browseLookup('InvoiceHeader_billToCode', 'bill_to'); void(0);" title="Click here to choose the Bill To Code for this invoice or enter the Bill To Code in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-billToCode", "Bill To Code", true)%></a>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-billToCode")%> ><input type=text  title="Enter Bill To Code" name="InvoiceHeader_billToCode" tabindex="41" size="25" maxlength="15" value="<%=invoiceHeader.getBillToCode()%>" onchange="upperCase(this);" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr>
					<td align=right <%=HtmlWriter.isVisible(oid, "ivc-billToAttn")%> nowrap><%=DictionaryManager.getLabel(oid, "ivc-billToAttn", "Attention", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "ivc-billToAttn")%> ><input type=text  title="Enter Bill To Contact" name="InvoiceHeader_billToContact" tabindex="43" size="25" maxlength="40" value="<%=invoiceHeader.getBillToContact()%>" <% if (bCreatedFromPo) { %>READONLY DISABLED<% } %>></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-IV02")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV02'); browseStd('InvoiceHeader_udf2Code', 'IV02'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV02", "UDF2")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV02", "UDF2", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf2Code" tabindex="47" size="15" maxlength="15" value="<%=invoiceHeader.getUdf2Code()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV04")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV04'); browseStd('InvoiceHeader_udf4Code', 'IV04'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV04", "UDF4")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV04", "UDF4", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf4Code" tabindex="47" size="15" maxlength="15" value="<%=invoiceHeader.getUdf4Code()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV06")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV06'); browseStd('InvoiceHeader_udf6Code', 'IV06'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV06", "UDF6")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV06", "UDF6", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf6Code" tabindex="47" size="15" maxlength="15" value="<%=invoiceHeader.getUdf6Code()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV08")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV08'); browseStd('InvoiceHeader_udf8Code', 'IV08'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV08", "UDF8")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV08", "UDF8", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf8Code" tabindex="47" size="15" maxlength="15" value="<%=invoiceHeader.getUdf8Code()%>" onchange="upperCase(this);"></td>
		        </tr>
		        <tr <%=HtmlWriter.isVisible(oid, "ivc-IV10")%>>
					<td align="right" nowrap><a href="javascript: setEdit('IV10'); browseStd('InvoiceHeader_udf10Code', 'IV10'); void(0);" title="Click here to choose the value for <%=DictionaryManager.getLabel(oid, "ivc-IV10", "UDF10")%> for this invoice or enter the value in the box on the right."><%=DictionaryManager.getLabel(oid, "ivc-IV10", "UDF10", true)%>:</a>&nbsp;</td>
					<td><input type="text" name="InvoiceHeader_udf10Code" tabindex="47" size="15" maxlength="15" value="<%=invoiceHeader.getUdf10Code()%>" onchange="upperCase(this);"></td>
		        </tr>
				</table>
			</td>
		</tr>
		<tr><td colspan="2"><br></td></tr>
		<tr>
			<td valign="top" colspan="2">
				<table border=0 cellpadding=0 cellspacing=0 align=center width="100%">
				<tr <%=HtmlWriter.isVisible(oid, "ivc-generalDescription")%>>
					<td nowrap align=right valign=top><%=DictionaryManager.getLabel(oid, "ivc-generalDescription", "General Description", true)%>:&nbsp;</td>
					<td><textarea wrap="virtual" name="InvoiceHeader_invoiceDesc" tabindex="51" rows="4" cols="70">${esapi:encodeForHTML(invoiceHeader.invoiceDesc)}</textarea></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 align="right" valign="top"><%@ include file="/supplierportal/invoice/iv_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<%@ include file="/supplierportal/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_INPROGRESS) > 0) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function formatMe(x)
	{
		if (x.name == "InvoiceHeader_invoiceTotal")
		{
			var total = eval(nfilter(frm.InvoiceHeader_invoiceTotal));
			if (frm.InvoiceHeader_invoiceTotal.value != '')
			{
				frm.InvoiceHeader_invoiceTotal.value = nformat(total, 2);
			}
		}
		else if (x.name == "InvoiceHeader_poTotal")
		{
			var total = eval(nfilter(frm.InvoiceHeader_poTotal));
			if (frm.InvoiceHeader_poTotal.value != '')
			{
				frm.InvoiceHeader_poTotal.value = nformat(total, 2);
			}
		}
	}

	function validateForm()
	{
		if (frm.handler.value.indexOf("Invoice") == 0)
		{
			if (frm.InvoiceHeader_invoiceNumber.value == "N/A" || frm.InvoiceHeader_invoiceNumber.value.length <= 0)
			{
				alert("Please enter an invoice number.");
				frm.InvoiceHeader_invoiceNumber.focus();
				return false;
			}
			else if (frm.InvoiceHeader_invoiceTotal.value == 0 || frm.InvoiceHeader_invoiceTotal.value.length <= 0)
			{
				alert("Please enter the invoice total.");
				frm.InvoiceHeader_invoiceTotal.focus();
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
<%	if (invoiceHeader.getStatus().compareTo(DocumentStatus.IVC_INPROGRESS) > 0) { %>
			allowEdit = false;
<%	} else { %>
			if (x == 'orderDate' && <%=bCreatedFromPo%>) {
				allowEdit = false;
			} else {
				allowEdit = true;
			}

			if ( (x == 'orderByName' || x == 'shipToCode' || x == 'billToCode') && <%=bCreatedFromPo%>) {
				frm.allowBrowse.value = false;
			} else {
				frm.allowBrowse.value = true;
			}
<%	} %>
	}

// End Hide script -->
</SCRIPT>