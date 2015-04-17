<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.AccountRollup" %>
<%@ page import="com.tsa.puridiom.jasperreports.ReportUtils" %>
<%@ page import="java.io.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	String	s_ivc_number = invoiceHeader.getInvoiceNumber();
	String	s_ivc_status = invoiceHeader.getStatus();
	//DO NOT change to use the isEmpty methods. This fields should be null if they are not set
	String alreadyApproved = (String) request.getAttribute("alreadyApproved");
	String invalidApprover = (String) request.getAttribute("invalidApprover");
	String override = HiltonUtility.ckNull((String) request.getAttribute("override"));
	String alreadyApprovedMsg = "";
	String	s_curr_code = "";

	String			s_input = "" ;
	BigDecimal	invoiceTotal = invoiceHeader.getInvoiceTotal() ;
	BigDecimal	inspTotal = new BigDecimal(0);
	int				inspCount = 0 ;
	String			inspDate = "" ;


	if (s_ivc_status.equals("6005") && oid.equalsIgnoreCase("ctb08p")) {
		String		targetUrl =  propertiesManager.getProperty("IMIS", "targetUrl", "http://procsys-test:8080/puridiom");
		String		docId = invoiceHeader.getObmoNumber() ;
		HttpURLConnection connection = null;
		InputStream inputStream = null;
		URL 	url = null;

		if (docId == null) docId = "" ;
		System.out.println("DocId=" + docId);
		docId = docId.trim().toUpperCase() ;
		if (docId.startsWith("OBMO")) docId = docId.substring(4).trim() ;
		System.out.println("DocId=" + docId);

		targetUrl = targetUrl + "/ImisObligateExtServlet?action=InspDate&docId=" + docId ;

		try {
			url = new URL(targetUrl );
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}


		try {
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST") ;
			connection.setUseCaches(false);
			connection.setAllowUserInteraction(false);
			connection.setRequestProperty("Content-Type","text/xml");

			OutputStream os = connection.getOutputStream();

			OutputStreamWriter osw = new OutputStreamWriter(os);
			osw.write("");
			osw.flush();
			osw.close();

			inputStream = connection.getInputStream();
			DataInputStream iData = new DataInputStream(inputStream);
			String iLine = null;
			StringBuffer buf = new StringBuffer("");

			do {
				try {
					iLine = iData.readLine();
				}
				catch(IOException e) {
					System.out.println("readLine Exception: " + e.toString());
				}
				if (iLine == null) {
					break;
				}
				if (iLine.trim().length() > 0) {
					buf.append(iLine);
				}
			} while(true);

	        s_input = buf.toString();

			inputStream.close() ;

			String	s_lines[] = s_input.split("\t") ;
			for (int ix = 0; ix < s_lines.length; ix++) {
				String s_data[] = s_lines[ix].split("=") ;
				if (s_data.length > 0) {
					if (s_data[0].toLowerCase().startsWith("lineinspamt")) {
						BigDecimal inspAmt = new BigDecimal(s_data[1].trim()) ;
						System.out.println("s_data[1]=" + s_data[1]);
						System.out.println("inspAmt=" + inspAmt.toString()) ;
						inspTotal = inspTotal.add(inspAmt) ;
						System.out.println("inspTotal=" + inspTotal.toString()) ;
					}
					if (s_data[0].toLowerCase().startsWith("lineinspdate")) {
						inspDate = s_data[1] ;
						inspCount++ ;
					}
				}
			}


		}
		catch(IOException e) {
			e.printStackTrace() ;
//			throw e;
		}

	}
System.out.println("inspCount=" + inspCount) ;
System.out.println("inspdt=" + inspDate);
System.out.println("total=" + inspTotal.toString());

	if (alreadyApproved == null && invalidApprover == null)
	{
%>

<tsa:hidden name="ApprovalLog_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/><br>
<tsa:hidden name="InvoiceLine_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="InvoiceHeader_status" value="<%=invoiceHeader.getStatus()%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=invoiceHeader.getInvoiceNumber()%>"/>
<tsa:hidden name="as_return" value="TRUE"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="invoiceaction" value="APPROVE"/>
<tsa:hidden name="formtype" value="IV"/>
<tsa:hidden name="Account_icHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="formType" value=""/>
<tsa:hidden name="lineNumber" value=""/>
<tsa:hidden name="originalAccount_icLine" value=""/>
<tsa:hidden name="override" value="<%=override%>"/>
<tsa:hidden name="alreadyApprovedFailurePage" value="/invoice/iv_forward.jsp" />

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
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
	<td valign=bottom align=middle width=*>
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
			<tr><td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td></tr>
			<tr><td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td></tr>
		</table>
	</td>
</tr>
</table>

<br>

<div id="mybuttons">
<% if ((inspTotal.compareTo(invoiceTotal) >= 0) || ( ! oid.equalsIgnoreCase("ctb08p"))) { %>
<%@ include file="/invoice/iv_approval_buttons.jsp" %>
<% } else { %>
Inspection must be performed, before approval can proceed!
<% } %>
</div>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="center" width="680px">
		<table id="routingTable" border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
		<tr>
			<td>
				<table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
				<tr>
					<td width="100%" align="center" valign="top" class="browseHdrDk">
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "routingApproverName")%> width="25%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproverName", "Approver")%></td>
							<td <%=HtmlWriter.isVisible(oid, "app-title")%> width="20%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-title", "Title")%></td>
							<td <%=HtmlWriter.isVisible(oid, "routingAssigned")%> width="15%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAssigned", "Date Assigned")%></td>
							<td <%=HtmlWriter.isVisible(oid, "routingApproved")%> width="20%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproved", "Date Approved")%></td>
							<td <%=HtmlWriter.isVisible(oid, "routing-callForward")%> width="20%" class="browseHdrDk" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routing-callForward", "Call Forward")%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width=100% align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width="100%" class=summary>
				<tr>
					<td>
						<table border="0" cellspacing="0" cellpadding=2 width="100%" class=summary>
