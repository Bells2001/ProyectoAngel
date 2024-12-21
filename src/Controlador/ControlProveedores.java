package Controlador;
import Modelo.Clientes;
import Modelo.ConsultarProveedores;
import Modelo.Proveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.frmProveedores;


public class ControlProveedores implements ActionListener {
    private final Proveedores modelo;
    private final ConsultarProveedores consultas;
    private final frmProveedores vista;
     private DefaultTableModel modeloTabla;

    public ControlProveedores(Proveedores modelo, ConsultarProveedores consultas, frmProveedores  vista) {
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
        vista.setTitle("Proveedores");
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
       

        vista.tblProveedores.setModel(modeloTabla);
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Proveedores> lista = consultas.listarTodos() ;

        if (lista != null) {
            for (Proveedores prv : lista) {
                Object[] fila = {
                    prv.getId(),
                    prv.getDescripcion(),
                    prv.getDireccion(),
                    prv.getTelefono(),};
                    
                modeloTabla.addRow(fila);
            }
        }
    }


    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnGuardar) {
            modelo.setDescripcion(vista.txtDescripcion.getText());
            modelo.setDireccion(vista.txtDireccion.getText());
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
        vista.txtTelefono.setText(null);
       
    }
    
    
    
    
    
    
}
