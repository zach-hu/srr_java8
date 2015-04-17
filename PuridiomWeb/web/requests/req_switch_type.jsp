<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.property.PropertiesManager" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<%pageContext.setAttribute("oid", oid); %>
<%pageContext.setAttribute("language", language); %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String	s_reqType = (String) request.getAttribute("reqType");
%>

<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" ALIGN="CENTER" VALIGN="BOTTOM" CLASS="basic" width="100%">
	<tsa:tr>
		<tsa:td colspan="2">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td colspan="2" width="100%" cssClass="browseHdr" height="18px" align="center"><tsa:label labelName="changeRequestType" defaultString="Change Request Type"></tsa:label></tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td colspan="2">&nbsp;</tsa:td>
	</tsa:tr>
	<% if (s_reqType.equals("N")||s_reqType.equals("E")||s_reqType.equals("S")) {%>
	<tsa:tr>
		<tsa:td colspan="2" width="100%" align="center"><tsa:input type="radio" name="newReqType" value="P"></tsa:input><tsa:label labelName="reqtype-p" defaultString="Purchase Request"></tsa:label></tsa:td>
	</tsa:tr>
	<% } if (s_reqType.equals("P")||s_reqType.equals("E")||s_reqType.equals("S")) {%>
	<tsa:tr>
		<tsa:td colspan="2" width="100%" align="center"><tsa:input type="radio" name="newReqType" value="N"></tsa:input><tsa:label labelName="reqtype-n" defaultString="Pricing Requisition"></tsa:label></tsa:td>
	</tsa:tr>
	<% } if (s_reqType.equals("P") && propertiesManager.getProperty("REQ OPTIONS", "INVENTORY REQUEST", "Y").equals("Y"))  {%>
	<tsa:tr>
		<tsa:td colspan="2" width="100%" align="center"><tsa:input type="radio" name="newReqType" value="S"></tsa:input><tsa:label labelName="reqtype-s" defaultString="Inventory Request"></tsa:label></tsa:td>
	</tsa:tr>
	<% } %>
	<tsa:tr>
		<tsa:td colspan="2">&nbsp;</tsa:td>
	</tsa:tr>
	<tsa:tr>
		<tsa:td align="CENTER"><A HREF="javascript: convertToOtherType(); void(0)"><IMG class="button" src="<%=contextPath%>/images/button_ok.gif" TABINDEX="4" BORDER="0" ALT="Convert"></A></tsa:td>
		<tsa:td align="CENTER"><A HREF="javascript: window.top.hidePopWin(); void(0)"><IMG class="button" src="<%=contextPath%>/images/button_cancel.gif" TABINDEX="4" BORDER="0" ALT="Cancel"></A></tsa:td>
	</tsa:tr>
</TABLE>
</FORM>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	thisfrm = document.purchaseform;
	if(thisfrm.newReqType.length>0)
	{
		thisfrm.newReqType[0].checked='checked';
		window.parent.frm.newReqType.value = thisfrm.newReqType[0].value;
	}
	else
	{
		thisfrm.newReqType.checked='checked';
		window.parent.frm.newReqType.value = thisfrm.newReqType.value;
	}
	self.focus();

	function convertToOtherType()
	{
		var i;
	    for (i=0;i<thisfrm.newReqType.length;i++)
	    {
			if (thisfrm.newReqType[i].checked)
			{
				window.parent.frm.newReqType.value = thisfrm.newReqType[i].value;
				break;
			}
		}
		window.parent.doSubmit('/requests/req_review.jsp', 'ReqChangeType;RequisitionRetrieve');

		window.top.hidePopWin();
		return false;
	}

// end hiding contents -->
</SCRIPT>

</body>
</html>