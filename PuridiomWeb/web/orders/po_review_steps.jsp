<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.steporder.*" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.reviewfinalize.tasks.*" %>
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
	String	s_current_page = "/orders/po_review_steps.jsp";
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
ProcessSteps rsteps = ProcessStepsFactory.getProcessStep("PO", s_poType, oid);
%>
<table border=0 cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="left" valign="top" width="520px">
		<%for (int ip = 0; ip < rsteps.getSize(); ip++)
		{%>
			<div style="clear: both; margin-top: 15px;">
				<div style="float: left; width: 50%;">
				<%boolean stepCompleted = ReviewFinalizeService.isStepChecked(reviewFinalizeList, rsteps.getLabel(ip)); %>
					<input type="checkbox" name="c_chkbox" id="c_chkbox<%= ip%>"
					<%if(stepCompleted){ %> checked="checked" <%} %>
					 onclick="checkStep('<%=rsteps.getLabel(ip) %>', this);">
					<label for="c_chkbox<%= ip%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, rsteps.getLabel(ip), rsteps.getLabel(ip), false)%></label>
					<tsa:hidden name="ReviewFinalize_completed" value="<%if(stepCompleted){%>Y<%}else{ %>N<%}%>"/>
					<tsa:hidden name="ReviewFinalize_step" value="<%=rsteps.getLabel(ip) %>"/>
					<tsa:hidden name="ReviewFinalize_owner" value=""/>
					<br>
					<table>
						<tr>
							<td>
								<label for="ReviewFinalize_revisedBy<%= ip%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reviewfinalize-revisedby", "Revised By")%></label>
							</td>
							<td>
								<input type="text" id="ReviewFinalize_revisedBy<%= ip%>"  name="ReviewFinalize_revisedBy" value="<%=ReviewFinalizeService.getReviewFinalize(reviewFinalizeList, rsteps.getLabel(ip)).getRevisedBy() %>" disabled>
							</td>
						</tr>
						<tr>
							<td>
								<label for="ReviewFinalize_dateCompleted<%= ip%>"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "reviewfinalize-datecompleted", "Date Completed")%></label>
							</td>
							<td>
								<input type="text" id="ReviewFinalize_dateCompleted<%= ip%>" name="ReviewFinalize_dateCompleted" value="<%=HiltonUtility.getFormattedDate(ReviewFinalizeService.getReviewFinalize(reviewFinalizeList, rsteps.getLabel(ip)).getDateCompletedAsDate(), oid, userDateFormat) %>" disabled>
							</td>
						</tr>
					</table>
				</div>
				<%String display = "display:none;";
				if(ReviewFinalizeService.getReviewFinalize(reviewFinalizeList, rsteps.getLabel(ip)).getNotes().length() > 0) { display = ""; }%>
				<div id="<%=rsteps.getLabel(ip) %>" style="<%=display %> float: right; margin-right: 1.5em; ">
				<textarea wrap="virtual" name="ReviewFinalize_notes" rows="4" cols="40" onKeyDown="textCounter(this, 2000);" onKeyUp="textCounter(this,2000);" onchange="textCounter(this,2000); "><%=HiltonUtility.encodeHtml(ReviewFinalizeService.getNotes(reviewFinalizeList, rsteps.getLabel(ip)))%></textarea>
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

  var ponumber = "<%= headerEncoder.encodeForJavaScript(s_po_number) %>";
  var fiscalyear = "<%=poHeader.getFiscalYear()%>";
  var currentpage = "<%= headerEncoder.encodeForJavaScript(s_current_page) %>";
  var currentmethod = "<%= headerEncoder.encodeForJavaScript(s_current_method) %>";
  var currentprocessmethod = "<%= headerEncoder.encodeForJavaScript(s_current_process_method) %>";

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
					frm.ReviewFinalize_dateCompleted[i].value = "<%= HiltonUtility.getFormattedDate(d_today, oid, userDateFormat)%>";
				}
				else
				{
					frm.ReviewFinalize_completed[i].value = "N";
					frm.ReviewFinalize_revisedBy[i].value = "";
					frm.ReviewFinalize_dateCompleted[i].value = "";
				 }

			}
		}
	}


	function poSave()
	{
		if (isNA(ponumber == "N/A"))
		{
			popupParameters = "formtype=PO;formnumber=" + ponumber + ";fiscalyear=" + fiscalyear + ";action=save;currentpage=" + currentpage + ";currentprocessmethod=" + currentmethod + "/" + currentprocessmethod;
			doSubmitToPopup('/base/save_as.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		}
		else
		{
			doSubmit(currentpage, currentmethod + ";" + currentprocessmethod);
		}
	}


// End Hide script -->
</SCRIPT>