<%
		boolean hasPreviousNotes = false;
		boolean bDisplayButtons = true;
		boolean bEditInspectionDate = false;
		boolean bEditProcessedDate = false;
		boolean bEditApprovedDate = false;
		boolean bEditPaymentDate = false;
		List routingList = (List) request.getAttribute("approvalLogList");
		if (routingList != null)
		{
			for (int i = 0; i < routingList.size(); i++)
			{
				ApprovalLog appLog = (ApprovalLog) routingList.get(i);
				ApprovalLogPK appLogPK = appLog.getComp_id();
				String	s_approver_code = appLogPK.getUserId();
				UserProfile approver = UserManager.getInstance().getUser(oid, s_approver_code);
				UserProfile callForward = UserManager.getInstance().getUser(oid, appLog.getCallForward());
				String s_approved_date = HiltonUtility.getFormattedDate(appLog.getDateApproved(), oid, userDateFormat);
				if (appLog.getApproved().equals("A"))
				{
					s_approved_date = DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "currentApprover", "Current Approver");
					if (appLog.getApproverType().equalsIgnoreCase("P") && invoiceHeader.getInspectionDate() == null)
					{
						//bDisplayButtons = false;
						bEditInspectionDate = true;
					}
					if (appLog.getUserId().equalsIgnoreCase("PAYMENT") && invoiceHeader.getProcessedDate() == null)
					{
						//bDisplayButtons = false;
						bEditProcessedDate = true;
					}
					if (appLog.getUserId().equalsIgnoreCase("TREASURY"))
					{
						if (invoiceHeader.getImisApprovedDate() == null)
						{
							//bDisplayButtons = false;
							bEditApprovedDate = true;
						}
						if (invoiceHeader.getImisPaymentDate() == null)
						{
							//bDisplayButtons = false;
							bEditPaymentDate = true;
						}
					}
				}
				if (!HiltonUtility.isEmpty(appLog.getApproverNotes()))
				{
					hasPreviousNotes = true;
				}
				String	altApproverMsg = "";
		        if(appLog.getApproved().equals("D")){
		        	String	s_approver_deferred = appLog.getDeferId();
					if(!HiltonUtility.isEmpty(s_approver_deferred))
					{
						UserProfile approverDeferred = UserManager.getInstance().getUser(oid, s_approver_deferred);
						altApproverMsg = approver.getDisplayName() + " " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovedOnDefer", "deferred approval to") + "  " + approverDeferred.getDisplayName();
					}
				}
%>

						<tr>
						<tr>
				            <%if (!HiltonUtility.isEmpty(altApproverMsg))	{ %>
				              <td <%=HtmlWriter.isVisible(oid, "routingApproverName")%> width="25%" class=summary><%=altApproverMsg%></td>
				            <%} else { %>
				              <td <%=HtmlWriter.isVisible(oid, "routingApproverName")%> width="25%" nowrap class=summary><%=approver.getDisplayName()%></td>
				            <%} %>
							<td <%=HtmlWriter.isVisible(oid, "app-title")%> width="20%" nowrap class=summary><%=approver.getTitle()%></td>
							<td <%=HtmlWriter.isVisible(oid, "routingAssigned")%> width="15%" nowrap class=summary><%=HiltonUtility.getFormattedDate(appLog.getDateAssigned(), oid, userDateFormat)%></td>
							<td <%=HtmlWriter.isVisible(oid, "routingApproved")%> width="20%" nowrap class=summary><%=s_approved_date%></td>
							<td <%=HtmlWriter.isVisible(oid, "routing-callForward")%> width="20%" nowrap class=summary><%=callForward.getDisplayName()%></td>
						</tr>
<%
			}	//end for loop
			if (hasPreviousNotes)
			{
%>
						<tr><td><br></td></tr>
						<tr><td colspan="6"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-previousnotes", "Previous Approver Notes")%></b></td></tr>
<%
				for (int i = 0; i < routingList.size(); i++)
				{
					ApprovalLog appLog = (ApprovalLog) routingList.get(i);
					ApprovalLogPK appLogPK = appLog.getComp_id();
					String	s_approver_code = appLogPK.getUserId();
					UserProfile approver = UserManager.getInstance().getUser(oid, s_approver_code);
					String	s_approver_name = approver.getFirstName() + " " + approver.getLastName();
					if (!HiltonUtility.isEmpty(appLog.getApproverNotes()))
					{
%>
						<tr><td colspan="6"><%=s_approver_name%> wrote:</td></tr>
						<tr><td colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=appLog.getApproverNotes()%><br><Br></td></tr>
<%
					}
				}	//end for loop
			}	//end if PreviousNotes
		}
