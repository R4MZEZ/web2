package beans;

import java.util.LinkedList;
import servlets.AreaCheckServlet;

public class JustBean {
  private final LinkedList<AreaCheckServlet.Point> points = new LinkedList<>();

  public void addPoint(AreaCheckServlet.Point point) {
    this.points.add(point);
  }

  public LinkedList<AreaCheckServlet.Point> getPoints() {
    return points;
  }

  public void clearPoints(){
    points.clear();
  }

}