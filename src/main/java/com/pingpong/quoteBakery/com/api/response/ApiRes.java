package com.pingpong.quoteBakery.com.api.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [공통] API Response 결과의 반환 값을 관리
 * 클라이언트에서 요청한 값에 대해 '응답' 반환 값의 형태를 구성
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiRes<T> {
  private int status;
  private T data;
  private String message;

  public static <T> ApiRes<T> createSuccess(T data) {
    return new ApiRes<>(HttpStatus.OK.value(), data, null);
  }

  public static ApiRes<?> createSuccessWithNoContent() {
    return new ApiRes<>(HttpStatus.OK.value(), null, null);
  }

  // Hibernate Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환
  public static ApiRes<?> createFail(BindingResult bindingResult) {
    Map<String, String> errors = new HashMap<>();

    List<ObjectError> allErrors = bindingResult.getAllErrors();
    for (ObjectError error : allErrors) {
      if (error instanceof FieldError) {
        errors.put(((FieldError) error).getField(), error.getDefaultMessage());
      } else {
        errors.put( error.getObjectName(), error.getDefaultMessage());
      }
    }
    return new ApiRes<>(HttpStatus.BAD_REQUEST.value(), errors, null);
  }

  // 예외 발생으로 API 호출 실패시 반환
  public static ApiRes<?> createError(int status, String message) {
    return new ApiRes<>(status, null, message);
  }

  private ApiRes(int status, T data, String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }
}