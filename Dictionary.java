import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Dictionary {
    private HashMap<String, String> map = new HashMap<String,String>();

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }


    public Dictionary() throws FileNotFoundException {
        Input();
    }


    public void Input() throws FileNotFoundException {
        String url = "slang.txt";

        File file = new File(url);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] w = line.split("`");
                map.put(w[0],w[1]);
                line = reader.readLine();
            }
        }catch (Exception e)
        {
            System.out.print(e.getMessage());
        }
        System.out.println(map.size());
    }

    public String findSlangWord(String slang)
    {
        if(map.containsKey(slang)) {
            return map.get(slang);
        }
        return "";
    }

    public ArrayList<String> findDefinitionSlangWord(String slang)
    {
        ArrayList<String> temp = new ArrayList<String>();
        for (String value : map.values()){
            if(value.toLowerCase().contains(slang.toLowerCase())){
                temp.add(value);
            }
        }
        return temp;
    }
}
