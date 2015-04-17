
package com.tsa.puridiom.jasperreports.tests;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator
 */
public class HqlQuery extends Task
{
    /* (non-Javadoc)
     * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
     */
    public Object executeTask(Object object) throws Exception
    {

        Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
        BigDecimal bdReqHeader = (BigDecimal) incomingRequest.get("icReqHeader") ;

        /*String queryString = "" +            "Select PoPaymentDetailView.fld4, PoPaymentDetailView.effectiveDate, " +
            "PoPaymentDetailView.expirationDate, PoPaymentDetailView.blanketLimit, " +
            "PoPaymentDetailView.paymentAmount, PoPaymentDetailView.udf1Code, " +
            "PoPaymentDetailView.poNumber, PoPaymentDetailView.poDate, " +
            "PoPaymentDetailView.vendorName, PoPaymentDetailView.internalComments, " +
            "PoPaymentDetailView.total, PoPaymentDetailView.lineTotal, PoPaymentDetailView.requisitionerCode, " +
            "PoPaymentDetailView.costCenter, PoPaymentDetailView.accountCode, " +
            "PoPaymentDetailView.checkNumber, PoPaymentDetailView.invoiceDate, " +
            "PoPaymentDetailView.itemNumber, PoPaymentDetailView.description, " +
            "PoPaymentDetailView.revisionNumber, PoPaymentDetailView.releaseNumber, " +
            "PoPaymentDetailView.lineNumber, PoPaymentDetailView.budgeted " +
            "from PoPaymentDetailView as PoPaymentDetailView " +
            "where 1 = 1 " +
            "order by PoPaymentDetailView.fld4 ASC, PoPaymentDetailView.poNumber ASC, PoPaymentDetailView.releaseNumber ASC, PoPaymentDetailView.revisionNumber ASC";
            */
        String queryString = "Select PoPaymentDetailView.fld4, PoPaymentDetailView.effectiveDate, " +
                "PoPaymentDetailView.expirationDate, PoPaymentDetailView.blanketLimit, " +
                "PoPaymentDetailView.paymentAmount, PoPaymentDetailView.contractNo, " +
                "PoPaymentDetailView.poNumber, PoPaymentDetailView.poDate, " +
                "PoPaymentDetailView.vendorName, PoPaymentDetailView.internalComments, " +
                "PoPaymentDetailView.total, PoPaymentDetailView.lineTotal, " +
                "PoPaymentDetailView.requisitionerCode, PoPaymentDetailView.checkNumber, " +
                "PoPaymentDetailView.invoiceDate, PoPaymentDetailView.itemNumber, " +
                "PoPaymentDetailView.description, PoPaymentDetailView.revisionNumber, " +
                "PoPaymentDetailView.releaseNumber, PoPaymentDetailView.lineNumber, " +
                "PoPaymentDetailView.budgeted, PoPaymentDetailView.fld1, " +
                "PoPaymentDetailView.fld2 " +
                "from PoPaymentDetailView as PoPaymentDetailView " +
                "where 1 = 1 " +
                "order by PoPaymentDetailView.fld4 ASC, " +
                "PoPaymentDetailView.poNumber ASC, " +
                "PoPaymentDetailView.releaseNumber ASC, " +
                "PoPaymentDetailView.revisionNumber ASC, " +
                "PoPaymentDetailView.lineNumber ASC" ;
        List result = dbs.query(queryString) ;

        this.setStatus(dbs.getStatus()) ;

        return result ;
    }

}
