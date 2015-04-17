<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" VALIGN="BOTTOM" HEIGHT="135px">
	<TR>
		<TD NOWRAP CLASS="formType"><A HREF="javasript: orderForward('approval'); void(0);">Forward for Approval</A></TD>
	</TR>
	<TR>
		<TD NOWRAP CLASS="formType"><A HREF="javascript: orderForward('award'); void(0);">Award Order</A></TD>
	</TR>
	<TR>
		<!-- <TD ALIGN="CENTER"><A HREF="javascript: window.top.hidePopWin(); void(0);"><IMG class=button src="<%=contextPath%>/images/button_cancel.gif" BORDER="0" ALT="Cancel"></A></TD> -->
	</TR>
</TABLE>

</FORM>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	thisfrm = document.purchaseform;
	self.focus();

	function orderForward(forward)
	{
		setTimeout('window.top.hidePopWin();', 10);

		//set cursor to hourglass while the system is processing
		window.parent.document.body.style.cursor = "wait";

		if (forward == "approval")
		{
			window.parent.frm.poForwardOption.value = "A";
			window.parent.doSubmit('/requests/po_forward.jsp', 'PoForward');
		}
		else
		{
			/*  award order*/
			window.parent.frm.poForwardOption.value = "F";
			window.parent.doSubmit('/orders/po_forward.jsp', 'PoForward');
		}

		window.top.hidePopWin();
	}

// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>