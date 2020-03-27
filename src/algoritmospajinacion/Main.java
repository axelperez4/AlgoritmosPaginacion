package AlgoritmosPajinacion;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
            
            JFrame frame = new JFrame("Algoritmos");
            frame.setSize(800,500);
            frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // as per your requirement.
            MainPanel mainP = new MainPanel();
            
            // TODO Auto-generated method stub
            int cantidadPaginas,cantidadFrames;
            int []paginas;

            cantidadPaginas=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese Cantidad de Páginas"));
            cantidadFrames=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese cantidad de frames"));

            paginas= new int[cantidadPaginas];

            for(int c=0;c<cantidadPaginas;c++){
                    paginas[c]=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese valor de paginas ["+(c+1)+"]"));
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
                
            frame.add(mainP, BorderLayout.CENTER);
            frame.setVisible(true);
            
	}

}
