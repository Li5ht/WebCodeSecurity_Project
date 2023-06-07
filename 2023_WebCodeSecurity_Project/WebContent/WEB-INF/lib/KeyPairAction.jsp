<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.security.KeyPair" %>
<%@ page import="java.security.KeyPairGenerator" %>
<%@ page import="java.security.NoSuchAlgorithmException" %>
<%@ page import="java.security.PrivateKey" %>
<%@ page import="java.security.PublicKey" %>

<%
    // 폼에서 입력된 파일 이름 가져오기
    String publicKeyFileName = request.getParameter("publicKeyFileName");
    String privateKeyFileName = request.getParameter("privateKeyFileName");

    // KeyPair 생성
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(2048);
    KeyPair keyPair = kpg.generateKeyPair();

    PublicKey publicKey = keyPair.getPublic();
    
    PrivateKey privateKey = keyPair.getPrivate();

    // 공개키 저장
    try (FileOutputStream fos = new FileOutputStream(publicKeyFileName)) {
        byte[] encodedKey = publicKey.getEncoded();
        fos.write(encodedKey);
    } catch (IOException e) {
        throw new RuntimeException("공개키를 저장하는 중에 오류가 발생했습니다.", e);
    }

    // 개인키 저장
    try (FileOutputStream fos = new FileOutputStream(privateKeyFileName)) {
        byte[] encodedKey = privateKey.getEncoded();
        
        fos.write(encodedKey);
    } catch (IOException e) {
        throw new RuntimeException("개인키를 저장하는 중에 오류가 발생했습니다.", e);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Save Key Pair</title>
</head>
<body>
    <h1>Save Key Pair</h1>
    <p>키가 성공적으로 저장되었습니다.</p>
</body>
</html>