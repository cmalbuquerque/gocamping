package KitchenServerSide;

import CommonInfra.Message;
import CommonInfra.MessageException;

/**
 * Este tipo de dados define o interface à Kitchen numa solução do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2 (replicação do
 * servidor).
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class KitchenInterface {

    /**
     * Kitchen (representa o serviço a ser prestado)
     *
     * @serialField kit Objecto Kitchen
     */
    private Kitchen kit;

    /**
     * Instanciação do interface à Kitchen.
     *
     * @param kit Um objecto do tipo Kitchen.
     */
    public KitchenInterface(Kitchen kit) {
        this.kit = kit;
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
            /*chef calls */
            case Message.WATCHNEWS:
                break;
            case Message.STARTPREPARING:
                break;
            case Message.PROCEDTOPRSENTATION:
                break;
            case Message.DELIVEREDALLPORTIONS:
                break;
            case Message.HAVEPORTIONREADY:
                break;
            case Message.ORDERCOMPLETED:
                break;
            case Message.CONTINUEPREPARATION:
                break;
            case Message.CLEANUP:
                break;

            /* waiter calls*/
            case Message.HANDNOTETOCHEF:
                break;
            case Message.COLLECTPORTION:
                break;

            case Message.SHUT:                                                        // shutdown do servidor
                break;
            default:
                throw new MessageException("Tipo inválido!", inMessage);
        }
        /* seu processamento */
        switch (inMessage.getType()) {
            /*chef calls */
            case Message.WATCHNEWS:
                kit.watchTheNews();
                outMessage = new Message(Message.ACK);
                break;

            case Message.STARTPREPARING:
                kit.startPreparation();
                outMessage = new Message(Message.ACK);
                break;
            case Message.PROCEDTOPRSENTATION:
                kit.procedToPresentation();
                outMessage = new Message(Message.ACK);
                break;
            case Message.DELIVEREDALLPORTIONS:
                boolean val = kit.haveAllPortionsBeenDelivered();
                outMessage = new Message(Message.DELIVEREDPORTIONSRES, val);
                break;
            case Message.HAVEPORTIONREADY:
                kit.haveNextPortionReady();
                outMessage = new Message(Message.ACK);
                break;
            case Message.ORDERCOMPLETED:
                boolean has = kit.hasTheOrderBeenCompleted();
                outMessage = new Message(Message.ORDERCOMPLETEDRES, has);
                break;
            case Message.CONTINUEPREPARATION:
                kit.continuePreparation();
                outMessage = new Message(Message.ACK);
                break;
            case Message.CLEANUP:
                kit.cleanUp();
                outMessage = new Message(Message.ACK);
                break;
            /* waiter calls*/
            case Message.HANDNOTETOCHEF:
                kit.handTheNoteToTheChef();
                outMessage = new Message(Message.ACK);
                break;
            case Message.COLLECTPORTION:
                kit.collectPortion();
                outMessage = new Message(Message.ACK);
                break;
            // shutdown do servidor
            case Message.SHUT:
                ServerKitchen.waitConnection = false;
                (((KitchenClientProxy) (Thread.currentThread())).getScon()).setTimeout(10);
                outMessage = new Message(Message.ACK);            // gerar confirmação
                break;
        }

        return (outMessage);
    }
}
