package TableServerSide;

import genclass.GenericIO;
import CommonInfra.Message;
import CommonInfra.MessageException;
import ServerSideGeral.ServerCom;

/**
 * Este tipo de dados define o thread agente prestador de serviço para uma
 * solução do Problema do Restaurante que implementa o modelo cliente-servidor
 * de tipo 2 (replicação do servidor). A comunicação baseia-se em passagem de
 * mensagens sobre sockets usando o protocolo TCP.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class TableClientProxy extends Thread {

    /**
     * Contador de threads lançados
     *
     * @serialField nProxy variável estática que conta o número de threads.
     */

    private static int nProxy = 0;

    /**
     * Canal de comunicação
     *
     * @serialField sconi canal de comunicação.
     */
    private ServerCom sconi;

    /**
     * Interface à Table
     *
     * @serialField tableInterface Interface à Table.
     */
    private TableInterface tableInterface;

    /**
     * Instanciação da interface à Table.
     *
     * @param sconi canal de comunicação
     * @param tabInter interface à table
     */
    public TableClientProxy(ServerCom sconi, TableInterface tabInter) {
        super("Proxy_" + TableClientProxy.getProxyId());

        this.sconi = sconi;
        this.tableInterface = tabInter;
    }

    /**
     * Ciclo de vida do thread agente prestador de serviço.
     */
    @Override
    public void run() {
        Message inMessage = null, // mensagem de entrada
                outMessage = null;                      // mensagem de saída

        inMessage = (Message) sconi.readObject();                     // ler pedido do cliente
        try {
            outMessage = tableInterface.processAndReply(inMessage);         // processá-lo
        } catch (MessageException e) {
            GenericIO.writelnString("Thread " + getName() + ": " + e.getMessage() + "!");
            GenericIO.writelnString(e.getMessageVal().toString());
            System.exit(1);
        }
        sconi.writeObject(outMessage);                                // enviar resposta ao cliente
        sconi.close();                                                // fechar canal de comunicação
    }

    /**
     * Geração do identificador da instanciação.
     *
     * @return identificador da instanciação
     */
    private static int getProxyId() {
        Class<?> cl = null;                                  // representação do tipo de dados TableClientProxy na máquina
        //   virtual de Java
        int proxyId;                                         // identificador da instanciação

        try {
            cl = Class.forName("TableServerSide.TableClientProxy");
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString("O tipo de dados ClientProxy não foi encontrado!");
            e.printStackTrace();
            System.exit(1);
        }

        synchronized (cl) {
            proxyId = nProxy;
            nProxy += 1;
        }

        return proxyId;
    }

    /**
     * Obtenção do canal de comunicação.
     *
     * @return canal de comunicação
     */
    public ServerCom getScon() {
        return sconi;
    }
}
