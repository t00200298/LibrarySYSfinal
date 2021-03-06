package LibrarySystem.Library;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/***************
 * Code from past student dj?? the Restaurant Project that was shown in problem solving class
 * Title Menu lines 694- 701 and line 39
 * Author: Cormac O'Shea
 * Owner: Past student Dj
 * Date:
 * Code version:
 */

public class LibraryApp extends JFrame implements ActionListener {
    private static ArrayList<LoanedBook> allLoaned;
    private JMenu bookMenu, studentMenu, issueMenu;
    private JLabel label1, heading;
    private ImageIcon image1;
    private JPanel main;
    private JButton studentButton, bookButton, overdueButton;
    ArrayList<Student> allStudents;
    ArrayList<Book> allBooks;
    private Student FileOutputStream;
    private TitledBorder titledBorder;



    public LibraryApp() {

        JFrame f = new JFrame("Library Application");
        f.setIconImage(new ImageIcon(getClass().getResource("book.png")).getImage());
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setSize(325,375);

        createStudentMenu();
        createBookMenu();
        createIssueMenu();


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.setBackground(Color.PINK);
        menuBar.add(this.studentMenu);
        menuBar.add(this.bookMenu);
        menuBar.add(this.issueMenu);


        JLabel l = new JLabel("Welcome to the Library System!");
        l.setFont(new Font("Sans Serif", Font.BOLD,20));
        l.setForeground(Color.BLUE);
        JPanel P = new JPanel();
        P.add(menuBar);
        P.add(l);

        JLabel label1 = new JLabel();
        label1.setIcon(new ImageIcon(getClass().getResource("owl2.png")));
        P.add(label1);
        P.add(Box.createVerticalStrut(20));


        //Links.setLayout(new GridLayout(1, 3));
        this.studentButton = new JButton("Add a Student");
        this.studentButton.addActionListener(this);
        P.add(this.studentButton);
        this.bookButton = new JButton("Add a Book");
        this.bookButton.addActionListener(this);
        P.add(this.bookButton);
        this.overdueButton = new JButton("View Overdue");
        this.overdueButton.addActionListener(this);
        P.add(this.overdueButton);      
        f.getContentPane().add(P);




        f.setVisible(true);

/*

*/
    }




    public static void main(String[] args) {

       LibraryApp app = new LibraryApp();





    }




    private void createStudentMenu() {
        JMenuItem item;

        this.studentMenu = new JMenu("Students");

        item = new JMenuItem("Manage students");
        item.addActionListener(this);

        this.studentMenu.add(item);


        studentMenu.addSeparator();//adds a horizontal separator line

        item = new JMenuItem("Save");
        item.addActionListener(this);

        this.studentMenu.add(item);

        item = new JMenuItem("Quit");
        item.addActionListener(this);

        this.studentMenu.add(item);


    }

    private void createBookMenu() {
        JMenuItem item;

        bookMenu = new JMenu("Books");

        item = new JMenuItem("Manage");
        item.addActionListener(this);

        bookMenu.add(item);
    }

    private void createIssueMenu() {
        JMenuItem item;

        issueMenu = new JMenu("Loaned Books");

        item = new JMenuItem("Issued Books");
        item.addActionListener(this);

        issueMenu.add(item);

    }


    public void manageStudents() throws IOException {


        Student s1 = new Student("t00200298", "Cormac O'Shea", "Computing with Software Development");
        Student s2 = new Student("t00110234", "Jurgen Klopp", "Sports with Leisure");
        Student s3 = new Student("t02132354", "Mo Salah", "Sports with Leisure");


        ArrayList<Student> allStudents = new ArrayList<Student>(Arrays.asList(s1, s2, s3));

        String choice;

        do {
            choice = JOptionPane.showInputDialog("1. Add a Student\n2. View Students\n3. Save changes\n4.Quit\n\n");

            int choiceAsInt = Integer.parseInt(choice);

            while (choiceAsInt < 1 || choiceAsInt > 5) {
                choice = JOptionPane.showInputDialog("1. Add a Student\n2. View Students\n3. Save changes\n4. Quit\n\nInvalid choice entered!! Must be between 1 and 3 inclusive");

                choiceAsInt = Integer.parseInt(choice);


            }

            switch (choice) {
                case "1":
                    addStudent(allStudents);
                    break;

                case "2":
                    viewStudents(allStudents);
                    break;

                case "3":
                    save();
                    break;

            }
        } while (!choice.equals("4"));
        JOptionPane.showMessageDialog(null, "Taking you back to the menu!",
                "Redirect", JOptionPane.INFORMATION_MESSAGE);
        dispose();





    }


