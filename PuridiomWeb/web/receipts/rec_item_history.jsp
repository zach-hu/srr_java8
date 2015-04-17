						<div id="itemReceiptHistory" style="visibility: hidden; display: none;">
						<table border=1 cellspacing=0 cellpadding=0 width=100% class=browseHdrDk>
						<tr>
							<td width=100% height=18px class=browseHdrDk>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseHdrDk>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-receiptNumber")%> width=13% class=browseHdrDk>&nbsp;<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptNumber", "Receipt #")%><b></b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-receiptType")%> width=12% class=browseHdrDk>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receiptType", "Type")%></b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-transactionDate")%> width=18% class=browseHdrDk>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-transactionDate", "Trans. Date")%></b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-enteredBy")%> width=14% class=browseHdrDk>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-receivedBy", "Received By")%></b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%> width=14% class=browseHdrDk>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "hdg-rec-packingSlip", "Pkg. Slip/RMA #")%></b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=9% class=browseHdrDk align=right>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReceived", "Received")%></b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=9% class=browseHdrDk align=right>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityRejected", "Rejected")%></b></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-quantityReturned")%> width=8% class=browseHdrDk align=right>&nbsp;<b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-quantityReturned", "Returned")%></b></td>
									<td width=3% class=browseHdrDk align=right valign=bottom><a href="javascript: hideReceiptHistory(<%=ipl%>); void(0);"><img src="<%=contextPath%>/images/bar_close.gif" border=0 alt='Close Receipt History'></a></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table border=0 cellspacing=0 cellpadding=2 width=100% class=browseRow>
<%		BigDecimal qtyPrevRejected = new BigDecimal(0);
			if (receiptLineList != null) {
				for (int irl = 0; irl < receiptLineList.size(); irl++) {
					ReceiptLine recLine = (ReceiptLine) receiptLineList.get(irl);
					qtyPrevRejected = qtyPrevRejected.add(recLine.getQtyRejected());
%>
								<tr>
									<td <%=HtmlWriter.isVisible(oid, "rec-receiptNumber")%> width=13% class=browseRow><%=recLine.getReceiptNumber()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-receiptType")%> width=12% class=browseRow><%=ReceiptType.toString(recLine.getReceiptType(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-transactionDate")%> width=18% class=browseRow nowrap><%=HiltonUtility.getFormattedDate(recLine.getReceiptDate(), oid, userDateFormat)%>&nbsp;<%=HiltonUtility.getFormattedTimeZone(recLine.getTimeZone())%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-enteredBy")%> width=14% class=browseRow><%=(UserManager.getInstance().getUser(oid, recLine.getReceivedBy())).getDisplayName()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-packingSlip")%> width=14% class=browseRow><%=recLine.getPackingSlip()%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-quantityReceived")%> width=9% class=browseRow align=right><%=HiltonUtility.getFormattedQuantity(recLine.getQtyReceived(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-quantityRejected")%> width=9% class=browseRow align=right><%=HiltonUtility.getFormattedQuantity(recLine.getQtyRejected(), oid)%></td>
									<td <%=HtmlWriter.isVisible(oid, "rec-quantityReturned")%> width=8% class=browseRow align=right><%=HiltonUtility.getFormattedQuantity(recLine.getQtyReturned(), oid)%></td>
									<td width=3% class=browseRow>&nbsp;</td>
								</tr>

<%				if (!HiltonUtility.isEmpty(recLine.getInspectionCode())) {%>
								<tr>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=25% align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-inspectionCode", "Inspection Code")%>:</td>
											<td><%=recLine.getInspectionCode()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
					if (!HiltonUtility.isEmpty(recLine.getRejectionCode())) {%>
								<tr>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=25% align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-rejectionCode", "Rejection Code")%>:</td>
											<td><%=recLine.getRejectionCode()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
					if (!HiltonUtility.isEmpty(recLine.getDispositionCode())) {%>
								<tr>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr <%=HtmlWriter.isVisible(oid, "rec-dispositionCode")%> >
											<td width=25% align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-dispositionCode", "Disposition Code")%>:</td>
											<td><%=recLine.getDispositionCode()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
					if (!HiltonUtility.isEmpty(recLine.getUdf1Code())) {%>
								<tr <%=HtmlWriter.isVisible(oid, "rec-LN01")%>>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=25% align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN01", "Line UDF 1")%>:</td>
											<td><%=recLine.getUdf1Code()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
					if (!HiltonUtility.isEmpty(recLine.getUdf2Code())) {%>
								<tr <%=HtmlWriter.isVisible(oid, "rec-LN02")%>>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=25% align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN02", "Line UDF 2")%>:</td>
											<td><%=recLine.getUdf2Code()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
					if (!HiltonUtility.isEmpty(recLine.getUdf3Code())) {%>
								<tr <%=HtmlWriter.isVisible(oid, "rec-LN03")%>>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=25% align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN03", "Line UDF 3")%>:</td>
											<td><%=recLine.getUdf3Code()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
					if (!HiltonUtility.isEmpty(recLine.getUdf4Code())) {%>
								<tr <%=HtmlWriter.isVisible(oid, "rec-LN04")%>>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=25% align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN04", "Line UDF 4")%>:</td>
											<td><%=recLine.getUdf4Code()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
					if (!HiltonUtility.isEmpty(recLine.getUdf5Code())) {%>
							<%if (!oid.equalsIgnoreCase("vse06p")) { %>
								<tr <%=HtmlWriter.isVisible(oid, "rec-LN05")%>>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=25% align=right><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN05", "Line UDF 5")%>:</td>
											<td><%=recLine.getUdf5Code()%></td>
										</tr>
										</table>
									</td>
								</tr>
							<% } %>
<%				}
					if (!HiltonUtility.isEmpty(recLine.getReceiptNotes())) {%>
								<tr>
									<td colspan=8>
										<table border=0 cellpadding=2 cellspacing=0 width=87%>
										<tr>
											<td width=25% align=right valign=top>Notes:</td>
											<td valign=top><%=recLine.getReceiptNotes()%></td>
										</tr>
										</table>
									</td>
								</tr>
<%				}
				}
			}%>
								</table>
								<tsa:hidden name="qtyPrevRejected" value="<%=qtyPrevRejected%>"/>
							</td>
						</tr>
						</table>
						</div>
