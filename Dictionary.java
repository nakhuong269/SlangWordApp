import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Dictionary extends Component {
    private HashMap<String, String> map = new HashMap<String,String>();

    private LinkedHashMap<String, String> resultSearch;

    public Dictionary(LinkedHashMap<String, String> resultSearch) {
        this.resultSearch = resultSearch;
    }

    public LinkedHashMap<String, String> getResultSearch() {
        return resultSearch;
    }

    public void setResultSearch(LinkedHashMap<String, String> resultSearch) {
        this.resultSearch = resultSearch;
    }

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

    public LinkedHashMap<String, String> findSlangWord(String slang)
    {
        resultSearch = new LinkedHashMap<String,String>();
        if(map.containsKey(slang)) {
            resultSearch.put(slang, map.get(slang));
        }
        return resultSearch;
    }

    public LinkedHashMap<String,String> findDefinitionSlangWord(String slang)
    {
        resultSearch = new LinkedHashMap<String,String>();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            SlangWord slangWord = new SlangWord(entry.getKey(),entry.getValue());
            if(slangWord.getMean().toLowerCase().contains(slang.toLowerCase())){
                resultSearch.put(slangWord.getSlag(),slangWord.getMean());
            }
        }
        return resultSearch;
    }

    public boolean addSlangWord(SlangWord slangWord)
    {
        try
        {
            String filename= "slang.txt";
            FileWriter fw = new FileWriter(filename,true);
            fw.write(slangWord.getSlag()+"`"+slangWord.getMean()+"\n");
            fw.close();
            this.Input();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public boolean deleteSlangWord(SlangWord slangWord)
    {
        int dialogResult = JOptionPane.showConfirmDialog (this, "Bạn có muốn xóa từ " + slangWord.getSlag() +  " không?","Xóa từ",JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION) {
            map.remove(slangWord.getSlag());
            resultSearch.remove(slangWord.getSlag());
            try {
                String filename = "slang.txt";
                FileWriter fw = new FileWriter(filename, false);
                for(Map.Entry<String, String> entry : map.entrySet()) {
                    fw.write(entry.getKey() + "`" + entry.getValue() + "\n");
                }
                fw.close();
                this.Input();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi");
            }
            return true;
        }
        return false;
    }

    public boolean editSlangWord(SlangWord slangWord)
    {
        map.replace(slangWord.getSlag(),slangWord.getMean());
        resultSearch.replace(slangWord.getSlag(),slangWord.getMean());
        try {
            String filename = "slang.txt";
            FileWriter fw = new FileWriter(filename,false);
            for(Map.Entry<String, String> entry : map.entrySet()) {
                fw.write(entry.getKey() + "`" + entry.getValue() + "\n");
            }
            fw.close();
            this.Input();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi");
            return false;
        }
        return true;
    }
}
