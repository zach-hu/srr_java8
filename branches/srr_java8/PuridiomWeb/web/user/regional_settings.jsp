<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String returnPage = (String) request.getAttribute("returnPage");
	String s_language = user.getLanguage();
	String s_timeZone = user.getTimeZone();

	if (HiltonUtility.isEmpty(returnPage)) {
		returnPage = "menu/main_menu.jsp";
	}
%>

<tsa:hidden name="returnPage" value="<%=headerEncoder.encodeForHTMLAttribute(returnPage)%>"/>
<tsa:hidden name="reviewProfilePage" value="user/user_profile.jsp"/>
<tsa:hidden name="allowBrowse" value="true"/>
<tsa:hidden name="UserProfile_organizationId" value="<%=user.getOrganizationId()%>"/>
<tsa:hidden name="UserProfile_mailId" value="<%=user.getMailId()%>"/>
<tsa:hidden name="UserProfile_userId" value="<%=user.getUserId()%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "regionalSettings", "Regional Settings", true)%></div>
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
<table border=0	cellspacing=0 cellpadding=2 width=680px>
<tr>	<td colspan=2><br></td></tr>
		<tr>
			<%
			String showCountryBrowse=propertiesManager.getProperty("REGIONAL SETTINGS", "SHOWCOUNTRYBROWSE","N");
			if (showCountryBrowse.equalsIgnoreCase("Y")) {%>
			<td align=right nowrap >
			<%}else{ %>
			<td align=right nowrap <%=HtmlWriter.isVisible(oid, "user-locale")%>>
			<%} %>
<%	if (DictionaryManager.isLink(oid, "user-locale")) { %>
				<a href="javascript: browseLookup('UserProfile_locale', 'country'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-locale", "Location Code", true)%>:</a>
<%	} else { %>
				<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-locale", "Location Code", true)%>:
<%	} %>
			</td>
			<%if (showCountryBrowse.equalsIgnoreCase("Y")) {%>
			<td><input type=text name="UserProfile_locale" value="<%=user.getLocale()%>" tabindex=10 size=35 maxLength=15 onchange="upperCase(this)"></td>
			<%}else{ %>
			<td <%=HtmlWriter.isVisible(oid, "user-locale")%>><input type=text name="UserProfile_locale" value="<%=user.getLocale()%>" tabindex=10 size=35 maxLength=15 onchange="upperCase(this)"></td>
			<%} %>
		</tr>
<!--tr <%=HtmlWriter.isVisible(oid, "user-language")%>>
			<td align=right <%=HtmlWriter.isVisible(oid, "user-language")%> nowrap><a href="javascript: browseStd('UserProfile_language', 'LANG'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-language", "Language", true)%>:</a>:</td>
			<td <%=HtmlWriter.isVisible(oid, "user-language")%>><input type=text name="UserProfile_language" value="<%=s_language%>" tabIndex=11>
			</td>
        </tr-->
<tr <%=HtmlWriter.isVisible(oid, "user-currency")%>>
          <!-- <td align=right <%=HtmlWriter.isVisible(oid, "user-currency")%> nowrap><a href="javascript: browseLookup('UserProfile_currencyCode', 'curr_code'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-currency", "Currency", true, true, "UserProfile_currencyCode")%></a>:</td> -->
          <td align=right <%=HtmlWriter.isVisible(oid, "user-currency")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-currency", "Currency", false, true, "UserProfile_currencyCode")%>:</td>
          <td <%=HtmlWriter.isVisible(oid, "user-currency")%>><input type=text name="UserProfile_currencyCode" value="<%=user.getCurrencyCode()%>" tabindex=12 size=35 maxlength=15 onchange="upperCase(this);"></td>
        </tr>
        <tr <%=HtmlWriter.isVisible(oid, "user-timezone")%>>
			<!-- <td align=right <%=HtmlWriter.isVisible(oid, "user-timezone")%> nowrap><a href="javascript: browseStd('UserProfile_timeZone', 'TMZN'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-timezone", "Time Zone", true, true, "UserProfile_timeZone")%></a>:</td> -->
			<td align=right <%=HtmlWriter.isVisible(oid, "user-timezone")%> nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-timezone", "Time Zone", false, true, "UserProfile_timeZone")%>:</td>
			<td <%=HtmlWriter.isVisible(oid, "user-timezone")%>><input type=text name="UserProfile_timeZone" value="<%=user.getTimeZone()%>" tabindex=13 size=35 maxlength=4></td>
        </tr>
        <!--tr <%=HtmlWriter.isVisible(oid, "user-dateformat")%>>
          <td align=right <%=HtmlWriter.isVisible(oid, "user-dateformat")%> nowrap><a href="javascript: browseStd('UserProfile_dateFormat', 'DFMT'); void(0);"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user-dateformat", "Date Format", true, true, "UserProfile_dateFormat")%></a>:</td>
          <td <%=HtmlWriter.isVisible(oid, "user-dateformat")%>><input type=text name="UserProfile_dateFormat" value="<%=user.getDateFormat()%>" tabindex=14 size=35 maxlength=15 onchange="correctDateFormat(this);"></td>
        </tr-->
		<tr><td colspan=2><br><br></td></tr>
		<tr>
			<td align=center colspan=2>
				<table border=0 width="600">
				<tr>
					<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForJavaScript(returnPage)%>', 'UserProfileUpdate'); void(0);" tabindex="3">Save</a></div></td>
					<td width="50%" align=center><div id="pxbutton"><a href="javascript: doSubmit('<%=headerEncoder.encodeForJavaScript(returnPage)%>', 'DoNothing'); void(0);" border=0 tabindex="4">Cancel</a></div></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr><td colspan=2><br><tsa:hidden name="temp" value=""/></td></tr>
</table>
<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	function correctDateFormat(fld) {
		var dateFormat = fld.value;
		if (!isEmpty(dateFormat) && (dateFormat.length == 10 || dateFormat.length == 8)) {
			dateFormat = replaceChars(dateFormat, "Y", "y");
			dateFormat = replaceChars(dateFormat, "D", "d");
			dateFormat = replaceChars(dateFormat, "m", "M");
		}
		fld.value = dateFormat;
	}
	function browseSetup(browseName, browseType, fieldName) {
	    if (browseType.length > 0){
		    browseStd(fieldName, browseType);
	    } else {
	    	browseLookup(fieldName, browseName);
	    }
	}
//-->
</SCRIPT>
