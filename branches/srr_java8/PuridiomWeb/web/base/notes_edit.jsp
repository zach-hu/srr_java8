<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ taglib uri="/WEB-INF/esapi.tld" prefix="esapi"%>
<%
	String cmtTitle = (String) request.getAttribute("cmtTitle");
	String cmtText = (String) request.getAttribute("cmtText");
	String cmtRow = (String) request.getAttribute("cmtRow");
	String cmtTotal = (String) request.getAttribute("cmtTotal");
	String cmtPublic = (String) request.getAttribute("cmtPublic");
	String allowEdit = (String) request.getAttribute("allowEdit");
	
	if (cmtTitle == null)		{	cmtTitle = "";		}
	if (cmtText == null)		{	cmtText = "";		}
	if (cmtPublic == null)	{	cmtPublic = "";		}
	if (allowEdit == null)		{	allowEdit = "";		}
%>

<table width=500px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Add/Edit Comment</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td width=100% height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
		</tr>
		<tr>
			<td height=2px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="2" /></td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=480px>
<tr>
	<td valign=top>
		<table border=0 cellspacing=0 cellpadding=0 height=100%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center>
				<tr>
					<td width=120px align=right nowrap>Title:&nbsp;</td>
					<td><input type="text" name="DocComment_commentTitle" value="<%=headerEncoder.encodeForHTMLAttribute(cmtTitle)%>"></td>
				</tr>
				<tr>
					<td width=120px valign=top align=right nowrap>Comment Text:&nbsp;</td>
					<td><textarea name="DocText_stdText" cols=40 rows=5>${esapi:encodeForHTML(cmtText)}</textarea></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=480px>
<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveText(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: window.top.hidePopWin(); void(0);">Close</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	var openerRowTotal = <%=headerEncoder.encodeForJavaScript(cmtTotal)%>;
	thisLoad();
	
	function saveText()
	{
		if (openerRowTotal > 1)
		{
			window.parent.frm.DocComment_commentTitle[<%=headerEncoder.encodeForJavaScript(cmtRow)%>].value = frm.DocComment_commentTitle.value;	
			window.parent.frm.DocText_stdText[<%=headerEncoder.encodeForJavaScript(cmtRow)%>].value = frm.DocText_stdText.value;
			window.parent.frm.icotext[<%=headerEncoder.encodeForJavaScript(cmtRow)%>].src = "<%=contextPath%>/images/ico_comment_on.gif";
			window.top.hidePopWin();
		}
		else
		{
			window.parent.frm.DocComment_commentTitle.value = frm.DocComment_commentTitle.value;
			window.parent.frm.DocText_stdText.value = frm.DocText_stdText.value;
			window.parent.frm.icotext.src = "<%=contextPath%>/images/ico_comment_on.gif";
			window.top.hidePopWin();
		}
	}
	
	function thisLoad()
	{
<%	if (cmtPublic.equalsIgnoreCase("N") || allowEdit.equalsIgnoreCase("FALSE")) { %>
			var e = frm.elements;
		
			for (i = 0; i < e.length; i++)
			{
				e.item(i).readOnly = true;
				e.item(i).contentEditable = false;
				if (e.item(i).type != "hidden")
				{
					e.item(i).disabled = true;
				}
			}
<%	} %>
	}

// End Hide script -->
</SCRIPT>