/**
 * CalcCurrResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;

public class CalcCurrResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* Contains the
     *                                 transaction details, including any
     * changes resulting from the currency
     *                             edits. */
    private com.coda.www.efinance.schemas.transaction.Transaction transaction;

    /* Contains information
     *                                 about the currencies edited in the
     * request corresponding to this
     *                             response. */
    private com.coda.www.efinance.schemas.inputext.CurrencyInfo[] currData;

    public CalcCurrResponse() {
    }

    public CalcCurrResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.transaction.Transaction transaction,
           com.coda.www.efinance.schemas.inputext.CurrencyInfo[] currData) {
        super();
        this.transaction = transaction;
        this.currData = currData;
    }


    /**
     * Gets the transaction value for this CalcCurrResponse.
     *
     * @return transaction   * Contains the
     *                                 transaction details, including any
     * changes resulting from the currency
     *                             edits.
     */
    public com.coda.www.efinance.schemas.transaction.Transaction getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this CalcCurrResponse.
     *
     * @param transaction   * Contains the
     *                                 transaction details, including any
     * changes resulting from the currency
     *                             edits.
     */
    public void setTransaction(com.coda.www.efinance.schemas.transaction.Transaction transaction) {
        this.transaction = transaction;
    }


    /**
     * Gets the currData value for this CalcCurrResponse.
     *
     * @return currData   * Contains information
     *                                 about the currencies edited in the
     * request corresponding to this
     *                             response.
     */
    public com.coda.www.efinance.schemas.inputext.CurrencyInfo[] getCurrData() {
        return currData;
    }


    /**
     * Sets the currData value for this CalcCurrResponse.
     *
     * @param currData   * Contains information
     *                                 about the currencies edited in the
     * request corresponding to this
     *                             response.
     */
    public void setCurrData(com.coda.www.efinance.schemas.inputext.CurrencyInfo[] currData) {
        this.currData = currData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CalcCurrResponse)) return false;
        CalcCurrResponse other = (CalcCurrResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.transaction==null && other.getTransaction()==null) ||
             (this.transaction!=null &&
              this.transaction.equals(other.getTransaction()))) &&
            ((this.currData==null && other.getCurrData()==null) ||
             (this.currData!=null &&
              java.util.Arrays.equals(this.currData, other.getCurrData())));
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
        if (getTransaction() != null) {
            _hashCode += getTransaction().hashCode();
        }
        if (getCurrData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCurrData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCurrData(), i);
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
        new org.apache.axis.description.TypeDesc(CalcCurrResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CalcCurrResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Transaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrencyInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Info"));
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
