<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ include file="/inventory/inv_property_checklist_fields.jsp" %>
<%@ page import="com.tsagate.foundation.rule.*" %>
<script language='Javascript1.2' src="<%=contextPath%>/scripts/browse.js"></script>
<%
	ValidationRules rules = (ValidationRules)request.getAttribute("rules");
	DisbHeader disbHeader = (DisbHeader)request.getAttribute("header");
	String s_disb_number = disbHeader.getDisbNumber();
	String extraSubjectFYI = HiltonUtility.ckNull((String) request.getAttribute("extraSubjectFYI"));
	boolean bForward = false;
	String disbaction = (String)request.getAttribute("disbaction");
	if (disbaction == null) {
		disbaction = "VALIDATE";
	}
	if (HiltonUtility.isEmpty(s_disb_number)) {
		s_disb_number = "N/A";
	}
%>

<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=disbHeader.getIcDsbHeader()%>"/>
<tsa:hidden name="DisbLine_icDsbHeader" value="<%=disbHeader.getIcDsbHeader()%>"/>
<tsa:hidden name="DisbLine_icDsbLine" value=""/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=disbHeader.getDisbNumber()%>"/>
<tsa:hidden name="DisbHeader_status" value="<%=disbHeader.getStatus()%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=disbHeader.getDisbType()%>"/>
<tsa:hidden name="DisbHeader_icAccount" value="<%=disbHeader.getIcDsbHeader()%>"/>
<tsa:hidden name="Account_icHeader" value="<%=disbHeader.getIcDsbHeader()%>"/>
<tsa:hidden name="Account_icLine" value="0"/>
<tsa:hidden name="Account_accountType" value="DBH"/>
<tsa:hidden name="DocComment_icHeader" value="<%=disbHeader.getIcDsbHeader()%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="disbaction" value=""/>
<tsa:hidden name="formtype" value="DSB"/>
<tsa:hidden name="budgetAction" value="1"/>
<tsa:hidden name="budgetMake"  value="1"/>
<tsa:hidden name="budgetType" value="DSB"/>
<tsa:hidden name="budgetYear" value="<%=disbHeader.getFiscalYear()%>"/>

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
    <span class="formType"><tsa:label labelName="disbursment" defaultString="Disbursment"></tsa:label></span><span class="hdr12">#<%=s_disb_number%></span>
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
	      if (rules.getResult() > -1 && disbaction.equalsIgnoreCase("FORWARD"))
	      { %>
	  <tsa:td align="center"><div id="pxbutton"><a href="javascript: disbForward(); void(0);">Forward</a></div></tsa:td>
	  <tsa:td align="center"><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Return</a></div></tsa:td>
	<%		}
	    } %>
	</tsa:tr>
	<%	if (rules.getResult() == 1 && disbaction.equalsIgnoreCase("FORWARD"))
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
	<%if (rules.getResult() == -1 || disbaction.equalsIgnoreCase("VALIDATE"))
	{%>
		<tsa:td align="center"><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Return</a></div></tsa:td>
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
<%	if (rules.getResult() == 1 && disbaction.equalsIgnoreCase("FORWARD"))
	{ %>
		displayArea('novalidationrules');
		disbForward();
<%	} else { %>
		displayArea('validationrules');
<%	} %>

	function checkHiddenMenu()
	{
		hideArea("navTable");
		displayArea("menuBarSpacer");
	}

	function disbForward()
	{
		doSubmit('/inventory/dsb_forward.jsp', 'DisbSetProperty;DisbForward');
	}

	function returnMe()
	{
	<%	if (s_view.equalsIgnoreCase("CLASSIC")) { %>
		doSubmit('inventory/dsb_summary.jsp', 'DisbSetProperty;DisbursementRetrieve');
	<%	} else { %>
		doSubmit('inventory/dsb_review.jsp', 'DisbSetProperty;DisbursementRetrieve');
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