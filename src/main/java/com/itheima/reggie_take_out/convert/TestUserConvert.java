package com.itheima.reggie_take_out.convert;

import com.itheima.reggie_take_out.dto.TestUserDTO;
import com.itheima.reggie_take_out.vo.TestUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface TestUserConvert {

//    static TestUserConvert INSTANCE = Mappers.getMapper(TestUserConvert.class);

    @Mappings({
            @Mapping(source = "userName", target = "name"),
            @Mapping(target = "time", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            //引用类型映射
            @Mapping(source = "testStudent", target = "testUser"),
            })
    TestUserDTO vo2Dto(TestUserVO testUserVO);


}
