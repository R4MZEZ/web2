package beans;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "points")
public class Point {

  private int id;
  private double x;
  private double y;
  private double r;
  private String hit;
  private String time;
  private long script_time;
  private static int nextID = 1;


  public Point(double x, double y, double r, long startTime) {
    this.id = nextID++;
    this.x = x;
    this.y = y;
    this.r = r;
    hit = calculate(x, y, r) ? "Попадание" : "Промах";
    time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    script_time = System.currentTimeMillis() - startTime;
  }

  public Point() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setR(double r) {
    this.r = r;
  }

  public double getR() {
    return r;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getTime() {
    return time;
  }

  public void setScript_time(long script_time) {
    this.script_time = script_time;
  }

  public long getScript_time() {
    return script_time;
  }


  public String getHit() {
    return hit;
  }

  public void setHit(String hit) {
    this.hit = hit;
  }

  public boolean calculate(double x, double y, double r) {

    if (x >= 0 && y >= 0) {
      return x * x + y * y <= r * r / 4;
    }
    if (x <= 0 && y <= 0) {
      return y >= -r && x >= -r / 2;
    }
    if (x >= 0 && y <= 0) {
      return y >= x - r / 2;
    }
    return false;
  }


}
