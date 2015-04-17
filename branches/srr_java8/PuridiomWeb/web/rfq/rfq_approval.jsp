<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	//ApprovalLog approvalLog = (ApprovalLog) request.getAttribute("approvalLog");
	List lineList = (List) rfqHeader.getRfqLineList();

	BigDecimal bd_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_fiscalYear = rfqHeader.getFiscalYear();
	String	errorMsg = (String) request.getAttribute("errorMsg");

	int	lineCount = 0;
	if (lineList != null) {
		lineCount = lineList.size();
	}
	String currencyCode = "";
	Map approvalNotes = new HashMap();
	String backgroundClass = "summary";
	
	String alreadyApproved = (String)request.getAttribute("alreadyApproved");
	String invalidApprover = (String)request.getAttribute("invalidApprover");

	String alreadyApprovedMsg = "";
	if(alreadyApproved == null && invalidApprover == null)
	{
%>

<tsa:hidden name="emailAction" value="NONE"/>
<tsa:hidden name="ApprovalLog_icHeader" value="<%=rfqHeader.getIcRfqHeader()%>"/>
<tsa:hidden name="as_return" value="TRUE"/>
<tsa:hidden name="allowBrowse" value="TRUE"/>
<tsa:hidden name="reqaction" value="APPROVE"/>
<tsa:hidden name="status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="fromPage" value="rfq_approval.jsp"/>
<tsa:hidden name="formtype" value="RFQ"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
  <td valign=top width=135px height=30px>
    <table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
    <tr>
      <td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
    </tr>
    <tr>
      <td nowrap class=hdr12 valign=middle>
        <div style="margin-left:10px; margin-right:10px" class=hdr12>Solicitation Information</div>
      </td>
    </tr>
    </table>
  </td>
  <td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
  <td valign=bottom align=right height=30px>
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

<div name="approve_link" style="visibility: visible;">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
  <td id="approve_link" align="center" width="680px">
    <table border="0" cellspacing="0" cellpadding="0"  width="665px" >
    <tr>
		<td align="center"><a href="javascript: approveMe(); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></td>
		<!--<td align="center"><a href="javascript: doSubmit('/rfq/rfq_forward.jsp', 'RfqApprove'); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></td>-->
		<td align="center"><a href="javascript: rejectMe(); void(0);"><img src="<%=contextPath%>/images/reject_black.gif" border="0" alt="Reject"></a></td>
		<td align="center"><a href="javascript: rerouteMe(); void(0);"><img src="<%=contextPath%>/images/reroute_black.gif" border="0" alt="Reroute"></a></td>
		<td align="center"><a href="javascript: deferMe(); void(0);"><img src="<%=contextPath%>/images/defer_black.gif" border="0" alt="Defer"></a></td>
	</tr>
	<tr>
		<td align="center"><a href="javascript: approveMe(); void(0);"><tsa:label labelName="clickhere-approve" defaultString="Click here to approve" /></a></td>
		<!--<td align="center"><a href="javascript: doSubmit('/rfq/rfq_forward.jsp', 'RfqApprove'); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></td>-->
		<td align="center"><a href="javascript: rejectMe(); void(0);"><tsa:label labelName="clickhere-reject" defaultString="Click here to reject" /></a></td>
		<td align="center"><a href="javascript: rerouteMe(); void(0);"><tsa:label labelName="clickhere-reroute" defaultString="Click here to reroute" /></a></td>
		<td align="center"><a href="javascript: deferMe(); void(0);"><tsa:label labelName="clickhere-defer" defaultString="Click here to defer" /></a></td>
	</tr>
    </table>
  </td>
</tr>
</table>
</div>

<br>
<br>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<%@ include file="/rfq/rfq_approval_routing_list.jsp" %>
		<%@ include file="/rfq/rfq_approval_notes_display.jsp" %>
  </td>
</tr>
<%--tr>
	<td align=center valign=top>
		<table id=routingTable border=0 cellspacing=2 cellpadding=2 width=665px class=summary>
<tr>
	<td>
		<br>
		<table border="0" cellspacing="0" cellpadding="0" width=655px class=summary>
		<tr height="20px">
			<td width=100% align=center valign=top>
				<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 0px; width:655px; align:center; overflow-y:auto; overflow-x:auto;">
				<table border="0" cellspacing="0" cellpadding="1" width="651px" height=18px>
				<tr>
					<td class="browseHdrDk" nowrap width="5%"><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=0></td>
					<td class="browseHdrDk" nowrap width="26%" >Approver&nbsp;</td>
					<td class="browseHdrDk" nowrap width="25%" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routing-callForward", "Call Forward")%>&nbsp;</td>
					<td class="browseHdrDk" nowrap width="20%"  align=right>Assigned&nbsp;</td>
					<td class="browseHdrDk" nowrap width="20%"  align=right>Approved&nbsp;</td>
					<td class="browseHdrDk" nowrap width="4%" align=right><img src="<%=contextPath%>/images/none.gif" border=0 width=10px height=1px></td>
				</tr>
				</table>
				<table id="approvers0" border="0" cellpadding="1" cellspacing="0" width="651px" class="browseRow">

				<tr height="15px">
					<td width="5%" class="selectedRow" NOWRAP align="right">

						<span id="sequence">1</span>
						&nbsp;
					</td>
					<td width="26%"  class="selectedRow" NOWRAP>
						<%=UserManager.getInstance().getUser(oid, approvalLog.getUserId()).getDisplayName()%>
					</td>
					<td width="25%"  class="selectedRow" NOWRAP><span id="callforwardText"><%=approvalLog.getCallForward()%></span></td>
					<td width="20%"  class="selectedRow" align="right" NOWRAP><%=approvalLog.getDateAssigned()%></td>
					<td width="20%"  class="selectedRow" align="right" NOWRAP><%=approvalLog.getApproved()%></td>
					<td width="2%" NOWRAP class="selectedRow" align=center>
						<tsa:hidden name="originalApprovalAmount" value="0"/>
						<tsa:hidden name="ApprovalLog_approvalAmount" value="0"/>
					</td>
					<td width="2%" NOWRAP class="selectedRow" align=center>

					</td>
						<tsa:hidden name="approverName" value="<%=approvalLog.getApproverName()%>"/>
						<tsa:hidden name="approverUserType" value="<%=approvalLog.getApproverType()%>"/>
						<tsa:hidden name="ruleNotes" value=""/>
						<tsa:hidden name="tableIndex" value="0"/>
						<tsa:hidden name="ApprovalLog_userId" value="<%=approvalLog.getUserId()%>"/>
						<tsa:hidden name="ApprovalLog_amount" value="<%=approvalLog.getAmount()%>"/>
						<tsa:hidden name="ApprovalLog_approverAmount" value="<%=approvalLog.getApproverAmount()%>"/>
						<tsa:hidden name="ApprovalLog_approved" value="<%=approvalLog.getApproved()%>"/>
						<tsa:hidden name="ApprovalLog_udfValues" value="<%=approvalLog.getUdfValues()%>"/>
						<tsa:hidden name="ApprovalLog_authorized" value="<%=approvalLog.getAuthorized()%>"/>
						<tsa:hidden name="ApprovalLog_alternateUserid" value="<%=approvalLog.getAlternateUserid()%>"/>
						<tsa:hidden name="ApprovalLog_approverType" value="<%=approvalLog.getApproverType()%>"/>
						<tsa:hidden name="ApprovalLog_ruleType" value="<%=approvalLog.getRuleType()%>"/>
						<tsa:hidden name="ApprovalLog_poolid" value="<%=approvalLog.getPoolid()%>"/>
						<tsa:hidden name="ApprovalLog_ruleAction" value="<%=approvalLog.getRuleAction()%>"/>
						<tsa:hidden name="ApprovalLog_approverSig" value="<%=approvalLog.getApproverSig()%>"/>
						<tsa:hidden name="ApprovalLog_callForward" value="<%=approvalLog.getCallForward()%>"/>
				</tr>
				</table>
		        </div>

		        <tsa:hidden name="approvalRule" value="[Cost Center=*]"/>
		        <tsa:hidden name="amountToApprove" value="420.13"/>
		      </td>
		  </tr>
		</table>

		<br><br>
	</td>
</tr>
</table>

  </td>
</tr--%>
<tr>
  <td align=center width=680px>
    <table border=0 cellpadding=0 cellspacing=2 align=center width=665px  class=summary>
    <tr >
      <td colspan=2><b>Please enter your notes here:</b></td>
    </tr>
    <tr>
      <td ><TEXTAREA NAME="ApprovalLog_approverNotes" WRAP="VIRTUAL" ROWS="8" COLS="67"></TEXTAREA></td>
      <td valign="top">
        <table border=0 cellpadding=0 cellspacing=2 align=center class=summary valign="top">

        <tr >
          <td valign=middle nowrap><b>Signature:&nbsp;</b>
          <input type=checkbox name="c_app_signed" value="N"></td>
        </tr>
        <tr >
          <td valign=middle nowrap><B>Signature Password:&nbsp;</b>
            &nbsp;<input type=password name="signature_password" autocomplete="off" value="" onchange="upperCase(this);"></td>
        </tr>
        <tr>
          <td width=100%><hr color="#9999CC"></td>
        </tr>

          <tr >
            <td valign=middle nowrap><b><a HREF="javascript: browseLookup('routTo', 'rfq_approver'); void(0);">Reroute To:</a></b>&nbsp;
            <input type=text name="routTo" onfocus="this.blur();" onchange="upperCase(this);"></td>
          </tr>
          <tr >
            <td valign=bottom nowrap>
              <input type=checkbox name="c_checkbox" value="Y" onclick="setReturn();" onfocus="checkAction('reroute');"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "returnmereroute", "Return to me after reroute")%>.
              <tsa:hidden name="returnTo" value=""/>
            </td>
          </tr>
		  <tr >
            <td valign=middle nowrap><b><a HREF="javascript: browseLookup('deferTo', 'rfq_approver'); void(0);">Defer To:</a></b>&nbsp;
            <input type=text name="deferTo" onfocus="this.blur();" onchange="upperCase(this);"></td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td colspan=2><br><br></td>
    </tr>
    </table>
  </td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center>
		<table border=0 cellpadding=0 cellspacing=0 width=665px>
		<tr>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
						<tr>
							<td class=browseHdrDk height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "general_information", "General Information")%></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100%>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-buyerName")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-buyerName", "Buyer")%>:</b>&nbsp;</td>
							<td nowrap><%=UserManager.getInstance().getUser(oid, rfqHeader.getBuyer()).getDisplayName()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-solicitationDate")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-solicitationDate", "Solicitation Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(rfqHeader.getRfqDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-fiscalYear")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-fiscalYear", "Fiscal Year")%>:&nbsp;</b></td>
							<td nowrap><%=rfqHeader.getFiscalYear()%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-dueDate")%>>
							<td nowrap align=right><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-dueDate", "Due Date")%>:</b>&nbsp;</td>
							<td nowrap><%=HiltonUtility.getFormattedDate(rfqHeader.getDueDate(), oid, userDateFormat)%></td>
						</tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-currency")%>>
							<td nowrap align=right width=40%><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-currency", "Currency")%>:&nbsp;</b></td>
							<td nowrap><%=rfqHeader.getCurrencyCode()%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
			<td width=50% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
				<tr>
					<td>
						<table border=1 cellspacing=0 cellpadding=0 width=320px class=browseHdrDk>
						<tr>
						  <td class=browseHdrDk height=18px nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "shipping_information", "Shipping Information")%></td>
						</tr>
						</table>
				  	</td>
				</tr>
				<tr>
					<td class=browseRow>
