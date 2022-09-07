package edu.eci.arsw.blueprints.test.filters.impl;

import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.filters.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RedundancyFilterTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testFilterPointsEmpty() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new RedundancyFilter();
        //defining blueprints
        Point[] pts=new Point[]{new Point(10, 10),new Point(10, 10), new Point(10, 10), new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        ibpp.saveBlueprint(bp);
        //Filtering
        bp = bpf.filterPoints(bp);
        List<Point> correctAnswer= new ArrayList<>();
        //testing results
        assertNotNull("Loading a points stored blueprint returned not null.", bp.getPoints());
        assertEquals("Points filtered by Redundancy", bp.getPoints().toString(), correctAnswer.toString());
    }
    @Test
    public void testFilterPointsNull() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new RedundancyFilter();
        //defining blueprints
        Blueprint bp=new Blueprint("john", "thepaint");
        ibpp.saveBlueprint(bp);
        //Filtering
        bp = bpf.filterPoints(bp);
        List<Point> correctAnswer= new ArrayList<>();
        //testing results
        assertNotNull("Loading a points stored blueprint returned not null.", bp.getPoints());
        assertEquals("Points filtered by Redundancy", bp.getPoints().toString(), correctAnswer.toString());
    }
    @Test
    public void testFilterPoints() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new RedundancyFilter();
        //defining blueprints
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10), new Point(10, 10), new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        ibpp.saveBlueprint(bp);
        //Filtering
        bp = bpf.filterPoints(bp);
        List<Point> correctAnswer= new ArrayList<>();
        correctAnswer.add(new Point(0,0));
        //testing results
        assertNotNull("Loading a points stored blueprint returned not null.", bp.getPoints());
        assertEquals("Points filtered by Redundancy", bp.getPoints().toString(), correctAnswer.toString());

    }
    @Test
    public void testFilterPointsEdges() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new RedundancyFilter();
        //defining blueprints
        Point[] pts=new Point[]{new Point(0, 0),new Point(0, 0), new Point(0, 10),
                new Point(10, 10), new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        ibpp.saveBlueprint(bp);
        //Filtering
        bp = bpf.filterPoints(bp);
        List<Point> correctAnswer= new ArrayList<>();
        correctAnswer.add(new Point(0,10));
        //testing results
        assertNotNull("Loading a points stored blueprint returned not null.", bp.getPoints());
        assertEquals("Points filtered by Redundancy", bp.getPoints().toString(), correctAnswer.toString());

    }
    @Test
    public void testFilterPointsMiddle() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new RedundancyFilter();
        //defining blueprints
        Point[] pts=new Point[]{new Point(0, 0),new Point(80, 70), new Point(80, 70),
                new Point(60, 80), new Point(60, 10), new Point(60, 10), new Point(4, 5)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        ibpp.saveBlueprint(bp);
        //Filtering
        bp = bpf.filterPoints(bp);
        List<Point> correctAnswer= new ArrayList<>();
        correctAnswer.add(new Point(0,0));
        correctAnswer.add(new Point(60,80));
        correctAnswer.add(new Point(4,5));
        //testing results
        assertNotNull("Loading a points stored blueprint returned not null.", bp.getPoints());
        assertEquals("Points filtered by Redundancy", bp.getPoints().toString(), correctAnswer.toString());

    }
}