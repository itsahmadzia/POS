package UILayer;

import DBLayer.DatabaseConnection;
import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategorywiseProducts extends JFrame {

    private JRadioButton dailyRadioButton;
    private JDateChooser from;
    private JLabel totalsalessum;
    private double totalsales=0;

    private JDateChooser tofield;
    private JButton generateButton;
    private JButton backButton;
    private ChartPanel chartPanel;
    private JTable dataTable;

    // JDBC database URL, username, and password of MySQL server
    private static final String DB_URL = "jdbc:mysql://your_database_url:3306/your_database_name";
    private static final String USER = "ostechnix";
    private static final String PASSWORD = "Password123#@!";

    public CategorywiseProducts() {
        setTitle("Graph");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tofield = new JDateChooser();
        from = new JDateChooser();
        Dimension preferredSize = new Dimension(150, 30); // Adjust the size as needed
        tofield.setPreferredSize(preferredSize);

        from.setPreferredSize(preferredSize);
        totalsalessum=new JLabel();
        totalsalessum.setFont(new Font("Arial", Font.BOLD, 24));
        totalsalessum.setBackground(new Color(6, 56, 6));


        generateButton = new JButton("Generate");
        backButton=new JButton("Back ");

        chartPanel = new ChartPanel(null);
        Panel infoPanel = new Panel();
        infoPanel.setLayout(new FlowLayout());

        Panel ButtonPanel = new Panel();
        chartPanel.setPreferredSize(new Dimension(600, 400));
        ButtonPanel.setLayout(new FlowLayout());
        infoPanel.add(totalsalessum);
        dataTable = new JTable();
        ButtonPanel.add(backButton);
        setLayout(new BorderLayout());
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());
        //   radioPanel.add(new JLabel("  End Date: "));
        //   radioPanel.add(tofield);
        //   radioPanel.add(new JLabel("  Start Date: "));
        //    radioPanel.add(from);
        radioPanel.add(generateButton);

        add(radioPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.EAST);
        add(new JScrollPane(dataTable), BorderLayout.CENTER);
        add(ButtonPanel,BorderLayout.WEST);



        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePieChart();
            }
        });

    }
    private void createAndDisplayPieChart(DefaultTableModel tableModel) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String name = (String) tableModel.getValueAt(i, 0); // Assuming name is in the first column
            int remainingDays = (int) tableModel.getValueAt(i, 1); // Assuming remainingDays is in the second column
            dataset.setValue(name, remainingDays);
            totalsales += remainingDays; // Accumulate the total sales
        }

        JFreeChart chart = createPieChart(dataset);

        chartPanel.setChart(chart);
    }

    private JFreeChart createPieChart(DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Product Acquisition of Categories",
                dataset,
                true,
                true,
                false
        );

        return chart;
    }

    private void generatePieChart() {
        totalsales = 0;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT c.name AS category_name, COUNT(p.id) AS number_of_products\n" +
                    "FROM Category c\n" +
                    "         LEFT JOIN Product p ON c.id = p.category_id\n" +
                    "GROUP BY c.id, c.name;";
            try (Statement preparedStatement = connection.createStatement()) {
                ResultSet resultSet = preparedStatement.executeQuery(query);

                dataTable.setModel(new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                ((DefaultTableModel) dataTable.getModel()).addColumn("Category Name");
                ((DefaultTableModel) dataTable.getModel()).addColumn("Total Items");

                while (resultSet.next()) {
                    String productName = resultSet.getString(1);
                    int remainingDays = resultSet.getInt(2);

                    // can add a check here to set a threshold
                    ((DefaultTableModel) dataTable.getModel()).addRow(new Object[]{productName, remainingDays});
                }


                createAndDisplayPieChart(((DefaultTableModel) dataTable.getModel()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CategorywiseProducts orderGraphUI = new CategorywiseProducts();
                orderGraphUI.setVisible(true);
            }
        });
    }
}