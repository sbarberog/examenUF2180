package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Centro;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMostrarCentros extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Controlador controlador;

	/**
	 * Create the frame.
	 */
	public VentanaMostrarCentros() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][grow][grow]"));
		
		JLabel lblNewLabel = new JLabel("Listado de Centros:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNewLabel, "cell 0 0 2 1");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 1 1,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo del centro", "Nombre", "Direcci\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(105);
		table.getColumnModel().getColumn(1).setPreferredWidth(131);
		table.getColumnModel().getColumn(2).setPreferredWidth(190);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 1 2,grow");
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel.add(btnNewButton);
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

	public void setListaCentros(ArrayList<Centro> lista) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (Centro centro : lista) {
			Object fila [] = {
					centro.getCod_centro(), centro.getNombre(), centro.getDireccion()
			};
			modelo.addRow(fila);
		}
	}

}
