package hospitalmanagementsystem;

import javax.naming.InvalidNameException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class HospitalSystem extends JFrame {
    private static int id = 1;
    private ArrayList<Patient> patients = new ArrayList<>();

    JTextField tname = new JTextField();
    JTextField tsurname = new JTextField();
    JTextField tage = new JTextField();
    JTextField tcontact = new JTextField();
    JTextField tgender = new JTextField();
    JTextField tcategory = new JTextField();
    JTextArea tdisplay = new JTextArea();


    public HospitalSystem() {

        setName("Appointment System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel patientPanel = new JPanel();
        patientPanel.setLayout(new GridLayout(8, 2));

        JLabel name = new JLabel("Name: ");
        JLabel surName = new JLabel("Surname: ");
        JLabel Age = new JLabel("Age: ");
        JLabel ContactInfo = new JLabel("Contact Info: ");
        JLabel gender = new JLabel("Gender: ");
        JLabel category = new JLabel("Doctor Category: ");


        tname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = tname.getText();
                    if (!input.matches("[a-zA-Z ]+")) {
                        throw new InvalidNameException("Invalid name");
                    }
                } catch (InvalidNameException ex) {
                    String msg="Invalid name";
                    JOptionPane.showMessageDialog(null,msg);
                }
            }
        });
        tsurname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = tsurname.getText();
                    if (!input.matches("[a-zA-Z]+")) {
                        throw new InvalidNameException("Invalid surname");
                    }
                } catch (InvalidNameException ex) {
                    String msg="Invalid surname";
                    JOptionPane.showMessageDialog(null,msg);
                }
            }
        });
       tgender.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   String input = tgender.getText();
                   if (!input.matches("[a-zA-Z]+")) {
                       throw new InvalidNameException("Invalid gender");
                   }
               } catch (InvalidNameException ex) {
                   String msg="Invalid gender";
                   JOptionPane.showMessageDialog(null,msg);
               }
           }
       });
        tcategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = tcategory.getText();
                    if (!input.matches("[a-zA-Z ]+")) {
                        throw new InvalidNameException("Invalid category");
                    }
                } catch (InvalidNameException ex) {
                    String msg="Invalid category";
                    JOptionPane.showMessageDialog(null,msg);
                }
            }
        });
        tage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = tage.getText();
                    Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    String msg="Please enter a numeric value for age";
                    JOptionPane.showMessageDialog(null,msg);
                }
            }
        });
        tcontact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = tcontact.getText();
                    if (!input.matches("0\\d{10}")) {
                        throw new InvalidNameException("Invalid contact number");
                    }
                } catch (InvalidNameException ex) {
                    String msg="Please enter an 11 digit number that starts with 0";
                    JOptionPane.showMessageDialog(null,msg);
                }
            }
        });


        tdisplay.setEditable(false);

        JButton Submit = new JButton("Submit");
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    checkFields();
                Patient patient = new Patient(id, tname.getText(), tsurname.getText(), tage.getText(), tcontact.getText(), tgender.getText(), tcategory.getText());
                patients.add(patient);
                displayPatients(patients);
                id++;
                tname.setText("");
                tsurname.setText("");
                tage.setText("");
                tcontact.setText("");
                tgender.setText("");
                tcategory.setText("");
                }catch (InvalidNameException ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });

        JButton Update = new JButton("Update");
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter patient ID to update: ");
                if (input!=null) {

                    try {

                        int patientid = Integer.parseInt(input);
                        boolean check = true;
                        for (Patient p : patients) {
                            if (p.getId() == patientid) {
                                checkFields();
                                p.setName(tname.getText());
                                p.setSurName(tsurname.getText());
                                p.setAge(tage.getText());
                                p.setContactInfo(tcontact.getText());
                                p.setCategory(tcategory.getText());
                                p.setGender(tgender.getText());
                                check = false;
                                displayPatients(patients);
                                JOptionPane.showMessageDialog(null, "Patient updated successfully");
                                tname.setText("");
                                tsurname.setText("");
                                tage.setText("");
                                tcontact.setText("");
                                tgender.setText("");
                                tcategory.setText("");
                                break;
                            }
                        }

                        if (check) {
                            JOptionPane.showMessageDialog(null, "Patient does not exist");
                        }
                    } catch (InvalidNameException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }

                }
            }
        });


        JButton Delete = new JButton("Delete");
        Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter patient ID to delete: ");
                if (input!=null) {
                    try {
                        int patientid = Integer.parseInt(input);
                        boolean check = true;
                        for (Patient p : patients) {
                            if (p.getId() == patientid) {
                                patients.remove(p);
                                displayPatients(patients);
                                JOptionPane.showMessageDialog(null, "Patient deleted successfully");
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            JOptionPane.showMessageDialog(null, "Patient does not exist");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }
                }

            }
        });

        patientPanel.add(name);
        patientPanel.add(tname);
        patientPanel.add(surName);
        patientPanel.add(tsurname);
        patientPanel.add(Age);
        patientPanel.add(tage);
        patientPanel.add(ContactInfo);
        patientPanel.add(tcontact);
        patientPanel.add(gender);
        patientPanel.add(tgender);
        patientPanel.add(category);
        patientPanel.add(tcategory);
        patientPanel.add(Submit);
        patientPanel.add(Update);
        patientPanel.add(Delete);
        patientPanel.add(tdisplay);
        patientPanel.add(new JScrollPane(tdisplay));


        tabbedPane.addTab("Patients", patientPanel);


        JPanel doctorPanel = new JPanel();
        doctorPanel.setLayout(new GridLayout(3, 3));

        JTextArea tdetails = new JTextArea();
        tdetails.setEditable(false);
        JTextArea tprescription = new JTextArea();
        tprescription.setEditable(false);
        JTextArea tcomments = new JTextArea();
        tcomments.setEditable(false);
        JTextArea tdate = new JTextArea();
        tdate.setEditable(false);

        JButton Details = new JButton("Fetch Patient Details");
        Details.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter patient ID to fetch details: ");
                if (input!=null) {
                    try {
                        int patientid = Integer.parseInt(input);
                        boolean check = true;
                        for (Patient p : patients) {
                            if (p.getId() == patientid) {
                                tdetails.setText("ID: " + p.getId() + "\n" +
                                        "Name: " + p.getName() + "\n" +
                                        "Surname: " + p.getSurName() + "\n" +
                                        "Age: " + p.getAge() + "\n" +
                                        "Contact Information:" + p.getContactInfo() + "\n" +
                                        "Gender: " + p.getGender() + "\n" +
                                        "Category:" + p.getCategory());
                                JOptionPane.showMessageDialog(null, "Patient details fetched successfully");
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            JOptionPane.showMessageDialog(null, "Patient does not exist");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }
                }

            }
        });
        JButton Prescription = new JButton("Prescription");
        Prescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter patient ID to prescribe medicine: ");
                if (input!=null) {
                    try {
                        int patientid = Integer.parseInt(input);
                        boolean check = true;
                        for (Patient p : patients) {
                            if (p.getId() == patientid) {
                                String medicine = JOptionPane.showInputDialog("Enter Medicine: ");
                                tprescription.setText("Patient ID: " + p.getId() + " - Prescription: " + medicine);

                                JOptionPane.showMessageDialog(null, "Prescription added successfully");
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            JOptionPane.showMessageDialog(null, "Patient does not exist");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }
                }
            }
        });

        JButton Comments = new JButton("Comments");
        Comments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter patient ID to add comments: ");
                if (input!=null) {
                    try {
                        int patientid = Integer.parseInt(input);
                        boolean check = true;
                        for (Patient p : patients) {
                            if (p.getId() == patientid) {
                                String comment = JOptionPane.showInputDialog("Enter Comments: ");
                                tcomments.setText("Patient ID: " + p.getId() + " - Comments: " + comment);

                                JOptionPane.showMessageDialog(null, "Comments added successfully");
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            JOptionPane.showMessageDialog(null, "Patient does not exist");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }
                }
            }
        });

        JButton Checkup = new JButton("Next Checkup");
        Checkup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter patient ID to add checkup date: ");
                if (input!=null) {
                    try {
                        int patientid = Integer.parseInt(input);
                        boolean check = true;
                        for (Patient p : patients) {
                            if (p.getId() == patientid) {
                                String date = JOptionPane.showInputDialog("Enter the checkup date and time(yyyy-mm-dd HH:mm): ");
                                tdate.setText("Patient ID: " + p.getId() + " - next checkup date: " + date);

                                JOptionPane.showMessageDialog(null, "Date added successfully");
                                check = false;
                                break;
                            }
                        }
                        if (check) {
                            JOptionPane.showMessageDialog(null, "Patient does not exist");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid ID");
                    }
                }
            }
        });
        tdate.setLineWrap(true);
        tdate.setWrapStyleWord(true);

        doctorPanel.add(tdetails);
        doctorPanel.add(Details);
        doctorPanel.add(Prescription);
        doctorPanel.add(Comments);
        doctorPanel.add(Checkup);
        doctorPanel.add(tprescription);
        doctorPanel.add(tcomments);
        doctorPanel.add(tdate);


        tabbedPane.addTab("Doctors", doctorPanel);


        add(tabbedPane);

        setVisible(true);
    }

    private void displayPatients(ArrayList<Patient> patients) {
        StringBuilder displayText = new StringBuilder();
        for (Patient p : patients) {
            displayText.append("Patient ID: ").append(p.getId()).append(" ").append(p.getName()).append(" ")
                    .append(p.getSurName()).append(" , Gender: ").append(p.getGender())
                    .append(" , Age: ").append(p.getAge()).append(" ,  Contact Information: ")
                    .append(p.getContactInfo()).append(" ,  Category: ").append(p.getCategory()).append("\n");
        }
        tdisplay.setText(displayText.toString());
    }
    private void checkFields() throws InvalidNameException {
        String name = tname.getText();
        String surname = tsurname.getText();
        String age = tage.getText();
        String contact = tcontact.getText();
        String gender = tgender.getText();
        String category = tcategory.getText();

        if (!name.matches("[a-zA-Z ]+")) {
            throw new InvalidNameException("Invalid name");
        }

        if (!surname.matches("[a-zA-Z]+")) {
            throw new InvalidNameException("Invalid surname");
        }

        try{
            Integer.parseInt(age);
        }catch (NumberFormatException ex){
            throw new InvalidNameException("Please enter a numeric value for age");
        }

        if (!contact.matches("0\\d{10}")) {
            throw new InvalidNameException("Please enter an 11 digit number that starts with 0");
        }

        if (!gender.matches("[a-zA-Z]+")) {
            throw new InvalidNameException("Invalid gender");
        }

        if (!category.matches("[a-zA-Z ]+")) {
            throw new InvalidNameException("Invalid category");
        }
    }
}