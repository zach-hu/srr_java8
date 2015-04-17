<%@ page language="java" errorPage="/system/error.jsp" %>
<%@ include file="/system/prevent_caching.jsp" %>
<%@ include file="/system/context_path.jsp" %>
<%@ include file="/system/header.jsp" %>

<script language='Javascript1.2' src="<%=contextPath%>/scripts/dynamicTables.js"></script>

<%
	PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
	String s_dollar_decimals = propertiesManager.getProperty("MISC", "DollarDecimals", "2");
	String s_quantity_decimals = propertiesManager.getProperty("MISC", "QtyDecimals", "2");
	RfqHeader rfqHeader = (RfqHeader) request.getAttribute("rfqHeader");
	List rfqVendorList = rfqHeader.getRfqVendorList();
	List lineList = (List) rfqHeader.getRfqLineList();
	BigDecimal b_icRfqHeader = rfqHeader.getIcRfqHeader();
	String s_rfqNumber = rfqHeader.getRfqNumber();
	String s_rfqAmendment = rfqHeader.getRfqAmendment();
	String s_rfqStatus = rfqHeader.getStatus();
	String s_rfqType = rfqHeader.getRfqType();
	String s_returnPage = "";
	int	lineCount = 0;

	if (s_view.equalsIgnoreCase("CLASSIC")) {
		s_returnPage = "/rfq/rfq_summary.jsp";
	}
	else {
		s_returnPage = "/rfq/rfq_review.jsp";
	}
	if (lineList != null) {
		lineCount = lineList.size();
	}
%>

<tsa:hidden name="RfqHeader_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqLine_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqBid_icRfqHeader" value="<%=b_icRfqHeader%>"/>
<tsa:hidden name="RfqHeader_rfqNumber" value="<%=s_rfqNumber%>"/>
<tsa:hidden name="RfqHeader_rfqAmendment" value="<%=s_rfqAmendment%>"/>
<tsa:hidden name="RfqHeader_status" value="<%=s_rfqStatus%>"/>
<tsa:hidden name="allowBrowse" value="true"/>

<table width=680px cellpadding=0 cellspacing=0 border=0>
<tr>
	<td valign=top width=135px height=30px>
		<table cellpadding=0 cellspacing=0 border=0 height=100% width=100%>
		<tr>
			<td height=1px class=darkShadow><img src="<%=contextPath%>/images/none.gif" width="1" height="1px" /></td>
		</tr>
		<tr>
			<td nowrap class=hdr12 valign=middle>
				<div style="margin-left:10px; margin-right:10px" class=hdr12>Supplier Award</div>
			</td>
		</tr>
		</table>
	</td>
	<td valign=bottom><img class=hdr12 src="<%=contextPath%>/images/angle.gif" height="31" /></td>
	<td valign=bottom align=right height=30px>
		<%@ include file="/rfq/rfq_status_title.jsp" %>
		<table cellpadding="0" cellspacing="0" border=0>
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
<%
	String s_response = "";
	int colspan = 1;
	List questionList = (List) rfqHeader.getRfqQuestionList();
	if (questionList != null && questionList.size() > 0) {
%>
<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center width=680px>
		<table id=questions border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top>
				<table border=0 cellspacing=0 cellpadding=4 width=100% class=browseRow>
				<tr>
					<td width=5% class=browseHdr nowrap>&nbsp;Supplier Questions</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
<%		for (int i = 0; i < questionList.size(); i++) {
				RfqQuestion rfqQuestion = (RfqQuestion) questionList.get(i);
				RfqQuestionPK rfqQuestionPK = rfqQuestion.getComp_id();
				BigDecimal bd_icQuestion = rfqQuestionPK.getIcQuestion();
				Map responseValueMap = rfqQuestion.getResponseValueMap();
%>
				<tr height=18px>
					<td width="5px">&nbsp;</td>
					<td colspan=3><b><%=rfqQuestion.getQuestionText()%></b></td>
				</tr>
<%		List responseList = (List) request.getAttribute("vendorResponseList");
			if (responseList != null && responseList.size() > 0) {
				for (int j = 0; j < responseList.size(); j++) {
					VendorResponse vendorResponse = (VendorResponse) responseList.get(j);
					VendorResponsePK vendorResponsePK = vendorResponse.getComp_id();
					if (bd_icQuestion.compareTo(vendorResponsePK.getIcQuestion()) == 0) {
						s_response = "";
						if (responseValueMap != null) {
							ResponseValue responseValue = (ResponseValue) responseValueMap.get(vendorResponse.getResponse());
							if (responseValue != null) {
								s_response = responseValue.getResponseText();
							}
						}
						icolspan = 1;
						if (HiltonUtility.isEmpty(s_response)) {
							if (vendorResponse.getResponse().equals("Y")) {
								s_response = "Yes";
							}
							else if (vendorResponse.getResponse().equals("N")) {
								s_response = "No";
							}
						}
%>
				<tr height=18px>
					<td width="5px">&nbsp;</td>
					<td width="20%" nowrap><%=vendorResponsePK.getVendorId()%></td>
					<%	if (s_response.length() > 0) {%>
					<td align="left" nowrap><%=s_response%></td>
					<%	} else {
								icolspan = 2;
							}  %>
					<td colspan="<%=icolspan%>"><%=vendorResponse.getTextResponse()%></td>
				</tr>
<%				}
				}
			}
%>
				<tr><td colspan=3><br></td></tr>
<%		} %>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<%	} %>

