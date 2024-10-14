package service;

import model.Position;

import java.util.List;

public interface PositionService {

    List<Position> getAllPosition();

    Position getPositionById(int id);

    boolean insertPosition(Position position);

    boolean updatePosition(Position position);

    boolean deletePosition(int id);
}
