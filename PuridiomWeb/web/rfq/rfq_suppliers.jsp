<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="com.tsa.puridiom.common.utility.*" %>
<%@ page import="com.tsagate.foundation.utility.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");

	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String s_fiscalYear = (String) request.getAttribute("RfqHeader_fiscalYear");

	if (HiltonUtility.isEmpty(s_rfqNumber))			{ s_rfqNumber = "N/A";			}

	List vendorList = (List) request.getAttribute("rfqVendorList");
	int i_linecount = 0;
	if (vendorList!=null)
	{
		i_linecount = vendorList.size();
	}

	String	s_current_process = "HEADER_SUPPLIERS";
	String	s_current_page = "/rfq/rfq_suppliers.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	boolean editMode = false;

	if (role.getAccessRights("RFQ") > 1 && (s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENSOLICITATION) < 0 || s_rfqStatus.compareTo(DocumentStatus.RFQ_OPENAMENDMENT) == 0)) {
		editMode = true;
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<!--input type=hidden name=RfqNote_icHeader value="<%=s_icRfqHeader%>"-->
<!--input type=hidden name=RfqNote_icLine value="0"-->
<!--input type=hidden name=RfqNote_vendorId value=""-->
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="RfqHeader_fiscalYear" value="<%=s_fiscalYear%>"/>
<tsa:hidden name="RfqVendor_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="currentVendorId" value=""/>
<tsa:hidden name="VendorDocument_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="VendorDocument_vendorId" value=""/>
<tsa:hidden name="DocComment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocAttachment_icHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="returnPage" value="/rfq/rfq_suppliers.jsp"/>
<tsa:hidden name="returnMethod" value="RfqVendorRetrieveByHeader"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Suppliers</div>
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
	<td>&nbsp;</td>
	<td align=center width=100%>
		<table border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td width=100% align=center valign=top>
				<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdr>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr>
							<td width=22% height=18px class=browseHdr>&nbsp;<b><tsa:label labelName="rfq-supplier" defaultString="Supplier" /></b></td>
							<tsa:td field="rfq-total" width="20%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-total" defaultString="Total" /></b>&nbsp;</tsa:td>
<%	if (extRfqsActive) {%>
							<tsa:td field="rfq-totalScore" width="15%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-totalScore" defaultString="Score" /></b>&nbsp;</tsa:td>
<%	}%>
							<tsa:td field="rfq-responseDate" width="16%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-responseDate" defaultString="Response Date" /></b></tsa:td>
<%	if (!extRfqsActive) {%>
							<tsa:td field="rfq-promiseDate" width="15%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-promiseDate" defaultString="Promise Date" /></b></tsa:td>
<%	}%>
							<tsa:td field="rfq-bidValidTo" width="14%" height="18px" cssClass="browseHdr" align="right"><b><tsa:label labelName="rfq-bidValidTo" defaultString="Bid Valid To" /></b></tsa:td>
<%	if (editMode) { %>
							<td width=7% height=18px class=browseHdr align=right><b><tsa:label labelName="delete" defaultString="Delete" /></b></td>
<%	}%>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
<%	if (i_linecount == 0) {%>
						<tr>
							<td align=center><br><b><tsa:label labelName="msg-rfq-no-suppliers" defaultString="There are currently no suppliers on this Solicitation" />.</b><br><br></td>
						</tr>
<%	}
		for (int i = 0; i < i_linecount; i++)
		{
			RfqVendor rfqVendor = (RfqVendor) vendorList.get(i);
			RfqVendorPK rfqVendorPK = rfqVendor.getComp_id();
			String s_vendorId = rfqVendorPK.getVendorId();
			String s_vendorName = VendorManager.getInstance().getVendorName(oid, s_vendorId);
			if (s_vendorName.length() > 20)
			{
				s_vendorName = s_vendorName.substring(0, 20);
			}
			String	evaluationScore = "";
			String vendorTerms = "";
			String dateResponseRecv = HiltonUtility.getFormattedDate(d_today, oid, userDateFormat);
			String datePromised = HiltonUtility.getFormattedDate(d_today,oid, userDateFormat);
			String bidValidTo = "";
			String totalBid = "";
			String totalScore = "";
			if(rfqVendor.getDateResponseRecv() != null)
			{
				dateResponseRecv = HiltonUtility.getFormattedDate(rfqVendor.getDateResponseRecv(), oid, userDateFormat);
			}
			if(rfqVendor.getBidValidTo() != null)
			{
				bidValidTo = HiltonUtility.getFormattedDate(rfqVendor.getBidValidTo(), oid, userDateFormat);
			}

			BigDecimal differenceDates = HiltonUtility.getDateDifference(rfqVendor.getBidValidTo(), new Date());
			if (differenceDates.compareTo(new BigDecimal(0)) < 0 && rfqVendor.getBidCode().equalsIgnoreCase("SE")) 
			{
				totalBid = "Sealed";
			}
			else
			{
				if (rfqVendor.getBidsEntered().equals("Y")) {
					vendorTerms = rfqVendor.getPaymentTerms();
					datePromised = HiltonUtility.getFormattedDate(rfqVendor.getDatePromised(), oid, userDateFormat);
					totalBid = "$" + String.valueOf(HiltonUtility.getFormattedDollar(rfqVendor.getBidTotal(), oid));
	
					if (HiltonUtility.isEmpty(rfqVendor.getEvaluationStatus()) || rfqVendor.getEvaluationStatus().equals(DocumentStatus.EVALUATION_INCOMPLETE)) {
						totalScore = "(Pending) " + String.valueOf(rfqVendor.getTotalScore());
					} else {
						totalScore = String.valueOf(rfqVendor.getTotalScore());
					}
				} else {
					totalBid = "No Bids Submitted";
				}
			}
			pageContext.setAttribute("i", i);
%>
						<tr>
							<td width=15% class=browseRow valign=top nowrap><tsa:hidden id="vendorId_${i}" name="vendorId_${i}" value="<%=s_vendorId%>"/><a href="javascript: viewSupplier(<%=i%>); void(0);" title="Click here to View/Modify Details for this Supplier."><%=s_vendorName%></a></td>
							<tsa:td field="rfq-total" width="15%" cssClass="browseRow" valign="top" align="right" noWrap="nowrap"><%=totalBid%>&nbsp;</tsa:td>
<%	if (extRfqsActive) {%>
							<tsa:td field="rfq-evaluationScore" width="15%" cssClass="browseRow" valign="top" align="right"><%=totalScore%>&nbsp;</tsa:td>
<%	}%>
							<tsa:td field="rfq-responseDate" width="16%" cssClass="browseRow" valign="top" align="right"><%=dateResponseRecv%></tsa:td>
<%	if (!extRfqsActive) {%>
							<tsa:td field="rfq-promiseDate" width="15%" cssClass="browseRow" valign="top" align="right"><%=datePromised%></tsa:td>
<%	}%>
							<tsa:td field="rfq-bidValidTo" width="14%" cssClass="browseRow" valign="top" align="right"><%=bidValidTo%></tsa:td>
<%	if (editMode) { %>
							<td width=7% align=center class=browseRow valign=top><a href="javascript: deleteMe(<%=i%>);"><img src="<%=contextPath%>/images/delete.gif" alt="<tsa:label labelName="delete" defaultString="Delete" />" border=0></a></td>
<%		}%>
						</tr>
<%	} %>
				</table>
			</td>
		</tr>
		</table>

<%	if (editMode) { %>
		<table>
		<tr>
			<td align=center valign=top><br><a href="javascript: browseRfqVendors(); void(0);"><font class="reset"><b><tsa:label labelName="rfq-Add-Suppliers" defaultString="Add Suppliers" /></B></font></a></td></tr>
		</tr>
		<%if(oid.equalsIgnoreCase("vse06p")){ %>
		<tr>
			<td align=center valign=top><br><a href="javascript: browseNewRfqVendors(); void(0);"><font class="reset"><b><tsa:label labelName="rfq-Add-New-Supplier" defaultString="Add New Supplier" /></B></font></a></td></tr>
		</tr>
		<% } %>
		</table>
<%	} %>

	</td>
	<td>&nbsp;</td>
<%	if (s_view.equals("WIZARD")) { %>
	<td align=right><%@ include file="/rfq/rfq_sidebar.jsp" %></td>
<%	} %>
</tr>
</table>

<br>

<%	if (s_view.equals("CLASSIC")) { %>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
<%		if (editMode) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: browseRfqVendors();  void(0);"><tsa:label labelName="rfq-Add" defaultString="Add" /></a></div></td>
<%		} %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_summary.jsp', 'RfqRetrieve'); void(0);"><tsa:label labelName="rfq-Return" defaultString="Return" /></a></div></td>
</tr>
</table>
<%	} %>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var rfqnumber = "<%=headerEncoder.encodeForJavaScript(s_rfqNumber)%>";
	var fiscalyear = "<%=headerEncoder.encodeForJavaScript(s_fiscalYear)%>";
	var currentpage = "<%=s_current_page%>";
	var currentmethod = "<%=s_current_method%>";
	var currentprocessmethod = "<%=s_current_process_method%>";
	setNavCookie("/rfq/rfq_suppliers.jsp", "RfqRetrieve", "Solicitation Supplier", false);

	var browser = browserCheck();

	setTableHeights();

	function setTableHeights() {
		setTableHeight("itemTable", "itemRows", 18);
	}

	function setTableHeight(hdrTableName, rowsTableName, rowHeight) {
/*		var browseTable = document.getElementById(hdrTableName);
		var rowsTable = document.getElementById(rowsTableName);

		var tableHeight = ((rowsTable.rows.length) * rowHeight) + 20;

		browseTable.style.height = tableHeight + "px";
*/	}

	function deleteMe(row)
	{
		var num = document.getElementById("vendorId_" + row);
		setHiddenFields(getHiddenFields() + "<input type=hidden name='RfqVendor_vendorId' value='" + num.value + "'>");
		if (confirm("Are you sure you want to delete \"" + num.value  + "\"?"))
		{
			doSubmit('rfq/rfq_suppliers.jsp', 'RfqVendorDelete;RfqRetrieve');
		}
	}

	function viewSupplier(row)
	{
		var vendorId = document.getElementById("vendorId_" + row).value;
//		frm.RfqVendor_vendorId.value = vendorId;
//		frm.VendorDocument_vendorId.value = vendorId;
//		frm.RfqNote_vendorId.value = vendorId;
//		doSubmit('rfq/rfq_supplier_edit.jsp', 'RfqVendorRetrieveById;VendorDocumentRetrieveByVendor;RfqNoteRetrieveById');
		frm.currentVendorId.value = vendorId;
		doSubmit('rfq/rfq_supplier_summary.jsp', 'RfqRetrieve');
	}


	function browseRfqVendors()
	{
		frm.browseName.value = "rfqvendor";
		doSubmit('/browse/browse_filter_rfqvendor.jsp', 'BrowseGetOptions');
	}
	function browseNewRfqVendors()
	{
		var newInputField = "<input type='hidden' name='save_suplier' value='true'>";
		doSubmit('admin/supplier/supplier_new_info.jsp', 'BrowseGetOptions')
	}

// End Hide script -->
</SCRIPT>
