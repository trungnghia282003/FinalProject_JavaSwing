/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Quyen {

    private int MaQuyen;
    private String TenQuyen, ChuThich;

    public int getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(int MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getTenQuyen() {
        return TenQuyen;
    }

    public void setTenQuyen(String TenQuyen) {
        if (containsDigits(TenQuyen)) {
            throw new IllegalArgumentException("Tên quyền không được chứa số");
        }
        this.TenQuyen = TenQuyen;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setChuThich(String ChuThich) {
        if (containsDigits(ChuThich)) {
            throw new IllegalArgumentException("Chú thích không được chứa số");
        }
        this.ChuThich = ChuThich;
    }

    // Hàm kiểm tra chuỗi có chứa số hay không
    private boolean containsDigits(String str) {
        // Kiểm tra nếu chuỗi null
        if (str == null) {
            return false;
        }
        // Kiểm tra chuỗi có chứa ký tự số hay không
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
