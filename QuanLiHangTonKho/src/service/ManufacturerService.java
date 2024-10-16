package service;

import model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> getAllManufacturer();

    Manufacturer getManufacturerById(int id);

    boolean insertManufacturer(Manufacturer manufacturer);

    boolean updateManufacturer(Manufacturer manufacturer);

    boolean deleteManufacturer(int id);
}
