package au.edu.usc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;


public class XmlCheckerSolutionOne {
    static Path fileName;
    static String actual;
    public static void main(String[] args) throws IOException {
        int iteration = 0;
        for (int i = 0; i < 5; i++) {
            fileName = Path.of(String.format("T1_test_cases/False_%s.xml", iteration + 1));
            actual = Files.readString(fileName);
            if (XMLMatch(actual) == false) {
                System.out.println(String.format("False_%s.xml is an Invalid XML Document", iteration + 1));
            } else {
                System.out.println(String.format("False_%s.xml is a Valid XML Document", iteration + 1));
            }
            iteration++;
        }
        iteration = 0;
        for (int j = 0; j < 4; j++) {

            fileName = Path.of(String.format("T1_test_cases/True_%s.xml", iteration + 1));
            actual = Files.readString(fileName);
            if (XMLMatch(actual) == false) {
                System.out.println(String.format("True_%s.xml is an Invalid XML Document", iteration + 1));
            } else {
                System.out.println(String.format("True_%s.xml is a Valid XML Document", iteration + 1));
            }
            iteration++;
        }

    }

    public static boolean XMLMatch(String xml)
    {
        LinkedList<String> buffer = new LinkedList<>();
        int index = actual.indexOf("<");
        String tagBeforeAttr = "";

        while(index != -1){
            int k = actual.indexOf('>', index + 1);
            tagBeforeAttr = "";
            if(k == -1)
            {
                return false;
            }
            String tag = actual.substring(index + 1, k);
            //Check for single root
            if(index > 0) {
                if (buffer.size() == 0) {
                    return false;
                }
            }
            if(tag.contains("=")){
                int findTag = tag.indexOf('=');
                tagBeforeAttr = tag.substring(0,findTag - 3);
                if(tag.substring(findTag+1,findTag+2).contains("\"")){
                    if(tag.endsWith("\"")){
                        //Do Nothing
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            if(!tag.startsWith("/")){
                if(tagBeforeAttr != ""){
                    buffer.push(tagBeforeAttr);
                }
                else
                {
                    buffer.push(tag);
                }

            }
            else{
                if(buffer.isEmpty())
                {
                    return false;
                }
                if(!tag.substring(1).equals(buffer.pop()))
                {
                    return false;
                }
            }
            index = actual.indexOf('<', k+1);
        }
        return buffer.isEmpty();
    }

}




