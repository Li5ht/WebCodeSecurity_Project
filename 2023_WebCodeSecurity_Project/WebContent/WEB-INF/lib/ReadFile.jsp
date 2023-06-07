<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>키 읽기</title>
</head>
<body>
    <h1>공개키 읽기</h1>
    <%
        String publicFileName = "공개키 파일 경로"; // 공개키 파일 경로를 지정하세요

        try (BufferedReader br = new BufferedReader(new FileReader(publicFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                out.println(line + "<br>");
            }
        } catch (Exception e) {
            out.println("파일을 읽는 중에 오류가 발생했습니다.");
            e.printStackTrace();
        }
    %>

    <h1>개인키 읽기</h1>
    <%
        String privateFileName = "개인키 파일 경로"; // 개인키 파일 경로를 지정하세요

        try (BufferedReader br = new BufferedReader(new FileReader(privateFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                out.println(line + "<br>");
            }
        } catch (Exception e) {
            out.println("파일을 읽는 중에 오류가 발생했습니다.");
            e.printStackTrace();
        }
    %>

    <h1>대칭키 읽기</h1>
    <%
        String symmetricKeyFileName = "대칭키 파일 경로"; // 대칭키 파일 경로를 지정하세요

        try (BufferedReader br = new BufferedReader(new FileReader(symmetricKeyFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                out.println(line + "<br>");
            }
        } catch (Exception e) {
            out.println("파일을 읽는 중에 오류가 발생했습니다.");
            e.printStackTrace();
        }
    %>
</body>
</html>