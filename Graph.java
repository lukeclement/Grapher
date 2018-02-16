import java.util.*;
import java.io.*;
/*
    Program written by Luke Clement on 16/02/2018.
    Feel free to use this program for whatever data that needs to be processed.
    Please cite me when doing so!
    Have a great day :D
*/
public class Graph{
    public static void main(String[] args){
        //Getting file name
        Scanner scan=new Scanner(System.in);
        System.out.println("File name of data?");
        System.out.print(">> ");
        String fileName="Data/";
        fileName += scan.nextLine();
        
        String pythonCode="graph.py";
        String line=null;
        
        System.out.println("Name of figure to produce");
        System.out.print(">> ");
        String saveName="Figures/";
        saveName += scan.nextLine();
        saveName += ".png";
        //Getting values for x, y and y error
        //Assuming starting document starts as:
        /*
            X|Y|Z
            1|2|1
            2|2|1
            3|3|2
         ...
        */
        int x,y,ye;
        
        System.out.println("Column for x values (number)");
        System.out.print(">> ");
        x = scan.nextInt();
        
        System.out.println("Column for y values (number)");
        System.out.print(">> ");
        y = scan.nextInt();
        
        System.out.println("Column for error in y values (number)");
        System.out.print(">> ");
        ye = scan.nextInt();
        
        //Initialising arrays
        
        List<String> X = new ArrayList<>();
        List<String> Y = new ArrayList<>();
        List<String> YE= new ArrayList<>();
        
        //Read file
        
        System.out.println("Reading file...");
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader br=new BufferedReader(fileReader);
            while((line=br.readLine())!=null){
                String[] values=line.split(",");
                X.add(values[x]);
                Y.add(values[y]);
                YE.add(values[ye]);
            }
            br.close();
            System.out.println("File read, "+(X.size()-1)+" entries found.");
        }catch(Exception ex){
            System.out.println("File not read!");
        }
        
        //Writing grapher
        System.out.println("Writing up python code...");
        try{
            
            FileWriter write = new FileWriter(pythonCode, false);
            PrintWriter print_line = new PrintWriter(write);
            print_line.printf( "%s" + "%n" , "import numpy as n");
            print_line.printf( "%s" + "%n" , "import matplotlib.pyplot as plt");
            print_line.printf( "%s" + "%n" , "plt.style.use('_classic_test')");
            print_line.printf( "%s" + "%n" , "x=n.array([");
            for(int i=1;i<X.size();i++){
                if(i==1){
                    print_line.printf( "%s" , X.get(i));
                }else{
                    print_line.printf( "%s" , ","+X.get(i));
                }
                
            }
            print_line.printf( "%s" + "%n" , "])");
            print_line.printf( "%s" , "y=n.array([");
            for(int i=1;i<X.size();i++){
                if(i==1){
                    print_line.printf( "%s" , Y.get(i));
                }else{
                    print_line.printf( "%s" , ","+Y.get(i));
                }
            }
            print_line.printf( "%s" + "%n", "])");
            print_line.printf( "%s" , "err=n.array([");
            for(int i=1;i<X.size();i++){
                if(i==1){
                    print_line.printf( "%s" , YE.get(i));
                }else{
                    print_line.printf( "%s" , ","+YE.get(i));
                }
            }
            print_line.printf( "%s" + "%n", "])");
            print_line.printf( "%s" + "%n", "plt.figure(num=None, figsize=(8,6), dpi=80, facecolor='w', edgecolor='k')");
            print_line.printf( "%s" + "%n", "plt.errorbar(x,y,yerr=err,fmt='x')");
            String[] xSplit=X.get(0).split("/");
            String[] ySplit=Y.get(0).split("/");
            print_line.printf( "%s" + "%n", "plt.xlabel(\""+X.get(0)+"\")");
            print_line.printf( "%s" + "%n", "plt.title(\""+ySplit[0]+" vs "+xSplit[0]+"\")");
            print_line.printf( "%s" + "%n", "plt.ylabel(\""+Y.get(0)+"\")");
            print_line.printf( "%s" + "%n", "plt.savefig(\""+saveName+"\")");
            print_line.close();
            
            System.out.println("Python coded!");
        }catch(Exception ex){
            System.out.println("A problem occured!");
            System.out.println(ex);
        }
        
        //Executing grapher
        System.out.println("Executing python...");
        Process p;
        try{
            p = Runtime.getRuntime().exec("python graph.py");
            p.waitFor();
            System.out.println("Finished successfully!");
        }catch(Exception ex){
            System.out.println("Something has went wrong!");
            System.out.println(ex);
        }
        
    }
}
