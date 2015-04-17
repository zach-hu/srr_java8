<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.OrderType" %>

<%
	String icPoHeader = (String)request.getAttribute("PoHeader_icPoHeader");
	String s_form_number = (String)request.getAttribute("PoHeader_poNumber");
	String displayNumber = (String)request.getAttribute("PoHeader_displayNumber");
	String s_poType = (String)request.getAttribute("PoHeader_poType");
	String s_vendorId = (String)request.getAttribute("PoHeader_vendorId");
	String s_contact_code = (String)request.getAttribute("PoHeader_vendContactCode");
	
%>
<tsa:hidden name="PoHeader_icPoHeader" value="<%=icPoHeader%>"/>
<tsa:hidden name="PoHeader_poType" value="<%=s_poType%>"/>
<tsa:hidden name="PoHeader_displayNumber" value="<%=displayNumber%>"/>
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
					<td height="1px" class="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
				</tr>
				<tr>
					<td nowrap class="hdr12" valign="middle">
						<div style="margin-left:10px; margin-right:10px" class="hdr12">Order</div>
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
							<td width="15px" align="center"><input type="radio" name="print_option" value="pdf" checked />
							<tsa:hidden name="viewNow" value="Y" /></td>
							<td nowrap>View Pdf</td>
						</tr>
						<tr>
							<td width="15px" align="center"><input type="radio" name="print_option" value="es" /></td>
							<td nowrap>Email To <a href="javascript: browseLookup('Pdf_PoHeader_vendorId', 'vendor'); void(0);" ><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "po-supplier", "Supplier")%></a>&nbsp;<input type="text" name="Pdf_PoHeader_vendorId" value="<%=s_vendorId%>" /></td>
						</tr>
						<tr>
							<td width="15px" align="center">&nbsp;</td>
							<td nowrap><input type="checkbox" name="TCs" value="Y" />&nbsp;&nbsp;Send T & Cs</td>
						</tr>
						<tr>
							<td width="15px" align="center"><input type="radio" name="print_option" value="eu" /></td>
							<td nowrap>Email To&nbsp;<input type="text" name="email" value="" /></td>
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
		<td align="center"><div id="pxbutton"><a href="javascript: pdfOptions();">Print</a></div></td>
		<TD align="CENTER"><div id="pxbutton"><a href="javascript: doSubmit('orders/po_review.jsp', 'DoNothing;PoRetrieve');">Return</a></div></TD>	
	</TR>
</TABLE>
<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();
	
	function pdfOptions()
	{
		popupParameters = "PoHeader_icPoHeader=<%= headerEncoder.encodeForJavaScript(icPoHeader) %>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.TCs.checked)
		{
			popupParameters = popupParameters + ";TCs=Y";
		}
		if(frm.print_option[0].checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToPopup('', 'PoEmaiApprovalsl', 'width=775px', 'height=850px');
		}
		else if(frm.print_option[1].checked)
		{
			frm.viewNow.value = 'N';
			frm.emailTo.value = 'N';
			doSubmit('orders/po_review.jsp', 'PoEmaiApprovalsl;PoRetrieve');
		}
		else if(frm.print_option[2].checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('orders/po_review.jsp', 'PoEmaiApprovalsl;PoRetrieve');
			}
		}
	}
	
	function checkemail()
	{
		var str= frm.email.value;
		var filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i
		
		if (filter.test(str))
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

</BODY>
</HTML>