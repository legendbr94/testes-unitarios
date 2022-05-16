package com.pedro.testesunitarios.servicos;

import com.pedro.testesunitarios.entidades.Filme;
import com.pedro.testesunitarios.entidades.Locacao;
import com.pedro.testesunitarios.entidades.Usuario;
import com.pedro.testesunitarios.exceptions.FilmeSemEstoqueException;
import com.pedro.testesunitarios.exceptions.LocadoraException;
import com.pedro.testesunitarios.utils.DataUtils;
import java.util.Date;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;


class LocacaoServiceTests {

  private LocacaoService locacaoService;

  @Rule
  public ErrorCollector error = new ErrorCollector();

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Before
  public void setup(){
    System.out.println("Before");
    locacaoService = new LocacaoService();

  }



  @Test
  void testeLocacao() throws Exception {

    //Cenário

    Usuario usuario = new Usuario("Pedro");
    Filme filme = new Filme("a", 2, 5.0);

    //Ação
    Locacao locacao = locacaoService.alugarFilme(usuario, filme);

    //Verificação
    error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(6.0)));
    error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
        CoreMatchers.is(true));
    error.checkThat(
        DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
        CoreMatchers.is(false));

    System.out.println("Teste");

  }

  @org.junit.Test(expected = FilmeSemEstoqueException.class)
  public void checarFilmeSemEstoque() throws Exception {
    //Cenário
    Usuario usuario = new Usuario("Pedro");
    Filme filme = new Filme("a", 0, 5.0);

    //Ação
    locacaoService.alugarFilme(usuario, filme);
  }


  @Test
  public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueException {
    //cenario
    Filme filme = new Filme("a", 1, 5.0);

    //acao
    try {
      locacaoService.alugarFilme(null, filme);
      Assert.fail();
    } catch (LocadoraException e) {
      MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Usuário vazio"));
    }
  }

  @Test
  public void testeLocacao_filmeVazio() throws FilmeSemEstoqueException, LocadoraException {

    //cenario
    Usuario usuario = new Usuario("Pedro");

    exception.expect(LocadoraException.class);
    exception.expectMessage("Filme vazio");

    //acao
    locacaoService.alugarFilme(usuario, null);

  }

}
