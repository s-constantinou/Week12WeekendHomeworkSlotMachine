import VendingMachine.Coins.Coin;
import VendingMachine.Coins.coinType;
import VendingMachine.Drawer.Drawer;
import VendingMachine.Drawer.drawerCode;
import VendingMachine.Products.Crisp;
import VendingMachine.Products.Drink;
import VendingMachine.Products.Sweet;
import VendingMachine.VendingMachine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VendingMachineTest {

    Drink IrnBru;
    Crisp CheeseAndOnion;
    Sweet MarsBar;
    Drawer drawer1;
    Drawer drawer2;
    Drawer drawer3;
    VendingMachine vendingMachine;
    Coin one;
    Coin two;
    Coin five;
    Coin ten;
    Coin twenty;
    Coin fifty;


    @Before

    public void setUp() {
        IrnBru = new Drink("Irn Bru", "Barr");
        CheeseAndOnion = new Crisp("Cheese and Onion", "Lays");
        MarsBar = new Sweet("Mars Bar", "Mars");
        drawer1 = new Drawer(drawerCode.A1, 75, IrnBru );
        drawer2 = new Drawer(drawerCode.B1, 60, CheeseAndOnion);
        drawer3 = new Drawer(drawerCode.C1, 90, MarsBar);
        vendingMachine = new VendingMachine();
        vendingMachine.addDrawer(drawer1);
        vendingMachine.addDrawer(drawer2);
        vendingMachine.addDrawer(drawer3);
        one = new Coin(coinType.ONE);
        two = new Coin(coinType.TWO);
        five = new Coin(coinType.FIVE);
        ten = new Coin(coinType.TEN);
        twenty = new Coin(coinType.TWENTY);
        fifty = new Coin(coinType.FIFTY);
    }

    @Test
    public void canAddDrawers() {
        assertEquals(3, vendingMachine.getNumberOfDrawers());
    }

    @Test
    public void canGetCorrectProductFromDrawers() {
        assertEquals(IrnBru, vendingMachine.getProduct(drawer1));
    }

    @Test
    public void canAddCoins() {
        vendingMachine.addCoin(five);
        vendingMachine.addCoin(fifty);
        vendingMachine.addCoin(twenty);
        assertEquals(75, vendingMachine.getTotalCoinValue());
    }

    @Test
    public void cannotAddInvalidCoins() {
        vendingMachine.addCoin(one);
        vendingMachine.addCoin(two);
        assertEquals(2, vendingMachine.getCoinReturnNumberOfCoins());
    }

    @Test
    public void canBuyProduct() {
        vendingMachine.addCoin(five);
        vendingMachine.addCoin(fifty);
        vendingMachine.addCoin(twenty);
        assertEquals(IrnBru, vendingMachine.buy(drawerCode.A1));
    }

    @Test
    public void cannotBuyProductWithUnsufficientFunds() {
        vendingMachine.addCoin(fifty);
        assertEquals(null, vendingMachine.buy(drawerCode.C1));
    }

}