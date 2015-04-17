<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>

<%
  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
  ValidationRules rules = (ValidationRules)request.getAttribute("rules");
  BudgetCenter budgetCenter = (BudgetCenter)request.getAttribute("budgetCenter");
  String s_budget = "";
  if ( budgetCenter != null) {
	  s_budget = budgetCenter.getBudget1() + " - " + budgetCenter.getBudget2();
  }
%>

<tsa:hidden name="BudgetCenter_budgetId" value="<%=budgetCenter.getBudgetId()%>"/>


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
            <div style="margin-left:10px; margin-right:10px" class="hdr12">Validation Results</div>
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
    <span class="formType">Budget :</span><span class="hdr12">  <%=s_budget%></span>
  </td>
 </tr>
<tr><td><br></td></tr>
<tr>
  <td width="100%" align="center" valign="top">
    <%@ include file="/base/validation-rules.jsp" %>
  </td>
</tr>
</table>

<br>
<br>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" VALIGN="BOTTOM" width="680px">
	<TR>
	<%	if ( rules.getResult() != 1 )
	    {
	      if (rules.getResult() > -1 )
	      { %>
	  <TD align="center"><a href="javascript: reqForward(); void(0);"><img class="button" src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward"></a></TD>
	  <TD align="center"><a href="javascript: returnMe(); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
	<%		}
	    } %>
	</TR>
	<%	if (rules.getResult() == 1 )
	    { %>
	       <tr>
	        <td>
	        	<div id="novalidationrules" style="display:none;">
	            <TABLE align="center">
	            	<tr>
		              <TD VALIGN="MIDDLE"><IMG SRC='<%=contextPath%>/images/alert.gif' VALIGN="MIDDLE" BORDER="0"></TD>
		              <TD VALIGN="MIDDLE" CLASS="basic"><B>Please wait while we validate your request.</B></TD>
		             </tr>
	            </TABLE>
	            </div>
	        </td>
	      </tr>
	<%}
	if (rules.getResult() == -1 )
	{%>
		<TD align="center"><a HREF="javascript: returnMe();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Budget"></a></TD>
	<%}%>
</TR>
</TABLE>

</FORM>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

  frm = document.purchaseform;

  self.focus();
<%	if (rules.getResult() == 1 )
		{ %>
			displayArea('novalidationrules');
	    	viewBudget();
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

   function viewBudget()
  {
     doSubmit('/budget/budget_review.jsp', 'BudgetCenterRetrieveById');
  }

  function returnMe()
  {
	doSubmit('budget/budget_review.jsp', 'BudgetCenterRetrieveById');
  }

  function thisUnLoadPopup()
  {
      window.parent.showForwardButton();
  }

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>