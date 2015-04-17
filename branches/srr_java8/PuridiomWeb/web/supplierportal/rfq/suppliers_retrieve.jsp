<%
/*******	RETRIEVES ALL SUPPLIERS FOR THE SOLICITATION	******/

	io.setComponentName( "RFQ" );
	io.setMethodName( "jag_supplier_retrieve" );

	io.setInputValue( "header_ic", ss_hic );

	oo = BeanJag.retrieve(io);

	retval = oo.getRetval();

	if (retval != 1)
	{
		String	errorstr = Form.ckNull(oo.getOutValue("errorMsg"));

		errorstr = "<BR>An error occurred retrieving the list of bidders.<BR>" + errorstr;

		sessionId.putValue("s_errorstr", errorstr);
		response.sendRedirect("/puridiom/xbidboard/error_pg.jsp?err=retrieve");
	}

	int	i_suppliers = BeanJag.getRowCount( "suppliers" );
	int	i_supplier_row = 0;
	String	s_bids_entered = "false";

	for (int ir = 1; ir < i_suppliers; ir++)
	{
		if ( (BeanJag.getValue("suppliers", "vendor_id", ir).trim()).equals(ss_supplier) ) {
			s_bids_entered = BeanJag.getValue("suppliers", "bids_entered", ir);
			i_supplier_row = ir;
			ir = i_suppliers;

			b_exists = true;
/*			if ( s_bids_entered.equalsIgnoreCase("true") ) {
				b_bids_entered = true;
			}
*/
		}
	}
%>