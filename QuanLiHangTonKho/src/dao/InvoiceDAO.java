/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Invoice;

import java.util.List;

/**
 * @author ASUS
 */
public interface InvoiceDAO {

    List<Invoice> getAllInvoices();

    Invoice getInvoiceById(int id);

    boolean insertInvoice(Invoice invoice);

    boolean updateInvoice(Invoice invoice);

    boolean deleteInvoice(int id);
}
