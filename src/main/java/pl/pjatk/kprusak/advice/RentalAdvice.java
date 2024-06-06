package pl.pjatk.kprusak.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.pjatk.kprusak.dto.ErrorResponse;
import pl.pjatk.kprusak.exception.BadGatewayException;
import pl.pjatk.kprusak.exception.BadRequestException;
import pl.pjatk.kprusak.exception.GatewayTimeoutException;
import pl.pjatk.kprusak.exception.MovieNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RentalAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMovieNotFoundException(MovieNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<ErrorResponse> handleBadGatewayException(BadGatewayException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_GATEWAY.value(), "Bad Gateway", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(502).body(errorResponse);
    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity<ErrorResponse> handleGatewayTimeoutException(GatewayTimeoutException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), HttpStatus.GATEWAY_TIMEOUT.value(), "Gateway Timeout", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(504).body(errorResponse);
    }
}
