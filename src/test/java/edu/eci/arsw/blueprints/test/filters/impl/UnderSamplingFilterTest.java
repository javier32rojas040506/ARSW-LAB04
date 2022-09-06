package edu.eci.arsw.blueprints.test.filters.impl;

import edu.eci.arsw.blueprints.filters.Filter;
import edu.eci.arsw.blueprints.filters.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.filters.impl.UnderSamplingFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UnderSamplingFilterTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }
    @Test
    public void testFilterPoints() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new UnderSamplingFilter();
        //defining blueprints
        Point[] pts=new Point[]{new Point(10, 10),new Point(20, 20), new Point(10, 10), new Point(40, 40)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        ibpp.saveBlueprint(bp);
        //Filtering
        bp = bpf.filterPoints(bp);
        List<Point> correctAnswer= new ArrayList<>();
        correctAnswer.add(new Point(20, 20));
        correctAnswer.add(new Point(40, 40));
        //testing results
        assertNotNull("Loading a points stored blueprint returned not null.", bp.getPoints());
        assertEquals("Points filtered by Redundancy", bp.getPoints().toString(), correctAnswer.toString());
    }
    @Test
    public void testFilterPointsEmpty() throws BlueprintPersistenceException {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        Filter bpf = new UnderSamplingFilter();
        //defining blueprints
        Point[] pts=new Point[]{};
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
        Filter bpf = new UnderSamplingFilter();
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
}