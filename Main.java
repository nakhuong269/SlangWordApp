import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

public class Main extends JPanel implements ActionListener{
    JPanel pn1, pn2, pn3, pn4, pn5, pn6, pnCont , pn7, pn8, pn9, pn10, pn11, pn12, pn13, pn14 ,pn15, pn16, pn17, pn18;

    JRadioButton rdbSlangGame, rdbMeanGame, rdbAnswerA, rdbAnswerB, rdbAnswerC, rdbAnswerD;
    JButton btnAdd, btnEdit, btnDelete, btnSearch, btnSearchDefinition, btnPageRandom,  btnRandom, btnReset, btnHistory, btnHome, btnGame, btnRefeshGame, btnCheckGame ;
    JTextField tfSearch;
    BoxLayout bl1, bl2, bl3, bl4, bl5;

    JList lHistory;
    JLabel lb1, lb2, lbSlang, lbMean, lb3, lbGame, lbSlangGame;
    BorderLayout bdl1, bdl2;
    FlowLayout fl1;
    CardLayout cl;
    String[] colHeader = { "Slang", "Mean"};

    ButtonGroup bgMode, bgAnswer;

    JTable jtbSlangWord;
    static Dictionary dictionary = new Dictionary();


    public static void main(String args[]) throws FileNotFoundException {
            try {
                createAndShowGUI();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        dictionary.Input();
    }

    public Main() throws FileNotFoundException {
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
        btnSearch = new JButton("Slang Search");
        btnSearch.addActionListener(this);
        btnSearch.setActionCommand("btnSearch");
        btnSearchDefinition = new JButton("Definition Search");
        btnSearchDefinition.addActionListener(this);
        btnSearchDefinition.setActionCommand("btnSearchDefinition");

        pn2.add(tfSearch);
        pn2.add(Box.createRigidArea(new Dimension(10, 0)));
        pn2.add(btnSearch);
        pn2.add(Box.createRigidArea(new Dimension(10,0)));
        pn2.add(btnSearchDefinition);

        pn3 = new JPanel();
        fl1 = new FlowLayout();
        pn3.setLayout(fl1);

        //Button
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        btnAdd.setActionCommand("btnAdd");
        btnEdit = new JButton("Edit");
        btnEdit.addActionListener(this);
        btnEdit.setActionCommand("btnEdit");
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
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
        btnHome = new JButton("Home");
        btnHome.addActionListener(this);
        btnHome.setActionCommand("btnHome");

        btnGame = new JButton("Quiz");
        btnGame.addActionListener(this);
        btnGame.setActionCommand("btnGame");

        btnPageRandom = new JButton("Random");
        btnPageRandom.addActionListener(this);
        btnPageRandom.setActionCommand("btnPageRandom");

        btnHistory = new JButton("History Search");
        btnHistory.addActionListener(this);
        btnHistory.setActionCommand("btnHistory");

        btnReset = new JButton("Reset");
        btnReset.addActionListener(this);
        btnReset.setActionCommand("btnReset");

        pn6.setBorder(new EmptyBorder(0,0,20,0));
        pn6.add(btnHome);
        pn6.add(btnPageRandom);
        pn6.add(btnGame);
        pn6.add(btnHistory);
        pn6.add(btnReset);


        pnCont = new JPanel();
        pnCont.setLayout(cl);
        pnCont.add(pn4,"Home");

        pn8 = new JPanel();
        bl3 = new BoxLayout(pn8,BoxLayout.PAGE_AXIS);
        pn8.setLayout(bl3);

        lbSlang = new JLabel();
        lbSlang.setAlignmentX(CENTER_ALIGNMENT);
        lbSlang.setFont(new Font("Arial",Font.PLAIN, 34));
        lbMean = new JLabel();
        lbMean.setAlignmentX(CENTER_ALIGNMENT);
        lbMean.setFont(new Font("Arial",Font.PLAIN, 26));
        lb1 = new JLabel("Slang: ");
        lb1.setAlignmentX(CENTER_ALIGNMENT);
        lb1.setFont(new Font("Arial",Font.PLAIN, 22));
        lb2 = new JLabel("Mean: ");
        lb2.setAlignmentX(CENTER_ALIGNMENT);
        lb2.setFont(new Font("Arial",Font.PLAIN, 22));

        btnRandom = new JButton("Start");
        btnRandom.addActionListener(this);
        btnRandom.setActionCommand("btnRandom");
        btnRandom.setAlignmentX(CENTER_ALIGNMENT);
        pn8.add(lb1);
        pn8.add(Box.createRigidArea(new Dimension(0 ,15)));
        pn8.add(lbSlang);
        pn8.add(Box.createRigidArea(new Dimension(0 ,15)));
        pn8.add(Box.createRigidArea(new Dimension(0 ,15)));
        pn8.add(lb2);
        pn8.add(Box.createRigidArea(new Dimension(0 ,15)));
        pn8.add(Box.createRigidArea(new Dimension(0 ,15)));
        pn8.add(lbMean);
        pn8.add(Box.createRigidArea(new Dimension(0 ,30)));
        pn8.add(btnRandom);

        pnCont.add(pn8, "Random");

        pn5 = new JPanel();


        pn9 = new JPanel();
        lb3 = new JLabel("Quiz: ");
        rdbSlangGame = new JRadioButton("Slang");
        rdbMeanGame = new JRadioButton("Mean");
        lb3.setFont(new Font("Arial", Font.BOLD,22));
        pn9.add(lb3);
        pn9.add(rdbSlangGame);
        pn9.add(Box.createRigidArea(new Dimension(20,0)));
        pn9.add(rdbMeanGame);
        rdbSlangGame.setActionCommand("rdbSlang");
        rdbSlangGame.addActionListener(this);
        rdbMeanGame.setActionCommand("rdbMean");
        rdbMeanGame.addActionListener(this);
        bgMode= new ButtonGroup();
        bgMode.add(rdbSlangGame);
        bgMode.add(rdbMeanGame);





        lbGame = new JLabel();
        lbGame.setFont(new Font("Arial",Font.BOLD,18));
        lbGame.setAlignmentX(CENTER_ALIGNMENT);
        pn14 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pn14.add(lbGame);


        pn10 = new JPanel();
        bl4 = new BoxLayout(pn10, BoxLayout.Y_AXIS);
        pn10.setLayout(bl4);
        bgAnswer = new ButtonGroup();


        rdbAnswerA = new JRadioButton();
        rdbAnswerB = new JRadioButton();
        rdbAnswerC = new JRadioButton();
        rdbAnswerD = new JRadioButton();
        pn15 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn15.add(rdbAnswerA);
        pn16 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn16.add(rdbAnswerB);
        pn17 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn17.add(rdbAnswerC);
        pn18 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pn18.add(rdbAnswerD);

        bgAnswer.add(rdbAnswerA);
        bgAnswer.add(rdbAnswerB);
        bgAnswer.add(rdbAnswerC);
        bgAnswer.add(rdbAnswerD);


        pn10.add(pn15);
        pn10.add(Box.createRigidArea(new Dimension(0,30)));
        pn10.add(pn16);
        pn10.add(Box.createRigidArea(new Dimension(0,30)));
        pn10.add(pn17);
        pn10.add(Box.createRigidArea(new Dimension(0,30)));
        pn10.add(pn18);
        pn10.add(Box.createRigidArea(new Dimension(0,30)));



        lbSlangGame = new JLabel();
        lbSlangGame.setAlignmentX(CENTER_ALIGNMENT);


        pn12 = new JPanel();
        btnRefeshGame = new JButton("Refesh");
        btnRefeshGame.addActionListener(this);
        btnRefeshGame.setActionCommand("btnRefeshGame");
        btnCheckGame = new JButton("Answer");
        btnCheckGame.addActionListener(this);
        btnCheckGame.setActionCommand("btnCheckGame");
        pn12.add(btnRefeshGame);
        pn12.add(Box.createRigidArea(new Dimension(50, 0)));
        pn12.add(btnCheckGame);




        pn11 = new JPanel();
        bl5 = new BoxLayout(pn11,BoxLayout.Y_AXIS);
        pn11.setLayout(bl5);

        pn11.add(pn9);
        pn11.add(Box.createRigidArea(new Dimension(0,30)));
        pn11.add(pn14);
        pn11.add(Box.createRigidArea(new Dimension(0,30)));
        pn11.add(pn10);
        pn11.add(lbSlangGame);
        pn11.add(Box.createRigidArea(new Dimension(0, 20)));
        pn11.add(pn12);

        pn5.add(pn11);
        pnCont.add(pn5,"Game");

        pn13 = new JPanel();
        bdl2 = new BorderLayout();
        pn13.setLayout(bdl2);
        pn13.setBorder(new EmptyBorder(10,10,10,10));
        pn13.setPreferredSize(new Dimension(200,50));

        dictionary.InputHistorySearch();
        DefaultListModel listmodel = new DefaultListModel();
        listmodel.addAll(dictionary.getHistorySearch().values());


        lHistory = new JList(listmodel);
        lHistory.setBorder(new EmptyBorder(10,10,10,10));

        lHistory.setModel(listmodel);
        JScrollPane scrollPane = new JScrollPane(lHistory,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pn13.add(scrollPane, BorderLayout.CENTER);
        pnCont.add(pn13, "History");


        //Table
        DefaultTableModel table_model = new DefaultTableModel(colHeader,0);
        jtbSlangWord = new JTable(table_model);
        jtbSlangWord.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                SlangWord slangWord = new SlangWord(
                        jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),0).toString(),
                        jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),1).toString());
            }});
        jtbSlangWord.setDefaultEditor(Object.class, null);

        pn7.add(new JScrollPane(jtbSlangWord));

        add(pn6,BorderLayout.PAGE_START);
        add(pnCont, BorderLayout.CENTER);
    }
    private static void createAndShowGUI() throws FileNotFoundException {
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
        } else if (str.equals("btnGame")) {
            rdbSlangGame.setSelected(true);
            cl.show(pnCont,"Game");
            randomSlangWord();
            bgAnswer.clearSelection();
            lbSlangGame.setText("");

        }
        else if (str.equals("btnPageRandom")) {
            cl.show(pnCont,"Random");
        }
        else if(str.equals("btnSearch"))
        {
            if(!tfSearch.getText().isEmpty()) {
                dictionary.findSlangWord(tfSearch.getText());
                try {
                    dictionary.addHistorySearch(tfSearch.getText());
                    dictionary.InputHistorySearch();
                    DefaultListModel listmodel = new DefaultListModel();
                    listmodel.addAll(dictionary.getHistorySearch().values());
                    lHistory.setModel(listmodel);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                fillTable();
            }
        } else if (str.equals("btnSearchDefinition")) {
            if(!tfSearch.getText().isEmpty()) {
                dictionary.findDefinitionSlangWord(tfSearch.getText());

                try {
                    dictionary.addHistorySearch(tfSearch.getText());
                    dictionary.InputHistorySearch();
                    DefaultListModel listmodel = new DefaultListModel();
                    listmodel.addAll(dictionary.getHistorySearch().values());
                    lHistory.setModel(listmodel);
                } catch (FileNotFoundException ex) {
                    System.out.println("Lỗi này");
                }
                fillTable();
            }
        } else if (str.equals("btnAdd")) {
            AddFrm addFrm = new AddFrm();
            addFrm.setVisible(true);
            try {
                dictionary.Input();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this,"Error");
            }
        } else if (str.equals("btnEdit")) {
            if(jtbSlangWord.getSelectionModel().isSelectionEmpty()==false)
            {
                EditFrm editFrm = new EditFrm();
                editFrm.tfSlag.setText(jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),0).toString());
                editFrm.tfMean.setText(jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),1).toString());
                editFrm.setVisible(true);
                fillTable();
            }else {
                JOptionPane.showMessageDialog(this,"Please search and choose slang wanna edit",
                        "Message",JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (str.equals("btnDelete")) {
            if(jtbSlangWord.getSelectionModel().isSelectionEmpty()==false)
            {
                if(dictionary.deleteSlangWord(new SlangWord(
                        jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),0).toString(),
                        jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),1).toString())))
                {
                    JOptionPane.showMessageDialog(this,"Delete Success");
                }
                fillTable();
            }else {
                JOptionPane.showMessageDialog(this,"Please search and choose slang",
                        "Message",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (str.equals("btnRandom")) {
            SlangWord slangWord = dictionary.randomSlangWord();
            lbSlang.setText(slangWord.getSlag());
            lbMean.setText(slangWord.getMean());
        } else if (str.equals("btnRefeshGame")) {
            String rdbString = bgMode.getSelection().getActionCommand();
            bgAnswer.clearSelection();
            lbSlangGame.setText("");
            if(rdbString.equals("rdbSlang"))
            {
                randomSlangWord();
            }else{
                randomDefinition();
            }
        } else if (str.equals("btnCheckGame")) {
            String rdbString = bgMode.getSelection().getActionCommand();
            String AnswerSelected = "";
            if(rdbAnswerA.isSelected())
            {
                AnswerSelected = rdbAnswerA.getText();
            }
            else if(rdbAnswerB.isSelected()){
                AnswerSelected = rdbAnswerB.getText();
            } else if (rdbAnswerC.isSelected()) {
                AnswerSelected = rdbAnswerC.getText();
            } else if (rdbAnswerD.isSelected()) {
                AnswerSelected = rdbAnswerD.getText();
            }
            if(rdbString.equals("rdbSlang"))
            {
                if(AnswerSelected.equals(dictionary.getMap().get(lbGame.getText())))
                {
                    lbSlangGame.setText("Correct");
                    lbSlangGame.setFont(new Font("Arial",Font.BOLD,22));
                    lbSlangGame.setForeground(Color.GREEN);
                }
                else {
                    lbSlangGame.setText("Incorrect");
                    lbSlangGame.setFont(new Font("Arial",Font.BOLD,22));
                    lbSlangGame.setForeground(Color.RED);
                }
            }else{
                if(dictionary.getMap().get(AnswerSelected).equals(lbGame.getText()))
                {
                    lbSlangGame.setText("Correct");
                    lbSlangGame.setFont(new Font("Arial",Font.BOLD,22));
                    lbSlangGame.setForeground(Color.GREEN);
                }
                else {
                    lbSlangGame.setText("Incorrect");
                    lbSlangGame.setFont(new Font("Arial",Font.BOLD,22));
                    lbSlangGame.setForeground(Color.RED);
                }
            }
        } else if (str.equals("rdbSlang")) {
            randomSlangWord();
            bgAnswer.clearSelection();
            lbSlangGame.setText("");
        } else if (str.equals("rdbMean")) {
            randomDefinition();
            bgAnswer.clearSelection();
            lbSlangGame.setText("");
        } else if (str.equals("btnHistory")) {
            cl.show(pnCont,"History");

        } else if (str.equals("btnReset")) {
            int dialogResult = JOptionPane.showConfirmDialog (this, "Do you want to reset dictionary ?","Delete",JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION) {
                try {
                    dictionary.resetDictionary();
                    fillTable();

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void randomSlangWord() {
        ArrayList<String> listSlangword =  dictionary.randomSlangWordGame();

        lbGame.setText(listSlangword.get(0));

        Collections.shuffle(listSlangword);
        rdbAnswerA.setText(dictionary.getMap().get(listSlangword.get(0)));
        rdbAnswerB.setText(dictionary.getMap().get(listSlangword.get(1)));
        rdbAnswerC.setText(dictionary.getMap().get(listSlangword.get(2)));
        rdbAnswerD.setText(dictionary.getMap().get(listSlangword.get(3)));
    }

    private void randomDefinition() {
        ArrayList<String> listSlangword =  dictionary.randomSlangWordGame();

        lbGame.setText(dictionary.getMap().get(listSlangword.get(0)));

        Collections.shuffle(listSlangword);
        rdbAnswerA.setText(listSlangword.get(0));
        rdbAnswerB.setText(listSlangword.get(1));
        rdbAnswerC.setText(listSlangword.get(2));
        rdbAnswerD.setText(listSlangword.get(3));
    }

    private void fillTable()
    {
        DefaultTableModel model = (DefaultTableModel) jtbSlangWord.getModel();
        model.setRowCount(0);
        for(Map.Entry<String, String> entry : dictionary.getResultSearch().entrySet()) {
            SlangWord slangWord = new SlangWord(entry.getKey(),entry.getValue());
            Object [] rowdata = new Object[2];
            rowdata[0] = slangWord.getSlag();
            rowdata[1] = slangWord.getMean();
            model.addRow(rowdata);
        }
    }
}
