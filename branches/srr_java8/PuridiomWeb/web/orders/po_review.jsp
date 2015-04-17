<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.common.documents.ReferenceInfo" %>
<%@ page import="com.tsa.puridiom.po.tasks.PoBlanketInfo" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>
<%@ page import="com.tsa.puridiom.common.documents.ScheduleType"%>
<%@ page import="com.tsagate.foundation.utility.Utility"%>
<%@ page import="com.tsa.puridiom.stdtable.tasks.StdTableManager" %>
<%
	int i_size;
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	Contact contact = (Contact) request.getAttribute("contact");
	PoBlanketInfo blanketInfo = (PoBlanketInfo) request.getAttribute("blanketInfo");
	String checkChangeRequest  = HiltonUtility.ckNull((String) request.getAttribute("allowToCreateCheckRequest"));
	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	String s_ic_po_header = bd_ic_po_header.toString();
	BigDecimal	bd_ic_header_history = poHeader.getIcHeaderHistory();
	BigDecimal	bd_release_number = poHeader.getReleaseNumber();
	BigDecimal	bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_po_number = poHeader.getPoNumber();
	String	s_po_status = poHeader.getStatus();
	String	s_po_type = poHeader.getPoType();
	String	s_curr_code = poHeader.getCurrencyCode();
	String	s_receipt_required = poHeader.getReceiptRequired();
	String	errorMsg = (String) request.getAttribute("errorMsg");

	String inspectionDiv = "inspectionDiv" + 0;

 	if (s_receipt_required.equalsIgnoreCase("R"))	{	s_receipt_required = "Receipt Required";		}
	if (s_receipt_required.equalsIgnoreCase("P"))	{	s_receipt_required = "Previously Received";	}
	if (s_receipt_required.equalsIgnoreCase("N"))	{	s_receipt_required = "No Receipt Required";	}
	if (s_receipt_required.equalsIgnoreCase("E"))	{	s_receipt_required = "End User Receipt";		}

	if (HiltonUtility.isEmpty(s_po_number))	{	s_po_number = "N/A";									}
	if (bd_release_number == null)		{	bd_release_number = new BigDecimal(0000);	}
	if (bd_revision_number == null)		{	bd_revision_number = new BigDecimal(0000);	}
	if (HiltonUtility.isEmpty(s_po_status))		{	s_po_status = DocumentStatus.PO_INPROGRESS;	}

	List lineList = (List) poHeader.getPoLineList();
	int	i_line_count = 0;

	if (lineList != null)
	{
		i_line_count = lineList.size();
	}

	String revisionDelete = (String) request.getAttribute("revisionDelete");
	String checkRestoreRevision = (String) request.getAttribute("checkRestoreRevision");
	if(HiltonUtility.isEmpty(checkRestoreRevision)){ checkRestoreRevision = "N"; }
	Calendar today_date = new GregorianCalendar();

	String annio = Integer.toString(today_date.get(Calendar.YEAR));

	String hasRequisitionSeveralVendors = HiltonUtility.ckNull((String)request.getAttribute("hasRequisitionSeveralVendors"));
%>
<%@page import="com.tsa.puridiom.entity.Contact"%>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/po_review.js' type='text/javascript'></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/scheduleArrays.js"></script>
<script language='Javascript1.2' src="<%=contextPath%>/menu/poOptionArrays.js"></script>
<%@ include file="/system/header.jsp" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	String	s_po_contractsaveaspo = propertiesManager.getProperty("PO OPTIONS", "CONTRACTSAVEASPO", "N");
	String poFormMultiLanguages = PropertiesManager.getInstance(oid).getProperty("PO OPTIONS", "POFORMMULTIPLELANGUAJE", "N") ;
	//s_flag = HiltonUtility.ckNull(poHeader.getFlagChange());
	s_flag = "N";

	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	String tableType = "AC";
	if (oid.equalsIgnoreCase("qri06p"))
	{
		String location = "ACCOUNT_" + poHeader.getUdf1Code();
		tableType = propertiesManager.getProperty("BROWSE", location, "AC");
	}

	String	s_current_process = "HEADER_REVIEW";
	String	s_current_page = "/orders/po_review.jsp";
	String	s_current_method = "DoNothing";
	String	s_current_process_method = "";

	String	s_buyer_code = poHeader.getBuyerCode();
	UserProfile buyer = UserManager.getInstance().getUser(oid, s_buyer_code);
	UserProfile requisitioner = UserManager.getInstance().getUser(oid, poHeader.getRequisitionerCode());

	String icHeader = bd_ic_po_header.toString();

	UserRole roleR = UserManager.getInstance().getUserRole(oid,uid);

	String uploadItemsAccess = propertiesManager.getProperty("UPLOADITEMS","ENABLED","N");
	String uploadItemsRole = "";
	if (roleR.getAccessRights("UPLOADITEMS")<1) {
		uploadItemsRole = "disabled";
	}
	else{
		uploadItemsRole = Integer.toString(roleR.getAccessRights("UPLOADITEMS"));
	}

	boolean bAllowEdit = true;
	boolean bAllowSaveAs = true;
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer() && !user.getUserId().equals(s_buyer_code))
	{
		bAllowEdit = false;
	}
	if (oid.equalsIgnoreCase("akdoc") && !user.isAnAdminBuyer())
	{
		bAllowSaveAs = false;
	}

	boolean bAllowAdmin = false;
	List groupList = user.getUserRoles();
	if (groupList != null)
	{
		for (int i=0; i<groupList.size(); i++)
		{
			String gid = (String)groupList.get(i);
			if (gid.equalsIgnoreCase("ADMIN"))
			{
				bAllowAdmin = true;
				break;
			}
		}
	}

	boolean bIsRequisitionOrder = true;
	if (oid.equalsIgnoreCase("dtn07p") && HiltonUtility.isEmpty(poHeader.getRequisitionNumber()))
	{
		bIsRequisitionOrder = false;
	}

	boolean bDisplaySchedules = true;
	if (s_po_type.equalsIgnoreCase("CT") && oid.equalsIgnoreCase("bsc04p")) {
		bDisplaySchedules = false;
	}

	String shipViaDescription = "";
	if (oid.equalsIgnoreCase("bly07p")) {
		shipViaDescription = (String) StdTableManager.getInstance(oid).getStdTableDescription(oid, "SHP", poHeader.getShipViaCode());
	}
%>
<script language='Javascript1.2' type="text/javascript">
<!--
 	var oid = "<%=oid%>";
	var doubleStepReceiving = "<%=doubleStepReceiving%>";
	var singlePageReceipt = "<%=singlePageReceipt%>";
	var invoiceTxt = "<tsa:label labelName="invoice" defaultString="Invoice" checkRequired="false" />";
	var changeReqTxt = "<tsa:label labelName="reqtype-c" defaultString="Change Request" checkRequired="false" />";
	var isAnAdminBuyer = <%=user.isAnAdminBuyer()%>;
	viewType = "<%=headerEncoder.encodeForJavaScript(s_view)%>";
	poNumber = "<%=s_po_number%>";
	poStatus = "<%=s_po_status%>";
	allowCreateChangeRequest = "<%=checkChangeRequest%>";
	pyStatus = "<%=poHeader.getPyStatus()%>";
	poType = "<%=s_po_type%>";
	poRequisitioner = "<%=poHeader.getRequisitionerCode()%>";
	poBuyer = "<%=poHeader.getBuyerCode()%>";
	poDepartment = "<%=poHeader.getDepartmentCode()%>";
	ctInProgress = "<%=DocumentStatus.CT_INPROGRESS%>";
	ctAwarded = "<%=DocumentStatus.CT_AWARDED%>";
	poInProgress = "<%=DocumentStatus.PO_INPROGRESS%>";
	evaluationIncomplete = "<%=DocumentStatus.EVALUATION_INCOMPLETE%>";
	poAwarded = "<%=DocumentStatus.PO_AWARDED%>";
	poReceived = "<%=DocumentStatus.RCV_RECEIVED%>";
	poInvoiced = "<%=DocumentStatus.RCV_INVOICED%>";
	pyFullyInvoiced = "<%=DocumentStatus.PY_FULLYINVOICED%>";
	poClosed = "<%=DocumentStatus.CLOSED%>";
	poCancelled = "<%=DocumentStatus.CANCELLED%>"
	poAccess = <%=role.getAccessRights("PO")%>;
	supevalaccess = <%=role.getAccessRights("VEND")%>;
	revisionNumber = <%=bd_revision_number%>;
	viewPaymentHistory = <%=propertiesManager.getProperty("Sun", "PaymentHist", "N").equalsIgnoreCase("Y")%>;
	budgetActive = <%=propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")%>;
	uploadItemsAccess = "<%=uploadItemsAccess%>";
	uploadItemsRole = "<%=uploadItemsRole%>";
	allowEdit = <%=bAllowEdit%>;
	allowSaveAs = <%=bAllowSaveAs%>;
	allowAdmin = <%=bAllowAdmin%>;
	isRequisitionOrder = <%=bIsRequisitionOrder%>;
	lastRevision = "<%=poHeader.getLastRevision()%>";
	changeReqAccess = "<%=role.getAccessRights("REQC")%>";

	Array91= createPoOptionsMenu(Array91[0]);

	<% if (propertiesManager.getProperty("PO OPTIONS", "SHOW ONLY PAYMENT SCHEDULES", "N").equals("Y")) { %>
		Array98.splice(1,3);
		Array98.splice(2,1);
	<% } %>

//-->
</SCRIPT>
<%@ include file="/orders/po_hidden_fields.jsp" %>

