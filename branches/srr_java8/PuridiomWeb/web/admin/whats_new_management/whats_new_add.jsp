<%@page language="java" errorPage="/system/error.jsp" %>
<%@include file="/system/prevent_caching.jsp" %>
<%@include file="/system/context_path.jsp" %>
<%@include file="/system/header.jsp"%>

<tsa:hidden name="fromPage" value="/admin/whats_new_management/whats_new_setup.jsp"/>
<tsa:hidden name="currentPage" value="/admin/whats_new_management/admin_menu.jsp"/>

<br>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Whats New Management</div>
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
<table border=0 cellspacing=0 cellpadding=0 width="680px">
<tr>
	<td width=100%>
		<table border=0 cellspacing=0 cellpadding=1>
			<tr>
				<td>
					<table border=0 cellpadding=1 cellspacing=0>
					<tr>
						<td width="15%" align=right><b><label for="news_text">Text:</label><b></td>
						<td width="85%"><input type=text name="News_newsText" id="news_text" value="" tabindex=2 size=100 maxlength=150></td>
					</tr>
					<tr>
						<td width="15%" align=right><b><label for="news_font">Font:</label><b></td>
						<td width="85%">
							<select name="News_newsFont" id="news_font" tabIndex=4>
								<option value="browseHdr">browseHdr</option>
								<option value="error">error</option>
								<option value="menuDate">menuDate</option>
								<option value="processOff">processOff</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="15%" align=right><b><label for="news_link">Link:</label><b></td>
						<td width="85%"><input type=text name="News_newsLink" id="news_link" value="" tabindex=6 size=100></td>
					</tr>
					<tr>
						<td width="15%" align=right><b><label for="news_image">Image:</label><b></td>
						<td width="85%">
							<select name="News_newsImage" id="news_image" tabIndex=8>
								<option value="bullet_red_onwhite.gif">bullet_red_onwhite.gif</option>
								<option value="news.gif">news.gif</option>
								<option value="none.gif">none.gif</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="15%" align=right><b><label for="news_altTag">Alt Tag:</label><b></td>
						<td width="85%"><input type=text name="News_newsAltTag" id="news_altTag" value="" tabindex=10 size=100 maxlength=150></td>
					</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>

<br><br>
<table border=0 cellpadding=0 cellspacing=0 width="680px" style="clear: both;">
  <tr>
   	<td width=33% align=center>
    	<a href="javascript: saveNews(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
    <td width=33% align=center>
    	<a href="javascript: returnToAdminMenu(); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0></a></td>
  </tr>
</table>
<script value=JavaScript>
  frm = document.purchaseform;

  function saveNews() {
	doSubmit('/admin/whats_new_management/whats_new_setup.jsp','NewsAdd');

  }

  function returnToAdminMenu(){
    doSubmit('/admin/whats_new_management/whats_new_setup.jsp','DoNothing');
  }

</script>

<%@ include file="/system/footer.jsp"%>
