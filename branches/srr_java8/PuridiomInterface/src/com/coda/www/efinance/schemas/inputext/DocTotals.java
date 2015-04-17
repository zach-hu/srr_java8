/**
 * DocTotals.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains the document totals in
 *                 document, home and dual currencies.
 */
public class DocTotals  implements java.io.Serializable {
    private java.math.BigDecimal docTotal;

    /* The sum of the
     *                         document's credit values, in document
     *                     currency. */
    private java.math.BigDecimal docCreditTotal;

    /* The
     *                         sum of the document's debit values, in
     *                         document currency. */
    private java.math.BigDecimal docDebitTotal;

    /* The
     *                         sum of the document's debit and credit
     *                         values, in home currency. */
    private java.math.BigDecimal homeTotal;

    /* The sum of the
     *                         document's credit values, in home
     *                     currency. */
    private java.math.BigDecimal homeCreditTotal;

    /* The sum of the
     *                         document's debit values, in home
     *                     currency. */
    private java.math.BigDecimal homeDebitTotal;

    /* The sum of the
     *                         document's debit and credit values, in dual
     * currency. */
    private java.math.BigDecimal dualTotal;

    /* The
     *                         sum of the document's credit values, in
     *                         dual currency. */
    private java.math.BigDecimal dualCreditTotal;

    /* The sum of the
     *                         document's debit values, in dual
     *                     currency. */
    private java.math.BigDecimal dualDebitTotal;

    /* An optional user defined
     *                         total derived from the input template that
     * was
     *                         used to input the document. */
    private java.math.BigDecimal userDefined;

    public DocTotals() {
    }

    public DocTotals(
           java.math.BigDecimal docTotal,
           java.math.BigDecimal docCreditTotal,
           java.math.BigDecimal docDebitTotal,
           java.math.BigDecimal homeTotal,
           java.math.BigDecimal homeCreditTotal,
           java.math.BigDecimal homeDebitTotal,
           java.math.BigDecimal dualTotal,
           java.math.BigDecimal dualCreditTotal,
           java.math.BigDecimal dualDebitTotal,
           java.math.BigDecimal userDefined) {
           this.docTotal = docTotal;
           this.docCreditTotal = docCreditTotal;
           this.docDebitTotal = docDebitTotal;
           this.homeTotal = homeTotal;
           this.homeCreditTotal = homeCreditTotal;
           this.homeDebitTotal = homeDebitTotal;
           this.dualTotal = dualTotal;
           this.dualCreditTotal = dualCreditTotal;
           this.dualDebitTotal = dualDebitTotal;
           this.userDefined = userDefined;
    }


    /**
     * Gets the docTotal value for this DocTotals.
     * 
     * @return docTotal
     */
    public java.math.BigDecimal getDocTotal() {
        return docTotal;
    }


    /**
     * Sets the docTotal value for this DocTotals.
     * 
     * @param docTotal
     */
    public void setDocTotal(java.math.BigDecimal docTotal) {
        this.docTotal = docTotal;
    }


    /**
     * Gets the docCreditTotal value for this DocTotals.
     * 
     * @return docCreditTotal   * The sum of the
     *                         document's credit values, in document
     *                     currency.
     */
    public java.math.BigDecimal getDocCreditTotal() {
        return docCreditTotal;
    }


    /**
     * Sets the docCreditTotal value for this DocTotals.
     * 
     * @param docCreditTotal   * The sum of the
     *                         document's credit values, in document
     *                     currency.
     */
    public void setDocCreditTotal(java.math.BigDecimal docCreditTotal) {
        this.docCreditTotal = docCreditTotal;
    }


    /**
     * Gets the docDebitTotal value for this DocTotals.
     * 
     * @return docDebitTotal   * The
     *                         sum of the document's debit values, in
     *                         document currency.
     */
    public java.math.BigDecimal getDocDebitTotal() {
        return docDebitTotal;
    }


