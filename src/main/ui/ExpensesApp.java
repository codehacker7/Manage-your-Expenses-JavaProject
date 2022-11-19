package ui;

import model.Customer;
import model.Expenses;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
This class is used to provide graphical user interface 
 */


public class ExpensesApp implements ActionListener {


    JFrame frame;
    JTextField textfield;
    JTextField paneltextfield;
    JTextField nametextfield;
    JTextField expenseTextField = new JTextField();
    JTextField priceTextField = new JTextField();
    JTextField expenseLimit = new JTextField();
    int convertedid = -1;
    Expenses ex = new Expenses();
    Customer customer;
    JButton loadbutton;
    JButton newuser;
    JButton submitbutton;
    JLabel label;
    JLabel lookexpenses;
    JPanel panel;
    Customer expensecustomer;
    JComboBox date;
    JComboBox month;
    JComboBox year;
    JComboBox category;
    boolean count = false;
    boolean expensecount = false;
    boolean expenselimitcount = false;
    boolean expenselimitcheck = false;
    JButton goBackButton;
    JButton saveGoBackButton;
    JButton expenselimitbutton;
    JButton secondButton;


    public static final String destination = "./data/expenses.json";


    //EFFECTS : IT is used to create an delay between the splashing screen and actual app
    public ExpensesApp() {

//        frame = new JFrame();
//        textfield = new JTextField();
//        paneltextfield = new JTextField(); //Do not reInitialize it
//        nametextfield = new JTextField(); //Do not reInitialize it
//        loadbutton = new JButton();
//        submitbutton = new JButton();
//        panel = new JPanel();
//        label = new JLabel();
//        newuser = new JButton();
//        lookexpenses = new JLabel();


        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        constructor();

                    }
                },
                4000
        );


        intial();


