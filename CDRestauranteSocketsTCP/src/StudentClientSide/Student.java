package StudentClientSide;

import EntitiesStates.Student_states;
import Stubs.TableStub;
import Stubs.BarStub;

/**
 * Define um objecto do tipo Student.
 *
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class Student extends Thread implements Runnable {
    /**
     * int Variável de auxilio á criação dos IDs dos students.
     */
    private static int nextID = 0;
    /**
     * Student_states Guarda o estadoactual do student.
     */
    private Student_states state;
    /**
     * TableStub Um objecto do tipo tableStub.
     */
    private TableStub table;
    /**
     * BarStub Um objecto do tipo BarStub.
     */
    private BarStub bar;
    /**
     * int ID do student.
     */
    private int id;
    /**
     * int Número de pratos que serão consumidos.
     */
    private final int courses;

    /**
     * Método para aceder ao ID do Student.
     *
     * @return O ID do student
     */
    public int getStudentId() {
        return id;
    }

    /**
     * Método usado para dar set ao ID do student.
     *
     * @param id ID do student.
     */
    public void setStudentId(int id) {
        this.id = id;
    }

    /**
     * Constructor da classe. Inicializa as variáveis necessárias
     *
     * @param table Um objecto do tipo TableStub.
     * @param bar Um objecto do tipo BarStub.
     * @param courses Número de pratos a serem consumidos.
     * @param maxStudents Número de students que vão comer.
     * @param ID ID do student.
     */
    public Student(TableStub table, BarStub bar, int courses, int maxStudents, int ID) {
        this.state = Student_states.GOING_TO_THE_RESTAURANT;
        this.table = table;
        this.bar = bar;
        this.id = nextID;
        Student.nextID++;
        this.courses = courses;

    }

    /**
     * Método que retorna o estado do student.
     *
     * @return O estado actual do student.
     */
    public Student_states getStudentState() {
        return state;
    }

    /**
     * Método que permite da rum novo estado ao student.
     *
     * @param state Estado para o qual o student deve mudar.
     */
    public void setStudentState(Student_states state) {
        this.state = state;
    }

    @Override
    public void run() {
        int arrive_order;
        walkABit();

        arrive_order = table.enter(id);
        bar.enter();
        table.readTheMenu(id);

        if (arrive_order == 1) {
            table.prepareTheOrder(id);
            bar.callTheWaiter();
            table.describeTheOrder();
            table.joinTheTalk(id);
        } else {
            table.informCompanion(id);
        }

        for (int course = 0; course < courses; course++) {
            table.startEating(id);
            table.endEating(id);
            if (table.hasEverybodyFinished()) {
                if (!(course == 2)) {
                    bar.signalTheWaiter();
                }
            }
        }
        if (arrive_order == 7) {
            bar.shoudHaveArrivedEarlier(id);
        }
        table.exit(id);
    }

    /**
     * Método privado que simula o student a andar antes de ir para o restaurante.
     * Usado para simular a chegada random e a tempos diferentes de cada student.
     */
    private void walkABit() {
        try {
            Thread.sleep((long) (150 * Math.random() + 1));
        } catch (InterruptedException ex) {
        }
    }

}