<tsa:hidden name="PoHeader_rated" value="<%=poHeader.getRated()%>"/>
<tsa:hidden name="poTotal" value="<%=poHeader.getTotal()%>"/>
<tsa:hidden name="currencyCode" value="<%=s_curr_code%>"/>
<tsa:hidden name="checkRestoreRevision" value="<%=checkRestoreRevision%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="icPoHeader"	value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="frompage" value="review"/>
<tsa:hidden name="poIcReqHeader" value="<%= poHeader.getIcReqHeader().toString() %>"/>
<tsa:hidden name="Buyer_Code" value="<%= s_buyer_code %>"/>
<tsa:hidden name="PoHeader_poDate" value="<%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid, userDateFormat)%>"/>
<tsa:hidden name="PoHeader_requireDate" value="<%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid, userDateFormat)%>"/>
<tsa:hidden name="Flag" value="I"/>
<tsa:hidden name="today_date" value="<%=annio%>"/>
<tsa:hidden name="Current_year" value="I"/>
<tsa:hidden name="PoHeader_flagChange" value="<%=s_flag%>"/>
<tsa:hidden name="PoHeader_applyFiscalYear" value=""/>
<% if(s_po_status.compareTo(DocumentStatus.CLOSED) >= 0) { %>
	<tsa:hidden name="updateStatus" value="TRUE" />
<% } %>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow colspan=2><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=OrderType.toString(s_po_type, oid)%> <tsa:label labelName="information" defaultString="Information" /></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
    <%@ include file="/orders/po_display_number.jsp" %>
	</td>
</tr>
</table>

<table border="0" cellpadding="2" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
	<td width=100% valign=middle height=36px>
		<table border="0" cellpadding="2" cellspacing="0" width="100%">
		<tr>
			<td width=60% valign=middle><%@ include file="/system/error_msg.jsp" %></td>
			<td align="center" width="10%"></td>
<%	if ( !oid.equalsIgnoreCase("qri06p") && bAllowEdit && bDisplaySchedules && !oid.equalsIgnoreCase("vse06p")) {	%>
			<td align="center" width="10%" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu98')" onMouseOver="popUp('Menu98',event)"><tsa:label labelName="schedules" defaultString="Schedules" /></a></td>
<%	} else {%>
			<td align="center" width="10%"></td>
<%	}%>
			</td>
			<td align="center" width="20%" nowrap><a href="javascript: void(0);" onMouseOut="popDown('Menu91')" onMouseOver="popUp('Menu91',event)"><tsa:label labelName="moreOptions" defaultString="More Options" /></a></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
<tr>
  <td width="100%" align="center">
    <table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
    <tr>
      <td width="50%" align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="350px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="350px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<tsa:label labelName="po-general_information" defaultString="General Information" /></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow">
            <table border="0" cellspacing="0" cellpadding="2" width="100%">
            <tsa:tr field="po-buyerName">
              <td nowrap align="right" width="40%"><b><tsa:label labelName="po-buyerName" defaultString="Buyer" />:</b>&nbsp;</td>
              <td nowrap><%=buyer.getDisplayName()%></td>
            </tsa:tr>
            <tr <%=HtmlWriter.isVisible(oid, "orderDate",s_po_type)%>>
              <td nowrap align="right" width="40%"><b><tsa:label labelName="orderDate" defaultString="Order Date" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPoDate(), oid, userDateFormat)%></td>
            </tr>
          	<% if (poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0) {%>
          	<tr>
          		<td nowrap align="right" width="40%"><b><tsa:label labelName="po-revisionDate" defaultString="Revision Date" checkRequired="false"/>:</b>&nbsp;</td>
          		<td nowrap>
          			<%=HiltonUtility.getFormattedDate(poHeader.getRevisionDate(), oid, userDateFormat)%>
          		</td>
          	</tr>
          	<% } %>
            <tr <%=HtmlWriter.isVisible(oid, "po-fiscalYear",s_po_type)%>>
              <td nowrap align="right" width="40%"><b><tsa:label labelName="po-fiscalYear" defaultString="Fiscal Year" />:</b>&nbsp;</td>
              <td nowrap>
                <%=poHeader.getFiscalYear()%>
              </td>
            </tr>
            <tsa:tr field="contract">
              <td nowrap align="right" width="40%"><b><tsa:label labelName="contract" defaultString="Contract" />:</b>&nbsp;</td>
              <td nowrap><%=poHeader.getContractNo()%></td>
            </tsa:tr>
           <%	if (( oid.equalsIgnoreCase("hoy08p")) && (s_po_type.equalsIgnoreCase("BO"))||!oid.equalsIgnoreCase("hoy08p")) { %>
           <tr <%=HtmlWriter.isVisible(oid, "effectiveDate",s_po_type)%>>
              <td nowrap align="right" width="40%"><b><tsa:label labelName="effectiveDate" defaultString="Effective Date" />:&nbsp;</b></td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getEffectiveDate(), oid, userDateFormat)%></td>
           </tr>
           <tr <%=HtmlWriter.isVisible(oid, "expirationDate", s_po_type)%>>
			  <td nowrap align="right" width="40%"><b><tsa:label labelName="expirationDate" defaultString="Expiration Date" />:&nbsp;</b></td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getExpirationDate(), oid, userDateFormat)%></td>
		   </tr>
		   <%	}%>
            <tr <%=HtmlWriter.isVisible(oid, "promisedDate",s_po_type)%>>
              <td nowrap align="right" width="40%"><b><tsa:label labelName="promisedDate" defaultString="Promised Date" />:&nbsp;</b></td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getPromisedDate(), oid, userDateFormat)%></td>
            </tr>
            <tr <%=HtmlWriter.isVisible(oid, "po-requiredDate",s_po_type)%>>
              <td nowrap align="right" width="40%"><b><tsa:label labelName="po-requiredDate" defaultString="Required Date" />:&nbsp;</b></td>
              <td nowrap><%=HiltonUtility.getFormattedDate(poHeader.getRequiredDate(), oid, userDateFormat)%></td>
            </tr>
<%	if (!HiltonUtility.isEmpty(poHeader.getRequisitionNumber())) { %>
            <tsa:tr field="po-requisitionNumber">
              <td nowrap align="right" width="40%"><b><tsa:label labelName="po-requisitionNumber" defaultString="Requisition Number" />:&nbsp;</b></td>
              <td nowrap>
	<%	if (poHeader.getIcReqHeader().compareTo(new BigDecimal(0)) > 0) { %>
					<a href="javascript: reqPreview('<%=poHeader.getIcReqHeader()%>'); void(0);" title="View Requisition"><%=headerEncoder.encodeForHTML(poHeader.getRequisitionNumber())%></a>
	<%	} else {%>
					<%=headerEncoder.encodeForHTML(poHeader.getRequisitionNumber())%>
	<%	}%>
				</td>
            </tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(poHeader.getRequisitionerCode())) { %>
            <tsa:tr field="po-requisitionerName">
              <td nowrap align="right" width="40%"><b><tsa:label labelName="po-requisitionerName" defaultString="Requisitioner Name" />:&nbsp;</b></td>
              <td nowrap><%=requisitioner.getDisplayName()%></td>
            </tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(poHeader.getMsrNumber())) { %>
		    <tsa:tr field="po-msrNumber">
              <td nowrap align="right" width="40%"><b><tsa:label labelName="po-msrNumber" defaultString="MSR #" />:&nbsp;</b></td>
              <td nowrap><%=poHeader.getMsrNumber()%></td>
            </tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(poHeader.getDepartmentCode())) { %>
        <tsa:tr field="po-department">
          <td align="right" width="40%"><b><tsa:label labelName="po-department" defaultString="Department" />:&nbsp;</b></td>
          <td nowrap><%=poHeader.getDepartmentCode()%></td>
        </tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(poHeader.getRfqNumber())) { %>
            <tsa:tr field="po-solicitationNumber">
              <td nowrap align="right" width="40%"><b><tsa:label labelName="po-solicitationNumber" defaultString="Solicitation Number" />:&nbsp;</b></td>
              <td nowrap>
	<%	if (poHeader.getIcRfqHeader().compareTo(new BigDecimal(0)) > 0) { %>
					<a href="javascript: rfqPreview('<%=poHeader.getIcRfqHeader()%>'); void(0);" title="View Solicitation"><%=poHeader.getRfqNumber()%></a>
	<%	} else {%>
					<%=poHeader.getRfqNumber()%>
	<%	}%>
              </td>
            </tsa:tr>
<%	}
	if (propertiesManager.getProperty("PO OPTIONS", "DISPLAYRFUDFS", "N").equals("Y")) { %>
						<tsa:tr field="req-po-PO">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO01" defaultString="UDF1" />:</b></td>
							<td valign=top><%=poHeader.getUdf1Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO02">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO02" defaultString="UDF2" />:</b></td>
							<td valign=top><%=poHeader.getUdf2Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO03">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO03" defaultString="UDF3" />:</b></td>
							<td valign=top><%=poHeader.getUdf3Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO04">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO04" defaultString="UDF4" />:</b></td>
							<td valign=top><%=poHeader.getUdf4Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO05">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO05" defaultString="UDF5" />:</b></td>
							<td valign=top><%=poHeader.getUdf5Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO06">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO06" defaultString="UDF6" />:</b></td>
							<td valign=top><%=poHeader.getUdf6Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO07">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO07" defaultString="UDF7" />:</b></td>
							<td valign=top><%=poHeader.getUdf7Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO08">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO08" defaultString="UDF8" />:</b></td>
							<td valign=top><%=poHeader.getUdf8Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO09">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO09" defaultString="UDF9" />:</b></td>
							<td valign=top><%=poHeader.getUdf9Code()%></td>
						</tsa:tr>
						<tsa:tr field="po-PO10">
							<td nowrap align=right valign=top><b><tsa:label labelName="po-PO10" defaultString="UDF10" />:</b></td>
							<td valign=top><%=poHeader.getUdf10Code()%></td>
						</tsa:tr>
<%	} else if (!HiltonUtility.isEmpty(poHeader.getUdf1Code())) { %>
    <tr <%=HtmlWriter.isVisible(oid, "po-PO01",s_po_type)%>>
      <td nowrap align="right" width="40%"><b><tsa:label labelName="po-PO01" defaultString="UdF1" />:&nbsp;</b></td>
      <td nowrap><%=poHeader.getUdf1Code()%></td>
    </tr>
<%	} %>
			<tsa:tr field="po-currency">
				<td nowrap align="right" width="40%"><b><tsa:label labelName="po-currency" defaultString="Currency" />:</b></td>
				<td valign=top><%=poHeader.getCurrencyCode()%></td>
			</tsa:tr>
			<tsa:tr field="po-timezone">
				<td nowrap align="right" width="40%"><b><tsa:label labelName="po-timezone" defaultString="Time Zone" />:</b></td>
				<td valign=top><%=HiltonUtility.getFormattedTimeZone(poHeader.getTimeZone())%></td>
			</tsa:tr>
			<tsa:tr field="po-pystatus">
				<td nowrap align="right" width="40%"><b><tsa:label labelName="po-pystatus" defaultString="Invoice Status" />:</b></td>
				<td valign=top><%=DocumentStatus.toString(poHeader.getPyStatus(), oid)%></td>
			</tsa:tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
