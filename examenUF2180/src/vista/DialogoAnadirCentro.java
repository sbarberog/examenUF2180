package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Centro;
import net.miginfocom.swing.MigLayout;

public class DialogoAnadirCentro extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodCentro;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private Controlador controlador;


	/**
	 * Create the dialog.
	 */
	public DialogoAnadirCentro() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Detalles del centro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			((TitledBorder)  panel.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(panel, "cell 0 0,grow");
			panel.setLayout(new MigLayout("", "[][][grow]", "[][][][][]"));
			{
				JLabel lblNewLabel_3 = new JLabel("");
				lblNewLabel_3.setIcon(new ImageIcon(DialogoAnadirCentro.class.getResource("/images/editar32.png")));
				panel.add(lblNewLabel_3, "cell 0 0 1 5");
			}
			{
				JLabel lblNewLabel = new JLabel("C\u00F3digo:");
				panel.add(lblNewLabel, "cell 1 0,alignx trailing");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtCodCentro = new JTextField();
				panel.add(txtCodCentro, "cell 2 0,growx");
				txtCodCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtCodCentro.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre:");
				panel.add(lblNewLabel_1, "cell 1 2,alignx trailing");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtNombre = new JTextField();
				panel.add(txtNombre, "cell 2 2,growx");
				txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtNombre.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Direcci\u00F3n:");
				panel.add(lblNewLabel_2, "cell 1 4,alignx trailing");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtDireccion = new JTextField();
				panel.add(txtDireccion, "cell 2 4,growx");
				txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtDireccion.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						recogerDatos();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	protected void recogerDatos() {
		int cod_centro = Integer.parseInt(txtCodCentro.getText());
		String nombre = txtNombre.getText();
		String direccion = txtDireccion.getText();
		Centro centro = new Centro(cod_centro, nombre, direccion);
		controlador.insertaCentro(centro);
		
	}


	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

}
