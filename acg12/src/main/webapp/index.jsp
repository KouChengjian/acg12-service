<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<body>
<h2>Hello World!</h2>


<button onclick="openApiHtml()">open api html</button>
<br/>
<br/>
<button onclick="openAdminHtml()">open admin html</button>
<br/>
<br/>
<button onclick="openClientHtml()">open client html</button>

<script>
    function openApiHtml() {
        window.open("http://localhost:8080/acg12/swagger-ui.html#/");
        <%--<%--%>
          <%--for (int i = 0 ; i < 10 ; i++){--%>
              <%--out.println("hello world");--%>
              <%--System.out.println("hello world");--%>
          <%--}--%>
        <%--%>--%>
    }

    function openAdminHtml() {
        window.open("http://localhost:8080/acg12/resources/jsp/index.jsp");
    }

    function openClientHtml() {
        window.open("http://localhost:8080/acg12/client/index.jsp");
    }
</script>

</body>
</html>
