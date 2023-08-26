package com.pingpong.quoteBakery.com.exception;

import com.pingpong.quoteBakery.com.api.response.ApiRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Controller 내에서 발생하는 Exception 대해서 Catch 하여 응답값(Response)을 보내주는 기능을 수행함.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * [Exception] API 호출 시 'PathVariable' 내에 데이터 값이 없는경우
     *
     * @param ex MissingPathVariableException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(MissingPathVariableException.class)
    protected ResponseEntity<ApiRes<?>> handleMissingPathVariableException(MissingPathVariableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRes.createError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    /**
     * [Exception] API 호출 시 'Header' 내에 데이터 값이 없는경우
     *
     * @param ex MissingRequestHeaderException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ApiRes<?>> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRes.createError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    /**
     * [Exception] 클라이언트에서 Body로 '객체' 데이터가 넘어오지 않았을 경우
     *
     * @param ex HttpMessageNotReadableException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiRes<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRes.createError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    /**
     * [Exception] 클라이언트에서 request로 '파라미터로' 데이터가 넘어오지 않았을 경우
     *
     * @param ex MissingServletRequestParameterException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ApiRes<?>> handleMissingRequestHeaderExceptionException(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRes.createError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    /**
     * [Exception] API 호출 시 '객체' 혹은 '파라미터' 데이터 값이 유효하지 않은 경우
     * `@Valid` annotation에 의해 발생하는 exception
     *
     * @param ex MethodArgumentNotValidException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiRes<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRes.createFail(ex.getBindingResult()));
    }

    /**
     * [Exception] API 호출 시 지원하지 않은 HTTP method 호출할 경우 발생
     *
     * @param ex MissingRequestHeaderException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ApiRes<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ApiRes.createError(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage()));
    }

    /**
     * [Exception] 잘못된 서버 요청일 경우 발생한 경우
     *
     * @param ex HttpClientErrorException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    protected ResponseEntity<ApiRes<?>> handleBadRequestException(HttpClientErrorException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRes.createError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    /**
     * [Exception] 잘못된 주소로 요청 한 경우
     *
     * @param ex NoHandlerFoundException
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ApiRes<?>> handleNoHandlerFoundExceptionException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiRes.createError(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }


    // =========  Business Exception ===============

    @ExceptionHandler(BusinessInvalidValueException.class)
    protected ResponseEntity<ApiRes<?>> handleBusinessInvalidValueException(BusinessInvalidValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiRes.createError(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    // ============= 최상위 Exception ===================

    /**
     * [Exception] 모든 Exception 경우 발생
     *
     * @param ex Exception
     * @return ResponseEntity<ApiResponse>
     */
    @ExceptionHandler(Exception.class)
    protected final ResponseEntity<ApiRes<?>> handleAllExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiRes.createError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }
}