package com.pedro.testesunitarios.servicos;

import com.pedro.testesunitarios.entidades.Filme;
import com.pedro.testesunitarios.entidades.Locacao;
import com.pedro.testesunitarios.entidades.Usuario;
import com.pedro.testesunitarios.exceptions.FilmeSemEstoqueException;
import com.pedro.testesunitarios.exceptions.LocadoraException;
import com.pedro.testesunitarios.utils.DataUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;


class LocacaoServiceTests {

  private LocacaoService locacaoService;

  @Rule
  public ErrorCollector error = new ErrorCollector();

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @BeforeEach
  public void setup() {
    locacaoService = new LocacaoService();
  }

  @Test
  void deveAlugarFilme() throws Exception {
    Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(),Calendar.SATURDAY));

    //Cenário
    Usuario usuario = new Usuario("Pedro");
    Filme filme = new Filme("a", 2, 5.0);

    List<Filme> filmeList = new ArrayList<>();
    filmeList.add(filme);

    //Ação
    Locacao locacao = locacaoService.alugarFilme(usuario, filmeList);

    //Verificação
    error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(6.0)));
    error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
        CoreMatchers.is(true));
    error.checkThat(
        DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
        CoreMatchers.is(false));

  }

  @Test
  void deveTer25PorcentoDescontoNoFilme3() throws FilmeSemEstoqueException, LocadoraException {

    //Cenário
    Usuario usuario = new Usuario("Pedro");
    List<Filme> filmeList = Arrays.asList(
        new Filme("Filme 1", 2, 4.0),
        new Filme("Filme 2", 2, 4.0),
        new Filme("Filme 3", 2, 4.0));

    //Ação
    Locacao locacao = locacaoService.alugarFilme(usuario, filmeList);

    //Verificação
    Assertions.assertEquals(11.0, locacao.getValor());

    filmeList.forEach(filme -> {
      System.out
          .println(filme.getNome() + " " + filme.getEstoque() + " " + filme.getPrecoLocacao());
    });

  }

  @Test
  void deveTer50PorcentoDescontoNoFilme4() throws Exception {

    //Cenário
    Usuario usuario = new Usuario("Pedro");
    List<Filme> filmeList = Arrays.asList(
        new Filme("Filme 1", 2, 4.0),
        new Filme("Filme 2", 2, 4.0),
        new Filme("Filme 3", 2, 4.0),
        new Filme("Filme 4", 2, 4.0));

    //Ação
    Locacao locacao = locacaoService.alugarFilme(usuario, filmeList);

    //Verificação
    Assertions.assertEquals(14.0, locacao.getValor());

    filmeList.forEach(filme -> {
      System.out
          .println(filme.getNome() + " " + filme.getEstoque() + " " + filme.getPrecoLocacao());
    });

  }

  @Test
  void deveTer75PorcentoDescontoNoFilme5() throws Exception {

    //Cenário
    Usuario usuario = new Usuario("Pedro");
    List<Filme> filmeList = Arrays.asList(
        new Filme("Filme 1", 2, 4.0),
        new Filme("Filme 2", 2, 4.0),
        new Filme("Filme 3", 2, 4.0),
        new Filme("Filme 4", 2, 4.0),
        new Filme("Filme 5", 2, 4.0));

    //Ação
    Locacao locacao = locacaoService.alugarFilme(usuario, filmeList);

    //Verificação
    Assertions.assertEquals(17.0, locacao.getValor());

    filmeList.forEach(filme -> {
      System.out
          .println(filme.getNome() + " " + filme.getEstoque() + " " + filme.getPrecoLocacao());
    });

  }

  @Test
  void deveTer100PorcentoDescontoNoFilme6() throws Exception {

    //Cenário
    Usuario usuario = new Usuario("Pedro");
    List<Filme> filmeList = Arrays.asList(
        new Filme("Filme 1", 2, 4.0),
        new Filme("Filme 2", 2, 4.0),
        new Filme("Filme 3", 2, 4.0),
        new Filme("Filme 4", 2, 4.0),
        new Filme("Filme 5", 2, 4.0),
        new Filme("Filme 6", 2, 4.0));

    //Ação
    Locacao locacao = locacaoService.alugarFilme(usuario, filmeList);

    //Verificação
    Assertions.assertEquals(20.0, locacao.getValor());

    filmeList.forEach(filme -> {
      System.out
          .println(filme.getNome() + " " + filme.getEstoque() + " " + filme.getPrecoLocacao());
    });

  }

  @org.junit.Test(expected = FilmeSemEstoqueException.class)
  public void naoDeveAlugarFilmeSemEstoque() throws Exception {
    //Cenário
    Usuario usuario = new Usuario("Pedro");
    Filme filme = new Filme("a", 0, 4.0);
    List<Filme> filmeList = new ArrayList<>();
    filmeList.add(filme);

    //Ação
    locacaoService.alugarFilme(usuario, filmeList);
  }

  @Test
  public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
    //cenario
    Filme filme = new Filme("a", 1, 5.0);
    List<Filme> filmeList = new ArrayList<>();
    filmeList.add(filme);

    //acao
    try {
      locacaoService.alugarFilme(null, filmeList);
      Assert.fail();
    } catch (LocadoraException e) {
      MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Usuário vazio"));
    }
  }

  @Test
  public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
    Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(),Calendar.SATURDAY));

    //cenario
    Usuario usuario = new Usuario("Pedro");
    List<Filme>filmeList = Arrays.asList(new Filme("Filme 1",1,5.0));

    //acao
    Locacao locacao = locacaoService.alugarFilme(usuario,filmeList);

    //verificacao
    boolean ehSegunda = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY);

    Assertions.assertTrue(ehSegunda);
  }

  @Test
  public void naoDeveAlugarFilmeSemFilme() {
    LocadoraException thrown = Assertions.assertThrows(LocadoraException.class, () -> {

      //cenario
      Usuario usuario = new Usuario("Pedro");

      //acao
      locacaoService.alugarFilme(usuario, null);

    });

    //verificacao
    Assertions.assertEquals("Filme vazio", thrown.getMessage());

  }
}
