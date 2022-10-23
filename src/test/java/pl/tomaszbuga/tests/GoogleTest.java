package pl.tomaszbuga.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pl.tomaszbuga.framework.BaseTest;
import pl.tomaszbuga.pom.Dog;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class GoogleTest extends BaseTest {
    //String dostępny przez caly czas trwania programu działania programu - static
    //String niezmienialny - niemutowalny - final
    // Inicjalizator instancji
    {
        int a = 10;
    }
    final static String CLASS_VARIABLE = "essa";
    static String des_static= "statyczny string do którego można sie dostać odwołując się bezpośredni do klasy bez tworzenia obiektu";
    @Test()
    public void firstMethod() {
        // Assign
        // Act
        // Assert
        String query = "pudzian";
        getDriver().get("https://www.google.com/search?q=" + query);
        getDriver().findElement(By.id("L2AGLb")).click();
        List<WebElement> elements = getDriver().findElement(By.id("rso")).findElements(By.tagName("h3"));
        for (WebElement element: elements){
            System.out.println(element.getText());
            if (element.getText().equals("Videos") || element.getText().equals("Grafika") ||element.getText().equals(" ")){
                continue;
            }
        }


        /**
        double random_val = 12;
        boolean check = random_val < Math.random() * Math.random() ? true : false;
        System.out.println(check);

        int condition = 12;

        String casing = switch (condition) {
            case 1, 2, 3 -> "one";
            case 4, 5, 6 -> "two";
            // yield is required when case is no one line, to return something
            case 7, 8, 9 -> {
                yield "Bump";
            }
            default -> "Default value";
        };

        enum Seasons {Winter, Spring, Summer, Fall};

        Seasons s = Seasons.Spring;
        String result = switch (s){
            case Winter -> "zimno";
            case Spring -> "muchy";
            case Summer -> "ale gorąco";
            case Fall -> "ale leje";
        };
        System.out.println(result);

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i <= arr.length; i++){
            System.out.println(arr[i]);
        }

        // for each - każdy element z kolekcji, tablicy lub string
        for (int alfa: arr){
            System.out.println(alfa);
        }
        */


        /*
        Dog pimpek = new Dog("Suzuki");
        Dog pimpek2 = new Dog("Maya");
        pimpek.get_name();
        pimpek.get_breed();
        pimpek2.get_name();
        pimpek2.get_breed();
         */

    }

    public void playground2() {
        String a = "alfa";
        a.length(); // display length of the string
        a.charAt(1); // display char at typed index
        a.indexOf("a", 1); //return index of typed value, fromindex - start at given index
        a.substring(3); //divine string at typed index
        a.toLowerCase(Locale.ROOT);
        a.equalsIgnoreCase(a); // ignore type of chars, just compare them
        a.startsWith("b"); // false
        a.endsWith("a"); // true
        a.contains("f"); // true
        a.trim(); // delete white spaces
        a.strip(); // delete white spaces
        a.stripLeading(); // delete from left site
        a.stripTrailing(); // delete from right site
        a.equals(a); //check if string is the same as typed
        a.isEmpty(); // always true if there is any char
        a.isBlank(); // true if there is no any char, white spaces are skipped

        String alfa = "a";
        for(char current = 'a'; current <= 'z'; current++){
            alfa += current;
        }
        System.out.println(alfa);

        var x = "Hello World";
        var y = "Hello World";
        System.out.println(x == y); // true

        var z = " Hello World";
        var v = " Hello World";

        System.out.println(z == v.trim()); // false

        int[] first_way = new int[12];
        int[] second_way = new int[] {12,32, 15, 22};
        int[] third_way = {12, 32, 15, 44};

        String[] robaczki = {"żuk", "taki czarny", "biedronka"};
        String[] alias = robaczki; // this is not copy of robaczki, it's only reference to the same place in the memory

        // formatowanie stringów
        String name =  "Kanye";
        System.out.println("Hello " + name + " what's up");
        System.out.println(String.format("Hello %s, what's up", name));
        System.out.println("Hello %s, what's up! %nThere is %d x's".formatted(name, 12)); //%n new line

        // Method Chaining
        a.toLowerCase(Locale.ROOT).trim().replace("a", "aa");
    }

    @Test()
    public void arrays(){
        int[] arr = {11,4,3,4,5,6};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }

        String[] alfa = {"10", "9", "100"};
        Arrays.sort(alfa);
        for (int i = 0; i < alfa.length; i++){
            System.out.print(alfa[i] + " ");
        }

        int[] search = {1,3,4,6,10,12};
        System.out.println(Arrays.binarySearch(search, 2)); // -1 because 2 should be on the index 1 but there is not then -

        int[][] double_array = {{1,2,3}, {4,5,6}};
        int[] dbl_arr[]; // this is also two dimension array

        Math.min(12,3);
        Math.max(12,3);
        Math.round(12.31);
        Math.ceil(3.15); // 4.00
        Math.floor(3.15); // 3.00
        Math.pow(5, 2); // potęgowanie
        System.out.println("Album_id=" + Math.random());

    }

    @Test()
    public void timeAndDate(){
        LocalDate data = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime localDate = LocalDateTime.now();
        ZonedDateTime zonedTime = ZonedDateTime.now();
        System.out.println(data);
        System.out.println(time);
        System.out.println(localDate);
        System.out.println(zonedTime);

        var date = LocalDate.of(2022, Month.APRIL, 29);
        System.out.println(date);

        var localTime = LocalTime.of(12,32,2,3);

        ZoneId zoneId = ZoneId.of("EST");
        var zoneTime = ZonedDateTime.of(date,localTime, zoneId);

        var annual = Period.ofYears(1);

        // Way to check time laps to do something
        var now =  Instant.now();
        // code to do
        var later = Instant.now();
        var duration = Duration.between(now, later);
        System.out.println(duration.toMillis());

    }
    // może być wywołana tylko w ramach tej samej klasy
    private void privMethod(){
    }
    // wywoływana w tej samej klasie znajdujących się w tym samym pakiecie
    protected  void protectedMethod(){
    }
    // wywoływana gdziekolwiek w programie
    public void publicClass(){
    }
    // dostęp w ramach tych samych pakietów
    void packageAccess(){
    }
    /**
     * static - metoda należy do współdzielonego obiektu klasy
     * abstract - występuje w klasach/metodach abstrakcyjnych kiedy ciało metody jest pomijane
     * final - oznacza że metoda nie może zostać nadpisana
     * default - występuje w interfejsach i zapewna domyślną implementacje
     * synchronized - wykorzystywane w kodowaniu wielowątkowym
     */


    @Test()
    private void lesson20_10(){
        String name = "Jerry";
        var size = 10;
        boolean what = true;

        if(size > 100) size++;
        name.substring(0);
        what = true;
    }

    // varargs always must be as last argument in method
    private void walk1(int... steps){}
    private void walk2(int step, int... starts){}


    public int multipleVars(int... steps) {
        int[] stp = steps;
        return stp[0];
    }

    public void scream(){
        multipleVars(12,32);
    }
    
    
    public void ahhSaturnday(){
        int i = 5;
        // Autoboxing - change simple type to wrapper class
        Integer random = i;
        // Unboxing - change wrapper class to simple type
        int a = random;

    }
    // It
    public void example12(int[] alfa){}
    public void example1(int... alfa){}

    // final - po klasie final nie można dziedziczyć
    // abstract - może zawierać metody abstrakcyjne i wymaga normalnej klasy do inicjalizacji
    // sealed - klasa może być dziedziczona tylko przez określoną listę klas
    // non-sealed - podklasa klasy sealed dopuszcza potencjalne nieokreślone podklasy
    // static - wykorzystywanie dla klasy typu static zagnieżdżonych w danej klasie




