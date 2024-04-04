package com.chomik.chomikdelivery.service.dto;


public class UserAddressDto {
    private String id;
    private String userId;
    private String country;
    private String city;
    private String street;
    private String house;
    private Integer floor;
    private String flat;
    private String extraInfo;

    public UserAddressDto() {
    }

    public UserAddressDto(String userId, String country, String city, String street, String house, Integer floor, String flat, String extraInfo) {
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
        this.flat = flat;
        this.extraInfo = extraInfo;
    }

    public UserAddressDto(String id, String userId, String country, String city, String street, String house, Integer floor, String flat, String extraInfo) {
        this.id = id;
        this.userId = userId;
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
        this.flat = flat;
        this.extraInfo = extraInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
