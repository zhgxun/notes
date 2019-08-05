package github.banana.tomcat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/self2")
public class SelF2Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("Content-Type: application/json;charset=UTF-8");
        resp.getWriter().println("{\"name\":\"张三\",\"desc\":\"这是一段描述\",\"type\":\"get2\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("Content-Type: application/json;charset=UTF-8");
        resp.getWriter().println("{\"name\":\"张三\",\"desc\":\"这是一段描述\",\"type\":\"post2\"}");
    }
}
