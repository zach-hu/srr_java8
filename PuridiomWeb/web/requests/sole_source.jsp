<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib uri="/WEB-INF/tsa.tld" prefix="tsa"%>
<% pageContext.setAttribute("oid", oid);
   pageContext.setAttribute("language", language);%>
<%
	DocComment docComment = (DocComment) request.getAttribute("docComment");
	DocText docText = (DocText) request.getAttribute("docText");
	String icHeader = (String) request.getAttribute("DocComment_icHeader");
	String cmtOrder = (String) request.getAttribute("DocComment_commentOrder");
	boolean isNew = false;

	if (docComment == null)
	{
		docComment = new DocComment();
		isNew = true;
	}
	else
	{
		DocCommentPK docCommentPK = (DocCommentPK) docComment.getComp_id();
		BigDecimal bdCmtOrder = docCommentPK.getCommentOrder();
		cmtOrder = bdCmtOrder.toString();
	}
	if (docText == null)
	{
		docText = new DocText();
	}
	String	stdText = docText.getStdText();
	stdText = stdText.replace('"', '~');
	stdText = stdText.replaceAll("~", "&#34;");
%>

<tsa:hidden name="DocComment_icHeader" value="<%=icHeader%>"/>
<tsa:hidden name="DocComment_icLine" value="0"/>
<tsa:hidden name="DocComment_commentOrder" value="<%=cmtOrder%>"/>
<tsa:hidden name="DocComment_commentId" value="SOLESOURCE"/>
<tsa:hidden name="DocComment_commentTitle" value="SOLE SOURCE JUSTIFICATION"/>
<tsa:hidden name="DocComment_commentPrint" value="Y"/>
<tsa:hidden name="DocComment_commentBold" value="N"/>
<tsa:hidden name="DocComment_commentPublic" value="Y"/>
<tsa:hidden name="DocComment_referenceType" value="RQH"/>
<tsa:hidden name="Default_referenceType" value="RQH"/>

<table width=500px cellpadding=0 cellspacing=0 border=0>
<tsa:tr>
	<tsa:td valign="top" width="135px" height="30px">
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tsa:tr>
			<tsa:td height="1px" cssClass="darkShadow"><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></tsa:td>
		</tsa:tr>
		<tsa:tr>
			<tsa:td noWrap="nowrap" cssClass="hdr12" valign="middle">
				<div style="margin-left:10px; margin-right:10px" class=hdr12><tsa:label labelName="soloSouceJustification" defaultString="Sole Source Justification"/></div>
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

<table border=0 cellpadding=2 cellspacing=2 width=480px>
<tsa:tr>
	<tsa:td><img src="<%=contextPath%>/images/alert.gif"></tsa:td>
	<tsa:td valign="top" cssClass="red">
		<b>*<tsa:label labelName="note" defaultString="Note"/>:</b> <tsa:label labelName="soloSourceNote" defaultString="All Supplier engagements exceeding $25,000 should be competitively bid.  Officer approval(Vice President and above) is required for all sole source and direct award engagements. You MUST provide justification to support your recommendation"/>.
	</tsa:td>
</tsa:tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=480px>
<tsa:tr>
	<tsa:td valign="top">
		<table border=0 cellspacing=0 cellpadding=0 height=100 width="100%">
		<tsa:tr>
			<tsa:td align="center">
				<table border=0 cellpadding=0 cellspacing=0 align=center>
				<tsa:tr>
					<tsa:td>
						<tsa:input type="textarea" name="DocText_stdText" cols="60" rows="9">${esapi:encodeForHTML(stdText)}</tsa:input>
					</tsa:td>
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
	<tsa:td width="50%" align="center"><a href="javascript: saveText(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0 alt="Save"></a></tsa:td>
	<tsa:td width="50%" align="center"><a href="javascript: window.top.hidePopWin(); void(0);"><img class=button src="<%=contextPath%>/images/button_close.gif" border=0 alt="Close Window"></a></tsa:td>
</tsa:tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function saveText()
	{
		var soleSource = trim(frm.DocText_stdText);
		if (soleSource.length < 1)
		{
			alert("Sole soure justification is required!");
			return false;
		}
<%	if (isNew) {	%>
			doSubmit("/system/close_window.jsp", "DocCommentAddSingle");
<%	} else {	%>
			doSubmit("/system/close_window.jsp", "DocCommentUpdateById");
<%	}	%>
	}

// End Hide script -->
</SCRIPT>