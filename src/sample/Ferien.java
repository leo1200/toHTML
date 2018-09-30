package sample;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Ferien {
    public Ferien(String ordner){
        try {
            PrintWriter f = new PrintWriter(ordner + "ferien.html");
            PrintWriter fb = new PrintWriter(ordner +"ferien_black.html");

            String quellcode = getHtml();

            f.write(lese(false, quellcode));
            fb.write(lese(true, quellcode));

            f.close();
            fb.close();

            System.out.println(lese(false, quellcode));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lese(Boolean black, String htmldoc){
        String erg = new String();
        String tab = new String();
        erg+="<html><body><br />";
        int i = htmldoc.indexOf("<table>");
        while(i >= 0) {
            tab=htmldoc.substring(i, htmldoc.indexOf("</table>", i)+8) + "<br /><br /><br /><br /><br />";
            erg+= "\n" + this.addyear(tab);
            i = htmldoc.indexOf("<table>", i+1);
        }
        erg+="</body><br /><br /><br /></html>";

        if(black){
            erg+="<style>\n" +
                    "    table{\n" +
                    "         border-spacing:20px;\n" +
                    "        \n" +
                    "    }\n" +
                    "    body{\n" +
                    "        background-color: black;\n" +
                    "    }\n" +
                    "    td{\n" +
                    "        color: white;\n" +
                    "    }\n" +
                    "    tr{\n" +
                    "        color: white;\n" +
                    "    }\n" +
                    "    caption{\n" +
                    "        color: white;\n" +
                    "    }\n" +
                    "    </style>";
        }
        else{
            erg+="<style>\n" +
                    "    table{\n" +
                    "         border-spacing:20px;\n" +
                    "    }\n" +
                    "    </style>";
        }
        return erg.replace("*", "");
    }

    public String getHtml() throws MalformedURLException, IOException {
        URL fer = new URL("https://www.schleswig-holstein.de/DE/Themen/F/Ferien.html");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        fer.openStream()));
        String inputLine;
        String ergeb = new String();
        while ((inputLine = in.readLine()) != null)
            ergeb+=inputLine;
        in.close();
        return ergeb;
    }

    public String addyear(String tab){
        String zwischen = tab;
        List Jahre = Arrays.asList((zwischen.replaceAll("[^0-9]+", " ").trim().split(" ")));
        Set<Integer> j = new HashSet<>();
        for(Object i: Jahre.toArray()){
            j.add(Integer.parseInt((String)i));
        }
        List<Integer> jo = new ArrayList<>();
        jo.addAll(j);
        Collections.sort(jo, Collections.reverseOrder());
        String title = "Schuljahr " + jo.get(1)+ "/" +jo.get(0);
        System.out.println(title);
        return tab.replace("<table>", "<table align=\"center\"> <caption>"+title+"</caption>" );
    }

    public static void main(String args[]){
        Ferien ferien = new Ferien("");
    }

}
