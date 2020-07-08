/*
*
*• Para cada carro que entra no sistema é necessário informar a sua placa, cor além das informações do motorista.
*• O estacionamento não deverá comportar o número superior de vagas.
*• Caso o estacionamento esteja lotado:
*  * Chegue mais um novo carro, o primeiro que estacionou deverá sair
*  * Caso o motorista do primeiro carro estacionado tenha uma idade superior a 55 anos, será escolhido o próximo motorista abaixo dos 55 anos.
*   * Caso todos os motoristas, dentro do estacionamento, tenham mais de 55 anos e chegue um motorista, ele não conseguirá estacionar.
*/

package challenge;

import challenge.domain.entities.Carro;
import challenge.domain.entities.Motorista;
import challenge.exceptions.EstacionamentoException;

import java.util.ArrayList;
import java.util.List;


public class Estacionamento {

    List<Carro> carros = new ArrayList<>();
    List<Motorista> motoristas = new ArrayList<>();
    int vagas = 10;   //• O número de vagas do estacionamento são de dez carros.


    public void estacionar(Carro carro) {
        if (carroValido(carro)) {
            while (vagas >= 0) {
                for (Carro veiculo : carros) {
                    carros.add(veiculo);
                    vagas = vagas -1;
                }    
            }            
        }    
    }

    public int carrosEstacionados() {
        return carros.size();        
    }

    public boolean carroEstacionado(Carro carro) {
        if (carros.contains(carro)){
            return true;
        }
        return false;
    }

    public Boolean carroValido(Carro carro) {

        if (carro.getMotorista() == null) { //• Para entrar no estacionamento, é necessário que exista um motorista, ou seja, nada de carro autônomo.
            throw new EstacionamentoException("Necessário motorista, carro autônomo não permitido");
        }
        if (carro.getMotorista().getIdade() <18) { //• O motorista precisa ter idade suficiente para dirigir e possuir uma habilitação.
            throw new EstacionamentoException("Proibido motoristtas menores de idade");
        }
        if (carro.getMotorista().getPontos() >20) {//• A habilitação não deverá está suspensa, ou seja, a pontuação da carteira de motorista não deverá ser superior a vinte pontos.
            throw new EstacionamentoException("Habilitação inválida, pontuação excedida");
        }        
        if (carro.getMotorista().getHabilitacao() == null) {
            throw new EstacionamentoException("Habilitação não informada");
        }
        if (carro.getMotorista().getNome().isEmpty()) {
            throw new NullPointerException("Nome do motorista inválido");
        }
        if (carro.getCor() == null) {
            throw new EstacionamentoException("Cor não informada");
        }
        if (carro.getPlaca() == null) {
            throw new NullPointerException("Placa não informada");
        }        
        return true;
    }

}
