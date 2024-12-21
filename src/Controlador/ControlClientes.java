
package Controlador;
import Modelo.Clientes;
import Modelo.ConsultarClientes;
import java.awt.event.ActionEvent;
import vista.frmClientes;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControlClientes implements ActionListener {
    private final Clientes modelo;
    private final ConsultarClientes consultas;
    private final frmClientes vista;
    private DefaultTableModel modeloTabla;

    public ControlClientes(Clientes modelo, ConsultarClientes consultas, frmClientes vista) {
        this.modelo = modelo;
        this.consultas = consultas;
        this.vista = vista;
        
                      
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        
        inicializarTabla();
        
        
    }    
    
    public void iniciar() {
        vista.setTitle("Clientes");
        vista.setLocationRelativeTo(null);
        vista.txtId.setVisible(false);
        cargarTabla();
    }
    
    
     private void inicializarTabla() {
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloTabla.addColumn("id");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("ruc");

        vista.tblClientes.setModel(modeloTabla);
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Clientes> lista = consultas.listarTodos();

        if (lista != null) {
            for (Clientes prv : lista) {
                Object[] fila = {
                    prv.getId(),
                    prv.getDescripcion(),
                    prv.getDireccion(),
                    prv.getTelefono(),};
                    prv.getRuc();
                modeloTabla.addRow(fila);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardar) {
            modelo.setDescripcion(vista.txtDescripcion.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setRuc(Integer.parseInt(vista.txtRuc.getText()));
            modelo.setTelefono(Integer.parseInt(vista.txtTelefono.getText()));

            if (consultas.Guardar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Guardado Satisfactoriamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnModificar) {
            //modelo.setId(Integer.parseInt(vista.txtId.getText()));
            modelo.setDescripcion(vista.txtDescripcion.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
            modelo.setTelefono(Integer.parseInt(vista.txtTelefono.getText()));
            modelo.setRuc(Integer.parseInt(vista.txtRuc.getText()));
           
            
           

            if (consultas.Modificar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Modificado Satisfactoriamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            modelo.setTelefono(Integer.parseInt(vista.txtTelefono.getText()));

            if (consultas.Eliminar(modelo)) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado Satisfactoriamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnBuscar) {
             modelo.setTelefono(Integer.parseInt(vista.txtTelefono.getText()));

            if (consultas.Buscar(modelo)) {
                vista.txtDescripcion.setText(modelo.getDescripcion());
                vista.txtDireccion.setText(modelo.getDireccion());
                vista.txtRuc.setText(String.valueOf(modelo.getRuc()));
             

            } else {
                JOptionPane.showMessageDialog(null, "No se encontro registro");
                limpiar();
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            limpiar();
        }
        
           
        
        
    }

    public void limpiar() {
        vista.txtId.setText(null);
        vista.txtDescripcion.setText(null);
        vista.txtDireccion.setText(null);
        vista.txtRuc.setText(null);
        vista.txtTelefono.setText(null);
       
    }
    
    
    
    
    
    
}
