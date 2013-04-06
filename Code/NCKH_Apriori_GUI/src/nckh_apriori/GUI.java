/*
 * Tên đề tài : Tìm hiểu thuật toán apriori trong xây dựng luật kết hợp
 * author : nguyenvanduocit
 * Tên file : NCKH_Apriori
 * Giao diện : Đồ họa
 * Thuật toán : Apriori
 * Mô tả thuật toán : 
 * Độ phức tạp của thuật toán : O(nm2^m) //n : Sô itemset, m : số item
 */
package nckh_apriori;

//<editor-fold defaultstate="collapsed" desc="Import các thư viện cần dùng">
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
//For apriori
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
//</editor-fold>


public class GUI extends javax.swing.JFrame {
    
    //<editor-fold defaultstate="collapsed" desc="Khai báo các biến cần dùng">
    static Map<String, Integer> SampleFrequency = new HashMap<>();
    static Map<String, Integer> AssociateRules = new HashMap<>();
    static Map<String, Integer> StrongAssociateRules = new HashMap<>();
    static SortedSet<String> AssociateRulesSet = new TreeSet<>();
    static float minSupport;
    static String inFileName;
    static float minConfidence;
    static int totalSamples;
    
    private static final int FILE_OPEN = 1;
    private static final int FILE_SAVE = 2;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Khởi tạo giao diện đồ họa">
    public GUI() {
        initComponents();
        setLocation(200,50);
    }
    //</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        cmdAction = new javax.swing.JButton();
        txtConf = new javax.swing.JTextField();
        txtSup = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSAR = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAFIS = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtL = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFileName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmdOpen = new javax.swing.JButton();
        mnuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuOpen = new javax.swing.JMenuItem();
        mnuSave = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnuExit = new javax.swing.JMenuItem();
        mnuAbout = new javax.swing.JMenu();
        mnuAboutProject = new javax.swing.JMenuItem();
        mnuAboutMe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdAction.setText("Action");
        cmdAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdActionActionPerformed(evt);
            }
        });

        txtConf.setText("0.5");

        txtSup.setText("0.5");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setLabelFor(txtConf);
        jLabel1.setText("Confidence");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setLabelFor(txtSup);
        jLabel2.setText("Support");

        txtSAR.setColumns(20);
        txtSAR.setEditable(false);
        txtSAR.setRows(5);
        jScrollPane1.setViewportView(txtSAR);

        txtAFIS.setColumns(20);
        txtAFIS.setEditable(false);
        txtAFIS.setRows(5);
        txtAFIS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setViewportView(txtAFIS);

        txtL.setColumns(20);
        txtL.setEditable(false);
        txtL.setRows(5);
        txtL.setAutoscrolls(false);
        jScrollPane3.setViewportView(txtL);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Frequent k–itemset");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("All frequent itemset");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Strong Associate Rules");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("File");

        txtFileName.setEditable(false);
        txtFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFileNameActionPerformed(evt);
            }
        });

        jLabel7.setText("<html>\n<strong>Cấu trúc input file</strong><br/>\n-Mỗi dòng là một itemset<br/>\n-Các phần tử trong dòng cách nhau ký tự tab<br/>\n-Phần tử đầu tiên là Tid<br/>\n<strong>Lưu ý:</strong><br/>\n- Support và confidence từ 0-1<br/>\n- Chọn file trước\n</html>"); // NOI18N

        cmdOpen.setText("...");
        cmdOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOpenActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        mnuOpen.setText("Open...");
        mnuOpen.setToolTipText("");
        mnuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpenActionPerformed(evt);
            }
        });
        jMenu1.add(mnuOpen);

        mnuSave.setText("Save...");
        mnuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSaveActionPerformed(evt);
            }
        });
        jMenu1.add(mnuSave);
        jMenu1.add(jSeparator2);

        mnuExit.setText("Exit");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        jMenu1.add(mnuExit);

        mnuBar.add(jMenu1);

        mnuAbout.setText("About");

        mnuAboutProject.setText("About Project");
        mnuAboutProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAboutProjectActionPerformed(evt);
            }
        });
        mnuAbout.add(mnuAboutProject);

        mnuAboutMe.setText("About Me");
        mnuAbout.add(mnuAboutMe);

        mnuBar.add(mnuAbout);

        setJMenuBar(mnuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(867, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdOpen))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtSup, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtConf, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdAction))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 328, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cmdAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdOpen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnuExitActionPerformed

    private void mnuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpenActionPerformed
        operateFile("Open itemsetFile", FILE_OPEN);
    }//GEN-LAST:event_mnuOpenActionPerformed

    private void mnuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSaveActionPerformed
        operateFile("Save result", FILE_SAVE);
    }//GEN-LAST:event_mnuSaveActionPerformed

    private void cmdActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdActionActionPerformed
        try {
            
            txtL.setText("");
            txtAFIS.setText("");
            txtSAR.setText("");
            
            minSupport = Float.parseFloat(txtSup.getText());
            minConfidence = Float.parseFloat(txtConf.getText());
            NCKH_Apriori();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Xảy ra lỗi hoặc thông số chưa đúng, Hãy kiểm tra lại minSupport, minConfidence và tên file nhé.");
        }
    }//GEN-LAST:event_cmdActionActionPerformed

    private void txtFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFileNameActionPerformed
        operateFile("Open itemsetFile", FILE_OPEN);
    }//GEN-LAST:event_txtFileNameActionPerformed

    private void cmdOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOpenActionPerformed
        operateFile("Open itemsetFile", FILE_OPEN);
    }//GEN-LAST:event_cmdOpenActionPerformed

    private void mnuAboutProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAboutProjectActionPerformed
        JOptionPane.showMessageDialog(null,"Đây là phiên bản demo cho đề tài nghiêm cứu khoa học sinh viên : Tìm hiểu thuật toán apriori ");
    }//GEN-LAST:event_mnuAboutProjectActionPerformed
    
    //<editor-fold defaultstate="collapsed" desc="Hàm main">
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Cập nhật các textarea">
    public static void updateL(String s)
    {
        txtL.append(s);
    }
    public static void updateAFIS(String s)
    {
        txtAFIS.append(s);
    }
    public static void updateSAR(String s)
    {
        txtSAR.append(s);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Lưu kết quả vào file">
    public static void saveToFile(String outFileName){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(outFileName, true));
            bw.write("Parameters\n");
            bw.write("Total itemset : "+ totalSamples+"\n");
            bw.write("minSupport : "+ minSupport +"\n");
            bw.write("minConfidence : "+ minConfidence +"\n");
            bw.write("inFileName : "+ inFileName +"\n");
            
            bw.write(txtL.getText()+"\n");
            
            bw.write("All freq itemset\n");
            bw.write(txtAFIS.getText()+"\n");
            
            bw.write("Strong Associate Rules\n");
            bw.write(txtSAR.getText()+"\n");
            
            bw.flush();
        } catch (IOException e)
        {
            // just ignore it
        } finally {                       // always close the file
            if (bw != null)
                try {
                    bw.close();
                } catch (IOException e) {
                    // just ignore it
                }
        } // end try/catch/finally
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Lấy tên file">
    public static void readFromFile(String inFileNameL){
        inFileName = inFileNameL;
        txtFileName.setText(inFileNameL);
        txtL.setText("");
        txtAFIS.setText("");
        txtSAR.setText("");
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Hộp thoại chọn file">
    private void operateFile(String title, int type)
    {
        JFileChooser chooser = new JFileChooser();
        int choose = -1;
        chooser.setDialogTitle(title);
        switch  (type){
            case FILE_OPEN:
                choose = chooser.showOpenDialog(null); //Hộp thoại open
                break;
            case FILE_SAVE:
                choose = chooser.showSaveDialog(null); //Hộp thoại save
                break;
        }
        
        if (choose == JFileChooser.APPROVE_OPTION)
        {
            switch  (type){
                case FILE_OPEN:
                    readFromFile(chooser.getSelectedFile().getPath());  // Đọc các itemset từ file
                    break;
                case FILE_SAVE:
                    saveToFile(chooser.getSelectedFile().getPath());    // Lưu kết quả vào file
                    break;
            }
        }
        
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Thuật toán apriori">
    public static void NCKH_Apriori() throws Exception {
        
        totalSamples=-1; // Đặt làm tên của từng itemset
        Map<String, Integer> Frequency = new HashMap<>();        
        
        //<editor-fold defaultstate="collapsed" desc="Đọc các itemset từ file">
        float supportItems = 0 ;
        try (BufferedReader inList = new BufferedReader(new FileReader(inFileName))) {
            String str;
            while ((str = inList.readLine()) != null) {
                totalSamples++;
                StringTokenizer stToken = new StringTokenizer(str, "\t");
                
                /* First one is the sample name. Ignored */
                stToken.nextToken();
                while (stToken.hasMoreTokens()) {
                    String token = stToken.nextToken();
                    addMap(Frequency, token);
                    SampleFrequency.put(String.valueOf(totalSamples) + "@"+ token, totalSamples);
                }
            }
            totalSamples++;
            supportItems = (minSupport * totalSamples);
            inList.close();
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Tìm tập khổ biến 1-itemset">
        updateL("Frequent 1-itemset :\n");
        
        SortedSet<String> sortedSet = FrequencyWithSupport(Frequency, supportItems);
        AssociateRulesSet.addAll(sortedSet);
        Iterator<String> it = sortedSet.iterator();
        
        while (it.hasNext()) {
            String s = (String)it.next();
            updateL(s+"\n");
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Tìm tập phổ biến k-itemset">
        int i = 2;
        while (i<sortedSet.size()-1)
        {
            updateL("Frequent "+i+"-itemset  :\n");
            //Tạo tập ứng viên (k+1)itemset dự trên tập ứng viên trước đó
            Map<String, Integer> map1 = NextCandidates(sortedSet, i);
            sortedSet.clear();
            
            sortedSet = FrequencyWithSupport(map1, supportItems);
            it = sortedSet.iterator();
            while (it.hasNext()) {
                String s = (String) it.next();
                updateL(s+"\n");
                Frequency.put(s, map1.get(s));
            }
            
            AssociateRulesSet.addAll(sortedSet);
            i++;
        }
        //</editor-fold>
        
        it = AssociateRulesSet.iterator();
        while (it.hasNext()) {
            updateAFIS(String.valueOf(it.next())+"\n");
        }
        
        //<editor-fold defaultstate="collapsed" desc="Tìm các luật kết hợp có thể có O(m2^(m-1)) ">
        SortedSet<String> AssociateRuleCandidatesSet = new TreeSet<>();
        it = AssociateRulesSet.iterator();
        while (it.hasNext())
        {
            AssociateRulesFinder(String.valueOf(it.next()),AssociateRuleCandidatesSet);
        }
        //</editor-fold>
        
        
        //<editor-fold defaultstate="collapsed" desc="Tìm các luật kết hợp mạnh ( luật kết hợp có độ tin cậy thỏa )">
        it = AssociateRuleCandidatesSet.iterator();
        while (it.hasNext())
        {
            String tempItString = String.valueOf(it.next());
            StringTokenizer st1 = new StringTokenizer(tempItString, "@");
            String FreqItemSetString = String.valueOf(st1.nextToken());
            String AssociateRuleLHS = String.valueOf(st1.nextToken());
            StringTokenizer st2 = new StringTokenizer(AssociateRuleLHS, "->");
            AssociateRuleLHS = String.valueOf(st2.nextToken());      // items bên trái
            String AssociateRuleRHS = String.valueOf(st2.nextToken()); // items bên phải
            
            
            int FreqItemSetStringCount = Frequency.get(FreqItemSetString);
            int AsssociateRuleLHSCount = Frequency.get(AssociateRuleLHS);
            //if (((float)FreqItemSetStringCount/(float)totalSamples >=support ) && ((float)FreqItemSetStringCount/(float)AsssociateRuleLHSCount>=confidence))
            if ((FreqItemSetStringCount >= minConfidence * AsssociateRuleLHSCount) && (FreqItemSetStringCount >= supportItems))
            {
                updateSAR("{" + AssociateRuleLHS + "}=>{"+ AssociateRuleRHS + "} [ supp = "+(float)FreqItemSetStringCount/(float)totalSamples+ " | conf = "+ (float)FreqItemSetStringCount/(float)AsssociateRuleLHSCount+" ]\n");
                StrongAssociateRules.put("{" + AssociateRuleLHS + "}->{"+ AssociateRuleRHS + "}", AsssociateRuleLHSCount/ FreqItemSetStringCount);
            }
        }
        //</editor-fold>
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Tìm các luật kết hợp có thể có">
    private static void AssociateRulesFinder(String string,SortedSet<String> associateRuleCandidatesSet) {
        StringTokenizer AssociateRulesTokenizer = new StringTokenizer(string,",");
        int AssociateRulesTokenSize = AssociateRulesTokenizer.countTokens();
        if (AssociateRulesTokenSize == 1) {
            return;
        }
        
        Set<String> AssociateRuleCandidateTokens = new HashSet<>();
        while (AssociateRulesTokenizer.hasMoreTokens()) {
            AssociateRuleCandidateTokens.add(String.valueOf(AssociateRulesTokenizer.nextToken()));
        }
        
        String[] elements = AssociateRuleCandidateTokens.toArray(new String[0]);
        for (int j = 1; j < AssociateRulesTokenSize; j++) {
            int[] index;
            CombinationGenerator x = new CombinationGenerator(elements.length,j);   //Tạo tổ hợp
            while (x.hasMore()) {
                SortedSet<String> tempFreqItemsElement = new TreeSet<>();
                index = x.getNext();
                for (int i = 0; i < index.length; i++) {
                    tempFreqItemsElement.add(String.valueOf(elements[index[i]]));
                }
                
                if (tempFreqItemsElement.size() == j) {
                    
                    String tempString = (String.valueOf(tempFreqItemsElement));
                    tempString = removeSpaces(tempString);
                    tempString = tempString.substring(1,tempString.length() - 1);
                    
                    String rhs = new String();
                    Iterator<String> tokenTempIt = AssociateRuleCandidateTokens.iterator();
                    while (tokenTempIt.hasNext()) {
                        String tempTokenString = String.valueOf(tokenTempIt.next());
                        
                        if (!tempFreqItemsElement.contains(tempTokenString)) {
                            rhs += (tempTokenString + ",");
                        }
                    }
                    
                    rhs = rhs.substring(0, rhs.length() - 1);
                    associateRuleCandidatesSet.add(string + "@" + tempString+ "->" + rhs);
                }
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Tạo tập ứng cử viên L(k) từ L(k-1)">
    private static Map<String, Integer> NextCandidates(SortedSet<String> set1, int level) throws IOException {
        
        Map<String, Integer> returnMap = new HashMap<>();
        SortedSet<String> copy1 = new TreeSet<>();
        
        Iterator<String> it = set1.iterator();
        while (it.hasNext()) {
            String setString = (String) it.next();
            StringTokenizer setST = new StringTokenizer(setString, ",");
            if (setST.countTokens() != level - 1) {
                // System.exit(level);
                return returnMap;
            }
            while (setST.hasMoreTokens()) {
                String token = setST.nextToken();
                copy1.add(token);
            }
        }
        
        String[] elements = copy1.toArray(new String[0]);
        copy1.clear();
        
        if (level > elements.length)
            return returnMap;
        int[] indices;
        
        CombinationGenerator x = new CombinationGenerator(elements.length,level);
        while (x.hasMore()) {
            SortedSet<String> tempFreqItemsElement = new TreeSet<>();
            indices = x.getNext();
            
            for (int i = 0; i < indices.length; i++) {
                tempFreqItemsElement.add(String.valueOf(elements[indices[i]]));
            }
            if (tempFreqItemsElement.size() == level) {
                //Chuẩn hóa lại itemset ( bỏ [] và space )
                String tempString = (String.valueOf(tempFreqItemsElement));
                tempString = removeSpaces(tempString);
                tempString = tempString.substring(1, tempString.length() - 1);
                
                for (int i = 0; i < totalSamples; i++) {
                    String sampleKey = String.valueOf(i);
                    
                    int matchCounter = 0;
                    String setString = tempString;
                    StringTokenizer setST = new StringTokenizer(setString, ",");
                    if (setST.countTokens() != level) {
                        System.exit(level);
                    }
                    while (setST.hasMoreTokens()) {
                        String token = setST.nextToken();
                        
                        if (SampleFrequency.containsKey(sampleKey + "@" + token))
                            matchCounter++;
                        else
                            matchCounter = 0;
                    }
                    if (matchCounter == level) {
                        addMap(returnMap, setString);
                    }
                }
            }
        }/* END OF WHILE */
        
        return returnMap;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Xóa khoảng trắng">
    private static String removeSpaces(String s) {
        StringTokenizer st = new StringTokenizer(s, " ", false);
        String t = "";
        while (st.hasMoreElements())
            t += st.nextElement();
        return t;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Lọc các tập phổ biến thỏa minSupport">
    private static SortedSet<String> FrequencyWithSupport(Map<String, Integer> frequency2, float supportItems)
    {
        SortedSet<String> returnSortedSet = new TreeSet<>();
        Iterator<String> it = frequency2.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            int count = frequency2.get(str); // Lấy tần số của tập str
            if (count >= supportItems) //count/totalSample >= Support  <=> count >= minSupport*totalSample
                returnSortedSet.add(str);
        }
        
        return returnSortedSet;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Thêm item vào map kèm theo số lần xuất hiện">
    private static void addMap(Map<String, Integer> m, String Item) {
        if (m.containsKey(Item)) {
            int i = m.get(Item);
            m.put(Item, ++i);
        } else {
            m.put(Item, 1);
        }
        
    }
    //</editor-fold>
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAction;
    private javax.swing.JButton cmdOpen;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu mnuAbout;
    private javax.swing.JMenuItem mnuAboutMe;
    private javax.swing.JMenuItem mnuAboutProject;
    private javax.swing.JMenuBar mnuBar;
    private javax.swing.JMenuItem mnuExit;
    private javax.swing.JMenuItem mnuOpen;
    private javax.swing.JMenuItem mnuSave;
    private static javax.swing.JTextArea txtAFIS;
    private javax.swing.JTextField txtConf;
    private static javax.swing.JTextField txtFileName;
    private static javax.swing.JTextArea txtL;
    private static javax.swing.JTextArea txtSAR;
    private javax.swing.JTextField txtSup;
    // End of variables declaration//GEN-END:variables
}
