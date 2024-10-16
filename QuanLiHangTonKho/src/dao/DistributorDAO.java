/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Distributor;

import java.util.List;

/**
 * @author ASUS
 */
public interface DistributorDAO {

    List<Distributor> getAllDistributors();

    Distributor getDistributorById(int id);

    boolean insertDistributor(Distributor distributor);

    boolean updateDistributor(Distributor distributor);

    boolean deleteDistributor(int id);
}
