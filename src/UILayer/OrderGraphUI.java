package UILayer;

import DBLayer.DatabaseConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

public class OrderGraphUI extends JFrame {
//MONTHLY WEEKLY AND DAILY REPORT
    private JRadioButton dailyRadioButton;
    private JRadioButton weeklyRadioButton;
    private JLabel totalsalessum;
    private double totalsales=0;

    private JRadioButton monthlyRadioButton;
    private JButton generateButton;
    private JButton backButton;
    private ChartPanel chartPanel;
    private JTable dataTable;

    // JDBC database URL, username, and password of MySQL server
    private static final String DB_URL = "jdbc:mysql://your_database_url:3306/your_database_name";
    private static final String USER = "ostechnix";
    private static final String PASSWORD = "Password123#@!";

    public OrderGraphUI() {
        setTitle("Order Graph");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dailyRadioButton = new JRadioButton("Daily");
        weeklyRadioButton = new JRadioButton("Weekly");
        monthlyRadioButton = new JRadioButton("Monthly");
        totalsalessum=new JLabel();
        totalsalessum.setFont(new Font("Arial", Font.BOLD, 24));
        totalsalessum.setBackground(new Color(6, 56, 6));
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(dailyRadioButton);
        buttonGroup.add(weeklyRadioButton);
        buttonGroup.add(monthlyRadioButton);

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
        radioPanel.add(dailyRadioButton);
        radioPanel.add(weeklyRadioButton);
        radioPanel.add(monthlyRadioButton);
        radioPanel.add(generateButton);

        add(radioPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.EAST);
        add(new JScrollPane(dataTable), BorderLayout.CENTER);
        add(ButtonPanel,BorderLayout.WEST);
        add(infoPanel,BorderLayout.SOUTH);


        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateGraph();
            }
        });

        weeklyRadioButton.setSelected(true);
    }
void updateSalessum(){
        totalsalessum.setText("TOTAL SALES "+totalsales);
}
    private void generateGraph() {
totalsales=0;
        if (weeklyRadioButton.isSelected()) {

            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "SELECT order_date, DAYNAME(order_date) AS day_name, IFNULL(SUM(total), 0) AS total " +
                        "FROM order_t " +
                        "WHERE order_date BETWEEN CURDATE() - INTERVAL 6 DAY AND CURDATE() " +
                        "GROUP BY order_date, day_name";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                     DefaultCategoryDataset dataset = new DefaultCategoryDataset();

                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("Date");
                    tableModel.addColumn("Day");
                    tableModel.addColumn("Total Sale");

                    String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

                    List<Date> last7Days = new ArrayList<>();
                    Calendar calendar = Calendar.getInstance();
                    for (int i = 0; i < 7; i++) {
                        last7Days.add(calendar.getTime());
                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                    }

                    Map<String, Double> resultMap = new HashMap<>();
                    for (String day : daysOfWeek) {
                        resultMap.put(day, 0.0);
                    }

                    while (resultSet.next()) {
                        String dayName = resultSet.getString("day_name");
                        double total = resultSet.getDouble("total");
                        Date orderDate = resultSet.getDate("order_date");

                        resultMap.put(dayName, total);
                    }

                    // Add resultMap data to the dataset and table model for all days
                    for (Date date : last7Days) {
                        String dayName = new SimpleDateFormat("EEEE").format(date);
                        double total = resultMap.getOrDefault(dayName, 0.0);

                        // Add to dataset
                //        dataset.addValue(total, "Total Sales", dayName);
                        String formatted = new SimpleDateFormat("dd MMM yyyy").format(date);

                        // Add to table model
                        totalsales+=total;
                        tableModel.addRow(new Object[]{ dayName, total,formatted});
                    }

                    // Create chart
                    //JFreeChart chart = createChart(dataset);
                    //chartPanel.setChart(chart);

                    // Set table model to JTable
                    dataTable.setModel(tableModel);
                    createAndDisplayBarChart(tableModel);
                    updateSalessum();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (monthlyRadioButton.isSelected()) {
            try (Connection connection = DatabaseConnection.getConnection()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = dateFormat.format(new Date());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            String thirtyDaysAgo = dateFormat.format(calendar.getTime());

            String query = "SELECT order_date, SUM(total) AS total " +
                    "FROM order_t " +
                    "WHERE order_date BETWEEN ? AND ? " +
                    "GROUP BY order_date";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, thirtyDaysAgo);
                preparedStatement.setString(2, todayDate);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Map<String, Double> salesMap = new HashMap<>();

                    while (resultSet.next()) {
                        String date = resultSet.getString("order_date");
                        double total = resultSet.getDouble("total");

                        salesMap.put(date, total);
                    }

                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("Date");
                    tableModel.addColumn("Total Sale");

                    // Generate a date range for the last 30 days
                    calendar.setTime(new Date());
                    for (int i = 0; i < 30; i++) {
                        String currentDate = dateFormat.format(calendar.getTime());
                        double total = salesMap.getOrDefault(currentDate, 0.0);

                        // Add to table model
                        totalsales += total;
                        tableModel.addRow(new Object[]{currentDate, total});

                        calendar.add(Calendar.DAY_OF_MONTH, -1);
                    }

                    dataTable.setModel(tableModel);
                    updateSalessum();
                    createAndDisplayBarChart(tableModel);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        }
        else{
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "SELECT " +
                        "    oi.product_name, " +
                        "    SUM(oi.item_price) AS total_sum " +
                        "FROM " +
                        "    order_t o " +
                        "        JOIN order_t_Item oi ON o.id = oi.order_id " +
                        "WHERE " +
                        "        o.order_date = CURDATE() " +
                        "GROUP BY " +
                        "    oi.product_name";

                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("Product Name");
                    tableModel.addColumn("Total Sale");

                    while (resultSet.next()) {
                        String productName = resultSet.getString("product_name");
                        double totalSum = resultSet.getDouble("total_sum");

                        // Add to table model
                        totalsales += totalSum;
                        tableModel.addRow(new Object[]{productName, totalSum});
                    }

                    // Set table model to JTable
                    dataTable.setModel(tableModel);
                    updateSalessum();
                    createAndDisplayBarChart(tableModel);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }

    private void createAndDisplayBarChart(DefaultTableModel tableModel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int rowCount = tableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String date = (String) tableModel.getValueAt(i, 0); // Assuming date is in the first column
            double total = (double) tableModel.getValueAt(i, 1); // Assuming total is in the second column
     dataset.addValue(total, "Total Sales", date);
        }

        JFreeChart chart = createChart(dataset);

        chartPanel.setChart(chart);
    }
    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Sales Chart",
                "Time",
                "Total Sales",
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
                OrderGraphUI orderGraphUI = new OrderGraphUI();
                orderGraphUI.setVisible(true);
            }
        });
    }
}