//
//
//    @Test()
//    public void playground(boolean check){
//        if(check){
//            int i = 0;
//
//        }
//        try {
//            System.out.println(i);
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//
//        // ! make negative val from given val
//        boolean of_course = true;
//        System.out.println(!of_course);
//
//        // ~ flip 01s val to reverted val
//        int flip_binary_val = 25;
//        System.out.println(~flip_binary_val);
//
//        //cast int to double
//        int alfa = 12;
//        double beta = (double) alfa;
//        System.out.println(beta);
//
//        //logical operator ^ true if one val is true and another is false
//        // & check both sides of id
//        // && check left side, and if false skip
//        // || true if at least one val is true
//    }

    /**
     * Kryteria automatyzacji:
     * - czestotliwość używania - automatyzujemy testy, które są wykonywane bardzo czesto
     * - złożoność automatyzacji - proste aplikacje czesto nie wymagają testów automatycznych,
     * - kompatybilność i złożoność narzędzi - czy istnieje narzędzie testowe do automatyzacji testów
     * !- Cucumber language - sprawdzić -!
     * - dojrzałość procesu testowego - młody projekt może się zmieniać i testy sie zdezaktualizują
     * - zasadność automatyzacji na danym etapie cyklu oprogramowania - czy dany etap rozwoju appki jest odp. na automatyzację
     * - trwałość środowiska
     * - sterowalność systemu podlegającego testowaniu (warunki wstępne, konfiguracja i stabilność)
     * - planowanie techniczne na potrzeby analizy zwrotu z inwestycji - ROI
     * - dostępność narzędzi umożliwiających automatyzację testów w środowsku testowym
     * - poprawność danych testowych i przypadków testowych
     * - zakres prac związanych z automatyzacją testów
     * - uświadomienie zespołowi testowemu skutków zmiany paradygmatu
     * - role i odpowiedzialności
     * - współpraca między programistami a inżynierami automatyzacji testów
     * - równoległe wykonywanie prac - skrypty automatyczne są pisane podczas wykonywania testów manualnych
     * - raportowanie nt. automatyzacji testów - przedstawienie wyników testów
     */

    /**
     * Core API
     * Concat
     * 1+2 //3
     * "a" + "b" // ab
     * "a" + "b" + 3 // ab3
     * 1 + 2 + "c" // 3c
     * "c" + 1 + 2 // c12
     * "c" + null // cnull
     *
     */

}


