
		<table border=0 cellpadding=0 cellspacing=0 width=645px>
		<tr height=18px>
			<td colspan=5 align=center>&nbsp;</td>
		</tr>
		</table>

		<table border="0" cellpadding="0" cellspacing="0" width="645px">
		<tr class="mnav" height="18px">
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "dateTime", "Date/Time")%></td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "user", "User")%></td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivingHistoryDescription", "Receiving History Description")%></td>
			<td width="15px" class="mnav">&nbsp;</td>
			<td nowrap class="mnav"><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "receivingHistoryType", "Type")%></td>
			<td></td>
		</tr>
<%	if (receiptHeaderList != null)
		{
			for (int i = 0; i < receiptHeaderList.size(); i++)
			{
				ReceiptHeader receiptHeader = (ReceiptHeader) receiptHeaderList.get(i);

				String	s_user = receiptHeader.getReceivedBy();
				StringBuffer	description = new StringBuffer("");
				String oidPackage = "";
				if(oid.equalsIgnoreCase("DTN07P")){
					oidPackage = receiptHeader.getPkgsReceived().toBigInteger().toString();
				}
				else{
					oidPackage = receiptHeader.getPkgsReceived().toString();
				}
				if (receiptHeader.getReceiptType().equals(ReceiptType.ORIGINAL)  ||
						receiptHeader.getReceiptType().equals(ReceiptType.FULL_RECEIPT_FROM_PO)) {
					if (receiptHeader.getPkgsReceived().compareTo(new BigDecimal(0)) > 0) {
						description.append("  Received  " + oidPackage + " package(s) on Receipt # " + receiptHeader.getReceiptNumber() + ".");
					} else {
						description.append("  Received item(s) on Receipt # " + receiptHeader.getReceiptNumber() + ".");
					}
				} else if (receiptHeader.getReceiptType().equals(ReceiptType.RETURN)) {
					if (receiptHeader.getPkgsReceived().compareTo(new BigDecimal(0)) > 0) {
						description.append("  Returned " + oidPackage + " package(s) on Receipt # " + receiptHeader.getReceiptNumber() + ".");
					} else {
						description.append("  Returned item(s) on Receipt # " + receiptHeader.getReceiptNumber() + ".");
					}
				} else if (receiptHeader.getReceiptType().equals(ReceiptType.ADJUSTMENT)) {
					description.append("  Adjusted Receipt # " + receiptHeader.getReceiptNumber() + ".");
				}

				if (!HiltonUtility.isEmpty(receiptHeader.getPackingSlip())) {
					if (receiptHeader.getReceiptType().equals(ReceiptType.RETURN)) {
						description.append("  RMA #: " + receiptHeader.getPackingSlip());
					} else {
						description.append("  Packing slip #: " + receiptHeader.getPackingSlip());
					}
				}
				if (!HiltonUtility.isEmpty(receiptHeader.getCarrierCode())) {
					description.append("  Carrier Code: " + receiptHeader.getCarrierCode());
				}

				UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
				String  s_timeZone = HiltonUtility.getFormattedTimeZone(receiptHeader.getTimeZone());
%>
			<tr height=18px>
				<td nowrap valign=top><%=HiltonUtility.getFormattedDate(receiptHeader.getReceiptDate(), oid, userDateFormat)%>&nbsp;<%=s_timeZone%></td>
				<td width=15px>&nbsp;</td>
				<td nowrap valign=top><%=userProfile.getDisplayName()%></td>
				<td width=15px>&nbsp;</td>
				<td valign=top><%=description.toString()%></td>
				<td width=15px>&nbsp;</td>
				<td valign=top><%=receiptHeader.getReceiptType()%></td>
				<td nowrap="nowrap">
					<a href="javascript: viewRecNow('<%=receiptHeader.getIcRecHeader()%>'); void(0);"><img class="button" src="<%=contextPath%>/images/adobe_pdf.gif" border="0" alt="View Pdf" ></a>
				<a href="javascript: rcvShowEmail('<%=receiptHeader.getIcRecHeader()%>', 'REC', '<%=receiptHeader.getReceiptNumber()%>'); void(0);"><img src="<%=contextPath%>/images/email.gif" border="0" alt="Email" ></a>
				</td>
			</tr>
<%
			}
		}
		if (receiptLineList != null)
		{
			if (!HiltonUtility.isEmpty(uom)) {
				uom = " (" + uom + ") ";
			}
			for (int i = 0; i < receiptLineList.size(); i++)
			{
				ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
				String	s_user = receiptLine.getReceivedBy();
				String	s_notes = receiptLine.getReceiptNotes();
				String  s_timeZone = HiltonUtility.getFormattedTimeZone(receiptLine.getTimeZone());
				StringBuffer	description = new StringBuffer("");

				if (receiptLine.getReceiptType().equals(ReceiptType.ORIGINAL)) {
					//description.append("  Quantity: " + receiptLine.getQtyReceived() + " " + uom + "Received on Receipt # " + receiptLine.getReceiptNumber() + ".<br>");
					description.append("  Received " + receiptLine.getQtyReceived() + " " + uom);
					if (receiptLine.getQtyRejected().compareTo(new BigDecimal(0)) > 0) {
						description.append("/ Rejected " + receiptLine.getQtyRejected() + " " + uom);
					}
					description.append(" on Receipt # " + receiptLine.getReceiptNumber() + ".<br>");
				} else if (receiptLine.getReceiptType().equals(ReceiptType.RETURN)) {
					description.append("  Quantity: " + receiptLine.getQtyReturned() + " " + uom + "Returned on Receipt # " + receiptLine.getReceiptNumber() + ".<br>");
				} else if (receiptLine.getReceiptType().equals(ReceiptType.ADJUSTMENT)) {
					description.append("  Quantity: " + receiptLine.getQtyReceived() + " " + uom + "Adjusted on Receipt # " + receiptLine.getReceiptNumber() + ".<br>");
				}

				if (!HiltonUtility.isEmpty(receiptLine.getPackingSlip())) {
					if (receiptLine.getReceiptType().equals(ReceiptType.RETURN)) {
						description.append("  RMA #: " + receiptLine.getPackingSlip() + ".");
					} else {
						description.append("  Packing slip #: " + receiptLine.getPackingSlip() + ".");
					}
				}
				if (!HiltonUtility.isEmpty(receiptLine.getInspectionCode())) {
					description.append("  Inspection Code: " + receiptLine.getInspectionCode() + ".");
				}
				if (!HiltonUtility.isEmpty(receiptLine.getRejectionCode())) {
					description.append("  Rejection Code: " + receiptLine.getRejectionCode() + ".");
				}
				if (!HiltonUtility.isEmpty(receiptLine.getDispositionCode())) {
					description.append("  Disposition Code: " + receiptLine.getDispositionCode() + ".");
				}
				if (!HiltonUtility.isEmpty(receiptLine.getUdf1Code())) {
					description.append("  " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN01", "Line UDF 1") + ": " + receiptLine.getUdf1Code() + ".");
				}
				if (!HiltonUtility.isEmpty(receiptLine.getUdf2Code())) {
					description.append("  " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN02", "Line UDF 2") + ": " + receiptLine.getUdf2Code() + ".");
				}
				if (!HiltonUtility.isEmpty(receiptLine.getUdf3Code())) {
					description.append("  " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN03", "Line UDF 3") + ": " + receiptLine.getUdf3Code() + ".");
				}
				if (!HiltonUtility.isEmpty(receiptLine.getUdf4Code())) {
					description.append("  " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN04", "Line UDF 4") + ": " + receiptLine.getUdf4Code() + ".");
				}
				if (!HiltonUtility.isEmpty(receiptLine.getUdf5Code())) {
					description.append("  " + DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "rec-LN05", "Line UDF 5") + ": " + receiptLine.getUdf5Code() + ".");
				}
				UserProfile userProfile = UserManager.getInstance().getUser(oid, s_user);
%>
			<tr height=18px>
				<td nowrap valign=top><%=HiltonUtility.getFormattedDate(receiptLine.getReceiptDate(), oid, userDateFormat)%> <%=receiptLine.getReceiptDate()%>&nbsp;<%=s_timeZone%></td>
				<td width=15px>&nbsp;</td>
				<td nowrap valign=top><%=userProfile.getDisplayName()%></td>
				<td width=15px>&nbsp;</td>
				<td valign=top><%=description.toString()%></td>
			</tr>
<%
			}
		}
		if ((receiptHeaderList == null || receiptHeaderList.size() <= 0) && (receiptLineList == null || receiptLineList.size() <= 0)) {%>
			<tr height="18px">
				<td colspan="5" align="center"><b><%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "noReceivingHistoryAvailable", "There is no receiving history available at this time")%>!</b></td>
			</tr>
<%	}%>
			</table>
				<div align="center">
				<iframe id="recframe" name="recframe" height="85px" width="230px" marginheight="0" hspace="0" frameborder="0" scrolling="no" src="" style="position: absolute; border:none; overflow-y:hidden; overflow-x:hidden; display:none;"></iframe>
				</div>
