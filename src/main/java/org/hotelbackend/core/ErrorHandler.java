package org.hotelbackend.core;


import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.hotelbackend.exceptions.AppObjectAlreadyExistsException;
import org.hotelbackend.exceptions.AppObjectNotFoundException;
import org.jboss.logging.Logger;
import java.sql.SQLException;
@Provider
public class ErrorHandler implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class);

    @Override
    public Response toResponse(Exception e) {

        //Error Logging
        LOGGER.error("An error occurred:", e);
        System.out.println(e.getStackTrace());


        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),500);
        if (e instanceof IllegalArgumentException) {
            errorResponse = new ErrorResponse(e.getMessage(), 400); // Bad Request
        }
        else if (e instanceof AppObjectAlreadyExistsException) {
            errorResponse = new ErrorResponse(e.getMessage(), 409);
        }
        else if (e instanceof NullPointerException) {
            errorResponse = new ErrorResponse(e.getMessage(), 500); // Internal Server Error (usually a bug)
        }
       else if (e instanceof AppObjectNotFoundException) {
            errorResponse = new ErrorResponse(e.getMessage(), 404); // Not Found
        } else if (e instanceof SQLException) {
            errorResponse = new ErrorResponse(e.getMessage(), 500); // Internal Server Error
        } else if (e instanceof WebApplicationException) {
            WebApplicationException webException = (WebApplicationException) e;
            errorResponse = new ErrorResponse(webException.getMessage(), webException.getResponse().getStatus()); // Use status from WebApplicationException
        }
        // Return the response
        return Response.status(errorResponse.getStatus()).entity(errorResponse).build();
    }


    public static class ErrorResponse {
        private String message;
        private int status;

        public ErrorResponse(String message, int status) {
            this.message = message;
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }
    }
}
