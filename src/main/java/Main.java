import api.*;
import dao.ProductDaoImpl;
import dao.UserDaoImpl;
import entity.Boots;
import entity.Cloth;
import entity.Product;
import entity.User;
import entity.enums.Color;
import entity.enums.Material;
import entity.enums.SkinType;
import entity.parser.ColorParser;
import entity.parser.MaterialParser;
import entity.parser.SkinParser;
import facade.ProductFacadeImpl;
import facade.UserRegisterLoginFacadeImpl;
import service.ProductServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        System.out.println("MANAGEMENT MENU");
        System.out.println("1 - Zaloguj się");
        System.out.println("2 - Zarejestruj się");
        System.out.println("0 - Wyjdź");
    }

    public static void loggedMenu() {
        System.out.println("MANAGEMENT MENU");
        System.out.println("1 - Dodaj nowy produkt");
        System.out.println("2 - Usuń produkt");
        System.out.println("3 - Wyświetl dostepne produkty");
        System.out.println("0 - Wyloguj się");
    }

    public static void productTypeMenu() {
        System.out.println("1 - Dodaj buty");
        System.out.println("2 - Dodaj ubrania");
        System.out.println("3 - Inne");
    }

    public static Product createOtherProduct() {
        String productName;
        Float price, weigth;
        Integer count;
        Color color;

        System.out.println("ProductName: ");
        productName = scanner.next();

        System.out.println("Price: ");
        price = scanner.nextFloat();

        System.out.println("Weight: ");
        weigth = scanner.nextFloat();

        System.out.println("Choose color: RED, BLUE, GREEN, BLACK, WHITE, YELLOW ");
        color = ColorParser.parseStrToColor(scanner.next());

        System.out.println("Count: ");
        count = scanner.nextInt();

        return new Product(1L, productName, price, weigth, color, count);
    }

    public static Product createBootsProduct() {
        String productName;
        Float price, weigth;
        Integer count, size;
        SkinType skinType;
        Color color;


        System.out.println("ProductName: ");
        productName = scanner.next();

        System.out.println("Price: ");
        price = scanner.nextFloat();

        System.out.println("Weight: ");
        weigth = scanner.nextFloat();

        System.out.println("Choose color: RED, BLUE, GREEN, BLACK, WHITE, YELLOW ");
        color = ColorParser.parseStrToColor(scanner.next());

        System.out.println("Count: ");
        count = scanner.nextInt();

        System.out.println("Size: ");
        size = scanner.nextInt();

        System.out.println("Choose skin type: NATURAL, ARTIFICIAL");
        skinType = SkinParser.parseStrToSkinType(scanner.next());

        return new Boots(1L, productName, price, weigth, color, count, size, skinType);
    }

    public static Product createClothProduct() {
        String productName, size;
        Float price, weigth;
        Integer count;
        Color color;
        Material material;

        System.out.println("ProductName: ");
        productName = scanner.next();

        System.out.println("Price: ");
        price = scanner.nextFloat();

        System.out.println("Weight: ");
        weigth = scanner.nextFloat();

        System.out.println("Choose color: RED, BLUE, GREEN, BLACK, WHITE, YELLOW ");
        color = ColorParser.parseStrToColor(scanner.next());

        System.out.println("Count: ");
        count = scanner.nextInt();

        System.out.println("Size: ");
        size = scanner.next();

        System.out.println("Choose material: LEATHER, FUR, COTTON, WOOL, POLYESTERS.");
        material = MaterialParser.parseStrToMaterial(scanner.next());

        return new Cloth(1L, productName, price, weigth, color, count, size, material);
    }

    public static void main(String[] args) {
        UserRegisterLoginFacade userFacade = UserRegisterLoginFacadeImpl.getInstance();
        ProductFacade productFacade = ProductFacadeImpl.getInstance();
        boolean appOn = true;
        boolean loggedOn = false;
        int read;

        while (appOn) {
            startMenu();
            read = scanner.nextInt();

            switch (read) {
                case 1:

                    System.out.println("Podaj login: ");
                    String loginLog = scanner.next();

                    System.out.println("Podaj haslo: ");
                    String passwordLog = scanner.next();

                    if (userFacade.loginUser(loginLog, passwordLog)) {
                        loggedOn = true;
                        System.out.println("Zalogowales sie");
                    } else {
                        System.out.println("Niepoprawy login lub haslo");
                    }
                    break;

                case 2:

                    System.out.println("Podaj login: ");
                    String loginReg = scanner.next();

                    System.out.println("Podaj haslo: ");
                    String passwordReg = scanner.next();

                    if (userFacade.loginUser(loginReg, passwordReg)) {
                        loggedOn = true;
                        System.out.println("Zarejestrowales sie");
                    } else {
                        System.out.println("Cos poszlo nie tak");
                    }
                    break;

                case 0:
                    appOn = false;
                    break;
            }
            while (loggedOn) {
                
                loggedMenu();
                read = scanner.nextInt();

                switch (read) {
                    case 1:
                        productTypeMenu();
                        read = scanner.nextInt();
                        Product product = null;
                        switch (read) {

                            case 1:
                                product = createBootsProduct();
                                break;

                            case 2:
                                product = createClothProduct();
                                break;

                            case 3:
                                product = createOtherProduct();
                                break;
                        }
                        System.out.println(productFacade.createProduct(product));
                        break;
                        
                    case 2:
                        System.out.println("Dostepne produty: " + productFacade.getAllProducts());
                        System.out.println("Podaj nazwe produktu do usuniecia: ");
                        String productName = scanner.next();
                        System.out.println(productFacade.removeProduct(productName));
                        break;
                        
                            case 0:
                                loggedOn = false;
                                break;
                }
            }
        }
    }
}
