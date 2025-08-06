package com.lab.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private int code;
    private String msg;
    private T data;

    public ResultVO() { this(200, "success", null); }
    public ResultVO(T data) { this(200, "success", data); }
    public ResultVO(int code, String msg, T data) {
        this.code = code; this.msg = msg; this.data = data;
    }

    public static <T> ResultVO<T> success() { return new ResultVO<>(); }
    public static <T> ResultVO<T> success(T data) { return new ResultVO<>(data); }
    public static <T> ResultVO<T> fail(String msg) { return new ResultVO<>(400, msg, null); }
}