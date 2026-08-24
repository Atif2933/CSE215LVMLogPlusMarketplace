package org.atf2933;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class Swing {

    Marketplace marketplace = new Marketplace();

    JFrame frame = new JFrame("Maintenance Log and Marketplace V1");

    public Swing() {
        frame.setSize(1366, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        JLabel title = new JLabel(
                "Welcome to Vehicle Maintenance Log! Select your access level to continue-",
                SwingConstants.CENTER
        );
        title.setFont(new Font("Arial", Font.BOLD, 28)); // Large title text
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0)); // Spacing around title


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));

        JButton adminb = new JButton("Admin");
        JButton user  = new JButton("User");


        Font buttonFont = new Font("Arial", Font.BOLD, 22);
        adminb.setFont(buttonFont);
        user.setFont(buttonFont);


        Dimension buttonSize = new Dimension(200, 60);
        adminb.setPreferredSize(buttonSize);
        user.setPreferredSize(buttonSize);

        buttonPanel.add(adminb);
        buttonPanel.add(user);

        frame.add(title, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        adminb.addActionListener(e -> {

            JFrame adminFrame = new JFrame("Admin Access Login");

            adminFrame.setSize(1366,768);
            adminFrame.setLayout(new FlowLayout());

            JLabel idLabel = new JLabel("ID: ");
            JTextField idField = new JTextField(8);

            JLabel passLabel = new JLabel("Password: ");
            JTextField passField = new JTextField(8);

            JButton loginButton = new JButton("Login");
            adminFrame.add(idLabel);
            adminFrame.add(idField);

            adminFrame.add(passLabel);
            adminFrame.add(passField);

            adminFrame.add(loginButton);
            loginButton.addActionListener(a -> {
                //System.out.println("Pressed");

                int ID;

                try {
                    ID = Integer.parseInt(idField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            adminFrame,
                            "ID must contain numbers only!"
                    );
                    return;
                }

                String Password = new String(passField.getText());

                admin administrator = admin.readAdmin();

                if (ID == administrator.getID() &&
                        Password.equals(administrator.getPassword())) {

                    JOptionPane.showMessageDialog(
                            adminFrame,
                            "Welcome to Admin Dashboard, "+administrator.getName()
                    );
                    adminFrame.dispose();

                    JFrame menu = new JFrame("Menu Selection");
                    menu.setSize(500,300);
                    menu.setLayout(null);
                    menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    JButton marketplaceButton = new JButton("Open Marketplace");
                    marketplaceButton.setBounds(100, 80, 280, 50);

                    JButton signedUsers = new JButton("Check Signed Up Users");
                    signedUsers.setBounds(100, 150, 280, 50);

                    menu.add(signedUsers);
                    menu.add(marketplaceButton);
                    signedUsers.addActionListener(s1->{

                        JFrame userList = new JFrame("User Database");
                        userList.setSize(800,500);
                        userList.setLayout(null);
                        userList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        String[] columns = {
                                "User ID",
                                "Name",
                                "Phone Number"
                        };
                        DefaultTableModel userListTable = new DefaultTableModel(columns,0);
                        JTable usersList = new JTable(userListTable);
                        JScrollPane scrollPane = new JScrollPane(usersList);
                        scrollPane.setBounds(20,20,740,300);
                        userList.add(scrollPane);

                        UserManager userManager = new UserManager(0, "", "", "");
                        userManager.readUsers();

                        ArrayList<User> displayedUsers = new ArrayList<>();

                        for (User userTable : userManager.getUserArray()) {

                            displayedUsers.add(userTable);

                            userListTable.addRow(new Object[]{
                                    userTable.getID(),
                                    userTable.getName(),
                                    userTable.getPhonenum()
                            });
                        }
                        userList.setVisible(true);

                        JButton addUserButton = new JButton("Add An User");

                        addUserButton.setBounds(200, 350, 150, 40);

                        userList.add(addUserButton);

                        addUserButton.addActionListener(u1 -> {

                            JFrame addUserFrame = new JFrame("Add an User to database");

                            addUserFrame.setSize(500, 400);
                            addUserFrame.setLayout(null);
                            addUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            JLabel IDLabel = new JLabel("User ID:");
                            IDLabel.setBounds(50, 40, 100, 30);

                            JTextField IDField = new JTextField();
                            IDField.setBounds(180, 40, 200, 30);


                            JLabel passwordLabel = new JLabel("Password:");
                            passwordLabel.setBounds(50, 90, 100, 30);

                            JTextField passwordField = new JTextField();
                            passwordField.setBounds(180, 90, 200, 30);

                            JLabel nameLabel = new JLabel("Name:");
                            nameLabel.setBounds(50, 140, 100, 30);

                            JTextField nameField = new JTextField();
                            nameField.setBounds(180, 140, 200, 30);

                            JLabel phoneLabel = new JLabel("Phone:");
                            phoneLabel.setBounds(50, 190, 100, 30);

                            JTextField phoneField = new JTextField();
                            phoneField.setBounds(180, 190, 200, 30);

                            JButton submitButton = new JButton("Add User");
                            submitButton.setBounds(180, 250, 130, 40);

                            addUserFrame.add(IDLabel);
                            addUserFrame.add(IDField);

                            addUserFrame.add(passwordLabel);
                            addUserFrame.add(passwordField);

                            addUserFrame.add(nameLabel);
                            addUserFrame.add(nameField);

                            addUserFrame.add(phoneLabel);
                            addUserFrame.add(phoneField);

                            addUserFrame.add(submitButton);

                            addUserFrame.setVisible(true);

                            submitButton.addActionListener(s -> {
                                try {

                                    User newUser = new UserManager(
                                            Integer.parseInt(IDField.getText()),
                                            passwordField.getText(),
                                            nameField.getText(),
                                            phoneField.getText()
                                    );

                                    userManager.createUser(newUser);

                                    displayedUsers.add(newUser);

                                    userListTable.addRow(new Object[]{
                                            newUser.getID(),
                                            newUser.getName(),
                                            newUser.getPhonenum()
                                    });

                                    JOptionPane.showMessageDialog(
                                            addUserFrame,
                                            "User Added Successfully!"
                                    );

                                    addUserFrame.dispose();
                                }catch(AlreadyRegisteredUserException arue){
                                    JOptionPane.showMessageDialog(
                                            addUserFrame,
                                            arue.getMessage());
                                }
                            });
                        });

                        JButton removeUserButton = new JButton("Remove an User");

                        removeUserButton.setBounds(400, 350, 150, 40);

                        userList.add(removeUserButton);

                        removeUserButton.addActionListener(r2 -> {

                            int selectedRow = usersList.getSelectedRow();

                            if (selectedRow == -1) {

                                JOptionPane.showMessageDialog(
                                        userList,
                                        "Please select a user first!"
                                );

                                return;
                            }

                            int choice = JOptionPane.showConfirmDialog(
                                    userList,
                                    "Are you sure you want to remove this user?",
                                    "Confirm Removal",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (choice == JOptionPane.YES_OPTION) {

                                User selectedUser =
                                        displayedUsers.get(selectedRow);

                                userManager.removeUser(selectedUser);

                                displayedUsers.remove(selectedRow);

                                userListTable.removeRow(selectedRow);

                                JOptionPane.showMessageDialog(
                                        userList,
                                        "User Removed Successfully!"
                                );
                            }
                        });
                    });
                    signedUsers.setVisible(true);

                    marketplaceButton.addActionListener(m2->{
                        JFrame marketplaceFrame = new JFrame("Marketplace");
                        marketplaceFrame.setSize(800, 500);
                        marketplaceFrame.setLayout(null);
                        marketplaceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        String[] columns = {
                                "Car Model",
                                "Brand",
                                "Owner Name",
                                "Telephone",
                                "Mileage",
                                "Engine Model"
                        };

                        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
                        JTable carTable = new JTable(tableModel);
                        //System.out.println("Number of cars: " + marketplace.getVehicles().size());
                        ArrayList<Vehicle> listedCars = new ArrayList<>();

                        for (Vehicle car : marketplace.getVehicles()) {


                            if(car.isForSale()) {
                                listedCars.add(car);
                                tableModel.addRow(new Object[]{
                                        car.getModelName(),
                                        car.getBrandName(),
                                        car.getOwnerName(),
                                        car.getOwnerPhone(),
                                        car.getMileage(),
                                        car.getEngineModel()
                                });
                            }
                        }
                        JScrollPane scrollPane = new JScrollPane(carTable);
                        scrollPane.setBounds(20, 20, 840, 300);
                        marketplaceFrame.add(scrollPane);
                        JButton listCarButton = new JButton("List a car for sale");
                        listCarButton.setBounds(200, 350, 180, 40);
                        JButton removeListedCar = new JButton("Remove car from list");
                        removeListedCar.setBounds(400, 350, 180, 40);
                        marketplaceFrame.add(listCarButton);
                        marketplaceFrame.add(removeListedCar);
                        listCarButton.addActionListener(l->{
                            JFrame listCar = new JFrame("List A Car For Sale");
                            listCar.setSize(500,300);
                            listCar.setLayout(null);
                            listCar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            JLabel brand = new JLabel("Brand: ");
                            brand.setBounds(50,40,100,30);

                            JTextField brandField = new JTextField();
                            brandField.setBounds(180, 40, 200, 30);

                            JLabel modelLabel = new JLabel("Model:");
                            modelLabel.setBounds(50, 90, 100, 30);

                            JTextField modelField = new JTextField();
                            modelField.setBounds(180, 90, 200, 30);

                            JLabel ownerLabel = new JLabel("Owner Name:");
                            ownerLabel.setBounds(50, 140, 100, 30);

                            JTextField ownerField = new JTextField();
                            ownerField.setBounds(180, 140, 200, 30);

                            JLabel phoneLabel = new JLabel("Phone:");
                            phoneLabel.setBounds(50, 190, 100, 30);

                            JTextField phoneField = new JTextField();
                            phoneField.setBounds(180, 190, 200, 30);

                            JLabel mileageLabel = new JLabel("Mileage:");
                            mileageLabel.setBounds(50, 240, 100, 30);

                            JTextField mileageField = new JTextField();
                            mileageField.setBounds(180, 240, 200, 30);

                            JLabel engineLabel = new JLabel("Engine Model:");
                            engineLabel.setBounds(50, 290, 100, 30);

                            JTextField engineField = new JTextField();
                            engineField.setBounds(180, 290, 200, 30);

                            JButton submit = new JButton("List Car for Sale");
                            submit.setBounds(180, 360, 130, 40);

                            listCar.add(brand);
                            listCar.add(brandField);
                            listCar.add(modelLabel);
                            listCar.add(modelField);
                            listCar.add(ownerLabel);
                            listCar.add(ownerField);
                            listCar.add(phoneLabel);
                            listCar.add(phoneField);
                            listCar.add(mileageLabel);
                            listCar.add(mileageField);
                            listCar.add(engineLabel);
                            listCar.add(engineField);
                            listCar.add(submit);
                            listCar.setVisible(true);

                            submit.addActionListener(s->{
                                try {
                                    Vehicle listCarForSale = new Vehicle(brandField.getText(),
                                            modelField.getText(),
                                            ownerField.getText(),
                                            phoneField.getText(),
                                            Integer.parseInt(mileageField.getText()),
                                            engineField.getText(),
                                            true
                                    );

                                    marketplace.addCar(listCarForSale);
                                    listedCars.add(listCarForSale);
                                    tableModel.addRow(new Object[]{
                                                    listCarForSale.getModelName(),
                                                    listCarForSale.getBrandName(),
                                                    listCarForSale.getOwnerName(),
                                                    listCarForSale.getOwnerPhone(),
                                                    listCarForSale.getMileage(),
                                                    listCarForSale.getEngineModel()
                                            }
                                    );

                                    JOptionPane.showMessageDialog(listCar, "Listed For Sale Successfully!");
                                    listCar.dispose();
                                }catch(AlreadyRegisteredVehicleException arve){
                                    JOptionPane.showMessageDialog(
                                            listCar,
                                            arve.getMessage());

                                }


                            });




                        });
                        removeListedCar.addActionListener(n-> {

                            int selectedRow = carTable.getSelectedRow();
                            if(selectedRow == -1){
                                JOptionPane.showMessageDialog(marketplaceFrame,"Select a car first!");
                                return;
                            }

                            int choice = JOptionPane.showConfirmDialog(marketplaceFrame,"Are you sure you want to remove this entry?","Confirmation",JOptionPane.YES_NO_OPTION);
                            if(choice == JOptionPane.YES_OPTION){
                                Vehicle selectedCar = listedCars.get(selectedRow);
                                marketplace.removeVehicle(selectedCar);
                                listedCars.remove(selectedRow);
                                tableModel.removeRow(selectedRow);


                                JOptionPane.showMessageDialog(marketplaceFrame,"Removed Successfully");

                            }

                                }
                                );
                        marketplaceFrame.setVisible(true);
                    });

                    menu.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(
                            adminFrame,
                            "Incorrect ID or Password!"
                    );
                }
            });
            adminFrame.setVisible(true);
        });

        user.addActionListener(e->{

            JFrame userFrame = new JFrame("User Access Login");

            userFrame.setSize(1366,768);
            userFrame.setLayout(new FlowLayout());

            JLabel nameLabel = new JLabel("Name: ");
            JTextField nameField = new JTextField(8);

            JLabel passLabel = new JLabel("Password: ");
            JTextField passField = new JTextField(8);

            JButton loginButton = new JButton("Login");


            userFrame.add(nameLabel);
            userFrame.add(nameField);

            userFrame.add(passLabel);
            userFrame.add(passField);

            userFrame.add(loginButton);

            //signUpButton.addActionListener(su->{

               // String name = new String(nameField.getText());

               // String Password = new String(passField.getText());

                //try{



                //} catch(AlreadyRegisteredUserException a7){
                    //a7.getMessage();
               // }

            //});
            loginButton.addActionListener(a -> {
              //  System.out.println("Pressed");

                String name = new String(nameField.getText());

                String Password = new String(passField.getText());

                UserManager userManager = new UserManager(0, "", "", "");
                //UserManager User =UserManager.readUsers();
                userManager.readUsers();

                boolean found = false;

                for(User userFind: userManager.getUserArray()){

                if (name.equals(userFind.getName()) &&
                        Password.equals(userFind.getPassword())) {

                    JOptionPane.showMessageDialog(
                            userFrame,
                            "Welcome to Maintenance Log and Marketplace, "+userFind.getName()+"!"
                    );
                    userFrame.dispose();
                    found = true;



                    JFrame menu = new JFrame("Menu Selection");
                    menu.setSize(500,300);
                    menu.setLayout(null);
                    menu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    JButton marketplaceButton = new JButton("Open Marketplace");
                    marketplaceButton.setBounds(100, 80, 280, 50);




                    menu.add(marketplaceButton);


                    marketplaceButton.addActionListener(m2->{
                        JFrame marketplaceFrame = new JFrame("Marketplace");
                        marketplaceFrame.setSize(800, 500);
                        marketplaceFrame.setLayout(null);
                        marketplaceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        JButton listCarButton = new JButton("List Car for Sale");


                        listCarButton.setBounds(20, 350, 200, 40);


                        marketplaceFrame.add(listCarButton);


                        String[] columns = {
                                "Car Model",
                                "Brand",
                                "Owner Name",
                                "Telephone",
                                "Mileage",
                                "Engine Model"
                        };

                        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
                        JTable carTable = new JTable(tableModel);
                        //System.out.println("Number of cars: " + marketplace.getVehicles().size());
                        for (Vehicle car : marketplace.getVehicles()) {


                            if(car.isForSale()) {
                                tableModel.addRow(new Object[]{
                                        car.getModelName(),
                                        car.getBrandName(),
                                        car.getOwnerName(),
                                        car.getOwnerPhone(),
                                        car.getMileage(),
                                        car.getEngineModel()
                                });
                            }
                        }
                        JScrollPane scrollPane = new JScrollPane(carTable);
                        scrollPane.setBounds(20, 20, 840, 300);
                        marketplaceFrame.add(scrollPane);
                        listCarButton.addActionListener(lcb -> {

                            JFrame listFrame = new JFrame("List Car for Sale");
                            listFrame.setSize(500, 500);
                            listFrame.setLayout(null);
                            listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            JLabel brandLabel = new JLabel("Brand:");
                            JLabel modelLabel = new JLabel("Model:");
                            JLabel engineLabel = new JLabel("Engine Model:");
                            JLabel mileageLabel = new JLabel("Mileage:");
                            JLabel phoneLabel = new JLabel("Telephone:");

                            JTextField brandField = new JTextField();
                            JTextField modelField = new JTextField();
                            JTextField engineField = new JTextField();
                            JTextField mileageField = new JTextField();
                            JTextField phoneField = new JTextField();

                            brandLabel.setBounds(50, 50, 120, 30);
                            brandField.setBounds(180, 50, 200, 30);

                            modelLabel.setBounds(50, 100, 120, 30);
                            modelField.setBounds(180, 100, 200, 30);

                            engineLabel.setBounds(50, 150, 120, 30);
                            engineField.setBounds(180, 150, 200, 30);

                            mileageLabel.setBounds(50, 200, 120, 30);
                            mileageField.setBounds(180, 200, 200, 30);

                            phoneLabel.setBounds(50, 250, 120, 30);
                            phoneField.setBounds(180, 250, 200, 30);

                            JButton submitButton = new JButton("List Vehicle");
                            submitButton.setBounds(150, 330, 180, 40);

                            listFrame.add(brandLabel);
                            listFrame.add(brandField);

                            listFrame.add(modelLabel);
                            listFrame.add(modelField);

                            listFrame.add(engineLabel);
                            listFrame.add(engineField);

                            listFrame.add(mileageLabel);
                            listFrame.add(mileageField);

                            listFrame.add(phoneLabel);
                            listFrame.add(phoneField);

                            listFrame.add(submitButton);

                            submitButton.addActionListener(sb->{
                                try{
                                    Vehicle newListCar = new Vehicle(
                                            modelField.getText(),
                                            brandField.getText(),
                                            name, // logged-in user's name
                                            phoneField.getText(),
                                            Integer.parseInt(mileageField.getText()),
                                            engineField.getText(),
                                            true
                                    );
                                    marketplace.addCar(newListCar);

                                    tableModel.addRow(new Object[]{
                                                    newListCar.getModelName(),
                                                    newListCar.getBrandName(),
                                                    newListCar.getOwnerName(),
                                                    newListCar.getOwnerPhone(),
                                                    newListCar.getMileage(),
                                                    newListCar.getEngineModel()
                                            }
                                    );

                                    JOptionPane.showMessageDialog(
                                            listFrame,
                                            "Vehicle successfully listed for sale!"
                                    );

                                    listFrame.dispose();


                                } catch (AlreadyRegisteredVehicleException arve2){
                                    arve2.getMessage();
                                }
                            });

                            listFrame.setVisible(true);
                        });
                        marketplaceFrame.setVisible(true);
                    });


                    menu.setVisible(true);
                    break;
                }


                } if(!found) {

                    JOptionPane.showMessageDialog(
                            userFrame,
                            "Incorrect ID or Password!"
                    );
                }
            });
            userFrame.setVisible(true);

        });

        frame.setVisible(true);
    }


}
