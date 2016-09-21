<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>${erro_error}</title>
</head>
<body>
${error_sorry}: <br/>
${errorDatabase} <br/>
<a href="controller?command=goToMain">${error_goToMain}}</a>
</body>
</html>