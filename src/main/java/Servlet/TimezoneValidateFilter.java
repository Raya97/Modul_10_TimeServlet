package Servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.TimeZone;

@WebFilter("/time") // Specifies that this filter will be applied to the /time URL
public class TimezoneValidateFilter implements Filter {

    // The init method is called when the filter is initialized
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    // The doFilter method performs the main filtering work
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Cast general ServletRequest and ServletResponse to HTTP-specific types
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Get the "timezone" parameter from the request
        String timezone = req.getParameter("timezone");
        if (timezone != null && !timezone.isEmpty()) {
            // Get the TimeZone object by timezone name
            TimeZone tz = TimeZone.getTimeZone(timezone);
            // Check if the timezone is valid
            if (tz.getID().equals("GMT") && !timezone.equalsIgnoreCase("GMT")) {
                // Set the response status to 400 (Bad Request) and send an error message
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Invalid timezone");
                return;
            }
        }

        // Pass the request and response further along the filter chain
        chain.doFilter(request, response);
    }

    // The destroy method is called when the filter is removed
    @Override
    public void destroy() {}
}
