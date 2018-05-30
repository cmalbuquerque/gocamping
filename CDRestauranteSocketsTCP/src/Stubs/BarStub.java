/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stubs;

import ClientSideGeral.ClientCom;
import CommonInfra.Message;

/**
 * Este tipo de dados define o stub ao Bar numa solução do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor).
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class BarStub {

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
     * Instanciação do stub ao Bar.
     *
     * @param hostName nome do sistema computacional onde está localizado o
     * servidor
     * @param port número do port de escuta do servidor
     */
    public BarStub(String hostName, int port) {
        serverHostName = hostName;
        serverPortNumb = port;
    }

    /**
     * Mudar valor de variável que sinaliza se os students acabaram.
     *
     * @param allFinished novo valor
     */
    public void setAllStudentsFinished(boolean allFinished) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.SETALLSTUDENTSFINISHED, allFinished);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Mudar valor de variável que sinaliza se o waiter pod servir.
     *
     * @param canServe novo valor
     */
    public void setWaiterCanServe(boolean canServe) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.SETWAITERCANSERVE, canServe);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Pedido de informação sobre se o waiter pode servir.
     *
     * @return boolean <ul> <li> true, em caso positivo</li>
     * <li> false, em caso negativo</li> </ul>
     */
    public boolean isWaiterCanServe() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.CANWAITERSERVE);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
        return inMessage.getState();
    }

    /**
     * Pedido de alerta ao waiter.
     */
    public void alertTheWaiter() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.ALERTWAITER);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Pedido de entrada no bar de um student.
     */
    public void enter() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.ENTERBAR);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Chamada ao waiter para dar a order.
     */
    public void callTheWaiter() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.CALLWAITER);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Sinalizar o waiter de que os students acabaram e estão prontos para a
     * próxima porção
     */
    public void signalTheWaiter() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.SIGNALWAITER);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Pedido de student que vai pagar a conta.
     *
     * @param studentID id so student que vai pagar
     */
    public void shoudHaveArrivedEarlier(int studentID) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.ARRIVEEARLIER, studentID);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Pedidodo waiter so seu método principal.
     *
     * @return int sinaliza o que o waiter vai fazer de seguida.
     */
    public int lookArround() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.LOOKARROUND);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
        return inMessage.getID();
    }

    /**
     * Waiter começar a preparar a conta.
     */
    public void prepareTheBill() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.PREPAREBILL);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Waiter apresenta a conta.
     */
    public void presentTheBill() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.PRESENTBILL);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Waiter volta ao bar.
     */
    public void returnToTheBar() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligacao
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.RETURNTOBAR);
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
