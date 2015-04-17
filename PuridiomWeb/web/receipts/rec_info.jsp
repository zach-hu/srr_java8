
<%@page import="com.tsa.puridiom.common.utility.HiltonUtility"%>
<%@page import="com.tsa.puridiom.common.documents.DocumentStatus"%><div style="margin-top: 10px;margin-left: 10px;font-size: 8px;">
<%=DictionaryManager.getLabelsInstance(oid, language).getLabel(oid, "highlightedFldMessage", "Fields highlighted in yellow are required.",true)%>
</div>
<% String actionStep = (String)request.getAttribute("actionStep"); 
	if(receiptHeader != null){
		if(receiptHeader.getReceiptStatus().equals(DocumentStatus.RCV_STEP_1)){
			actionStep = "step1";
		} else if(receiptHeader.getReceiptStatus().equals(DocumentStatus.RCV_STEP_2)){
			actionStep = "step2";
		} else if(receiptHeader.getReceiptStatus().equals(DocumentStatus.RCV_STEP_3)){
			actionStep = "step3";
		}
	}
%>
<tsa:hidden name="actionStep" value="<%=actionStep%>"/>
<%
	List receiptLineListStatus = (List)request.getAttribute("receiptLineList");
	boolean enableStep1 = false;
	boolean enableStep2 = false;
	boolean enableStep3 = false;
	boolean s_inspectionRequired = false;
	boolean s_markTagRequired = false;
	int count = 0;
	boolean allRejected = false;
	String s_inspectorAssgned = "";
	String s_engineerAssigned = "";
	int s_i_linecount = 0;
	if(receiptLineListStatus != null){
		s_i_linecount = receiptLineListStatus.size();
	}
	for (int i = 0; i < s_i_linecount; i++){
		ReceiptLine receiptLineStatus = (ReceiptLine)receiptLineListStatus.get(i);
		if(receiptLineStatus.getStatus().equals(DocumentStatus.RCV_INPROGRESS) && receiptLineStatus.getQtyStep0Received().equals(new BigDecimal("0")))
		{
			count++;
		}
		if(receiptLineStatus.getStatus().compareTo(DocumentStatus.RCV_STEP_1) >= 0 && receiptLineStatus.getInspectionRequired().equalsIgnoreCase("Y")){
			enableStep1 = true;
		}
		if(receiptLineStatus.getStatus().equals(DocumentStatus.RCV_STEP_2)){
			enableStep2 = true;
		}
		if(receiptLineStatus.getStatus().equals(DocumentStatus.RCV_STEP_3)){
			enableStep3 = true;
		}
		if(receiptLineStatus.getInspectionRequired().equals("Y")){
			s_inspectionRequired = true;
			s_inspectorAssgned = receiptLineStatus.getInspectorAssigned();
			s_engineerAssigned = receiptLineStatus.getEngineerAssigned();
		}
		if(receiptLineStatus.getMarkTagRequired() != null && !"N".equals(receiptLineStatus.getMarkTagRequired()) && !"".equals(receiptLineStatus.getMarkTagRequired()) ){
			s_markTagRequired = true;
		}
		if (receiptLineStatus.getStatus().equals(DocumentStatus.RCV_INPROGRESS) && (HiltonUtility.ckNull(receiptLineStatus.getQtyStep0Received()).compareTo(new BigDecimal("0")) > 0 &&
				HiltonUtility.ckNull(receiptLineStatus.getQtyStep0Rejected()).compareTo(HiltonUtility.ckNull(receiptLineStatus.getQtyStep0Received())) == 0)){
			count++;
		}
		if(count == s_i_linecount){
			allRejected = true;
		}
	}
%>