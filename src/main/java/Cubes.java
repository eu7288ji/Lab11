import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cubes extends JFrame {
    private JPanel rootPanel;
    private JButton Addbutton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField nameText;
    private JTextField updateText;
    private JList list1;
    private JButton confirmButton;
    private DefaultListModel<String> listModel;
    private static Cubes_Database cubes_database;

    Cubes(){
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        confirmButton.setVisible(false);

        listModel = new DefaultListModel<String>();
        list1.setModel(listModel);

        listModel.addElement("hello");
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        try {
            cubes_database = new Cubes_Database();
            cubes_database.connectDB();
            ResultSet rs = cubes_database.queryDB();
            while (rs.next()) {

                StringBuilder builder = new StringBuilder();
                builder.append(rs.getString("cube_solver"));
                builder.append("+");
                builder.append(rs.getDouble("how_long"));

                String data = builder.toString();

                String timer = rs.getString("cube_solver") + "Time: " + rs.getDouble("how_long");
                
                System.out.println(data);
                listModel.addElement(data);
            }


        } catch (SQLException error){
            error.printStackTrace();
        }






        Addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newBot = nameText.getText();
                Double newTime = Double.parseDouble(updateText.getText());

                cubes_database.addToDB(newBot, newTime);
                listModel.addElement(newBot + "Time: " + newTime);
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newBot = nameText.getText();
                Double newTime = Double.parseDouble(updateText.getText());

                cubes_database.updateToDB(newBot, newTime);
                listModel.addElement(newBot + "Time: " + newTime);
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newBot = nameText.getText();
                Double newTime = Double.parseDouble(updateText.getText());

                try {
                    cubes_database.psDelete.setString(1, newBot);

                    cubes_database.psDelete.executeUpdate();

                } catch(SQLException error) {
                    error.printStackTrace();
                }
            }
        });
    }
}


