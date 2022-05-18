package com.pedro.testesunitarios.servicos;

import com.pedro.testesunitarios.entidades.Calculadora;
import com.pedro.testesunitarios.exceptions.LocadoraException;
import com.pedro.testesunitarios.exceptions.NaoPodeDividirPorZeroException;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculadoraTest {

  private Calculadora calculadora;

  @Before
  public void setup() {
    calculadora = new Calculadora();
  }

  @Test
  public void deveSomarDoisValores() {
    //cenario
    BigDecimal a = BigDecimal.valueOf(5);
    BigDecimal b = BigDecimal.valueOf(3);
    Calculadora calculadora = new Calculadora();

    //acao
    BigDecimal resultado = calculadora.somar(a, b);

    //verificacao
    Assert.assertEquals(BigDecimal.valueOf(8), resultado);

  }

  @Test
  public void deveSubtrairDoisValores() {
    //cenario
    BigDecimal a = BigDecimal.valueOf(8);
    BigDecimal b = BigDecimal.valueOf(5);
    Calculadora calculadora = new Calculadora();

    //acao
    BigDecimal resultado = calculadora.subtrair(a, b);

    //verificacao
    Assert.assertEquals(BigDecimal.valueOf(3), resultado);

  }

  @Test
  public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
    //cenario
    BigDecimal a = BigDecimal.valueOf(6);
    BigDecimal b = BigDecimal.valueOf(3);

    //acao
    BigDecimal resultado = calculadora.dividir(a, b);

    //verificacao
    Assert.assertEquals(BigDecimal.valueOf(2), resultado);

  }

  @Test(expected = NaoPodeDividirPorZeroException.class)
  public void deveLancarExecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
    //cenario
    BigDecimal a = BigDecimal.valueOf(6);
    BigDecimal b = BigDecimal.valueOf(3);

    //acao
    BigDecimal resultado = calculadora.dividir(a, b);

    //verificacao
    Assert.assertEquals(BigDecimal.valueOf(2), resultado);

  }

}
