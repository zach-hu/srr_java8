/**
 * ExternalReferences.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds information about
 *                 external references.
 */
public class ExternalReferences  implements java.io.Serializable {
    private boolean documentWide;

    /* The
     *                         name of the custom program called for checking
     * duplicates in external
     *                     references. */
    private java.lang.String userExit;

    /* The action code which is
     *                         passed with the user exit when the line is
     * added
     *                         or modified in Input. */
    private java.lang.String actionCode;

    /* Specifies whether checking
     *                         external references for duplicates is performed
     * for all references combined or for each
     *                         reference individually. */
    private boolean combine;

    /* This
     *                         element holds information about how combined
     * checking of external references for duplicates
     *                         is performed. */
    private com.coda.www.efinance.schemas.documentmaster.ExtRefCombined combinedChecking;

    /* This element holds
     *                         information about external reference
     *                     1 */
    private com.coda.www.efinance.schemas.documentmaster.ExtRef reference1;

    /* This
     *                         element holds information about external
     *                         reference 2. */
    private com.coda.www.efinance.schemas.documentmaster.ExtRef reference2;

    /* This element holds
     *                         information about external reference
     *                     3. */
    private com.coda.www.efinance.schemas.documentmaster.ExtRef reference3;

    /* This
     *                         element holds information about external
     *                         reference 4. */
    private com.coda.www.efinance.schemas.documentmaster.ExtRef reference4;

    /* This element holds
     *                         information about external reference
     *                     5. */
    private com.coda.www.efinance.schemas.documentmaster.ExtRef reference5;

    /* This
     *                         element holds information about external
     *                         reference 6. */
    private com.coda.www.efinance.schemas.documentmaster.ExtRef reference6;

    public ExternalReferences() {
    }

    public ExternalReferences(
           boolean documentWide,
           java.lang.String userExit,
           java.lang.String actionCode,
           boolean combine,
           com.coda.www.efinance.schemas.documentmaster.ExtRefCombined combinedChecking,
           com.coda.www.efinance.schemas.documentmaster.ExtRef reference1,
           com.coda.www.efinance.schemas.documentmaster.ExtRef reference2,
           com.coda.www.efinance.schemas.documentmaster.ExtRef reference3,
           com.coda.www.efinance.schemas.documentmaster.ExtRef reference4,
           com.coda.www.efinance.schemas.documentmaster.ExtRef reference5,
           com.coda.www.efinance.schemas.documentmaster.ExtRef reference6) {
           this.documentWide = documentWide;
           this.userExit = userExit;
           this.actionCode = actionCode;
           this.combine = combine;
           this.combinedChecking = combinedChecking;
           this.reference1 = reference1;
           this.reference2 = reference2;
           this.reference3 = reference3;
           this.reference4 = reference4;
           this.reference5 = reference5;
           this.reference6 = reference6;
    }


    /**
     * Gets the documentWide value for this ExternalReferences.
     * 
     * @return documentWide
     */
    public boolean isDocumentWide() {
        return documentWide;
    }


    /**
     * Sets the documentWide value for this ExternalReferences.
     * 
     * @param documentWide
     */
    public void setDocumentWide(boolean documentWide) {
        this.documentWide = documentWide;
    }


    /**
     * Gets the userExit value for this ExternalReferences.
     * 
     * @return userExit   * The
     *                         name of the custom program called for checking
     * duplicates in external
     *                     references.
     */
    public java.lang.String getUserExit() {
        return userExit;
    }


    /**
     * Sets the userExit value for this ExternalReferences.
     * 
     * @param userExit   * The
     *                         name of the custom program called for checking
     * duplicates in external
     *                     references.
     */
    public void setUserExit(java.lang.String userExit) {
        this.userExit = userExit;
    }


    /**
     * Gets the actionCode value for this ExternalReferences.
     * 
     * @return actionCode   * The action code which is
     *                         passed with the user exit when the line is
     * added
     *                         or modified in Input.
     */
    public java.lang.String getActionCode() {
        return actionCode;
    }


    /**
     * Sets the actionCode value for this ExternalReferences.
     * 
     * @param actionCode   * The action code which is
     *                         passed with the user exit when the line is
     * added
     *                         or modified in Input.
     */
    public void setActionCode(java.lang.String actionCode) {
        this.actionCode = actionCode;
    }


    /**
     * Gets the combine value for this ExternalReferences.
     * 
     * @return combine   * Specifies whether checking
     *                         external references for duplicates is performed
     * for all references combined or for each
     *                         reference individually.
     */
    public boolean isCombine() {
        return combine;
    }


    /**
     * Sets the combine value for this ExternalReferences.
     * 
     * @param combine   * Specifies whether checking
     *                         external references for duplicates is performed
     * for all references combined or for each
     *                         reference individually.
     */
    public void setCombine(boolean combine) {
        this.combine = combine;
    }


    /**
     * Gets the combinedChecking value for this ExternalReferences.
     * 
     * @return combinedChecking   * This
     *                         element holds information about how combined
     * checking of external references for duplicates
     *                         is performed.
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRefCombined getCombinedChecking() {
        return combinedChecking;
    }


    /**
     * Sets the combinedChecking value for this ExternalReferences.
     * 
     * @param combinedChecking   * This
     *                         element holds information about how combined
     * checking of external references for duplicates
     *                         is performed.
     */
    public void setCombinedChecking(com.coda.www.efinance.schemas.documentmaster.ExtRefCombined combinedChecking) {
        this.combinedChecking = combinedChecking;
    }