<%
	//Shipping Information
	String s_shipToCode = rfqHeader.getShipToCode();
	if (s_shipToCode==null){s_shipToCode = "";}
	Address address = rfqHeader.getShipToAddress();
	if(address==null){
		address = new Address();
	}

	String s_addressLine1 = address.getAddressLine1();
	String s_addressLine2 = address.getAddressLine2();
	String s_addressLine3 = address.getAddressLine3();
	String s_addressLine4 = address.getAddressLine4();
	String s_city = address.getCity();
	String s_state = address.getState();
	String s_postalCode = address.getPostalCode();
	String s_country = address.getCountry();
	String s_attention = rfqHeader.getShipToContact();
	String s_priority = rfqHeader.getPriorityCode();
	String s_shipVia = rfqHeader.getShippingCode();
	String s_routing = rfqHeader.getRouting();
%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shipToCode")%>><td class=browseRow height=12px nowrap><b><%=s_shipToCode%></b></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine1")%>><td class=browseRow height=12px nowrap><%=s_addressLine1%></td></tr>
						</table>

						<table id=shippingRows border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%	if (!HiltonUtility.isEmpty(s_addressLine2)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine2")%>><td class=browseRow height=12px nowrap><%=s_addressLine2%></td></tr>
<%	}
		if(!HiltonUtility.isEmpty(s_addressLine3)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine3")%>><td class=browseRow height=12px nowrap><%=s_addressLine3%></td></tr>
<%	}
		if(!HiltonUtility.isEmpty(s_addressLine4)) { %>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-addressLine4")%>><td class=browseRow height=12px nowrap><%=s_addressLine4%></td></tr>
<%	}
		if(!HiltonUtility.isEmpty(s_city + s_state + s_postalCode + s_country)) {
			if(!HiltonUtility.isEmpty(s_city) && !HiltonUtility.isEmpty(s_state)) {%>
						<tr><td class=browseRow height=12px nowrap><%=s_city%>, <%=s_state%>   <%=s_postalCode%>  <%=s_country%></td></tr>
<%		} else {%>
						<tr><td class=browseRow height=12px nowrap><%=s_city%> <%=s_state%>   <%=s_postalCode%>  <%=s_country%></td></tr>
<%		}
		}%>
						</table>