    public static void addStudent(ArrayList<Student> allStudents) {
        String Tnumber;
        Tnumber = JOptionPane.showInputDialog("Please enter the t-number of the student");


            if (Tnumber.length() == 9) {

                if (Tnumber.charAt(0) == 't' || Tnumber.charAt(0) == 'T' && Character.isDigit(Tnumber.charAt(1)) && Character.isDigit(Tnumber.charAt(2)) && Character.isDigit(Tnumber.charAt(3))
                        && Character.isDigit(Tnumber.charAt(4)) && Character.isDigit(Tnumber.charAt(5)) && Character.isDigit(Tnumber.charAt(6)) && Character.isDigit(Tnumber.charAt(7))
                        && Character.isDigit(Tnumber.charAt(8))) {
                    JOptionPane.showMessageDialog(null, "Valid t-number entered", "Valid t-number",
                            JOptionPane.INFORMATION_MESSAGE);




                } else {
                    Tnumber = JOptionPane.showInputDialog("Invalid t-number must begin with a 't' and finish with numbers, re-enter");
                }

            } else {
                Tnumber = JOptionPane.showInputDialog("Invalid!! t-number must be 9 characters long, re-enter");
            }




        String name = JOptionPane.showInputDialog("Please enter the name of the Student");

        int i;
        for (i = 0; i < name.length() && (Character.isLetter(name.charAt(i)) || name.charAt(i) == ' ' || name.charAt(i) == '\''); ++i) {
            if (i == name.length()) {

                String course = JOptionPane.showInputDialog("Please enter the course of the Student");
                if (course.length()!=0) {


                    Student s = new Student(Tnumber, name, course);

                    allStudents.add(s);
                    JOptionPane.showMessageDialog(null, "Student now created and added to the system!",
                            "Student added", JOptionPane.INFORMATION_MESSAGE);


                }else{
                    course = JOptionPane.showInputDialog("Invalid! You must enter a course: ");
                }
            }else {
                name = JOptionPane.showInputDialog("Invalid name, Please re-enter the student name: ");
            }

        } 


    }




        private static void viewStudents (ArrayList < Student > allStudents) {
            String allStudentData = "";
            Student st;

            Iterator<Student> iterator = allStudents.iterator();

            while (iterator.hasNext()) {
                st = iterator.next();
                if (st != null)
                    allStudentData += st + "\n";

            }

            JOptionPane.showMessageDialog(null, allStudentData, "List of all Students",
                    JOptionPane.INFORMATION_MESSAGE);

    }

    private void save() throws IOException {
        try{
            ObjectOutputStream st = new ObjectOutputStream(new FileOutputStream("manageStudents.dat"));
            st.writeObject(allStudents);
            st.close();


            JOptionPane.showMessageDialog((Component)null,"Data saved successfully",
                    "Saved",1);
        }catch (IOException var4) {
            JOptionPane.showMessageDialog((Component)null,"Save Unsuccessful",
                    "Unsuccessful",2);
            var4.printStackTrace();


        }


       // ObjectOutputStream bk = new ObjectOutputStream(new FileOutputStream("ManageBooks.dat"));
       //// bk.writeObject(this.books);
       // bk.close();


    }



