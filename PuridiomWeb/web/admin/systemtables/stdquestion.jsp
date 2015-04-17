<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionRatingMethod" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionResponseType" %>
<%
		StdQuestion stdQuestion = (StdQuestion) request.getAttribute("stdQuestion");
		boolean newQuestion = false;

		if (stdQuestion == null) {
			stdQuestion = new StdQuestion();
			newQuestion = true;
		}
		String	s_questionTitle = stdQuestion.getQuestionTitle();
		String	s_questionText = stdQuestion.getQuestionText();
		String	s_responseType = stdQuestion.getResponseType();
		String	s_ratingMethod = stdQuestion.getRatingMethod();
%>

<tsa:hidden name="StdQuestion_icQuestion" value="<%=stdQuestion.getIcQuestion()%>"/>
<%	if (newQuestion) {%>
<tsa:hidden name="createAction" value="SAVE"/>
<%	}%>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Standard Solicitation Question</div>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top width=100%>
		<table border=0 cellspacing=0 cellpadding=0 width=100%>
		<tr>
			<td>
				<table border=0 cellpadding=0 cellspacing=2 width=250px align=center>
				<tr>
					<td nowrap align=right class=processOn>Title:&nbsp;</td>
					<td><input type="text" name="StdQuestion_questionTitle" size=40 value="<%=s_questionTitle%>" tabIndex=2 onChange="alphaNumericCheck(this);"></td>
				</tr>
				<tr>
					<td nowrap align=right valign=top class=processOn>Question:&nbsp;</td>
					<td><textarea wrap=nonvirtual name="StdQuestion_questionText" cols=40 rows=6 ONKEYDOWN="limit(frm.StdQuestion_questionText, 250);" ONKEYUP="limit(frm.StdQuestion_questionText, 250);" tabIndex=4>${esapi:encodeForHTML(stdQuestion.questionText)}</textarea></td>
				</tr>
				<tr>
					<td nowrap align=right class=processOn>Response Type:&nbsp;</td>
					<td>
						<select name="StdQuestion_responseType" onchange="setupResponseValues(false);" tabindex=6>
							<option value="<%=StdQuestionResponseType.YES_NO%>" <% if (s_responseType.equals(StdQuestionResponseType.YES_NO)) {%>SELECTED<%}%>><%=StdQuestionResponseType.toString(StdQuestionResponseType.YES_NO, oid)%></option>
							<option value="<%=StdQuestionResponseType.OPEN_TEXT%>" <% if (s_responseType.equals(StdQuestionResponseType.OPEN_TEXT)) {%>SELECTED<%}%>><%=StdQuestionResponseType.toString(StdQuestionResponseType.OPEN_TEXT, oid)%></option>
							<option value="<%=StdQuestionResponseType.YES_NO_AND_OPEN_TEXT%>" <% if (s_responseType.equals(StdQuestionResponseType.YES_NO_AND_OPEN_TEXT)) {%>SELECTED<%}%>><%=StdQuestionResponseType.toString(StdQuestionResponseType.YES_NO_AND_OPEN_TEXT, oid)%></option>
							<option value="<%=StdQuestionResponseType.MULTIPLE_CHOICE%>" <% if (s_responseType.equals(StdQuestionResponseType.MULTIPLE_CHOICE)) {%>SELECTED<%}%>><%=StdQuestionResponseType.toString(StdQuestionResponseType.MULTIPLE_CHOICE, oid)%></option>
						</select>
					</td>
				</tr>
				<tr <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>>
					<td align=right class=label nowrap>Rating Method:</td>
					<td>
						<select name="StdQuestion_ratingMethod" onchange="setupWeight();" tabIndex=8>
						<!--option value="<%=StdQuestionRatingMethod.MANUAL%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.MANUAL)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.MANUAL, oid)%></option-->
						<option value="<%=StdQuestionRatingMethod.RATING_ONLY%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.RATING_ONLY)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.RATING_ONLY, oid)%></option>
						<option value="<%=StdQuestionRatingMethod.WEIGHTED_RATING%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.WEIGHTED_RATING)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.WEIGHTED_RATING, oid)%></option>
						</select>
					</td>
				</tr>
				<tr id="questionWeight" <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>>
					<td align=right class=label nowrap>Weighted Rating:</td>
					<td><input type=text name="StdQuestion_weight" value="<%=stdQuestion.getWeight()%>" tabIndex=10></td>
				</tr>
				<tr id="questionMaxPoints" <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%} else {%> <%=HtmlWriter.isVisible(oid, "std-rfq-questionMaxPoints")%><%}%>>
					<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "std-rfq-questionMaxPoints", "Max Points")%>:</td>
					<td><input type=text name="StdQuestion_maxPoints" value="<%=stdQuestion.getMaxPoints()%>"></td>
				</tr>
				</table>
			</td>
			<td valign=top>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center id="responseValues">
				<tr>
					<td class=label align=center>Response Value</td>
					<td class=label align=center>Response Option</td>
					<td class=label align=center <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>>Points</td>
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
					<td align=center><input type=text name="ResponseValue_responseValue" value="<%=responseValue.getComp_id().getResponseValue()%>" size="6"><tsa:hidden name="ResponseValue_icQuestion" value="<%=stdQuestion.getIcQuestion()%>"/></td>
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
	<td width=50% align=center><a href="javascript: saveQuestion(); void(0);"><img class=button src="<%=contextPath%>/images/button_save.gif" border=0></a></td>
	<td width=50% align=center><a href="javascript: browse('stdquestion_admin'); void(0);"><img class=button src="<%=contextPath%>/images/button_cancel.gif" border=0 alt="Cancel"></a></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;
	frm.browseName.value="stdquestion_admin";
	setNavCookie("/admin/systemtables/stdquestion.jsp", "StdQuestionRetrieveById", "Question <%=stdQuestion.getQuestionTitle()%>");

	var responseRows = frm.elements.item("ResponseValue_responseValue").length;
	var newQuestion = <%=newQuestion%>;

	setupResponseValues(true);
	setupWeight();
	calculateMaxPoints();

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
			if (!initialSetup || newQuestion) {
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
			frm.StdQuestion_maxPoints.disabled = true;
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
			frm.StdQuestion_maxPoints.disabled = false;
		} else if (responseType == "MC") {
			if (!initialSetup) {
				clearResponseValues();
			}
			frm.StdQuestion_maxPoints.disabled = true;
		} else if (responseType == "TEXT") {
			clearResponseValues();

			for (var i=0; i < responseRows; i++) {
				frm.ResponseValue_responseValue[i].disabled = true;
				frm.ResponseValue_responseText[i].disabled = true;
				frm.ResponseValue_rating[i].disabled = true;
			}
			frm.StdQuestion_maxPoints.disabled = false;
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

	function clearResponseValues() {
		for (var i=0; i < responseRows; i++) {
			frm.ResponseValue_responseValue[i].disabled = false;
			frm.ResponseValue_responseValue[i].value = "";
			frm.ResponseValue_responseText[i].disabled = false;
			frm.ResponseValue_responseText[i].value = "";
			frm.ResponseValue_rating[i].disabled = false;
			frm.ResponseValue_rating[i].value = "";
		}
	}

	function browse(x) {
		frm.browseName.value = x;

		doSubmit('/browse/browse_sys_tables.jsp', 'BrowseRetrieve');
	}

	function saveQuestion() {
		var handlers = "ResponseValueUpdateList;BrowseRetrieve";

		if (newQuestion) {
			handlers = "StdQuestionCreate;" + handlers;
		} else {
			handlers = "StdQuestionUpdateById;" + handlers;
		}
		doSubmit('/browse/browse_sys_tables.jsp', handlers);
	}

	function formatRating(fld) {
		calculateMaxPoints();
	}

	function calculateMaxPoints() {
		var totalPoints = 0;
		for (var i=0; i < responseRows; i++) {
			var responsePoints = nfilter(frm.ResponseValue_rating[i]);
			var weight = nfilter(frm.StdQuestion_weight);

			if (!isEmpty(responsePoints) && !isEmpty(weight)) {
				totalPoints = totalPoints + eval(responsePoints * weight);
			}
		}
		frm.StdQuestion_maxPoints.value = totalPoints;
	}

	function validateForm() {
		var handlers = frm.handler.value;

		if (handlers.indexOf("ResponseValueUpdateList") >= 0) {
			var questionText = frm.StdQuestion_questionText.value;

			if (isEmpty(questionText)) {
				alert("You must enter a question to save.");
				return false;
			}

			var responseType = frm.StdQuestion_responseType[frm.StdQuestion_responseType.selectedIndex].value;

			if (responseType == "MC") {
				var cnt = 0;
				var emptyCnt = 0;

				for (var i=0; i < responseRows; i++) {
					var responseValue = frm.ResponseValue_responseValue[i].value;
					var responseText = frm.ResponseValue_responseText[i].value;

					if (!isEmpty(responseValue) && !isEmpty(responseText)) {
						cnt++;
					} else if (isEmpty(responseValue) && !isEmpty(responseText)) {
						emptyCnt++;
					} else if (!isEmpty(responseValue) && isEmpty(responseText)) {
						emptyCnt++;
					}
				}
				if (cnt < 2) {
					alert("You must specify at least 2 possible responses for Multiple Choice Questions.");
					return false;
				}
				if (emptyCnt > 0) {
					alert("You must specify the value and text for each possible response.");
					return false;
				}

			}
		}
		return true;
	}

// End Hide script -->
</SCRIPT>