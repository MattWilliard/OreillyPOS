package com.oreillys.invoice.pos.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AuthenticationFilterTest {

    private AuthenticationFilter authenticationFilter;
    private Map<String, String> headers;

    private String passKey = "aBc129";

    @BeforeEach
    void setUp(){
        authenticationFilter = new AuthenticationFilter();
        ReflectionTestUtils.setField(authenticationFilter, "authenticate", true);
        ReflectionTestUtils.setField(authenticationFilter, "passKey", passKey);
    }

    @Test
    void filterShouldValidatePassKey() throws ServletException, IOException {
        headers = new HashMap<>();
        headers.put("api-key", passKey);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        FilterChain mockFilterChain = mock(FilterChain.class);

        doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable{
                Object[] args = invocation.getArguments();
                return headers.get((String) args[0]);
            }
        }).when(mockRequest).getHeader("api-key");

        when(mockRequest.getHeaderNames()).thenReturn(getMockHeaderNames());
        when(mockRequest.getHeaders("api-key")).thenReturn(getMockHeaderValues());
        when(mockRequest.getServletPath()).thenReturn("/api/v1/invoice");
        authenticationFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);
    }

    @Test
    void filterShouldNOTValidatePassKey() throws ServletException, IOException {
        headers = new HashMap<>();
        headers.put("api-key", "wrong");

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        FilterChain mockFilterChain = mock(FilterChain.class);

        doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable{
                Object[] args = invocation.getArguments();
                return headers.get((String) args[0]);
            }
        }).when(mockRequest).getHeader("api-key");

        when(mockRequest.getHeaderNames()).thenReturn(getMockHeaderNames());
        when(mockRequest.getHeaders("api-key")).thenReturn(getMockHeaderValues());
        when(mockRequest.getServletPath()).thenReturn("/api/v1/invoice");
        authenticationFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChain);
    }

    @Test
    void shouldNotFilterMethod() throws ServletException {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);

        when(mockRequest.getServletPath()).thenReturn("/api/v1/invoice");
        boolean shouldNotFilterShouldBeFalse = authenticationFilter.shouldNotFilter(mockRequest);
        assertFalse(shouldNotFilterShouldBeFalse);

        when(mockRequest.getServletPath()).thenReturn("/swagger-ui/index.html");
        boolean shouldNotFilterSwaggerShouldBeTrue = authenticationFilter.shouldNotFilter(mockRequest);
        assertTrue(shouldNotFilterSwaggerShouldBeTrue);

        when(mockRequest.getServletPath()).thenReturn("/h2-console");
        boolean shouldNotFilterH2ShouldBeTrue = authenticationFilter.shouldNotFilter(mockRequest);
        assertTrue(shouldNotFilterH2ShouldBeTrue);
    }

    private Enumeration<String> getMockHeaderNames(){
        ArrayList<String> headerKeyList = new ArrayList<>();
        headerKeyList.add("api-key");
        Enumeration<String> headerNames = Collections.enumeration(headerKeyList);
        return headerNames;
    }

    private Enumeration<String> getMockHeaderValues(){
        ArrayList<String> headerValueList = new ArrayList<>();
        headerValueList.add(passKey);
        Enumeration<String> headerValues = Collections.enumeration(headerValueList);
        return headerValues;
    }
}
