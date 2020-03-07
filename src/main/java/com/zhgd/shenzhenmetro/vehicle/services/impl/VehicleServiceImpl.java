package com.zhgd.shenzhenmetro.vehicle.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhgd.shenzhenmetro.vehicle.dao.VehicleMapper;
import com.zhgd.shenzhenmetro.vehicle.entity.Vehicle;
import com.zhgd.shenzhenmetro.vehicle.services.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service("vehicleService")
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper,Vehicle> implements VehicleService {

    @Override
    public boolean save(Vehicle entity) {
        return super.save(entity);
    }

    public List<Vehicle> selectVehicle(){
        return getBaseMapper().selectVehicle();
    }

    @Override
    public List<Vehicle> selectVehicles() {
        return getBaseMapper().selectVehicles();
    }
}
