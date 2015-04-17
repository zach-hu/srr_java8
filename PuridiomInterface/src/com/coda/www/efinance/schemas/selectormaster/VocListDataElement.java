/**
 * VocListDataElement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.selectormaster;


/**
 * A data element on the vocabulary
 *             list.
 */
public class VocListDataElement  implements java.io.Serializable {
    private java.lang.String logical;

    /* The
     *                         number of opening parentheses used to group
     * the
     *                         data elements. */
    private java.lang.String openBracket;

    /* The
     *                         vocabulary name of the data item that you
     * want
     *                         to select. */
    private java.lang.String vocabID;

    /* The
     *                         symbol signifying the operation to be performed
     * on the selected vocabulary
     *                     item. */
    private java.lang.String operatorID;

    /* The
     *                         value of the parameter or start of a range,
     * if
     *                     applicable. */
    private java.lang.String from;

    /* The last value in the range,
     *                         if applicable. */
    private java.lang.String to;

    /* If TRUE specifies that
     *                         editing of this parameter will be allowed
     * at run
     *                     time. */
    private java.lang.String modify;

    /* The
     *                         text that prompts the user for input at run
     * time, if you have allowed
     *                     modification. */
    private java.lang.String prompt;

    /* The
     *                         number of closing parentheses used to group
     * the
     *                         data elements. */
    private java.lang.String closeBracket;

    public VocListDataElement() {
    }

    public VocListDataElement(
           java.lang.String logical,
           java.lang.String openBracket,
           java.lang.String vocabID,
           java.lang.String operatorID,
           java.lang.String from,
           java.lang.String to,
           java.lang.String modify,
           java.lang.String prompt,
           java.lang.String closeBracket) {
           this.logical = logical;
           this.openBracket = openBracket;
           this.vocabID = vocabID;
           this.operatorID = operatorID;
           this.from = from;
           this.to = to;
           this.modify = modify;
           this.prompt = prompt;
           this.closeBracket = closeBracket;
    }


    /**
     * Gets the logical value for this VocListDataElement.
     * 
     * @return logical
     */
    public java.lang.String getLogical() {
        return logical;
    }


    /**
     * Sets the logical value for this VocListDataElement.
     * 
     * @param logical
     */
    public void setLogical(java.lang.String logical) {
        this.logical = logical;
    }


    /**
     * Gets the openBracket value for this VocListDataElement.
     * 
     * @return openBracket   * The
     *                         number of opening parentheses used to group
     * the
     *                         data elements.
     */
    public java.lang.String getOpenBracket() {
        return openBracket;
    }


    /**
     * Sets the openBracket value for this VocListDataElement.
     * 
     * @param openBracket   * The
     *                         number of opening parentheses used to group
     * the
     *                         data elements.
     */
    public void setOpenBracket(java.lang.String openBracket) {
        this.openBracket = openBracket;
    }


    /**
     * Gets the vocabID value for this VocListDataElement.
     * 
     * @return vocabID   * The
     *                         vocabulary name of the data item that you
     * want
     *                         to select.
     */
    public java.lang.String getVocabID() {
        return vocabID;
    }


    /**
     * Sets the vocabID value for this VocListDataElement.
     * 
     * @param vocabID   * The
     *                         vocabulary name of the data item that you
     * want
     *                         to select.
     */
    public void setVocabID(java.lang.String vocabID) {
        this.vocabID = vocabID;
    }


    /**
     * Gets the operatorID value for this VocListDataElement.
     * 
     * @return operatorID   * The
     *                         symbol signifying the operation to be performed
     * on the selected vocabulary
     *                     item.
     */
    public java.lang.String getOperatorID() {
        return operatorID;
    }


    /**
     * Sets the operatorID value for this VocListDataElement.
     * 
     * @param operatorID   * The
     *                         symbol signifying the operation to be performed
     * on the selected vocabulary
     *                     item.
     */
    public void setOperatorID(java.lang.String operatorID) {
        this.operatorID = operatorID;
    }


    /**
     * Gets the from value for this VocListDataElement.
     * 
     * @return from   * The
     *                         value of the parameter or start of a range,
     * if
     *                     applicable.
     */
    public java.lang.String getFrom() {
        return from;
    }


    /**
     * Sets the from value for this VocListDataElement.
     * 
     * @param from   * The
     *                         value of the parameter or start of a range,
     * if
     *                     applicable.
     */
    public void setFrom(java.lang.String from) {
        this.from = from;
    }


