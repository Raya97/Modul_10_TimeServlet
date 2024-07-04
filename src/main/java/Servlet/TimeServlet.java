package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeServlet extends HttpServlet {
    // Override the doGet method to handle GET requests
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set the content type of the response to HTML with UTF-8 encoding
        resp.setContentType("text/html;charset=UTF-8");
        // Get the "timezone" parameter from the request
        String timezone = req.getParameter("timezone");
        // If the timezone parameter is null or empty, default to UTC; otherwise, use the provided timezone
        ZoneId zoneId = (timezone == null || timezone.isEmpty()) ? ZoneId.of("UTC") : ZoneId.of(timezone);

        // Get the current time in the specified timezone
        ZonedDateTime currentTime = ZonedDateTime.now(zoneId);
        // Format the current time using the specified pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String formattedTime = currentTime.format(formatter);

        // Get the writer object to send the response
        try (PrintWriter out = resp.getWriter()) {
            // Write the HTML response
            out.println("<html><body>");
            // Display the current time in a header element
            out.println("<h1>Поточний час: " + formattedTime + "</h1>");
            out.println("</body></html>");
        }
    }
}