<br>
<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td align=center valign=top>
		<table border=0 cellspacing=0 cellpadding=2 width=98%>
		<tr>
			<td width="53%">
			<td align=right>
				<select name="awardSelectionType" size="1" onchange="setAwardSelection();">
					<option selected="selected" value="byItem"><b>Award by item</b></option>
<%		if (rfqVendorList != null) {
			for (int iv = 0; iv < rfqVendorList.size(); iv++) {
				RfqVendor rfqVendor = (RfqVendor) rfqVendorList.get(iv);
				String	vendorId = rfqVendor.getComp_id().getVendorId();
%>
					<option value="<%=vendorId%>">Award all items to <%=vendorId%> (ORIG Bid)</option>
<%			}
		}
%>
				</select>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<td align=center width=680px>
		<table id=itemTable border=1 cellspacing=0 cellpadding=0 width=665px class=browseHdr>
		<tr>
			<td width=100% align=center valign=top class=browseRow>
				<table border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td width=50% align=center valign=top class=browseRow>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
						<td <%=HtmlWriter.isVisible(oid, "rfq-lineNumber")%> width=5% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-lineNumber", "Line #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-bidType")%> width=8% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-bidType", "Bid Type")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-itemNumber")%> width=21% class=browseHdr nowrap>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-itemNumber", "Item/Part #")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-promiseDate")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-promiseDate", "Promise Date")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-quantity")%> width=10% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-quantity", "Quantity")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-uom")%> width=4% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-uom", "UOM")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-unitCost")%> width=12% class=browseHdr nowrap align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-unitCost", "Unit Cost")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-supplier")%> width=15% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-supplier", "Supplier")%></td>
							<td <%=HtmlWriter.isVisible(oid, "rfq-award")%> width=5% class=browseHdr nowrap><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rfq-award", "Award")%></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan=2 align=center valign=top>
				<tsa:hidden name="RfqBid_icRfqLine" value=""/>
				<tsa:hidden name="RfqLine_icRfqLine" value=""/>
				<table id=itemRow border=0 cellspacing=0 cellpadding=0 width=100% class=browseRow>
				<tr>
					<td>

						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td valign=top width=100%>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow valign=top>
								<tr>
									<td  width=5% class=browseRow nowrap align=right>1.00&nbsp;</td>
									<td  width=8% class=browseRow nowrap>ORIG</td>
									<td  width=21% class=browseRow nowrap>Grade B Pipe</td>
									<td width=10% class=browseRow valign=middle align=center>01-01-2008</td>
									<td  width=10% class=browseRow nowrap align=right>100</td>
									<td  width=4% class=browseRow nowrap>FT</td>
									<td  width=12% class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="78.0000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td width=15% class=browseRow valign=middle>SUPPLIER A</td>
									<td width=5% class=browseRow valign=middle><input type=radio name=awardTo0 value="A"></input></td>
								</tr>
								<tr>
									<td class=browseRow nowrap align=right id="origCopy0">1.00&nbsp;</td>
									<td class=browseRow nowrap id="origCopy0">ORIG</td>
									<td class=browseRow nowrap id="origCopy0">Grade B Pipe</td>
									<td class=browseRow valign=middle align=center id="origCopy0">01-01-2008</td>
									<td class=browseRow nowrap align=right id="origCopy0">100</td>
									<td class=browseRow nowrap id="origCopy0">FT</td>
									<td class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="70.0000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td class=browseRow valign=middle>SUPPLIER B</td>
									<td class=browseRow valign=middle><input type=radio name=awardTo0 value="B"></input></td>
								</tr>
								<tr id="alt0">
									<td class=browseRow nowrap align=right>1.01&nbsp;</td>
									<td  class=browseRow nowrap>ALT</td>
									<td class=browseRow nowrap>Grade B Pipe</td>
									<td class=browseRow valign=middle align=center>01-01-2008</td>
									<td class=browseRow nowrap align=right><font class=highlight>300</font></td>
									<td class=browseRow nowrap>FT</td>
									<td class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="67.0000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td class=browseRow valign=middle>SUPPLIER A</td>
									<td class=browseRow valign=middle><input type=radio name=awardTo0 value="A.01"></input></td>
								</tr>
								<tr id="alt0">
									<td class=browseRow nowrap align=right>1.02&nbsp;</td>
									<td  class=browseRow nowrap>ALT</td>
									<td class=browseRow nowrap>Grade B Pipe</td>
									<td class=browseRow valign=middle align=center>01-01-2008</td>
									<td class=browseRow nowrap align=right><font class=highlight>500</font></td>
									<td class=browseRow nowrap>FT</td>
									<td class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="58.0000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td class=browseRow valign=middle>SUPPLIER A</td>
									<td class=browseRow valign=middle><input type=radio name=awardTo0 value="A.02"></input></td>
								</tr>
								<tr id="alt0">
									<td class=browseRow nowrap align=right>1.03&nbsp;</td>
									<td  class=browseRow nowrap>ALT</td>
									<td class=browseRow nowrap><font class=highlight>Grade A Pipe</font></td>
									<td class=browseRow valign=middle align=center>01-01-2008</td>
									<td  class=browseRow nowrap align=right>100</td>
									<td  class=browseRow nowrap>FT</td>
									<td  class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="67.5000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td class=browseRow valign=middle>SUPPLIER B</td>
									<td class=browseRow valign=middle><input type=radio name=awardTo0 value="B"></input></td>
								</tr>
								<tr><td colspan=9 id="altOption0">&nbsp;&nbsp;<a href="javascript: hideAlternates(0); void(0);">Hide Alternates</a></td></tr>
								</table>
							</td>
						</tr>
						</table>

						<hr size=0 width=98%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td valign=top width=100%>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow valign=top>
								<tr>
									<td  width=5% class=browseRow nowrap align=right>2.00&nbsp;</td>
									<td  width=8% class=browseRow nowrap>ORIG</td>
									<td  width=21% class=browseRow nowrap>Large Grade B Pipe</td>
									<td width=10% class=browseRow valign=middle align=center>01-01-2008</td>
									<td  width=10% class=browseRow nowrap align=right>50</td>
									<td  width=4% class=browseRow nowrap>FT</td>
									<td  width=12% class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="95.0000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td width=15% class=browseRow valign=middle>SUPPLIER A</td>
									<td width=5% class=browseRow valign=middle><input type=radio name=awardTo1 value="A"></input></td>
								</tr>
								<tr>
									<td class=browseRow nowrap align=right id="origCopy1">&nbsp;</td>
									<td  class=browseRow nowrap id="origCopy1">&nbsp;</td>
									<td class=browseRow nowrap id="origCopy1">&nbsp;</td>
									<td class=browseRow valign=middle align=center id="origCopy1">&nbsp;</td>
									<td  class=browseRow nowrap align=right id="origCopy1">&nbsp;</td>
									<td  class=browseRow nowrap id="origCopy1">&nbsp;</td>
									<td  class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="92.0000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td class=browseRow valign=middle>SUPPLIER B</td>
									<td class=browseRow valign=middle><input type=radio name=awardTo1 value="B"></input></td>
								</tr>
								<tr><td colspan=9>&nbsp;&nbsp;<a href="javascript: viewAlternates(0); void(0);">View Alternates</a></td></tr>
								</table>
							</td>
						</tr>
						</table>

						<hr size=0 width=98%>
						<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
						<tr>
							<td valign=top width=100%>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow valign=top>
								<tr>
									<td  width=5% class=browseRow nowrap align=right>3.00&nbsp;</td>
									<td  width=8% class=browseRow nowrap>ORIG</td>
									<td  width=21% class=browseRow nowrap>Pipe Connectors</td>
									<td width=10% class=browseRow valign=middle align=center>01-01-2008</td>
									<td  width=10% class=browseRow nowrap align=right>10</td>
									<td  width=4% class=browseRow nowrap>BX</td>
									<td  width=12% class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="5.0000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td width=15% class=browseRow valign=middle>SUPPLIER A</td>
									<td width=5% class=browseRow valign=middle><input type=radio name=awardTo2 value="A"></input></td>
								</tr>
								<tr>
									<td class=browseRow nowrap align=right>&nbsp;</td>
									<td  class=browseRow nowrap>&nbsp;</td>
									<td class=browseRow nowrap>&nbsp;</td>
									<td class=browseRow valign=middle align=center>&nbsp;</td>
									<td  class=browseRow nowrap align=right>&nbsp;</td>
									<td  class=browseRow nowrap>&nbsp;</td>
									<td  class=browseRow align=right>
										<input type="text" name="bd_unitPrice" value="7.0000" style="text-align: right; border: none" size=10 readonly/>
									</td>
									<td class=browseRow valign=middle>SUPPLIER B</td>
									<td class=browseRow valign=middle><input type=radio name=awardTo2 value="B"></input></td>
								</tr>
								<tr><td colspan=9>&nbsp;&nbsp;No Alternates</td></tr>
								</table>
							</td>
						</tr>
						</table>

						<hr size=0 width=98%>

					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br>

