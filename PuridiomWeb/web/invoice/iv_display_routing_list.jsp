<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.ApprovalLog" %>

<%
	List routingList = (List) request.getAttribute("routingList");
	InvoiceHeader invoiceHeader = (InvoiceHeader) request.getAttribute("invoiceHeader");
	if (routingList == null)
	{
		routingList = new ArrayList();
	}
%>

<script language='Javascript1.2' src='<%=contextPath%>/scripts/browse.js' type='text/javascript'></script>
<script language='Javascript1.2' src='<%=contextPath%>/scripts/dynamicTables.js' type='text/javascript'></script>

<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=invoiceHeader.getIcIvcHeader()%>"/>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tr><td><br></td></tr>
<tr>
  <td width="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
      <td valign="top" width="50px" height="30px">
        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        <tr>
          <td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
        </tr>
        <tr>
          <td nowrap class="hdr12" valign="middle">
            <div style="margin-left:10px; margin-right:10px" class="hdr12">Routing List</div>
          </td>
        </tr>
        </table>
      </td>
      <td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
      <td valign="bottom" align="right" height="30px" width="100%">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
          <td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="2px" class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
        </tr>
        </table>
      </td>
    </tr>
    </table>
  </td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td align="center">
		<font class="formType">Invoice </font><font class="hdr12">#<%=invoiceHeader.getInvoiceNumber()%></font>
	</td>
</tr>
<tr><td><br></td></tr>
</table>


<table border="0" cellspacing="0" cellpadding="0" width="640px">
  <tr height="20px">
    <td>
      <table border="0" align="center" width="25px">

      </table>
    </td>
    <td width="620px" class="browseHdrDk" align="center" valign="top">
        <table border="0" cellspacing="0" cellpadding="2" width="620px">
           <tr>
             <!--td nowrap="nowrap" width="4%" align="center" <%=HtmlWriter.isVisible(oid, "routingSequence")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingSequence", "Sequence")%>&nbsp;</td-->
             <td nowrap="nowrap" width="1%" class="browseHdrDk">&nbsp;</td>
              <td nowrap="nowrap" width="3%" <%=HtmlWriter.isVisible(oid, "routingAction")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAction", "Action")%>&nbsp;</td>
              <td nowrap="nowrap" width="10%" <%=HtmlWriter.isVisible(oid, "routingApprover")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprover", "Approver")%>&nbsp;</td>
              <td nowrap="nowrap" width="17%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproverName", "Approver Name")%>&nbsp;</td>
              <td nowrap="nowrap" width="10%" <%=HtmlWriter.isVisible(oid, "routingPoolId")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingPoolId", "Pool Id")%>&nbsp;</td>
              <td nowrap="nowrap" width="10%" <%=HtmlWriter.isVisible(oid, "routingAssigned")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAssigned", "Assigned")%>&nbsp;</td>
              <td nowrap="nowrap" width="12%" <%=HtmlWriter.isVisible(oid, "routingApproved")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApproved", "Approved")%>&nbsp;</td>
              <td nowrap="nowrap" width="5%" <%=HtmlWriter.isVisible(oid, "routingApprovalType")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingApprovalType", "Approval Type")%>&nbsp;</td>
              <td nowrap="nowrap" width="15%" <%=HtmlWriter.isVisible(oid, "routing-callForward")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routing-callForward", "Call Forward")%>&nbsp;</td>
              <td nowrap="nowrap" width="10%" align="right" <%=HtmlWriter.isVisible(oid, "routingAuthority")%> class="browseHdrDk"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "routingAuthority", "Authority")%>&nbsp;</td>
              <td width="1%" align="right" class="browseHdrDk" NOWRAP>

              </td>
          </tr>
        </table>
          <div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 630px; height: 190px; align:center; overflow-y:visible; overflow-x:auto;">
          <table id="approvers" border="0" cellpadding="0" cellspacing="0" width="620px" class="browseRow">
