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
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_poType%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="PoHeader_vendContactCode" value="<%=s_contact_code%>"/>
<tsa:hidden name="emailTo" value="N" />

<table border="0" cellpadding="0" cellspacing="0" width="505px">
<tr><td><br></td></tr>
<tr>
	<td width="100%">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td valign="top" width="50px" height="30px">
				<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
				<tr>
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" valign="middle">
						<div style="margin-left:10px; margin-right:10px" class="hdr12">Order</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left"><img class="hdr12" src="<%=contextPath%>/supplierportal/images/angle.gif" height="31" /></td>
			<td valign="bottom" align="right" height="30px" width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td width="100%" height="1px" class="lightShadow"><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="1" /></td>
				</tr>
				<tr>
					<td height="2px" class=darkShadow><img src="<%=contextPath%>/supplierportal/images/none.gif" width="1" height="2" /></td>
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
		<font class="formType"><%=OrderType.toString(s_poType, oid)%> </font><font class="hdr12">#<%=s_form_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<table border="1" cellpadding="0" cellspacing="0" width="445px">
			<tr class="mnav" height="18px">
				<td class="mnav" nowrap colspan="2">&nbsp;&nbsp;Print Options&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table border=0>
						<tr>
							<td width="15px" align="center">
								<input type="checkbox" name="print_option_view" value="pdf" checked onclick="setViewOptions();"/>
								<tsa:hidden name="viewNow" value="Y" />
							</td>
							<td nowrap colspan=2>View Pdf</td>
						</tr>

						<tr>
							<td width="15px" align="center"><input type="checkbox" name="print_option_email" value="eu" onclick="setEmailOptions();"/></td>
							<td colspan=2 nowrap>Email To&nbsp;<input type="text" name="email" value="" onfocus="selectEmailTo();" / size=60></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="455px">
	<tr>
		<td align=center><a href="javascript: pdfOptions();"><img class="button" src="<%=contextPath%>/supplierportal/images/button_print.gif" border="0" alt="Print" ></a></td>
		<td align=center><a href="javascript: returnMe();"><img class="button" src="<%=contextPath%>/supplierportal/images/button_return.gif" border=0></a></td>
	</tr>
</table>
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

	function returnMe()
	{
		doSubmit('orders/po_review.jsp', 'PoRetrieve');
	}


// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>