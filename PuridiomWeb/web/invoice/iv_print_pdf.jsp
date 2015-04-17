<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>

<%
	String icIvcHeader = (String)request.getAttribute("InvoiceHeader_icIvcHeader");
	String s_form_number = (String)request.getAttribute("InvoiceHeader_invoiceNumber");
	String s_vendorId = (String)request.getAttribute("InvoiceHeader_vendorId");
	String s_contact_code = (String)request.getAttribute("InvoiceHeader_vendContactCode");
	String s_email = (String)request.getAttribute("InvoiceHeader_email");
	String s_icPoHeader = (String) request.getAttribute("InvoiceHeader_icPoHeader");

	String s_emailAddress = "";
	String s_faxNumber    = "";
	Object vendorEntity = VendorManager.getInstance().getVendor(oid, s_vendorId);
	if (vendorEntity != null)
	{
		if (vendorEntity instanceof Vendor)
		{
			s_emailAddress = ((Vendor)vendorEntity).getEmailAddress();
			s_faxNumber    = ((Vendor)vendorEntity).getFaxNumber();
		}
	}
%>
<tsa:hidden name="InvoiceHeader_icIvcHeader" value="<%=icIvcHeader%>"/>
<tsa:hidden name="InvoiceHeader_invoiceNumber" value="<%=s_form_number%>"/>

<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="emailTo" value="N" />

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
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
						<div style="margin-left:10px; margin-right:10px" class="hdr12">Invoice</div>
					</td>
				</tr>
				</table>
			</td>
			<td valign="bottom" align="left" width=*><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></td>
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
		<font class="formType">Invoice #</font><font class="hdr12"><%=s_form_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>

<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<table border="1" cellpadding="0" cellspacing="0" width="75%">
			<tr class="mnav" height="18px">
				<td class="mnav" nowrap colspan="2">&nbsp;&nbsp;Print Options&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table border=0>
					<tr>
						<td width="15px" align="center"><input type="radio" name="print_option" value="pdf" checked onClick="setDisplayOptions();"/>
						<tsa:hidden name="viewNow" value="Y" /></td>
						<td nowrap>View Pdf</td>
					</tr>
					<tr>
						<td width="15px" align="center"><input type="radio" name="print_option" value="fu"  onClick="setDisplayOptions();"/></td>
						<td nowrap>Fax To&nbsp;</td><td><input type="text" name="fax" value="<%=s_faxNumber%>" onfocus="selectFaxTo();" size="35" />&nbsp;i.e. 012.345.6789</td>
					</tr>

					<tr>
						<td width="15px" align="center"><input type="radio" name="print_option" value="eu"  onClick="setDisplayOptions();"/></td>
						<td nowrap>Email To&nbsp;</td><td><input type="text" name="email" value="<%=s_emailAddress%>" onfocus="selectEmailTo();" size="35" /></td>
					</tr>
					<tr>
						<td width="15px" align="center"></td>
						<td nowrap valign="top" align="right"><label for="notes">Notes</label>&nbsp;</td>
						<td nowrap colspan="2"><textarea name="notes" cols="55" rows="4" id="notes" onfocus="selectEmailTo();"></textarea></td>
					</tr>
					<tsa:tr>
						<tsa:td colspan="2">&nbsp;</tsa:td>
						<tsa:td field="ivc-linkToPO">
							<table>
							<tsa:tr>
								<tsa:td field="ivc-linkToPO"><tsa:input type="checkbox" id="ivc-linkToPO" name="linkToPO" checked="true" value="Y"/></tsa:td>
								<tsa:td><tsa:label labelName="ivc-linkToPO" defaultString="Include Link to View Order"/></tsa:td>
							</tsa:tr>
							</table>
						</tsa:td>
					</tsa:tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="<%=formEntryWidth%>">
	<TR>
		<td align=center width=50%><div id="pxbutton"><a href="javascript: pdfOptions(); void(0);">Print</a></div></td>
		<td align=center width=50%><div id="pxbutton"><a href="javascript: returnMe(); void(0);">Return</a></div></td>
	</TR>
</TABLE>
<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	setDisplayOptions();
	
	function selectEmailTo()
	{
		frm.print_option[2].checked = true;
		setDisplayOptions();
	}

	function selectFaxTo()
	{
		frm.print_option[1].checked = true;
		setDisplayOptions();
	}

	function pdfOptions()
	{
		popupParameters = "InvoiceHeader_icIvcHeader=<%=icIvcHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.print_option[0].checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToPopup('/system/popupDocAttachment.jsp', 'InvoicePdf', 'width=775px', 'height=850px');
		}
		else if(frm.print_option[2].checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('invoice/iv_review.jsp', 'EmailInvoicePdf;InvoiceRetrieve');
			}
		}
		else
		{
			if(checkfax())
			{
				;
			}
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
		doSubmit('invoice/iv_review.jsp', 'InvoiceRetrieve');
	}

	function checkfax()
	{
		var fax = frm.fax.value;
		fax = fax.replace(/[\(\)\.\-\ ]/g, '');

		if (isNaN(parseInt(fax)))
		{
			alert("Please input a valid fax number!");
			return false;
		}
		else if (!(fax.length == 10))
		{
			alert("Please input a valid fax number!");
			return false;
		}
		return true;
	}

	function setDisplayOptions() {
		var icPoHeader = "<%=s_icPoHeader%>";
		var emailPdf  = frm.print_option[2].checked;

		if (isEmpty(icPoHeader) || icPoHeader <= '0') {
			hideArea("ivc-linkToPO");
			frm.linkToPO.checked = false;
			frm.linkToPO.value = "N";
		} else if (emailPdf) {
			displayArea("ivc-linkToPO");
		} else {
			hideArea("ivc-linkToPO");
		}
	}	
// end hiding contents -->
</SCRIPT>

</BODY>
</HTML>