<%	if (!HiltonUtility.isEmpty(rfqHeader.getShipToContact())) { %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shp-attention")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shp-attention", "Attention")%>: <%=s_attention%></td></tr>
						</table>
<%	} %>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-requiredDate")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-requiredDate", "Required By")%>: <%=HiltonUtility.getFormattedDate(rfqHeader.getRequiredDate(), oid, userDateFormat)%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-priority")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-priority", "Priority")%>: <%=s_priority%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-shipVia")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-shipVia", "Ship Via")%>: <%=s_shipVia%></td></tr>
						<tr <%=HtmlWriter.isVisible(oid, "rfq-routing")%>><td class=browseRow height=12px nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-routing", "Routing")%>: <%=rfqHeader.getRouting()%></td></tr>
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
	<td align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
				<tr><td class=browseHdrDk height=18px nowrap>&nbsp;Comment</td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=browseRow>
				
<%
      int iBefore = 0;
      List cmtList = (List) rfqHeader.getDocCommentList();
      if (cmtList != null && cmtList.size() > 0) {
        for(int i = 0; i < cmtList.size(); i++) {
          DocComment docComment = (DocComment) cmtList.get(i);
          DocText docText = docComment.getDocText();

          String s_cmt_title = docComment.getCommentTitle();
          String s_cmt_print = docComment.getCommentPrint();
          String s_cmt_bold = docComment.getCommentBold();
          String s_cmt_place = docComment.getCommentPlace();
          String s_cmt_text = docText.getStdText();

          if (s_cmt_place.equals("A")) {
            continue;
          }
          /*if (s_cmt_print.equals("N")) {
            continue;
          }*/
          iBefore++;
%>				
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=100% class=browseRow>
						<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
				<table class=browseRow>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
						<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
<%			}
    } %>
				
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>

