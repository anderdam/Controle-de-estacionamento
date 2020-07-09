/*
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

    private final List<Carro> vagasUtilizadas = new ArrayList<>();
    int limiteDeVagas = 10;
    int idadePreferencial = 55;

    public void estacionar(Carro carro) {

        carroPermitido(carro);

        if(vagasUtilizadas.size() < limiteDeVagas){
            vagasUtilizadas.add(carro);
        }else if (vagasUtilizadas.size() == limiteDeVagas){
            if (vagasUtilizadas.stream().noneMatch(veiculo -> veiculo.getMotorista().getIdade() < idadePreferencial)){
                throw new EstacionamentoException("Não há vagas");
            }
            for (int i = 0; i < vagasUtilizadas.size(); i++) {
                Carro veiculo = vagasUtilizadas.get(i);
                if (veiculo.getMotorista().getIdade() >= idadePreferencial) {
                    continue;
                } else {
                    vagasUtilizadas.remove(veiculo);
                    vagasUtilizadas.add(carro);
                    break;
                }
            }
        }else {
            throw new EstacionamentoException("Não há vagas");
        }
    }

    public int carrosEstacionados() {
        return vagasUtilizadas.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return vagasUtilizadas.contains(carro);
    }

    public Boolean carroPermitido(Carro carro) {
        return carro.carroComMotorista() && carro.getMotorista().motoristaValido();
    }
}
