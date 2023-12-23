/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author ASUS
 */
public class MyComboBox {
    Object value; //Chứa mã loại
    Object text; //Chứa tên loại

    public MyComboBox(Object value, Object text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public String toString() {
        return text.toString();
    }
    
    //Ham lay value kieu int
    public int MaInt(){
        return Integer.parseInt(value.toString());
    }
    //Ham lay value kieu String
    public String MaString(){
        return value.toString();
    }
    
}
