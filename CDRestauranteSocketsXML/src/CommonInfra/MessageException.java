package CommonInfra;

/**
 * Este tipo de dados define uma excepcao que e lancada se a mensagem for
 * invalida.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class MessageException extends Exception {

    /**
     * Mensagem que originou a excepcao
     *
     */

    private Message msg;

    /**
     * Instanciacao de uma mensagem.
     *
     * @param errorMessage texto sinalizando a condicao de erro
     * @param msg mensagem que esta na origem da excepcao
     */
    public MessageException(String errorMessage, Message msg) {
        super(errorMessage);
        this.msg = msg;
    }

    /**
     * Obtencao da mensagem que originou a excepcao.
     *
     * @return mensagem mensagem que originou o erro.
     */
    public Message getMessageVal() {
        return (msg);
    }
}
