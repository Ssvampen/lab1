package src.vehicle;

import src.hingeable.LoadingPlatform;
import src.hingeable.Ramp;

public class VehicleFactory {
    public static Vehicle createNewVolvo240() {
        return new Volvo240();
    }

    public static Vehicle createNewSaab95() {
        return new Saab95();
    }

    public static Vehicle createNewScania(LoadingPlatform loadingPlatform) {
        return new Scania(loadingPlatform);
    }

    public static Vehicle createNewLorry(Ramp ramp) {
        return new Lorry(ramp);
    }
}
