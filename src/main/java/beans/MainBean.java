package beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

}

