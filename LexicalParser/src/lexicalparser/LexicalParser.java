/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexicalparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author QT
 */
public class LexicalParser {

    /**
     * @param args the command line arguments
     */
//    E  E + T | T
//    T  T * F | F
//    F  ( E ) | id
    
    private final String PRODUCTION_FILE_PATH = "Production.txt";
    HashMap m_HM = new HashMap();
    
    public LexicalParser()
    {
        if (GetProductionFromFile(PRODUCTION_FILE_PATH, m_HM) == false)
        {
            return;
        }
    }
    
    private boolean GetProductionFromFile(String strFilePath, HashMap hm)
    {
        BufferedReader br = null;
        String [] arrLeftRight;
        String strLeft = null;
        String []arrRightPart = null;
        ArrayList<ArrayList<String>> list = null;
        ArrayList<String> data = null;
        String [] parts;
        
        try {
            String sCurrentLine;

            br = new BufferedReader(new FileReader(strFilePath));
            while ((sCurrentLine = br.readLine()) != null)
            {
                System.out.println(sCurrentLine);
                arrLeftRight = sCurrentLine.split(":");
                
                System.out.println("LEFT : " + arrLeftRight[0]);
                System.out.println("Right : " + arrLeftRight[1]);
                System.out.println("Process right");
                
                arrRightPart = arrLeftRight[1].split("\\|");
                System.out.println("Right length :" +  arrRightPart.length);
                
                list = new  ArrayList<ArrayList<String>>();
                for (int i = 0; i < arrRightPart.length; i++)
                {
                    System.out.println(">>" + arrRightPart[i]);
                    parts = arrRightPart[i].split(" ");
                    System.out.println(">>>>" + parts.length);
                    data = new ArrayList<String>();
                    for (int j = 0; j < parts.length; j++)
                    {
                        if (parts[j].compareTo("") == 0)
                            continue;
                        data.add(parts[j]);
                    }
                    
                    list.add(data);
                }
                
                hm.put(arrLeftRight[0], list);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        } finally {
            try
            {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
            
            return true;
        }
    }
    
    public void PrintProduction(HashMap hm)
    {
        Set set = hm.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
           Map.Entry me = (Map.Entry)i.next();
           System.out.print(me.getKey() + ": ");
           System.out.println(me.getValue());
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String strTokenString = "9 - 5 + 2";
        
        LexicalParser lp = new LexicalParser();
        
        
    }
}
