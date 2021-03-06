import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现一个自定义的 Servlet 容器
 */
public class SelfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get请求示例");
        // 默认未被实现, 需要自行实现
        // super.doGet(req, resp);
        resp.setContentType("Content-Type: application/json;charset=UTF-8");
        resp.getWriter().println("{\"name\":\"张三\",\"desc\":\"这是一段描述\"}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post请求示例");
        // 默认未被实现, 需要自行实现
        // super.doPost(req, resp);
        resp.setContentType("Content-Type: application/json;charset=UTF-8");
        resp.getWriter().println("{\"name\":\"张三\",\"desc\":\"这是一段描述\",\"type\":\"post\"}");
    }
}
