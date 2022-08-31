package com.itheima.reggie_take_out.dto;

import com.itheima.reggie_take_out.vo.TestStudent;
import lombok.Data;

import java.util.Date;

@Data
public class TestUserDTO {

    private String id;

    private String name;

    private Date time;

    private TestStudent TestUser;
}
