package WaiterClientSide;

import Stubs.*;

import CommonInfra.Definitions;
/**
 *   Este tipo de dados simula uma solucao do lado do Waiter do Reataurante que implementa o
 *   modelo cliente-servidor de tipo 2 (replicacao do servidor).
 *   A comunicacao baseia-se em passagem de mensagens sobre sockets usando o protocolo TCP.
 * 
 * @author Pedro Pires
 * @author Dimitri Silva
 */

public class WaiterMain {
	
	/**
	   *   Programa principal.
	   */
	
	public static void main(String[] args) {
		/* Criacao da thread do Waiter */
		TableStub tab = new TableStub(Definitions.TableIP, Definitions.TablePort);
		BarStub bar = new BarStub(Definitions.BarIP, Definitions.BarPort);
		KitchenStub kit =new KitchenStub(Definitions.KitchenIP, Definitions.KitchenPort);
		LoggerStub logger = new LoggerStub(Definitions.LoggerIP, Definitions.LoggerPort);
		
		Thread waiter = new Thread(new Waiter(kit, bar, tab));
		
		/* Arranque da simulacao */
		
		waiter.start();
		
		/* Fim da simulacao */
		
		try {
			waiter.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* Shutdow dos servidores */
                try{
                Thread.sleep(50);          //espera um pouco para eprmitir que todos os serviços acabem
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
		
                tab.shutDown();
		bar.shutDown();
		kit.shutDown();
		logger.shutDown();
	}
}