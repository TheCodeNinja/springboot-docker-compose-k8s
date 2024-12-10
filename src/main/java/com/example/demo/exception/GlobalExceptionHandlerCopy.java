package com.example.demo.exception;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestControllerAdvice
//@Slf4j
public class GlobalExceptionHandlerCopy extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                LocalDateTime.now(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Internal Server Error",
//                ex.getMessage(),
//                ((ServletWebRequest) request).getRequest().getRequestURI()
//        );
//        log.error("Unexpected error occurred", ex);
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                LocalDateTime.now(),
//                HttpStatus.NOT_FOUND.value(),
//                "User Not Found",
//                ex.getMessage(),
//                ((ServletWebRequest) request).getRequest().getRequestURI()
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse(
//                LocalDateTime.now(),
//                HttpStatus.UNAUTHORIZED.value(),
//                "Authentication Failed",
//                "Invalid username or password",
//                ((ServletWebRequest) request).getRequest().getRequestURI()
//        );
//        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//    }
}