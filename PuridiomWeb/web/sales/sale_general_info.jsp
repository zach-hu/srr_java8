<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	SaleHeader saleHeader = (SaleHeader) request.getAttribute("saleHeader");
	int i;

	String s_icSaleHeader = saleHeader.getIcSaleHeader().toString();
	String s_status = saleHeader.getStatus();
	String s_saleNumber = saleHeader.getSaleNumber();
	String s_amendment = saleHeader.getAmendment();
	String s_fiscalYear = saleHeader.getFiscalYear();
	String s_language = saleHeader.getLanguage();
	String s_buyer = saleHeader.getContact();
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer);

	String	s_current_process = "HEADER_GENERAL_INFO";
	String	s_current_page = "/sales/sale_general_info.jsp";
	String	s_current_method = "SaleHeaderUpdateById";
	String	s_current_process_method = "";
%>
<%@ include file="/sales/sale_hidden_fields.jsp" %>

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
		<%@ include file="/sales/sale_status_title.jsp" %>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<tr <%=HtmlWriter.isVisible(oid, "sale-surplusDate")%>>
					<td width=105px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-suprlusDate", "Surplus Date", true)%>:&nbsp;</td>
					<td>
						<input type=text name="SaleHeader_saleDate" tabindex=1 size=15 maxlength=10 value="<%=HiltonUtility.getFormattedDate(saleHeader.getSaleDate(), oid, userDateFormat)%>">
						<a href="javascript: show_calendar('SaleHeader_saleDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or enter a Date in the box on the left."valign=bottom border=0></a>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-fiscalYear")%>>
					<td width=105px align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-fiscalYear", "Fiscal Year", true)%>:&nbsp;</td>
					<td><input type=text name="as_fiscalYear" tabindex=3 size=15 maxlength=4 value="<%=saleHeader.getFiscalYear()%>" onchange="frm.SaleHeader_fiscalYear.value=this.value;"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-buyer")%>>
					<td align=right nowrap><a href="javascript: browseLookup('SaleHeader_contact', 'buyer'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-buyer", "Buyer")%> for this surplus or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-contact", "Contact")%> in the box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-contact", "Contact", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_contact" tabindex=13 size=15 maxlength=15 value="<%=s_buyer%>" onchange="upperCase(this); getNewInfo('buyer', this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-buyerName")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-contactName", "Contact Name")%>:&nbsp;</td>
					<td><input type=text name="as_buyerName" size=24 maxlength=24 value="<%=buyer.getDisplayName()%>" onfocus="this.blur();" disabled></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-currency")%>>
					<td align=right nowrap><a href="javascript: browseLookup('SaleHeader_currency', 'curr_code'); void(0);" title="Click here to choose the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-currency", "Currency")%> for this surplus or enter the <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-currency", "Currency")%> in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-currency", "Currency", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_currency" tabindex=17 size=15 maxlength=30 value="<%=saleHeader.getCurrency()%>" onchange="upperCase(this);"></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-language")%>>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-language", "Language", true)%>:&nbsp;</td>
					<td>
						<select name="RfHeader_language" tabindex=19>
						<option value="(Default)" <% if ( s_language.equals("(Default)") ) { %>selected<%}%> >(Default)</option>
						<option value="" <% if ( s_language.equals("") ) { %>selected<%}%> ></option>
						</select>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF01")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf1Code','RF01');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF01", "UDF 1")%> for this surplus or enter the value in the box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF01", "UDF 1", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf1Code" tabindex=21 size=15 maxlength=15 value="<%=saleHeader.getUdf1Code()%>" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF03")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf3Code','RF03');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF03", "UDF 3")%> for this surplus or enter the value in the box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF03", "UDF 3", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf3Code" tabindex=21 size=15 maxlength=15 value="<%=saleHeader.getUdf3Code()%>" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF05")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf5Code','RF05');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF05", "UDF 5")%> for this surplus or enter the value in the box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF05", "UDF 5", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf5Code" tabindex=21 size=15 maxlength=15 value="<%=saleHeader.getUdf5Code()%>" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF07")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf7Code','RF07');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF07", "UDF 7")%> for this surplus or enter the value in the box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF07", "UDF 7", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf7Code" tabindex=21 size=15 maxlength=15 value="<%=saleHeader.getUdf7Code()%>" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF09")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf9Code','RF09');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF09", "UDF 9")%> for this surplus or enter the value in the box on the right." ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF09", "UDF 9", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf9Code" tabindex=21 size=15 maxlength=15 value="<%=saleHeader.getUdf9Code()%>" ></td>
				</tr>
				</table>
			</td>
			<td>&nbsp;</td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF02")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf2Code','RF02');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF02", "UDF 2")%> for this surplus or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF02", "UDF 2", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf2Code" tabindex=29 size=15 maxlength=15 value="<%=saleHeader.getUdf2Code()%>" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF04")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf4Code','RF04');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF04", "UDF 4")%> for this surplus or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF04", "UDF 4", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf4Code" tabindex=29 size=15 maxlength=15 value="<%=saleHeader.getUdf4Code()%>" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF06")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf6Code','RF06');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF06", "UDF 6")%> for this surplus or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF06", "UDF 6", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf6Code" tabindex=29 size=15 maxlength=15 value="<%=saleHeader.getUdf6Code()%>" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF08")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf8Code','RF08');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF08", "UDF 8")%> for this surplus or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF08", "UDF 8", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf8Code" tabindex=29 size=15 maxlength=15 value="<%=saleHeader.getUdf8Code()%>" ></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "sale-RF10")%>>
					<td align=right nowrap><a href="javascript: browseStd('SaleHeader_udf10Code','RF10');" title="Click here to choose the value for <%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF10", "UDF 10")%> for this surplus or enter the value in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-RF10", "UDF 10", true)%>:</a>&nbsp;</td>
					<td><input type=text name="SaleHeader_udf10Code" tabindex=29 size=15 maxlength=15 value="<%=saleHeader.getUdf10Code()%>" ></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=3>
				<table border=0 cellpadding=0 cellspacing=0>
				<tr <%=HtmlWriter.isVisible(oid, "sale-purpose")%>>
					<td width=105px nowrap align=right valign=top><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-description", "Description", true)%>:&nbsp;</td>
					<td>
						<textarea name="SaleHeader_description" tabindex=31 rows="6" cols="64" wrap="nonvirtual" maxlength="255" onchange="checkLength(); upperCase(this);">${esapi:encodeForHTML(saleHeader.description)}</textarea>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
			<td rowspan=2 valign=top><%@ include file="/sales/sale_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=550px>
<tr>
<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) < 0 || s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) == 0) { %>
	<td width=50% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleHeaderUpdateById;SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%	} else {%>
	<td width=100% align=center><a href="javascript: doSubmit('/sales/sale_summary.jsp', 'SaleRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
<%	}%>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var salenumber = "<%=s_saleNumber%>";
	var fiscalyear = "<%=saleHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
<%	if (s_status.compareTo(DocumentStatus.SALE_BIDS_PENDING) >= 0 && s_status.compareTo(DocumentStatus.SALE_OPENAMENDMENT) != 0) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	function checkLength()
	{
		var desc = frm.SaleHeader_description.value;

		if ( desc.length > 250 ) {
			alert("This description field can only hold 250 characters.  The description will be truncated.");
			desc = desc.substring(0, 250);
			frm.SaleHeader_description.value = desc;
		}
	}

	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("SaleHeaderUpdateById") >= 0) {
			var alertMessage = "";

			if (frm.SaleHeader_saleDate && !chkdate(frm.SaleHeader_saleDate)) {
				alertMessage += "\n<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "sale-saleDate", "Surplus Date")%> is not a valid date.";
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