%>
						<tr><td colspan="6"><br></td></tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td align="center" width="680px">
		<table border="0" cellpadding="0" cellspacing="2" align="center" width=665px  class="summary">
		<tr <%=HtmlWriter.isVisible(oid, "approvalNotes")%>>
			<td colspan=2><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "approvalNotes", "Notes")%>:</b></td>
		</tr>
		<tr>
			<td <%=HtmlWriter.isVisible(oid, "approvalNotes")%>><TEXTAREA NAME="ApprovalLog_approverNotes" WRAP="VIRTUAL" ROWS="8" COLS="67" onKeyDown="limitText(this,255);" onKeyUp="limitText(this,255);"></TEXTAREA></td>
			<td valign="top">
				<table border="0" cellpadding="0" cellspacing=2 align="center" class="summary" valign="top">
<%	if (propertiesManager.isModuleActive("SIGNATURE") && !oid.equalsIgnoreCase("DTN07P")) {%>
				<tr <%=HtmlWriter.isVisible(oid, "app-signature")%>>
					<td valign="middle" nowrap>
						<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-signature", "Signature")%>:&nbsp;</b>
						<input type="checkbox" name="c_app_signed" value="N">
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "app-signaturePassword")%>>
					<td valign="middle" nowrap>
						<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "app-signaturePassword", "Approver")%>:&nbsp;</b>
						&nbsp;<input type="password" name="signature_password" autocomplete="off" value="" onchange="upperCase(this);">
					</td>
				</tr>
				<tr>
					<td width="100%"><hr color="#9999CC"></td>
				</tr>
<%	} %>
				<tr <%=HtmlWriter.isVisible(oid, "reroute")%>>
					<td valign="middle" nowrap>
						<b><A HREF="javascript: browseLookup('routeTo', 'vchapprover'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reroute", "Reroute To")%>:</b></a>&nbsp;
						<input type="text" name="routeTo" onfocus="this.blur();" onchange="upperCase(this);">
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "reroute")%>>
					<td valign="bottom" nowrap>
						<input type="checkbox" name="c_checkbox" value="Y" onclick="setReturn();" onfocus="checkAction('reroute');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "returnmereroute", "Return to me after reroute")%>.
						<tsa:hidden name="returnTo" value=""/></td>
					</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "defer")%>>
					<td valign="middle" nowrap>
						<b><A HREF="javascript: browseLookup('deferTo', 'vchapprover'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "defer", "Defer To")%>:</b></a>&nbsp;
						<input type="text" name="deferTo" onfocus="this.blur();" onchange="upperCase(this);">
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="2"><br><br></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="680px" align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="665px">
		<tr>
			<td width=50% align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
				<tr>
					<td>
						<table border=1 cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
						<tr>
							<td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="browseRow">
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tr <%=HtmlWriter.isVisible(oid, "ivc-enteredby")%>>
							<td nowrap align="right"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-enteredby", "Entered By")%>:&nbsp;</td>
							<td nowrap width="100%"><%=invoiceHeader.getEnteredBy()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "invoiceDate")%>>
							<td align=right <%=HtmlWriter.isVisible(oid, "invoiceDate")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceDate", "Invoice Date")%>:&nbsp;</td>
							<td nowrap width="100%"><%=HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "invoiceDueDate")%>>
							<td align=right <%=HtmlWriter.isVisible(oid, "invoiceDueDate")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "invoiceDueDate", "Payment Due Date")%>:&nbsp;</td>
							<td nowrap width="100%"><%=HiltonUtility.getFormattedDate(invoiceHeader.getPaymentDueDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-obmoNumber")%>>
							<td nowrap align=right valign=top width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoNumber", "OBMO Number")%>:</td>
							<td nowrap valign=top><%=invoiceHeader.getObmoNumber()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-obmoDate")%>>
							<td nowrap align=right valign=top width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoDate", "OBMO Date")%>:</td>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getObmoDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-obmoTotal")%>>
							<td nowrap align=right valign=top width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-obmoTotal", "OBMO Total")%>:</td>
							<td valign=top><%=HiltonUtility.getFormattedCurrency(invoiceHeader.getObmoTotal(), s_curr_code, oid, true)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-inspectionDate")%>>
							<td nowrap align=right valign=top width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-inspectionDate", "Inspection Date")%>:</td>
<%	if (bEditInspectionDate) { %>
							<td>
								<input type="text" name="InvoiceHeader_inspectionDate" tabindex="6" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getInspectionDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('InvoiceHeader_inspectionDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
							</td>
<%	} else { %>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getInspectionDate(), oid, userDateFormat)%></td>
<%	} %>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-processedDate")%>>
							<td nowrap align=right valign=top width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-processedDate", "Processed Date")%>:</td>
<%	if (bEditProcessedDate) { %>
							<td>
								<input type="text" name="InvoiceHeader_processedDate" tabindex="6" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getProcessedDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('InvoiceHeader_processedDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
							</td>
<%	} else { %>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getProcessedDate(), oid, userDateFormat)%></td>
<%	} %>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-imisApprovedDate")%>>
							<td nowrap align=right valign=top width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-imisApprovedDate", "Imis Approved Date")%>:</td>
