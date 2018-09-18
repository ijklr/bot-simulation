package com.mycompany.app;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class Bot {
  static final List<Integer> xIndices = new ArrayList<>();
  static final List<Integer> yIndices = new ArrayList<>();
  final CustomHttpClient customHttpClient = new CustomHttpClient();
  static final List<Point> sectors = new ArrayList<>();

  int score = 0;
  final String id;
  Point point;
  private final String urlPrefix;

  public Bot(final String id, final Point xy, final String urlPrefix) {
    this.score = 0;
    this.id = id;
    this.urlPrefix = urlPrefix;
    this.point = new Point(xy);
    for (Integer i = 2; i < 98; i+=5) {
      xIndices.add(i);
      yIndices.add(i);
    }
    for (Integer i = 0; i < 98; i+=5) {
      yIndices.add(97 - i);
    }

  }

  public void mineSector(final Point xy) {
    travelTo(xy);
    final List<Point> pointList = scan();
    for (Point point : pointList) {
      travelTo(point);
      if (claim()) {
        int resource = mine();
        while (resource > 0) {
          resource = mine();
        }
      }
    }
    //tally the score to track progress
    this.score = getScore();
  }

  public void travelTo(final Point xy) {
    while (!point.equals(xy)) {
      point = move(nextPoint(xy));
    }
  }

  public Point move(final Point xy) {
    customHttpClient.post(urlPrefix + "/move", )
  }

  private Point nextPoint(final Point xy) {
    Point nextPoint = new Point();
    if (xy.x > point.x) {
      nextPoint.x = point.x + 1;
    }
    if (xy.x < point.x) {
      nextPoint.x = point.x - 1;
    }
    if (xy.y > point.y) {
      nextPoint.y = point.y + 1;
    }
    if (xy.y < point.y) {
      nextPoint.y = point.y - 1;
    }
    return nextPoint;
  }

  public List<Point> scan() {
    return null;
  }

  public Boolean claim() {

  }

  public Integer mine() {

  }

  public Integer getScore() {

  }

  Response makeRequst(String action, JSONObject jsonObject) {
    final Response response = new Response();
    try {
      final String responseStr =
          customHttpClient.post(urlPrefix + "/" + action, jsonObject.toString());

      final JSONObject topLayer = new JSONObject(responseStr);
      topLayer.get("Status");

    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }
}
