<%@ page language="java" errorPage="/system/error_popup.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/header_popup.jsp" %>
<%@ page import="com.tsa.puridiom.entity.*" %>
<%@ page import="java.math.BigDecimal" %>
<%
	List	rfqQuestionList = (List) request.getAttribute("rfqQuestionList");
	List	responseList =  (List) request.getAttribute("vendorResponseList");
	String vendorId = (String) request.getAttribute("VendorResponse_vendorId");
	int i_rsprows = 0;
	int i_qstrows = 0;

	if (rfqQuestionList != null) {
		i_qstrows = rfqQuestionList.size();
	}
	if (responseList != null) {
		i_rsprows = responseList.size();
	}
%>
<table width=<%=formWidth%> cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Bid Response Questions</div>
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

<table border=0 width=680px valign=top>
<tr>
	<td valign=top align=center>
		<br>
		<table border=0 width=100% cellpadding=0 cellspacing=0>
		<tr><td><b>You must respond to all of the questions below before submitting bids.</b></td></tr>
		<tr><td><br></td></tr>
		<tr>
			<td class= summary>
				<table border=0 width=100% cellpadding=2 cellspacing=1 class=summary>
				<tr valign=middle>
					<td class=summary colspan=2 width=5%>&nbsp;</td>
					<td class=summary width=70%>&nbsp;</td>
					<td class=summary width=15%>&nbsp;</td>
				</tr>
<%
	String	s_response_type = "";
	String	s_type = "";
	int	i_rowcount = 0;

	for ( int ii = 0; ii < i_qstrows; ii++ )
	{
		RfqQuestion rfqQuestion = (RfqQuestion) rfqQuestionList.get(ii);
		BigDecimal	questionIc = rfqQuestion.getComp_id().getIcQuestion();
		List	responseValueList = rfqQuestion.getResponseValueList();
		String	s_response = "";
		String	s_text_response = "";

		if (i_rsprows > 0) {
			for ( int ir = 0; ir < i_rsprows; ir++ ) {
				VendorResponse vendorResponse = (VendorResponse) responseList.get(ir);
				if (vendorResponse.getComp_id().getIcQuestion().equals(questionIc)) {
					s_text_response = vendorResponse.getTextResponse();
					s_response = vendorResponse.getResponse();
					responseList.remove(ir);
					i_rsprows--;
					break;
				}
			}
		}

		s_response_type = rfqQuestion.getResponseType().trim().toUpperCase();

		if (responseValueList == null) {
			responseValueList = new ArrayList();
		}
		if (responseValueList.size() == 0 && (s_response_type.equals("YN") || s_response_type.equals("BOTH"))) {
			ResponseValuePK yesResponsePK = new ResponseValuePK();
			yesResponsePK.setIcQuestion(rfqQuestion.getComp_id().getIcQuestion());
			yesResponsePK.setResponseValue("Y");
			ResponseValue yesResponse = new ResponseValue(yesResponsePK);
			yesResponse.setResponseText("Yes");
			responseValueList.add(yesResponse);

			ResponseValuePK noResponsePK = new ResponseValuePK();
			noResponsePK.setIcQuestion(rfqQuestion.getComp_id().getIcQuestion());
			noResponsePK.setResponseValue("N");
			ResponseValue noResponse = new ResponseValue(noResponsePK);
			noResponse.setResponseText("No");
			responseValueList.add(noResponse);
		}

		i_rowcount++;
%>
				<tr>
					<td class=summary>&nbsp;</td>
					<td class=summary valign=top>&nbsp;<b><%=i_rowcount%>.</b></td>
					<td class=summary valign=top><%=rfqQuestion.getQuestionText()%></td>
					<td class=summary>
						<tsa:hidden name="VendorResponse_icQuestion" value="<%=rfqQuestion.getComp_id().getIcQuestion()%>"/>
						<tsa:hidden name="VendorResponse_icRfqHeader" value="<%=rfqQuestion.getComp_id().getIcRfqHeader()%>"/>
						<tsa:hidden name="VendorResponse_vendorId" value="<%=vendorId%>"/>
						<tsa:hidden name="VendorResponse_response" value="<%=s_response%>"/>
						<tsa:hidden name="VendorResponse_textResponse" value="<%=s_text_response%>"/>
						<tsa:hidden name="as_response_type" value="<%=s_response_type%>"/>
					</td>
				</tr>
				<tr>
					<td class=summary colspan=2>&nbsp;</td>
					<td class=summary colspan=2 valign=top>
						<table border=0 class=summary cellpadding=0 cellspacing=0 valign=top height=10px>
						<tr height=10px>
<%		if (s_response_type.equals("YN") || s_response_type.equals("BOTH") || s_response_type.equals("MC")) {
				for (int ir = 0; ir < responseValueList.size(); ir++) {
					ResponseValue responseValue = (ResponseValue) responseValueList.get(ir);
					String	rvalue = responseValue.getComp_id().getResponseValue();%>
							<td class=summary valign=middle align=right <% if (ir > 0) {%>width=40px<%}%>>
								<input type=radio name="radio<%=i_rowcount-1%>" value="<%=rvalue%>" <%if (s_response.equals(rvalue)) {%>CHECKED<%}%> onClick="setResponse('<%=rvalue%>', <%=i_rowcount-1%>);">
							</td>
							<td class=summary valign=middle nowrap>
								<%=responseValue.getResponseText()%>
							</td>
							<!--td class=summary valign=top width=50px><input type=radio name="radio<%=i_rowcount-1%>" value="Y" <%if (s_response.equals("Y")) {%>CHECKED<%}%> onClick="setResponse('Y', <%=i_rowcount-1%>);">Yes</td>
							<td class=summary valign=top width=50px><input type=radio name="radio<%=i_rowcount-1%>" value="N" <%if (s_response.equals("N")) {%>CHECKED<%}%> onClick="setResponse('N', <%=i_rowcount-1%>);">No</td-->
<%			}
			}
			if (s_response_type.equals("TEXT") || s_response_type.equals("BOTH")) {%>
							<td class=summary valign=top width=450px><input type=text name="text_response" maxlength=60 size=65 value="<%=s_text_response%>" onChange="setText(this.value, <%=i_rowcount-1%>);">&nbsp;</td>
<%		}
			else {%>
							<td class=summary><tsa:hidden name="text_response" value=""/></td>
<%		}%>
						</tr>
						</table>
					</td>
				</tr>
				<tr><td class=summary colspan=4><br></td></tr>
<%	}

		if (i_qstrows == 0) {%>
				<tr>
					<td>&nbsp;</td>
					<td class=summary align=center colspan=4>&nbsp;<b>There are no questions attached to this solicitation.</b></td>
				</tr>
<%	} %>
				</table>
			</td>
		</tr>
		</table>
		<br>
		<table border=0 width=100%>
		<tr>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: doSubmit('/system/close_window.jsp', 'VendorResponseUpdate'); void(0);">Save</a></div></td>
	<td width=50% align=center><div id="pxbutton"><a href="javascript: this.close(); void(0);">Close</a></div></td>
		</tr>
		</table>
	</td>