    /**
     * Sets the docDebitTotal value for this DocTotals.
     * 
     * @param docDebitTotal   * The
     *                         sum of the document's debit values, in
     *                         document currency.
     */
    public void setDocDebitTotal(java.math.BigDecimal docDebitTotal) {
        this.docDebitTotal = docDebitTotal;
    }


    /**
     * Gets the homeTotal value for this DocTotals.
     * 
     * @return homeTotal   * The
     *                         sum of the document's debit and credit
     *                         values, in home currency.
     */
    public java.math.BigDecimal getHomeTotal() {
        return homeTotal;
    }


    /**
     * Sets the homeTotal value for this DocTotals.
     * 
     * @param homeTotal   * The
     *                         sum of the document's debit and credit
     *                         values, in home currency.
     */
    public void setHomeTotal(java.math.BigDecimal homeTotal) {
        this.homeTotal = homeTotal;
    }


    /**
     * Gets the homeCreditTotal value for this DocTotals.
     * 
     * @return homeCreditTotal   * The sum of the
     *                         document's credit values, in home
     *                     currency.
     */
    public java.math.BigDecimal getHomeCreditTotal() {
        return homeCreditTotal;
    }


    /**
     * Sets the homeCreditTotal value for this DocTotals.
     * 
     * @param homeCreditTotal   * The sum of the
     *                         document's credit values, in home
     *                     currency.
     */
    public void setHomeCreditTotal(java.math.BigDecimal homeCreditTotal) {
        this.homeCreditTotal = homeCreditTotal;
    }


    /**
     * Gets the homeDebitTotal value for this DocTotals.
     * 
     * @return homeDebitTotal   * The sum of the
     *                         document's debit values, in home
     *                     currency.
     */
    public java.math.BigDecimal getHomeDebitTotal() {
        return homeDebitTotal;
    }


    /**
     * Sets the homeDebitTotal value for this DocTotals.
     * 
     * @param homeDebitTotal   * The sum of the
     *                         document's debit values, in home
     *                     currency.
     */
    public void setHomeDebitTotal(java.math.BigDecimal homeDebitTotal) {
        this.homeDebitTotal = homeDebitTotal;
    }


    /**
     * Gets the dualTotal value for this DocTotals.
     * 
     * @return dualTotal   * The sum of the
     *                         document's debit and credit values, in dual
     * currency.
     */
    public java.math.BigDecimal getDualTotal() {
        return dualTotal;
    }


    /**
     * Sets the dualTotal value for this DocTotals.
     * 
     * @param dualTotal   * The sum of the
     *                         document's debit and credit values, in dual
     * currency.
     */
    public void setDualTotal(java.math.BigDecimal dualTotal) {
        this.dualTotal = dualTotal;
    }


    /**
     * Gets the dualCreditTotal value for this DocTotals.
     * 
     * @return dualCreditTotal   * The
     *                         sum of the document's credit values, in
     *                         dual currency.
     */
    public java.math.BigDecimal getDualCreditTotal() {
        return dualCreditTotal;
    }


    /**
     * Sets the dualCreditTotal value for this DocTotals.
     * 
     * @param dualCreditTotal   * The
     *                         sum of the document's credit values, in
     *                         dual currency.
     */
    public void setDualCreditTotal(java.math.BigDecimal dualCreditTotal) {
        this.dualCreditTotal = dualCreditTotal;
    }


    /**
     * Gets the dualDebitTotal value for this DocTotals.
     * 
     * @return dualDebitTotal   * The sum of the
     *                         document's debit values, in dual
     *                     currency.
     */
    public java.math.BigDecimal getDualDebitTotal() {
        return dualDebitTotal;
    }


    /**
     * Sets the dualDebitTotal value for this DocTotals.
     * 
     * @param dualDebitTotal   * The sum of the
     *                         document's debit values, in dual
     *                     currency.
     */
    public void setDualDebitTotal(java.math.BigDecimal dualDebitTotal) {
        this.dualDebitTotal = dualDebitTotal;
    }


