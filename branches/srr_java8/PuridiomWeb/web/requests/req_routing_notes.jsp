<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% pageContext.setAttribute("oid", oid);
   pageContext.setAttribute("language", language);%>
<%
	String notesType = HiltonUtility.ckNull((String) request.getAttribute("notesType"));
	String instructions = HiltonUtility.ckNull((String) request.getAttribute("instructions"));
%>

<table width=500px cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="routingNotes" defaultString="Routing Notes"/></div>
			</tsa:td>
		</tsa:tr>
		</table>
	</tsa:td>
	<tsa:td valign="bottom" width="3px"><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></tsa:td>
	<tsa:td valign="bottom" height="30px" width="*">
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
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
<br>

<table border=0 cellpadding=0 cellspacing=0 width=480px>
<tsa:tr>
	<tsa:td valign="top" align="center">
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tsa:tr><tsa:td><%=instructions%></tsa:td></tsa:tr>
		</table>
		<br>
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tsa:tr>
			<tsa:td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<tsa:tr>
					<tsa:td align="center"><tsa:input type="textarea" name="DocText_stdText" cols="70" rows="5"></tsa:input></tsa:td>
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

<table border=0 cellpadding=0 cellspacing=0 width=480px>
<tsa:tr>
	<tsa:td width="50%" align="center"><a href="javascript: saveText(); void(0);"><img class=button src="<%=contextPath%>/images/button_ok.gif" border=0 alt="Save"></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: window.top.hidePopWin(); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Close Window"></a></tsa:td>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	
	function saveText() {
		window.top.hidePopWin();
	}
	
// End Hide script -->
</SCRIPT>