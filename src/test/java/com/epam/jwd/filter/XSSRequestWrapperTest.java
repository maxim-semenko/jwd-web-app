package com.epam.jwd.filter;

import com.epam.jwd.context.AppContext;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;

public class XSSRequestWrapperTest {

    @Mock
    static HttpServletRequest req;


    @BeforeClass
    public static void beforeClass() {
        AppContext.setType(AppContext.Type.TEST);
        AppContext.getInstance().init();
        req = mock(HttpServletRequest.class);
    }

    @Test
    public void testStripXSS() {
        String input = "\"<script>\", \"<script>alert(‘XSS’)</script>\", \"<script>alert(document.cookie)</script>\"";
        String output = new XSSRequestWrapper(req).stripXSS(input);
        Assert.assertEquals(output, "\"\", \"\"");
    }

}