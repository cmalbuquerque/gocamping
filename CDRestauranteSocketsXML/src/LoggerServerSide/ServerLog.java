package LoggerServerSide;

import genclass.GenericIO;
import java.net.SocketTimeoutException;
import ServerSideGeral.ServerCom;
import CommonInfra.Definitions;

/**
 * Este tipo de dados simula uma solução do lado do servidor do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2 (replicação do
 * servidor). A comunicação baseia-se em passagem de mensagens sobre sockets
 * usando o protocolo TCP.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class ServerLog {

    /**
     * Número do port de escuta do serviço a ser prestado (4000, por defeito)
     *
     * @serialField portNumb Número da porta do serviço
     */
    private static final int portNumb = Definitions.LoggerPort;  //22660 a 22669
    /**
     * boolean sinaliza se deve esperar por coneção ou não. Pode ter os valores:
     * <ul> <li> true, se for para esperar</li>
     * <li> false, em caso contrário</li> </ul>
     */
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     * Programa principal do servidor do logger.
     */
    public static void main(String[] args) {
        LogWriter log;                                    // Logger (representa o serviço a ser prestado)
        LogInterface logInterface;                      // interface ao Logger
        ServerCom scon, sconi;                               // canais de comunicação
        LogClientProxy cliProxy;                                // thread agente prestador do serviço

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        log = new LogWriter(Definitions.LOGFILENAME);            // activação do serviço
        // activação do serviço
        logInterface = new LogInterface(log);        // activação do interface com o serviço
        GenericIO.writelnString("O serviço do Log foi estabelecido!");
        GenericIO.writelnString("O servidor do Log esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();                          // entrada em processo de escuta
                cliProxy = new LogClientProxy(sconi, logInterface);  // lançamento do agente prestador do serviço
                cliProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        scon.end();                                         // terminação de operações
        GenericIO.writelnString("O servidor foi desactivado.");
    }
}
