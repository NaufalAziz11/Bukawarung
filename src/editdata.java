/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import bukawarung.koneksiDB;
import java.awt.Color;
/**
 *
 * @author USER
 */
public class editdata extends javax.swing.JFrame {
private static String lev_user = "Pemilik Toko";
private static String id_user = "";

public String JK="";
koneksiDB getcnn = new koneksiDB();
Connection _cnn;
ResultSet data1;
PreparedStatement pst;
String sqlselect,sqlinsert,sqldelete,sqlupdate;
String vid,vnama,vjk,vtgl,v_alamat,v_telpon,vpass,v_lev;
private DefaultTableModel tbldata;
    /**
     * Creates new form editdata
     */
    public editdata() {
        initComponents();
        settabel();
        showdata();
        tampildata();
        disabled();
    }
    private void clearform (){
        txtno.setText("");
        txtid1.setText("");
        txtpass.setText("");
        txtalamat.setText("");
        txtNama.setText("");
        
    } 
    private void disabled(){
        btnsimpan.setVisible(false);
        btnsimpan1.setVisible(false);
        
    }
    
       
void tampildata(){
        try{
            Statement stm;//buat stm
            stm=_cnn.createStatement();
            tbldata.getDataVector().removeAllElements();
            data1=stm.executeQuery("select * from tb_user");
            while(data1.next()){
                Object[] data={data1.getString("id_user"),
                               data1.getString("nama_user"),
                               data1.getString("alamat"),
                               data1.getString("no_telpon"),
                               data1.getString("password"),
                               
                };
            tbldata.addRow(data);
            }
            tbdata.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbdata.getColumnModel().getColumn(1).setPreferredWidth(90);
            tbdata.getColumnModel().getColumn(2).setPreferredWidth(20);
            tbdata.getColumnModel().getColumn(3).setPreferredWidth(70);
            tbdata.getColumnModel().getColumn(4).setPreferredWidth(50);
            
            
            
       }catch (Exception e){
               e.printStackTrace();
               }
    }
    private void settabel(){
        String[] kolom1 = {"Id user","nama user","alamat","no telpon","password"};
        tbldata = new DefaultTableModel(null,kolom1){
            Class[] types = new Class[]{
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
        tbdata.getColumnModel().getColumn(2).setPreferredWidth(30);
        tbdata.getColumnModel().getColumn(3).setPreferredWidth(200);
         tbdata.getColumnModel().getColumn(4).setPreferredWidth(200);
          
    
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
            sqlselect = " select * from tb_user order by id_user asc ";
            Statement stat = _cnn.createStatement();
            ResultSet res = stat.executeQuery(sqlselect);
            while(res.next()){
                id_user =  res.getString(1);
                vnama = res.getString(2);
                v_alamat = res.getString(5);
                v_telpon = res.getString(6);
                vpass= res.getString(7);
                Object[] data = {id_user,vnama,v_alamat,v_telpon,vpass};
                tbldata.addRow(data);
                
            }
            lblrecord.setText("Record : "+tbdata.getRowCount());
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Eror Method showdata(): "+ex);
            
            
        }  
        
        }
      private void aksiupdate(){
        vnama = txtNama.getText();
        v_telpon = txtno.getText();
        v_alamat = txtalamat.getText();
        id_user = txtid1.getText();
        vpass= txtpass.getText();
        
          sqlupdate = "update tb_user set nama_user ='"+vnama+"',alamat ='"+v_alamat+"',no_telpon ='"+v_telpon+"',password ='"+vpass+"' where id_user ='"+id_user+"' ";
         
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
        int jawab = JOptionPane.showConfirmDialog(this,"Anda yakin akan menghapus data ini ? ID. user : "+id_user,
                "Konfirmasi",JOptionPane.YES_NO_OPTION);
        if(jawab == JOptionPane.YES_NO_OPTION){ 
            try{
                _cnn = null;
                _cnn = getcnn.getConnection();
                sqldelete = "delete from tb_user where id_user ='"+id_user+"'";
                Statement stat = _cnn.createStatement();
                stat.executeUpdate(sqldelete);
                JOptionPane.showMessageDialog(this,"Data Berhasil Dihapus","Informasi",JOptionPane.INFORMATION_MESSAGE);
                clearform();showdata();
                
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
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtno = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        btnsimpan = new javax.swing.JPanel();
        btnu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        txtid1 = new javax.swing.JTextField();
        txtlihat = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdata = new javax.swing.JTable();
        btnsimpan1 = new javax.swing.JPanel();
        btnh = new javax.swing.JLabel();
        btnsimpan2 = new javax.swing.JPanel();
        btnb = new javax.swing.JLabel();
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
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 890, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Alamat");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 80, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nama User");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 70, -1));

        txtno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnoActionPerformed(evt);
            }
        });
        jPanel2.add(txtno, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, 170, 30));

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });
        jPanel2.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, 170, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Id_user");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 80, -1));

        txtpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpassActionPerformed(evt);
            }
        });
        jPanel2.add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 170, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Password");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 80, -1));

        btnsimpan.setBackground(new java.awt.Color(0, 138, 0));
        btnsimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsimpanMouseClicked(evt);
            }
        });

        btnu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnu.setForeground(new java.awt.Color(255, 255, 255));
        btnu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnu.setText("Update");
        btnu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnsimpanLayout = new javax.swing.GroupLayout(btnsimpan);
        btnsimpan.setLayout(btnsimpanLayout);
        btnsimpanLayout.setHorizontalGroup(
            btnsimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnsimpanLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(btnu)
                .addGap(32, 32, 32))
        );
        btnsimpanLayout.setVerticalGroup(
            btnsimpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnu, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 110, 40));

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        jScrollPane1.setViewportView(txtalamat);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, 250, 110));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("No.telpon");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 80, -1));

        txtid1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtid1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 170, 30));

        txtlihat.setBackground(new java.awt.Color(255, 255, 255));
        txtlihat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-eye-24.png"))); // NOI18N
        txtlihat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlihatActionPerformed(evt);
            }
        });
        jPanel2.add(txtlihat, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 130, -1, -1));

        tbdata.setModel(new javax.swing.table.DefaultTableModel(
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
        tbdata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbdata);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 450, 370));

        btnsimpan1.setBackground(new java.awt.Color(0, 138, 0));
        btnsimpan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsimpan1MouseClicked(evt);
            }
        });

        btnh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnh.setForeground(new java.awt.Color(255, 255, 255));
        btnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnh.setText("Hapus");
        btnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnsimpan1Layout = new javax.swing.GroupLayout(btnsimpan1);
        btnsimpan1.setLayout(btnsimpan1Layout);
        btnsimpan1Layout.setHorizontalGroup(
            btnsimpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnsimpan1Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addComponent(btnh)
                .addGap(34, 34, 34))
        );
        btnsimpan1Layout.setVerticalGroup(
            btnsimpan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnh, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(btnsimpan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, -1, -1));

        btnsimpan2.setBackground(new java.awt.Color(0, 138, 0));
        btnsimpan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsimpan2MouseClicked(evt);
            }
        });

        btnb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnb.setForeground(new java.awt.Color(255, 255, 255));
        btnb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnb.setText("Batal");
        btnb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnsimpan2Layout = new javax.swing.GroupLayout(btnsimpan2);
        btnsimpan2.setLayout(btnsimpan2Layout);
        btnsimpan2Layout.setHorizontalGroup(
            btnsimpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnsimpan2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnb, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        btnsimpan2Layout.setVerticalGroup(
            btnsimpan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnb, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(btnsimpan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 390, -1, -1));

        lblrecord.setText("Record : 0");
        jPanel2.add(lblrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(979, 624));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked
        new menu_utama(lev_user).show();
        this.dispose();

    }//GEN-LAST:event_jLabel33MouseClicked

    private void txtnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnoActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpassActionPerformed

    private void btnuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnuMouseClicked
       
        
    }//GEN-LAST:event_btnuMouseClicked

    private void btnsimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsimpanMouseClicked
aksiupdate();
    }//GEN-LAST:event_btnsimpanMouseClicked

    private void txtid1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtid1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtid1ActionPerformed

    private void txtlihatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlihatActionPerformed
        if (txtlihat.isSelected()) {
            txtpass.setEchoChar((char)0);

        } else {
            txtpass.setEchoChar('\u25cf');

        }
    }//GEN-LAST:event_txtlihatActionPerformed

    private void tbdataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdataMouseClicked
       if(evt.getClickCount()==2){
            int brs = tbdata.getSelectedRow();
            id_user = tbdata.getValueAt(brs, 0).toString();
            vnama = tbdata.getValueAt(brs, 1).toString();
            v_alamat = tbdata.getValueAt(brs, 2).toString();
            v_telpon = tbdata.getValueAt(brs, 3).toString();
            vpass = tbdata.getValueAt(brs, 4).toString();
            
            txtid1.setText(id_user);
            txtNama.setText(vnama);
            txtalamat.setText(v_alamat);
            txtno.setText(v_telpon);
            txtpass.setText(vpass);
            
            txtid1.setEnabled(false);
           btnsimpan.setVisible(true);
        btnsimpan1.setVisible(true); 
        }
    }//GEN-LAST:event_tbdataMouseClicked

    private void btnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnhMouseClicked
       
    }//GEN-LAST:event_btnhMouseClicked

    private void btnsimpan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsimpan1MouseClicked
        aksihapus();
    }//GEN-LAST:event_btnsimpan1MouseClicked

    private void btnbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbMouseClicked

    private void btnsimpan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsimpan2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsimpan2MouseClicked

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
            java.util.logging.Logger.getLogger(editdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editdata.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editdata().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnb;
    private javax.swing.JLabel btnh;
    private javax.swing.JPanel btnsimpan;
    private javax.swing.JPanel btnsimpan1;
    private javax.swing.JPanel btnsimpan2;
    private javax.swing.JLabel btnu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblrecord;
    private javax.swing.JTable tbdata;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtid1;
    private javax.swing.JCheckBox txtlihat;
    private javax.swing.JTextField txtno;
    private javax.swing.JPasswordField txtpass;
    // End of variables declaration//GEN-END:variables
}
