<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" VALIGN="BOTTOM" HEIGHT="135px">
	<TR>
		<TD NOWRAP CLASS="formType"><A HREF="javascript: reqForward('reprice'); void(0);"><tsa:label labelName="forwardRepricing" defaultString="Forward for Repricing" /></A></TD>
	</TR>
	<TR>
		<TD NOWRAP CLASS="formType"><A HREF="javascript: reqForward('approve'); void(0);"><tsa:label labelName="forwardPurchasing" defaultString="Forward to Purchasing" /></A></TD>
	</TR>
	<TR>
		<TD ALIGN="CENTER"><A HREF="javascript: window.top.hidePopWin(); void(0);"><IMG class=button src="<%=contextPath%>/images/button_cancel.gif" BORDER="0" ALT="Cancel"></A></TD>
	</TR>	
</TABLE>

</FORM>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	thisfrm = document.purchaseform;
	self.focus();

	function reqForward(forward)
	{
		if (forward == "reprice")
		{
			window.parent.frm.pricingAction.value = "repricing";
			window.parent.doSubmit('/requests/req_forward.jsp', 'RequisitionForward');
		}
		else
		{
			window.parent.doSubmit('/requests/req_forward.jsp', 'RequisitionForward');
		}
		
		setTimeout('window.top.hidePopWin();', 10);
	}

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>