<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<%@ page import="com.tsa.puridiom.common.documents.*" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
	ValidationRules rules = (ValidationRules)request.getAttribute("rules");
	PoHeader poHeader = (PoHeader)request.getAttribute("header");
	String s_po_type = poHeader.getPoType();
	String s_form_number = poHeader.getPoNumber();
	boolean bForward = false;
	String poAction = (String)request.getAttribute("poaction");
	if(poAction == null){ poAction = "VALIDATE";	}
	  PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=poHeader.getIcPoHeader()%>"/>
<tsa:hidden name="PoLine_icPoHeader" value="<%= poHeader.getIcPoHeader() %>"/>
<tsa:hidden name="Account_icHeader" value="<%= poHeader.getIcPoHeader() %>"/>
<tsa:hidden name="Account_accountType" value="POH"/>
<tsa:hidden name="PoHeader_status" value="<%=poHeader.getStatus()%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=poHeader.getPoType()%>"/>
<tsa:hidden name="poaction" value=""/>
<tsa:hidden name="poForwardOption" value=""/>
<tsa:hidden name="goforward" value=""/>
<tsa:hidden name="formtype" value="PO"/>
<tsa:hidden name="budgetAction" value="1"/>
<tsa:hidden name="budgetMake"  value="1"/>
<tsa:hidden name="budgetType" value="PO"/>
<tsa:hidden name="budgetYear" value="<%=poHeader.getFiscalYear()%>"/>

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
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="validation-results" defaultString="Validation Results" /></div>
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
		<font class="formType"><%=OrderType.toString(poHeader.getPoType(), oid)%> </font><font class="hdr12">#<%=s_form_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<%@ include file="/base/validation_rules.jsp" %>
	</td>
</tr>
</table>

<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="655px">
			<TR>
			<%	if ( rules.getResult() != 1 )
				{
    				if(rules.getResult() > -1 && poAction.equalsIgnoreCase("FORWARD"))
    				{ %>
				<td align="center">
					<div id="pxbutton">
      					<a href="javascript: orderForward();"><tsa:label labelName="pobuttonforward" defaultString="Forward" /></a>
	    		    </div>
      			</td>
      			<td align="center"><a href="javascript: returnToOrder();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
    			<% }
    			} %>

			</tr>
		<%if(rules.getResult() == 1 && poAction.equalsIgnoreCase("FORWARD"))
		{%>
			 <tr>
    		<td>
    		<div id="novalidationrules" style="display:none;">
      		<TABLE align="center">
      			<tr>
      			<TD VALIGN="MIDDLE"><IMG SRC='<%=contextPath%>/images/alert.gif' VALIGN="MIDDLE" BORDER="0"></TD>
      			<TD VALIGN="MIDDLE" CLASS="basic"><B><tsa:label labelName="waitWhileValidateMessage" defaultString="Please wait while we validate your order." /></B></TD>
      			</tr>
      		</TABLE>
      		</div>
    		</td>
			</tr>
		<%}
		if (rules.getResult() == -1 || poAction.equalsIgnoreCase("VALIDATE"))
		{%>
			<TD align="CENTER"><a HREF="javascript: returnToOrder();"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Order"></a></TD>
		<%}%>
	</TR>
</TABLE>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();
	<%if(rules.getResult() == 1 && poAction.equalsIgnoreCase("FORWARD"))
	{%>
		displayArea('novalidationrules');
	    orderForward();
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

	function orderForward()
  {
  		frm.poaction.value = "FORWARD";

<%
	if (propertiesManager.getProperty("MODULES", "ACCOUNT BUDGET", "N").equalsIgnoreCase("Y")) { %>
		<%	if (propertiesManager.getProperty("BUDGET", "BUDGETSERVICEACTIVE", "N").equalsIgnoreCase("Y")) { %>
     	  doSubmit('/orders/po_budget_service_check_no_popup.jsp', 'CheckAccountsForDocumentLine;BudgetServiceCheck');
      	<% } else { %>
			doSubmit('/orders/po_budget_check_no_popup.jsp', 'PoBudgetCheck');
      	<% } %>
	<% } else if (propertiesManager.getProperty("MODULES", "PO APPROVALS", "N").equalsIgnoreCase("Y") &&
			(!poHeader.getPoType().equals("CT") || propertiesManager.getProperty("PO APPROVALS", "CONTRACTAPPROVALS", "Y").equalsIgnoreCase("Y"))) { %>
		      //doSubmitToPopup('/orders/po_forward_options.jsp', 'DoNothing', 'WIDTH=350', 'HEIGHT=165');
		      doSubmit('/orders/po_award_options.jsp', 'PoAwardOptions');
	<%} else { %>
	      frm.poForwardOption.value = "F";	/*  award Order*/
	     // doSubmit('/orders/po_forward_options.jsp', 'PoForward');
	      doSubmit('/orders/po_forward_options.jsp', 'PoForward');
	<%} %>
  }

	function returnToOrder() {
		if (frm.viewType.value == "CLASSIC") {
			doSubmit('orders/po_summary.jsp', 'PoRetrieve')
		} else {
			doSubmit('orders/po_review.jsp', 'PoRetrieve')
		}
	}

// end hiding contents -->
</SCRIPT>

<%@ include file="/system/footer.jsp" %>