package com.epam.jwd.service;

import com.epam.jwd.entity.EnumFaculty;
import com.epam.jwd.entity.Faculty;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.listener.InitListener;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletContextEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class FacultyServiceTest {

    @Test
    public void testGetInstance() {
        FacultyService facultyService = FacultyService.getInstance();
        Assert.assertEquals(facultyService, FacultyService.getInstance());
    }

    @Test
    public void testSelectById() {
        //Assert.assertNotEquals(FacultyService.getInstance().selectById(1), new Faculty(EnumFaculty.FCSN, 99));
    }

    @Test
    public void testSelectAll() {
        List<Faculty> testList = new ArrayList<>();
        testList.add(new Faculty(EnumFaculty.FCSN, 99));
        testList.add(new Faculty(EnumFaculty.FITC, 3));
        testList.add(new Faculty(EnumFaculty.FCAD, 2));
        testList.add(new Faculty(EnumFaculty.FRE, 2));

        Assert.assertNotEquals(testList, FacultyService.getInstance().selectAll());
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void testUpdate() {
    }
}