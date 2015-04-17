<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RequisitionType" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>

<%
	String icReqHeader = (String)request.getAttribute("RequisitionHeader_icReqHeader");
	String s_form_number = (String)request.getAttribute("RequisitionHeader_requisitionNumber");
	String s_reqType = (String)request.getAttribute("RequisitionHeader_requisitionType");
	String s_contact_code = (String)request.getAttribute("RequisitionHeader_vendAttn");
	String faxEnabled = PropertiesManager.getInstance(oid).getProperty("FAX", "ENABLED", "N");

	String s_vendorId = (String)request.getAttribute("RequisitionHeader_vendorId");
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
<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=icReqHeader%>"/>
<tsa:hidden name="RequisitionHeader_requisitionType" value="<%=s_reqType%>"/>
<tsa:hidden name="RequisitionHeader_requisitionNumber" value="<%=s_form_number%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="RequisitionHeader_vendAttn" value="<%=s_contact_code%>"/>
<tsa:hidden name="emailTo" value="N" />

<table border="0" cellpadding="0" cellspacing="0" width="505px">
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
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
						<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="requisition" defaultString="Requisition"></tsa:label></div>
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
		<span class="formType"><%=RequisitionType.toString(s_reqType, oid)%> </span><span class="hdr12">#<%=headerEncoder.encodeForHTML(s_form_number)%></span>
	</tsa:td>
</tsa:tr>
<tsa:tr><tsa:td><br></tsa:td></tsa:tr>
<tsa:tr>
	<tsa:td width="100%" align="center" valign="top">
		<table border="1" cellpadding="0" cellspacing="0" width="445px">
			<tsa:tr cssClass="mnav" height="18px">
				<tsa:td cssClass="mnav" noWrap="nowrap" colspan="2">&nbsp;&nbsp;<tsa:label labelName="printoptions" defaultString="Print/Email Options"></tsa:label>&nbsp;</tsa:td>
			</tsa:tr>
			<tsa:tr>
				<tsa:td>
					<table border="0">
						<tsa:tr>
							<tsa:td width="15px" align="center" id="viewpdf"><tsa:input type="radio" name="print_option" value="pdf" checked="checked" />
							<tsa:hidden name="viewNow" value="Y" /></tsa:td>
							<tsa:td noWrap="nowrap" id="viewpdf" colspan="2"><tsa:label labelName="viewPdf" defaultString="View PDF"></tsa:label></tsa:td>
						</tsa:tr>
						<tsa:tr>
							<tsa:td width="15px" align="center" id="emailto"><tsa:input type="radio" name="print_option" value="eu" /></tsa:td>
							<tsa:td noWrap="nowrap" id="emailto" align="right"><tsa:label labelName="emailTo" defaultString="Email To"></tsa:label>&nbsp;</tsa:td>
							<tsa:td noWrap="nowrap"><tsa:input type="text" name="email" value="<%=s_emailAddress%>" size="35" onfocus="selectEmailTo();" /></tsa:td>
						</tsa:tr>
					<%--if (faxEnabled.equalsIgnoreCase("Y")){%>
					    <tr>
							<td width="15px" align="center" id="faxto"><input type="radio" name="print_option" value="fu" /></td>
							<td nowrap id="faxto" align="right">Fax To&nbsp;</td>
							<td nowrap><input type="text" name="fax" value="<%=s_faxNumber%>" size="35" onfocus="selectFaxTo();" />&nbsp;i.e. 012.345.6789</td>
						</tr>
					<%}--%>
						<tsa:tr>
							<tsa:td width="15px" align="center">&nbsp;</tsa:td>
							<tsa:td noWrap="nowrap" valign="top" align="right"><label for="notes"><tsa:label labelName="notes" defaultString="Notes"></tsa:label></label>&nbsp;</tsa:td>
							<tsa:td noWrap="nowrap"><tsa:input type="textarea" name="notes" cols="55" rows="4" id="notes"></tsa:input></tsa:td>
						</tsa:tr>
					</table>
				</tsa:td>
			</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="455px">
	<tsa:tr>
		<tsa:td align="center"><div id="pxbutton"><a href="javascript: pdfOptions();"><tsa:label labelName="req-submit" defaultString="Submit"></tsa:label></a></div></tsa:td>
		<tsa:td align="center"><div id="pxbutton"><a href="javascript: doSubmit('requests/req_review.jsp', 'DoNothing;RequisitionRetrieve');"><tsa:label labelName="req-return" defaultString="Return"></tsa:label></a></div></tsa:td>
	</tsa:tr>
</table>
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

	function selectFaxTo()
	{
		frm.print_option[2].checked = true;
	}

	function pdfOptions()
	{
		popupParameters = "RequisitionHeader_icReqHeader=<%=icReqHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";

		if(frm.print_option[0].checked)
		{
			frm.emailTo.value = 'N';
			popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
			doSubmitToPopup('/system/popupDocAttachment.jsp', 'PrintPdf', 'width=775px', 'height=850px');
		}
		else if(frm.print_option[1].checked)
		{
			if(checkemail())
			{
				frm.viewNow.value = 'N';
				frm.emailTo.value = 'Y';
				doSubmit('requests/req_review.jsp', 'EmailReqPdf;RequisitionRetrieve');
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
		str = removeBlanks(str);
		frm.email.value=str;
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
		if (!filter.test(email))
		{
			alert("Please enter a valid email address! ");
			return false;
		}
		return true;
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
	function removeBlanks(str)
	{
		var emailArray = str.split(String.fromCharCode(32));
		str = "";
		for (x=0; x<emailArray.length; x++)
		{
			str+=emailArray[x];
		}
		return str;
	}

// end hiding contents -->
</SCRIPT>