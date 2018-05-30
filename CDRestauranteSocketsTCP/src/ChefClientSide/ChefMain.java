package ChefClientSide;

import CommonInfra.Definitions;
import Stubs.BarStub;
import Stubs.KitchenStub;

/**
 * Este tipo de dados simula uma solucao do lado do chef do Restaurante que
 * implementa o modelo cliente-servidor de tipo 2 (replicacao do servidor). A
 * comunicacao baseia-se em passagem de mensagens sobre sockets usando o
 * protocolo TCP.
 *
 * @author Pedro Pires
 * @author Dimitri Silva
 */
public class ChefMain {

    /**
     * Programa principal.
     */
    public static void main(String[] args) {
        /* Criacao da thread do Chef */
        KitchenStub kit = new KitchenStub(Definitions.KitchenIP, Definitions.KitchenPort);
        BarStub bar = new BarStub(Definitions.BarIP, Definitions.BarPort);

        Thread chef = new Thread(new Chef(kit, bar, Definitions.COURSES, Definitions.MAXSTUDENTS));

        /* Arranque da simulacao */
        chef.start();

        /* Fim da simulacao */
        try {
            chef.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
