<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <p>
            <%
                Object[] listOfFiles = (Object[]) request.getAttribute("listOfFiles");
                Object curUrl = request.getAttribute("curUrl");
                out.println("<p>" + curUrl.toString() + "</p>");
                for (Object element : listOfFiles) {
                    Object elementUrl = curUrl.toString()  + "?path=" + element.toString();
                    out.println("<p>" + elementUrl.toString() + "</p>");
                    out.println("<p><a href = " + elementUrl + ">" + element.toString() + "</a></p>");
                }
            %>
        </p>

        <script>
        function deleteFunction() {
            http.send();
        }
        </script>

        <a href="" onclick="deleteFunction">Выйти из аккаунта</a>
    </body>
</html>