/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import database.ConnectDB;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author asjad
 */
public class LoginPage extends javax.swing.JFrame {

    ConnectDB cn = new ConnectDB();
    Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    // Constructor method
    public LoginPage() throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Login Page");
        LoadComboBoxQuyen();

    }

    public void LoadComboBoxQuyen() throws SQLException {
        conn = cn.getConnection();
        String sql = " SELECT * FROM dbo.Quyen";
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        //Tao 1 DefaultComboBoxModel
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) comboboxQuyen.getModel();
        cbbModel.removeAllElements();

        try {
            //Doc danh sach de do vao ComboBox
            while (rs.next()) {
                int MaQuyen = rs.getInt("MaQuyen");
                String TenQuyen = rs.getString("TenQuyen");

                MyComboBox mycbb = new MyComboBox(MaQuyen, TenQuyen);
                //Them mycbb va Combobox 
                cbbModel.addElement(mycbb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userText = new javax.swing.JTextField();
        passText = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        comboboxQuyen = new javax.swing.JComboBox<>();
        loginButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(102, 102, 102));
        setBounds(new java.awt.Rectangle(500, 100, 0, 0));
        setName("loginFrame"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Password:");

        userText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextActionPerformed(evt);
            }
        });

        passText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passTextActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Poor Richard", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("STORE  INVENTORY");

        comboboxQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxQuyenActionPerformed(evt);
            }
        });

        loginButton.setText("LOGIN");
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        clearButton.setText("CLEAR");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userText))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passText))
                    .addComponent(comboboxQuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userText, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(18, 18, 18)
                .addComponent(comboboxQuyen, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        conn = cn.getConnection();

        String username = userText.getText();
        String password = passText.getText();
        String quyen = comboboxQuyen.getSelectedItem().toString();
        int MaQuyen;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxQuyen.getSelectedItem();
        MaQuyen = LoaiDuocChon.MaInt();

        StringBuffer sb = new StringBuffer();
        if (username.equals("") || password.equals("")) {
            sb.append("Tài khoản và mật khẩu không được để trống\n");
        }
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(this, sb.toString());
            return;
        }
        try {
            String sql = "SELECT * FROM Users WHERE TaiKhoan=? and MatKhau=? and MaQuyen=?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);
            pst.setInt(3, MaQuyen);

            rs = pst.executeQuery();

            if (rs.next()) {
                int userID = rs.getInt("ID");
                this.hide();
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                new Dashboard(username, quyen).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu hoặc quyền truy cập bị sai  ", "Message", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException | SQLException e) {
        }

    }//GEN-LAST:event_loginButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        userText.setText("");
        passText.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void passTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passTextActionPerformed

    }//GEN-LAST:event_passTextActionPerformed

    private void comboboxQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxQuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboboxQuyenActionPerformed

    private void userTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTextActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LoginPage().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox<String> comboboxQuyen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passText;
    private javax.swing.JTextField userText;
    // End of variables declaration//GEN-END:variables
}
