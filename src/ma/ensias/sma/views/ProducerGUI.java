package ma.ensias.sma.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import jade.wrapper.StaleProxyException;
import ma.ensias.sma.agents.Producer;
import ma.ensias.sma.beans.Product;

public class ProducerGUI {
	
	Producer producer;

	private JFrame frame;
	private JTextField nameField;
	private JTextField unitPriceField;
	private JTextField unitCostField;
	private JTextField totalQuantitySoldField;
	private JTextField amountOfProfitField;
	private JTextField numberOfCustomersField;

	public void setVisible(boolean b) {
		this.frame.setVisible(b);
	}
	
	public void showAlert(String message) {
		JOptionPane.showMessageDialog(this.frame, message);
	}
	
	public void showErrorAlert(String message) {
		JOptionPane.showMessageDialog(this.frame, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/** Create the application. */
	public ProducerGUI(Producer producer) {
		initialize();
		this.producer = producer;
	}

	public void updateProductReport(int totalQuantitySold, double amountOfProfit) {
		totalQuantitySoldField.setText(Integer.toString(totalQuantitySold));
		amountOfProfitField.setText(Double.toString(amountOfProfit));
	
	}

	private void advertiseProductHandler() {
		System.out.println("Advertising the Product ...");
		String productName = nameField.getText();
		try {
			Double unitPrice = Double.parseDouble(unitPriceField.getText());
			Double unitCost = Double.parseDouble(unitCostField.getText());
			Product product = new Product(productName, unitPrice, unitCost);
			producer.advertiseProduct(product);	
		} catch (NumberFormatException e) {
			showErrorAlert("The price must be a number !");
		}

	}

	private void createConsumerHandler() {
		System.out.println("Creating the Consumer ...");
		try {
			int newNumberOfCustomers = producer.createConsumer();
			numberOfCustomersField.setText(Integer.toString(newNumberOfCustomers));		
		} catch (StaleProxyException e) {
			showErrorAlert("Stale Proxy Exception");
			e.printStackTrace();
		}
	}

	/** Initialize the contents of the frame. */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel footerPanel = new JPanel();
		frame.getContentPane().add(footerPanel, BorderLayout.SOUTH);
		
		JButton advertiseBtn = new JButton("Advertise");
		advertiseBtn.addActionListener(e -> advertiseProductHandler());
		footerPanel.add(advertiseBtn);
		
		JButton createConsumerBtn = new JButton("Create consumer Agent");
		createConsumerBtn.addActionListener(e -> createConsumerHandler());
		footerPanel.add(createConsumerBtn);
		
		JPanel mainContentPanel = new JPanel();
		frame.getContentPane().add(mainContentPanel, BorderLayout.CENTER);
		mainContentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel productPanel = new JPanel();
		mainContentPanel.add(productPanel);
		productPanel.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel nameLabel = new JLabel("Name of Product");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productPanel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		productPanel.add(nameField);
		nameField.setColumns(10);
		
		JLabel unitPriceLabel = new JLabel("Unit Price");
		unitPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productPanel.add(unitPriceLabel);
		
		unitPriceField = new JTextField();
		unitPriceField.setHorizontalAlignment(SwingConstants.CENTER);
		productPanel.add(unitPriceField);
		unitPriceField.setColumns(10);
		
		JLabel unitCostLabel = new JLabel("Unit Cost");
		unitCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		productPanel.add(unitCostLabel);
		
		unitCostField = new JTextField();
		unitCostField.setHorizontalAlignment(SwingConstants.CENTER);
		productPanel.add(unitCostField);
		unitCostField.setColumns(10);
		
		JPanel reportingPanel = new JPanel();
		mainContentPanel.add(reportingPanel);
		reportingPanel.setLayout(new GridLayout(6, 0, 0, 0));
		
		JLabel totalQuantitySoldLabel = new JLabel("Total Quantity Sold");
		totalQuantitySoldLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reportingPanel.add(totalQuantitySoldLabel);
		
		totalQuantitySoldField = new JTextField();
		totalQuantitySoldField.setHorizontalAlignment(SwingConstants.CENTER);
		totalQuantitySoldField.setEditable(false);
		totalQuantitySoldField.setText("0");
		reportingPanel.add(totalQuantitySoldField);
		totalQuantitySoldField.setColumns(10);
		
		JLabel amountOfProfitLabel = new JLabel("Amount Of Profit");
		amountOfProfitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reportingPanel.add(amountOfProfitLabel);
		
		amountOfProfitField = new JTextField();
		amountOfProfitField.setHorizontalAlignment(SwingConstants.CENTER);
		amountOfProfitField.setEditable(false);
		amountOfProfitField.setText("0.0");
		reportingPanel.add(amountOfProfitField);
		amountOfProfitField.setColumns(10);
		
		JLabel numberOfConsumersLabel = new JLabel("Number of Consumers");
		numberOfConsumersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reportingPanel.add(numberOfConsumersLabel);
		
		numberOfCustomersField = new JTextField();
		numberOfCustomersField.setHorizontalAlignment(SwingConstants.CENTER);
		numberOfCustomersField.setEditable(false);
		numberOfCustomersField.setText("0");
		reportingPanel.add(numberOfCustomersField);
		numberOfCustomersField.setColumns(10);
		
		JPanel topPanel = new JPanel();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Producer GUI");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		topPanel.add(label);
	}

}

