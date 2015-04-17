<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionRatingMethod" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionResponseType" %>
<%
		StdQuestion stdQuestion = (StdQuestion) request.getAttribute("stdQuestion");
		BigDecimal bd_icQuestion = stdQuestion.getIcQuestion();
		String	s_questionTitle = stdQuestion.getQuestionTitle();
		String	s_questionText = stdQuestion.getQuestionText();
		String	s_responseType = stdQuestion.getResponseType();
		String	s_ratingMethod = stdQuestion.getRatingMethod();
%>

<tsa:hidden name="StdQuestion_icQuestion" value="<%=bd_icQuestion%>"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Edit Standard Solicitation Question</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<table cellpadding="0" cellspacing="0" border=0 width=100%>
		<tr>
			<td height=1px class=lightShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1" /></td>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=2 width=250px align=center>
				<tr>
					<td nowrap align=right class=processOn>Title:&nbsp;</td>
					<td><input type="text" name="StdQuestion_questionTitle" size=40 value="<%=s_questionTitle%>"></td>
				</tr>
				<tr>
					<td nowrap align=right valign=top class=processOn>Question:&nbsp;</td>
					<td><textarea wrap=nonvirtual name="StdQuestion_questionText" cols=40 rows=6 ONKEYDOWN="limit(frm.StdQuestion_questionText, 250);" ONKEYUP="limit(frm.StdQuestion_questionText, 250);">${esapi:encodeForHTML(s_questionText)}</textarea></td>
				</tr>
				<tr>
					<td nowrap align=right class=processOn>Response Type:&nbsp;</td>
					<td>
						<select name="StdQuestion_responseType" onchange="setupResponseValues(false);" tabindex=17>
							<option value="<%=StdQuestionResponseType.YES_NO%>" <% if (s_responseType.equals(StdQuestionResponseType.YES_NO)) {%>SELECTED<%}%>><%=StdQuestionResponseType.toString(StdQuestionResponseType.YES_NO, oid)%></option>
							<option value="<%=StdQuestionResponseType.OPEN_TEXT%>" <% if (s_responseType.equals(StdQuestionResponseType.OPEN_TEXT)) {%>SELECTED<%}%>><%=StdQuestionResponseType.toString(StdQuestionResponseType.OPEN_TEXT, oid)%></option>
							<option value="<%=StdQuestionResponseType.YES_NO_AND_OPEN_TEXT%>" <% if (s_responseType.equals(StdQuestionResponseType.YES_NO_AND_OPEN_TEXT)) {%>SELECTED<%}%>><%=StdQuestionResponseType.toString(StdQuestionResponseType.YES_NO_AND_OPEN_TEXT, oid)%></option>
							<option value="<%=StdQuestionResponseType.MULTIPLE_CHOICE%>" <% if (s_responseType.equals(StdQuestionResponseType.MULTIPLE_CHOICE)) {%>SELECTED<%}%>><%=StdQuestionResponseType.toString(StdQuestionResponseType.MULTIPLE_CHOICE, oid)%></option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=right class=label nowrap>Default Rating Method:</td>
					<td>
						<select name="StdQuestion_ratingMethod" onchange="setupWeight();">
						<!--option value="<%=StdQuestionRatingMethod.MANUAL%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.MANUAL)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.MANUAL, oid)%></option-->
						<option value="<%=StdQuestionRatingMethod.RATING_ONLY%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.RATING_ONLY)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.RATING_ONLY, oid)%></option>
						<option value="<%=StdQuestionRatingMethod.WEIGHTED_RATING%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.WEIGHTED_RATING)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.WEIGHTED_RATING, oid)%></option>
						</select>
					</td>
				</tr>
				<tr id="questionWeight">
					<td align=right class=label nowrap>Default Weighted Rating:</td>
					<td><input type=text name="StdQuestion_weight" value="<%=stdQuestion.getWeight()%>"></td>
				</tr>
				</table>
			</td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center id="responseValues">
				<tr>
					<td class=label align=center>Response Value</td>
					<td class=label align=center>Response Option</td>
					<td class=label align=center <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>>Response Rating</td>
				</tr>
