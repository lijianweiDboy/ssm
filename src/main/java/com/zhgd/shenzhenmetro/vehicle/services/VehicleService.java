package com.zhgd.shenzhenmetro.vehicle.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhgd.shenzhenmetro.vehicle.entity.Vehicle;

import java.util.List;

public interface VehicleService  extends IService<Vehicle> {
     List<Vehicle> selectVehicle();
     List<Vehicle> selectVehicles();
}