<table border=0 cellpadding=0 cellspacing=0 width=680px>
<tr>
	<td width=20% align=center id="buttons"><a href="javascript: closeRfq(); void(0);"><img class=button src="<%=contextPath%>/images/button_close.gif" border=0 alt="Close / Cancel Solicitation"></a></td>
	<td width=20% align=center id="buttons"><a href="javascript: forward(); void(0);"><img class=button src="<%=contextPath%>/images/button_forward.gif" border=0 alt="Forward for Approval"></a></td>
	<td width=20% align=center id="buttons"><a href="javascript: createPo(); void(0);"><img class=button src="<%=contextPath%>/images/button_create_po.gif" border=0 alt="Create Purchase Order"></a></td>
	<td width=20% align=center id="buttons"><a href="javascript: printWorksheet(); void(0);"><img class=button src="<%=contextPath%>/images/button_print.gif" border=0 alt="Print / Email Worksheet"></a></td>
	<td width=20% align=center id="buttons"><a href="javascript: doSubmit('/rfq/rfq_review.jsp', 'RfqRetrieve'); void(0);"><img class=button src="<%=contextPath%>/images/button_return.gif" border=0 alt="Return"></a></td>
</tr>
</table>

<br>

<table border=0 cellspacing=0 cellpadding=0 width=680px vAlign=bottom id="copyright">
<hr size=0 color=black width=680px align=left>
<tr>
	<td class=copyright width=33% align=left>&nbsp;Copyright &copy; 2007&nbsp;<a href="http://www.puridiom.com" target="_blank">Puridiom.com</a></td>
	<td class=copyright width=34% align=center><a href="http://www.puridiom.com/procurement/legal/index.html" target="_blank">Legal Notices</a></td>
	<td class=copyright width=33% align=right>Release 3.0.-datetime-</td>
