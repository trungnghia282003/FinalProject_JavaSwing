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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author ASUS
 */
public class PhieuXuatPage extends javax.swing.JInternalFrame {

    final String header[] = {"Mã phiếu xuất", "Mã khách hàng", "Mã nhân viên", "Ngày lập hoá đơn"};
    final DefaultTableModel tb = new DefaultTableModel(header, 0);
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectDB cn = new ConnectDB();
    Connection conn;

    public PhieuXuatPage() throws SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadHoaDon();
        LoadComboBoxKhachHang();
        AutoCompleteDecorator.decorate(comboboxKhachHang);
        txtMaHoaDon.setVisible(false);
        txtNgayLapHoaDon.setVisible(false);
    }

    public void loadHoaDon() {
        try {
            conn = cn.getConnection();
            int number;
            Vector row;
            String sql = "SELECT MaHoaDon,MaKhachHang,MaNhanVien,NgayLapHoaDon FROM dbo.HoaDon";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            tb.setRowCount(0);
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.add(rs.getString("MaHoaDon").trim());
                    row.add(rs.getString("MaKhachHang").trim());
                    row.add(rs.getString("MaNhanVien").trim());
                    row.add(rs.getString("NgayLapHoaDon").trim());
                }
                tb.addRow(row);
                tableHoaDon.setModel(tb);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
        tableHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableHoaDon.getSelectedRow() >= 0) {
                    txtMaHoaDon.setText(tableHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 0) + "");
                    txtMaNhanVien.setText(tableHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 2) + "");
                    txtNgayLapHoaDon.setText(tableHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 3) + "");
                }
            }

        });
    }

    public void LoadComboBoxKhachHang() throws SQLException {
        String sql = " SELECT * FROM dbo.KhachHang";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        //Tao 1 DefaultComboBoxModel
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) comboboxKhachHang.getModel();
        cbbModel.removeAllElements();
        try {
            //Doc danh sach de do vao ComboBox
            while (rs.next()) {
                int MaKhachHang = rs.getInt("MaKhachHang");
                String TenKhachHang = rs.getString("TenKhachHang");

                MyComboBox mycbb = new MyComboBox(MaKhachHang, TenKhachHang);
                //Them mycbb va Combobox 
                cbbModel.addElement(mycbb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    private void xoatrang() {
        txtMaNhanVien.setText("");
        txtMaHoaDon.setText("");
        txtNgayLapHoaDon.setText("");
        loadHoaDon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        sellPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        txtNgayLapHoaDon = new javax.swing.JTextField();
        buttonCapNhat = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        buttonLamMoi = new javax.swing.JButton();
        addCustButton = new javax.swing.JButton();
        prodNameLabel = new javax.swing.JLabel();
        buttonThem = new javax.swing.JButton();
        txtMaNhanVien = new javax.swing.JTextField();
        comboboxKhachHang = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHoaDon = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        buttonXemChiTietHoaDon = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(860, 610));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Phiếu xuất");

        sellPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Mã khách hàng:");

        jLabel5.setText("Mã nhân viên:");

        txtMaHoaDon.setEditable(false);
        txtMaHoaDon.setEnabled(false);
        txtMaHoaDon.setFocusable(false);

        txtNgayLapHoaDon.setEditable(false);
        txtNgayLapHoaDon.setEnabled(false);
        txtNgayLapHoaDon.setFocusable(false);

        buttonCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        buttonCapNhat.setText("Cập nhật");
        buttonCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCapNhatActionPerformed(evt);
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

        buttonLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        buttonLamMoi.setText("Làm mới");
        buttonLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLamMoiActionPerformed(evt);
            }
        });

        addCustButton.setText("Thêm mới");
        addCustButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCustButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustButtonActionPerformed(evt);
            }
        });

        prodNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        buttonThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        buttonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        buttonThem.setText("Thêm");
        buttonThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sellPanelLayout = new javax.swing.GroupLayout(sellPanel);
        sellPanel.setLayout(sellPanelLayout);
        sellPanelLayout.setHorizontalGroup(
            sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sellPanelLayout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(prodNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 5, Short.MAX_VALUE))
                    .addComponent(txtMaHoaDon)
                    .addComponent(buttonThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sellPanelLayout.createSequentialGroup()
                        .addComponent(buttonXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(sellPanelLayout.createSequentialGroup()
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNhanVien)
                            .addGroup(sellPanelLayout.createSequentialGroup()
                                .addComponent(comboboxKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addCustButton)))))
                .addContainerGap())
            .addComponent(txtNgayLapHoaDon)
        );
        sellPanelLayout.setVerticalGroup(
            sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustButton)
                    .addComponent(comboboxKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonXoa)
                    .addComponent(buttonLamMoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(txtNgayLapHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prodNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHoaDon);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        jLabel8.setText("Tìm Kiếm:");

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

        buttonXemChiTietHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/billpage.png"))); // NOI18N
        buttonXemChiTietHoaDon.setText("Xem Chi Tiết Phiếu Xuất");
        buttonXemChiTietHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonXemChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXemChiTietHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(118, 118, 118)
                        .addComponent(buttonXemChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sellPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(buttonXemChiTietHoaDon))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(sellPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, Short.MAX_VALUE))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatActionPerformed
        conn = cn.getConnection();

        String TenKhachHang;
        TenKhachHang = comboboxKhachHang.getSelectedItem().toString(); // chuyen object --> String
        int MaKhachHang;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxKhachHang.getSelectedItem();
        MaKhachHang = LoaiDuocChon.MaInt();

        try {
            if (txtMaNhanVien.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                Statement st = conn.createStatement();
                String sqlUpdate = "UPDATE dbo.HoaDon SET MaKhachHang ='" + MaKhachHang + "',MaNhanVien ='" + txtMaNhanVien.getText() + "',NgayLapHoaDon='" + txtNgayLapHoaDon.getText() + "' WHERE MaHoaDon = '" + txtMaHoaDon.getText() + "'";
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

    private void buttonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaActionPerformed
        conn = cn.getConnection();
        try {
            String sqlDelete = "DELETE dbo.HoaDon WHERE MaHoaDon = '" + txtMaHoaDon.getText() + "'";
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

    private void buttonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLamMoiActionPerformed
        xoatrang();
    }//GEN-LAST:event_buttonLamMoiActionPerformed

    private void addCustButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustButtonActionPerformed
        try {
            jPanel1.removeAll();
            KhachHangPage khachHangPage = new KhachHangPage();
            jPanel1.add(khachHangPage).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuXuatPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addCustButtonActionPerformed

    private void tableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHoaDonMouseClicked
        int x = tableHoaDon.getSelectedRow();
        if (x >= 0) {
            txtMaHoaDon.setText(tableHoaDon.getValueAt(x, 0) + "");
            txtMaNhanVien.setText(tableHoaDon.getValueAt(x, 2) + "");
            txtNgayLapHoaDon.setText(tableHoaDon.getValueAt(x, 3) + "");
            txtMaHoaDon.setEnabled(false);
        }
    }//GEN-LAST:event_tableHoaDonMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DefaultTableModel ob = (DefaultTableModel) tableHoaDon.getModel();
        TableRowSorter<DefaultTableModel> row = new TableRowSorter<>(ob);
        tableHoaDon.setRowSorter(row);
        row.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed
        conn = cn.getConnection();

        String TenKhachHang;
        TenKhachHang = comboboxKhachHang.getSelectedItem().toString(); // chuyen object --> String
        int MaKhachHang;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxKhachHang.getSelectedItem();
        MaKhachHang = LoaiDuocChon.MaInt();

        try {
            if (txtMaNhanVien.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                StringBuffer sb = new StringBuffer();
                String sqlCheck = "SELECT MaHoaDon FROM dbo.HoaDon WHERE MaHoaDon = '" + txtMaHoaDon.getText() + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlCheck);
                if (rs.next()) {
                    sb.append("Mã phiếu xuất hoặc khách hàng đã tồn tại!.Hãy làm mới và nhập lại");
                }
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    String sqlInsert = "INSERT INTO dbo.HoaDon (MaKhachHang,MaNhanVien,NgayLapHoaDon) VALUES (?,?,?)";
                    try {
                        pst = conn.prepareStatement(sqlInsert);
                        pst.setInt(1, MaKhachHang);
                        pst.setString(2, txtMaNhanVien.getText());
                        pst.setString(3, txtNgayLapHoaDon.getText());
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

    private void buttonXemChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXemChiTietHoaDonActionPerformed
        jPanel1.removeAll();
        ChiTietPhieuXuatPage chiTietHoaDonPage = new ChiTietPhieuXuatPage();
        jPanel1.add(chiTietHoaDonPage).setVisible(true);
    }//GEN-LAST:event_buttonXemChiTietHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCustButton;
    private javax.swing.JButton buttonCapNhat;
    private javax.swing.JButton buttonLamMoi;
    private javax.swing.JButton buttonThem;
    private javax.swing.JButton buttonXemChiTietHoaDon;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JComboBox<String> comboboxKhachHang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel prodNameLabel;
    private javax.swing.JPanel sellPanel;
    private javax.swing.JTable tableHoaDon;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgayLapHoaDon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
