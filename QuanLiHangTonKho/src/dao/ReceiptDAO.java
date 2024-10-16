/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Receipt;

import java.util.List;

/**
 * @author ASUS
 */
public interface ReceiptDAO {

    List<Receipt> getAllReceipts();

    Receipt getReceiptById(int id);

    boolean insertReceipt(Receipt receipt);

    boolean updateReceipt(Receipt receipt , int oldProductId, int oldQuantity);

    boolean deleteReceipt(int id);
}