    public void manageBook(){

        Book b1 = new Book("Harry Potter and the Goblet of Fire","J.K Rowling",654,6);
        Book b2 = new Book("Harry Potter and the Philosopher's Stone","J.K Rowling",345,3);
        Book b3 = new Book("Harry Potter and the Prisoner of Azkaban","J.K Rowling",456,2);

        ArrayList<Book> allBooks = new ArrayList<Book>(Arrays.asList(b1,b2,b3));

        String choice;

        do {
            choice = JOptionPane.showInputDialog("1. Add a Book\n2.Amend a Book\n3. Remove a Book" +
                    "\n4. View Books\n5. Quit\n\nPlease enter your choice");

            int choiceAsInt = Integer.parseInt(choice);

            while (choiceAsInt<1 || choiceAsInt >5){

                choice = JOptionPane.showInputDialog("1. Add a Book\n2. Amend a Book\n3. Remove a Book" +
                        "\n4. View Books\n5. Quit\n\nInvalid choice entered!! Must be between 1 and 5 inclusive");

                choiceAsInt = Integer.parseInt(choice);

            }

            switch (choice){
                case "1":
                    addBook(allBooks);
                    break;

                case "2":
                    amendBook(allBooks);
                    break;

                case "3":
                    removeBook(allBooks);
                    break;

                case "4":
                    viewBooks(allBooks);
            }


        }while (!choice.equals("5"));

        JOptionPane.showMessageDialog(null,"Thanks for using the system!",
                "Farewell", JOptionPane.INFORMATION_MESSAGE);


        System.exit(0);


    }

    public static void addBook(ArrayList<Book> allBooks) {
        String title = JOptionPane.showInputDialog("Please enter the title of the Book");
        String author = JOptionPane.showInputDialog("Please enter the author of the Book");
        int pages = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of pages of the Book"));
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of copies of the Book"));

        Book b = new Book(title,author,pages,quantity);

        allBooks.add(b);
        JOptionPane.showMessageDialog(null,"Book now created and added to the system",
                "Book Added",JOptionPane.INFORMATION_MESSAGE);

    }


    private static void amendBook(ArrayList<Book> allBooks){
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        String searchKey = JOptionPane.showInputDialog("Please enter the title of the Book you wish to amend");

        for (Book bk: allBooks)
            if (bk.getTitle().toLowerCase().contains(searchKey.toLowerCase()))
                foundBooks.add(bk);

        String text="";

        for (Book bk: foundBooks)
            if (bk !=null){
                text +=bk + "\n";
            }
        int searchID = Integer.parseInt(JOptionPane.showInputDialog("The following books matched your search phrase\n\n" + text +
                "\n\nEnter the id of the one you want to amend"));

            Book bookToAmend = null;

            for (Book bk : allBooks)
                if (bk != null && bk.getId()==searchID)
                    bookToAmend = bk;

            String amendChoice = JOptionPane.showInputDialog("The details of the product you wish to amend are:\n\n " +
                    bookToAmend + "\n\n1. Amend Title\n2. Amend Author\n3. Amend pages " +
                    "\n4. Amend quantity\n5. Cancel Amendment\n\nPlease enter your choice");

                    int amendChoiceAsInt = Integer.parseInt(amendChoice);

                while (amendChoiceAsInt<1 || amendChoiceAsInt>5){
                    amendChoice = JOptionPane.showInputDialog("The details of the book you wish to amend are:\n\n" +
                           bookToAmend + "\n\n1. Amend Title\n2. Amend Author\n3. Amend pages" +
                            "\n4. Amend quantity\n5. Cancel Amendment\n\nInvalid choice entered!! Must be between 1 and 5 inclusive");

                    amendChoiceAsInt = Integer.parseInt(amendChoice);
                }

                switch (amendChoice){
                    case "1":
                        String newtitle =JOptionPane.showInputDialog("Please enter the new title for the book");

                        bookToAmend.setTitle(newtitle);

                        break;

                    case "2":
                        String newauthor = JOptionPane.showInputDialog("Please enter the new author for the book");

                        bookToAmend.setAuthor(newauthor);

                        break;

                    case "3":
                        int newpages = Integer.parseInt(JOptionPane.showInputDialog("Please enter the new number of pages for the book"));

                        bookToAmend.setPages(newpages);

                        break;

                    case "4":
                        int newquantity = Integer.parseInt(JOptionPane.showInputDialog("Please enter the new quantity of that particular book"));

                        bookToAmend.setQuantity(newquantity);

                        break;


                }

                JOptionPane.showMessageDialog(null, "Book details now amended!",
                        "Book Amended",JOptionPane.INFORMATION_MESSAGE);

    }

