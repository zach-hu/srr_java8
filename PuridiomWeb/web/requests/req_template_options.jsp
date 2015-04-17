<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%
	RequisitionHeader reqHeader = (RequisitionHeader) request.getAttribute("requisitionHeader");
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String hidePublicOption = propertiesManager.getProperty("REQ OPTIONS", "HIDE PUBLIC TEMPLATE", "N");
%>

<tsa:hidden name="RequisitionHeader_icReqHeader" value="<%=reqHeader.getIcReqHeader()%>"/>
<tsa:hidden name="RequisitionHeader_rqSubType" value="R"/>

<table width="<%=formWidth%>" cellpadding="0" cellspacing="0" border="0">
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left: 10px; margin-right: 10px" class="hdr12"><tsa:label labelName="templateOptions" defaultString="Template Options"></tsa:label></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class="hdr12" src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
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

<br>

<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td align="center">
		<table border="0" cellpadding="2" cellspacing="2">
		<tsa:tr>
			<tsa:td>&nbsp;</tsa:td>
			<% if(!hidePublicOption.equalsIgnoreCase("Y")) { %>
			<tsa:td><tsa:input type="radio" value="R" name="securityType" checked="checked" onclick="setSecurity();"></tsa:input><tsa:label labelName="private" defaultString="Private"></tsa:label></tsa:td>
			<tsa:td><tsa:input type="radio" value="U" name="securityType" onclick="setSecurity();"></tsa:input><tsa:label labelName="public" defaultString="Public"></tsa:label></tsa:td>
			<% } %>
		</tsa:tr>
		<% if(!hidePublicOption.equalsIgnoreCase("Y")) { %>
		<tsa:tr>
			<tsa:td>&nbsp;</tsa:td>
			<tsa:td colspan="2" cssClass="red"><i>* <tsa:label labelName="note" defaultString="Note"></tsa:label>: <tsa:label labelName="selectingPublicTemplateVisible" defaultString="Selecting Public will make this template visible to all users"></tsa:label>.</i></tsa:td>
		</tsa:tr>
		<% } %>
		<tsa:tr>
			<tsa:td align="right"><tsa:label labelName="template" defaultString="Template"></tsa:label> #:</tsa:td>
			<tsa:td colspan="2"><tsa:input type="text" name="templateNumber" value="" onchange="upperCase(this); alphaNumericCheck(this);"></tsa:input></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td align="right" valign="top"><tsa:label labelName="description" defaultString="Description"></tsa:label>:</tsa:td>
			<tsa:td colspan="2"><tsa:input type="textarea" name="RequisitionHeader_internalComments" tabIndex="2"  rows="6" cols="60" wrap="virtual" maxLength="255" onkeydown="textCounter(this, 255);" onkeyup="textCounter(this,255);" onchange="textCounter(this,255); upperCase(this);">${esapi:encodeForHTML(reqHeader.internalComments)}</tsa:input></tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
</tsa:tr>
</table>

<br>

<%
	String returnPage = "/requests/req_review.jsp";
	if (s_view.equalsIgnoreCase("CLASSIC"))
	{
		returnPage = "/requests/req_summary.jsp";
	}
%>
<table border="0" cellpadding="0" cellspacing="0" width="680px">
<tsa:tr>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('/menu/main_menu.jsp', 'RequisitionSaveasTemplate'); void(0);">Save</a></div></tsa:td>
	<tsa:td width="50%" align="center"><div id="pxbutton"><a href="javascript: doSubmit('<%=returnPage%>', 'RequisitionRetrieve'); void(0);">Return</a></div></tsa:td>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value="JavaScript">
<!-- Hide script

	frm = document.purchaseform;

	function setSecurity()
	{
		if (frm.securityType[1].checked == true)
		{
			frm.RequisitionHeader_rqSubType.value = "U";	//PUBLIC
		}
		else
		{
			frm.RequisitionHeader_rqSubType.value = "R";	//PRIVATE
		}
	}

	function alphaNumericCheck(x)
	{
		x.value=x.value.toUpperCase().replace(/([^0-9A-Z . -])/g,"").trim();
	}

// End Hide script -->
</SCRIPT>