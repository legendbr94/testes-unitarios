package com.pedro.testesunitarios.servicos;

import com.pedro.testesunitarios.entidades.Filme;
import com.pedro.testesunitarios.entidades.Locacao;
import com.pedro.testesunitarios.entidades.Usuario;
import com.pedro.testesunitarios.utils.DataUtils;
import java.util.Date;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.springframework.boot.test.context.SpringBootTest;


class LocacaoServiceTests {

  @Rule
  public ErrorCollector error = new ErrorCollector();

  @Test
  void testeLocacao() throws Exception {

    //Cenário
    Usuario usuario = new Usuario("Pedro");
    Filme filme = new Filme("a", 1, 5.0);

    //Ação
    Locacao locacao = LocacaoService.alugarFilme(usuario, filme);

    //Verificação
    error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(6.0)));
    error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),CoreMatchers.is(false));
    error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),CoreMatchers.is(false));
  }

}
