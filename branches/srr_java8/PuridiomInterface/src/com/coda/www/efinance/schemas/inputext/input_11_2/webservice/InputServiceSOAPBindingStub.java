/**
 * InputServiceSOAPBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class InputServiceSOAPBindingStub extends org.apache.axis.client.Stub implements com.coda.www.efinance.schemas.inputext.input_11_2.webservice.InputServicePortTypes {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[31];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetMissingDocNumbers");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetMissingDocNumbersRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingDocNumbersRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingDocNumbersResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetMissingDocNumbersResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SelectTemplate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "SelectTemplateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectTemplateRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectTemplateResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "SelectTemplateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetElmBanks");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetElmBanksRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetElmBanksRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetElmBanksResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetElmBanksResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SetHeader");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "SetHeaderRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SetHeaderRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SetHeaderResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "SetHeaderResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetHardenedDates");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetHardenedDatesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetHardenedDatesRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetHardenedDatesResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetHardenedDatesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GenerateTaxWithoutTemplate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GenerateTaxWithoutTemplateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxWithoutTemplateRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxWithoutTemplateResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GenerateTaxWithoutTemplateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetUserStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetUserStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetUserStatusRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetUserStatusResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetUserStatusResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("PostToIntray");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostToIntrayRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToIntrayRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToIntrayResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostToIntrayResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GenerateTax");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GenerateTaxRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GenerateTaxResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Resolve");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "ResolveRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">ResolveRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">ResolveResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "ResolveResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("BalancingReview");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "BalancingReviewRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">BalancingReviewRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">BalancingReviewResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "BalancingReviewResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetMissingStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetMissingStatusRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingStatusRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingStatusResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetMissingStatusResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IntrayAutoPost");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayAutoPostRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayAutoPostRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayAutoPostResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayAutoPostResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CurrReview");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CurrReviewRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CurrReviewRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CurrReviewResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CurrReviewResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IntrayUnAuthorise");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayUnAuthoriseRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayUnAuthoriseRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayUnAuthoriseResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayUnAuthoriseResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CancelDoc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CancelDocRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CancelDocRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CancelDocResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CancelDocResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IntrayDelete");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayDeleteRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayDeleteRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayDeleteResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayDeleteResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CheckPostWithoutTemplate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CheckPostWithoutTemplateRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostWithoutTemplateRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostWithoutTemplateResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CheckPostWithoutTemplateResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Post");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetCustomerSupplierElmSummary");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetCustomerSupplierElmSummaryRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCustomerSupplierElmSummaryRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCustomerSupplierElmSummaryResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetCustomerSupplierElmSummaryResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("PostToBooks");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostToBooksRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToBooksRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToBooksResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "PostToBooksResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IntrayLoad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayLoadRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayLoadRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayLoadResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayLoadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CopyDoc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CopyDocRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CopyDocRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CopyDocResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CopyDocResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetCopyDocParams");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetCopyDocParamsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCopyDocParamsRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCopyDocParamsResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetCopyDocParamsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CheckPost");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CheckPostRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CheckPostResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GetElmAddresses");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetElmAddressesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetElmAddressesRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetElmAddressesResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GetElmAddressesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RefreshTotals");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "RefreshTotalsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">RefreshTotalsRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">RefreshTotalsResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "RefreshTotalsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CalcCurrencies");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CalcCurrenciesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CalcCurrenciesRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CalcCurrenciesResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CalcCurrenciesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SelectIntray");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "SelectIntrayRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectIntrayRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectIntrayResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "SelectIntrayResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("GenerateBalancingLines");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GenerateBalancingLinesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateBalancingLinesRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateBalancingLinesResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "GenerateBalancingLinesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("AutoBalanceTax");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "AutoBalanceTaxRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">AutoBalanceTaxRequest"), com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">AutoBalanceTaxResponse"));
        oper.setReturnClass(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "AutoBalanceTaxResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

    }

    public InputServiceSOAPBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public InputServiceSOAPBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public InputServiceSOAPBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
        addBindings0();
        addBindings1();
        addBindings2();
        addBindings3();
        addBindings4();
        addBindings5();
    }

    private void addBindings0() {
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
            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Attachment");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.attachment.Attachment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "GetReferenceAttachmentDisplayURLResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.attachment.GetReferenceAttachmentDisplayURLResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.attachment.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "ObjectUsageSummary");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.attachment.ObjectUsageSummary.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "SummariseObjectUsageResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.attachment.SummariseObjectUsageResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "ValidateReferenceAttachmentResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.attachment.ValidateReferenceAttachmentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "AddResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.AddResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "DeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.DeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "FindResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.FindResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "ListResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.ListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFDefinedValue");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.UDFDefinedValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFDefinedValueList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.UDFDefinedValue[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFDefinedValue");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFDefinedValue");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMaster");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.UDFMaster.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterItemList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.UDFMasterListItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterListItem");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterListItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.UDFMaster[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMaster");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMaster");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UDFMasterListItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.UDFMasterListItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/common/schemas/udfmaster", "UpdateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.common.schemas.udfmaster.UpdateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "CurrRateValue");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.CurrRateValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "CurrValue");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.CurrValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ExtensionRef");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ExtensionRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ListFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ListFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "MultiCompanyResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.MultiCompanyResponse.class;
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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReqKeys");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ReqKeys.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ReqKeysWithShortName");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.ReqKeysWithShortName.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Response");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.Response.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAcCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAcCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAcCodeSubstB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAddress");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAddressName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAstCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeAuthInit");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankAccName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankAccNum");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankAccRef");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankSort");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBankSwift");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseTime");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYear");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBool");
            cachedSerQNames.add(qName);
            cls = boolean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBoolean");
            cachedSerQNames.add(qName);
            cls = boolean.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCloseBracket");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCodeWild");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeComUdfField");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeComUdfListingMethod");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCorbaIor");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCrAgy");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCrPayIndex");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCrRating");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCrRef");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtAccessLog");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtAccessOp");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtAcCodeAccessLevel");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtAmountRule");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtARCOptions");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtAssetDest");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtAuthorisingUser");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtBalElmRule");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCalcDisc");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCalcTax");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtControlTotals");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCreditLimitCurrency");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCurLinkType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCurRateControl");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCurrencyRule");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtCustSuppExt");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocCancel");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocCurr");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocDefSense");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocDest");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocDestCtrl");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocExtAcRule");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocExtRefUsage");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocLineDrCrB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocLineOrigin");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocLineTypeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocNumberType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocPost");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocSeqRule");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDocumentType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtDupXR");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtElmVocabEnum");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtForceDisperse");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtIntrayCheck");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtLanguageInf");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtLeftBracketVocab");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtModDueDate");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtModValDate");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

    }
    private void addBindings1() {
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
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtMulDiv");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtOneOfN");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtPeriodUsage");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtPhasingRule");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtPlBal");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtResponsibilityUsage");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtRightBracketVocab");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtRllLogical");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtRllOperator");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtRllVocab");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtSalesInvoiceStatus");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtSplit");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtStatPayDoc");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtStatPayDocLine");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtStatPayElm");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtStatRec");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtSummary");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtTaxMethod");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtUserRefType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWFlowHdrStatus");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWFlowHdrStatusNoAny");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWFlowLineStatus");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWFlowType");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWhichCur");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWorkflowAuthReq");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWorkflowRestart");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtYesNoEither");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtYesNoSome");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDate");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDateB");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDateRule");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDbGuid");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDecPlace");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDescr");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDescrB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDllName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDocNum");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDocNumAlpha");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDocNumAlphaB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDocNumWild");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmCodeWild");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmLevel");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmLevelB");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeElmSubsLevel");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeEMailAddr");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeEMailAddrB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeExtRefB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeFax");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeFederalTax");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeHardTime");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeIBANB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeItemCodeB");
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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeLanguage");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeLogTitle");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeLong");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeMatchingOffsetDays");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeMoney");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeMoneyB");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeName");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeNameB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeNameTextValues");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeNum");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeOpenBracket");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePercent");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePeriod");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePeriodAny");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePeriodB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePhasing");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePosixTime");
            cachedSerQNames.add(qName);
            cls = java.util.Calendar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typePostCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeQtyTitle");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeRate");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeRateB");
            cachedSerQNames.add(qName);
            cls = java.math.BigDecimal.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeResponseStatus");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.TypeResponseStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeShortName");
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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeSIC");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeSocialSecurity");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeStatUserB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeStoreCloseReason");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTel");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTerm");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTermB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAny");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAnyB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAnyDbQuotes");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextAnyUnlimited");
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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextWild");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTimeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTraderCode");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTraderCodeB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

    }
    private void addBindings2() {
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
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTraderCodeWild");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUdfText");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeULong");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeULongB");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUpper");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUri");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUserRefB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUserStatusB");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUWord");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeUWordB");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeVATB");
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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeWordB");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeYear");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "Warning");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.common.Warning.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AddResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.AddResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AnalysisLines");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.AnalysisLines.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "BalancingElements");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.BalancingElements.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Currency");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.Currency.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.DeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocNumList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.DocNumListItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocNumListItem");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocNumListItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocNumListItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.DocNumListItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocReserveKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.DocReserveKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "docSelectKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.DocSelectKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocTax");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.DocTax.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocumentMaster");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.DocumentMaster.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DueDate");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.DueDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExternalReferences");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ExternalReferences.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRef");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ExtRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefChecking");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ExtRefChecking.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefCombined");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ExtRefCombined.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefCompare");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ExtRefCompare.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "FieldAccess");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.FieldAccess.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "GetNoListsResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.GetNoListsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Intercompany");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.Intercompany.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ListResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Master");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.Master.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Pay");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.Pay.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PayData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.PayData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PayElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.PayElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PDL");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.PDL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PDLExtRefs");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.PDLExtRefs.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PDLList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.PDL[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PDL");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PreDefinedLine");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PreDefinedLines");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.PreDefinedLines.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PrintDocSelectKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.PrintDocSelectKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectedElements");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ProtectedElements.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Quantities");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.Quantities.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Recurring");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.Recurring.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ReleaseReservationResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ReleaseReservationResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ReserveResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ReserveResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "SummaryLines");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.SummaryLines.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UpdateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.UpdateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReference");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.UserReference.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReferences");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.UserReferences.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ValueDate");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.documentmaster.ValueDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "AddResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.AddResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "DeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.DeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterItem");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterItem");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterListFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterListFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ElementFilterMaster");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.ElementFilterMaster.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "ListResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.ListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "TestResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.TestResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementfiltermaster", "UpdateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementfiltermaster.UpdateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.AddResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.AddressElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.AddressElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddTraderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.AddTraderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AllCmpTraderFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.AllCmpTraderFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.BankElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Bank");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.BankElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CommentData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextB");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Comment");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.DeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.DiscountElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Discount");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.DiscountElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Element");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.Element.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElementFinderFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElementFinderFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElementFinderNumberRange");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElementFinderNumberRange.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmFullKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKeyWithTemporaryElmFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmFullKeyWithTemporaryElmFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElementNamed");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataNamed");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmKeyDataElementNamed[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmKeyDataElementNamed");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Key");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmReqFullKeys");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmReqFullKeys.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmTemporaryElmFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ElmTemporaryElmFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FetchKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.FetchKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FetchResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.FetchResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FilterResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.FilterResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FinderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.FinderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GetAddressesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.GetAddressesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GetBanksResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.GetBanksResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GetTraderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.GetTraderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GroupData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GroupCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransaction");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.LastTransaction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransactions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.LastTransactions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings3() {
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
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ListAllTraderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ListAllTraderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ListPunchoutItemCodesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ListPunchoutItemCodesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ListResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ListTraderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.ListTraderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Master");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.Master.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MediaData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MediaCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MnemonicData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Mnemonic");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OutputDeviceData");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OutputDeviceCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.PunchoutElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutAdvancedParam");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.PunchoutElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PurgeTraderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.PurgeTraderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "QuantityData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.QuantityElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "QuantityElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Quantity");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "QuantityElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.QuantityElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RetailData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailStoreTemporaryClosure");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RetailStoreTemporaryClosure.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailStoreTemporaryClosures");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RetailStoreTemporaryClosure[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailStoreTemporaryClosure");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Closure");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RuleElement[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleElement");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Rule");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleElement");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.RuleElement.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SalesInvoiceData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRow");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRow.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceUDFRowEx");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SalesInvoiceUDFRowEx.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SelectTraderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SelectTraderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.SetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Tax");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.Tax.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Trader");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.Trader.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderAddress");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.TraderAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderBank");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.TraderBank.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderCodes");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTraderCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderDetail");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.TraderDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderDetails");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.TraderDetail[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderDetail");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Detail");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.TraderKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectDetail");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.TraderSelectDetail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectDetails");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.TraderSelectDetail[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectDetail");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Detail");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderSelectFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.TraderSelectFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDateField");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFDateField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDateFields");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFDateField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDateField");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFDate");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElmField");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFElmField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElmFields");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFElmField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElmField");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFElm");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberField");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFNumberField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberFields");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFNumberField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumberField");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFNumber");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFTextField");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFTextField.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFTextFields");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UDFTextField[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFTextField");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UDFText");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UmbrellaData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UmbrellaData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UpdateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UpdateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UpdateTraderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.elementmaster.UpdateTraderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "VocabList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtElmVocabEnum");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "VocabID");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">>CheckPostRequest>CheckPostOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequestCheckPostOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">>CheckPostWithoutTemplateRequest>CheckPostWithoutTemplateOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateRequestCheckPostWithoutTemplateOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">>PostRequest>PostOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostRequestPostOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">>PostToBooksRequest>PostToBooksOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequestPostToBooksOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">>PostToIntrayRequest>PostToIntrayOptions");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayRequestPostToIntrayOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">AutoBalanceTaxRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">AutoBalanceTaxResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">BalancingReviewRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">BalancingReviewResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CalcCurrenciesRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CalcCurrenciesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CancelDocRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CancelDocResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostWithoutTemplateRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CheckPostWithoutTemplateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CopyDocRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CopyDocResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CurrReviewRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CurrReviewResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateBalancingLinesRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateBalancingLinesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxWithoutTemplateRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GenerateTaxWithoutTemplateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCopyDocParamsRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCopyDocParamsResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCustomerSupplierElmSummaryRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetCustomerSupplierElmSummaryResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetElmAddressesRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetElmAddressesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetElmBanksRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetElmBanksResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetHardenedDatesRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetHardenedDatesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingDocNumbersRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingDocNumbersResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingStatusRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetMissingStatusResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetUserStatusRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">GetUserStatusResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayAutoPostRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayAutoPostResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayDeleteRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayDeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayLoadRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayLoadResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayUnAuthoriseRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayUnAuthoriseResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings4() {
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
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToBooksRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToBooksResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToIntrayRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">PostToIntrayResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">RefreshTotalsRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">RefreshTotalsResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">ResolveRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">ResolveResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectIntrayRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectIntrayResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectTemplateRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SelectTemplateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SetHeaderRequest");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">SetHeaderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "AutoBalanceTaxResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.AutoBalanceTaxResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BalancingCurrReview");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.BalancingCurrReview.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BalancingReview");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.BalancingReview.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BalancingReviewResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.BalancingReviewResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CalcCurrResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CalcCurrResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CalcCurrResponseVerb");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CalcCurrResponseVerb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancelDoc");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CancelDoc.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancelDocInterCompanyWarning");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CancelDocInterCompanyWarning.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancelDocResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CancelDocResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancelDocResults");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CancelDocResults.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CheckPostResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CheckPostResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CheckPostResponseVerb");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CheckPostResponseVerb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CheckPostWithoutTemplateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CheckPostWithoutTemplateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CheckPostWithoutTemplateResponseVerb");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CheckPostWithoutTemplateResponseVerb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CopyDocResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CopyDocResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrencyInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrencyInfo");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Info");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrEdit");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrEdit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrEdits");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrEdit[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrEdit");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Edit");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrencyInfo");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrencyInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReview");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrReview.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewCurrRateValue");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewCurrRateValueList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewCurrRateValue");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Currency");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewLine");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrReviewLine.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewLineList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrReviewLine[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewLine");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Line");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CurrReviewResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CustomerSupplierElmSummary");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CustomerSupplierElmSummaryLine[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CustomerSupplierElmSummaryLine");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Line");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CustomerSupplierElmSummaryLine");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.CustomerSupplierElmSummaryLine.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocTotals");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.DocTotals.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocumentWideData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.DocumentWideData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmReview");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ElmReviewItem[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmReviewItem");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmReviewItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ElmReviewItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraDiscountData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ExtraDiscountInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraDiscountInfo");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Discount");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraDiscountInfo");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ExtraDiscountInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraPayData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ExtraPayData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraQuantityData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ExtraQuantityInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraQuantityInfo");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Qty");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraQuantityInfo");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ExtraQuantityInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTaxData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTaxInfo");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Tax");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTaxInfo");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ExtraTaxInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTen99Data");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTaxInfo");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Ten99");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GenerateBalancingLinesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GenerateBalancingLinesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GenerateTaxResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GenerateTaxResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GenerateTaxWithoutTemplateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GenerateTaxWithoutTemplateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetCopyDocParamsResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GetCopyDocParamsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetCustomerSupplierElmSummaryResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GetCustomerSupplierElmSummaryResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetElmAddressesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GetElmAddressesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetElmBanksResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GetElmBanksResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetHardenedDatesResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GetHardenedDatesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetMissingDocNumbersResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GetMissingDocNumbersResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetMissingStatusResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GetMissingStatusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "GetUserStatusResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.GetUserStatusResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HardenedDatesParams");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.HardenedDatesParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HardenedDatesResults");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.HardenedDatesResults.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayAutoPostInfo");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayAutoPostInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayAutoPostResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayAutoPostResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayAutoPostResults");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayAutoPostResults.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayDeleteInfo");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayDeleteInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayDeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayDeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayDeleteResults");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayDeleteResults.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayFilter");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayFilter.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayItem");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayItem.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayItems");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayItems.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayKeys");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.TxnKey[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Key");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayLoadResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayLoadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayPostKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayPostKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayPostKeys");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayPostKey[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayPostKey");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Key");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayPostResult");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayPostResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayResult");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayUnAuthoriseResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.IntrayUnAuthoriseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingDocNumber");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.MissingDocNumber.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingDocNumbers");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.MissingDocNumber[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingDocNumber");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingDocNumber");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingQuantityData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.MissingQuantityInfo[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingQuantityInfo");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Qty");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingQuantityInfo");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.MissingQuantityInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PayValue");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.PayValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.PostData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.PostResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostResponseVerb");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.PostResponseVerb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostToXResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.PostToXResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PostToXResponseVerb");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.PostToXResponseVerb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemLine");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ProblemLine.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemLines");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ProblemLine[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemLine");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Line");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemPay");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ProblemPay.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Problems");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.Problems.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemUserReference");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ProblemUserReference.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RecurringPostData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.RecurringPostData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RefreshTotalsResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.RefreshTotalsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RefreshTotalsResponseVerb");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.RefreshTotalsResponseVerb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings5() {
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
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ResolveResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ResolveResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ReversingPostData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.ReversingPostData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "SelectedDocList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeDocNum");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocNumber");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "SelectIntrayResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.SelectIntrayResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "SelectTemplateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.SelectTemplateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "SetHeaderResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.SetHeaderResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "SetHeaderResponseVerb");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.SetHeaderResponseVerb.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "SpecialDocPostData");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputext.SpecialDocPostData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "AddResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.AddResponse.class;
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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DeleteResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.DeleteResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ListResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.ListResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

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

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "UpdateResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.UpdateResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "VocabEntity");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.inputtemplate.VocabEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input", "PostResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.input.PostResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/input", "PostToXResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.input.PostToXResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccessLevelParams");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.AccessLevelParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Asset");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Asset.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "BasicLine");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.BasicLine.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CheckCodeResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.CheckCodeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeAnswer");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.ChkAccCodeAnswer.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeFailed");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.ChkAccCodeFailed.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeResult");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeShortNameB");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ShortName");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Comments");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextB");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Comment");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Currencies");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Currency[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Currency");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Currency");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Currency");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Currency.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CurrencyInfo");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.CurrencyInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestAndAccount");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.DestAndAccount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestAndAccountList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.DestAndAccount[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestAndAccount");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Account");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discount");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Discount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discounts");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Discount[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discount");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discount");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.ElmQuantities.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetAccessLevelResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.GetAccessLevelResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetDefaultTaxCodeResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.GetDefaultTaxCodeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetDocumentResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.GetDocumentResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.GetResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetResponsibleUsersResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.GetResponsibleUsersResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetResponsibleUsersWithToleranceResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.GetResponsibleUsersWithToleranceResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Header");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Header.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "HeaderWorkflow");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.HeaderWorkflow.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Line");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Line.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Lines");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Line[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Line");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Line");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LineWorkflow");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.LineWorkflow.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "RecurringDiscount");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.RecurringDiscount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "RecurringDiscounts");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.RecurringDiscount[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "RecurringDiscount");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discount");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "SubstituteAccountParams");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.SubstituteAccountParams.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "SubstituteAccountResponse");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.SubstituteAccountResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Tax");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Tax.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Taxes");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Tax[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Tax");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Tax");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Ten99Taxes");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Tax[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Tax");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Ten99Tax");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Transaction");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.Transaction.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.TxnKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserAndTolerance");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.UserAndTolerance.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserAndToleranceList");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.UserAndTolerance[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserAndTolerance");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserAndTolerance");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserCodeList");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserCode");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UsersAndTolerances");
            cachedSerQNames.add(qName);
            cls = com.coda.www.efinance.schemas.transaction.UsersAndTolerances.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "WhichCurrs");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeCtWhichCur");
            qName2 = new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Curr");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

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
     * Retrieves any missing document numbers from the database using
     * the information you provide.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersResponse getMissingDocNumbers(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersRequest getMissingDocNumbersRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GetMissingDocNumbers");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetMissingDocNumbers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getMissingDocNumbersRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingDocNumbersResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves the specified input template master.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateResponse selectTemplate(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateRequest selectTemplateRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/SelectTemplate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "SelectTemplate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {selectTemplateRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectTemplateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves the specified element's bank details.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksResponse getElmBanks(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksRequest getElmBanksRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GetElmBanks");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetElmBanks"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getElmBanksRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmBanksResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Changes the header information on a document.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderResponse setHeader(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderRequest setHeaderRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/SetHeader");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "SetHeader"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {setHeaderRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SetHeaderResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Resolves soft dates set for due date, value date and discount
     * dates in the specified element master.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesResponse getHardenedDates(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesRequest getHardenedDatesRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GetHardenedDates");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetHardenedDates"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getHardenedDatesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetHardenedDatesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * As per GenerateTax with the exception that the document does
     * not need to have a Financials input template.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateResponse generateTaxWithoutTemplate(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateRequest generateTaxWithoutTemplateRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GenerateTaxWithoutTemplate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GenerateTaxWithoutTemplate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {generateTaxWithoutTemplateRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxWithoutTemplateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Derives the user status from the driving element on the specified
     * document line.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusResponse getUserStatus(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusRequest getUserStatusRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GetUserStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetUserStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getUserStatusRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetUserStatusResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Posts the specified document to the Intray.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayResponse postToIntray(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayRequest postToIntrayRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/PostToIntray");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "PostToIntray"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {postToIntrayRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToIntrayResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Generates tax lines for the specified document.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxResponse generateTax(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxRequest generateTaxRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GenerateTax");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GenerateTax"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {generateTaxRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateTaxResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Completes any missing transaction data that can be derived
     * from the account code or from the document value.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveResponse resolve(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveRequest resolveRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/Resolve");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Resolve"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {resolveRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.ResolveResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Returns the sum of values on the specified document for each
     * balancing element and the whole document (in document, home and dual
     * currencies).
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewResponse balancingReview(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewRequest balancingReviewRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/BalancingReview");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "BalancingReview"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {balancingReviewRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.BalancingReviewResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Validates whether the specified document number is a missing
     * document number.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusResponse getMissingStatus(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusRequest getMissingStatusRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GetMissingStatus");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetMissingStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getMissingStatusRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetMissingStatusResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Posts the specified documents on the Intray, to the Books.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostResponse intrayAutoPost(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostRequest intrayAutoPostRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/IntrayAutoPost");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "IntrayAutoPost"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {intrayAutoPostRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayAutoPostResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Returns the currency rates and currency values of all lines
     * in the specified document.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewResponse currReview(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewRequest currReviewRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/CurrReview");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CurrReview"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {currReviewRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CurrReviewResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Removes authorisation from a document that has completed its
     * associated workflow.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseResponse intrayUnAuthorise(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseRequest intrayUnAuthoriseRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/IntrayUnAuthorise");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "IntrayUnAuthorise"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {intrayUnAuthoriseRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayUnAuthoriseResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Posts cancelling documents to cancel the specified document(s)
     * in the Books.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocResponse cancelDoc(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocRequest cancelDocRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/CancelDoc");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CancelDoc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cancelDocRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CancelDocResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Deletes the specified document(s) on the Intray.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteResponse intrayDelete(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteRequest intrayDeleteRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/IntrayDelete");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "IntrayDelete"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {intrayDeleteRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayDeleteResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * As per CheckPost with the exception that the document does
     * not need to have a Financials input template.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateResponse checkPostWithoutTemplate(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateRequest checkPostWithoutTemplateRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/CheckPostWithoutTemplate");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CheckPostWithoutTemplate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {checkPostWithoutTemplateRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostWithoutTemplateResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Posts the specified document to the Books or the Intray.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostResponse post(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostRequest postRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/Post");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Post"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {postRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Returns summary information for the customer/supplier elements
     * in the specified document.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryResponse getCustomerSupplierElmSummary(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryRequest getCustomerSupplierElmSummaryRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GetCustomerSupplierElmSummary");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetCustomerSupplierElmSummary"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getCustomerSupplierElmSummaryRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCustomerSupplierElmSummaryResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Posts the specified document to the Intray.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksResponse postToBooks(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksRequest postToBooksRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/PostToBooks");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "PostToBooks"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {postToBooksRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.PostToBooksResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Loads the specified document on the Intray.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadResponse intrayLoad(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadRequest intrayLoadRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/IntrayLoad");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "IntrayLoad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {intrayLoadRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.IntrayLoadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Creates a new document by copying an existing document and
     * using the details you specify.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocResponse copyDoc(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocRequest copyDocRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/CopyDoc");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CopyDoc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {copyDocRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CopyDocResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves the document date, posting period, and input template
     * master for a document created using the CopyDocRequest.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsResponse getCopyDocParams(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsRequest getCopyDocParamsRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GetCopyDocParams");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetCopyDocParams"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getCopyDocParamsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetCopyDocParamsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Checks whether the specified document is valid for posting
     * to the Books or the Intray.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostResponse checkPost(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostRequest checkPostRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/CheckPost");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CheckPost"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {checkPostRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CheckPostResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Retrieves the specified element's address details.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesResponse getElmAddresses(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesRequest getElmAddressesRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GetElmAddresses");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GetElmAddresses"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {getElmAddressesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GetElmAddressesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Calculates the values to be displayed in the footer fields
     * based on the current information on the document.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsResponse refreshTotals(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsRequest refreshTotalsRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/RefreshTotals");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "RefreshTotals"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {refreshTotalsRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.RefreshTotalsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Calculates the value of a specified line following currency
     * edits.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesResponse calcCurrencies(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesRequest calcCurrenciesRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/CalcCurrencies");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CalcCurrencies"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {calcCurrenciesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.CalcCurrenciesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Selects documents on the Intray, using the specified filter.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayResponse selectIntray(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayRequest selectIntrayRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/SelectIntray");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "SelectIntray"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {selectIntrayRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.SelectIntrayResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Generates balancing lines on the specified document.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesResponse generateBalancingLines(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesRequest generateBalancingLinesRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/GenerateBalancingLines");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "GenerateBalancingLines"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {generateBalancingLinesRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.GenerateBalancingLinesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }


    /**
     * Balances the specified document by editing the generated tax
     * line to bring the document into balance in document currency.
     */
    public com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxResponse autoBalanceTax(com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxRequest autoBalanceTaxRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("uri-coda-webservice/11.200.0306/finance/Input/AutoBalanceTax");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "AutoBalanceTax"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {autoBalanceTaxRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.coda.www.efinance.schemas.inputext.input_11_2.webservice.AutoBalanceTaxResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
