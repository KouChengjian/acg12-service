<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<body>
<h2>Hello World!</h2>


<button onclick="ssss()">ssss</button>


<script>
    function ssss() {
        <%
          for (int i = 0 ; i < 10 ; i++){
              out.println("hello world");
              System.out.println("hello world");

          }
        %>
    }
</script>

</body>
</html>
