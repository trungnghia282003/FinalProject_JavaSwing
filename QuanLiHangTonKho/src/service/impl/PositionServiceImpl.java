package service.impl;

import dao.PositionDAO;
import dao.impl.PositionDAOImpl;
import model.Position;
import service.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {

    private PositionDAO positionDAO;

    public PositionServiceImpl() {
        this.positionDAO = new PositionDAOImpl();
    }

    @Override
    public List<Position> getAllPosition() {
        return positionDAO.getAllPositions();
    }

    @Override
    public Position getPositionById(int id) {
        return positionDAO.getPositionById(id);
    }

    @Override
    public boolean insertPosition(Position position) {
        return positionDAO.insertPosition(position);
    }

    @Override
    public boolean updatePosition(Position position) {
        return positionDAO.updatePosition(position);
    }

    @Override
    public boolean deletePosition(int id) {
        return positionDAO.deletePosition(id);
    }
}