<%	for (int i = 0; i < routingList.size(); i++)
    {
      ApprovalLog appLog = (ApprovalLog) routingList.get(i);
      String classType = "browseRow";
      String s_approved_date = HiltonUtility.getFormattedDate(appLog.getDateApproved(), oid, userDateFormat);
      if (appLog.getApproved().equals("A"))
      {
        s_approved_date = "Current Approver";
        classType = "summary";
      }

      String	s_approver = appLog.getComp_id().getUserId();
      UserProfile approver = UserManager.getInstance().getUser(oid, s_approver);
      UserProfile callForward = UserManager.getInstance().getUser(oid, appLog.getCallForward());
      
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
            <tr height="15px">
              <!--td width="4%" <%=HtmlWriter.isVisible(oid, "routingSequence")%> class="browseRow" NOWRAP align="right"><%=appLog.getComp_id().getSequence()%>&nbsp;</td-->
              <td width="1%" class="<%=classType%>" NOWRAP align="right">
<%		if (appLog.getApproved().equals("Y"))	{ %>
                <img src="<%=contextPath%>/images/check.gif">
<%		} %>
                <span id="sequence<%=i%>"><%=appLog.getComp_id().getSequence()%>&nbsp;</span>
              </td>
                <td width="3%" <%=HtmlWriter.isVisible(oid, "routingAction")%> class="<%=classType%>" NOWRAP><%=appLog.getRuleAction()%>&nbsp;</td>
                <td width="10%" <%=HtmlWriter.isVisible(oid, "routingApprover")%> class="<%=classType%>" NOWRAP><%=appLog.getComp_id().getUserId()%>&nbsp;</td>
                <%if (!HiltonUtility.isEmpty(altApproverMsg))	{ %>
              	<td width="17%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%> class="<%=classType%>"><%=altApproverMsg%></td>
            	<%} else { %>
              	<td width="17%" <%=HtmlWriter.isVisible(oid, "routingApproverName")%> class="<%=classType%>" NOWRAP><%=approver.getDisplayName()%></td>
            	<%} %>
                <td width="10%" <%=HtmlWriter.isVisible(oid, "routingPoolId")%> class="<%=classType%>" NOWRAP><%=appLog.getPoolid()%>&nbsp;</td>
                <td width="10%" <%=HtmlWriter.isVisible(oid, "routingAssigned")%> class="<%=classType%>" NOWRAP><%=HiltonUtility.getFormattedDate(appLog.getDateAssigned(), oid, userDateFormat)%>&nbsp;</td>
                <td width="12%" <%=HtmlWriter.isVisible(oid, "routingApproved")%> class="<%=classType%>" NOWRAP><%=s_approved_date%>&nbsp;</td>
                <td width="5%" <%=HtmlWriter.isVisible(oid, "routingApprovalType")%> class="<%=classType%>" NOWRAP><%=appLog.getApproverType()%>&nbsp;</td>
                <td width="15%" <%=HtmlWriter.isVisible(oid, "routing-callForward")%> class="<%=classType%>" NOWRAP><span id="callforwardText<%=i%>"><%=callForward.getDisplayName()%>&nbsp;</span></td>
                <td width="10%" <%=HtmlWriter.isVisible(oid, "routingAuthority")%> class="<%=classType%>" align="right" NOWRAP><span id="authorityText<%=i%>"><%=HiltonUtility.getFormattedDollar(appLog.getApproverAmount(), oid)%>&nbsp;</span></td>
                <td width="1%" NOWRAP class="<%=classType%>">&nbsp;&nbsp;&nbsp;
                  <span id="deleteLink<%=i%>">

                  </span>&nbsp;
                </td>
                <span id="hiddenFields<%=i%>">
                <tsa:hidden name="ApprovalLog_userId" value="<%=appLog.getComp_id().getUserId()%>"/>
                <tsa:hidden name="ApprovalLog_amount" value="<%=appLog.getAmount()%>"/>
                <tsa:hidden name="ApprovalLog_approverAmount" value="<%=appLog.getApproverAmount()%>"/>
                <tsa:hidden name="ApprovalLog_approved" value="<%=appLog.getApproved()%>"/>
                <tsa:hidden name="ApprovalLog_udfValues" value="<%=appLog.getUdfValues()%>"/>
                <tsa:hidden name="ApprovalLog_authorized" value="<%=appLog.getAuthorized()%>"/>
                <tsa:hidden name="ApprovalLog_alternateUserid" value="<%=appLog.getAlternateUserid()%>"/>
                <tsa:hidden name="ApprovalLog_approverType" value="<%=appLog.getApproverType()%>"/>
                <tsa:hidden name="ApprovalLog_ruleType" value="<%=appLog.getRuleType()%>"/>
                <tsa:hidden name="ApprovalLog_poolid" value="<%=appLog.getPoolid()%>"/>
                <tsa:hidden name="ApprovalLog_ruleAction" value="<%=appLog.getRuleAction()%>"/>
                <tsa:hidden name="ApprovalLog_approverSig" value="<%=appLog.getApproverSig()%>"/>
                <tsa:hidden name="ApprovalLog_callForward" value="<%=appLog.getCallForward()%>"/>
                <!--
                ApprovalLog_dateAssigned
                ApprovalLog_dateApproved
                -->
              </span>
            </tr>
<%	} %>
          </table>
        </div>
      </td>
  </tr>
  <tr>
  	<td>
  	</td>
  	<td>
  		<%@ include file="/invoice/iv_approval_notes_display.jsp" %>
  	</td>
  </tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="2" CELLPADDING="2" ALIGN="CENTER" VALIGN="BOTTOM" WIDTH="300px">
<TR>
	<td align="center"><a href="javascript: window.top.hidePopWin(); void(0);"><img class="button" src="<%=contextPath%>/images/button_close.gif" border=0 alt="Close"></a></td>
</TR>
</TABLE>

</FORM>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>