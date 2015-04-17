<%@ page import="com.tsa.puridiom.graphs.*"%>

<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.tsa.puridiom.usermanager.UserManager" %>

<%
Map data = MenuManager.getGraphData(oid, uid);

String	reqGraph = "";
String	poCountGraph = "";
String	spendBySupplierGraph = "";
String	spendByCommodityGraph = "";
String	spendByDepartmentGraph = "";
String	requestsByCommodityGraph = "";
boolean reqGraphHasData = false;
boolean poGraphHasData = false;
boolean rfqByStatusGraphHasData = false;
boolean spendBySupplierGraphHasData = false;
boolean spendByCommodityGraphHasData = false;
boolean spendByDepartmentGraphHasData = false;
boolean requestsByCommodityGraphHasData = false;
String invoiceGraphImg = "";
BigDecimal bdFormWidth = new BigDecimal(formWidth.replaceAll("px", " ").trim());
BigDecimal bdLeftWidth = bdFormWidth.multiply(new BigDecimal("0.23"));
BigDecimal bdTopLeftWidth = bdFormWidth.multiply(new BigDecimal("0.5"));
int ileftWidth = bdLeftWidth.intValue() - 15;
int itopLeftWidth = bdTopLeftWidth.intValue() - 15;

if(role.getAccessRights("REQ") > 0 && user.isARequisitioner())
{
	RequisitionerReqCountGraphData requisitionerData = new RequisitionerReqCountGraphData(data);
	GraphParams params = new GraphParams();
    params.setHeigth(200);
    params.setWidth(ileftWidth);
    params.setSession(session);
    params.setWriter(new PrintWriter(out));
    params.setXTitle("Requisitions");
    //params.setTitle("My Requisition Actions");
    params.setContextPath(request.getContextPath());
    params.setOid(oid);

	String graphImg = requisitionerData.getBarChart(params);
	//String graphImg = requisitionerData.getPieChart(pieParams);

	out.println("<!-- graphImg = " + graphImg + " -->");

	reqGraph = graphImg;
	reqGraphHasData = params.getHasData();
	
	if (role.getAccessRights("PO") <= 0 || !user.isABuyer()) {
		/**	Requests By Commodity	**/
		SpendByCommodityGraphData spendByCommodityData = new SpendByCommodityGraphData(data);
		PieParams commodityParams = new PieParams();
	    commodityParams.setHeigth(200);
	    commodityParams.setWidth(itopLeftWidth);
	    commodityParams.setSession(session);
	    commodityParams.setWriter(new PrintWriter(out));
	    commodityParams.setFormType("spendbycommodity") ;
	    commodityParams.setContextPath(request.getContextPath());
	    commodityParams.setOid(oid);
	
		requestsByCommodityGraph = spendByCommodityData.getPieChart(commodityParams);
		requestsByCommodityGraphHasData = commodityParams.getHasData();			
	}
}

if(role.getAccessRights("PO") > 0 && user.isABuyer())
{
	/**	Spend By Supplier	**/
	SpendBySupplierGraphData spendBySupplierData = new SpendBySupplierGraphData(data);
	GraphParams supplierParams = new GraphParams();
    supplierParams.setHeigth(200);
    supplierParams.setWidth(ileftWidth);
    supplierParams.setSession(session);
    supplierParams.setWriter(new PrintWriter(out));
    supplierParams.setXTitle("Suppliers");
    supplierParams.setYTitle("Total Spend");
    supplierParams.setFormType("spend") ;
    supplierParams.setContextPath(request.getContextPath());
    supplierParams.setOid(oid);

	spendBySupplierGraph = HiltonUtility.encodingString(spendBySupplierData.getVerticalBarChart(supplierParams));
	spendBySupplierGraphHasData = supplierParams.getHasData();

	/**	Spend By Commodity	**/
	SpendByCommodityGraphData spendByCommodityData = new SpendByCommodityGraphData(data);
	PieParams commodityParams = new PieParams();
    commodityParams.setHeigth(200);
    commodityParams.setWidth(itopLeftWidth);
    commodityParams.setSession(session);
    commodityParams.setWriter(new PrintWriter(out));
//    commodityParams.setXTitle("Commodity");
//    commodityParams.setYTitle("Total Spend");
    commodityParams.setFormType("spendbycommodity") ;
    commodityParams.setContextPath(request.getContextPath());
    commodityParams.setOid(oid);

	spendByCommodityGraph = spendByCommodityData.getPieChart(commodityParams);
	spendByCommodityGraphHasData = commodityParams.getHasData();

	/**	Spend By Department	**/
	SpendByDepartmentGraphData spendByDepartmentData = new SpendByDepartmentGraphData(data);
	GraphParams departmentParams = new GraphParams();
    departmentParams.setHeigth(200);
    departmentParams.setWidth(ileftWidth);
    departmentParams.setSession(session);
    departmentParams.setWriter(new PrintWriter(out));
    departmentParams.setXTitle("Department");
    departmentParams.setYTitle("Total Spend");
    departmentParams.setFormType("spendbydepartment") ;
    departmentParams.setTickUnits(5000);
    departmentParams.setContextPath(request.getContextPath());
    departmentParams.setOid(oid);

	spendByDepartmentGraph = HiltonUtility.encodingString(spendByDepartmentData.getVerticalBarChart(departmentParams));

	spendByDepartmentGraphHasData = departmentParams.getHasData();
}

