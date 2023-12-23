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
public class PhieuNhapPage extends javax.swing.JInternalFrame {

    final String header[] = {"Mã phiếu nhập", "Mã nhân viên", "Mã nhà phân phối", "Ngày nhập"};
    final DefaultTableModel tb = new DefaultTableModel(header, 0);
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectDB cn = new ConnectDB();
    Connection conn;

    public PhieuNhapPage() throws SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadNhapHang();
        LoadComboBoxNhaPhanPhoi();
        AutoCompleteDecorator.decorate(comboboxMaNhanPhanPhoi);
        txtMaPhieuNhap.setVisible(false);
        txtNgayNhap.setVisible(false);
    }
    
    
    public void loadNhapHang() {
        try {
            conn = cn.getConnection();
            int number;
            Vector row;
            String sql = "SELECT MaPhieuNhap,MaNhanVien,MaNhaPhanPhoi,NgayNhap  FROM dbo.PhieuNhap";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            tb.setRowCount(0);
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.add(rs.getString("MaPhieuNhap").trim());
                    row.add(rs.getString("MaNhanVien").trim());
                    row.add(rs.getString("MaNhaPhanPhoi").trim());
                    row.add(rs.getString("NgayNhap").trim());
                }
                tb.addRow(row);
                tableNhapHang.setModel(tb);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
        tableNhapHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableNhapHang.getSelectedRow() >= 0) {
                    txtMaPhieuNhap.setText(tableNhapHang.getValueAt(tableNhapHang.getSelectedRow(), 0) + "");
                    txtMaNhanVien.setText(tableNhapHang.getValueAt(tableNhapHang.getSelectedRow(), 1) + "");
                    txtNgayNhap.setText(tableNhapHang.getValueAt(tableNhapHang.getSelectedRow(), 3) + "");
                }
            }

        });
    }
    
    
    
    //Do danh sach quyen
    public void LoadComboBoxNhaPhanPhoi() throws SQLException {
        String sql = " SELECT * FROM dbo.NhaPhanPhoi";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        //Tao 1 DefaultComboBoxModel
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) comboboxMaNhanPhanPhoi.getModel();
        cbbModel.removeAllElements();
        try {
            //Doc danh sach de do vao ComboBox
            while (rs.next()) {
                int MaNhaPhanPhoi = rs.getInt("MaNhaPhanPhoi");
                String TenNhaPhanPhoi = rs.getString("TenNhaPhanPhoi");

                MyComboBox mycbb = new MyComboBox(MaNhaPhanPhoi, TenNhaPhanPhoi);
                //Them mycbb va Combobox 
                cbbModel.addElement(mycbb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    private void xoatrang() {
        txtMaPhieuNhap.setText("");
        txtNgayNhap.setText("");
        txtMaNhanVien.setText("");
        loadNhapHang();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboboxMaNhanPhanPhoi = new javax.swing.JComboBox<>();
        addSuppButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buttonNhap = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        buttonCapNhat = new javax.swing.JButton();
        buttonQuayLai = new javax.swing.JButton();
        txtMaPhieuNhap = new javax.swing.JTextField();
        txtNgayNhap = new javax.swing.JTextField();
        txtMaNhanVien = new javax.swing.JTextField();
        JS1 = new javax.swing.JScrollPane();
        tableNhapHang = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        buttonXemChiTietPhieuNhap = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(860, 610));

        jPanel1.setPreferredSize(new java.awt.Dimension(860, 610));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Phiếu nhập");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Supplier:");

        addSuppButton.setText("Thêm mới");
        addSuppButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSuppButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã nhân viên:");

        jLabel5.setText("Mã nhà phân phối:");

        buttonNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        buttonNhap.setText("Thêm");
        buttonNhap.setFocusTraversalPolicyProvider(true);
        buttonNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNhapActionPerformed(evt);
            }
        });

        buttonXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        buttonXoa.setText("Xoá");
        buttonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaActionPerformed(evt);
            }
        });

        buttonCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        buttonCapNhat.setText("Cập nhật");
        buttonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCapNhatActionPerformed(evt);
            }
        });

        buttonQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        buttonQuayLai.setText("Làm mới");
        buttonQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQuayLaiActionPerformed(evt);
            }
        });

        txtMaPhieuNhap.setEditable(false);
        txtMaPhieuNhap.setEnabled(false);
        txtMaPhieuNhap.setFocusable(false);

        txtNgayNhap.setEditable(false);
        txtNgayNhap.setEnabled(false);
        txtNgayNhap.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaPhieuNhap)
                            .addComponent(txtNgayNhap)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaNhanVien))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(buttonNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(buttonCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboboxMaNhanPhanPhoi, 0, 118, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addSuppButton)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxMaNhanPhanPhoi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSuppButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonNhap))
                .addGap(12, 12, 12)
                .addComponent(buttonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonQuayLai)
                .addGap(106, 106, 106)
                .addComponent(txtMaPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        tableNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhapHangMouseClicked(evt);
            }
        });
        JS1.setViewportView(tableNhapHang);

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

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        jLabel10.setText("Tìm kiếm:");

        buttonXemChiTietPhieuNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/purchasepage.png"))); // NOI18N
        buttonXemChiTietPhieuNhap.setText("Xem chi tiết phiếu nhập");
        buttonXemChiTietPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXemChiTietPhieuNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JS1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(66, 66, 66)
                        .addComponent(buttonXemChiTietPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(buttonXemChiTietPhieuNhap))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JS1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhapHangMouseClicked
        int x = tableNhapHang.getSelectedRow();
        if (x >= 0) {
            txtMaPhieuNhap.setText(tableNhapHang.getValueAt(x, 0) + "");
            txtMaNhanVien.setText(tableNhapHang.getValueAt(x, 1) + "");
            txtNgayNhap.setText(tableNhapHang.getValueAt(x, 3) + "");
            txtMaPhieuNhap.setEnabled(false);
        }
    }//GEN-LAST:event_tableNhapHangMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DefaultTableModel ob = (DefaultTableModel) tableNhapHang.getModel();
        TableRowSorter<DefaultTableModel> row = new TableRowSorter<>(ob);
        tableNhapHang.setRowSorter(row);
        row.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void buttonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatActionPerformed
        conn = cn.getConnection();
        
        //Lay ma loai quyen  tu comboboxQuyen
        String tenNhaPhanPhoi;
        tenNhaPhanPhoi = comboboxMaNhanPhanPhoi.getSelectedItem().toString(); // chuyen object --> String
        int MaNhaPhanPhoi;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxMaNhanPhanPhoi.getSelectedItem();
        MaNhaPhanPhoi = LoaiDuocChon.MaInt();

        
        try {
            if (txtMaNhanVien.getText().equals("") || tenNhaPhanPhoi.equals("") ) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                Statement st = conn.createStatement();
                String sqlUpdate = "UPDATE dbo.PhieuNhap SET MaNhanVien ='" + txtMaNhanVien.getText() + "',MaNhaPhanPhoi ='" + MaNhaPhanPhoi + "',NgayNhap ='" + txtNgayNhap.getText() + "' WHERE MaPhieuNhap = '" + txtMaPhieuNhap.getText() + "'";
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
            String sqlDelete = "DELETE dbo.PhieuNhap WHERE MaPhieuNhap = '" + txtMaPhieuNhap.getText() + "'";
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

    private void buttonNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNhapActionPerformed
        conn = cn.getConnection();
        
        //Lay ma loai quyen  tu comboboxQuyen
        String tenNhaPhanPhoi;
        tenNhaPhanPhoi = comboboxMaNhanPhanPhoi.getSelectedItem().toString(); // chuyen object --> String
        int MaNhaPhanPhoi;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxMaNhanPhanPhoi.getSelectedItem();
        MaNhaPhanPhoi = LoaiDuocChon.MaInt();
        
        try {
            if (txtMaNhanVien.getText().equals("") || tenNhaPhanPhoi.equals("") ) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                StringBuffer sb = new StringBuffer();
                String sqlCheck = "SELECT MaNhaPhanPhoi FROM dbo.PhieuNhap WHERE MaNhaPhanPhoi = '" + txtMaPhieuNhap.getText() + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlCheck);
                if (rs.next()) {
                    sb.append("Mã nhập hàng đã tồn tại!.Hãy làm mới và nhập lại");
                }
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    String sqlInsert = "INSERT INTO dbo.PhieuNhap (MaNhanVien,MaNhaPhanPhoi,NgayNhap) VALUES (?,?,?)";
                    try {
                        pst = conn.prepareStatement(sqlInsert);
                        pst.setString(1, txtMaNhanVien.getText());
                        pst.setInt(2, MaNhaPhanPhoi);
                        pst.setString(3, txtNgayNhap.getText());
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
    }//GEN-LAST:event_buttonNhapActionPerformed

    private void addSuppButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSuppButtonActionPerformed
        jPanel1.removeAll();
        NhaPhanPhoiPage nhaPhanPhoiPage = new NhaPhanPhoiPage();
        jPanel1.add(nhaPhanPhoiPage).setVisible(true);
    }//GEN-LAST:event_addSuppButtonActionPerformed

    private void buttonQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQuayLaiActionPerformed
        xoatrang();
    }//GEN-LAST:event_buttonQuayLaiActionPerformed

    private void buttonXemChiTietPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXemChiTietPhieuNhapActionPerformed
        jPanel1.removeAll();
        ChiTietPhieuNhapPage chiTietPhieuXuatPage = new ChiTietPhieuNhapPage();
        jPanel1.add(chiTietPhieuXuatPage).setVisible(true);
    }//GEN-LAST:event_buttonXemChiTietPhieuNhapActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        
    }//GEN-LAST:event_txtTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JS1;
    private javax.swing.JButton addSuppButton;
    private javax.swing.JButton buttonCapNhat;
    private javax.swing.JButton buttonNhap;
    private javax.swing.JButton buttonQuayLai;
    private javax.swing.JButton buttonXemChiTietPhieuNhap;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JComboBox<String> comboboxMaNhanPhanPhoi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTable tableNhapHang;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMaPhieuNhap;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
