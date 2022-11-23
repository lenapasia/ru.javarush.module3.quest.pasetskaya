package ru.javarush.quest.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;

class ServletUtilTest {

    @Test
    void reqRespForward() throws Exception {
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.doReturn(requestDispatcher).when(request).getRequestDispatcher(anyString());

        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        String jspName = "test-jsp";

        ServletUtil.reqRespForward(request, response, jspName);
        Mockito.verify(request).getRequestDispatcher(anyString());
    }

    @Test
    void getId() {
        final Long expected = 7L;

        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.doReturn(expected.toString()).when(request).getParameter("id");

        final Long actual = ServletUtil.getId(request);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "NotInteger"})
    void getIdReturnsNullWhenParameterIsNotIntegerString(String idValue) {
        getIdReturnsNull(idValue);
    }

    @Test
    void getIdReturnsNullWhenParameterIsNull() {
        getIdReturnsNull(null);
    }

    private void getIdReturnsNull(String idValue) {
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.doReturn(idValue).when(request).getParameter("id");

        final Long actual = ServletUtil.getId(request);
        assertNull(actual);
    }
}