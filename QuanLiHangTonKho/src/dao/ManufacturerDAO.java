package dao;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import model.Manufacturer;

import java.util.List;

/**
 * @author ASUS
 */
public interface ManufacturerDAO {

    List<Manufacturer> getAllManufacturers();

    Manufacturer getManufacturerById(int id);

    boolean insertManufacturer(Manufacturer Manufacturer);

    boolean updateManufacturer(Manufacturer Manufacturer);

    boolean deleteManufacturer(int id);
}
