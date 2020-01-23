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
import javax.swing.table.TableRowSorter;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import bukawarung.koneksiDB;
import java.util.Locale;

public class pembelian extends javax.swing.JFrame {

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
String vno,vid_member,vid_suplier,vtgl,vtotal,vdiskon,vjumlah,vstok,vid_barang,vbayar_tunai,vkembalian,vharga_jual,vharga_beli;
 ResultSet data1=null;
 
    /**
     * Creates new form penjualan
     */
    public pembelian() {
        initComponents();
        txttgl2.setText(tanggal);
        txttanggal.setText(tanggal);
        settabel();
        settabel2();
        showdata();
        showdata2();
        tampildata();
        tampildata2();
        dataFromDataBaseToComboBox();
        txtuser1.setText(Login.xiduser);
   
    }
    public void dataFromDataBaseToComboBox(){

try {
String query = "SELECT * FROM id_suplier";
Statement st = getcnn.getConnection().createStatement();
ResultSet rs = st.executeQuery(query);
while (rs.next()) {
cbosuplier.addItem(rs.getString("id_suplier"));
}
rs.last();
int jumlahdata = rs.getRow();
rs.first();

} catch (SQLException e) {
}
}
   
    private void enableform(){
         txtno2.setEnabled(false);
        txttgl2.setEnabled(false);
        cbosuplier.setEnabled(false);
        txtkode2.setEnabled(false);
       txtnama2.setEnabled(false);
       txthb2.setEnabled(false);
       txthj2.setEnabled(false);
       txtjml2.setEnabled(false);
       txtqty2.setEnabled(false);
    }
     
    private void disableform(){
        txtno2.setEnabled(true);
        txttgl2.setEnabled(true);
        cbosuplier.setEnabled(true);
        txtkode2.setEnabled(true);
       txtnama2.setEnabled(true);
       txthb2.setEnabled(true);
       txthj2.setEnabled(true);
       txtjml2.setEnabled(true);
       txtqty2.setEnabled(true);
    }
   
    private void  clearform2(){
      txtno2.setText("");
        cbosuplier.setSelectedItem("");
        txtkode2.setText("");
       txtnama2.setText("");
       txthb2.setText("");
       txthj2.setText("");
       txtqty2.setText("");
       txtdiskon2.setText("");
       txttotal2.setText(""); 
       
    }
     void tampildata(){
        try{
            Statement stm;
            stm=_cnn.createStatement();
            tbldata2.getDataVector().removeAllElements();
            data1=stm.executeQuery("select * from tb_pembelian");
            while(data1.next()){
                Object[] data={data1.getString("no_faktur_pembelian"),
                               data1.getString("id_suplier"),
                               data1.getString("id_user"),
                               data1.getString("tgl_transaksi"),
                               data1.getString("total"),
                               data1.getString("diskon"),
                              
                };
            tbldata2.addRow(data);
            }
            tbdata2.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbdata2.getColumnModel().getColumn(1).setPreferredWidth(90);
            tbdata2.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbdata2.getColumnModel().getColumn(3).setPreferredWidth(70);
            tbdata2.getColumnModel().getColumn(4).setPreferredWidth(50);
            tbdata2.getColumnModel().getColumn(5).setPreferredWidth(50);
            
            
            
       }catch (Exception e){
               e.printStackTrace();
               }
    }
          void tampildata2(){
        try{
            Statement stm;
            stm=_cnn.createStatement();
            tbldata.getDataVector().removeAllElements();
            data1=stm.executeQuery("select * from tb_detail_pembelian");
            while(data1.next()){
                Object[] data={data1.getString("no_faktur_pembelian"),
                               data1.getString("id_barang"),
                               data1.getString("harga_beli"),
                               data1.getString("harga_jual"),
                               data1.getString("total_bayar"),
                               data1.getString("jumlah_barang"),
                              
                };
            tbldata.addRow(data);
            }
            tbdata3.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbdata3.getColumnModel().getColumn(1).setPreferredWidth(90);
            tbdata3.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbdata3.getColumnModel().getColumn(3).setPreferredWidth(70);
            tbdata3.getColumnModel().getColumn(4).setPreferredWidth(50);
            tbdata3.getColumnModel().getColumn(5).setPreferredWidth(50);
            
            
            
       }catch (Exception e){
               e.printStackTrace();
               }
    }

