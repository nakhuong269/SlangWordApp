import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class EditFrm extends JDialog implements ActionListener {
    JPanel pn1, pn2;
    GridBagLayout gbl1;

    JLabel lb1, lb2;
    JTextField tfSlag, tfMean;

    BoxLayout bl1;
    JButton btnEdit, btnCancel;
    public EditFrm()
    {
        pn1 = new JPanel();
        gbl1 = new GridBagLayout();
        pn1.setLayout(gbl1);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        lb1 = new JLabel("Slang");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0,10,10,10);
        pn1.add(lb1,gridBagConstraints);

        Font font1 = new Font("Arial", Font.PLAIN, 14);
        tfSlag = new JTextField(18);
        tfSlag.setPreferredSize(new Dimension(80,30));
        tfSlag.setFont(font1);
        tfSlag.setEditable(false);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0,10,10,10);
        pn1.add(tfSlag,gridBagConstraints);

        lb2 = new JLabel("Mean");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0,10,10,10);
        pn1.add(lb2,gridBagConstraints);

        tfMean = new JTextField(18);
        tfMean.setPreferredSize(new Dimension(80,30));
        tfMean.setFont(font1);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0,10,10,10);
        pn1.add(tfMean,gridBagConstraints);


        pn2 = new JPanel();
        bl1 = new BoxLayout(pn2,BoxLayout.X_AXIS);
        pn2.setLayout(bl1);
        btnEdit = new JButton("Cập nhật");
        btnEdit.addActionListener(this);
        btnEdit.setActionCommand("btnEdit");


        btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(this);
        btnCancel.setActionCommand("btnCancel");
        pn2.add(btnEdit);
        pn2.add(Box.createRigidArea(new Dimension(80,0)));
        pn2.add(btnCancel);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(0,10,10,10);
        pn1.add(pn2,gridBagConstraints);



        add(pn1);
        setTitle("Cập nhật Slang");
        setMinimumSize(new Dimension(250,180));
        setModal(true);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if(str.equals("btnEdit"))
        {
            if(tfMean.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this,"Vui lòng nhập đủ thông tin","Thông báo",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                SlangWord slangWord = new SlangWord(tfSlag.getText().trim(), tfMean.getText().trim());


                if(Main.dictionary.editSlangWord(slangWord))
                {
                    JOptionPane.showMessageDialog(this,"Cập nhật thành công");
                }
                else {
                    JOptionPane.showMessageDialog(this,"Lỗi");
                }
            }
        }
        else if(str.equals("btnCancel"))
        {
            this.dispose();
        }
    }
}
