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
import java.util.Calendar;

public class salesBydate extends JFrame {

    private JRadioButton dailyRadioButton;
    private JDateChooser from;
    private JLabel totalsalessum;
    private double totalsales=0;

    private JDateChooser tofield;
    private JButton generateButton;
    private JButton backButton;
    private ChartPanel chartPanel;
    private JTable dataTable;

//reports with fields for to and from

    public salesBydate() {
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
        radioPanel.add(new JLabel("  End Date: "));
        radioPanel.add(tofield);
        radioPanel.add(new JLabel("  Start Date: "));
        radioPanel.add(from);
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

    }
    void updateSalessum(){
        totalsalessum.setText("TOTAL SALES "+totalsales);
    }
    private void generateGraph() {
        totalsales=0;


    {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "SELECT " +
                        "    oi.product_name, " +
                        "    SUM(oi.item_price) AS total_sum " +
                        "FROM " +
                        "    order_t o " +
                        "        JOIN order_t_Item oi ON o.id = oi.order_id " +
                        "WHERE " +
                        "        o.order_date BETWEEN ? AND ? " +
                        "GROUP BY " +
                        "    oi.product_name";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                java.util.Date fromDateUtil = from.getDate();
                java.util.Date toDateUtil = tofield.getDate();
                java.sql.Date fromDate;
                Date toDate;
                if (fromDateUtil != null && toDateUtil != null )    {
                    // Convert to java.sql.Date if the check is successful

                    fromDate = new java.sql.Date(fromDateUtil.getTime());
                  toDate = new java.sql.Date(toDateUtil.getTime());
                    Calendar fromCalendar = Calendar.getInstance();
                    fromCalendar.setTime(fromDateUtil);
                    int fromYear = fromCalendar.get(Calendar.YEAR);
                    int fromMonth = fromCalendar.get(Calendar.MONTH);
                    int fromDay = fromCalendar.get(Calendar.DAY_OF_MONTH);

                    Calendar toCalendar = Calendar.getInstance();
                    toCalendar.setTime(toDateUtil);
                    int toYear = toCalendar.get(Calendar.YEAR);
                    int toMonth = toCalendar.get(Calendar.MONTH);
                    int toDay = toCalendar.get(Calendar.DAY_OF_MONTH);

                    if (toYear > fromYear || (toYear == fromYear && (toMonth > fromMonth || (toMonth == fromMonth && toDay >= fromDay)))) {
                        preparedStatement.setDate(1, fromDate);

                        preparedStatement.setDate(2, toDate);

                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Invalid date range", "Date Range Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    // Continue with your logic, for example, execute the query
                    // ...
                }
                else {
                    // Display an error message or take appropriate action
                    JOptionPane.showMessageDialog(this, "Invalid date range", "Date Range Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                try {
                    ResultSet resultSet = preparedStatement.executeQuery();

                    dataTable.setModel(new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    ((DefaultTableModel) dataTable.getModel()).addColumn("Product Name");
                    ((DefaultTableModel) dataTable.getModel()).addColumn("Total Sale");

                    while (resultSet.next()) {
                        String productName = resultSet.getString("product_name");
                        double totalSum = resultSet.getDouble("total_sum");

                        totalsales += totalSum;
                        ((DefaultTableModel) dataTable.getModel()).addRow(new Object[]{productName, totalSum});
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
                "Name",
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
                salesBydate orderGraphUI = new salesBydate();
                orderGraphUI.setVisible(true);
            }
        });
    }
}
