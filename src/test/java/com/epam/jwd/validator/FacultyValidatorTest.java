package com.epam.jwd.validator;

import com.epam.jwd.entity.EnumFaculty;
import com.epam.jwd.entity.Faculty;
import org.junit.Assert;
import org.junit.Test;

public class FacultyValidatorTest {

    FacultyValidator facultyValidator = FacultyValidator.getInstance();


    @Test
    public void testGetInstance() {
        FacultyValidator facultyValidator = FacultyValidator.getInstance();
    }

    @Test
    public void testValidate() {
        Assert.assertTrue(facultyValidator.validate(new Faculty(EnumFaculty.FCSN, 15)));
    }
}