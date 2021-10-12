package servlets;

import beans.JustBean;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import tools.JSONConverter;

@WebServlet(name = "check_area", urlPatterns = {"/check_area"})
public class AreaCheckServlet extends HttpServlet {

  private JustBean justBean;

  @Override
  public void init() {
    justBean = new JustBean();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.sendRedirect("/controller");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    long startTime = System.currentTimeMillis();

    try (PrintWriter out = response.getWriter()) {


      double x = 0;
      double r = 0;
      double y = 0;

      try {
        x = Double.parseDouble(request.getParameter("x").replace(',', '.').trim());
        if (x < -2 || x > 2) {
          response.sendRedirect("/controller");
        }
      } catch (NumberFormatException e) {
        response.sendRedirect("/controller");
      }

      try {
        y = Double.parseDouble(request.getParameter("y").replace(',', '.').trim());
        if (y < -5 || y > 3) {
          response.sendRedirect("/controller");
        }
      } catch (NumberFormatException e) {
        response.sendRedirect("/controller");
      }

      try {
        r = Double.parseDouble(request.getParameter("r").replace(',', '.').trim());
        if (r < 1 || r > 5) {
          response.sendRedirect("/controller");
        }
      } catch (NumberFormatException e) {
        response.sendRedirect("/controller");
      }

      Point point = new Point(x, y, r, startTime);

      justBean.addPoint(point);
      LinkedList<Point> points = justBean.getPoints();

      System.out.println(Arrays.toString(points.toArray()));
//      Collections.reverse(points);

      out.println(JSONConverter.listToJSON(points));
    }
  }

  protected void doDelete(HttpServletRequest request, HttpServletResponse response){
    justBean.clearPoints();
  }

  public static class Point {

    private final int id;
    private final double x;
    private final double y;
    private final double r;
    private final boolean isInArea;
    private final LocalDateTime time;
    private final long script_time;
    private static int nextID = 1;

    public Point(double x, double y, double r, long startTime) {

      this.id = nextID++;
      this.x = x;
      this.y = y;
      this.r = r;
      isInArea = setInArea(x, y, r);
      time = LocalDateTime.now();
      script_time = System.currentTimeMillis() - startTime;
    }

    public int getId() {
      return id;
    }

    public double getX() {
      return x;
    }

    public double getY() {
      return y;
    }

    public double getR() {
      return r;
    }

    public boolean isInArea() {
      return isInArea;
    }

    public boolean setInArea(double x, double y, double r) {

      if (x >= 0 && y >= 0) {
        return x * x + y * y <= r * r / 4;
      }
      if (x <= 0 && y <= 0) {
        return y >= -r && x >= -r/2;
      }
      if (x>=0 && y <=0){
        return y >= x - r/2;
      }
      return false;
    }

    public LocalDateTime getTime() {
      return time;
    }

    public long getScript_time() {
      return script_time;
    }
  }
}