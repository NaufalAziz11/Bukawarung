import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import bukawarung.koneksiDB;
import java.awt.Color;
import java.awt.HeadlessException;


public class olahdata_barang extends javax.swing.JFrame {
private static String lev_user = "Petugas Gudang";
koneksiDB getcnn = new koneksiDB();
Connection _cnn;
String sqlselect,sqlinsert,sqldelete,sqlupdate;
private DefaultTableModel tbldata;
String vid_barang,vid_kategori,vnama_barang,vharga_beli,vharga_jual,v_stok;
 ResultSet data1=null;
 PreparedStatement pst;
    /**
     * Creates new form olahdata_barang
     */
    public olahdata_barang() {
        initComponents();
        clearform();
        disableform();
        settabel();
        showdata();
        tampildata();
    }
    private void clearform (){
        cbokategori.setSelectedIndex(0);
        txtkode.setText("");
        txtNama.setText("");
        txthb.setText("");
        txthj.setText("");
        txtstok.setText("");
        btnsimpan.setText("Simpan");
       
        
    }
    private void disableform(){
         cbokategori.setEnabled(false);
         txtNama.setEditable(false);
         btnsimpan.setEnabled(false);
         btnhpus.setEnabled(false);
         btnedit_brg.setEnabled(false);
         txthb.setEditable(false);
        txthj.setEditable(false);
        txtstok.setEditable(false);
    }
    
    private void enableform(){
         cbokategori.setEnabled(true);
         txtkode.setEditable(true);
         txtNama.setEditable(true);
         txthb.setEditable(true);
        txthj.setEditable(true);
        txtstok.setEditable(true);
         btnsimpan.setEnabled(true);
    }
     void tampildata(){
        try{
            Statement stm;
            stm=_cnn.createStatement();
            tbldata.getDataVector().removeAllElements();
            data1=stm.executeQuery("select * from tb_barang");//
            while(data1.next()){
                Object[] data={data1.getString("id_barang"),
                               data1.getString("id_kategori"),
                               data1.getString("nama_barang"),
                               data1.getString("harga_beli"),
                               data1.getString("harga_jual"),
                               data1.getString("stok"),
                              
                };
            tbldata.addRow(data);
            }
            tbdata.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbdata.getColumnModel().getColumn(1).setPreferredWidth(90);
            tbdata.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbdata.getColumnModel().getColumn(3).setPreferredWidth(70);
            tbdata.getColumnModel().getColumn(4).setPreferredWidth(50);
            tbdata.getColumnModel().getColumn(5).setPreferredWidth(60);
            
       }catch (Exception e){
               e.printStackTrace();
               }
    }
    private void settabel(){
        String[] kolom1 = {"Kode Barang","Kategori","Nama Barang","Harga Beli","Harga Jual","Stok"};
        tbldata = new DefaultTableModel(null,kolom1){
            Class[] types = new Class[]{
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class
            
            };
           public Class getColumnClass(int columnIndex){
               return types [columnIndex];
           }
           public boolean isCellEditable(int row , int col){
               int cola = tbdata.getColumnCount();
               return (col < cola)? false : true;
           }
           
        };
        tbdata.setModel(tbldata);
        tbdata.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbdata.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbdata.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbdata.getColumnModel().getColumn(3).setPreferredWidth(200);
         tbdata.getColumnModel().getColumn(4).setPreferredWidth(200);
          tbdata.getColumnModel().getColumn(5).setPreferredWidth(50);
        
    
    }
    
