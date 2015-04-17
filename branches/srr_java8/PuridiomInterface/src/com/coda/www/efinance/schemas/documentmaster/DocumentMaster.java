/**
 * DocumentMaster.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds a document
 *             master.
 */
public class DocumentMaster  extends com.coda.www.efinance.schemas.documentmaster.Master  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.documentmaster.DocNumListItem[] docNumList;

    /* This element holds a
     *                                 list of predefined
     *                             lines. */
    private com.coda.www.efinance.schemas.documentmaster.PDL[] preDefinedLineList;

    public DocumentMaster() {
    }

    public DocumentMaster(
           short timeStamp,
           java.lang.String cmpCode,
           java.lang.String code,
           java.lang.String name,
           java.lang.String shortName,
           java.lang.String cancelDoc,
           boolean cancelMatch,
           boolean addToTurnovers,
           boolean batch,
           java.lang.String userStatus,
           java.lang.String payStatus,
           boolean requireDescription,
           java.lang.String bankStatement,
           java.lang.String numberRule,
           java.lang.String numberFormat,
           java.lang.String controlTotals,
           java.lang.String destination,
           java.lang.String checking,
           java.lang.String authorisingUser,
           boolean validateOnAccount,
           java.lang.String periodUsage,
           boolean purchaseOrdering,
           boolean allowDiscounts,
           java.lang.String updateTransaction,
           boolean retainValues,
           boolean amendInBrowse,
           java.lang.String headerUserExit,
           boolean matchingFromInput,
           java.lang.String inputMatchingMasterCode,
           boolean matchingFromBrowse,
           java.lang.String browseMatchingMasterCode,
           java.lang.String assetDoc,
           boolean selfProportioning,
           boolean recurring,
           boolean reversing,
           com.coda.www.efinance.schemas.documentmaster.DueDate dueDate,
           com.coda.www.efinance.schemas.documentmaster.ValueDate valueDate,
           com.coda.www.efinance.schemas.documentmaster.Currency currency,
           com.coda.www.efinance.schemas.documentmaster.DocTax tax,
           com.coda.www.efinance.schemas.documentmaster.Pay pay,
           com.coda.www.efinance.schemas.documentmaster.Quantities quantities,
           com.coda.www.efinance.schemas.documentmaster.SummaryLines summaryLines,
           com.coda.www.efinance.schemas.documentmaster.AnalysisLines analysisLines,
           com.coda.www.efinance.schemas.documentmaster.Intercompany intercompany,
           com.coda.www.efinance.schemas.documentmaster.BalancingElements balancingElements,
           com.coda.www.efinance.schemas.documentmaster.ExternalReferences externalReferences,
           com.coda.www.efinance.schemas.documentmaster.UserReferences userReferences,
           com.coda.www.efinance.schemas.documentmaster.Recurring recurringDetails,
           com.coda.www.efinance.schemas.documentmaster.PreDefinedLines preDefinedLines,
           com.coda.www.efinance.schemas.documentmaster.FieldAccess fieldAccess,
           com.coda.www.efinance.schemas.common.ExtensionRef headerExtension,
           com.coda.www.efinance.schemas.common.ExtensionRef extRefExtension,
           boolean prevIntrayDel,
           boolean prevIntrayMod,
           java.util.Calendar createDate,
           java.util.Calendar modifyDate,
           java.lang.String user,
           java.lang.Boolean assetPerLine,
           java.lang.String workflowRequired,
           java.lang.String workItemExplodePresenter,
           java.lang.String positionHierarchy,
           java.lang.String intrayWorkflow,
           boolean protectIntrayWorkflow,
           boolean confirmIntrayWorkflow,
           boolean allowWorkflowIntrayEdits,
           java.lang.String booksWorkflow,
           boolean protectBooksWorkflow,
           boolean promptForAuthorisingUser,
           boolean isReserved,
           java.lang.String reservingApplicationId,
           com.coda.www.efinance.schemas.documentmaster.DocNumListItem[] docNumList,
           com.coda.www.efinance.schemas.documentmaster.PDL[] preDefinedLineList) {
        super(
            timeStamp,
            cmpCode,
            code,
            name,
            shortName,
            cancelDoc,
            cancelMatch,
            addToTurnovers,
            batch,
            userStatus,
            payStatus,
            requireDescription,
            bankStatement,
            numberRule,
            numberFormat,
            controlTotals,
            destination,
            checking,
            authorisingUser,
            validateOnAccount,
            periodUsage,
            purchaseOrdering,
            allowDiscounts,
            updateTransaction,
            retainValues,
            amendInBrowse,
            headerUserExit,
            matchingFromInput,
            inputMatchingMasterCode,
            matchingFromBrowse,
            browseMatchingMasterCode,
            assetDoc,
            selfProportioning,
            recurring,
            reversing,
            dueDate,
            valueDate,
            currency,
            tax,
            pay,
            quantities,
            summaryLines,
            analysisLines,
            intercompany,
            balancingElements,
            externalReferences,
            userReferences,
            recurringDetails,
            preDefinedLines,
            fieldAccess,
            headerExtension,
            extRefExtension,
            prevIntrayDel,
            prevIntrayMod,
            createDate,
            modifyDate,
            user,
            assetPerLine,
            workflowRequired,
            workItemExplodePresenter,
            positionHierarchy,
            intrayWorkflow,
            protectIntrayWorkflow,
            confirmIntrayWorkflow,
            allowWorkflowIntrayEdits,
            booksWorkflow,
            protectBooksWorkflow,
            promptForAuthorisingUser,
            isReserved,
            reservingApplicationId);
        this.docNumList = docNumList;
        this.preDefinedLineList = preDefinedLineList;
    }


    /**
     * Gets the docNumList value for this DocumentMaster.
     * 
     * @return docNumList
     */
    public com.coda.www.efinance.schemas.documentmaster.DocNumListItem[] getDocNumList() {
        return docNumList;
    }


    /**
     * Sets the docNumList value for this DocumentMaster.
     * 
     * @param docNumList
     */
    public void setDocNumList(com.coda.www.efinance.schemas.documentmaster.DocNumListItem[] docNumList) {
        this.docNumList = docNumList;
    }


    /**
     * Gets the preDefinedLineList value for this DocumentMaster.
     * 
     * @return preDefinedLineList   * This element holds a
     *                                 list of predefined
     *                             lines.
     */
    public com.coda.www.efinance.schemas.documentmaster.PDL[] getPreDefinedLineList() {
        return preDefinedLineList;
    }


    /**
     * Sets the preDefinedLineList value for this DocumentMaster.
     * 
     * @param preDefinedLineList   * This element holds a
     *                                 list of predefined
     *                             lines.
     */
    public void setPreDefinedLineList(com.coda.www.efinance.schemas.documentmaster.PDL[] preDefinedLineList) {
        this.preDefinedLineList = preDefinedLineList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentMaster)) return false;
        DocumentMaster other = (DocumentMaster) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.docNumList==null && other.getDocNumList()==null) || 
             (this.docNumList!=null &&
              java.util.Arrays.equals(this.docNumList, other.getDocNumList()))) &&
            ((this.preDefinedLineList==null && other.getPreDefinedLineList()==null) || 
             (this.preDefinedLineList!=null &&
              java.util.Arrays.equals(this.preDefinedLineList, other.getPreDefinedLineList())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getDocNumList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocNumList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocNumList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPreDefinedLineList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPreDefinedLineList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPreDefinedLineList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentMaster.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocumentMaster"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docNumList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocNumList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocNumListItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocNumListItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preDefinedLineList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PreDefinedLineList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PDL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PreDefinedLine"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
