import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main extends JPanel implements ActionListener{
    JPanel pn1, pn2, pn3, pn4, pn5, pn6, pnCont , pn7, pn8  ;
    JButton btnAdd, btnEdit, btnDelete, btnSearch, btnSearchDefinition, btnPageRandom,  btnRandom, btnReset, btnHome, btnGame;
    JTextField tfSearch;
    BoxLayout bl1, bl2, bl3;

    JLabel lb1, lb2, lbSlang, lbMean;
    BorderLayout bdl1;
    FlowLayout fl1;
    CardLayout cl;
    String[] colHeader = { "Từ", "Nghĩa"};

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
        lbMean.setFont(new Font("Arial",Font.PLAIN, 28));
        lb1 = new JLabel("Từ: ");
        lb1.setAlignmentX(CENTER_ALIGNMENT);
        lb1.setFont(new Font("Arial",Font.PLAIN, 22));
        lb2 = new JLabel("Nghĩa: ");
        lb2.setAlignmentX(CENTER_ALIGNMENT);
        lb2.setFont(new Font("Arial",Font.PLAIN, 22));

        btnRandom = new JButton("Bắt đầu");
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
        pn5.setBackground(Color.BLACK);
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
            cl.show(pnCont,"Game");
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
