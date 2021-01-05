package com.epam.jwd.entity;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class FacultyTest {

    Faculty faculty = new Faculty(EnumFaculty.FCAD, 3);

    @Test
    public void testTestToString() {
        Assert.assertEquals(faculty.toString(), "Faculty(type=FCAD, countPlaces=3)");
    }

    @Test
    public void testSetType() {
        faculty.setType(EnumFaculty.FCSN);
        Assert.assertEquals(faculty.getType(), EnumFaculty.FCSN);
    }

    @Test
    public void testSetCountPlaces() {
        faculty.setCountPlaces(5);
        Assert.assertEquals(faculty.getCountPlaces(), 5);
    }
}