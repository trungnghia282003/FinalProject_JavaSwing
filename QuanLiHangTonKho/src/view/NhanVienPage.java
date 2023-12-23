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
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ASUS
 */
public class NhanVienPage extends javax.swing.JInternalFrame {

    final String header[] = {"Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Giới tính", "Ngày vào làm", "Chức vụ", "Địa chỉ", "SDT"};
    final DefaultTableModel tb = new DefaultTableModel(header, 0);
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    ConnectDB cn = new ConnectDB();
    Connection conn;

    public NhanVienPage() throws SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadNhanVien();
        LoadComboChucVu();
        txtMaNhanVien.setVisible(false);
        txtNgaySinh.setVisible(false);
        txtNgayVaoLam.setVisible(false);
    }

    public void loadNhanVien() {
        try {
            conn = cn.getConnection();
            int number;
            Vector row;
            String sql = "SELECT MaNhanVien,TenNhanVien,NgaySinh,GioiTinh,NgayVaoLam,ChucVu,DiaChi,SoDT FROM dbo.NhanVien";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData metadata = rs.getMetaData();
            number = metadata.getColumnCount();
            tb.setRowCount(0);
            while (rs.next()) {
                row = new Vector();
                for (int i = 1; i <= number; i++) {
                    row.add(rs.getString("MaNhanVien").trim());
                    row.add(rs.getString("TenNhanVien").trim());
                    row.add(rs.getString("NgaySinh").trim());
                    row.add(rs.getString("GioiTinh").trim());
                    row.add(rs.getString("NgayVaoLam").trim());
                    row.add(rs.getString("ChucVu").trim());
                    row.add(rs.getString("DiaChi").trim());
                    row.add(rs.getString("SoDT").trim());
                }
                tb.addRow(row);
                tableNhanVien.setModel(tb);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        }
        tableNhanVien.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tableNhanVien.getSelectedRow() >= 0) {
                    txtMaNhanVien.setText(tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 0) + "");
                    txtTenNhanVien.setText(tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 1) + "");
                    txtNgaySinh.setText(tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 2) + "");
                    txtGioiTinh.setText(tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 3) + "");
                    txtNgayVaoLam.setText(tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 4) + "");
                    txtDiaChi.setText(tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 6) + "");
                    txtSoDT.setText(tableNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 7) + "");
                }
            }

        });
    }

    //Do danh sach quyen
    public void LoadComboChucVu() throws SQLException {
        String sql = " SELECT * FROM dbo.ChucVu";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        //Tao 1 DefaultComboBoxModel
        DefaultComboBoxModel cbbModel = (DefaultComboBoxModel) comboboxChucVu.getModel();
        cbbModel.removeAllElements();
        try {
            //Doc danh sach de do vao ComboBox
            while (rs.next()) {
                int MaChucVu = rs.getInt("MaChucVu");
                String TenChucVu = rs.getString("TenChucVu");

                MyComboBox mycbb = new MyComboBox(MaChucVu, TenChucVu);
                //Them mycbb va Combobox 
                cbbModel.addElement(mycbb);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    private void xoatrang() {
        txtMaNhanVien.setText("");
        txtTenNhanVien.setText("");
        txtNgaySinh.setText("");
        txtGioiTinh.setText("");
        txtNgayVaoLam.setText("");
        txtDiaChi.setText("");
        txtSoDT.setText("");
        loadNhanVien();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        buttonThem = new javax.swing.JButton();
        buttonSua = new javax.swing.JButton();
        buttonLamMoi = new javax.swing.JButton();
        buttonXoa = new javax.swing.JButton();
        txtMaNhanVien = new javax.swing.JTextField();
        txtNgayVaoLam = new javax.swing.JTextField();
        txtTenNhanVien = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtGioiTinh = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JTextField();
        comboboxChucVu = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(875, 610));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setText("Nhân viên");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        jLabel16.setText("Tìm kiếm:");

        tableNhanVien.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tableNhanVien.setForeground(new java.awt.Color(102, 102, 102));
        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Tittle 1", "Tittle 2", "Tittle 3", "Tittle 4", "Tittle 5", "Tittle 6", "Tittle 7", "Tittle 8"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tableNhanVien.setName("Users"); // NOI18N
        tableNhanVien.setShowGrid(true);
        tableNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableNhanVien);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(380, 260));

        jLabel24.setText("Giới tính:");

        jLabel26.setText("Chức vụ:");

        jLabel28.setText("Tên nhân viên:");

        jLabel30.setText("Địa chỉ:");

        jLabel31.setText("SốDT:");

        buttonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        buttonThem.setText("Thêm");
        buttonThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonThemActionPerformed(evt);
            }
        });

        buttonSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png"))); // NOI18N
        buttonSua.setText("Sửa");
        buttonSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSuaActionPerformed(evt);
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

        buttonXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        buttonXoa.setText("Xoá");
        buttonXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonXoaActionPerformed(evt);
            }
        });

        txtMaNhanVien.setEditable(false);
        txtMaNhanVien.setEnabled(false);
        txtMaNhanVien.setFocusable(false);

        txtNgayVaoLam.setEditable(false);
        txtNgayVaoLam.setEnabled(false);
        txtNgayVaoLam.setFocusable(false);
        txtNgayVaoLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayVaoLamActionPerformed(evt);
            }
        });

        txtGioiTinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGioiTinhFocusLost(evt);
            }
        });

        txtNgaySinh.setEditable(false);
        txtNgaySinh.setEnabled(false);
        txtNgaySinh.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(comboboxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNgaySinh)
                        .addComponent(txtNgayVaoLam)
                        .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(buttonThem, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(buttonSua, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonLamMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonThem)
                    .addComponent(buttonSua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DefaultTableModel ob = (DefaultTableModel) tableNhanVien.getModel();
        TableRowSorter<DefaultTableModel> row = new TableRowSorter<>(ob);
        tableNhanVien.setRowSorter(row);
        row.setRowFilter(RowFilter.regexFilter(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tableNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhanVienMouseClicked

    }//GEN-LAST:event_tableNhanVienMouseClicked

    private void buttonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonXoaActionPerformed
        conn = cn.getConnection();

        try {
            String sqlDelete = "DELETE dbo.NhanVien WHERE MaNhanVien = '" + txtMaNhanVien.getText() + "'";
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

    private void buttonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonThemActionPerformed
        conn = cn.getConnection();

        String chucVu;
        chucVu = comboboxChucVu.getSelectedItem().toString(); // chuyen object --> String

        //Lay ma loai quyen  tu comboboxQuyen
        int MaChucVu;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxChucVu.getSelectedItem();
        MaChucVu = LoaiDuocChon.MaInt();

        try {
            if (txtTenNhanVien.getText().equals("") || txtGioiTinh.getText().equals("") || chucVu.equals("") || txtDiaChi.getText().equals("") || txtSoDT.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                StringBuffer sb = new StringBuffer();
                String sqlCheck = "SELECT MaNhanVien FROM dbo.NhanVien WHERE MaNhanVien = '" + txtMaNhanVien.getText() + "'";
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sqlCheck);
                if (rs.next()) {
                    sb.append("Nhân viên đã tồn tại!.Hãy làm mới và nhập lại");
                }
                if (sb.length() > 0) {
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    String sqlInsert = "INSERT INTO dbo.NhanVien (TenNhanVien,NgaySinh,GioiTinh,NgayVaoLam,ChucVu,DiaChi,SoDT) VALUES (?,?,?,?,?,?,?)";
                    try {
                        pst = conn.prepareStatement(sqlInsert);
                        pst.setString(1, txtTenNhanVien.getText());
                        pst.setString(2, txtNgaySinh.getText());
                        pst.setString(3, txtGioiTinh.getText());
                        pst.setString(4, txtNgayVaoLam.getText());
                        pst.setInt(5, MaChucVu);
                        pst.setString(6, txtDiaChi.getText());
                        pst.setString(7, txtSoDT.getText());
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

    private void buttonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSuaActionPerformed
        conn = cn.getConnection();

        String chucVu;
        chucVu = comboboxChucVu.getSelectedItem().toString(); // chuyen object --> String

        //Lay ma loai quyen  tu comboboxQuyen
        int MaChucVu;
        MyComboBox LoaiDuocChon = (MyComboBox) comboboxChucVu.getSelectedItem();
        MaChucVu = LoaiDuocChon.MaInt();

        try {
            if (txtTenNhanVien.getText().equals("") || txtGioiTinh.getText().equals("") || txtDiaChi.getText().equals("") || txtSoDT.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn cần nhập dữ liệu");
            } else {
                Statement st = conn.createStatement();
                String sqlUpdate = "UPDATE dbo.NhanVien SET TenNhanVien ='" + txtTenNhanVien.getText() + "',NgaySinh ='" + txtNgaySinh.getText() + "',GioiTinh='" + txtGioiTinh.getText() + "',NgayVaoLam ='" + txtNgayVaoLam.getText() + "',ChucVu='" + MaChucVu + "',DiaChi='" + txtDiaChi.getText() + "',SoDT='" + txtSoDT.getText() + "' WHERE MaNhanVien = '" + txtMaNhanVien.getText() + "'";
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

    private void buttonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLamMoiActionPerformed
        xoatrang();
    }//GEN-LAST:event_buttonLamMoiActionPerformed

    private void txtNgayVaoLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayVaoLamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayVaoLamActionPerformed

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
    private javax.swing.JButton buttonLamMoi;
    private javax.swing.JButton buttonSua;
    private javax.swing.JButton buttonThem;
    private javax.swing.JButton buttonXoa;
    private javax.swing.JComboBox<String> comboboxChucVu;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNgayVaoLam;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtTenNhanVien;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
