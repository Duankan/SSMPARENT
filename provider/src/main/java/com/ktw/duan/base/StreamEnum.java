package com.ktw.duan.base;

public enum StreamEnum {
    INPUTSTREAM("1", "input"),
    OUTPUTSTREAM("2", "output"),
    READER("3", "reader"),
    WRITER("4", "writer"),
    RCHANNEL("5","rchannel"),
    WRCHANNEL("6","wrchannel");

    private StreamEnum(String code,String name) {
    this.code=code;
    this.name=name;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
