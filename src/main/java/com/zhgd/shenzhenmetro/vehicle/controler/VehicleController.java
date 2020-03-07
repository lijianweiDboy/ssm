package com.zhgd.shenzhenmetro.vehicle.controler;

import cn.hutool.core.math.MathUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.zhgd.shenzhenmetro.vehicle.entity.Vehicle;
import com.zhgd.shenzhenmetro.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.sql.Timestamp;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ResponseBody
    @RequestMapping(value = "/pushVehicleData",method = RequestMethod.POST)
    public String pushVehicleData(@RequestBody String json){
        Vehicle vehicle = new Vehicle();
        vehicle.setDatetime(new Timestamp(System.currentTimeMillis()));
        vehicle.setHardwareId(RandomUtil.randomNumbers(8));
        vehicle.setVehicleNo(RandomUtil.randomString(8));
        vehicleService.save(vehicle);
        System.out.println(json);
        return "123";
    }

    @ResponseBody
    @RequestMapping(value = "/getVehicleData",method = RequestMethod.GET)
    public String getVehicleData(){
        System.out.println(JSONUtil.parseArray(vehicleService.selectVehicles()).toString());
        return JSONUtil.parseArray(vehicleService.selectVehicle()).toString();
    }
    //1.数据没有组织好,需要在本地数据库组织数据
    //2.需要选择数据发送方式
    //   1. 推- 调用队列接口,实时推送车辆数据,旧数据怎么办,网络终端怎么办?可能需要写定时器发送数据
    //   2. 拉- 通过适配器采集,但是没有外网地址
    //   3.只能够采用方案1 -推
    // 推:1.增加数据库字段,标识版本号,它作为id,定时推送id大于某值的数据,或者用时间作为标识,需要持久化数据到硬盘
    //    2. 如果没有历史数据都是实时数据,那么只能推送实时数据

}
