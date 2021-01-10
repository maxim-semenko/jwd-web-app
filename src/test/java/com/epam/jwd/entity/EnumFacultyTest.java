package com.epam.jwd.entity;

import com.epam.jwd.exception.UnknownEnumFacultyException;
import org.junit.Assert;
import org.junit.Test;

public class EnumFacultyTest {

    @Test
    public void resolveByIdFCSN() throws UnknownEnumFacultyException {
        EnumFaculty faculty = EnumFaculty.resolveById(1);
        Assert.assertEquals(faculty, EnumFaculty.FCSN);
    }

    @Test
    public void resolveByIdEFITC() throws UnknownEnumFacultyException {
        EnumFaculty faculty = EnumFaculty.resolveById(2);
        Assert.assertEquals(faculty, EnumFaculty.FITC);
    }

    @Test
    public void resolveByIdFCAD() throws UnknownEnumFacultyException {
        EnumFaculty faculty = EnumFaculty.resolveById(3);
        Assert.assertEquals(faculty, EnumFaculty.FCAD);
    }

    @Test
    public void resolveByIdEFRE() throws UnknownEnumFacultyException {
        EnumFaculty faculty = EnumFaculty.resolveById(4);
        Assert.assertEquals(faculty, EnumFaculty.FRE);
    }

    @Test
    public void resolveByIdException() throws UnknownEnumFacultyException {
        boolean isError = false;
        try {
            EnumFaculty faculty = EnumFaculty.resolveById(5);
        } catch (UnknownEnumFacultyException e) {
            isError = true;
        }
        Assert.assertTrue(isError);
    }

    @Test
    public void getId() {
        EnumFaculty status = EnumFaculty.FCSN;
        Assert.assertEquals(status.getId(), 1);
    }

}