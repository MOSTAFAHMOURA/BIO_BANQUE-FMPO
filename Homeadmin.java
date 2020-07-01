/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stagefindf;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import static com.datastax.driver.core.querybuilder.QueryBuilder.delete;
import com.placeholder.PlaceHolder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.*;
import java.lang.Object;
import javax.swing.*;  


/**
 *
 * @author proxc
 */
public class Homeadmin extends javax.swing.JFrame {

    private CardLayout cardLayout;
    
    String serverIP = "127.0.0.1";
    String keyspace = "bio";
    Cluster cluster = Cluster.builder()
            .addContactPoints(serverIP)
            .build();
    Session session = cluster.connect(keyspace);
    
    /**
     * Creates new form Home20
     */
    
    
    
    public Homeadmin(String idd) {
        initComponents();
        ind_typo.setOpaque(true);
        resetColor(btn_btns);
        resetColor(btn_btns1);
        setColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_icons1);
        resetColor(btn_btns2);
        resetColor(btn_btns3); 
        resetColor(btn_btns4);
        
        Homeadmin.this.getRootPane().setBorder(new LineBorder(new Color(21, 51, 23)));
        lblTitle.setText(this.getTitle());
        cardLayout = (CardLayout) pnlRight.getLayout();
        
        
        listecons.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        listecons.getTableHeader().setOpaque(false);
        listecons.getTableHeader().setBackground(new Color(255,204,0));
        listecons.getTableHeader().setForeground(new Color(0, 0, 0));
        
        DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) listecons.getDefaultRenderer(Object.class);
        rendere.setHorizontalAlignment(SwingConstants.CENTER);
        
        infogen(idd);
        rlistecons();

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
    }
    
    
    public void infogen(String id){
        
      String query = "SELECT * FROM users where cne='"+id+"' ;";  
      try {
            Row n = session.execute(query).one();
            
            String tele = "0"+n.getInt(4);
            
            adcni.setText(n.getString(0).toUpperCase());
            adnom.setText(n.getString(3).toUpperCase());
            adpnom.setText(n.getString(5).toUpperCase());
            adtele.setText(tele);
            ademail.setText(n.getString(1));

        } catch (Exception e) {
            System.out.print("err");
        }
        
    }
    
    public void rlistecons(){
        
        String query = "SELECT * FROM users ;";
        
        DefaultTableModel yourModel = (DefaultTableModel) listecons.getModel();
        
        yourModel.setRowCount(0);
        
        try {
            for (Row n : session.execute(query)) {
                yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(3).toUpperCase()+" "+n.getString(5).toUpperCase(),n.getString(1),"0"+n.getInt(4)});
            }

        } catch (Exception e) {
            System.out.print("err");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        btn_icons1 = new javax.swing.JPanel();
        ind_icons1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btn_btns1 = new javax.swing.JPanel();
        ind_btns1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_btns2 = new javax.swing.JPanel();
        ind_btns2 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        btn_btns3 = new javax.swing.JPanel();
        ind_btns3 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        btn_btns4 = new javax.swing.JPanel();
        ind_btns4 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        Acceuil = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        adnom = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        adpnom = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        adcni = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        ademail = new javax.swing.JLabel();
        adtele = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listecons = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        add = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        utcni = new javax.swing.JTextField();
        utnom = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        utpnom = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        utemail = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        uttele = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        utmdp = new javax.swing.JTextField();
        utcmdp = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        r3 = new javax.swing.JRadioButton();
        spec = new javax.swing.JTextField();
        consvalider = new java.awt.Button();
        listeU = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listU = new javax.swing.JTable();
        addsymmal = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        s = new javax.swing.JRadioButton();
        m = new javax.swing.JRadioButton();
        a = new javax.swing.JTextField();
        b = new javax.swing.JTextField();
        c = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        h = new javax.swing.JTextField();
        g = new javax.swing.JTextField();
        f = new javax.swing.JTextField();
        e = new javax.swing.JTextField();
        consvalider1 = new java.awt.Button();
        Listsymmal = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listU1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        listU2 = new javax.swing.JTable();
        addpatient = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        valider = new java.awt.Button();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        patientpnom = new javax.swing.JTextField();
        patientnom = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        patientcin = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        patientmere = new javax.swing.JTextField();
        patientpere = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        patientproffession = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        patientadresse = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        patientville = new javax.swing.JTextField();
        patienttele = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        patientemail = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        patientnatin = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        patientdate = new com.toedter.calendar.JDateChooser();
        patientlieunaiss = new javax.swing.JTextField();
        patientmed = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        listepatient = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        listU3 = new javax.swing.JTable();
        editutilisateur = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        mcne = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        mtele = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        mnom = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        mpnom = new javax.swing.JTextField();
        memail = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        mmdp = new javax.swing.JTextField();
        mtypecmp = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        mspec = new javax.swing.JTextField();
        consvalider2 = new java.awt.Button();
        editpatient = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        edpcne = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        edpnom = new javax.swing.JTextField();
        edppnom = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        edpemail = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        edptele = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        edplieu = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        edpsexe = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        edpville = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        edpnationnaliter = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        edpproff = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        edpadresse = new javax.swing.JTextField();
        cd = new javax.swing.JLabel();
        edpmedcne = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        edpmere = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        edppere = new javax.swing.JTextField();
        consvalider3 = new java.awt.Button();
        edpdate = new javax.swing.JTextField();
        findutilisateur = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        finduser = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabfindu = new javax.swing.JTable();
        findpatient = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        finduser1 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        findpt = new javax.swing.JTable();

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
        btn_typo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Home");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Admin");
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
        jLabel5.setText("Ajouter un Utilisateur");
        btn_fonts.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

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
        jLabel6.setText("Liste des Utilisateurs");
        btn_icons.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

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
        jLabel9.setText("Liste des sympts/malads");
        btn_btns.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        btn_icons1.setBackground(new java.awt.Color(41, 168, 73));
        btn_icons1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_icons1MousePressed(evt);
            }
        });
        btn_icons1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_icons1.setOpaque(false);
        ind_icons1.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_icons1Layout = new javax.swing.GroupLayout(ind_icons1);
        ind_icons1.setLayout(ind_icons1Layout);
        ind_icons1Layout.setHorizontalGroup(
            ind_icons1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_icons1Layout.setVerticalGroup(
            ind_icons1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_icons1.add(ind_icons1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Ajouter des sympt/malad");
        btn_icons1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 180, -1));

        btn_btns1.setBackground(new java.awt.Color(41, 168, 73));
        btn_btns1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_btns1MousePressed(evt);
            }
        });
        btn_btns1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_btns1.setOpaque(false);
        ind_btns1.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_btns1Layout = new javax.swing.GroupLayout(ind_btns1);
        ind_btns1.setLayout(ind_btns1Layout);
        ind_btns1Layout.setHorizontalGroup(
            ind_btns1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_btns1Layout.setVerticalGroup(
            ind_btns1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_btns1.add(ind_btns1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Ajouter Un Patient");
        btn_btns1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        btn_btns2.setBackground(new java.awt.Color(41, 168, 73));
        btn_btns2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_btns2MousePressed(evt);
            }
        });
        btn_btns2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_btns2.setOpaque(false);
        ind_btns2.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_btns2Layout = new javax.swing.GroupLayout(ind_btns2);
        ind_btns2.setLayout(ind_btns2Layout);
        ind_btns2Layout.setHorizontalGroup(
            ind_btns2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_btns2Layout.setVerticalGroup(
            ind_btns2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_btns2.add(ind_btns2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel36.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Liste des Patient");
        btn_btns2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        btn_btns3.setBackground(new java.awt.Color(41, 168, 73));
        btn_btns3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_btns3MousePressed(evt);
            }
        });
        btn_btns3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_btns3.setOpaque(false);
        ind_btns3.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_btns3Layout = new javax.swing.GroupLayout(ind_btns3);
        ind_btns3.setLayout(ind_btns3Layout);
        ind_btns3Layout.setHorizontalGroup(
            ind_btns3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_btns3Layout.setVerticalGroup(
            ind_btns3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_btns3.add(ind_btns3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel38.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Chercher Un Utilisateur");
        btn_btns3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        btn_btns4.setBackground(new java.awt.Color(41, 168, 73));
        btn_btns4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_btns4MousePressed(evt);
            }
        });
        btn_btns4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ind_btns4.setOpaque(false);
        ind_btns4.setPreferredSize(new java.awt.Dimension(4, 40));

        javax.swing.GroupLayout ind_btns4Layout = new javax.swing.GroupLayout(ind_btns4);
        ind_btns4.setLayout(ind_btns4Layout);
        ind_btns4Layout.setHorizontalGroup(
            ind_btns4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );
        ind_btns4Layout.setVerticalGroup(
            ind_btns4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btn_btns4.add(ind_btns4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel76.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Chercher Un Patient");
        btn_btns4.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        javax.swing.GroupLayout sidepaneLayout = new javax.swing.GroupLayout(sidepane);
        sidepane.setLayout(sidepaneLayout);
        sidepaneLayout.setHorizontalGroup(
            sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_typo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_fonts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_icons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_btns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidepaneLayout.createSequentialGroup()
                .addGroup(sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidepaneLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(sidepaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(sidepaneLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btn_icons1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
            .addComponent(btn_btns1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_btns2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_btns3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_btns4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btn_icons1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_btns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_btns1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_btns2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_btns3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_btns4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pnlParent.add(sidepane, java.awt.BorderLayout.LINE_START);

        pnlRight.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(41, 168, 73));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 0));
        jLabel11.setText("Admin HMOURA MOSTAFA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(41, 168, 73));

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 0));
        jLabel14.setText("Information Générale");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 0));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_administrator_male_100px.png"))); // NOI18N
        jLabel18.setText("jLabel18");

        jLabel19.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nom :");

        adnom.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        adnom.setForeground(new java.awt.Color(255, 255, 0));
        adnom.setText("HMOURA MOST");

        jLabel23.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Prénom:");

        adpnom.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        adpnom.setForeground(new java.awt.Color(255, 255, 0));
        adpnom.setText("HMOURA MOST");

        jLabel35.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("CNI :");

        adcni.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        adcni.setForeground(new java.awt.Color(255, 255, 0));
        adcni.setText("F12345");

        jLabel37.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Email :");

        ademail.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        ademail.setForeground(new java.awt.Color(255, 255, 0));
        ademail.setText("mostafahmoura@gmail.com");

        adtele.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        adtele.setForeground(new java.awt.Color(255, 255, 0));
        adtele.setText("0611010114");

        jLabel40.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("№ Télé :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(adnom)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(adpnom)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel35)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(adcni))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ademail)
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel40)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(adtele)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adnom, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adpnom, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adcni, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ademail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adtele, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jLabel41.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel41.setText("Liste des Utilisateurs");

        listecons.setBackground(new java.awt.Color(255, 204, 0));
        listecons.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        listecons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CNI", "Nom et Prénom", "Email", "№ Télé."
            }
        ));
        listecons.setRowHeight(32);
        listecons.setSelectionBackground(new java.awt.Color(255, 255, 0));
        listecons.setShowVerticalLines(false);
        listecons.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listeconsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listecons);
        if (listecons.getColumnModel().getColumnCount() > 0) {
            listecons.getColumnModel().getColumn(0).setMinWidth(90);
            listecons.getColumnModel().getColumn(0).setMaxWidth(90);
            listecons.getColumnModel().getColumn(3).setMinWidth(120);
            listecons.getColumnModel().getColumn(3).setMaxWidth(120);
        }

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px_2.png"))); // NOI18N
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel22)
                        .addGap(8, 8, 8))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout AcceuilLayout = new javax.swing.GroupLayout(Acceuil);
        Acceuil.setLayout(AcceuilLayout);
        AcceuilLayout.setHorizontalGroup(
            AcceuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AcceuilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AcceuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AcceuilLayout.setVerticalGroup(
            AcceuilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AcceuilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(Acceuil, "card2");

        jPanel4.setBackground(new java.awt.Color(41, 168, 73));

        jLabel42.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 0));
        jLabel42.setText("Ajouter un Utilisateur");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addContainerGap(519, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(41, 168, 73));

        jLabel43.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("CNI :");

        utcni.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        utnom.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Nom :");

        jLabel45.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Prénom :");

        utpnom.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Email :");

        utemail.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        jLabel47.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("№ Télé. :");

        uttele.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Mot de passe :");

        utmdp.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        utcmdp.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Confirmer Le Mot de passe :");

        jLabel50.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Type de Compte :");

        r1.setBackground(new java.awt.Color(41, 168, 73));
        r1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        r1.setForeground(new java.awt.Color(255, 255, 255));
        r1.setText("Admin");
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });

        r2.setBackground(new java.awt.Color(41, 168, 73));
        r2.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        r2.setForeground(new java.awt.Color(255, 255, 255));
        r2.setText("Consultant");
        r2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r2ActionPerformed(evt);
            }
        });

        r3.setBackground(new java.awt.Color(41, 168, 73));
        r3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        r3.setForeground(new java.awt.Color(255, 255, 255));
        r3.setText("Médecin");
        r3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r3ActionPerformed(evt);
            }
        });

        spec.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        consvalider.setBackground(new java.awt.Color(41, 168, 73));
        consvalider.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        consvalider.setForeground(new java.awt.Color(255, 255, 255));
        consvalider.setLabel("Ajouter");
        consvalider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consvaliderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(utcni, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(utnom, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(utpnom, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(utemail, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(uttele, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(utmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(utcmdp))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addGap(18, 18, 18)
                        .addComponent(r1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(consvalider, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(r2)
                                .addGap(18, 18, 18)
                                .addComponent(r3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spec)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(utcni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(utnom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(utpnom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(utemail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(uttele, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(utmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(utcmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(r1)
                        .addComponent(r2)
                        .addComponent(r3)
                        .addComponent(spec, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56)
                .addComponent(consvalider, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addLayout = new javax.swing.GroupLayout(add);
        add.setLayout(addLayout);
        addLayout.setHorizontalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        addLayout.setVerticalGroup(
            addLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(add, "card3");

        jPanel6.setBackground(new java.awt.Color(41, 168, 73));

        jLabel51.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 0));
        jLabel51.setText("Liste des Utilisateurs");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 204, 0));

        listU.setBackground(new java.awt.Color(255, 204, 0));
        listU.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        listU.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"F1234", "mostafahmoura2gmail.om", "123456678", "admin"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CNE", "Email", "Mot de Passe", "Privilège"
            }
        ));
        listU.setGridColor(new java.awt.Color(255, 255, 255));
        listU.setRowHeight(32);
        listU.setSelectionBackground(new java.awt.Color(255, 255, 0));
        listU.setSelectionForeground(new java.awt.Color(0, 0, 0));
        listU.setShowVerticalLines(false);
        listU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listUMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listU);
        if (listU.getColumnModel().getColumnCount() > 0) {
            listU.getColumnModel().getColumn(0).setMinWidth(110);
            listU.getColumnModel().getColumn(0).setMaxWidth(110);
            listU.getColumnModel().getColumn(2).setMinWidth(150);
            listU.getColumnModel().getColumn(2).setMaxWidth(150);
            listU.getColumnModel().getColumn(3).setMinWidth(140);
            listU.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout listeULayout = new javax.swing.GroupLayout(listeU);
        listeU.setLayout(listeULayout);
        listeULayout.setHorizontalGroup(
            listeULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listeULayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listeULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        listeULayout.setVerticalGroup(
            listeULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listeULayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(listeU, "card4");

        jPanel9.setBackground(new java.awt.Color(41, 168, 73));

        jLabel52.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 0));
        jLabel52.setText("Ajouter des Symptômes / Maladies");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(41, 168, 73));

        s.setBackground(new java.awt.Color(41, 168, 73));
        s.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        s.setForeground(new java.awt.Color(255, 255, 255));
        s.setText("Symptômes");
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });

        m.setBackground(new java.awt.Color(41, 168, 73));
        m.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        m.setForeground(new java.awt.Color(255, 255, 255));
        m.setText("Maladies");
        m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mActionPerformed(evt);
            }
        });

        e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eActionPerformed(evt);
            }
        });

        consvalider1.setBackground(new java.awt.Color(41, 168, 73));
        consvalider1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        consvalider1.setForeground(new java.awt.Color(255, 255, 255));
        consvalider1.setLabel("Ajouter");
        consvalider1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consvalider1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(s, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                        .addGap(128, 128, 128))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(d, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(c, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(a, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(h, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                        .addComponent(g, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(f, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(e, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(consvalider1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(275, 275, 275))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(a, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(b, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(f, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(g, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(h, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75)
                .addComponent(consvalider1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addsymmalLayout = new javax.swing.GroupLayout(addsymmal);
        addsymmal.setLayout(addsymmalLayout);
        addsymmalLayout.setHorizontalGroup(
            addsymmalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addsymmalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addsymmalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        addsymmalLayout.setVerticalGroup(
            addsymmalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addsymmalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(addsymmal, "card5");

        jPanel8.setBackground(new java.awt.Color(41, 168, 73));

        jLabel53.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 0));
        jLabel53.setText("Liste des Symptômes / Maladies");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 361, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 204, 0));

        listU1.setBackground(new java.awt.Color(255, 204, 0));
        listU1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        listU1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"F1234"},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Symptômes"
            }
        ));
        listU1.setGridColor(new java.awt.Color(255, 255, 255));
        listU1.setRowHeight(32);
        listU1.setSelectionBackground(new java.awt.Color(255, 255, 0));
        listU1.setSelectionForeground(new java.awt.Color(0, 0, 0));
        listU1.setShowVerticalLines(false);
        listU1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listU1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listU1);

        listU2.setBackground(new java.awt.Color(255, 204, 0));
        listU2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        listU2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"F1234"},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Maladies"
            }
        ));
        listU2.setGridColor(new java.awt.Color(255, 255, 255));
        listU2.setRowHeight(32);
        listU2.setSelectionBackground(new java.awt.Color(255, 255, 0));
        listU2.setSelectionForeground(new java.awt.Color(0, 0, 0));
        listU2.setShowVerticalLines(false);
        listU2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listU2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(listU2);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        javax.swing.GroupLayout ListsymmalLayout = new javax.swing.GroupLayout(Listsymmal);
        Listsymmal.setLayout(ListsymmalLayout);
        ListsymmalLayout.setHorizontalGroup(
            ListsymmalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListsymmalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ListsymmalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        ListsymmalLayout.setVerticalGroup(
            ListsymmalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListsymmalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(Listsymmal, "card6");

        jPanel12.setBackground(new java.awt.Color(41, 168, 73));

        jLabel54.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 0));
        jLabel54.setText("Ajouter Un Nouveaux Patient");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(41, 168, 73));

        valider.setBackground(new java.awt.Color(41, 168, 73));
        valider.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        valider.setForeground(new java.awt.Color(255, 255, 255));
        valider.setLabel("Ajouter");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CIN :");

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nom :");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Prénom : ");

        jRadioButton1.setBackground(new java.awt.Color(41, 168, 73));
        jRadioButton1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Femme");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Sexe :");

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

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Date de Naissance :");

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Lieu de Naissance :");

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Ville :");

        jSeparator8.setForeground(new java.awt.Color(41, 168, 73));

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

        jSeparator6.setForeground(new java.awt.Color(41, 168, 73));

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

        jSeparator3.setForeground(new java.awt.Color(41, 168, 73));

        jLabel32.setBackground(new java.awt.Color(255, 255, 255));
        jLabel32.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Origine Père :");

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Origine Mère :");

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Profession : ");

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Adresse : ");

        jLabel27.setBackground(new java.awt.Color(255, 255, 255));
        jLabel27.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Téléphone : ");

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Email : ");

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Nationalité :");

        jLabel33.setBackground(new java.awt.Color(255, 255, 102));
        jLabel33.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Nouveau Patient");

        jSeparator16.setForeground(new java.awt.Color(41, 168, 73));

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

        jSeparator15.setForeground(new java.awt.Color(41, 168, 73));

        jSeparator14.setForeground(new java.awt.Color(41, 168, 73));

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

        jSeparator13.setForeground(new java.awt.Color(41, 168, 73));

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

        jSeparator12.setForeground(new java.awt.Color(41, 168, 73));

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

        jSeparator11.setForeground(new java.awt.Color(41, 168, 73));

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

        jSeparator10.setForeground(new java.awt.Color(41, 168, 73));

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

        jSeparator9.setForeground(new java.awt.Color(41, 168, 73));

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

        patientmed.setForeground(new java.awt.Color(102, 102, 102));
        patientmed.setBorder(null);
        patientmed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                patientmedFocusGained(evt);
            }
        });
        patientmed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientmedActionPerformed(evt);
            }
        });

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Segoe UI Semibold", 1, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Med CNI : ");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(patientnom)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(patientpnom)
                                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(patientdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                            .addComponent(jRadioButton2)
                                            .addGap(18, 18, 18)
                                            .addComponent(jRadioButton1)))
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                                        .addGroup(jPanel13Layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(patientlieunaiss)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(patientnatin, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(patienttele)
                                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(patientville)
                                                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(patientadresse)
                                            .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(patientproffession)
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(patientmere)
                                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(patientpere)
                                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(patientemail)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(patientcin)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(patientmed, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator17, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(valider, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(283, 283, 283))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(patientpnom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(patientcin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel34)
                                    .addComponent(patientmed, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addComponent(patientnom, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel27)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addComponent(patienttele, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel17))
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                        .addComponent(patientville, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton2)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(patientadresse, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(patientdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(patientproffession, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                    .addComponent(patientlieunaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(patientmere, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel31)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel25)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(patientnatin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(patientpere, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(patientemail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(valider, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout addpatientLayout = new javax.swing.GroupLayout(addpatient);
        addpatient.setLayout(addpatientLayout);
        addpatientLayout.setHorizontalGroup(
            addpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addpatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        addpatientLayout.setVerticalGroup(
            addpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addpatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(addpatient, "card7");

        jPanel14.setBackground(new java.awt.Color(41, 168, 73));

        jLabel55.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 0));
        jLabel55.setText("Liste des Patients");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 204, 0));

        listU3.setBackground(new java.awt.Color(255, 204, 0));
        listU3.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        listU3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"F1234", "hmoura mostafa", "123456678", "admin"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CNE", "Nom du Patient", "Date de Naissance", "Sexe"
            }
        ));
        listU3.setGridColor(new java.awt.Color(255, 255, 255));
        listU3.setRowHeight(32);
        listU3.setSelectionBackground(new java.awt.Color(255, 255, 0));
        listU3.setSelectionForeground(new java.awt.Color(0, 0, 0));
        listU3.setShowVerticalLines(false);
        listU3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listU3MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(listU3);
        if (listU3.getColumnModel().getColumnCount() > 0) {
            listU3.getColumnModel().getColumn(0).setMinWidth(110);
            listU3.getColumnModel().getColumn(0).setMaxWidth(110);
            listU3.getColumnModel().getColumn(2).setMinWidth(150);
            listU3.getColumnModel().getColumn(2).setMaxWidth(150);
            listU3.getColumnModel().getColumn(3).setMinWidth(140);
            listU3.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout listepatientLayout = new javax.swing.GroupLayout(listepatient);
        listepatient.setLayout(listepatientLayout);
        listepatientLayout.setHorizontalGroup(
            listepatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listepatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(listepatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        listepatientLayout.setVerticalGroup(
            listepatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listepatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(listepatient, "card8");

        jPanel16.setBackground(new java.awt.Color(41, 168, 73));

        jLabel39.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 0));
        jLabel39.setText("Modifier Un Utilisateur");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addContainerGap(505, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(41, 168, 73));

        jLabel56.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setText("CNE :");

        mcne.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("№ Télé :");

        mtele.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel58.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Nom :");

        mnom.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Prénom :");

        mpnom.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        memail.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel60.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("Email :");

        jLabel61.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Mot de Passe:");

        mmdp.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        mtypecmp.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel62.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Type de Compte :");

        jLabel63.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Spécialité IF Médecin :");

        mspec.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        consvalider2.setBackground(new java.awt.Color(41, 168, 73));
        consvalider2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        consvalider2.setForeground(new java.awt.Color(255, 255, 255));
        consvalider2.setLabel("Modifier");
        consvalider2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consvalider2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel60)
                            .addGap(18, 18, 18)
                            .addComponent(memail))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel61)
                            .addGap(18, 18, 18)
                            .addComponent(mmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(18, 18, 18)
                                .addComponent(mcne, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel58)
                                .addGap(18, 18, 18)
                                .addComponent(mnom, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel59)
                                .addGap(18, 18, 18)
                                .addComponent(mpnom, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel57)
                                .addGap(18, 18, 18)
                                .addComponent(mtele, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel62)
                            .addGap(18, 18, 18)
                            .addComponent(mtypecmp))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel63)
                            .addGap(18, 18, 18)
                            .addComponent(mspec, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(consvalider2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mcne, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mtele, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mnom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mpnom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(memail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mmdp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mtypecmp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mspec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(consvalider2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editutilisateurLayout = new javax.swing.GroupLayout(editutilisateur);
        editutilisateur.setLayout(editutilisateurLayout);
        editutilisateurLayout.setHorizontalGroup(
            editutilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editutilisateurLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editutilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editutilisateurLayout.setVerticalGroup(
            editutilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editutilisateurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(editutilisateur, "card9");

        jPanel18.setBackground(new java.awt.Color(41, 168, 73));

        jLabel64.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 0));
        jLabel64.setText("Modifier Un Patient");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(41, 168, 73));

        jLabel65.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setText("CNE Patient :");

        edpcne.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel66.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Nom :");

        edpnom.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        edppnom.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel67.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Prénom :");

        jLabel68.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Email :");

        edpemail.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel69.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("№ Télé :");

        edptele.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel70.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Date de Naissance :");

        jLabel71.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Lieu de Naissance:");

        edplieu.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel72.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Sexe :");

        edpsexe.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel73.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("Ville :");

        edpville.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel74.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Nationalité :");

        edpnationnaliter.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel75.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Profession :");

        edpproff.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel78.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Adresse :");

        edpadresse.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        cd.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        cd.setForeground(new java.awt.Color(255, 255, 255));
        cd.setText("CNE Médecine :");

        edpmedcne.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel80.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Origine Mère :");

        edpmere.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        jLabel81.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Origine Père :");

        edppere.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        consvalider3.setBackground(new java.awt.Color(41, 168, 73));
        consvalider3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        consvalider3.setForeground(new java.awt.Color(255, 255, 255));
        consvalider3.setLabel("Modifier");
        consvalider3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consvalider3ActionPerformed(evt);
            }
        });

        edpdate.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel78)
                                .addGap(18, 18, 18)
                                .addComponent(edpadresse))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addComponent(jLabel68)
                                        .addGap(18, 18, 18)
                                        .addComponent(edpemail, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel69)
                                        .addGap(18, 18, 18)
                                        .addComponent(edptele, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel19Layout.createSequentialGroup()
                                                .addComponent(jLabel65)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(edpcne, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel19Layout.createSequentialGroup()
                                                .addComponent(jLabel66)
                                                .addGap(18, 18, 18)
                                                .addComponent(edpnom, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel19Layout.createSequentialGroup()
                                                .addComponent(jLabel67)
                                                .addGap(18, 18, 18)
                                                .addComponent(edppnom, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel19Layout.createSequentialGroup()
                                                .addComponent(cd)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(edpmedcne, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel19Layout.createSequentialGroup()
                                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                                        .addComponent(jLabel72)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(edpsexe, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                                        .addComponent(jLabel74)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(edpnationnaliter, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(72, 72, 72))
                                            .addGroup(jPanel19Layout.createSequentialGroup()
                                                .addComponent(jLabel70)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(edpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel19Layout.createSequentialGroup()
                                                .addComponent(jLabel71)
                                                .addGap(18, 18, 18)
                                                .addComponent(edplieu, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                                        .addComponent(jLabel73)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(edpville))
                                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                                        .addComponent(jLabel75)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(edpproff)))
                                                .addGap(39, 39, 39)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addGap(18, 18, 18)
                        .addComponent(edpmere, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel81)
                        .addGap(18, 18, 18)
                        .addComponent(edppere, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(consvalider3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edpcne, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edpmedcne, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(edpnom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(edppnom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(edptele, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(edpemail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel69, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(edpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edplieu, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(edpville, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(edpsexe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(edpnationnaliter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(edpproff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(edppere, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(edpmere, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edpadresse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(consvalider3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editpatientLayout = new javax.swing.GroupLayout(editpatient);
        editpatient.setLayout(editpatientLayout);
        editpatientLayout.setHorizontalGroup(
            editpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editpatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        editpatientLayout.setVerticalGroup(
            editpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editpatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(editpatient, "card10");

        jPanel20.setBackground(new java.awt.Color(41, 168, 73));

        jLabel77.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 0));
        jLabel77.setText("Entrer l'Email de l'Utilisateur :");

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel79.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel79MouseClicked(evt);
            }
        });

        finduser.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        finduser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                finduserKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(finduser, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel79)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finduser))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 204, 0));

        tabfindu.setBackground(new java.awt.Color(255, 204, 0));
        tabfindu.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        tabfindu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"F1234", "mostafahmoura2gmail.om", "123456678", "admin"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CNE", "Email", "Mot de Passe", "Privilège"
            }
        ));
        tabfindu.setGridColor(new java.awt.Color(255, 255, 255));
        tabfindu.setRowHeight(32);
        tabfindu.setSelectionBackground(new java.awt.Color(255, 255, 0));
        tabfindu.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tabfindu.setShowVerticalLines(false);
        tabfindu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabfinduMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabfindu);
        if (tabfindu.getColumnModel().getColumnCount() > 0) {
            tabfindu.getColumnModel().getColumn(0).setMinWidth(110);
            tabfindu.getColumnModel().getColumn(0).setMaxWidth(110);
            tabfindu.getColumnModel().getColumn(2).setMinWidth(150);
            tabfindu.getColumnModel().getColumn(2).setMaxWidth(150);
            tabfindu.getColumnModel().getColumn(3).setMinWidth(140);
            tabfindu.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout findutilisateurLayout = new javax.swing.GroupLayout(findutilisateur);
        findutilisateur.setLayout(findutilisateurLayout);
        findutilisateurLayout.setHorizontalGroup(
            findutilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findutilisateurLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(findutilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        findutilisateurLayout.setVerticalGroup(
            findutilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findutilisateurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(findutilisateur, "card11");

        jPanel22.setBackground(new java.awt.Color(41, 168, 73));

        jLabel82.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 0));
        jLabel82.setText("Entrer le Nom Du Patient :");

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stagefindf/images/icons8_plus_math_32px.png"))); // NOI18N
        jLabel83.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel83MouseClicked(evt);
            }
        });

        finduser1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        finduser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                finduser1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(finduser1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel83)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finduser1))
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(255, 204, 0));

        findpt.setBackground(new java.awt.Color(255, 204, 0));
        findpt.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        findpt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"F1234", "hmoura mostafa", "123456678", "admin"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "CNE", "Nom du Patient", "Date de Naissance", "Sexe"
            }
        ));
        findpt.setGridColor(new java.awt.Color(255, 255, 255));
        findpt.setRowHeight(32);
        findpt.setSelectionBackground(new java.awt.Color(255, 255, 0));
        findpt.setSelectionForeground(new java.awt.Color(0, 0, 0));
        findpt.setShowVerticalLines(false);
        findpt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                findptMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(findpt);
        if (findpt.getColumnModel().getColumnCount() > 0) {
            findpt.getColumnModel().getColumn(0).setMinWidth(110);
            findpt.getColumnModel().getColumn(0).setMaxWidth(110);
            findpt.getColumnModel().getColumn(2).setMinWidth(150);
            findpt.getColumnModel().getColumn(2).setMaxWidth(150);
            findpt.getColumnModel().getColumn(3).setMinWidth(140);
            findpt.getColumnModel().getColumn(3).setMaxWidth(140);
        }

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout findpatientLayout = new javax.swing.GroupLayout(findpatient);
        findpatient.setLayout(findpatientLayout);
        findpatientLayout.setHorizontalGroup(
            findpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findpatientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(findpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        findpatientLayout.setVerticalGroup(
            findpatientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findpatientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlRight.add(findpatient, "card12");

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
        resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        resetColor(btn_btns3); 
        ind_btns3.setOpaque(false);
        resetColor(btn_icons1);
        ind_icons1.setOpaque(false);
        setColor(btn_typo);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        ind_typo.setOpaque(true);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
        
        rlistecons();
        
        cardLayout.show(pnlRight, "card2");

    }//GEN-LAST:event_btn_typoMousePressed

    private void btn_fontsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fontsMousePressed

        spec.setEditable(false);
        PlaceHolder p = new PlaceHolder(spec,"  Spécialité du Médecin");
        spec.setForeground(Color.BLACK);
        
        // TODO add your handling code here:
        resetColor(btn_btns3); 
        ind_btns3.setOpaque(false);
        resetColor(btn_icons1);
        ind_icons1.setOpaque(false);
        setColor(btn_fonts);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
                resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(true);
        ind_icons.setOpaque(false);
        
        //sep.setEditable(false);
        cardLayout.show(pnlRight, "card3");
        
    }//GEN-LAST:event_btn_fontsMousePressed

    private void btn_iconsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_iconsMousePressed
        // TODO add your handling code here:
        resetColor(btn_btns3); 
        ind_btns3.setOpaque(false);
        resetColor(btn_icons1);
        ind_icons1.setOpaque(false);
        setColor(btn_icons);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_typo);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(true);
                resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        
        listU.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        listU.getTableHeader().setOpaque(false);
        listU.getTableHeader().setBackground(new Color(255,204,0));
        listU.getTableHeader().setForeground(new Color(0,0,0));
        
        DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) listU.getDefaultRenderer(Object.class);
        rendere.setHorizontalAlignment(SwingConstants.CENTER);

        rlistU();
        
        cardLayout.show(pnlRight, "card4");

    }//GEN-LAST:event_btn_iconsMousePressed
    
    public void rlistU(){
        
        String query = "SELECT * FROM users ;";
        
        DefaultTableModel yourModel = (DefaultTableModel) listU.getModel();
        
        yourModel.setRowCount(0);
        
        try {
            for (Row n : session.execute(query)) {
                yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(1),n.getString(2),n.getString(7)});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
        
    }
    
    private void btn_btnsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_btnsMousePressed
        
       

        rlistemalsym();

        // TODO add your handling code here:
        resetColor(btn_btns3); 
        ind_btns3.setOpaque(false);
        setColor(btn_btns);
        resetColor(btn_btns1);
        resetColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_icons1);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
        resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(true);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(false);
        
        cardLayout.show(pnlRight, "card6");
        
    }//GEN-LAST:event_btn_btnsMousePressed
    
    public void rlistemalsym(){
        
        
        
         listU1.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        listU1.getTableHeader().setOpaque(false);
        listU1.getTableHeader().setBackground(new Color(255,204,0));
        listU1.getTableHeader().setForeground(new Color(0,0,0));
        
        DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) listU1.getDefaultRenderer(Object.class);
        rendere.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableModel dtm = (DefaultTableModel) listU1.getModel();
        dtm.setRowCount(0);
        
        listU2.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        listU2.getTableHeader().setOpaque(false);
        listU2.getTableHeader().setBackground(new Color(255,204,0));
        listU2.getTableHeader().setForeground(new Color(0,0,0));
        
        DefaultTableCellRenderer renderee = (DefaultTableCellRenderer) listU2.getDefaultRenderer(Object.class);
        renderee.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableModel dtmm = (DefaultTableModel) listU1.getModel();
        dtmm.setRowCount(0);
        
        String query = "SELECT * FROM symptome ;";
        
        DefaultTableModel yourModel = (DefaultTableModel) listU1.getModel();
        
        yourModel.setRowCount(0);
        
        try {
            for (Row n : session.execute(query)) {
                yourModel.addRow(new Object[]{n.getString(1)});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
        String queryy = "SELECT * FROM maladies ;";
        
        DefaultTableModel yourModell = (DefaultTableModel) listU2.getModel();
        
        yourModell.setRowCount(0);
        
        try {
            for (Row n : session.execute(queryy)) {
                yourModell.addRow(new Object[]{n.getString(1)});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
        
    }
    
    

    private void lblMaximizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMaximizeMousePressed
        if (Homeadmin.this.getExtendedState() == MAXIMIZED_BOTH) {
            Homeadmin.this.setExtendedState(JFrame.NORMAL);
        } else {
            Homeadmin.this.setExtendedState(MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_lblMaximizeMousePressed

    private void lblCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMousePressed
        System.exit(0);
    }//GEN-LAST:event_lblCloseMousePressed

    private void lblMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMousePressed
        Homeadmin.this.setState(Frame.ICONIFIED);
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
            if (Homeadmin.this.getExtendedState() == MAXIMIZED_BOTH) {
                Homeadmin.this.setExtendedState(JFrame.NORMAL);
            } else {
                Homeadmin.this.setExtendedState(MAXIMIZED_BOTH);
            }
        }
    }//GEN-LAST:event_pnlTopMouseClicked

    private void consvaliderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consvaliderActionPerformed

        if ( !(utmdp.getText().equals(utcmdp.getText())) ) {
            JOptionPane.showMessageDialog(this, " Attention Vérifier Votre Mot de passe ");
            utcmdp.setText("");
        } else {
            
                    if(      utcni.getText().equals("") ||
                            utemail.getText().equals("") ||
                            utnom.getText().equals("") ||
                            utpnom.getText().equals("") ||
                            uttele.getText().equals("")    ){

                    JOptionPane.showMessageDialog(this, " Attention Entrer Toutes les Information ");
                }
                    else {
                            
                            if(r3.isSelected()){
                                
                                    if(spec.getText().equals("  Spécialité du Médecin")){
                                        JOptionPane.showMessageDialog(this, " Attention Entrer La Spécialité du Médecin ");
                                        adduser();
                                    }
                                    else adduser();
                            } else {
                                adduser();
                            }
                        

                    }
        }
    }//GEN-LAST:event_consvaliderActionPerformed
    
    
    public void adduser(){
        
        String typec=null,query=null;
        if(r1.isSelected()) typec=r1.getText();
        if(r2.isSelected()) typec=r2.getText();
        
        if(r3.isSelected()) {
                typec=r3.getText();
                query = "INSERT INTO users (cne,email,mdp,nom,ntele,prenom,specialite,type_cmp)"
                + "VALUES('"+utcni.getText()+"',"
                +"'"+utemail.getText()+"',"
                +"'"+utmdp.getText()+"',"
                +"'"+utnom.getText()+"',"
                +""+uttele.getText()+","
                +"'"+utpnom.getText()+"',"
                +"'"+spec.getText()+"',"
                +"'"+typec+"') ;";
        }
        else {
                query = "INSERT INTO users (cne,email,mdp,nom,ntele,prenom,specialite,type_cmp)"
                + "VALUES('"+utcni.getText()+"',"
                +"'"+utemail.getText()+"',"
                +"'"+utmdp.getText()+"',"
                +"'"+utnom.getText()+"',"
                +""+uttele.getText()+","
                +"'"+utpnom.getText()+"',"
                +"'"+""+"',"
                +"'"+typec+"') ;";
        }
        
        
        System.out.println("<<"+query);

        try {
            session.execute(query);
            JOptionPane.showMessageDialog(this, " Utilisateur a été Bien Ajouter ");
            rlistecons();
            setColor(btn_typo);
            resetColor(btn_btns1);
            resetColor(btn_btns);
            resetColor(btn_fonts);
            resetColor(btn_icons);
            resetColor(btn_btns3); 
            ind_btns3.setOpaque(false);
            ind_typo.setOpaque(true);
            ind_btns.setOpaque(false);
            ind_btns1.setOpaque(false);
            ind_fonts.setOpaque(false);
            ind_icons.setOpaque(false);
            resetColor(btn_btns4);
            ind_btns4.setOpaque(false);
            cardLayout.show(pnlRight, "card2");
            videradduser(utcmdp);
            videradduser(utcni);
            videradduser(utemail);
            videradduser(utmdp);
            videradduser(utnom);
            videradduser(utpnom);
            videradduser(uttele);
            videradduser(spec);
            r1.setSelected(true);
            r2.setSelected(false);
            r2.setSelected(false);
            System.gc();
        } catch (Exception e) {
            System.out.print("err");
        }
        
        
    }
    
    public void videradduser(JTextField t){
        t.setText("");
    }
    
    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        
        if(r1.isSelected()){
            
            spec.setEditable(false);
            r2.setSelected(false);
            r3.setSelected(false);

        }
        
        
    }//GEN-LAST:event_r1ActionPerformed

    private void r2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r2ActionPerformed
       
        if(r2.isSelected()){
            
            spec.setEditable(false);
            r1.setSelected(false);
            r3.setSelected(false);

        }
        
    }//GEN-LAST:event_r2ActionPerformed

    private void r3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r3ActionPerformed
        
        if(r3.isSelected()){
            r1.setSelected(false);
            r2.setSelected(false);
            spec.setEditable(true);
        }
        if( !(r3.isSelected())){
            spec.setEditable(false);
        }
        
    }//GEN-LAST:event_r3ActionPerformed

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        
        spec.setEditable(false);
        PlaceHolder p = new PlaceHolder(spec,"  Spécialité du Médecin");
        spec.setForeground(Color.BLACK);
        
        // TODO add your handling code here:
        setColor(btn_fonts);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);
        resetColor(btn_btns4);
        ind_btns4.setOpaque(false);

        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(true);
        ind_icons.setOpaque(false);
        
        //sep.setEditable(false);
        cardLayout.show(pnlRight, "card3"); 
        
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        spec.setEditable(false);
        PlaceHolder p = new PlaceHolder(spec,"  Spécialité du Médecin");
        spec.setForeground(Color.BLACK);
        
        // TODO add your handling code here:
        setColor(btn_fonts);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);
        resetColor(btn_btns4);
        ind_btns4.setOpaque(false);

        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(true);
        ind_icons.setOpaque(false);
        
        //sep.setEditable(false);
        cardLayout.show(pnlRight, "card3");
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btn_icons1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_icons1MousePressed
        
        
     // TODO add your handling code here:
         resetColor(btn_btns3); 
        ind_btns3.setOpaque(false);
        setColor( btn_icons1);
        resetColor(btn_fonts);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
                resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(true);
        
        //sep.setEditable(false);
        cardLayout.show(pnlRight, "card5");   
        
        
        
        
        
    }//GEN-LAST:event_btn_icons1MousePressed

    private void consvalider1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consvalider1ActionPerformed
        
        if(s.isSelected()){
            
            if(a.getText().equals("")){
                JOptionPane.showMessageDialog(this, " Attention entrer au moins une Nom de Symptômes ");
            }
            else{    
                if( !( a.getText().equals("")) ) { addsym(a.getText()); videradduser(a);}
                if( !( b.getText().equals("")) ) { addsym(b.getText()); videradduser(b);}
                if( !( c.getText().equals("")) ) { addsym(c.getText()); videradduser(c);}
                if( !( d.getText().equals("")) )  { addsym(d.getText()); videradduser(d);}
                JOptionPane.showMessageDialog(this, " Symptômes a été Bien Ajouter ");
                s.setSelected(false);
            }
        }
        
        if(m.isSelected()){
            if(e.getText().equals("")){
                JOptionPane.showMessageDialog(this, " Attention entrer au moins une Nom de Maladies ");
            }
            else {
                if( !( e.getText().equals("")) ) { addmal(e.getText()); videradduser(e);}
                if( !( f.getText().equals("")) ) { addmal(f.getText()); videradduser(f);}
                if( !( g.getText().equals("")) ) { addmal(g.getText()); videradduser(g);}
                if( !( h.getText().equals("")) ) { addmal(h.getText()); videradduser(h);}
                JOptionPane.showMessageDialog(this, " Maladies a été Bien Ajouter ");
                m.setSelected(false);
                System.gc();
            }
        }
        
                rlistemalsym();
                setColor(btn_btns);
                resetColor(btn_btns1);
                resetColor(btn_typo);
                resetColor(btn_fonts);
                resetColor(btn_icons);
                resetColor(btn_icons1);
                resetColor(btn_btns3);
                resetColor(btn_btns4);
                ind_btns4.setOpaque(false);
                ind_btns3.setOpaque(false);
                ind_typo.setOpaque(false);
                ind_btns.setOpaque(true);
                ind_btns1.setOpaque(false);
                ind_fonts.setOpaque(false);
                ind_icons.setOpaque(false);
                ind_icons1.setOpaque(false);
                cardLayout.show(pnlRight, "card6");
        
        
        
    }//GEN-LAST:event_consvalider1ActionPerformed
    
    public void addsym(String str){
        
       String query = "INSERT INTO symptome (id_s,type_s)"
                + "VALUES( uuid() ,'"+str+"') ;";
       
        try {
            session.execute(query);
            System.gc();  
        } catch (Exception e) {
            System.out.print("err");
        }
        
        System.out.println(query);
    }
    public void addmal(String str){
        
        String query = "INSERT INTO maladies (id_m,nom)"
                + "VALUES( uuid() ,'"+str+"') ;";
        
        try {
            session.execute(query);
            System.gc();
        } catch (Exception e) {
            System.out.print("err");
        }
        
        System.out.println(query);
        
    }
    
    
    private void mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        setColor( btn_icons1);
        resetColor(btn_fonts);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_typo);
        resetColor(btn_icons);
                resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(true);
        
        //sep.setEditable(false);
        cardLayout.show(pnlRight, "card5");  
    }//GEN-LAST:event_jLabel4MouseClicked

    private void eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eActionPerformed

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sActionPerformed

    private void btn_btns1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_btns1MousePressed
         resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        resetColor(btn_btns3); 
        ind_btns3.setOpaque(false);
        resetColor(btn_btns);
        setColor(btn_btns1);
        resetColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_icons1);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(true);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(false);
        
        cardLayout.show(pnlRight, "card7");
        
    }//GEN-LAST:event_btn_btns1MousePressed

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
                + " '" + patientmed.getText() + "');";
        
        String tr = patientnom.getText().toUpperCase()+" "+patientpnom.getText().toUpperCase()+" Ajouté avec succès";

        try {
            
            session.execute(cqlStatementt);
            
            JOptionPane.showMessageDialog(this, tr);

            setColor(btn_typo);
            resetColor(btn_btns1);
            resetColor(btn_btns);
            resetColor(btn_fonts);
            resetColor(btn_icons);
            ind_typo.setOpaque(true);
            ind_btns.setOpaque(false);
            ind_btns1.setOpaque(false);
            ind_fonts.setOpaque(false);
            ind_icons.setOpaque(false);
            resetColor(btn_btns3); 
           resetColor(btn_btns4);
             ind_btns4.setOpaque(false);
            ind_btns3.setOpaque(false);
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
            patientmed.setText("");
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
            
            cardLayout.show(pnlRight, "card2");
            
        } catch (Exception e) {
            System.out.println("err");
            
        }

        System.out.println(cqlStatementt);
    }
        
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jRadioButton2.setSelected(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed

        jRadioButton1.setSelected(false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void patientpnomFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientpnomFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientpnomFocusGained

    private void patientpnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientpnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientpnomActionPerformed

    private void patientnomFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientnomFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientnomFocusGained

    private void patientnomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientnomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientnomActionPerformed

    private void patientcinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientcinFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientcinFocusGained

    private void patientcinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientcinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientcinActionPerformed

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

    private void patientproffessionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientproffessionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientproffessionFocusGained

    private void patientproffessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientproffessionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientproffessionActionPerformed

    private void patientadresseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientadresseFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientadresseFocusGained

    private void patientadresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientadresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientadresseActionPerformed

    private void patientvilleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientvilleFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientvilleFocusGained

    private void patientvilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientvilleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientvilleActionPerformed

    private void patientteleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientteleFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientteleFocusGained

    private void patientteleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientteleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientteleActionPerformed

    private void patientemailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientemailFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientemailFocusGained

    private void patientemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientemailActionPerformed

    private void patientnatinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientnatinFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientnatinFocusGained

    private void patientnatinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientnatinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientnatinActionPerformed

    private void patientlieunaissFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientlieunaissFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientlieunaissFocusGained

    private void patientlieunaissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientlieunaissActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientlieunaissActionPerformed

    private void patientmedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_patientmedFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_patientmedFocusGained

    private void patientmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientmedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientmedActionPerformed

    private void btn_btns2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_btns2MousePressed
        
        resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        resetColor(btn_btns);
        resetColor(btn_btns1);
        resetColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_icons1);
        setColor(btn_btns2);
        resetColor(btn_btns3); 
        ind_btns3.setOpaque(false);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_btns2.setOpaque(true);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(false);
        
        listepatient();
        
        cardLayout.show(pnlRight, "card8");
        
        
    }//GEN-LAST:event_btn_btns2MousePressed
    
    public void listepatient(){
        
        listU3.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        listU3.getTableHeader().setOpaque(false);
        listU3.getTableHeader().setBackground(new Color(255,204,0));
        listU3.getTableHeader().setForeground(new Color(0,0,0));
        
        DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) listU3.getDefaultRenderer(Object.class);
        rendere.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        String query = "SELECT * FROM patient ;";
        
        DefaultTableModel yourModel = (DefaultTableModel) listU3.getModel();
        
        yourModel.setRowCount(0);
        
        try {
            for (Row n : session.execute(query)) {
                yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(7)+" "+n.getString(10).toUpperCase(),
                    n.getDate(2).toString(),n.getString(12).toUpperCase()});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
    }
    
    
    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
       
        resetColor(btn_btns);
        setColor(btn_btns1);
        resetColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_icons1);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
        resetColor(btn_btns3);
                resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        ind_btns3.setOpaque(false);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(true);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(false);
        
        cardLayout.show(pnlRight, "card7");
    }//GEN-LAST:event_jLabel10MouseClicked
    
    
    public void rtabfindu(){
        
        finduser.setText("");
        
        String query = "SELECT * FROM users ;";
        
        DefaultTableModel yourModel = (DefaultTableModel) tabfindu.getModel();
        
        yourModel.setRowCount(0);
        
        try {
            for (Row n : session.execute(query)) {
                yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(1),n.getString(2),n.getString(7)});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
    }
    
    private void btn_btns3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_btns3MousePressed
        
        
        resetColor(btn_btns);
        resetColor(btn_btns1);
        resetColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_icons1);
        resetColor(btn_btns2);
        resetColor(btn_btns4);
        setColor(btn_btns3);

        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_btns2.setOpaque(false);
        ind_btns3.setOpaque(true);
        ind_btns4.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(false);
        
        tabfindu.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tabfindu.getTableHeader().setOpaque(false);
        tabfindu.getTableHeader().setBackground(new Color(255,204,0));
        tabfindu.getTableHeader().setForeground(new Color(0,0,0));
        
        DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) tabfindu.getDefaultRenderer(Object.class);
        rendere.setHorizontalAlignment(SwingConstants.CENTER);

        rtabfindu();
        
        cardLayout.show(pnlRight, "card11");
        
    }//GEN-LAST:event_btn_btns3MousePressed

    private void listeconsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listeconsMouseClicked
              JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            String str=source.getModel().getValueAt(row, 0)+"";
        
          final JPopupMenu popupmenu = new JPopupMenu("Edit");   
          JMenuItem m = new JMenuItem("Modifer");  
          JMenuItem s = new JMenuItem("Supprimer");  

          m.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          m.setForeground(new Color(255,255,255));
          m.setBackground(new Color(41,168,73));
          s.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          s.setForeground(new Color(255,255,255));
          s.setBackground(new Color(41,168,73));
          s.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\delete.png"));
          m.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\edit.png"));

          popupmenu.add(m); popupmenu.add(s); 
          popupmenu.show(evt.getComponent(), evt.getX(), evt.getY()); 

         m.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {  
             
               int input = JOptionPane.showConfirmDialog(null,
                "Attention Vous êtes sûr de modifier ce Utilisateur !", "Modifier Un Utilisateur",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                if(input == 0) modifieruser(str);
                if( input == 1 || input == 2 ) rlistecons();
         }  
         });
         s.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {              
             int input = JOptionPane.showConfirmDialog(null, "Pour supprimer, vous devez confirmer !", "Supprimer Un Utilisateur",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);            
             if(input == 0) deleteuser(str);
             if( input == 1 || input == 2 ) rlistecons();
 
         }  
         });
    }//GEN-LAST:event_listeconsMouseClicked

    private void listUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listUMouseClicked
             JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            String str=source.getModel().getValueAt(row, 0)+"";
        
          final JPopupMenu popupmenu = new JPopupMenu("Edit");   
          JMenuItem m = new JMenuItem("Modifer");  
          JMenuItem s = new JMenuItem("Supprimer");  

          m.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          m.setForeground(new Color(255,255,255));
          m.setBackground(new Color(41,168,73));
          s.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          s.setForeground(new Color(255,255,255));
          s.setBackground(new Color(41,168,73));
          s.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\delete.png"));
          m.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\edit.png"));
          
          popupmenu.add(m); popupmenu.add(s); 
          popupmenu.show(evt.getComponent(), evt.getX(), evt.getY()); 

         m.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {  
             
               int input = JOptionPane.showConfirmDialog(null,
                "Attention Vous êtes sûr de modifier ce Utilisateur !", "Modifier Un Utilisateur",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                if(input == 0) modifieruser(str);
                if( input == 1 || input == 2 ) rlistU();
         }  
         });
         s.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {              
             int input = JOptionPane.showConfirmDialog(null, "Pour supprimer, vous devez confirmer !", "Supprimer Un Utilisateur",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);            
             if(input == 0) deleteuser(str);
             if( input == 1 || input == 2 ) rlistU();
         }  
         });
    }//GEN-LAST:event_listUMouseClicked
    
    
    public void modifieruser(String str){
        
        String query = "select * from users where cne = '"+str+"'ALLOW FILTERING; ";
        try {
            Row n = session.execute(query).one();
            mcne.setText(str);
            String tele = "0"+n.getInt(4);
            mtele.setText(tele);
            mnom.setText(n.getString(3));
            mpnom.setText(n.getString(5));
            memail.setText(n.getString(1));
            mmdp.setText(n.getString(2));
            mtypecmp.setText(n.getString(7));
            mspec.setText(n.getString(6));

        } catch (Exception e) {
            System.out.print("err");
        }
        resetColor(btn_icons1);
        ind_icons1.setOpaque(false);
        resetColor(btn_icons);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_fonts);
        resetColor(btn_typo);
        resetColor(btn_btns3);
        resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        ind_btns3.setOpaque(false);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        finduser.setText("");
        mcne.setEditable(false);
        cardLayout.show(pnlRight, "card9");
    }
    
    
    private void listU3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listU3MouseClicked
             JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            String str=source.getModel().getValueAt(row, 0)+"";
        
          final JPopupMenu popupmenu = new JPopupMenu("Edit");   
          JMenuItem m = new JMenuItem("Modifer");  
          JMenuItem s = new JMenuItem("Supprimer");  

          m.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          m.setForeground(new Color(255,255,255));
          m.setBackground(new Color(41,168,73));
          s.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          s.setForeground(new Color(255,255,255));
          s.setBackground(new Color(41,168,73));
           s.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\delete.png"));
          m.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\edit.png"));
          
          popupmenu.add(m); popupmenu.add(s); 
          popupmenu.show(evt.getComponent(), evt.getX(), evt.getY()); 

         m.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {  
             
               int input = JOptionPane.showConfirmDialog(null,
                "Attention Vous êtes sûr de modifier ce Patient !", "Modifier Un Patient",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                if(input == 0) editpatient(str);
                if(input == 1 || input == 2) listepatient();
         }  
         });
         s.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {              
             int input = JOptionPane.showConfirmDialog(null, "Pour supprimer, vous devez confirmer !", "Supprimer Un Patient",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);            
             if(input == 0) deletepatient(str);
             if(input == 1 || input == 2) listepatient();
 
         }  
         });
    }//GEN-LAST:event_listU3MouseClicked
    
    
    public void editpatient(String str){
        
        String query = "select * FROM patient WHERE cne='"+str+"' ALLOW FILTERING;";
        try {
                Row n = session.execute(query).one();
                
                edpcne.setText(str);
                edpcne.setEditable(false);
                edpmedcne.setText(n.getString(5));
                edpadresse.setText(n.getString(1));
                edpdate.setText(n.getDate(2).toString());
                edpemail.setText(n.getString(3));
                edplieu.setText(n.getString(4));
                edpmere.setText(n.getString(8));
                edppere.setText(n.getString(9));
                edpnationnaliter.setText(n.getString(6));
                edpproff.setText(n.getString(11));
                edpnom.setText(n.getString(7));
                edppnom.setText(n.getString(10));
                edpsexe.setText(n.getString(12));
                edpville.setText(n.getString(14));
                String tele = "0"+n.getInt(13);
                edptele.setText(tele);
                
                resetColor(btn_btns);
                resetColor(btn_btns1);
                resetColor(btn_typo);
                resetColor(btn_fonts);
                resetColor(btn_icons);
                resetColor(btn_icons1);
                resetColor(btn_btns2);
                resetColor(btn_btns3); 
                resetColor(btn_btns4);
                ind_btns4.setOpaque(false);
                ind_btns3.setOpaque(false);
                ind_typo.setOpaque(false);
                ind_btns.setOpaque(false);
                ind_btns1.setOpaque(false);
                ind_btns2.setOpaque(false);
                ind_fonts.setOpaque(false);
                ind_icons.setOpaque(false);
                ind_icons1.setOpaque(false);



                cardLayout.show(pnlRight, "card10");
                finduser1.setText("");
                rfindpt();
                listepatient();
                System.gc();
   
        } catch (Exception e) {
            System.out.print("505");
            JOptionPane.showMessageDialog(this, "505");
        } 
        
    }
    
        public void updatesym(String old,String neww){
        
        String tr = "Symptômes "+old.toUpperCase()+" a été mis à jour";
        String trr = "Symptômes "+old.toUpperCase()+" n'a pas été mis à jour";
        String queryy = "select * FROM symptome WHERE type_s='"+old+"' ALLOW FILTERING;";
        
         try {
                Row n = session.execute(queryy).one();              
                String query = "UPDATE symptome set type_s='"+neww+"' WHERE id_s="+n.getUUID(0)+";";
                System.out.print(query);
                session.execute(query).one();
                JOptionPane.showMessageDialog(this, tr);
                System.gc();
             
        } catch (Exception e) {
            System.out.print("505");
            JOptionPane.showMessageDialog(this, tr);
        }
        rlistemalsym();
   
    }
        public void updatemal(String old,String neww){
        
        String tr = "Maladie "+old.toUpperCase()+" a été mis à jour";
        String trr = "Maladie "+old.toUpperCase()+" n'a pas été mis à jour";
        String queryy = "select * FROM maladies WHERE nom='"+old+"' ALLOW FILTERING;";
        
         try {
                Row n = session.execute(queryy).one();              
                String query = "UPDATE maladies set nom='"+neww+"' WHERE id_m="+n.getUUID(0)+";";
                System.out.print(query);
                session.execute(query).one();
                JOptionPane.showMessageDialog(this, tr);
                System.gc();
             
        } catch (Exception e) {
            System.out.print("505");
            JOptionPane.showMessageDialog(this, tr);
        }
        rlistemalsym();
   
    }
        
    private void listU1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listU1MouseClicked
            JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            String str=source.getModel().getValueAt(row, 0)+"";
        
          final JPopupMenu popupmenu = new JPopupMenu("Edit");   
          JMenuItem m = new JMenuItem("Modifer");  
          JMenuItem s = new JMenuItem("Supprimer");  

          m.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          m.setForeground(new Color(255,255,255));
          m.setBackground(new Color(41,168,73));
          s.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          s.setForeground(new Color(255,255,255));
          s.setBackground(new Color(41,168,73));
          s.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\delete.png"));
          m.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\edit.png"));
          
          popupmenu.add(m); popupmenu.add(s); 
          popupmenu.show(evt.getComponent(), evt.getX(), evt.getY()); 

         m.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {  
             
               int input = JOptionPane.showConfirmDialog(null,
                "Attention Vous êtes sûr de modifier ce Symptômes !", "Modifier Un Symptômes",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                // 0=yes, 1=no, 2=cancel
               if(input == 0){
                   JPanel panel = new JPanel(new BorderLayout(5, 5));
                JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
                label.add(new JLabel("", SwingConstants.RIGHT));
                panel.add(label, BorderLayout.WEST);
                JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
                JTextField username = new JTextField();
                username.setText(str);
                username.setFont(new Font("Segoe UI", Font.BOLD, 16));
                username.setForeground(Color.BLACK);
                controls.add(username);
                panel.add(controls, BorderLayout.CENTER);
                if(username.getText().equalsIgnoreCase(str)) rlistemalsym();
                if(username.getText().equalsIgnoreCase(str)){
                    JOptionPane.showMessageDialog(null, panel, "Modifier Le Nom du Symptômes", JOptionPane.DEFAULT_OPTION);
                    if(username.getText().equalsIgnoreCase(str)) rlistemalsym();
                    else updatesym(str,username.getText());
                }
                
               }
                else rlistemalsym();

         }  
         });
         s.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {              
             int input = JOptionPane.showConfirmDialog(null, "Pour supprimer, vous devez confirmer !", "Supprimer Un Symptômes",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);            
             if(input == 0) deletesym(str);
             if(input != 0) rlistemalsym();
         }  
         });
    }//GEN-LAST:event_listU1MouseClicked
    

    
    
    private void listU2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listU2MouseClicked
             JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            String str=source.getModel().getValueAt(row, 0)+"";
        
          final JPopupMenu popupmenu = new JPopupMenu("Edit");   
          JMenuItem m = new JMenuItem("Modifer");  
          JMenuItem s = new JMenuItem("Supprimer");  

          m.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          m.setForeground(new Color(255,255,255));
          m.setBackground(new Color(41,168,73));
          s.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          s.setForeground(new Color(255,255,255));
          s.setBackground(new Color(41,168,73));
          s.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\delete.png"));
          m.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\edit.png"));
          
          popupmenu.add(m); popupmenu.add(s); 
          popupmenu.show(evt.getComponent(), evt.getX(), evt.getY()); 

         m.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {  
             
               int input = JOptionPane.showConfirmDialog(null,
                "Attention Vous êtes sûr de modifier ce Maladie !", "Modifier Un Maladie",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                // 0=yes, 1=no, 2=cancel
                if( input == 0) {
                   JPanel panel = new JPanel(new BorderLayout(5, 5));
                JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
                label.add(new JLabel("", SwingConstants.RIGHT));
                panel.add(label, BorderLayout.WEST);
                JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
                JTextField username = new JTextField();
                username.setText(str);
                username.setFont(new Font("Segoe UI", Font.BOLD, 16));
                username.setForeground(Color.BLACK);
                controls.add(username);
                panel.add(controls, BorderLayout.CENTER);
                if(username.getText().equalsIgnoreCase(str)) rlistemalsym();
                if(username.getText().equalsIgnoreCase(str)){
                    JOptionPane.showMessageDialog(null, panel, "Modifier Le Nom du Maladie", JOptionPane.DEFAULT_OPTION);
                    if(username.getText().equalsIgnoreCase(str)) rlistemalsym();
                    else updatemal(str,username.getText());
                } 
                }
                else rlistemalsym();
                
         }  
         });
         s.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {              
             int input = JOptionPane.showConfirmDialog(null, "Pour supprimer, vous devez confirmer !", "Supprimer Un Maladie",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);            
             if(input == 0) deletemal(str);
             if(input != 0) rlistemalsym();
         }  
         });
    }//GEN-LAST:event_listU2MouseClicked

    private void consvalider2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consvalider2ActionPerformed
        
        
        if(mtypecmp.getText().equalsIgnoreCase("medcin")){
            if(mspec.getText().equals("")) JOptionPane.showMessageDialog(this, " Attention entrer la Spécialité du Médecin ");
            else{
                if( mcne.getText().equals("") ||
                     mtele.getText().equals("") ||
                     mnom.getText().equals("") ||
                     mpnom.getText().equals("") ||
                     memail.getText().equals("") ||
                     mtypecmp.getText().equals("") 
                        ) JOptionPane.showMessageDialog(this, " Attention entrer toutes les Informations ");
                else updateuser(mcne.getText());
            }
        }
        else {
            if( mcne.getText().equals("") ||
                     mtele.getText().equals("") ||
                     mnom.getText().equals("") ||
                     mpnom.getText().equals("") ||
                     memail.getText().equals("") ||
                     mtypecmp.getText().equals("") 
                        ) JOptionPane.showMessageDialog(this, " Attention entrer toutes les Informations ");
                else updateuser(mcne.getText());
        }
  
    }//GEN-LAST:event_consvalider2ActionPerformed

    private void consvalider3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consvalider3ActionPerformed
        
        
        if(     edpcne.getText().equals("") ||
                edpmedcne.getText().equals("") ||
                edpadresse.getText().equals("") ||
                edpdate.getText().equals("") ||
                edpemail.getText().equals("") ||
                edplieu.getText().equals("") ||
                edpmere.getText().equals("") ||
                edppere.getText().equals("") ||
                edpnationnaliter.getText().equals("") ||
                edpproff.getText().equals("") ||
                edpnom.getText().equals("") ||
                edppnom.getText().equals("") ||
                edpsexe.getText().equals("") ||
                edpville.getText().equals("") ||
                edptele.getText().equals("") ) JOptionPane.showMessageDialog(this, " Attention entrer toutes les Informations ");
            
        else updatepatient(edpcne.getText());
      
    }//GEN-LAST:event_consvalider3ActionPerformed

    private void btn_btns4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_btns4MousePressed
        resetColor(btn_btns);
        resetColor(btn_btns1);
        resetColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_icons1);
        resetColor(btn_btns2);
        resetColor(btn_btns3); 
        setColor(btn_btns4); 
        ind_btns3.setOpaque(false);
        ind_btns4.setOpaque(true);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_btns2.setOpaque(false);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(false);
        
        rfindpt();
        
        cardLayout.show(pnlRight, "card12");
    }//GEN-LAST:event_btn_btns4MousePressed
    
    public void rfindpt(){
        
        findpt.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        findpt.getTableHeader().setOpaque(false);
        findpt.getTableHeader().setBackground(new Color(255,204,0));
        findpt.getTableHeader().setForeground(new Color(0,0,0));
        
        DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) findpt.getDefaultRenderer(Object.class);
        rendere.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        String query = "SELECT * FROM patient ;";
        
        DefaultTableModel yourModel = (DefaultTableModel) findpt.getModel();
        
        yourModel.setRowCount(0);
        
        try {
            for (Row n : session.execute(query)) {
                yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(7)+" "+n.getString(10).toUpperCase(),
                    n.getDate(2).toString(),n.getString(12).toUpperCase()});
            }

        } catch (Exception e) {
            System.out.print("err");
        }
        
    }
    
    private void jLabel79MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel79MouseClicked
        spec.setEditable(false);
        PlaceHolder p = new PlaceHolder(spec,"  Spécialité du Médecin");
        spec.setForeground(Color.BLACK);
        
        // TODO add your handling code here:
        setColor(btn_fonts);
        resetColor(btn_btns1);
        resetColor(btn_btns);
        resetColor(btn_btns3);
        resetColor(btn_typo);
        resetColor(btn_icons);
        resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        //indicators
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(false);
        ind_btns3.setOpaque(false);
        ind_fonts.setOpaque(true);
        ind_icons.setOpaque(false);
        
        //sep.setEditable(false);
        cardLayout.show(pnlRight, "card3");
    }//GEN-LAST:event_jLabel79MouseClicked

    private void tabfinduMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabfinduMouseClicked
           JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            String str=source.getModel().getValueAt(row, 0)+"";
        
          final JPopupMenu popupmenu = new JPopupMenu("Edit");   
          JMenuItem m = new JMenuItem("Modifer");  
          JMenuItem s = new JMenuItem("Supprimer");  

          m.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          m.setForeground(new Color(255,255,255));
          m.setBackground(new Color(41,168,73));
          s.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          s.setForeground(new Color(255,255,255));
          s.setBackground(new Color(41,168,73));
          s.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\delete.png"));
          m.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\edit.png"));
          
          popupmenu.add(m); popupmenu.add(s); 
          popupmenu.show(evt.getComponent(), evt.getX(), evt.getY()); 

         m.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {  
             
               int input = JOptionPane.showConfirmDialog(null,
                "Attention Vous êtes sûr de modifier ce Utilisateur !", "Modifier Un Utilisateur",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                if(input == 0) modifieruser(str);
                if( input == 1 || input == 2 ) rtabfindu();
         }  
         });
         s.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {              
             int input = JOptionPane.showConfirmDialog(null, "Pour supprimer, vous devez confirmer !", "Supprimer Un Utilisateur",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);            
             if(input == 0) deleteuser(str);
             if( input == 1 || input == 2 ) rtabfindu();
         }  
         });
    }//GEN-LAST:event_tabfinduMouseClicked

    private void finduserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_finduserKeyReleased
        
        chercher(finduser.getText());
    }//GEN-LAST:event_finduserKeyReleased

    private void jLabel83MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel83MouseClicked
         resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
        resetColor(btn_btns3); 
        ind_btns3.setOpaque(false);
        resetColor(btn_btns);
        setColor(btn_btns1);
        resetColor(btn_typo);
        resetColor(btn_fonts);
        resetColor(btn_icons);
        resetColor(btn_icons1);
        ind_btns2.setOpaque(false);
        resetColor(btn_btns2);
        ind_typo.setOpaque(false);
        ind_btns.setOpaque(false);
        ind_btns1.setOpaque(true);
        ind_fonts.setOpaque(false);
        ind_icons.setOpaque(false);
        ind_icons1.setOpaque(false);
        
        cardLayout.show(pnlRight, "card7");
    }//GEN-LAST:event_jLabel83MouseClicked

    private void findptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findptMouseClicked
        
        
            JTable source = (JTable)evt.getSource();
            int row = source.rowAtPoint( evt.getPoint() );
            String str=source.getModel().getValueAt(row, 0)+"";
        
          final JPopupMenu popupmenu = new JPopupMenu("Edit");   
          JMenuItem m = new JMenuItem("Modifer");  
          JMenuItem s = new JMenuItem("Supprimer");  

          m.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          m.setForeground(new Color(255,255,255));
          m.setBackground(new Color(41,168,73));
          s.setFont(new Font("Segoe UI", Font.PLAIN, 20));
          s.setForeground(new Color(255,255,255));
          s.setBackground(new Color(41,168,73));
           s.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\delete.png"));
          m.setIcon(new ImageIcon("C:\\Users\\Dell\\Desktop\\StageFinDF\\src\\stagefindf\\images\\edit.png"));
          
          popupmenu.add(m); popupmenu.add(s); 
          popupmenu.show(evt.getComponent(), evt.getX(), evt.getY()); 

         m.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {  
             
               int input = JOptionPane.showConfirmDialog(null,
                "Attention Vous êtes sûr de modifier ce Patient !", "Modifier Un Patient",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                if(input == 0) editpatient(str);
                if(input == 1 || input == 2) rfindpt();
         }  
         });
         s.addActionListener(new ActionListener(){  
         public void actionPerformed(ActionEvent e) {              
             int input = JOptionPane.showConfirmDialog(null, "Pour supprimer, vous devez confirmer !", "Supprimer Un Patient",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);            
             if(input == 0) deletepatient(str);
             if(input == 1 || input == 2) rfindpt();
 
         }  
         });
        
        
    }//GEN-LAST:event_findptMouseClicked

    private void finduser1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_finduser1KeyReleased
        
        
        findpat(finduser1.getText());
        
        
    }//GEN-LAST:event_finduser1KeyReleased
    
    public void findpat(String str){
        
        
        findpt.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        findpt.getTableHeader().setOpaque(false);
        findpt.getTableHeader().setBackground(new Color(255,204,0));
        findpt.getTableHeader().setForeground(new Color(0,0,0));
        
        

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) findpt.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableModel yourModel = (DefaultTableModel) findpt.getModel();
        
        if(str.equals("")){
            yourModel.setRowCount(0);
            rfindpt();
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
    public void chercher(String str){
        
        tabfindu.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tabfindu.getTableHeader().setOpaque(false);
        tabfindu.getTableHeader().setBackground(new Color(255,204,0));
        tabfindu.getTableHeader().setForeground(new Color(0,0,0));
        
        DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) tabfindu.getDefaultRenderer(Object.class);
        rendere.setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableModel yourModel = (DefaultTableModel) tabfindu.getModel();
        
         if(str.equals("")){
            yourModel.setRowCount(0);
            rtabfindu();
        }
        else{
             
               String cql = " SELECT * FROM users WHERE email LIKE '%"+str+"%';";
               int i=0;
               try {

                   yourModel.setRowCount(0);
                        
                   for (Row n : session.execute(cql)) {
                       i++;
                   }
                   if(i > 0){
                       for (Row n : session.execute(cql)) {
                       yourModel.addRow(new Object[]{n.getString(0).toUpperCase(),n.getString(1),n.getString(2),n.getString(7)});
                        }
                   }
                   if(i == 0 ){
                       
                       yourModel.addRow(new Object[]{"NULL","NULL","NULL","NULL"});
                        
                   }

               } catch (Exception e) {
                   System.out.print("err");
               }
        }

        
    }
    
    public void updatepatient(String str){
        
         String query = "UPDATE patient " +
                "  SET email = '"+edpemail.getText()+"',"+
                "   sexe = '"+edpsexe.getText()+"',"+
                "   nom = '"+edpnom.getText()+"',"+
                "   prenom = '"+edppnom.getText()+"',"+
                "   telephone = "+edptele.getText()+","+
                "   ville = '"+edpville.getText()+"',"+
                "   address = '"+edpadresse.getText()+"',"+
                "   date_naissance = '"+edpdate.getText()+"',"+
                "   lieu_naissance = '"+edplieu.getText()+"',"+
                "   profession = '"+edpproff.getText()+"',"+
                "   origine_pere = '"+edppere.getText()+"',"+
                "   origine_mere = '"+edpmere.getText()+"',"+
                "   nationnalite  = '"+edpnationnaliter.getText()+"',"+
                "   medc_cne  = '"+edpmedcne.getText()+"'"+
                "  WHERE cne = '"+str+"';";
         
                try {
                      Row n = session.execute(query).one();

                      JOptionPane.showMessageDialog(this, " Le Patient a été Mis à jour ");

                        resetColor(btn_btns);
                        resetColor(btn_btns1);
                        resetColor(btn_typo);
                        resetColor(btn_fonts);
                        resetColor(btn_icons);
                        resetColor(btn_icons1);
                        setColor(btn_btns2);
                                resetColor(btn_btns4);
                        ind_btns4.setOpaque(false);
                        ind_typo.setOpaque(false);
                        ind_btns.setOpaque(false);
                        ind_btns1.setOpaque(false);
                        ind_btns2.setOpaque(true);
                        ind_fonts.setOpaque(false);
                        ind_icons.setOpaque(false);
                        ind_icons1.setOpaque(false);
                         resetColor(btn_btns3); 
                        ind_btns3.setOpaque(false);
                        listepatient();

                        cardLayout.show(pnlRight, "card8");

                      System.gc();

              } catch (Exception e) {
                  System.out.print("505");
                  JOptionPane.showMessageDialog(this, "505");
              } 
        
    }
    public void updateuser(String str){
        
        
        String query = "UPDATE users " +
                "  SET email = '"+memail.getText()+"',"+
                "   mdp = '"+mmdp.getText()+"',"+
                "   nom = '"+mnom.getText()+"',"+
                "   prenom = '"+mpnom.getText()+"',"+
                "   ntele = "+mtele.getText()+","+
                "   specialite = '"+mspec.getText()+"',"+
                "   type_cmp = '"+mtypecmp.getText()+"'"+
                "  WHERE cne = '"+str+"';";
        System.out.print("505>"+query);
          try {
                Row n = session.execute(query).one();
                
                JOptionPane.showMessageDialog(this, " L'utilisateur a été Mis à jour ");
                
                resetColor(btn_icons1);
                ind_icons1.setOpaque(false);
                setColor(btn_icons);
                resetColor(btn_btns1);
                resetColor(btn_btns);
                resetColor(btn_fonts);
                resetColor(btn_typo);
                ind_btns2.setOpaque(false);
                resetColor(btn_btns2);
                resetColor(btn_btns3);
                        resetColor(btn_btns4);
        ind_btns4.setOpaque(false);
                ind_typo.setOpaque(false);
                ind_btns.setOpaque(false);
                ind_btns1.setOpaque(false);
                ind_btns3.setOpaque(false);
                ind_fonts.setOpaque(false);
                ind_icons.setOpaque(true);
                listU.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 18));
                listU.getTableHeader().setOpaque(false);
                listU.getTableHeader().setBackground(new Color(255,204,0));
                listU.getTableHeader().setForeground(new Color(0,0,0));

                DefaultTableCellRenderer rendere = (DefaultTableCellRenderer) listU.getDefaultRenderer(Object.class);
                rendere.setHorizontalAlignment(SwingConstants.CENTER);
                
                
                rtabfindu();
                rlistU();

                cardLayout.show(pnlRight, "card4");
                
                System.gc();
   
        } catch (Exception e) {
            System.out.print("505");
            JOptionPane.showMessageDialog(this, "505");
        }   
    }

    public void deletemal(String str){
        
       String tr = "Maladie "+str.toUpperCase()+" Supprimer avec succès";
        String trr = "Maladie "+str.toUpperCase()+" n'a pas été supprimé";
        String queryy = "select * FROM maladies WHERE nom='"+str+"' ALLOW FILTERING;";
        
         try {
                Row n = session.execute(queryy).one();
                
                String query = "DELETE FROM maladies WHERE id_m="+n.getUUID(0)+";";
               
                session.execute(query).one();
                JOptionPane.showMessageDialog(this, tr);
             
        } catch (Exception e) {
            System.out.print("505");
            JOptionPane.showMessageDialog(this, tr);
        }
        rlistemalsym();
        
    }
    public void deletesym(String str){
        
        String tr = "Symptômes "+str.toUpperCase()+" Supprimer avec succès";
        String trr = "Symptômes "+str.toUpperCase()+" n'a pas été supprimé";
        String queryy = "select * FROM symptome WHERE type_s='"+str+"' ALLOW FILTERING;";
        
         try {
                Row n = session.execute(queryy).one();
                
                String query = "DELETE FROM symptome WHERE id_s="+n.getUUID(0)+";";
               
                session.execute(query).one();
                JOptionPane.showMessageDialog(this, tr);
             
        } catch (Exception e) {
            System.out.print("505");
            JOptionPane.showMessageDialog(this, tr);
        }
        rlistemalsym();
        
    }
    
    
    public void deleteuser(String str){
       
        String tr = "l'Utilisateur "+str.toUpperCase()+" Supprimer avec succès";
        String trr = "l'Utilisateur "+str.toUpperCase()+" n'a pas été supprimé";
        String query = "DELETE FROM users WHERE cne='"+str+"';";
         try {
                session.execute(query).one();
                JOptionPane.showMessageDialog(this, tr);
             
        } catch (Exception e) {
            System.out.print("err");
            JOptionPane.showMessageDialog(this, tr);
        }
        finduser.setText("");
        rlistecons();
        rlistU();
        rtabfindu();
    }  
    public void deletepatient(String str){
       
        String tr = "Patient "+str.toUpperCase()+" Supprimer avec succès";
        String trr = "Patient "+str.toUpperCase()+" n'a pas été supprimé";
        String query = "DELETE FROM patient WHERE cne='"+str+"';";
         try {
                session.execute(query).one();
                JOptionPane.showMessageDialog(this, tr);
             
        } catch (Exception e) {
            System.out.print("err");
            JOptionPane.showMessageDialog(this, tr);
        }
        finduser1.setText("");
        listepatient();
        rfindpt();
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Homeadmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Acceuil;
    private javax.swing.JPanel Listsymmal;
    private javax.swing.JTextField a;
    private javax.swing.JLabel adcni;
    private javax.swing.JPanel add;
    private javax.swing.JPanel addpatient;
    private javax.swing.JPanel addsymmal;
    private javax.swing.JLabel ademail;
    private javax.swing.JLabel adnom;
    private javax.swing.JLabel adpnom;
    private javax.swing.JLabel adtele;
    private javax.swing.JTextField b;
    private javax.swing.JPanel btn_btns;
    private javax.swing.JPanel btn_btns1;
    private javax.swing.JPanel btn_btns2;
    private javax.swing.JPanel btn_btns3;
    private javax.swing.JPanel btn_btns4;
    private javax.swing.JPanel btn_fonts;
    private javax.swing.JPanel btn_icons;
    private javax.swing.JPanel btn_icons1;
    private javax.swing.JPanel btn_typo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField c;
    private javax.swing.JLabel cd;
    private java.awt.Button consvalider;
    private java.awt.Button consvalider1;
    private java.awt.Button consvalider2;
    private java.awt.Button consvalider3;
    private javax.swing.JTextField d;
    private javax.swing.JTextField e;
    private javax.swing.JPanel editpatient;
    private javax.swing.JPanel editutilisateur;
    private javax.swing.JTextField edpadresse;
    private javax.swing.JTextField edpcne;
    private javax.swing.JTextField edpdate;
    private javax.swing.JTextField edpemail;
    private javax.swing.JTextField edplieu;
    private javax.swing.JTextField edpmedcne;
    private javax.swing.JTextField edpmere;
    private javax.swing.JTextField edpnationnaliter;
    private javax.swing.JTextField edpnom;
    private javax.swing.JTextField edppere;
    private javax.swing.JTextField edppnom;
    private javax.swing.JTextField edpproff;
    private javax.swing.JTextField edpsexe;
    private javax.swing.JTextField edptele;
    private javax.swing.JTextField edpville;
    private javax.swing.JTextField f;
    private javax.swing.JPanel findpatient;
    private javax.swing.JTable findpt;
    private javax.swing.JTextField finduser;
    private javax.swing.JTextField finduser1;
    private javax.swing.JPanel findutilisateur;
    private javax.swing.JTextField g;
    private javax.swing.JTextField h;
    private javax.swing.JPanel ind_btns;
    private javax.swing.JPanel ind_btns1;
    private javax.swing.JPanel ind_btns2;
    private javax.swing.JPanel ind_btns3;
    private javax.swing.JPanel ind_btns4;
    private javax.swing.JPanel ind_fonts;
    private javax.swing.JPanel ind_icons;
    private javax.swing.JPanel ind_icons1;
    private javax.swing.JPanel ind_typo;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblMaximize;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable listU;
    private javax.swing.JTable listU1;
    private javax.swing.JTable listU2;
    private javax.swing.JTable listU3;
    private javax.swing.JPanel listeU;
    private javax.swing.JTable listecons;
    private javax.swing.JPanel listepatient;
    private javax.swing.JRadioButton m;
    private javax.swing.JTextField mcne;
    private javax.swing.JTextField memail;
    private javax.swing.JTextField mmdp;
    private javax.swing.JTextField mnom;
    private javax.swing.JTextField mpnom;
    private javax.swing.JTextField mspec;
    private javax.swing.JTextField mtele;
    private javax.swing.JTextField mtypecmp;
    private javax.swing.JTextField patientadresse;
    private javax.swing.JTextField patientcin;
    private com.toedter.calendar.JDateChooser patientdate;
    private javax.swing.JTextField patientemail;
    private javax.swing.JTextField patientlieunaiss;
    private javax.swing.JTextField patientmed;
    private javax.swing.JTextField patientmere;
    private javax.swing.JTextField patientnatin;
    private javax.swing.JTextField patientnom;
    private javax.swing.JTextField patientpere;
    private javax.swing.JTextField patientpnom;
    private javax.swing.JTextField patientproffession;
    private javax.swing.JTextField patienttele;
    private javax.swing.JTextField patientville;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlParent;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JRadioButton r3;
    private javax.swing.JRadioButton s;
    private javax.swing.JPanel sidepane;
    private javax.swing.JTextField spec;
    private javax.swing.JTable tabfindu;
    private javax.swing.JTextField utcmdp;
    private javax.swing.JTextField utcni;
    private javax.swing.JTextField utemail;
    private javax.swing.JTextField utmdp;
    private javax.swing.JTextField utnom;
    private javax.swing.JTextField utpnom;
    private javax.swing.JTextField uttele;
    private java.awt.Button valider;
    // End of variables declaration//GEN-END:variables
}
