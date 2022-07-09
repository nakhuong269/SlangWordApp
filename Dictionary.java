import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Dictionary extends Component {
    private HashMap<String, String> map = new HashMap<String,String>();

    private TreeMap<Integer,String> historySearch = new TreeMap<Integer,String>(Collections.reverseOrder());
    private LinkedHashMap<String, String> resultSearch;

    private ArrayList<String> randomArray;

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

    public TreeMap<Integer, String> getHistorySearch() {
        return historySearch;
    }

    public void setHistorySearch(TreeMap<Integer, String> historySearch) {
        this.historySearch = historySearch;
    }

    public ArrayList<String> getRandomArray() {
        return randomArray;
    }

    public void setRandomArray(ArrayList<String> randomArray) {
        this.randomArray = randomArray;
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
        InputHistorySearch();
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


    public SlangWord randomSlangWord()
    {
        Object[] randomString = map.keySet().toArray();
        int Index = new Random().nextInt(randomString.length);
        Object key = randomString[Index];

        return new SlangWord(key.toString(), map.get(key));
    }

    public ArrayList<String> randomSlangWordGame()
    {
        randomArray = new ArrayList<String>();
        for(int i = 0 ; i < 4;i++)
        {
            SlangWord slangWord =  this.randomSlangWord();
            randomArray.add(slangWord.getSlag());
        }
        return randomArray;
    }

    public void InputHistorySearch() throws FileNotFoundException {
        String url = "history.txt";

        File file = new File(url);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        try {
            String line = reader.readLine();
            while (line != null) {
                String[] w = line.split("`");
                historySearch.put(Integer.parseInt(w[0]),w[1]);
                line = reader.readLine();
            }
        }catch (Exception e)
        {
            System.out.print(e.getMessage());
        }
        System.out.println(historySearch.size());
    }
    public void addHistorySearch(String s) throws FileNotFoundException {
        try
        {
            String filename= "history.txt";
            FileWriter fw = new FileWriter(filename,true);
            fw.write(this.historySearch.size() + "`"+ s + "\n");
            fw.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        InputHistorySearch();
    }

}
