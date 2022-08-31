package com.itheima.reggie_take_out.vo;

import lombok.Data;

import java.util.Date;

@Data
public class TestUserVO {

    private String id;

    private String userName;

    private Date time;

    private TestStudent testStudent;
}
