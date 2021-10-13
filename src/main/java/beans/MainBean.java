package beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@ManagedBean(name = "mainBean")
@SessionScoped
public class MainBean implements Serializable {

  private float y = 0;
  private float x = 0;
  private int r = 1;
  private ArrayList<Point> pointList = new ArrayList<>();

  public MainBean() {
    DatabaseHandler databaseHandler = new DatabaseHandler();
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public void setR1() {
    r = 1;
  }

  public void setR2() {
    r = 2;
  }

  public void setR3() {
    r = 3;
  }

  public void setR4() {
    r = 4;
  }

  public void setR5() {
    r = 5;
  }

  public void setR(int r) {
    this.r = r;
  }

  public int getR() {
    return r;
  }

  public ArrayList<Point> getPointList() {
    return pointList;
  }

  public void setPointList(ArrayList<Point> pointList) {
    this.pointList = pointList;
  }

  public void checkPoint(){
    Point point = new Point(getX(), getY(), getR(), System.currentTimeMillis());
    pointList.add(point);
  }

  public void nothing(){

  }

  public void clearPoints() {
    pointList.clear();
  }

  @Entity
  @Table(name = "points")
  public static class Point {

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
    public void setId(int id){
      this.id = id;
    }

    public double getX() {
      return x;
    }
    public void setX(double x){
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
    public void setHit(String hit){
      this.hit = hit;
    }

    public boolean calculate(double x, double y, double r) {

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


  }
}

