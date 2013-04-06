/*
 * Tên đề tài : Tìm hiểu thuật toán apriori trong xây dựng luật kết hợp
 * author : nguyenvanduocit
 * Tên project : NCKH_Apriori
 * Giao diện : Đồ họa
 * Thuật toán : Apriori
 * Mô tả thuật toán : 
 * Độ phức tạp của thuật toán : O(nm2^m) //n : Sô itemset, m : số item
 */
package nckh_apriori;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NCKH_Apriori {

//<editor-fold defaultstate="collapsed" desc="Khai báo các biến cần dùng">
static Map<String, Integer> SampleFrequency = new HashMap<>();
static Map<String, Integer> AssociateRules = new HashMap<>();
static Map<String, Integer> StrongAssociateRules = new HashMap<>();
static SortedSet<String> AssociateRulesSet = new TreeSet<>();
static float minSupport;
static String inFileName;
static String outFileName;
static float minConfidence;
static int totalSamples;
//</editor-fold>

public static void main(String[] args) throws Exception {
    
    
    Date d;
    long start, end;
    Map<String, Integer> Frequency = new HashMap<>();

    if (!getSetting(args))
        System.exit(1);

    d = new Date();
    start = d.getTime();
    float supportItems;
    totalSamples=-1;
    //<editor-fold defaultstate="collapsed" desc="Đọc các itemset từ file">
    try (BufferedReader inList = new BufferedReader(new FileReader(inFileName))) {
        String str;
        while ((str = inList.readLine()) != null) {
            totalSamples++;
            StringTokenizer stToken = new StringTokenizer(str, "\t");
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
    
    //<editor-fold defaultstate="collapsed" desc="Xuất kết quả ra file">
    Logger logger = Logger.getLogger("aprioriOutput");
    logger.setUseParentHandlers(false);
    FileHandler handler = new FileHandler(outFileName);
    
    handler.setFormatter(new CustomLogFormatter());
    logger.addHandler(handler);
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Xuất các tham số">
    logger.log(Level.OFF, "Parameters");
    logger.log(Level.OFF, "Total itemset : {0}", totalSamples);
    logger.log(Level.OFF, "minSupport : {0}", minSupport);
    logger.log(Level.OFF, "minConfidence : {0}", minConfidence);
    logger.log(Level.OFF, "inFileName : {0}", inFileName);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Tìm tập khổ biến 1-itemset">
    logger.log(Level.OFF,"\nFrequent 1-itemset :");
    SortedSet<String> sortedSet = FrequencyWithSupport(Frequency, supportItems);
    AssociateRulesSet.addAll(sortedSet);

    Iterator<String> it = sortedSet.iterator();
    while (it.hasNext())
    {
        String s = (String)it.next();
        logger.log(Level.OFF, "{0}", s);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Tìm tập phổ biến k-itemset">
    int i = 2;
    while (i<sortedSet.size())
    {
        logger.log(Level.OFF, "Frequent {0}-itemset  :", i);
        //Tạo tập ứng viên (k+1)itemset dự trên tập ứng viên trước đó
        Map<String, Integer> map1 = aprioriGen(sortedSet, i);
        sortedSet.clear();

        sortedSet = FrequencyWithSupport(map1, supportItems);
        it = sortedSet.iterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            logger.log(Level.OFF, "{0}", s);
            Frequency.put(s, map1.get(s));
        }

        AssociateRulesSet.addAll(sortedSet);
        i++;
    }
    //</editor-fold>

    it = AssociateRulesSet.iterator();
    logger.log(Level.OFF,"\nAll frequent itemset:");
    while (it.hasNext()) {
        logger.log(Level.OFF, "{0}", String.valueOf(it.next()));
    }

    //<editor-fold defaultstate="collapsed" desc="Tìm các luật kết hợp có thể có O(m2^(m-1)) ">
    SortedSet<String> AssociateRuleCandidatesSet = new TreeSet<>();
    it = AssociateRulesSet.iterator();
    while (it.hasNext())
    {
        findAllAssociateRules(String.valueOf(it.next()),AssociateRuleCandidatesSet);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Tìm các luật kết hợp mạnh ( luật kết hợp có độ tin cậy thỏa )">
    logger.log(Level.OFF,"\nStrong Associate Rules");
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
            logger.log(Level.OFF, "'{'{0}'}'=>'{'{1}'}' [ supp = {2} | conf = {3} ]", new Object[]{AssociateRuleLHS, AssociateRuleRHS, (float)FreqItemSetStringCount/(float)totalSamples, (float)FreqItemSetStringCount/(float)AsssociateRuleLHSCount});
            //logger.log(Level.OFF, "'{'{0}'}'=>'{'{1}'}'", new Object[]{AssociateRuleLHS, AssociateRuleRHS});
            StrongAssociateRules.put("{" + AssociateRuleLHS + "}->{"+ AssociateRuleRHS + "}", AsssociateRuleLHSCount/ FreqItemSetStringCount);
        }
    }
    //</editor-fold>

        d = new Date();
        end = d.getTime();
        logger.log(Level.OFF, "Execution time is: {0} seconds.", ((double)(end-start)/1000));
    }

    //<editor-fold defaultstate="collapsed" desc="Lấy các thông số từ dòng lệnh">
    private static boolean getSetting(String[] args)
    {
        if (args.length >= 3)
        {
            try
            {
                inFileName=args[0];
                outFileName = args[1];
                minSupport=Float.parseFloat(args[2]);
                minConfidence = Float.parseFloat(args[3]);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Tìm các luật kết hợp có thể có">
    private static void findAllAssociateRules(String string,SortedSet<String> associateRuleCandidatesSet) {
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
                    
                    String tempString = String.valueOf(tempFreqItemsElement);
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
    private static Map<String, Integer> aprioriGen(SortedSet<String> set1, int level) throws IOException {
        
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
                String tempString = String.valueOf(tempFreqItemsElement);
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
}
