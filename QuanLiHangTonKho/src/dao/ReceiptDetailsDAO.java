/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Position;

import java.util.List;

/**
 * @author ASUS
 */
public interface ReceiptDetailsDAO {

    List<Position> getAllPositions();

    Position getPositionById(int id);

    boolean insertPosition(Position position);

    boolean updatePosition(Position position);

    boolean deletePosition(int id);
}
