package com.pedro.testesunitarios.servicos;

import static com.pedro.testesunitarios.utils.DataUtils.adicionarDias;

import com.pedro.testesunitarios.entidades.Filme;
import com.pedro.testesunitarios.entidades.Locacao;
import com.pedro.testesunitarios.entidades.Usuario;
import com.pedro.testesunitarios.exceptions.FilmeSemEstoqueException;
import com.pedro.testesunitarios.exceptions.LocadoraException;
import java.util.Date;
import java.util.List;

public class LocacaoService {

  public Locacao alugarFilme(Usuario usuario, List<Filme> filmes)
      throws FilmeSemEstoqueException, LocadoraException {

    if (usuario == null) {
      throw new LocadoraException("Usuário vazio");
    }
    if (filmes == null || filmes.isEmpty()) {
      throw new LocadoraException("Filme vazio");
    }

    for (Filme filme : filmes) {
      if (filme.getEstoque() == 0) {
        throw new FilmeSemEstoqueException();
      }
    }

    Locacao locacao = new Locacao();
    locacao.setFilmes(filmes);
    locacao.setUsuario(usuario);
    locacao.setDataLocacao(new Date());

    Double valorTotal = 0d;
    int i = 0;
    for (Filme filme : filmes) {
      i++;

      if (filmes.size() == 1) {
        locacao.setValor(filme.getPrecoLocacao());
      }

      if (filmes.size() == 3 && i == 2) {
        Double valorTerceiroFilme = filmes.get(i).getPrecoLocacao();
        Double novoValor = calcularDescontoEmPercentual(25.0, valorTerceiroFilme);
        filmes.get(i).setPrecoLocacao(novoValor);

      }
      if (filmes.size() == 4 && i == 3) {
        Double valorQuartoFilme = filmes.get(i).getPrecoLocacao();
        Double novoValor = calcularDescontoEmPercentual(50.0, valorQuartoFilme);
        filmes.get(i).setPrecoLocacao(novoValor);
      }
      if (filmes.size() == 5 && i == 4) {
        Double valorQuintoFilme = filmes.get(i).getPrecoLocacao();
        Double novoValor = calcularDescontoEmPercentual(75.0, valorQuintoFilme);
        filmes.get(i).setPrecoLocacao(novoValor);
      }
      if (filmes.size() >= 6 && i == 5) {
        Double valorSextoFilme = filmes.get(i).getPrecoLocacao();
        Double novoValor = calcularDescontoEmPercentual(100.0, valorSextoFilme);
        filmes.get(i).setPrecoLocacao(novoValor);
      }

      valorTotal += filme.getPrecoLocacao();
    }

    locacao.setValor(valorTotal);

    //Entrega no dia seguinte
    Date dataEntrega = new Date();
    dataEntrega = adicionarDias(dataEntrega, 1);
    locacao.setDataRetorno(dataEntrega);

    //Salvando a locacao...
    //TODO adicionar método para salvar

    return locacao;
  }

  private double calcularDescontoEmPercentual(Double percentual, Double valor) {
    Double resultadoCalculo = percentual / 100 * valor;

    return valor - resultadoCalculo;
  }
}