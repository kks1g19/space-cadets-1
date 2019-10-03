/*
    * UniNameGetter - class for getting university name 
    * from the university id
    * @author KKS 
    * @version 1.0.0
*/

//import the scanner class to get user input

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UniNameGetter {
    //method for extracting the name from 
    public static String getName(BufferedReader htmlResponse){
        
        //declare line to go across the lines of the reader
        String line; 
        try {
            //if line is not null
            while ((line = htmlResponse.readLine()) != null){
                //if line contains the name
                if(line.contains("property=\"name\"")){
                    //find the regexp
                    //Pattern p = Pattern.compile("^.*property=\"name\">(.*)<em.*$");
                    //Matcher matchedLine = p.matcher(line);
                    int from = line.indexOf("property=\"name\">") + "property=\"name\">".length();
                    int to = line.lastIndexOf("<em");
                    return line.substring(from, to);
                }
            }    
        } catch (Exception e){
            e.printStackTrace();
        }

        return "Not found.";
    }

    public static void main(String[] args) {
        //convert the inputstream System.in reader to a bufferedreader
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID: ");
        //try to capture the input
        try {
            String id = scanner.readLine(); 
            //close the scanner bufferedreader
            scanner.close();

            String address = "https://www.ecs.soton.ac.uk/people/" + id;

            //create URL object from String address 
            URL webpageURL = new URL(address);

            //url reader object - read from the url
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(webpageURL.openStream()));

            System.out.println(getName(urlReader));

        } catch (Exception e){
            //print the trace of the error
            e.printStackTrace();
        }
    }
}