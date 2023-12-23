package view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
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
public class ChiTietPhieuXuatPage extends javax.swing.JInternalFrame {

    final String header[] = {"Mã chi tiết hoá đơn ", "Mã hoá đơn", "Mã sản phẩm", "Số lượng", "Tổng tiền"};
    final DefaultTableModel tb = new DefaultTableModel(header, 0);
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectDB cn = new ConnectDB();
    Connection conn;

    public ChiTietPhieuXuatPage() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadChiTietHoaDon();
        txtMaCTHD.setVisible(false);
    }

    public void loadChiTietHoaDon() {
        try {
            conn = cn.getConnection();
            int number;
            Vector row;
            String sql = "SELECT MaCTHD,MaHoaDon,MaSanPham,SoLuong,TongTien FROM dbo.ChiTietHoaDon";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            tb.setRowCount(0);
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.add(rs.getString("MaCTHD").trim());
                    row.add(rs.getString("MaHoaDon").trim());
                    row.add(rs.getString("MaSanPham").trim());
                    row.add(rs.getString("SoLuong").trim());
                    row.add(rs.getString("TongTien").trim());
                }
                tb.addRow(row);
                tableChiTietHoaDon.setModel(tb);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
        tableChiTietHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableChiTietHoaDon.getSelectedRow() >= 0) {
                    txtMaCTHD.setText(tableChiTietHoaDon.getValueAt(tableChiTietHoaDon.getSelectedRow(), 0) + "");
                    txtMaHoaDon.setText(tableChiTietHoaDon.getValueAt(tableChiTietHoaDon.getSelectedRow(), 1) + "");
                    txtMaSanPham.setText(tableChiTietHoaDon.getValueAt(tableChiTietHoaDon.getSelectedRow(), 2) + "");
                    txtSoLuong.setText(tableChiTietHoaDon.getValueAt(tableChiTietHoaDon.getSelectedRow(), 3) + "");
                    txtTongTien.setText(tableChiTietHoaDon.getValueAt(tableChiTietHoaDon.getSelectedRow(), 4) + "");
                }
            }

        });
    }

    private void xoatrang() {
        txtMaCTHD.setText("");
        txtMaHoaDon.setText("");
        txtMaSanPham.setText("");
        txtSoLuong.setText("");
        txtTongTien.setText("");
        loadChiTietHoaDon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        txtTimKiem = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableChiTietHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtMaCTHD = new javax.swing.JTextField();
        buttonLamMoi = new javax.swing.JButton();
        buttonCapNhat = new javax.swing.JButton();
        buttonThem = new javax.swing.JButton();
        ButtonXoa = new javax.swing.JButton();
        txtTongTien = new javax.swing.JTextField();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setPreferredSize(new java.awt.Dimension(835, 610));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        jLabel9.setText("Tìm Kiếm:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Chi tiết phiếu xuất");

        tableChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tableChiTietHoaDon.setPreferredSize(new java.awt.Dimension(400, 630));
        jScrollPane1.setViewportView(tableChiTietHoaDon);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(380, 281));

        jLabel4.setText("Tổng tiền:");

        jLabel5.setText("Số lượng:");

        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã sản phẩm:");

        jLabel6.setText("Mã hoá đơn:");

        txtMaCTHD.setEditable(false);
        txtMaCTHD.setText("Ẩn mã CTHD");
        txtMaCTHD.setEnabled(false);

        buttonLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        buttonLamMoi.setText("Làm mới");
        buttonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLamMoiActionPerformed(evt);
            }
        });

        buttonCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        buttonCapNhat.setText("Cập nhật");
        buttonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCapNhatActionPerformed(evt);
            }
        });

        buttonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        buttonThem.setText("Thêm");
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });

        ButtonXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        ButtonXoa.setText("Xoá");
        ButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ButtonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addComponent(buttonThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 16, Short.MAX_VALUE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLamMoi)
                    .addComponent(buttonCapNhat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaCTHD, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonXoa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLamMoiActionPerformed
        xoatrang();
    }//GEN-LAST:event_buttonLamMoiActionPerformed

    private void buttonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatActionPerformed
        conn = cn.getConnection();

        try {
            if (txtMaHoaDon.getText().equals("") || txtMaSanPham.getText().equals("") || txtSoLuong.getText().equals("") || txtTongTien.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                Statement st = conn.createStatement();
                String sqlUpdate = "UPDATE dbo.ChiTietHoaDon SET MaHoaDon='" + txtMaHoaDon.getText() + "', MaSanPham ='" + txtMaSanPham.getText() + "',SoLuong ='" + txtSoLuong.getText() + "',TongTien ='" + txtTongTien.getText() + "' WHERE MaCTHD = '" + txtMaCTHD.getText() + "'";
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

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed
        conn = cn.getConnection();
        try {
            if (txtMaHoaDon.getText().equals("") || txtMaSanPham.getText().equals("") || txtSoLuong.getText().equals("") || txtTongTien.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                StringBuffer sb = new StringBuffer();
                String sqlCheck = "SELECT MaCTHD FROM dbo.ChiTietHoaDon WHERE MaCTHD = '" + txtMaCTHD.getText() + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlCheck);
                if (rs.next()) {
                    sb.append("Mã chi tiết hoá đơn đã tồn tại!.Hãy làm mới và nhập lại");
                }
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    String sqlInsert = "INSERT INTO dbo.ChiTietHoaDon (MaHoaDon,MaSanPham,SoLuong,TongTien) VALUES (?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sqlInsert);
                        pst.setString(1, txtMaHoaDon.getText());
                        pst.setString(2, txtMaSanPham.getText());
                        pst.setString(3, txtSoLuong.getText());
                        pst.setString(4, txtTongTien.getText());
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

    private void ButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonXoaActionPerformed
        conn = cn.getConnection();
        try {
            String sqlDelete = "DELETE dbo.ChiTietHoaDon WHERE MaCTHD = '" + txtMaCTHD.getText() + "'";
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
    }//GEN-LAST:event_ButtonXoaActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DefaultTableModel ob = (DefaultTableModel) tableChiTietHoaDon.getModel();
        TableRowSorter<DefaultTableModel> row = new TableRowSorter<>(ob);
        tableChiTietHoaDon.setRowSorter(row);
        row.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPhamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonXoa;
    private javax.swing.JButton buttonCapNhat;
    private javax.swing.JButton buttonLamMoi;
    private javax.swing.JButton buttonThem;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableChiTietHoaDon;
    private javax.swing.JTextField txtMaCTHD;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