    private static void removeBook(ArrayList<Book> allBooks) {
        ArrayList<Book> foundBooks = new ArrayList<Book>();
        String searchKey = JOptionPane.showInputDialog("Please enter the book you wish to remove");


        for (Book bk: allBooks)
            if (bk.getTitle().toLowerCase().contains(searchKey.toLowerCase()))
                foundBooks.add(bk);

        String text="";

        for (Book bk: foundBooks)
            if (bk != null){
                text+= bk + "\n";
            }

        int searchID = Integer.parseInt(JOptionPane.showInputDialog("The following books matched your search phrase\n\n" + text +
                "\n\nPlease enter the id of the one you want to remove"));

        Book bookToRemove=null;

        for (Book bk: allBooks)
            if (bk !=null && bk.getId()==searchID)
                bookToRemove = bk;

        int removeChoice = JOptionPane.showConfirmDialog(null,"The details of the book you wish to remove are\n\n" +
                bookToRemove + "\n\nAre you sure you wish to remove this book?","Book Removal Confirmation",JOptionPane.YES_NO_CANCEL_OPTION);

        if (removeChoice==JOptionPane.YES_OPTION){
            allBooks.remove(bookToRemove);
            JOptionPane.showMessageDialog(null,"Book now removed from the system!",
                    "Book Removed",JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null,"Book removal cancelled",
                    "Book Not Removed",JOptionPane.INFORMATION_MESSAGE);

        foundBooks.clear();

    }

    private static void viewBooks(ArrayList<Book> allBooks) {
        String allBookData="";
        Book bk;

        Iterator<Book> iterator = allBooks.iterator();

        while (iterator.hasNext()){
            bk = iterator.next();
            if (bk !=null)
                allBookData += bk + "\n";
        }

        JOptionPane.showMessageDialog(null,allBookData,
                "List of all Books",JOptionPane.INFORMATION_MESSAGE);
    }

    public void manageIssues(ArrayList<Book> allBooks, ArrayList<Student> allStudents){
      LoanedBook l1 = new LoanedBook(new Student("t00200298","Cormac O'Shea","Computing with Software Development"),
              new Book("Harry Potter and the philosopher's Stone","j.k rowling",
                      456,1),new GregorianCalendar(2020,12,1), "n",0);

      ArrayList<LoanedBook> allLoaned = new ArrayList<LoanedBook>(Arrays.asList(l1));

        String choice;

        do {
            choice = JOptionPane.showInputDialog("1. issue a book\n2.View Issued books\n3. View over due" +
                    "\n4. Quit\n\nPlease enter your choice");

            int choiceAsInt = Integer.parseInt(choice);

            while (choiceAsInt<1 || choiceAsInt >5){

                choice = JOptionPane.showInputDialog("1. issue a book\\n2.View Issued books\\n3. View over due"  +
                                           "\n4. Quit\n\nInvalid choice entered!! Must be between 1 and 5 inclusive");

                choiceAsInt = Integer.parseInt(choice);

            }

            switch (choice){
                case "1":
                    issueBook(allLoaned);
                    break;

                case "2":
                    viewIssuedBooks(allLoaned);
                    break;

                case "3":
                    viewOverdues(allLoaned);
                    break;

            }


        }while (!choice.equals("4"));

        JOptionPane.showMessageDialog(null,"Bringing you back to the menu!",
                "Redirect", JOptionPane.INFORMATION_MESSAGE);


        this.dispose();

    }

    public void issueBook(ArrayList<LoanedBook> allLoaned ){
        LibraryApp.allLoaned = allLoaned;
        String name, title, startDate, returned;

         name = JOptionPane.showInputDialog("Please enter the name of the name of the student");
        boolean valid = false;
        while (!valid) {
             title = JOptionPane.showInputDialog("Please enter the title of the book");
             startDate = JOptionPane.showInputDialog("Please enter the date the book was issued");
            if (startDate.length() == 10) {
                if (Character.isDigit(startDate.charAt(0)) && Character.isDigit(startDate.charAt(1)) && startDate.charAt(2) == '-' && Character.isDigit(startDate.charAt(3)) && Character.isDigit(startDate.charAt(4))
                        && startDate.charAt(5) == '-' && Character.isDigit(startDate.charAt(6)) && Character.isDigit(startDate.charAt(7)) && Character.isDigit(startDate.charAt(8)) && Character.isDigit(startDate.charAt(9))) {
                    int year = Integer.parseInt(startDate.substring(6, 10));
                    int month = Integer.parseInt(startDate.substring(3, 5));
                    int day = Integer.parseInt(startDate.substring(1, 2));
                    Calendar cal = new GregorianCalendar();
                    if (year >= cal.get(2015)) {
                        if (month >= 1 && month <= 12) {
                            if (day >= 1 && day <= 31) {

                                 returned = JOptionPane.showInputDialog("Is this book returned? (y or n)");
                                if (returned.toLowerCase().charAt(0) =='Y' || returned.toLowerCase().charAt(0) == 'N') {

                                    valid = true;

                                }else{
                                    returned = JOptionPane.showInputDialog("Invalid input!. Please re-enter (y or n): ");
                                }
                            } else {
                                startDate = JOptionPane.showInputDialog("Invalid day!. Please re-enter:");

                            }
                        } else {
                            startDate = JOptionPane.showInputDialog("Invalid month!. Please re-enter:");
                        }
                    } else {
                        startDate = JOptionPane.showInputDialog("Invalid! year!. Please re-enter: ");
                    }

                }
            } else {
                startDate = JOptionPane.showInputDialog("Invalid! Please re-enter the date in the form dd-mm-yy");
            }


        //allLoaned.add(name,title,startDate,returned)
        }

        //LoanedBook l = new LoanedBook(name,title,startDate,returned);


    }

    public static void viewIssuedBooks(ArrayList<LoanedBook> allLoaned){
        String allLoanedData="";
        LoanedBook lb;

        Iterator<LoanedBook> iterator = allLoaned.iterator();

        while (iterator.hasNext()){
            lb = iterator.next();
            if (lb !=null)
                allLoanedData += lb + "\n";
        }

        JOptionPane.showMessageDialog(null,allLoanedData,
                "List of all Loaned Books",JOptionPane.INFORMATION_MESSAGE);
    }

    public void viewOverdues(ArrayList<LoanedBook> allLoaned){

    }










    //when a menu item is clicked, response starts here
    public void actionPerformed(ActionEvent e) {
        String menuName;



        menuName = e.getActionCommand();
        if (e.getSource()==this.bookButton){
            addBook(allBooks);
        }
        if (e.getSource()==this.studentButton){
            addStudent(allStudents);
        }

        if (e.getSource()==this.overdueButton){
           addStudent(allStudents);
        }
        if (menuName == "Manage") {
            this.manageBook();
        }


        if (menuName == "Manage students") {

            try {
                this.manageStudents();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        } else if (menuName == "Save") {
            try {
                this.save();
                JOptionPane.showMessageDialog((Component) null, "Data saved successfully", "Saved", 1);
            } catch (IOException var4) {
                JOptionPane.showMessageDialog((Component) null, "Not able to save the file");

            }
        }
        else if (menuName == "Issued Books"){

            this.manageIssues(allBooks,  allStudents);
        }



        else if (menuName == "Quit") {
            JOptionPane.showMessageDialog(null, "Now closing window", "Closing Window",
                    JOptionPane.INFORMATION_MESSAGE);
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit this application?",
                    "Exiting Application Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
            if (choice == JOptionPane.YES_OPTION)
                dispose();
        }


    }


}

