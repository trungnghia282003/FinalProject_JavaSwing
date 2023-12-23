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

/**
 *
 * @author ASUS
 */
public class KhachHangPage extends javax.swing.JInternalFrame {

    final String header[] = {"Mã khách hàng", "Tên khách hàng", "Ngày sinh", "Giới tính", "Địa chỉ", "SDT", "Loại khách hàng",};
    final DefaultTableModel tb = new DefaultTableModel(header, 0);
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectDB cn = new ConnectDB();
    Connection conn;

    public KhachHangPage() throws SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadKhachHang();
        LoadComboBoxLoaiSanPham();
        txtMaKhachHang.setVisible(false);
        txtNgaySinh.setVisible(false);
    }

    public void loadKhachHang() {
        try {
            conn = cn.getConnection();
            int number;
            Vector row;
            String sql = "SELECT MaKhachHang,TenKhachHang,Ngaysinh,GioiTinh,DiaChi,SDT,LoaiKhachHang FROM dbo.KhachHang";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            tb.setRowCount(0);
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.add(rs.getString("MaKhachHang").trim());
                    row.add(rs.getString("TenKhachHang").trim());
                    row.add(rs.getString("Ngaysinh").trim());
                    row.add(rs.getString("GioiTinh").trim());
                    row.add(rs.getString("DiaChi").trim());
                    row.add(rs.getString("SDT").trim());
                    row.add(rs.getString("LoaiKhachHang").trim());
                }
                tb.addRow(row);
                tableKhachHang.setModel(tb);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
        tableKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableKhachHang.getSelectedRow() >= 0) {
                    txtMaKhachHang.setText(tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 0) + "");
                    txtTenKhachHang.setText(tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 1) + "");
                    txtNgaySinh.setText(tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 2) + "");
                    txtGioiTinh.setText(tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 3) + "");
                    txtDiaChi.setText(tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 4) + "");
                    txtSDT.setText(tableKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 5) + "");

                }
            }

        });
    }

    public void LoadComboBoxLoaiSanPham() throws SQLException {
        String sql = " SELECT * FROM dbo.LoaiKhachHang";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        //Tao 1 DefaultComboBoxModel
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) comboboxLoaiKhachHang.getModel();
        cbbModel.removeAllElements();
        try {
            //Doc danh sach de do vao ComboBox
            while (rs.next()) {
                int MaLoaiKhachHang = rs.getInt("MaLoaiKhachHang");
                String TenLoaiKhachHang = rs.getString("TenLoaiKhachHang");

                MyComboBox mycbb = new MyComboBox(MaLoaiKhachHang, TenLoaiKhachHang);
                //Them mycbb va Combobox 
                cbbModel.addElement(mycbb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    private void xoatrang() {
        txtMaKhachHang.setText("");
        txtTenKhachHang.setText("");
        txtNgaySinh.setText("");
        txtGioiTinh.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        loadKhachHang();
        txtMaKhachHang.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        entryPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKhachHang = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        buttonThem = new javax.swing.JButton();
        buttonCapNhat = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        buttonQuayLai = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        comboboxLoaiKhachHang = new javax.swing.JComboBox<>();
        txtGioiTinh = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableKhachHang = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(860, 610));

        jPanel1.setMaximumSize(new java.awt.Dimension(30000, 30000));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Khách Hàng");

        entryPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Tên khách hàng:");

        txtMaKhachHang.setEnabled(false);
        txtMaKhachHang.setFocusable(false);

        buttonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        buttonThem.setText("Thêm");
        buttonThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });

        buttonCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        buttonCapNhat.setText("Cập nhật ");
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

        buttonQuayLai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buttonQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/refresh.png"))); // NOI18N
        buttonQuayLai.setText("Làm mới");
        buttonQuayLai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonQuayLaiActionPerformed(evt);
            }
        });

        jLabel7.setText("Giới tính:");

        jLabel11.setText("Địa chỉ:");

        jLabel12.setText("Loại khách hàng:");

        jLabel13.setText("Số điện thoại:");

        txtNgaySinh.setEditable(false);
        txtNgaySinh.setEnabled(false);
        txtNgaySinh.setFocusable(false);

        comboboxLoaiKhachHang.setName(""); // NOI18N

        txtGioiTinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGioiTinhFocusLost(evt);
            }
        });

        javax.swing.GroupLayout entryPanelLayout = new javax.swing.GroupLayout(entryPanel);
        entryPanel.setLayout(entryPanelLayout);
        entryPanelLayout.setHorizontalGroup(
            entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryPanelLayout.createSequentialGroup()
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboboxLoaiKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtGioiTinh, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(entryPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(entryPanelLayout.createSequentialGroup()
                                .addComponent(buttonThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonCapNhat)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(buttonQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaKhachHang)
                            .addComponent(txtNgaySinh))))
                .addContainerGap())
        );
        entryPanelLayout.setVerticalGroup(
            entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxLoaiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonThem)
                    .addComponent(buttonCapNhat)
                    .addComponent(buttonXoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableKhachHang);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        jLabel16.setText("Tìm kiếm");

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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(entryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(entryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 292, Short.MAX_VALUE))
                .addGap(0, 140, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed

        conn = cn.getConnection();

        String loaiKhachHang;
        loaiKhachHang = comboboxLoaiKhachHang.getSelectedItem().toString(); // chuyen object --> String
        int maLoaiKhachHang;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxLoaiKhachHang.getSelectedItem();
        maLoaiKhachHang = LoaiDuocChon.MaInt();

        try {
            if (txtTenKhachHang.getText().equals("") || txtGioiTinh.getText().equals("") || txtDiaChi.getText().equals("") || txtSDT.getText().equals("") || loaiKhachHang.equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                StringBuffer sb = new StringBuffer();
                String sqlCheck = "SELECT MaKhachHang FROM dbo.KhachHang WHERE MaKhachHang = '" + txtMaKhachHang.getText() + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlCheck);
                if (rs.next()) {
                    sb.append("Khách hàng đã tồn tại!.Hãy làm mới và nhập lại");
                }
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    String sqlInsert = "INSERT INTO dbo.KhachHang(TenKhachHang, Ngaysinh, GioiTinh,DiaChi, SDT,LoaiKhachHang) VALUES (?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sqlInsert);
                        pst.setString(1, txtTenKhachHang.getText());
                        pst.setString(2, txtNgaySinh.getText());
                        pst.setString(3, txtGioiTinh.getText());
                        pst.setString(4, txtDiaChi.getText());
                        pst.setString(5, txtSDT.getText());
                        pst.setInt(6, maLoaiKhachHang);

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

    private void buttonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCapNhatActionPerformed
        conn = cn.getConnection();

        String loaiKhachHang;
        loaiKhachHang = comboboxLoaiKhachHang.getSelectedItem().toString(); // chuyen object --> String
        int maLoaiKhachHang;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxLoaiKhachHang.getSelectedItem();
        maLoaiKhachHang = LoaiDuocChon.MaInt();

        try {
            if (txtTenKhachHang.getText().equals("") || txtGioiTinh.getText().equals("") || txtDiaChi.getText().equals("") || txtSDT.getText().equals("") || loaiKhachHang.equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                Statement st = conn.createStatement();
                String sqlUpdate = "UPDATE dbo.KhachHang SET TenKhachHang ='" + txtTenKhachHang.getText() + "',Ngaysinh ='" + txtNgaySinh.getText() + "',GioiTinh='" + txtGioiTinh.getText() + "',DiaChi ='" + txtDiaChi.getText() + "',SDT='" + txtSDT.getText() + "',LoaiKhachHang='" + maLoaiKhachHang + "' WHERE MaKhachHang = '" + txtMaKhachHang.getText() + "'";

                try {
                    st = conn.createStatement();
                    int kq = st.executeUpdate(sqlUpdate);
                    if (kq > 0) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                        xoatrang();
                    }
                    st.close();

                } catch (Exception ex) {
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
            String sqlDelete = "DELETE dbo.KhachHang WHERE MaKhachHang = '" + txtMaKhachHang.getText() + "'";
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
        }
    }//GEN-LAST:event_buttonXoaActionPerformed

    private void buttonQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonQuayLaiActionPerformed
        xoatrang();
    }//GEN-LAST:event_buttonQuayLaiActionPerformed

    private void tableKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKhachHangMouseClicked
        int x = tableKhachHang.getSelectedRow();
        if (x >= 0) {
            txtMaKhachHang.setText(tableKhachHang.getValueAt(x, 0) + "");
            txtTenKhachHang.setText(tableKhachHang.getValueAt(x, 1) + "");
            txtNgaySinh.setText(tableKhachHang.getValueAt(x, 2) + "");
            txtGioiTinh.setText(tableKhachHang.getValueAt(x, 3) + "");
            txtDiaChi.setText(tableKhachHang.getValueAt(x, 4) + "");
            txtSDT.setText(tableKhachHang.getValueAt(x, 5) + "");
            txtMaKhachHang.setEnabled(false);
        }
    }//GEN-LAST:event_tableKhachHangMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DefaultTableModel ob = (DefaultTableModel) tableKhachHang.getModel();
        TableRowSorter<DefaultTableModel> row = new TableRowSorter<>(ob);
        tableKhachHang.setRowSorter(row);
        row.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtGioiTinhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGioiTinhFocusLost
        if (this.isVisible()) {
            if (txtGioiTinh.getText().length() > 0) {
                if (!(txtGioiTinh.getText().trim().equals("0")) || !(txtGioiTinh.getText().trim().equals("1"))) {
                    JOptionPane.showMessageDialog(rootPane, "Sai định dạng, nhập lại giới tính, ví dụ: 1-Nam hoặc 0-Nữ");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Giới tính không được để trống");
                }
            }
        }
    }//GEN-LAST:event_txtGioiTinhFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCapNhat;
    private javax.swing.JButton buttonQuayLai;
    private javax.swing.JButton buttonThem;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JComboBox<String> comboboxLoaiKhachHang;
    private javax.swing.JPanel entryPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tableKhachHang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
