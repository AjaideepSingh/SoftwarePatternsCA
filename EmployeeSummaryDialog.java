import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class EmployeeSummaryDialog extends JDialog implements ActionListener {
	private final Vector<Object> allEmployees;
	private JButton back;
	
	public EmployeeSummaryDialog(Vector<Object> allEmployees) {
		setTitle("Employee Summary");
		setModal(true);
		this.allEmployees = allEmployees;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JScrollPane scrollPane = new JScrollPane(summaryPane());
		setContentPane(scrollPane);
		setSize(850, 500);
		setLocation(350, 250);
		setVisible(true);
	}

	public Container summaryPane() {
		JPanel summaryDialog = new JPanel(new MigLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JTable employeeTable;
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		Vector<String> header = new Vector<>();
		String[] headerName = {"ID","PPS Number","Surname","First Name","Gender","Department","Salary","Full Time" };
		int[] colWidth = {15,100,120,120,50,120,80,80};
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		for (String s : headerName) {
			header.addElement(s);
		}
		DefaultTableModel tableModel = new DefaultTableModel(allEmployees.size(), header.size()) {
			public Class getColumnClass(int c) {
				return switch (c) {
					case 0 -> Integer.class;
					case 4 -> Character.class;
					case 6 -> Double.class;
					case 7 -> Boolean.class;
					default -> String.class;
				};
			}
		};
		employeeTable = new JTable(tableModel);
		for (int i = 0; i < employeeTable.getColumnCount(); i++) {
			employeeTable.getColumn(headerName[i]).setMinWidth(colWidth[i]);
		}
		employeeTable.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		employeeTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		employeeTable.getColumnModel().getColumn(6).setCellRenderer(new DecimalFormatRenderer());
		employeeTable.setEnabled(false);
		employeeTable.setPreferredScrollableViewportSize(new Dimension(800, (15 * employeeTable.getRowCount() + 15)));
		employeeTable.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(employeeTable);
		buttonPanel.add(back = new JButton("Back"));
		back.addActionListener(this);
		back.setToolTipText("Return to main screen");
		summaryDialog.add(buttonPanel,"growx, pushx, wrap");
		summaryDialog.add(scrollPane,"growx, pushx, wrap");
		scrollPane.setBorder(BorderFactory.createTitledBorder("Employee Details"));
		return summaryDialog;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back){
			dispose();
		}
	}

	static class DecimalFormatRenderer extends DefaultTableCellRenderer {
		 private static final DecimalFormat format = new DecimalFormat(
		 "\u20ac ###,###,##0.00" );
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			 JLabel label = (JLabel) c;
			 label.setHorizontalAlignment(JLabel.RIGHT);
			value = format.format(value);
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	}
}
