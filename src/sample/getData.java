package sample;

import com.snowtide.*;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;

import java.io.PrintWriter;
import java.util.ArrayList;

public class getData {

    public static void main(String pd, String outp) throws Exception {
        Document pdf = PDF.open(pd);
        StringBuilder text = new StringBuilder(1024);
        pdf.pipe(new OutputTarget(text));
        pdf.close();


        String[] lines = text.toString().split(System.getProperty("line.separator"));
        ArrayList<ArrayList<String>> aal= format("E", lines,1);
        ArrayList<ArrayList<String>> aa2= format("Q1", lines,0);
        ArrayList<ArrayList<String>> aa3= format("Q2", lines,0);

        PrintWriter out = new PrintWriter(outp+"klausurene.html");
        PrintWriter out2 = new PrintWriter(outp+"klausurenq.html");
        PrintWriter out3 = new PrintWriter(outp+"klausurenq2.html");

        PrintWriter out4 = new PrintWriter(outp+"klausurene_black.html");
        PrintWriter out5 = new PrintWriter(outp+"klausurenq_black.html");
        PrintWriter out6 = new PrintWriter(outp+"klausurenq2_black.html");

        out.write(toTable(aal,false));
        out2.write(toTable(aa2, false));
        out3.write(toTable(aa3,false));
        out.close();
        out2.close();
        out3.close();

        out4.write(toTable(aal,true));
        out5.write(toTable(aa2, true));
        out6.write(toTable(aa3,true));
        out4.close();
        out5.close();
        out6.close();

        for(ArrayList<String> p:aal){
            System.out.println(p);
        }
        for(ArrayList<String> p:aa2){
            System.out.println(p);
        }
        for(ArrayList<String> p:aa3){
            System.out.println(p);
        }
        System.out.println(toTable(aa2,false));


    }

    public static String toTable(ArrayList<ArrayList<String>> j, Boolean black){
        String h=new String();
        h+="<html>";
        h+="\n<body>";
        h+="\n<table>";
        for(ArrayList<String> p:j){
            h+="\n<tr>";
            for(String w:p){
                h+="\n<td>"+w+"</td>";
            }
            h+="</tr>";
        }
        h+="</table>";
        h+="</body>";

        if(black){
            h+="<style>\n" +
                    "    body{\n" +
                    "        background-color: black;\n" +
                    "    }\n" +
                    "    td{\n" +
                    "        color: white;\n" +
                    "    }\n" +
                    "    tr{\n" +
                    "        color: white;\n" +
                    "    }\n" +
                    "</style>";
        }
        h+="\n</html>";

        return h;
    }


    public static ArrayList<ArrayList<String>> format(String start, String[] lines, int a){
        ArrayList<ArrayList<String>> e = new ArrayList<>();
        ArrayList<String> temp=new ArrayList<>();
        for(int i=0; i<lines.length;i++){
            if(lines[i].startsWith(start)){
                //temp.add(lines[i].substring(0,2-a));
                temp.add(lines[i].substring(11-a,16-a));
                ArrayList<Integer> platz = new ArrayList<>();
                for(int k=4; k<lines[i].length(); k++){
                    if(lines[i].substring(k-4,k).equals("    ")) {
                        platz.add(k);

                    }
                }
                System.out.println(platz.size());
                if(platz.size()>6) {
                    temp.add(lines[i].substring(platz.get(5), platz.get(6) - 4));
                }

                else if(platz.size()>3){
                    temp.add(lines[i].substring(platz.get(2)+1, platz.get(2)+5)+lines[i].substring(platz.get(5), lines[i].length()-2));
                }

                else {
                    temp.add(lines[i].substring(platz.get(2), lines[i].length()-2));
                }

                e.add(temp);

                temp=new ArrayList<>();

            }
        }
        return e;
    }

}

// C:/Users/leona/Desktop/Schule/mpsgen/arbeiten.pdf
// C:/Users/leona/Desktop/Schule/mpsgen/

