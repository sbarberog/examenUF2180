package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Centro;
import modelo.Departamento;

import javax.swing.ButtonGroup;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class DialogoAnadirDepartamento extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1840511636229303891L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodDepto;
	private JComboBox<Centro> cmbCodCentro;
	private JTextField txtNombre;
	private final ButtonGroup grupoTipoDir = new ButtonGroup();
	private JRadioButton rbtP;
	private JRadioButton rbtF;
	private JSpinner spinner;
	private Controlador controlador;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DialogoAnadirDepartamento dialog = new DialogoAnadirDepartamento();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public DialogoAnadirDepartamento() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Insertar Departamento");
		setBounds(100, 100, 460, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[569.00]", "[285.00]"));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Detalles del departamento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, "cell 0 0,grow");
			panel.setLayout(new MigLayout("", "[][83.00,right][grow][grow]", "[][][][][][][][][]"));
			{
				JLabel lblNewLabel_6 = new JLabel("");
				lblNewLabel_6.setIcon(new ImageIcon(DialogoAnadirDepartamento.class.getResource("/images/editar32.png")));
				panel.add(lblNewLabel_6, "cell 0 0 1 9");
			}
			{
				JLabel lblNewLabel = new JLabel("Código:");
				panel.add(lblNewLabel, "cell 1 0,alignx trailing");
			}
			{
				txtCodDepto = new JTextField();
				panel.add(txtCodDepto, "cell 2 0 2 1,growx");
				txtCodDepto.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Centro:");
				panel.add(lblNewLabel_1, "cell 1 2,alignx trailing");
			}
			{
				cmbCodCentro = new JComboBox<Centro>();
				panel.add(cmbCodCentro, "cell 2 2 2 1,growx");
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Tipo Dirección:");
				panel.add(lblNewLabel_2, "cell 1 4");
			}
			{
				rbtP = new JRadioButton("Propiedad");
				rbtP.setActionCommand("P");
				grupoTipoDir.add(rbtP);
				rbtP.setSelected(true);
				panel.add(rbtP, "cell 2 4");
			}
			{
				rbtF = new JRadioButton("En funciones");
				rbtF.setActionCommand("F");
				grupoTipoDir.add(rbtF);
				panel.add(rbtF, "cell 3 4");
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Presupuesto:");
				panel.add(lblNewLabel_3, "cell 1 6");
			}
			{
				spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(5, 1, 100, 1));
				panel.add(spinner, "cell 2 6");
			}
			{
				JLabel lblNewLabel_5 = new JLabel("(en miles de €)");
				panel.add(lblNewLabel_5, "cell 3 6");
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Nombre:");
				panel.add(lblNewLabel_4, "cell 1 8,alignx trailing");
			}
			{
				txtNombre = new JTextField();
				panel.add(txtNombre, "cell 2 8 2 1,growx");
				txtNombre.setColumns(10);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void recogerDatos() {
		try {
			Departamento d = new Departamento();
			d.setCodDepartamento(Integer.parseInt(txtCodDepto.getText()));
			Centro c=(Centro)cmbCodCentro.getSelectedItem();
			d.setCodCentro(c.getCod_centro());
			d.setTipoDir(grupoTipoDir.getSelection().getActionCommand());
			d.setPresupuesto((int) spinner.getValue());
			d.setNombre(txtNombre.getText());
	
			controlador.insertaDepartamento(d);
			JOptionPane.showMessageDialog(this, "Insercion del departamento correcta");
			setVisible(false);
			vaciar();
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(contentPanel, e, "Debe introducir un número correcto", JOptionPane.ERROR_MESSAGE);
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(contentPanel, e, "Debe rellenar todos los campos", JOptionPane.ERROR_MESSAGE);
		}catch(SQLException e) {
			JOptionPane.showMessageDialog(this, "No se ha podido insertar el departamento", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void vaciar() {
		txtCodDepto.setText("");
//		cmbCodCentro.setText("");
		rbtP.setSelected(true);
		rbtF.setSelected(false);
		spinner.setValue(5);
		txtNombre.setText("");
		
		
	}

	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
		
	}

	public void setListaCentros(ArrayList<Centro> lista) {
		// se encarga de recoger la lista y añadirla al JComboBox
		cmbCodCentro.removeAllItems();
		for(Centro c: lista) {
			cmbCodCentro.addItem(c);
		}
		
	}
}
