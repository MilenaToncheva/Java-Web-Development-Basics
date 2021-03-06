package util;

import java.io.*;

public class HtmlReader {

  public   String readHtmlFile(String filePath) throws IOException {
        File file=new File(filePath);
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder htmlFileContent=new StringBuilder();
        String line;
        while((line=br.readLine())!=null){
            htmlFileContent.append(line).append(System.lineSeparator());
        }
        return htmlFileContent.toString().trim();
    }
}
