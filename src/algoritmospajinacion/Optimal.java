package algoritmospajinacion;

import java.util.Scanner;

/**
 *
 * @author Joshuar
 */
public class Optimal {

    private int noFrames;
    private int noPages;
    private int[] pages;
    public AlgoritmosPajinacion.MainPanel frame;

    public Optimal() {
    }

    public Optimal(AlgoritmosPajinacion.MainPanel frame) {
        this.frame = frame;
    }

    public void getOptimal() {
        Scanner in = new Scanner(System.in);
        int frames = 0;
        int pointer = 0;
        int numFault = 0;
        int ref_len;
        boolean isFull = false;
        int buffer[];
        boolean hit[];
        int fault[];
        int reference[];
        int mem_layout[][];

        frames = noFrames;
        ref_len = noPages;

        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        hit = new boolean[ref_len];
        fault = new int[ref_len];
        for (int j = 0; j < frames; j++) {
            buffer[j] = -1;
        }

        for (int i = 0; i < pages.length; i++) {
            reference[i] = pages[i];
        }
        for (int i = 0; i < ref_len; i++) {
            int search = -1;
            for (int j = 0; j < frames; j++) {
                if (buffer[j] == reference[i]) {
                    search = j;
                    hit[i] = true;
                    fault[i] = numFault;
                    break;
                }
            }

            if (search == -1) {
                if (isFull) {
                    int index[] = new int[frames];
                    boolean index_flag[] = new boolean[frames];
                    for (int j = i + 1; j < ref_len; j++) {
                        for (int k = 0; k < frames; k++) {
                            if ((reference[j] == buffer[k]) && (index_flag[k] == false)) {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    int max = index[0];
                    pointer = 0;
                    if (max == 0) {
                        max = 200;
                    }

                    for (int j = 0; j < frames; j++) {
                        if (index[j] == 0) {
                            index[j] = 200;
                        }

                        if (index[j] > max) {
                            max = index[j];
                            pointer = j;
                        }
                    }
                }
                buffer[pointer] = reference[i];
                numFault++;
                fault[i] = numFault;
                if (!isFull) {
                    pointer++;
                    if (pointer == frames) {
                        pointer = 0;
                        isFull = true;
                    }
                }
            }

            for (int j = 0; j < frames; j++) {
                mem_layout[i][j] = buffer[j];
            }
        }

        for (int i = 0; i < ref_len; i++) {
            System.out.print(reference[i] + ": Memoria es: ");
            for (int j = 0; j < frames; j++) {
                if (mem_layout[i][j] == -1) {
                    System.out.printf("%3s ", "*");
                } else {
                    System.out.printf("%3d ", mem_layout[i][j]);
                }
            }
            System.out.print(": ");
            if (hit[i]) {
                System.out.print("");
            } else {
                System.out.print("Pagina Fallo");
            }
            System.out.print(": (Numero de pagina fallo: " + fault[i] + ")");
            System.out.println();
        }
        System.out.println("Fallos encontrados: " + numFault);

        double frecuencia = (double) numFault / (double) noPages;
        double rendimiento = 1 - frecuencia;
        frame.opt_fallos.setText(String.valueOf(numFault));
        frame.opt_frecuencia.setText(String.format("%.2f", (frecuencia * (double) 100)) + '%');
        frame.opt_rendimiento.setText(String.format("%.2f", (rendimiento * (double) 100)) + '%'); 
    }

    public int getNoFrames() {
        return noFrames;
    }

    public void setNoFrames(int noFrames) {
        this.noFrames = noFrames;
    }

    public int getNoPages() {
        return noPages;
    }

    public void setNoPages(int noPages) {
        this.noPages = noPages;
    }

    public int[] getPages() {
        return pages;
    }

    public void setPages(int[] pages) {
        this.pages = pages;
    }

}
