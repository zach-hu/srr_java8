<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	String errorMsg = HiltonUtility.ckNull((String) request.getAttribute("errorMsg"));
	String loginId = HiltonUtility.ckNull((String) request.getAttribute("loginId"));
	String x_invoice_num = HiltonUtility.ckNull((String) request.getAttribute("x_invoice_num"));
	String x_trans_id = HiltonUtility.ckNull((String) request.getAttribute("x_trans_id"));
	String x_description = HiltonUtility.ckNull((String) request.getAttribute("x_description"));
	String x_amount = HiltonUtility.ckNull((String) request.getAttribute("x_amount"));
	String renewalPackageName = x_description;

	String paymentSummaryMsg = (String) request.getAttribute("paymentSummaryMsg");
	if (HiltonUtility.isEmpty(paymentSummaryMsg)) {
		String organizationName = OrganizationManager.getInstance().getOrganizationName(oid);

		if (HiltonUtility.ckNull(x_description).toUpperCase().startsWith("FREE")) {
			renewalPackageName = "Individual";
			paymentSummaryMsg = "No charges have been made to your credit card at this time." +
				"<br><br><b>Organization Id:</b> " + oid +
				"<br><b>Organization Name:</b> " + organizationName +
				"<br><b>Description:</b> " + x_description +
				"<br><b>Transaction #:</b> " + x_trans_id;
		}
		else {
			paymentSummaryMsg = "<b>Organization Id:</b> " + oid +
				"<br><b>Organization Name:</b> " + organizationName +
				"<br><b>Description:</b> " + x_description +
				"<br><b>Transaction #: " + x_trans_id +
				"<br><b>Invoice #:</b> " + x_invoice_num +
				"<br><b>Total Charges:</b> US " + x_amount;
		}
	}

	Date renewalDate = OrganizationManager.getInstance().getOrganization(oid).getDateExpires();
	String renewalMsg = "On " + HiltonUtility.getFormattedDate(renewalDate, oid, "MMM d, yyyy") + ", your credit card will be automatically billed for the " + renewalPackageName + " annual subscription rate.";
	if (HiltonUtility.ckNull(x_description).toUpperCase().startsWith("FREE")) {
		renewalMsg = renewalMsg + "<br>If you choose to cancel your membership before your trial has ended no charges will be incurred.";
	}
%>

<tsa:hidden name="resetFailurePage" value="user/pswd_help.jsp"/>
<tsa:hidden name="loginId" value="<%=loginId%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "registrationComplete", "Registration Complete", false)%></div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%> height=100px>
<tr>
	<td align=center>
		<table border=0 cellspacing=0 cellpadding=0 width=700px>
		<tr><td class=error><%=errorMsg%></td></tr>
		<tr><td><b>Congratulations!  You have completed the registration process and are just minutes away from accessing the Power of Puridiom Xpress.</b></td></tr>
		<tr><td><br></td></tr>
		<tr><td><b><font class="largeText">You will receive an email with your login information to activate your account.</font></b></td></tr>
		<tr><td><br></td></tr>
		<tr><td>If you do not receive an email within 24 hours, please contact: support@puridiomx.com .</td></tr>
		</table>
		<br><br>
		<table border=0 cellspacing=0 cellpadding=0 width=700px>
		<tr><td><%=paymentSummaryMsg%></td></tr>
		</table>
		<br><br>
		<table border=0 cellspacing=0 cellpadding=0 width=700px>
		<tr><td><%=renewalMsg%></td></tr>
		</table>
		<br><br>
		<table border=0 cellspacing=0 cellpadding=0 width=700px>
		<tr><td>Print this information for your records.</td></tr>
		</table>
		<br><br>
		<table border=0 cellspacing=0 cellpadding=0 width=<%=formEntryWidth%>>
		<tr><td align=center><div id="pxbutton"><a href="javascript: printSummary(); void(0);">Print</a></div></td></tr>
		</table>
		<br><br>
		<table border=0 cellspacing=0 cellpadding=0 width=700px>
		<tr><td><a href="http://www.puridiomx.com/help/manual/Puridiom_Administration.pdf" target="_blank">Click here for information on Puridiom Xpress System Administration.</a></td></tr>
		</table>

	</td>
</tr>
</table>

<br><br>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function thisLoad() {
		return;
	}

	function printSummary() {
		this.print();
	}
//-->
</SCRIPT>

<%@ include file="/system/prevent_caching.jsp" %>