    /**
     * Gets the reference1 value for this ExternalReferences.
     * 
     * @return reference1   * This element holds
     *                         information about external reference
     *                     1
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRef getReference1() {
        return reference1;
    }


    /**
     * Sets the reference1 value for this ExternalReferences.
     * 
     * @param reference1   * This element holds
     *                         information about external reference
     *                     1
     */
    public void setReference1(com.coda.www.efinance.schemas.documentmaster.ExtRef reference1) {
        this.reference1 = reference1;
    }


    /**
     * Gets the reference2 value for this ExternalReferences.
     * 
     * @return reference2   * This
     *                         element holds information about external
     *                         reference 2.
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRef getReference2() {
        return reference2;
    }


    /**
     * Sets the reference2 value for this ExternalReferences.
     * 
     * @param reference2   * This
     *                         element holds information about external
     *                         reference 2.
     */
    public void setReference2(com.coda.www.efinance.schemas.documentmaster.ExtRef reference2) {
        this.reference2 = reference2;
    }


    /**
     * Gets the reference3 value for this ExternalReferences.
     * 
     * @return reference3   * This element holds
     *                         information about external reference
     *                     3.
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRef getReference3() {
        return reference3;
    }


    /**
     * Sets the reference3 value for this ExternalReferences.
     * 
     * @param reference3   * This element holds
     *                         information about external reference
     *                     3.
     */
    public void setReference3(com.coda.www.efinance.schemas.documentmaster.ExtRef reference3) {
        this.reference3 = reference3;
    }


    /**
     * Gets the reference4 value for this ExternalReferences.
     * 
     * @return reference4   * This
     *                         element holds information about external
     *                         reference 4.
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRef getReference4() {
        return reference4;
    }


    /**
     * Sets the reference4 value for this ExternalReferences.
     * 
     * @param reference4   * This
     *                         element holds information about external
     *                         reference 4.
     */
    public void setReference4(com.coda.www.efinance.schemas.documentmaster.ExtRef reference4) {
        this.reference4 = reference4;
    }


    /**
     * Gets the reference5 value for this ExternalReferences.
     * 
     * @return reference5   * This element holds
     *                         information about external reference
     *                     5.
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRef getReference5() {
        return reference5;
    }


    /**
     * Sets the reference5 value for this ExternalReferences.
     * 
     * @param reference5   * This element holds
     *                         information about external reference
     *                     5.
     */
    public void setReference5(com.coda.www.efinance.schemas.documentmaster.ExtRef reference5) {
        this.reference5 = reference5;
    }


    /**
     * Gets the reference6 value for this ExternalReferences.
     * 
     * @return reference6   * This
     *                         element holds information about external
     *                         reference 6.
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRef getReference6() {
        return reference6;
    }


    /**
     * Sets the reference6 value for this ExternalReferences.
     * 
     * @param reference6   * This
     *                         element holds information about external
     *                         reference 6.
     */
    public void setReference6(com.coda.www.efinance.schemas.documentmaster.ExtRef reference6) {
        this.reference6 = reference6;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExternalReferences)) return false;
        ExternalReferences other = (ExternalReferences) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.documentWide == other.isDocumentWide() &&
            ((this.userExit==null && other.getUserExit()==null) || 
             (this.userExit!=null &&
              this.userExit.equals(other.getUserExit()))) &&
            ((this.actionCode==null && other.getActionCode()==null) || 
             (this.actionCode!=null &&
              this.actionCode.equals(other.getActionCode()))) &&
            this.combine == other.isCombine() &&
            ((this.combinedChecking==null && other.getCombinedChecking()==null) || 
             (this.combinedChecking!=null &&
              this.combinedChecking.equals(other.getCombinedChecking()))) &&
            ((this.reference1==null && other.getReference1()==null) || 
             (this.reference1!=null &&
              this.reference1.equals(other.getReference1()))) &&
            ((this.reference2==null && other.getReference2()==null) || 
             (this.reference2!=null &&
              this.reference2.equals(other.getReference2()))) &&
            ((this.reference3==null && other.getReference3()==null) || 
             (this.reference3!=null &&
              this.reference3.equals(other.getReference3()))) &&
            ((this.reference4==null && other.getReference4()==null) || 
             (this.reference4!=null &&
              this.reference4.equals(other.getReference4()))) &&
            ((this.reference5==null && other.getReference5()==null) || 
             (this.reference5!=null &&
              this.reference5.equals(other.getReference5()))) &&
            ((this.reference6==null && other.getReference6()==null) || 
             (this.reference6!=null &&
              this.reference6.equals(other.getReference6())));
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
        _hashCode += (isDocumentWide() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getUserExit() != null) {
            _hashCode += getUserExit().hashCode();
        }
        if (getActionCode() != null) {
            _hashCode += getActionCode().hashCode();
        }
        _hashCode += (isCombine() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCombinedChecking() != null) {
            _hashCode += getCombinedChecking().hashCode();
        }
        if (getReference1() != null) {
            _hashCode += getReference1().hashCode();
        }
        if (getReference2() != null) {
            _hashCode += getReference2().hashCode();
        }
        if (getReference3() != null) {
            _hashCode += getReference3().hashCode();
        }
        if (getReference4() != null) {
            _hashCode += getReference4().hashCode();
        }
        if (getReference5() != null) {
            _hashCode += getReference5().hashCode();
        }
        if (getReference6() != null) {
            _hashCode += getReference6().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExternalReferences.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExternalReferences"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentWide");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocumentWide"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userExit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserExit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ActionCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("combine");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Combine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("combinedChecking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "CombinedChecking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefCombined"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRef"));
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