<%
  String	s_ship_address_line_1 = "";
  String	s_ship_address_line_2 = "";
  String	s_ship_address_line_3 = "";
  String	s_ship_address_line_4 = "";
  String	s_ship_city = "";
  String	s_ship_state = "";
  String	s_ship_postal_code = "";
  String	s_ship_country = "";

  Address shipTo = (Address) poHeader.getShipToAddress();
  if (shipTo != null)
  {
    s_ship_address_line_1 = shipTo.getAddressLine1();
    s_ship_address_line_2 = shipTo.getAddressLine2();
    s_ship_address_line_3 = shipTo.getAddressLine3();
    s_ship_address_line_4 = shipTo.getAddressLine4();
    s_ship_city = shipTo.getCity();
    s_ship_state = shipTo.getState();
    s_ship_postal_code = shipTo.getPostalCode();
    s_ship_country = shipTo.getCountry();
  }


  String formType = "PO";
%>
	<td width="50%" align="center" valign="top">
		<table border="0" cellspacing="0" cellpadding="0" width="350px" class="browseRow">
 <%	if (! s_po_type.equals("CT")) {	%>
		<tr>
			<td>
				<table border="1" cellspacing="0" cellpadding="0" width="100%" class="browseHdr">
				<tr>
					<td class="browseHdr" height="18px" nowrap>&nbsp;<tsa:label labelName="po-shipping_information" defaultString="Shipping Information" /></td>
				</tr>
				</table>
			</td>
		</tr>
        <tr>
			<td class="browseRow">
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr field="po-shipToCode"><td class="browseRow" height="12px" nowrap><b><%=poHeader.getShipToCode()%></b></td></tsa:tr>
				<tsa:tr field="po-shp-addressLine1"><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_1%></td></tsa:tr>
				</table>

				<table id="shipToRows" border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%	if (!HiltonUtility.isEmpty(s_ship_address_line_2))
		{ %>
				<tsa:tr field="po-shp-addressLine2"><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_2%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_3))
    	{ %>
				<tsa:tr field="po-shp-addressLine3"><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_3%></td></tsa:tr>
<%	}
		if (!HiltonUtility.isEmpty(s_ship_address_line_4))
		{ %>
            	<tsa:tr field="po-shp-addressLine4"><td class="browseRow" height="12px" nowrap><%=s_ship_address_line_4%></td></tsa:tr>
<%	} %>

				<tr><td class="browseRow" height="12px" nowrap><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td></tr>
				<tsa:tr field="po-shp-country"><td class="browseRow" height="12px" nowrap><%=s_ship_country%></td></tsa:tr>
				</table>

<%	if (!HiltonUtility.isEmpty(poHeader.getShipToContact()) && ! s_po_type.equals("CT")) { %>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tsa:tr field="po-shp-attention"><td class="browseRow" height="12px" nowrap><tsa:label labelName="po-shp-attention" defaultString="Attn" />:&nbsp;<%=poHeader.getShipToContact()%></td></tsa:tr>
				</table>
<%	} %>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
<%	if (!HiltonUtility.isEmpty(poHeader.getShipViaCode())) { %>
				<tsa:tr field="po-shipVia">
					<td class="browseRow" width=10% height="12px" nowrap><tsa:label labelName="po-shipVia" defaultString="Ship Via" />:</td>
					<td class="browseRow" width=90% height="12px" nowrap><%=poHeader.getShipViaCode()%></td>
				</tsa:tr>
<%	}
	if (!HiltonUtility.isEmpty(shipViaDescription)) { %>
				<tsa:tr field="po-shipVia">
					<td class="browseRow" width=10% height="12px" nowrap>&nbsp;</td>
					<td class="browseRow" width=90% height="12px" nowrap><%=shipViaDescription%></td>
				</tsa:tr>
<%	}
    	if (!HiltonUtility.isEmpty(poHeader.getPriorityCode())) { %>
				<tsa:tr field="po-priority"><td colspan=2 class="browseRow" height="12px" nowrap><tsa:label labelName="po-priority" defaultString="Priority" checkRequired="false" />:&nbsp;<%=poHeader.getPriorityCode()%></td></tsa:tr>
<%	}
	    if (!HiltonUtility.isEmpty(poHeader.getRouting())) { %>
				<tsa:tr field="po-routing"><td colspan=2 class="browseRow" height="12px" nowrap><tsa:label labelName="po-routing" defaultString="Routing" checkRequired="false"/>:&nbsp;<%=poHeader.getRouting()%></td></tsa:tr>
<%	} %>
				</table>
			</td>
		</tr>
<%	}	%>
		</table>
	</td>
	<td rowspan="5" align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
</tr>
<tr><td colspan="2">
<table style="margin-left: 87px" border="0">
  <tr>
  	<td valign=top align="right"><b><tsa:label labelName="po-purpose" defaultString="Purpose" checkRequired="false"/>:</b></td>
    <td> <textarea readonly class="browseRow" style="border: 0" style="overflow:hidden" cols="84" rows="5" >${esapi:encodeForHTML(poHeader.internalComments)}</textarea></td>
  </tr>
</table>
</td></tr>
<tr><td colspan="2">&nbsp;</td></tr>
<%
  String	s_vend_address_line_1 = "";
  String	s_vend_address_line_2 = "";
  String	s_vend_address_line_3 = "";
  String	s_vend_address_line_4 = "";
  String	s_vend_city = "";
  String	s_vend_state = "";
  String	s_vend_postal_code = "";
  String	s_vend_country = "";

  Address vendor = (Address) poHeader.getVendorAddress();
  if (vendor != null)
  {
    s_vend_address_line_1 = vendor.getAddressLine1();
    s_vend_address_line_2 = vendor.getAddressLine2();
    s_vend_address_line_3 = vendor.getAddressLine3();
    s_vend_address_line_4 = vendor.getAddressLine4();
    s_vend_city = vendor.getCity();
    s_vend_state = vendor.getState();
    s_vend_postal_code = vendor.getPostalCode();
    s_vend_country = vendor.getCountry();
  }

	String s_fax_number = "";
	String s_vendUdf1   = "";
	Vendor vendorEntity = (Vendor) request.getAttribute("vendor");
	if (vendorEntity != null) {
		s_fax_number = vendorEntity.getFaxNumber();
		s_vendUdf1   = vendorEntity.getVendUdf1();
	}

//    String FilenameXls = null;
//    if(request.getAttribute("FilenameXls")!= null)
//    {
//    FilenameXls = (String) request.getAttribute("FilenameXls");
//    }


%>
    <tr>
      <td width="400px" align="center" valign="top">
        <table id="supplierTable" border="0" cellspacing="0" cellpadding="0" width="350px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width=100% class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<tsa:label labelName="po-supplier_information" defaultString="Supplier Information" /></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow" id="supplierRows">
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tsa:tr field="po-supplier"><td colspan="2" class="browseRow" height="12px" nowrap><b><%=poHeader.getVendorId()%></b></td></tsa:tr>
            <tsa:tr field="po-sup-addressLine1"><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_address_line_1%></td></tsa:tr>

<%	if (!HiltonUtility.isEmpty(s_vend_address_line_2))
    { %>
            <tsa:tr field="po-sup-addressLine2"><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_address_line_2%></td></tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(s_vend_address_line_3))
    { %>
            <tsa:tr field="po-sup-addressLine3"><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_address_line_3%></td></tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(s_vend_address_line_4))
    { %>
            <tsa:tr field="po-sup-addressLine4"><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_address_line_4%></td></tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(s_vend_city) || !HiltonUtility.isEmpty(s_vend_state) || !HiltonUtility.isEmpty(s_vend_postal_code)) { %>
            <tr>
              <td colspan="2" class="browseRow" height="12px" nowrap>
<%		if (!HiltonUtility.isEmpty(s_vend_city)) { %>
                <%=s_vend_city%>&nbsp;
<%		}
      if (!HiltonUtility.isEmpty(s_vend_state)) { %>
                <%=s_vend_state%>&nbsp;
<%		}
      if (!HiltonUtility.isEmpty(s_vend_postal_code)) { %>
                <%=s_vend_postal_code%>
<%		} %>
              </td>
            </tr>
<%	} %>
<%	if (!HiltonUtility.isEmpty(s_vend_country)) { %>
            <tsa:tr field="po-sup-country"><td colspan="2" class="browseRow" height="12px" nowrap><%=s_vend_country%></td></tsa:tr>
<%	} %>

