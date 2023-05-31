package project.collisionAriyan;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static project.collisionAriyan.GeometricToolbox.isInBound;

public class ReyDetection {
    public static ArrayList<Point> getVertexModel(BufferedImage image, int n, Point pivot) {
        Polygon bound = new Polygon();
        bound.addPoint(1, 1);
        bound.addPoint(image.getWidth() - 1, 1);
        bound.addPoint(image.getWidth() - 1, image.getHeight() - 1);
        bound.addPoint(image.getHeight() - 1, 1);
        return getVertexModel(image, n, pivot, bound);
    }

    public static ArrayList<Point> getVertexModel(BufferedImage image, int n, Point pivot, Polygon bound) {
        ArrayList<Double> angles = new ArrayList<>();
        double dTheta = 180.0 / n;
        double currentAngle = 0;
        for (int i = 0; i < n; i++) {
            angles.add(currentAngle);
            currentAngle += dTheta;
        }
        ArrayList<Point> points = new ArrayList<>();    // start and ends of lines
        for (double angle : angles) {
            Point[] heads = getRayHeadPoints(image, angle, pivot, bound);
            if (heads[0] != null) points.add(heads[0]);
            if (heads[1] != null) points.add(heads[1]);
        }

        double deltaX = (double) image.getWidth() / n;
        double x = 0;
        for (int i = 0; i < n; i++) {
            if (image.getWidth() - x <= 0.5) {
                x--;
            }
            Point newPivot = new Point((int) Math.round(x), 0);
            Point[] heads = getRayHeadPoints(image, 90, newPivot, bound);
            if (heads[0] != null) points.add(heads[0]);
            if (heads[1] != null) points.add(heads[1]);
            x += deltaX;
        }

        double deltaY = (double) image.getWidth() / n;
        double y = 0;
        for (int i = 0; i < n; i++) {
            if (image.getHeight() - y <= 0.5) {
                y--;
            }
            Point newPivot = new Point(0, (int) Math.round(y));
            Point[] heads = getRayHeadPoints(image, 0, newPivot, bound);
            if (heads[0] != null) points.add(heads[0]);
            if (heads[1] != null) points.add(heads[1]);
            x += deltaX;
        }
        return points;
    }

    public static Point[] getRayHeadPoints(BufferedImage image, double angle, Point pivot, Polygon bound) {
        Point maxPoint = null;
        Point minPoint = null;

        if (angle != 90) {
            double m = Math.tan(Math.toRadians(angle));
            //kjsgddh
            for (int x = (int) bound.getBounds().getMinX(); x < bound.getBounds().getMaxX(); x++) {
                int deltaX = x - pivot.x;
                int deltaY = (int) Math.floor(m * deltaX);

                Point target = new Point(x, pivot.y - deltaY);
                if (isInBound(target, bound) && !isTransparent(image, target) && !isSurrounded(image, target, bound)) {
                    maxPoint = target;
                    if (minPoint == null) {
                        minPoint = target;
                    }
                }
            }
        } else {
            for (int y = (int) bound.getBounds().getMinY(); y < bound.getBounds().getMaxY(); y++) {
                Point target = new Point(pivot.x, y);
                if (isInBound(target, bound) && !isTransparent(image, target) && !isSurrounded(image, target, bound)) {
                    maxPoint = target;
                    if (minPoint == null) {
                        minPoint = target;
                    }
                }
            }
        }
        Point[] answer = new Point[2];
        if (minPoint != null) answer[0] = minPoint;
        if (maxPoint != null) answer[1] = maxPoint;
        return answer;
    }

    public static boolean isTransparent(BufferedImage image, Point point) {
        return (image.getRGB(point.x, point.y) >> 24) == 0x00;
    }


    public static boolean isSurrounded(BufferedImage image, Point point, Polygon bound) {
        Point left = new Point(point.x - 1, point.y);
        Point up = new Point(point.x, point.y + 1);
        Point right = new Point(point.x + 1, point.y);
        Point down = new Point(point.x, point.y - 1);
        boolean isValid = isInBound(left, bound) && isInBound(up, bound) && isInBound(right, bound) && isInBound(down, bound);
        if (!isValid) {
            return false;
        }
        return isTransparent(image, left) && isTransparent(image, up) && isTransparent(image, right) && isTransparent(image, down);
    }
}
