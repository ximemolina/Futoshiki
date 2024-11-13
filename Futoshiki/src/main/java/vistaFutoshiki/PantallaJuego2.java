package vistaFutoshiki;

import modeloFutoshiki.*;

import java.awt.Image;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author ximena molina - juan pablo cambronero
 */
public class PantallaJuego2 extends javax.swing.JFrame {

    private Juego juego;
    private MatrizJuego matriz;

    /**
     * Creates new form PantallaJuego
     */
    public PantallaJuego2(Juego juego) {
        this.juego = juego;
        this.matriz = juego.getMatriz();
        this.botones = matriz.getBotonesCasillas();
        this.botonesNumeros = matriz.getBotonesNumeros();
        this.desigualdades = matriz.getDesigualdades();
        initComponents();
        
        
    }
    
    public JTable getTblTemporizador() {
        return jTable1;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        lblNombre2 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        btnJugar = new javax.swing.JButton();
        btnBorrarJugada = new javax.swing.JButton();
        btnBorrarJuego = new javax.swing.JButton();
        btnGuardarJuego = new javax.swing.JButton();
        btnRehacerJugada = new javax.swing.JButton();
        btnTerminarJuego = new javax.swing.JButton();
        btnCargarJuego = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        botonesJuego(botones,botonesNumeros);
        desigualdadesLabels(desigualdades);
        
        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(204, 0, 0));
        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" FUTOSHIKI ");
        jLabel1.setOpaque(true);

        lblNivel.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        lblNivel.setText("Nivel");

        lblNombre2.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        lblNombre2.setText("Nombre del jugador: ");

        lblNombre.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        lblNombre.setText("......");
        lblNombre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        btnJugar.setBackground(new java.awt.Color(51, 153, 0));
        btnJugar.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        btnJugar.setForeground(new java.awt.Color(255, 255, 255));
        btnJugar.setText("Jugar");
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });

        btnBorrarJugada.setBackground(new java.awt.Color(255, 102, 0));
        btnBorrarJugada.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        btnBorrarJugada.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrarJugada.setText("<html>Borrar<br>Jugada</html>");
        btnBorrarJugada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBorrarJugada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarJugadaActionPerformed(evt);
            }
        });

        btnBorrarJuego.setBackground(new java.awt.Color(255, 204, 0));
        btnBorrarJuego.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        btnBorrarJuego.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrarJuego.setText("<html>Borrar<br>Juego</html>");
        btnBorrarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarJuegoActionPerformed(evt);
            }
        });

        btnGuardarJuego.setBackground(new java.awt.Color(102, 0, 102));
        btnGuardarJuego.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        btnGuardarJuego.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarJuego.setText("<html>Guardar<br>Juego</html>");
        btnGuardarJuego.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarJuegoActionPerformed(evt);
            }
        });

        btnRehacerJugada.setBackground(new java.awt.Color(255, 102, 102));
        btnRehacerJugada.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        btnRehacerJugada.setForeground(new java.awt.Color(255, 255, 255));
        btnRehacerJugada.setText("<html>Rehacer<br>Jugada</html>");
        btnRehacerJugada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRehacerJugada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerJugadaActionPerformed(evt);
            }
        });

        btnTerminarJuego.setBackground(new java.awt.Color(0, 153, 153));
        btnTerminarJuego.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        btnTerminarJuego.setForeground(new java.awt.Color(255, 255, 255));
        btnTerminarJuego.setText("<html>Terminar<br>Juego</html>");
        btnTerminarJuego.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTerminarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarJuegoActionPerformed(evt);
            }
        });

        btnCargarJuego.setBackground(new java.awt.Color(102, 51, 0));
        btnCargarJuego.setFont(new java.awt.Font("Leelawadee UI", 0, 18)); // NOI18N
        btnCargarJuego.setForeground(new java.awt.Color(255, 255, 255));
        btnCargarJuego.setText("<html>Cargar<br>Juego</html>");
        btnCargarJuego.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCargarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarJuegoActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Horas", "Minutos", "Segundos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jTable1.setShowGrid(true);
        jTable1.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(jTable1);
        
        btnVolver.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        btnVolver.setText("Volver");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(lblNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNombre2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(118, 118, 118)
                                        .addComponent(btnRehacerJugada, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(149, 149, 149)
                                        .addComponent(btnJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBorrarJugada, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnBorrarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnGuardarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnTerminarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCargarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(336, 336, 336))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnVolver))
                .addGap(18, 18, 18)
                .addComponent(lblNivel)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre2)
                            .addComponent(lblNombre))
                        .addGap(0, 422, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBorrarJugada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardarJuego))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRehacerJugada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTerminarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCargarJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnJugar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    // <editor-fold defaultstate="collapsed" desc="Botones">   
    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {                                         

    }                                        

    private void btnBorrarJugadaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void btnBorrarJuegoActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void btnGuardarJuegoActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void btnRehacerJugadaActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void btnTerminarJuegoActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void btnCargarJuegoActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Main">   
    public void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaJuego2(juego).setVisible(true);
            }
        });
    }
    //</editor-fold>
    
    public void botonesJuego(ArrayList <JButton> botones, ArrayList <JButton> botonesNumeros){
    // Inicialización tamaño de botones
        int tamano = 40;
        if (juego.getTamano()== 8 ||juego.getTamano()== 9 ) tamano =25; //si la cuadricula es de 8x8 o 9x9 los btns deben ser mas pequeños
        if (juego.getTamano() == 10) tamano= 22; //si la cuadricula es de  los btns es de 10x10 deben ser aún mas pequeños
        
         // tamaño de los ubicacion y contador
        int x = 30, y =170,cont = 0;
        if (juego.isPosicion()){ // si es para derechos
            for (int i = 0; i < 100; i++) {

                if (i == juego.getTamano()*juego.getTamano()) break; //revisar tamaño de cuadricula

                JButton boton = new JButton("");
                if (cont >= juego.getTamano()){ // una vez que termina fila, baja una columna y continua con la siguiente fila
                    y = y+tamano + 20;
                    cont = 0;
                    x = 30;
                }
                boton.setBounds(x, y, tamano, tamano);
                botones.add(boton);
                add(boton);
                x = x+ tamano + 20;
                cont ++;
            }
            x=600; y=170;   
            for (int i = 1; i <= juego.getTamano(); i++) { //desplegar botones de numeros
                if (i == 6)
                { 
                    x=680;
                    y = 170;
                }
                JButton boton = new JButton(String.valueOf(i));
                boton.setFont(new java.awt.Font("Leelawadee UI", 1, 20));
                boton.setBounds(x, y, 60, 60);
                botonesNumeros.add(boton);
                add(boton);
                y = y+85;
            } 
            ImageIcon imagen = new ImageIcon("src/main/java/imagenes/borrador.png");  // Ruta a la imagen
            Image imagen2 = imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon imagenTamanoCorrecto = new ImageIcon(imagen2);
            JButton btnBorrador = new JButton(imagenTamanoCorrecto);
            btnBorrador.setBackground(new java.awt.Color(255, 255, 255));
            btnBorrador.setBounds(770, 200, 90, 80);
            btnBorrador.setBorderPainted(false);
            add(btnBorrador);
        } else { // si es para zurdos
            x= 465;
            for (int i = 0; i < 100; i++) {

                if (i == juego.getTamano()*juego.getTamano()) break; //revisar tamaño de cuadricula

                JButton boton = new JButton("");
                if (cont >= juego.getTamano()){ // una vez que termina fila, baja una columna y continua con la siguiente fila
                    y = y+tamano + 20;
                    cont = 0;
                    x = 465;
                }
                boton.setBounds(x, y, tamano, tamano);
                botones.add(boton);
                add(boton);
                x = x+ tamano + 20;
                cont ++;
            }
            x=150; y=170;   
            for (int i = 1; i <= juego.getTamano(); i++) { //desplegar botones de numeros
                if (i == 6)
                { 
                    x=230;
                    y = 170;
                }
                JButton boton = new JButton(String.valueOf(i));
                boton.setFont(new java.awt.Font("Leelawadee UI", 1, 20));
                boton.setBounds(x, y, 60, 60);
                botonesNumeros.add(boton);
                add(boton);
                y = y+85;
            } 
            ImageIcon imagen = new ImageIcon("src/main/java/imagenes/borrador.png");  // Ruta a la imagen
            Image imagen2 = imagen.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon imagenTamanoCorrecto = new ImageIcon(imagen2);
            JButton btnBorrador = new JButton(imagenTamanoCorrecto);
            btnBorrador.setBackground(new java.awt.Color(255, 255, 255));
            btnBorrador.setBounds(30, 200, 90, 80);
            btnBorrador.setBorderPainted(false);
            add(btnBorrador);

        }
        matriz.setBotonesCasillas(botones);
        matriz.setBotonesNumeros(botonesNumeros);
    }
    
    public void desigualdadesLabels(ArrayList <JLabel> desigualdades){
        int tamano = 40;
        boolean filaOColumna = true;
        if (juego.getTamano()== 8 ||juego.getTamano()== 9 ) tamano =25; //si la cuadricula es de 8x8 o 9x9 los btns deben ser mas pequeños
        if (juego.getTamano() == 10) tamano= 22; //si la cuadricula es de  los btns es de 10x10 deben ser aún mas pequeños
        
         // tamaño de los ubicacion y contador
        int x = 60, y =165,cont = 0;//*************
        if (juego.isPosicion()){ // si es para derechos
            for (int i = 0; i < (juego.getTamano()-2)*juego.getTamano()+ juego.getTamano()*juego.getTamano(); i++) {

                JLabel desigualdad = new JLabel("<");
                desigualdad.setSize(WIDTH, tamano);
                if (cont+1 >= juego.getTamano() && filaOColumna){ // una vez que termina fila, baja una columna y continua con la siguiente fila
                    y = y+tamano-tamano/7;
                    cont = 0;
                    x = 40;
                    filaOColumna = false;
                } else if (cont >= juego.getTamano() && !filaOColumna){
                    y = y+tamano;
                    cont = 0;
                    x = 60;
                    filaOColumna = true;
                
                }
                desigualdad.setBounds(x, y, tamano, tamano);
                desigualdades.add(desigualdad);
                add(desigualdad);
                x = x+ tamano + 20;
                cont ++;
            }

        } else { // si es para zurdos
            filaOColumna = true;
            x= 495;
            for (int i = 0; i < (juego.getTamano()-2)*juego.getTamano()+ juego.getTamano()*juego.getTamano(); i++) {

                JLabel desigualdad = new JLabel("<");
                desigualdad.setSize(WIDTH, tamano);
                if (cont+1 >= juego.getTamano() && filaOColumna){ // una vez que termina fila, baja una columna y continua con la siguiente fila
                    y = y+tamano-4;
                    cont = 0;
                    x = 475;
                    filaOColumna = false;
                } else if (cont >= juego.getTamano() && !filaOColumna){
                    y = y+tamano;
                    cont = 0;
                    x = 495;
                    filaOColumna = true;
                
                }
                desigualdad.setBounds(x, y, tamano, tamano);
                desigualdades.add(desigualdad);
                add(desigualdad);
                x = x+ tamano + 20;
                cont ++;
            }

        }
        matriz.setBotonesCasillas(botones);
    }

    // Variables declaration - do not modify                     
    public javax.swing.JButton btnBorrarJuego;
    public javax.swing.JButton btnBorrarJugada;
    public javax.swing.JButton btnCargarJuego;
    public javax.swing.JButton btnGuardarJuego;
    public javax.swing.JButton btnJugar;
    public javax.swing.JButton btnRehacerJugada;
    public javax.swing.JButton btnTerminarJuego;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    public javax.swing.JLabel lblNivel;
    public javax.swing.JLabel lblNombre;
    public ArrayList <JButton> botones; 
    public ArrayList <JButton> botonesNumeros ;
    public ArrayList <JLabel> desigualdades;
    private javax.swing.JLabel lblNombre2;
    // End of variables declaration                   
}
