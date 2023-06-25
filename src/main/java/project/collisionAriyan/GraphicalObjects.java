package project.collisionAriyan;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static project.collisionAriyan.GeometricToolbox.pointsToPolygon;
import static project.collisionAriyan.GeometricToolbox.sortClockwise;
import static project.collisionAriyan.ReyDetection.getVertexModel;

public class GraphicalObjects {
    public static final int PRECISION = 20;
    BufferedImage image;
    Polygon boundingPolygon;
    Triangulation triangulation;
    Point center;

    public GraphicalObjects(BufferedImage image) {
        this.image = image;
        center = new Point(image.getWidth() / 2, image.getHeight() / 2);
        System.out.println("maybe here");
        getVertexModel(image, PRECISION, center);
        System.out.println("ooo lala");
        ArrayList<Point> points = sortClockwise(getVertexModel(image, PRECISION, center), center);
        System.out.println("here");
        boundingPolygon = pointsToPolygon(points);
        System.out.println("there");
        triangulation = new Triangulation(boundingPolygon);
        System.out.println("and there");
        triangulation.triangulate();
        System.out.println("and at last here");
    }

    public Polygon getBoundingPolygon() {
        return boundingPolygon;
    }
}
