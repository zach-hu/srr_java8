<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/supplierportal/system/prevent_caching.jsp" %>
<%@ include file="/supplierportal/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>

<%
	String icPoHeader = (String) request.getAttribute("PoHeader_icPoHeader");
	String s_form_number = (String) request.getAttribute("PoHeader_poNumber");
	String s_poType = (String) request.getAttribute("PoHeader_poType");
	String s_vendorId = (String) request.getAttribute("PoHeader_vendorId");
	String s_vendorName = (String) request.getAttribute("PoHeader_vendorName");
	String s_contact_code = (String) request.getAttribute("PoHeader_vendContactCode");
%>
<tsa:hidden name="allowBrowse" value="true"/>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr><td><br></td></tr>
<tr>
	<td align="center">Thank you!
		<font class="formType"><%=OrderType.toString(s_poType, oid)%> </font><a href="javascript: viewPo('<%=icPoHeader%>', '<%=s_poType%>'); void(0);"><font class="hdr12">#<%=s_form_number%></font></a>
		has been acknowledged.
	</td>
</tr>
<tr><td><br><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
<%@ include file="/supplierportal/browse/browse_form.jsp" %>
	</td>
</tr>
<tr>
	<TD align="center"><a HREF="javascript: doSubmit('/menu/main_menu.jsp', 'DoNothing'); void(0);"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return to Requisition"></a></TD>
</tr>
</table>
<br>
<br>
<br/>
<%@ include file="/supplierportal/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	function selectEmailTo()
	{
		frm.print_option_email.checked = true;
	}

	function setViewOptions()
	{
		if(frm.print_option_view.checked)
		{
			frm.print_option_email.checked = false;
			frm.viewNow.value = "Y";
		}
	}

	function setEmailOptions()
	{
		if (frm.print_option_email.checked)
		{
			frm.print_option_view.checked = false;
			frm.viewNow.value = "N";
		}
	}

	function pdfOptions()
	{
		popupParameters = "PoHeader_icPoHeader=<%=icPoHeader%>";
		popupParameters = popupParameters + ";oid=<%=oid%>";


		if (frm.print_option_view.checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToLookup('', 'PrintPoPdf', 'width=775px', 'height=850px');
		}
		else if (frm.print_option_email.checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('/orders/po_review.jsp', 'EmailPoPdf;PoRetrieve');
			}
		}
		else
		{
			alert("You must select an option.");
		}
	}

	function checkemail()
	{
		var str = frm.email.value;
		var emailArray = str.split(";");

		for (x=0; x < emailArray.length; x++)
		{
			if (checkOneEmail(emailArray[x]))
			{
				//return true;
			}
			else
			{
				frm.email.select();
				return false;
			}
		}

		return true;
	}
	function checkOneEmail(email)
	{
		var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i

		if (filter.test(email))
		{
			return true;
		}
		else
		{
			alert("Please input a valid email address!");
			return false;
		}
	}

	function viewPo(ic, type) {
		var myCell = document.getElementById("hiddenFields");
		myCell.innerHTML = "";
		var newInputField = "<input type='hidden' name='PoHeader_icPoHeader' value='" + ic + "'>";

		myCell.innerHTML = newInputField;
		doSubmit('/orders/po_review.jsp','PoRetrieve');
	}


// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>