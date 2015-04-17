<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<tsa:hidden name="UserPreference_userId" value="${esapi:encodeForHTMLAttribute(userId)}"/>
<% String userPreferenceProperty = "COLOR";%>
<tsa:hidden name="UserPreference_property" value="${esapi:encodeForHTMLAttribute(userPreferenceProperty)}"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=Middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>My Color Scheme</td>
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
<table border=0 cellspacing=0 cellpadding=0 width=680px>
<tr>
<td>
	<table align=left>
	<tr>
		<td align=left valign=top nowrap>
			Please Choose a Color Scheme:
		</td>
		<td valign=top>
			<select name="UserPreference_value" onChange="changeImage()">
			<option <% if (colorscheme.equals("default")) {%> SELECTED <%}%>value="default">[None Selected]</option>
			<option <% if (colorscheme.equals("green")) {%> SELECTED <%}%>value="green">Green</option>
			<option <% if (colorscheme.equals("orange")) {%> SELECTED <%}%>value="orange">Orange</option>
			<option <% if (colorscheme.equals("purple")) {%> SELECTED <%}%>value="purple">Purple</option>
			<option <% if (colorscheme.equals("red")) {%> SELECTED <%}%>value="red">Red</option>
			<option <% if (colorscheme.equals("blue")) {%> SELECTED <%}%>value="blue">Blue</option>
			</select>
		</td>
		<td width=30px>
		</td>
		<td>
		<img id=previewImage src = "<%=contextPath%>/images/ps_orange.GIF" width="364px" height="240px">
		</td>
	</tr>
	</table>
</td>
</tr>
</table>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<% if (oid.equalsIgnoreCase("vse06p")) { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('user/user_profile.jsp', 'UserProfileValidateRetrieve;UserPreferenceUpdate'); void(0);">Save</a></div></td>
	<% } else { %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: submitThis('user/user_profile.jsp', 'UserPreferenceUpdate'); void(0);">Save</a></div></td>
	<% } %>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('user/user_profile.jsp', 'DoNothing'); void(0);">Cancel</a></div></td>
</tr>
</table>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script
	frm = document.purchaseform;
	var purple = "<%=contextPath%>/images/ps_purple.gif";
	var green = "<%=contextPath%>/images/ps_green.GIF";
	var orange = "<%=contextPath%>/images/ps_orange.gif";
	var red = "<%=contextPath%>/images/ps_red.gif";
	var blue = "<%=contextPath%>/images/ps_purple.gif";

	changeImage();

	function changeImage()
	{
		var imageChoice=frm.UserPreference_value.options[frm.UserPreference_value.selectedIndex].value;
		if (imageChoice == "default") {
			imageChoice = "<%=PropertiesManager.getInstance(oid).getProperty("MISC","COLORSCHEMEDEFAULT","orange")%>";
		}
		frm.previewImage.src=eval(imageChoice);
	}

	function submitThis(page, handlers)
	{
		var imageChoice = frm.UserPreference_value.options[frm.UserPreference_value.selectedIndex].value;
		if (imageChoice == "default") {
			frm.UserPreference_value.value = "<%=PropertiesManager.getInstance(oid).getProperty("MISC","COLORSCHEMEDEFAULT","orange")%>";
		}
		doSubmit(page, handlers);
	}
// End Hide script -->
</SCRIPT>