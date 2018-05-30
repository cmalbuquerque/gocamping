package LoggerServerSide;

import CommonInfra.Message;
import CommonInfra.MessageException;
import CommonInfra.Definitions;
import EntitiesStates.*;

/**
 * Este tipo de dados define o interface ao Logger numa solução do Problema do
 * Restaurante que implementa o modelo cliente-servidor de tipo 2 (replicação do
 * servidor).
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class LogInterface {

    /**
     * LogWriter (representa o serviço a ser prestado)
     *
     * @serialField log
     */
    private LogWriter log;

    /**
     * Instanciação do interface ao Logger.
     *
     * @param log Um objecto do tipo LogWriter.
     */
    public LogInterface(LogWriter log) {
        this.log = log;
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
            case Message.UPDATESTUDENT:
                if ((inMessage.getID() < 0) || (inMessage.getID() >= Definitions.MAXSTUDENTS)) {
                    throw new MessageException("Id do student inválido!", inMessage);
                }
                break;
            case Message.UPDATEWAITER:
                break;
            case Message.UPDATECHEF:
                break;
            case Message.UPDATECOURSE:
                break;
            case Message.SHUT:                                                        // shutdown do servidor
                break;
            default:
                throw new MessageException("Tipo inválido!", inMessage);
        }

        /* seu processamento */
        switch (inMessage.getType()) {
            case Message.UPDATESTUDENT:
                Student_states studentState = inMessage.getStudentState();
                int studentID = inMessage.getID();
                log.UpdateStudentState(studentState, studentID);
                outMessage = new Message(Message.ACK);
                break;
            case Message.UPDATEWAITER:
                Waiter_states waiterState = inMessage.getWaiterState();
                log.UpdateWaiterState(waiterState);
                outMessage = new Message(Message.ACK);
                break;
            case Message.UPDATECHEF:
                Chef_states chefState = inMessage.getChefState();
                log.UpdateChefState(chefState);
                outMessage = new Message(Message.ACK);
                break;
            case Message.UPDATECOURSE:
                int newCourseNUmber = inMessage.getID();
                log.UpdateCourse(newCourseNUmber);
                outMessage = new Message(Message.ACK);
                break;
            // shutdown do servidor
            case Message.SHUT:
                ServerLog.waitConnection = false;
                (((LogClientProxy) (Thread.currentThread())).getScon()).setTimeout(10);
                outMessage = new Message(Message.ACK);            // gerar confirmação
                break;
        }

        return (outMessage);
    }
}
