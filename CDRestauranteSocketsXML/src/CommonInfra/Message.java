package CommonInfra;

import java.io.Serializable;
import EntitiesStates.*;

/**
 * Este tipo de dados define as mensagens que sao trocadas entre os clientes e o
 * servidor numa solucao do Problema do Restaurante que implementa o modelo
 * cliente-servidor de tipo 2 (replicacao do servidor). A comunicacao
 * propriamente dita baseia-se na troca de objectos de tipo Message num canal
 * TCP.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class Message implements Serializable {

    /**
     * Chave de serializacao
     *
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 1001L;

    /* Tipo de mensagem	*/
    /**
     * Mensagem de reconhecimento. Enviada para sinalizar que o servidor recebeu
     * e processou a mensagem.
     *
     * @serialField ACK int
     */
    public static final int ACK = 1;

    /**
     * Mensagem de término. Sinaliza o pedido do término do serviço
     *
     * @serialField SHUT int
     */
    public static final int SHUT = 2;

    /**
     * Mensagem de entrada na table (Student) . Sinaliza a chegada de um student
     * a table. Acompanhada do seu ID.
     *
     * @serialField ENTERTABLE int
     */
    public static final int ENTERTABLE = 3;

    /**
     * Mensagem de resposta do servidor(servidor) . Sinaliza que recebeu a
     * entrada do student. Junto com ela deve enviar a respectiva arrival ID.
     *
     */
    public static final int ENTEREDTABLE = 4;

    /**
     * Mensagem de aviso ao waiter (student). Sinaliza que um student está a
     * dizer ao waiter que chegou e está a espera do menu.
     *
     * @serialField ENTERBAR int
     */
    public static final int ENTERBAR = 5;

    /**
     * Mensagem de leitura do menu (student). Sinaliza que um student está a ler
     * o menu.
     *
     * @serialField READMENU int
     */
    public static final int READMENU = 6;

    /**
     * Mensagem de preperação do pedido (student). Sinaliza que o primeiro
     * student a chegar começou a preparar o pedido e está à espera que todos os
     * outros students acabem de escolher os pratos.
     *
     * @serialField PREPAREORDER int
     */
    public static final int PREPAREORDER = 7;

    /**
     * Mensagem de chamada ao waiter (student). Sinaliza que todos já escolherem
     * e que o primeiro student está preparado para dar a ID ao waiter.
     *
     * @serialField CALLWAITER int
     */
    public static final int CALLWAITER = 8;

    /**
     * Mensagem de entrega da order (student). Sinaliza que o waiter já chegou e
     * que o student está a descrever a order.
     *
     * @serialField DESCRIBEORDER int
     */
    public static final int DESCRIBEORDER = 9;

    /**
     * Mensagem finalização do pedido da ID (student). Sinaliza que o student
     * acabou de dar a ID e fica então à conversa com os seus companheiros.
     *
     * @serialField JOINTALK int
     */
    public static final int JOINTALK = 10;

    /**
     * Mensagem de passagem do pedido ao organizador (student). Sinaliza que o
     * student acabou de escolher o seu prato e quer informar o primeiro student
     * da sua escolha.
     *
     * @serialField INFORMCOMPANION int
     */
    public static final int INFORMCOMPANION = 11;

    /**
     * Mensagem de começar a comer (student). Sinaliza que o student começou a
     * comer o seu prato.
     *
     * @serialField STARTEATING int
     */
    public static final int STARTEATING = 12;

    /**
     * Mensagem de acabar de comer (student). Sinaliza que o student acabou de
     * comer o seu prato.
     *
     * @serialField ENDEATING int
     */
    public static final int ENDEATING = 13;

    /**
     * Mensagem confirmação que todos já acabaram (student). Pede a confirmação
     * de se todos já comeram o seu prato ou não.
     *
     * @serialField EVERYBODYFINISHED int
     */
    public static final int EVERYBODYFINISHED = 14;

    /**
     * Mensagem enviada pelo servidor. Resposta a se já todos os students
     * comeram o prato. Será acompanhada de um boolean.
     *
     */
    public static final int FINISHEDRESPONSE = 15;

    /**
     * Mensagem de sinalização ao waiter (student). Sinaliza ao waiter que já
     * todos comeram e pode servir o próximo prato.
     *
     * @serialField SIGNALWAITER int
     */
    public static final int SIGNALWAITER = 16;

    /**
     * Mensagem que vai começar a pagar a conta (student). Sinaliza que o
     * student está a espera que todos acabem de comer para começar a pagar a
     * conta.
     *
     * @serialField ARRIVEEARLIER int
     */
    public static final int ARRIVEEARLIER = 17;

    /**
     * Mensagem de saida (student). Sinaliza que o student já comeu e a conta já
     * foi paga, portanto vai embora.
     *
     * @serialField EXIT int
     */
    public static final int EXIT = 18;

    /**
     * Mensagem de espera (waiter). Sinaliza que o waiter está no bar à espera
     * de algo.
     *
     * @serialField LOOKARROUND int
     */
    public static final int LOOKARROUND = 19;
    /**
     * Mensagem de resposta. COntém a resposta enviada eplo servidor quandoo
     * waiter fez lookarround. Acompanhado de um int que sinaliza essa resposta.
     */
    public static final int LOOKARROUNDRES = 20;

    /**
     * Mensagem saudar o cliente (waiter). Sinaliza que o waiter foi à table
     * saudar um cliente e entregar o menu.
     *
     * @serialField SALUTECLIENT int
     */
    public static final int SALUTECLIENT = 21;

    /**
     * Mensagem de receber ID (waiter). Sinaliza que o waiter foi à table
     * receber a ID dos students.
     *
     * @serialField GETTHEPAD int
     */
    public static final int GETTHEPAD = 22;

    /**
     * Mensagem de dar ID ao chef (waiter). Sinaliza que o waiter foi à kitchen
     * dar a ID dos clientes ao chef.
     *
     * @serialField HANDNOTETOCHEF int
     */
    public static final int HANDNOTETOCHEF = 23;

    /**
     * Mensagem de recolher porção (waiter). Sinaliza que o waiter agarrou uma
     * porção para servir a um cliente
     *
     * @serialField COLLECTPORTION int
     */
    public static final int COLLECTPORTION = 24;

    /**
     * Mensagem de servir porção (waiter). Sinaliza que o waiter serviu uma
     * porção a um cliente
     *
     * @serialField DELIVERPORTION int
     */
    public static final int DELIVERPORTION = 25;

    /**
     * Mensagem de pedido de informação (waiter). Pede a confirmação se todos os
     * clientes já foram servidos ou não.
     *
     * @serialField ALLCLIENTSSERVED int
     */
    public static final int ALLCLIENTSSERVED = 26;

    /**
     * Mensagem do servidor. Mensagem de resposta enviada pelo servidor para
     * sinalizar se todos os clientes foram servidos. Acompanhada de um boolean
     * que o sinalizar.
     *
     */
    public static final int CLIENTSERVEDRESPONSE = 27;

    /**
     * Mensagem de preparação da bill (waiter). Sinaliza que o waiter está a
     * preparar a conta para apresentar ao cliente
     *
     * @serialField PREPAREBILL int
     */
    public static final int PREPAREBILL = 28;

    /**
     * Mensagem de apresentação da conta (waiter). Sinaliza que o waiter vai
     * apresentar a conta ao student.
     *
     * @serialField PRESENTBILL int
     */
    public static final int PRESENTBILL = 29;

    /**
     * Mensagem de retorno ao bar (waiter). Sinaliza que o waiter vai retornar
     * ao bar.
     *
     * @serialField RETURNTOBAR int
     */
    public static final int RETURNTOBAR = 30;

    /**
     * Mensagem de sinalização (chef). Sinaliza que o chef está a ver as
     * noticias enquanto espera por alguma ID.
     *
     * @serialField WATCHNEWS int
     */
    public static final int WATCHNEWS = 31;

    /**
     * Mensagem de começo da preparação (chef). Sinaliza que o chef vai começar
     * a preparar a comida.
     *
     * @serialField STARTPREPARING int
     */
    public static final int STARTPREPARING = 32;

    /**
     * Mensagem de começo da presentation (chef). Sinaliza que o chef está
     * pronto para apresentar os pratos.
     *
     * @serialField PROCEDTOPRSENTATION int
     */
    public static final int PROCEDTOPRSENTATION = 33;

    /**
     * Mensagem de alerta ao waiter (chef). Sinaliza o waiter que o chef está
     * pronto para dar dish às porções.
     *
     * @serialField ALERTWAITER int
     */
    public static final int ALERTWAITER = 34;

    /**
     * Mensagem de pedido de informação (chef). Pede a confirmação de se todas
     * as porções já foram servidas.
     *
     * @serialField DELIVEREDALLPORTIONS int
     */
    public static final int DELIVEREDALLPORTIONS = 35;

    /**
     * Mensagem de resposta do servidor. Mensagem de resposta enviada para
     * sinalizar se todas as portions foram entregues. Acompanhada de um boolean
     * que o sinaliza.
     *
     * @serialField DELIVEREDPORTIONSRES int
     *
     */
    public static final int DELIVEREDPORTIONSRES = 36;

    /**
     * Mensagem continuação da preparação para a próxima porção (chef). Sinaliza
     * que o chef vai ter mais uma porção pronta.
     *
     * @serialField HAVEPORTIONREADY int
     */
    public static final int HAVEPORTIONREADY = 37;

    /**
     * Mensagem pedido de informação (chef). Pede informação sobre se a ID está
     * completa ou não.
     *
     * @serialField ORDERCOMPLETED int
     */
    public static final int ORDERCOMPLETED = 38;

    /**
     * Mensagem de resposta do servidor. Mensagem de resposta a se aorder já foi
     * completada. Acompanhada de um boolean que o sinaliza.
     *
     * @serialField ORDERCOMPLETEDRES int
     */
    public static final int ORDERCOMPLETEDRES = 39;

    /**
     * Mensagem continuação para o próximo prato (chef). Sinaliza que o chef vai
     * continuar a preparação para o próximo prato.
     *
     * @serialField CONTINUEPREPARATION int
     */
    public static final int CONTINUEPREPARATION = 40;

    /**
     * Mensagem finalização (chef). Sinaliza que o chef finalizou o serviço e
     * vai limpar para depois sair..
     *
     * @serialField CLEANUP int
     */
    public static final int CLEANUP = 41;

    /* Mensagens de pedido de informação/acesso a informação*/
    /**
     * Mensagem de actualização. Actualiza uma variavel presente no bar que
     * sinaliza se o waiter pode servir ou não.
     *
     * @serialField SETWAITERCANSERVE int
     */
    public static final int SETWAITERCANSERVE = 42;
    /**
     * Mensagem de actualização. Actualiza uma variavel presente no bar que
     * sinaliza se todos os students acabaram a sua porção ou não.
     *
     * @serialField SETALLSTUDENTSFINISHED int
     */
    public static final int SETALLSTUDENTSFINISHED = 43;
    /**
     * Mensagem pedido de informação. Pede informação relativa a se o waiter
     * pode servir a próxima porção ou não. Acompanhada de um boolean.
     *
     * @serialField CANWAITERSERVE int
     */
    public static final int CANWAITERSERVE = 44;
    /**
     * Mensagem resposta a pedido de informação. Devolve informação relativa a
     * se o waiter pode servir a próxima porção ou não. Acompanhada de um
     * boolean.
     *
     * @serialField CANWAITERSERVE int
     */
    public static final int CANWAITERSERVERES = 45;
    /**
     * Mensagem de update ao state do student. Pede um update ao state de um
     * student. Acompanhada normalmente do ID do student.
     *
     * @serialField UPDATESTUDENT int
     */
    public static final int UPDATESTUDENT = 46;
    /**
     * Mensagem de update ao state do chef. Pede um update ao state do chef.
     *
     * @serialField UPDATECHEF int
     */
    public static final int UPDATECHEF = 47;
    /**
     * Mensagem de update ao state do waiter. Pede um update ao state do waiter.
     *
     * @serialField UPDATEWAITER int
     */
    public static final int UPDATEWAITER = 48;
    /**
     * Mensagem de update ao state do course. Pede um update ao número do
     * course. Acompanhada de um int com o novo número de course.
     *
     * @serialField UPDATECOURSE int
     */
    public static final int UPDATECOURSE = 49;


    /* Campos das mensagens */
    /**
     * Tipo da mensagem
     *
     */
    private int msgType = -1;

    /**
     * Ordem de chegada do Student
     *
     */
    private int ID = -1;

    /**
     * Estado de um pedido. Usado para sinalizar true ou false quando alguma
     * informação é pedida.
     */
    private boolean state;

    /**
     * Ordem de chegada de um student. Sinaliza a ordem de chegada de um
     * student.
     */
    private int order;
    /**
     * Estado de um student. Sinaliza o novo estado de um student.
     */
    private Student_states stuState;
    /**
     * Estado do chef. Sinaliza o novo estado do chef.
     */
    private Chef_states chefState;
    /**
     * Estado do waiter. Sinaliza o novo estado do waiter.
     */
    private Waiter_states waiterState;

    /**
     * Instanciacao de uma mensagem (forma 1).
     *
     * @param type tipo da mensagem
     */
    public Message(int type) {
        this.msgType = type;
    }

    /**
     * Instanciacao de uma mensagem (forma 2).
     *
     * @param type tipo da mensagem
     * @param ID id do student
     */
    public Message(int type, int ID) {
        this.msgType = type;
        this.ID = ID;
    }

    /**
     * Instanciacao de uma mensagem (forma 3).
     *
     * @param type tipo da mensagem
     * @param ID ID do student
     * @param order ordem de chegada do student
     */
    public Message(int type, int ID, int order) {
        this.msgType = type;
        this.ID = ID;
        this.order = order;
    }

    /**
     * Instanciacao de uma mensagem (forma 4).
     *
     * @param type tipo da mensagem
     * @param state sinalização, true ou false,de algum pedido.
     * <ul>
     * <li> true, se retornar uma resposta positiva </li>
     * <li> false, se retornar uma resposta negativa </li>
     * </ul>
     */
    public Message(int type, boolean state) {
        this.msgType = type;
        this.state = state;
    }

    /**
     * Instanciacao de uma mensagem (forma 5).
     *
     * @param type Tipo da mensagem
     * @param state Novo estado de um student
     * @param ID ID do student a mudar o estadp
     */
    public Message(int type, Student_states state, int ID) {
        this.msgType = type;
        this.stuState = state;
        this.ID = ID;
    }

    /**
     * Instanciacao de uma mensagem (forma 6).
     *
     * @param type tipo da mensagem
     * @param state Novo estado do chef
     */
    public Message(int type, Chef_states state) {
        this.msgType = type;
        this.chefState = state;
    }

    /**
     * Instanciacao de uma mensagem (forma 7).
     *
     * @param type tipo da mensagem
     * @param state Novo estado do waiter
     */
    public Message(int type, Waiter_states state) {
        this.msgType = type;
        this.waiterState = state;
    }

    /**
     * Receber o tipo de mensagem
     *
     * @return msgType tipo da mensagem.
     */
    public int getType() {
        return msgType;
    }

    /**
     * Receber a ordem de chegada so student
     *
     * @return ID ID do student.
     */
    public int getID() {
        return ID;
    }

    /**
     * Receber o estado.
     *
     * @return state estado actual de algo.
     * <ul>
     * <li> true, se for uma resposta positiva</li>
     * <li> false, se for uma resposta negativa</li>
     * </ul>
     */
    public boolean getState() {
        return state;
    }

    /**
     * Receber a ordem de chegada.
     *
     * @return ordem de chegada de um student.
     */
    public int getOrder() {
        return order;
    }

    /**
     * Receber o estado do student.
     *
     * @return Novo estado de um student.
     */
    public Student_states getStudentState() {
        return stuState;
    }

    /**
     * Receber o estado do Chef.
     *
     * @return Novo estado do Chef.
     */
    public Chef_states getChefState() {
        return chefState;
    }

    /**
     * Receber o estado do Waiter.
     *
     * @return Novo estado do Waiter.
     */
    public Waiter_states getWaiterState() {
        return waiterState;
    }

    /**
     * Passar o msgType para string
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Msg: " + this.msgType;
    }
}
