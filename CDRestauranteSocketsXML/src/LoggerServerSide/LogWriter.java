package LoggerServerSide;

import CommonInfra.Definitions;
import EntitiesStates.Chef_states;
import EntitiesStates.Student_states;
import EntitiesStates.Waiter_states;
import genclass.*;
import java.util.HashMap;

/**
 * Tipo de dados que trata do log do programa. É aqui que estão implementadas as
 * funções que tratam do logging do programa. Fazem uso da biblioteca Genclass.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class LogWriter {

    /**
     * Chef_states Guarda o estado do chef.
     */
    private Chef_states chef_state;
    /**
     * Student_states Array que contém o estado de cada um dos students.
     */
    private Student_states[] students_state;
    /**
     * Waiter_states Contém o estado do waiter.
     */
    private Waiter_states waiter_state;
    /**
     * int Número actual de course.
     */
    private int courseNumber;
    /**
     * TextFile Ficheiro de log.
     */
    private TextFile logFile;
    /**
     * String Nome do ficheiro de log.
     */
    private String logFileName;
    /**
     * HashMap<String, String> Faz a conversão entre um Waiter_states,
     * Chef_states e Student_states para o acrónimo usado para efeitos de log.
     */
    private HashMap<String, String> statesMap;
    private String last;
    private boolean flag;

    /**
     * Construtor da classe.
     *
     *
     * @param fileName String cujo valor é o nome do ficheiro de log.
     */
    public LogWriter(String fileName) {
        this.logFile = new TextFile();
        this.logFileName = fileName;
        this.chef_state = Chef_states.WAITING_FOR_AN_ORDER;
        this.waiter_state = Waiter_states.APPRAISING_SITUATION;
        this.last = "";
        this.students_state = new Student_states[Definitions.MAXSTUDENTS];
        for (int i = 0; i < students_state.length; i++) {
            students_state[i] = Student_states.GOING_TO_THE_RESTAURANT;
        }
        StartStateHashMaps();
        this.flag = true;
        

    }

    /**
     * Escrever o header e o estado inicial do problema para o ficheiro de log.
     * Inicializa e escreve o header do ficheiro de log. Faz uso da biblioteca
     * Genclass disponibilizada.
     *
     */
    private void WriteHeader() {
        InitFile();
        logFile.writelnString("                                                        The Restaurant\n                                         ");
        logFile.writeString("     |   Chef   |  Waiter  | Student0 | Student1 | Student2 | Student3 | Student4 | Student5 | Student6 |  course  |\n");
        GenericIO.writelnString("                                                        The Restaurant\n                                         ");
        GenericIO.writelnString("     |   Chef   |  Waiter  | Student0 | Student1 | Student2 | Student3 | Student4 | Student5 | Student6 |  course  |\n");
        logFile.writelnString("\n" + GetCurrentStateValues());
        GenericIO.writelnString(GetCurrentStateValues());
        logFile.close();
    }

    /**
     * Escrever uma mudança do estado do chef para o ficheiro de log. Recebe
     * como parametro o estado actual do chef. Faz uso da biblioteca Genclass
     * disponibilizada.
     *
     * @param state Estado actual do Chef.
     */
    public synchronized void UpdateChefState(Chef_states state) {
        if (flag){
            WriteHeader();
            flag = false;
        }
        chef_state = state;
        AppendLine();
    }

    /**
     * Escrever uma mudança do estado do waiter para o ficheiro de log. Recebe
     * como parametro o estado actual do waiter. Faz uso da biblioteca Genclass
     * disponibilizada.
     *
     * @param state Estado actual do Waiter.
     */
    public synchronized void UpdateWaiterState(Waiter_states state) {
        if (flag){
            WriteHeader();
            flag = false;
        }
        waiter_state = state;
        AppendLine();
    }

    /**
     * Escrever uma mudança do estado de um student para o ficheiro de log.
     * Recebe como parametro o estado actual do student e o seu id. Faz uso da
     * biblioteca Genclass disponibilizada.
     *
     * @param state Estado actual de um student.
     * @param studentID Identificador do Student.
     */
    public synchronized void UpdateStudentState(Student_states state, int studentID) {
        if (flag){
            WriteHeader();
            flag = false;
        }
        students_state[studentID] = state;
        AppendLine();
    }

    /**
     * Escreve uma mudança de prato (course) para o ficheiro de log. Recebe como
     * parametro o número actual do prato.
     *
     * @param newCourseNumber Novo número de prato.
     */
    public synchronized void UpdateCourse(int newCourseNumber) {
        courseNumber = newCourseNumber;
        AppendLine();
    }

    /**
     * Método que inicializa o ficheiro de log. Abre, ou cria caso não exista, o
     * ficheiro para escrita.
     *
     * @param fileName Nome do ficheiro de log.
     */
    private void InitFile() {
        if (!logFile.openForWriting(null, logFileName)) {
            GenericIO.writeString("Não foi possível abrir o ficheiro");
            System.exit(1);
        }
    }

    /**
     * Método usado para escrever mais uma linha no ficheiro de log. Método que
     * faz append ao final de um ficheiro existente.
     *
     * @param fileName Nome do ficheiro de log.
     */
    private synchronized void AppendLine() {
        if (!logFile.openForAppending(null, logFileName)) {
            GenericIO.writeString("Erro ao abrir ficheiro de log para adicionar linha");
            System.exit(1);
        }
        String states = GetCurrentStateValues();
        if (!(last.equals(states))) {
            logFile.writeString("\n");
            logFile.writelnString(states);
            GenericIO.writelnString(states);
            last = states;
        }
        logFile.close();
    }

    /**
     * Método que agarra os estados actuais de todos os intervenientes e os
     * guarda e formata numa string que será escrita no ficheiro.
     *
     * @return String Uma String que contém os estados actuais de todos os
     * intervenientes:
     * <li> Chef. </li>
     * <li> Waiter. </li>
     * <li> Student. </li>
     * <li> Número do prato. </li>
     */
    private synchronized String GetCurrentStateValues() {
        StringBuilder tmp = new StringBuilder(35);
        tmp.append("     |  ");
        tmp.append(statesMap.get(chef_state.toString()));
        tmp.append("  |  ");
        tmp.append(statesMap.get(waiter_state.toString()));
        tmp.append("  |  ");
        for (Student_states state : students_state) {
            tmp.append(statesMap.get(state.toString()));
            tmp.append("  |  ");
        }
        tmp.append("  0");
        tmp.append((courseNumber));
        tmp.append("    |");
        return tmp.toString();

    }
     /**
      * Método privado que incializa o HashMap.
      */
    private void StartStateHashMaps() {

        //Starting states to acron hashmap
        statesMap = new HashMap<String, String>();

        //Chef states
        statesMap.put(Chef_states.CLOSING_SERVICE.toString(), "CLOSVC");
        statesMap.put(Chef_states.DELIVERING_THE_PORTIONS.toString(), "DELTPO");
        statesMap.put(Chef_states.DISHING_THE_PORTIONS.toString(), "DISTPO");
        statesMap.put(Chef_states.PREPARING_THE_COURSE.toString(), "PRETCO");
        statesMap.put(Chef_states.WAITING_FOR_AN_ORDER.toString(), "WTFAOD");

        //Waiter states
        statesMap.put(Waiter_states.APPRAISING_SITUATION.toString(), "APRSIT");
        statesMap.put(Waiter_states.PLACING_THE_ORDER.toString(), "PLATOR");
        statesMap.put(Waiter_states.PRESENTING_THE_MENU.toString(), "PRETME");
        statesMap.put(Waiter_states.PROCESSING_THE_BILL.toString(), "PROTBI");
        statesMap.put(Waiter_states.RECEIVING_PAYMENT.toString(), "RECPAY");
        statesMap.put(Waiter_states.TAKING_THE_ORDER.toString(), "TAKTOR");
        statesMap.put(Waiter_states.WAITING_FOR_PORTION.toString(), "WAFPOR");

        //Student states
        statesMap.put(Student_states.CHATTING_WITH_COMPANIONS.toString(), "CTWCOM");
        statesMap.put(Student_states.ENJOYING_THE_MEAL.toString(), "ENJTME");
        statesMap.put(Student_states.GOING_HOME.toString(), "GOHOME");
        statesMap.put(Student_states.GOING_TO_THE_RESTAURANT.toString(), "GOTORT");
        statesMap.put(Student_states.ORGANIZING_THE_ORDER.toString(), "ORGORD");
        statesMap.put(Student_states.PAYING_THE_MEAL.toString(), "PAYMEA");
        statesMap.put(Student_states.SELECTING_THE_COURSES.toString(), "SELTCO");
        statesMap.put(Student_states.TAKING_A_SEAT_AT_THE_TABLE.toString(), "TAKSEA");
    }

}
