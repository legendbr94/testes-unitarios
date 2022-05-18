package com.pedro.testesunitarios.entidades;

import com.pedro.testesunitarios.exceptions.NaoPodeDividirPorZeroException;
import java.math.BigDecimal;

public class Calculadora {

  public BigDecimal somar(BigDecimal a, BigDecimal b) {
    return a.add(b);
  }

  public BigDecimal subtrair(BigDecimal a, BigDecimal b) {
    return a.subtract(b);
  }

  public BigDecimal dividir(BigDecimal a, BigDecimal b) throws NaoPodeDividirPorZeroException {
    if(b == BigDecimal.ZERO){
      throw new NaoPodeDividirPorZeroException();
    }
    return a.divide(b);
  }
}
