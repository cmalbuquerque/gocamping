/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stubs;

import ClientSideGeral.ClientCom;
import CommonInfra.Message;

/**
 * Este tipo de dados define o stub à cozinha numa solução do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2 (replicação do
 * servidor).
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class KitchenStub {

    /**
     * Nome do sistema computacional onde está localizado o servidor
     *
     * @serialField serverHostName
     */
    private String serverHostName = null;

    /**
     * Número do port de escuta do servidor
     *
     * @serialField serverPortNumb
     */
    private int serverPortNumb;

    /**
     * Instanciação do stub à Kitchen.
     *
     * @param hostName nome do sistema computacional onde está localizado o
     * servidor
     * @param port número do port de escuta do servidor
     */
    public KitchenStub(String hostName, int port) {
        serverHostName = hostName;
        serverPortNumb = port;
    }

    /**
     * Chef a ver as noticias
     */
    public void watchTheNews() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.WATCHNEWS);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Chef começa a preparação dos pratos.
     */
    public void startPreparation() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.STARTPREPARING);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Chef procede para a apresentação dos pratos.
     */
    public void procedToPresentation() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.PROCEDTOPRSENTATION);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Pedido de informação se todas as porções foram entregues.
     *
     * @return boolean <ul> <li> true, se todas as porções foram entregues </li>
     * <li> false, em caso contrário</li> </ul>
     */
    public boolean haveAllPortionsBeenDelivered() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.DELIVEREDALLPORTIONS);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
        return inMessage.getState();
    }

    /**
     * Chef prepara a próxima porção.
     */
    public void haveNextPortionReady() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.HAVEPORTIONREADY);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Pedido de informação sobre se a order já está completa.
     *
     * @return <ul> <li> true em caso positivo </li>
     * <li> false em caso contrário</li> </ul>
     */
    public boolean hasTheOrderBeenCompleted() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.ORDERCOMPLETED);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
        return inMessage.getState();
    }

    /**
     * Chef passa para a preparação do próximo prato.
     */
    public void continuePreparation() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.CONTINUEPREPARATION);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Chef acabou e vai embora.
     */
    public void cleanUp() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.CLEANUP);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Waiter entrega a order ao chef.
     */
    public void handTheNoteToTheChef() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.HANDNOTETOCHEF);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Waiter agarra muma porção para is servir.
     */
    public void collectPortion() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.COLLECTPORTION);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Pedido de shutdown do servidor.
     */
    public void shutDown() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.SHUT);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

}
