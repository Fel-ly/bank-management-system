import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MainFrame extends JFrame {
    JLabel accNoLBL, ownerLBL, balanceLBL, cityLBL, genderLBL, amountLBL;
    JTextField accNoTXT, ownerTXT, balanceTXT, amountTXT;
    JComboBox<City> citiesCMB;

    JButton newBTN, saveBTN, showBTN, quitBTN, depositBTN, withdrawBTN;
    JRadioButton maleRDB, femaleRDB;
    ButtonGroup genderBTNGRP;

    JList<Account> accountLST;
    JPanel p1, p2, p3, p4, p5;

    Set<Account> accountset = new TreeSet<>();
    Account acc, x;
    boolean newRecord = true;

    //default Combobox data
    DefaultComboBoxModel<City> citiesCMBMDL;
    DefaultListModel<Account> accountsLSTMDL;

    //Table data
    JTable table;
    DefaultTableModel tableModel;
    ArrayList<Transaction> transList = new ArrayList<>();

    public MainFrame() {
        super("Account Operations");
        setLayout(null);
        setSize(600,400);

        //adding components to the frame
        //add labels
        accNoLBL = new JLabel("Account No.");
        ownerLBL = new JLabel("Owner");
        balanceLBL = new JLabel("Balance");
        cityLBL = new JLabel("City");
        genderLBL = new JLabel("Gender");
        amountLBL = new JLabel("Amount");

        // add textfields
        accNoTXT = new JTextField(); accNoTXT.setEnabled(false);
        ownerTXT = new JTextField();
        balanceTXT = new JTextField(); balanceTXT.setEnabled(false);
        amountTXT = new JTextField();
        amountTXT.setPreferredSize(new Dimension(150,25));

        //add Combobox
        citiesCMBMDL = new DefaultComboBoxModel<>();
        citiesCMBMDL.addElement(null);
        citiesCMBMDL.addElement(new City("Nairobi","Kilimani"));
        citiesCMBMDL.addElement(new City("Kisumu","Oyugis"));
        citiesCMBMDL.addElement(new City("Nakuru","Naivasha"));
        citiesCMBMDL.addElement(new City("Nairobi","Dagoretti"));
        citiesCMB = new JComboBox<City>(citiesCMBMDL);

        //Radio buttons
        maleRDB = new JRadioButton("Male", true);
        femaleRDB = new JRadioButton("Female");
        genderBTNGRP = new ButtonGroup();
        genderBTNGRP.add(maleRDB);
        genderBTNGRP.add(femaleRDB);

        //add buttons
        newBTN = new JButton("New");
        saveBTN = new JButton("Save");
        showBTN = new JButton("Show");
        quitBTN = new JButton("Quit");
        depositBTN = new JButton("Deposit");
        withdrawBTN = new JButton("Withdraw");

        //table
        accountsLSTMDL = new DefaultListModel<>();
        accountLST = new JList<>(accountsLSTMDL);

        //Panels
        p1 = new JPanel(); p1.setBounds(5,5,300,150);
        p1.setLayout(new GridLayout(5,2));

        p2 = new JPanel(); p2.setBounds(5,155,300,40);
        p2.setLayout(new FlowLayout());

        p3 = new JPanel(); p3.setBounds(5,195,600,40);
        p3.setLayout(new FlowLayout());

        p4 = new JPanel(); p4.setBounds(305,5,300,190);
        p4.setLayout(new BorderLayout());

        p5 = new JPanel(); p5.setBounds(5,240,580,120);
        p5.setLayout(new BorderLayout());

        //add components to panel
        p1.add(accNoLBL);
        p1.add(accNoTXT);
        p1.add(ownerLBL);
        p1.add(ownerTXT);
        p1.add(balanceLBL);
        p1.add(balanceTXT);
        p1.add(cityLBL);
        p1.add(citiesCMB);
        p1.add(maleRDB);
        p1.add(femaleRDB);

        p2.add(newBTN);
        p2.add(saveBTN);
        p2.add(showBTN);
        p2.add(quitBTN);

        p3.add(amountLBL);
        p3.add(amountTXT);
        p3.add(depositBTN);
        p3.add(withdrawBTN);

        p4.add(accountLST);


        //add panels to the frame
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);

        //creating table
        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);
        tableModel.addColumn("Transaction No");
        tableModel.addColumn("Transaction Date");
        tableModel.addColumn("Transaction Type");
        tableModel.addColumn("Transaction Amount");

        JScrollPane scrollpane = new JScrollPane(table);
        p5.add(scrollpane);

        // adding functionality of the buttons
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accNoTXT.setText("");
                ownerLBL.setText("");
                citiesCMB.setSelectedIndex(0);
                maleRDB.setSelected(true);
                balanceTXT.setText("");
                amountTXT.setText("");
                newRecord = true;
            }
        });

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newRecord) {
                            // Insertion of a new record
                    if  (ownerTXT.getText().length() != 0) {
                        acc = new Account(
                                ownerTXT.getText(),
                                (City) citiesCMB.getSelectedItem(),
                                maleRDB.isSelected()? 'M' : 'F'
                        );
                        accNoTXT.setText(String.valueOf(acc.accountNo));
                        accountset.add(acc);
                        accountsLSTMDL.addElement(acc);
                        newRecord = false;
                    } else {
                        JOptionPane.showMessageDialog(null,"Please fill all fields to proceed");
                    }
                }
                else {
                            // updating an existing record
                    accountset.remove(acc);

                    int a = Integer.parseInt(accNoTXT.getText());
                    String o = ownerTXT.getText();
                    City c = (City) citiesCMB.getSelectedItem();

                    char g  = maleRDB.isSelected()? 'M' : 'F';
                    double b = Double.parseDouble(balanceTXT.getText());
                    acc = new Account(a,o,c,g,b);
                    accountset.add(acc);
                    accountsLSTMDL.setElementAt(acc, accountLST.getSelectedIndex());
                    newRecord = false;
                }
            }
        });
    }

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
