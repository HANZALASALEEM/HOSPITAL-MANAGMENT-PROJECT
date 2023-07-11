import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



class Geust{
    private String name;
    private int password;

    public Geust() {

        JFrame f = new JFrame("Guest Window");
        JMenuBar mb = new JMenuBar();
        JMenu update, search , updateP, updateD;
        JMenu search_patient, search_doctor;
        JMenuItem search_p_by_ID, search_p_by_name, search_d_by_ID, search_d_by_name;
        JMenuItem Update_p_by_ID, Update_p_by_name, Updated_d_by_ID, Updated_d_by_name;

        search = new JMenu("Search");
        search_patient = new JMenu("search_patient");
        search_doctor = new JMenu("search doctor ");

        search_p_by_ID = new JMenuItem("Search patient by ID");

        //search patient by id
        search_p_by_ID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient ID"));
                String get_table = "select * from patient";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);
                    int find = 0;
                    while(rs.next()){
                        if(id == rs.getInt("pid")){
                            find = 1;
                            String name = rs.getString("pname");
                            int age = rs.getInt("page");
                            String diease = rs.getString("pdiease");
                            String discription = rs.getString("pdiscription");
                            String doctor_name = rs.getString("pdoctor");

                            JOptionPane.showMessageDialog(null, " Name : " + name + "\n Age : " + age + "\n desies : "+ diease + " \nDiscription : " + discription + "\n Doctor name : " + doctor_name );

                        }
                    }
                    if (find ==0 ){
                        JOptionPane.showMessageDialog(null, "Not match with database ");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        search_p_by_name = new JMenuItem("Search pateint by name");

        //search by patient by name
        search_p_by_name.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String p_name = JOptionPane.showInputDialog("Enter Patient Name");
                String get_table = "select * from patient";
                boolean find = false;
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);

                    while (rs.next())
                    {
                        if (p_name.equals(rs.getString("pname") ))
                        {
                            find = true;
                            String name = rs.getString("pname");
                            int age = rs.getInt("page");
                            String diease = rs.getString("pdiease");
                            String discription = rs.getString("pdiscription");
                            String doctor_name = rs.getString("pdoctor");

                            JOptionPane.showMessageDialog(null, "Name : " + name + "\nAge : " + age + "\ndesies : " + diease + " \nDiscription : " + discription + "\nDoctor name : " + doctor_name);

                        }

                    }

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
//                if(find == false) {
//                    JOptionPane.showMessageDialog(null,"Not Match with Database");
//                }
            }
        });


        search_d_by_ID = new JMenuItem("Search doctor by ID");
        search_d_by_name = new JMenuItem("Search doctor by name");


        search_doctor.add(search_d_by_ID);
        search_doctor.add(search_d_by_name);

        search_patient.add(search_p_by_ID);
        search_patient.add(search_p_by_name);
        search.add(search_patient);
        search.add(search_doctor);


        update = new JMenu("update");
        updateP = new JMenu("Update_patient");
        updateD = new JMenu("update doctor ");

        Update_p_by_ID = new JMenuItem("Update patient by ID");
        Update_p_by_name = new JMenuItem("Update pateint by name");

        Updated_d_by_ID = new JMenuItem("Update doctor by ID");
        Updated_d_by_name = new JMenuItem("Update doctor by name");


        updateD.add(Updated_d_by_ID);
        updateD.add(Updated_d_by_name);

        updateP.add(Update_p_by_ID);
        updateP.add(Update_p_by_name);
        update.add(updateP);
        update.add(updateD);


        //print and help item
        JMenu print = new JMenu("Print");
        JMenu help = new JMenu("Help");


        mb.add(search);
        mb.add(update);
        mb.add(print);
        mb.add(help);
        f.setJMenuBar(mb);

        f.setSize(1000, 800);
        f.setLayout(null);
        f.setVisible(true);

}
}
class Admin extends JFrame{
    public static String adminName = "HANZALA";
    public static int adminPassword = 1234;

