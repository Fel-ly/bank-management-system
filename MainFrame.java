import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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

    }

    public static void main(String[] args) {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
