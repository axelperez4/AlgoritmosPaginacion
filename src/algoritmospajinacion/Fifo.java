package AlgoritmosPajinacion;

public class Fifo {

	private int cantidadPaginas;
	private int cantidadFrames;
	int []paginas;
	int [][]matriz;
	int []fallos;
	int auxiliar=0;
        public MainPanel frame;
	
	public Fifo(MainPanel frame){
            this.frame = frame;
            System.out.println("FIFO\n");
	}


	public void setPaginas(int[] paginas) {
		this.paginas = paginas;
	}


	public void setCantidadPaginas(int cantidadPaginas) {
		this.cantidadPaginas = cantidadPaginas;
	}

	public void setCantidadFrames(int cantidadFrames) {
		this.cantidadFrames = cantidadFrames;
	}
	
	public void iniciarxfallos(){
		for(int i=0;i<cantidadPaginas;i++){
			fallos[i]=0;
		}
	}
		
	public void siguiente(){
		auxiliar++;
		if(auxiliar==cantidadFrames){//si llega al final de los frames regresa al primer frame
			auxiliar=0;
		}
	}
	
	public boolean buscar(int paginaAcutal){
		boolean bandera=false;
		for(int j=0;j<cantidadFrames;j++){
			//recorre todos los frames de una pagina determinada
			if(paginas[paginaAcutal]==matriz[j][paginaAcutal]){
				//si el valor de la pagina actual existe en algun frame la bandera se le asigna verdadero
				bandera=true;
			}
		}
		return bandera;
	}
	
	
	public void modificar(boolean encontrado,int paginaActual){
		if(!encontrado){
			for(int aux=paginaActual;aux<cantidadPaginas;aux++){
				matriz[auxiliar][aux]=paginas[paginaActual];
				fallos[paginaActual]=1;
			}
			siguiente();
		}
	}
	
	public void fifo(){
		matriz= new int[cantidadFrames][cantidadPaginas];
		fallos= new int [cantidadPaginas];
		iniciarxfallos();
		for(int i=0;i<cantidadPaginas;i++){
			modificar(buscar(i),i);
		}
		mostrarMatriz();
	}
	
	public void mostrarMatriz(){
		int cantidadFallos=0;
		for(int i=0;i<cantidadFrames;i++){
			for(int j=0;j<cantidadPaginas;j++){
				System.out.print(""+matriz[i][j]);
			}
			System.out.println();
		}
		
		for(int i=0;i<cantidadPaginas;i++){
			if(fallos[i]==1){
				cantidadFallos++;
			}
			System.out.print(""+fallos[i]);
		}
                
                double frecuencia = (double)cantidadFallos/(double)cantidadPaginas;
                double rendimiento = 1 - frecuencia;
                frame.fifo_fallos.setText(String.valueOf(cantidadFallos));
                frame.fifo_frecuencia.setText(String.format("%.2f", (frecuencia * (double)100)) + '%');
                frame.fifo_rendimiento.setText(String.format("%.2f", (rendimiento * (double)100)) + '%');
		System.out.println("\n\nFallos encontrados: "+cantidadFallos);
	}
	
}
