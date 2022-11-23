package ru.javarush.quest.util;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServletUtilTest {

    @Test
    void reqRespForward() throws Exception {
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.doReturn(requestDispatcher).when(request).getRequestDispatcher(Mockito.anyString());

        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String jspName = "test-jsp";

        ServletUtil.reqRespForward(request, response, jspName);
        Mockito.verify(request).getRequestDispatcher(Mockito.anyString());
    }

    @Test
    void getId() {
        final Long expected = 7L;

        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.doReturn(expected.toString()).when(request).getParameter("id");

        final Long actual = ServletUtil.getId(request);
        assertEquals(expected, actual);
    }
}