<%	if (bEditApprovedDate) { %>
							<td>
								<input type="text" name="InvoiceHeader_imisApprovedDate" tabindex="6" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getImisApprovedDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('InvoiceHeader_imisApprovedDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
							</td>
<%	} else { %>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getImisApprovedDate(), oid, userDateFormat)%></td>
<%	} %>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-imisPaymentDate")%>>
							<td nowrap align=right valign=top width=40%><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-imisPaymentDate", "Imis Payment Date")%>:</td>
<%	if (bEditPaymentDate) { %>
							<td>
								<input type="text" name="InvoiceHeader_imisPaymentDate" tabindex="6" size="15" maxlength="15" value="<%=HiltonUtility.getFormattedDate(invoiceHeader.getImisPaymentDate(), oid, userDateFormat)%>">
								<a href="javascript: show_calendar('InvoiceHeader_imisPaymentDate', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a date or enter a date in the box on the left." valign=bottom border=0></a>
							</td>
<%	} else { %>
							<td nowrap valign=top><%=HiltonUtility.getFormattedDate(invoiceHeader.getImisPaymentDate(), oid, userDateFormat)%></td>
<%	} %>
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
							<td align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-IV01", "UDF1")%>:&nbsp;</td>
							<td nowrap width="100%"><%=s_udf_1_code%></td>
				        </tr>
						<% } %>
						</table>
					</td>
				</tr>
				</table>
			</td>
			<td width=50% align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width=320px class="browseHdrDk">
				<tr>
					<td>
						<table border=1 cellspacing="0" cellpadding="0" width=320px class="browseHdrDk">
						<tr>
							<td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
<%
	Address shipTo = (Address) invoiceHeader.getShipToAddress();
	if (shipTo == null)
	{
		shipTo = new Address();
	}
%>
				<tr>
					<td class="browseRow">
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shipToCode")%>>
							<td class="browseRow" height="12px" nowrap><b><%=invoiceHeader.getShipToCode()%></b></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine1")%>>
							<td class="browseRow" height="12px" nowrap><%=shipTo.getAddressLine1()%></td>
						</tr>
<%
			if (!HiltonUtility.isEmpty(shipTo.getAddressLine2()))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine2")%>>
							<td class="browseRow" height="12px" nowrap><%=shipTo.getAddressLine2()%></td>
						</tr>
<%		}
			if (!HiltonUtility.isEmpty(shipTo.getAddressLine3()))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine3")%>>
							<td class="browseRow" height="12px" nowrap><%=shipTo.getAddressLine3()%></td>
						</tr>
<%		}
			if (!HiltonUtility.isEmpty(shipTo.getAddressLine4()))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-shp-addressLine4")%>>
							<td class="browseRow" height="12px" nowrap><%=shipTo.getAddressLine4()%></td>
						</tr>
<%		}
			if (!HiltonUtility.isEmpty(shipTo.getCityStatePostal()))
			{ %>
						<tr>
							<td class="browseRow" height="12px" nowrap><%=shipTo.getCityStatePostal()%></td>
						</tr>
<%		} %>
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

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="680px" align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="665px">
		<tr>
			<td width=50% align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
				<tr>
					<td>
						<table border=1 cellspacing="0" cellpadding="0" width="320px" class="browseHdrDk">
						<tr>
							<td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "supplier_information", "Supplier Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
<%
		InvoiceVendor vendor= (InvoiceVendor) request.getAttribute("invoiceVendor");
		InvoiceAddress vendAddr = (InvoiceAddress) request.getAttribute("invoiceAddress");
%>
<% if(vendAddr!=null){ %>
				<tr>
					<td class="browseRow">
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tr <%=HtmlWriter.isVisible(oid, "ivc-supplier")%>><td class="browseRow" height="12px" nowrap><b><%=invoiceHeader.getVendorId()%></b></td></tr>
									<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine1")%>><td class="browseRow" height="12px" nowrap><%=vendAddr.getAddressLine1()%></td></tr>
<%	if (!HiltonUtility.isEmpty(vendAddr.getAddressLine2())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=vendAddr.getAddressLine2()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(vendAddr.getAddressLine3())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=vendAddr.getAddressLine3()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(vendAddr.getAddressLine4())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=vendAddr.getAddressLine4()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(vendAddr.getCityStatePostal())) { %>
						<tr><td class="browseRow" height="12px" nowrap><%=vendAddr.getCityStatePostal()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(vendAddr.getCountry())) {%>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-country")%>><td class="browseRow" height="12px" nowrap><%=vendAddr.getCountry()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(invoiceHeader.getContactName())) { %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-sup-attention")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-sup-attention", "Attn")%>: <%=invoiceHeader.getContactName()%></td></tr>
<%	}
		if (!HiltonUtility.isEmpty(invoiceHeader.getApReference())) { %>
			<tr <%=HtmlWriter.isVisible(oid, "ivc-apreference")%>><td class="browseRow" height="12px" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-apreference", "AP Reference")%>: <%=invoiceHeader.getApReference()%></td></tr>
<%	} %>
						</table>
					</td>
				</tr>
<% } %>
				</table>
			</td>
			<td width=50% align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width=320px class="browseHdrDk">
				<tr>
					<td>
						<table border=1 cellspacing="0" cellpadding="0" width=320px class="browseHdrDk">
						<tr>
							<td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "billing_information", "Billing Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
<%
	Address billTo = (Address) invoiceHeader.getBillToAddress();
	if (billTo == null)
	{
		billTo = new Address();
	}
