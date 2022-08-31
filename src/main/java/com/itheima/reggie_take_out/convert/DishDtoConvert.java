package com.itheima.reggie_take_out.convert;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie_take_out.dto.DishDto;
import com.itheima.reggie_take_out.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface DishDtoConvert {
//    public static DishDtoConvert INSTANCE = Mappers.getMapper(DishDtoConvert.class);

    /*@Mappings({
            //dateFormat时间类型转换
            @Mapping(source = "createTime", target = "categoryName", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            //numberFormat数字类型转换："#.00"--》保留两位小数
            @Mapping(target = "copies", numberFormat = "#.00"),

            //ignore = true 表示该字段不映射
            //注意：如果映射双方中一方的某个字段没有，则不报错，该字段也不进行映射
            @Mapping(target = "flavors", ignore = true)
    })*/
    DishDto dish2dto(Dish dish);

    List<DishDto> dishList2dtoList(List<Dish> dishes);

    @Mapping(target = "records", ignore = true)
    Page<DishDto> toPage(Page<Dish> dishPage);
}
