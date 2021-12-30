package tresbits.springbootbackendapirest.common.errors;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class exceptions {

    private static final Logger logger = LoggerFactory.getLogger(exceptions.class);

    @ExceptionHandler(value = { DataAccessException.class })
    public ResponseEntity<?> handleDataAccessException(
            DataAccessException ex,
            WebRequest request,
            HttpServletRequest httpRequest) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Error al realizar al realizar una operación en la base de datos");
        response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause());
        response.put("ok", false);

        logger.error("Invalid DataAccessException: " + ex.getMessage());
        logger.error("Error en el endpoint: " + httpRequest.getRequestURI().toString());

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<?> handleException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("ok", false);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