%>
				<tr>
					<td class="browseRow">
						<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
						<tr <%=HtmlWriter.isVisible(oid, "ivc-billToCode")%>>
							<td class="browseRow" height="12px" nowrap><b><%=invoiceHeader.getBillToCode()%></b></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine1")%>>
							<td class="browseRow" height="12px" nowrap><%=billTo.getAddressLine1()%></td>
						</tr>
<%		if (!HiltonUtility.isEmpty(billTo.getAddressLine2()))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine2")%>><td class="browseRow" height="12px" nowrap><%=billTo.getAddressLine2()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(billTo.getAddressLine3()))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine3")%>><td class="browseRow" height="12px" nowrap><%=billTo.getAddressLine3()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(billTo.getAddressLine4()))
			{ %>
						<tr <%=HtmlWriter.isVisible(oid, "ivc-bil-addressLine4")%>><td class="browseRow" height="12px" nowrap><%=billTo.getAddressLine4()%></td></tr>
<%		}
			if (!HiltonUtility.isEmpty(billTo.getCityStatePostal()))
			{ %>
						<tr><td class="browseRow" height="12px" nowrap><%=billTo.getCityStatePostal()%></td></tr>
<%		} %>
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

<div id="commentBefore" style="display:none;">
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class="browseHdrDk">
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class="browseHdrDk">
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class="browseHdrDk">
						<tr>
							<td width=75% height=18px class="browseHdrDk">&nbsp;<b>Comment</b></td>
							<td width=8% height=18px class="browseHdrDk" align=center>&nbsp;<b>Print</b></td>
							<td width=7% height=18px class="browseHdrDk" align=center>&nbsp;<b>Bold</b></td>
							<td width=10% height=18px class="browseHdrDk" align=center>&nbsp;<b>Placement</b></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" cellspacing="0" cellpadding="2" width="100%">
<%
	int iBefore = 0;
	List cmtList = (List) invoiceHeader.getDocCommentList();
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
				continue;
			}
			//if (s_cmt_print.equals("N"))
			//{
			//	continue;
			//}
			iBefore++;
%>
				<tr>
					<td width="75%" class="browseRow"><% if (docComment.getCommentBold().equals("Y")) { %><b><% } %><%=docText.getStdText()%><% if (docComment.getCommentBold().equals("Y")) { %></b><% } %></td>
				</tr>
<%	}
	} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=0 cellspacing=0 cellpadding=0 width=665px class="browseHdrDk">
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class="browseHdrDk">
				<tr>
					<td width=100% align=center valign=top>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class="browseHdrDk">
						<tr>
							<td width=3% class="browseHdrDk" nowrap>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> width=13% class="browseHdrDk" nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> width=8% class="browseHdrDk" align=right nowrap align=center><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-quantity", "Quantity")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> width=8% class="browseHdrDk" align=center nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-uom", "UOM")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> width=13% class="browseHdrDk" align=right nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-unitCost", "Unit Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> width=13% class="browseHdrDk" align=right nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-hdg-extendedCost", "Ext Cost")%></td>
							<td width=3% class="browseHdrDk">&nbsp;</td>
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
		if (lineitems != null && lineitems.size() > 0)
		{
			for (int i = 0; i < lineitems.size(); i++)
			{
				InvoiceLine invoiceLine = (InvoiceLine) lineitems.get(i);
				BigDecimal bdQuantity = invoiceLine.getQuantity();
				BigDecimal bdUnitPrice = invoiceLine.getUnitPrice();
				BigDecimal bdUmFactor = invoiceLine.getUmFactor();
				if (bdUmFactor.compareTo(new BigDecimal(0)) == 0)
				{
					bdUmFactor = new BigDecimal(1);
				}
				BigDecimal bdExtCost = HiltonUtility.getFormattedDollar(bdQuantity.multiply(bdUnitPrice).multiply(bdUmFactor), oid);

				List accountList = (List) invoiceLine.getAccountList();

				if ( oid.equalsIgnoreCase("dtn07p") && accountList.size() == 0 )
				{
					accountList = (List) invoiceHeader.getAccountList();
				}

%>
						<tr id=itemRows>
							<td width=3% class=browseRow nowrap align=right><%=i+1%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-itemNumber")%> width=13% class=browseRow nowrap>
								<%=invoiceLine.getItemNumber()%>
							</td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-quantity")%> width=8% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(invoiceLine.getQuantity(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-uom")%> width=8% class=browseRow align=center nowrap><%=invoiceLine.getUmCode()%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-unitCost")%> width=13% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedDollar(invoiceLine.getUnitPrice(), oid)%></td>
							<td <%=HtmlWriter.isVisible(oid, "ivc-extendedCost")%> width=13% class=browseRow nowrap align=right><%=bdExtCost%></td>
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
<%
  if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) {
%>
							<td colspan=7 class=browseRow><b>Account:</b>&nbsp;
							<A HREF="javascript: showBudget('<%=s_account%>','<%=invoiceHeader.getFiscalYear()%>','<%=invoiceLine.getCommodity()%>'); void(0);"><%=s_account%></A>&nbsp;&nbsp;&nbsp;$<%=bd_alloc_amt.setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
<%} else  {%>
			<%	if (propertiesManager.getProperty("ACCOUNTS", "EDIT ACCOUNT ON APPROVALS", "N").equalsIgnoreCase("Y")) { %>
							<td colspan=7 class=browseRow><b><a href="javascript: retrieveAccount('<%=invoiceLine.getIcIvcLine()%>', <%=i + 1%>);" title="Account Allocations" style="text-decoration: underline;">Account</a>:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;$<%=bd_alloc_amt.setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
			<%	} else { %>
							<td colspan=7 class=browseRow><b>Account:</b>&nbsp;<%=s_account%>&nbsp;&nbsp;&nbsp;$<%=bd_alloc_amt.setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
			<%	} %>
<% } %>
						</tr>
