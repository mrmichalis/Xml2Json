import java.io.*;


import net.sf.json.JSON;
import net.sf.json.xml.*;

public class Xml2Json {
    /**
     * @param args
     */
    public static void main(String[] args) {

        try {
            // Collection<File> all = new ArrayList<File>();
            // addTree(new File("E://MotoQ.Resources//xml//"), all);
            // System.out.println(all);

            FilenameFilter textFilter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String lowercaseName = name.toLowerCase();
                    if (lowercaseName.endsWith(".xml")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            String xmlFolder = "/Users/michalis/Projects/MotoQ/MotoQ/xml/";
            File folder = new File(xmlFolder);
            File[] listOfFiles = folder.listFiles(textFilter);

            for (File listOfFile : listOfFiles)
                if (!listOfFile.isDirectory()) {
                    //System.out.println();                   
                    System.out.println(listOfFile.getAbsolutePath());
                    //System.out.println();
                    StringBuffer xml = new StringBuffer();

                    Reader reader = new InputStreamReader(new FileInputStream(
                            xmlFolder + listOfFile.getName()), "utf-8");

/*     Reader reader = new InputStreamReader(new FileInputStream(
       "C://dev//!projects//Xml2Json//xml//"
         + "AB2398.xml"), "utf-8");
*/
                    try {
                        char[] ch = new char[1024];
                        int len = 0;
                        while ((len = reader.read(ch)) != -1) {
                            xml.append(ch, 0, len);
                        }
                    } finally {
                        reader.close();
                    }

                    XMLSerializer xmlSerializer = new XMLSerializer();
                    JSON json = xmlSerializer.read(xml.toString());
                    //System.out.println(json.toString());

                    try {
                        FileWriter fstream = new FileWriter("/Users/michalis/Documents/workspace/Xml2Json/js/"
                                + listOfFile.getName().replace(".xml", ".js"));
                        BufferedWriter out = new BufferedWriter(fstream);
                        out.write(json.toString());

                        out.close();
                    } catch (Exception e) {
                        //System.err.println("Error: " + e.getMessage());
                    }

                }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
