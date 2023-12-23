/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import database.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ASUS
 */
public class NhaPhanPhoiPage extends javax.swing.JInternalFrame {

    final String header[] = {"Mã nhà phân phối", "Tên nhà phân phối", "Địa chỉ", "SDT", "Email"};
    final DefaultTableModel tb = new DefaultTableModel(header, 0);
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectDB cn = new ConnectDB();
    Connection conn;

    public NhaPhanPhoiPage() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadNhaCungCap();
        txtMaNhaPhanPhoi.setVisible(false);
    }

    public void loadNhaCungCap() {
        try {
            conn = cn.getConnection();
            int number;
            Vector row;
            String sql = "SELECT MaNhaPhanPhoi,TenNhaPhanPhoi,DiaChi,SDT,Email FROM dbo.NhaPhanPhoi";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            tb.setRowCount(0);
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.add(rs.getString("MaNhaPhanPhoi").trim());
                    row.add(rs.getString("TenNhaPhanPhoi").trim());
                    row.add(rs.getString("DiaChi").trim());
                    row.add(rs.getString("SDT").trim());
                    row.add(rs.getString("Email").trim());
                }
                tb.addRow(row);
                tableNhaPhanPhoi.setModel(tb);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
        tableNhaPhanPhoi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableNhaPhanPhoi.getSelectedRow() >= 0) {
                    txtMaNhaPhanPhoi.setText(tableNhaPhanPhoi.getValueAt(tableNhaPhanPhoi.getSelectedRow(), 0) + "");
                    txtTenNhaPhanPhoi.setText(tableNhaPhanPhoi.getValueAt(tableNhaPhanPhoi.getSelectedRow(), 1) + "");
                    txtDiaChi.setText(tableNhaPhanPhoi.getValueAt(tableNhaPhanPhoi.getSelectedRow(), 2) + "");
                    txtSDT.setText(tableNhaPhanPhoi.getValueAt(tableNhaPhanPhoi.getSelectedRow(), 3) + "");
                    txtEmail.setText(tableNhaPhanPhoi.getValueAt(tableNhaPhanPhoi.getSelectedRow(), 4) + "");
                }
            }

        });
    }

    private void xoatrang() {
        txtMaNhaPhanPhoi.setText("");
        txtTenNhaPhanPhoi.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        loadNhaCungCap();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNhaPhanPhoi = new javax.swing.JTextField();
        txtTenNhaPhanPhoi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        buttonThem = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        buttonCapNhat = new javax.swing.JButton();
        buttonQuayLai = new javax.swing.JButton();
        JscrollPane = new javax.swing.JScrollPane();
        tableNhaPhanPhoi = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(860, 610));

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 630));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Nhà phân phối");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(340, 267));

        jLabel3.setText("Tên nhà phân phối:");

        jLabel4.setText("Địa chỉ:");

        jLabel5.setText("SDT:");

        jLabel6.setText("Email:");

        txtMaNhaPhanPhoi.setEditable(false);
        txtMaNhaPhanPhoi.setEnabled(false);
        txtMaNhaPhanPhoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNhaPhanPhoiActionPerformed(evt);
            }
        });

        buttonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        buttonThem.setText("Thêm");
        buttonThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });

        buttonXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        buttonXoa.setText("Xoá");
        buttonXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaActionPerformed(evt);
            }
        });

        buttonCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        buttonCapNhat.setText("Cập nhật");
        buttonCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCapNhatActionPerformed(evt);
            }
        });

        buttonQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        buttonQuayLai.setText("Làm mới");
        buttonQuayLai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addComponent(txtTenNhaPhanPhoi))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(buttonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonQuayLai))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(53, 53, 53))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiaChi)
                                    .addComponent(txtSDT)
                                    .addComponent(txtEmail)))
                            .addComponent(buttonCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaNhaPhanPhoi, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNhaPhanPhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonXoa)
                    .addComponent(buttonQuayLai)
                    .addComponent(buttonThem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCapNhat)
                .addGap(24, 24, 24)
                .addComponent(txtMaNhaPhanPhoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JscrollPane.setPreferredSize(new java.awt.Dimension(300, 80));

        tableNhaPhanPhoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tableNhaPhanPhoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableNhaPhanPhoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhaPhanPhoiMouseClicked(evt);
            }
        });
        JscrollPane.setViewportView(tableNhaPhanPhoi);

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        jLabel8.setText("Tìm Kiếm:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JscrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(46, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JscrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, Short.MAX_VALUE))
                .addContainerGap(297, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed
        conn = cn.getConnection();
        try {
            if (txtTenNhaPhanPhoi.getText().equals("") || txtDiaChi.getText().equals("") || txtSDT.getText().equals("") || txtEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                StringBuffer sb = new StringBuffer();
                String sqlCheck = "SELECT MaNhaPhanPhoi FROM dbo.NhaPhanPhoi WHERE MaNhaPhanPhoi = '" + txtMaNhaPhanPhoi.getText() + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlCheck);
                if (rs.next()) {
                    sb.append("Nhà phân phối đã tồn tại!.Hãy làm mới và nhập lại");
                }
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    String sqlInsert = "INSERT INTO dbo.NhaPhanPhoi (TenNhaPhanPhoi,DiaChi,SDT,Email) VALUES (?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sqlInsert);
                        pst.setString(1, txtTenNhaPhanPhoi.getText());
                        pst.setString(2, txtDiaChi.getText());
                        pst.setString(3, txtSDT.getText());
                        pst.setString(4, txtEmail.getText());
                        int kq = pst.executeUpdate();
                        if (kq > 0) {
                            JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
                            xoatrang();
                        }
                        st.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
                conn.close();
                rs.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_buttonThemActionPerformed

    private void buttonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaActionPerformed
        conn = cn.getConnection();
        try {
            String sqlDelete = "DELETE dbo.NhaPhanPhoi WHERE MaNhaPhanPhoi = '" + txtMaNhaPhanPhoi.getText() + "'";
            Statement st = conn.createStatement();
            int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn xoá chứ", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (check == JOptionPane.YES_OPTION) {
                st.execute(sqlDelete);
                xoatrang();
                JOptionPane.showMessageDialog(this, "Đã xoá thành công");
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_buttonXoaActionPerformed

    private void buttonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatActionPerformed
        conn = cn.getConnection();
        try {
            if (txtTenNhaPhanPhoi.getText().equals("") || txtDiaChi.getText().equals("") || txtSDT.getText().equals("") || txtEmail.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                Statement st = conn.createStatement();
                String sqlUpdate = "UPDATE dbo.NhaPhanPhoi SET TenNhaPhanPhoi ='" + txtTenNhaPhanPhoi.getText() + "',DiaChi ='" + txtDiaChi.getText() + "',SDT='" + txtSDT.getText() + "',Email ='" + txtEmail.getText() + "' WHERE MaNhaPhanPhoi = '" + txtMaNhaPhanPhoi.getText() + "'";
                try {
                    st = conn.createStatement();
                    int kq = st.executeUpdate(sqlUpdate);
                    if (kq > 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                        xoatrang();
                    }
                    st.close();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_buttonCapNhatActionPerformed

    private void buttonQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQuayLaiActionPerformed
        xoatrang();
    }//GEN-LAST:event_buttonQuayLaiActionPerformed

    private void tableNhaPhanPhoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhaPhanPhoiMouseClicked
        int x = tableNhaPhanPhoi.getSelectedRow();
        if (x >= 0) {
            txtMaNhaPhanPhoi.setText(tableNhaPhanPhoi.getValueAt(x, 0) + "");
            txtTenNhaPhanPhoi.setText(tableNhaPhanPhoi.getValueAt(x, 1) + "");
            txtDiaChi.setText(tableNhaPhanPhoi.getValueAt(x, 2) + "");
            txtSDT.setText(tableNhaPhanPhoi.getValueAt(x, 3) + "");
            txtEmail.setText(tableNhaPhanPhoi.getValueAt(x, 4) + "");
            txtMaNhaPhanPhoi.setEnabled(false);
        }
    }//GEN-LAST:event_tableNhaPhanPhoiMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DefaultTableModel ob = (DefaultTableModel) tableNhaPhanPhoi.getModel();
        TableRowSorter<DefaultTableModel> row = new TableRowSorter<>(ob);
        tableNhaPhanPhoi.setRowSorter(row);
        row.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtMaNhaPhanPhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNhaPhanPhoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNhaPhanPhoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JscrollPane;
    private javax.swing.JButton buttonCapNhat;
    private javax.swing.JButton buttonQuayLai;
    private javax.swing.JButton buttonThem;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableNhaPhanPhoi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNhaPhanPhoi;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNhaPhanPhoi;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
