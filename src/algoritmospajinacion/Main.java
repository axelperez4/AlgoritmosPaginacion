package AlgoritmosPajinacion;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
            
            JFrame frame = new JFrame("Algoritmos");
            frame.setSize(600,600);
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // as per your requirement.
            MainPanel mainP = new MainPanel(); 
            
            int cantidadPaginas,cantidadFrames;
            int []paginas;
            String cadenas;
            cadenas = String.valueOf(JOptionPane.showInputDialog(null,"Ingrese cadena de peticiones. Si desea generarla aleatoriamente, dejar vacío.")).trim();
            //Para evitar que la cantidad de páginas y la longitud de la cadena ingresada difiera y se levante una excepción, 
            //pedir cadena de solicitudes y sacar el número de páginas de allí.
            if (cadenas.length() == 0){
                cantidadPaginas=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese Cantidad de Páginas"));
            }
            else {
                cantidadPaginas = cadenas.split(",").length;
            }
            cantidadFrames=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese cantidad de frames"));
            paginas= new int[cantidadPaginas];

            //Si no se ingresó cadena de solicitudes, generar una. Si se ingresó, utilizar esa.
            if (cadenas.length() == 0)
            {
                Random random = new Random();
                for(int c=0;c<cantidadPaginas;c++){
                    paginas[c]= random.nextInt(10);
                }
            }
            else {
                String[] dato = cadenas.replaceAll(" ", "").split(","); 
                for(int c=0;c<cantidadPaginas;c++){
                    paginas[c]= Integer.valueOf(dato[c]); 
                } 
            }
            
            
            Fifo fifo=new Fifo(mainP);
            fifo.setCantidadFrames(cantidadFrames);
            fifo.setCantidadPaginas(cantidadPaginas);
            fifo.setPaginas(paginas);
            fifo.fifo();

            LRU lru=new LRU(mainP);
            lru.setCantidadPaginas(cantidadPaginas);
            lru.setCantidadFrames(cantidadFrames);
            lru.setPaginas(paginas);
            lru.lru();
            
            algoritmospajinacion.Optimal opt = new algoritmospajinacion.Optimal(mainP);
            opt.setNoFrames(cantidadFrames);
            opt.setNoPages(cantidadPaginas);
            opt.setPages(paginas);
            opt.getOptimal();
            
            //Mostrar cadena generada en UI
            String stringDeSolicitudes = "";
            for (int j = 0; j < cantidadPaginas; j++) {
                stringDeSolicitudes += String.valueOf(paginas[j]) + " -> ";
            }
            stringDeSolicitudes = stringDeSolicitudes.substring(0, stringDeSolicitudes.length() - 4); //Quitamos la última flecha
            mainP.Cadena.setText(stringDeSolicitudes);
            
            //Definir el mejor algoritmo / más eficiente
            String mejores = "";
            int[] fallos = {Integer.parseInt(mainP.fifo_fallos.getText()), Integer.parseInt(mainP.lru_fallos.getText()), Integer.parseInt(mainP.opt_fallos.getText())};
            int min = Arrays.stream(fallos).min().getAsInt();
            if (Integer.parseInt(mainP.fifo_fallos.getText()) == min) {
                mejores = "FIFO, ";
            }
            if (Integer.parseInt(mainP.lru_fallos.getText()) == min) {
                mejores += "LRU, ";
            }
            if (Integer.parseInt(mainP.opt_fallos.getText()) == min) {
                mejores += "OPTIMO, ";
            }
            mejores = mejores.substring(0, mejores.length() - 2);
            mainP.mejorAlgoritmo.setText(mejores);
             
            frame.add(mainP, BorderLayout.CENTER);
            frame.setVisible(true);
            
	}

}
