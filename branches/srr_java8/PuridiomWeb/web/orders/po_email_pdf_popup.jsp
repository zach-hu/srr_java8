<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>
<%@ page import="java.math.BigDecimal" %>
<%
	PoHeader poHeader = (PoHeader) request.getAttribute("poHeader");
	BigDecimal	bd_ic_po_header = poHeader.getIcPoHeader();
	BigDecimal bd_release_number = poHeader.getReleaseNumber();
	BigDecimal bd_revision_number = poHeader.getRevisionNumber();
	BigDecimal	bd_zero = new BigDecimal(0);
	String	s_po_number = poHeader.getPoNumber();
	String	s_po_status = poHeader.getStatus();
	String	s_po_type = poHeader.getPoType();
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=bd_ic_po_header%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_po_type%>"/>
<tsa:hidden name="PoHeader_poNumber" value="<%=s_po_number%>"/>
<tsa:hidden name="PoHeader_vendContactCode" value="<%=poHeader.getVendContactCode()%>"/>
<tsa:hidden name="PoHeader_displayNumber" value="<%=poHeader.getDisplayPoNumber()%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="emailTo" value="Y" />
<tsa:hidden name="viewNow" value="N"/>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
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
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "order", "Order")%></div>
					</td>
				</tr>
				</table>
			</td>
			<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
			<td valign=bottom align=right height=30px>
		    <%@ include file="/orders/po_display_number.jsp" %>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td><br></td></tr>
<tr><td><br></td></tr>
<tr>
	<td width=100% align=center valign=top>
		<table border=0 cellpadding=2 cellspacing=0>
		<tr>
			<td width="15px" align="center"><input type="checkbox" name="print_option_supplier" value="es" onclick="setEmailOptions();"/></td>
			<td nowrap>Email To <a href="javascript: browseSupplier(); void(0);" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-supplier", "Supplier")%></a></td>
			<td><input type="text" name="Pdf_PoHeader_vendorId" value="<%=poHeader.getVendorId()%>" size="30"/></td>
		</tr>
		<tr>
			<td width="15px" align="center">&nbsp;</td>
			<td>&nbsp;</td>
			<td><input type="text" name="PoHeader_vendorName" value="<%=poHeader.getVendorName()%>" size="60" style="border: none" /></td>
		</tr>
		<tr>
			<td width="15px" align="center"><input type="checkbox" name="print_option_email" value="eu" onclick="setEmailOptions();"/></td>
			<td colspan=2 nowrap>Email To&nbsp;<input type="text" name="email" value="" onfocus="selectEmailTo();" / size=60 onChange="lowerCase(this); trim(this);"></td>
		</tr>
		<tr><td colspan=3><br></td></tr>
		<tr>
			<td width="15px" align="center"><input type="checkbox" name="TCs" value="Y" onclick="setEmailOptions();"/></td>
			<td colspan=3 nowrap><b>Send Terms & Conditions</b></td>
		</tr>
		</table>
	</td>
</tr>
</table>
<br>
<br>
<br>
<br>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tr>
	<td align="center" width=50%><div id="pxbutton"><a href="javascript: emailPdf();">Submit</a></div></td>
	<td align="center" width=50%><div id="pxbutton"><a href="javascript: window.top.hidePopWin();">Close</a></div></td>
</tr>
</table>
<br>
<br>
<br>
<%@ include file="/system/footer.jsp" %>

<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	function emailPdf() {
		if (checkemail(frm.email)) {
			doSubmit('system/close_window.jsp', 'EmailPoPdf');
		}
	}
	

	function selectEmailTo()
	{
		frm.print_option_email.checked = true;
	}

	function setEmailOptions()
	{
		if(frm.print_option_supplier.checked || frm.print_option_email.checked)
		{
			frm.viewNow.value = "N";
		}
	}
	function pdfOptions()
	{
		popupParameters = "PoHeader_icPoHeader=<%=bd_ic_po_header%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.TCs.checked)
		{
			popupParameters = popupParameters + ";TCs=Y";
		}
		if(frm.print_option_supplier.checked)
		{
			frm.viewNow.value = 'N';
			frm.emailTo.value = 'N';
			doSubmit('orders/po_review.jsp', 'EmailPoPdf;PoRetrieve');
		}
		else if(frm.print_option_email.checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('orders/po_review.jsp', 'EmailPoPdf;PoRetrieve');
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
		if (frm.viewType.value == "CLASSIC")
		{
			doSubmit('orders/po_summary.jsp', 'PoRetrieve');
		}
		else
		{
			doSubmit('orders/po_review.jsp', 'PoRetrieve');
		}
	}

	function browseSupplier()
	{
		frm.print_option_supplier.checked = true;
		setEmailOptions();
		browseLookup('Pdf_PoHeader_vendorId', 'vendor');
	}

// end hiding contents -->
</SCRIPT>