if(role.getAccessRights("PO-Pie") > 0 && user.isABuyer())
{
	BuyerPoCountGraphData poData = new BuyerPoCountGraphData(data);

	PieParams pieParams = new PieParams();
    pieParams.setHeigth(200);
    pieParams.setWidth(400);
    pieParams.setSession(session);
    pieParams.setWriter(new PrintWriter(out));
    pieParams.setContextPath(request.getContextPath());
    pieParams.setOid(oid);

	poCountGraph = poData.getPoPieChart(pieParams);
	poGraphHasData = pieParams.getHasData();
}

//**************************solicitations by status **********************
	String rfqByStatusGraph = "";
	if(role.getAccessRights("RFQ") > 0)
	{
		RfqsByStatusGraphData rfqByStatusData = new RfqsByStatusGraphData(data);
		GraphParams rfqByStatusparams = new GraphParams();
		rfqByStatusparams.setHeigth(170);
		rfqByStatusparams.setWidth(ileftWidth);
		rfqByStatusparams.setSession(session);
		rfqByStatusparams.setWriter(new PrintWriter(out));
		rfqByStatusparams.setXTitle("Solicitations");
	    rfqByStatusparams.setContextPath(request.getContextPath());
	    rfqByStatusparams.setOid(oid);
	    rfqByStatusparams.setFormType("rfq");

		String rfqByStatusGraphImg = rfqByStatusData.getBarChart(rfqByStatusparams);

		out.println("<!-- rfqbustatusgraphImg = " + rfqByStatusGraphImg + " -->");

		rfqByStatusGraph = rfqByStatusGraphImg;
		rfqByStatusGraphHasData = rfqByStatusparams.getHasData();
	}

//**************************solicitations by status **********************

//	**************************** start invoice status **************************
	boolean invoiceStatusGraphHasData = false;
	if(role.getAccessRights("VOUCHERS") > 0)
	{
		InvoiceStatusMenuGraphData invoiceData = new InvoiceStatusMenuGraphData(data);
		GraphParams invoiceParams = new GraphParams();
		invoiceParams.setHeigth(170);
		invoiceParams.setWidth(200);
		invoiceParams.setSession(session);
		invoiceParams.setWriter(new PrintWriter(out));
		invoiceParams.setXTitle("Invoice Status");
	    //params.setTitle("My Requisition Actions");
	    invoiceParams.setContextPath(request.getContextPath());
	    invoiceParams.setOid(oid);
	    invoiceParams.setFormType("vch");

		invoiceGraphImg = invoiceData.getBarChart(invoiceParams);
		//out.println("<!-- graphImg = " + invoiceGraphImg + " -->");
		invoiceStatusGraphHasData = invoiceParams.getHasData();
	}
//**************************** end invoice status **************************
%>
<script language='Javascript1.2' type="text/javascript">
<!--
	reqGraph = '<%=reqGraph%>';
	rfqByStatusGraph = '<%=rfqByStatusGraph%>';
	approvedReqs = '';
	invoiceStatusGraph = '<%=invoiceGraphImg%>';
	spendBySupplierGraph = '<%=spendBySupplierGraph%>';
	spendByCommodityGraph = '<%=spendByCommodityGraph%>';
	spendByDepartmentGraph = '<%=spendByDepartmentGraph%>';
	requestsByCommodityGraph = '<%=requestsByCommodityGraph%>';
//-->
</script>