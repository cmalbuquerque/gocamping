package TableServerSide;

import CommonInfra.Definitions;
import CommonInfra.Message;
import CommonInfra.MessageException;

/**
 * Este tipo de dados define o interface à table numa solução do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2 (replicação do
 * servidor).
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class TableInterface {

    /**
     * Table (representa o serviço a ser prestado)
     *
     * @serialField tab Um objecto do tipo Table
     */
    private Table tab;

    /**
     * Instanciação do interface à table.
     *
     * @param table Um objecto do tipo table.
     */
    public TableInterface(Table table) {
        this.tab = table;
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
            /* student calls */
            case Message.ENTERTABLE:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;
            case Message.READMENU:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;
            case Message.PREPAREORDER:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;
            case Message.DESCRIBEORDER:
                break;
            case Message.JOINTALK:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;
            case Message.INFORMCOMPANION:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;
            case Message.STARTEATING:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;
            case Message.ENDEATING:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;
            case Message.EVERYBODYFINISHED:
                break;
            case Message.EXIT:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;

            /*waiter calls */
            case Message.SALUTECLIENT:
                break;
            case Message.GETTHEPAD:
                break;
            case Message.DELIVERPORTION:
                break;
            case Message.ALLCLIENTSSERVED:
                break;

            /*shutdown do servidor*/
            case Message.SHUT:
                break;
            default:
                throw new MessageException("Tipo inválido!", inMessage);
        }

        /* seu processamento */
        switch (inMessage.getType()) {
            case Message.ENTERTABLE:
                int order = tab.enter(inMessage.getID());
                outMessage = new Message(Message.ENTEREDTABLE, inMessage.getID(), order);
                break;
            case Message.READMENU:
                tab.readTheMenu(inMessage.getID());
                outMessage = new Message(Message.ACK);
                break;
            case Message.PREPAREORDER:
                tab.prepareTheOrder(inMessage.getID());
                outMessage = new Message(Message.ACK);
                break;
            case Message.DESCRIBEORDER:
                tab.describeTheOrder();
                outMessage = new Message(Message.ACK);
                break;
            case Message.JOINTALK:
                tab.joinTheTalk(inMessage.getID());
                outMessage = new Message(Message.ACK);
                break;
            case Message.INFORMCOMPANION:
                tab.informCompanion(inMessage.getID());
                outMessage = new Message(Message.ACK);
                break;
            case Message.STARTEATING:
                tab.startEating(inMessage.getID());
                outMessage = new Message(Message.ACK);
                break;
            case Message.ENDEATING:
                tab.endEating(inMessage.getID());
                outMessage = new Message(Message.ACK);
                break;
            case Message.EVERYBODYFINISHED:
                boolean stat = tab.hasEverybodyFinished();
                outMessage = new Message(Message.FINISHEDRESPONSE, stat);
                break;
            case Message.EXIT:
                tab.exit(inMessage.getID());
                outMessage = new Message(Message.ACK);
                break;

            /* waiter */
            case Message.SALUTECLIENT:
                tab.saluteTheClient();
                outMessage = new Message(Message.ACK);
                break;
            case Message.GETTHEPAD:
                tab.getThePad();
                outMessage = new Message(Message.ACK);
                break;
            case Message.DELIVERPORTION:
                tab.deliverPortion();
                outMessage = new Message(Message.ACK);
                break;
            case Message.ALLCLIENTSSERVED:
                boolean val = tab.haveAllClientsBeenServed();
                outMessage = new Message(Message.ACK, val);
                break;
            case Message.SHUT:

                // shutdown do servidor
                ServerTable.waitConnection = false;
                (((TableClientProxy) (Thread.currentThread())).getScon()).setTimeout(10);
                outMessage = new Message(Message.ACK);            // gerar confirmação
                break;
        }
        return (outMessage);
    }
}
