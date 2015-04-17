package com.tsa.puridiom.receiptheader.history;

import java.math.BigDecimal;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.ReceiptType;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.historylog.tasks.HistoryLogSetupValues;
import com.tsagate.foundation.utility.Utility;

public class ReceiptHistorySetupValues extends HistoryLogSetupValues
{
	public HistoryLog getHeaderHistoryLog(ReceiptHeader newHeader)
	{
		HistoryLog history = null;
		history = this.getHistoryRecord(newHeader);

		return history;
	}

	public HistoryLog setText(ReceiptHeader header, HistoryLog history)
	{
		HistoryLogReceiptText text = new HistoryLogReceiptText(header);
		text.setOrganizationId(this.getOrganizationId());

		String historyStatus = (String)this.getHistoryStatus();
		String status = header.getReceiptStatus();
		String type = header.getReceiptType();

		if(status.equals(DocumentStatus.RCV_INPROGRESS) && Utility.isEmpty(historyStatus)) {
			history.setDescription(text.createText());
		}
		else if(status.equals(DocumentStatus.RCV_STEP_1) && Utility.isEmpty(historyStatus)) {
			history.setDescription(text.forwardText("Inspection"));
		}
		else if(status.equals(DocumentStatus.RCV_STEP_2) && Utility.isEmpty(historyStatus)) {
			history.setDescription(text.forwardText("Mark/Tag"));
		}
		else if(status.equals(DocumentStatus.RCV_STEP_3) && Utility.isEmpty(historyStatus)) {
			history.setDescription(text.forwardText("Delivery"));
		}
		else if(status.equals(DocumentStatus.RCV_PENDINGFINALIZATION) && Utility.isEmpty(historyStatus)) {
			history.setDescription(text.forwardText(this.getForwardedTo()));
		}
		else if(status.equals(DocumentStatus.RCV_RECEIVED) && type.equalsIgnoreCase(ReceiptType.FULL_RECEIPT_FROM_PO) && Utility.isEmpty(historyStatus)) {
			history.setDescription(text.forwardText());
		}
		else if(status.equals(DocumentStatus.RCV_RECEIVED) && Utility.isEmpty(historyStatus)) {
			history.setDescription(text.finalizeText());
		}
		else {
			history = null;
		}
		return history;
	}

	public HistoryLog getHistoryRecord(ReceiptHeader header)
	{
		HistoryLog history = this.recHeaderSetup(null, header);
		history = this.setText(header, history);

		return history;
	}

	public HistoryLog recHeaderSetup(HistoryLog history, ReceiptHeader header)
	{
		if(history == null) {
			history = this.setUpHistory(history);
		}
		history.setIcHeaderHistory(header.getIcHeaderHistory());
		history.setIcLineHistory(new BigDecimal("0"));
		history.setIcHeader(header.getIcRecHeader());
		history.setIcLine(new BigDecimal("0"));
		history.setDoctype("RCH");
		history.setStatus(header.getReceiptStatus());
		history.setUserid(this.getUserId());

		return history;
	}
}