<br>

<%
	String s_response = "";
	icolspan = 1;
	List questionList = (List) rfqHeader.getRfqQuestionList();
	if (questionList != null && questionList.size() > 0) {
%>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=questions border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdrDk>
				<tr>
					<td width=5% height=18px class=browseHdrDk nowrap>&nbsp;Supplier Questions</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%		for (int i = 0; i < questionList.size(); i++) {
				RfqQuestion rfqQuestion = (RfqQuestion) questionList.get(i);
				RfqQuestionPK rfqQuestionPK = rfqQuestion.getComp_id();
				BigDecimal bd_icQuestion = rfqQuestionPK.getIcQuestion();
				Map responseValueMap = rfqQuestion.getResponseValueMap();
%>
				<tr height=18px>
					<td width="5px">&nbsp;</td>
					<td colspan=3><b><%=rfqQuestion.getQuestionText()%></b></td>
				</tr>
<%		List responseList = (List) request.getAttribute("vendorResponseList");
			if (responseList != null && responseList.size() > 0) {
				for (int j = 0; j < responseList.size(); j++) {
					VendorResponse vendorResponse = (VendorResponse) responseList.get(j);
					VendorResponsePK vendorResponsePK = vendorResponse.getComp_id();
					if (bd_icQuestion.compareTo(vendorResponsePK.getIcQuestion()) == 0) {
						s_response = "";
						if (responseValueMap != null) {
							ResponseValue responseValue = (ResponseValue) responseValueMap.get(vendorResponse.getResponse());
							if (responseValue != null) {
								s_response = responseValue.getResponseText();
							}
						}
						icolspan = 1;
						if (HiltonUtility.isEmpty(s_response)) {
							if (vendorResponse.getResponse().equals("Y")) {
								s_response = "Yes";
							}
							else if (vendorResponse.getResponse().equals("N")) {
								s_response = "No";
							}
						}
%>
				<tr height=18px>
					<td width="5px">&nbsp;</td>
					<td width="20%" nowrap><%=vendorResponsePK.getVendorId()%></td>
					<%	if (s_response.length() > 0) {%>
					<td align="left" nowrap><%=s_response%></td>
					<%	} else {
								icolspan = 2;
							}  %>
					<td colspan="<%=icolspan%>"><%=vendorResponse.getTextResponse()%></td>
				</tr>
<%				}
				}
			}
%>
				<tr><td colspan=3><br></td></tr>
<%		} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br/>
<%	} %>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
  <td align=center valign=top>
        <table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
		<tr>
			<td width=100% align=center valign=top class=browseHdrDk height=18px>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseHdrDk>
				<tr>
					<td width=50% align=center valign=top class=browseHdrDk>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseHdrDk>
						<tr>
							<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=5% class=browseHdrDk nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lineNumber", "Line #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=15% class=browseHdrDk nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=11% class=browseHdrDk nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantity", "Quantity")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=8% class=browseHdrDk nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM")%></td>
						</table>
					</td>
					<td width=50% align=center valign=top class=browseHdrDk>
						<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseHdrDk>
						<tr>
							<td width=6% class=browseHdrDk nowrap>&nbsp;</td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=36% class=browseHdrDk nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%> width=29% class=browseHdrDk nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-unitCost", "Unit Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%> width=29% class=browseHdrDk nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-extendedCost", "Extended Cost")%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align=center valign=top>
				<tsa:hidden name="RfqBid_icRfqLine" value=""/>
				<tsa:hidden name="RfqLine_icRfqLine" value=""/>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>