    private void cleartabel(){
        int row =tbldata.getRowCount();
        for (int i = 0 ; i< row; i++){
            tbldata.removeRow(0);
        }
    }
    private void showdata(){
        try{
            _cnn = null;
            _cnn = getcnn.getConnection();
            cleartabel();
            sqlselect = " select * from tb_barang order by id_barang asc ";
            Statement stat = _cnn.createStatement();
            ResultSet res = stat.executeQuery(sqlselect);
            while(res.next()){
                vid_barang =  res.getString(1);
                vid_kategori = res.getString(2);
                vnama_barang = res.getString(3);
                vharga_beli = res.getString(4);
                vharga_jual = res.getString(5);
                v_stok = res.getString(6);
                Object[] data = {vid_barang,vid_kategori,vnama_barang,vharga_beli,vharga_jual,v_stok };
                tbldata.addRow(data);
                
            }
           lblrecord.setText("Record : "+tbdata.getRowCount());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Eror Method showdata(): "+ex);
            
            
            
        }
        
    }
    private void aksisimpan(){
        vid_barang = txtkode.getText();
        vid_kategori = cbokategori.getSelectedItem().toString();
        vnama_barang = txtNama.getText();
        vharga_beli = txthb.getText();
        vharga_jual = txthj.getText();
        v_stok = txtstok.getText();
        if(btnsimpan.getText().equals("Simpan")){
            sqlinsert = "insert into tb_barang values ('"+vid_barang+"','"+vid_kategori+"','"+vnama_barang+"'"
                    +",'"+vharga_beli+"','"+vharga_jual+"','"+v_stok+"')";
        }else{
            sqlinsert = "update tb_barang set nama_barang ='"+vnama_barang+"', id_kategori='"+vid_kategori+"' "
                    +" where id_barang ='"+vid_barang+"'";
        }
        try{
                
                _cnn = getcnn.getConnection();
                Statement stat = _cnn.createStatement();
                stat.executeUpdate(sqlinsert);
                JOptionPane.showMessageDialog(this,"Data Berhasil DiSimpan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                clearform();
                 }catch(SQLException ex){
                JOptionPane.showMessageDialog(this,"Kode barang sudah ada ","Informasi",JOptionPane.INFORMATION_MESSAGE);
                 }
     
            
        
    }
    private void aksiupdate(){
        vid_barang = txtkode.getText();
        vid_kategori = cbokategori.getSelectedItem().toString();
        vnama_barang = txtNama.getText();
        vharga_beli = txthb.getText();
        vharga_jual = txthj.getText();
        v_stok = txtstok.getText();
        
          sqlupdate = "update tb_barang set id_barang ='"+vid_barang+"',id_kategori ='"+vid_kategori+"',nama_barang ='"+vnama_barang+"',harga_beli ='"+vharga_beli+"',harga_jual ='"+vharga_jual+"',stok ='"+v_stok+"' where id_barang ='"+vid_barang+"' ";
         
        try{
                
                _cnn = getcnn.getConnection();
                Statement stat = _cnn.createStatement();
                
              pst = _cnn.prepareStatement(sqlupdate);
                pst.executeUpdate();
                   
                JOptionPane.showMessageDialog(this,"Data Berhasil diubah ","Informasi",JOptionPane.INFORMATION_MESSAGE);
                clearform();
                 }catch(SQLException ex){
                JOptionPane.showMessageDialog(this,"Ada Kesalahan Dalam Input","Informasi",JOptionPane.INFORMATION_MESSAGE);
                 }
    }
    private void aksihapus(){
        int jawab = JOptionPane.showConfirmDialog(this,"Anda yakin akan menghapus data ini ? ID. Barang : "+vid_barang,
                "Konfirmasi",JOptionPane.YES_NO_OPTION);
        if(jawab == JOptionPane.YES_NO_OPTION){
            try{
                _cnn = null;
                _cnn = getcnn.getConnection();
                sqldelete = "delete from tb_barang where id_barang ='"+vid_barang+"'";
                Statement stat = _cnn.createStatement();
                stat.executeUpdate(sqldelete);
                JOptionPane.showMessageDialog(this,"Data Berhasil Dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                clearform();disableform();showdata();
                
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(this,"Eror Method aksihapus():"+ex);
            }
            
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        cbokategori = new javax.swing.JComboBox<>();
        txtstok = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnbr_baru = new javax.swing.JPanel();
        btnbb = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtkode = new javax.swing.JTextField();
        txthb = new javax.swing.JTextField();
        txthj = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        btnedit_brg = new javax.swing.JPanel();
        btnedit = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JLabel();
        btnsrc = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdata = new javax.swing.JTable();
        btnhpus = new javax.swing.JPanel();
        btnhapus = new javax.swing.JLabel();
        lblrecord = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 138, 0));
        jLabel2.setText("BukaWarung");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-delete-32.png"))); // NOI18N
        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-subtract-32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-package-64.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 60));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 138, 0));
        jLabel35.setText("Form Olah Data Barang");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 520, 10));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nama Barang");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 90, -1));

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });
        jPanel2.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 190, 30));

        cbokategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Kategori Barang --", "1001", "1002", "1003", "1004", " ", " " }));
        jPanel2.add(cbokategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 200, 30));

        txtstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstokActionPerformed(evt);
            }
        });
        jPanel2.add(txtstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 60, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Stok");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 90, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 520, 10));

        btnbr_baru.setBackground(new java.awt.Color(0, 138, 0));
        btnbr_baru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbr_baruMouseClicked(evt);
            }
        });

        btnbb.setBackground(new java.awt.Color(255, 255, 255));
        btnbb.setForeground(new java.awt.Color(255, 255, 255));
        btnbb.setText("Barang Baru");
        btnbb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbbMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnbr_baruLayout = new javax.swing.GroupLayout(btnbr_baru);
        btnbr_baru.setLayout(btnbr_baruLayout);
        btnbr_baruLayout.setHorizontalGroup(
            btnbr_baruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnbr_baruLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnbb)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        btnbr_baruLayout.setVerticalGroup(
            btnbr_baruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnbb, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(btnbr_baru, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 110, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Kode Barang");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 90, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Harga Beli");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 90, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Harga Jual");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 90, -1));

        txtkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkodeActionPerformed(evt);
            }
        });
        jPanel2.add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 190, 30));

        txthb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthbActionPerformed(evt);
            }
        });
        jPanel2.add(txthb, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 190, 30));

        txthj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthjActionPerformed(evt);
            }
        });
        jPanel2.add(txthj, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 190, 30));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 520, 10));

        btnedit_brg.setBackground(new java.awt.Color(0, 138, 0));
        btnedit_brg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnedit_brg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnedit_brgMouseClicked(evt);
            }
        });

        btnedit.setBackground(new java.awt.Color(255, 255, 255));
        btnedit.setForeground(new java.awt.Color(255, 255, 255));
        btnedit.setText("Ubah");

        javax.swing.GroupLayout btnedit_brgLayout = new javax.swing.GroupLayout(btnedit_brg);
        btnedit_brg.setLayout(btnedit_brgLayout);
        btnedit_brgLayout.setHorizontalGroup(
            btnedit_brgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnedit_brgLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnedit)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        btnedit_brgLayout.setVerticalGroup(
            btnedit_brgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(btnedit_brg, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 130, -1));

        jPanel6.setBackground(new java.awt.Color(0, 138, 0));

        btnsimpan.setBackground(new java.awt.Color(255, 255, 255));
        btnsimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnsimpan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnsimpan.setText("Simpan");
        btnsimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsimpanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 450, -1, -1));

        btnsrc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-32.png"))); // NOI18N
        btnsrc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsrcMouseClicked(evt);
            }
        });
        jPanel2.add(btnsrc, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 40, -1));

        jTabbedPane1.addTab("Data Barang", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tbdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Kategori", "Nama Barang", "Harga Beli", "Harga Jual", "Stok"
            }
        ));
        tbdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbdata);
        if (tbdata.getColumnModel().getColumnCount() > 0) {
            tbdata.getColumnModel().getColumn(0).setResizable(false);
            tbdata.getColumnModel().getColumn(0).setPreferredWidth(50);
            tbdata.getColumnModel().getColumn(1).setResizable(false);
            tbdata.getColumnModel().getColumn(2).setResizable(false);
            tbdata.getColumnModel().getColumn(3).setResizable(false);
            tbdata.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbdata.getColumnModel().getColumn(4).setResizable(false);
            tbdata.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbdata.getColumnModel().getColumn(5).setResizable(false);
            tbdata.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        btnhpus.setBackground(new java.awt.Color(0, 138, 0));

        btnhapus.setBackground(new java.awt.Color(255, 255, 255));
        btnhapus.setForeground(new java.awt.Color(255, 255, 255));
        btnhapus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnhapus.setText("Hapus");
        btnhapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnhapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnhpusLayout = new javax.swing.GroupLayout(btnhpus);
        btnhpus.setLayout(btnhpusLayout);
        btnhpusLayout.setHorizontalGroup(
            btnhpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnhpusLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnhapus, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        btnhpusLayout.setVerticalGroup(
            btnhpusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnhapus, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        lblrecord.setText("Record : 0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(263, 263, 263)
                                .addComponent(btnhpus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblrecord)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblrecord)
                .addGap(32, 32, 32)
                .addComponent(btnhpus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(335, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tabel Data Barang", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(705, 647));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstokActionPerformed

    private void txtkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkodeActionPerformed

    private void txthbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthbActionPerformed

    private void txthjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthjActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnbbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbbMouseClicked
      enableform();
      clearform();
      cbokategori.requestFocus(true);
    }//GEN-LAST:event_btnbbMouseClicked

    private void btnsimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsimpanMouseClicked
        if(txtkode.getText().equals("")){
          JOptionPane.showMessageDialog(this, "Kode barang belum diisi ","informasi",
                  JOptionPane.INFORMATION_MESSAGE);
      }else if(txtNama.getText().equals("")){
          JOptionPane.showMessageDialog(this, "Nama Barang belum diisi ","informasi",
                  JOptionPane.INFORMATION_MESSAGE);     
          
      }else if(cbokategori.getSelectedIndex()<=0){
          JOptionPane.showMessageDialog(this, "Kategori belum dipilih ","informasi",
                  JOptionPane.INFORMATION_MESSAGE);
      }else{
          aksisimpan();
          showdata();
      
    }                                         
    }//GEN-LAST:event_btnsimpanMouseClicked

    private void tbdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdataMouseClicked
        if(evt.getClickCount()==2){
            int brs = tbdata.getSelectedRow();
            vid_barang = tbdata.getValueAt(brs, 0).toString();
            vid_kategori = tbdata.getValueAt(brs, 1).toString();
            vnama_barang = tbdata.getValueAt(brs, 2).toString();
            vharga_beli = tbdata.getValueAt(brs, 3).toString();
            vharga_jual = tbdata.getValueAt(brs, 4).toString();
            v_stok = tbdata.getValueAt(brs, 5).toString();
            
            txtkode.setText(vid_barang);
            cbokategori.setSelectedItem(vid_kategori);
            txtNama.setText(vnama_barang);
            txthb.setText(vharga_beli);
            txthj.setText(vharga_jual);
            txtstok.setText(v_stok);
            enableform();
            txtkode.setEnabled(false);
            btnhapus.setEnabled(true);
            
        }
    }//GEN-LAST:event_tbdataMouseClicked

    private void btnhapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhapusMouseClicked
         if(txtkode.getText().equals("")){
           JOptionPane.showMessageDialog(this, "Anda belum memilih data yang akan dihapus","Informasi",
                   JOptionPane.INFORMATION_MESSAGE);
           
           
       }else{
           aksihapus();
       }
                     
    }//GEN-LAST:event_btnhapusMouseClicked

    private void btnsrcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsrcMouseClicked
       txthj.setEditable(false);
       txtNama.setEditable(false);
       cbokategori.setEnabled(false);
       btnedit_brg.setEnabled(true);
       
        try{
        Statement stm=_cnn.createStatement();
        data1=stm.executeQuery("select *from tb_barang where id_barang ="+"'"+txtkode.getText()+"'");
        while (data1.next()){
            cbokategori.setSelectedItem(data1.getString("id_kategori"));
            txtNama.setText(data1.getString("nama_barang"));
            txthb.setText(data1.getString("harga_beli"));
            txthj.setText(data1.getString("harga_jual"));
            txtstok.setText(data1.getString("stok"));
        
        }}catch(Exception e){
        e.printStackTrace();
        
        }
    }//GEN-LAST:event_btnsrcMouseClicked

    private void btnbr_baruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbr_baruMouseClicked
      enableform();
      clearform();
      cbokategori.requestFocus(true);
      txtkode.setEnabled(true);
    }//GEN-LAST:event_btnbr_baruMouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        new menu_utama(lev_user).show();
        this.dispose();                     
    }//GEN-LAST:event_jLabel33MouseClicked

    private void btnedit_brgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnedit_brgMouseClicked
       aksiupdate();
       showdata();
    }//GEN-LAST:event_btnedit_brgMouseClicked
 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(olahdata_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(olahdata_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(olahdata_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(olahdata_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new olahdata_barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnbb;
    private javax.swing.JPanel btnbr_baru;
    private javax.swing.JLabel btnedit;
    private javax.swing.JPanel btnedit_brg;
    private javax.swing.JLabel btnhapus;
    private javax.swing.JPanel btnhpus;
    private javax.swing.JLabel btnsimpan;
    private javax.swing.JLabel btnsrc;
    private javax.swing.JComboBox<String> cbokategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblrecord;
    private javax.swing.JTable tbdata;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txthb;
    private javax.swing.JTextField txthj;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtstok;
    // End of variables declaration//GEN-END:variables
}
