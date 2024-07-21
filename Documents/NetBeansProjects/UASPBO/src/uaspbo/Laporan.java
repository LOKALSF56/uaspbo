/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uaspbo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
public class Laporan extends javax.swing.JFrame {
    private Connection con;
    /**
     * Creates new form Laporan
     */
    public Laporan() {
        initComponents();
        con = db.getKonek();
        loadTransaksiTable();
        loadAbsensiTable();
    }
        private void loadTransaksiTable() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); // Clear existing data

        String query = "SELECT * FROM transaksi"; // Ganti nama tabel jika berbeda

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String menu = rs.getString("menu");
                String atasNama = rs.getString("atasnama");
                String nomorMeja = rs.getString("nomormeja");
                int jumlah = rs.getInt("jumlah");
                int harga = rs.getInt("harga");
                String jenis = rs.getString("tempatmakan");

                model.addRow(new Object[]{id, menu, atasNama, nomorMeja, jumlah, harga, jenis});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data transaksi.");
        }
    }

    private void loadAbsensiTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing data

        String query = "SELECT * FROM absensi"; // Ganti nama tabel jika berbeda

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String namaPegawai = rs.getString("namapegawai");
                String kehadiran = rs.getString("kehadiran");

                model.addRow(new Object[]{id, namaPegawai, kehadiran});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memuat data absensi.");
        }
    }
        private void exportTablesToPDF() {
 Document document = new Document();
    try {
        // Get the user's home directory and append the Downloads folder
        String home = System.getProperty("user.home");
        String downloadPath = home + "\\Documents\\Laporan.pdf";
        
        PdfWriter.getInstance(document, new FileOutputStream(downloadPath));
        document.open();
        
        // Export jTable2
        PdfPTable pdfTable2 = new PdfPTable(jTable2.getColumnCount());
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            pdfTable2.addCell(new PdfPCell(new Phrase(jTable2.getColumnName(i))));
        }
        for (int rows = 0; rows < jTable2.getRowCount(); rows++) {
            for (int cols = 0; cols < jTable2.getColumnCount(); cols++) {
                pdfTable2.addCell(new PdfPCell(new Phrase(jTable2.getModel().getValueAt(rows, cols).toString())));
            }
        }
        document.add(new Paragraph("Transaksi"));
        document.add(pdfTable2);
        document.add(new Paragraph(" "));
        
        // Export jTable1
        PdfPTable pdfTable1 = new PdfPTable(jTable1.getColumnCount());
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            pdfTable1.addCell(new PdfPCell(new Phrase(jTable1.getColumnName(i))));
        }
        for (int rows = 0; rows < jTable1.getRowCount(); rows++) {
            for (int cols = 0; cols < jTable1.getColumnCount(); cols++) {
                pdfTable1.addCell(new PdfPCell(new Phrase(jTable1.getModel().getValueAt(rows, cols).toString())));
            }
        }
        document.add(new Paragraph("Absensi"));
        document.add(pdfTable1);
        
        JOptionPane.showMessageDialog(null, "PDF berhasil diunduh ke " + downloadPath);
    } catch (DocumentException | FileNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengunduh PDF.");
    } finally {
        document.close();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nama Pegawai", "Kehadiran"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 710, 150));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Menu", "Atas Nama", "Nomor Meja", "Jumlah", "Harga", "Tempat Makan"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 710, 150));

        jLabel2.setText("Absensi");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jLabel3.setText("Transaksi");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 480, 150, 40));

        jButton2.setText("Download PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 150, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg/Frame 5.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     exportTablesToPDF();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     new HOME().setVisible(true);
     setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