    public Admin(String adminName, int adminPassword) {
        this.adminName=adminName;
        this.adminPassword=adminPassword;
        JFrame f = new JFrame("Patient Managment System");
        JMenuBar mb = new JMenuBar();
        JMenu manage,update, search, help;

        //For Manage MenuItem
        JMenuItem add_pat, add_doc, delete_pat, update_pat, update_doc;
        manage = new JMenu("Manage");
        update = new JMenu("Update");
        add_pat = new JMenuItem("Add Patient");
        add_pat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // int id = Integer.parseInt(JOptionPane.showInputDialog("Enter your ID"));
                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your Age"));
                String name = JOptionPane.showInputDialog("Enter your Name");
                String diease = JOptionPane.showInputDialog("Enter your Diease");
                String discription = JOptionPane.showInputDialog("Enter your Discription");
                String doctor_name = JOptionPane.showInputDialog("Enter your Doctor Name");

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    //Statement stmt = con.createStatement();
                    String q = "Insert into patient (pname , page , pdiease, pdiscription , pdoctor) values (?,?,?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(q);
                    pstmt.setString(1, name);
                    pstmt.setInt(2,age);
                    pstmt.setString(3, diease);
                    pstmt.setString(4, discription);
                    pstmt.setString(5,doctor_name);


                    //String insert_patient = "Insert into patient (pname , page , pdiease, pdiscription , pdoctor) values ("+ name + age + diease + discription + doctor_name + ")";
                    pstmt.executeUpdate();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        });
        add_doc = new JMenuItem("Add Doctor");
        add_doc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Doctor Name");
                String specialization = JOptionPane.showInputDialog("Enter Doctor specialization");


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    //Statement stmt = con.createStatement();
                    String q = "Insert into doctor (dname , dspecialization) values (?,?)";
                    PreparedStatement pstmt = con.prepareStatement(q);
                    pstmt.setString(1, name);
                    pstmt.setString(2,specialization);
                    pstmt.executeUpdate();


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        delete_pat = new JMenuItem("delete_pat");
        delete_pat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p_name = JOptionPane.showInputDialog("Enter Patient Name");
                String delete_table = "delete from patient where pname = ?";
                boolean find = false;
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);


                    find = true ;
                    PreparedStatement pstmt = con.prepareStatement(delete_table);
                    pstmt.setString(1,p_name);
                    pstmt.executeUpdate();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(find == false){
                    JOptionPane.showMessageDialog(null, "Not match with database");
                }
            }
            });
        //Update Patient
        update_pat = new JMenuItem("update_pat");
        update_pat.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String p_name = JOptionPane.showInputDialog("Enter Patient Name");
                String update_table = "select * from patient";
                boolean find = false;
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery(update_table);
                    while (rs.next())
                    {
                        if (p_name.equals(rs.getString("pname") ))
                        {
                            find = true ;
                           String diease = rs.getString("pdiease");
                           String current_diease = JOptionPane.showInputDialog("Enter Updated Diease");
                           rs.updateString("pdiease", current_diease);
                           rs.updateRow();
                           JOptionPane.showMessageDialog(null, "Updated Diease : " + current_diease + "\nPrevious Diease : " + diease);
                        }

                    }
                }

                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(find == false){
                    JOptionPane.showMessageDialog(null, "Not match with database");
                }
            }
        });
        //Update Doctor
        update_doc = new JMenuItem("update_doc");
        update_doc.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String d_name = JOptionPane.showInputDialog("Enter Doctor Name");
                String update_table = "select * from patient";
                boolean find = false;
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
                    ResultSet rs = stmt.executeQuery(update_table);
                    while (rs.next())
                    {
                        if (d_name.equals(rs.getString("dname") ))
                        {
                            find = true ;
                            String specialization = rs.getString("dspecialization");
                            String current_specialization = JOptionPane.showInputDialog("Enter Updated specialization");
                            rs.updateString("pspecialization", current_specialization);
                            rs.updateRow();
                            JOptionPane.showMessageDialog(null, "Updated specialization : " + current_specialization +"\nPrevious Specialization : " + specialization);
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(find == false){
                    JOptionPane.showMessageDialog(null, "Not match with database");
                }
            }
        });
        manage.add(add_pat);
        manage.add(add_doc);
        manage.add(delete_pat);
        update.add(update_pat);
        update.add(update_doc);
        manage.add(update);


        //For search MenuItem
        JMenu patient, doctor;

        search = new JMenu("Search");
        doctor = new JMenu("Doctor");
        patient=new JMenu("Patient");

        //for doctor
        JMenuItem doc_name, doc_specialization;
        //search doctor by name
        doc_name= new JMenuItem("by Name");
        doc_name.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String d_name = JOptionPane.showInputDialog("Enter Doctor Name");
                String get_table = "select * from doctor";
                boolean find = false;
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);
                    while (rs.next())
                    {
                        if (d_name.equals(rs.getString("dname") ))
                        {
                            find = true ;
                            String name = rs.getString("dname");
                            String spec = rs.getString("dspecialization");


                            JOptionPane.showMessageDialog(null, "Name : " + name + "\nSpecialization : " + spec );

                        }

                    }
                }

                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(find == false){
                    JOptionPane.showMessageDialog(null, "Not match with database");
                }
            }
        });
        //search doctor by specialization
        doc_specialization= new JMenuItem("by Specialization");
        doc_specialization.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String d_spec = JOptionPane.showInputDialog("Enter Doctor Name");
                String get_table = "select * from doctor";
                boolean find = false;
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);
                    while (rs.next())
                    {

                        if (d_spec.equals(rs.getString("dspecialization") ))
                        {
                            find = true;
                            String name = rs.getString("dname");
                            String spec = rs.getString("dspecialization");
                            JOptionPane.showMessageDialog(null, "Name : " + name + "\nSpecialization : " + spec );
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(find == false){
                    JOptionPane.showMessageDialog(null, "Not match with database");
                }
            }
        });


        doctor.add(doc_name);
        doctor.add(doc_specialization);

        //for patient
        JMenuItem pat_name, pat_id, pat_age, pat_diease, pat_doctor;
        //Search by Name
        pat_name= new JMenuItem("by Name");
        pat_name.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String p_name = JOptionPane.showInputDialog("Enter Patient Name");
                String get_table = "select * from patient";
                boolean find = false;
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);
                    while (rs.next())
                    {
                        if (p_name.equals(rs.getString("pname") ))
                        {
                            find = true;
                            String name = rs.getString("pname");
                            int age = rs.getInt("page");
                            String diease = rs.getString("pdiease");
                            String discription = rs.getString("pdiscription");
                            String doctor_name = rs.getString("pdoctor");

                            JOptionPane.showMessageDialog(null, "Name : " + name + "\nAge : " + age + "\ndesies : " + diease + " \nDiscription : " + discription + "\nDoctor name : " + doctor_name);

                        }

                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(find == false){
                    JOptionPane.showMessageDialog(null, "Not match with database");
                }
            }
        });
        //Search by ID
        pat_id= new JMenuItem("by ID");
        pat_id.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient ID"));
                String get_table = "select * from patient";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);
                    while(rs.next()){
                        if(id == rs.getInt("pid")){
                            String name = rs.getString("pname");
                            int age = rs.getInt("page");
                            String diease = rs.getString("pdiease");
                            String discription = rs.getString("pdiscription");
                            String doctor_name = rs.getString("pdoctor");

                            JOptionPane.showMessageDialog(null, " Name : " + name + "\n Age : " + age + "\n desies : "+ diease + " \nDiscription : " + discription + "\n Doctor name : " + doctor_name );

                        }
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }



            }
        });
        //Search by Age
        pat_age= new JMenuItem("by age");
        pat_age.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int p_age = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient ID"));
                String get_table = "select * from patient";
                boolean find = false;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);
                    while(rs.next()){
                        if(p_age == rs.getInt("page")){
                            find = true;
                            String name = rs.getString("pname");
                            int age = rs.getInt("page");
                            String diease = rs.getString("pdiease");
                            String discription = rs.getString("pdiscription");
                            String doctor_name = rs.getString("pdoctor");

                            JOptionPane.showMessageDialog(null, " Name : " + name + "\n Age : " + age + "\n desies : "+ diease + " \nDiscription : " + discription + "\n Doctor name : " + doctor_name );

                        }
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(find==false){
                    JOptionPane.showMessageDialog(null, "Not match with database");
                }

            }
        });
        //Search by Diease
        pat_diease= new JMenuItem("by diease");
        pat_diease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p_diease = JOptionPane.showInputDialog("Enter Patient ID");
                String get_table = "select * from patient";
                boolean find = false;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);
                    while(rs.next()){
                        if(p_diease == rs.getString("pdiease")){
                            find = true;
                            String name = rs.getString("pname");
                            int age = rs.getInt("page");
                            String diease = rs.getString("pdiease");
                            String discription = rs.getString("pdiscription");
                            String doctor_name = rs.getString("pdoctor");

                            JOptionPane.showMessageDialog(null, " Name : " + name + "\n Age : " + age + "\n desies : "+ diease + " \nDiscription : " + discription + "\n Doctor name : " + doctor_name );

                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(find == false){
                    JOptionPane.showMessageDialog(null, "Not match with database");
                }
            }
        });
        //Search by Doctor name
        pat_doctor= new JMenuItem("by Doctor name");
        pat_doctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String p_doctor = JOptionPane.showInputDialog("Enter Patient ID");
                String get_table = "select * from patient";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user = "hanzala";
                    String password = "2293";
                    String url = "jdbc:mysql://127.0.0.1/patient_manage";
                    Connection con = DriverManager.getConnection(url, user, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(get_table);
                    while(rs.next()){
                        if(p_doctor == rs.getString("pdoctor")){
                            String name = rs.getString("pname");
                            int age = rs.getInt("page");
                            String diease = rs.getString("pdiease");
                            String discription = rs.getString("pdiscription");
                            String doctor_name = rs.getString("pdoctor");

                            JOptionPane.showMessageDialog(null, " Name : " + name + "\n Age : " + age + "\n desies : "+ diease + " \nDiscription : " + discription + "\n Doctor name : " + doctor_name );

                        }
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }



            }
        });
        patient.add(pat_name);
        patient.add(pat_id);
        patient.add(pat_age);
        patient.add(pat_diease);
        patient.add(pat_doctor);

        search.add(doctor);
        search.add(patient);

        //for help MenuItem
        help=new JMenu("help");
        JMenuItem about, ch_password;
        about=new JMenuItem("About us");
        ch_password=new JMenuItem("Change Password");