<%				}
						}
						if (invoiceLine.getPoLine() != null)
						{
							PoLine poLine = invoiceLine.getPoLine();
							BigDecimal bdOrdered = poLine.getQuantity();
							BigDecimal bdReceived = poLine.getQtyReceived();
%>
						<tr>
							<td>&nbsp;</td>
							<td colspan=2>Ordered: <%=HiltonUtility.getFormattedQuantity(bdOrdered, oid)%></td>
							<td>&nbsp;</td>
							<td colspan=2>Previously Received: <%=HiltonUtility.getFormattedQuantity(bdReceived, oid)%></td>
						</tr>
<%					} %>
						<tr><td colspan=7><hr></td></tr>
<%		}
		} %>
						</table>
					</td>
				</tr>
				</table>

<%
	BigDecimal  bdSubtotal = invoiceHeader.getInvoiceSubtotal();
	BigDecimal  bdDiscount = invoiceHeader.getInvoiceDiscount();
	BigDecimal  bdTax = invoiceHeader.getInvoiceTax();
	BigDecimal  bdUseTax = invoiceHeader.getUseTax();
	BigDecimal  bdShipping = invoiceHeader.getInvoiceShipping();
	BigDecimal  bdOther = invoiceHeader.getInvoiceOther();
	BigDecimal  bdRejecting = invoiceHeader.getInvoiceRejecting();

	BigDecimal bdTotal = bdSubtotal.subtract(bdDiscount);
	bdTotal = bdTotal.add(bdTax).add(bdShipping).add(bdOther);

	BigDecimal bdTransaction = bdTotal.add(bdUseTax).subtract(bdRejecting);
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-subtotal")%>>
					<td colspan="3" width=82% class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-subtotal", "Subtotal")%>:</td>
					<td width=13% class=browseRow nowrap align=right><%=invoiceHeader.getInvoiceSubtotal().setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
					<td width=5% class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-discountAmount")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-discountAmount", "Discount")%>:</td>
					<td class=browseRow nowrap align=right><%=invoiceHeader.getInvoiceDiscount().setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<% if( bdTax.compareTo(new BigDecimal(0))!=0 ) {%>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-taxAmount")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-taxAmount", "Tax")%>:</td>
					<td class=browseRow nowrap align=right><%=invoiceHeader.getInvoiceTax().setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<% } %>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-shippingCharges")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-shippingCharges", "Shipping")%>:</td>
					<td class=browseRow nowrap align=right><%=invoiceHeader.getInvoiceShipping().setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-otherCharges")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-otherCharges", "Other")%>:</td>
					<td class=browseRow nowrap align=right><%=invoiceHeader.getInvoiceOther().setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3" class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=3 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-invoiceTotal")%>>
					<td colspan="3" class=processOn nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-total", "Total")%>:</td>
					<td class=browseRow nowrap align=right><b><%=bdTotal.setScale(2, BigDecimal.ROUND_HALF_UP)%></b></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<% if( bdUseTax.compareTo(new BigDecimal(0))!=0 ) {%>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-useTaxAmount")%>>
					<td colspan="3" class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-useTaxAmount", "Tax")%>:</td>
					<td class=browseRow nowrap align=right><%=invoiceHeader.getUseTax().setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<% } %>
				<% if( bdRejecting.compareTo(new BigDecimal(0))!=0 ) {%>
				<tr <%=HtmlWriter.isVisible(oid, "ivc-rejectedReason")%>>
					<td width="50%" class=browseRow nowrap align=right>
						<%	if (!HiltonUtility.isEmpty(invoiceHeader.getReasonCode())) {%>
						&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-rejectedReason", "Reason")%>:
						<%	} %>
					</td>
					<td class=browseRow nowrap align=left><%=invoiceHeader.getReasonCode()%></td>
					<td class=browseRow nowrap align=right>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "ivc-rejectedAmount", "Rejecting")%>:</td>
					<td class=browseRow nowrap align=right><%=invoiceHeader.getInvoiceRejecting().setScale(2, BigDecimal.ROUND_HALF_UP)%></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<% } %>
				<% if( bdUseTax.compareTo(new BigDecimal(0))!=0 || bdRejecting.compareTo(new BigDecimal(0))!=0 ) {%>
				<tr>
					<td colspan="3" class=browseRow>&nbsp;</td>
					<td class=browseRow colspan=3 valign=top><hr size=0 align=right colspan=2></td>
				</tr>
				<tr>
					<td colspan="3" class=processOn nowrap align=right>&nbsp;Transaction:</td>
					<td class=browseRow nowrap align=right><b><%=bdTransaction.setScale(2, BigDecimal.ROUND_HALF_UP)%></b></td>
					<td class=browseRow nowrap align=right>&nbsp;</td>
				</tr>
				<% } %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<%
			BigDecimal bd_total_perc = new BigDecimal(0.00);
			BigDecimal bd_total_amt = new BigDecimal(0.00);
			int	acci = 0;
