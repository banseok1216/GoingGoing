package com.example.utils.response;

public record HttpResponse<T>(int code, String message, T data) {
  public static <T> HttpResponse<T> success(T data) {
    return new HttpResponse<>(200, "Success", data);
  }
  public static <T> HttpResponse<T> successOnly() {
    return new HttpResponse<>(200, "Success",null);
  }
}
