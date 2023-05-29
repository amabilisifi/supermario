package project.collisionManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static project.collisionManager.GeometricToolbox.pointsToPolygon;
import static project.collisionManager.ReyDetection.getVertexModel;
import static project.collisionManager.GeometricToolbox.sortClockwise;

public class GraphicalObjects {
    public static final int PRECISION = 20;
    BufferedImage image;
    Polygon boundingPolygon;
    Triangulation triangulation;
    Point center;

    public GraphicalObjects(BufferedImage image) {
        this.image = image;
        center  = new Point(image.getWidth()/2,image.getHeight()/2);
        ArrayList<Point> points = sortClockwise(getVertexModel(image,PRECISION,center),center);
        boundingPolygon  = pointsToPolygon(points);
        triangulation = new Triangulation(boundingPolygon);
        triangulation.triangulate();
    }
}
