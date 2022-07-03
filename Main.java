import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Main extends JPanel implements ActionListener{
    JPanel pn1, pn2, pn3, pn4, pn5, pn6;

    JButton btnAdd, btnEdit, btnDelete, btnSearch, btnSearchDefinition, btnRandom, btnReset, btnHome, btnGame;
    JTextField tfSearch;
    BoxLayout bl1, bl2;

    JLabel lb22;
    BorderLayout bdl1;
    FlowLayout fl1;



    public static void main(String args[])
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public Main()
    {
        super(new BorderLayout());
        pn1 = new JPanel();
        bl1 = new BoxLayout(pn1,BoxLayout.Y_AXIS);
        pn1.setBorder(new EmptyBorder(0,10,10,10));
        pn1.setLayout(bl1);



        tfSearch = new JTextField();
        pn2 = new JPanel();
        bl2 = new BoxLayout(pn2, BoxLayout.X_AXIS);
        pn2.setLayout(bl2);
        pn2.setBorder(new EmptyBorder(0, 0,15,0));
        btnSearch = new JButton("Tìm kiếm chính xác");
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

        pn4 = new JPanel(new CardLayout());
        bdl1 = new BorderLayout();
        pn4.setLayout(bdl1);
        pn4.add(pn1,BorderLayout.PAGE_START);

        pn6 = new JPanel();
        btnHome = new JButton("Trang chủ");
        btnGame = new JButton("Đố vui");
        pn6.setBorder(new EmptyBorder(0,0,20,0));
        pn6.add(btnHome);
        pn6.add(btnGame);


        add(pn6,BorderLayout.PAGE_START);
        add(pn4, BorderLayout.CENTER);

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

    }

}
