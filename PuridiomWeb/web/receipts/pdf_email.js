function emailPdf()
{
	popupParameters = "ReceiptHeader_icRecHeader=<%=icRecHeader%>";
	popupParameters = popupParameters + ";uid=<%=uid%>";
	popupParameters = popupParameters + ";oid=<%=oid%>";
	if(checkemail())
	{
		frm.viewNow.value = 'N';
		frm.emailTo.value = 'Y';
		doSubmit('receipts/rec_print_pdf.jsp', 'EmailRecPdf;ReceiptRetrieve');
	}
}