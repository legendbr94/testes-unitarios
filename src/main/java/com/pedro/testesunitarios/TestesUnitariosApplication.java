package com.pedro.testesunitarios;

import com.pedro.testesunitarios.entidades.Filme;
import com.pedro.testesunitarios.entidades.Locacao;
import com.pedro.testesunitarios.entidades.Usuario;
import com.pedro.testesunitarios.servicos.LocacaoService;
import com.pedro.testesunitarios.utils.DataUtils;
import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestesUnitariosApplication {

  public static void main(String[] args) {
    SpringApplication.run(TestesUnitariosApplication.class, args);

    //Cenário
    Usuario usuario = new Usuario("Pedro");
    Filme filme = new Filme("a", 1, 5.0);

    //Ação
    Locacao locacao = LocacaoService.alugarFilme(usuario, filme);

    //Verificação
    System.out.println(locacao.getValor() == 5.0);
    System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(),new Date()));
    System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(),DataUtils.obterDataComDiferencaDias(1)));
  }

}

