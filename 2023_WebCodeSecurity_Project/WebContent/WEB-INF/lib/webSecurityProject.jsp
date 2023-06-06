<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.security.*" %>
<%@ page import="javax.crypto.*" %>
<%@ page import="java.util.Base64" %>
<%@ page import="Envelope.EnvelopeGenerator" %>
<%@ page import="Envelope.EnvelopeVerifier" %>
<%@ page import="KeyManager.KeyManager" %>

<%
    // 전자봉투 생성
    String document = "임의의 문서 내용";
    KeyManager keyManager = new KeyManager();
    EnvelopeGenerator envelopeGenerator = new EnvelopeGenerator();
    KeyPair keyPair = keyManager.generateKeyPair();
    String envelope = envelopeGenerator.generateEnvelope(document, keyPair.getPrivate());

    // 전자봉투 검증
    EnvelopeVerifier envelopeVerifier = new EnvelopeVerifier();
    boolean isValid = envelopeVerifier.verifyEnvelope(document, envelope, keyPair.getPublic());
%>


<!DOCTYPE html>
<html>
<head>
    <title>전자봉투 생성 및 검증</title>
</head>
<body>
    <h1>전자봉투 생성 및 검증 결과</h1>
    <p>문서: <%= document %></p>
    <p>전자봉투: <%= envelope %></p>
    <p>전자봉투 검증 결과: <%= isValid ? "유효함" : "유효하지 않음" %></p>
</body>
</html>
