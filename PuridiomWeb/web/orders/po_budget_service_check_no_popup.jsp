<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ page import="com.puridiom.service.budget.BudgetReturn" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ include file="/system/header.jsp" %>

<%
  PoHeader poHeader = (PoHeader) request.getAttribute("header");
  List	errorList = (List) request.getAttribute("errorList");
  String s_po_number = poHeader.getPoNumber();
  String poAction = (String)request.getAttribute("poaction");
  String	s_status = HiltonUtility.ckNull((String) request.getAttribute("budgetCheckStatus"));
  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);

  if (poAction == null){ poAction = "BUDGETCHECK";	}

  if (HiltonUtility.isEmpty(s_status)) {
	  s_status = "PASSED";
  }

  if (errorList == null) {
	  errorList = new ArrayList();
  }

  Boolean isBudgetUpdated = (Boolean) request.getAttribute("budgetUpdated");
%>

<tsa:hidden name="PoHeader_icPoHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%= poHeader.getIcPoHeader() %>"/>
<tsa:hidden name="Account_icHeader" value="<%= poHeader.getIcPoHeader() %>"/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="PoHeader_status" value="<%=poHeader.getStatus()%>"/>
<tsa:hidden name="poaction" value=""/>
<tsa:hidden name="poForwardOption" value=""/>
<tsa:hidden name="goforward" value=""/>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
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
            <div style="margin-left:10px; margin-right:10px" class="hdr12">Budget Check Results</div>
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
    <font class="formType"><%=OrderType.toString(poHeader.getPoType(), oid)%> </font><font class="hdr12">#<%=s_po_number%></font>
  </td>
</tr>
<tr><td><br></td></tr>
<tr>
  <td width="100%" align="center" valign="top">
  <div id="budgetrules" style="display:none;">
		<div id="browseBorder" class="browseHdrDk" style="border:solid 2px; background: #FFFFFF; padding: 2px; width: 600px; height: 100px; align:center; overflow-y:visible; overflow-x:auto;">
		<table border="0" cellpadding="0" cellspacing="0" width="600px">
			<tr class="mnav" height="18px">
				<td nowrap class="mnav" align="center">Error Message</td>
				<td width="15px">&nbsp;</td>
				<td nowrap class="mnav" align="center">Budget Id</td>
				<td width="15px">&nbsp;</td>
				<td nowrap class="mnav" align="center">Budget Authority</td>
				<td width="15px">&nbsp;</td>
				<%--
				<td nowrap class="mnav" align="center">Initial Budget</td>
				<td width="15px">&nbsp;</td>
				<td nowrap class="mnav" align="center">Budget Balance</td>
				<td width="15px">&nbsp;</td>
				--%>
			</tr>
		<%
				String classType = "summary";
				String severity = "";
				int counter = 1;
				int countBudgetLocked = 0;
				for (int i = 0; i < errorList.size(); i++)
				{
					Hashtable ht = (Hashtable) errorList.get(i) ;
					String		bError = (String) ht.get("budgetError");
					String		bId = (String) ht.get("budgetId");
					String		bAuth = (String) ht.get("budgetAuth");
					BigDecimal		bBalance = (BigDecimal) ht.get("budgetBalance");
					BigDecimal		bInitial = (BigDecimal) ht.get("budgetInitial");
					String		bSeverity = (String) ht.get("errorSeverity");
					String bException = (String) ht.get("budgetException");
		%>
					<tr class="<%=classType%>" height="20px">
						<td nowrap class="<%=classType%>" align="left" valign="top"><%=bError%>.</td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						<td class="<%=classType%>" align="left" valign="top"><%=bId%></td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						<td class="<%=classType%>" align="left" valign="top"><%=bAuth%></td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						<%--
						<td class="<%=classType%>" align="right" valign="top"><%=HiltonUtility.getFormattedDollar(bInitial,oid)%></td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						<td class="<%=classType%>" align="right" valign="top"><%=HiltonUtility.getFormattedDollar(bBalance,oid)%></td>
						<td class="<%=classType%>" width="15px">&nbsp;</td>
						--%>
					</tr>
				<%if(classType.equalsIgnoreCase("browseRow"))
		    		{
						classType = "summary";
		    		}
					else if(classType.equalsIgnoreCase("summary"))
					{
						classType = "browseRow";
					}

					if (Integer.parseInt(bException) == BudgetReturn.BUDGETLOCKED) {
						countBudgetLocked++;
					}

					counter++;
				} %>
			<tr ><td nowrap colspan="10"><hr class=browseHR></td></tr>
		</table>
		<table>
			<% 	if (s_status.equalsIgnoreCase("FAILED")) {  %>
					<TR><TD ALIGN="CENTER"><BR><B>Budget Check Failed!:<br><br><br></B></TD></TR>
			<%	} else if (s_status.equalsIgnoreCase("WARNING")) { %>
					<TR><TD ALIGN="CENTER"><BR><BR><B>Budget Check Passed with warnings!</B><BR><BR></TD></TR>
			<%	} else { %>
					<TR><TD ALIGN="CENTER"><BR><BR><B>Budget Check Passed!</B><BR><BR></TD></TR>
			<%	}  %>
		</table>
	   </div>
	</div>
  </td>
</tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" VALIGN="BOTTOM" width="655px">
	<TR>
	<% 	if (s_status.equals("FAILED") || poAction.equals("BUDGETCHECK")) {%>
			<% if (poAction.equals("FORWARD") && (errorList.size() == countBudgetLocked)) { %>
	    <TD align="center">
	    	<div id="pxbutton">
 				<a href="javascript: checkBudget(); void(0);"><%= DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "budgetTryAgain", "Try Again") %></a>
   		    </div>
	    </TD>
	    	<% } %>
   	<%} else if (s_status.equals("WARNING")) {%>
   		<TD align="center"><a HREF="javascript: orderForward();"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward Order"></a></TD>
	<% } %>
		<TD align="center"><a HREF="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Order"></a></TD>
</TR>
</TABLE>

</FORM>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;
  self.focus();
<%	if (s_status.equals("PASSED") && poAction.equals("FORWARD"))
		{ %>
			displayArea('budgetrules');
	    	orderForward();
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

	function checkBudget()
	{
		var poaction = '<%= headerEncoder.encodeForJavaScript(poAction) %>';
    	frm.poaction.value = poaction;

		doSubmit('/orders/po_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
	}

	function orderForward()
	{
		frm.poaction.value = "FORWARD";

	<%	if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y") &&
			(!poHeader.getPoType().equals("CT") || propertiesManager.getProperty("PO APPROVALS", "CONTRACTAPPROVALS", "Y").equalsIgnoreCase("Y"))) { %>
	      //doSubmitToPopup('/orders/po_forward_options.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
	      doSubmit('/orders/po_award_options.jsp', 'PoAwardOptions');
	<%	}
		else
		{ %>
	      frm.poForwardOption.value = "F";	/*  award Order*/
	     // doSubmit('/orders/po_forward_options.jsp', 'PoForward');
	      doSubmit('/orders/po_forward_options.jsp', 'PoForward');
	<%	} %>
	}

  function forward()
  {

      frm.poaction.value = "FORWARD";
      doSubmit('/orders/po_forward.jsp', 'PoForward');
  }

  function returnMe()
  {
<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
	doSubmit('orders/po_summary.jsp', 'PoRetrieve');
<%	} else { %>
	doSubmit('orders/po_review.jsp', 'PoRetrieve');
<%	} %>
  }
// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>