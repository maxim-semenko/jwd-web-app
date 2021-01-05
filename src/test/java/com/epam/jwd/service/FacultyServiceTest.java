package com.epam.jwd.service;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.dao.impl.FacultyDao;
import com.epam.jwd.entity.EnumFaculty;
import com.epam.jwd.entity.Faculty;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FacultyServiceTest {

    @Test
    public void testGetInstance() {
        FacultyService facultyService = FacultyService.getInstance();
        Assert.assertEquals(facultyService, FacultyService.getInstance());
    }

    @Test
    public void testSelectById() {
        AppContext.getInstance().init();
        Assert.assertEquals(FacultyService.getInstance().selectById(1), new Faculty(EnumFaculty.FCSN, 3));
    }

    @Test
    public void testSelectAll() {
        AppContext.getInstance().init();
        List<Faculty> testList = new ArrayList<>();
        testList.add(new Faculty(EnumFaculty.FCSN, 3));
        testList.add(new Faculty(EnumFaculty.FITC, 3));
        testList.add(new Faculty(EnumFaculty.FCAD, 2));
        testList.add(new Faculty(EnumFaculty.FRE, 2));

        Assert.assertEquals(testList, FacultyService.getInstance().selectAll());
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void testUpdate() {
    }
}