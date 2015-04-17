<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>
<%@ page import="com.tsa.puridiom.common.documents.DocumentStatus" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionRatingMethod" %>
<%@ page import="com.tsa.puridiom.common.documents.StdQuestionResponseType" %>

<SCRIPT LANGUAGE="JavaScript1.2" SRC="<%=contextPath%>/scripts/calendar.js"></SCRIPT>

<%
	String s_icRfqHeader = (String) request.getAttribute("RfqHeader_icRfqHeader");
	String s_rfqNumber = (String) request.getAttribute("RfqHeader_rfqNumber");
	String s_rfqAmendment = (String) request.getAttribute("RfqHeader_rfqAmendment");
	String s_rfqStatus = (String) request.getAttribute("RfqHeader_status");
	String	s_questionAction = (String) request.getAttribute("questionAction");

	RfqQuestion rfqQuestion = (RfqQuestion) request.getAttribute("rfqQuestion");
	RfqQuestionPK rfqQuestionPK = (RfqQuestionPK) rfqQuestion.getComp_id();
	BigDecimal bd_icQuestion = rfqQuestionPK.getIcQuestion();
	String s_questionText = rfqQuestion.getQuestionText();
	String s_responseType = rfqQuestion.getResponseType();
	String s_ratingMethod = rfqQuestion.getRatingMethod();
	String s_maxPoints = rfqQuestion.getMaxPoints().toString();

	if (HiltonUtility.isEmpty(s_questionAction)) {
		s_questionAction = "Edit";
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqQuestion_icRfqHeader" value="<%=s_icRfqHeader%>"/>
<tsa:hidden name="RfqQuestion_icQuestion" value="<%=bd_icQuestion%>"/>
<tsa:hidden name="RfqQuestion_sequence" value="<%=rfqQuestion.getSequence()%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12><%=headerEncoder.encodeForHTML(s_questionAction)%> Solicitation Question</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom width=3px><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom height=30px width=*>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
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

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td valign=top>
		<table border=0 cellspacing=0 cellpadding=0 height=100% width=100%>
		<tr>
			<td align=center>
				<br><br><br>
				<table border=0 cellpadding=2 cellspacing=0>
				<tr>
					<td align=right valign=top class=label nowrap>Question:</td>
					<td><textarea name="RfqQuestion_questionText" cols=50 rows=5>${esapi:encodeForHTML(rfqQuestion.questionText)}</textarea></td>
					<tsa:hidden name="createAction" value="SAVE"/>
				</tr>
				<tr>
					<td align=right class=label nowrap>Response Type:</td>
					<td>
						<select name="RfqQuestion_responseType" onchange="setupResponseValues(false);">
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
						<select name="RfqQuestion_ratingMethod" onchange="setupWeight();">
						<!--option value="<%=StdQuestionRatingMethod.MANUAL%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.MANUAL)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.MANUAL, oid)%></option-->
						<option value="<%=StdQuestionRatingMethod.RATING_ONLY%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.RATING_ONLY)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.RATING_ONLY, oid)%></option>
						<option value="<%=StdQuestionRatingMethod.WEIGHTED_RATING%>" <% if (s_ratingMethod.equals(StdQuestionRatingMethod.WEIGHTED_RATING)) {%>SELECTED<%}%>><%=StdQuestionRatingMethod.toString(StdQuestionRatingMethod.WEIGHTED_RATING, oid)%></option>
						</select>
					</td>
				</tr>
				<tr id="questionWeight" <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>>
					<td align=right class=label nowrap>Weighted Rating:</td>
					<td><input type=text name="RfqQuestion_weight" value="<%=rfqQuestion.getWeight()%>" onchange="calculateMaxPoints();"></td>
				</tr>
				<tr id="questionMaxPoints" <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%} else {%> <%=HtmlWriter.isVisible(oid, "rfq-questionMaxPoints")%><%}%>>
					<td align=right class=label nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-questionMaxPoints", "Max Points")%>:</td>
					<td><input type=text name="RfqQuestion_maxPoints" value="<%=rfqQuestion.getMaxPoints()%>"></td>
				</tr>
				</table>
			</td>
			<td>
				<table border=0 cellpadding=0 cellspacing=0 width=250px align=center id="responseValues">
				<tr>
					<td class=label align=center>Response Value</td>
					<td class=label align=center>Response Option</td>
					<td class=label align=center <%	if (!extRfqsActive) {%> style="visibility:hidden;"<%}%>>Response Points</td>
				</tr>
