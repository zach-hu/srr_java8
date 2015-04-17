/**
 * InputTemplateServiceSOAPBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice;

public class InputTemplateServiceSOAPBindingStub extends org.apache.axis.client.Stub implements com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.InputTemplateServicePortTypes {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("List");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "ListRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">ListRequest"), com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">ListResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "ListResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Add");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "AddRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">AddRequest"), com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">AddResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "AddResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Update");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "UpdateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">UpdateRequest"), com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">UpdateResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "UpdateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "GetRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">GetRequest"), com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">GetResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "GetResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Delete");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "DeleteRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">DeleteRequest"), com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">DeleteResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", "DeleteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

    }

    public InputTemplateServiceSOAPBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public InputTemplateServiceSOAPBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public InputTemplateServiceSOAPBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Companies");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Company");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "FullKeyDataElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.FullKeyDataElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.Key.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "KeyData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.KeyDataElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "KeyDataElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Key");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "KeyDataElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.KeyDataElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Reason");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.Reason.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReasonText");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ReasonText.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDateB");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmLevel");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeItmCaption");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeItmDerivedB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeItmHeaderValueB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeShortNameB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeWord");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">>AddResponse>MultiCompany");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponseMultiCompany.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">>UpdateResponse>MultiCompany");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateResponseMultiCompany.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">AddRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">AddResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">DeleteRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">DeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">GetRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">ListRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">ListResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">UpdateRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate/inputtemplate-11.1/webservice", ">UpdateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DefinedLine");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.DefinedLine.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DefinedLines");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.DefinedLine[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DefinedLine");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Line");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DocCodeList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DocCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ElementFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.ElementFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ElementFilters");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.ElementFilter[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ElementFilter");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Filter");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "FooterItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.FooterItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "FooterItems");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.FooterItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "FooterItem");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GeneratedLineItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GeneratedLineItems");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GeneratedLineItem");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "HeaderItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.HeaderItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "HeaderItems");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.HeaderItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "HeaderItem");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "InputTemplate");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.InputTemplate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "itmReqKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.ItmReqKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "itmReqKeys");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.ItmReqKeys.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LineItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.LineItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LineItems");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.LineItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LineItem");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Master");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.Master.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "PrintListKeys");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.PrintListKeys.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "typeRowItemRange");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "VocabEntity");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.VocabEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }


    /**
     * Retrieves key information for the specified input template
     * masters from the database.
     */
    public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListResponse list(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListRequest listRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/InputTemplate/List");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "List"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {listRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.ListResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Creates new input template masters in the database using the
     * input template information you provide.
     */
    public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponse add(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddRequest addRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/InputTemplate/Add");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Add"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {addRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.AddResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Updates input templates in the database using the input template
     * information you provide.
     */
    public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateResponse update(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateRequest updateRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/InputTemplate/Update");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Update"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {updateRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.UpdateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves the specified input template masters from the database.
     */
    public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetResponse get(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetRequest getRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/InputTemplate/Get");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Get"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.GetResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Deletes the specified input template masters from the database.
     */
    public com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteResponse delete(com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteRequest deleteRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/InputTemplate/Delete");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Delete"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {deleteRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputtemplate.inputtemplate_11_1.webservice.DeleteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
