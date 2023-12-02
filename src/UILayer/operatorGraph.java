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

public class operatorGraph extends JFrame {
//SHOW ORDERS DONE BY OPERATORS IN PIE CHART
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
    private void performACTION(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new graphMenu().setVisible(true);
            }

        });
        this.dispose();
    }
    public operatorGraph() {

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

        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(800, 20)); // Adjust the size as needed

        add(gapPanel, BorderLayout.SOUTH);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performACTION(e);
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePieChart();
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);

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
                "Orders done by Operators",
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
            String query = "SELECT\n" +
                    "    order_t.operator_username AS name,\n" +
                    "    COUNT(id) AS number_of_orders,\n" +
                    "    SUM(total) AS total_amount\n" +
                    "FROM\n" +
                    "    order_t\n" +
                    "GROUP BY\n" +
                    "    order_t.operator_username;";
            try (Statement preparedStatement = connection.createStatement()) {
                ResultSet resultSet = preparedStatement.executeQuery(query);

                dataTable.setModel(new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                ((DefaultTableModel) dataTable.getModel()).addColumn("Name");
                ((DefaultTableModel) dataTable.getModel()).addColumn("Total Orders");
                ((DefaultTableModel) dataTable.getModel()).addColumn("Orders Value");

                while (resultSet.next()) {
                    String productName = resultSet.getString(1);
                    int remainingDays = resultSet.getInt(2);
                    int sum = resultSet.getInt(3);

                    // can add a check here to set a threshold
                    ((DefaultTableModel) dataTable.getModel()).addRow(new Object[]{productName, remainingDays,sum});
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
                operatorGraph orderGraphUI = new operatorGraph();
                orderGraphUI.setVisible(true);
            }
        });
    }
}