<%	if (!HiltonUtility.isEmpty(poHeader.getContactName()))
    { %>
            <tsa:tr field="po-sup-attention"><td colspan="2" class="browseRow" height="12px" nowrap><tsa:label labelName="po-sup-attention" defaultString="Attn" />:&nbsp;<%=poHeader.getContactName()%></td></tsa:tr>
<%	} %>
<%		if (!HiltonUtility.isEmpty(poHeader.getContactPhone())) { %>
						<tsa:tr field="po-sup-phone"><td colspan="2" class=browseRow height=12px nowrap><tsa:label labelName="po-sup-phone" defaultString="Phone" />:&nbsp;<%=poHeader.getContactPhone()%></td></tsa:tr>
<%		}
		if (!HiltonUtility.isEmpty(poHeader.getVendorId()) && poHeader.getEdiOrder().equals("F")) { %>
						<tsa:tr field="po-sup-faxNumber"><td colspan="2" class=browseRow height=12px nowrap><tsa:label labelName="po-poSendMethod" defaultString="PO Send Method" checkRequired="true" />:&nbsp;Fax PO</td></tsa:tr>
						<tsa:tr field="po-sup-faxNumber"><td colspan="2" class=browseRow height=12px nowrap><tsa:label labelName="sup-faxNumber" defaultString="Fax Number" checkRequired="true" />:&nbsp;<%=s_fax_number%></td></tsa:tr>
<%		}
		if(contact != null) {
			%>
		<tsa:tr field="cnt-email"><td colspan="2" class="browseRow" height="12px" nowrap><b><tsa:label labelName="contact-information" defaultString="Contact information" /></b></td></tsa:tr>
		<tr>
			<td nowrap align="right" width="40%"><tsa:label labelName="cnt-email" defaultString="email" />:&nbsp;</td>
			<td nowrap><%= contact.getEmailAddr() %></td>
		</tr>
		<tr>
			<td nowrap align="right" width="40%"><tsa:label labelName="contact-phoneNumber" defaultString="Phone" />:&nbsp;</td>
			<td nowrap><%= contact.getPhoneNumber() %></td>
		</tr>
		<tr>
			<td nowrap align="right" width="40%"><tsa:label labelName="contact-mobileNumber" defaultString="Mobile Phone" />:&nbsp;</td>
			<td nowrap><%= contact.getMobileNumber() %></td>
		</tr>
			<%
		}
%>
            </table>
          </td>
        </tr>
        </table>
      </td>
<%
  String	s_bill_address_line_1 = "";
  String	s_bill_address_line_2 = "";
  String	s_bill_address_line_3 = "";
  String	s_bill_address_line_4 = "";
  String	s_bill_city = "";
  String	s_bill_state = "";
  String	s_bill_postal_code = "";
  String	s_bill_country = "";

  Address billTo = (Address) poHeader.getBillToAddress();
  if (billTo != null)
  {
    s_bill_address_line_1 = billTo.getAddressLine1();
    s_bill_address_line_2 = billTo.getAddressLine2();
    s_bill_address_line_3 = billTo.getAddressLine3();
    s_bill_address_line_4 = billTo.getAddressLine4();
    s_bill_city = billTo.getCity();
    s_bill_state = billTo.getState();
    s_bill_postal_code = billTo.getPostalCode();
    s_bill_country = billTo.getCountry();
  }
%>
      <td width="400px" align="center" valign="top">
        <table id="billingTable" border="0" cellspacing="0" cellpadding="0" width="350px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width=100% class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<tsa:label labelName="po-billing_information" defaultString="Billing Information" /></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow" id="billingRows">
            <table border="0" cellspacing="0" cellpadding="2" width="210px" class="browseRow">
            <tsa:tr field="po-billToCode"><td class="browseRow" height="12px" nowrap><b><%=poHeader.getBillToCode()%></b></td></tsa:tr>
            <tsa:tr field="po-bil-addressLine1"><td class="browseRow" height="12px" nowrap><%=s_bill_address_line_1%></td></tsa:tr>

<%	if (!HiltonUtility.isEmpty(s_bill_address_line_2))
    { %>
            <tsa:tr field="po-bil-addressLine2"><td class="browseRow" height="12px" nowrap><%=s_bill_address_line_2%></td></tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(s_bill_address_line_3))
    { %>
            <tsa:tr field="po-bil-addressLine3"><td class="browseRow" height="12px" nowrap><%=s_bill_address_line_3%></td></tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(s_bill_address_line_4))
    { %>
            <tsa:tr field="po-bil-addressLine4"><td class="browseRow" height="12px" nowrap><%=s_bill_address_line_4%></td></tsa:tr>
<%	} %>
            <tr><td class="browseRow" height="12px" nowrap><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td></tr>
            <tsa:tr field="po-bil-country"><td class="browseRow" height="12px" nowrap><%=s_bill_country%></td></tsa:tr>

<%	if (!HiltonUtility.isEmpty(poHeader.getBillToContact()))
    { %>
            <tsa:tr field="po-bil-attention"><td class="browseRow" height="12px" nowrap><tsa:label labelName="po-bil-attention" defaultString="Attn" />:&nbsp;<%=poHeader.getBillToContact()%></td></tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(poHeader.getTermsCode()))
    { %>
            <tsa:tr field="po-paymentTerms"><td class="browseRow" height="12px" nowrap><tsa:label labelName="po-paymentTerms" defaultString="Terms" />:&nbsp;<%=poHeader.getTermsCode()%></td></tsa:tr>
<%	}
    if (!HiltonUtility.isEmpty(poHeader.getFobCode()))
    { %>
            <tsa:tr field="po-fob"><td class="browseRow" height="12px" nowrap><tsa:label labelName="po-fob" defaultString="FOB" />:&nbsp;<%=poHeader.getFobCode()%></td></tsa:tr>
<%	} %>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr><td colspan="2">&nbsp;</td></tr>
    <tr>
      <td width="300px" align="center" valign="top">
<%	if (processMap.containsKey("BLANKET_INFO")) { %>
        <table border="0" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
        <tr>
          <td>
            <table border="1" cellspacing="0" cellpadding="0" width="250px" class="browseHdr">
            <tr>
              <td class="browseHdr" height="18px" nowrap>&nbsp;<tsa:label labelName="blanket_information" defaultString="Blanket Information" /></td>
            </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td class="browseRow">
            <table border="0" cellspacing="0" cellpadding="2" width="100%">
            <tsa:tr field="effectiveDate">
              <td nowrap align="right" width="50%"><b><tsa:label labelName="effectiveDate" defaultString="Effective Date" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoEffective(), oid, userDateFormat)%></td>
            </tsa:tr>
            <tsa:tr field="expirationDate">
              <td nowrap align="right" width="50%"><b><tsa:label labelName="expirationDate" defaultString="Expiration Date" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedDate(blanketInfo.getPoExpires(), oid, userDateFormat)%></td>
            </tsa:tr>
            <tsa:tr field="releaseLimit">
              <td nowrap align="right" width="50%"><b><tsa:label labelName="releaseLimit" defaultString="Release Limit" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedCurrency(blanketInfo.getPoReleaseLimit(), s_curr_code, oid)%></td>
            </tsa:tr>
            <tsa:tr field="blanketLimit">
              <td nowrap align="right" width="50%"><b><tsa:label labelName="blanketLimit" defaultString="Blanket Limit" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedCurrency(blanketInfo.getPoBlanket(), s_curr_code, oid)%></td>
            </tsa:tr>
            <tsa:tr field="numberOfReleases">
              <td nowrap align="right" width="50%"><b><tsa:label labelName="numberOfReleases" defaultString="Number of Releases" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getBigDecimalFormatted(blanketInfo.getReleaseCount(), 0)%></td>
            </tsa:tr>
            <tsa:tr field="releaseTotal">
              <td nowrap align="right" width="50%"><b><tsa:label labelName="releaseTotal" defaultString="Release Total" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedCurrency(blanketInfo.getReleaseTotal(), s_curr_code, oid)%></td>
            </tsa:tr>
            <tsa:tr field="availableBlanket">
              <td nowrap align="right" width="50%"><b><tsa:label labelName="availableBlanket" defaultString="Available Blanket" />:</b>&nbsp;</td>
              <td nowrap><%=HiltonUtility.getFormattedCurrency(blanketInfo.getAvailableBlanket(), s_curr_code, oid)%></td>
            </tsa:tr>
            </table>
          </td>
        </tr>
        </table>
<%	} %>
      </td>
      <td width="300px" align="center" valign="top">
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>

