package com.nextgamezada.exception;

import com.nextgamezada.gamesInPool.GamesInPoolController;
import com.nextgamezada.pools.PoolController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.RequestDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice(basePackageClasses = {PoolController.class, GamesInPoolController.class})//TODO ajustar packages para agrupar todas controllers
public class NextGamezadaControllerAdvice extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(PoolNotFoundException.class)
    public ResponseEntity<?> handlePoolNotFoundException(HttpServletRequest request, Throwable ex) {

        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new NextGamezadaErrorBody(ex.getMessage(), status.value()), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return (Objects.nonNull(code)) ? HttpStatus.resolve(code) : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
