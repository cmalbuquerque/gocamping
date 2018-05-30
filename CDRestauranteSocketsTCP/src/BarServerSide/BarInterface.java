package BarServerSide;

import CommonInfra.Message;
import CommonInfra.MessageException;

/**
 * Este tipo de dados define o interface ao Bar numa solução do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2 (replicação do
 * servidor).
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class BarInterface {

    /**
     * Bar (representa o serviço a ser prestado)
     *
     * @serialField bar
     */
    private Bar bar;

    /**
     * Instanciação do interface ao Bar.
     *
     * @param bar Um objecto do tipo Bar.
     */
    public BarInterface(Bar bar) {
        this.bar = bar;
    }

    /**
     * Processamento das mensagens através da execução da tarefa correspondente.
     * Geração de uma mensagem de resposta.
     *
     * @param inMessage mensagem com o pedido
     *
     * @return mensagem de resposta
     *
     * @throws MessageException se a mensagem com o pedido for considerada
     * inválida
     */
    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null;                           // mensagem de resposta

        /* validação da mensagem recebida */
        switch (inMessage.getType()) {
            /*student calls */
 /*student calls */
            case Message.ENTERBAR:
                break;

            case Message.CALLWAITER:
                break;
            case Message.SIGNALWAITER:
                break;
            case Message.ARRIVEEARLIER:
                break;

            /* chef calls */
            case Message.ALERTWAITER:
                break;

            /* waiter calls*/
            case Message.LOOKARROUND:
                break;
            case Message.PREPAREBILL:
                break;
            case Message.PRESENTBILL:
                break;
            case Message.RETURNTOBAR:
                break;

            /* info calls*/
            case Message.SETWAITERCANSERVE:
                break;
            case Message.SETALLSTUDENTSFINISHED:
                break;
            case Message.CANWAITERSERVE:
                break;

            case Message.SHUT:                                                        // shutdown do servidor
                break;
            default:
                throw new MessageException("Tipo inválido!", inMessage);
        }

        /* seu processamento */
        switch (inMessage.getType()) {

            case Message.ENTERBAR:
                bar.enter();
                outMessage = new Message(Message.ACK);
                break;
            case Message.CALLWAITER:
                bar.callTheWaiter();
                outMessage = new Message(Message.ACK);
                break;
            case Message.SIGNALWAITER:
                bar.signalTheWaiter();
                outMessage = new Message(Message.ACK);
                break;
            case Message.ARRIVEEARLIER:
                int sID = inMessage.getID();
                bar.shoudHaveArrivedEarlier(sID);
                outMessage = new Message(Message.ACK);
                break;

            /* chef calls */
            case Message.ALERTWAITER:
                bar.alertTheWaiter();
                outMessage = new Message(Message.ACK);
                break;

            /* waiter calls*/
            case Message.LOOKARROUND:
                int waiterOPT = bar.lookArround();
                outMessage = new Message(Message.LOOKARROUNDRES, waiterOPT);
                break;
            case Message.PREPAREBILL:
                bar.prepareTheBill();
                outMessage = new Message(Message.ACK);
                break;
            case Message.PRESENTBILL:
                bar.presentTheBill();
                outMessage = new Message(Message.ACK);
                break;
            case Message.RETURNTOBAR:
                bar.returnToTheBar();
                outMessage = new Message(Message.ACK);
                break;

            /* info calls*/
            case Message.SETWAITERCANSERVE:
                boolean val = inMessage.getState();
                bar.setWaiterCanServe(val);
                outMessage = new Message(Message.ACK);
                break;
            case Message.SETALLSTUDENTSFINISHED:
                boolean value = inMessage.getState();
                bar.allStudentsFinished(value);
                outMessage = new Message(Message.ACK);
                break;
            case Message.CANWAITERSERVE:
                boolean can = bar.isWaiterCanServe();
                outMessage = new Message(Message.CANWAITERSERVERES, can);
                break;

            // shutdown do servidor
            case Message.SHUT:
                ServerBar.waitConnection = false;
                (((BarClientProxy) (Thread.currentThread())).getScon()).setTimeout(10);
                outMessage = new Message(Message.ACK);            // gerar confirmação
                break;
        }

        return (outMessage);
    }
}
