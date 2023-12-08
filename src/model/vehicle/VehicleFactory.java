package src.model.vehicle;

import src.model.hingeable.LoadingPlatform;
import src.model.hingeable.Ramp;

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
