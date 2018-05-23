package com.caodaxing.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

public class SystemSoftware {    
    private JFrame f = new JFrame("本系统已经安装的软件列表");  
    private JTextPane textPane = new JTextPane();  
    private MyTable myTable=new MyTable();  
    public static Charset charset = Charset.forName("GBK");  
    public SystemSoftware() {  
        f.setLocation(300, 200);  
        f.setSize(800,500);  
        JScrollPane jScrollPane = new JScrollPane(myTable.getTable());  
        f.add(jScrollPane);  
        f.setVisible(true);  
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);  
  
        try {  
            check();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    private void check() throws Exception {  
        textPane.setText("您已经安装的软件：");  
        Runtime runtime = Runtime.getRuntime();  
        Process process = null;  
        Process process1 = null;  
        //64位
        process = runtime.exec("cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");  
        //32位
        process1 = runtime.exec("cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");  
                                       //HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Windows\CurrentVersion\Uninstall
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));  
        BufferedReader in1 = new BufferedReader(new InputStreamReader(process1.getInputStream(),"GBK"));  
        String string = null;  
        String s32 = null; 
        
        List<Object[]> list64 = new ArrayList<Object[]>();
        List<Object[]> list32 = new ArrayList<Object[]>();
        List<Object[]> list = new ArrayList<Object[]>();
        
        
        while ((string = in.readLine()) != null) {  
            process = runtime.exec("cmd /c reg query " + string  
                    + " /v DisplayName");  
            BufferedReader name = new BufferedReader(new InputStreamReader(  
                    process.getInputStream(),"GBK"));  
            String[] message = queryValue(string);  
            if(message!=null){
                myTable.addRow(message);
                //list64.add(message);
            }   
            f.repaint();  
        } 
//        while((s32 = in1.readLine()) != null) {
//        	System.out.println(s32);
//        }
        //32位
        while ((s32 = in1.readLine()) != null) {  
//            process1 = runtime.exec("cmd /c reg query " + s32  
//                    + " /v DisplayName");  
//            BufferedReader name = new BufferedReader(new InputStreamReader(  
//                    process1.getInputStream(),"GBK"));  
            String[] message = queryValue(s32);  
            if(message!=null){
                myTable.addRow(message); 
            //    list32.add(message);
            }  
            f.repaint();  
        }  
        /*for(Object[] obj64:list64){
            list.add(obj64);
        }
        for(Object[] obj32:list32){
            boolean flag=false;
            for(Object[] obj64:list64){
                if(Arrays.equals(obj32, obj64)){
                    flag=true;
                }
            }
            if(!flag){
                list.add(obj32);
            }
        }
        for(Object[] obj:list){
            myTable.addRow(obj);
            f.repaint();  
        }*/
        
        
        
        in.close();  
        in1.close();  
        process.destroy();  
        process1.destroy();  
  
    }  
  
    //具体查询每一个软件的详细信息  
    private String[] queryValue(String string) throws IOException {  
        String nameString = "";  //软件名称
        String versionString = "";  //软件版本
          
        String publisherString="";  //出版商
        String InstallLocation = "";  //安装路径
        String InstallDate = "";  //安装时间
        
          
        Runtime runtime = Runtime.getRuntime();  
        Process process = null;  
        BufferedReader br = null;  
          //软件名称
        process = runtime.exec("cmd /c reg query " + string + " /v DisplayName");  
        br = new BufferedReader(new InputStreamReader(process  
                .getInputStream(),"GBK"));  
        br.readLine();br.readLine();//去掉前两行无用信息  
        if((nameString=br.readLine())!=null){  
            nameString=nameString.replaceAll("DisplayName    REG_SZ    ", "");  //去掉无用信息  
        }  
          
  //软件版本
        process = runtime.exec("cmd /c reg query " + string + " /v DisplayVersion");  
        br = new BufferedReader(new InputStreamReader(process  
                .getInputStream(),"GBK"));  
        br.readLine();br.readLine();//去掉前两行无用信息  
        if((versionString=br.readLine())!=null){  
            versionString=versionString.replaceAll("DisplayVersion    REG_SZ    ", ""); //去掉无用信息  
        }  
     //出版商     
        process = runtime.exec("cmd /c reg query " + string + " /v Publisher");  
        br = new BufferedReader(new InputStreamReader(process  
                .getInputStream(),"GBK"));  
        br.readLine();br.readLine();//去掉前两行无用信息  
        if((publisherString=br.readLine())!=null){  
            publisherString =publisherString.replaceAll("Publisher    REG_SZ    ", ""); //去掉无用信息  
        }  
      //安装路径    
        process = runtime.exec("cmd /c reg query " + string + " /v DisplayIcon");  
        br = new BufferedReader(new InputStreamReader(process  
                .getInputStream(),"GBK"));  
        br.readLine();br.readLine();//去掉前两行无用信息  
        if((InstallLocation=br.readLine())!=null){  
            InstallLocation=InstallLocation.replaceAll("DisplayIcon    REG_SZ    ", "");    //去掉无用信息  
        }else {
        	process = runtime.exec("cmd /c reg query " + string + " /v InstallLocation");  
            br = new BufferedReader(new InputStreamReader(process  
                    .getInputStream(),"GBK"));  
            br.readLine();br.readLine();//去掉前两行无用信息  
            if((InstallLocation=br.readLine())!=null)
            	InstallLocation=InstallLocation.replaceAll("InstallLocation    REG_SZ    ", "");
        }
        //安装时间
        process = runtime.exec("cmd /c reg query " + string + " /v InstallDate");  
        br = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));  
        br.readLine();br.readLine();//去掉前两行无用信息  
        if((InstallDate=br.readLine())!=null){  
            InstallDate=InstallDate.replaceAll("InstallDateREG_SZ", "");    //去掉无用信息  
        }
          
        String[] resultString=new String[6];  
        resultString[0]= nameString ;//== null ? null : new String(nameString.getBytes(),"GB-2312");  
        resultString[1]= versionString ;//== null ? null : new String(versionString.getBytes(),"GB-2312");  
        resultString[2]= publisherString ;//== null ? null : new String(publisherString.getBytes(),"GB-2312");  
        resultString[3]= InstallLocation ;//== null ? null : new String(uninstallPathString.getBytes(),"GB-2312");  
        resultString[4]= InstallDate ;//== null ? null : new String(uninstallPathString.getBytes(),"GB-2312"); 
        if(nameString !=null && !"".equals(nameString)){
            if("腾讯QQ".equals(nameString.trim()) || "微信".equals(nameString.trim())){
                String instantMessaging="是";//即时通讯
                resultString[5]=instantMessaging;
            }
        }
        if(resultString[0]==null)
        	resultString=null;    //没有名字的不显示  
        return resultString;  
    }  
      
    //列表  
    private class MyTable{  
        private JTable jTable;  
        private Object[][] data=new Object[220][6];  
        private Object[] colNames= { "软件名称","版本号","出版商","安装路径","安装时间","即时通讯"};  
        private int p=-1;  
          
        public MyTable(){  
              
        }  
        public void addRow(Object[] data){
            p++;  
            if(p>=220) return ;  
            this.data[p]=data;  
        }  
        public JTable getTable(){
            
            
            
            jTable=new JTable(data,colNames);  
            return jTable;  
        }  
          
    }  
      
    public static void main(String[] args) {  
        new SystemSoftware();  
       /* int[] a={1,2,3,4};
        int[] b={1,2,3,4,5};
     System.out.println(Arrays.equals(a, b));*/
    }  
}  
