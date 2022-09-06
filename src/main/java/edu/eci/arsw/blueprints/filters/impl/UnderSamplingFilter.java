package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class UnderSamplingFilter implements Filter {
    @Override
    public Blueprint filterPoints(Blueprint bp) {
        List<Point> pts = bp.getPoints();
        Boolean delete = false;
        List<Point> ptsAnswer = new ArrayList<>();
        for (Point p: pts) {
            if(delete.equals(true)){
                ptsAnswer.add(p);
                delete = false;
            }
            else{
                delete = true;
            }
        }
        bp.setPoints(ptsAnswer);
        return bp;
    }
}