</tr>
</table>

</form>
</body>
</html>

<SCRIPT value=JavaScript>
<!-- Hide script

	frm = document.purchaseform;

	 var lineCount = 2;

	function submitAward() {
		doSubmit("/rfq/rfq_award_options.jsp", "RfqLineListUpdateVendor;RfqRetrieve");
	}

	function setAwardSelection() {
		var awardSelection = frm.awardSelectionType[frm.awardSelectionType.selectedIndex].value;
		var options = frm.awardSelectionType.length;
		var award = "byItem";
		var ind = 0;

		if (options > 1) {
			for (var i = 0; i < options; i++) {
				if (frm.awardSelectionType.options[i].selected) {
					ind = i;
					break;
				}
			}
			if (ind==0) {

				frm.awardTo0.selectedIndex = 0;

				frm.awardTo1.selectedIndex = 0;


			}
			else {

				frm.awardTo0.selectedIndex = ind-1;

				frm.awardTo1.selectedIndex = ind-1;

			}
	    }

		changePrices(0,frm.awardTo0);

		changePrices(1,frm.awardTo1);

	}

	function changePrices(ind,supplier) {

		if (supplier.value == "OFFICEMAX" && ind == 0) {
			frm.bd_unitPrice[ind].value = "95.0000";
			frm.bd_extendedPrice[ind].value = "28500.0000";
		}

		if (supplier.value == "STAPLES" && ind == 0) {
			frm.bd_unitPrice[ind].value = "0.0000";
			frm.bd_extendedPrice[ind].value = "0.0000";
		}

		if (supplier.value == "TSA" && ind == 0) {
			frm.bd_unitPrice[ind].value = "0.0000";
			frm.bd_extendedPrice[ind].value = "0.0000";
		}

		if (supplier.value == "OFFICEMAX" && ind == 1) {
			frm.bd_unitPrice[ind].value = "0.0500";
			frm.bd_extendedPrice[ind].value = "4003.8500";
		}

		if (supplier.value == "STAPLES" && ind == 1) {
			frm.bd_unitPrice[ind].value = "0.0000";
			frm.bd_extendedPrice[ind].value = "0.0000";
		}

		if (supplier.value == "TSA" && ind == 1) {
			frm.bd_unitPrice[ind].value = "0.0000";
			frm.bd_extendedPrice[ind].value = "0.0000";
		}


	}

	function viewAlternates(row) {
		displayArea("alt" + row);
		displayArea("origCopy" + row);
		setInnerHTML("altOption" + row, "&nbsp;&nbsp;<a href=\"javascript: hideAlternates(" + row + "); void(0);\">Hide Alternates</a>");
	}

	function hideAlternates(row) {
		hideArea("alt" + row);
		hideAreaWithBlock("origCopy" + row);
		setInnerHTML("altOption" + row, "&nbsp;&nbsp;<a href=\"javascript: viewAlternates(" + row + "); void(0);\">View Alternates</a>");
	}

// End Hide script -->
</SCRIPT>