<% 	//Rfq Lines
		for (int i = 0; i < lineCount; i++)
		{
			RfqLine rfqLine = (RfqLine)lineList.get(i);
			BigDecimal b_icRfqLine = rfqLine.getIcRfqLine();
			BigDecimal b_umFactor = rfqLine.getUmFactor();
			if (b_umFactor==null  || b_umFactor.compareTo(new BigDecimal(0)) <= 0) {b_umFactor = new BigDecimal(1);}
			List bidList = (List) rfqLine.getRfqBidList();
			String lowVendor = rfqLine.getLowestVendorId();
%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<% 			List commentList = (List) rfqLine.getDocCommentList();
			if (commentList != null && commentList.size() > 0)
			{ %>
						<tr>
							<td valign=top width=55%>
							<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
			
<%				for (int ix = 0; ix < commentList.size(); ix++)
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
					}	%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow>
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
						</tr>
<%				}	%>
							</table>
							</td>
						</tr>
<%			}	%>
						<tr>
							<td valign=top width=50%>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow valign=top>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=5% class=browseRow nowrap align=right><%=i+1%>&nbsp;</td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=15% class=browseRow nowrap><%=rfqLine.getItemNumber()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=11% class=browseRow nowrap align=right><%=HiltonUtility.getFormattedQuantity(rfqLine.getQuantity(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=8% class=browseRow nowrap><%=rfqLine.getUmCode()%></td>
								</tr>
								<tr>
									<td class=browseRow nowrap>&nbsp;</td>
									<td colspan=4 class=browseRow><%=rfqLine.getDescription()%></td>
								</tr>
								</table>
							</td>
							<td width=50% valign=top>
								<table border=0 cellspacing=0 cellpadding=1 width=100% class=browseRow valign=top>
<%		if (bidList != null)
			{
				for (int b = 0; b < bidList.size(); b++)
				{
					RfqBid rfqBid = (RfqBid)bidList.get(b);
					RfqBidPK rfqBidPK = (RfqBidPK) rfqBid.getComp_id();
					String vendorId = rfqBidPK.getVendorId();
					BigDecimal bd_bidQuantity = HiltonUtility.getFormattedQuantity(rfqBid.getQuantity(), oid);
					BigDecimal bd_bidUnitPrice = HiltonUtility.getFormattedDollar(rfqBid.getUnitPrice(), oid);
					BigDecimal bd_bidExtendedPrice = HiltonUtility.getFormattedDollar(bd_bidQuantity.multiply(bd_bidUnitPrice).multiply(b_umFactor), oid);
					String	rowClass = "browseRow";

					if (lowVendor.equals(vendorId)) {
						rowClass =  "selectedRow";
					}
%>
								<tr class=<%=rowClass%>>
									<td width=6% class=<%=rowClass%> valign=middle>&nbsp;<%if (lowVendor.equals(vendorId)) {%><img src="<%=contextPath%>/images/check.gif" border=0><%}%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%>  width=36% class=<%=rowClass%>><%=vendorId%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%>  width=29% class=<%=rowClass%> align=right><%=bd_bidUnitPrice%></td>
									<td <%=HtmlWriter.isVisible(oid, "rfq-extendedCost")%>  width=29% class=<%=rowClass%> align=right><%=bd_bidExtendedPrice%></td>
								</tr>
<%			}
			}
			else
			{%>
								<tr>
									<td  width=14% class=browseRow></td>
									<td  width=13% class=browseRow align=right></td>
									<td  width=13% class=browseRow align=right></td>
								</tr>
<% 		} %>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<table border=0 cellspacing=0 cellpadding=0  class=browseRow>
<%
            List attachmentList = (List) rfqLine.getDocAttachmentList();
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
							<td width=3% class=browseRow nowrap>&nbsp;</td>
							<td width=13% class=browseRow nowrap align="right">
							<%if(!flagAttachment){%>
								<b><tsa:label labelName="po-catalogItemAttachment" defaultString="Attachment" />:&nbsp;</b>
								<%flagAttachment=true;
							}%>

<%		if (ext.equalsIgnoreCase("DOC")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>');void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%		} else if (ext.equalsIgnoreCase("PDF")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%		} else if (ext.equalsIgnoreCase("XLS")) {%>
							<a href="javascript: oopenAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%		} else if (ext.equalsIgnoreCase("EML")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border=0 alt="Open Attached Email"></a>
<%		} else if (ext.equalsIgnoreCase("MPP")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%		} else if (ext.equalsIgnoreCase("PPT")) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%		} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%		} else {%>
							<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%		}%>
						</td>
							<td width=50% class=browseRow nowrap>
								<a href="javascript: openAttachment('<%=docAttachment.getDocFilename()%>'); void(0);" title="Click here to Open the file"><%=docAttachment.getDocTitle()%></a>
							</td>
							<td width="34">&nbsp;</td>

						</tr><%
					}
			}
            %>
								</table>
							</td>
						</tr>
