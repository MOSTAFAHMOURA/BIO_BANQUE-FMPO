/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stagefindf;

import com.datastax.driver.core.*;
import com.datastax.driver.core.utils.UUIDs;
import com.placeholder.PlaceHolder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author proxc
 */
public class HomeMedecin extends javax.swing.JFrame {

    private CardLayout cardLayout;

    String serverIP = "127.0.0.1";
    String keyspace = "bio";

    Cluster cluster = Cluster.builder()
            .addContactPoints(serverIP)
            .build();

    Session session = cluster.connect(keyspace);

    String name = "";
    String id;

    public HomeMedecin(String idd) {
        
        initComponents();
        this.id = idd;
        
        remplirtabacceuil();
        
        String cqlStatement = "SELECT * FROM users WHERE cne = '" + id + "' allow filtering ;";
        
       try {
                final Row row = session.execute(cqlStatement).one();
                //name=row.getString(6).toUpperCase();
                jLabel3.setText(jLabel3.getText()+" "+row.getString(5).toUpperCase()+"");
                nom.setText(row.getString(3).toUpperCase()+"");
                prenom.setText(row.getString(5).toUpperCase()+"");
                spec.setText(row.getString(6).toUpperCase()+"");
                tele.setText("0"+row.getInt(4)+"");
                CIN.setText(row.getString(0).toUpperCase()+"");
            }catch(Exception e){
                System.out.println("err");
            }
        //String cqlStatement = "SELECT * FROM med WHERE id = "+id+" allow filtering ;";
        listepatientmin.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listepatientmin.getTableHeader().setOpaque(false);
        listepatientmin.getTableHeader().setBackground(new Color(255, 204, 204));
        listepatientmin.getTableHeader().setForeground(new Color(0, 0, 0));
        
        

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) listepatientmin.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        listecons.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 16));
        listecons.getTableHeader().setOpaque(false);
        listecons.getTableHeader().setBackground(new Color(255,102,102));
        listecons.getTableHeader().setForeground(new Color(0, 0, 0));
        
        DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) listecons.getDefaultRenderer(Object.class);
        rendere.setHorizontalAlignment(SwingConstants.CENTER);
        

        HomeMedecin.this.getRootPane().setBorder(new LineBorder(new Color(21, 51, 23)));
        lblTitle.setText(this.getTitle());
        cardLayout = (CardLayout) pnlRight.getLayout();
        
        
        if (OSUtils.getOSType() == OSUtils.OSType.MacOS) {
            pnlTop.remove(pnlTitle);
            pnlTop.remove(pnlRight);

            pnlTop.add(pnlTitle, BorderLayout.EAST);
            pnlTop.add(pnlActions, BorderLayout.WEST);

            pnlActions.remove(lblClose);
            pnlActions.remove(lblMaximize);
            pnlActions.remove(lblMinimize);

            pnlActions.add(lblClose);
            pnlActions.add(lblMaximize);
            pnlActions.add(lblMinimize);

            pnlTitle.remove(lblTitle);
            pnlTitle.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
            pnlTitle.add(lblTitle);

        }
        if (OSUtils.getOSType() == OSUtils.OSType.Windows) {
            pnlTop.remove(pnlTitle);
            pnlTop.remove(pnlRight);

            pnlTop.add(pnlTitle, BorderLayout.WEST);
            pnlTop.add(pnlActions, BorderLayout.EAST);

            pnlActions.remove(lblClose);
            pnlActions.remove(lblMaximize);
            pnlActions.remove(lblMinimize);

            pnlActions.add(lblMinimize);
            pnlActions.add(lblMaximize);
            pnlActions.add(lblClose);

            pnlTitle.remove(lblTitle);
            pnlTitle.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 8));
            pnlTitle.add(lblTitle);

        }
        
        resetColor(btn_data1);
        resetColor(btn_data);
        setColor(btn_typo);
        ind_typo.setOpaque(true);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_data2);
    }
    
    public void remplirtabacceuil(){
        
        listepatientmin.getSelectionModel().clearSelection();
        listecons.getSelectionModel().clearSelection();
        
        
        String cql = " select * from patient ;";
        
        DefaultTableModel yourModel = (DefaultTableModel) listepatientmin.getModel();
        DefaultTableModel yourModell = (DefaultTableModel) listecons.getModel();  
        
        yourModell.setRowCount(0);        
        yourModel.setRowCount(0);
        
        
        try {
            for (Row n : session.execute(cql)) {
                yourModel.addRow(new Object[]{n.getString(0),n.getString(7)+" "+n.getString(10)});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
        String cqll = " select * from consultation;";
        
        try {
            
            for (Row n : session.execute(cqll)) {
                
                String name = "select * from patient where cne = '"+n.getString(1)+"' allow filtering ;";
                
                for (Row nn : session.execute(name)) {
                    yourModell.addRow(new Object[]{n.getString(1),nn.getString(7)+" "+nn.getString(10),n.getDate(2)});
                }
            
            }

        } catch (Exception e) {
            System.out.println("err lst conss min");
        }
    }
    
    
    public void decc(){
        symdec.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        symdec.getTableHeader().setOpaque(false);
        symdec.getTableHeader().setBackground(new Color(255,204,51));
        symdec.getTableHeader().setForeground(new Color(0,0,0));
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) symdec.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        maldec.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        maldec.getTableHeader().setOpaque(false);
        maldec.getTableHeader().setBackground(new Color(255,204,51));
        maldec.getTableHeader().setForeground(new Color(0,0,0));
        DefaultTableCellRenderer rendererr = (DefaultTableCellRenderer) maldec.getDefaultRenderer(Object.class);
        rendererr.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableModel yourModel = (DefaultTableModel) symdec.getModel();
        DefaultTableModel yourModell = (DefaultTableModel) maldec.getModel();
        
        for(int i = yourModel.getRowCount(); i > 0; --i) yourModel.removeRow(i-1);    
        for(int i = yourModell.getRowCount(); i > 0; --i) yourModell.removeRow(i-1);
    }
    
    public void tableaumespatient(){
        
        listpatientp.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        listpatientp.getTableHeader().setOpaque(false);
        listpatientp.getTableHeader().setBackground(new Color(41,168,73));
        listpatientp.getTableHeader().setForeground(new Color(255, 255, 255));
        
        

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) listpatientp.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        String cql = " select * from patient ;";
        DefaultTableModel yourModel = (DefaultTableModel) listpatientp.getModel();
        try {
            for (Row n : session.execute(cql)) {
                yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(7).toUpperCase()+" "+n.getString(10).toUpperCase(),n.getDate(2),n.getString(12).toUpperCase()});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
        
    }
    
    
    public void dec(String dd){
        
        decc();
        
        String id=dd;
        
        DefaultTableModel yourModel = (DefaultTableModel) symdec.getModel();
        DefaultTableModel yourModell = (DefaultTableModel) maldec.getModel();
        
            patienttmp.setText("");
            patienttaille.setText("");
            patienttention.setText("");
            patientpoid.setText("");
            nommed.setText("");
        
        
        
        String idmed = null;
        
        String cqlStatement = " select * from patient where cne ='"+id+"' ;";
        try {
            
            
            cnicons.setText(id);
            final Row row = session.execute(cqlStatement).one();
            idmed =row.getString(5);
            System.out.println(cqlStatement);
            
            String cql = " select * from users where cne ='"+idmed+"' ;";
            final Row roww = session.execute(cql).one();
            System.out.println(cql);
            
            
            String name= nommed.getText()+" "+roww.getString(3).toUpperCase()+" "+roww.getString(5).toUpperCase();
            nommed.setText(name);
            
            String cqll = " select * from patient where cne ='"+id+"' ;";
            final Row rowww = session.execute(cqll).one();
            
            String namepatientt= rowww.getString(7).toUpperCase()+" "+rowww.getString(10).toUpperCase();
            namepatient.setText(namepatientt);
            
            String ddd = " select * from consultation where cni_patient ='"+id+"' ALLOW FILTERING ;";
            final Row ro = session.execute(ddd).one();
            
            System.err.println(">>"+ddd);
            
            String d = ro.getDate(2).toString();
            consultationdate.setText(d);
            consmotif.setText(ro.getString(3).toUpperCase());
            
            String cli = " select * from dn_clinique where num_consultation ='"+ro.getUUID(0)+"' ALLOW FILTERING ;";
            final Row rom = session.execute(cli).one();
            
            String tmp = rom.getString(4)+" °C";
            patienttmp.setText(tmp);
            
            String cm = rom.getString(3)+" Cm";
            patienttaille.setText(cm);
            
            String c = rom.getString(5)+"";
            patienttention.setText(c);
            
            String p = rom.getString(2)+" Kg";
            patientpoid.setText(p);
            
            
                String lqs = " SELECT  * FROM aide_sym where cni = '"+id+"' ALLOW FILTERING ;";
                
                  try {
                    
                    Row n = session.execute(lqs).one();
                    String lsd = " SELECT  * FROM symptome where id_s = "+n.getString(2)+" ALLOW FILTERING ;";
                    System.out.println(">>hna"+lsd);
                    for (Row nn : session.execute(lsd)) {
                        yourModel.addRow(new Object[]{nn.getString(1)});
                    }

                  } catch (Exception e) {
                    System.out.print("err tab 1");
                  }
                  
                   String lqss = " SELECT  * FROM aide_mal where cni = '"+id+"' ALLOW FILTERING ;";
                    System.out.println(">>"+lqss);
                        try {
                    
                             Row nd = session.execute(lqss).one();
                            String lsd = " SELECT  * FROM maladies where id_m = "+nd.getString(2)+" ALLOW FILTERING ;";
                            System.out.println(">>"+lsd);
                            
                        for (Row nnn : session.execute(lsd)) {
                                 yourModell.addRow(new Object[]{nnn.getString(1)});
                            }

                         } catch (Exception e) {
                             System.out.print("err tab 2");
                        }


            } catch (Exception e) {
                System.out.print("err kbira");
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

        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jTextField1 = new javax.swing.JTextField();
        pnlTop = new javax.swing.JPanel();
        pnlActions = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        lblMaximize = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pnlParent = new javax.swing.JPanel();
        sidepane = new javax.swing.JPanel();
        btn_typo = new javax.swing.JPanel();
        ind_typo = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_fonts = new javax.swing.JPanel();
        ind_fonts = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btn_icons = new javax.swing.JPanel();
        ind_icons = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_btns = new javax.swing.JPanel();
        ind_btns = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btn_data = new javax.swing.JPanel();
        ind_data = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btn_data1 = new javax.swing.JPanel();
        ind_data1 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        btn_data2 = new javax.swing.JPanel();
        ind_data2 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        pnlTypography = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        nom = new javax.swing.JLabel();
        prenom = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        spec = new javax.swing.JLabel();
        tele = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        CIN = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        listepatientmin = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listecons = new javax.swing.JTable();
        pnlDataCards = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        valider = new java.awt.Button();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        patientcin = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        patientnom = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        patientpnom = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        patientlieunaiss = new javax.swing.JTextField();
        patientdate = new com.toedter.calendar.JDateChooser();
        jSeparator9 = new javax.swing.JSeparator();
        patientnatin = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        patientemail = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        patienttele = new javax.swing.JTextField();
        patientville = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        patientadresse = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        patientproffession = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        patientmere = new javax.swing.JTextField();
        patientpere = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        pnlcons = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        conscni = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        consdate = new com.toedter.calendar.JDateChooser();
        jLabel40 = new javax.swing.JLabel();
        consdesc = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        constaille = new javax.swing.JTextField();
        conspoids = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        constention = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        constmp = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        consvalider = new java.awt.Button();
        jSeparator22 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sym = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        valid = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        mal = new javax.swing.JTable();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        pnllistemal = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listpatientp = new javax.swing.JTable();
        infocons = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel54 = new javax.swing.JLabel();
        namepatient = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        cnicons = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        nommed = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        consultationdate = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        consmotif = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jSeparator25 = new javax.swing.JSeparator();
        jLabel66 = new javax.swing.JLabel();
        patientpoid = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        patienttaille = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        patienttention = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        patienttmp = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        symdec = new javax.swing.JTable();
        jLabel74 = new javax.swing.JLabel();
        jSeparator26 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        maldec = new javax.swing.JTable();
        jLabel75 = new javax.swing.JLabel();
        jSeparator27 = new javax.swing.JSeparator();
        addprevaide = new javax.swing.JPanel();
        echan = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jSeparator33 = new javax.swing.JSeparator();
        jLabel81 = new javax.swing.JLabel();
        adn = new javax.swing.JCheckBox();
        jLabel82 = new javax.swing.JLabel();
        adnnbr = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        adncode = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        adncong = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        adntir = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        adnboite = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        adnempboite = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        adnboitevol = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        adnrem = new javax.swing.JTextField();
        arn = new javax.swing.JCheckBox();
        arnnbr = new javax.swing.JTextField();
        arncode = new javax.swing.JTextField();
        arncong = new javax.swing.JTextField();
        arntir = new javax.swing.JTextField();
        arnboite = new javax.swing.JTextField();
        arnempboite = new javax.swing.JTextField();
        arnboitevol = new javax.swing.JTextField();
        arnrem = new javax.swing.JTextField();
        plasmaboite = new javax.swing.JTextField();
        plasma = new javax.swing.JCheckBox();
        plasmanbr = new javax.swing.JTextField();
        plasmaboitevol = new javax.swing.JTextField();
        plasmacong = new javax.swing.JTextField();
        plasmacode = new javax.swing.JTextField();
        plasmarem = new javax.swing.JTextField();
        plasmatir = new javax.swing.JTextField();
        plasmaempboite = new javax.swing.JTextField();
        autrerem = new javax.swing.JTextField();
        autreboite = new javax.swing.JTextField();
        autretir = new javax.swing.JTextField();
        autrenbr = new javax.swing.JTextField();
        autreempboite = new javax.swing.JTextField();
        autrecode = new javax.swing.JTextField();
        echautre = new javax.swing.JCheckBox();
        autreboitevol = new javax.swing.JTextField();
        autrecong = new javax.swing.JTextField();
        consvalider3 = new java.awt.Button();
        addpr = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jSeparator34 = new javax.swing.JSeparator();
        jLabel78 = new javax.swing.JLabel();
        s = new javax.swing.JCheckBox();
        t = new javax.swing.JCheckBox();
        ee = new javax.swing.JCheckBox();
        bb = new javax.swing.JCheckBox();
        aa = new javax.swing.JCheckBox();
        eco = new javax.swing.JTextField();
        tissu = new javax.swing.JTextField();
        bio = new javax.swing.JTextField();
        autre = new javax.swing.JTextField();
        jSeparator35 = new javax.swing.JSeparator();
        jLabel79 = new javax.swing.JLabel();
        jSeparator36 = new javax.swing.JSeparator();
        lieuprel = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jSeparator37 = new javax.swing.JSeparator();
        rprel = new javax.swing.JTextField();
        b = new javax.swing.JTextField();
        c = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        e = new javax.swing.JTextField();
        sang = new javax.swing.JTextField();
        a = new javax.swing.JTextField();
        cniprel = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jSeparator38 = new javax.swing.JSeparator();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        consvalider4 = new java.awt.Button();
        consvalider5 = new java.awt.Button();
        patient = new javax.swing.JPanel();
        patientnamee = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jSeparator28 = new javax.swing.JSeparator();
        jLabel57 = new javax.swing.JLabel();
        patientname = new javax.swing.JLabel();
        patientcni = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        patientdateniassance = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        patientlieu = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        patientsexe = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        patientnumtele = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        patientnationnaliter = new javax.swing.JLabel();
        patientvillee = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        patientadr = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jSeparator29 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableconspatient = new javax.swing.JTable();
        jSeparator30 = new javax.swing.JSeparator();
        jLabel96 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableechspatient = new javax.swing.JTable();
        chercher = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        listpatientp1 = new javax.swing.JTable();
        help = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jSeparator31 = new javax.swing.JSeparator();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setLocationByPlatform(true);
        setUndecorated(true);

        pnlTop.setBackground(new java.awt.Color(41, 168, 73));
        pnlTop.setPreferredSize(new java.awt.Dimension(1024, 30));
        pnlTop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlTopMouseDragged(evt);
            }
        });
        pnlTop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTopMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlTopMousePressed(evt);
            }
        });
        pnlTop.setLayout(new java.awt.BorderLayout());

        pnlActions.setBackground(new java.awt.Color(41, 168, 73));
        pnlActions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lblMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_minus_18px_1.png"))); // NOI18N
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMinimizeMousePressed(evt);
            }
        });
        pnlActions.add(lblMinimize);

        lblMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_rectangle_stroked_18px.png"))); // NOI18N
        lblMaximize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMaximizeMousePressed(evt);
            }
        });
        pnlActions.add(lblMaximize);

        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_multiply_18px_1.png"))); // NOI18N
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCloseMousePressed(evt);
            }
        });
        pnlActions.add(lblClose);

        pnlTop.add(pnlActions, java.awt.BorderLayout.LINE_END);

        pnlTitle.setBackground(new java.awt.Color(41, 168, 73));
        pnlTitle.setPreferredSize(new java.awt.Dimension(200, 30));
        pnlTitle.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 8));

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Home");
        pnlTitle.add(lblTitle);

        pnlTop.add(pnlTitle, java.awt.BorderLayout.LINE_START);

        getContentPane().add(pnlTop, java.awt.BorderLayout.PAGE_START);

        pnlParent.setLayout(new java.awt.BorderLayout());

        sidepane.setBackground(new java.awt.Color(41, 168, 73));
        sidepane.setForeground(new java.awt.Color(51, 51, 51));
        sidepane.setPreferredSize(new java.awt.Dimension(250, 200));
        sidepane.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                sidepaneMouseDragged(evt);
            }
        });
        sidepane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sidepaneMousePressed(evt);
            }
        });

        btn_typo.setBackground(new java.awt.Color(41, 168, 73));
        btn_typo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_typoMousePressed(evt);
            }
        });
        btn_typo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_typo.setOpaque(false);
        ind_typo.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_typoLayout = new javax.swing.GroupLayout(ind_typo);
        ind_typo.setLayout(ind_typoLayout);
        ind_typoLayout.setHorizontalGroup(
            ind_typoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_typoLayout.setVerticalGroup(
            ind_typoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_typo.add(ind_typo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Acceuil");
        btn_typo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Acceuil");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Medcin");
        jLabel2.setToolTipText("");

        btn_fonts.setBackground(new java.awt.Color(41, 168, 73));
        btn_fonts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_fontsMousePressed(evt);
            }
        });
        btn_fonts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_fonts.setOpaque(false);
        ind_fonts.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_fontsLayout = new javax.swing.GroupLayout(ind_fonts);
        ind_fonts.setLayout(ind_fontsLayout);
        ind_fontsLayout.setHorizontalGroup(
            ind_fontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_fontsLayout.setVerticalGroup(
            ind_fontsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_fonts.add(ind_fonts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ajouter Un Patient");
        btn_fonts.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        btn_icons.setBackground(new java.awt.Color(41, 168, 73));
        btn_icons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_iconsMousePressed(evt);
            }
        });
        btn_icons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_icons.setOpaque(false);
        ind_icons.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_iconsLayout = new javax.swing.GroupLayout(ind_icons);
        ind_icons.setLayout(ind_iconsLayout);
        ind_iconsLayout.setHorizontalGroup(
            ind_iconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_iconsLayout.setVerticalGroup(
            ind_iconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_icons.add(ind_icons, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ajouter Une Consultation");
        btn_icons.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Menu");

        btn_btns.setBackground(new java.awt.Color(41, 168, 73));
        btn_btns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_btnsMousePressed(evt);
            }
        });
        btn_btns.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_btns.setOpaque(false);
        ind_btns.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_btnsLayout = new javax.swing.GroupLayout(ind_btns);
        ind_btns.setLayout(ind_btnsLayout);
        ind_btnsLayout.setHorizontalGroup(
            ind_btnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_btnsLayout.setVerticalGroup(
            ind_btnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_btns.add(ind_btns, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Liste des Patients");
        btn_btns.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        btn_data.setBackground(new java.awt.Color(41, 168, 73));
        btn_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dataMousePressed(evt);
            }
        });
        btn_data.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_data.setOpaque(false);
        ind_data.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_dataLayout = new javax.swing.GroupLayout(ind_data);
        ind_data.setLayout(ind_dataLayout);
        ind_dataLayout.setHorizontalGroup(
            ind_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_dataLayout.setVerticalGroup(
            ind_dataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_data.add(ind_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ajouter Un Prélèvement");
        btn_data.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        btn_data1.setBackground(new java.awt.Color(41, 168, 73));
        btn_data1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_data1MousePressed(evt);
            }
        });
        btn_data1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_data1.setOpaque(false);
        ind_data1.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_data1Layout = new javax.swing.GroupLayout(ind_data1);
        ind_data1.setLayout(ind_data1Layout);
        ind_data1Layout.setHorizontalGroup(
            ind_data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_data1Layout.setVerticalGroup(
            ind_data1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_data1.add(ind_data1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel51.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Help");
        btn_data1.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        btn_data2.setBackground(new java.awt.Color(41, 168, 73));
        btn_data2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_data2MousePressed(evt);
            }
        });
        btn_data2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_data2.setOpaque(false);
        ind_data2.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_data2Layout = new javax.swing.GroupLayout(ind_data2);
        ind_data2.setLayout(ind_data2Layout);
        ind_data2Layout.setHorizontalGroup(
            ind_data2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_data2Layout.setVerticalGroup(
            ind_data2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_data2.add(ind_data2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel73.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Chercher Un Patient");
        btn_data2.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        javax.swing.GroupLayout sidepaneLayout = new javax.swing.GroupLayout(sidepane);
        sidepane.setLayout(sidepaneLayout);
        sidepaneLayout.setHorizontalGroup(
            sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_typo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_fonts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_icons, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(btn_btns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_data, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidepaneLayout.createSequentialGroup()
                .addGroup(sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidepaneLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(sidepaneLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btn_data1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_data2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidepaneLayout.setVerticalGroup(
            sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidepaneLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(41, 41, 41)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_typo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_fonts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_icons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_btns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_data2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_data1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        pnlParent.add(sidepane, java.awt.BorderLayout.LINE_START);

        pnlRight.setLayout(new java.awt.CardLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Bienvenue Dr");

        jSeparator3.setForeground(new java.awt.Color(51, 153, 0));

        jPanel1.setBackground(new java.awt.Color(41, 168, 73));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8-doctor-male-48.png"))); // NOI18N

        jLabel11.setBackground(new java.awt.Color(255, 255, 102));
        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Information du Médecin");

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Nom :");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Prénom :");

        nom.setBackground(new java.awt.Color(255, 255, 255));
        nom.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        nom.setForeground(new java.awt.Color(255, 255, 0));
        nom.setText("HMOURA");

        prenom.setBackground(new java.awt.Color(255, 255, 255));
        prenom.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        prenom.setForeground(new java.awt.Color(255, 255, 0));
        prenom.setText("MOSTAFA");

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Téléphone :");

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Spécialité médicale :");

        spec.setBackground(new java.awt.Color(255, 255, 255));
        spec.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        spec.setForeground(new java.awt.Color(255, 255, 0));
        spec.setText("chirurgie cardiaque");

        tele.setBackground(new java.awt.Color(255, 255, 255));
        tele.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        tele.setForeground(new java.awt.Color(255, 255, 0));
        tele.setText("061101014");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("CIN :");

        CIN.setBackground(new java.awt.Color(255, 255, 255));
        CIN.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        CIN.setForeground(new java.awt.Color(255, 255, 0));
        CIN.setText("F99382");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(prenom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(spec, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(jLabel22)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tele, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CIN, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(nom)
                            .addComponent(jLabel22)
                            .addComponent(tele)
                            .addComponent(jLabel24)
                            .addComponent(CIN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(prenom)
                            .addComponent(jLabel23)
                            .addComponent(spec))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        jLabel34.setBackground(new java.awt.Color(0, 204, 153));
        jLabel34.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel34.setText("Liste des Patients :");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 204));

        listepatientmin.setBackground(new java.awt.Color(255, 204, 204));
        listepatientmin.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        listepatientmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CIN", "PTIENT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        listepatientmin.setRowHeight(28);
        listepatientmin.setSelectionBackground(new java.awt.Color(204, 0, 0));
        listepatientmin.setShowVerticalLines(false);
        listepatientmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listepatientminMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(listepatientmin);
        if (listepatientmin.getColumnModel().getColumnCount() > 0) {
            listepatientmin.getColumnModel().getColumn(0).setMinWidth(130);
            listepatientmin.getColumnModel().getColumn(0).setMaxWidth(300);
        }

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px_2.png"))); // NOI18N
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel48)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 102, 102));

        jLabel35.setBackground(new java.awt.Color(0, 204, 153));
        jLabel35.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel35.setText("Liste des Consultations :");

        jSeparator18.setBackground(new java.awt.Color(255, 255, 204));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel50MouseClicked(evt);
            }
        });

        listecons.setBackground(new java.awt.Color(255, 102, 102));
        listecons.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        listecons.setForeground(new java.awt.Color(255, 255, 255));
        listecons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CIN", "Patient", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        listecons.setRowHeight(28);
        listecons.setSelectionBackground(new java.awt.Color(255, 0, 0));
        listecons.setShowVerticalLines(false);
        listecons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listeconsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listecons);
        if (listecons.getColumnModel().getColumnCount() > 0) {
            listecons.getColumnModel().getColumn(0).setMinWidth(90);
            listecons.getColumnModel().getColumn(0).setMaxWidth(90);
            listecons.getColumnModel().getColumn(2).setMinWidth(100);
            listecons.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator18, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35)
                        .addComponent(jLabel49)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlTypographyLayout = new javax.swing.GroupLayout(pnlTypography);
        pnlTypography.setLayout(pnlTypographyLayout);
        pnlTypographyLayout.setHorizontalGroup(
            pnlTypographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTypographyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTypographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTypographyLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTypographyLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTypographyLayout.setVerticalGroup(
            pnlTypographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTypographyLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTypographyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlRight.add(pnlTypography, "card2");

        jPanel2.setBackground(new java.awt.Color(41, 168, 73));

        valider.setBackground(new java.awt.Color(41, 168, 73));
        valider.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        valider.setForeground(new java.awt.Color(255, 255, 255));
        valider.setLabel("Ajouter");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CIN :");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nom :");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Prénom : ");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Sexe :");

        jRadioButton1.setBackground(new java.awt.Color(41, 168, 73));
        jRadioButton1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Femme");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(41, 168, 73));
        jRadioButton2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Homme");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Date de Naissance :");

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Lieu de Naissance :");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Nationalité :");

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Email : ");

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Téléphone : ");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Adresse : ");

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Profession : ");

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Ville :");

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Origine Mère :");

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Origine Père :");

        jSeparator2.setForeground(new java.awt.Color(41, 168, 73));

        patientcin.setForeground(new java.awt.Color(102, 102, 102));
        patientcin.setBorder(null);
        patientcin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientcinFocusGained(evt);
            }
        });
        patientcin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientcinActionPerformed(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(41, 168, 73));

        patientnom.setForeground(new java.awt.Color(102, 102, 102));
        patientnom.setBorder(null);
        patientnom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientnomFocusGained(evt);
            }
        });
        patientnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientnomActionPerformed(evt);
            }
        });

        jSeparator7.setForeground(new java.awt.Color(41, 168, 73));

        patientpnom.setForeground(new java.awt.Color(102, 102, 102));
        patientpnom.setBorder(null);
        patientpnom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientpnomFocusGained(evt);
            }
        });
        patientpnom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientpnomActionPerformed(evt);
            }
        });

        jSeparator8.setForeground(new java.awt.Color(41, 168, 73));

        patientlieunaiss.setForeground(new java.awt.Color(102, 102, 102));
        patientlieunaiss.setBorder(null);
        patientlieunaiss.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientlieunaissFocusGained(evt);
            }
        });
        patientlieunaiss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientlieunaissActionPerformed(evt);
            }
        });

        jSeparator9.setForeground(new java.awt.Color(41, 168, 73));

        patientnatin.setForeground(new java.awt.Color(102, 102, 102));
        patientnatin.setBorder(null);
        patientnatin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientnatinFocusGained(evt);
            }
        });
        patientnatin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientnatinActionPerformed(evt);
            }
        });

        jSeparator10.setForeground(new java.awt.Color(41, 168, 73));

        patientemail.setForeground(new java.awt.Color(102, 102, 102));
        patientemail.setBorder(null);
        patientemail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientemailFocusGained(evt);
            }
        });
        patientemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientemailActionPerformed(evt);
            }
        });

        jSeparator11.setForeground(new java.awt.Color(41, 168, 73));

        patienttele.setForeground(new java.awt.Color(102, 102, 102));
        patienttele.setBorder(null);
        patienttele.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientteleFocusGained(evt);
            }
        });
        patienttele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientteleActionPerformed(evt);
            }
        });

        patientville.setForeground(new java.awt.Color(102, 102, 102));
        patientville.setBorder(null);
        patientville.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientvilleFocusGained(evt);
            }
        });
        patientville.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientvilleActionPerformed(evt);
            }
        });

        jSeparator12.setForeground(new java.awt.Color(41, 168, 73));

        patientadresse.setForeground(new java.awt.Color(102, 102, 102));
        patientadresse.setBorder(null);
        patientadresse.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientadresseFocusGained(evt);
            }
        });
        patientadresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientadresseActionPerformed(evt);
            }
        });

        jSeparator13.setForeground(new java.awt.Color(41, 168, 73));

        patientproffession.setForeground(new java.awt.Color(102, 102, 102));
        patientproffession.setBorder(null);
        patientproffession.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientproffessionFocusGained(evt);
            }
        });
        patientproffession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientproffessionActionPerformed(evt);
            }
        });

        jSeparator14.setForeground(new java.awt.Color(41, 168, 73));

        jSeparator15.setForeground(new java.awt.Color(41, 168, 73));

        patientmere.setForeground(new java.awt.Color(102, 102, 102));
        patientmere.setBorder(null);
        patientmere.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientmereFocusGained(evt);
            }
        });
        patientmere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientmereActionPerformed(evt);
            }
        });

        patientpere.setForeground(new java.awt.Color(102, 102, 102));
        patientpere.setBorder(null);
        patientpere.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientpereFocusGained(evt);
            }
        });
        patientpere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientpereActionPerformed(evt);
            }
        });

        jSeparator16.setForeground(new java.awt.Color(41, 168, 73));

        jLabel33.setBackground(new java.awt.Color(255, 255, 102));
        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Nouveau Patient");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(patientnom)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(patientpnom)
                                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(patientdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jRadioButton2)
                                            .addGap(18, 18, 18)
                                            .addComponent(jRadioButton1)))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(patientlieunaiss)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(patientnatin, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(patienttele)
                                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(patientville)
                                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(patientadresse)
                                            .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(patientproffession)
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(patientmere)
                                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(patientpere)
                                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(patientemail)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(patientcin)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(357, 357, 357)))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(valider, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(patientcin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(patientnom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(patienttele, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(patientpnom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(patientville, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton2)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(patientadresse, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(patientproffession, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(patientlieunaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(patientmere, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel31)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel25)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(patientnatin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(patientpere, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(patientemail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(valider, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Ajouter Un Patient");

        jSeparator5.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout pnlDataCardsLayout = new javax.swing.GroupLayout(pnlDataCards);
        pnlDataCards.setLayout(pnlDataCardsLayout);
        pnlDataCardsLayout.setHorizontalGroup(
            pnlDataCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataCardsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDataCardsLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator5))
                .addContainerGap())
        );
        pnlDataCardsLayout.setVerticalGroup(
            pnlDataCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDataCardsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pnlRight.add(pnlDataCards, "card1");

        jPanel3.setBackground(new java.awt.Color(41, 168, 73));

        jLabel37.setBackground(new java.awt.Color(255, 255, 102));
        jLabel37.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Information de La Consultation");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("CNI Patient :");

        conscni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conscniActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Date de La Consultaion :");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Description de La Consultation :");

        consdesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consdescActionPerformed(evt);
            }
        });

        jLabel41.setBackground(new java.awt.Color(255, 255, 102));
        jLabel41.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Les données cliniques");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Taille (Cm) :");

        constaille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                constailleActionPerformed(evt);
            }
        });

        conspoids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conspoidsActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Poids (Kg) :");

        constention.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                constentionActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Tension :");

        constmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                constmpActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Temperature :");

        jLabel46.setBackground(new java.awt.Color(255, 255, 102));
        jLabel46.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Symptômes");

        consvalider.setBackground(new java.awt.Color(41, 168, 73));
        consvalider.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        consvalider.setForeground(new java.awt.Color(255, 255, 255));
        consvalider.setLabel("Ajouter");
        consvalider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consvaliderActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(240, 119, 104));

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 153));

        sym.setAutoCreateRowSorter(true);
        sym.setBackground(new java.awt.Color(41, 168, 73));
        sym.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        sym.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        sym.setForeground(new java.awt.Color(255, 255, 255));
        sym.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sélectionner des Symptômes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        sym.setGridColor(new java.awt.Color(255, 255, 255));
        sym.setIntercellSpacing(new java.awt.Dimension(15, 1));
        sym.setRowHeight(25);
        sym.setSelectionBackground(new java.awt.Color(255, 255, 0));
        sym.setShowVerticalLines(false);
        sym.getTableHeader().setReorderingAllowed(false);
        sym.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                symMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(sym);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel47.setBackground(new java.awt.Color(255, 255, 102));
        jLabel47.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Maladies");

        valid.setBackground(new java.awt.Color(240, 119, 104));

        jScrollPane3.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane3.setBorder(null);
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 153));

        mal.setAutoCreateRowSorter(true);
        mal.setBackground(new java.awt.Color(41, 168, 73));
        mal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        mal.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        mal.setForeground(new java.awt.Color(255, 255, 255));
        mal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sélectionner des Maladies"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        mal.setGridColor(new java.awt.Color(255, 255, 255));
        mal.setIntercellSpacing(new java.awt.Dimension(15, 1));
        mal.setRowHeight(25);
        mal.setSelectionBackground(new java.awt.Color(255, 255, 0));
        mal.setShowVerticalLines(false);
        mal.getTableHeader().setReorderingAllowed(false);
        mal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                malMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(mal);

        javax.swing.GroupLayout validLayout = new javax.swing.GroupLayout(valid);
        valid.setLayout(validLayout);
        validLayout.setHorizontalGroup(
            validLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(validLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        validLayout.setVerticalGroup(
            validLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel45)
                                        .addGap(18, 18, 18)
                                        .addComponent(constmp, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(constaille, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(83, 83, 83)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel44))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(constention, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(conspoids, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(18, 18, 18)
                                .addComponent(conscni, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel39)
                                .addGap(18, 18, 18)
                                .addComponent(consdate, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(18, 18, 18)
                                .addComponent(consdesc)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                        .addComponent(jSeparator22))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(valid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(consvalider, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(conscni, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(consdesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(constaille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(conspoids, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(constmp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(constention, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(19, 19, 19)
                .addComponent(consvalider, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jSeparator19.setForeground(new java.awt.Color(51, 153, 0));

        jLabel36.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setText("Ajouter Une Consultation");

        javax.swing.GroupLayout pnlconsLayout = new javax.swing.GroupLayout(pnlcons);
        pnlcons.setLayout(pnlconsLayout);
        pnlconsLayout.setHorizontalGroup(
            pnlconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlconsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator19)
                    .addGroup(pnlconsLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlconsLayout.setVerticalGroup(
            pnlconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlconsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(pnlcons, "card4");

        jPanel7.setBackground(new java.awt.Color(41, 168, 73));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Liste des Patients");

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel52.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel52MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
                .addContainerGap())
        );

        listpatientp.setBackground(new java.awt.Color(41, 168, 73));
        listpatientp.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        listpatientp.setForeground(new java.awt.Color(255, 255, 255));
        listpatientp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CIN", "Nom et Prénom", "Date de Naissance", "Sexe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        listpatientp.setGridColor(new java.awt.Color(255, 255, 255));
        listpatientp.setRowHeight(34);
        listpatientp.setSelectionBackground(new java.awt.Color(0, 153, 0));
        listpatientp.setShowVerticalLines(false);
        listpatientp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listpatientpMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(listpatientp);
        if (listpatientp.getColumnModel().getColumnCount() > 0) {
            listpatientp.getColumnModel().getColumn(0).setMinWidth(130);
            listpatientp.getColumnModel().getColumn(0).setMaxWidth(130);
            listpatientp.getColumnModel().getColumn(2).setMinWidth(170);
            listpatientp.getColumnModel().getColumn(2).setMaxWidth(170);
            listpatientp.getColumnModel().getColumn(3).setMinWidth(130);
            listpatientp.getColumnModel().getColumn(3).setMaxWidth(130);
        }

        javax.swing.GroupLayout pnllistemalLayout = new javax.swing.GroupLayout(pnllistemal);
        pnllistemal.setLayout(pnllistemalLayout);
        pnllistemalLayout.setHorizontalGroup(
            pnllistemalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnllistemalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnllistemalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnllistemalLayout.setVerticalGroup(
            pnllistemalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnllistemalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(pnllistemal, "card5");

        jPanel8.setBackground(new java.awt.Color(41, 168, 73));

        jLabel53.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Information de La Consultation");

        jLabel54.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Nom et Prénom du Patient :");

        namepatient.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        namepatient.setForeground(new java.awt.Color(255, 255, 0));
        namepatient.setText("HMOURA MOSTAFA MOSTAFA");

        jLabel56.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("CIN  :");

        cnicons.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        cnicons.setForeground(new java.awt.Color(255, 255, 0));
        cnicons.setText("F12345");

        jLabel58.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Médecin consultant :");

        nommed.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        nommed.setForeground(new java.awt.Color(255, 255, 0));
        nommed.setText("DR");

        jLabel60.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Date De Consultation  :");

        consultationdate.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        consultationdate.setForeground(new java.awt.Color(255, 255, 0));
        consultationdate.setText("1998-07-18");

        jLabel62.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Motif de La Consultation  :");

        consmotif.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        consmotif.setForeground(new java.awt.Color(255, 255, 0));
        consmotif.setText("HMOURA MOSTAFA MOSTAFA");

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator24)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nommed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consultationdate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel64))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consmotif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namepatient, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cnicons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1)))
                .addComponent(jSeparator24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(namepatient, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cnicons, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nommed, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consultationdate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consmotif, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(41, 168, 73));

        jLabel65.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("Données Cliniques");

        jLabel66.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Poid du Patient (Kg) : ");

        patientpoid.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientpoid.setForeground(new java.awt.Color(255, 255, 0));
        patientpoid.setText("1000 Kg");

        jLabel68.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Taille du Patient (Cm)  : ");

        patienttaille.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patienttaille.setForeground(new java.awt.Color(255, 255, 0));
        patienttaille.setText("1000 Cm");

        jLabel70.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Tention du Patient : ");

        patienttention.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patienttention.setForeground(new java.awt.Color(255, 255, 0));
        patienttention.setText("1000 Kg");

        jLabel72.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Temperature du Patient (°C) :");

        patienttmp.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patienttmp.setForeground(new java.awt.Color(255, 255, 0));
        patienttmp.setText("1000 °C");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator25)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel66))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientpoid, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patienttention, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel68)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patienttmp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patienttaille, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jSeparator25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientpoid, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patienttaille, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patienttention, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patienttmp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 204, 51));

        jScrollPane6.setBackground(new java.awt.Color(255, 204, 51));

        symdec.setBackground(new java.awt.Color(255, 204, 51));
        symdec.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        symdec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Symptômes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        symdec.setRowHeight(32);
        symdec.setSelectionBackground(new java.awt.Color(255, 102, 0));
        symdec.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane6.setViewportView(symdec);

        jLabel74.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Symptômes Decouvert");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(0, 173, Short.MAX_VALUE))
                    .addComponent(jSeparator26))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator26, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 204, 51));

        maldec.setBackground(new java.awt.Color(255, 204, 51));
        maldec.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        maldec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Maladies"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        maldec.setRowHeight(32);
        maldec.setSelectionBackground(new java.awt.Color(255, 102, 0));
        maldec.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jScrollPane7.setViewportView(maldec);

        jLabel75.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Maladies Decouvert");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator27))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator27, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout infoconsLayout = new javax.swing.GroupLayout(infocons);
        infocons.setLayout(infoconsLayout);
        infoconsLayout.setHorizontalGroup(
            infoconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoconsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(infoconsLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        infoconsLayout.setVerticalGroup(
            infoconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoconsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoconsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlRight.add(infocons, "card6");

        javax.swing.GroupLayout addprevaideLayout = new javax.swing.GroupLayout(addprevaide);
        addprevaide.setLayout(addprevaideLayout);
        addprevaideLayout.setHorizontalGroup(
            addprevaideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );
        addprevaideLayout.setVerticalGroup(
            addprevaideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
        );

        pnlRight.add(addprevaide, "card7");

        jPanel14.setBackground(new java.awt.Color(41, 168, 73));

        jLabel71.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Ajouter Un Échantillon ");

        jSeparator33.setForeground(new java.awt.Color(51, 153, 0));

        jLabel81.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Type d'échantillon :");

        adn.setBackground(new java.awt.Color(41, 168, 73));
        adn.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        adn.setForeground(new java.awt.Color(255, 255, 255));
        adn.setText("ADN");
        adn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                adnItemStateChanged(evt);
            }
        });
        adn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adnActionPerformed(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("Nombre d'échantillon :");

        jLabel83.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Code d'échantillon :");

        jLabel84.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("Numéro congélateur :");

        jLabel85.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Numéro tiroire :");

        jLabel86.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Numéro boite :");

        jLabel87.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Emplacement dans la boite :");

        jLabel88.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Volume d'échantillon :");

        jLabel89.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Remarque :");

        arn.setBackground(new java.awt.Color(41, 168, 73));
        arn.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        arn.setForeground(new java.awt.Color(255, 255, 255));
        arn.setText("ARN");
        arn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                arnItemStateChanged(evt);
            }
        });
        arn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arnActionPerformed(evt);
            }
        });

        plasma.setBackground(new java.awt.Color(41, 168, 73));
        plasma.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        plasma.setForeground(new java.awt.Color(255, 255, 255));
        plasma.setText("Plasma");
        plasma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                plasmaItemStateChanged(evt);
            }
        });
        plasma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plasmaActionPerformed(evt);
            }
        });

        echautre.setBackground(new java.awt.Color(41, 168, 73));
        echautre.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        echautre.setForeground(new java.awt.Color(255, 255, 255));
        echautre.setText("Autre");
        echautre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                echautreItemStateChanged(evt);
            }
        });
        echautre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                echautreActionPerformed(evt);
            }
        });

        consvalider3.setBackground(new java.awt.Color(41, 168, 73));
        consvalider3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        consvalider3.setForeground(new java.awt.Color(255, 255, 255));
        consvalider3.setLabel("Ajouter l'Échantillon");
        consvalider3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consvalider3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 343, Short.MAX_VALUE))
                            .addComponent(jSeparator33)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel84)
                            .addComponent(jLabel85)
                            .addComponent(jLabel86)
                            .addComponent(jLabel82)
                            .addComponent(jLabel81)
                            .addComponent(jLabel83)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel87)
                                .addComponent(jLabel88, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(adn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(adnboitevol)
                                    .addComponent(adnempboite)
                                    .addComponent(adnboite)
                                    .addComponent(adntir)
                                    .addComponent(adncong)
                                    .addComponent(adncode)
                                    .addComponent(adnnbr)
                                    .addComponent(adnrem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(arn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(arnboitevol)
                                    .addComponent(arnempboite)
                                    .addComponent(arnboite)
                                    .addComponent(arntir)
                                    .addComponent(arncong)
                                    .addComponent(arncode)
                                    .addComponent(arnnbr)
                                    .addComponent(arnrem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(consvalider3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(plasma, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plasmaboitevol)
                            .addComponent(plasmaempboite)
                            .addComponent(plasmaboite)
                            .addComponent(plasmatir)
                            .addComponent(plasmacong)
                            .addComponent(plasmacode)
                            .addComponent(plasmanbr)
                            .addComponent(plasmarem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(echautre, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(autreboitevol)
                            .addComponent(autreempboite)
                            .addComponent(autreboite)
                            .addComponent(autretir)
                            .addComponent(autrecong)
                            .addComponent(autrecode)
                            .addComponent(autrenbr)
                            .addComponent(autrerem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(adn)
                    .addComponent(arn)
                    .addComponent(plasma)
                    .addComponent(echautre))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(adnnbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arnnbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmanbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autrenbr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(adncode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arncode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmacode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autrecode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(adncong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arncong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmacong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autrecong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(adntir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arntir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmatir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autretir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(adnboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arnboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmaboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autreboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(adnempboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arnempboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmaempboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autreempboite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(adnboitevol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arnboitevol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmaboitevol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autreboitevol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adnrem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89)
                    .addComponent(arnrem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmarem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(autrerem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(consvalider3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout echanLayout = new javax.swing.GroupLayout(echan);
        echan.setLayout(echanLayout);
        echanLayout.setHorizontalGroup(
            echanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(echanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        echanLayout.setVerticalGroup(
            echanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(echanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(echan, "card7");

        jPanel15.setBackground(new java.awt.Color(41, 168, 73));

        jLabel77.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Ajouter Un Prélèvement");

        jSeparator34.setForeground(new java.awt.Color(51, 153, 0));

        jLabel78.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Type Prélèvement :");

        s.setBackground(new java.awt.Color(41, 168, 73));
        s.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        s.setForeground(new java.awt.Color(255, 255, 255));
        s.setText("Sang");
        s.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sItemStateChanged(evt);
            }
        });
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });

        t.setBackground(new java.awt.Color(41, 168, 73));
        t.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        t.setForeground(new java.awt.Color(255, 255, 255));
        t.setText("Tissu");
        t.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tItemStateChanged(evt);
            }
        });
        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tActionPerformed(evt);
            }
        });

        ee.setBackground(new java.awt.Color(41, 168, 73));
        ee.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        ee.setForeground(new java.awt.Color(255, 255, 255));
        ee.setText("Écouvillon");
        ee.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                eeItemStateChanged(evt);
            }
        });
        ee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eeActionPerformed(evt);
            }
        });

        bb.setBackground(new java.awt.Color(41, 168, 73));
        bb.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        bb.setForeground(new java.awt.Color(255, 255, 255));
        bb.setText("Biopsie");
        bb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                bbItemStateChanged(evt);
            }
        });
        bb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bbActionPerformed(evt);
            }
        });

        aa.setBackground(new java.awt.Color(41, 168, 73));
        aa.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        aa.setForeground(new java.awt.Color(255, 255, 255));
        aa.setText("Autre");
        aa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                aaItemStateChanged(evt);
            }
        });
        aa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aaActionPerformed(evt);
            }
        });

        jSeparator35.setForeground(new java.awt.Color(255, 255, 255));

        jLabel79.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Lieu du Prélèvement :");

        jSeparator36.setForeground(new java.awt.Color(255, 255, 255));

        jLabel80.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Réception du Prélèvement :");

        jSeparator37.setForeground(new java.awt.Color(255, 255, 255));

        jLabel90.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("CNI Patient :");

        jSeparator38.setForeground(new java.awt.Color(255, 255, 255));

        jLabel91.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Nombre de Prélèvement :");

        jLabel92.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Volume départ (ml) :");

        consvalider4.setBackground(new java.awt.Color(41, 168, 73));
        consvalider4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        consvalider4.setForeground(new java.awt.Color(255, 255, 255));
        consvalider4.setLabel("Ajouter des Échantillons ");
        consvalider4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consvalider4ActionPerformed(evt);
            }
        });

        consvalider5.setBackground(new java.awt.Color(41, 168, 73));
        consvalider5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        consvalider5.setForeground(new java.awt.Color(255, 255, 255));
        consvalider5.setLabel("Ajouter le Prélèvement");
        consvalider5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consvalider5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jSeparator35))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(aa)
                                                            .addComponent(bb))
                                                        .addGap(44, 44, 44)
                                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(bio)
                                                            .addComponent(autre, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                                        .addComponent(ee)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(eco, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(tissu, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(b)
                                                    .addComponent(d)
                                                    .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(s)
                                                    .addComponent(t))
                                                .addGap(60, 60, 60)
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel91, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(sang, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(a)
                                                    .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jSeparator37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(rprel, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jSeparator36, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jSeparator38, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lieuprel, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cniprel, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jSeparator34)
                        .addContainerGap())))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(consvalider4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(consvalider5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s)
                            .addComponent(sang, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t)
                            .addComponent(tissu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ee))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bb, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(autre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aa))))
                .addGap(66, 66, 66)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(cniprel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lieuprel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(rprel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consvalider5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(consvalider4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        javax.swing.GroupLayout addprLayout = new javax.swing.GroupLayout(addpr);
        addpr.setLayout(addprLayout);
        addprLayout.setHorizontalGroup(
            addprLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addprLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        addprLayout.setVerticalGroup(
            addprLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addprLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(addpr, "card9");

        patientnamee.setBackground(new java.awt.Color(41, 168, 73));

        jLabel55.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Information du Patient");

        jLabel57.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Nom et Prénom du Patient :");

        patientname.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientname.setForeground(new java.awt.Color(255, 255, 0));
        patientname.setText("HMOURA MOSTAFA MOSTAFA");

        patientcni.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientcni.setForeground(new java.awt.Color(255, 255, 0));
        patientcni.setText("F12345");

        jLabel59.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("CIN  :");

        patientdateniassance.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientdateniassance.setForeground(new java.awt.Color(255, 255, 0));
        patientdateniassance.setText("18-07-1998");

        jLabel61.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Date de Naissance :");

        jLabel63.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Lieu de Naissance :");

        patientlieu.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientlieu.setForeground(new java.awt.Color(255, 255, 0));
        patientlieu.setText("18-07-1998");

        jLabel67.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Sexe  :");

        patientsexe.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientsexe.setForeground(new java.awt.Color(255, 255, 0));
        patientsexe.setText("Homme");

        jLabel69.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Numéro de Téléphone :");

        patientnumtele.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientnumtele.setForeground(new java.awt.Color(255, 255, 0));
        patientnumtele.setText("0611010114");

        jLabel76.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Nationalité :");

        patientnationnaliter.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientnationnaliter.setForeground(new java.awt.Color(255, 255, 0));
        patientnationnaliter.setText("Maroccain");

        patientvillee.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientvillee.setForeground(new java.awt.Color(255, 255, 0));
        patientvillee.setText("Oujda");

        jLabel93.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("Ville  :");

        jLabel94.setFont(new java.awt.Font("Segoe UI Semilight", 1, 14)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("Adresse :");

        patientadr.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        patientadr.setForeground(new java.awt.Color(255, 255, 0));
        patientadr.setText("Lieu dit ou service particulier de distribution – Poste ");

        javax.swing.GroupLayout patientnameeLayout = new javax.swing.GroupLayout(patientnamee);
        patientnamee.setLayout(patientnameeLayout);
        patientnameeLayout.setHorizontalGroup(
            patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientnameeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator28)
                    .addGroup(patientnameeLayout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(patientnameeLayout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(patientdateniassance, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(patientlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patientnameeLayout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(patientsexe, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(patientnameeLayout.createSequentialGroup()
                                .addComponent(jLabel93)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(patientvillee, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE))
                    .addGroup(patientnameeLayout.createSequentialGroup()
                        .addGroup(patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(patientnameeLayout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(patientname, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel59))
                            .addGroup(patientnameeLayout.createSequentialGroup()
                                .addComponent(jLabel69)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(patientnumtele, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(patientnationnaliter, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(patientnameeLayout.createSequentialGroup()
                                .addComponent(jLabel94)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(patientadr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(patientcni, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        patientnameeLayout.setVerticalGroup(
            patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientnameeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientname, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientcni, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientdateniassance, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientsexe, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientvillee, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientnumtele, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientnationnaliter, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(patientnameeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patientadr, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(41, 168, 73));

        jLabel95.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setText("Consultations");

        jScrollPane8.setBorder(null);

        tableconspatient.setBackground(new java.awt.Color(41, 168, 73));
        tableconspatient.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        tableconspatient.setForeground(new java.awt.Color(255, 255, 255));
        tableconspatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "motif de consultation", "Date de consultation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableconspatient.setGridColor(new java.awt.Color(255, 255, 255));
        tableconspatient.setRowHeight(25);
        tableconspatient.setSelectionBackground(new java.awt.Color(255, 255, 0));
        tableconspatient.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane8.setViewportView(tableconspatient);

        jLabel96.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("Échantillon");

        tableechspatient.setBackground(new java.awt.Color(41, 168, 73));
        tableechspatient.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        tableechspatient.setForeground(new java.awt.Color(255, 255, 255));
        tableechspatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date de Prélèvement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableechspatient.setGridColor(new java.awt.Color(255, 255, 255));
        tableechspatient.setRowHeight(25);
        tableechspatient.setSelectionBackground(new java.awt.Color(255, 255, 0));
        tableechspatient.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tableechspatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableechspatientMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tableechspatient);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator29)
                    .addComponent(jScrollPane8)
                    .addComponent(jSeparator30)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel95)
                            .addComponent(jLabel96))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane10))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout patientLayout = new javax.swing.GroupLayout(patient);
        patient.setLayout(patientLayout);
        patientLayout.setHorizontalGroup(
            patientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(patientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientnamee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        patientLayout.setVerticalGroup(
            patientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(patientnamee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(patient, "card10");

        jPanel12.setBackground(new java.awt.Color(41, 168, 73));

        jLabel97.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("Entrer Le Nom du Patient :");

        jLabel98.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel98MouseClicked(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel98)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                            .addComponent(jTextField2))))
                .addContainerGap())
        );

        listpatientp1.setBackground(new java.awt.Color(41, 168, 73));
        listpatientp1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        listpatientp1.setForeground(new java.awt.Color(255, 255, 255));
        listpatientp1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CIN", "Nom et Prénom", "Date de Naissance", "Sexe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        listpatientp1.setGridColor(new java.awt.Color(255, 255, 255));
        listpatientp1.setRowHeight(34);
        listpatientp1.setSelectionBackground(new java.awt.Color(0, 153, 0));
        listpatientp1.setShowVerticalLines(false);
        listpatientp1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listpatientp1MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(listpatientp1);
        if (listpatientp1.getColumnModel().getColumnCount() > 0) {
            listpatientp1.getColumnModel().getColumn(0).setMinWidth(130);
            listpatientp1.getColumnModel().getColumn(0).setMaxWidth(130);
            listpatientp1.getColumnModel().getColumn(2).setMinWidth(170);
            listpatientp1.getColumnModel().getColumn(2).setMaxWidth(170);
            listpatientp1.getColumnModel().getColumn(3).setMinWidth(130);
            listpatientp1.getColumnModel().getColumn(3).setMaxWidth(130);
        }

        javax.swing.GroupLayout chercherLayout = new javax.swing.GroupLayout(chercher);
        chercher.setLayout(chercherLayout);
        chercherLayout.setHorizontalGroup(
            chercherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chercherLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chercherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE))
                .addContainerGap())
        );
        chercherLayout.setVerticalGroup(
            chercherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chercherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(chercher, "card11");

        help.setBackground(new java.awt.Color(41, 168, 73));

        jLabel99.setBackground(new java.awt.Color(41, 168, 73));
        jLabel99.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 0));
        jLabel99.setText("BioBanque Application de gestion de BioBanque");

        jLabel100.setBackground(new java.awt.Color(41, 168, 73));
        jLabel100.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 0));
        jLabel100.setText("L'application Biobanque est une application destinée ");

        jLabel101.setBackground(new java.awt.Color(41, 168, 73));
        jLabel101.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 0));
        jLabel101.setText("aux médecins hospitaliers");

        jLabel102.setBackground(new java.awt.Color(41, 168, 73));
        jLabel102.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 0));
        jLabel102.setText("Dans cette application, le médecin peut faire :");

        jLabel103.setBackground(new java.awt.Color(41, 168, 73));
        jLabel103.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 0));
        jLabel103.setText("Ajouter un Patient.");

        jLabel104.setBackground(new java.awt.Color(41, 168, 73));
        jLabel104.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 0));
        jLabel104.setText("Ajouter Une Consultation d'un Patient.");

        jLabel105.setBackground(new java.awt.Color(41, 168, 73));
        jLabel105.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 0));
        jLabel105.setText("Voir La Liste des Patients.");

        jLabel106.setBackground(new java.awt.Color(41, 168, 73));
        jLabel106.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 0));
        jLabel106.setText("Ajouter un prélevement et un échantillon d'un patient.");

        jLabel107.setBackground(new java.awt.Color(41, 168, 73));
        jLabel107.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 0));
        jLabel107.setText("Chercher Un Patient.");

        jSeparator31.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel108.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/casaandra.png"))); // NOI18N

        jLabel109.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/java.png"))); // NOI18N

        javax.swing.GroupLayout helpLayout = new javax.swing.GroupLayout(help);
        help.setLayout(helpLayout);
        helpLayout.setHorizontalGroup(
            helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(helpLayout.createSequentialGroup()
                        .addComponent(jSeparator31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(helpLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helpLayout.createSequentialGroup()
                                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))
                                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        helpLayout.setVerticalGroup(
            helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator31, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(helpLayout.createSequentialGroup()
                        .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel101)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel102)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(helpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pnlRight.add(help, "card12");

        pnlParent.add(pnlRight, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlParent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    int xy, xx;
    private void sidepaneMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidepaneMouseDragged
        // TODO add your handling code here:

    }//GEN-LAST:event_sidepaneMouseDragged

    private void sidepaneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidepaneMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_sidepaneMousePressed

    private void btn_typoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_typoMousePressed
        // TODO add your handling code here:

        setColor(btn_typo);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        ind_typo.setOpaque(true);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_data2.setOpaque(false);
        resetColor(btn_data2);
        
        ind_data2.setOpaque(false);
        resetColor(btn_data2);
        ind_data1.setOpaque(false);
        resetColor(btn_data1);
        
        remplirtabacceuil();
        cardLayout.show(pnlRight, "card2");

    }//GEN-LAST:event_btn_typoMousePressed

    private void btn_fontsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fontsMousePressed
        // TODO add your handling code here:
        setColor(btn_fonts);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);

        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(true);
        ind_icons.setOpaque(false);
        
        ind_data1.setOpaque(false);
        resetColor(btn_data1);
        
        remplirtabacceuil();
        tableaumespatient();
        cardLayout.show(pnlRight, "card1");

    }//GEN-LAST:event_btn_fontsMousePressed

    private void btn_iconsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_iconsMousePressed
        // TODO add your handling code here:
        setColor(btn_icons);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_typo);

        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(true);
        
        ind_data2.setOpaque(false);
        resetColor(btn_data2);
        
        ind_data1.setOpaque(false);
        resetColor(btn_data1);
        
        cardLayout.show(pnlRight, "card4");

        sym.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        sym.getTableHeader().setOpaque(false);
        sym.getTableHeader().setBackground(new Color(41, 168, 73));
        sym.getTableHeader().setForeground(new Color(255, 255, 255));

        mal.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        mal.getTableHeader().setOpaque(false);
        mal.getTableHeader().setBackground(new Color(41, 168, 73));
        mal.getTableHeader().setForeground(new Color(255, 255, 255));

        tablerem();
        tableremmal();
        
        

    }//GEN-LAST:event_btn_iconsMousePressed

    private void btn_btnsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_btnsMousePressed
        // TODO add your handling code here:
        setColor(btn_btns);
        resetColor(btn_data);
        resetColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);

        ind_typo.setOpaque(false);
        ind_btns.setOpaque(true);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_data2.setOpaque(false);
        resetColor(btn_data2);
        ind_data1.setOpaque(false);
        resetColor(btn_data1);
        
        tableaumespatient();

        cardLayout.show(pnlRight, "card5");
    }//GEN-LAST:event_btn_btnsMousePressed

    private void btn_dataMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dataMousePressed
        // TODO add your handling code here:
        setColor(btn_data);
        resetColor(btn_typo);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        
        ind_data2.setOpaque(false);
        resetColor(btn_data2);
        
        ind_data1.setOpaque(false);
        resetColor(btn_data1);
        
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(true);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        //decc();
        
        PlaceHolder p1 = new PlaceHolder(sang,"   Nombre de Prélèvement");
        PlaceHolder pa = new PlaceHolder(a,"   Volume départ (ml)");
        sang.setForeground(Color.BLACK);
        a.setForeground(Color.BLACK);
        sang.setEditable(false);
        a.setEditable(false);
        
        PlaceHolder p2 = new PlaceHolder(tissu,"   Nombre de Prélèvement");
        PlaceHolder pb = new PlaceHolder(b,"   Volume départ (ml)");
        tissu.setForeground(Color.BLACK);
        b.setForeground(Color.BLACK);
        tissu.setEditable(false);
        b.setEditable(false);
        
        PlaceHolder p3 = new PlaceHolder(eco,"   Nombre de Prélèvement");
        PlaceHolder pc = new PlaceHolder(c,"   Volume départ (ml)");
        eco.setForeground(Color.BLACK);
        c.setForeground(Color.BLACK);
        eco.setEditable(false);
        c.setEditable(false);
        
        PlaceHolder p4 = new PlaceHolder(bio,"   Nombre de Prélèvement");
        PlaceHolder pd = new PlaceHolder(d,"   Volume départ (ml)");
        bio.setForeground(Color.BLACK);
        d.setForeground(Color.BLACK);
        bio.setEditable(false);
        d.setEditable(false);
        
        PlaceHolder p5 = new PlaceHolder(autre,"   Nombre de Prélèvement");
        PlaceHolder pe = new PlaceHolder(e,"   Volume départ (ml)");
        autre.setForeground(Color.BLACK);
        e.setForeground(Color.BLACK);
        autre.setEditable(false);
        e.setEditable(false);
        
        
        
        cardLayout.show(pnlRight, "card9");
           
    }//GEN-LAST:event_btn_dataMousePressed


    private void lblMaximizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaximizeMousePressed
        if (HomeMedecin.this.getExtendedState() == MAXIMIZED_BOTH) {
            HomeMedecin.this.setExtendedState(JFrame.NORMAL);
        } else {
            HomeMedecin.this.setExtendedState(MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_lblMaximizeMousePressed

    private void lblCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMousePressed
        System.exit(0);
    }//GEN-LAST:event_lblCloseMousePressed

    private void lblMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMousePressed
        HomeMedecin.this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMousePressed

    private void pnlTopMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_pnlTopMousePressed

    private void pnlTopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlTopMouseDragged

    private void pnlTopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTopMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            if (HomeMedecin.this.getExtendedState() == MAXIMIZED_BOTH) {
                HomeMedecin.this.setExtendedState(JFrame.NORMAL);
            } else {
                HomeMedecin.this.setExtendedState(MAXIMIZED_BOTH);
            }
        }
    }//GEN-LAST:event_pnlTopMouseClicked

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed

        jRadioButton1.setSelected(false);

    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jRadioButton2.setSelected(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed

         //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //Date newDate = new Date();
        //System.out.println(dateFormat.format(patientdate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        //Date date = new SimpleDateFormat("MM-dd-YYYY").parse(patientdate.getDate());
        //System.out.println(">>>"+patientdate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        //System.out.println(">>>"+patientdate.getDate());
        //System.out.println(">>>"+jRadioButton1.isSelected());
        
            if (        patientcin.getText().equals("")
                    || patientadresse.getText().equals("")
                    || patientemail.getText().equals("")
                    || patientlieunaiss.getText().equals("")
                    || patientmere.getText().equals("")
                    || patientnatin.getText().equals("")
                    || patientpere.getText().equals("")
                    || patientnom.getText().equals("")
                    || patientpnom.getText().equals("")
                    || patientproffession.getText().equals("")
                    || patienttele.getText().equals("")
                    || patientville.getText().equals("")
                    || patientcin.getText().equals("")
                    || patientdate.getDate() == null) {
                System.out.println("Salam 1");
                JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
            } else {
                ajouterpatient();
                System.out.println("Salam 2");
            }
        


    }//GEN-LAST:event_validerActionPerformed

    public void ajouterpatient() {

        String sexe = jRadioButton1.isSelected() ? jRadioButton1.getText() : jRadioButton2.getText();
        Date date = patientdate.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        String cqlStatementt = "INSERT INTO patient (cne,nom,prenom,sexe,date_naissance,"
                + "lieu_naissance,nationnalite,email,telephone,"
                + "ville,address,profession,origine_mere,origine_pere,medc_cne)"
                + "VALUES ('" + patientcin.getText() + "',"
                + " '" + patientnom.getText() + "',"
                + "'" + patientpnom.getText() + "',"
                + "'" + sexe + "',"
                + " '" + strDate + "',"
                + "'" + patientlieunaiss.getText() + "',"
                + " '" + patientnatin.getText() + "',"
                + " '" + patientemail.getText() + "',"
                + "" + patienttele.getText() + ","
                + " '" + patientville.getText() + "',"
                + " '" + patientadresse.getText() + "',"
                + " '" + patientproffession.getText() + "',"
                + " '" + patientmere.getText() + "',"
                + " '" + patientpere.getText() + "',"
                + " '" + id + "');";
        
        String tr = patientnom.getText().toUpperCase()+" "+patientpnom.getText().toUpperCase()+" Ajouté avec succès";

        try {
            
            session.execute(cqlStatementt);
            
            JOptionPane.showMessageDialog(this, tr);
            
            remplirtabacceuil();
            
            setColor(btn_typo);
            resetColor(btn_data);
            resetColor(btn_btns);
            resetColor(btn_fonts);
            resetColor(btn_icons);
            ind_typo.setOpaque(true);
            ind_btns.setOpaque(false);
            ind_data.setOpaque(false);
            ind_fonts.setOpaque(false);
            ind_icons.setOpaque(false);
            
            patientcin.setText("");
            patientadresse.setText("");
            patientdate.setDate(null);
            patientemail.setText("");
            patientlieunaiss.setText("");
            patientmere.setText("");
            patientnatin.setText("");
            patientpere.setText("");
            patientnom.setText("");
            patientpnom.setText("");
            patientproffession.setText("");
            patienttele.setText("");
            patientville.setText("");
            patientcin.setText("");
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
            
            cardLayout.show(pnlRight, "card2");
            
        } catch (Exception e) {
            System.out.println("err");
            
        }

        System.out.println(cqlStatementt);
    }

    public void tablerem() {

        String cqlStatement = " select * from symptome ;";
        DefaultTableModel yourModel = (DefaultTableModel) sym.getModel();
        try {
            for (Row n : session.execute(cqlStatement)) {
                yourModel.addRow(new Object[]{n.getString(1)});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
    }

    public void tableremmal() {

        String cqlStatement = " select * from maladies ;";
        DefaultTableModel yourModel = (DefaultTableModel) mal.getModel();
        try {
            for (Row n : session.execute(cqlStatement)) {
                yourModel.addRow(new Object[]{n.getString(1)});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
    }


    private void patientcinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientcinFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientcinFocusGained

    private void patientcinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientcinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientcinActionPerformed

    private void patientnomFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientnomFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientnomFocusGained

    private void patientnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientnomActionPerformed

    private void patientpnomFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientpnomFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientpnomFocusGained

    private void patientpnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientpnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientpnomActionPerformed

    private void patientlieunaissFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientlieunaissFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientlieunaissFocusGained

    private void patientlieunaissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientlieunaissActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientlieunaissActionPerformed

    private void patientnatinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientnatinFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientnatinFocusGained

    private void patientnatinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientnatinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientnatinActionPerformed

    private void patientemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientemailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientemailFocusGained

    private void patientemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientemailActionPerformed

    private void patientteleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientteleFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientteleFocusGained

    private void patientteleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientteleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientteleActionPerformed

    private void patientvilleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientvilleFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientvilleFocusGained

    private void patientvilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientvilleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientvilleActionPerformed

    private void patientadresseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientadresseFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientadresseFocusGained

    private void patientadresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientadresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientadresseActionPerformed

    private void patientproffessionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientproffessionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientproffessionFocusGained

    private void patientproffessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientproffessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientproffessionActionPerformed

    private void patientmereFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientmereFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientmereFocusGained

    private void patientmereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientmereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientmereActionPerformed

    private void patientpereFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientpereFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientpereFocusGained

    private void patientpereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientpereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientpereActionPerformed

    private void conscniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conscniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conscniActionPerformed

    private void consdescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consdescActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consdescActionPerformed

    private void constailleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_constailleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_constailleActionPerformed

    private void conspoidsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conspoidsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conspoidsActionPerformed

    private void constentionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_constentionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_constentionActionPerformed

    private void constmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_constmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_constmpActionPerformed

    private void symMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_symMouseClicked

    }//GEN-LAST:event_symMouseClicked

    private void consvaliderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consvaliderActionPerformed

        

        if (!(consdate.getDate() == null)) {
            if (conscni.getText().equals("")
                    || consdesc.getText().equals("")
                    || conspoids.getText().equals("")
                    || constaille.getText().equals("")
                    || constention.getText().equals("")
                    || constmp.getText().equals("")) {
                System.out.println("Salam");
                JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
            } else {
                ajoutercons();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
        }


    }//GEN-LAST:event_consvaliderActionPerformed

    public void ajoutercons() {

        Date date = consdate.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);

        String cqlStatementt = "INSERT INTO consultation (id_c ,cni_patient,date_c,desc_c)"
                + " VALUES ( uuid(),"
                + "  '" + conscni.getText() + "',"
                + " '" + strDate + "',"
                + "'" + consdesc.getText() + "');";

        String cql = "select * from consultation where cni_patient='" + conscni.getText() + "' allow filtering ;";

        try {
            session.execute(cqlStatementt);

        } catch (Exception e) {
            System.out.println("err");
        }

        System.out.println(cqlStatementt);
        System.out.println(cql);

        try {

            final Row row = session.execute(cql).one();

            String cqlinsert = "INSERT INTO dn_clinique (id_dn , num_consultation ,poid_patient,taille,tempuratur,tension) "
                    + "VALUES ( uuid(),"
                    + "'" + row.getUUID(0) + "',"
                    + "  '" + conspoids.getText() + "',"
                    + "  '" + constaille.getText() + "',"
                    + "  '" + constmp.getText() + "',"
                    + "  '" + constention.getText() + "') ;";

            session.execute(cqlinsert);

        } catch (Exception e) {
            System.out.println("err insertt in clinique");
        }
        
        
        
        
        String selectedData = null;
        int[] selection = sym.getSelectedRows();

        for (int i = 0; i < selection.length; i++) {

            selectedData = (String) sym.getValueAt(selection[i], 0);

            String cqlj = "select * from symptome where type_s='" + selectedData + "' allow filtering ;";
            System.out.println(cqlj);
            try {
                final Row row = session.execute(cqlj).one();

                String cqlinsert = "INSERT INTO aide_sym ( id ,id_sym ,cni ) "
                        + "VALUES ( uuid(),"
                        + "'" + row.getUUID(0) + "',"
                        + "  '" + conscni.getText() + "') ;";

                session.execute(cqlinsert);

            } catch (Exception e) {
                System.out.println("err");
            }

            System.out.println(selectedData);
        }

        String selectedDataa = null;
        int[] selectionn = mal.getSelectedRows();

        for (int i = 0; i < selectionn.length; i++) {

            selectedDataa = (String) mal.getValueAt(selectionn[i], 0);

            String cqli = "select * from maladies where nom='" + selectedDataa + "' allow filtering ;";
            
            
            String trr = "Consultation de "+conscni.getText().toUpperCase()+"  Ajouté avec succès";
            System.out.println(cqli);
            try {
                final Row row = session.execute(cqli).one();

                String cqlinsert = "INSERT INTO aide_mal ( id ,id_mal ,cni ) "
                        + "VALUES ( uuid(),"
                        + "'" + row.getUUID(0) + "',"
                        + "  '" + conscni.getText() + "') ;";

                session.execute(cqlinsert);
                
            JOptionPane.showMessageDialog(this, trr);
            
            remplirtabacceuil();
            
            setColor(btn_typo);
            resetColor(btn_data);
            resetColor(btn_btns);
            resetColor(btn_fonts);
            resetColor(btn_icons);
            ind_typo.setOpaque(true);
            ind_btns.setOpaque(false);
            ind_data.setOpaque(false);
            ind_fonts.setOpaque(false);
            ind_icons.setOpaque(false);
            
            conscni.setText("");
            consdesc.setText("");
            consdate.setDate(null);
            conspoids.setText("");
            constaille.setText("");
            constention.setText("");
            constmp.setText("");
            
            mal.getSelectionModel().clearSelection();
            sym.getSelectionModel().clearSelection();
                    
            cardLayout.show(pnlRight, "card2");

            } catch (Exception e) {
                System.out.println("err");
            }

            System.out.println(selectedDataa);
        }

    }


    private void malMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_malMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_malMouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked
        
        
        // TODO add your handling code here:
        setColor(btn_fonts);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);

        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(true);
        ind_icons.setOpaque(false);
        
        cardLayout.show(pnlRight, "card1");
        
    }//GEN-LAST:event_jLabel48MouseClicked

    private void btn_data1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_data1MousePressed
        
        setColor(btn_data1);
        resetColor(btn_data);
        resetColor(btn_typo);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_data2);
        
        ind_data2.setOpaque(false);
        
        
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_data1.setOpaque(true);
        
        cardLayout.show(pnlRight, "card12");
        
    }//GEN-LAST:event_btn_data1MousePressed

    private void jLabel52MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel52MouseClicked
      
        
        setColor(btn_fonts);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);

        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(true);
        ind_icons.setOpaque(false);
        
        cardLayout.show(pnlRight, "card1");
        
    }//GEN-LAST:event_jLabel52MouseClicked

    private void jLabel50MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel50MouseClicked
        
        
        setColor(btn_icons);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_typo);

        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(true);

        cardLayout.show(pnlRight, "card4");

        sym.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        sym.getTableHeader().setOpaque(false);
        sym.getTableHeader().setBackground(new Color(41, 168, 73));
        sym.getTableHeader().setForeground(new Color(255, 255, 255));

        mal.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        mal.getTableHeader().setOpaque(false);
        mal.getTableHeader().setBackground(new Color(41, 168, 73));
        mal.getTableHeader().setForeground(new Color(255, 255, 255));

        tablerem();
        tableremmal();
        
        
        
    }//GEN-LAST:event_jLabel50MouseClicked

    private void listeconsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listeconsMouseClicked
        
        
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        String s=source.getModel().getValueAt(row, 0)+"";
        
        System.out.println(">>>"+s);
        
        dec(s);
        
        resetColor(btn_typo);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
       
        
        cardLayout.show(pnlRight, "card6");
        
        source.getSelectionModel().clearSelection();
       
        
        
    }//GEN-LAST:event_listeconsMouseClicked

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        
        setColor(btn_icons);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_typo);

        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(true);

        cardLayout.show(pnlRight, "card4");

        sym.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        sym.getTableHeader().setOpaque(false);
        sym.getTableHeader().setBackground(new Color(41, 168, 73));
        sym.getTableHeader().setForeground(new Color(255, 255, 255));

        mal.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        mal.getTableHeader().setOpaque(false);
        mal.getTableHeader().setBackground(new Color(41, 168, 73));
        mal.getTableHeader().setForeground(new Color(255, 255, 255));

        tablerem();
        tableremmal();
        
        
        
    }//GEN-LAST:event_jLabel64MouseClicked

    private void btn_data2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_data2MousePressed
        
        setColor(btn_data2);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_typo);

        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_data2.setOpaque(true);
        ind_data1.setOpaque(false);
        resetColor(btn_data1);
                
        tabchercher();
        
        cardLayout.show(pnlRight, "card11");
    }//GEN-LAST:event_btn_data2MousePressed

    public void tabchercher(){
        
        listpatientp1.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        listpatientp1.getTableHeader().setOpaque(false);
        listpatientp1.getTableHeader().setBackground(new Color(41,168,73));
        listpatientp1.getTableHeader().setForeground(new Color(255, 255, 255));
        
        

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) listpatientp1.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        String cql = " select * from patient ;";
        DefaultTableModel yourModel = (DefaultTableModel) listpatientp1.getModel();
        try {
            for (Row n : session.execute(cql)) {
                yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(7).toUpperCase()+" "+n.getString(10).toUpperCase(),n.getDate(2),n.getString(12).toUpperCase()});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
        
    }
    
    private void consvalider3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consvalider3ActionPerformed

            ech(cniprel.getText());

    }//GEN-LAST:event_consvalider3ActionPerformed

    private void echautreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_echautreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_echautreActionPerformed

    private void plasmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plasmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plasmaActionPerformed

    private void arnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_arnActionPerformed

    private void adnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adnActionPerformed

    private void sItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sItemStateChanged
        
        
        if(s.isSelected()){
            
            sang.setEditable(true);
            a.setEditable(true);

        }else{

            sang.setEditable(false);
            a.setEditable(false);
        }
        
    }//GEN-LAST:event_sItemStateChanged

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sActionPerformed

    private void tItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tItemStateChanged
        
        if(t.isSelected()){
            
            tissu.setEditable(true);
            b.setEditable(true);

        }else{

            tissu.setEditable(false);
            b.setEditable(false);
        }
        
    }//GEN-LAST:event_tItemStateChanged

    private void tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tActionPerformed

    private void eeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_eeItemStateChanged
        
        if(ee.isSelected()){
            
            eco.setEditable(true);
            c.setEditable(true);

        }else{

            eco.setEditable(false);
            c.setEditable(false);
        }
        
    }//GEN-LAST:event_eeItemStateChanged

    private void eeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eeActionPerformed

    private void bbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_bbItemStateChanged
        
         if(bb.isSelected()){
            
            bio.setEditable(true);
            d.setEditable(true);

        }else{

            bio.setEditable(false);
            d.setEditable(false);
        }
        
        
    }//GEN-LAST:event_bbItemStateChanged

    private void bbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bbActionPerformed

    private void aaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_aaItemStateChanged
        
         if(aa.isSelected()){
            
            autre.setEditable(true);
            e.setEditable(true);

        }else{

            autre.setEditable(false);
            e.setEditable(false);
        }
        
    }//GEN-LAST:event_aaItemStateChanged

    private void aaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aaActionPerformed

    private void consvalider4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consvalider4ActionPerformed
        
        
        if( cniprel.getText().equals("") || lieuprel.getText().equals("") || rprel.getText().equals("") ){

            JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");

        }
        
        else {

            autreboite.setEditable(false);
            autreboitevol.setEditable(false);
            autrecode.setEditable(false);
            autrecong.setEditable(false);
            autreempboite.setEditable(false);
            autrenbr.setEditable(false);
            autrerem.setEditable(false);
            autretir.setEditable(false);

            plasmaboite.setEditable(false);
            plasmaboitevol.setEditable(false);
            plasmacode.setEditable(false);
            plasmacong.setEditable(false);
            plasmaempboite.setEditable(false);
            plasmanbr.setEditable(false);
            plasmarem.setEditable(false);
            plasmatir.setEditable(false);

            arnboite.setEditable(false);
            arnboitevol.setEditable(false);
            arncode.setEditable(false);
            arncong.setEditable(false);
            arnempboite.setEditable(false);
            arnnbr.setEditable(false);
            arnrem.setEditable(false);
            arntir.setEditable(false);

                adnboite.setEditable(false);
                adnboitevol.setEditable(false);
                adncode.setEditable(false);
                adncong.setEditable(false);
                adnempboite.setEditable(false);
                adnnbr.setEditable(false);
                adnrem.setEditable(false);
                adntir.setEditable(false);

            cardLayout.show(pnlRight, "card7");
        }
        
        
        
    }//GEN-LAST:event_consvalider4ActionPerformed

    private void consvalider5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consvalider5ActionPerformed
        
        
        

        if( cniprel.getText().equals("") || lieuprel.getText().equals("") || rprel.getText().equals("") ){

            JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
        }
        else prel();
        
    }//GEN-LAST:event_consvalider5ActionPerformed
     
        
        
     public void prel(){
         
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strdate = dateFormat.format(date);
        
        
        if(!(s.isSelected()) && !(t.isSelected()) && !(ee.isSelected()) && !(bb.isSelected()) && !(aa.isSelected()) ){
            
            JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
            
        }
        else {

         if(s.isSelected()){

                if(s.isSelected() && !(sang.getText().equals("   Nombre de Prélèvement")) && !(a.getText().equals("   Volume départ (ml)")) ) {

                    String cqlinsert = "Insert into prelevement (id,cin_patient,type_prev,nombre_prev,lieu_prev,date_prev,reception_prev,volum_depart)"
                    + "VALUES ( uuid(),"
                    + "'" + cniprel.getText() + "',"
                    + "'" + s.getText() + "',"
                    + "" + sang.getText() + ","
                    + "'" + lieuprel.getText() + "',"
                    + "'" + strdate + "',"
                    + "'" + rprel.getText() + "',"
                    + "'" + a.getText() + "') ;";

                    try {

                        session.execute(cqlinsert);
                        System.out.println(cqlinsert);

                    } catch (Exception e) {
                        System.out.println("err");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                }

            }

            if(t.isSelected()){

                if(t.isSelected() && !(tissu.getText().equals("   Nombre de Prélèvement")) && !(b.getText().equals("   Volume départ (ml)")) ) {

                    String cqlinsert = "Insert into prelevement (id,cin_patient,type_prev,nombre_prev,lieu_prev,date_prev,reception_prev,volum_depart)"
                    + "VALUES ( uuid(),"
                    + "'" + cniprel.getText() + "',"
                    + "'" + t.getText() + "',"
                    + "" + tissu.getText() + ","
                    + "'" + lieuprel.getText() + "',"
                    + "'" + strdate + "',"
                    + "'" + rprel.getText() + "',"
                    + "'" + b.getText() + "') ;";

                    try {

                        session.execute(cqlinsert);
                        System.out.println(cqlinsert);

                    } catch (Exception e) {
                        System.out.println("err");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                }

            }

            if(ee.isSelected()){

                if(ee.isSelected() && !(eco.getText().equals("   Nombre de Prélèvement")) && !(c.getText().equals("   Volume départ (ml)")) ) {

                    String cqlinsert = "Insert into prelevement (id,cin_patient,type_prev,nombre_prev,lieu_prev,date_prev,reception_prev,volum_depart)"
                    + "VALUES ( uuid(),"
                    + "'" + cniprel.getText() + "',"
                    + "'" + "ecouvillon" + "',"
                    + "" + eco.getText() + ","
                    + "'" + lieuprel.getText() + "',"
                    + "'" + strdate + "',"
                    + "'" + rprel.getText() + "',"
                    + "'" + c.getText() + "') ;";

                    try {

                        session.execute(cqlinsert);
                        System.out.println(cqlinsert);

                    } catch (Exception e) {
                        System.out.println("err");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                }

            }

            if(bb.isSelected()){

                if(bb.isSelected() && !(bio.getText().equals("   Nombre de Prélèvement")) && !(d.getText().equals("   Volume départ (ml)")) ) {

                    String cqlinsert = "Insert into prelevement (id,cin_patient,type_prev,nombre_prev,lieu_prev,date_prev,reception_prev,volum_depart)"
                    + "VALUES ( uuid(),"
                    + "'" + cniprel.getText() + "',"
                    + "'" + bb.getText() + "',"
                    + "" + bio.getText() + ","
                    + "'" + lieuprel.getText() + "',"
                    + "'" + strdate + "',"
                    + "'" + rprel.getText() + "',"
                    + "'" + d.getText() + "') ;";

                    try {

                        session.execute(cqlinsert);
                        System.out.println(cqlinsert);

                    } catch (Exception e) {
                        System.out.println("err");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                }

            }

            if(aa.isSelected()){

                if(aa.isSelected() && !(autre.getText().equals("   Nombre de Prélèvement")) && !(e.getText().equals("   Volume départ (ml)")) ) {

                    String cqlinsert = "Insert into prelevement (id,cin_patient,type_prev,nombre_prev,lieu_prev,date_prev,reception_prev,volum_depart)"
                    + "VALUES ( uuid(),"
                    + "'" + cniprel.getText() + "',"
                    + "'" + aa.getText() + "',"
                    + "" + autre.getText() + ","
                    + "'" + lieuprel.getText() + "',"
                    + "'" + strdate + "',"
                    + "'" + rprel.getText() + "',"
                    + "'" + e.getText() + "') ;";

                    try {

                        session.execute(cqlinsert);

                        System.out.println(cqlinsert);

                    } catch (Exception e) {
                        System.out.println("err");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                }

            }
            
            
                String trr = "Prélèvement de "+cniprel.getText().toUpperCase()+"  Ajouté avec succès";
                JOptionPane.showMessageDialog(this, trr);
                
             
                setColor(btn_typo);
                resetColor(btn_data);
                resetColor(btn_btns);
                resetColor(btn_fonts);
                resetColor(btn_icons);
                ind_typo.setOpaque(true);
                ind_btns.setOpaque(false);
                ind_data.setOpaque(false);
                ind_fonts.setOpaque(false);
                ind_icons.setOpaque(false);

                remplirtabacceuil();
                
                cardLayout.show(pnlRight, "card2");
                
                rprel.setText("");
                cniprel.setText("");
                lieuprel.setText("");
                rprel.setText("");
                sang.setText("");
                a.setText("");
                tissu.setText("");
                b.setText("");
                bio.setText("");
                d.setText("");
                autre.setText("");
                e.setText("");
                eco.setText("");
                c.setText("");
                
               aa.setSelected(false);
               bb.setSelected(false);
               ee.setSelected(false);
               t.setSelected(false);
               s.setSelected(false);
            
            
            /*if(i >0) {
                String trr = "Prélèvement de "+cniprel.getText().toUpperCase()+"  Ajouté avec succès";
                JOptionPane.showMessageDialog(this, trr);
                System.out.println("i="+i);
            }*/

        }
     
     }
     
     
     public void ech(String id){
         //String cniechan=cniprel.getText();
        String cniechan=id;
        
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strdate = dateFormat.format(date);
        
        if( (adn.isSelected()) || (arn.isSelected()) || (plasma.isSelected())  || (echautre.isSelected()) )
                {
                   if (adn.isSelected()){

                            if( adnboite.getText().equals("") ||
                                adnboitevol.getText().equals("") ||
                                adncode.getText().equals("") ||
                                adncong.getText().equals("") ||
                                adnempboite.getText().equals("") ||
                                adnnbr.getText().equals("") ||
                                adnrem.getText().equals("") ||
                                adntir.getText().equals("") ){
                                
                                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                                    
                            }
                            else {
                                
                                
                                     String cqlinsert = "INSERT INTO Echantion ( id,cni_patient,type_echant,date_echant,nombr_echant,code_echant,"
                                             + "num_congilateur,num_tiritoire,num_boite,place_boite,volume_echant,remarque) "
                                    + "VALUES ( uuid(),"
                                    + "'" + cniechan + "',"
                                    + "'" + adn.getText() + "',"
                                    + "'" + strdate + "',"
                                    + "" + adnnbr.getText() + ","
                                    + "'" + adncode.getText() + "',"
                                    + "'" + adncong.getText() + "',"
                                    + "'" + adntir.getText() + "',"
                                    + "'" + adnboite.getText() + "',"
                                    + "'" + adnempboite.getText() + "',"
                                    + "'" + adnboitevol.getText() + "',"
                                    + "'" + adnrem.getText() + "') ;";
                                    
                                     System.out.println(">>>"+cqlinsert);
                                    try {

                                        session.execute(cqlinsert);
                                        System.out.println(cqlinsert);
                                        
                                        adn.setSelected(false);
                                        adnboite.setText("");
                                        adnboitevol.setText("");
                                        adncode.setText("");
                                        adncong.setText("");
                                        adnempboite.setText("");
                                        adnnbr.setText("");
                                        adnrem.setText("");
                                        adntir.setText("");

                                    } catch (Exception e) {
                                        System.out.println("err");
                                    }
                                
                                
                                
                            }
                           
                         }
                   if (arn.isSelected()){

                            if( arnboite.getText().equals("") ||
                                arnboitevol.getText().equals("") ||
                                arncode.getText().equals("") ||
                                arncong.getText().equals("") ||
                                arnempboite.getText().equals("") ||
                                arnnbr.getText().equals("") ||
                                arnrem.getText().equals("") ||
                                arntir.getText().equals("") ){
                                
                                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                                    
                            }
                            else {
                                
                                
                                     String cqlinsert = "INSERT INTO Echantion ( id,cni_patient,type_echant,date_echant,nombr_echant,code_echant,"
                                             + "num_congilateur,num_tiritoire,num_boite,place_boite,volume_echant,remarque) "
                                    + "VALUES ( uuid(),"
                                    + "'" + cniechan + "',"
                                    + "'" + arn.getText() + "',"
                                    + "'" + strdate + "',"
                                    + "" + arnnbr.getText() + ","
                                    + "'" + arncode.getText() + "',"
                                    + "'" + arncong.getText() + "',"
                                    + "'" + arntir.getText() + "',"
                                    + "'" + arnboite.getText() + "',"
                                    + "'" + arnempboite.getText() + "',"
                                    + "'" + arnboitevol.getText() + "',"
                                    + "'" + arnrem.getText() + "') ;";
                                    
                                     System.out.println(">>>"+cqlinsert);
                                    try {

                                        session.execute(cqlinsert);
                                        System.out.println(cqlinsert);
                                        
                                        arn.setSelected(false);
                                        arnboite.setText("");
                                        arnboitevol.setText("");
                                        arncode.setText("");
                                        arncong.setText("");
                                        arnempboite.setText("");
                                        arnnbr.setText("");
                                        arnrem.setText("");
                                        arntir.setText("");

                                    } catch (Exception e) {
                                        System.out.println("err");
                                    }
                                
                                
                                
                            }
                           
                         }
                   if (plasma.isSelected()){

                            if( plasmaboite.getText().equals("") ||
                                plasmaboitevol.getText().equals("") ||
                                plasmacode.getText().equals("") ||
                                plasmacong.getText().equals("") ||
                                plasmaempboite.getText().equals("") ||
                                plasmanbr.getText().equals("") ||
                                plasmarem.getText().equals("") ||
                                plasmatir.getText().equals("") ){
                                
                                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                                    
                            }
                            else {
                                
                                
                                     String cqlinsert = "INSERT INTO Echantion ( id,cni_patient,type_echant,date_echant,nombr_echant,code_echant,"
                                             + "num_congilateur,num_tiritoire,num_boite,place_boite,volume_echant,remarque) "
                                    + "VALUES ( uuid(),"
                                    + "'" + cniechan + "',"
                                    + "'" + plasma.getText() + "',"
                                    + "'" + strdate + "',"
                                    + "" + plasmanbr.getText() + ","
                                    + "'" + plasmacode.getText() + "',"
                                    + "'" + plasmacong.getText() + "',"
                                    + "'" + plasmatir.getText() + "',"
                                    + "'" + plasmaboite.getText() + "',"
                                    + "'" + plasmaempboite.getText() + "',"
                                    + "'" + plasmaboitevol.getText() + "',"
                                    + "'" + plasmarem.getText() + "') ;";
                                    
                                     System.out.println(">>>"+cqlinsert);
                                    try {

                                        session.execute(cqlinsert);
                                        System.out.println(cqlinsert);

                                        plasma.setSelected(false);
                                        plasmaboite.setText("");
                                        plasmaboitevol.setText("");
                                        plasmacode.setText("");
                                        plasmacong.setText("");
                                        plasmaempboite.setText("");
                                        plasmanbr.setText("");
                                        plasmarem.setText("");
                                        plasmatir.setText("");

                                    } catch (Exception e) {
                                        System.out.println("err");
                                    }
                                
                                
                                
                            }
                           
                         }
                   if (echautre.isSelected()){

                            if( autreboite.getText().equals("") ||
                                autreboitevol.getText().equals("") ||
                                autrecode.getText().equals("") ||
                                autrecong.getText().equals("") ||
                                autreempboite.getText().equals("") ||
                                autrenbr.getText().equals("") ||
                                autrerem.getText().equals("") ||
                                autretir.getText().equals("") ){
                                
                                    JOptionPane.showMessageDialog(this, "Attention entrer tous les informations");
                                    
                            }
                            else {
                                
                                
                                     String cqlinsert = "INSERT INTO Echantion ( id,cni_patient,type_echant,date_echant,nombr_echant,code_echant,"
                                             + "num_congilateur,num_tiritoire,num_boite,place_boite,volume_echant,remarque) "
                                    + "VALUES ( uuid(),"
                                    + "'" + cniechan + "',"
                                    + "'" + echautre.getText() + "',"
                                    + "'" + strdate + "',"
                                    + "" + autrenbr.getText() + ","
                                    + "'" + autrecode.getText() + "',"
                                    + "'" + autrecong.getText() + "',"
                                    + "'" + autretir.getText() + "',"
                                    + "'" + autreboite.getText() + "',"
                                    + "'" + autreempboite.getText() + "',"
                                    + "'" + autreboitevol.getText() + "',"
                                    + "'" + autrerem.getText() + "') ;";
                                    
                                     System.out.println(">>>"+cqlinsert);
                                    try {

                                        session.execute(cqlinsert);
                                        System.out.println(cqlinsert);
                                        
                                        echautre.setSelected(false);
                                        autreboite.setText("");
                                        autreboitevol.setText("");
                                        autrecode.setText("");
                                        autrecong.setText("");
                                        autreempboite.setText("");
                                        autrenbr.setText("");
                                        autrerem.setText("");
                                        autretir.setText("");
                                        

                                    } catch (Exception e) {
                                        System.out.println("err");
                                    }
                                
                                
                                
                            }
                           
                         }
                   
                    String tr = " Échantillon de "+id.toUpperCase()+" Ajouté avec succès";     
                    JOptionPane.showMessageDialog(this, tr);

                    rprel.setText("");
                    cniprel.setText("");
                    lieuprel.setText("");
                    rprel.setText("");
                    sang.setText("");
                    a.setText("");
                    tissu.setText("");
                    b.setText("");
                    bio.setText("");
                    d.setText("");
                    autre.setText("");
                    e.setText("");
                    eco.setText("");
                    c.setText("");
                
                    aa.setSelected(false);
                    bb.setSelected(false);
                    ee.setSelected(false);
                    t.setSelected(false);
                    s.setSelected(false);
                    
                    
                    setColor(btn_typo);
                    resetColor(btn_data);
                    resetColor(btn_btns);
                    resetColor(btn_fonts);
                    resetColor(btn_icons);
                    ind_typo.setOpaque(false);
                    ind_btns.setOpaque(false);
                    ind_data.setOpaque(false);
                    ind_fonts.setOpaque(false);
                    ind_icons.setOpaque(false);
                     
                    remplirtabacceuil();
                    
                   cardLayout.show(pnlRight, "card2");

        }
        else {
            JOptionPane.showMessageDialog(this, "Attention Aucune information sélectionner");
        }
        
     }
     
        
        
        
    private void adnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_adnItemStateChanged
        
        if(adn.isSelected()){
            adnboite.setEditable(true);
            adnboitevol.setEditable(true);
            adncode.setEditable(true);
            adncong.setEditable(true);
            adnempboite.setEditable(true);
            adnnbr.setEditable(true);
            adnrem.setEditable(true);
            adntir.setEditable(true);
            
        }
        else {
                adnboite.setEditable(false);
                adnboitevol.setEditable(false);
                adncode.setEditable(false);
                adncong.setEditable(false);
                adnempboite.setEditable(false);
                adnnbr.setEditable(false);
                adnrem.setEditable(false);
                adntir.setEditable(false);
        }
        
    }//GEN-LAST:event_adnItemStateChanged

    private void arnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_arnItemStateChanged
        if(arn.isSelected()){
            arnboite.setEditable(true);
            arnboitevol.setEditable(true);
            arncode.setEditable(true);
            arncong.setEditable(true);
            arnempboite.setEditable(true);
            arnnbr.setEditable(true);
            arnrem.setEditable(true);
            arntir.setEditable(true);
            
        }
        else {
            arnboite.setEditable(false);
            arnboitevol.setEditable(false);
            arncode.setEditable(false);
            arncong.setEditable(false);
            arnempboite.setEditable(false);
            arnnbr.setEditable(false);
            arnrem.setEditable(false);
            arntir.setEditable(false);
        }
    }//GEN-LAST:event_arnItemStateChanged

    private void plasmaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_plasmaItemStateChanged
        if(plasma.isSelected()){
            plasmaboite.setEditable(true);
            plasmaboitevol.setEditable(true);
            plasmacode.setEditable(true);
            plasmacong.setEditable(true);
            plasmaempboite.setEditable(true);
            plasmanbr.setEditable(true);
            plasmarem.setEditable(true);
            plasmatir.setEditable(true);
            
        }
        else {
            plasmaboite.setEditable(false);
            plasmaboitevol.setEditable(false);
            plasmacode.setEditable(false);
            plasmacong.setEditable(false);
            plasmaempboite.setEditable(false);
            plasmanbr.setEditable(false);
            plasmarem.setEditable(false);
            plasmatir.setEditable(false);
        }
    }//GEN-LAST:event_plasmaItemStateChanged

    private void echautreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_echautreItemStateChanged
        if(echautre.isSelected()){
            autreboite.setEditable(true);
            autreboitevol.setEditable(true);
            autrecode.setEditable(true);
            autrecong.setEditable(true);
            autreempboite.setEditable(true);
            autrenbr.setEditable(true);
            autrerem.setEditable(true);
            autretir.setEditable(true);
            
        }
        else {
            autreboite.setEditable(false);
            autreboitevol.setEditable(false);
            autrecode.setEditable(false);
            autrecong.setEditable(false);
            autreempboite.setEditable(false);
            autrenbr.setEditable(false);
            autrerem.setEditable(false);
            autretir.setEditable(false);
        }
    }//GEN-LAST:event_echautreItemStateChanged

    private void listepatientminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listepatientminMouseClicked
        
        
        resetColor(btn_typo);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        
        
         JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        String s=source.getModel().getValueAt(row, 0)+"";
        
        System.out.println(">>>"+s);
        
        info(s);
        
        tableconspatient.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableconspatient.getTableHeader().setOpaque(false);
        tableconspatient.getTableHeader().setBackground(new Color(41, 168, 73));
        tableconspatient.getTableHeader().setForeground(new Color(255, 255, 255));
        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableconspatient.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererr = (DefaultTableCellRenderer) tableechspatient.getDefaultRenderer(Object.class);
        rendererr.setHorizontalAlignment(SwingConstants.CENTER);
        
        tableechspatient.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableechspatient.getTableHeader().setOpaque(false);
        tableechspatient.getTableHeader().setBackground(new Color(41, 168, 73));
        tableechspatient.getTableHeader().setForeground(new Color(255, 255, 255));
        
        tableechspatient.getSelectionModel().clearSelection();
        tableconspatient.getSelectionModel().clearSelection();
        source.getSelectionModel().clearSelection();
        
        cardLayout.show(pnlRight, "card10");
 
    }//GEN-LAST:event_listepatientminMouseClicked

    private void tableechspatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableechspatientMouseClicked

               autreboite.setEditable(false);
               autreboitevol.setEditable(false);
               autrecode.setEditable(false);
               autrecong.setEditable(false);
               autreempboite.setEditable(false);
               autrenbr.setEditable(false);
               autrerem.setEditable(false);
               autretir.setEditable(false);

               plasmaboite.setEditable(false);
               plasmaboitevol.setEditable(false);
               plasmacode.setEditable(false);
               plasmacong.setEditable(false);
               plasmaempboite.setEditable(false);
               plasmanbr.setEditable(false);
               plasmarem.setEditable(false);
               plasmatir.setEditable(false);

               arnboite.setEditable(false);
               arnboitevol.setEditable(false);
               arncode.setEditable(false);
               arncong.setEditable(false);
               arnempboite.setEditable(false);
               arnnbr.setEditable(false);
               arnrem.setEditable(false);
               arntir.setEditable(false);

                adnboite.setEditable(false);
                adnboitevol.setEditable(false);
                adncode.setEditable(false);
                adncong.setEditable(false);
                adnempboite.setEditable(false);
                adnnbr.setEditable(false);
                adnrem.setEditable(false);
                adntir.setEditable(false);

                cniprel.setText(patientcni.getText());
                cardLayout.show(pnlRight, "card7");

    }//GEN-LAST:event_tableechspatientMouseClicked

    private void listpatientpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listpatientpMouseClicked
        
         resetColor(btn_typo);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        
        
         JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        String s=source.getModel().getValueAt(row, 0)+"";
        
        System.out.println(">>>"+s);
        
        info(s);
        
        tableconspatient.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableconspatient.getTableHeader().setOpaque(false);
        tableconspatient.getTableHeader().setBackground(new Color(41, 168, 73));
        tableconspatient.getTableHeader().setForeground(new Color(255, 255, 255));
        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableconspatient.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererr = (DefaultTableCellRenderer) tableechspatient.getDefaultRenderer(Object.class);
        rendererr.setHorizontalAlignment(SwingConstants.CENTER);
        
        tableechspatient.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableechspatient.getTableHeader().setOpaque(false);
        tableechspatient.getTableHeader().setBackground(new Color(41, 168, 73));
        tableechspatient.getTableHeader().setForeground(new Color(255, 255, 255));
        
        tableechspatient.getSelectionModel().clearSelection();
        tableconspatient.getSelectionModel().clearSelection();
        source.getSelectionModel().clearSelection();
        
        cardLayout.show(pnlRight, "card10");
    }//GEN-LAST:event_listpatientpMouseClicked

    private void jLabel98MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel98MouseClicked
        
        
        setColor(btn_fonts);
        resetColor(btn_data);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);

        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_fonts.setOpaque(true);
        ind_icons.setOpaque(false);
        
        remplirtabacceuil();
        tableaumespatient();
        cardLayout.show(pnlRight, "card1");
        
        
    }//GEN-LAST:event_jLabel98MouseClicked

    private void listpatientp1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listpatientp1MouseClicked
       
        
        
        resetColor(btn_typo);
        resetColor(btn_data);
        resetColor(btn_data2);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_data.setOpaque(false);
        ind_data2.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        
        
         JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        String s=source.getModel().getValueAt(row, 0)+"";
        
        System.out.println(">>>"+s);
        
        info(s);
        
        tableconspatient.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableconspatient.getTableHeader().setOpaque(false);
        tableconspatient.getTableHeader().setBackground(new Color(41, 168, 73));
        tableconspatient.getTableHeader().setForeground(new Color(255, 255, 255));
        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tableconspatient.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer rendererr = (DefaultTableCellRenderer) tableechspatient.getDefaultRenderer(Object.class);
        rendererr.setHorizontalAlignment(SwingConstants.CENTER);
        
        tableechspatient.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableechspatient.getTableHeader().setOpaque(false);
        tableechspatient.getTableHeader().setBackground(new Color(41, 168, 73));
        tableechspatient.getTableHeader().setForeground(new Color(255, 255, 255));
        
        tableechspatient.getSelectionModel().clearSelection();
        tableconspatient.getSelectionModel().clearSelection();
        source.getSelectionModel().clearSelection();
        
        cardLayout.show(pnlRight, "card10");
        
        
    }//GEN-LAST:event_listpatientp1MouseClicked

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        
        chercher(jTextField2.getText());
        
        
    }//GEN-LAST:event_jTextField2KeyReleased
    
    
    public void chercher(String str){
        
        listpatientp1.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        listpatientp1.getTableHeader().setOpaque(false);
        listpatientp1.getTableHeader().setBackground(new Color(41,168,73));
        listpatientp1.getTableHeader().setForeground(new Color(255, 255, 255));
        
        

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) listpatientp1.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableModel yourModel = (DefaultTableModel) listpatientp1.getModel();
        
        if(str.equals("")){
            yourModel.setRowCount(0);
            tabchercher();
        }
        else{
             
               String cql = " SELECT * FROM patient WHERE prenom LIKE '%"+str+"%';";
               String cqll = " SELECT * FROM patient WHERE nom LIKE '%"+str+"%';";
               int i=0,j=0;
               try {

                   yourModel.setRowCount(0);
                        
                   for (Row n : session.execute(cql)) {
                       i++;
                   }
                   for (Row nn : session.execute(cqll)) {
                       j++;
                   }
                   
                   if(i > 0){
                       for (Row n : session.execute(cql)) {
                       yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(7).toUpperCase()+" "+n.getString(10).toUpperCase(),n.getDate(2),n.getString(12).toUpperCase()});
                        }
                   }
                   if(j > 0){
                       for (Row nn : session.execute(cqll)) {
                       yourModel.addRow(new Object[]{nn.getString(0).toUpperCase(),nn.getString(7).toUpperCase()+" "+nn.getString(10).toUpperCase(),nn.getDate(2),nn.getString(12).toUpperCase()});
                        }
                   }
                   
                   if(i == 0 && j== 0){
                       
                       yourModel.addRow(new Object[]{"NULL","NULL","NULL","NULL"});
                        
                   }

               } catch (Exception e) {
                   System.out.print("err");
               }
        }
        
       
        
        
        
    }
    
    
    
    public void info(String id){
        
       
                
        String cnip = id;
        
        DefaultTableModel yourModel = (DefaultTableModel) tableconspatient.getModel();
        DefaultTableModel yourModell = (DefaultTableModel) tableechspatient.getModel();
        
        
        String cqlStatement1 = "SELECT * from patient where cne = '"+cnip+"' allow filtering ;";
        String cqlStatement2 = "SELECT * from consultation where cni_patient = '"+cnip+"' allow filtering ;";
        String cqlStatement3 = "SELECT * from prelevement where cin_patient = '"+cnip+"' allow filtering ;";
        
        System.out.println(cqlStatement1);
        System.out.println(cqlStatement2);
        System.out.println(cqlStatement3);
        
        
         try {
                
             
                Row n = session.execute(cqlStatement1).one();
             
                String tele = "0"+n.getInt(13);
             
                patientcni.setText(cnip.toUpperCase());
                
                
                
                String name = n.getString(7).toUpperCase()+" "+n.getString(10).toUpperCase();
                patientname.setText(name);
                patientdateniassance.setText(n.getDate(2).toString());
                patientlieu.setText(n.getString(4).toUpperCase());
                patientnumtele.setText(tele);
                patientnationnaliter.setText(n.getString(6).toUpperCase());
                patientvillee.setText(n.getString(14).toUpperCase());
                patientadr.setText(n.getString(1).toUpperCase());
                patientsexe.setText(n.getString(12).toUpperCase());
              
                
                    try {
                        for (Row nn : session.execute(cqlStatement2)) {
                            yourModel.addRow(new Object[]{nn.getString(3).toUpperCase(),nn.getDate(2).toString()});
                            System.out.println("hhh");
                        }

                    } catch (Exception e) {
                        System.out.print("err1");
                    }
         
                
                    try {
                        for (Row nnn : session.execute(cqlStatement3)) {
                            yourModell.addRow(new Object[]{nnn.getDate(2).toString()});
                        }

                    } catch (Exception e) {
                        System.out.print("err2");
                    }
            

        } catch (Exception e) {
            System.out.print("err kbir");
        }
        
        
        
        
        
    }
    
    
    // set and reset color
    void setColor(JPanel panel) {
        panel.setBackground(new Color(25, 222, 38));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(24, 168, 34));
    }

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
                if ("Windows.Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeMedecin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeMedecin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeMedecin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeMedecin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new HomeMedecin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CIN;
    private javax.swing.JTextField a;
    private javax.swing.JCheckBox aa;
    private javax.swing.JPanel addpr;
    private javax.swing.JPanel addprevaide;
    private javax.swing.JCheckBox adn;
    private javax.swing.JTextField adnboite;
    private javax.swing.JTextField adnboitevol;
    private javax.swing.JTextField adncode;
    private javax.swing.JTextField adncong;
    private javax.swing.JTextField adnempboite;
    private javax.swing.JTextField adnnbr;
    private javax.swing.JTextField adnrem;
    private javax.swing.JTextField adntir;
    private javax.swing.JCheckBox arn;
    private javax.swing.JTextField arnboite;
    private javax.swing.JTextField arnboitevol;
    private javax.swing.JTextField arncode;
    private javax.swing.JTextField arncong;
    private javax.swing.JTextField arnempboite;
    private javax.swing.JTextField arnnbr;
    private javax.swing.JTextField arnrem;
    private javax.swing.JTextField arntir;
    private javax.swing.JTextField autre;
    private javax.swing.JTextField autreboite;
    private javax.swing.JTextField autreboitevol;
    private javax.swing.JTextField autrecode;
    private javax.swing.JTextField autrecong;
    private javax.swing.JTextField autreempboite;
    private javax.swing.JTextField autrenbr;
    private javax.swing.JTextField autrerem;
    private javax.swing.JTextField autretir;
    private javax.swing.JTextField b;
    private javax.swing.JCheckBox bb;
    private javax.swing.JTextField bio;
    private javax.swing.JPanel btn_btns;
    private javax.swing.JPanel btn_data;
    private javax.swing.JPanel btn_data1;
    private javax.swing.JPanel btn_data2;
    private javax.swing.JPanel btn_fonts;
    private javax.swing.JPanel btn_icons;
    private javax.swing.JPanel btn_typo;
    private javax.swing.JTextField c;
    private javax.swing.JPanel chercher;
    private javax.swing.JLabel cnicons;
    private javax.swing.JTextField cniprel;
    private javax.swing.JTextField conscni;
    private com.toedter.calendar.JDateChooser consdate;
    private javax.swing.JTextField consdesc;
    private javax.swing.JLabel consmotif;
    private javax.swing.JTextField conspoids;
    private javax.swing.JTextField constaille;
    private javax.swing.JTextField constention;
    private javax.swing.JTextField constmp;
    private javax.swing.JLabel consultationdate;
    private java.awt.Button consvalider;
    private java.awt.Button consvalider3;
    private java.awt.Button consvalider4;
    private java.awt.Button consvalider5;
    private javax.swing.JTextField d;
    private javax.swing.JTextField e;
    private javax.swing.JPanel echan;
    private javax.swing.JCheckBox echautre;
    private javax.swing.JTextField eco;
    private javax.swing.JCheckBox ee;
    private javax.swing.JPanel help;
    private javax.swing.JPanel ind_btns;
    private javax.swing.JPanel ind_data;
    private javax.swing.JPanel ind_data1;
    private javax.swing.JPanel ind_data2;
    private javax.swing.JPanel ind_fonts;
    private javax.swing.JPanel ind_icons;
    private javax.swing.JPanel ind_typo;
    private javax.swing.JPanel infocons;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblMaximize;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField lieuprel;
    private javax.swing.JTable listecons;
    private javax.swing.JTable listepatientmin;
    private javax.swing.JTable listpatientp;
    private javax.swing.JTable listpatientp1;
    private javax.swing.JTable mal;
    private javax.swing.JTable maldec;
    private javax.swing.JLabel namepatient;
    private javax.swing.JLabel nom;
    private javax.swing.JLabel nommed;
    private javax.swing.JPanel patient;
    private javax.swing.JLabel patientadr;
    private javax.swing.JTextField patientadresse;
    private javax.swing.JTextField patientcin;
    private javax.swing.JLabel patientcni;
    private com.toedter.calendar.JDateChooser patientdate;
    private javax.swing.JLabel patientdateniassance;
    private javax.swing.JTextField patientemail;
    private javax.swing.JLabel patientlieu;
    private javax.swing.JTextField patientlieunaiss;
    private javax.swing.JTextField patientmere;
    private javax.swing.JLabel patientname;
    private javax.swing.JPanel patientnamee;
    private javax.swing.JTextField patientnatin;
    private javax.swing.JLabel patientnationnaliter;
    private javax.swing.JTextField patientnom;
    private javax.swing.JLabel patientnumtele;
    private javax.swing.JTextField patientpere;
    private javax.swing.JTextField patientpnom;
    private javax.swing.JLabel patientpoid;
    private javax.swing.JTextField patientproffession;
    private javax.swing.JLabel patientsexe;
    private javax.swing.JLabel patienttaille;
    private javax.swing.JTextField patienttele;
    private javax.swing.JLabel patienttention;
    private javax.swing.JLabel patienttmp;
    private javax.swing.JTextField patientville;
    private javax.swing.JLabel patientvillee;
    private javax.swing.JCheckBox plasma;
    private javax.swing.JTextField plasmaboite;
    private javax.swing.JTextField plasmaboitevol;
    private javax.swing.JTextField plasmacode;
    private javax.swing.JTextField plasmacong;
    private javax.swing.JTextField plasmaempboite;
    private javax.swing.JTextField plasmanbr;
    private javax.swing.JTextField plasmarem;
    private javax.swing.JTextField plasmatir;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlDataCards;
    private javax.swing.JPanel pnlParent;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JPanel pnlTypography;
    private javax.swing.JPanel pnlcons;
    private javax.swing.JPanel pnllistemal;
    private javax.swing.JLabel prenom;
    private javax.swing.JTextField rprel;
    private javax.swing.JCheckBox s;
    private javax.swing.JTextField sang;
    private javax.swing.JPanel sidepane;
    private javax.swing.JLabel spec;
    private javax.swing.JTable sym;
    private javax.swing.JTable symdec;
    private javax.swing.JCheckBox t;
    private javax.swing.JTable tableconspatient;
    private javax.swing.JTable tableechspatient;
    private javax.swing.JLabel tele;
    private javax.swing.JTextField tissu;
    private javax.swing.JPanel valid;
    private java.awt.Button valider;
    // End of variables declaration//GEN-END:variables
}
