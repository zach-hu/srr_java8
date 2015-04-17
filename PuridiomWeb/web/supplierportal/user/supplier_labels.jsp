<%
/*****	SUPPLIER ADDRESS LABELS  ******/

	String	slb_address_line1	= "Company Name"	;
	String	slb_address_line2	= "Street Address"	;
	String	slb_address_line3	= "Floor/Suite"		;
	String	slb_address_line4	= "Street (line 2)"	;
	String	slb_city		= "City"		;
	String	slb_state		= "State"		;
	String	slb_zip			= "Zip Code"		;
	String	slb_country		= "Country"		;
	String	slb_fax			= "Fax"			;


/*****	SUPPLIER ADDRESS HIDDEN VARIABLES  *****/

	boolean	b_display_address_line1	= true	;
	boolean	b_display_address_line2	= true	;
	boolean	b_display_address_line3	= true	;
	boolean	b_display_address_line4	= true	;
	boolean	b_display_city		= true	;
	boolean	b_display_state		= true	;
	boolean	b_display_zip		= true	;
	boolean	b_display_country	= true	;
	boolean	b_display_fax		= true	;


/*****	SUPPLIER ADDRESS FIELDS REQUIRED  *****/

	boolean	brq_address_line1	= true	;
	boolean	brq_address_line2	= true	;
	boolean	brq_address_line3	= true	;
	boolean	brq_address_line4	= true	;
	boolean	brq_city		= true	;
	boolean	brq_state		= true	;
	boolean	brq_zip			= true	;
	boolean	brq_country		= true	;
	boolean	brq_fax			= true	;


/*****	SUPPLIER CONTACT LABELS  ******/

	String	slb_first_name	= "First Name"		;
	String	slb_middle_init	= "MI"			;
	String	slb_last_name	= "Last Name"		;
	String	slb_title	= "Title"		;
	String	slb_email_addr	= "Email Address"	;
	String	slb_phone	= "Phone"		;
	String	slb_contact_fax	= "Fax"			;


/*****	SUPPLIER CONTACT HIDDEN VARIABLES  ******/

	boolean	b_display_first_name	= true	;
	boolean	b_display_middle_init	= true	;
	boolean	b_display_last_name	= true	;
	boolean	b_display_title		= true	;
	boolean	b_display_email_addr	= true	;
	boolean	b_display_phone		= true	;
	boolean	b_display_contact_fax	= true	;


/*****	SUPPLIER CONTACT REQUIRED FIELDS  ******/

	boolean	brq_first_name	= true	;
	boolean	brq_middle_init	= true	;
	boolean	brq_last_name	= true	;
	boolean	brq_title	= true	;
	boolean	brq_email_addr	= true	;
	boolean	brq_phone	= true	;
	boolean	brq_contact_fax	= true	;


/*****	ALTERNATE CONTACT LABELS  ******/

	String	slb_alt_first_name	= "First Name"		;
	String	slb_alt_middle_init	= "MI"			;
	String	slb_alt_last_name	= "Last Name"		;
	String	slb_alt_title		= "Title"		;
	String	slb_alt_email_addr	= "Email Address"	;
	String	slb_alt_phone		= "Phone"		;
	String	slb_alt_contact_fax	= "Fax"			;


/*****	ALTERNATE CONTACT HIDDEN VARIABLES  ******/

	boolean	b_display_alt_first_name	= true	;
	boolean	b_display_alt_middle_init	= true	;
	boolean	b_display_alt_last_name		= true	;
	boolean	b_display_alt_title		= true	;
	boolean	b_display_alt_email_addr	= true	;
	boolean	b_display_alt_phone		= true	;
	boolean	b_display_alt_contact_fax	= true	;


/*****	ALTERNATE CONTACT REQUIRED FIELDS  ******/

	boolean	brq_alt_first_name	= true	;
	boolean	brq_alt_middle_init	= true	;
	boolean	brq_alt_last_name	= true	;
	boolean	brq_alt_title		= true	;
	boolean	brq_alt_email_addr	= true	;
	boolean	brq_alt_phone		= true	;
	boolean	brq_alt_contact_fax	= true	;


