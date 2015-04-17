/**
 * Line.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This
 *                 element holds the information for one document
 *             line.
 */
public class Line  extends com.coda.www.efinance.schemas.transaction.BasicLine  implements java.io.Serializable {
    private java.lang.String[] comments;

    public Line() {
    }

    public Line(
           int number,
           java.lang.Short timeStamp,
           java.lang.String destCode,
           java.lang.String accountCode,
           java.lang.String traderCode,
           java.math.BigDecimal docValue,
           java.math.BigDecimal docRate,
           java.math.BigDecimal homeValue,
           java.math.BigDecimal homeFullValue,
           java.math.BigDecimal dualValue,
           java.math.BigDecimal dualFullValue,
           java.math.BigDecimal dualRate,
           java.lang.String parentCurrency,
           java.math.BigDecimal parentRate,
           java.math.BigDecimal parentValue,
           java.math.BigDecimal parentFullValue,
           java.lang.String userStatus,
           java.lang.String lineType,
           java.lang.String lineSense,
           java.lang.String lineOrigin,
           java.lang.String description,
           java.util.Calendar dueDate,
           java.lang.String dueTerms,
           java.util.Calendar valueDate,
           java.lang.String valueTerms,
           java.lang.String extRef1,
           java.lang.String extRef2,
           java.lang.String extRef3,
           java.lang.String extRef4,
           java.lang.String extRef5,
           java.lang.String extRef6,
           java.math.BigDecimal docSumTax,
           java.lang.String taxLineCode,
           java.math.BigDecimal docTaxTurnover,
           java.lang.Boolean taxInclusive,
           java.lang.String mediaCode,
           java.lang.String bankCode,
           java.lang.Boolean disableSummaries,
           java.lang.Short elmBankTag,
           java.lang.Short elmAddrTag,
           java.lang.String userRef1,
           java.lang.String userRef2,
           java.lang.String userRef3,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities1,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities2,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities3,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities4,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities5,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities6,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities7,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities8,
           java.lang.String payStatus,
           java.util.Calendar payDate,
           java.lang.Integer payNumber,
           java.lang.String recStatus,
           java.lang.Integer recNumber,
           java.math.BigDecimal partPayment,
           java.math.BigDecimal homeSumTax,
           java.lang.Short matchableElmLevel,
           java.lang.String taxMethod,
           com.coda.www.efinance.schemas.transaction.LineWorkflow workflow,
           java.lang.String elmBankAccount,
           java.lang.String elmAddrName,
           java.lang.Boolean discsAllowed,
           java.lang.String calcDisc,
           java.lang.Boolean matchableElmTemporary,
           java.lang.Boolean accCodeValid,
           java.lang.Boolean customerSupplier,
           com.coda.www.efinance.schemas.transaction.Currency[] currencies,
           com.coda.www.efinance.schemas.transaction.Tax[] taxes,
           com.coda.www.efinance.schemas.transaction.Tax[] ten99Taxes,
           com.coda.www.efinance.schemas.transaction.Discount[] discounts,
           com.coda.www.efinance.schemas.transaction.RecurringDiscount[] recurringDiscounts,
           java.lang.Integer DBLineNum,
           java.lang.Boolean involvedInMatch,
           com.coda.www.efinance.schemas.transaction.Asset asset,
           java.lang.String extensionData,
           java.lang.String extensionData2,
           java.lang.String[] comments) {
        super(
            number,
            timeStamp,
            destCode,
            accountCode,
            traderCode,
            docValue,
            docRate,
            homeValue,
            homeFullValue,
            dualValue,
            dualFullValue,
            dualRate,
            parentCurrency,
            parentRate,
            parentValue,
            parentFullValue,
            userStatus,
            lineType,
            lineSense,
            lineOrigin,
            description,
            dueDate,
            dueTerms,
            valueDate,
            valueTerms,
            extRef1,
            extRef2,
            extRef3,
            extRef4,
            extRef5,
            extRef6,
            docSumTax,
            taxLineCode,
            docTaxTurnover,
            taxInclusive,
            mediaCode,
            bankCode,
            disableSummaries,
            elmBankTag,
            elmAddrTag,
            userRef1,
            userRef2,
            userRef3,
            elmQuantities1,
            elmQuantities2,
            elmQuantities3,
            elmQuantities4,
            elmQuantities5,
            elmQuantities6,
            elmQuantities7,
            elmQuantities8,
            payStatus,
            payDate,
            payNumber,
            recStatus,
            recNumber,
            partPayment,
            homeSumTax,
            matchableElmLevel,
            taxMethod,
            workflow,
            elmBankAccount,
            elmAddrName,
            discsAllowed,
            calcDisc,
            matchableElmTemporary,
            accCodeValid,
            customerSupplier,
            currencies,
            taxes,
            ten99Taxes,
            discounts,
            recurringDiscounts,
            DBLineNum,
            involvedInMatch,
            asset,
            extensionData,
            extensionData2);
        this.comments = comments;
    }


    /**
     * Gets the comments value for this Line.
     * 
     * @return comments
     */
    public java.lang.String[] getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this Line.
     * 
     * @param comments
     */
    public void setComments(java.lang.String[] comments) {
        this.comments = comments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Line)) return false;
        Line other = (Line) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              java.util.Arrays.equals(this.comments, other.getComments())));
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
        if (getComments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComments(), i);
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
        new org.apache.axis.description.TypeDesc(Line.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Line"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextB"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Comment"));
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
