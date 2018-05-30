/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stubs;

import ClientSideGeral.ClientCom;
import CommonInfra.Message;

/**
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class TableStub {

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
     * Instanciação do stub ao Table.
     *
     * @param hostName nome do sistema computacional onde está localizado o
     * servidor
     * @param port número do port de escuta do servidor
     */
    public TableStub(String hostName, int port) {
        serverHostName = hostName;
        serverPortNumb = port;
    }

    public int enter(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.ENTERTABLE, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
        return inMessage.getOrder();
    }

    public void readTheMenu(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.READMENU, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void prepareTheOrder(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.PREPAREORDER, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void describeTheOrder() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.DESCRIBEORDER);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void joinTheTalk(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.JOINTALK, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void informCompanion(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.INFORMCOMPANION, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void startEating(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.STARTEATING, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void endEating(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.ENDEATING, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public boolean hasEverybodyFinished() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(Message.EVERYBODYFINISHED);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if (inMessage.getType() != Message.FINISHEDRESPONSE){
        }
        boolean val = inMessage.getState();
        con.close();
        
        return val;
    }

    public void exit(int id) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(Message.EXIT, id);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void saluteTheClient() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.SALUTECLIENT);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void getThePad() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.GETTHEPAD);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public void deliverPortion() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.DELIVERPORTION);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    public boolean haveAllClientsBeenServed() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(Message.ALLCLIENTSSERVED);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
        return inMessage.getState();
    }

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
