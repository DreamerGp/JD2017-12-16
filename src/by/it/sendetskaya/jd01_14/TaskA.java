package by.it.sendetskaya.jd01_14;

import java.io.*;

public class TaskA {

    public static void main(String[] args) {
        String path=System.getProperty("user.dir");
        path=path+"/src/by/it/sendetskaya/jd01_14/";
        File file=new File(path,"dataTaskA.bin");
       try (DataOutputStream dos=new DataOutputStream  (new FileOutputStream(file))) {
           for (int i = 0; i < 20; i++) {
               dos.writeInt((int)Math.round(Math.random()*20));
           }
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }

       //reading

        try (DataInputStream dis=new DataInputStream  (new BufferedInputStream
                (new FileInputStream(file)));
             PrintWriter print=new PrintWriter(new FileWriter(new File(path,"resultTaskA.txt")))) {

           double sum=0;
           int count=0;

           while (dis.available()>0)
           {
               int value=dis.readInt();
               System.out.print(value+" ");
               print.print(value+" ");
               sum+=value;
               count++;
           }
            System.out.println("\navg="+sum/count);
           print.print("\navg="+sum/count);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
