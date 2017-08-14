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

		System.out.println("Testando método teste_IsEmpty()");

		// testando o arraylist vazio: resultado esperado TRUE
		assertTrue(lista.isEmpty());

		// add um elemento no arraylist e testa de novo o método isEmpty():
		// resultado esperado FALSE
		lista.add(1);
		assertFalse(lista.isEmpty());
	}

	@Test
	public void teste_Clear() {

		System.out.println("Testando método teste_Clear()");

		// limpando um arraylist que já está vazio para testar o comportamento
		// do método clear()
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

		System.out.println("Testando método teste_Size()");
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
		System.out.println("Testando método Contains()");

		lista.add(1);
		lista.add(2);
		lista.add(4);

		assertTrue("Erro: O elemento 1 existe no ArrayList", lista.contains(1));
		assertTrue("Erro: O elemento 2 existe no ArrayList", lista.contains(2));
		assertFalse("Erro: O elemento 3 não existe no ArrayList", lista.contains(3));

	}

	@Test
	public void teste_Get() {

		System.out.println("Testando método Get()");

		try {
			lista.get(-1);
			fail("Não gerou IndexOutOfBoundsException!");

		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		}

		lista.add(56);

		assertEquals("Erro: O elemento de índice 0 do arrayList é 56!", 56, (Object) lista.get(0));

		lista.add(57);
		lista.add(58);

		assertEquals("Erro: O elemento de índice 2 do arrayList é 58!", 58, (Object) lista.get(2));
	}

	@Test
	public void teste_RemoveObject() {

		/*
		 * O metodo Remove (Object o) Removes the FIRST occurrence of the
		 * specified element from this list, if it is present
		 */

		System.out.println("Testando método Remove(Object o)");

		lista.add(34);
		lista.add(45);
		lista.add(89);
		lista.add(23);
		lista.add(34);

		lista.remove((Object) 34);

		// Testando se realmente o método Remove, remove a primeira instancia do
		// objeto 34 presente na lista
		assertEquals("Erro: O primeiro objeto da lista deveria ser diferente de '34'", (Integer) 45, lista.get(0));

		// Complementando o teste anterior, este teste verifica se a outra
		// instancia do objeto 34 está de fato
		// presente na lista, após a remoção
		assertTrue(lista.contains((Object) 34));

		// O método Remove (Object o) pode receber como parâmetro um objeto
		// nulo, esse teste testa isso.
		// O assert é falso, já que null não pertence a lista em questão e o
		// remove neste caso dará falso.
		assertFalse(lista.remove(null));

		// Caso o objeto passado como parâmetro não esteja contido no
		// arrayList, a lista fica inalterada.
		lista.remove((Object) 77);
		assertEquals((Integer) 45, lista.get(0));
		assertEquals((Integer) 89, lista.get(1));
		assertEquals((Integer) 23, lista.get(2));
		assertEquals((Integer) 34, lista.get(3));

		// Listando os elementos da colecao pra verificar a remoção
		for (Integer i : lista) {
			System.out.println(i + " ");
		}

	}

	@Test
	public void teste_RemoveIndex() {

		System.out.println("Testando método Remove(int index)");

		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);

		// testando remoção com indice negativo
		try {
			int x = lista.remove(-2);
			fail("Não gerou IndexOfBoundsException!");

		} catch (IndexOutOfBoundsException ex) {

			System.err.println("Exceção! Foi passado o índice " + ex.getMessage() + " como parâmetro.");
		}

		// testando com indice maior que o tamanho da lista atual
		try {
			int x = lista.remove(10);
			fail("Não gerou IndexOfBoundsException!");
		} catch (IndexOutOfBoundsException ex) {
			System.err.println("Exceção! Foi passado um índice maior que o tamanho da lista: " + ex.getMessage());
		}

		// testando se realmente o método remove(int index) funciona de acordo
		// com a especificação
		// ele deve retornar o elemento removido e deslocar à esquerda os demais
		// elementos após o removido

		int x = lista.remove(1);
		// testando se realmente o 2 foi removido!
		assertEquals("O elemento removido é o 2!", 2, (Object) x);

		// testando se a lista ficou com tamanho 4 após a remoção!
		assertEquals("O tamanho da lista devia ser 4!", 4, lista.size());

		// testando se os indices dos elementos foram diminuidos de 1 unidade!
		assertEquals(1, lista.indexOf(3));
		assertEquals(2, lista.indexOf(4));
		assertEquals(3, lista.indexOf(5));
	}

	@Test
	public void teste_Add() {
		System.out.println("Testando método Add(E e)");

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

		System.out.println("Testando método Add(int index, E element)");

		// inicializando lista
		lista.add(1);
		lista.add(3);
		lista.add(4);
		lista.add(5);

		// add o elemento 2 no índice 1 que já contém o elemento 3
		lista.add(1, 2);

		// testando se o elementos 3 foi deslocado a direita, indice antigo 1 ->
		// indice atual 2
		// se sim, todos os outros também estarão, por indução
		assertEquals("Erro: o elemento 3 devia ser deslocado a direita!", 2, lista.indexOf(3));

		// add o elemento 6 em um índice negativo
		try {
			lista.add(-1, 6);
			fail("Não gerou IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		}

		// add o elemento 6 em um índice maior que o tamanho da lista
		try {
			lista.add(20, 6);
			fail("Não gerou IndexOutOfBoundsException");
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		}

	}
}