<%
			List	responseValueList = rfqQuestion.getResponseValueList();
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
	<td width=50% align=center><div id="pxbutton"><a href="javascript: saveQuestion(); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/rfq/rfq_questions.jsp', 'RfqQuestionRetrieveByHeader'); void(0);">Return</a></div></td>
</tr>
</table>

<%@ include file="/system/footer.jsp" %>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	var responseRows = frm.elements.item("ResponseValue_responseValue").length;
	var questionAction = "<%=headerEncoder.encodeForJavaScript(s_questionAction)%>";

	function thisLoad()
	{
		f_StartIt();
<%	if (Integer.valueOf(s_rfqStatus).intValue() >= 2005 && Integer.valueOf(s_rfqStatus).intValue() != 2015) { %>
			checkInputSettings();
			allowEdit = false;
<%	} %>
	}

	setupResponseValues(true);
	setupWeight();
	calculateMaxPoints();

	function setupResponseValues(initialSetup) {
		var responseType = frm.RfqQuestion_responseType[frm.RfqQuestion_responseType.selectedIndex].value;

		if (responseType == "YN") {
			if (!initialSetup || (questionAction == "Add")) {
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
				frm.ResponseValue_rating[i].value = "";
				frm.ResponseValue_rating[i].disabled = true;
			}
			frm.RfqQuestion_maxPoints.disabled = true;
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
			frm.RfqQuestion_maxPoints.disabled = false;
		} else if (responseType == "MC") {
			if (!initialSetup) {
				clearResponseValues();
			} else {
				for (var i=0; i < responseRows; i++) {
					if (isEmpty(frm.ResponseValue_responseText[i].value) && frm.ResponseValue_rating[i].value == "0") {
						frm.ResponseValue_rating[i].value = "";
					}
				}
			}
			frm.RfqQuestion_maxPoints.disabled = true;
		} else if (responseType == "TEXT") {
			clearResponseValues();

			for (var i=0; i < responseRows; i++) {
				frm.ResponseValue_responseValue[i].disabled = true;
				frm.ResponseValue_responseText[i].disabled = true;
				frm.ResponseValue_rating[i].disabled = true;
			}
			frm.RfqQuestion_maxPoints.disabled = false;
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

	function validateForm() {
		var handlers = frm.handler.value;
		if (handlers.indexOf("ResponseValueUpdateList") >= 0) {
			var questionText = frm.RfqQuestion_questionText.value;

			if (isEmpty(questionText)) {
				alert("You must enter a question to save.");
				return false;
			}

			var responseType = frm.RfqQuestion_responseType[frm.RfqQuestion_responseType.selectedIndex].value;

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
				deleteEmptyResponseRows();
			}
		}
		return true;
	}

	function deleteEmptyResponseRows() {
		var myTable = document.getElementById("responseValues");

		for (var i=responseRows-1; i >= 0; i--) {
			var responseValue = frm.ResponseValue_responseValue[i].value;
			var responseText = frm.ResponseValue_responseText[i].value;

			if (isEmpty(responseValue) && isEmpty(responseText)) {
				deleteRow(myTable, i+1);
			}
		}
	}

	function formatRating(fld) {
		calculateMaxPoints();
	}

	function saveQuestion() {
		var handlers = "ResponseValueUpdateList;RfqQuestionRetrieveByHeader";

		if (questionAction == "Add") {
			handlers = "RfqQuestionAdd;" + handlers;
		} else {
			handlers = "RfqQuestionUpdateById;" + handlers;
		}
		doSubmit('/rfq/rfq_questions.jsp', handlers);
	}

	function setupWeight() {
		var ratingMethod = frm.RfqQuestion_ratingMethod[frm.RfqQuestion_ratingMethod.selectedIndex].value;

		if (ratingMethod == "<%=StdQuestionRatingMethod.WEIGHTED_RATING%>") {
			displayArea("questionWeight");
		} else {
			hideAreaWithBlock("questionWeight");
			frm.RfqQuestion_weight.value = "1";
		}
	}

	function calculateMaxPoints() {
		var maxPoints = "<%= headerEncoder.encodeForJavaScript(s_maxPoints) %>";
		for (var i=0; i < responseRows; i++) {
			var responsePoints = nfilter(frm.ResponseValue_rating[i]);
			var weight = nfilter(frm.RfqQuestion_weight);

			if (!isEmpty(responsePoints) && !isEmpty(weight)) {
				var totalPoints = eval(responsePoints * weight);
				if (totalPoints > maxPoints) {
					maxPoints = totalPoints;
				}
			}
		}
		frm.RfqQuestion_maxPoints.value = maxPoints;
	}

// End Hide script -->
</SCRIPT>