</tr>
</table>
</form>

<!---- JavaScripts for Entry Validation ----->
<SCRIPT LANGUAGE="JavaScript1.2">
<!--  hide script from old browsers

	frm = document.purchaseform;
	var fldObject = null;
	var fldFromObject = null;
	var fldToObject = null;
	var qstRows = <%=i_qstrows%>;
	var thisrow ;

	function setResponse(val, row) {
		if (qstRows == 1) {
			frm.VendorResponse_response.value = val;
		}
		else {
			frm.VendorResponse_response[row].value = val;
		}
	}

	function setText(val, row) {
		if (qstRows == 1) {
			frm.VendorResponse_textResponse.value = val;
		}
		else {
			frm.VendorResponse_textResponse[row].value = val;
		}
	}

	function validateForm() {
		var response = "";
		var response_text = "";
		var type = "";
		var surveyComplete = true;

		if (qstRows == 1) {
			type = frm.as_response_type.value;
			response = frm.VendorResponse_response.value;
			text = frm.VendorResponse_textResponse.value;

			surveyComplete = isResponseComplete(type,response, text);
		}
		else {
			for (var ir = 0; ir < qstRows; ir++)
			{
				type = frm.as_response_type[ir].value;
				response = frm.VendorResponse_response[ir].value;
				text = frm.VendorResponse_textResponse[ir].value;

				surveyComplete = isResponseComplete(type,response, text);

				if (!surveyComplete) {
					ir = qstRows;
				}
			}
		}

		window.parent.frm.as_survey_complete.value = surveyComplete;

		if (!surveyComplete) {
			return verifyAction("You must respond to all questions before submitting bids.  Click cancel to complete your responses now.");
		}

		window.parent.frm.as_info_changed.value = "Y";

		return true;
	}

	function isResponseComplete(type,response, text) {
		var complete = true;

		if (type == "YN" || type == "BOTH") {
			if (isEmpty(response)) {
				complete = false;
			}
		}

		if (type == "TEXT" || type == "BOTH") {
			if (isEmpty(text)) {
				complete = false;
			}
		}

		return complete;
	}

// end hiding contents -->
</SCRIPT>

</body>
</html>
<%@ include file="/system/prevent_caching.jsp" %>