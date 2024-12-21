
package mainCRUD;
import Modelo.Clientes;
import Modelo.ConsultarClientes;
import vista.frmClientes;
import Controlador.ControlClientes;

public class clienteCRUD {

   
    public static void main(String[] args) {
     
        Clientes modelo = new Clientes();
        ConsultarClientes consulta = new ConsultarClientes();
        frmClientes vista = new frmClientes();

        ControlClientes ctrl = new ControlClientes(modelo, consulta, vista);
        ctrl.iniciar();
        vista.setVisible(true);
    }
    
}
