<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.steporder.*" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.reviewfinalize.tasks.*" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/date_check.js"></SCRIPT>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	String	s_account_separator = propertiesManager.getProperty("MISC", "AccountSeparator", "-");
	String	s_po_admin = propertiesManager.getProperty("PO OPTIONS", "AdminChanges", "N");
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
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
	String	s_current_process = "REVIEW_STEPS";
	String	s_current_page = "/orders/ct_review_steps.jsp";
	String	s_current_method = "PoReviewFinalizeUpdate";
	String	s_current_process_method = "";
	int	i_line_count = 0;

	boolean bAdminChanges = false;
	if (s_po_admin.equalsIgnoreCase("Y") && role.getAccessRights("PO") == 99)
	{
		bAdminChanges = true;
	}
	String	s_buyer_code = poHeader.getBuyerCode();
	List reviewFinalizeList = (List)request.getAttribute("reviewFinalizeList");
%>
<%@ include file="/orders/po_hidden_fields.jsp" %>
<%@page import="com.tsa.puridiom.entity.ChecklistEntry"%>
<tsa:hidden name="ReviewFinalize_icHeader" value="<%=bd_ic_po_header%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reviewsteps", "Review Steps", false)%></div>
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
<%
String s_poType = (String)request.getAttribute("PoHeader_poType");
List rsteps = ProcessStepsFactory.getCheckListEntry(oid);
%>
<table border=0 cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="left" valign="top" width="520px">
		<%for (int ip = 0; ip < rsteps.size(); ip++)
		{
			ChecklistEntry checkListEntry = (ChecklistEntry)rsteps.get(ip);
			String checkListEntryIc = checkListEntry.getIcQuestion().toString(); %>
			<div style="clear: both; margin-top: 15px;">
				<div style="float: left; width: 50%;">
				<%boolean stepCompleted = ReviewFinalizeService.isStepChecked(reviewFinalizeList, checkListEntryIc); %>
					<input type="checkbox" name="c_chkbox" id="c_chkbox<%= ip%>"
					<%if(stepCompleted){ %> checked="checked" <%} %>
					 onclick="checkStep('<%=checkListEntryIc %>', this);">
					<label for="c_chkbox<%= ip%>"><%=checkListEntry.getQuestionText()%></label>
					<tsa:hidden name="ReviewFinalize_questionText" value="<%=checkListEntry.getQuestionText()%>"/>
					<tsa:hidden name="ReviewFinalize_completed" value="<%if(stepCompleted){%>Y<%}else{ %>N<%}%>" />
					<tsa:hidden name="ReviewFinalize_step" value="<%=checkListEntryIc %>"/>
					<tsa:hidden name="ReviewFinalize_owner" value=""/>
					<br>
					<table>
						<tr>
							<td>
								<label for="ReviewFinalize_revisedBy<%= ip%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reviewfinalize-revisedby", "Revised By")%></label>
							</td>
							<td>
								<input type="text" id="ReviewFinalize_revisedBy<%= ip%>"  name="ReviewFinalize_revisedBy" value="<%=ReviewFinalizeService.getReviewFinalize(reviewFinalizeList, checkListEntryIc).getRevisedBy() %>" disabled>
							</td>
						</tr>
						<tr>
							<td>
								<label for="ReviewFinalize_dateCompleted<%= ip%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reviewfinalize-datecompleted", "Date Completed")%></label>
							</td>
							<td>
								<input type="text" id="ReviewFinalize_dateCompleted<%= ip%>" name="ReviewFinalize_dateCompleted" value="<%=HiltonUtility.getFormattedDate(ReviewFinalizeService.getReviewFinalize(reviewFinalizeList, checkListEntryIc).getDateCompletedAsDate(), oid, userDateFormat) %>" disabled>
								<span id="dateCompleted<%= ip %>_section" style="display: none;">
									<a href="javascript: show_calendar('ReviewFinalize_dateCompleted<%= ip %>', '<%=userDateFormat%>');"><img src="<%=contextPath%>/images/calendar.gif" alt="Click here to choose a Date or Enter a Date in the box on the left." valign="bottom" border="0"></a>
								</span>
							</td>
						</tr>
					</table>
				</div>
				<%String display = "";
				if(ReviewFinalizeService.getReviewFinalize(reviewFinalizeList, checkListEntryIc).getNotes().length() > 0) { display = ""; }%>
				<div id="<%=checkListEntryIc %>" style="<%=display %> float: right; margin-right: 1.5em; ">
				<textarea wrap="virtual" name="ReviewFinalize_notes" rows="4" cols="40" onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000); "><%=HiltonUtility.encodeHtml(ReviewFinalizeService.getNotes(reviewFinalizeList, checkListEntryIc))%></textarea>
				</div>
			</div>
			<br><div style="position: relative; left: 0px; height: 5px; width: 300px"></div><br>
				<%}%>
	</td>
	<td  align="right" valign="top"><%@ include file="/orders/po_sidebar.jsp" %></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
  frm = document.purchaseform;

  var ponumber = "<%=s_po_number%>";
  var fiscalyear = "<%=poHeader.getFiscalYear()%>";
  var currentpage = "<%=s_current_page%>";
  var currentmethod = "<%=s_current_method%>";
  var currentprocessmethod = "<%=s_current_process_method%>";

  function checkStep(stepTextArea, ckbox)
  {
  		if (ckbox.checked == true)
		{
  			displayArea(stepTextArea);
  		}
		setCompleted(ckbox, stepTextArea);

  }

  function setCompleted(ckbox, stepChecked)
  {
		var count = document.all.ReviewFinalize_step.length;
//alert("Step to check:" + stepChecked);
		for (var i=0; i < count; i++)
		{
			step = frm.ReviewFinalize_step[i].value;

//alert("step: " + step);
			if (stepChecked == step)
			{
				if (ckbox.checked == true)
				{
					frm.ReviewFinalize_completed[i].value = "Y";
					frm.ReviewFinalize_revisedBy[i].value = "${esapi:encodeForJavaScript(userId)}";
					frm.ReviewFinalize_dateCompleted[i].value = "";
					frm.ReviewFinalize_dateCompleted[i].disabled = false;
					document.getElementById('dateCompleted' + i + '_section').style.display = 'inline';
				}
				else
				{
					frm.ReviewFinalize_completed[i].value = "N";
					frm.ReviewFinalize_revisedBy[i].value = "";
					frm.ReviewFinalize_dateCompleted[i].value = "";
					frm.ReviewFinalize_dateCompleted[i].disabled = true;
					document.getElementById('dateCompleted' + i + '_section').style.display = 'none';
				 }

			}
		}
	}


	function poSave()
	{
		if(!validateForm())
		{
			return;
		}
		if (isNA(ponumber))
		{
			popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}

	function validateForm() {
		var handlers = frm.handler.value;
		var dateCompleted = frm.ReviewFinalize_dateCompleted;
		var questionText = frm.ReviewFinalize_questionText;
		if (handlers.indexOf("PoReviewFinalizeUpdate") >= 0) {
			var alertMessage = "";
			for(i=0;i<dateCompleted.length;i++)	{
				if (dateCompleted[i] && !chkdate(dateCompleted[i])) {
					alertMessage += "\nInvalid Date Completed for "+ questionText[i].value;
				}
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