<% 			if (commentList != null && commentList.size() > 0)
			{ %>
						<tr>
							<td valign=top width=55%>
							<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow valign=top>
			
<%				for (int ix = 0; ix < commentList.size(); ix++)
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
					}	%>
						<tr>
							<td class=browseRow nowrap>&nbsp;</td>
							<td class=browseRow>
								<b><tsa:label labelName="req-catalogItemComment" defaultString="Comment" />:&nbsp;</b>
								<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
							</td>
						</tr>
<%				}	%>
							</table>
							</td>
						</tr>
<%			}	%>
						</table>
<%		if (i != lineList.size() - 1) { %>
						<hr size=0 width=98%>
<%		}
		}%>
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

<div id="commentAfter" style="display:none;">
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=680px align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
				<tr><td class=browseHdrDk height=18px nowrap>&nbsp;Comment</td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=browseRow>
<%
      int iAfter = 0;
      if (cmtList != null) {
        for (int i = 0; i < cmtList.size(); i++) {
          DocComment docComment = (DocComment) cmtList.get(i);
          DocText docText = docComment.getDocText();

          String s_cmt_title = docComment.getCommentTitle();
          String s_cmt_print = docComment.getCommentPrint();
          String s_cmt_bold = docComment.getCommentBold();
          String s_cmt_place = docComment.getCommentPlace();
          String s_cmt_text = docText.getStdText();

          if (s_cmt_place.equals("B")) {
            continue;
          }
          /*if (s_cmt_print.equals("N")) {
            continue;
          }*/
          iAfter++;
%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr>
					<td width=100% class=browseRow>
						<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_title%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
				<table class=browseRow>
				<tr>
					<td width=50px>&nbsp;</td>
					<td width=100%>
						<% if (s_cmt_bold.equals("Y")) { %><b><% } %><%=s_cmt_text%><% if (s_cmt_bold.equals("Y")) { %></b><% } %>
					</td>
				</tr>
				</table>
<%			}
      }%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>

