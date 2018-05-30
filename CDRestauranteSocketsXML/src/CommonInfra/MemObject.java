package CommonInfra;

/**
 * Descrição geral: este tipo de dados define uma memória genérica.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */

public abstract class MemObject {

    /**
     * Definição da memória genérica
     */

    protected int[] mem;                                // área de armazenamento

    /**
     * Construtor de variáveis
     * @param nElem Tamanho do fifo.
     */
    protected MemObject(int nElem) {
        if (nElem > 0) {
            mem = new int[nElem];
        }
    }

    /**
     * Escrita de um valor -- método virtual
     * @param val valor a escrever no fifo.
     */
    protected abstract void write(int val);

    /**
     * Leitura de um valor -- método virtual
     * @return retorna o valor a ser lido.
     */
    protected abstract int read();
}
