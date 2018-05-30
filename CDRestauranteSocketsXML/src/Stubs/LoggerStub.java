/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stubs;

import ClientSideGeral.ClientCom;
import CommonInfra.Message;
import EntitiesStates.Chef_states;
import EntitiesStates.Student_states;
import EntitiesStates.Waiter_states;

/**
 * Este tipo de dados define o stub ao Logger numa solução do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2 (replicação do
 * servidor).
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class LoggerStub {

    /**
     * Nome do sistema computacional onde está localizado o servidor
     *
     * @serialField serverHostName Nome do host.
     */
    private String serverHostName = null;

    /**
     * Número do port de escuta do servidor
     *
     * @serialField serverPortNumb porta de escuta.
     */
    private int serverPortNumb;

    /**
     * Instanciação do stub ao logger.
     *
     * @param hostName nome do sistema computacional onde está localizado o
     * servidor
     * @param port número do port de escuta do servidor
     */
    public LoggerStub(String hostName, int port) {
        serverHostName = hostName;
        serverPortNumb = port;
    }

    /**
     * Update do estado de um student.
     *
     * @param student_state Novo estado do student
     * @param studentID ID do student cujo estado mudou
     */
    public void UpdateStudentState(Student_states student_state, int studentID) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(Message.UPDATESTUDENT, student_state, studentID);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Update ao estado do waiter.
     *
     * @param waiter_state novo estado do waiter.
     */
    public void UpdateWaiterState(Waiter_states waiter_state) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(Message.UPDATEWAITER, waiter_state);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Update ao estado do chef.
     *
     * @param chef_states novo estado do chef.
     */
    public void UpdateChefState(Chef_states chef_states) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(Message.UPDATECHEF, chef_states);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        con.close();
    }

    /**
     * Update ao course em que o chef está a tabalhar de momento.
     *
     * @param currentCourse Número do course.
     */
    public void UpdateCourse(int currentCourse) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!con.open()) { 				// aguarda ligacao
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }

        outMessage = new Message(Message.UPDATECOURSE, currentCourse);
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
