package project.collisionAriyan;

import earcut4j.Earcut;

import java.awt.*;
import java.util.ArrayList;

public class Triangulation {
  Polygon polygon;
  ArrayList<BoundarySensitivePolygon> triangles = new ArrayList<>();
  public Triangulation(Polygon polygon){
      this.polygon = polygon;
  }
  public void triangulate(){
      double[] coordinates = new double[2*polygon.npoints];
      for (int i = 0; i < polygon.npoints; i++) {
          // {ax,ay,bx,cy,...,zx,zy}
          coordinates[2*i] = polygon.xpoints[i];
          coordinates[2*i+1] = polygon.ypoints[i];
      }
      ArrayList<Integer> triangleIndices = (ArrayList<Integer>) Earcut.earcut(coordinates);
      for (int i = 0; i < triangleIndices.size(); i++) {
          BoundarySensitivePolygon triangle = new BoundarySensitivePolygon(polygon);
          triangle.addPoint(polygon.xpoints[triangleIndices.get(3*i)],polygon.ypoints[triangleIndices.get(3*i)]);
          triangle.addPoint(polygon.xpoints[triangleIndices.get(3*i+1)],polygon.ypoints[triangleIndices.get(3*i+1)]);
          triangle.addPoint(polygon.xpoints[triangleIndices.get(3*i+2)],polygon.ypoints[triangleIndices.get(3*i+2)]);

          triangles.add(triangle);
      }
  }
}
