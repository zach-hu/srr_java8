package com.tsa.puridiom.supplierportal.handlers;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Dates;

import java.util.*;

public class SetEventPageHandler implements IHandler {

	public Map  handleRequest (Map incomingRequest) throws Exception {
		try {
			String	organizationId = (String) incomingRequest.get("organizationId");
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");

			if (rfqHeader != null) {
                boolean extendedRfqs = PropertiesManager.getInstance(organizationId).isModuleActive("EXTENDED RFQS");
                Date currentDate = Dates.getDate("");
                Date dueDate = rfqHeader.getDueDateTime();
                String  closedEventPage = "/rfq/rfq_summary_postauction.jsp";
                boolean pageSet = false;

                if (!extendedRfqs) {
                    if (dueDate.before(currentDate)) {
                        incomingRequest.put("viewPage", closedEventPage);
                        pageSet = true;
                    } else if (!rfqHeader.getWebpost().equalsIgnoreCase("N") && !HiltonUtility.isEmpty(rfqHeader.getWebpost())) {
                        incomingRequest.put("viewPage", "/rfq/rfq_summary.jsp");
                        pageSet = true;
                    }
                } else {
    			    String	qaEventPage = "/rfq/rfq_qa_event.jsp";
    			    String	bidEventPage = "/rfq/rfq_bid_event.jsp";
    			    String	auctionEventPage = "/rfq/rfq_auction.jsp";
    			    String	eventSchedulePage = "/rfq/rfq_event_schedule.jsp";

    			    if (rfqHeader.getEventPaused().equalsIgnoreCase("Y")) {
    			        incomingRequest.put("viewPage", eventSchedulePage);
    			        pageSet = true;
    			    }
    			    if (!pageSet && rfqHeader.getQaEvent().equalsIgnoreCase("Y")) {
    			        Date qaStartDate = rfqHeader.getQaStartDateTime();
    			        Date qaEndDate = rfqHeader.getQaEndDateTime();

    			        if ((qaStartDate.equals(currentDate) || qaStartDate.before(currentDate)) && (qaEndDate.after(currentDate) || qaEndDate.after(currentDate))) {
    			            incomingRequest.put("viewPage", qaEventPage);
    			            pageSet = true;
    			        } else if (qaStartDate.after(currentDate)) {
    			            // QA Event did not start yet
    			            incomingRequest.put("viewPage", eventSchedulePage);
    			            pageSet = true;
    			        }
    			    }
    			    if (!pageSet && rfqHeader.getBidEvent().equalsIgnoreCase("Y")) {
    			        Date bidStartDate = rfqHeader.getBidStartDateTime();
    			        Date bidEndDate = rfqHeader.getBidEndDateTime();

    			        if ((bidStartDate.equals(currentDate) || bidStartDate.before(currentDate)) && (bidEndDate.after(currentDate) || bidEndDate.after(currentDate))) {
    			            RfqVendor rfqVendor = (RfqVendor) incomingRequest.get("currentVendor");
    			            if (rfqVendor != null && rfqVendor.getBidsEntered().equalsIgnoreCase("Y") && !rfqHeader.getAuctionType().equalsIgnoreCase("S")) {
    				            // Vendor already sumbitted bids for Bid Event
    				            // Vendor can update bids in this event for Sealed bids
    				            incomingRequest.put("viewPage", eventSchedulePage);
    				            pageSet = true;
    			            } else {
    			                incomingRequest.put("viewPage", bidEventPage);
    			                pageSet = true;
    			            }
    			        } else if (bidStartDate.after(currentDate)) {
    			            // Bid Event did not start yet
    			            incomingRequest.put("viewPage", eventSchedulePage);
    			            pageSet = true;
    			        }
    			    }
    			    if (!pageSet && rfqHeader.getAuctionEvent().equalsIgnoreCase("Y")) {
    			        Date auctionStartDate = rfqHeader.getAuctionStartDateTime();
    			        Date auctionEndDate = rfqHeader.getAuctionEndDateTime();

    			        if ((auctionStartDate.equals(currentDate) || auctionStartDate.before(currentDate)) && (auctionEndDate.after(currentDate) || auctionEndDate.after(currentDate))) {
    			            incomingRequest.put("viewPage", auctionEventPage);
    			            pageSet = true;
    			        } else if (auctionStartDate.after(currentDate)) {
    			            // Bid Event did not start yet
    			            incomingRequest.put("viewPage", eventSchedulePage);
    			            pageSet = true;
    			        }
    			    }
    			    if (!pageSet) {
    			        if (dueDate.before(currentDate)) {
    			            incomingRequest.put("viewPage", closedEventPage);
    			            pageSet = true;
    			        } else if (rfqHeader.getQaEvent().equalsIgnoreCase("Y") || rfqHeader.getBidEvent().equalsIgnoreCase("Y") || rfqHeader.getAuctionEvent().equalsIgnoreCase("Y")) {
    			            incomingRequest.put("viewPage", eventSchedulePage);
    			            pageSet = true;
    			        }
    			    }
    			    String viewPage = (String) incomingRequest.get("viewPage");
    			    if (!pageSet || HiltonUtility.isEmpty(viewPage)) {
    			        incomingRequest.put("viewPage", incomingRequest.get("successPage"));
    			    }
                }
			}
			else {
				// do not change viewPage
			}
		} catch (Exception exception) {
			incomingRequest.put("errorMsg", exception.getMessage());
			incomingRequest.put("exception", exception);
			incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
		} finally {
			if (incomingRequest.get("viewPage") == null) {
				incomingRequest.put("viewPage", incomingRequest.get("failurePage"));
			}
		}
		return incomingRequest;
	}

}