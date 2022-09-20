package com.nmz.concretestatistics.Utils;

public enum State {
    Success(1,"添加成功"),
    Fail(2,"添加失败，请重试");

    private int number;
    private String state;

    State(int number, String state) {
        this.number = number;
        this.state = state;
    }
}
