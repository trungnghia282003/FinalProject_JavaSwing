package service.impl;

import dao.ManufacturerDAO;
import dao.impl.ManufacturerDAOImpl;
import model.Manufacturer;
import service.ManufacturerService;

import java.util.List;

public class ManufacturerServiceImpl implements ManufacturerService {

    private ManufacturerDAO ManufacturerDAO;

    public ManufacturerServiceImpl() {
        this.ManufacturerDAO = new ManufacturerDAOImpl();
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return ManufacturerDAO.getAllManufacturers();
    }

    @Override
    public Manufacturer getManufacturerById(int id) {
        return ManufacturerDAO.getManufacturerById(id);
    }

    @Override
    public boolean insertManufacturer(Manufacturer Manufacturer) {
        return ManufacturerDAO.insertManufacturer(Manufacturer);
    }

    @Override
    public boolean updateManufacturer(Manufacturer Manufacturer) {
        return ManufacturerDAO.updateManufacturer(Manufacturer);
    }

    @Override
    public boolean deleteManufacturer(int id) {
        return ManufacturerDAO.deleteManufacturer(id);
    }
}