    /**
     * Gets the userDefined value for this DocTotals.
     * 
     * @return userDefined   * An optional user defined
     *                         total derived from the input template that
     * was
     *                         used to input the document.
     */
    public java.math.BigDecimal getUserDefined() {
        return userDefined;
    }


    /**
     * Sets the userDefined value for this DocTotals.
     * 
     * @param userDefined   * An optional user defined
     *                         total derived from the input template that
     * was
     *                         used to input the document.
     */
    public void setUserDefined(java.math.BigDecimal userDefined) {
        this.userDefined = userDefined;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocTotals)) return false;
        DocTotals other = (DocTotals) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.docTotal==null && other.getDocTotal()==null) || 
             (this.docTotal!=null &&
              this.docTotal.equals(other.getDocTotal()))) &&
            ((this.docCreditTotal==null && other.getDocCreditTotal()==null) || 
             (this.docCreditTotal!=null &&
              this.docCreditTotal.equals(other.getDocCreditTotal()))) &&
            ((this.docDebitTotal==null && other.getDocDebitTotal()==null) || 
             (this.docDebitTotal!=null &&
              this.docDebitTotal.equals(other.getDocDebitTotal()))) &&
            ((this.homeTotal==null && other.getHomeTotal()==null) || 
             (this.homeTotal!=null &&
              this.homeTotal.equals(other.getHomeTotal()))) &&
            ((this.homeCreditTotal==null && other.getHomeCreditTotal()==null) || 
             (this.homeCreditTotal!=null &&
              this.homeCreditTotal.equals(other.getHomeCreditTotal()))) &&
            ((this.homeDebitTotal==null && other.getHomeDebitTotal()==null) || 
             (this.homeDebitTotal!=null &&
              this.homeDebitTotal.equals(other.getHomeDebitTotal()))) &&
            ((this.dualTotal==null && other.getDualTotal()==null) || 
             (this.dualTotal!=null &&
              this.dualTotal.equals(other.getDualTotal()))) &&
            ((this.dualCreditTotal==null && other.getDualCreditTotal()==null) || 
             (this.dualCreditTotal!=null &&
              this.dualCreditTotal.equals(other.getDualCreditTotal()))) &&
            ((this.dualDebitTotal==null && other.getDualDebitTotal()==null) || 
             (this.dualDebitTotal!=null &&
              this.dualDebitTotal.equals(other.getDualDebitTotal()))) &&
            ((this.userDefined==null && other.getUserDefined()==null) || 
             (this.userDefined!=null &&
              this.userDefined.equals(other.getUserDefined())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDocTotal() != null) {
            _hashCode += getDocTotal().hashCode();
        }
        if (getDocCreditTotal() != null) {
            _hashCode += getDocCreditTotal().hashCode();
        }
        if (getDocDebitTotal() != null) {
            _hashCode += getDocDebitTotal().hashCode();
        }
        if (getHomeTotal() != null) {
            _hashCode += getHomeTotal().hashCode();
        }
        if (getHomeCreditTotal() != null) {
            _hashCode += getHomeCreditTotal().hashCode();
        }
        if (getHomeDebitTotal() != null) {
            _hashCode += getHomeDebitTotal().hashCode();
        }
        if (getDualTotal() != null) {
            _hashCode += getDualTotal().hashCode();
        }
        if (getDualCreditTotal() != null) {
            _hashCode += getDualCreditTotal().hashCode();
        }
        if (getDualDebitTotal() != null) {
            _hashCode += getDualDebitTotal().hashCode();
        }
        if (getUserDefined() != null) {
            _hashCode += getUserDefined().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocTotals.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocTotals"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCreditTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocCreditTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docDebitTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocDebitTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HomeTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeCreditTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HomeCreditTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeDebitTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HomeDebitTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dualTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DualTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dualCreditTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DualCreditTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dualDebitTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DualDebitTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userDefined");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "UserDefined"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
