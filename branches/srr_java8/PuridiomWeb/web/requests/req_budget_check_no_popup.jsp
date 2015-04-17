<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ page import="java.math.BigDecimal" %>

<%
	RequisitionHeader reqHeader = (RequisitionHeader)request.getAttribute("header");
	List	errorList = (List) request.getAttribute("errorList");
	List	accountList = (List) request.getAttribute("accountList");
  	String s_req_number = reqHeader.getRequisitionNumber();
  	boolean bForward = false;
  	String reqAction = (String)request.getAttribute("reqaction");
  	String	pricingAction = "";
  	if (reqAction == null){ reqAction = "BUDGETCHECK";	}
	if (reqAction.equalsIgnoreCase("REPRICING")) {
    	pricingAction = reqAction;
    	reqAction = "FORWARD";
  	}

  	String	s_status = (String) request.getAttribute("budgetStatus");
  	String	s_form_type = request.getParameter("budgetType");
  	String	s_action_code = request.getParameter("budgetAction");
  	String	s_action = request.getParameter("budgetActionText");
  	String 	s_prc_action = request.getParameter("prc_action");
  	String	errorMsg = "";

	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_releaseRequestDollarAmount = propertiesManager.getProperty("REQ OPTIONS","ReleaseRequestDollarAmount","0");
	BigDecimal bd_releaseRequestDollarAmount = new BigDecimal (s_releaseRequestDollarAmount);
	boolean allow_releaseRequest = true;
	if (reqHeader.getTotal().compareTo(bd_releaseRequestDollarAmount) > 0) {
		allow_releaseRequest = false;
	}
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionHeader_status" value="<%=reqHeader.getStatus()%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=reqHeader.getRequisitionType()%>"/>
<tsa:hidden name="reqaction" value=""/>
<tsa:hidden name="pricingAction" value="<%=pricingAction%>"/>

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
            <div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="budgetCheckResults" defaultString="Budget Check Results"/></div>
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
    <font class="formType"><tsa:label labelName="requisition" defaultString="Requisition"/> </font><font class="hdr12">#<%=headerEncoder.encodeForHTML(s_req_number)%></font>
  </tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
  <tsa:td width="100%" align="center" valign="top">
  <div id="budgetrules" style="display:none;">
		<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 600px; height: 100px; align:center; overflow-y:visible; overflow-x:auto;">
		<table border="0" cellpadding="0" cellspacing="0" width="600px">
			<tsa:tr cssClass="mnav" height="18px">
				<tsa:td noWrap="nowrap" cssClass="mnav" align="center"><tsa:label labelName="req-error-message" defaultString="Error Message"/></tsa:td>
				<tsa:td width="15px">&nbsp;</tsa:td>
				<tsa:td noWrap="nowrap" cssClass="mnav" align="center"><tsa:label labelName="req-budget-id" defaultString="Budget Id"/></tsa:td>
				<tsa:td width="15px">&nbsp;</tsa:td>
				<tsa:td noWrap="nowrap" cssClass="mnav" align="center"><tsa:label labelName="req-budget-authority" defaultString="Budget Authority"/></tsa:td>
				<tsa:td width="15px">&nbsp;</tsa:td>
				<tsa:td noWrap="nowrap" cssClass="mnav" align="center"><tsa:label labelName="req-initial-budget" defaultString="Initial Budget"/></tsa:td>
				<tsa:td width="15px">&nbsp;</tsa:td>
				<tsa:td noWrap="nowrap" cssClass="mnav" align="center"><tsa:label labelName="req-budget-balance" defaultString="Budget Balance"/></tsa:td>
				<tsa:td width="15px">&nbsp;</tsa:td>
			</tsa:tr>
		<%
				String classType = "summary";
				String severity = "";
				int counter = 1;
				int counterList = 0;
				for (int i = 0; i < errorList.size(); i++)
				{
					Hashtable ht = (Hashtable) errorList.get(i) ;
					String		bError = (String) ht.get("budgetError");
					String		bId = (String) ht.get("budgetId");
					String		bAuth = (String) ht.get("budgetAuth");
					BigDecimal		bBalance = (BigDecimal) ht.get("budgetBalance");
					BigDecimal		bInitial = (BigDecimal) ht.get("budgetInitial");
					String		bSeverity = (String) ht.get("errorSeverity");
		%>
					<tsa:tr cssClass="<%=classType%>" height="20px">
						<tsa:td noWrap="nowrap" cssClass="<%=classType%>" align="left" valign="top"><%=bError%>.</tsa:td>
						<tsa:td cssClass="<%=classType%>" width="15px">&nbsp;</tsa:td>
						<tsa:td cssClass="<%=classType%>" align="left" valign="top"><%=bId%></tsa:td>
						<tsa:td cssClass="<%=classType%>" width="15px">&nbsp;</tsa:td>
						<tsa:td cssClass="<%=classType%>" align="left" valign="top"><%=bAuth%></tsa:td>
						<tsa:td cssClass="<%=classType%>" width="15px">&nbsp;</tsa:td>
						<tsa:td cssClass="<%=classType%>" align="right" valign="top"><%=HiltonUtility.getFormattedDollar(bInitial,oid)%></tsa:td>
						<tsa:td cssClass="<%=classType%>" width="15px">&nbsp;</tsa:td>
						<tsa:td cssClass="<%=classType%>" align="right" valign="top"><%=HiltonUtility.getFormattedDollar(bBalance,oid)%></tsa:td>
						<tsa:td cssClass="<%=classType%>" width="15px">&nbsp;</tsa:td>
						<% if(bError.equalsIgnoreCase("Warning! Over Budget") || bError.equalsIgnoreCase("Over Budget"))
						{
							for (int j = 0; j < accountList.size(); j++)
							{
								Account account = (Account) accountList.get(j);
								String  fld1 = HiltonUtility.ckNull(account.getFld1());
								String  fld2 = HiltonUtility.ckNull(account.getFld2());
								String  fld3 = HiltonUtility.ckNull(account.getFld3());
								String  fld4 = HiltonUtility.ckNull(account.getFld4());
								String  ls_acct_string = fld1 + ";" + fld2 + ";" + fld3 + ";" + fld4;
								String  cad[] = bId.split(";");
					        	String  old_ls_acct_string = "";
					        	for (int c = 0; c < cad.length; c++)
								{
					        		String fld = cad[c];
					        		if(cad[c].equalsIgnoreCase("*") && c == 0)
					        		{
					        			fld = HiltonUtility.ckNull(account.getFld1());
					        		}
					        		if(cad[c].equalsIgnoreCase("*") && c == 1)
					        		{
					        			fld = HiltonUtility.ckNull(account.getFld2());
					        		}
					        		if(cad[c].equalsIgnoreCase("*") && c == 2)
					        		{
					        			fld = HiltonUtility.ckNull(account.getFld3());
					        		}
					        		if(cad[c].equalsIgnoreCase("*") && c == 3)
					        		{
					        			fld = HiltonUtility.ckNull(account.getFld4());
					        		}
					        		old_ls_acct_string += fld + ";";
								}
					        	if(old_ls_acct_string.indexOf(ls_acct_string) >= 0 && !HiltonUtility.isEmpty(fld4) && !fld4.equalsIgnoreCase("0000"))
								{
									if (counterList == 0)
									{	counterList++;
									%>
										<tsa:hidden name="BudgetFlag" value="OB"/>
								<%	}	%>
									<tsa:hidden name="account<%=i%><%=fld4%>" value="<%=fld4%>"/>
									<tsa:hidden name="Balance<%=i%><%=fld4%>" value="<%=bBalance%>"/>
					<%				break;
								}
							}
						} %>
						<tsa:hidden name="budgetError" value="<%=bId%>"/>
						<tsa:hidden name="budgetId" value="<%=bAuth%>"/>
					</tsa:tr>
				<%if(classType.equalsIgnoreCase("browseRow"))
		    		{
						classType = "summary";
		    		}
					else if(classType.equalsIgnoreCase("summary"))
					{
						classType = "browseRow";
					}
					counter++;
				} %>
			<tsa:tr><tsa:td noWrap="nowrap" colspan="10"><hr class="browseHR"></tsa:td></tsa:tr>
		</table>
		<table>
			<% 	if (s_status.equalsIgnoreCase("FAILED")) {  %>
					<tsa:tr><tsa:td align="CENTER"><BR><B><tsa:label labelName="req-budget-check-failed" defaultString="Budget Check Failed"/>!:<br><br><br></B></tsa:td></tsa:tr>
			<%	} else if (s_status.equalsIgnoreCase("IGNORE")) { %>
					<tsa:tr><tsa:td align="CENTER"><BR><BR><BR><B><tsa:label labelName="req-budget-Check-not-required" defaultString="A Budget Check is not required for this Requisition"/>!</B><BR><BR></tsa:td></tsa:tr>
			<%	} else { %>
					<tsa:tr><tsa:td align="CENTER"><BR><BR><B><tsa:label labelName="req-budget-check-passed" defaultString="Budget Check Passed"/>!</B><BR><BR></tsa:td></tsa:tr>
			<%	}  %>
			<% 	if (s_status.equalsIgnoreCase("FAILED")) { %>
<!--  					<TR><TD  ALIGN="CENTER"><BR><B>FAILURE REASONS<BR></B></TD></TR> -->
<!-- 					<TR><TD  ALIGN="CENTER">1. The amount of the request exceeds the budget.<BR></TD></TR>
					<TR><TD  ALIGN="CENTER">2. The authority to use the budget is not allowed.<BR></TD></TR>
					<TR><TD  ALIGN="CENTER">3. The budget does not exist.<br><br></TD></TR>
					<TR><TD  ALIGN="CENTER"> Please re-submit a revised and approved project budget to Procurement<br><br><br></TD></TR>
-->
<%				} 	%>
		</table>
		</div>
		</div>
  </tsa:td>
