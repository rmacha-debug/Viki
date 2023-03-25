package assiangroup.programms.array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class viki {

    public static int getData(int pageNumber,String p) {
        boolean result = false;
        HashSet<String> set = new HashSet<>();
        try {
            StringBuilder content = new StringBuilder();
            URL url = new URL("https://api.viki.io/v4/videos.json?app=100250a&per_page=10&page="+pageNumber);

            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
                if(line.contains("more")){
                    Pattern pattern = Pattern.compile(p,Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(line);
                    while( matcher.find() ) {
                        set.add(matcher.group());
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set.size();
    }

        public static void main(String[] args) {
            int hgflagTrue =0;
            int hgflagFlase = 0;
            int country = 0;
            int TotalPage = 10;
           for(int i=1;i<=TotalPage;i++){
               if(viki.getData(i,"\"hd\":true")>=1){
                   hgflagTrue++;
               };
               if(viki.getData(i,"\"hd\":false")>=1){
                   hgflagFlase++;
               };
               if(viki.getData(i,"country\":\"[a-z]+")>=1){
                   country++;
               };
           }
           System.out.println("hgflag = true count "+hgflagTrue);
           System.out.println("hgflag = false count "+hgflagFlase );
           System.out.println("country count "+country );
        }

}
