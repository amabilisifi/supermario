package project.collisionManager;

import java.awt.*;
import java.util.ArrayList;

public class BoundarySensitivePolygon extends Polygon {
    ArrayList<Polygon> boundaryPoints = new ArrayList<>();
    Point pivot; // pivot can be anything ,but we set the middle of polygon ,so we should find that
    Polygon superPolygon;
    public BoundarySensitivePolygon(Polygon superPolygon){
        this.superPolygon = superPolygon;
    }
    public void setPivot(){
        int xAverage = 0;
        int yAverage = 0;
        for (int i = 0; i < npoints; i++) {
            xAverage += xpoints[i];
            yAverage += ypoints[i];
        }
        xAverage /= npoints;
        yAverage /= npoints;
        pivot = new Point(xAverage,yAverage);
    }
}
