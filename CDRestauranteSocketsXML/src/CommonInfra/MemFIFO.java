package CommonInfra;

public class MemFIFO extends MemObject {

    /**
     * Definição da memória de tipo fifo.
     *
     * @author Pedro Pires
     * @author Dimitri Silva
     */
    private int inPnt, outPnt;                              // ponteiros de inserção e de retirada de um valor
    private boolean empty;                                  // sinalização de memória vazia

    /**
     *Construtor ca classe.
     * @param nElem tamanho do fifo a criar.
     */
    public MemFIFO(int nElem) {
        super(nElem);
        inPnt = outPnt = 0;
        empty = true;
    }

    /**
     * Escrita de um valor no fifo.
     *
     * @param val Int a ser escrito neste tipo de memória.
     */
    @Override
    public void write(int val) {
        if (empty || (inPnt != outPnt)) {
            mem[inPnt] = val;
            inPnt = (inPnt + 1) % mem.length;
            empty = false;
        }
    }

    /**
     * Leitura de um valor do fifo.
     *
     * @return Int lido da memória.
     */
    @Override
    public int read() {
        int val = -1;

        if (!empty) {
            val = mem[outPnt];
            outPnt = (outPnt + 1) % mem.length;
            empty = (inPnt==outPnt);
        }
        return val;
    }

}
