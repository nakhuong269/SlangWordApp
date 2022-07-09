import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class Main extends JPanel implements ActionListener{
    JPanel pn1, pn2, pn3, pn4, pn5, pn6, pnCont , pn7, pn8, pn9, pn10, pn11, pn12;

    JRadioButton rdbSlangGame, rdbMeanGame, rdbAnswerA, rdbAnswerB, rdbAnswerC, rdbAnswerD;
    JButton btnAdd, btnEdit, btnDelete, btnSearch, btnSearchDefinition, btnPageRandom,  btnRandom, btnReset, btnHome, btnGame, btnRefeshGame, btnCheckGame ;
    JTextField tfSearch;
    BoxLayout bl1, bl2, bl3, bl4, bl5;

    JLabel lb1, lb2, lbSlang, lbMean, lb3, lbGame, lbSlangGame;
    BorderLayout bdl1;
    FlowLayout fl1;
    CardLayout cl;
    String[] colHeader = { "Từ", "Nghĩa"};

    ButtonGroup bgMode, bgAnswer;

    JTable jtbSlangWord;
    static Dictionary dictionary = new Dictionary();


    public static void main(String args[]) throws FileNotFoundException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        dictionary.Input();
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
        btnAdd = new JButton("Thêm mới");
        btnAdd.addActionListener(this);
        btnAdd.setActionCommand("btnAdd");
        btnEdit = new JButton("Chỉnh sửa");
        btnEdit.addActionListener(this);
        btnEdit.setActionCommand("btnEdit");
        btnDelete = new JButton("Xóa");
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
        btnHome = new JButton("Trang chủ");
        btnHome.addActionListener(this);
        btnHome.setActionCommand("btnHome");

        btnGame = new JButton("Đố vui");
        btnGame.addActionListener(this);
        btnGame.setActionCommand("btnGame");

        btnPageRandom = new JButton("Từ ngẫu nhiên");
        btnPageRandom.addActionListener(this);
        btnPageRandom.setActionCommand("btnPageRandom");

        pn6.setBorder(new EmptyBorder(0,0,20,0));
        pn6.add(btnHome);
        pn6.add(btnPageRandom);
        pn6.add(btnGame);


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
        lb1 = new JLabel("Từ: ");
        lb1.setAlignmentX(CENTER_ALIGNMENT);
        lb1.setFont(new Font("Arial",Font.PLAIN, 22));
        lb2 = new JLabel("Nghĩa: ");
        lb2.setAlignmentX(CENTER_ALIGNMENT);
        lb2.setFont(new Font("Arial",Font.PLAIN, 22));

        btnRandom = new JButton("Làm mới");
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
        lb3 = new JLabel("Đố vui theo: ");
        rdbSlangGame = new JRadioButton("Từ");
        rdbMeanGame = new JRadioButton("Nghĩa");
        lb3.setFont(new Font("Arial", Font.BOLD,22));
        pn9.add(lb3);
        pn9.add(rdbSlangGame);
        pn9.add(Box.createRigidArea(new Dimension(20,0)));
        pn9.add(rdbMeanGame);
        rdbSlangGame.setActionCommand("Slang");
        rdbSlangGame.addActionListener(this);
        rdbMeanGame.setActionCommand("Mean");
        rdbMeanGame.addActionListener(this);
        bgMode= new ButtonGroup();
        bgMode.add(rdbSlangGame);
        bgMode.add(rdbMeanGame);



        pn10 = new JPanel();
        bl4 = new BoxLayout(pn10, BoxLayout.Y_AXIS);
        pn10.setLayout(bl4);

        lbGame = new JLabel();
        lbGame.setFont(new Font("Arial",Font.BOLD,20));
        lbGame.setAlignmentX(CENTER_ALIGNMENT);
        pn10.add(lbGame);
        pn10.add(Box.createRigidArea(new Dimension(0, 30)));

        bgAnswer = new ButtonGroup();
        rdbAnswerA = new JRadioButton();
        rdbAnswerB = new JRadioButton();
        rdbAnswerC = new JRadioButton();
        rdbAnswerD = new JRadioButton();
        bgAnswer.add(rdbAnswerA);
        bgAnswer.add(rdbAnswerB);
        bgAnswer.add(rdbAnswerC);
        bgAnswer.add(rdbAnswerD);


        pn10.add(rdbAnswerA);
        pn10.add(Box.createRigidArea(new Dimension(0,30)));
        pn10.add(rdbAnswerB);
        pn10.add(Box.createRigidArea(new Dimension(0,30)));
        pn10.add(rdbAnswerC);
        pn10.add(Box.createRigidArea(new Dimension(0,30)));
        pn10.add(rdbAnswerD);
        pn10.add(Box.createRigidArea(new Dimension(0,30)));
        pn10.setBorder(new EmptyBorder(0,0,80,0));



        pn12 = new JPanel();
        btnRefeshGame = new JButton("Refesh");
        btnCheckGame = new JButton("Answer");
        pn12.add(btnRefeshGame);
        pn12.add(Box.createRigidArea(new Dimension(50, 0)));
        pn12.add(btnCheckGame);

        pn11 = new JPanel();
        bl5 = new BoxLayout(pn11,BoxLayout.Y_AXIS);
        pn11.setLayout(bl5);

        pn11.add(pn9);
        pn11.add(Box.createRigidArea(new Dimension(0,30)));
        pn11.add(pn10);
        pn11.add(pn12);

        pn5.add(pn11);
        pnCont.add(pn5,"Game");

        //Table
        DefaultTableModel table_model = new DefaultTableModel(colHeader,0);
        jtbSlangWord = new JTable(table_model);
        jtbSlangWord.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SlangWord slangWord = new SlangWord(
                        jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),0).toString(),
                        jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),1).toString());
            }});
        jtbSlangWord.setDefaultEditor(Object.class, null);

        pn7.add(new JScrollPane(jtbSlangWord));

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
        } else if (str.equals("btnGame")) {
            rdbSlangGame.setSelected(true);
            cl.show(pnCont,"Game");
            ArrayList<String> listSlangword =  dictionary.randomSlangWordGame();

            lbGame.setText(listSlangword.get(0));
            
            Collections.shuffle(listSlangword);
            rdbAnswerA.setText(dictionary.getMap().get(listSlangword.get(0)));
            rdbAnswerB.setText(dictionary.getMap().get(listSlangword.get(1)));
            rdbAnswerC.setText(dictionary.getMap().get(listSlangword.get(2)));
            rdbAnswerD.setText(dictionary.getMap().get(listSlangword.get(3)));

        }
        else if (str.equals("btnPageRandom")) {
            cl.show(pnCont,"Random");
        }
        else if(str.equals("btnSearch"))
        {
            dictionary.findSlangWord(tfSearch.getText());
            fillTable();
        } else if (str.equals("btnSearchDefinition")) {
            dictionary.findDefinitionSlangWord(tfSearch.getText());
            fillTable();
        } else if (str.equals("btnAdd")) {
            AddFrm addFrm = new AddFrm();
            addFrm.setVisible(true);
            try {
                dictionary.Input();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this,"Lỗi");
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
                JOptionPane.showMessageDialog(this,"Vui lòng tìm kiếm và chọn từ cần chỉnh sửa",
                        "Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (str.equals("btnDelete")) {
            if(jtbSlangWord.getSelectionModel().isSelectionEmpty()==false)
            {
                if(dictionary.deleteSlangWord(new SlangWord(
                        jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),0).toString(),
                        jtbSlangWord.getValueAt(jtbSlangWord.getSelectedRow(),1).toString())))
                {
                    JOptionPane.showMessageDialog(this,"Xóa thành công");
                }
                fillTable();
            }else {
                JOptionPane.showMessageDialog(this,"Vui lòng tìm kiếm và chọn từ cần xóa",
                        "Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (str.equals("btnRandom")) {
            SlangWord slangWord = dictionary.randomSlangWord();
            lbSlang.setText(slangWord.getSlag());
            lbMean.setText(slangWord.getMean());
        }
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
