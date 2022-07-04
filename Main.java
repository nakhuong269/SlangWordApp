import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Main extends JPanel implements ActionListener{
    JPanel pn1, pn2, pn3, pn4, pn5, pn6, pnCont , pn7  ;
    JButton btnAdd, btnEdit, btnDelete, btnSearch, btnSearchDefinition, btnRandom, btnReset, btnHome, btnGame;
    JTextField tfSearch;
    BoxLayout bl1, bl2;

    JLabel lb22;
    BorderLayout bdl1;
    FlowLayout fl1;
    CardLayout cl;

    static HashMap<String, String> map;

    public static void main(String args[]) throws FileNotFoundException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        initData();
    }

    public Main()
    {
        super(new BorderLayout());
        pn1 = new JPanel();
        bl1 = new BoxLayout(pn1,BoxLayout.Y_AXIS);
        pn1.setBorder(new EmptyBorder(0,10,10,10));
        pn1.setLayout(bl1);



        tfSearch = new JTextField(20);
        pn2 = new JPanel();
        bl2 = new BoxLayout(pn2, BoxLayout.X_AXIS);
        pn2.setLayout(bl2);
        pn2.setBorder(new EmptyBorder(0, 0,15,0));
        btnSearch = new JButton("Tìm kiếm chính xác");
        btnSearch.addActionListener(this);
        btnSearch.setActionCommand("btnSearch");
        btnSearchDefinition = new JButton("Tìm kiếm liên quan");

        pn2.add(tfSearch);
        pn2.add(Box.createRigidArea(new Dimension(10, 0)));
        pn2.add(btnSearch);
        pn2.add(Box.createRigidArea(new Dimension(10,0)));
        pn2.add(btnSearchDefinition);

        pn3 = new JPanel();
        fl1 = new FlowLayout();
        pn3.setLayout(fl1);

        //Button
        btnAdd = new JButton("Thêm");
        //btnAdd.addActionListener(this);
        btnAdd.setActionCommand("btnAdd");
        btnEdit = new JButton("Chỉnh sửa");
        //btnEdit.addActionListener(this);
        btnEdit.setActionCommand("btnEdit");
        btnDelete = new JButton("Xóa");
        //btnDelete.addActionListener(this);
        btnDelete.setActionCommand("btnDelete");

        pn3.add(btnAdd);
        pn3.add(btnEdit);
        pn3.add(btnDelete);

        pn1.add(pn2);
        pn1.add(pn3);

        pn4 = new JPanel();
        cl = new CardLayout();

        pn4.setLayout(cl);

        pn7 = new JPanel();
        bdl1 = new BorderLayout();
        pn7.setLayout(bdl1);
        pn7.add(pn1,BorderLayout.PAGE_START);
        pn4.add(pn7);

        pn6 = new JPanel();
        btnHome = new JButton("Trang chủ");
        btnHome.addActionListener(this);
        btnHome.setActionCommand("btnHome");

        btnGame = new JButton("Đố vui");
        btnGame.addActionListener(this);
        btnGame.setActionCommand("btnGame");
        pn6.setBorder(new EmptyBorder(0,0,20,0));
        pn6.add(btnHome);
        pn6.add(btnGame);

        pnCont = new JPanel();
        pnCont.setLayout(cl);
        pnCont.add(pn4,"Home");

        pn5 = new JPanel();
        pn5.setBackground(Color.BLACK);
        pnCont.add(pn5,"Game");

        add(pn6,BorderLayout.PAGE_START);
        add(pnCont, BorderLayout.CENTER);
    }
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("SlangWordApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Create and set up the content pane.
        JComponent newContentPane = new Main();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);


        //Display the window.
        frame.setMinimumSize(new Dimension(500,400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if(str.equals("btnHome"))
        {
            cl.show(pnCont,"Home");
            System.out.println("Home");
        } else if (str.equals("btnGame")) {
            cl.show(pnCont,"Game");
            System.out.println("Game");
        }
        else if(str.equals("btnSearch"))
        {
            System.out.println(findSlangWord(tfSearch.getText()));
        }
    }

    private static void initData() throws FileNotFoundException {
        String url = "slang.txt";

        File file = new File(url);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        try {
            String line = reader.readLine();
            map = new HashMap<String,String>();
            while (line != null) {
                String[] w = line.split("`");
                map.put(w[0],w[1]);
                line = reader.readLine();
            }
        }catch (Exception e)
        {
            System.out.print(e.getMessage());
        }
    }

    private String findSlangWord(String slang)
    {
        if(map.containsKey(slang)) {
            return map.get(slang);
        }
        return "";
    }
}