</tsa:tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" VALIGN="BOTTOM" width="655px">
	<tsa:tr>
	<% 	if (s_status.equals("FAILED") || reqAction.equals("BUDGETCHECK")) {%>
		<tsa:td align="center"><a HREF="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Requisition"></a></tsa:td>
	<%} else if (s_status.equals("WARNING") || reqAction.equals("FORWARD")) {%>
	  <tsa:td align="center"><a href="javascript: reqForward(); void(0);"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></tsa:td>
		<tsa:td align="center"><a HREF="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Requisition"></a></tsa:td>
<%}%>
</tsa:tr>
</TABLE>

</FORM>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;
  self.focus();
<%	if ((s_status.equalsIgnoreCase("PASSED") || s_status.equalsIgnoreCase("IGNORE")) && reqAction.equals("FORWARD"))
		{ %>
			displayArea('budgetrules');
	    	reqForward();
		<%}
  		else
  		{%>
  			displayArea('budgetrules');
  		<%}%>

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menubarSpacer");
	}

  function reqForward()
  {
    var reqaction = '<%=reqAction%>';
    frm.reqaction.value = reqaction;
    <%if(pricingAction.equalsIgnoreCase("repricing") || (reqHeader.getRequisitionType().equals("N") && reqHeader.getStatus().equals(DocumentStatus.REQ_INPROGRESS)) || (reqHeader.getRequisitionType().equals("E") && reqHeader.getStatus().equals(DocumentStatus.REQ_INPROGRESS) && allow_releaseRequest))
  {%>
    forward();
  <%}
  else
  {%>
      doSubmit('/requests/req_routinglist_no_popup.jsp', 'RequisitionGetRoutingList');
    <%}%>
  }


  function forward()
  {

      frm.reqaction.value = "FORWARD";
      doSubmit('/requests/req_forward.jsp', 'RequisitionForward');
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