<div id="attachments" style="display:none;">
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
		<tr>
			<td>
				<table border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdrDk>
				<tr>
					<td class=browseHdrDk height=18px nowrap>&nbsp;Attachment</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=browseRow>
<%
		List attachmentList = (List) rfqHeader.getDocAttachmentList();
		int ai = 0;
		if (attachmentList != null) {
			for (int i = 0; i < attachmentList.size(); i++) {
				DocAttachment docAttachment = (DocAttachment) attachmentList.get(i);
				if (docAttachment == null) {
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
<%			if (ext.equalsIgnoreCase("DOC")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_word.gif" border=0 alt="Open Attached MS Word Document"></a>
<%			} else if (ext.equalsIgnoreCase("PDF")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/adobe_pdf.gif" border=0 alt="Open Attached Adobe PDF Document"></a>
<%			} else if (ext.equalsIgnoreCase("XLS")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_excel.gif" border=0 alt="Open Attached MS Excel Document"></a>
<%			} else if (ext.equalsIgnoreCase("MPP")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_project.gif" border=0 alt="Open Attached MS Project Document"></a>
<%			} else if (ext.equalsIgnoreCase("PPT")) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/ms_powerpoint.gif" border=0 alt="Open Attached MS Powerpoint Document"></a>
<%			} else if ( ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("GIF") || ext.equalsIgnoreCase("BMP") ) {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/picture_sm.gif" border=0 alt="Open Attached Image"></a>
<%			} else {%>
								<a href="javascript: openDocument(<%=i%>); void(0);"><img src="<%=contextPath%>/images/doc_attached.gif" border=0 alt="Open Attached Document"></a>
<%			}%>
							</td>
							<td>
								<a href="javascript: openDocument(<%=i%>); void(0);" title="Click here to open the <%=docAttachment.getDocTitle()%> attachment."><%=docAttachment.getDocTitle()%></a>
								<tsa:hidden name="docFilename" value="<%=filename%>"/>
							</td>
						</tr>
						</table>
					</td>
					<td width=10% class=browseRow align=center valign=top><%=docAttachment.getDocPrint()%></td>
					<td width=10% class=browseRow align=center valign=top></td>
				</tr>
				</table>
  <% 		}
      }
      if (ai == 0) {%>
				<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
				<tr><td class=browseRow>There are no attachments associated with this solicitation.</td></tr>
				</table>
<%		}%>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</div>

<br>
<br>

<div name="approve_link" style="visibility: visible;">
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td id="approve_link" align="center" width="680px">
		<table border="0" cellspacing="0" cellpadding="0"  width="665px" >
		<tr>
			<td align="center"><a href="javascript: approveMe(); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></td>
			<!--<td align="center"><a href="javascript: doSubmit('/rfq/rfq_forward.jsp', 'RfqApprove'); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></td>-->
			<td align="center"><a href="javascript: rejectMe(); void(0);"><img src="<%=contextPath%>/images/reject_black.gif" border="0" alt="Reject"></a></td>
			<td align="center"><a href="javascript: rerouteMe(); void(0);"><img src="<%=contextPath%>/images/reroute_black.gif" border="0" alt="Reroute"></a></td>
			<td align="center"><a href="javascript: deferMe(); void(0);"><img src="<%=contextPath%>/images/defer_black.gif" border="0" alt="Defer"></a></td>
		</tr>
		<tr>
			<td align="center"><a href="javascript: approveMe(); void(0);"><tsa:label labelName="clickhere-approve" defaultString="Click here to approve" /></a></td>
			<!--<td align="center"><a href="javascript: doSubmit('/rfq/rfq_forward.jsp', 'RfqApprove'); void(0);"><img src="<%=contextPath%>/images/approve_black.gif" border="0" alt="Approve"></a></td>-->
			<td align="center"><a href="javascript: rejectMe(); void(0);"><tsa:label labelName="clickhere-reject" defaultString="Click here to reject" /></a></td>
			<td align="center"><a href="javascript: rerouteMe(); void(0);"><tsa:label labelName="clickhere-reroute" defaultString="Click here to reroute" /></a></td>
			<td align="center"><a href="javascript: deferMe(); void(0);"><tsa:label labelName="clickhere-defer" defaultString="Click here to defer" /></a></td>
		</tr>
	    </table>
	</td>
