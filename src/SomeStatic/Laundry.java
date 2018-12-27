package SomeStatic;

import SomeStatic.MyTrainingExceptions.ClothingException;
import SomeStatic.MyTrainingExceptions.LingerieException;
import SomeStatic.MyTrainingExceptions.PantsException;
import SomeStatic.MyTrainingExceptions.TeeShirtException;

public class Laundry {
    private Boolean triger = true;
    public void doLaundry() throws ClothingException {
        System.out.println("Method doLaundry from class Laundry");
        if (triger.equals(true)) {
            throw new PantsException();

        } else {
            throw new TeeShirtException();
        }
    }

    public Boolean getTriger() {
        return triger;
    }

    public void setTriger(Boolean triger) {
        this.triger = triger;
    }
}
