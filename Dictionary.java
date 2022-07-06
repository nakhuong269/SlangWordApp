import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Dictionary extends Component {
    private HashMap<String, String> map = new HashMap<String,String>();

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }


    public Dictionary() {}


    public void Input() throws FileNotFoundException {
        String url = "slang.txt";

        File file = new File(url);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] w = line.split("`");
                SlangWord slangWord = new SlangWord(w[0],w[1]);
                map.put(slangWord.getSlag(),slangWord.getMean());
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

    public void addSlangWord(SlangWord slangWord)
    {
        try
        {
            String filename= "slang.txt";
            FileWriter fw = new FileWriter(filename,true);
            fw.write(slangWord.getSlag()+"`"+slangWord.getMean()+"\n");
            fw.close();
            this.Input();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Lỗi");
        }
    }
}
