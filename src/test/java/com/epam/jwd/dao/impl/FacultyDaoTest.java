package com.epam.jwd.dao.impl;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.entity.EnumFaculty;
import com.epam.jwd.entity.Faculty;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FacultyDaoTest {

    @Test
    public void testGetInstance() {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().initProperties();
        FacultyDao facultyDao = FacultyDao.getInstance();
        Assert.assertEquals(facultyDao, FacultyDao.getInstance());
    }

    @Test
    public void testSelectAll() {
        AppContext.getInstance().init();

        List<Faculty> testList = new ArrayList<>();
        testList.add(new Faculty(EnumFaculty.FCSN, 3));
        testList.add(new Faculty(EnumFaculty.FITC, 3));
        testList.add(new Faculty(EnumFaculty.FCAD, 2));
        testList.add(new Faculty(EnumFaculty.FRE, 2));

        //Assert.assertEquals(testList, FacultyDao.getInstance().selectAll());

    }

    @Test
    public void testSelectById() {
        AppContext.getInstance().init();

        //Assert.assertEquals(new Faculty(EnumFaculty.FCSN, 3), FacultyDao.getInstance().selectById(1));
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testCountId() {
    }
}