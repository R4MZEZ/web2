package servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class ControllerServlet extends javax.servlet.http.HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {
    String x = request.getParameter("x");
    String r = request.getParameter("r");
    String y = request.getParameter("y");
    System.out.println("Enter parameters:\nX:" + x + "\nY:" + y + "\nR:" + r);
    if (x == null || y == null || r == null) {
      request.getServletContext().getRequestDispatcher("/index.xhtml").forward(request, response);
    } else {
      request.getServletContext().getRequestDispatcher("/check_area").forward(request, response);
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {
    request.getServletContext().getRequestDispatcher("/index.xhtml").forward(request, response);

  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {
    request.getServletContext().getRequestDispatcher("/check_area").forward(request, response);

  }

}