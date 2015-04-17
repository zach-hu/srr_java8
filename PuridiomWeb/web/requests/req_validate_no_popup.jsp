<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
  ValidationRules rules = (ValidationRules)request.getAttribute("rules");
  RequisitionHeader reqHeader = (RequisitionHeader)request.getAttribute("header");
  String s_req_number = reqHeader.getRequisitionNumber();
  String extraSubjectFYI = HiltonUtility.ckNull((String) request.getAttribute("extraSubjectFYI"));
  boolean bForward = false;
  String reqAction = (String)request.getAttribute("reqaction");
  String	pricingAction = "";
  if (reqAction == null){ reqAction = "VALIDATE";	}

  if (reqAction.equalsIgnoreCase("REPRICING")) {
    pricingAction = reqAction;
    reqAction = "FORWARD";
  }
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionLine_icReqHeader" value="<%= reqHeader.getIcReqHeader() %>"/>
<tsa:hidden name="Account_icHeader" value="<%= reqHeader.getIcReqHeader() %>"/>
<tsa:hidden name="Account_accountType" value="RQH"/>
<tsa:hidden name="RequisitionHeader_fiscalYear" value="<%=reqHeader.getFiscalYear()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=reqHeader.getStatus()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=reqHeader.getRequisitionType()%>"/>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="pricingAction" value="<%=pricingAction%>"/>
<tsa:hidden name="formtype" value="REQ"/>
<tsa:hidden name="budgetAction" value="1"/>
<tsa:hidden name="budgetMake"  value="1"/>
<tsa:hidden name="budgetType" value="REQ"/>
<tsa:hidden name="budgetYear" value="<%=reqHeader.getFiscalYear()%>"/>
<tsa:hidden name="extraSubjectFYI" value="<%=extraSubjectFYI%>"/>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
<tsa:tr>
  <tsa:td width="100%">
    <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tsa:tr>
      <tsa:td valign="top" width="50px" height="30px">
        <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
        <tsa:tr>
          <tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
        </tsa:tr>
        <tsa:tr>
          <tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
            <div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="validationResults" defaultString="Validation Results"></tsa:label></div>
          </tsa:td>
        </tsa:tr>
        </table>
      </tsa:td>
      <tsa:td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
      <tsa:td valign="bottom" align="right" height="30px" width="100%">
        <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tsa:tr>
          <tsa:td width="100%" height="1px" cssClass="lightShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></tsa:td>
        </tsa:tr>
        <tsa:tr>
          <tsa:td height="2px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></tsa:td>
        </tsa:tr>
        </table>
      </tsa:td>
    </tsa:tr>
    </table>
  </tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
  <tsa:td align="center">
    <span class="formType"><tsa:label labelName="requisition" defaultString="Requisition"></tsa:label></span><span class="hdr12">#<%=headerEncoder.encodeForHTML(s_req_number)%></span>
  </tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
  <tsa:td width="100%" align="center" valign="top">
    <%@ include file="/base/validation_rules.jsp" %>
  </tsa:td>
</tsa:tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" VALIGN="BOTTOM" width="680px">
	<tsa:tr>
	<%	if ( rules.getResult() != 1 )
	    {
	      if (rules.getResult() > -1 && reqAction.equalsIgnoreCase("FORWARD"))
	      { %>
	  <tsa:td align="center"><a href="javascript: reqForward(); void(0);"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border="0" alt="Forward"></a></tsa:td>
	  <tsa:td align="center"><a href="javascript: returnMe(); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return"></a></tsa:td>
	<%		}
	    } %>
	</tsa:tr>
	<%	if (rules.getResult() == 1 && reqAction.equalsIgnoreCase("FORWARD"))
	    { %>
	       <tsa:tr>
	        <tsa:td>
	        	<div id="novalidationrules" style="display: none;">
	            <TABLE align="center">
	            	<tsa:tr>
		              <tsa:td valign="MIDDLE"><IMG SRC='<%=contextPath%>/images/alert.gif' VALIGN="MIDDLE" BORDER="0"></tsa:td>
		              <tsa:td valign="MIDDLE" cssClass="basic"><B><tsa:label labelName="req-validate-your-request" defaultString="Please wait while we validate your request."></tsa:label></B></tsa:td>
		             </tsa:tr>
	            </TABLE>
	            </div>
	        </tsa:td>
	      </tsa:tr>
	<%}%>
	<tsa:tr>
	<%if (rules.getResult() == -1 || reqAction.equalsIgnoreCase("VALIDATE"))
	{%>
		<tsa:td align="center"><a HREF="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border="0" alt="Return to Requisition"></a></tsa:td>
	<%}%>
</tsa:tr>
</TABLE>

</FORM>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;
  var reqType = '<%=request.getParameter("RequisitionHeader_requisitionType")%>' ;
  var autoDisb = '<%=propertiesManager.getProperty("INVENTORY","AutoCreateDisb","N")%>' ;
  self.focus();
<%	if (rules.getResult() == 1 && reqAction.equalsIgnoreCase("FORWARD"))
		{ %>
			displayArea('novalidationrules');
	    	reqForward();
		<%}
  		else
  		{%>
  			displayArea('validationrules');
  		<%}%>

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

  function reqForward()
  {
    var reqaction = '<%=reqAction%>';
    frm.reqaction.value = reqaction;

    <%
    if( pricingAction.equalsIgnoreCase("repricing") || ( reqHeader.getRequisitionType().equals("N") && reqHeader.getStatus().equals(DocumentStatus.REQ_INPROGRESS) ) ||
    		((reqHeader.getRequisitionType().equals("S") || reqHeader.getRequisitionType().equals("T") || reqHeader.getRequisitionType().equals("I")) && propertiesManager.getProperty("APPROVALS","ApproveInvRequests","N").equalsIgnoreCase("Y") ) )
  {%>
    forward();
  <%}
  else
  {%>
	<%	if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) { %>
			<%	if (propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
//      	  doSubmit('/requests/req_budget_check_no_popup.jsp', 'CheckAccountsForDocumentLine;RequisitionBudgetCheck');
      	  doSubmit('/requests/req_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
      	  //doSubmit('/requests/req_routinglist_no_popup.jsp', 'RequisitionGetRoutingList');
	      	<% } else { %>
      	  doSubmit('/requests/req_budget_check_no_popup.jsp', 'RequisitionBudgetCheck');
	      	<% } %>
      <% } else { %>
	      doSubmit('/requests/req_routinglist_no_popup.jsp', 'RequisitionGetRoutingList');
	      <% } %>
 <%}%>
  }

  /*function forward()
  {
    var reqaction = '<%=reqAction%>';

    setTimeout('window.top.hidePopWin();', 500);

    //set cursor to hourglass while the system is processing
    window.parent.document.body.style.cursor = "wait";
    window.parent.frm.reqaction.value = "FORWARD";
    window.parent.frm.pricingAction.value = frm.pricingAction.value;
    window.parent.reqForward();

    window.top.hidePopWin();
  }
  */

  function forward()
  {

      frm.reqaction.value = "FORWARD";
      if (reqType == "S" && autoDisb == "Y") {
	      doSubmit('/requests/req_forward.jsp', 'RequisitionForward;DisbursementCreate');
      } else {
			doSubmit('/requests/req_forward.jsp', 'RequisitionForward');
	  }
  }

  function returnMe()
  {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	doSubmit('requests/req_summary.jsp', 'RequisitionRetrieve');
<%	} else { %>
	doSubmit('requests/req_review.jsp', 'RequisitionRetrieve');
<%	} %>
  }

  function thisUnLoadPopup()
  {
      window.parent.showForwardButton();
  }

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>