<%	if (processMap.containsKey("SHOPCART")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="880px">
<tr>
  <td align="center" width="880px">
    <table id="itemTable" border="0" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
        <tr>
          <td width="100%" align="center" valign="top">
            <table border="0" cellspacing="0" cellpadding=4 width="100%" class="browseRow">
            <tr>
              <td width="2%" class="browseHdr" align=center nowrap>&nbsp;</td>
              <td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width="13%" class="browseHdr" align="center" nowrap><tsa:label labelName="po-hdg-itemNumber" defaultString="Item/Part #" checkRequired="false"/></td>
              <td <%=HtmlWriter.isVisible(oid, "po-catalog")%> width="11%" class="browseHdr" nowrap align="center"><tsa:label labelName="po-hdg-catalog" defaultString="Catalog" checkRequired="false"/></td>
              <td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width="18%" class="browseHdr" align="center" nowrap><tsa:label labelName="po-hdg-commodity" defaultString="Commodity" checkRequired="false"/></td>
              <td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width="10%" class="browseHdr" nowrap align="right"><tsa:label labelName="po-hdg-quantity" defaultString="Quantity" checkRequired="false"/></td>
              <td <%=HtmlWriter.isVisible(oid, "po-UOM")%> width="6%" class="browseHdr" align="center" nowrap><tsa:label labelName="po-hdg-uom" defaultString="UOM" checkRequired="false"/></td>
              <td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> width="10%" class="browseHdr" nowrap align="right"><tsa:label labelName="po-hdg-unitCost" defaultString="Unit Cost" checkRequired="false"/></td>
              <td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width="13%" class="browseHdr" nowrap align="right"><tsa:label labelName="po-hdg-extendedCost" defaultString="Ext Cost" checkRequired="false"/></td>
              <td <%=HtmlWriter.isVisible(oid, "po-lineStatus")%> width="17%" class="browseHdr" align="center" nowrap><tsa:label labelName="po-hdg-lineStatus" defaultString="Line Status" checkRequired="false"/></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td width="100%" align="center" valign="top">
      <tsa:hidden name="PoLine_icPoLine" value=""/>
        <table id=itemRow border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
        <tr>
          <td>
<%
	int iRow = 0;
    for (int i = 0; i < i_line_count; i++)
    {
      PoLine poLine = (PoLine) lineList.get(i);
      BigDecimal iclinekey = poLine.getIcLineKey();

      BigDecimal bd_quantity = HiltonUtility.getFormattedQuantity(poLine.getQuantity(), oid);
      BigDecimal bd_unit_price = poLine.getUnitPrice();
      BigDecimal b_um_factor = poLine.getUmFactor();
      BigDecimal bd_extended_cost = bd_quantity.multiply(bd_unit_price).multiply(b_um_factor);

      List shipToList = (List) poLine.getShipToList();
	  List billToList = (List) poLine.getBillToList();
	  AltText altText = poLine.getAltText();
	  pageContext.setAttribute("i", i);
%>
            <table border="0" cellspacing="0" cellpadding="4" width="100%" class="browseRow">

<%			if (i != 0) {	%>
			<tr><td colspan="10" align="center"><hr width="100%"></td></tr>
<%			}	%>

<%
			List commentList = (List) poLine.getDocCommentList();
			int iBeforeItem = 0;
            if (commentList != null)
			{
					for (int ix = 0; ix < commentList.size(); ix++)
					{
						DocComment docComment = (DocComment) commentList.get(ix);
						DocText docText = docComment.getDocText();

						String s_cmt_title = docComment.getCommentTitle();
						String s_cmt_print = docComment.getCommentPrint();
						String s_cmt_bold = docComment.getCommentBold();
						String s_cmt_place = docComment.getCommentPlace();
						String s_cmt_text = docText.getStdText();

						if (s_cmt_place.equals("A"))
						{
							continue;
						}
						/*if (s_cmt_print.equals("N"))
						{
							continue;
						}*/
						iBeforeItem++;
				%>
			<tr>
				<td class=browseRow nowrap>&nbsp;</td>
				<td colspan=7 class=browseRow>
					<b><tsa:label labelName="po-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
					<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
				</td>
			</tr>
<%
					}
			}
%>
            <tr>
              <td width="2%" class="browseRow" nowrap align="right">
              <a href="javascript: viewItem(<%=i%>); void(0);" title="Click here to View/Modify Item Details."><%=i+1%></a>
              <%
              if (assetsActive && poLine.getAsset().equals("Y") &&  Integer.valueOf(poLine.getStatus()).intValue() >= 3030 )
              {
               %><a href="javascript: viewAssetRelated('<%=iclinekey%>'); "><img src="<%=contextPath%>/images/asset2.GIF" border=0></a><%
              }
              %>
              </td>

              <td <%=HtmlWriter.isVisible(oid, "po-itemNumber")%> width="13%" class="browseRow" nowrap>
                <%=poLine.getItemNumber()%>
                <tsa:hidden id="icPoLine_${i}" name="icPoLine_${i}" value="<%=poLine.getIcPoLine()%>"/>
              </td>
              <td <%=HtmlWriter.isVisible(oid, "po-catalog")%> width="10%" class="browseRow" nowrap><%=poLine.getCatalogId()%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-commodity")%> width="19%" class="browseRow" nowrap><%=poLine.getCommodity()%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-quantity")%> width="10%" class="browseRow" nowrap align="center"><%=bd_quantity%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-uom")%> width="6%" class="browseRow" nowrap align="center"><%=poLine.getUmCode()%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-unitCost")%> width="10%" class="browseRow" nowrap align="center"><%=HiltonUtility.getCurrency(bd_unit_price, s_curr_code, oid, false)%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-extendedCost")%> width="13%" class="browseRow" nowrap align="center"><%=HiltonUtility.getCurrency(bd_extended_cost, s_curr_code, oid, false)%></td>
              <td <%=HtmlWriter.isVisible(oid, "po-lineStatus")%> width="17%" class="browseRow" nowrap align="center"><%=DocumentStatus.toString(poLine.getStatus())%></td>
            </tr>
            <tr>
              <td class="browseRow" nowrap>&nbsp;</td>
              <td colspan="8" class="browseRow"><%=poLine.getDescription()%></td>
            </tr>
            <%	if (altText != null && DictionaryManager.isVisible(oid, "po-description-alttext")) {
				DocText docText = altText.getDocText();
				if (docText != null) {%>
					<tr id=itemRows>
						<td class=browseRow nowrap>&nbsp;</td>
						<td colspan=9 class=browseRow><%=docText.getStdText()%></td>
					</tr>
				<% }
            }
				if ( oid.equalsIgnoreCase("bly07p") && !HiltonUtility.isEmpty(poLine.getVendorId())) { %>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=1 class=browseRow nowrap><b><tsa:label labelName="po-supplier" defaultString="Supplier" />:&nbsp;</b></td>
							<td colspan=7 class=browseRow><%=poLine.getVendorId()%></td>
						</tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=1 class=browseRow nowrap><b><tsa:label labelName="po-vendorName" defaultString="Vendor Name" />:&nbsp;</b></td>
							<td colspan=7 class=browseRow><%=poLine.getVendorName()%></td>
						</tr>
<% } %>

<%
		List accountList = (List) poLine.getAccountList();

		if (accountList != null)
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
				String s_fld4 = "";
				String s_fld5 = "";
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
				if ( !HiltonUtility.isEmpty(s_accArray[3]))
				{
					s_fld4 = s_accArray[3];
				}
				if ( !HiltonUtility.isEmpty(s_accArray[4]))
				{
					s_fld5 = s_accArray[4];
				}
%>
				<tr>
					<td class=browseRow nowrap>&nbsp;</td>
					<td colspan=7>
						<table border=0 width=100% cellpadding=2 cellspacing=0>
						<tr>
							<td class=browseRow align=right><b><tsa:label labelName="po-allocationString" defaultString="Account" />:</b></td>
							<td><%=s_account%>&nbsp;&nbsp;&nbsp;<%=HiltonUtility.getCurrency(bd_alloc_amt, s_curr_code, oid)%></td>
						</tr>
<%					if (!HiltonUtility.isEmpty(account.getAllocationDescription())) {	%>
						<tr>
							<td class=browseRow align=right width=75px><b><tsa:label labelName="po-allocationDescription" defaultString="Description" />:</b></td>
							<td class=browseRow><%=account.getAllocationDescription()%></td>
						</tr>
<%					}%>
						</table>
					</td>
				</tr>
<%			if (oid.equalsIgnoreCase("qri06p")) {	%>
				<tr>
					<td colspan="8">
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%				if ( !HiltonUtility.isEmpty(s_fld4)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b><tsa:label labelName="AC04" defaultString="UDF4" />:&nbsp;<%=s_fld4%></b>&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", s_fld4)%></td>
						</tr>
<%				}
					if ( !HiltonUtility.isEmpty(s_fld5)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b><tsa:label labelName="AC05" defaultString="UDF5" />:&nbsp;<%=s_fld5%></b>&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", s_fld5)%></td>
						</tr>
<%				}	%>
						</table>
					</td>
				</tr>
<%			}
			}
		}

			int iAfterItem = 0;
            if (commentList != null)
			{
					for (int ix = 0; ix < commentList.size(); ix++)
					{
						DocComment docComment = (DocComment) commentList.get(ix);
						DocText docText = docComment.getDocText();

						String s_cmt_title = docComment.getCommentTitle();
						String s_cmt_print = docComment.getCommentPrint();
						String s_cmt_bold = docComment.getCommentBold();
						String s_cmt_place = docComment.getCommentPlace();
						String s_cmt_text = docText.getStdText();

						if (s_cmt_place.equals("B"))
						{
							continue;
						}
						/*if (s_cmt_print.equals("N"))
						{
							continue;
						}*/
						iAfterItem++;
				%>
			<tr>
				<td class=browseRow nowrap>&nbsp;</td>
				<td colspan=7 class=browseRow>
					<b><tsa:label labelName="po-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
					<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
				</td>
			</tr>
<%
					}
			}

            if (poLine != null) {
    			List inspList = poLine.getInspectionList();
    			if (inspList != null && !inspList.isEmpty()) {
    			%>
    			<tr>
    					<tsa:td colspan="7" height="18px">
    						<table cellpadding="0" cellspacing="0" border="0"  width="100%">
    						<tsa:tr>
    				          <tsa:td align="left"><a href="javascript: inspection('<%=iRow%>')" title="Inspection"><img id='inspectionImg<%=iRow%>' valign='baseline' src='<%=contextPath%>/images/arrows_down.gif' class='processOnImg' border=0 alt="View Inspections"></a>
    				          	<a href="javascript: inspection('<%=iRow%>')" title="Inspection">&nbsp;Receipt Inspection Details</a></tsa:td>
    				        </tsa:tr>
    						<td>
    						<table cellspacing="0">
    						<div id="inspectionDiv" value="<%=iRow%>" style="display: none">
    						<ol>
    					<%
    						for (int ix = 0; ix < inspList.size(); ix++) {
    						InspectionHeader insp = (InspectionHeader) inspList.get(ix);
    						InspectionStd inspStd = insp.getInspectionStd();
    						if(inspStd == null){inspStd = new InspectionStd();}
    						%>
    						    <% String inspType = insp.getInspectType() ;
    						    		String inspDesc = "Receipt Inspection" ;
    						    		if (inspType == null) inspType = "RI" ;
    						    		if (inspType.equals("FI"))   {
    						    			inspDesc = "Field Inspection" ;
    						    		} else if (inspType.equals("GI"))   {
    						    			inspDesc = "General Inspection" ;
    						    		} else if (inspType.equals("CG"))   {
    						    			inspDesc = "CGD Inspection" ;
    						    		}
    						    %>
    							<li>
    								<%=HiltonUtility.ckNull(inspDesc) %>&nbsp;&nbsp;&nbsp; <%=HiltonUtility.ckNull(insp.getStandardCode())%>&nbsp;&nbsp;:&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspStd.getDescription()) %>
    								<br>
									&nbsp;&nbsp;&nbsp;CGD No : <%=HiltonUtility.ckNull(insp.getCgdNo())%>&nbsp;&nbsp;CGD Rev : <%=HiltonUtility.ckNull(insp.getCgdRev())%>
    							</li>
    								<ul>
    							<%	if (insp != null)
    								{
    									List inspLineLi	= (List) insp.getInspectionLineList();
    									if (inspLineLi != null) {
    										for (int j = 0; j < inspLineLi.size(); j++)
    										{
    												InspectionLine inspLine = (InspectionLine) inspLineLi.get(j) ;
    							%>
    												<li>&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritNo())%>
    												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getInspectCode())%>
    												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=HiltonUtility.ckNull(inspLine.getCritDescription())%></li>
    												<br></br>
    												<br>
    							<%			}
    									}
    								}
    								if (insp.getInspectionLineList() != null && insp.getInspectionLineList().size() > 0) {
    								} else {%>
    											<li><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noInspectionsItem", "There are no inspection records for this item")%>.</b></li>
    											<br></br>
    											<br>
    								<%}%>
    								</ul>
    						<% }%>
    						</ol>
    						</div>
    						</table>
    						</td>
    						</table>
    				</tsa:td>
    			</tr>
    			<% 		}
    				}
    			iRow++; %>
            <!-- Desde aqui es el add para el Attachment --><%
            List attachmentList = (List) poLine.getDocAttachmentList();
            boolean flagAttachment = false;
            if (attachmentList != null)
			{
					for (int ix = 0; ix < attachmentList.size(); ix++)
					{
						DocAttachment docAttachment = (DocAttachment) attachmentList.get(ix);
						String	filename = docAttachment.getDocFilename();
						String	ext = filename.substring(filename.lastIndexOf(".") + 1);
						%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow align="right">
							<%if(!flagAttachment){%>
								<b><tsa:label labelName="po-catalogItemAttachment" defaultString="Attachments" />:&nbsp;</b>
								<%flagAttachment=true;
							}%>

<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
							<td nowrap>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachment.getDocTitle()%></a>
							</td>
						</tr>
<%
					}
			} %>