help.add(about);
help.add(ch_password);


//toolbar
        JPanel toolbar = new JPanel();
        JButton add_pa, search_pa , add_do;
        add_pa = new JButton("Add Patient");
        search_pa = new JButton("Search Patient");
        add_do = new JButton("Add Doctor");
        toolbar.add(add_pa);
        toolbar.add(search_pa);
        toolbar.add(add_do);
        toolbar.setBackground(Color.darkGray);



        mb.add(manage);
        mb.add(search);
        mb.add(help);
        f.setJMenuBar(mb);
        f.add(toolbar);
        f.add(add_pa);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
}
class User {
    public static void main(String s[]) {

        JFrame home = new JFrame("Wellcome to Patient managment system");
        JPanel homepanel = new JPanel();
        JButton bAdmin, bGuest;
        bAdmin = new JButton("Enter as Administration");
        bAdmin.setBounds(150,100,70,50);
        bGuest = new JButton("Enter as Guest");
        bGuest.setBounds(150,150,70,50);
        homepanel.add(bAdmin);
        homepanel.add(bGuest);
        home.add(homepanel);
        home.setSize(400, 400);
        home.setVisible(true);

        bAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin a = new Admin("HANZALA", 1234);
            }
        });
        bGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Geust g = new Geust();
            }
        });
    }
}