//        setFrame();

    }


    //EFFECTS : This method creates the very first beginning logo of the app
    public void intial() {
        frame = new JFrame();
        frame.setTitle("Expense Manager");
        frame.setResizable(false);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(null);

        ImageIcon imageIcon = newImageSetterforGUI();
        addImage2(imageIcon, 50, 50, 1000, 700);

        frame.setVisible(true);

    }

    //EFFECTS : This method sets the size and modifies and returns the expense-manager-logo.png image
    private ImageIcon newImageSetterforGUI() {
        ImageIcon imageIcon = new ImageIcon("expense-manager-logo.png");
        Image piggyImage = imageIcon.getImage();
        Image modifiedPiggyImage = piggyImage.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(modifiedPiggyImage);

        return imageIcon;

    }

    //EFFECTS : This method is used to display the image on the screen
    private void addImage2(ImageIcon imageIcon, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setIcon(imageIcon);
        frame.add(label);
    }

    //EFFECTS : This is the constructor which is used to set up a JFrame object,JTextFields,JPanel,JLabel,JButton
    public void constructor() {
        frame.dispose();
        frame = new JFrame();
        textfield = new JTextField();
        paneltextfield = new JTextField(); //Do not reInitialize it
        nametextfield = new JTextField(); //Do not reInitialize it
        loadbutton = new JButton();
        submitbutton = new JButton();
        panel = new JPanel();
        label = new JLabel();
        newuser = new JButton();
        lookexpenses = new JLabel();
        setFrame();
    }

    //EFFECTS : This is the constructor which is used to set up title,Color,Layout of the frame and the field Manage
    //your expenses that a user sees on the top
    public void setFrame() {
        textfield.setBackground(Color.decode("#add8e6"));
        textfield.setBounds(0, 0, 1000, 150);
        textfield.setOpaque(true);
        textfield.setFont(new Font("Arial", Font.ITALIC, 45));
        textfield.setText("Manage your Expenses");
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setForeground(Color.BLUE);
        textfield.setFocusable(false);
        textfield.setEditable(false);
        textfield.setLayout(null);

        frame.setTitle("Expense Manager");
        frame.setResizable(false);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setLayout(null);
        frame.add(textfield);
        setPanel();
    }

    //EFFECTS : This is the constructor which is used to reintialize refrences
    public void reIntialize() {
        frame.dispose();
        frame = new JFrame();
        textfield = new JTextField();
        loadbutton = new JButton();
        panel = new JPanel();
        label = new JLabel();
        newuser = new JButton();
        lookexpenses = new JLabel();
        expenseTextField = new JTextField();
        priceTextField = new JTextField();
        expenseLimit = new JTextField();
        setFrame1();
    }

    //EFFECTS : This is the constructor which is used to set up title,Color,Layout of the frame and the field Manage
    //your expenses that a user sees on the top
    public void setFrame1() {
        textfield.setBackground(Color.decode("#add8e6"));
        textfield.setBounds(0, 0, 1000, 150);
        textfield.setOpaque(true);
        textfield.setFont(new Font("Arial", Font.ITALIC, 45));
        textfield.setText("Manage your Expenses");
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setForeground(Color.BLUE);
        textfield.setFocusable(false);
        textfield.setEditable(false);
        textfield.setLayout(null);

        frame.setTitle("Expense Manager");
        frame.setResizable(false);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setLayout(null);
        frame.add(textfield);
    }

    //EFFECTS : This is the constructor which is used to make two textfields for taking the user id and name of the user
    //This method also makes some buttons
    public void setPanel() {
        panel.setBounds(340, 390, 300, 200);
        panel.setBackground(Color.decode("#add8e6"));

        label.setBounds(250, 510, 10, 10);
        label.setForeground(Color.black);
        label.setFont(new Font("MV BOLI", Font.PLAIN, 19));
        label.setText("Enter your name and id:   ");

        nametextfield = new JTextField();
        nametextfield.setBounds(415, 440, 150, 30);

        paneltextfield = new JTextField();
        paneltextfield.setBounds(415, 480, 150, 30);


        loadbutton = new JButton("Load Expenses!");
        loadbutton.setFocusable(false);
        loadbutton.setBackground(Color.YELLOW);
        loadbutton.setBounds(340, 540, 126, 30);
        loadbutton.setForeground(Color.BLACK);
        loadbutton.addActionListener(this);

        setSaveUser();

        frame.add(loadbutton);
        frame.add(paneltextfield);
        frame.add(nametextfield);
        frame.add(panel);
        panel.add(label);
        frame.setVisible(true);


    }

    //EFFECTS : This is the constructor which is used to set up the save button
    public void setSaveUser() {
        newuser = new JButton("Save Expenses! ");
        newuser.setFocusable(false);
        newuser.setBackground(Color.YELLOW);
        newuser.setBounds(490, 540, 126, 30);
        newuser.setForeground(Color.BLACK);
        newuser.addActionListener(this);

        frame.add(newuser);

        ImageIcon imageIcon = imageSetter("expensetrack2.png");
        ImageIcon imageIcon1 = imageSetter("welcome2.png");


        addImage(imageIcon1, 30, 150, 400, 330);
        addImage(imageIcon, 650, 510, 450, 330);


    }

    //EFFECTS : This method is used to display the image on the screen
    private void addImage(ImageIcon imageIcon, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        label.setBounds(x, y, width, height);
        label.setIcon(imageIcon);
        frame.add(label);


    }

    //EFFECTS : This method sets the size and modifies and returns the expense-manager-logo.png image
    public ImageIcon imageSetter(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image piggyImage = imageIcon.getImage();
        Image modifiedPiggyImage = piggyImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(modifiedPiggyImage);

        return imageIcon;

    }


    //EFFECTS : This method checks which button the user clicked on and perform a bunch of actions like loading expenses
    //if the user clicked on load button or if the user clicked on save button take the user to next screen
    @Override
    public void actionPerformed(ActionEvent e) {

        String customername = nametextfield.getText();
        String stringid = paneltextfield.getText();

        actionPerformedHelper(e, stringid, customername);
        reducemorelength(e, stringid, customername);

        if (e.getSource() == goBackButton) {
            reIntialize();
            setPanel();
        }

        if (e.getSource() == saveGoBackButton) {
            count = false;
            int realid = Integer.parseInt(stringid);
            expensecustomer = new Customer(customername, realid);
            ex = new Expenses();
            setExpensesLabel();


        }

        if (e.getSource() == expenselimitbutton) {
            count = false;
            int realid = Integer.parseInt(stringid);
            expensecustomer = new Customer(customername, realid);
            ex = new Expenses();
            setExpensesLabel();
        }

    }

    //EFFECTS : This is used to actually the length of the previous method while performing task of saving expenses
    private void reducemorelength(ActionEvent e, String stringid, String customername) {
        if (e.getSource() == secondButton) {
            setExpensesLabel();
            int realid = Integer.parseInt(stringid);
            expensecustomer = new Customer(customername, realid);
            convertedid = Integer.parseInt(stringid);
        }
    }

    //EFFECTS : If the user clickes on load button this method loads the expenses of the user
    //An IO Exception is generated if an error occurs while reading expenses of the user
    //GENERATES A NEW INTERFACE IF THE USER IS A NEW USER
    //IF THE USER IS A NEW INTERFACE AND THE USER ENTERS ALL THEIR EXPENSES DETAILS THAN ALL THE DATA SUCH
    //AS EXPENSE NAME, PRICE,DATE EXPENSE LIMIT(IF FILLED) AND CATEGORY IS EXTRACTED FROM TEXTFIELDS USING OPERATIONS
    //ON SUBMIT METHOD
    private void actionPerformedHelper(ActionEvent e, String stringid, String customername) {

        if (e.getSource() == loadbutton) {

            int id = Integer.parseInt(stringid);
            Customer user = new Customer(customername, id);
            try {
                readExpenses(user.getId());
                totalexpenses(user.getId());
                welcomecustomer(user.getId());
                showexpenselimit(user.getId());
            } catch (IOException c) {
                System.out.println("IO Exception was not expected");
            }
        }

        if (e.getSource() == newuser) {
            convertedid = Integer.parseInt(stringid);
            expensecustomer = new Customer(customername, convertedid);
            saveExpenses();


        }

        if (e.getSource() == submitbutton) {
            count = true;
            operationsOnSubmit();

        }


    }

    //EFFECTS : IF THE USER IS A NEW INTERFACE AND THE USER ENTERS ALL THEIR EXPENSES DETAILS THAN ALL THE DATA SUCH
    //AS EXPENSE NAME, PRICE,DATE EXPENSE LIMIT(IF FILLED) AND CATEGORY IS EXTRACTED FROM TEXTFIELDS USING OPERATIONS
    //ON SUBMIT METHOD
    private void operationsOnSubmit() {

        JTextField expenseTextField = setExpensesLabel();
        String expenseName = expenseTextField.getText();


        JTextField priceTextField = pricelabel();
        String priceString = priceTextField.getText();


        double price = Double.parseDouble(priceString);


        operationsHelper(expenseName, price);

    }

    //EFFECTS : This method is used to extract the full date from the JComboBox in the GUI
    private void operationsHelper(String expenseName, double price) {
        JComboBox datecombo = datelabel();
        String date = (String) datecombo.getSelectedItem();

        JComboBox monthcombo = setMonth();
        String month = (String) monthcombo.getSelectedItem();

        JComboBox yearcombo = setYear();
        String year = (String) yearcombo.getSelectedItem();

        String fullDateDetails = date + " " + month + " " + year;

        operationshelper1(expenseName, price, fullDateDetails);

    }

    //EFFECTS : This method is used to extract the category along with expenseLimit from the JComboBox
    //  and Textfield in the GUI. As there is an option to keep the expenseLimit Textfield as empty than we need to
    //do error handling as we are parsing it to double and empty string cannot be parsed to double
    //If everything goes well than expenses are added to the Expenses object but we also check that if the users
    //expense Limit is enough to add expenses if the expenses limit is not enough then User is asked to update
    //their expense limit
    private void operationshelper1(String expenseName, double price, String fullDateDetails) {


        JComboBox categorycombo = setCategory();
        String category = (String) categorycombo.getSelectedItem();

        JTextField expenseLimitTextField = expenseLimit();
        String expenseLimit = expenseLimitTextField.getText();

        try {
            double expense = Double.parseDouble(expenseLimit);
            ex.setExpenseLimit(expense);
            expenselimitcheck = true;
        } catch (Exception e) {
            System.out.println();
        }

        ex.addExpenses(expenseName, price, fullDateDetails, category);


        expensecustomer.addExpenses(ex);

        checkLimitMessage(ex, price, convertedid);


        if (!expensecount) {
            newSaveExpenses(expensecustomer, convertedid);
        }
        if (expenselimitcount) {
            newSaveExpenses(expensecustomer, convertedid);
        }


    }

    //EFFECTS : This method is used to read the data from json file and parse it to Customer object and then
    //it checks that if the id is not -1 that means that there is an user with the given id then reduce length method is
    //called
    private void checkLimitMessage(Expenses ex, double price, int id) {

        try {


            JsonReader reader = new JsonReader(destination);
            Customer c3 = reader.read(id);

            double expenselimit = 0;
            double totalamountspent = 0;

            if (c3.getId() != -1) {
                reducelength(c3, price);
            }
        } catch (IOException e) {
            System.out.println("Error IO Exception");
        }

    }


    //EFFECTS : This is an helper method which prints Sorry your expenselimit is not enough to add this expense.
    // Please update it if the expense limit of the user is not enough to add expenses
    //else it makes expenselimitcount = true and  expensecount = true which will help to call the Save expenses method
    //for the upper code
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void reducelength(Customer c3, double price) {
        double expenselimit = 0;
        double totalamountspent = 0;

        if (c3.getId() != -1) {

            for (Expenses c : c3.getreadCustomerExpense()) {
                expenselimit = c.getExpenseLimit();
                totalamountspent = c.getTotalAmountSpent();
            }

            if (expenselimitcheck) {
                expenselimit = ex.getExpenseLimit();
            }


            if (expenselimit < (totalamountspent + price)) {
                reIntialize();

                JLabel label = new JLabel();
                label.setBounds(10, 200, 1000, 150);
                label.setFont(new Font("Arial", Font.BOLD, 24));
                label.setForeground(Color.green);
                label.setText("Sorry your expenselimit is not enough to add this expense. Please update it");

                expensecount = true;
                expenselimitcount = false;
                expenseLimitmethod(expenselimit, price, totalamountspent);

                frame.add(label);
                frame.setVisible(true);

            } else if (expenselimit >= (totalamountspent + price)) {
                expenselimitcount = true;
                expensecount = true;
            }

        }
    }

    //EFFECTS : This is an GO Back button which is used to take user back to the HomeScreen
    private void expenseLimitmethod(double expenselimit, double price, double totalamountspent) {

        expenselimitbutton = new JButton("Go Back");
        expenselimitbutton.setBounds(300, 730, 300, 30);
        expenselimitbutton.setFont(new Font("Arial", Font.BOLD, 15));
        expenselimitbutton.setBackground(Color.pink);
        expenselimitbutton.addActionListener(this);
        frame.add(expenselimitbutton);
        frame.setVisible(true);


    }

    //EFFECTS : EFFECTS : This method is used to save the expenses of the user
    private void newSaveExpenses(Customer expensecustomer, int convertedid) {

        try {
            JsonReader reader = new JsonReader(destination);
            JSONObject c3 = reader.read(); // FULL JSON OBJECT
            JsonWriter writer1 = new JsonWriter(destination);
            writer1.open();
            writer1.write(c3, expensecustomer, convertedid);
            writer1.close();
            newSaveInterface(expensecustomer, convertedid);

        } catch (IOException c) {
            System.out.println("Error! IO Exception");
        }


    }

    //EFFECTS : This method is used to reIntialize all the refrences to new objects and print Thanks for
    //saving expenses with ID number : (ID number of the user)  and the task of this method is also to intialize the
    //Save Go Back Button
    private void newSaveInterface(Customer expensecustomer, int convertedid) {

        reIntialize();


        JLabel label = new JLabel();
        label.setBounds(60, 250, 1000, 100);
        label.setFont(new Font("Arial", Font.ITALIC, 30));
        label.setText("Thanks " + expensecustomer.getCustomername() + " for saving expenses "
                + "with ID number " + expensecustomer.getId());
        label.setForeground(Color.MAGENTA);

        JLabel newlabel = new JLabel();
        newlabel.setBounds(60, 350, 1000, 100);
        newlabel.setFont(new Font("Arial", Font.BOLD, 30));
        newlabel.setText("You can use this ID number for saving/loading expenses");
        newlabel.setForeground(Color.black);


        frame.add(newlabel);
        frame.add(label);
        frame.setVisible(true);

        saveGoBackButton = new JButton("Go Back");
        saveGoBackButton.setBounds(300, 730, 300, 30);
        saveGoBackButton.setFont(new Font("Arial", Font.BOLD, 15));
        saveGoBackButton.setBackground(Color.pink);
        saveGoBackButton.addActionListener(this);
        frame.add(saveGoBackButton);

        reducelength4();

        frame.setVisible(true);

    }

    //EFFECTS : This method is used to add the SavaImage  in the save expenses method
    private void reducelength4() {
        ImageIcon imageIcon = imageSetter("saveimage1.png");
        addImage(imageIcon, 300, 490, 400, 200);


    }

    //EFFECTS : This method is used to mention Sorry no details found with your given id if the id does not exist
    //in the system
    public void welcomecustomer(int id) throws IOException {

        JsonReader reader = new JsonReader(destination);
        customer = reader.read(id);


        JLabel welcomelabel = new JLabel();
        welcomelabel.setBounds(60, 160, 500, 100);
        welcomelabel.setBackground(Color.yellow);
        welcomelabel.setForeground(Color.yellow);
        welcomelabel.setFont(new Font("Arial", Font.ITALIC, 30));

        if (customer.getCustomername().equals("No details found")) {
            welcomelabel.setBounds(60, 160, 900, 100);
            welcomelabel.setForeground(Color.red);
            welcomelabel.setFont(new Font("Arial", Font.ITALIC, 40));

            welcomelabel.setText("Sorry no details found with your given id ");
            ImageIcon imageIcon = imageSetteru("nothingfound.png");
            addImage(imageIcon, 780, 10, 200, 480);
        } else {

            welcomelabel.setText("Welcome Back! : " + customer.getCustomername());
        }


        frame.add(welcomelabel);
        frame.setVisible(true);


    }

    //EFFECTS : This method sets the size of the image so that it can sit perfectly in the frame
    public ImageIcon imageSetteru(String path) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image piggyImage = imageIcon.getImage();
        Image modifiedPiggyImage = piggyImage.getScaledInstance(200, 260, Image.SCALE_AREA_AVERAGING);
        imageIcon = new ImageIcon(modifiedPiggyImage);

        return imageIcon;

    }

    //EFFECTS : This method sets the size and modifies and returns the expense-manager-logo.png image
    public void readExpenses(int id) throws IOException {
        JsonReader reader = new JsonReader(destination);
        customer = reader.read(id);
        reIntialize();

        JTextArea textarea = new JTextArea(5, 20);
        textarea.setBackground(Color.blue);
        textarea.setForeground(Color.decode("#FF69B4"));

        textarea.setBounds(80, 200, 900, 300);
        textarea.setFont(new Font("Arial", Font.ITALIC, 15));
        JScrollPane scrollPane1 = new JScrollPane(textarea);


        for (Expenses exp : customer.getreadCustomerExpense()) {


            for (int j = 0; j < exp.getExpenseList().size(); j += 3) {

                textarea.append("You purchased  " + exp.getExpenseList().get(j)
                        + " with price being  $ " + exp.getExpenseList().get(j + 1)
                        + " and the purchase was made on : " + exp.getExpenseList().get(j + 2)
                        + " which belongs to : " + exp.getCategory().get(j / 3) + "\n\n");
            }
        }

        scrollPane1.setBounds(60, 350, 900, 249);
        scrollPane1.setBorder(BorderFactory.createEmptyBorder());
        scrollPane1.setViewportBorder(null);
        textarea.setEditable(false);

        frame.add(scrollPane1);
        frame.setVisible(true);

    }


    //EFFECTS : This method is used to display the total expenses of a particular user
    public void totalexpenses(int id) throws IOException {

        JsonReader reader = new JsonReader(destination);
        customer = reader.read(id);

        JLabel expenselabel = new JLabel();
        expenselabel.setBounds(60, 240, 500, 100);

        double expenses = 0;

        for (Expenses exp : customer.getreadCustomerExpense()) {
            expenses = exp.getTotalAmountSpent();

        }

        expenselabel.setBackground(Color.yellow);
        expenselabel.setForeground(Color.yellow);
        expenselabel.setFont(new Font("Arial", Font.ITALIC, 30));
        expenselabel.setText("Total amount spent is  $" + expenses);

        frame.add(expenselabel);
        frame.setVisible(true);

    }

    //EFFECTS : This method is used to display Your expense limit is and Available limit is in the load interface
    public void showexpenselimit(int id) throws IOException {
        JsonReader reader = new JsonReader(destination);
        Customer c = reader.read(id);

        JLabel expenselimitlabel = new JLabel();
        expenselimitlabel.setBounds(0, 530, 1000, 200);

        double expenselimit = 0;
        double totalamountspent = 0;

        for (Expenses exp : c.getreadCustomerExpense()) {
            expenselimit = exp.getExpenseLimit();
            totalamountspent = exp.getTotalAmountSpent();
        }

        expenselimitlabel.setBackground(Color.pink);
        expenselimitlabel.setForeground(Color.green);
        expenselimitlabel.setFont(new Font("Arial", Font.ITALIC, 30));
        expenselimitlabel.setText("Your Expense limit is :  " + expenselimit + ", "
                + "Available limit is : " + (expenselimit - totalamountspent));

        goBackmethod();
        frame.add(expenselimitlabel);
        frame.setVisible(true);

    }

    //EFFECTS : This method is used to set the GO BACK BUTTON
    private void goBackmethod() {

        goBackButton = new JButton("Go Back!");
        goBackButton.setBounds(200, 730, 250, 30);
        goBackButton.setFont(new Font("Arial", Font.BOLD, 15));
        goBackButton.setBackground(Color.pink);
        goBackButton.addActionListener(this);
        frame.add(goBackButton);
        frame.setVisible(true);

        secondButtonmethod();

    }

    //EFFECTS : This method is used to set the ADD EXPENSES BUTTON IN THE FRAME
    private void secondButtonmethod() {
        secondButton = new JButton("Add Expenses!");
        secondButton.setBounds(500, 730, 300, 30);
        secondButton.setFont(new Font("Arial", Font.BOLD, 15));
        secondButton.setBackground(Color.pink);
        secondButton.addActionListener(this);
        frame.add(secondButton);
        frame.setVisible(true);

    }


    //EFFECTS : This method is used to call expenses Label method which in turn causes Expenses : label to form
    public void saveExpenses() {

        try {
            JsonReader reader = new JsonReader(destination);
            JSONObject c3 = reader.read();
            setExpensesLabel();
        } catch (IOException c) {
            System.out.println("IO Exception is not expected");
        }


    }

    //EFFECTS : This method is used to set expenses Label method. Another point is from now on I have used a bunch of
    //count in my code this is because if the user clicks on submit button then our task is to extract all the data
    //from textfields but if we do not have a count which makes if condition false then all the refrences will be
    //reintialized and all the data will be lost so we have a count
    public JTextField setExpensesLabel() {


        if (!count) {

            reIntialize();

            JLabel expenselabel = new JLabel();
            expenselabel.setBounds(30, 250, 300, 30);
            expenselabel.setFont(new Font("MV BOLI", Font.BOLD, 23));
            expenselabel.setForeground(Color.pink);
            expenselabel.setText("Expenses: ");

            expenseTextField.setBounds(150, 250, 300, 30);

            pricelabel();
            frame.add(expenseTextField);
            frame.add(expenselabel);
            frame.setVisible(true);

            return expenseTextField;
        }


        return expenseTextField;


    }

    //EFFECTS : This method is used to set the price label of the method
    private JTextField pricelabel() {

        if (!count) {

            JLabel pricelabel = new JLabel();
            pricelabel.setBounds(470, 250, 300, 30);
            pricelabel.setFont(new Font("MV BOLI", Font.BOLD, 23));
            pricelabel.setForeground(Color.pink);
            pricelabel.setText("Price : ");

            priceTextField.setBounds(560, 250, 300, 30);

            datelabel();
            frame.add(priceTextField);
            frame.add(pricelabel);
            frame.setVisible(true);

            return priceTextField;

        }

        return priceTextField;


    }

    //EFFECTS : This method is used to set the date field in JCOMBOBOX
    private JComboBox datelabel() {


        if (!count) {

            JLabel datelabel = new JLabel();

            datelabel.setBounds(470, 320, 300, 30);
            datelabel.setFont(new Font("MV BOLI", Font.BOLD, 23));
            datelabel.setForeground(Color.pink);
            datelabel.setText("Date: ");

            String[] dates = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                    "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

            date = new JComboBox(dates);
            date.setFont(new Font("Arial", Font.PLAIN, 15));
            date.setSize(100, 30);
            date.setLocation(540, 325);

            setMonth();
            setYear();
            setCategory();
            expenseLimit();

            frame.add(date);
            frame.add(datelabel);
            frame.setVisible(true);


            return date;
        }

        return date;


    }

    //EFFECTS : This method is used to set the CATEGORY field in JCOMBOBOX
    private JComboBox setCategory() {


        if (!count) {

            String[] categoryarray = {"Grocery", "Education", "Entertainment", "Eatout", "Other"};

            JLabel categorylabel = new JLabel();
            categorylabel.setBounds(30, 320, 300, 30);
            categorylabel.setFont(new Font("MV BOLI", Font.BOLD, 23));
            categorylabel.setForeground(Color.pink);
            categorylabel.setText("ExpenseCategory: ");

            category = new JComboBox(categoryarray);
            category.setFont(new Font("Arial", Font.PLAIN, 15));
            category.setSize(200, 30);
            category.setLocation(240, 325);

            frame.add(category);
            frame.add(categorylabel);
            frame.setVisible(true);

            return category;
        }

        return category;


    }

    //EFFECTS : This method is used to set the expenselimit label and textfield
    private JTextField expenseLimit() {

        if (!count) {

            JLabel expenselabel = new JLabel();
            expenselabel.setBounds(30, 390, 300, 30);
            expenselabel.setFont(new Font("MV BOLI", Font.BOLD, 23));
            expenselabel.setForeground(Color.pink);
            expenselabel.setText("ExpenseLimit: ");

            expenseLimit.setBounds(200, 390, 200, 30);

            expenseLimitMessage();

            frame.add(expenseLimit);
            frame.add(expenselabel);
            frame.setVisible(true);

            return expenseLimit;
        }
        return expenseLimit;

    }

    //EFFECTS : This method is used to set the line Note - By default users are assingned an expense limit of $2000
    private void expenseLimitMessage() {
        JLabel expenselabel = new JLabel();
        expenselabel.setBounds(0, 540, 1000, 30);
        expenselabel.setFont(new Font("MV BOLI", Font.BOLD, 23));
        expenselabel.setForeground(Color.GREEN);
        expenselabel.setText("Note - By default users are assingned an expense limit of $2000 ");

        frame.add(expenselabel);
        frame.setVisible(true);


    }

    //EFFECTS : This method is used to set the month in JComboBox
    private JComboBox setMonth() {

        if (!count) {

            String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};

            month = new JComboBox(months);
            month.setFont(new Font("Arial", Font.PLAIN, 15));
            month.setSize(100, 30);
            month.setLocation(640, 325);

            month.setFont(new Font("Arial", Font.PLAIN, 15));
            month.setSize(100, 30);
            month.setLocation(640, 325);

            frame.add(month);
            frame.setVisible(true);

            return month;
        }
        return month;


    }

    //EFFECTS : This method is used to set the year in JComboBox
    private JComboBox setYear() {

        if (!count) {
            String[] years = {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032",
                    "2033", "2034", "2035",
                    "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044"};

            year = new JComboBox(years);
            year.setFont(new Font("Arial", Font.PLAIN, 15));
            year.setSize(100, 30);
            year.setLocation(740, 325);
            frame.add(year);
            frame.setVisible(true);
            submit();
            return year;
        }

        return year;

    }

    //EFFECTS: This method is used to put an piggy bank image on the save interface along with setting up the
    //submitbutton
    private void submit() {
        submitbutton.setBounds(390, 450, 100, 30);
        submitbutton.setText("Submit! ");
        submitbutton.setFont(new Font("MV BOLI", Font.ITALIC, 15));
        submitbutton.addActionListener(this);
        frame.add(submitbutton);


        ImageIcon imageIcon = imageSetter("piggyimage 2.png");
        addImage(imageIcon, 340, 530, 450, 300);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        ExpensesApp app = new ExpensesApp();
    }


}