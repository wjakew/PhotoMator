/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photomator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class copy_window extends javax.swing.JDialog {

    /**
     * Creates new form copy_window
     */
    
    ArrayList<Photo> c;
    String dest;
    ArrayList<ArrayList<Photo>> sorted;
    Configuration actual_c;
    
    public copy_window(java.awt.Frame parent, boolean modal,ArrayList<Photo> to_copy,String dir_path) {
        super(parent, modal);
        
        c = to_copy;
        dest = dir_path;
        sorted = new ArrayList<ArrayList<Photo>>();
        
        initComponents();
        podsumowanie_field.setText(prepare_summary());
        
        actual_c = new Configuration();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    String prepare_summary(){
        String to_ret = "CEL: "+dest+"\n"
                + "ILOŚĆ PLIKÓW: "+Integer.toString(c.size())+"\n"
                + "---\n"
                + "LISTA PLIKÓW:\n";
        
        for(Photo zdjecie : c){
            to_ret = to_ret + zdjecie.photo_src+"\n";
        }
        
        to_ret = to_ret+"GOTOWY DO KOPIOWANIA";
        return to_ret;
    }
    
    void sort(){//trzeba naprawic nie dziala!
        int lg = 0;
        ArrayList<Photo> one_date = new ArrayList<>();
        
        for (Photo zdjecie: c){
            System.out.println(zdjecie.name);
            if(sorted.isEmpty()){
                one_date.add(zdjecie);
                sorted.add(one_date);
                System.out.println("----"+sorted.get(0).get(0).name);
            }
            else{
                for(ArrayList<Photo> element_listy: sorted){
                    if(element_listy.get(0).same(zdjecie)){
                        element_listy.add(zdjecie);
                        lg = 1;
                    }
                }
                if(lg == 0){
                        one_date.add(zdjecie);
                        sorted.add(one_date);
                    }
                lg=0;
                
            }
            
        }
    }
    void show_sorted(){
        System.out.println("COPIER: Rozpoczynam wyswietlanie posortowanych plikow:");
        for(ArrayList<Photo> lista_zdjec: sorted){
            System.out.println("Wyswietlam dla data: "+lista_zdjec.get(0).photo_date);
            for(Photo zdjecie : lista_zdjec){
                System.out.println(zdjecie.name+" - "+zdjecie.photo_date);
            }
        }
        System.out.println("COPIER: Koncze wyswietlanie");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        podsumowanie_field = new javax.swing.JTextArea();
        kopiuj_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        log_field = new javax.swing.JTextArea();
        anuluj_button = new javax.swing.JButton();
        zakoncz_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Podsumowanie Operacji");

        podsumowanie_field.setColumns(20);
        podsumowanie_field.setRows(5);
        jScrollPane1.setViewportView(podsumowanie_field);

        kopiuj_button.setText("Kopiuj");
        kopiuj_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kopiuj_buttonActionPerformed(evt);
            }
        });

        log_field.setColumns(20);
        log_field.setRows(5);
        jScrollPane2.setViewportView(log_field);

        anuluj_button.setText("Anuluj");

        zakoncz_button.setText("Zakończ");
        zakoncz_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zakoncz_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(anuluj_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(zakoncz_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(kopiuj_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(kopiuj_button)
                .addGap(18, 18, 18)
                .addComponent(anuluj_button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zakoncz_button)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kopiuj_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kopiuj_buttonActionPerformed
        String log = "ROZPOCZYNAM KOPIOWANIE PLIKOW: \n";
        log_field.setText(log);
        for(Photo plik: c){
            try {
                if(new File(dest+"\\"+plik.dir_name(actual_c.get_date_format())).exists()){
                    Files.copy(plik.photo.toPath(), new File(dest+"\\"+plik.dir_name(actual_c.get_date_format())+"\\"+plik.photo.getName()).toPath(),StandardCopyOption.REPLACE_EXISTING);
                    log = log +"SKOPIOWANO"+plik.name+"\n";
                    log_field.setText(log);
                }
                else{
                    new File(dest+"\\"+plik.dir_name(actual_c.get_date_format())).mkdir();
                    log = log + "UTWORZONO FOLDER: "+plik.dir_name(actual_c.get_date_format())+"\n";
                    Files.copy(plik.photo.toPath(), new File(dest+"\\"+plik.dir_name(actual_c.get_date_format())+"\\"+plik.photo.getName()).toPath(),StandardCopyOption.REPLACE_EXISTING);
                    log = log +"SKOPIOWANO"+plik.name+"\n";
                    log_field.setText(log);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(copy_window.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_kopiuj_buttonActionPerformed

    private void zakoncz_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zakoncz_buttonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_zakoncz_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anuluj_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kopiuj_button;
    private javax.swing.JTextArea log_field;
    private javax.swing.JTextArea podsumowanie_field;
    private javax.swing.JButton zakoncz_button;
    // End of variables declaration//GEN-END:variables
}
