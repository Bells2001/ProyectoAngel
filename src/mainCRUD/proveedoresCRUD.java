
package mainCRUD;

import Controlador.ControlProveedores;
import Modelo.Proveedores;
import Modelo.ConsultarProveedores;
import vista.frmProveedores;


public class proveedoresCRUD {

  
    public static void main(String[] args) {
        Proveedores modelo = new Proveedores();
        ConsultarProveedores consulta = new ConsultarProveedores();
        frmProveedores vista = new frmProveedores();

        ControlProveedores ctrl = new ControlProveedores(modelo, consulta, vista);
        ctrl.iniciar();
        vista.setVisible(true);
        
    }
    
}