<%
			List	responseValueList = stdQuestion.getResponseValueList();
			for (int ir = 0; ir < 5; ir++) {
				ResponseValue responseValue;
				if (responseValueList != null && ir < responseValueList.size()) {
					responseValue = (ResponseValue) responseValueList.get(ir);
				} else {
					ResponseValuePK responseValuePK = new ResponseValuePK();
					responseValue = new ResponseValue(responseValuePK);
				}
		%>
				<tr>
					<td align=center><input type=text name="ResponseValue_responseValue" value="<%=responseValue.getComp_id().getResponseValue()%>" size="6"><tsa:hidden name="ResponseValue_icQuestion" value="<%=bd_icQuestion%>"/></td>
					<td align=center><input type=text name="ResponseValue_responseText" value="<%=responseValue.getResponseText()%>" size="20"></td>
					<td align=center <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>><input type=text name="ResponseValue_rating" value="<%=responseValue.getRating()%>" size="10" onchange="formatRating(this);"></td>
				</tr>
<%		}%>
				</table>
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_std_questions.jsp', 'StdQuestionUpdateById;BrowseRetrieve'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_std_questions.jsp', 'BrowseRetrieve'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	var responseRows = frm.elements.item("ResponseValue_responseValue").length;

	frm = document.purchaseform;
	frm.browseName.value="stdquestion";

	setupResponseValues(true);
	setupWeight();

	function limit(field, maxlimit)
	{
		if (field.value.length > maxlimit) {
			// field is too long...trim it!
			field.value = field.value.substring(0, maxlimit);
		}
	}

	function setupResponseValues(initialSetup) {
		var responseType = frm.StdQuestion_responseType[frm.StdQuestion_responseType.selectedIndex].value;

		if (responseType == "YN") {
			if (!initialSetup) {
				clearResponseValues();
				frm.ResponseValue_responseValue[0].value = "Y";
				frm.ResponseValue_responseValue[0].disabled = true;
				frm.ResponseValue_responseText[0].value = "Yes";
				frm.ResponseValue_responseText[0].disabled = true;
				frm.ResponseValue_responseValue[1].value = "N";
				frm.ResponseValue_responseValue[1].disabled = true;
				frm.ResponseValue_responseText[1].value = "No";
				frm.ResponseValue_responseText[1].disabled = true;
			}

			for (var i=2; i < responseRows; i++) {
				frm.ResponseValue_responseValue[i].value = "";
				frm.ResponseValue_responseValue[i].disabled = true;
				frm.ResponseValue_responseText[i].value = "";
				frm.ResponseValue_responseText[i].disabled = true;
				frm.ResponseValue_rating[i].value = "";;
				frm.ResponseValue_rating[i].disabled = true;
			}
		} else if (responseType == "BOTH") {
			clearResponseValues();
			frm.ResponseValue_responseValue[0].value = "Y";
			frm.ResponseValue_responseValue[0].disabled = true;
			frm.ResponseValue_responseText[0].value = "Yes";
			frm.ResponseValue_responseText[0].disabled = true;
			frm.ResponseValue_responseValue[1].value = "N";
			frm.ResponseValue_responseValue[1].disabled = true;
			frm.ResponseValue_responseText[1].value = "No";
			frm.ResponseValue_responseText[1].disabled = true;

			for (var i=2; i < responseRows; i++) {
				frm.ResponseValue_responseValue[i].disabled = true;
				frm.ResponseValue_responseText[i].disabled = true;
				frm.ResponseValue_rating[i].disabled = true;
			}
		} else if (responseType == "MC") {
			if (!initialSetup) {
				clearResponseValues();
			}
		} else if (responseType == "TEXT") {
			clearResponseValues();

			for (var i=0; i < responseRows; i++) {
				frm.ResponseValue_responseValue[i].disabled = true;
				frm.ResponseValue_responseText[i].disabled = true;
				frm.ResponseValue_rating[i].disabled = true;
			}
		}
	}

	function setupWeight() {
		var ratingMethod = frm.StdQuestion_ratingMethod[frm.StdQuestion_ratingMethod.selectedIndex].value;

		if (ratingMethod == "<%=StdQuestionRatingMethod.WEIGHTED_RATING%>") {
			displayArea("questionWeight");
		} else {
			hideAreaWithBlock("questionWeight");
			frm.StdQuestion_weight.value = "1";
		}
	}
// End Hide script -->
</SCRIPT>