</tr>
</table>
</div>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

<%	if (iBefore > 0) { %>
    	  displayArea('commentBefore');
<%	}
	    if (iAfter > 0) { %>
      	displayArea('commentAfter');
<%	}
		if (ai > 0) { %>
			displayArea('attachments');
<%	}%>

	function setFirstFocus() {
		// please do not take this out!  its here because otherwise when the page loads it will focus on the first text box which is at the BOTTOM of the page
		// we do NOT want to focus there!!!
	}

	function checkHiddenMenu() {
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

	function openDocument(row) {
		var filename = "";
		if (document.all("docFilename").length > 1) {
			filename = frm.docFilename[row].value;
		}
		else {
			filename = frm.docFilename.value;
		}
		openAttachment(filename);
	}

	function approveMe() {
		frm.routTo.value="";
	  	frm.deferTo.value="";
    	doSubmit('/rfq/rfq_forward.jsp', 'RfqApprove;ApprovalLogRetrieveByHeader;RfqRetrieveByStatusApprovingForApprover');
    	//doSubmit('/rfq/rfq_forward.jsp', 'RfqApprove;PoMultipleCreateFromRfq;RfqRetrieveByStatusApprovingForApprover');
	}

	function rejectMe() {
		frm.routTo.value="";
	  	frm.deferTo.value="";
    	frm.reqaction.value = "REJECT";
    	if (frm.ApprovalLog_approverNotes.value.length <= 0)
    	{
	      alert("Please first enter notes to the requisitioner explaining why you are rejecting this requisition.");
	    }
	    else
	    {
	      doSubmit('/rfq/rfq_forward.jsp', 'RfqReject;RfqRetrieveByStatusApprovingForApprover');
	    }
	}

	function rerouteMe()
	{
		frm.deferTo.value="";
	    if (frm.routTo.value.length <= 0)
	    {
	      alert("You must first select an approver to reroute this Solicitation!");
	      browseLookup('routTo', 'rfq_approver');
	    }
	    else
	    {
	      frm.reqaction.value = "REROUTE";
	      doSubmit('/rfq/rfq_forward.jsp', 'RfqReroute;RfqRetrieveByStatusApprovingForApprover');
	    }
	}

	function deferMe()
	{
		frm.routTo.value="";
	    if (frm.deferTo.value.length <= 0)
	    {
	      alert('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "norfqapproverdefer", "You must first select an approver to defer this Solicitation")%>!');
	      browseLookup('deferTo', 'rfq_approver');
	    }
	    else
	    {
			if (isEmpty(frm.ApprovalLog_approverNotes.value)) {
				alert('<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "nodeferrfqnotes", "Please first enter notes explaining why you are deferring this Solicitation")%>.');
				return false;
			}
	      frm.reqaction.value = "DEFER";
	      doSubmit('/rfq/rfq_forward.jsp', 'RfqDefer;RfqRetrieveByStatusApprovingForApprover');
	    }
	}  

// End Hide script -->
</SCRIPT>

<%	} else {/**Already approved Order!*/
		alreadyApprovedMsg = (String)request.getAttribute("Message");%>
	<table border="0" cellpadding="0" cellspacing="0" width="680px">
      <tr>
        <td align="center" width="680px">
          <table border="0" cellspacing="0" cellpadding="0" width="665px" class="browseRow">
            <tr>
              <td colspan=3><br><br></td>
            </tr>
            <tr>
              <td align="center">You have already processed <%=rfqHeader.getDisplayRfqNumber()%>.</td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
<%	} %>