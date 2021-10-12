package tools;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import servlets.AreaCheckServlet;
import servlets.AreaCheckServlet.Point;

public class JSONConverter {

  public static String toJSON(AreaCheckServlet.Point point) {
    return String.format("{\"x\":\"%f\",\"y\":\"%f\",\"r\":\"%f\"," +
            "\"currentTime\":\"%s\",\"scriptTime\":\"%d\",\"hit\":\"%b\",\"id\":\"%d\"}",
        point.getX(), point.getY(), point.getR(),
        point.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
        point.getScript_time() * 1000,
        point.isInArea(), point.getId());
  }

  public static String listToJSON(List<Point> points){
    StringBuilder message = new StringBuilder("[");

    Iterator<Point> iterator = points.iterator();
    while (iterator.hasNext()){
      Point element = iterator.next();
      message.append(toJSON(element));
      if (iterator.hasNext())
        message.append(",");
    }
    message.append("]");

    return message.toString();
  }
}