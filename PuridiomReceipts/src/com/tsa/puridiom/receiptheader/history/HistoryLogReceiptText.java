package com.tsa.puridiom.receiptheader.history;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.historylog.tasks.HistoryBaseText;
import com.tsagate.foundation.utility.Utility;

public class HistoryLogReceiptText extends HistoryBaseText
{
	public HistoryLogReceiptText(Object rHeader, Object rLine) {
		super(rHeader, rLine);
	}

	public HistoryLogReceiptText(ReceiptHeader rHeader) {
		super(rHeader, null);
	}

	public HistoryLogReceiptText(ReceiptLine rLine) {
		super(null, rLine);
	}

	public String headerText(String text) {
		return super.headerText(text, this.getRecHeader().getReceiptNumber());
	}

	public ReceiptHeader getRecHeader() {
		return (ReceiptHeader)this.getHeader();
	}

	public String createText() {
		return this.headerText("Created Receipt #");
	}

	public String forwardText() {
		return this.headerText("Forwarded Receipt #");
	}

	public String forwardText(String user) {
		StringBuffer sb = new StringBuffer(this.headerText("Forwarded Receipt #"));
		if(!Utility.isEmpty(user)) {
			sb.append(" to " + user);
		}
		return sb.toString();
	}
	
	public String forwardText(String user, String reason) {
		StringBuffer sb = new StringBuffer(this.headerText("Forwarded Receipt #"));
		if(!Utility.isEmpty(user)) {
			sb.append(" to " + user);
		}
		
		if(!Utility.isEmpty(reason)) {
			sb.append(" for " + reason);
		}
		return sb.toString();
	}

	public String finalizeText() {
		return this.headerText("Finalized Receipt #");
	}
}