/*****	SUPPLIER COMPANY LABELS  ******/

	String	slb_duns		= "Dun & Bradstreet Number"	;
	String	slb_sic			= "Primary SIC Code"		;
	String	slb_sic_instructions	= "Standard Industrial Classification (SIC) codes are described at: " ;
	String	slb_sic_href		= "http://www.osha.gov/cgi-bin/sic/sicser5" ;
	String	slb_commodities		= "Key Words/Commodities"	;
	String	slb_socio_economic	= "Socio-Economic Class"	;
	String	slb_web_address		= "Web Site Address"		;
	String	slb_vendor_terms	= "Payment Terms"		;
	String	slb_years_in_bus	= "Years in Business"		;
	String	slb_lead_days		= "Order Lead Time (days)"	;
	String	slb_ein_number		= "EIN Number"			;
	String	slb_edi_vendor		= "EDI Capable (Y/N)"		;
	String	slb_edi_address		= "EDI Address"			;
	String	slb_notes		= "Notes"			;


/*****	SUPPLIER COMPANY HIDDEN VARIABLES  ******/

	boolean	b_display_duns		= true	;
	boolean	b_display_sic		= true	;
	boolean	b_display_commodities	= true	;
	boolean	b_display_socio_economic= true	;
	boolean b_display_web_address	= true	;
	boolean b_display_vendor_terms	= true	;
	boolean b_display_years_in_bus	= true	;
	boolean b_display_lead_days	= true	;
	boolean b_display_ein_number	= true	;
	boolean b_display_edi_vendor	= true	;
	boolean b_display_edi_address	= true	;
	boolean	b_display_notes		= true	;


/*****	SUPPLIER COMPANY ACTIVE LINKS  ******/

	boolean	b_link_commodities	= true	;
	boolean	b_link_socio_economic	= true	;
	boolean b_link_vendor_terms	= true	;


/*****	SUPPLIER COMPANY REQUIRED FIELDS  ******/

	boolean	brq_duns		= true	;
	boolean	brq_sic			= true	;
	boolean	brq_commodities		= true	;
	boolean	brq_socio_economic	= true	;
	boolean brq_web_address		= true	;
	boolean brq_vendor_terms	= true	;
	boolean brq_years_in_bus	= true	;
	boolean brq_lead_days		= true	;
	boolean brq_ein_number		= true	;
	boolean brq_edi_vendor		= true	;
	boolean brq_edi_address		= true	;
	boolean	brq_notes		= true	;


/*****	SUPPLIER UDF LABELS	*****/

	String	slb_udf01 = "Prior Year Revenue"	;
	String	slb_udf02 = "Business Type (Corp., Private, Partnership)"	;
	String	slb_udf03 = "Parent Company (if any)"	;
	String	slb_udf04 = "Collective Bargaining Unit (Y/N)"	;
	String	slb_udf05 = "No. of Employees"	;
	String	slb_udf06 = "Average Tenure"	;
	String	slb_udf07 = "Related to TSA employees"	;
	String	slb_udf08 = "Ticker Symbol"	;
	String	slb_udf09 = "Plant / Warehouse Location"	;
	String	slb_udf10 = "Supplier UDF10"	;


/*****	SUPPLIER UDF HIDDEN VARIABLES	*****/

	boolean b_display_udf01	= true	;
	boolean b_display_udf02 = true	;
	boolean b_display_udf03 = true	;
	boolean b_display_udf04 = true	;
	boolean b_display_udf05 = true	;
	boolean b_display_udf06 = false	;
	boolean b_display_udf07 = false	;
	boolean b_display_udf08 = false	;
	boolean b_display_udf09 = false	;
	boolean b_display_udf10 = false	;


/*****	SUPPLIER UDF ACTIVE LINKS	*****/

	boolean b_link_udf01	= true	;
	boolean b_link_udf02	= true	;
	boolean b_link_udf03	= true	;
	boolean b_link_udf04	= true	;
	boolean b_link_udf05	= true	;
	boolean b_link_udf06	= false	;
	boolean b_link_udf07	= false	;
	boolean b_link_udf08	= false	;
	boolean b_link_udf09	= false	;
	boolean b_link_udf10	= false	;


/*****	SUPPLIER UDF REQUIRED FIELDS	*****/

	boolean brq_udf01	= false	;
	boolean brq_udf02	= false	;
	boolean brq_udf03	= false	;
	boolean brq_udf04	= false	;
	boolean brq_udf05	= false	;
	boolean brq_udf06	= false	;
	boolean brq_udf07	= false	;
	boolean brq_udf08	= false	;
	boolean brq_udf09	= false	;
	boolean brq_udf10	= false	;

%>