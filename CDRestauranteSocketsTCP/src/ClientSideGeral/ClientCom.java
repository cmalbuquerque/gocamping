package ClientSideGeral;

import genclass.GenericIO;
import java.io.*;
import java.net.*;

/**
 * Este tipo de dados implementa o canal de comunicacao, do lado do cliente,
 * para uma comunicacao baseada em passagem de mensagens sobre sockets usando o
 * protocolo TCP. A transferencia de dados e baseada em objectos, um objecto de
 * cada vez.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class ClientCom {

    /**
     * Socket de comunicacao
     *
     * @serialField commSocket
     */
    private Socket commSocket = null;

    /**
     * Nome do sistema computacional onde esta localizado o servidor
     *
     * @serialField serverHostName
     */
    private String serverHostName = null;

    /**
     * Numero do port de escuta do servidor
     *
     * @serialField serverPortNumb
     */
    private int serverPortNumb;

    /**
     * Stream de entrada do canal de comunicacao
     *
     * @serialField in
     */
    private ObjectInputStream in = null;

    /**
     * Stream de saida do canal de comunicacao
     *
     * @serialField out
     */
    private ObjectOutputStream out = null;

    /**
     * Instanciacao de um canal de comunicacao.
     *
     * @param hostName nome do sistema computacional onde esta localizado o
     * servidor
     * @param portNumb numero do port de escuta do servidor
     */
    public ClientCom(String hostName, int portNumb) {
        serverHostName = hostName;
        serverPortNumb = portNumb;
    }

    /**
     * Abertura do canal de comunicacao. Instanciacao de um socket de
     * comunicacao e sua associacao ao enderecoo do servidor. Abertura dos
     * streams de entrada e de saida do socket.
     *
     * @return  <ul> <li>true, se o canal de comunicacao foi aberto </li>
     * <li>false, em caso contrario </li> </ul>
     */
    public boolean open() {
        boolean success = true;
        SocketAddress serverAddress = new InetSocketAddress(serverHostName, serverPortNumb);

        try {
            commSocket = new Socket();
            commSocket.connect(serverAddress);
        } catch (UnknownHostException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o nome do sistema computacional onde reside o servidor é desconhecido: "
                    + serverHostName + "!");
            e.printStackTrace();
            System.exit(1);
        } catch (NoRouteToHostException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o nome do sistema computacional onde reside o servidor é inatingível: "
                    + serverHostName + "!");
            e.printStackTrace();
            System.exit(1);
        } catch (ConnectException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o servidor não responde em: " + serverHostName + "." + serverPortNumb + "!");
            if (e.getMessage().equals("Connection refused")) {
                success = false;
            } else {
                GenericIO.writelnString(e.getMessage() + "!");
                e.printStackTrace();
                System.exit(1);
            }
        } catch (SocketTimeoutException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - ocorreu um time out no estabelecimento da ligação a: "
                    + serverHostName + "." + serverPortNumb + "!");
            success = false;
        } catch (IOException e) // erro fatal --- outras causas
        {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - ocorreu um erro indeterminado no estabelecimento da ligação a: "
                    + serverHostName + "." + serverPortNumb + "!");
            e.printStackTrace();
            System.exit(1);
        }

        if (!success) {
            return (success);
        }

        try {
            out = new ObjectOutputStream(commSocket.getOutputStream());
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível abrir o canal de saída do socket!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            in = new ObjectInputStream(commSocket.getInputStream());
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível abrir o canal de entrada do socket!");
            e.printStackTrace();
            System.exit(1);
        }

        return (success);
    }

    /**
     * Fecho do canal de comunicacao. Fecho dos streams de entrada e de saida do
     * socket. Fecho do socket de comunicacao.
     */
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível fechar o canal de entrada do socket!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            out.close();
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível fechar o canal de saída do socket!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            commSocket.close();
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - não foi possível fechar o socket de comunicação!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Leitura de um objecto do canal de comunicacao.
     *
     * @return objecto lido
     */
    public Object readObject() {
        Object fromServer = null;                            // objecto

        try {
            fromServer = in.readObject();
        } catch (InvalidClassException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o objecto lido não á passível de desserialização!");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - erro na leitura de um objecto do canal de entrada do socket de comunicação!");
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o objecto lido corresponde a um tipo de dados desconhecido!");
            e.printStackTrace();
            System.exit(1);
        }

        return fromServer;
    }

    /**
     * Escrita de um objecto no canal de comunicacao.
     *
     * @param toServer objecto a ser escrito
     */
    public void writeObject(Object toServer) {
        try {
            out.writeObject(toServer);
        } catch (InvalidClassException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o objecto a ser escrito não é passível de serialização!");
            e.printStackTrace();
            System.exit(1);
        } catch (NotSerializableException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - o objecto a ser escrito pertence a um tipo de dados não serializável!");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            GenericIO.writelnString(Thread.currentThread().getName()
                    + " - erro na escrita de um objecto do canal de saída do socket de comunicação!");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