    private void settabel(){
        String[] kolom1 = {"No","id suplier","id user","tanggal","total bayar","diskon"};
        tbldata2 = new DefaultTableModel(null,kolom1){
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
            sqlselect = " select * from tb_pembelian order by no_faktur_pembelian asc ";
            Statement stat = _cnn.createStatement();
            ResultSet res = stat.executeQuery(sqlselect);
            while(res.next()){
                vno =  res.getString(1);
                vid_suplier = res.getString(2);
                id_user = res.getString(3);
                vtgl = res.getString(4);
                vtotal = res.getString(5);
                vdiskon = res.getString(6);
                
                Object[] data = {vno,vid_suplier,id_user,vtgl,vtotal,vdiskon};
                tbldata2.addRow(data);
                
            }
           lblrecord2.setText("Record : "+tbdata2.getRowCount());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Eror Method showdata(): "+ex);
            
            
            
        }
        
    }
    private void settabel2(){
        String[] kolom1 = {"No","id barang","harga beli","harga jual","total bayar","qty"};
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
               int cola = tbdata3.getColumnCount();
               return (col < cola)? false : true;
           }
           
        };
        tbdata3.setModel(tbldata);
        tbdata3.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbdata3.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbdata3.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbdata3.getColumnModel().getColumn(3).setPreferredWidth(200);
         tbdata3.getColumnModel().getColumn(4).setPreferredWidth(200);
          tbdata3.getColumnModel().getColumn(5).setPreferredWidth(50);
       
    
    }
    private void cleartabel2(){
        int row =tbldata2.getRowCount();
        for (int i = 0 ; i< row; i++){
            tbldata2.removeRow(0);
        }
    }
    private void showdata2(){
        try{
            _cnn = null;
            _cnn = getcnn.getConnection();
            cleartabel();
            sqlselect2 = " select * from tb_detail_pembelian order by no_faktur_pembelian asc ";
            Statement stat = _cnn.createStatement();
            ResultSet res = stat.executeQuery(sqlselect2);
            while(res.next()){
                vno =  res.getString(1);
                vid_barang = res.getString(2);
                vharga_beli = res.getString(3);
                vharga_jual = res.getString(4);
                vtotal = res.getString(5);
                vjumlah = res.getString(6);
                
                Object[] data = {vno,vid_barang,vharga_beli,vharga_jual,vtotal,vjumlah };
                tbldata2.addRow(data);
                
            }
           lblrecord.setText("Record : "+tbdata2.getRowCount());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Eror Method showdata(): "+ex);
            
            
            
        }
    
    }
    
    private void aksisimpan(){
      
        vid_barang = txtkode2.getText();
        vno = txtno2.getText();
        id_user = txtuser1.getText();
        vid_suplier = cbosuplier.getSelectedItem().toString();
        vtgl = txttgl2.getText();
        vharga_beli = txthb2.getText();
         vharga_jual = txthj2.getText();
        vtotal = txttotal2.getText();
        vdiskon = txtdiskon2.getText();
        vjumlah = txtqty2.getText();
        vstok = txtstok3.getText();
        
         sqlinsert = "insert into tb_pembelian values ('"+vno+"','"+vid_suplier+"','"+id_user+"','"+vtgl+"'"
                    +",'"+vtotal+"','"+vdiskon+"')";
          sqlinsert2 = "insert into tb_detail_pembelian values ('"+vno+"','"+vid_barang+"','"+vharga_beli+"','"+vharga_jual+"'"
                    +",'"+vtotal+"','"+vjumlah+"')"; 
          sqlupdate = "update tb_barang set stok ='"+vstok+"' where id_barang ='"+vid_barang+"' ";
         
        try{
                
                _cnn = getcnn.getConnection();
                Statement stat = _cnn.createStatement();
                stat.executeUpdate(sqlinsert);
                stat.executeUpdate(sqlinsert2);
              pst = _cnn.prepareStatement(sqlupdate);
                pst.executeUpdate();
                   
                JOptionPane.showMessageDialog(this,"Data Berhasil diSimpan,lanjutkan untuk cetak struk ","Informasi",JOptionPane.INFORMATION_MESSAGE);
                clearform2();showdata();new cetak_struk().show();
                 }catch(SQLException ex){
                JOptionPane.showMessageDialog(this,"Ada Kesalahan Dalam Input","Informasi",JOptionPane.INFORMATION_MESSAGE);
                 }
     
            
        
    
    
        }
    
    private void cek_kode(){
        try{
        Statement stm=_cnn.createStatement();
        data1=stm.executeQuery("select *from tb_barang where id_barang ="+"'"+txtkode2.getText()+"'");
        while (data1.next()){
            txtnama2.setText(data1.getString("nama_barang"));
            txthj2.setText(data1.getString("harga_jual"));
            txtstok2.setText(data1.getString("stok"));
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

        input2 = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdata3 = new javax.swing.JTable();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtno2 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtkode2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtnama2 = new javax.swing.JTextField();
        txthj2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtqty2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtdiskon2 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        cbosuplier = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        txthb2 = new javax.swing.JTextField();
        txttgl2 = new javax.swing.JTextField();
        txtjml2 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtstok2 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtstok3 = new javax.swing.JLabel();
        txttotal2 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblrecord2 = new javax.swing.JLabel();
        txtuser1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        btnback2 = new javax.swing.JLabel();
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
        jLabel32 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblrecord = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        input2.setUndecorated(true);
        input2.setSize(new java.awt.Dimension(914, 766));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbdata3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "kd.Barang", "nama barang", "qty", "harga", "total"
            }
        ));
        jScrollPane2.setViewportView(tbdata3);

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 500, 450));
        jPanel8.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 830, 20));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-transaction-64.png"))); // NOI18N
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 80));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 138, 0));
        jLabel36.setText("Form Transaksi Pembelian");
        jPanel8.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(153, 153, 153)));
        jPanel3.setForeground(new java.awt.Color(153, 153, 153));

        txtno2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtno2ActionPerformed(evt);
            }
        });

        jLabel18.setText("No.Transaksi");

        jLabel28.setText("Tanggal Transaksi");

        jLabel20.setText("Suplier");

        jLabel21.setText("Kode Barang");

        txtkode2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkode2ActionPerformed(evt);
            }
        });

        jLabel22.setText("Nama Barang");

        txtnama2.setEditable(false);
        txtnama2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnama2ActionPerformed(evt);
            }
        });

        txthj2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthj2ActionPerformed(evt);
            }
        });

        jLabel23.setText("Harga Jual");

        txtqty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtqty2ActionPerformed(evt);
            }
        });

        jLabel24.setText("QTY");

        jLabel25.setText("Diskon");

        txtdiskon2.setEditable(false);
        txtdiskon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiskon2ActionPerformed(evt);
            }
        });

        jLabel26.setText("%");

        cbosuplier.setEditable(true);
        cbosuplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbosuplierActionPerformed(evt);
            }
        });

        jLabel27.setText("Harga Beli");

        txthb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txthb2ActionPerformed(evt);
            }
        });

        txttgl2.setEditable(false);
        txttgl2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttgl2ActionPerformed(evt);
            }
        });

        txtjml2.setEditable(false);
        txtjml2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjml2ActionPerformed(evt);
            }
        });

        jLabel37.setText("jumlah");

        txtstok2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstok2ActionPerformed(evt);
            }
        });

        jLabel31.setText("Stok");

        txtstok3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(txthb2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(txtnama2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(txtkode2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txthj2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addComponent(txtjml2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtqty2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstok2)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtdiskon2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(txtstok3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttgl2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel20)
                        .addComponent(jLabel28)
                        .addComponent(jLabel18)
                        .addComponent(txtno2)
                        .addComponent(cbosuplier, 0, 226, Short.MAX_VALUE)))
                .addGap(46, 46, 46))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtno2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addGap(7, 7, 7)
                .addComponent(txttgl2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbosuplier, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtkode2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnama2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txthb2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthj2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtjml2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel31)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtqty2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(txtstok2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txtstok3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdiskon2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))))
                .addContainerGap())
        );

        jPanel8.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 320, 520));

        txttotal2.setEditable(false);
        txttotal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotal2ActionPerformed(evt);
            }
        });
        jPanel8.add(txttotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(373, 571, 130, 30));

        jLabel29.setText("Rp");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 570, -1, -1));

        jLabel30.setText("Total Bayar");
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 570, -1, -1));

        lblrecord2.setText("Record : 0");
        jPanel8.add(lblrecord2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, -1, -1));

        txtuser1.setEditable(false);
        txtuser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuser1ActionPerformed(evt);
            }
        });
        jPanel8.add(txtuser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 226, 31));

        jLabel38.setText("Id.user");
        jPanel8.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 138, 0));
        jLabel2.setText("BukaWarung");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-delete-32.png"))); // NOI18N
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-subtract-32.png"))); // NOI18N

        btnback2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-back-button-64.png"))); // NOI18N
        btnback2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnback2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnback2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(btnback2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout input2Layout = new javax.swing.GroupLayout(input2.getContentPane());
        input2.getContentPane().setLayout(input2Layout);
        input2Layout.setHorizontalGroup(
            input2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(input2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        input2Layout.setVerticalGroup(
            input2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(input2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
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

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-transaction-64.png"))); // NOI18N

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 138, 0));
        jLabel41.setText("Form Transaksi Pembelian");

        lblrecord.setText("Record : 0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(34, 34, 34)
                        .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblrecord)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel41)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(9, 9, 9)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(jLabel19)
                    .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(lblrecord)
                .addContainerGap())
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        pack();
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

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        input2.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void txtno2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtno2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtno2ActionPerformed

    private void txtkode2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkode2ActionPerformed
        try{
            Statement stm=_cnn.createStatement();
            data1=stm.executeQuery("select *from tb_barang where id_barang ="+"'"+txtkode2.getText()+"'");
            while (data1.next()){
                txtnama2.setText(data1.getString("nama_barang"));
                txthj2.setText(data1.getString("harga_jual"));
                txtstok2.setText(data1.getString("stok"));
            }}catch(Exception e){
                e.printStackTrace();

            }
    }//GEN-LAST:event_txtkode2ActionPerformed

    private void txtnama2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnama2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnama2ActionPerformed

    private void txthj2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthj2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthj2ActionPerformed

    private void txtqty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtqty2ActionPerformed
        int a = Integer.parseInt(txtqty2.getText());
        int b = Integer.parseInt(txthb2.getText());
        int z = Integer.parseInt(txtqty2.getText());
        int y = Integer.parseInt(txtstok2.getText());

        int x = y+z;
        String stok= String.valueOf(x);
        txtstok3.setText(stok);
        int e = Integer.parseInt(txtqty2.getText());
        int f = Integer.parseInt(txthb2.getText());
        int g = e* f;
        String jumlah= String.valueOf(g);
        txtjml2.setText(jumlah);

        if (g <= 100000){
            txtdiskon2.setText("0");
            if (g >= 100000 || g<=150000){
                txtdiskon2.setText("15");
                if (g > 200000)
                txtdiskon2.setText("20");
            }else {
                txtdiskon2.setText("0");
            }
            int c = Integer.parseInt(txtdiskon2.getText());
            String diskon= String.valueOf(c);
            int d = (g -((g * c)/100))  ;
            String hasil= String.valueOf(d);
            txttotal2.setText(hasil);
            aksisimpan();

        }
    }//GEN-LAST:event_txtqty2ActionPerformed

    private void txtdiskon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiskon2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiskon2ActionPerformed

    private void cbosuplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbosuplierActionPerformed

    }//GEN-LAST:event_cbosuplierActionPerformed

    private void txthb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txthb2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txthb2ActionPerformed

    private void txttgl2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttgl2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttgl2ActionPerformed

    private void txtjml2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjml2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjml2ActionPerformed

    private void txtstok2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstok2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstok2ActionPerformed

    private void txttotal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotal2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotal2ActionPerformed

    private void txtuser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuser1ActionPerformed
        try{
            Statement stm=_cnn.createStatement();
            data1=stm.executeQuery("select *from tb_user where id_user ="+"'"+txtuser1.getText()+"'");

            if(data1.first()){
                JOptionPane.showMessageDialog(this,"Id ditemukan","Informasi",JOptionPane.INFORMATION_MESSAGE);
                disableform();

            }else{
                JOptionPane.showMessageDialog(this,"Id tidak ditemukan ","Informasi",JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();

        }

    }//GEN-LAST:event_txtuser1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        new penjualan().show();
        this.dispose();

    }//GEN-LAST:event_jLabel39MouseClicked

    private void btnback2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnback2MouseClicked
        new menu_utama(lev_user).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnback2MouseClicked

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
    private javax.swing.JLabel btnback2;
    private javax.swing.JComboBox<String> cbosuplier;
    private javax.swing.JDialog input2;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblrecord;
    private javax.swing.JLabel lblrecord2;
    private javax.swing.JTable tbdata2;
    private javax.swing.JTable tbdata3;
    private javax.swing.JTextField txtdiskon2;
    private javax.swing.JTextField txthb2;
    private javax.swing.JTextField txthj2;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtjml2;
    private javax.swing.JTextField txtkode2;
    private javax.swing.JTextField txtnama2;
    private javax.swing.JTextField txtno2;
    private javax.swing.JTextField txtqty2;
    private javax.swing.JTextField txtstok2;
    private javax.swing.JLabel txtstok3;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttgl2;
    private javax.swing.JTextField txttotal2;
    private javax.swing.JTextField txtuser1;
    // End of variables declaration//GEN-END:variables
}
