package com.pedro.testesunitarios;

import com.pedro.testesunitarios.entidades.Usuario;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssertionsExamples {

  @Test
  void assertions() {

    Assertions.assertTrue(true);
    Assertions.assertFalse(false);

    Assertions.assertEquals(1, 1);
    Assertions.assertEquals(0.51234, 0.512, 0.001);
    Assertions.assertEquals(Math.PI, 3.14, 0.01);

    //Verifique QUE o valor atual É
    MatcherAssert.assertThat(5, CoreMatchers.is(5));
    MatcherAssert.assertThat(5, CoreMatchers.equalTo(5));
    MatcherAssert.assertThat(5, CoreMatchers.is(CoreMatchers.not(5)));

    int i = 5;
    Integer i2 = 5;
    Assertions.assertEquals(i, i2);

    Assertions.assertEquals("bola", "bola");
    Assertions.assertNotEquals("bola", "casa");
    Assertions.assertTrue("bola".equalsIgnoreCase("Bola"));

    Assertions.assertTrue("bola".equalsIgnoreCase("Bola"));
    Assertions.assertTrue("bola".startsWith("bo"));

    Usuario u1 = new Usuario("Usuario 1");
    Usuario u2 = new Usuario("Usuario 1");
    Usuario u3 = null;

    //Compara através do equals e hashcode
    Assertions.assertEquals(u1, u2);

    //Validar se é a mesma instancia
    Assertions.assertSame(u2, u2);
    Assertions.assertNotSame(u1, u2);

    Assertions.assertNull(u3);
    Assertions.assertNotNull(u2);
  }

}