<!-- Hasta aqui es el add para el Attachment -->
<%
		if (!(poLine.getRequisitionNumber().equalsIgnoreCase(poHeader.getRequisitionNumber())) || (!poLine.getRequisitionerCode().equals(poHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(poLine.getRequisitionerCode())))
		{	%>
            <tr>
              <td class=browseRow nowrap>&nbsp;</td>
<%			if (!poLine.getRequisitionNumber().equals(poHeader.getRequisitionNumber())) {%>
              <td <%=HtmlWriter.isVisible(oid, "ln-po-requisitionNumber")%> colspan=4 class=browseRow><b><tsa:label labelName="ln-po-requisitionNumber" defaultString="Requisition #" />:</b>&nbsp;<%=headerEncoder.encodeForHTML(poLine.getRequisitionNumber())%></td>
<%			}
				if (!poLine.getRequisitionerCode().equals(poHeader.getRequisitionerCode()) && !HiltonUtility.isEmpty(poLine.getRequisitionerCode())) {%>
              <td <%=HtmlWriter.isVisible(oid, "ln-po-requisitionerName")%> colspan=4 class=browseRow><b><tsa:label labelName="ln-po-requisitionerName" defaultString="Requisitioner Name" />:</b>&nbsp;<%=UserManager.getInstance().getUser(oid, poLine.getRequisitionerCode()).getDisplayName()%></td>
<%			}%>
            </tr>
<%	} %>
<%			if (shipToList != null)
			{
				for (int ix = 0; ix < shipToList.size(); ix++)
				{
					ShipTo lineShipTo = (ShipTo) shipToList.get(ix);
					ShipToPK shipToPK = lineShipTo.getComp_id();
					String s_shp_attn = lineShipTo.getAttention();


					Address shipToAddress = (Address) lineShipTo.getShipToAddress();
					if (shipToAddress != null)
					{
						s_ship_address_line_1 = shipToAddress.getAddressLine1();
						s_ship_address_line_2 = shipToAddress.getAddressLine2();
						s_ship_address_line_3 = shipToAddress.getAddressLine3();
						s_ship_address_line_4 = shipToAddress.getAddressLine4();
						s_ship_city = shipToAddress.getCity();
						s_ship_state = shipToAddress.getState();
						s_ship_postal_code = shipToAddress.getPostalCode();
						s_ship_country = shipToAddress.getCountry();
					}
%>
						<tr><td colspan=8><br></td></tr>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow>Ship <b><%=HiltonUtility.getFormattedQuantity(lineShipTo.getQuantity(), oid)%></b> to:</td>
						</tr>
						<tsa:tr field="req-shipToCode">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><b><%=shipToPK.getShipToCode()%></b></td>
						</tsa:tr>
<%					if (!HiltonUtility.isEmpty(s_ship_address_line_1))
						{ %>
						<tsa:tr field="req-shp-addressLine1">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_1%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_2))
						{ %>
						<tsa:tr field="req-shp-addressLine2">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_2%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_3))
						{ %>
						<tsa:tr field="req-shp-addressLine3">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_3%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_ship_address_line_4))
						{ %>
						<tsa:tr field="req-shp-addressLine4">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_address_line_4%></td>
						</tsa:tr>
<%					}%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_city%>&nbsp;<%=s_ship_state%>&nbsp;<%=s_ship_postal_code%></td>
						</tr>
<%
						if (!HiltonUtility.isEmpty(s_ship_country))
						{ %>
						<tsa:tr field="req-shp-country">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><%=s_ship_country%></td>
						</tsa:tr>
<%					}
						if (!HiltonUtility.isEmpty(s_shp_attn))
						{ %>
						<tsa:tr field="req-shp-attention">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><tsa:label labelName="req-shp-attention" defaultString="Attn" />: <%=s_shp_attn%></td>
						</tsa:tr>
<%					} %>
						<tsa:tr field="req-requiredDate">
							<td class=browseRow nowrap>&nbsp;</td>
							<td colspan=7 class=browseRow><tsa:label labelName="req-requiredDate" defaultString="Required By" />: <%=HiltonUtility.getFormattedDate(lineShipTo.getShipDate(), oid, userDateFormat)%></td>
						</tsa:tr>
<%				}
				}
%>
<%
	if (billToList != null)
	{
		for (int ix = 0; ix < billToList.size(); ix++)
		{
			BillTo billToOrder = (BillTo) billToList.get(ix);
			BillToPK billToPK = billToOrder.getComp_id();
			String s_bill_attn = billToOrder.getAttention();

			Address billToAddress = (Address) billToOrder.getBillToAddress();
			if (billToAddress != null)
			{
				s_bill_address_line_1 = billToAddress.getAddressLine1();
				s_bill_address_line_2 = billToAddress.getAddressLine2();
				s_bill_address_line_3 = billToAddress.getAddressLine3();
				s_bill_city = billToAddress.getCity();
				s_bill_state = billToAddress.getState();
				s_bill_postal_code = billToAddress.getPostalCode();
				s_bill_country = billToAddress.getCountry();
			}
%>
		<tr><td colspan=8><br></td></tr>
		<tr>
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow>Bill <b><%=HiltonUtility.getFormattedQuantity(billToOrder.getQuantity(), oid)%></b> to:</td>
		</tr>
		<tsa:tr field="req-billToCode">
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow><b><%=billToPK.getBillToCode()%></b></td>
		</tsa:tr>
<%					if (!HiltonUtility.isEmpty(s_bill_address_line_1))
		{ %>
		<tsa:tr field="req-bil-addressLine1">
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow><%=s_bill_address_line_1%></td>
		</tsa:tr>
<%					}
		if (!HiltonUtility.isEmpty(s_bill_address_line_2))
		{ %>
		<tsa:tr field="req-bil-addressLine2">
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow><%=s_bill_address_line_2%></td>
		</tsa:tr>
<%					}
		if (!HiltonUtility.isEmpty(s_bill_address_line_3))
		{ %>
		<tsa:tr field="req-bil-addressLine3">
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow><%=s_bill_address_line_3%></td>
		</tsa:tr>
<%					}
		if (!HiltonUtility.isEmpty(s_bill_address_line_4))
		{ %>
		<tsa:tr field="req-bil-addressLine4">
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow><%=s_bill_address_line_4%></td>
		</tsa:tr>
<%					}%>
		<tr>
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow><%=s_bill_city%>&nbsp;<%=s_bill_state%>&nbsp;<%=s_bill_postal_code%></td>
		</tr>
<%
		if (!HiltonUtility.isEmpty(s_bill_country))
		{ %>
		<tsa:tr field="req-bil-country">
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow><%=s_bill_country%></td>
		</tsa:tr>
<%					}
		if (!HiltonUtility.isEmpty(s_bill_attn))
		{ %>
		<tsa:tr field="req-bil-attention">
			<td class=browseRow nowrap>&nbsp;</td>
			<td colspan=7 class=browseRow><tsa:label labelName="req-bil-attention" defaultString="Attn" />: <%=s_bill_attn%></td>
		</tsa:tr>
<%					}
		}
	}
%>
	</table>
<%	} %>
          </td>
        </tr>
<%	if (i_line_count > 0) {%>
        <tr><td class="browseRow" width="100%"><hr size="0"></td></tr>
<%	}%>
        </table>

