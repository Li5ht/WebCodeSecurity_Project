<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Save Key Pair</title>
</head>
<body>
    <h1>Save Key Pair</h1>
    <form method="post" action="KeyPairAction.jsp">
        <label for="publicKeyFileName">공개키 파일 이름:</label>
        <input type="text" id="publicKeyFileName" name="publicKeyFileName" required>
        <br>
        <label for="privateKeyFileName">개인키 파일 이름:</label>
        <input type="text" id="privateKeyFileName" name="privateKeyFileName" required>
        <br>
        <input type="submit" value="키 저장">
    </form>
</body>
</html>
