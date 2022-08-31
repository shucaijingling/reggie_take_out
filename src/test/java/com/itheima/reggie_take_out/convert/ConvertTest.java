package com.itheima.reggie_take_out.convert;

import com.itheima.reggie_take_out.dto.DishDto;
import com.itheima.reggie_take_out.entity.Dish;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class ConvertTest {
    
    @Resource
    TestUserConvert testUserConvert;

    @Autowired
    DishDtoConvert dishDtoConvert;

    @Test
    void test(){

//        Dish dish = new Dish();
//        dish.setPrice(BigDecimal.valueOf(123.123123));
//        dish.setCreateTime(LocalDateTime.now());
//        DishDto dishDto = DishDtoConvert.INSTANCE.dto2vo(dish);
//
//        System.out.println(dishDto);

//        TestUserVO testUserVO = new TestUserVO();
//        testUserVO.setId("1");
//        testUserVO.setUserName("name");
//        testUserVO.setTime(new Date());
//
//        TestStudent testStudent = new TestStudent();
//        testStudent.setStudentId("StudentId:22");
//        testStudent.setStudentName("Student:sss");
//        testUserVO.setTestStudent(testStudent);
////        TestUserDTO userDTO = TestUserConvert.INSTANCE.vo2Dto(testUserVO);
//        TestUserDTO userDTO = testUserConvert.vo2Dto(testUserVO);
//        log.info("converted:{}",userDTO);
        Dish dish = new Dish();
        dish.setName("!23213123");
        DishDto dishDto = dishDtoConvert.dish2dto(dish);
        System.out.println(dishDto);
    }
}