<%			if (processMap.containsKey("HEADER_TOTALS")) { %>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tsa:tr field="po-subtotal">
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-subtotal" defaultString="Subtotal" />:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getCurrency(poHeader.getSubtotal(), s_curr_code, oid, false)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="po-discountAmount">
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-discountAmount" defaultString="Discount" />:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getCurrency(poHeader.getDiscountAmount(), s_curr_code, oid, false)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="po-taxAmount">
				<%String taxLabel = "Tax Amount";
				if(!HiltonUtility.isQriCanadian(oid, poHeader.getUdf1Code()))
				{
					taxLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-taxAmount", "Tax Amount");
				}
				else
				{
					taxLabel = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-canTaxAmount", "GST");
				}%>
  			  <td class=browseRow nowrap align=right>&nbsp;<%=taxLabel%>:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getCurrency(poHeader.getTaxAmount(), s_curr_code, oid, false)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            <%if(HiltonUtility.isQriCanadian(oid, poHeader.getUdf1Code()) || !oid.equalsIgnoreCase("qri06p"))
            {%>
					<TR <%=HtmlWriter.isVisible(oid, "po-useTaxAmount")%>>
						<td class=browseRow nowrap align=right>&nbsp;<tsa:label labelName="po-useTaxAmount" defaultString="Tax" />:</td>
						<td class=browseRow nowrap align=right><%=HiltonUtility.getCurrency(poHeader.getUseTaxAmount(), s_curr_code, oid, false)%></td>
						<td class=browseRow nowrap align=right>&nbsp;</td>
					</tr>
			<%} %>
            <tsa:tr field="po-shippingCharges">
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-shippingCharges" defaultString="Shipping" />:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getCurrency(poHeader.getShippingCharges(), s_curr_code, oid, false)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="po-shippingTaxAmount">
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-shippingTaxAmount" defaultString="Shipping Tax" />:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getCurrency(poHeader.getShippingTax(), s_curr_code, oid, false)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="po-otherCharges">
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-otherCharges" defaultString="Other" />:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getCurrency(poHeader.getOtherCharges(), s_curr_code, oid, false)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="po-otherTaxAmount">
              <td width="65%" class="browseRow" nowrap align="right">&nbsp;<tsa:label labelName="po-otherTaxAmount" defaultString="Other Tax" />:</td>
              <td width="10%" class="browseRow" nowrap align="right"><%=HiltonUtility.getCurrency(poHeader.getOtherTax(), s_curr_code, oid, false)%></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            </table>
          </td>
        </tr>
        </table>

        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="browseRow">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
            <tsa:tr field="po-total">
              <td width="65%" class="browseRow" nowrap align="right"><b><tsa:label labelName="po-total" defaultString="Total" />:</b></td>
              <td width="10%" class="browseRow" nowrap align="right"><b><%=HiltonUtility.getCurrency(poHeader.getTotal(), s_curr_code, oid)%></b></td>
              <td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            <tsa:tr field="po-estCost">
            	<td width="65%" class="browseRow" norwap align="right"><b><tsa:label labelName="po-estCost" defaultString="Est Cost"/>:</b></td>
            	<td width="10%" class="browseRow" nowrap align="right"><b><%=HiltonUtility.getCurrency(poHeader.getEstimatedCost(), s_curr_code, oid)%></b></td>
            	<td width="25%" class="browseRow" nowrap align="right">&nbsp;</td>
            </tsa:tr>
            </table>
          </td>
        </tr>
        </table>
<%	} %>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	}%>
<br>

<%	if ((oid.equalsIgnoreCase("msg07p")) || processMap.containsKey("HEADER_ACCOUNTS")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="880px">
<tr>
  <td align="center" width="880px">
    <table border="0" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="70%" height="18px" class="browseHdr">&nbsp;<b><tsa:label labelName="po-account_information" defaultString="Account" /></b></td>
              <td width="15%" height="18px" class="browseHdr" align="right">&nbsp;<b><tsa:label labelName="po-allocationPercent" defaultString="Percent Alloc" />.</b></td>
              <td width="15%" height="18px" class="browseHdr" align="right">&nbsp;<b><tsa:label labelName="po-allocationAmount" defaultString="Amount Alloc" />.</b></td>
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
			List accList = (List) request.getAttribute("accountAllocAmountList");
			BigDecimal bd_total_amt = new BigDecimal(0.00);
			if (accList != null)
			{
				for (int i = 0; i < accList.size(); i++)
				{
					Object obj[] = (Object[]) accList.get(i);
					BigDecimal bd_alloc_amt = (BigDecimal) obj[0];
					if (bd_alloc_amt == null) {
						bd_alloc_amt = new BigDecimal(0);
					}

					String	s_account = "";
					String allocDesc = "";

					if (obj.length > 16)
					{
						allocDesc = (String) obj[16];
					}

					String udf = "";
					String fld4 = "";
					String fld5 = "";
					for (int j = 1; j < 16; j ++)
					{
						udf = (String) obj[j];
						if (!HiltonUtility.isEmpty(udf))
						{
							if (s_account.length() > 0)
							{
								s_account = s_account + s_account_separator + udf;
							}
							else
							{
								s_account = udf;
							}
						}
						if (j == 4 && !HiltonUtility.isEmpty(udf))
						{
							fld4 = udf;
						}
						if (j == 5 && !HiltonUtility.isEmpty(udf))
						{
							fld5 = udf;
						}
					}
					bd_total_amt = bd_total_amt.add(bd_alloc_amt);
%>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tr>
					<td width="75%" class="browseRow"><%=s_account%></td>
					<td width="25%" class="browseRow" align="right"><%=HiltonUtility.getCurrency(bd_alloc_amt, s_curr_code, oid, false)%></td>
				</tr>
<%				if (!HiltonUtility.isEmpty(allocDesc)) {	%>
				<tr>
					<td>
						<table border=0 cellpadding=2 cellspacing=0 width=100%>
						<tr>
							<td class=browseRow align=right width=75px><b><tsa:label labelName="po-allocationDescription" defaultString="Description" />:</b></td>
							<td class=browseRow><%=allocDesc%></td>
						</tr>
						</table>
					</td>
					<td>&nbsp;</td>
				</tr>
<%				}
					if (oid.equalsIgnoreCase("qri06p")) {	%>
				<tr>
					<td colspan="2">
						<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%					if ( !HiltonUtility.isEmpty(fld4)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b><tsa:label labelName="AC04" defaultString="UDF4" />:&nbsp;<%=fld4%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "04", fld4)%></td>
						</tr>
<%					}
						if ( !HiltonUtility.isEmpty(fld5)) {	%>
						<tr>
							<td width="50px">&nbsp;</td>
							<td><b><tsa:label labelName="AC05" defaultString="UDF5" />:&nbsp;<%=fld5%></b>&nbsp;&nbsp;&nbsp;<%=ReportUtils.getStdTableDescription(oid, tableType + "05", fld5)%></td>
						</tr>
<%					}	%>
						</table>
					</td>
				</tr>
<%				}	%>

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
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tr><td class="browseRow">There are no accounts associated with this order.</td></tr>
				</table>
<%		}	%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	} %>

<br>

<%	if (processMap.containsKey("HEADER_NOTES")) { %>
<table border="0" cellpadding="0" cellspacing="0" width="880px">
<tr>
  <td align="center" width="880px">
    <table border="0" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="75%" height="18px" class="browseHdr">&nbsp;<b><tsa:label labelName="po-comments" defaultString="Comment" /></b></td>
              <td width="8%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><tsa:label labelName="po-print" defaultString="Print" /></b></td>
              <td width="7%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><tsa:label labelName="po-bold" defaultString="Bold" /></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center" nowrap="nowrap">&nbsp;<b><tsa:label labelName="po-placement" defaultString="Placement" /></b></td>
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
      List cmtList = (List) poHeader.getDocCommentList();
      int ci = 0;
      if (cmtList != null)
      {
        for(int i = 0; i < cmtList.size(); i++)
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
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="75%" class="browseRow">
          	<%if(s_cmt_bold.equals("Y")){%><b><%}%><%=s_cmt_title%><%if(s_cmt_bold.equals("Y")){%></b><%}%>
          </td>
          <td width="8%" class="browseRow" align="center" valign="top"><%=s_cmt_print%></td>
          <td width="7%" class="browseRow" align="center" valign="top"><%=s_cmt_bold%></td>
          <td width="10%" class="browseRow" align="center" valign="top"><%=s_cmt_place%></td>
        </tr>
        </table>

        <table class="browseRow">
        <tr>
          <td width="50px">&nbsp;</td>
          <td width="100%">
          	<%if(s_cmt_bold.equals("Y")){%><b><%}%><%=s_cmt_text%><%if(s_cmt_bold.equals("Y")){%></b><%}%>
          </td>
        </tr>
        </table>
<% 			}
      }
      if (ci == 0) {%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr><td class="browseRow"><tsa:label labelName="po-nocomments" defaultString="There are no comments associated with this order" />.</td></tr>
        </table>
<%		}%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	} %>
<%	if (propertiesManager.isModuleActive("DOCUMENTS")) {%>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="880px">
<tr>
  <td align="center" width="880px">
    <table border="0" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="80%" height="18px" class="browseHdr">&nbsp;<b><tsa:label labelName="po-attachment" defaultString="Attachment" /></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-print" defaultString="Print" /></b></td>
              <td width="10%" height="18px" class="browseHdr" align="center">&nbsp;</td>
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
    List attachmentList = (List) poHeader.getDocAttachmentList();
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
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="80%" class="browseRow">
            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="browseRow">
            <tr>
              <td width="25px" align="center" valign="middle">
<%		if (ext.equalsIgnoreCase("DOC") || ext.equalsIgnoreCase("DOCX")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border="0" alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS") || ext.equalsIgnoreCase("XLSX")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border="0" alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border="0" alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT") || ext.equalsIgnoreCase("PPTX")) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border="0" alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border="0" alt="Open Attached Image"></a>
<%		} else {%>
              <a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border="0" alt="Open Attached Document"></a>
<%		}%>
              </td>
              <td>
                <a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
                <tsa:hidden name="docFilename" value="<%=filename%>"/>
              </td>
            </tr>
            </table>
          </td>
          <td width="10%" class="browseRow" align="center" valign="top"><%=docAttachment.getDocPrint()%></td>
          <td width="10%" class="browseRow" align="center" valign="top"></td>
        </tr>
        </table>

  <% 		}
      }
      if (ai == 0) {%>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr><td><tsa:label labelName="po-noattachments" defaultString="There are no attachments associated with this order" />.</td></tr>
        </table>
<%		}%>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<%	}%>


<!-- ADDED 2007/01/27 -->
<br><br>
<table border="0" cellpadding="0" cellspacing="0" width="880px">
<tr>
  <td align="center" width="880px">
