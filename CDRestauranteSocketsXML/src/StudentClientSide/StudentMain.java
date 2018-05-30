package StudentClientSide;

import CommonInfra.Definitions;
import Stubs.BarStub;
import Stubs.TableStub;

/**
 * Este tipo de dados simula uma solucao do lado do Student do problema do
 * restaurante que implementa o modelo cliente-servidor de tipo 2 (replicacao do
 * servidor). A comunicacao baseia-se em passagem de mensagens sobre sockets
 * usando o protocolo TCP.
 * 
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class StudentMain {

    /**
     * Programa principal.
     */
    public static void main(String[] args) {
        int studentsNumber = Definitions.MAXSTUDENTS;
        Thread[] students = new Thread[studentsNumber];		//Array de threads Student

        /* Criacao das threads Student */
        for (int i = 0; i < studentsNumber; i++) {
            TableStub tab = new TableStub(Definitions.TableIP, Definitions.TablePort);
            BarStub bar = new BarStub(Definitions.BarIP, Definitions.BarPort);
            students[i] = new Thread(new Student(tab, bar, Definitions.COURSES, studentsNumber, i));
        }

        /* Arranque da simulacao */
        for (int i = 0; i < studentsNumber; i++) {
            students[i].start();
        }

        /* Fim da simulacao */
        for (int i = 0; i < studentsNumber; i++) {
            try {
                students[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
