package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoffeeMakerTest
{
	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;

	@Before
	public void setUp() throws Exception
	{
		cm = new CoffeeMaker();

		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice("50");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setAmtChocolate("0");
		

		r2 = new Recipe();
		r2.setName("Coffee1");
		r2.setPrice("50");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setAmtChocolate("0");
	}
	
	@Test
	public void checkAddRecipe1()
	{
		assertTrue(cm.addRecipe(r1));
	}
	
	@Test
	public void checkAddRecipe2()
	{
		cm.addRecipe(r1);
		assertTrue(cm.addRecipe(r2));
	}
	
	@Test
	public void checkDeleteRecipe1()
	{
		cm.addRecipe(r1);
		Recipe [] R_A = cm.getRecipes();

		assertEquals(R_A[0].getName(),"Coffee");

		String result = cm.deleteRecipe(0);

		assertEquals("Coffee",result);
	}
	
	@Test
	public void checkEditRecipe1() throws RecipeException
	{
		cm.addRecipe(r1);
		
		Recipe [] R_A = cm.getRecipes();
		assertEquals(R_A[0].getName(),"Coffee");


		r3 = new Recipe();
		r3.setName("Coffee3");
		r3.setPrice("50");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("1");
		r3.setAmtSugar("1");
		r3.setAmtChocolate("0");

		String result = cm.editRecipe(0, r3);

		R_A = cm.getRecipes();

		
		assertEquals(R_A[0].getName(),"Coffee3" );
	}
	
	@Test
	public void checkAddInventory1() throws InventoryException
	{
		String inv = cm.checkInventory();

		int F_Coffee = GetIng(inv,"Coffee");
		int F_Milk = GetIng(inv,"Milk");
		int F_Sugar = GetIng(inv,"Sugar");
		int F_Chocolate = GetIng(inv,"Chocolate");

		cm.addInventory("1","2","3","4");

		int newCoffee = GetIng(inv,"Coffee");
		int newMilk = GetIng(inv,"Milk");
		int newSugar = GetIng(inv,"Sugar");
		int newChocolate = GetIng(inv,"Chocolate");

		assertEquals((F_Coffee + 1),newCoffee );
		assertEquals((F_Milk + 2), newMilk );
		assertEquals((F_Sugar + 3), newSugar );
		assertEquals((F_Chocolate + 4), newChocolate );

	}
	
	@Test
	public void checkInventoryDisplay1()
	{
		String inv = cm.checkInventory();
		
		inv = cm.checkInventory();
		System.out.println("Inventory");
		System.out.println(inv);
	}
	
	@Test
	public void checkMakeCoffee1() throws Exception
	{
		cm.addRecipe(r1);
		int result;

		result = cm.makeCoffee(0,50);
		assertEquals(0, result);

		result = cm.makeCoffee(0,50);
		assertEquals(0, result);

		result = cm.makeCoffee(0, 100);
		assertEquals(50, result);

		result = cm.makeCoffee(0,20);
		assertEquals(20, result);

	} 
	
	private int GetIng(String inv, String target)
	{
		int ing = -1;

		String[] INGs = inv.split("\n");

		for (int i = 0; i < INGs.length; i ++)
		{
			if (INGs[i].contains(target) && INGs[i].contains(":"))
			{
				String[] singleIngred = INGs[i].split(":");
				ing = Integer.parseInt(singleIngred[1].trim());
			}
		}
		return ing;
	}
}