<%
    List scheduleList = (List) poHeader.getScheduleList();
      int si = 0;
      String typeOfSchedule= "";
      String lastTypeOfSchedule= "";
      if (scheduleList != null) {
        for(int i = 0; i < scheduleList.size(); i++) {
          Schedule schedule = (Schedule) scheduleList.get(i);
          typeOfSchedule = schedule.getComp_id().getScheduleType();

          //if (schedule == null) {
          //  continue;
          //}
          si++;
%>
    <table border="0" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
<%

          if(!typeOfSchedule.equals(lastTypeOfSchedule)) {
%>
    <tr>
      <td>
        <table border="1" cellspacing="0" cellpadding="0" width="865px" class="browseHdr">
        <tr>
          <td>
            <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseHdr">
            <tr>
              <td width="55%" height="18px" class="browseHdr">&nbsp;<b><%=ScheduleType.toString(typeOfSchedule, oid)%></b></td>
              <td width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-scheduleDate" defaultString="Schedule" /></b></td>
              <td <%=HtmlWriter.isVisible(oid, "schedulesRevisedDate")%> width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-revisedDate" defaultString="Revised" /></b></td>
              <td <%=HtmlWriter.isVisible(oid, "schedulesCompletionDate")%> width="15%" height="18px" class="browseHdr" align="center">&nbsp;<b><tsa:label labelName="po-completionDate" defaultString="Completion" /></b></td>
            </tr>
            </table>
          </td>
        </tr>
        </table>
      </td>
    </tr>
<%
          }
%>
    <tr>
      <td>
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
        <tr>
          <td width="55%" class="browseRow"><%=(schedule.getDescription() == null)?"":String.valueOf(schedule.getDescription())%></td>
          <td width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getScheduleDate() == null)?"":String.valueOf(schedule.getScheduleDate())%></td>
		  <td <%=HtmlWriter.isVisible(oid, "schedulesRevisedDate")%> width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getRevisedDate() == null)?"":String.valueOf(schedule.getRevisedDate())%></td>
          <td <%=HtmlWriter.isVisible(oid, "schedulesCompletionDate")%> width="15%" class="browseRow" align="center" valign="top"><%=(schedule.getCompletionDate() == null)?"":String.valueOf(schedule.getCompletionDate())%></td>
        </tr>
        </table>
      </td>
    </tr>

    </table>

<%
		lastTypeOfSchedule = schedule.getComp_id().getScheduleType();
        }
      }
%>
  </td>
</tr>
</table>
<!-- END ADDED 2007/01/27 -->


<br><br>

<%-- <tsa:hidden name="FilenameXls" value="<%=FilenameXls --%>
<tsa:hidden name="formType" value="<%=formType%>"/>
<tsa:hidden name="icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="vendUdf1" value="<%=s_vendUdf1%>"/>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

/***** PLEASE ADD ANY JS FUNCTIONS THAT APPLY TO BOTH po_review.jsp and po_summary.jsp TO /scripts/po_review.js *****/

  frm = document.purchaseform;

  var ponumber = "<%=s_po_number%>";
  var porelease = "<%=bd_release_number%>";
  var fiscalyear = "<%=poHeader.getFiscalYear()%>";
  var currentpage = "<%=s_current_page%>";
  var currentmethod = "<%=s_current_method%>";
  var currentprocessmethod = "<%=s_current_process_method%>";
  var itemLocation = "<%=poHeader.getItemLocation()%>";
  poFormMultiLanguajes = "<%=poFormMultiLanguages%>";

setNavCookie("/orders/po_review.jsp", "PoRetrieve", "<%=OrderType.toString(s_po_type, oid)%>", true);

<%
if (role.getAccessRights("PO") < 2 || s_po_admin.equalsIgnoreCase("Y") && (s_po_status.compareTo(DocumentStatus.PO_APPROVING) >= 0 || (s_po_type.equals("CT") && s_po_contractsaveaspo.equals("Y") && s_po_status.compareTo(DocumentStatus.CT_APPROVING) >= 0))) { %>
			hideForwardButton();
<%	} %>

<%	if (!HiltonUtility.isEmpty(errorMsg)) {%>
	alert("<%= headerEncoder.encodeForJavaScript(errorMsg) %>");
<%	}%>

if(frm.checkRestoreRevision.value == "Y"){ restoreReview(); }

<%	if (hasRequisitionSeveralVendors.equalsIgnoreCase("Y")) { %>
	alert("This requisition contains mutliple suppliers");
<%	} %>

  function toggleDetailsDisplay(areaName, type) {
    var myAreas = document.getElementsByName(areaName);
    var myArea;
    var myImg = document.getElementById(areaName + "Img");
    var myText = document.getElementById(areaName + "Text");
    var hideArea = false;

    for (var i = 0; i < myAreas.length; i++) {
      myArea = myAreas(i);


      if (myArea.style.display == "none") {
        myArea.style.display = "block";
      }
      else {
        hideArea = true;
        myArea.style.display = "none";
      }
    }

    if (type == undefined) {
      type = "Details";
    }

    if (hideArea) {
      myImg.src = "<%=contextPath%>/images/plus.gif";
      myImg.alt = "View" + type;
      myText.innerText = "View " + type;
    }
    else {
      myImg.src = "<%=contextPath%>/images/minus.gif";
      myImg.alt = "Hide " + type;
      myText.innerText = "Hide " + type;
    }
  }

  function orderForward()
  {
    //goforward is used to check if any "errors" occured during the forward process.
    //errors where user input is required--- RR 01/12/05
    /*  this may need to change once receipts are done*/
    //frm.goforward.value = "Y";
<%	if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y") &&
		(!poHeader.getPoType().equals("CT") || propertiesManager.getProperty("PO APPROVALS", "CONTRACTAPPROVALS", "Y").equalsIgnoreCase("Y"))) { %>
      doSubmitToPopup('/orders/po_forward_options.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
<%	} else { %>
      frm.poForwardOption.value = "F";	/*  award Order*/
      doSubmit('/orders/po_forward_options.jsp', 'PoForwardNotification', 'WIDTH=350', 'HEIGHT=165');
<%	} %>
  }
  function switchView()
  {
    frm.viewType.value = "CLASSIC";
    doSubmit('/orders/po_summary.jsp', 'PoRetrieve');
  }

  function viewAssetRelated(iclinekey)
  {
	frm.allowBrowse.value="true";
	var newInputField = "<input type='hidden' name='Asset_icLineKey' value='" + iclinekey + "'>";
		newInputField = newInputField + "<input type='hidden' name='action'		value='po-review'>";
		newInputField = newInputField + "<input type='hidden' name='process'	value='AssetRetrieveByIcLineKey'>";
		newInputField = newInputField + "<input type='hidden' name='urlret'		value='/orders/po_review'>";
	setHiddenFields(newInputField);
	doSubmit("/asset/asset_po_browse.jsp", "AssetRetrieveByIcLineKey");

  }

    function ReadXlsItems(){
 	doSubmit("/orders/po_items.jsp","RfqReqPoAddItems;PoHeaderUpdate");
 	}

 	function ToReview(){
 	doSubmit("/orders/po_review.jsp", "PoRetrieve");
 	}

    function RfqReqPoAddItems(){

    	var uploadItemsRole = <%=uploadItemsRole%>
    	//alert(uploadItemsRole);

    	if (uploadItemsRole== "1"){
 			//alert("You Only Can See This Option");
 		}
    	if (uploadItemsRole!= "1"){
	  		doSubmit("/orders/po_xls_upload_items_new.jsp","DoNothing");
       }
    }

	function reqPreview(icReqHeader) {
		popupParameters = "RequisitionHeader_icReqHeader=" + icReqHeader;
		doSubmitToPopup('/requests/req_preview.jsp', 'RequisitionRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

    function rfqPreview(icRfqHeader) {
		popupParameters = "RfqHeader_icRfqHeader=" + icRfqHeader;
		doSubmitToPopup('/rfq/rfq_preview.jsp', 'RfqRetrieve', 'WIDTH=680px', 'HEIGHT=700px');
	}

	function poDeleteRevision(){
	 	var answer = confirm ("Do you want to delete this Revision?")
		if (answer)
		{
			doSubmit("/orders/po_review.jsp", "PoDeleteRevision;PoRetrieve");
		}
		else
		{
	 	}
 	}

	function restoreReview() {
		if(frm.checkRestoreRevision.value == "Y")
		{
			popupParameters = "PoHeader_icPoHeader=" + '<%=s_ic_po_header%>'+ ";revisionDelete=" + '<%=revisionDelete%>';
			doSubmitToPopup('/orders/po_restore_revision_popup.jsp', 'PoRetrieve', 'WIDTH=363', 'HEIGHT=155');
		}
	}

	function getBudgetCheck(action, baction, btype, bmake)
	{
    	frm.poaction.value = action;
	    frm.budgetAction.value = baction;
	    frm.budgetType.value = btype;
	    frm.budgetMake.value = bmake;
	    frm.budgetYear.value = frm.PoHeader_fiscalYear.value ;

		<%	if (propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
    	doSubmit('/orders/po_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
      	<% } else { %>
	    doSubmit('/orders/po_budget_check_no_popup.jsp', 'PoBudgetCheck');
      	<% } %>
	}
	function getBudgetReview()
	{
		popupParameters = popupParameters + "colname=BudgetAudit_icHeader;operator==;filter_txt=" + frm.PoHeader_icPoHeader.value + ";logicalOperator=AND;originalFilter=Y;sort=N;";
		browseLookup('', 'budget_review');
	}
	function BrowseOrderRevision()
	{
		browse("poheader-revision");
	}
	function resetCloseStatus() {
		if (verifyAction('Do you want Change the Status <%=DocumentStatus.toString(s_po_status, oid)%> for this Order?')) {
			doSubmit('/orders/po_change_status_closed.jsp', 'PoRetrieve');
		}
	}

	function inspection(iRow)
	{
		var myImg = document.getElementById("InspectionImg" + iRow);
		if ($('div[value*="'+ iRow + '"]').is(":hidden")) {
			$('div[value*="' + iRow + '"]').slideDown("slow");
			myImg.src = "<%=contextPath%>/images/arrows_up.gif";
			myImg.alt = "Hide Inspections";
		} else {
			$('div[value*="' + iRow + '"]').hide();
			myImg.src = "<%=contextPath%>/images/arrows_down.gif";
			myImg.alt = "View Inspections";
		}
	}

// End Hide script -->
</SCRIPT>
