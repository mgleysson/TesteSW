package test_arraylist;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArraylistTest {

	private List<Integer> lista;

	@Before
	public void setUp() {
		System.out.println("setUp");
		lista = new ArrayList<Integer>();
	}

	@After
	public void tearDown() {
		System.out.println("tearDown\n\n");
		lista.clear();
	}

	@Test
	public void teste_IsEmpty() {

		System.out.println("Testando m�todo teste_IsEmpty()");

		// testando o arraylist vazio: resultado esperado TRUE
		assertTrue(lista.isEmpty());

		// add um elemento no arraylist e testa de novo o m�todo isEmpty():
		// resultado esperado FALSE
		lista.add(1);
		assertFalse(lista.isEmpty());
	}

	@Test
	public void teste_Clear() {

		System.out.println("Testando m�todo teste_Clear()");

		// limpando um arraylist que j� est� vazio para testar o comportamento
		// do m�todo clear()
		lista.clear();
		assertTrue(lista.isEmpty());

		// testando clear() com um elemento inserido
		lista.add(1);

		lista.clear();
		assertTrue(lista.isEmpty());

		// testando clear() com n elementos inseridos no arrayList
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		lista.clear();

		assertTrue(lista.isEmpty());
	}

	@Test
	public void teste_Size() {

		System.out.println("Testando m�todo teste_Size()");
		assertEquals("Erro: O tamanho deveria ser 0!", 0, lista.size());

		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		assertEquals("Erro: O tamanho deveria ser 4!", 4, lista.size());

		lista.add(5);
		assertEquals("Erro: O tamanho deveria ser 5!", 5, lista.size());
	}

	@Test
	public void teste_Contains() {
		System.out.println("Testando m�todo Contains()");

		lista.add(1);
		lista.add(2);
		lista.add(4);

		assertTrue("Erro: O elemento 1 existe no ArrayList", lista.contains(1));
		assertTrue("Erro: O elemento 2 existe no ArrayList", lista.contains(2));
		assertFalse("Erro: O elemento 3 n�o existe no ArrayList", lista.contains(3));

	}

	@Test
	public void teste_Get() {

		System.out.println("Testando m�todo Get()");

		try {
			lista.get(-1);
			fail("N�o gerou IndexOutOfBoundsException!");

		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		}

		lista.add(56);

		assertEquals("Erro: O elemento de �ndice 0 do arrayList � 56!", 56, (Object) lista.get(0));

		lista.add(57);
		lista.add(58);

		assertEquals("Erro: O elemento de �ndice 2 do arrayList � 58!", 58, (Object) lista.get(2));
	}

	@Test
	public void teste_RemoveObject() {

		/*
		 * O metodo Remove (Object o) Removes the FIRST occurrence of the
		 * specified element from this list, if it is present
		 */

		System.out.println("Testando m�todo Remove(Object o)");

		lista.add(34);
		lista.add(45);
		lista.add(89);
		lista.add(23);
		lista.add(34);

		lista.remove((Object) 34);

		// Testando se realmente o m�todo Remove, remove a primeira instancia do
		// objeto 34 presente na lista
		assertEquals("Erro: O primeiro objeto da lista deveria ser diferente de '34'", (Integer) 45, lista.get(0));

		// Complementando o teste anterior, este teste verifica se a outra
		// instancia do objeto 34 est� de fato
		// presente na lista, ap�s a remo��o
		assertTrue(lista.contains((Object) 34));

		// O m�todo Remove (Object o) pode receber como par�metro um objeto
		// nulo, esse teste testa isso.
		// O assert � falso, j� que null n�o pertence a lista em quest�o e o
		// remove neste caso dar� falso.
		assertFalse(lista.remove(null));

		// Caso o objeto passado como par�metro n�o esteja contido no
		// arrayList, a lista fica inalterada.
		lista.remove((Object) 77);
		assertEquals((Integer) 45, lista.get(0));
		assertEquals((Integer) 89, lista.get(1));
		assertEquals((Integer) 23, lista.get(2));
		assertEquals((Integer) 34, lista.get(3));

		// Listando os elementos da colecao pra verificar a remo��o
		for (Integer i : lista) {
			System.out.println(i + " ");
		}

	}

	@Test
	public void teste_RemoveIndex() {

		System.out.println("Testando m�todo Remove(int index)");

		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);

		// testando remo��o com indice negativo
		try {
			int x = lista.remove(-2);
			fail("N�o gerou IndexOfBoundsException!");

		} catch (IndexOutOfBoundsException ex) {

			System.err.println("Exce��o! Foi passado o �ndice " + ex.getMessage() + " como par�metro.");
		}

		// testando com indice maior que o tamanho da lista atual
		try {
			int x = lista.remove(10);
			fail("N�o gerou IndexOfBoundsException!");
		} catch (IndexOutOfBoundsException ex) {
			System.err.println("Exce��o! Foi passado um �ndice maior que o tamanho da lista: " + ex.getMessage());
		}

		// testando se realmente o m�todo remove(int index) funciona de acordo
		// com a especifica��o
		// ele deve retornar o elemento removido e deslocar � esquerda os demais
		// elementos ap�s o removido

		int x = lista.remove(1);
		// testando se realmente o 2 foi removido!
		assertEquals("O elemento removido � o 2!", 2, (Object) x);

		// testando se a lista ficou com tamanho 4 ap�s a remo��o!
		assertEquals("O tamanho da lista devia ser 4!", 4, lista.size());

		// testando se os indices dos elementos foram diminuidos de 1 unidade!
		assertEquals(1, lista.indexOf(3));
		assertEquals(2, lista.indexOf(4));
		assertEquals(3, lista.indexOf(5));
	}

	@Test
	public void teste_Add() {
		System.out.println("Testando m�todo Add(E e)");

		lista.add(1);
		lista.add(1);
		lista.add(2);

		assertEquals("Erro: a lista deveria ter 3 elementos!", 3, lista.size());

		assertTrue("Erro: o elemento nulo devia ser inserido na lista!", lista.add(null));

	}

	@Test
	public void teste_AddIndex() {

		/*
		 * 
		 * Inserts the specified element at the specified position in this list
		 * 
		 * (optional operation). Shifts the element currently at that position
		 * (if any) and any subsequent elements to the right (adds one to their
		 * indices).
		 * 
		 */

		System.out.println("Testando m�todo Add(int index, E element)");

		// inicializando lista
		lista.add(1);
		lista.add(3);
		lista.add(4);
		lista.add(5);

		// add o elemento 2 no �ndice 1 que j� cont�m o elemento 3
		lista.add(1, 2);

		// testando se o elementos 3 foi deslocado a direita, indice antigo 1 ->
		// indice atual 2
		// se sim, todos os outros tamb�m estar�o, por indu��o
		assertEquals("Erro: o elemento 3 devia ser deslocado a direita!", 2, lista.indexOf(3));

		// add o elemento 6 em um �ndice negativo
		try {
			lista.add(-1, 6);
			fail("N�o gerou IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		}

		// add o elemento 6 em um �ndice maior que o tamanho da lista
		try {
			lista.add(20, 6);
			fail("N�o gerou IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		}

	}
}