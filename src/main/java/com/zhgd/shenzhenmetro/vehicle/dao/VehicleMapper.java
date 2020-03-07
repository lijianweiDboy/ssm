package com.zhgd.shenzhenmetro.vehicle.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhgd.shenzhenmetro.vehicle.entity.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VehicleMapper extends BaseMapper<Vehicle> {
    @Select("select * from vehicle")
    @Results({
            @Result(property = "vehicleNo", column = "vehicle_no"),
            @Result(property = "isRemoved", column = "is_removed")
    })
    List<Vehicle> selectVehicle();
    List<Vehicle> selectVehicles();
}
