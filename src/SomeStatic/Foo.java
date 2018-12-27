package SomeStatic;

import SomeStatic.MyTrainingExceptions.ClothingException;
import SomeStatic.MyTrainingExceptions.LingerieException;
import SomeStatic.MyTrainingExceptions.PantsException;

public class Foo {
    public void go() {
        Laundry laundry = new Laundry();
        try {
            System.out.println("Begin try from Foo.go()");
            laundry.doLaundry();
        } catch (PantsException pe) {
            System.out.println("Catch from Foo PantException");
        } catch (LingerieException le) {
            System.out.println("Catch from Foo LingerieException");
        } catch (ClothingException ce) {
            System.out.println("Catch from Foo ClothingException");
        }
    }
}
