package BarServerSide;

import genclass.GenericIO;
import java.net.SocketTimeoutException;
import ServerSideGeral.ServerCom;
import CommonInfra.Definitions;
import Stubs.LoggerStub;

/**
 * Este tipo de dados simula uma solução do lado do servidor do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2 (replicação do
 * servidor). A comunicação baseia-se em passagem de mensagens sobre sockets
 * usando o protocolo TCP.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class ServerBar {

    /**
     * Número do port de escuta do serviço a ser prestado (4000, por defeito)
     *
     * @serialField portNumb
     */
    private static final int portNumb = Definitions.BarPort;  //22660 a 22669
    /**
     * boolean sinaliza se deve esperar por coneção ou não. Pode ter os valores:
     * <ul> <li> true, se for para esperar</li>
     * <li> false, em caso contrário</li> </ul>
     */
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     * Programa principal do servidor do Bar.
     */
    public static void main(String[] args) {
        Bar bar;                                    // Bar (representa o serviço a ser prestado)
        BarInterface barInterface;                      // interface ao Bar
        ServerCom scon, sconi;                               // canais de comunicação
        BarClientProxy cliProxy;                                // thread agente prestador do serviço

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        bar = new Bar(Definitions.MAXSTUDENTS, // activação do serviço
                new LoggerStub(Definitions.LoggerIP, Definitions.LoggerPort));
        // activação do serviço
        barInterface = new BarInterface(bar);        // activação do interface com o serviço
        GenericIO.writelnString("O serviço do Bar foi estabelecido!");
        GenericIO.writelnString("O servidor do Bar esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();                          // entrada em processo de escuta
                cliProxy = new BarClientProxy(sconi, barInterface);  // lançamento do agente prestador do serviço
                cliProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        scon.end();                                         // terminação de operações
        GenericIO.writelnString("O servidor foi desactivado.");
    }
}
