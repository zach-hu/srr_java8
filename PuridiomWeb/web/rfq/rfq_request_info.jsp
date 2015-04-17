<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_department = rfqHeader.getDepartmentCode();

	UserProfile requisitioner = UserManager.getInstance().getUser(oid, rfqHeader.getRequisitionerCode());
	UserProfile authBy = UserManager.getInstance().getUser(oid, rfqHeader.getAuthorizationCode());

	String	s_current_process = "HEADER_REQUEST_INFO";
	String	s_current_page = "/rfq/rfq_request_info.jsp";
	String	s_current_method = "RfqHeaderUpdateById";
	String	s_current_process_method = "";
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
<tsa:hidden name="allowBrowse" value="true"/>

<br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Request Information</div>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<table border=0 cellpadding=0 cellspacing=0 width=680px>
		<TR>
			<TD width=250px align=center valign=top>
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="0" width=100%>
				<tr>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionNumber", "Requisition #")%>:&nbsp;</td>
					<td><input type=text name="RfqHeader_requisitionNumber" size=15 maxlength=20 value="<%=headerEncoder.encodeForHTMLAttribute(rfqHeader.getRequisitionNumber())%>" disabled></td>
				</tr>
				<tr>
					<td align=right nowrap><a href="javascript: browseLookup('RfqHeader_requisitionerCode', 'requisitioner'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitioner", "Requisitioner")%>:</a>&nbsp;</td>
					<td><input type=text name="RfqHeader_requisitionerCode" size=15 maxlength=15 value="<%=rfqHeader.getRequisitionerCode()%>" onchange="upperCase(this); getNewInfo('requisitioner', this);"></td>
				</tr>
				<tr>
					<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "requisitionerName", "Requisitioner Name")%>:&nbsp;</td>
					<td><input type=text name="as_requisitionerName" tsize=24 maxlength=24 value="<%=requisitioner.getDisplayName()%>" onchange="upperCase(this);" disabled></td>
				</tr>
				<tr>
					<td align=right nowrap><a href="javascript: browseLookup('RfqHeader_departmentCode', 'department'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "department", "Department")%>:</a>&nbsp;</td>
					<td><input type=text name="RfqHeader_departmentCode" size=15 maxlength=15 value="<%=s_department%>" onchange="upperCase(this);"></td>
				</tr>
				<TR><TD COLSPAN="2"><BR></TD></TR>
				</TABLE>
			</TD>
			<TD WIDTH=5px>&nbsp;</TD>
			<TD width=250px align=center valign=top>
				<TABLE BORDER="0" CELLPADDING="0" CELLSPACING=0 width=100%>
				<tr>
					<td align=right nowrap><a href="javascript: browseLookup('RfqHeader_authorizationCode', 'authorization'); void(0);">Authorized By:</a>&nbsp;</td>
					<td><input type=text name="RfqHeader_authorizationCode" size=15 maxlength=15 value="<%=rfqHeader.getAuthorizationCode()%>"  onchange="upperCase(this); getNewInfo('authby', this);"></td>
				</tr>
				<tr>
					<td align=right nowrap>Authorized By Name:&nbsp;</td>
					<td><input type=text name="as_authbyName" size=24 maxlength=24 value="<%=authBy.getDisplayName()%>" onchange="upperCase(this);" onfocus="this.blur();" disabled></td>
				</tr>
				<TR><TD COLSPAN="2"><BR></TD></TR>
				</TABLE>
			</td>
			<TD>&nbsp;</TD>
<%	if (s_view.equalsIgnoreCase("WIZARD")) { %>
			<td align=right><%@ include file="/rfq/rfq_sidebar.jsp" %></td>
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqHeaderUpdateById;RfqRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve');  void(0);">Return</a></div></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var rfqnumber = "<%=s_rfqNumber%>";
	var fiscalyear = "<%=rfqHeader.getFiscalYear()%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";

	function thisLoad()
	{
		f_StartIt();
<%	if (Integer.valueOf(s_rfqStatus).intValue() >= 2005 && Integer.valueOf(s_rfqStatus).intValue() != 2015) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

// End Hide script -->
</SCRIPT>