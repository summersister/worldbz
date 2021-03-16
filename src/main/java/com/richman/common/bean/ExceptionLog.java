package com.richman.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionLog {

    private String param;

    private String url;

    private String message;

    private String cause;

    private String stack;

    private Long timestamp;

}