    /**
     * Gets the to value for this VocListDataElement.
     * 
     * @return to   * The last value in the range,
     *                         if applicable.
     */
    public java.lang.String getTo() {
        return to;
    }


    /**
     * Sets the to value for this VocListDataElement.
     * 
     * @param to   * The last value in the range,
     *                         if applicable.
     */
    public void setTo(java.lang.String to) {
        this.to = to;
    }


    /**
     * Gets the modify value for this VocListDataElement.
     * 
     * @return modify   * If TRUE specifies that
     *                         editing of this parameter will be allowed
     * at run
     *                     time.
     */
    public java.lang.String getModify() {
        return modify;
    }


    /**
     * Sets the modify value for this VocListDataElement.
     * 
     * @param modify   * If TRUE specifies that
     *                         editing of this parameter will be allowed
     * at run
     *                     time.
     */
    public void setModify(java.lang.String modify) {
        this.modify = modify;
    }


    /**
     * Gets the prompt value for this VocListDataElement.
     * 
     * @return prompt   * The
     *                         text that prompts the user for input at run
     * time, if you have allowed
     *                     modification.
     */
    public java.lang.String getPrompt() {
        return prompt;
    }


    /**
     * Sets the prompt value for this VocListDataElement.
     * 
     * @param prompt   * The
     *                         text that prompts the user for input at run
     * time, if you have allowed
     *                     modification.
     */
    public void setPrompt(java.lang.String prompt) {
        this.prompt = prompt;
    }


    /**
     * Gets the closeBracket value for this VocListDataElement.
     * 
     * @return closeBracket   * The
     *                         number of closing parentheses used to group
     * the
     *                         data elements.
     */
    public java.lang.String getCloseBracket() {
        return closeBracket;
    }


    /**
     * Sets the closeBracket value for this VocListDataElement.
     * 
     * @param closeBracket   * The
     *                         number of closing parentheses used to group
     * the
     *                         data elements.
     */
    public void setCloseBracket(java.lang.String closeBracket) {
        this.closeBracket = closeBracket;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VocListDataElement)) return false;
        VocListDataElement other = (VocListDataElement) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.logical==null && other.getLogical()==null) || 
             (this.logical!=null &&
              this.logical.equals(other.getLogical()))) &&
            ((this.openBracket==null && other.getOpenBracket()==null) || 
             (this.openBracket!=null &&
              this.openBracket.equals(other.getOpenBracket()))) &&
            ((this.vocabID==null && other.getVocabID()==null) || 
             (this.vocabID!=null &&
              this.vocabID.equals(other.getVocabID()))) &&
            ((this.operatorID==null && other.getOperatorID()==null) || 
             (this.operatorID!=null &&
              this.operatorID.equals(other.getOperatorID()))) &&
            ((this.from==null && other.getFrom()==null) || 
             (this.from!=null &&
              this.from.equals(other.getFrom()))) &&
            ((this.to==null && other.getTo()==null) || 
             (this.to!=null &&
              this.to.equals(other.getTo()))) &&
            ((this.modify==null && other.getModify()==null) || 
             (this.modify!=null &&
              this.modify.equals(other.getModify()))) &&
            ((this.prompt==null && other.getPrompt()==null) || 
             (this.prompt!=null &&
              this.prompt.equals(other.getPrompt()))) &&
            ((this.closeBracket==null && other.getCloseBracket()==null) || 
             (this.closeBracket!=null &&
              this.closeBracket.equals(other.getCloseBracket())));
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
        if (getLogical() != null) {
            _hashCode += getLogical().hashCode();
        }
        if (getOpenBracket() != null) {
            _hashCode += getOpenBracket().hashCode();
        }
        if (getVocabID() != null) {
            _hashCode += getVocabID().hashCode();
        }
        if (getOperatorID() != null) {
            _hashCode += getOperatorID().hashCode();
        }
        if (getFrom() != null) {
            _hashCode += getFrom().hashCode();
        }
        if (getTo() != null) {
            _hashCode += getTo().hashCode();
        }
        if (getModify() != null) {
            _hashCode += getModify().hashCode();
        }
        if (getPrompt() != null) {
            _hashCode += getPrompt().hashCode();
        }
        if (getCloseBracket() != null) {
            _hashCode += getCloseBracket().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VocListDataElement.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "VocListDataElement"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logical");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Logical"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openBracket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "OpenBracket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "VocabID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operatorID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "OperatorID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "From"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextWild"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("to");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "To"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextWild"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modify");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Modify"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prompt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Prompt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("closeBracket");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "CloseBracket"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
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
