package com.pingpong.iosapp.com.api.code;

import lombok.Getter;

/**
 * [공통 코드] API 통신에 대한 '에러 코드'를 Enum 형태로 관리를 한다.
 * Global Error CodeList : 전역으로 발생하는 에러코드를 관리한다.
 * business Error CodeList : 업무 페이지에서 발생하는 에러코드를 관리한다
 * Error Code Constructor : 에러코드를 직접적으로 사용하기 위한 생성자를 구성한다.
 *
 */
@Getter
public enum ErrorCode {

  /**
   * ******************************* Global Error CodeList ***************************************
   * HTTP Status Code
   * 400 : Bad Request
   * 401 : Unauthorized
   * 403 : Forbidden
   * 404 : Not Found
   * 500 : Internal Server Error
   * *********************************************************************************************
   */
  // 잘못된 서버 요청
  BAD_REQUEST_ERROR(400, "G000", "Bad Request Exception"),

  // @RequestHeader 데이터 미 존재
  MISSING_REQUEST_HEADER_ERROR(400, "G001", "Required request header is missing"),

  // @RequestBody 데이터 미 존재
  MISSING_REQUEST_BODY_ERROR(400, "G002", "Required request body is missing"),

  // Request Parameter 로 데이터가 전달되지 않을 경우
  MISSING_REQUEST_PARAMETER_ERROR(400, "G003", "Missing Servlet RequestParameter Exception"),

  // Path variable로 데이터가 전달되지 않을 경우
  MISSING_REQUEST_PATH_VAR_ERROR(400, "G003", "Missing Path Variable Exception"),

  // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
  NOT_VALID_ERROR(400, "G004", "Invalid request body value"),

  // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
  NOT_VALID_HEADER_ERROR(400, "G005", "Invalid request header value"),

  // 유효하지 않은 타입
  INVALID_TYPE_VALUE(400, "G006", "Invalid Value Type"),

  // 입력/출력 값이 유효하지 않음
  IO_ERROR(400, "G007", "I/O Exception"),

  // 권한이 없음
  FORBIDDEN_ERROR(403, "G008", "Forbidden Exception"),

  // 서버로 요청한 리소스가 존재하지 않음
  NOT_FOUND_ERROR(404, "G009", "Not Found Exception"),

  // NULL Point Exception 발생
  NULL_POINT_ERROR(404, "G010", "Null Point Exception"),

  //지원하지 않은 HTTP method 호츌
  NOT_ALLOWED_METHOD(405, "G011", "Wrong Http Method"),

  // 서버가 처리 할 방법을 모르는 경우 발생
  INTERNAL_SERVER_ERROR(500, "G999", "Internal Server Error Exception"),


  /**
   * ******************************* business Error CodeList ***************************************
   */
  // Transaction Insert Error
  INSERT_ERROR(200, "B001", "Insert Transaction Error Exception"),
  // Transaction Update Error
  UPDATE_ERROR(200, "B002", "Update Transaction Error Exception"),
  // Transaction Delete Error
  DELETE_ERROR(200, "B003", "Delete Transaction Error Exception"),

  BUISINESS_INVALID_VALUE(400, "B0010", "유효하지 않은 값.")

  ; // End

  /**
   * ******************************* Error Code Constructor ***************************************
   */
  // 에러 코드의 '코드 상태'을 반환한다.
  private final int status;

  // 에러 코드의 '코드간 구분 값'을 반환한다.
  private final String divisionCode;

  // 에러 코드의 '코드 메시지'을 반환한다.
  private final String message;

  // 생성자 구성
  ErrorCode(final int status, final String divisionCode, final String message) {
    this.status = status;
    this.divisionCode = divisionCode;
    this.message = message;
  }
}