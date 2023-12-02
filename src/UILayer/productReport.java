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

    }

    private void performBack(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Managermenu().setVisible(true);
            }

        });
        this.dispose();

    }

    void updateSalessum(){
        totalsalessum.setText("TOTAL SALES "+totalsales);
    }
    // ...

    private void generateGraph() {
        totalsales = 0;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT name, expiryDate FROM Product ORDER BY expiryDate ASC ";
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

                    // Calculate the number of days left
                    long daysLeft = calculateDaysLeft(expiryDate);
                    System.out.println(daysLeft);
// can add a check here to set a threshold
                    ((DefaultTableModel) dataTable.getModel()).addRow(new Object[]{productName, daysLeft});
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

    private long calculateDaysLeft(Date expiryDate) {
        java.util.Date currentDate = new java.util.Date();

        long differenceInMillis = expiryDate.getTime() - currentDate.getTime();

        long daysLeft = differenceInMillis / (24 * 60 * 60 * 1000);

        return daysLeft;
    }



    private void createAndDisplayBarChart(DefaultTableModel tableModel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String date = (String) tableModel.getValueAt(i, 0);
           long total = (long) tableModel.getValueAt(i, 1);
            System.out.println("total graph"+total);
            dataset.addValue(total, "Total Sales", date);
        }

        JFreeChart chart = createChart(dataset);

        chartPanel.setChart(chart);
    }
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
