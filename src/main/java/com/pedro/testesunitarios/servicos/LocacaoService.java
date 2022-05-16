package com.pedro.testesunitarios.servicos;

import static com.pedro.testesunitarios.utils.DataUtils.adicionarDias;

import com.pedro.testesunitarios.entidades.Filme;
import com.pedro.testesunitarios.entidades.Locacao;
import com.pedro.testesunitarios.entidades.Usuario;
import com.pedro.testesunitarios.exceptions.FilmeSemEstoqueException;
import com.pedro.testesunitarios.exceptions.LocadoraException;
import java.util.Date;

public class LocacaoService {

  public static Locacao alugarFilme(Usuario usuario, Filme filme)
      throws FilmeSemEstoqueException, LocadoraException {


    if(usuario == null){
      throw new LocadoraException("Usuário vazio");
    }
    if (filme == null){
      throw new LocadoraException("Filme vazio");
    }
    if(filme.getEstoque() == 0){
      throw new FilmeSemEstoqueException();
    }

    Locacao locacao = new Locacao();
    locacao.setFilme(filme);
    locacao.setUsuario(usuario);
    locacao.setDataLocacao(new Date());
    locacao.setValor(filme.getPrecoLocacao());

    //Entrega no dia seguinte
    Date dataEntrega = new Date();
    dataEntrega = adicionarDias(dataEntrega, 1);
    locacao.setDataRetorno(dataEntrega);

    //Salvando a locacao...
    //TODO adicionar método para salvar

    return locacao;
  }
}