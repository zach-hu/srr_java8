<%@ page import="com.tsa.puridiom.graphs.*"%>
<%@ page import="java.io.PrintWriter" %>

<%
	Map data = MenuManager.getGraphData(oid, user.getVendorId(), user.getContactCode());

//*****************  orders by status  *****************
	String poStatusGraph = "";
	boolean poStatusGraphHasData = false;

	if ( !user.isGuest() )
	{
		OrdersByStatusGraphData poData = new OrdersByStatusGraphData(data);
		if (poData != null) {
			GraphParams params = new GraphParams();
		    params.setHeigth(200);
		    params.setWidth(400);
		    params.setSession(session);
		    params.setWriter(new PrintWriter(out));
		    params.setXTitle("Purchase Orders");
		    params.setContextPath(request.getContextPath());
		    params.setOid(oid);
		    params.setFormType("po");

			poStatusGraph = poData.getPoBarChart(params);
			poStatusGraphHasData = params.getHasData();
		}
	}
//*****************  solicitations by status  *****************
	String rfqByStatusGraph = "";
	boolean rfqByStatusGraphHasData = false;
/*
	RfqsByStatusGraphData rfqByStatusData = new RfqsByStatusGraphData(data);
	GraphParams rfqByStatusparams = new GraphParams();
	rfqByStatusparams.setHeigth(170);
	rfqByStatusparams.setWidth(400);
	rfqByStatusparams.setSession(session);
	rfqByStatusparams.setWriter(new PrintWriter(out));
	rfqByStatusparams.setXTitle("Solicitations");
    rfqByStatusparams.setContextPath(request.getContextPath());
    rfqByStatusparams.setOid(oid);
    rfqByStatusparams.setFormType("rfq");

	rfqByStatusGraph = rfqByStatusData.getBarChart(rfqByStatusparams);
	rfqByStatusGraphHasData = rfqByStatusparams.getHasData();
*/
%>

<script language='Javascript1.2' type="text/javascript">
<!--

	poStatusGraph = '<%=poStatusGraph%>';
	rfqByStatusGraph = '<%=rfqByStatusGraph%>';

//-->
</script>