package chushka.util;

import java.io.*;

public class HtmlReader {
    public String readHtmlFile(String htmlFilePath) throws IOException {
        File file=new File(htmlFilePath);
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        StringBuilder htmlFileContent=new StringBuilder();
        String line;
        while((line=br.readLine())!=null&&line.length()>0){
            htmlFileContent.append(line).append(System.lineSeparator());
        }
        return htmlFileContent.toString().trim();
    }
}
