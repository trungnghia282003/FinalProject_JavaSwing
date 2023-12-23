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
import javax.swing.JTextField;
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
public class SanPhamPage extends javax.swing.JInternalFrame {

    final String header[] = {"Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Hãng sản xuất", "Giá nhập", "Giá bán", "Tồn kho", "Trạng thái"};
    final DefaultTableModel tb = new DefaultTableModel(header, 0);
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectDB cn = new ConnectDB();
    Connection conn;

    public SanPhamPage() throws SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadSanPham();
        LoadComboBoxLoaiSanPham();
        LoadComboBoxHangSanXuat();
        AutoCompleteDecorator.decorate(comboboxLoaiSanPham);
        AutoCompleteDecorator.decorate(comboboxHangSanXuat);
        txtMaSanPham.setVisible(false);
    }

    public void loadSanPham() {
        try {
            conn = cn.getConnection();
            int number;
            Vector row;
            String sql = "SELECT MaSanPham,TenSanPham,LoaiSanPham,HangSanXuat,GiaNhap,GiaBan,TonKho,TrangThai FROM dbo.SanPham";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            tb.setRowCount(0);
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.add(rs.getString("MaSanPham").trim());
                    row.add(rs.getString("TenSanPham").trim());
                    row.add(rs.getString("LoaiSanPham").trim());
                    row.add(rs.getString("HangSanXuat").trim());
                    row.add(rs.getString("GiaNhap").trim());
                    row.add(rs.getString("GiaBan").trim());
                    row.add(rs.getString("TonKho").trim());
                    row.add(rs.getString("TrangThai").trim());
                }
                tb.addRow(row);
                tableSanPham.setModel(tb);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
        tableSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableSanPham.getSelectedRow() >= 0) {
                    txtMaSanPham.setText(tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 0) + "");
                    txtTenSanPham.setText(tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 1) + "");
                    txtGiaNhap.setText(tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 4) + "");
                    txtGiaBan.setText(tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 5) + "");
                    txtTonKho.setText(tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 6) + "");
                    txtTrangThai.setText(tableSanPham.getValueAt(tableSanPham.getSelectedRow(), 7) + "");
                }
            }

        });
    }
    //Do danh sach quyen

    public void LoadComboBoxHangSanXuat() throws SQLException {
        String sql = " SELECT * FROM dbo.HangSanXuat";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        //Tao 1 DefaultComboBoxModel
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) comboboxHangSanXuat.getModel();
        cbbModel.removeAllElements();
        try {
            //Doc danh sach de do vao ComboBox
            while (rs.next()) {
                int MaHangSanXuat = rs.getInt("MaHangSanXuat");
                String TenHangSanXuat = rs.getString("TenHangSanXuat");

                MyComboBox mycbb = new MyComboBox(MaHangSanXuat, TenHangSanXuat);
                //Them mycbb va Combobox 
                cbbModel.addElement(mycbb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    //Do danh sach quyen
    public void LoadComboBoxLoaiSanPham() throws SQLException {
        String sql = " SELECT * FROM dbo.LoaiSanPham";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        //Tao 1 DefaultComboBoxModel
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) comboboxLoaiSanPham.getModel();
        cbbModel.removeAllElements();
        try {
            //Doc danh sach de do vao ComboBox
            while (rs.next()) {
                int MaLoaiSanPham = rs.getInt("MaLoaiSanPham");
                String TenLoaiSanPham = rs.getString("TenLoaiSanPham");

                MyComboBox mycbb = new MyComboBox(MaLoaiSanPham, TenLoaiSanPham);
                //Them mycbb va Combobox 
                cbbModel.addElement(mycbb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    private void xoatrang() {
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
        txtTonKho.setText("");
        txtTrangThai.setText("");
        loadSanPham();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        entryPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        buttonThem = new javax.swing.JButton();
        buttonSua = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        buttonLamMoi = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTonKho = new javax.swing.JTextField();
        txtTrangThai = new javax.swing.JTextField();
        comboboxLoaiSanPham = new javax.swing.JComboBox<>();
        comboboxHangSanXuat = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSanPham = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(880, 610));

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 450));
        jPanel1.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Sản phẩm");

        entryPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        entryPanel.setPreferredSize(new java.awt.Dimension(280, 382));

        jLabel3.setText("Tên sản phẩm:");

        jLabel5.setText("Loại sản phẩm:");

        jLabel6.setText("Hãng sản xuất:");

        jLabel7.setText("Giá nhập");

        jLabel8.setText("Giá bán:");

        txtMaSanPham.setEditable(false);
        txtMaSanPham.setEnabled(false);
        txtMaSanPham.setFocusable(false);

        buttonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        buttonThem.setText("Thêm");
        buttonThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });

        buttonSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        buttonSua.setText("Sửa ");
        buttonSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSuaActionPerformed(evt);
            }
        });

        buttonXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        buttonXoa.setText("Xoá");
        buttonXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonXoa.setHideActionText(true);
        buttonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaActionPerformed(evt);
            }
        });

        buttonLamMoi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        buttonLamMoi.setText("Làm mới");
        buttonLamMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLamMoiActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng thái:");

        jLabel11.setText("Tồn kho:");

        txtTrangThai.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTrangThaiFocusLost(evt);
            }
        });

        comboboxLoaiSanPham.setEditable(true);

        comboboxHangSanXuat.setEditable(true);

        javax.swing.GroupLayout entryPanelLayout = new javax.swing.GroupLayout(entryPanel);
        entryPanel.setLayout(entryPanelLayout);
        entryPanelLayout.setHorizontalGroup(
            entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, entryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMaSanPham)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSanPham))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaBan))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaNhap))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTrangThai))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTonKho))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboboxHangSanXuat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboboxLoaiSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, entryPanelLayout.createSequentialGroup()
                        .addComponent(buttonThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(buttonLamMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        entryPanelLayout.setVerticalGroup(
            entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryPanelLayout.createSequentialGroup()
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxHangSanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTonKho, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonThem)
                    .addComponent(buttonSua)
                    .addComponent(buttonXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLamMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8"
            }
        ));
        tableSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSanPham);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        jLabel9.setText("Tìm kiếm:");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                    .addComponent(entryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(entryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(107, 107, 107))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSanPhamMouseClicked

    }//GEN-LAST:event_tableSanPhamMouseClicked

    private void buttonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLamMoiActionPerformed
        xoatrang();
    }//GEN-LAST:event_buttonLamMoiActionPerformed

    private void buttonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaActionPerformed
        conn = cn.getConnection();
        try {
            String sqlDelete = "DELETE dbo.SanPham WHERE MaSanPham = '" + txtMaSanPham.getText() + "'";
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

    private void buttonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSuaActionPerformed
        conn = cn.getConnection();

        String loaiSanPham;
        loaiSanPham = comboboxLoaiSanPham.getSelectedItem().toString(); // chuyen object --> String
        String hangSanXuat;
        hangSanXuat = comboboxHangSanXuat.getSelectedItem().toString(); // chuyen object --> String

        //Lay ma loai quyen  tu comboboxQuyen
        int MaLoaiSanPham;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxLoaiSanPham.getSelectedItem();
        MaLoaiSanPham = LoaiDuocChon.MaInt();
        int MaHangSanXuat;
        MyComboBox LoaiDuocChon1 = (MyComboBox) comboboxHangSanXuat.getSelectedItem();
        MaHangSanXuat = LoaiDuocChon.MaInt();
        try {
            if (txtTenSanPham.getText().equals("") || loaiSanPham.equals("") || hangSanXuat.equals("") || txtGiaNhap.getText().equals("") || txtGiaBan.getText().equals("") || txtTonKho.getText().equals("") || txtTrangThai.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                Statement st = conn.createStatement();
                String sqlUpdate = "UPDATE dbo.SanPham SET TenSanPham ='" + txtTenSanPham.getText() + "',LoaiSanPham ='" + MaLoaiSanPham + "',HangSanXuat='" + MaHangSanXuat + "',GiaNhap ='" + txtGiaNhap.getText() + "',GiaBan='" + txtGiaBan.getText() + "',TonKho='" + txtTonKho.getText() + "',TrangThai='" + txtTrangThai.getText() + "' WHERE MaSanPham = '" + txtMaSanPham.getText() + "'";
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
    }//GEN-LAST:event_buttonSuaActionPerformed

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed
        conn = cn.getConnection();

        String loaiSanPham;
        loaiSanPham = comboboxLoaiSanPham.getSelectedItem().toString(); // chuyen object --> String
        String hangSanXuat;
        hangSanXuat = comboboxHangSanXuat.getSelectedItem().toString(); // chuyen object --> String

        //Lay ma loai quyen  tu comboboxQuyen
        int MaLoaiSanPham;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxLoaiSanPham.getSelectedItem();
        MaLoaiSanPham = LoaiDuocChon.MaInt();
        int MaHangSanXuat;
        MyComboBox LoaiDuocChon1 = (MyComboBox) comboboxHangSanXuat.getSelectedItem();
        MaHangSanXuat = LoaiDuocChon.MaInt();

        try {
            if (txtTenSanPham.getText().equals("") || loaiSanPham.equals("") || hangSanXuat.equals("") || txtGiaNhap.getText().equals("") || txtGiaBan.getText().equals("") || txtTonKho.getText().equals("") || txtTrangThai.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                StringBuffer sb = new StringBuffer();
                String sqlCheck = "SELECT MaSanPham FROM dbo.SanPham WHERE MaSanPham = '" + txtMaSanPham.getText() + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlCheck);
                if (rs.next()) {
                    sb.append("Sản phẩm đã tồn tại!.Hãy làm mới và nhập lại");
                }
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    String sqlInsert = "INSERT INTO dbo.SanPham (TenSanPham,LoaiSanPham,HangSanXuat,GiaNhap,GiaBan,TonKho,TrangThai) VALUES (?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sqlInsert);
                        pst.setString(1, txtTenSanPham.getText());
                        pst.setInt(2, MaLoaiSanPham);
                        pst.setInt(3, MaHangSanXuat);
                        pst.setString(4, txtGiaNhap.getText());
                        pst.setString(5, txtGiaBan.getText());
                        pst.setString(6, txtTonKho.getText());
                        pst.setString(7, txtTrangThai.getText());
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

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DefaultTableModel ob = (DefaultTableModel) tableSanPham.getModel();
        TableRowSorter<DefaultTableModel> row = new TableRowSorter<>(ob);
        tableSanPham.setRowSorter(row);
        row.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTrangThaiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTrangThaiFocusLost
        if (this.isVisible()) {
            if (txtTrangThai.getText().length() > 0) {
                if (!(txtTrangThai.getText().trim().equals("0")) || !(txtTrangThai.getText().trim().equals("1"))) {
                    JOptionPane.showMessageDialog(rootPane, "Sai định dạng, nhập lại trang thái, ví dụ: 1-Còn hàng hoặc 0-Hết hàng");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Trạng thái không được để trống");
                }
            }
        }
    }//GEN-LAST:event_txtTrangThaiFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLamMoi;
    private javax.swing.JButton buttonSua;
    private javax.swing.JButton buttonThem;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JComboBox<String> comboboxHangSanXuat;
    private javax.swing.JComboBox<String> comboboxLoaiSanPham;
    private javax.swing.JPanel entryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtTenSanPham;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTonKho;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
