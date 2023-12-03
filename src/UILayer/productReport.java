package UILayer;

import DBLayer.DatabaseConnection;
import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.concurrent.TimeUnit;
/**
 * The productReport class represents a JFrame for displaying bar charts of product expiration dates.
 */
public class productReport extends JFrame {
//expiration count down
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
    
    /**
     * Creates a new instance of the productReport class.
     */
    public productReport() {
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

backButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      performBack(e);
    }
});

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateGraph();
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    /**
     * Handles the action when the Back button is clicked.
     *
     * @param e The action event.
     */
    private void performBack(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Managermenu().setVisible(true);
            }

        });
        this.dispose();

    }
    /**
     * Updates the total sales label.
     */
    void updateSalessum(){
        totalsalessum.setText("TOTAL SALES "+totalsales);
    }
    
    /**
     * Generates a bar chart based on product expiration dates.
     */
    private void generateGraph() {
        totalsales = 0;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT  name, expiryDate FROM Product ORDER BY expiryDate ASC";
            try (Statement preparedStatement = connection.createStatement()) {
                ResultSet resultSet = preparedStatement.executeQuery(query);

                dataTable.setModel(new DefaultTableModel() {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                ((DefaultTableModel) dataTable.getModel()).addColumn("Product Name");
                ((DefaultTableModel) dataTable.getModel()).addColumn("Days Left");

                while (resultSet.next()) {

                    String productName = resultSet.getString("name");
                    Date expiryDate = resultSet.getDate("expiryDate");

                    int daysLeft = calculateDaysLeft(expiryDate);
                    System.out.println(daysLeft);
if(daysLeft!=0)
                    ((DefaultTableModel) dataTable.getModel()).addRow(new Object[]{dataTable.getRowCount()+") "+productName, daysLeft});
                }


                updateSalessum();
                createAndDisplayBarChart(((DefaultTableModel) dataTable.getModel()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Calculates the number of days left until the specified expiration date.
     *
     * @param expiryDate The expiration date of the product.
     * @return The number of days left till expiration date.
     */
    private int calculateDaysLeft(Date expiryDate) {
        java.util.Date currentDate = new java.util.Date();

        long differenceInMillis = expiryDate.getTime() - currentDate.getTime();

        int daysLeft = (int) TimeUnit.MILLISECONDS.toDays(differenceInMillis);

        return daysLeft;
    }

    /**
     * Creates and displays a bar chart based on the given table model.
     *
     * @param tableModel The table model containing data.
     */
    private void createAndDisplayBarChart(DefaultTableModel tableModel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String name = (String) tableModel.getValueAt(i, 0);
            int daysLeft = (int) tableModel.getValueAt(i, 1);


            dataset.addValue(daysLeft, "Total Days",name);

            System.out.println("Days Left for " + name + ": " + daysLeft);
        }

        JFreeChart chart = createChart(dataset);

        chartPanel.setChart(chart);
    }
    
    /**
     * Creates a JFreeChart bar chart based on the given data set.
     *
     * @param dataset The data set for the bar chart.
     * @return The created JFreeChart object.
     */
    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Expiration Date left",
                "Name",
                "Remaining Days",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDrawBarOutline(false);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryMargin(0.2);

        return chart;
    }
    
     /**
     * Main method.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                productReport orderGraphUI = new productReport();
                orderGraphUI.setVisible(true);
            }
        });
    }
}