%>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdrDk>
						<tr>
							<td width=75% height=18px class=browseHdrDk>&nbsp;<b>Account</b></td>
							<td width=25% height=18px class=browseHdrDk align=right>&nbsp;<b>Amount Alloc.</b></td>
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
					<td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedCurrency(bd_alloc_amt, s_curr_code, oid, false)%></td>
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
					<td width=25% class=browseRow align=right><%=HiltonUtility.getFormattedCurrency(bd_total_amt, s_curr_code, oid)%></td>
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


<%	List accountList = invoiceHeader.getAccountList();
		String accountTitle = "Account";
		String className = "browseHdrDk";
		if (accountList != null && accountList.size() > 0)
		{ %>
			<%//@ include file="/invoice/display_account.jsp"%>
<%	} %>

<%	accountList = (List) request.getAttribute("taxAccountList");
		accountTitle = "Tax Account";
		if (accountList != null && accountList.size() > 0)
		{ %>
			<%//@ include file="/invoice/display_account.jsp"%>
<%	} %>

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
<%	} %>


<%	accountList = (List) request.getAttribute("otherAccountList");
		accountTitle = "Other Account";
		if (accountList != null && accountList.size() > 0)
		{ %>
			<%//@ include file="/invoice/display_account.jsp"%>
<%	} %>

<br>

<div id="commentAfter" style="display:none;">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td width="680px" align="center">
		<table border="0" cellpadding="0" cellspacing="0" width="665px">
		<tr>
			<td align="center" valign="top">
				<table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
				<tr>
					<td>
						<table border="1" cellspacing="0" cellpadding="0" width="665px" class="browseHdrDk">
						<tr>
							<td class="browseHdrDk" height="18px" nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "comment", "Comment")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="browseRow">
						<table border="0" cellspacing="0" cellpadding="2" width="100%">
