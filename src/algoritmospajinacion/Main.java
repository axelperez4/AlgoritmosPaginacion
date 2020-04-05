package AlgoritmosPajinacion;

import java.awt.BorderLayout;
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

            cantidadPaginas=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese Cantidad de Páginas"));
            cantidadFrames=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese cantidad de frames"));

            paginas= new int[cantidadPaginas];

            Random random = new Random();
            for(int c=0;c<cantidadPaginas;c++){
                    paginas[c]= random.nextInt(10);
                    System.out.println(paginas[c]);
            }
            System.out.println("===========================================");
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
            
            //Mostrar cadena generada en UI
            String stringDeSolicitudes = "";
            for (int j = 0; j < cantidadPaginas; j++) {
                stringDeSolicitudes += String.valueOf(paginas[j]) + " -> ";
            }
            stringDeSolicitudes = stringDeSolicitudes.substring(0, stringDeSolicitudes.length() - 4); //Quitamos la última flecha
            mainP.Cadena.setText(stringDeSolicitudes);
            
            //Definir el mejor algoritmo / más eficiente
            if (Integer.parseInt(mainP.fifo_fallos.getText()) < Integer.parseInt(mainP.lru_fallos.getText())) {
                mainP.mejorAlgoritmo.setText("FIFO");
            }
            else { mainP.mejorAlgoritmo.setText("LRU"); }
            
            frame.add(mainP, BorderLayout.CENTER);
            frame.setVisible(true);
            
	}

}
