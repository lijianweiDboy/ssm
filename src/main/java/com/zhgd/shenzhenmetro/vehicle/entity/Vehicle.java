package com.zhgd.shenzhenmetro.vehicle.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class Vehicle {
    private String hardwareId;
    private String vehicleNo;
    private String image;
    private String thumbnail;
    private int plateType;
    private String plateColor;
    private String panorama;
    private int liftType;
    private int direction;
    private Timestamp datetime;
    private Boolean isRemoved = false;
}
