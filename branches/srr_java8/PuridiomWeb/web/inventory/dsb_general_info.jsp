<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_property_checklist_fields.jsp" %>
<%@ page import="java.util.Date.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	DisbHeader disbHeader = (DisbHeader) request.getAttribute("disbHeader");
	BigDecimal bd_ic_dsb_header = disbHeader.getIcDsbHeader();
	String s_dsb_number = disbHeader.getDisbNumber();
	String s_dsb_status = HiltonUtility.ckNull(disbHeader.getStatus());
	String s_dsb_type = HiltonUtility.ckNull(disbHeader.getDisbType());
	String s_requisitioner_code = disbHeader.getRequisitionerCode();
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, s_requisitioner_code);

	if (s_dsb_number == null || s_dsb_number.length() <= 0)
	{
		s_dsb_number = "N/A";
	}

	String s_current_process = "HEADER_GENERAL_INFO";
	String s_current_page = "/inventory/dsb_general_info.jsp";
	String s_current_method = "DisbHeaderUpdate";
	String s_current_process_method = "";
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=bd_ic_dsb_header%>"/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=HiltonUtility.ckNull(disbHeader.getDisbNumber())%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=s_dsb_status%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_dsb_type%>"/>
<tsa:hidden name="Account_icHeader" value="<%=bd_ic_dsb_header%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="DBH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=bd_ic_dsb_header%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<input type=hidden id="userSearch" value="Y"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="stdTable_tableType" value=""/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
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
			<td align=right><b>Disbursement #:</b></td>
			<td width=125px><%=s_dsb_number%></td>
		</tr>
		<tr>
			<td align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "status", "Status")%>:</b></td>
			<td width=125px><%=DocumentStatus.toString(s_dsb_status, oid)%></td>
		</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
		<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=<%=formEntryWidth%>>
<tr>
	<td valign=top align=center>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width="90%">
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<tr>
					<td align=right nowrap>Disbursement Date:&nbsp;</td>
					<td>
						<input type=text name="DisbHeader_disbDate" tabindex=5 size=15 value="<%=HiltonUtility.getFormattedDate(disbHeader.getDisbDate(), oid, userDateFormat)%>" onchange="checkDate(this);">
						<a href="javascript: show_calendar('DisbHeader_disbDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign=bottom border=0></a>
					</td>
				</tr>
				<tr>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "fiscalYear", "Fiscal Year")%>:&nbsp;</td>
					<td><input type=text name="DisbHeader_fiscalYear" tabindex=6 size=15 maxlength=4 value="<%=HiltonUtility.ckNull(disbHeader.getFiscalYear())%>"></td>
				</tr>
				<tr>
					<td align=right nowrap>Inventory Location:&nbsp;</td>
					<td><input type=text name="DisbHeader_itemLocation" tabindex=20 size=15 maxlength=15 value="<%=HiltonUtility.ckNull(disbHeader.getItemLocation())%>" disabled></td>
				</tr>
				</table>
			</td>
			<td>&nbsp;</td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
<%	if (!s_dsb_type.equals("O")) { %>
				<tr>
					<td align=right nowrap>Requisition Number:&nbsp;</td>
					<td><input type=text name="DisbHeader_requisitionNumber" tabindex=7 size=15 maxlength=20 value="<%=headerEncoder.encodeForHTMLAttribute(HiltonUtility.ckNull(disbHeader.getRequisitionNumber()))%>" disabled></td>
				</tr>
<%	} %>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "dsb-requisitioner")%> align=right nowrap><a href="javascript: browseLookup('DisbHeader_requisitionerCode', 'requisitioner'); void(0);" title="Click here to choose the Requisitioner for this disbursement or Enter the Requisitioner ID in the box on the right."><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-requisitioner", "Requisitioner", s_dsb_type, true)%>:</a>&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-requisitioner")%>><input type=text name="DisbHeader_requisitionerCode" tabindex=7 size=15 maxlength=15 value="<%=s_requisitioner_code%>" onchange="upperCase(this); getNewInfo('requisitioner', this);"></td>
				</tr>
				<tr>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionerName", "Requisitioner Name")%>:&nbsp;</td>
					<td><input type=text name="as_requisitionerName" size=24 value="<%=requisitioner.getDisplayName()%>" onfocus="this.blur();" disabled></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=3>
				<table border=0 cellpadding=1 cellspacing=0>
				<tr>
					<td <%=HtmlWriter.isVisible(oid, "dsb-purpose")%> width=123px nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dsb-purpose", "Description", true)%>:&nbsp;</td>
					<td <%=HtmlWriter.isVisible(oid, "dsb-purpose")%>><input type=text name="DisbHeader_internalComments" tabindex=16 size=65 maxlength=255 value="<%=HiltonUtility.ckNull(disbHeader.getInternalComments())%>" onchange="upperCase(this);"></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td rowspan=2 align=right><%@ include file="/inventory/dsb_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>
<br>

<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbSetProperty;DisbHeaderUpdate;DisbursementRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></td>
	<td width=50% align=center><a href="javascript: doSubmit('/inventory/dsb_summary.jsp', 'DisbSetProperty;DisbursementRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var disbnumber = "<%=s_dsb_number%>";
	var fiscalyear = "<%=HiltonUtility.ckNull(disbHeader.getFiscalYear())%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	var allowEdit;

	function thisLoad()
	{
		f_StartIt();
<%	if (s_dsb_status.compareTo(DocumentStatus.INV_INPROGRESS) > 0) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

// End Hide script -->
</SCRIPT>
