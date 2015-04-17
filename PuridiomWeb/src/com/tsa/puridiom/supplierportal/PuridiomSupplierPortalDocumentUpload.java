package com.tsa.puridiom.supplierportal;

/*
 * HiltonSupplierPortalDocumentUpload.java
 * 		Servlet to handle file uploads using DocumentFileUpload
 */

import com.tsa.puridiom.documentfile.tasks.DocumentFileUpload;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class PuridiomSupplierPortalDocumentUpload extends PuridiomSupplierPortalServletController {
        
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = this.getServletContext();
        String	failurePage = "/system/error_popup.jsp";
        
        try {
            response.setContentType("text/plain");
            
            Map incomingRequest = new HashMap();
            incomingRequest.put("documentType", "external-document-path");
            incomingRequest.put("request", request);

            DocumentFileUpload upload = new DocumentFileUpload();
            upload.executeTask(incomingRequest);
            
            String	successPage = (String) incomingRequest.get("successPage");
            failurePage = (String) incomingRequest.get("failurePage");
            
            incomingRequest.put("VendorDocument_docFilename", (String) incomingRequest.get("filename"));
            
            this.setRequestParametersFromHashMap(incomingRequest, request);
            
            RequestDispatcher rd = sc.getRequestDispatcher(successPage);
            if (rd != null) {
                try {
                    rd.forward(request, response);
                } catch (Exception e) {
                    request.setAttribute("errorMsg", "There is a problem with the following page: " + successPage + ".");
                    request.setAttribute("exception", e);
                    rd = sc.getRequestDispatcher(failurePage);
                    rd.forward(request, response);
                }
            }
        }
        catch (IOException lEx) {
            this.getServletContext().log("There was an error reading or saving the file.");
            RequestDispatcher rd = sc.getRequestDispatcher(failurePage);
            request.setAttribute("errorMsg", lEx.getMessage());
            request.setAttribute("exception", lEx);
            rd = sc.getRequestDispatcher(failurePage);
            rd.forward(request, response);
        } catch (Exception e) {
            RequestDispatcher rd = sc.getRequestDispatcher(failurePage);
            request.setAttribute("errorMsg", e.getMessage());
            request.setAttribute("exception", e);
            rd = sc.getRequestDispatcher(failurePage);
            rd.forward(request, response);
        }    
    }
}
