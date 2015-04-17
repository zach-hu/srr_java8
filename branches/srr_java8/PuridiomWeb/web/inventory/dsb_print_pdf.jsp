<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DisbursementType" %>

<%
	String icDsbHeader = (String)request.getAttribute("DisbHeader_icDsbHeader");
	String s_form_number = (String)request.getAttribute("DisbHeader_disbNumber");
	String s_disbType = (String)request.getAttribute("DisbHeader_disbType");
//	String s_contact_code = (String)request.getAttribute("DisbLine_icDsbHeader");

%>
<tsa:hidden name="DisbHeader_icDsbHeader" value="<%=icDsbHeader%>"/>
<tsa:hidden name="DisbHeader_disbType" value="<%=s_disbType%>"/>
<tsa:hidden name="DisbHeader_disbNumber" value="<%=s_form_number%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="emailTo" value="N" />
<tsa:hidden name="dontPrintCancelledItems" value="N" />

<table border="0" cellpadding="0" cellspacing="0" width="505px">
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
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "disbursment", "Disbursment")%></div>
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

<% 							if(oid.equalsIgnoreCase("VSE06P")) {%>
		<span class="formType"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "disbursment", "Disbursment")%> </span><span class="hdr12">#<%=s_form_number%></span>
<% } else { %>
		<span class="formType"><%=DisbursementType.toString(s_disbType, oid)%> </span><span class="hdr12">#<%=s_form_number%></span>
<%} %>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<table border="1" cellpadding="0" cellspacing="0" width="445px">
			<tr class="mnav" height="18px">
				<td class="mnav" nowrap colspan="2">&nbsp;&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "printoptions", "Print Options")%>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table border=0>
						<tr>
							<td width="15px" align="center" id="viewpdf"><input type="radio" name="print_option" value="pdf" checked />
							<tsa:hidden name="viewNow" value="Y" /></td>
							<td nowrap id="viewpdf" colspan="2">View Pdf</td>
						</tr>
						<tr>
							<td width="15px" align="center" id="emailto"><input type="radio" name="print_option" value="eu" /></td>
							<td nowrap id="emailto" align="right">Email To&nbsp;</td>
							<td nowrap><input type="text" name="email" value="" onfocus="selectEmailTo();" /></td>
						</tr>
						<tr>
							<td width="15px" align="center">&nbsp;</td>
							<td nowrap valign="top" align="right"><label for="notes">Notes</label>&nbsp;</td>
							<td nowrap><textarea name="notes" cols="55" rows="4" id="notes" onfocus="selectEmailTo();"></textarea></td>
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
	<TR>
		<td align="center"><a href="javascript: pdfOptions();"><img class="button" src="<%=contextPath%>/images/button_print.gif" border="0" alt="Print" ></a></td>
		<TD align="CENTER"><a href="javascript: doSubmit('inventory/dsb_review.jsp', 'DoNothing;DisbursementRetrieve');"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0></a></TD>
	</TR>
</TABLE>
<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	function selectEmailTo()
	{
		frm.print_option[1].checked = true;
	}

	function pdfOptions()
	{
		popupParameters = "DisbHeader_icDsbHeader=<%=icDsbHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.print_option[0].checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			<%if(oid.equalsIgnoreCase("VSE06P"))
			{%>
				popupParameters = popupParameters + ";dontPrintCancelledItems=Y";
			<%}%>
			doSubmitToLookup('', 'PrintPdfDsb', 'width=775px', 'height=850px');
		}
		else if(frm.print_option[1].checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				<%if(oid.equalsIgnoreCase("VSE06P"))
				{%>
					frm.dontPrintCancelledItems.value = 'Y';
				<%}%>
				doSubmit('inventory/dsb_review.jsp', 'EmailDsbPdf;DisbursementRetrieve');
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

// end hiding contents -->
</SCRIPT>
