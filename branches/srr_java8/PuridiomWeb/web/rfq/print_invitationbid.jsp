<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.RfqType" %>
<%@ page import="com.tsa.puridiom.vendor.VendorManager" %>
<%@ page import="org.owasp.esapi.Encoder" %>
<%@ page import="org.owasp.esapi.reference.DefaultEncoder" %>

<%
	String icRfqHeader = (String)request.getAttribute("RfqHeader_icRfqHeader");
	String s_form_number = (String)request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqType = (String)request.getAttribute("RfqHeader_rfqType");
	String printtype = (String)request.getAttribute("printtype");
	List vendorList = (List)request.getAttribute("rfqVendorList");
	if(HiltonUtility.isEmpty(printtype))
	{
		printtype = "Ita";
	}
%>
<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqType" value="${RfqHeader_rfqType}"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_form_number%>"/>
<tsa:hidden name="allowBrowse" value="true"/>
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
						<div style="margin-left:10px; margin-right:10px" class="hdr12"><tsa:label labelName="solicitation" defaultString="Solicitation" /></div>
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
		<% Encoder encoder = DefaultEncoder.getInstance(); %>
		<font class="formType"><%=encoder.encodeForHTML(RfqType.toString(s_rfqType, oid))%> </font><font class="hdr12">#<%=s_form_number%></font>
	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td width="100%" align="center" valign="top">
		<table border="0" cellpadding="0" cellspacing="0" width="445px">
			<tr class="mnav" height="18px">
				<td class="mnav" nowrap colspan="2">&nbsp;&nbsp;<tsa:label labelName="intenttoawardtitle" defaultString="Select The Supplier you intend to Award to" />&nbsp;</td>
			</tr>
			<%for(int vendorIndex = 0; vendorIndex < vendorList.size(); vendorIndex++)
			{
				RfqVendor rfqVendor = (RfqVendor)vendorList.get(vendorIndex);%>
				<tr>
					<td>
						<table border=0>
							<tr>
								<td width="15px" align="center"><input type="radio" name="vendor" value="<%=vendorIndex %>" onclick="selectMe('<%= rfqVendor.getComp_id().getVendorId()%>', '<%=vendorIndex %>')"/>
								<tsa:hidden name="viewNow" value="Y" />
								</td>
								<td nowrap><%=VendorManager.getInstance().getVendorName(oid, rfqVendor.getComp_id().getVendorId()) %></td>
							</tr>
						</table>
					</td>
				</tr>
			<%} %>
			<tr>
				<td><tsa:hidden name="email" value="" />
				<tsa:hidden name="awardedVendor" value="" />
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
		<TD align="CENTER"><a href="javascript: doSubmit('rfq/rfq_review.jsp', 'DoNothing;RfqRetrieve');"><img class="button" src="<%=contextPath%>/images/button_return.gif" border=0></a></TD>
	</TR>
</TABLE>
<br/>
<%@ include file="/system/footer.jsp" %>
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	self.focus();

	function selectMe(vendorId, index)
	{
		<%if(vendorList.size() > 1)
		{%>
			if(frm.vendor[index].checked)
			{
				frm.awardedVendor.value = vendorId;
			}
		<%}
		else
		{%>
			frm.awardedVendor.value = vendorId;
		<%}%>
	}

	function pdfOptions()
	{
		popupParameters = "RfqHeader_icRfqHeader=<%=icRfqHeader%>";
		popupParameters = popupParameters + ";uid=${esapi:encodeForJavaScript(userId)}";
		popupParameters = popupParameters + ";oid=<%=oid%>";
		popupParameters = popupParameters + ";awardedVendor=" + frm.awardedVendor.value;
		popupParameters = popupParameters + ";viewNow=" + frm.viewNow.value;
		doSubmitToPopup('', 'Print<%=printtype%>Pdf', 'width=775px', 'height=850px');
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
