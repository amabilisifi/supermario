package project.collisionAriyan;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Comparator;

public class GeometricToolbox {
    public static Polygon pointsToPolygon(ArrayList<Point> points) {
        Polygon polygon = new Polygon();
        for (Point point : points) {
            polygon.addPoint(point.x, point.y);
        }
        return polygon;
    }
    public static Comparator<Point> clockwiseComparator(Point point) {
        return ((o1, o2) -> {
            if (o1.x - point.x >= 0 && o2.x - point.x < 0) return 1;
            if (o1.x - point.x < 0 && o2.x - point.x >= 0) return -1;
            if (o1.x - point.x == 0 && o2.x - point.x == 0) return Integer.compare(o1.y, o2.y);
            return crossProduct(point, o1, o2);
        });
    }

    public static int crossProduct(Point o, Point A, Point B) {
        return (A.x - o.x) * (B.y - o.y) - (A.y - o.y) * (B.x - o.x);
    }
    public static ArrayList<Point> sortClockwise(ArrayList<Point> points,Point pivot){
        ArrayList<Point> answer = new ArrayList<>(points);
        answer.sort(clockwiseComparator(pivot));
        return answer;
    }
    public static boolean isInBound(Point point,Polygon bound){
       int count = 0;
        for (int i = 0; i < bound.npoints; i++) {
            int index1 = i%bound.npoints;
            int index2 = (i+1)%bound.npoints;
            if(Line2D.linesIntersect(0,0,point.x,point.y,bound.xpoints[index1],bound.ypoints[index2],bound.xpoints[index2],bound.ypoints[index1])){
                count++;
            }
        }
        return count%2==1;
    }
}