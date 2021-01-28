package com.epam.jwd.service;

import com.epam.jwd.context.AppContext;
import com.epam.jwd.entity.EnumFaculty;
import com.epam.jwd.entity.Faculty;
import com.epam.jwd.exception.ValidatorException;
import com.epam.jwd.pool.ConnectionPool;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class FacultyServiceTest {

    static ConnectionPool connectionPool;
    static FacultyService facultyService;

    @BeforeClass
    public static void beforeClass() {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();
        facultyService = FacultyService.getInstance();
        connectionPool = ConnectionPool.getInstance();
    }

    @Test
    public void testGetInstance() {
        Assert.assertEquals(facultyService, FacultyService.getInstance());
    }

    @Test
    public void testSelectById() {
        Assert.assertNotEquals(FacultyService.getInstance().selectById(1), new Faculty(EnumFaculty.FCSN, 3));
    }

    @Test
    public void testSelectAll() {
        List<Faculty> testList = new ArrayList<>();
        testList.add(new Faculty(EnumFaculty.FCSN, 3));
        testList.add(new Faculty(EnumFaculty.FITC, 3));
        testList.add(new Faculty(EnumFaculty.FCAD, 2));
        testList.add(new Faculty(EnumFaculty.FRE, 2));

        Assert.assertNotEquals(testList, FacultyService.getInstance().selectAll());
    }

    @Test
    public void testInsert() throws ValidatorException {
        Faculty faculty = new Faculty(EnumFaculty.FCSN, 3);
        facultyService.insert(faculty);
        Assert.assertNotNull(facultyService.selectById(1));
    }

    @Test
    public void testInsertThatReturnValidatorException() {
        boolean isError = false;
        Faculty faculty = new Faculty(EnumFaculty.FCSN, -2);
        try {
            facultyService.insert(faculty);
        } catch (ValidatorException e) {
            isError = true;
            e.printStackTrace();
        }
        assertTrue(isError);
    }

    @Test
    public void testUpdate() throws ValidatorException {
        Faculty faculty = new Faculty(EnumFaculty.FCSN, 3);
        facultyService.update(faculty);
        Assert.assertEquals(faculty.getCountPlaces(), facultyService.selectById(1).getCountPlaces());
    }

    @Test
    public void testUpdateThatReturnValidatorException() {
        boolean isError = false;
        Faculty faculty = new Faculty(EnumFaculty.FCSN, -20);
        try {
            facultyService.update(faculty);
        } catch (ValidatorException e) {
            isError = true;
            e.printStackTrace();
        }
        assertTrue(isError);
    }


}