<%
			int iAfter = 0;
			if (cmtList != null)
			{
				for (int i = 0; i < cmtList.size(); i++)
				{
					DocComment docComment = (DocComment) cmtList.get(i);
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
					//if (s_cmt_print.equals("N"))
					//{
					//	continue;
					//}
					iAfter++;
%>
						<tr>
							<td width=75% class="browseRow"><% if (docComment.getCommentBold().equals("Y")) { %><b><% } %><%=docText.getStdText()%><% if (docComment.getCommentBold().equals("Y")) { %></b><% } %></td>
						</tr>
<%			}
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
</div>

<%   if (propertiesManager.isModuleActive("DOCUMENTS")) {	%>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class="browseHdrDk">
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class="browseHdrDk">
				<tr>
					<td>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class="browseHdrDk">
						<tr>
							<td width=80% height=18px class="browseHdrDk">&nbsp;<b>Attachment</b></td>
							<td width=10% height=18px class="browseHdrDk" align=center>&nbsp;<b>Print</b></td>
							<td width=10% height=18px class="browseHdrDk" align=center>&nbsp;</td>
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
		if (attachmentList != null)
		{
			for (int i = 0; i < attachmentList.size(); i++)
			{
				DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
				if (docAttachment == null)
				{
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
<%			if (ext.equalsIgnoreCase("DOC")) {	%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%			} else if (ext.equalsIgnoreCase("PDF")) {	%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%			} else if (ext.equalsIgnoreCase("XLS")) {	%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%			} else if (ext.equalsIgnoreCase("MPP")) {	%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%			} else if (ext.equalsIgnoreCase("PPT")) {	%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%			} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {	%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%			} else {	%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}	%>
							</td>
							<td>
								<a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
								<tsa:hidden name="docFilename" value="<%=filename%>"/>
							</td>
						</tr>
						</table>
					</td>
					<td width="10%" class="browseRow" align="center" valign="top"><%=docAttachment.getDocPrint()%></td>
					<td width="10%" class="browseRow" align="center" valign="top">&nbsp;</td>
				</tr>
				</table>
<% 			}
			}
			if (ai == 0) {	%>
				<table border="0" cellspacing="0" cellpadding="2" width="100%" class="browseRow">
				<tr><td class="browseRow">There are no attachments associated with this invoice.</td></tr>
				</table>
<%		}	%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}	%>

<div id="mybuttons">
<% if ((inspTotal.compareTo(invoiceTotal) >= 0) || ( ! oid.equalsIgnoreCase("ctb08p"))) { %>
<%@ include file="/invoice/iv_approval_buttons.jsp" %>
<% } %>
</div>

<br><br>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var invoiceTotal = parseFloat('<%=invoiceTotal%>');

<%	if (iBefore > 0) { %>
			displayArea('commentBefore');
<%	}
			if (iAfter > 0) { %>
			displayArea('commentAfter');
<%	}
		if (!bDisplayButtons) { %>
			var myButtons = document.all("myButtons");
			myButtons[0].style.visibility = "hidden";
			myButtons[0].style.display = "none";
			myButtons[1].style.visibility = "hidden";
			myButtons[1].style.display = "none";
<%	} %>

	function setFirstFocus()
	{
		// please do not take this out!  its here because otherwise when the page loads it will focus on the first text box which is at the BOTTOM of the page
		// we do NOT want to focus there!!!
	}

	function limitText(formField, limit) {
		if (formField.value.length > limit) {
			formField.value = formField.value.substring(0, limit);
		}
	}

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

	function setReturn()
	{
		var rtn = "N";

		if (frm.c_checkbox.checked)
		{
			rtn = "Y";
		}
		frm.returnTo.value = rtn;
	}

	function checkAction(s)
	{
		//frm.rq_action.value = s;

		if ((s == 'approve') || (s == 'reject'))
		{
			frm.routeTo.value = '';
			frm.c_checkbox.checked = false;
			frm.returnTo.value = "N";
		}
		else if (s == 'reroute')
		{
			//frm.rq_action[2].checked = true;
		}
	}

	function approveMe()
	{
		frm.routeTo.value="";
	  	frm.deferTo.value="";
		if (frm.InvoiceHeader_inspectionDate)
		{
			if (frm.InvoiceHeader_inspectionDate.value == null || frm.InvoiceHeader_inspectionDate.value == "")
			{
				alert("Please enter a valid inspection date!");
				return;
			}
		}
		if (frm.InvoiceHeader_processedDate)
		{
			if (frm.InvoiceHeader_processedDate.value == null || frm.InvoiceHeader_processedDate.value == "")
			{
				alert("Please enter a valid processed date!");
				return;
			}
		}
		if (frm.InvoiceHeader_imisApprovedDate)
		{
			if (frm.InvoiceHeader_imisApprovedDate.value == null || frm.InvoiceHeader_imisApprovedDate.value == "")
			{
				alert("Please enter a valid IMIS approved date!");
				return;
			}
		}
		if (frm.InvoiceHeader_imisPaymentDate)
		{
			if (frm.InvoiceHeader_imisPaymentDate.value == null || frm.InvoiceHeader_imisPaymentDate.value == "")
			{
				alert("Please enter a valid IMIS payment date!");
				return;
			}
		}
		hideAreaWithBlock("approve_link");
		doSubmit("/invoice/iv_forward.jsp", "InvoiceApprove;BudgetTranPreEmcumbered");
	}

	function rejectMe()
	{
		frm.routeTo.value="";
	  	frm.deferTo.value="";
		frm.invoiceaction.value = "REJECT";
		if (frm.ApprovalLog_approverNotes.value.length <= 0)
		{
			alert('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nobuyernotes", "Please first enter notes explaining why you are rejecting this Invoice")%>.');
		}
		else
		{
			//doSubmit("/invoice/iv_forward_options.jsp", "InvoiceReject");
			doSubmit("/invoice/iv_forward.jsp", "InvoiceReject");
		}
	}

	function deferMe()
	{
		frm.routeTo.value="";
	    if (frm.deferTo.value.length <= 0)
	    {
	      alert('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noivapproverdefer", "You must first select an approver to defer this Invoice")%>!');
	      browseLookup('deferTo', 'vchapprover');
	    }
	    else
	    {
			if (isEmpty(frm.ApprovalLog_approverNotes.value)) {
				alert('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nodeferivnotes", "Please first enter notes explaining why you are deferring this Invoice")%>.');
				return false;
			}
	      frm.invoiceaction.value = "DEFER";
	      doSubmit('/invoice/iv_forward.jsp', 'InvoiceDefer');
	    }
	}

	function rerouteMe()
	{
		frm.deferTo.value="";
		if (frm.routeTo.value.length <= 0)
		{
			alert('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noapproverreoute", "You must first select an approver to reroute this Invoice")%>!');
			browseLookup("routeTo", "vchapprover");
		}
		else
		{
			frm.invoiceaction.value = "REROUTE";
			doSubmit("/invoice/iv_forward.jsp", "InvoiceReroute");
		}
	}

	function browseLookup(formField, xml) {
	    	popupParameters =  popupParameters + "formField=" + formField +";browseName=" + xml;
	  	popupParameters = popupParameters + ";amtToApprove="+invoiceTotal;
	  	doSubmitToPopup('browse/browse_popup.jsp', 'BrowseRetrieve', 'WIDTH=700px', 'HEIGHT=500px');
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

	function showBudget(accs, fy, commodity) {
		var burl = "/budget/budget_view.jsp" ;
		popupParameters = "accountString=" + accs + ";fiscalYear=" + fy + ";currencyCode=<%=invoiceHeader.getCurrencyCode()%>;";
		popupParameters = popupParameters + "commodity=" + commodity + ";";
		doSubmitToPopup(burl, "BudgetCenterRetrieveView", "WIDTH=500", "HEIGHT=300") ;
	}

	function retrieveAccount(ic, row)
	{
		if (ic == "new")
		{
			alert("You must save before you can add Account Information to this item.");
		}
		else
		{
			frm.originalAccount_icLine.value = ic;
			frm.lineNumber.value = row;
			frm.formType.value = "IVL";

			doSubmit("/invoice/iv_accounts_ln_approvals.jsp", "InvoiceLineRetrieveAccountApprovals");
		}
	}

// End Hide script -->
</SCRIPT>

<%
	}
	else
	{
		//Already approved Invoice!
		alreadyApprovedMsg = (String) request.getAttribute("Message");
%>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="center" width="680px">
		<table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseRow">
		<tr>
			<td align="center">You have already processed <%=invoiceHeader.getInvoiceNumber()%>.</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	}	%>

<%@ include file="/system/footer.jsp" %>