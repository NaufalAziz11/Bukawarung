import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import bukawarung.koneksiDB;
import java.util.Locale;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class penjualan extends javax.swing.JFrame {

java.util.Date tglsekarang = new java.util.Date();
    private final SimpleDateFormat smpdtfmt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    //diatas adalah pengaturan format penulisan, bisa diubah sesuai keinginan.
    private final String tanggal = smpdtfmt.format(tglsekarang);
    private com.mysql.jdbc.Statement stm;
    
private static String lev_user = "Kasir";
private static String id_user = "";
koneksiDB getcnn = new koneksiDB();
Connection _cnn;
PreparedStatement pst;
String sqlselect,sqlselect2,sqlinsert,sqlinsert2,sqldelete,sqlupdate;
private DefaultTableModel tbldata;
private DefaultTableModel tbldata2;
String vno,vid_member,vid_suplier,vtgl,vtotal,vdiskon,vjumlah,vstok,vid_barang,vbayar_tunai,vkembalian;
 ResultSet data1=null;
 
    /**
     * Creates ne w form penjualan
     */
    public penjualan() {
        initComponents();
        txttgl.setText(tanggal);
        txttanggal.setText(tanggal);
         clearform();
        settabel();
        settabel2();
        showdata();
        showdata2();
        tampildata();
        tampildata2();
        dataFromDataBaseToComboBox();
        txtuser.setText(Login.xiduser);
   
    }
    public void dataFromDataBaseToComboBox(){

try {
String query = "SELECT * FROM id_suplier";
Statement st = getcnn.getConnection().createStatement();
ResultSet rs = st.executeQuery(query);


rs.last();
int jumlahdata = rs.getRow();
rs.first();

} catch (SQLException e) {
}
}
   
    private void enableform(){
         txtno.setEnabled(false);
        txttgl.setEnabled(false);
        txtid_member.setEnabled(false);
        txtnama_member.setEnabled(false);
        txtkode_barang.setEnabled(false);
       txtnama_barang.setEnabled(false);
       txtharga.setEnabled(false);
       txtqty.setEnabled(false);
       txtdiskon.setEnabled(false);
       txttotal.setEnabled(false);
    }
     
    private void disbleform(){
        txtno.setEnabled(true);
        txttgl.setEnabled(true);
        txtid_member.setEnabled(true);
        txtnama_member.setEnabled(true);
        txtkode_barang.setEnabled(true);
       txtnama_barang.setEnabled(true);
       txtharga.setEnabled(true);
       txtqty.setEnabled(true);
       txtdiskon.setEnabled(true);
       txttotal.setEnabled(true);
    }
   
    private void clearform (){
        txtno.setText("");
        txtjml.setText("");
        txtid_member.setText("");
        txtnama_member.setText("");
        txtkode_barang.setText("");
       txtnama_barang.setText("");
       txtharga.setText("");
       txtqty.setText("");
       txtdiskon.setText("");
       txttotal.setText("");
        
       
    }
     void tampildata(){
        try{
            Statement stm;//buat stm
            stm=_cnn.createStatement();
            tbldata2.getDataVector().removeAllElements();
            data1=stm.executeQuery("select * from tb_penjualan");
            while(data1.next()){
                Object[] data={data1.getString("no_faktur_penjualan"),
                               data1.getString("id_user"),
                               data1.getString("id_member"),
                               data1.getString("tgl_transaksi"),
                               data1.getString("total"),
                               data1.getString("diskon"),
                               data1.getString("jumlah_barang"),
                              
                };
            tbldata2.addRow(data);
            }
            tbdata2.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbdata2.getColumnModel().getColumn(1).setPreferredWidth(90);
            tbdata2.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbdata2.getColumnModel().getColumn(3).setPreferredWidth(70);
            tbdata2.getColumnModel().getColumn(4).setPreferredWidth(50);
            tbdata2.getColumnModel().getColumn(5).setPreferredWidth(60);
            
            
            
       }catch (Exception e){
               e.printStackTrace();
               }
    }
          void tampildata2(){
        try{
            Statement stm;//buat stm
            stm=_cnn.createStatement();
            tbldata.getDataVector().removeAllElements();
            data1=stm.executeQuery("select * from tb_detail_penjualan");
            while(data1.next()){
                Object[] data={data1.getString("no_faktur_penjualan"),
                               data1.getString("id_barang"),
                               data1.getString("bayar"),
                               data1.getString("total_bayar"),
                               data1.getString("kembalian"),
                               data1.getString("diskon"),
                               data1.getString("jumlah_barang"),
                              
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
        String[] kolom1 = {"No","id user","id member","tanggal transaksi","Total","diskon","qty"};
        tbldata2 = new DefaultTableModel(null,kolom1){
            Class[] types = new Class[]{
                java.lang.String.class,
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
               int cola = tbdata2.getColumnCount();
               return (col < cola)? false : true;
           }
           
        };
        tbdata2.setModel(tbldata2);
        tbdata2.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbdata2.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbdata2.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbdata2.getColumnModel().getColumn(3).setPreferredWidth(200);
         tbdata2.getColumnModel().getColumn(4).setPreferredWidth(200);
          tbdata2.getColumnModel().getColumn(5).setPreferredWidth(50);
        tbdata2.getColumnModel().getColumn(6).setPreferredWidth(50);
    
    }
    private void cleartabel(){
        int row =tbldata2.getRowCount();
        for (int i = 0 ; i< row; i++){
            tbldata2.removeRow(0);
        }
    }
    private void showdata(){
        try{
            _cnn = null;
            _cnn = getcnn.getConnection();
            cleartabel();
            sqlselect = " select * from tb_penjualan order by no_faktur_penjualan asc ";
            Statement stat = _cnn.createStatement();
            ResultSet res = stat.executeQuery(sqlselect);
            while(res.next()){
                vno =  res.getString(1);
                id_user = res.getString(2);
                vid_member = res.getString(3);
                vtgl = res.getString(4);
                vtotal = res.getString(5);
                vdiskon = res.getString(6);
                vjumlah = res.getString(7);
                
                Object[] data = {vno,id_user,vid_member,vtgl,vtotal,vdiskon,vjumlah };
                tbldata2.addRow(data);
                
            }
           lblrecord1.setText("Record : "+tbdata2.getRowCount());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Eror Method showdata(): "+ex);
            
            
            
        }
        
    }
    private void settabel2(){
        String[] kolom1 = {"No","id barang","bayar","total bayar","kembalian","diskon","qty"};
        tbldata = new DefaultTableModel(null,kolom1){
            Class[] types = new Class[]{
                java.lang.String.class,
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
        tbdata.getColumnModel().getColumn(6).setPreferredWidth(50);
    
    }
    private void cleartabel2(){
        int row =tbldata.getRowCount();
        for (int i = 0 ; i< row; i++){
            tbldata.removeRow(0);
        }
    }
    private void showdata2(){
        try{
            _cnn = null;
            _cnn = getcnn.getConnection();
            cleartabel();
            sqlselect2 = " select * from tb_detail_penjualan order by no_faktur_penjualan asc ";
            Statement stat = _cnn.createStatement();
            ResultSet res = stat.executeQuery(sqlselect2);
            while(res.next()){
                vno =  res.getString(1);
                vid_barang = res.getString(2);
                vbayar_tunai = res.getString(3);
                vtotal = res.getString(4);
                vkembalian = res.getString(5);
                vdiskon = res.getString(6);
                vjumlah = res.getString(7);
                
                Object[] data = {vno,vid_barang,vbayar_tunai,vtotal,vkembalian,vdiskon,vjumlah };
                tbldata.addRow(data);
                
            }
           lblrecord.setText("Record : "+tbdata.getRowCount());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Eror Method showdata(): "+ex);
            
            
            
        }
    
    }
    
    private void aksisimpan(){
        vstok = txtstok1.getText();
        vid_barang = txtkode_barang.getText();
        vno = txtno.getText();
        id_user = txtuser.getText();
        vid_member = txtid_member.getText();
        vid_barang = txtkode_barang.getText();
        vtgl = txttgl.getText();
        vtotal = txttotal.getText();
        vdiskon = txtdiskon.getText();
        vjumlah = txtqty.getText();
        vstok = txtstok1.getText();
        vbayar_tunai = txtbt.getText();
        vkembalian = txtk.getText();
         sqlinsert = "insert into tb_penjualan values ('"+vno+"','"+id_user+"','"+vid_member+"','"+vtgl+"'"
                    +",'"+vtotal+"','"+vdiskon+"','"+vjumlah+"')";
          sqlinsert2 = "insert into tb_detail_penjualan values ('"+vno+"','"+vid_barang+"','"+vbayar_tunai+"','"+vtotal+"'"
                    +",'"+vkembalian+"','"+vdiskon+"','"+vjumlah+"')";
          sqlupdate = "update tb_barang set stok ='"+vstok+"' where id_barang ='"+vid_barang+"' ";
         
        try{
                
                _cnn = getcnn.getConnection();
                Statement stat = _cnn.createStatement();
                stat.executeUpdate(sqlinsert);
                stat.executeUpdate(sqlinsert2);
              pst = _cnn.prepareStatement(sqlupdate);
                pst.executeUpdate();
                   
                JOptionPane.showMessageDialog(this,"Data Berhasil diSimpan,lanjutkan untuk cetak struk ","Informasi",JOptionPane.INFORMATION_MESSAGE);
                clearform();showdata();new cetak_struk().show();
                 }catch(SQLException ex){
                JOptionPane.showMessageDialog(this,"Ada Kesalahan Dalam Input","Informasi",JOptionPane.INFORMATION_MESSAGE);
                 }
     
            
        
    
    
        }
    
    private void cek_kode(){
        try{
        Statement stm=_cnn.createStatement();
        data1=stm.executeQuery("select *from tb_barang where id_barang ="+"'"+txtkode_barang.getText()+"'");
        while (data1.next()){
            txtnama_barang.setText(data1.getString("nama_barang"));
            txtharga.setText(data1.getString("harga_jual"));
            txtstok.setText(data1.getString("stok"));
        }}catch(Exception e){
        e.printStackTrace();
        
        
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

        input = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdata = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtno = new javax.swing.JTextField();
        txtid_member = new javax.swing.JTextField();
        txtnama_member = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtkode_barang = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtnama_barang = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtdiskon = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txttgl = new javax.swing.JTextField();
        txtjml = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtstok = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtstok1 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblrecord = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtbt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtk = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        btnback1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdata2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txttanggal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblrecord1 = new javax.swing.JLabel();

        input.setUndecorated(true);
        input.setSize(new java.awt.Dimension(914, 766));

        jPanel7.setBackground(new java.awt.Color(153, 255, 153));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "kd.Barang", "nama barang", "qty", "harga", "total", "jumlah"
            }
        ));
        jScrollPane2.setViewportView(tbdata);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 500, 450));
        jPanel7.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 830, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-user-32 (1).png"))); // NOI18N
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 80));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 138, 0));
        jLabel35.setText("Form Transaksi Penjualan");
        jPanel7.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel3.setForeground(new java.awt.Color(153, 153, 153));

        txtno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnoActionPerformed(evt);
            }
        });

        txtid_member.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtid_memberCaretUpdate(evt);
            }
        });
        txtid_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid_memberActionPerformed(evt);
            }
        });

        txtnama_member.setEditable(false);
        txtnama_member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnama_memberActionPerformed(evt);
            }
        });

        jLabel8.setText("No.Transaksi");

        jLabel9.setText("Tanggal Transaksi");

        jLabel10.setText("ID Member");

        jLabel11.setText("Nama Member");

        jLabel12.setText("Kode Barang");

        txtkode_barang.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtkode_barangCaretUpdate(evt);
            }
        });
        txtkode_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkode_barangActionPerformed(evt);
            }
        });

        jLabel13.setText("Nama Barang");

        txtnama_barang.setEditable(false);
        txtnama_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnama_barangActionPerformed(evt);
            }
        });

        txtharga.setEditable(false);
        txtharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthargaActionPerformed(evt);
            }
        });

        jLabel14.setText("Harga");

        txtqty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtqtyActionPerformed(evt);
            }
        });

        jLabel15.setText("QTY");

        jLabel16.setText("Diskon");

        txtdiskon.setEditable(false);
        txtdiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiskonActionPerformed(evt);
            }
        });

        jLabel17.setText("%");

        txttgl.setEditable(false);
        txttgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttglActionPerformed(evt);
            }
        });

        txtjml.setEditable(false);
        txtjml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjmlActionPerformed(evt);
            }
        });

        jLabel32.setText("jumlah");

        txtstok.setEditable(false);
        txtstok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstokActionPerformed(evt);
            }
        });

        jLabel18.setText("Stok");

        txtstok1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtjml, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtstok)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(28, 28, 28)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(txtnama_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtkode_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(txtnama_member)
                            .addComponent(txtid_member)
                            .addComponent(txtno)
                            .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(txtstok1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtno, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(14, 14, 14)
                .addComponent(txttgl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(8, 8, 8)
                .addComponent(txtid_member, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnama_member, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtkode_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtnama_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtjml, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtstok, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtstok1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 320, 520));

        txttotal.setEditable(false);
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });
        jPanel7.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 130, 30));

        jLabel20.setText("Rp");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 570, -1, -1));

        jLabel21.setText("Total Bayar");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, -1, -1));

        lblrecord.setText("Record : 0");
        jPanel7.add(lblrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 50, -1));

        txtuser.setEditable(false);
        txtuser.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtuserCaretUpdate(evt);
            }
        });
        txtuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserActionPerformed(evt);
            }
        });
        jPanel7.add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 226, 31));

        jLabel22.setText("Id.user");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        txtbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbtActionPerformed(evt);
            }
        });
        jPanel7.add(txtbt, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 130, 30));

        jLabel24.setText("Rp");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 570, -1, -1));

        jLabel25.setText("bayar tunai");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 570, -1, -1));

        jLabel26.setText("Kembalian");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 620, -1, -1));

        jLabel27.setText("Rp");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 620, -1, -1));

        txtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkActionPerformed(evt);
            }
        });
        jPanel7.add(txtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 620, 130, 30));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 138, 0));
        jLabel23.setText("BukaWarung");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-delete-32.png"))); // NOI18N
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-subtract-32.png"))); // NOI18N

        btnback1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-back-button-64.png"))); // NOI18N
        btnback1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnback1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnback1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel23)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(btnback1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout inputLayout = new javax.swing.GroupLayout(input.getContentPane());
        input.getContentPane().setLayout(inputLayout);
        inputLayout.setHorizontalGroup(
            inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        inputLayout.setVerticalGroup(
            inputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inputLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 138, 0));
        jLabel3.setText("BukaWarung");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
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
                .addComponent(jLabel3)
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
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tbdata2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbdata2);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-plus-32.png"))); // NOI18N

        jLabel5.setText("Tambah");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidKeyReleased(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-32.png"))); // NOI18N
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        txttanggal.setEditable(false);
        txttanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttanggalActionPerformed(evt);
            }
        });

        jLabel6.setText("Tanggal");

        jPanel5.setBackground(new java.awt.Color(0, 138, 0));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pembelian");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-transaction-64.png"))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 138, 0));
        jLabel38.setText("Form Transaksi Penjualan");

        lblrecord1.setText("Record : 0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel28)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(34, 34, 34)
                                .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addGap(25, 25, 25))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(313, 313, 313)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblrecord1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel38))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jLabel19))))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(lblrecord1)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(798, 546));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        new menu_utama(lev_user).show();
        this.dispose();

    }//GEN-LAST:event_jLabel33MouseClicked

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        DefaultTableModel tabel = (DefaultTableModel)tbdata2.getModel();
        String sc = txtid.getText().toLowerCase();
            TableRowSorter<DefaultTableModel> tr =new TableRowSorter<DefaultTableModel>(tabel);
            tbdata2.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(sc));
        
    }//GEN-LAST:event_jLabel19MouseClicked

    private void txttanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttanggalActionPerformed

    private void txtnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnoActionPerformed

    private void txtid_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid_memberActionPerformed
        try{
            Statement stm=_cnn.createStatement();
            data1=stm.executeQuery("select *from tbmember where id_member ="+"'"+txtid_member.getText()+"'");
            while (data1.next()){
                txtnama_member.setText(data1.getString("nama_user"));
            }}catch(Exception e){
                e.printStackTrace();

            }

    }//GEN-LAST:event_txtid_memberActionPerformed

    private void txtnama_memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnama_memberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnama_memberActionPerformed

    private void txtkode_barangCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtkode_barangCaretUpdate
        cek_kode();
    }//GEN-LAST:event_txtkode_barangCaretUpdate

    private void txtkode_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkode_barangActionPerformed
        cek_kode();
    }//GEN-LAST:event_txtkode_barangActionPerformed

    private void txtnama_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnama_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnama_barangActionPerformed

    private void txthargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthargaActionPerformed

    private void txtqtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqtyActionPerformed
        int a = Integer.parseInt(txtqty.getText());
        int b = Integer.parseInt(txtharga.getText());
        int z = Integer.parseInt(txtqty.getText());
        int y = Integer.parseInt(txtstok.getText());
        
        int x = y-z;
        String stok= String.valueOf(x);
        txtstok1.setText(stok);
        int e = Integer.parseInt(txtqty.getText());
        int f = Integer.parseInt(txtharga.getText());
        int g = e* f;
        String jumlah= String.valueOf(g);
        txtjml.setText(jumlah);
         
         
        if (g <= 100000){
            txtdiskon.setText("0");
            if (g >= 100000 || g<=150000){
                txtdiskon.setText("10");
                if (g > 200000)
                txtdiskon.setText("15");
            }else {
                txtdiskon.setText("0");
            }
            int c = Integer.parseInt(txtdiskon.getText());
            String diskon= String.valueOf(c);
            int d = (g -((g * c)/100))  ;
            String hasil= String.valueOf(d);
            txttotal.setText(hasil);
            
            

        }
        
    }//GEN-LAST:event_txtqtyActionPerformed

    private void txtdiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiskonActionPerformed

    private void txttglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttglActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttglActionPerformed

    private void txtjmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjmlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjmlActionPerformed

    private void txtstokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstokActionPerformed

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void txtuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserActionPerformed
 try{
            Statement stm=_cnn.createStatement();
            data1=stm.executeQuery("select *from tb_user where id_user ="+"'"+txtuser.getText()+"'");

            if(data1.first()){
                JOptionPane.showMessageDialog(this,"Id ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                disbleform();

            }else{
                JOptionPane.showMessageDialog(this,"Id tidak ditemukan ","Informasi",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();

        }        

    }//GEN-LAST:event_txtuserActionPerformed

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked

    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        new penjualan().show();
        this.dispose();

    }//GEN-LAST:event_jLabel36MouseClicked

    private void btnback1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnback1MouseClicked
        new menu_utama(lev_user).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnback1MouseClicked

    private void txtbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbtActionPerformed

           int d = Integer.parseInt(txttotal.getText());
            
            int h = Integer.parseInt(txtbt.getText());
            int j =  h - d;
            String kembalian = String.valueOf(j);
            txtk.setText(kembalian);
        aksisimpan();
    }//GEN-LAST:event_txtbtActionPerformed

    private void txtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        input.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
       new pembelian().setVisible(true);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void txtidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyReleased
        DefaultTableModel tabel = (DefaultTableModel)tbdata2.getModel();
        String sc = txtid.getText().toLowerCase();
            TableRowSorter<DefaultTableModel> tr =new TableRowSorter<DefaultTableModel>(tabel);
            tbdata2.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter(sc));
    }//GEN-LAST:event_txtidKeyReleased

    private void txtuserCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtuserCaretUpdate

    }//GEN-LAST:event_txtuserCaretUpdate

    private void txtid_memberCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtid_memberCaretUpdate
         try{
        Statement stm=_cnn.createStatement();
        data1=stm.executeQuery("select *from tbmember where id_member ="+"'"+txtid_member.getText()+"'");
        while (data1.next()){
              txtnama_member.setText(data1.getString("nama_user"));
        }}catch(Exception e){
        e.printStackTrace();
        
        
    } 
    }//GEN-LAST:event_txtid_memberCaretUpdate

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
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(penjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new penjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnback1;
    private javax.swing.JDialog input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblrecord;
    private javax.swing.JLabel lblrecord1;
    private javax.swing.JTable tbdata;
    private javax.swing.JTable tbdata2;
    private javax.swing.JTextField txtbt;
    private javax.swing.JTextField txtdiskon;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtid_member;
    private javax.swing.JTextField txtjml;
    private javax.swing.JTextField txtk;
    private javax.swing.JTextField txtkode_barang;
    private javax.swing.JTextField txtnama_barang;
    private javax.swing.JTextField txtnama_member;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtstok;
    private javax.swing.JLabel txtstok1;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttgl;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
